package cz.sionzee.MajnCraft.Auction;

import org.bukkit.entity.Player;

public class MajnCraftPlayer extends Account {

    Player player;

    public MajnCraftPlayer(Player player, int money) {
        super(money, player.getName());

        this.player = player;
    }

    public Account getAccount() {
        return this;
    }

}
