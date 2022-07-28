package dorinon.market;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Inventories {

    private final Inventory stockMarketInv;


    public Inventories() {

        ItemStack filter = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);

        ItemMeta meta = filter.getItemMeta();
        meta.setDisplayName("");
        filter.setItemMeta(meta);

        stockMarketInv = Bukkit.createInventory(null, 54, "Stock Market \nuneditable");

        for (int i = 0; i < 9; i++){

            stockMarketInv.setItem(i + 1, filter);
            stockMarketInv.setItem(53 - i, filter);
        }
        for (int i = 0; i < 6; i++){

            stockMarketInv.setItem(i * 9, filter);
            stockMarketInv.setItem(i * 9 + 8, filter);
        }

        stockMarketInv.setItem(50, new ItemStack(Material.OAK_SIGN));
        stockMarketInv.setItem(48, new ItemStack(Material.CHEST));
        stockMarketInv.setItem(49, new ItemStack(Material.WRITABLE_BOOK));
    }

    public Inventory getStockMarketInv(){return stockMarketInv;}
}