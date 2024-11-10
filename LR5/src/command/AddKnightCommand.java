package command;

import database.KnightDatabase;
import knight.Knight;
import java.util.Scanner;

public class AddKnightCommand implements Command {
    private Scanner scanner;

    public AddKnightCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Enter knight name: ");
        String knightName = scanner.next();
        Knight knight = new Knight(knightName);
        KnightDatabase.addKnight(knight);
    }
}
