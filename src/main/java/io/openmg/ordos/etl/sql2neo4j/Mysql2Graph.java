package io.openmg.ordos.etl.sql2neo4j;

import io.openmg.ordos.etl.sql2neo4j.entity.mysql.Column;
import io.openmg.ordos.etl.sql2neo4j.entity.mysql.Mysql;
import io.openmg.ordos.etl.sql2neo4j.entity.mysql.Table;
import org.apache.tinkerpop.gremlin.structure.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * write data to graph from mysql.
 * Created by zhaoliang on 2017/8/16.
 */
public class Mysql2Graph implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private Mysql mysql;
    private org.apache.tinkerpop.gremlin.structure.Graph graph;

    public Mysql2Graph(Mysql mysql, org.apache.tinkerpop.gremlin.structure.Graph graph) {
        this.mysql = mysql;
        this.graph = graph;
    }

    @Override
    public void run() {
        try {
            write();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    private void write() throws Exception {
        String mysqlDriver = mysql.getDriver();
        String databaseURL = mysql.getDatabaseURL();
        String user = mysql.getUser();
        String password = mysql.getPassword();

        /* 数据库链接。 */
        Class.forName(mysqlDriver);
        Connection connection = DriverManager.getConnection(databaseURL, user, password);
        Statement statement = connection.createStatement();

        List<Table> tables = mysql.getTables();

        for (Table table : tables) {
            /* 准备相关参数，组建sql查询语句。 */
            List<Column> columnsEntity = table.getColumns();
            String tableName = table.getTableName();
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

        }
        connection.close();
        graph.close();

    }
}
