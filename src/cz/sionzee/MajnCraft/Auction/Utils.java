package cz.sionzee.MajnCraft.Auction;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

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

    public static void generateMessages() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File(Index.getInstance().getDataFolder() + "/messages/DEFAULT.txt"), true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, String> msgs : Messages.messages.entrySet()) {
            String name = msgs.getKey();
            String value = msgs.getValue();
            String result = name + "=" + value;
            try {
                writer.write(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum Type {
        STRING,
        INT
    }

    public static BlockFace getClosestFace(float direction) {

        direction = direction % 360;

        if (direction < 0)
            direction += 360;

        direction = Math.round(direction / 45);

        switch ((int) direction) {

            case 0:
                return BlockFace.WEST;
            case 1:
                return BlockFace.NORTH_WEST;
            case 2:
                return BlockFace.NORTH;
            case 3:
                return BlockFace.NORTH_EAST;
            case 4:
                return BlockFace.EAST;
            case 5:
                return BlockFace.SOUTH_EAST;
            case 6:
                return BlockFace.SOUTH;
            case 7:
                return BlockFace.SOUTH_WEST;
            default:
                return BlockFace.WEST;

        }
    }

}
