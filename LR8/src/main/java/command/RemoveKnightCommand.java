package command;

import database.KnightDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thirdparty.Choice;

import java.util.Scanner;

public class RemoveKnightCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(RemoveKnightCommand.class);
    private Scanner scanner;
    private Command showKnightsCommand;

    public RemoveKnightCommand(Scanner scanner, Command showKnightsCommand) {
        this.scanner = scanner;
        this.showKnightsCommand = showKnightsCommand;
    }

    @Override
    public void execute() {
        if (KnightDatabase.isEmpty()) {
            LOG.warn("Knight database is empty. No knights to remove.");
            System.out.println("No knights available to remove.");
            return;
        }

        System.out.println("Select a knight to remove");
        showKnightsCommand.execute();

        int knightCount = KnightDatabase.size();
        int id = Choice.inputId(scanner, knightCount);

        if (id < 1 || id > knightCount) {
            LOG.warn("Invalid knight ID [{}] entered. Operation cancelled.", id);
            System.out.println("Invalid knight ID. Operation cancelled.");
            return;
        }

        KnightDatabase.removeKnight(id);
        System.out.println("Knight removed successfully.");
    }

    @Override
    public String getDesc() {
        return "Remove knight";
    }
}
