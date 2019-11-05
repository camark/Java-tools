package com.chinasuse;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDbUtil {

    /**** 数据库连接*** */

    public static Connection getConn(String host,String db,String userName,String password) {
        Connection conn = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://"+host+"/"+db+"?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        DbUtils.loadDriver(driver);
        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void CloseConn(Connection conn){
        DbUtils.closeQuietly(conn);
    }
}
