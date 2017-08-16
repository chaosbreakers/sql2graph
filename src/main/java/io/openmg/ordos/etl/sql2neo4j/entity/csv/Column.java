package io.openmg.ordos.etl.sql2neo4j.entity.csv;

/**
 * Created by zhaoliang on 2017/8/16.
 */
public class Column {
    private int pos;
    private String name;
    private String valueType;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

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
                "pos=" + pos +
                ", name='" + name + '\'' +
                ", valueType='" + valueType + '\'' +
                '}';
    }
}
