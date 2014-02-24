package cz.sionzee.MajnCraft.Auction.Managers;

import cz.sionzee.MajnCraft.Auction.MajnCraftPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class EconomyManager {

    static HashMap<String, MajnCraftPlayer> players;
    static boolean enabled;

    public static void initialize() {
        players = new HashMap<String, MajnCraftPlayer>();
        enabled = true;
    }

    public static void addPlayer(MajnCraftPlayer mc, String playerName) {
        players.put(playerName, mc);
    }

    public static void removePlayer(String playerName) {
        players.remove(playerName);
    }

    public static MajnCraftPlayer getPlayer(String name) {
        return players.get(name);
    }

    public static int getThisMoney(String name) {
        DatabaseManager.preparedQuery(name, String.format("SELECT money FROM `%s`.`%s` WHERE `playername`='%s' ENGINE = InnoDB", DatabaseManager.getDatabaseName(), DatabaseManager.getTablePrefix() + "players", name));
        ResultSet rs = null;
        try {
            rs = DatabaseManager.getPreparedQuery(name).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            return rs.getInt(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getMoney(String name) {
        if (enabled) {
            return players.get(name).getMoney();
        } else {
            return (int) VaultManager.economy.getBalance(name);
        }
    }

    public static boolean isEnabled() {
        return enabled;
    }
}
