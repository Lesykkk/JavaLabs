package command;

import database.KnightDatabase;
import knight.Knight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class AddKnightCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(AddKnightCommand.class);
    private Scanner scanner;

    public AddKnightCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Enter knight name: ");
        String knightName = scanner.next();

        if (knightName.isBlank()) {
            LOG.warn("Empty knight name entered. Operation cancelled.");
            System.out.println("Knight name cannot be empty. Operation cancelled.");
            return;
        }

        Knight knight = new Knight(knightName);
        KnightDatabase.addKnight(knight);
        System.out.println("Knight added successfully.");
    }

    @Override
    public String getDesc() {
        return "Add new knight";
    }
}
