package cz.sionzee.MajnCraft.Auction;

import cz.sionzee.MajnCraft.Auction.Managers.EconomyManager;

public class Accounts {

    public static Account getAccountByPlayerName(String playerName) {
        return EconomyManager.getPlayer(playerName).getAccount();
    }

}
