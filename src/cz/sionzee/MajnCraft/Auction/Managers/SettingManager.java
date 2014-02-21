package cz.sionzee.MajnCraft.Auction.Managers;

import org.bukkit.configuration.Configuration;

public class SettingManager {

    static String[] signInputLines;
    static String[] signOutputLines;

    public static void initialize() {
        Configuration config = ConfigurationManager.getConfig();
        signInputLines = (String[]) config.getStringList("Sign.input.lines").toArray();
        signOutputLines = (String[]) config.getStringList("Sign.output.lines").toArray();
    }

    public static String[] getSignInputLines() {
        return signInputLines;
    }

    public static String[] getSignOutputLines() {
        return signOutputLines;
    }

}
