package banking.viewers;

import banking.controllers.BankingController;
import banking.utils.Input;

public class BankingViewer {

    private static final BankingController controller = BankingController.getInstance();
    private static final Input input = Input.getInstance();

    public static void perform() {
        while (true) {
            if ("main".equals(controller.getState())) {
                displayMainMenu();
            } else {
                displayAccountMenu();
            }

            String cmd = input.nextLine().trim();
            if ("0".equals(cmd)) {
                System.out.println("\nBye!");
                return;
            } else {
                controller.perform(cmd);
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println(
                "1. Create account\n" +
                "2. Log into account\n" +
                "0. Exit");
    }

    private static void displayAccountMenu() {
        System.out.println(
                "1. Balance\n" +
                "2. Add income\n" +
                "3. Do transfer\n" +
                "4. Close account\n" +
                "5. Log out\n" +
                "0. Exit");
    }
}
