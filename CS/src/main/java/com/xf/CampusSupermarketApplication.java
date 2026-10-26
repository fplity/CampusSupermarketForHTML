package com.xf;

import com.xf.util.DBUtil;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.Statement;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.xf.servlet")
public class CampusSupermarketApplication {
    public static void main(String[] args) {
        SpringApplication.run(CampusSupermarketApplication.class, args);
    }

    @Bean
    ApplicationRunner verifyDatabase() {
        return args -> {
            try (Connection connection = DBUtil.getConnection();
                 Statement statement = connection.createStatement()) {
                statement.setQueryTimeout(5);
                try (var ignored = statement.executeQuery("select sid,sname,price,image,stock from shopping limit 0")) {
                    // Check the existing schema without changing its rows.
                }
                try (var ignored = statement.executeQuery("select account,password,tel from `user` limit 0")) {
                    // No accounts or passwords are read or written here.
                }
            } catch (Exception failure) {
                throw new IllegalStateException(
                        "数据库检查失败：请检查 MySQL、cs 数据库、表结构以及 DB_URL / DB_USER / DB_PASSWORD 配置。",
                        failure);
            }
        };
    }
}
