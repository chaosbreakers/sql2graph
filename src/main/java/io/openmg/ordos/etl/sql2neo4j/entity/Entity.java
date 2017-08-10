package io.openmg.ordos.etl.sql2neo4j.entity;

/**
 * Created by zhaoliang on 2017/8/9.
 */
public class Entity {
    private Mysql mysql;
    private Graph neo4jGraph;

    public Mysql getMysql() {
        return mysql;
    }

    public void setMysql(Mysql mysql) {
        this.mysql = mysql;
    }

    public Graph getNeo4jGraph() {
        return neo4jGraph;
    }

    public void setNeo4jGraph(Graph neo4jGraph) {
        this.neo4jGraph = neo4jGraph;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "mysql=" + mysql +
                ", neo4jGraph=" + neo4jGraph +
                '}';
    }
}
