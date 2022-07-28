package dorinon.market;

import dorinon.market.commands.OpenStockMarketCmd;
import dorinon.market.listeners.InvProtect;
import org.bukkit.plugin.java.JavaPlugin;

public final class StockMarket extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("stockmarket").setExecutor(new OpenStockMarketCmd());
        getServer().getPluginManager().registerEvents(new InvProtect(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
