package cz.sionzee.MajnCraft.Auction.Managers;

import cz.sionzee.MajnCraft.Auction.ErrorMessages;
import cz.sionzee.MajnCraft.Auction.Log;
import org.bukkit.configuration.Configuration;

import java.sql.*;
import java.util.HashMap;

public class DatabaseManager {

    static Connection con;
    static Statement sta;
    static String database;
    static String tablePrefix;
    static String username;
    static String password;
    static String host;
    static int port;

    //static MongoClient mongoClient;
    //static DB db;

    static HashMap<String, PreparedStatement> store;

    public static String getDatabaseName() {
        return database;
    }

    public static String getTablePrefix() {
        return tablePrefix;
    }

    public static boolean initialize() {

        store = new HashMap<String, PreparedStatement>();

        Configuration config = ConfigurationManager.getConfig();

        username = config.getString("Database.Username");
        password = config.getString("Database.Password");
        database = config.getString("Database.Database");
        host = config.getString("Database.Host");
        tablePrefix = config.getString("Database.TablesPrefix");
        port = config.getInt("Database.Port");

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

        openConnection();

        try {
            sta = con.createStatement();
        } catch (SQLException e) {
            Log.$("ERROR: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static void openConnection() {
        try {
            con = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", host, port, database), username, password);
        } catch (SQLException e) {
            Log.$(ErrorMessages.DATABASEWRONGPARAMETERS);
            Log.$("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean checkTables() {
        try {

            if (EconomyManager.isEnabled()) {
                sta.executeQuery(String.format("CREATE TABLE IF NOT EXISTS `%s`.`%s` ( `id` INT NOT NULL AUTO_INCREMENT, `playername` VARCHAR(32) NULL, `money` INT NULL DEFAULT 0, `access` TINYINT NULL DEFAULT 1, `password` VARCHAR(100) NULL, PRIMARY KEY (`id`)) ENGINE = InnoDB", database, tablePrefix + "players"));
            }

            return true;
        } catch (SQLException e) {
            Log.$(ErrorMessages.TABLESCHECKERROR);
            Log.$("ERROR: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static ResultSet executeQuery(String quera) {

        try {
            if (con.isClosed())
                openConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (sta.isClosed())
                sta = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (quera.toLowerCase().startsWith("select")) {
            try {
                return sta.executeQuery(quera);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                sta.executeUpdate(quera);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static PreparedStatement getPreparedQuery(String name) {
        return store.get(name);
    }


    public static void preparedQuery(String ind, String quera) {
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(quera);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        store.put(ind, ps);
    }

}
