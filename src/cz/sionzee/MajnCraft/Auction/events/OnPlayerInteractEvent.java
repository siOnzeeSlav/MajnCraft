package cz.sionzee.MajnCraft.Auction.events;

import cz.sionzee.MajnCraft.Auction.Messages;
import cz.sionzee.MajnCraft.Auction.Storer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Project ${PROJECT_NAME}.
 * Created by siOnzeeSlav on 24.2.14.
 */
public class OnPlayerInteractEvent implements Listener {

    @EventHandler
    void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player p = event.getPlayer();
            String playerName = p.getName();
            Block block = event.getClickedBlock();

            Material blockType = block.getType();

            if (blockType == Material.SIGN || blockType == Material.SIGN_POST) {
                Sign sign = (Sign) block.getState();
                if (sign.getLine(0).equals("[MineAuction]")) {
                    if (sign.getLine(1).equals(Messages.get("SIGN_OPEN"))) {
                        if (p.getOpenInventory() == null)
                            p.openInventory(Storer.auctionMenu);
                    }
                }
            }


            if (Storer.playernames.contains(playerName)) {

                Location blockLocation = block.getLocation();
                Location upLoc = new Location(blockLocation.getWorld(), blockLocation.getX(), blockLocation.getY() + 1, blockLocation.getZ());
                upLoc.getWorld().getBlockAt(upLoc).setType(Material.SIGN);
                Sign sign = (Sign) upLoc.getWorld().getBlockAt(upLoc).getState();
                sign.setLine(0, "[MineAuction]");
                sign.setLine(1, Messages.get("SIGN_OPEN"));
                sign.update();
                p.sendMessage(Messages.get("SIGN_PLACED"));
                Storer.playernames.remove(playerName);
            }

            if (Storer.playernamesremove.contains(playerName)) {
                Location blockLocation = block.getLocation();
                Sign sign = (Sign) blockLocation.getWorld().getBlockAt(blockLocation).getState();
                if (sign.getLine(0).equals("[MineAuction]")) {
                    blockLocation.getWorld().getBlockAt(blockLocation).setType(Material.AIR);
                    p.sendMessage(Messages.get("SIGN_REMOVED"));
                }
            }
        }
    }

}
