package cz.sionzee.MajnCraft.Auction;

import cz.sionzee.MajnCraft.Auction.Managers.DatabaseManager;

public abstract class Account {

    protected String accountName;
    protected int money;

    public Account(int money, String accountName) {
        this.accountName = accountName;
        this.money = money;
    }

    public void addMoney(int money) {
        this.money += money;
        DatabaseManager.executeQuery(String.format("UPDATE `%1%s`.`%2%s` SET `money` = '4%d' WHERE `%1%s`.`playername` = '%3%s'", DatabaseManager.getDatabaseName(), DatabaseManager.getTablePrefix() + "players", accountName, money));
    }

    public void takeMoney(int money) {
        this.money -= money;
        DatabaseManager.executeQuery(String.format("UPDATE `%1%s`.`%2%s` SET `money` = '4%d' WHERE `%1%s`.`playername` = '%3%s'", DatabaseManager.getDatabaseName(), DatabaseManager.getTablePrefix() + "players", accountName, money));
    }

    public void setMoney(int money) {
        this.money = money;
        DatabaseManager.executeQuery(String.format("UPDATE `%1%s`.`%2%s` SET `money` = '4%d' WHERE `%1%s`.`playername` = '%3%s'", DatabaseManager.getDatabaseName(), DatabaseManager.getTablePrefix() + "players", accountName, money));
    }

    public int getMoney() {
        return money;
    }

}
