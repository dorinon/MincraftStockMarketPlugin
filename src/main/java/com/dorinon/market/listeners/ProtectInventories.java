package com.dorinon.market.listeners;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public final class ProtectInventories implements Listener {
    @EventHandler
    public void invClickEvent(@NotNull InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;

        if (event.getView().getTitle().contains("\nuneditable")) {
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
        }
    }
}
