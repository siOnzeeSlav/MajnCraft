package cz.sionzee.MajnCraft.Auction.events;

import cz.sionzee.MajnCraft.Auction.Messages;
import cz.sionzee.MajnCraft.Auction.Storer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Project ${PROJECT_NAME}.
 * Created by siOnzeeSlav on 24.2.14.
 */
public class OnPlayerInteractEvent {

    @EventHandler
    void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player p = event.getPlayer();
            String playerName = p.getName();
            if (Storer.playernames.contains(playerName)) {
                Block block = event.getClickedBlock();
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
        }
    }

}
