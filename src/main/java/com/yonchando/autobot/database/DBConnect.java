package com.yonchando.autobot.database;

import lombok.Data;

import java.sql.*;
import java.util.Properties;

@Data
public class DBConnect implements DBConnectInterface {

    @Override
    public Connection initial() throws SQLException {
        String url = "jdbc:postgresql://" + System.getenv("DB_HOST") + ":" + System.getenv("DB_PORT") + "/auto_mention_bot";
        Properties props = new Properties();
        props.setProperty("user", System.getenv("DB_USER"));
        props.setProperty("password", System.getenv("DB_PASSWORD"));

        return DriverManager.getConnection(url, props);
    }
}
