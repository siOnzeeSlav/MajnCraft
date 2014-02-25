package cz.sionzee.MajnCraft.Auction.events;

import cz.sionzee.MajnCraft.Auction.Messages;
import cz.sionzee.MajnCraft.Auction.Storer;
import cz.sionzee.MajnCraft.Auction.Utils;
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

            if (Storer.playernamesremove.contains(playerName)) {
                Location blockLocation = block.getLocation();
                if (blockType == Material.SIGN_POST || blockType == Material.WALL_SIGN) {
                    Sign sign = (Sign) blockLocation.getWorld().getBlockAt(blockLocation).getState();
                    if (sign.getLine(0).equals("[MineAuction]")) {
                        blockLocation.getWorld().getBlockAt(blockLocation).setType(Material.AIR);
                        p.sendMessage(Messages.get("SIGN_REMOVED"));
                        event.setCancelled(true);
                    }
                }
            }

            if (Storer.playernames.contains(playerName)) {
                Location blockLocation = block.getLocation();
                Location upLoc = new Location(blockLocation.getWorld(), blockLocation.getBlockX(), blockLocation.getBlockY() + 1, blockLocation.getBlockZ());
                Block upLocBlock = upLoc.getBlock();
                upLocBlock.setType(Material.SIGN_POST);
                org.bukkit.material.Sign matSign = new org.bukkit.material.Sign(Material.SIGN_POST);
                matSign.setFacingDirection(Utils.getClosestFace((float) Math.toDegrees(Math.atan2(p.getLocation().getBlockX() - upLoc.getX(), upLoc.getZ() - p.getLocation().getBlockZ()))).getOppositeFace());
                Sign sign = (Sign) upLocBlock.getState();
                sign.setLine(0, "[MineAuction]");
                sign.setLine(1, Messages.get("SIGN_OPEN"));
                sign.setData(matSign);
                sign.update();
                p.sendMessage(Messages.get("SIGN_PLACED"));
                Storer.playernames.remove(playerName);
            }

            if (blockType == Material.SIGN_POST || blockType == Material.WALL_SIGN) {
                Sign sign = (Sign) block.getState();
                if (sign.getLine(0).equals("[MineAuction]")) {
                    if (sign.getLine(1).equals(Messages.get("SIGN_OPEN"))) {
                        p.openInventory(Storer.auctionMenu);
                    }
                }
            }
        }
    }

}
