package io.openmg.ordos.etl.sql2neo4j.entity.csv;

import java.util.List;

/**
 * Created by zhaoliang on 2017/8/16.
 */
public class CSVSchema {
    private String path;
    private String label;
    private List<Column> columns;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
