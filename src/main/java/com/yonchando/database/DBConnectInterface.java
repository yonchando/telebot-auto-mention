package com.yonchando.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnectInterface {
    Connection initial() throws SQLException;
}
