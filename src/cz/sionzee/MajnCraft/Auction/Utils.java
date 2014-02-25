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

}
