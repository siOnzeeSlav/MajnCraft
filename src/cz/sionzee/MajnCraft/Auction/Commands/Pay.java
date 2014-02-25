package cz.sionzee.MajnCraft.Auction.Commands;

import cz.sionzee.MajnCraft.Auction.Interfaces.ICommand;
import cz.sionzee.MajnCraft.Auction.MajnCraftPlayer;
import cz.sionzee.MajnCraft.Auction.Managers.EconomyManager;
import cz.sionzee.MajnCraft.Auction.Messages;
import cz.sionzee.MajnCraft.Auction.Utils;
import org.bukkit.entity.Player;

public class Pay implements ICommand {

    @Override
    public boolean onCommand(Player p, String[] args) {

        if (args.length > 1) {
            String target = args[0];
            int moneyToSend = Integer.parseInt(args[2]);
            if (EconomyManager.getPlayer(target) != null) {
                MajnCraftPlayer mcp = EconomyManager.getPlayer(p.getName());
                MajnCraftPlayer mcpT = EconomyManager.getPlayer(target);
                if (mcp.getMoney() >= moneyToSend) {
                    mcp.takeMoney(moneyToSend);
                    mcpT.addMoney(moneyToSend);
                    p.sendMessage(Utils.checkMessage(Messages.get("ECONOMY_PAY_SUCCESSFULL"), moneyToSend, Utils.Type.INT));
                    mcpT.getPlayer().sendMessage(String.format(Messages.get("ECONOMY_PAY_COLLECTED"), moneyToSend, p.getDisplayName()));
                } else {
                    p.sendMessage(Messages.get("ECONOMY_PAY_NOMONEY"));
                    p.sendMessage(Utils.checkMessage(Messages.get("ECONOMY_PAY_NEED"), (moneyToSend - mcp.getMoney()), Utils.Type.INT));
                }
            } else {
                p.sendMessage(Utils.checkMessage(Messages.get("ECONOMY_PAY_NOTFOUND"), target, Utils.Type.STRING));
            }
        } else {
            return true;
        }
        return false;
    }

}
