package cz.sionzee.MajnCraft.Auction.events;

import cz.sionzee.MajnCraft.Auction.MajnCraftPlayer;
import cz.sionzee.MajnCraft.Auction.Managers.DatabaseManager;
import cz.sionzee.MajnCraft.Auction.Managers.EconomyManager;
import cz.sionzee.MajnCraft.Auction.Managers.VaultManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OnPlayerKickJoinQuitEventVault implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        String playerName = player.getName();

        ResultSet rs;

        rs = DatabaseManager.executeQuery(String.format("SELECT COUNT(*) FROM `%s`.`%s` WHERE `playername`='%s'", DatabaseManager.getDatabaseName(), DatabaseManager.getTablePrefix() + "players", playerName));

        boolean access = true;

        try {
            if (rs.next()) {
                access = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MajnCraftPlayer mcp = new MajnCraftPlayer(player, (int) VaultManager.economy.getBalance(playerName), access);

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
