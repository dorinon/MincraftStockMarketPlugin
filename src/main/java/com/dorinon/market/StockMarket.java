package com.dorinon.market;

import com.dorinon.market.commands.OpenStockMarket;
import com.dorinon.market.database.Database;
import com.dorinon.market.listeners.ProtectInventories;
import com.dorinon.market.crypto.Crypto;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public final class StockMarket extends JavaPlugin {
    private Database database;

    @Override
    public void onEnable() {
        try {
            database = new Database(this);

            Crypto crypto = new Crypto(this);

            this.getCommand("stockmarket").setExecutor(new OpenStockMarket(database));
            this.getServer().getPluginManager().registerEvents(new ProtectInventories(), this);

        } catch (SQLException | IOException | NoSuchAlgorithmException | InvalidKeySpecException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        try {
            database.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
