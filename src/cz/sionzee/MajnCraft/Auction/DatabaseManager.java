package cz.sionzee.MajnCraft.Auction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.configuration.Configuration;

public class DatabaseManager {

    static Connection con;
    static Statement sta;

    public static boolean initialize() {

        Configuration config = ConfigurationManager.getConfig();

        String username = config.getString("MySQL.Username");
        String password = config.getString("MySQL.Password");
        String database = config.getString("MySQL.Database");
        String host = config.getString("MySQL.Host");
        String tablePrefix = config.getString("MySQL.TablesPrefix");
        int port = config.getInt("MySQL.Port");

        if (username.length() == 0 || password.length() == 0 || database.length() == 0 || host.length() == 0 || tablePrefix.length() == 0 || port == 0) {
            Log.$(ErrorMessages.NODATABASECONFIG);
            return false;
        }

        try {
            con = DriverManager.getConnection(String.format("jdbc:mysql://{0}:{1}/{2}", host, port, database), username, password);
        } catch (SQLException e) {
            Log.$(ErrorMessages.DATABASEWRONGPARAMETERS);
            Log.$("ERROR: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

        try {
            sta = con.createStatement();
        } catch (SQLException e) {
            Log.$("ERROR: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

        try {
            sta.executeQuery(String.format("CREATE TABLE IF NOT EXISTS {0} (int id)", tablePrefix + "auctions"));
        } catch (SQLException e) {
            Log.$(ErrorMessages.TABLESCHECKERROR);
            Log.$("ERROR: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
