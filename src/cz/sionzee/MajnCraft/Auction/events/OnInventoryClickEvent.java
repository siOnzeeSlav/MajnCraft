package cz.sionzee.MajnCraft.Auction.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Project MajnCraft Auctions.
 * Created by siOnzeeSlav on 25.2.14.
 */
public class OnInventoryClickEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        String playerName = player.getName();
        Inventory inventory = event.getInventory();
        if (inventory.getName().equals(playerName + " HOPPER")) {
            event.setCancelled(true);
        }

        if (inventory.getName().equals(playerName + " CHEST")) {
            event.setCancelled(true);
        }

        if (inventory.getName().equals("Auction Menu")) {
            if (event.getCurrentItem() != null) {
                ItemStack clicked = event.getCurrentItem();

                switch (clicked.getType()) {
                    case HOPPER:
                        player.closeInventory();
                        Bukkit.createInventory(player, 9, player.getName() + " HOPPER");
                        break;
                    case WOOD_DOOR:
                        player.closeInventory();
                        break;

                    case CHEST:
                        Bukkit.createInventory(player, 9, player.getName() + " CHEST");
                        break;
                }
                event.setCancelled(true);
            }
        }
    }

}
