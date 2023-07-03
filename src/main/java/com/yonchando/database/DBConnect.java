package com.yonchando.database;

import lombok.Data;

import java.sql.*;
import java.util.Properties;
import java.util.Set;

@Data
public class DBConnect {

    protected Connection connection;
    protected Statement statement;
    protected ResultSet resultSet;

    public DBConnect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5433/auth_mention_bot";
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "secret");
        connection = DriverManager.getConnection(url, props);
        statement = connection.createStatement();
    }

    public void close() throws SQLException {
        resultSet.close();
        statement.close();
    }
}
