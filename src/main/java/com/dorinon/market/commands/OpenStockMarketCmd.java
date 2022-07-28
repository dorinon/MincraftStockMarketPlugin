package com.dorinon.market.commands;

import com.dorinon.market.Inventories;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenStockMarketCmd implements CommandExecutor {

    private Inventories inventories = new Inventories();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player))return false;

        Player player = (((Player) sender).getPlayer());

        player.openInventory(inventories.getStockMarketInv());

        return true;
    }
}
