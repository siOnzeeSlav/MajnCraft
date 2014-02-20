package cz.sionzee.MajnCraft.Auction;

import org.bukkit.configuration.Configuration;

public class SettingManager {

    static String[] signLines;

    public static void initialize() {
        Configuration config = ConfigurationManager.getConfig();
        signLines = (String[]) config.getStringList("Sign.lines").toArray();
    }

    public static String[] getSignLines() {
        return signLines;
    }

}
