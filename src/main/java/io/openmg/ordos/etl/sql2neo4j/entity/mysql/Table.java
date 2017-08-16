package io.openmg.ordos.etl.sql2neo4j.entity.mysql;

import io.openmg.ordos.etl.sql2neo4j.entity.mysql.Column;

import java.util.List;

/**
 * Created by zhaoliang on 2017/8/9.
 */
public class Table {
    private String tableName;
    private List<Column> columns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableName='" + tableName + '\'' +
                ", columns=" + columns +
                '}';
    }
}

