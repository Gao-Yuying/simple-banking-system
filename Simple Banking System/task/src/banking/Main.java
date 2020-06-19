package banking;

import banking.db.Database;
import banking.viewers.BankingViewer;

public class Main {
    public static void main(String[] args) {
        Database.connect(args.length > 0 ? args[1] : "test.s3db");
        BankingViewer.perform();
        Database.disconnect();
    }
}
