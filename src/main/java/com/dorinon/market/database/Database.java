package com.dorinon.market.database;

import com.dorinon.market.OfferType;
import com.google.gson.Gson;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

        int currentSchema = connection.createStatement().executeQuery("PRAGMA user_version").getInt(1);
        for (int i = currentSchema; i < SCHEME_UPGRADE_HANDLERS.size(); i++) SCHEME_UPGRADE_HANDLERS.get(i).accept(connection);
        connection.createStatement().execute("PRAGMA user_version = %d".formatted(SCHEME_UPGRADE_HANDLERS.size()));
    }

    public void disconnect() throws SQLException {
        if (connection == null) return;
        connection.close();
    }

    private final RepeatableStatement saveOfferStatement = new RepeatableStatement("""
                INSERT INTO offers (player_uuid, item_id, amount, offer_type, cost)
                VALUES (?, ?, ?, ?, ?)
                """);

    public void saveOffer(@NotNull UUID uuid, ArrayList<String> itemIdentifiers, int amount, OfferType offerType, double cost) throws SQLException {
        PreparedStatement statement = saveOfferStatement.get(connection);
        statement.setString(1, uuid.toString());
        statement.setString(2, new Gson().toJsonTree(itemIdentifiers).toString());
        statement.setInt(3, amount);
        statement.setInt(4, offerType.toInt());
        statement.setDouble(5, cost);
        statement.execute();
    }

    @FunctionalInterface
    private interface UpgradeHandler {
        void accept(Connection connection) throws SQLException;
    }

    private static final class RepeatableStatement {
        private final String statementString;
        private PreparedStatement preparedStatement;

        public RepeatableStatement(String statement) {
            statementString = statement;
        }

        private @NotNull PreparedStatement get(@NotNull Connection connection) throws SQLException {
            if (preparedStatement == null) preparedStatement = connection.prepareStatement(statementString);
            return preparedStatement;
        }
    }
}
