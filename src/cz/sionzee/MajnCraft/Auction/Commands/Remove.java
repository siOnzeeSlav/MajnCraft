package cz.sionzee.MajnCraft.Auction.Commands;

import cz.sionzee.MajnCraft.Auction.Interfaces.ICommand;
import cz.sionzee.MajnCraft.Auction.Storer;
import org.bukkit.entity.Player;

public class Remove implements ICommand {

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (!Storer.playernames.contains(p.getName()))
            Storer.playernamesremove.add(p.getName());
        return false;
    }

}
