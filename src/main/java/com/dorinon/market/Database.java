package com.dorinon.market;

import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database {

    private final Connection connection;

    Database(Plugin plugin) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:%s".formatted(plugin.getDataFolder().toPath().resolve("database.db")));
    }

    public void getItemPrice() {

    }
}
