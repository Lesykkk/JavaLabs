package command;

import database.AmmunitionDatabase;
import ammunition.*;
import java.util.Scanner;

public class AddAmmunitionCommand implements Command {
    private Scanner scanner;

    public AddAmmunitionCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Choose ammunition type\n\t1 - Armor\n\t2 - Helmet\n\t3 - Shield\n\t4 - Sword");

        int type = -1;
        while (true) {
            System.out.print("Enter type: ");
            type = scanner.nextInt();
            if (type < 1 || type > 4) {
                System.out.println("Incorrect input. Try again.");
                continue;
            }
            break;
        }

        System.out.print("Enter ammunition weight: ");
        double weight = scanner.nextDouble();

        System.out.print("Enter ammunition price: ");
        double price = scanner.nextDouble();

        Ammunition ammunition = null;
        switch (type) {
            case 1 -> ammunition = new Armor(weight, price);
            case 2 -> ammunition = new Helmet(weight, price);
            case 3 -> ammunition = new Shield(weight, price);
            case 4 -> ammunition = new Sword(weight, price);
        }

        AmmunitionDatabase.addAmmunition(ammunition);
    }
}
