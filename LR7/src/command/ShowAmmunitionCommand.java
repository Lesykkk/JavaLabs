package command;

import database.AmmunitionDatabase;
import ammunition.Ammunition;
import java.util.*;

public class ShowAmmunitionCommand implements Command {
    Scanner scanner;

    public ShowAmmunitionCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (AmmunitionDatabase.isEmpty()) {
            System.out.println("Ammunition database is empty.");
        } else {
            Map<Integer, Ammunition> ammunitionMap = AmmunitionDatabase.getAmmunition();
            List<Map.Entry<Integer, Ammunition>> ammunitionMapEntriesList = new ArrayList<>(ammunitionMap.entrySet());

            System.out.print("Sort ammunition by weight? Type [yes/no]: ");
            if (scanner.next().equalsIgnoreCase("yes")) {
                ammunitionMapEntriesList.sort(Comparator.comparingDouble(entry -> entry.getValue().getWeight()));
            }
            displayAmmunition(ammunitionMapEntriesList);

            System.out.print("\nFilter ammunition by price? Type [yes/no]: ");
            if (scanner.next().equalsIgnoreCase("yes")) {
                double minPrice, maxPrice;
                System.out.print("Enter MIN and MAX price: ");
                minPrice = scanner.nextDouble();
                maxPrice = scanner.nextDouble();

                ammunitionMapEntriesList.removeIf(entry -> entry.getValue().getPrice() < minPrice || entry.getValue().getPrice() > maxPrice);
                displayAmmunition(ammunitionMapEntriesList);
            }
        }
    }

    @Override
    public String getDesc() {
        return "Show all ammunition";
    }

    private void displayAmmunition(List<Map.Entry<Integer, Ammunition>> ammunitionMapEntriesList) {
        for (Map.Entry<Integer, Ammunition> entry : ammunitionMapEntriesList) {
            Ammunition ammunition = entry.getValue();
            int id = entry.getKey();  // Original index
            System.out.println("ID [" + id + "] Ammunition: " + ammunition);
        }
    }
}