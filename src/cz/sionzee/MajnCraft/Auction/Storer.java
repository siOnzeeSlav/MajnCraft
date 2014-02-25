package cz.sionzee.MajnCraft.Auction;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Project MajnCraft Auctions.
 * Created by siOnzeeSlav on 24.2.14.
 */
public class Storer {

    public static List<String> playernames = new ArrayList<String>();
    public static List<String> playernamesremove = new ArrayList<String>();
    public static Inventory auctionMenu = Bukkit.createInventory(null, 9, "Auction Menu");
    public static boolean canUseTop = true;
    public static TreeMap<String, Integer> topPlayers = new TreeMap<String, Integer>();
    public static String writeToLines = "";
}
