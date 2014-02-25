package cz.sionzee.MajnCraft.Auction.Managers;

import cz.sionzee.MajnCraft.Auction.Index;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

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
                    configuration = YamlConfiguration.loadConfiguration(file);
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
                file.renameTo(new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(File.separator)) + "config.yml.old.v" + (configurationVersion - 1)));
                checkConfigurationVersion(configuration, ConfigurationType.CONFIG);
                break;

        }
    }

    public static Configuration getConfig() {
        return configuration;
    }

    public enum ConfigurationType {
        CONFIG;
    }

    public static void setMessagesFalse() {
        configuration.set("Messages", false);
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
