package com.dorinon.market;

import com.dorinon.market.commands.OpenStockMarketCmd;
import com.dorinon.market.listeners.InvProtect;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class StockMarket extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            Database database = new Database(this);

            this.getCommand("stockmarket").setExecutor(new OpenStockMarketCmd());
            getServer().getPluginManager().registerEvents(new InvProtect(), this);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
