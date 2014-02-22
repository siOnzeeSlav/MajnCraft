package cz.sionzee.MajnCraft.Auction;

public abstract class Account {

    protected String accountName;
    protected int money;

    public Account(int money, String accountName) {
        this.accountName = accountName;
        this.money = money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void takeMoney(int money) {
        this.money -= money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

}
