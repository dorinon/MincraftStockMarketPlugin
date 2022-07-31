package com.dorinon.market.commands;

import com.dorinon.market.StockMarketMenu;
import com.dorinon.market.database.Database;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class OpenStockMarket implements CommandExecutor {

    private Database database;

    public OpenStockMarket(Database database){
        this.database = database;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;


        return true;
    }
}
