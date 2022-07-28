package com.dorinon.market;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public final class Inventories {
    public static @NotNull Inventory getStockMarketInventory() {
        ItemStack filter = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);

        ItemMeta meta = filter.getItemMeta();
        meta.setDisplayName("");
        filter.setItemMeta(meta);

        Inventory stockMarketInventory = Bukkit.createInventory(null, 54, "Stock Market \nuneditable");

        for (int i = 0; i < 9; i++){
            stockMarketInventory.setItem(i + 1, filter);
            stockMarketInventory.setItem(53 - i, filter);
        }

        for (int i = 0; i < 6; i++){
            stockMarketInventory.setItem(i * 9, filter);
            stockMarketInventory.setItem(i * 9 + 8, filter);
        }

        stockMarketInventory.setItem(50, new ItemStack(Material.OAK_SIGN));
        stockMarketInventory.setItem(48, new ItemStack(Material.CHEST));
        stockMarketInventory.setItem(49, new ItemStack(Material.WRITABLE_BOOK));

        return stockMarketInventory;
    }
}
