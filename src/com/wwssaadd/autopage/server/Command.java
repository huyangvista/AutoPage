package com.wwssaadd.autopage.server;

/**
 * Created by Hocean on 2017/6/6.
 */
public class Command {
    private String action;
    private String value;
    private Object obj;

    private String actionData;
    private String driverName; // = "com.mysql.jdbc.Driver";
    private String dbURL;      // = "jdbc:mysql://vives.cc:3306/tbi_erp?useUnicode=true&characterEncoding=utf8";
    private String username;   // = "root";
    private String password;   // = "hoceanvista";
    private String sql;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDbURL() {
        return dbURL;
    }

    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getActionData() {
        return actionData;
    }

    public void setActionData(String actionData) {
        this.actionData = actionData;
    }
}
