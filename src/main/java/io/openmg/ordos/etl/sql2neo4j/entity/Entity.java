package io.openmg.ordos.etl.sql2neo4j.entity;

import io.openmg.ordos.etl.sql2neo4j.entity.csv.CSVSchema;
import io.openmg.ordos.etl.sql2neo4j.entity.mysql.Mysql;

import java.util.List;

/**
 * Created by zhaoliang on 2017/8/9.
 */
public class Entity {
    private String dataSource;
    private Mysql mysql;
    private Graph neo4jGraph;
    private List<CSVSchema> csv;

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

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

    public List<CSVSchema> getCsv() {
        return csv;
    }

    public void setCsv(List<CSVSchema> csv) {
        this.csv = csv;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "dataSource='" + dataSource + '\'' +
                ", mysql=" + mysql +
                ", neo4jGraph=" + neo4jGraph +
                ", csv=" + csv +
                '}';
    }
}
