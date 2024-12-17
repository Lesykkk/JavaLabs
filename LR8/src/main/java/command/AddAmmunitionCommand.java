package command;

import ammunition.*;
import database.AmmunitionDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class AddAmmunitionCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(AddAmmunitionCommand.class);
    private Scanner scanner;

    public AddAmmunitionCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Choose ammunition type\n\t1 - Armor\n\t2 - Helmet\n\t3 - Shield\n\t4 - Sword");

        int type = -1;
        while (true) {
            try {
                System.out.print("Enter type: ");
                type = scanner.nextInt();
                if (type < 1 || type > 4) {
                    System.out.println("Incorrect input. Try again.");
                    LOG.warn("User entered invalid ammunition type: {}", type);
                    continue;
                }
                break;
            } catch (Exception e) {
                LOG.error("Error while entering ammunition type", e);
                scanner.nextLine();
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        double weight = 0.0;
        while (true) {
            try {
                System.out.print("Enter ammunition weight: ");
                weight = scanner.nextDouble();
                if (weight <= 0) {
                    System.out.println("Weight must be greater than zero. Try again.");
                    LOG.warn("User entered invalid weight: {}", weight);
                    continue;
                }
                break;
            } catch (Exception e) {
                LOG.error("Error while entering ammunition weight", e);
                scanner.nextLine();
                System.out.println("Invalid input. Please enter a valid weight.");
            }
        }

        double price = 0.0;
        while (true) {
            try {
                System.out.print("Enter ammunition price: ");
                price = scanner.nextDouble();
                if (price <= 0) {
                    System.out.println("Price must be greater than zero. Try again.");
                    LOG.warn("User entered invalid price: {}", price);
                    continue;
                }
                break;
            } catch (Exception e) {
                LOG.error("Error while entering ammunition price", e);
                scanner.nextLine();
                System.out.println("Invalid input. Please enter a valid price.");
            }
        }

        Ammunition ammunition = null;
        switch (type) {
            case 1 -> {
                ammunition = new Armor(weight, price);
                LOG.info("Created new Armor: weight={}, price={}", weight, price);
            }
            case 2 -> {
                ammunition = new Helmet(weight, price);
                LOG.info("Created new Helmet: weight={}, price={}", weight, price);
            }
            case 3 -> {
                ammunition = new Shield(weight, price);
                LOG.info("Created new Shield: weight={}, price={}", weight, price);
            }
            case 4 -> {
                ammunition = new Sword(weight, price);
                LOG.info("Created new Sword: weight={}, price={}", weight, price);
            }
        }

        AmmunitionDatabase.addAmmunition(ammunition);
        System.out.println("Ammunition successfully added!");
    }

    @Override
    public String getDesc() {
        return "Add new ammunition";
    }
}
