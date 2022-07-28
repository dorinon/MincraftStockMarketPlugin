package com.dorinon.market;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class Database {

    private final Connection connection;
    private Statement stmt;

    public Database(Plugin plugin) throws SQLException {

        File dataFolder = new File(plugin.getDataFolder().getAbsolutePath());
        dataFolder.mkdir();

        File databaseFile = plugin.getDataFolder().toPath().resolve("database.db").toFile();

        connection = DriverManager.getConnection("jdbc:sqlite:%s".formatted(databaseFile.getAbsolutePath()));

        if (connection.){}
    }

    public void getItemPrice() {

    }
}
