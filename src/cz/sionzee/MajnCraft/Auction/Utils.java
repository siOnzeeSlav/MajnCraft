package cz.sionzee.MajnCraft.Auction;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * Project MajnCraft Auctions.
 * Created by siOnzeeSlav on 25.2.14.
 */
public class Utils {

    public static ItemStack createItemStack(Material material, String name, final String lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(new ArrayList<String>() {{
            add(0, lore);
        }});
        item.setItemMeta(meta);
        return item;
    }

    public static String checkMessage(String message, Object replaceTo, Type type) {
        switch (type) {
            case INT:
                if (message.contains("%d"))
                    message = String.format(message, replaceTo);
                else message += replaceTo;
                break;
            case STRING:
                if (message.contains("%s"))
                    message = String.format(message, replaceTo);
                else message += replaceTo;
                break;
        }
        return message;
    }

    public enum Type {
        STRING,
        INT
    }

}
