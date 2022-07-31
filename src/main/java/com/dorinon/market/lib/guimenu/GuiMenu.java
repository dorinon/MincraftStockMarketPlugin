package com.dorinon.market.lib.guimenu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class GuiMenu implements InventoryHolder {
    private final String title;
    private final int height;

    private final GuiMenuItem[] items;
    private Inventory inventory;
    private Player player;

    public GuiMenu(@NotNull String title, int height) {
        this.title = title;
        this.height = height;

        items = new GuiMenuItem[height * 9];
    }

    protected abstract void onCreate(@NotNull GuiMenu menu);

    public void open(@NotNull Player player) {
        if (this.player != null) return;

        inventory = Bukkit.createInventory(this, height * 9, title);

        onCreate(this);

        player.openInventory(inventory);

        this.player = player;
    }

    public void setTitle(String title){

        Inventory inventory1 = Bukkit.createInventory(null, inventory.getSize(), title);
        inventory1.setStorageContents(inventory.getStorageContents());
        inventory = inventory1;


        if (player.getOpenInventory().getTopInventory().equals(inventory)){

            player.closeInventory();
            player.openInventory(inventory);
        }
    }

    public void close() {
        if (this.player != null) player.closeInventory();
    }

    void onClick(int slot, Player player) {
        items[slot].onClick(this, player);
    }

    public void setItem(int x, int y, GuiMenuItem item) {
        setItem((y - 1) * 9 + x - 1, item);
    }

    public void setItem(int slot, GuiMenuItem item) {
        items[slot] = item;
        inventory.setItem(slot, item.getItem());
    }

    public void refreshItemStack(GuiMenuItem item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == item) {
                inventory.setItem(i, item.getItem());
            }
        }
    }

    protected void fillBackground(ItemStack item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null || items[i].isBackground()) setItem(i, new GuiMenuItem() {
                @Override
                public ItemStack getItem() {
                    return item;
                }

                @Override
                public boolean isBackground() {
                    return true;
                }
            });
        }
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
