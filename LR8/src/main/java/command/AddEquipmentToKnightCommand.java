package command;

import database.AmmunitionDatabase;
import database.KnightDatabase;
import knight.Knight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thirdparty.Choice;

import java.util.Scanner;

public class AddEquipmentToKnightCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(AddEquipmentToKnightCommand.class);

    private Scanner scanner;
    private Command showKnightsCommand;
    private Command showAmmunitionCommand;

    public AddEquipmentToKnightCommand(Scanner scanner, Command showKnightsCommand, Command showAmmunitionCommand) {
        this.scanner = scanner;
        this.showKnightsCommand = showKnightsCommand;
        this.showAmmunitionCommand = showAmmunitionCommand;
    }

    @Override
    public void execute() {
        if (KnightDatabase.isEmpty()) {
            LOG.warn("Knight database is empty. No knights to select.");
            System.out.println("No knights available to select.");
            return;
        }

        if (AmmunitionDatabase.isEmpty()) {
            LOG.warn("Ammunition database is empty. No equipment to select.");
            System.out.println("No equipment available to select.");
            return;
        }

        System.out.println("Select a knight:");
        showKnightsCommand.execute();

        int knightId = Choice.inputId(scanner, KnightDatabase.size());
        if (!KnightDatabase.getKnights().containsKey(knightId)) {
            LOG.warn("Invalid knight ID [{}] entered.", knightId);
            System.out.println("Invalid knight ID. Operation cancelled.");
            return;
        }

        System.out.println("\nSelect an equipment:");
        showAmmunitionCommand.execute();

        int ammunitionId = Choice.inputId(scanner, AmmunitionDatabase.size());
        if (!AmmunitionDatabase.getAmmunition().containsKey(ammunitionId)) {
            LOG.warn("Invalid ammunition ID [{}] entered.", ammunitionId);
            System.out.println("Invalid equipment ID. Operation cancelled.");
            return;
        }

        Knight knight = KnightDatabase.getKnights().get(knightId);
        knight.addEquipment(AmmunitionDatabase.getAmmunition().get(ammunitionId));
        KnightDatabase.replaceKnight(knightId, knight);
        System.out.println("Equipment successfully added to knight.");
    }

    @Override
    public String getDesc() {
        return "Add equipment to knight";
    }
}
