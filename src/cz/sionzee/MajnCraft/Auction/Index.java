package cz.sionzee.MajnCraft.Auction;

import cz.sionzee.MajnCraft.Auction.Managers.ConfigurationManager;
import cz.sionzee.MajnCraft.Auction.Managers.DatabaseManager;
import cz.sionzee.MajnCraft.Auction.Managers.EconomyManager;
import cz.sionzee.MajnCraft.Auction.Managers.VaultManager;
import cz.sionzee.MajnCraft.Auction.events.OnInventoryClickEvent;
import cz.sionzee.MajnCraft.Auction.events.OnPlayerInteractEvent;
import cz.sionzee.MajnCraft.Auction.events.OnPlayerKickJoinQuitEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Index extends JavaPlugin {

    static Index i;

    @Override
    public void onEnable() {
        i = this;
        PluginManager pm = Bukkit.getPluginManager();
        ConfigurationManager.initialize();
        if (!DatabaseManager.initialize()) {
            this.setEnabled(false);
        }
        getCommand("ma").setExecutor(new CommandHandler());
        CommandHandler.initialize();
        EconomyManager.initialize();
        if (EconomyManager.isEnabled()) {
            pm.registerEvents(new OnPlayerKickJoinQuitEvent(), this);
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
        Storer.auctionMenu.setItem(0, Utils.createItemStack(Material.CHEST, Messages.get("AUCTION_MENU_COLLECT"), Messages.get("AUCTION_MENU_COLLECT_LORE")));
        Storer.auctionMenu.setItem(1, Utils.createItemStack(Material.HOPPER, Messages.get("AUCTION_MENU_GIVEOVER"), Messages.get("AUCTION_MENU_GIVEOVER_LORE")));
        Storer.auctionMenu.setItem(8, Utils.createItemStack(Material.WOODEN_DOOR, Messages.get("AUCTION_MENU_EXIT"), Messages.get("AUCTION_MENU_EXIT_LORE")));
        pm.registerEvents(new OnInventoryClickEvent(), this);
        pm.registerEvents(new OnPlayerInteractEvent(), this);

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
