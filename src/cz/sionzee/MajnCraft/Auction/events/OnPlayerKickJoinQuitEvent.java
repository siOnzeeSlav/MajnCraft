package cz.sionzee.MajnCraft.Auction.events;

import cz.sionzee.MajnCraft.Auction.MajnCraftPlayer;
import cz.sionzee.MajnCraft.Auction.Managers.DatabaseManager;
import cz.sionzee.MajnCraft.Auction.Managers.EconomyManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OnPlayerKickJoinQuitEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        String playerName = player.getName();

        ResultSet rs;

        rs = DatabaseManager.executeQuery(String.format("SELECT COUNT(*) FROM `%s`.`%s` WHERE `playername`='%s'", DatabaseManager.getDatabaseName(), DatabaseManager.getTablePrefix() + "players", playerName));

        boolean readMoney = false;
        try {
            if (!rs.next()) {
                readMoney = true;
            } else {
                DatabaseManager.executeQuery(String.format("INSERT INTO `%s`.`%s` (playername, money) VALUES ('%s', '%d')", DatabaseManager.getDatabaseName(), DatabaseManager.getTablePrefix() + "players", playerName, 0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int playerMoney = 0;

        if (readMoney) {
            try {
                rs = DatabaseManager.executeQuery(String.format("SELECT money FROM `%s`.`%s` WHERE `playername`='%s'", DatabaseManager.getDatabaseName(), DatabaseManager.getTablePrefix() + "players", playerName));
                rs.next();
                playerMoney = rs.getInt(0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        rs = DatabaseManager.executeQuery(String.format("SELECT COUNT(*) FROM `%s`.`%s` WHERE `playername`='%s'", DatabaseManager.getDatabaseName(), DatabaseManager.getTablePrefix() + "players", playerName));

        boolean access = true;

        try {
            if (rs.next()) {
                access = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MajnCraftPlayer mcp = new MajnCraftPlayer(player, playerMoney, access);

        EconomyManager.addPlayer(mcp, playerName);

    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        EconomyManager.removePlayer(playerName);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        EconomyManager.removePlayer(playerName);
    }

}
