package cz.sionzee.MajnCraft.Auction;

import cz.sionzee.MajnCraft.Auction.Managers.ConfigurationManager;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Project MajnCraft Auctions.
 * Created by siOnzeeSlav on 25.2.14.
 */
public class Messages {

    public static HashMap<String, String> messages = new HashMap<String, String>();

    public static void initialize() {
        loadDefault();
        checkLocale();
    }

    static void checkLocale() {
        String locale = ConfigurationManager.getConfig().getString("Locale");

        if (locale.contains("default"))
            return;

        File file = new File(Index.getInstance().getDataFolder() + "/messages/Messages." + locale);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<String> lines = new ArrayList<String>();
            try {

                String line = "";
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (String line : lines) {
                String name = line.split("=")[0];
                String value = line.split("=")[1];
                if (messages.containsKey(name)) {
                    messages.put(name, value);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void loadDefault() {
        messages.put("SIGN_OPEN", "Open");
        messages.put("SIGN_PLACED", "You're successfully placed auction sign.");
        messages.put("SIGN_REMOVED", "You're successfully destroyed auction sign");
        messages.put("AUCTION_MENU_COLLECT", "Collect items");
        messages.put("AUCTION_MENU_COLLECT_LORE", "Pick up your stuff that you bought.");
        messages.put("AUCTION_MENU_GIVEOVER", "Give over items");
        messages.put("AUCTION_MENU_GIVEOVER_LORE", "Give the things you want to sell.");
        messages.put("AUCTION_MENU_EXIT", "Exit");
        messages.put("AUCTION_MENU_EXIT_LORE", "Exit the auction menu.");
        messages.put("ECONOMY_MONEY", "You're have %d money.");
        messages.put("ECONOMY_PAY_NOTFOUND", "Player %s not found.");
        messages.put("ECONOMY_PAY_NOMONEY", "You don't have money!");
        messages.put("ECONOMY_PAY_NEED", "To send money you need %d.");
        messages.put("ECONOMY_PAY_SUCCESSFULL", "You're successfull sended %d money.");
        messages.put("ECONOMY_PAY_COLLECTED", "You're collected %d money from %s.");
        messages.put("HELP_BAN", "Disallow access to auction");
        messages.put("HELP_CREATE", "Create new auction, press right click place");
        messages.put("HELP_MONEY", "Show balance");
        messages.put("HELP_PASSWORD", "Setup password for website");
        messages.put("HELP_PAY", "Give another player money");
        messages.put("HELP_REMOVE", "Remove auction, press right click on sign");
        messages.put("HELP_TOP", "Show top 10 players");
    }

    public static String get(String name) {
        return messages.get(name);
    }
}
