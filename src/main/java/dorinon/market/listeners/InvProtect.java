package dorinon.market.listeners;

import dorinon.market.Inventories;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvProtect implements Listener {


    @EventHandler
    public void invClickEvent(InventoryClickEvent event) {
        if(event.getClickedInventory() == null) return;
        if (event.getView().getTitle().contains("\nuneditable")) {
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
        }
    }
}
