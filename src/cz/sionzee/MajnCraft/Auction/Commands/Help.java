package cz.sionzee.MajnCraft.Auction.Commands;

import cz.sionzee.MajnCraft.Auction.Interfaces.ICommand;
import cz.sionzee.MajnCraft.Auction.Messages;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Project MajnCraft Auctions.
 * Created by siOnzeeSlav on 25.2.14.
 */
public class Help implements ICommand {
    @Override
    public boolean onCommand(Player p, String[] args) {
        p.sendMessage(ChatColor.GOLD + "/ma ban <player>  -" + ChatColor.WHITE + Messages.get("HELP_BAN"));
        p.sendMessage(ChatColor.GOLD + "/ma create  -" + ChatColor.WHITE + Messages.get("HELP_CREATE"));
        p.sendMessage(ChatColor.GOLD + "/ma money  -" + ChatColor.WHITE + Messages.get("HELP_MONEY"));
        p.sendMessage(ChatColor.GOLD + "/ma password <password>  -" + ChatColor.WHITE + Messages.get("HELP_PASSWORD"));
        p.sendMessage(ChatColor.GOLD + "/ma pay <player> <money>  -" + ChatColor.WHITE + Messages.get("HELP_PAY"));
        p.sendMessage(ChatColor.GOLD + "/ma remove  -" + ChatColor.WHITE + Messages.get("HELP_REMOVE"));
        p.sendMessage(ChatColor.GOLD + "/ma top  -" + ChatColor.WHITE + Messages.get("HELP_TOP"));
        return false;
    }
}
