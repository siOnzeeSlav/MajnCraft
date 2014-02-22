package cz.sionzee.MajnCraft.Auction.Managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.configuration.Configuration;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import cz.sionzee.MajnCraft.Auction.ErrorMessages;
import cz.sionzee.MajnCraft.Auction.Log;

public class DatabaseManager {

    static Connection con;
    static Statement sta;
    static String database;
    static String tablePrefix;

    static MongoClient mongoClient;
    static DB db;

    public static boolean initialize() {

        Configuration config = ConfigurationManager.getConfig();

        String username = config.getString("Database.Username");
        String password = config.getString("Database.Password");
        database = config.getString("Database.Database");
        String host = config.getString("Database.Host");
        tablePrefix = config.getString("Database.TablesPrefix");
        int port = config.getInt("Database.Port");

        if (username.length() == 0 || password.length() == 0 || database.length() == 0 || host.length() == 0 || tablePrefix.length() == 0 || port == 0) {
            Log.$(ErrorMessages.NODATABASECONFIG);
            return false;
        }

        /*try {
            mongoClient = new MongoClient(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        }

        db = mongoClient.getDB(database);

        if (!db.authenticate(username, password.toCharArray())) {
            return false;
        }

        /** Successfull in db 
        */

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
        return true;
    }

    public static boolean checkTables() {
        try {

            if (EconomyManager.isEnabled()) {
                sta.executeQuery(String.format("CREATE TABLE IF NOT EXISTS `{0}`.`{1}` ( `id` INT NOT NULL AUTO_INCREMENT, `playername` VARCHAR(32) NULL, `money` INT NULL DEFAULT 0, `access` TINYINT NULL DEFAULT 1, PRIMARY KEY (`id`)) ENGINE = InnoDB", database, tablePrefix + "players"));
            }

            return true;
        } catch (SQLException e) {
            Log.$(ErrorMessages.TABLESCHECKERROR);
            Log.$("ERROR: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
