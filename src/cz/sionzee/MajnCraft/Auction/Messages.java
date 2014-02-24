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
    }

    public static String get(String name) {
        return messages.get(name);
    }
}
