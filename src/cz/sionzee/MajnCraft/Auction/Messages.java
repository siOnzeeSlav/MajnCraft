package cz.sionzee.MajnCraft.Auction;

import java.util.HashMap;

/**
 * Project MajnCraft Auctions.
 * Created by siOnzeeSlav on 25.2.14.
 */
public class Messages {

    public static HashMap<String, String> messages = new HashMap<String, String>();

    public static void loadDefault() {
        messages.put("SIGN_OPEN", "Open");
        messages.put("SIGN_PLACED", "You're successfully placed auction sign.");
        messages.put("AUCTION_MENU_COLLECT", "Collect items");
        messages.put("AUCTION_MENU_COLLECT_LORE", "Pick up your stuff that you bought.");
        messages.put("AUCTION_MENU_GIVEOVER", "Give over items");
        messages.put("AUCTION_MENU_GIVEOVER_LORE", "Give the things you want to sell.");
        messages.put("AUCTION_MENU_EXIT", "Exit");
        messages.put("AUCTION_MENU_EXIT_LORE", "Exit the auction menu.");
    }

    public static String get(String name) {
        return messages.get(name);
    }
}
