package com.dorinon.market.commands;

import com.dorinon.market.Database;
import com.dorinon.market.Inventories;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.sql.SQLException;

public class OpenStockMarketCmd implements CommandExecutor {

    Plugin plugin;

    public OpenStockMarketCmd(Plugin plugin){
        this.plugin = plugin;
    }

    private Inventories inventories = new Inventories();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player))return false;

        Player player = (((Player) sender).getPlayer());

        try {
            Database database = new Database(plugin);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        player.openInventory(inventories.getStockMarketInv());

        return true;
    }
}
