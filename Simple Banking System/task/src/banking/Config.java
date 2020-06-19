package banking;

public class Config {
    public static final String createTableSql =
            "CREATE TABLE IF NOT EXISTS card (\n" +
            "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  number TEXT,\n" +
            "  pin TEXT,\n" +
            "  balance INTEGER DEFAULT 0\n" +
            ");";

    public static final String addAccountSql =
            "INSERT INTO card (number, pin) VALUES ('%s', '%s');";

    public static final String getAccountIdSql =
            "SELECT id FROM card WHERE number = '%s' AND pin = '%s';";

    public static final String getIdByCardNumberSql =
            "SELECT id FROM card WHERE number = '%s';";

    public static final String getBalanceSql =
            "SELECT balance FROM card WHERE id = %d;";

    public static final String depositSql =
            "UPDATE card SET balance = balance + %d\n" +
            "WHERE id = %d;";

    public static final String withdrawSql =
            "UPDATE card SET balance = balance - %d\n" +
            "WHERE id = %d;";

    public static final String deleteAccountSql =
            "DELETE FROM card WHERE id = %d;";

    public static final String getMaxIdSql =
            "SELECT max(id) as id FROM card;";

    public static final String getRowsSql =
            "SELECT count(*) as rows FROM card;";
}
