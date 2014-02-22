package cz.sionzee.MajnCraft.Auction.Managers;

import java.util.HashMap;

import cz.sionzee.MajnCraft.Auction.MajnCraftPlayer;

public class EconomyManager {

    static HashMap<String, MajnCraftPlayer> players;

    public static void initialize() {
        players = new HashMap<String, MajnCraftPlayer>();
    }

    public static void addPlayer(MajnCraftPlayer mc, String playerName) {
        players.put(playerName, mc);
    }

    public static MajnCraftPlayer getPlayer(String name) {
        return players.get(name);
    }

}
