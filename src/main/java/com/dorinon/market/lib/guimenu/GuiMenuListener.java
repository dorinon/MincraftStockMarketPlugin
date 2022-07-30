package com.dorinon.market.lib.guimenu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiMenuListener implements Listener {
    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof GuiMenu menu)) return;

        event.setCancelled(true);

        menu.onClick(event.getSlot(), (Player) event.getWhoClicked());
    }
}
