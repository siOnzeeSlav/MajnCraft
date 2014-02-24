package cz.sionzee.MajnCraft.Auction;

import cz.sionzee.MajnCraft.Auction.Managers.ConfigurationManager;
import cz.sionzee.MajnCraft.Auction.Managers.DatabaseManager;
import cz.sionzee.MajnCraft.Auction.Managers.EconomyManager;
import cz.sionzee.MajnCraft.Auction.Managers.VaultManager;
import cz.sionzee.MajnCraft.Auction.events.OnPlayerKickJoinQuitEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Index extends JavaPlugin {

    static Index i;

    @Override
    public void onEnable() {
        i = this;
        ConfigurationManager.initialize();
        if (!DatabaseManager.initialize()) {
            this.setEnabled(false);
        }
        getCommand("ma").setExecutor(new CommandHandler());
        CommandHandler.initialize();
        EconomyManager.initialize();
        if (EconomyManager.isEnabled()) {
            Bukkit.getPluginManager().registerEvents(new OnPlayerKickJoinQuitEvent(), this);
            for (Player player : Bukkit.getOnlinePlayers()) {
                String playerName = player.getName();
                EconomyManager.addPlayer(new MajnCraftPlayer(player, EconomyManager.getThisMoney(playerName)), playerName);
            }
        } else {
            if (Bukkit.getPluginManager().getPlugin("Vault") == null || !Bukkit.getPluginManager().getPlugin("Vault").isEnabled()) {
                this.setEnabled(false);
            } else {
                VaultManager.initialize();
            }
        }
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String playerName = player.getName();
            EconomyManager.removePlayer(playerName);
        }
    }

    public static Index getInstance() {
        return i;
    }

}
