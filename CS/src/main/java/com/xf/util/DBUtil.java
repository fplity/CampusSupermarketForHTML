package com.xf.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = environment("DB_URL", "jdbc:mysql://localhost:3306/cs");
            String username = environment("DB_USER", "root");
            String password = System.getenv("DB_PASSWORD");
            if (password == null) {
                throw new IllegalStateException("请设置 DB_PASSWORD 环境变量");
            }
            DriverManager.setLoginTimeout(5);
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String environment(String name, String defaultValue) {
        String value = System.getenv(name);
        return value == null || value.isBlank() ? defaultValue : value;
    }
}
