package com.yonchando.database;

import lombok.Data;

import java.sql.*;
import java.util.Properties;

@Data
public class DBConnect implements DBConnectInterface {

    @Override
    public Connection initial() throws SQLException {
        String url = "jdbc:postgresql://localhost:5433/auto_mention_bot";
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "secret");

        return DriverManager.getConnection(url, props);
    }
}
