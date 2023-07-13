package com.yonchando.autobot.database;

import lombok.Data;

import java.sql.*;
import java.util.Properties;

@Data
public class DBConnect implements DBConnectInterface {

    private final String DB_HOST;
    private final String DB_PORT;

    public DBConnect() {
        DB_HOST = System.getenv("DB_HOST").isEmpty() ? "localhost" : System.getenv("DB_HOST");
        DB_PORT = System.getenv("DB_PORT").isEmpty() ? "5432" : System.getenv("DB_PORT");
    }

    @Override
    public Connection initial() throws SQLException {
        String url = "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + System.getenv("DB_NAME");
        Properties props = new Properties();
        props.setProperty("user", System.getenv("DB_USER"));
        props.setProperty("password", System.getenv("DB_PASSWORD"));

        return DriverManager.getConnection(url, props);
    }
}
