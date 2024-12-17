package knightapp;

import command.*;
import thirdparty.Dash;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

class Menu {
    Map<Integer, Command> menuCommands = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

    public Menu() {
        init();
    }

    protected void init(){
        menuCommands.put(1, new ShowKnightsCommand());
        menuCommands.put(2, new ShowAmmunitionCommand(scanner));
        menuCommands.put(3, new AddKnightCommand(scanner));
        menuCommands.put(4, new AddAmmunitionCommand(scanner));
        menuCommands.put(5, new RemoveKnightCommand(scanner, menuCommands.get(1)));
        menuCommands.put(6, new RemoveAmmunitionCommand(scanner, menuCommands.get(2)));
        menuCommands.put(7, new AddEquipmentToKnightCommand(scanner, menuCommands.get(1), menuCommands.get(2)));
        menuCommands.put(8, new RemoveEquipmentFromKnightCommand(scanner, menuCommands.get(1)));
    }

    public void display() {
        while (true) {
            System.out.println();
            printMenu();
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

    public void printMenu() {
        Dash.print();
        System.out.println("  Knight Ammunition Menu");
        Dash.print();
        for (Map.Entry<Integer, Command> entry : menuCommands.entrySet()){
            System.out.println(entry.getKey() + " - " + entry.getValue().getDesc());
        }
        System.out.println(0 + " - Exit ");
        Dash.print();
    }
}
