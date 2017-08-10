package io.openmg.ordos.etl.sql2neo4j.entity;

/**
 * mysql entity.
 * Created by zhaoliang on 2017/8/9.
 */
public class Mysql {
    private String driver;
    private String databaseURL;
    private String user;
    private String password;
    private Table table;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }


    public String getDatabaseURL() {
        return databaseURL;
    }

    public void setDatabaseURL(String databaseURL) {
        this.databaseURL = databaseURL;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "Mysql{" +
                "driver='" + driver + '\'' +
                ", databaseURL='" + databaseURL + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", table=" + table +
                '}';
    }
}
