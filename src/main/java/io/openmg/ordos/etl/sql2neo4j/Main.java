package io.openmg.ordos.etl.sql2neo4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.openmg.ordos.etl.sql2neo4j.entity.Entity;
import io.openmg.ordos.etl.sql2neo4j.entity.Graph;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.tinkerpop.gremlin.neo4j.structure.Neo4jGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

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

        /* neo4j graph加载。*/
        PropertiesConfiguration configuration = new PropertiesConfiguration();
        Graph neo4jGraphEntity = entity.getNeo4jGraph();
        configuration.addProperty("gremlin.neo4j.directory", neo4jGraphEntity.getDirectory() + System.currentTimeMillis());
        configuration.addProperty("gremlin.graph", neo4jGraphEntity.getGremlinGraph());
        Neo4jGraph graph = Neo4jGraph.open(configuration);

        String dataSource = entity.getDataSource();
        if (dataSource.equalsIgnoreCase("mysql")) {
            new Thread(new Mysql2Graph(entity.getMysql(), graph)).start();
        } else if (dataSource.equalsIgnoreCase("csv")) {
            new Thread(new Csv2Graph(entity.getCsv(), graph)).start();
        }
    }
}
