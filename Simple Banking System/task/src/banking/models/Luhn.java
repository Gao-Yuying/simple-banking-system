package banking.models;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Luhn {

    public static int getChecksum(String mainNumber) {
        int sum = sum(mainNumber);
        return sum % 10 == 0 ? 0 : 10 - sum % 10;
    }

    public static boolean isValid(String cardNumber) {
        return sum(cardNumber) % 10 == 0;
    }

    private static int sum(String number) {
        int[] arr = Arrays.stream(number.split("")).mapToInt(Integer::parseInt).toArray();
        return IntStream.range(0, number.length())
                .map(i -> {
                    if (i % 2 == 0) { arr[i] *= 2; }
                    if (arr[i] > 9) { arr[i] -= 9; }
                    return arr[i];
                })
                .sum();
    }
}
