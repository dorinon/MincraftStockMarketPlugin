package com.dorinon.market.lib.guimenu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface GuiMenuItem {
    ItemStack getItem();

    default void onClick(GuiMenu menu, Player player) { }

    default boolean isBackground() { return false; }
}
