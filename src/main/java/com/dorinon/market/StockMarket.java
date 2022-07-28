package com.dorinon.market;

import com.dorinon.market.commands.OpenStockMarket;
import com.dorinon.market.listeners.ProtectInventories;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class StockMarket extends JavaPlugin {
    @Override
    public void onEnable() {
        try {

            Database database = new Database(this);

            this.getCommand("stockmarket").setExecutor(new OpenStockMarket());
            this.getServer().getPluginManager().registerEvents(new ProtectInventories(), this);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
