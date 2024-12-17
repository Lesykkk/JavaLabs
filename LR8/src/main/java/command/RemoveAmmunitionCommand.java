package command;

import database.AmmunitionDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thirdparty.Choice;

import java.util.Scanner;

public class RemoveAmmunitionCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(RemoveAmmunitionCommand.class);
    private Scanner scanner;
    private Command showAmmunitionCommand;

    public RemoveAmmunitionCommand(Scanner scanner, Command showAmmunitionCommand) {
        this.scanner = scanner;
        this.showAmmunitionCommand = showAmmunitionCommand;
    }

    @Override
    public void execute() {
        System.out.println("Select an ammunition to remove:");
        showAmmunitionCommand.execute();

        int id = Choice.inputId(scanner, AmmunitionDatabase.size());
        if (id < 1 || id > AmmunitionDatabase.size()) {
            LOG.warn("Invalid ID entered: {}", id);
            System.out.println("Invalid ID. Operation cancelled.");
            return;
        }

        AmmunitionDatabase.removeAmmunition(id);
        System.out.println("Ammunition removed successfully.");
    }

    @Override
    public String getDesc() {
        return "Remove ammunition";
    }
}
