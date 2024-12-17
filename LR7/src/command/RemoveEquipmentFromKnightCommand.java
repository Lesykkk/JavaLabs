package command;

import ammunition.Ammunition;
import database.KnightDatabase;
import knight.Knight;
import thirdparty.Choice;
import java.util.List;
import java.util.Scanner;

public class RemoveEquipmentFromKnightCommand implements Command {
    private Scanner scanner;
    private Command showKnightsCommand;

    public RemoveEquipmentFromKnightCommand(Scanner scanner, Command showKnightsCommand) {
        this.scanner = scanner;
        this.showKnightsCommand = showKnightsCommand;
    }

    @Override
    public void execute() {
        System.out.println("Select a knight");
        showKnightsCommand.execute();
        int knightId = Choice.inputId(scanner, KnightDatabase.size());
        Knight knight = KnightDatabase.getKnights().get(knightId);

        System.out.println("\nSelect an equipment");
        List<Ammunition> equipmentList = knight.getEquipment();
        for (int i = 0; i < equipmentList.size(); i++) {
            System.out.println("\tID [" + i + "] " + equipmentList.get(i));
        }
        int equipmentIndex = Choice.inputId(scanner, equipmentList.size()) - 1;
        knight.removeEquipment(equipmentIndex);
        KnightDatabase.replaceKnight(knightId, knight);
    }

    @Override
    public String getDesc() {
        return "Remove equipment from knight";
    }
}