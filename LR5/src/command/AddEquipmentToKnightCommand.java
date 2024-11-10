package command;

import database.AmmunitionDatabase;
import database.KnightDatabase;
import knight.Knight;
import thirdparty.Choice;
import java.util.Scanner;

public class AddEquipmentToKnightCommand implements Command {
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
        System.out.println("Select a knight:");
        showKnightsCommand.execute();
        int knightId = Choice.inputId(scanner, KnightDatabase.size());

        System.out.println("\nSelect an equipment:");
        showAmmunitionCommand.execute();
        int ammunitionId = Choice.inputId(scanner, AmmunitionDatabase.size());

        Knight knight = KnightDatabase.getKnights().get(knightId);
        knight.addEquipment(AmmunitionDatabase.getAmmunition().get(ammunitionId));
        KnightDatabase.replaceKnight(knightId, knight);
    }
}
