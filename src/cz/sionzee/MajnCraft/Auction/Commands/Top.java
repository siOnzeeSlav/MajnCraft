package cz.sionzee.MajnCraft.Auction.Commands;

import cz.sionzee.MajnCraft.Auction.Index;
import cz.sionzee.MajnCraft.Auction.Interfaces.ICommand;
import cz.sionzee.MajnCraft.Auction.Managers.DatabaseManager;
import cz.sionzee.MajnCraft.Auction.Storer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class Top implements ICommand {

    @Override
    public boolean onCommand(Player p, String[] args) {

        if (Storer.canUseTop) {
            Storer.topPlayers.clear();
            Storer.writeToLines = "\n";
            int i = 0;

            ResultSet rs = DatabaseManager.executeQuery(String.format("SELECT playername, money FROM `%s`.`%s` ORDER BY money LIMIT 0,10", DatabaseManager.getDatabaseName(), DatabaseManager.getTablePrefix() + "players"));

            try {
                while (rs.next()) {
                    Storer.topPlayers.put(rs.getString(0), rs.getInt(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            i = 1;

            for (Map.Entry<String, Integer> pl : Storer.topPlayers.entrySet()) {
                String name = pl.getKey();
                int money = pl.getValue();
                Storer.writeToLines += i + ".) " + name + ": " + money + "\n";
                i++;
            }

            Storer.canUseTop = false;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Index.getInstance(), new Runnable() {
                @Override
                public void run() {
                    Storer.canUseTop = true;
                }
            }, 12000L);
        }

        p.sendMessage(Storer.writeToLines);
        return false;
    }

}
