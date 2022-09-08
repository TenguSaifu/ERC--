package com.dao;

import java.sql.*;

public class BaseDao {

    private Connection conn;

    public BaseDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/Project",
                        "root", "root");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to get database connection");
        }
        System.out.println("Get database connection succeeded!");
        return conn;
    }

    public void release(Connection conn, PreparedStatement st, ResultSet rs) {
        if (rs != null) {
            try {
                //关闭存储查询结果的ResultSet对象
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if (st != null) {
            try {
                //关闭负责执行SQL命令的Statement对象
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                //将Connection连接对象还给数据库连接池
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
