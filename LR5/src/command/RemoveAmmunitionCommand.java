package command;

import database.AmmunitionDatabase;
import thirdparty.Choice;
import java.util.Scanner;

public class RemoveAmmunitionCommand implements Command {
    private Scanner scanner;
    private Command showAmmunitionCommand;

    public RemoveAmmunitionCommand(Scanner scanner, Command showAmmunitionCommand) {
        this.scanner = scanner;
        this.showAmmunitionCommand = showAmmunitionCommand;
    }

    @Override
    public void execute() {
        System.out.println("Select an ammunition to remove");
        showAmmunitionCommand.execute();

        int id = Choice.inputId(scanner, AmmunitionDatabase.size());
        AmmunitionDatabase.removeAmmunition(id);
    }
}