package io.openmg.ordos.etl.sql2neo4j.entity.mysql;

/**
 * Created by zhaoliang on 2017/8/9.
 */
public class Column {
    private String name;
    private String valueType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", valueType='" + valueType + '\'' +
                '}';
    }
}
