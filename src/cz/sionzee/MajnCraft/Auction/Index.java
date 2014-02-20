package cz.sionzee.MajnCraft.Auction;

import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

public class Index extends JavaPlugin {

    public static HashMap<String, MajnCraftPlayer> players;
    static Index i;

    @Override
    public void onEnable() {
        i = this;
        players = new HashMap<String, MajnCraftPlayer>();
        ConfigurationManager.initialize();
        if (!DatabaseManager.initialize()) {
            Log.$(ErrorMessages.NODATABASECONFIG);
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
