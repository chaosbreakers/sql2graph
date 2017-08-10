package io.openmg.ordos.etl.sql2neo4j.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhaoliang on 2017/8/9.
 */
public class Graph {
    @JsonProperty("gremlin.neo4j.directory")
    private String directory;

    @JsonProperty("gremlin.graph")
    private String gremlinGraph;

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getGremlinGraph() {
        return gremlinGraph;
    }

    public void setGremlinGraph(String gremlinGraph) {
        this.gremlinGraph = gremlinGraph;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "directory='" + directory + '\'' +
                ", gremlinGraph='" + gremlinGraph + '\'' +
                '}';
    }
}
