package knightapp;

import command.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thirdparty.Dash;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Menu {
    private static final Logger LOG = LogManager.getLogger(Menu.class);
    Map<Integer, Command> menuCommands = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

    public Menu() {
        LOG.info("Initializing menu");
        init();
    }

    protected void init() {
        menuCommands.put(1, new ShowKnightsCommand());
        menuCommands.put(2, new ShowAmmunitionCommand(scanner));
        menuCommands.put(3, new AddKnightCommand(scanner));
        menuCommands.put(4, new AddAmmunitionCommand(scanner));
        menuCommands.put(5, new RemoveKnightCommand(scanner, menuCommands.get(1)));
        menuCommands.put(6, new RemoveAmmunitionCommand(scanner, menuCommands.get(2)));
        menuCommands.put(7, new AddEquipmentToKnightCommand(scanner, menuCommands.get(1), menuCommands.get(2)));
        menuCommands.put(8, new RemoveEquipmentFromKnightCommand(scanner, menuCommands.get(1)));
        LOG.info("Menu initialized successfully.");
    }

    public void display() {
        LOG.info("Displaying menu to the user");
        while (true) {
            try {
                System.out.println();
                printMenu();
                int choice = scanner.nextInt();
                if (choice == 0) {
                    LOG.info("User selected to exit the program.");
                    break;
                } else {
                    Command command = menuCommands.get(choice);
                    if (command != null) {
                        LOG.info("Executing command: {}", command.getDesc());
                        System.out.println();
                        command.execute();
                    } else {
                        LOG.warn("User entered an invalid option: {}", choice);
                        System.out.println("Wrong option, please try again.");
                    }
                }
            } catch (Exception e) {
                LOG.error("An error occurred while executing a menu option: ", e);
                scanner.nextLine();
                System.out.println("Invalid input, please enter a number.");
            }
        }
    }

    public void printMenu() {
        Dash.print();
        System.out.println("  Knight Ammunition Menu");
        Dash.print();
        for (Map.Entry<Integer, Command> entry : menuCommands.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getDesc());
        }
        System.out.println(0 + " - Exit ");
        Dash.print();
    }
}
