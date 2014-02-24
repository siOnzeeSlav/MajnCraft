package cz.sionzee.MajnCraft.Auction.Commands;

import cz.sionzee.MajnCraft.Auction.Interfaces.ICommand;
import cz.sionzee.MajnCraft.Auction.Managers.DatabaseManager;
import org.bukkit.entity.Player;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password implements ICommand {

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args[0].length() > 0) {
            MessageDigest m = null;
            try {
                m = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            assert m != null;
            byte[] passBytes = m.digest(args[0].getBytes());
            String passMD5 = new String(passBytes);

            try {
                m = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            byte[] passMD5Bytes = m.digest(passMD5.getBytes());
            String passSHA256 = new String(passMD5Bytes);

            DatabaseManager.executeQuery(String.format("UPDATE `%1%s`.`%2%s` SET `password` = '%4%s' WHERE `%1%s`.`playername` = '%3%s'", DatabaseManager.getDatabaseName(), DatabaseManager.getTablePrefix() + "players", p.getName(), passSHA256));

        }

        return false;
    }

}
