package com.cmcc.pay.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ech0 on 2016/4/3.
 */
public class DBHelper {

        public static  String url  = "";
        public static  String name = "";
        public static  String user  = "";
        public static  String password = "";

        public Connection conn = null;
        public PreparedStatement pst = null;

        public DBHelper(String sql) {
            try {
                Class.forName(name);//指定连接类型
                conn = DriverManager.getConnection(url, user, password);//获取连接
                pst = conn.prepareStatement(sql);//准备执行语句
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void close() {
            try {
                this.conn.close();
                this.pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
