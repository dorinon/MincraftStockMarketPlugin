package dorinon.market.commands;

import dorinon.market.Inventories;
import dorinon.market.SQLLite;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

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
