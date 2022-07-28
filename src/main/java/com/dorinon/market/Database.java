package com.dorinon.market;

import org.bukkit.plugin.Plugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class Database {

    private final Connection connection;

    public Database(Plugin plugin) throws SQLException {
        File databaseFile = plugin.getDataFolder().toPath().resolve("database.db").toFile();

        connection = DriverManager.getConnection("jdbc:sqlite:%s".formatted(databaseFile.getAbsolutePath()));

        System.out.println(connection);
        System.out.println(databaseFile);
    }

    public void getItemPrice() {

    }
}
