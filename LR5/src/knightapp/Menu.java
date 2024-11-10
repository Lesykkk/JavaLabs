package knightapp;

import command.*;
import thirdparty.Dash;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

class Menu {
    private Menu() {}

    public static void display() {
        Scanner scanner = new Scanner(System.in);

        Map<Integer, Command> menuCommands = new HashMap<>();
        menuCommands.put(1, new ShowKnightsCommand());
        menuCommands.put(2, new ShowAmmunitionCommand(scanner));
        menuCommands.put(3, new AddKnightCommand(scanner));
        menuCommands.put(4, new AddAmmunitionCommand(scanner));
        menuCommands.put(5, new RemoveKnightCommand(scanner, menuCommands.get(1)));
        menuCommands.put(6, new RemoveAmmunitionCommand(scanner, menuCommands.get(2)));
        menuCommands.put(7, new AddEquipmentToKnightCommand(scanner, menuCommands.get(1), menuCommands.get(2)));
        menuCommands.put(8, new RemoveEquipmentFromKnightCommand(scanner, menuCommands.get(1)));

        while (true) {
            System.out.println();
            Menu.printMenu();
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            } else {
                Command command = menuCommands.get(choice);
                if (command != null) {
                    System.out.println();
                    command.execute();
                } else {
                    System.out.println("Wrong option, please try again.");
                }
            }
        }
    }

    public static final List<String> OPTIONS = Arrays.asList(
            "Exit",
            "Show all knights",
            "Show all ammunition",
            "Add new knight",
            "Add new ammunition",
            "Remove knight",
            "Remove ammunition",
            "Add equipment to knight",
            "Remove equipment from knight"

    );


    public static void printMenu() {
        Dash.print();
        System.out.println("  Knight Ammunition Menu");
        Dash.print();
        for (int i = 1; i < OPTIONS.size(); i++) {
            System.out.println(i + " - " + OPTIONS.get(i));
        }
        System.out.println(0 + " - " + OPTIONS.get(0));
        Dash.print();
    }
}
