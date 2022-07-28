package com.dorinon.market;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

public final class Database {
    private final static String DATABASE_FILE_NAME = "database.db";
    private final static int DATABASE_SCHEMA_VERSION = 1;

    private final Connection connection;

    public Database(@NotNull Plugin plugin) throws SQLException {
        plugin.getDataFolder().mkdir();

        connection = DriverManager.getConnection(
                "jdbc:sqlite:%s".formatted(plugin.getDataFolder().toPath().resolve(DATABASE_FILE_NAME))
        );

        handleSchema();
    }

    private void handleSchema() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("PRAGMA USER_VERSION");
        System.out.println(rs);
    }
}
