package cz.sionzee.MajnCraft.Auction;

import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import cz.sionzee.MajnCraft.Auction.Managers.ConfigurationManager;
import cz.sionzee.MajnCraft.Auction.Managers.DatabaseManager;

public class Index extends JavaPlugin {

    public static HashMap<String, MajnCraftPlayer> players;
    static Index i;

    @Override
    public void onEnable() {
        i = this;
        players = new HashMap<String, MajnCraftPlayer>();
        ConfigurationManager.initialize();
        if (!DatabaseManager.initialize()) {
            this.setEnabled(false);
        }
        getCommand("ma").setExecutor(new CommandHandler());
        CommandHandler.initialize();
    }

    @Override
    public void onDisable() {

    }

    public static Index getInstance() {
        return i;
    }

}
