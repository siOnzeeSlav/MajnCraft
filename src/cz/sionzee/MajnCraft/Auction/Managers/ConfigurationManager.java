package cz.sionzee.MajnCraft.Auction.Managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import cz.sionzee.MajnCraft.Auction.Index;

public class ConfigurationManager {

    final static int configurationVersion = 1;

    static FileConfiguration configuration;
    static File file;

    static FileConfiguration configuration2;
    static File file2;

    public static void initialize() {
        file = new File(Index.getInstance().getDataFolder() + "/config.yml");
        configuration = YamlConfiguration.loadConfiguration(file);
        checkConfigurationVersion(configuration, ConfigurationType.CONFIG);

        file2 = new File(Index.getInstance().getDataFolder() + "/config.data");
        configuration2 = YamlConfiguration.loadConfiguration(file2);

    }

    static void checkConfigurationVersion(FileConfiguration c, ConfigurationType ct) {
        switch (ct) {
            case CONFIG:
                if (!file.exists()) {
                    Index.getInstance().saveDefaultConfig();
                    return;
                }
                if (c.contains("version")) {
                    if (c.getInt("version") != configurationVersion)
                        updateConfigurationFile(c, ct);
                } else {
                    try {
                        c.save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }

    }

    static void updateConfigurationFile(FileConfiguration c, ConfigurationType ct) {
        switch (ct) {
            case CONFIG:
                break;

        }
    }

    public static Configuration getConfig() {
        return configuration;
    }

    public enum ConfigurationType {
        CONFIG;
    }

}