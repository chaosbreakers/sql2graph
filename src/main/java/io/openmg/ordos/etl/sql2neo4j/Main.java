package io.openmg.ordos.etl.sql2neo4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.openmg.ordos.etl.sql2neo4j.entity.Column;
import io.openmg.ordos.etl.sql2neo4j.entity.Entity;
import io.openmg.ordos.etl.sql2neo4j.entity.Graph;
import io.openmg.ordos.etl.sql2neo4j.entity.Mysql;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.tinkerpop.gremlin.neo4j.structure.Neo4jGraph;
import org.apache.tinkerpop.gremlin.structure.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 函数入口。
 * Created by zhaoliang on 2017/8/9.
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {

        /*  加载config.json配置文件，读取相关配置参数。 */
        Entity entity = mapper.readValue(Main.class.getClassLoader().getResource("conf/sql2neo4j.json"), Entity.class);
        logger.info("加载配置文件：{}", mapper.writeValueAsString(entity));
        Objects.requireNonNull(entity);
        Mysql mysql = entity.getMysql();
        String mysqlDriver = mysql.getDriver();
        String databaseURL = mysql.getDatabaseURL();
        String user = mysql.getUser();
        String password = mysql.getPassword();

        /* neo4j graph加载。*/
        PropertiesConfiguration configuration = new PropertiesConfiguration();
        Graph neo4jGraphEntity = entity.getNeo4jGraph();
        configuration.addProperty("gremlin.neo4j.directory", neo4jGraphEntity.getDirectory());
        configuration.addProperty("gremlin.graph", neo4jGraphEntity.getGremlinGraph());
        Neo4jGraph graph = Neo4jGraph.open(configuration);


        /* 数据库链接。 */
        Class.forName(mysqlDriver);
        Connection connection = DriverManager.getConnection(databaseURL, user, password);
        Statement statement = connection.createStatement();

        /* 准备相关参数，组建sql查询语句。 */
        List<Column> columnsEntity = mysql.getTable().getColumns();
        String tableName = mysql.getTable().getTableName();
        List<String> columns = columnsEntity.stream().map(Column::getName).collect(Collectors.toList());
        String sql = String.format("SELECT %s FROM %s", String.join(",", columns), tableName);
        logger.info("执行sql的语句为：{}", sql);

        /* 执行查询。 */
        ResultSet rs = statement.executeQuery(sql);

        graph.tx().open();
        while (rs.next()) {
            ArrayList<Object> objects = new ArrayList<>();
            columnsEntity.forEach((Column column) -> {

                String name = column.getName();
                String valueType = column.getValueType();
                Object value = null;
                switch (valueType) {
                    case "string":
                        try {
                            value = rs.getString(name);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "int":
                        try {
                            value = rs.getInt(name);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "long":
                        try {
                            value = rs.getLong(name);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        try {
                            value = rs.getObject(name);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                }
                objects.add(name);
                objects.add(value);

            });
            objects.add(T.label);
            objects.add(tableName);
            graph.addVertex(objects.toArray());
            logger.info("添加数据：" + objects.toString());
        }
        graph.tx().commit();
        connection.close();
        graph.close();

//        List<Vertex> student = graph.traversal().V().has(T.label, P.eq("student")).toList();
//        student.forEach(vertex -> System.out.println(String.format("%s,%s,%s,%s", vertex.property("name").value(),
//                vertex.property("addr").value(),
//                vertex.property("teacher").value(),
//                vertex.property("age").value())));
    }
}
