package cz.sionzee.MajnCraft.Auction.Managers;

import cz.sionzee.MajnCraft.Auction.Index;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultManager {

    static Permission permission = null;
    public static Economy economy = null;
    static Chat chat = null;

    public static void initialize() {

        Index i = Index.getInstance();

        RegisteredServiceProvider<Permission> permissionProvider = i.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }

        RegisteredServiceProvider<Chat> chatProvider = i.getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        RegisteredServiceProvider<Economy> economyProvider = i.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }
    }

}
