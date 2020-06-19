package banking.models;

import banking.Config;
import banking.db.Database;

import java.sql.SQLException;
import java.util.Random;

public class CardInfoGenerator {

    public static String getCardNumber() {
        String IIN = "400000";
        String accountNumber = String.format("789%06d", getId());
        String checksum = String.valueOf(getChecksum(IIN + accountNumber));
        return IIN + accountNumber + checksum;
    }

    public static String getPIN() {
        return String.format("%04d", new Random().nextInt(10000));
    }

    private static int getId() {
        int id = 0;
        try {
            id = Database.query(Config.getRowsSql).getInt("rows") == 0 ?
                    1 : Database.query(Config.getMaxIdSql).getInt("id") + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private static int getChecksum(String mainNumber) {
        return Luhn.getChecksum(mainNumber);
    }
}
