package cz.sionzee.MajnCraft.Auction;

import cz.sionzee.MajnCraft.Auction.Commands.*;
import cz.sionzee.MajnCraft.Auction.Interfaces.ICommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandHandler implements CommandExecutor {

    static HashMap<String, ICommand> commands;

    public static void initialize() {
        commands = new HashMap<String, ICommand>();
        loadCommands();
    }

    static void loadCommands() {
        commands.put("password", new Password());
        commands.put("ban", new Ban());
        commands.put("create", new Create());
        commands.put("money", new Money());
        commands.put("pay", new Pay());
        commands.put("top", new Top());
        commands.put("remove", new Remove());
        commands.put("help", new Help());
    }

    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
        Player p = (Player) arg0;

        if (arg3.length == 0) {
            p.sendMessage(ChatColor.GOLD + "/ma help");
            return true;
        }

        String cmd = arg3[0].toLowerCase();

        if (!commands.containsKey(cmd)) {
            p.sendMessage(ChatColor.GOLD + "/ma help");
            return true;
        }

        String permission = "MajnCraft." + cmd;

        if (!p.hasPermission(permission)) {
            p.sendMessage(Messages.get("COMMANDS_NOPERMISSION") + permission);
            return true;
        }

        commands.get(cmd).onCommand(p, fixArgs(arg3));

        return false;
    }

    String[] fixArgs(String[] args) {
        List<String> arguments = new ArrayList<String>();
        for (int i = 1; i < args.length; i++) {
            arguments.add(args[i]);
        }
        return arguments.toArray(new String[arguments.size()]);
    }

}
