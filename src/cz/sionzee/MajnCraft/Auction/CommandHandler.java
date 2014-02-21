package cz.sionzee.MajnCraft.Auction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cz.sionzee.MajnCraft.Auction.Commands.Ban;
import cz.sionzee.MajnCraft.Auction.Commands.Create;
import cz.sionzee.MajnCraft.Auction.Commands.Password;
import cz.sionzee.MajnCraft.Auction.Commands.Remove;
import cz.sionzee.MajnCraft.Auction.Interfaces.ICommand;

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
        commands.put("remove", new Remove());
    }

    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
        Player p = (Player) arg0;
        String cmd = arg3[0].toLowerCase();

        if (!commands.containsKey(cmd)) {
            p.sendMessage(cmd + " není zaznamenán.");
            p.sendMessage("/" + arg1.getName() + " help");
            return false;
        }

        String permission = "MajnCraft." + cmd;

        if (!p.hasPermission(permission)) {
            p.sendMessage("Nemáte oprávnění " + permission);
            return false;
        }

        commands.get(cmd).onCommand(p, fixArgs(arg3));

        return true;
    }

    String[] fixArgs(String[] args) {
        List<String> arguments = new ArrayList<String>();
        for (int i = 1; i < args.length; i++) {
            arguments.add(args[i]);
        }
        return (String[]) arguments.toArray();
    }

}
