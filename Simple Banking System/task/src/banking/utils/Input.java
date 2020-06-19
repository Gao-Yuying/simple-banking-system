package banking.utils;

import java.util.Scanner;

public class Input {

    private final Scanner scanner;
    private static final Input instance = new Input();

    private Input() {
        scanner = new Scanner(System.in);
    }

    public static Input getInstance() {
        return instance;
    }

    public String next() {
        return scanner.next();
    }

    public String nextLine() {
        return scanner.nextLine();
    }
}
