package banking.models;

import banking.Config;
import banking.db.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Banking {

    private int currentAccountId;

    private static final Banking instance = new Banking();

    private Banking() {
        Database.execute(Config.createTableSql);
        currentAccountId = 0;
    }

    public static Banking getInstance() { return instance; }

    public String[] createAccount() {
        String number = CardInfoGenerator.getCardNumber();
        String PIN = CardInfoGenerator.getPIN();
        Database.execute(String.format(Config.addAccountSql, number, PIN));
        return new String[]{number, PIN};
    }

    public int getBalance() {
        try {
            return Database.query(String.format(Config.getBalanceSql, currentAccountId)).getInt("balance");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void deposit(int income) {
        Database.execute(String.format(Config.depositSql, income, currentAccountId));
    }

    public void transfer(int id, int amount) {
        Database.execute(String.format(Config.withdrawSql, amount, currentAccountId));
        Database.execute(String.format(Config.depositSql, amount, id));
    }

    public void closeAccount() {
        Database.execute(String.format(Config.deleteAccountSql, currentAccountId));
        currentAccountId = 0;
    }

    public boolean logIn(String cardNumber, String PIN) {
        try {
            ResultSet rs = Database.query(String.format(Config.getAccountIdSql, cardNumber, PIN));
            if (!rs.next()) { return false; }
            currentAccountId = rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0 != currentAccountId;
    }

    public void logOut() { currentAccountId = 0; }

    public boolean isLoggedIn() { return currentAccountId != 0; }

    public int getCurrentAccountId() { return currentAccountId; }

    public int getIdByCardNumber(String cardNumber) {
        int id = 0;
        try {
            ResultSet rs = Database.query(String.format(Config.getIdByCardNumberSql, cardNumber));
            if (!rs.next()) { return 0; }
            id = rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
