package cz.sionzee.MajnCraft.Auction.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Project MajnCraft Auctions.
 * Created by siOnzeeSlav on 25.2.14.
 */
public class OnInventoryClickEvent {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory.getName().equals("Auction Menu")) {
            Player player = (Player) event.getWhoClicked();
            ItemStack clicked = event.getCurrentItem();

            switch (clicked.getType()) {
                case HOPPER:

                    break;
                case WOODEN_DOOR:
                    player.closeInventory();
                    break;

                case CHEST:

                    break;
            }
            event.setCancelled(true);
        }
    }

}
