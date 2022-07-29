package com.dorinon.market;

import com.dorinon.market.database.Database;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class StockMarketInv {

    private Player player;
    private Database database;

    public StockMarketInv(Player player, Database database){
        this.player = player;
        this.database = database;
    }

    private int pageNum = 1;
    private ItemStack changePage = new ItemStack(Material.PAPER);
    private ItemStack toggleView = new ItemStack(Material.NETHER_STAR);
    private TradingModes states = TradingModes.Sell;


    public @NotNull Inventory getDefaultStockMarketInv() {

        String menu = ChatColor.WHITE + "" + ChatColor.BOLD + "Main Menu";
        switch (states){
            case Options -> menu = ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Options";
            case Futures ->menu = ChatColor.BLUE + "" + ChatColor.BOLD + "Futures";
        }


        Inventory stockMarketInventory = Bukkit.createInventory(null, 54, menu + ChatColor.GRAY + "" + ChatColor.BOLD + "- page#" + pageNum + "\nuneditable");

        // setting the default items
        ItemStack filter = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = filter.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "");
        filter.setItemMeta(meta);

        ItemStack refresh = new ItemStack(Material.SUNFLOWER);
        meta = refresh.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Refresh Menu");
        refresh.setItemMeta(meta);
        stockMarketInventory.setItem(49, refresh);

        ItemStack balance = new ItemStack(Material.EMERALD);
        meta = balance.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Balance");
        balance.setItemMeta(meta);
        stockMarketInventory.setItem(50, balance);

        meta = toggleView.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Toggle View");
        List<String> lore = new ArrayList();
        String tradingMode = ChatColor.YELLOW + "Normal Trading";

        switch (states){
            case Futures -> tradingMode = ChatColor.BLUE + "Futures Trading";
            case Options -> tradingMode = ChatColor.LIGHT_PURPLE + "Options Trading";
        }

        lore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Current View: " + tradingMode);
        meta.setLore(lore);
        toggleView.setItemMeta(meta);
        stockMarketInventory.setItem(48, toggleView);

        meta = changePage.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Page Forward");
        changePage.setItemMeta(meta);
        stockMarketInventory.setItem(52, changePage);

        // cancer
        stockMarketInventory.setItem(45, filter);
        stockMarketInventory.setItem(46, filter);
        stockMarketInventory.setItem(47, filter);
        stockMarketInventory.setItem(51, filter);
        stockMarketInventory.setItem(53, filter);


        return stockMarketInventory;
    }

    public void saveItem(TradingModes mode, ItemStack itemStack, int amount, Player player, double cost){

        ArrayList<String> itemVerify = new ArrayList<>();

        itemVerify.add(itemStack.getType().toString());
        itemVerify.add(itemStack.getEnchantments().toString());
        if (itemStack.getItemMeta().hasDisplayName()) {
            itemVerify.add(itemStack.getItemMeta().getDisplayName());
        } else itemVerify.add(itemStack.getItemMeta().getLocalizedName());

        itemVerify.add(itemStack.getItemMeta().getLore().toString());

        try {
            database.saveOffer(player.getUniqueId(), itemVerify, amount, mode.ordinal(), cost);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //public int pageChange(){}

    //public long getBalance(Player player){}

    //public void toggleTradingMode(){}

    enum TradingModes{
        Sell, Buy, Futures, Options
    }
}
