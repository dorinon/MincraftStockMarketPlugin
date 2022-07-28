package com.dorinon.market;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.List;

public final class Database {
    private final static String DATABASE_FILE_NAME = "database.db";

    private final static List<UpgradeHandler> SCHEME_UPGRADE_HANDLERS = List.of(
            connection -> connection.createStatement().execute("""
                    CREATE TABLE offers
                    (
                        player_uuid TEXT NOT NULL,
                        item_id     TEXT NOT NULL,
                        amount      INT DEFAULT 1,
                        offer_type  INT  NOT NULL,
                        cost        REAL NOT NULL
                    )
            """)
    );

    private final Connection connection;

    public Database(@NotNull Plugin plugin) throws SQLException {
        plugin.getDataFolder().mkdir();
        connection = DriverManager.getConnection("jdbc:sqlite:%s".formatted(plugin.getDataFolder().toPath().resolve(DATABASE_FILE_NAME)));
        handleSchema(connection);
    }

    private void handleSchema(@NotNull Connection connection) throws SQLException {
        int currentSchema = connection.createStatement().executeQuery("PRAGMA user_version").getInt(1);
        for (int i = currentSchema; i < SCHEME_UPGRADE_HANDLERS.size(); i++) SCHEME_UPGRADE_HANDLERS.get(i).accept(connection);
        connection.createStatement().execute("PRAGMA user_version = %d".formatted(SCHEME_UPGRADE_HANDLERS.size()));
    }

    public void disconnect() throws SQLException {
        if (connection == null) return;
        connection.close();
    }

    @FunctionalInterface
    private interface UpgradeHandler {
        void accept(Connection connection) throws SQLException;
    }
}
