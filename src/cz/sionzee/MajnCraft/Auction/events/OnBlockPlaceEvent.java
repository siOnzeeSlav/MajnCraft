package cz.sionzee.MajnCraft.Auction.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.block.BlockPlaceEvent;

import cz.sionzee.MajnCraft.Auction.SettingManager;

public class OnBlockPlaceEvent {

    public void ev(BlockPlaceEvent event) {
        Block block = event.getBlock();

        if (block.getType() == Material.SIGN || block.getType() == Material.SIGN_POST) {
            Sign sign = (Sign) block.getState();
            String[] auctionLines = SettingManager.getSignLines();
            if (sign.getLine(0) == auctionLines[0]) {

            }
        }

    }

}
