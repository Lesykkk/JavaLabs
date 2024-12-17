package command;

import ammunition.Ammunition;
import database.KnightDatabase;
import knight.Knight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thirdparty.Choice;

import java.util.List;
import java.util.Scanner;

public class RemoveEquipmentFromKnightCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(RemoveEquipmentFromKnightCommand.class);

    private Scanner scanner;
    private Command showKnightsCommand;

    public RemoveEquipmentFromKnightCommand(Scanner scanner, Command showKnightsCommand) {
        this.scanner = scanner;
        this.showKnightsCommand = showKnightsCommand;
    }

    @Override
    public void execute() {
        if (KnightDatabase.isEmpty()) {
            LOG.warn("Knight database is empty. No knights to select.");
            System.out.println("No knights available to select.");
            return;
        }

        System.out.println("Select a knight");
        showKnightsCommand.execute();

        int knightId = Choice.inputId(scanner, KnightDatabase.size());
        Knight knight = KnightDatabase.getKnights().get(knightId);

        if (knight == null) {
            LOG.warn("Invalid knight ID [{}] entered.", knightId);
            System.out.println("Invalid knight ID. Operation cancelled.");
            return;
        }

        List<Ammunition> equipmentList = knight.getEquipment();
        if (equipmentList.isEmpty()) {
            LOG.warn("Knight ID [{}] has no equipment to remove.", knightId);
            System.out.println("This knight has no equipment.");
            return;
        }

        System.out.println("\nSelect an equipment to remove:");
        for (int i = 0; i < equipmentList.size(); i++) {
            System.out.println("\tID [" + (i + 1) + "] " + equipmentList.get(i));
        }

        int equipmentIndex = Choice.inputId(scanner, equipmentList.size()) - 1;
        if (equipmentIndex < 0 || equipmentIndex >= equipmentList.size()) {
            LOG.warn("Invalid equipment index [{}] entered for Knight ID [{}].", equipmentIndex, knightId);
            System.out.println("Invalid equipment ID. Operation cancelled.");
            return;
        }

        knight.removeEquipment(equipmentIndex);
        KnightDatabase.replaceKnight(knightId, knight);
        System.out.println("Equipment successfully removed from knight.");
    }

    @Override
    public String getDesc() {
        return "Remove equipment from knight";
    }
}
