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
        messages.put("ECONOMY_MONEY", "You're have %d money.");
        messages.put("ECONOMY_PAY_NOTFOUND", "Player %s not found.");
        messages.put("ECONOMY_PAY_NOMONEY", "You don't have money!");
        messages.put("ECONOMY_PAY_NEED", "To send money you need %d.");
        messages.put("ECONOMY_PAY_SUCCESSFULL", "You're successfull sended %d money.");
        messages.put("ECONOMY_PAY_COLLECTED", "You're collected %d money from %s.");
    }

    public static String get(String name) {
        return messages.get(name);
    }
}
