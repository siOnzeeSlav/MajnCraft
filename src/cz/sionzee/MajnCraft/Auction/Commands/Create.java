package cz.sionzee.MajnCraft.Auction.Commands;

import cz.sionzee.MajnCraft.Auction.Interfaces.ICommand;
import cz.sionzee.MajnCraft.Auction.Storer;
import org.bukkit.entity.Player;

public class Create implements ICommand {

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (!Storer.playernamesremove.contains(p.getName()))
            Storer.playernames.add(p.getName());
        return false;
    }

}
