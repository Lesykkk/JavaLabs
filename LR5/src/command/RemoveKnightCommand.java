package command;

import database.KnightDatabase;
import thirdparty.Choice;
import java.util.Scanner;

public class RemoveKnightCommand implements Command {
    private Scanner scanner;
    private Command showKnightsCommand;

    public RemoveKnightCommand(Scanner scanner, Command showKnightsCommand) {
        this.scanner = scanner;
        this.showKnightsCommand = showKnightsCommand;
    }

    @Override
    public void execute() {
        System.out.println("Select a knight to remove");
        showKnightsCommand.execute();

        int id = Choice.inputId(scanner, KnightDatabase.size());
        KnightDatabase.removeKnight(id);
    }
}
