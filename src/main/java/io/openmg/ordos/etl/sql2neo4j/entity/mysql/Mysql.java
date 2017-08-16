package io.openmg.ordos.etl.sql2neo4j.entity.mysql;

import io.openmg.ordos.etl.sql2neo4j.entity.mysql.Table;

import java.util.List;

/**
 * mysql entity.
 * Created by zhaoliang on 2017/8/9.
 */
public class Mysql {
    private String driver;
    private String databaseURL;
    private String user;
    private String password;
    private List<Table> tables;

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

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
