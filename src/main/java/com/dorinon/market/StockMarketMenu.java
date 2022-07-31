package com.dorinon.market;

import com.dorinon.market.lib.guimenu.GuiMenu;
import com.dorinon.market.lib.guimenu.GuiMenuItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class StockMarketMenu extends GuiMenu {

    public StockMarketMenu() {
        super(ChatColor.WHITE + "" + ChatColor.BOLD +   "Main Menu" + ChatColor.GRAY + "- Page#1", 6);
    }

    enum TradingMode{

        Normal(ChatColor.YELLOW + "Normal", 1),
        Futures(ChatColor.BLUE + "Futures", 2),
        Options(ChatColor.LIGHT_PURPLE + "Options", 3);

        String string;
        int num;
        TradingMode(String string, int num){
            this.string = string;
            this.num = num;
        }
    }

    private TradingMode mode = TradingMode.Normal;
    @Override
    protected void onCreate(@NotNull GuiMenu menu) {

        setItem(4, 6, new GuiMenuItem() {
            @Override
            public ItemStack getItem() {

                ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
                ItemMeta meta = itemStack.getItemMeta();
                meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Toggle View");
                ArrayList<String> lore = new ArrayList<>();
                lore.add(ChatColor.RESET + "" + ChatColor.GRAY + "Current View: " + mode.string + " Trading");
                itemStack.setItemMeta(meta);
                return itemStack;
            }

            @Override
            public void onClick(GuiMenu menu, Player player) {

                mode = TradingMode.values()[mode.num%3];
                if (mode == TradingMode.Normal){setTitle(ChatColor.WHITE + "" + ChatColor.BOLD + "Main menu " + ChatColor.GRAY + "- page#1");
                } else setTitle(ChatColor.BOLD + mode.string + ChatColor.GRAY + "- Page#1");

                refreshItemStack(this);
            }
        });
    }
}
