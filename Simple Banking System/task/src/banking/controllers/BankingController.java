package banking.controllers;

import banking.models.Banking;
import banking.models.Luhn;
import banking.utils.Input;

public class BankingController {

    private enum State {MAIN, ACCOUNT}

    private final Banking banking = Banking.getInstance();
    private final Input input = Input.getInstance();
    private State state;

    private static final BankingController instance = new BankingController();

    private BankingController() { state = State.MAIN; }

    public static BankingController getInstance() { return instance; }

    public String getState() {
        switch (state) {
            case MAIN:
                return "main";
            case ACCOUNT:
                return "account";
            default:
                return null;
        }
    }

    public void perform(String cmd) {
        if (state == State.MAIN) {
            performMain(cmd);
        } else {
            performAccount(cmd);
        }
        updateState();
    }

    private void performMain(String cmd) {
        switch (cmd) {
            case "1":
                createAccount();
                break;
            case "2":
                logIn();
                break;
            default:
                System.out.println("Invalid command.");
        }
    }

    private void performAccount(String cmd) {
        switch (cmd) {
            case "1":
                displayBalance();
                break;
            case "2":
                deposit();
                break;
            case "3":
                transfer();
                break;
            case "4":
                closeAccount();
                break;
            case "5":
                logOut();
            default:
                System.out.println("Invalid command.");
        }
    }

    private void updateState() {
        state = banking.isLoggedIn() ? State.ACCOUNT : State.MAIN;
    }

    private void createAccount() {
        String[] accountInfo = banking.createAccount();
        System.out.printf(
                "\nYour card have been created\n" +
                "Your card number:\n" +
                "%s\n" +
                "Your card PIN:\n" +
                "%s\n\n",
                accountInfo[0], accountInfo[1]);
    }

    private void logIn() {
        System.out.println("\nEnter your card number:");
        String cardNumber = input.nextLine().trim();
        System.out.println("Enter your PIN:");
        String PIN = input.nextLine().trim();

        boolean success = banking.logIn(cardNumber, PIN);
        System.out.println(success ?
                "\nYou have successfully logged in!\n" :
                "\nWrong card number or PIN!\n");
    }

    private void displayBalance() {
        System.out.printf("\nBalance: %d\n\n", banking.getBalance());
    }

    private void deposit() {
        System.out.println("\nEnter income:");
        int income = Integer.parseInt(input.nextLine().trim());
        banking.deposit(income);
        System.out.println("Income was added!\n");
    }

    private void transfer() {
        System.out.println("\nTransfer\nEnter card number:");
        String cardNumber = input.nextLine().trim();
        if (!Luhn.isValid(cardNumber)) {
            System.out.println("Probably you made mistake in the card number. Please try again!\n");
            return;
        }

        int id = banking.getIdByCardNumber(cardNumber);
        if (id == banking.getCurrentAccountId()) {
            System.out.println("You can't transfer money to the same account!\n");
            return;
        }
        if (id == 0) {
            System.out.println("Such a card does not exist.\n");
            return;
        }

        System.out.println("Enter how much money you want to transfer:");
        int amount = Integer.parseInt(input.nextLine().trim());
        if (amount > banking.getBalance()) {
            System.out.println("Not enough money!\n");
            return;
        }

        banking.transfer(id, amount);
        System.out.println("Success!\n");
    }

    private void closeAccount() {
        banking.closeAccount();
        System.out.println("The account has been closed!\n");
    }

    private void logOut() {
        banking.logOut();
        System.out.println("\nYou have successfully logged out!\n");
    }
}
