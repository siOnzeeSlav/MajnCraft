package cz.sionzee.MajnCraft.Auction.Commands;

import cz.sionzee.MajnCraft.Auction.Interfaces.ICommand;
import cz.sionzee.MajnCraft.Auction.MajnCraftPlayer;
import cz.sionzee.MajnCraft.Auction.Managers.EconomyManager;
import cz.sionzee.MajnCraft.Auction.Messages;
import org.bukkit.entity.Player;

public class Money implements ICommand {

    @Override
    public boolean onCommand(Player p, String[] args) {
        MajnCraftPlayer mcp = EconomyManager.getPlayer(p.getName());
        String message = Messages.get("ECONOMY_MONEY");
        if (!message.contains("%d")) {
            message += mcp.getMoney();
        } else {
            message = String.format(message, mcp.getMoney());
        }
        p.sendMessage(message);
        return false;
    }

}
