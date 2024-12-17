package thirdparty;

import java.util.Scanner;

public class Choice {
    private Choice() {}

    public static int inputId(Scanner scanner, int size) {
        int choice = -1;
        while (true) {
            System.out.print("Enter ID: ");
            choice = scanner.nextInt();
            if (choice < 1 || choice > size) {
                System.out.println("Incorrect input. Try again.");
                continue;
            }
            return choice;
        }
    }
}
