package game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import static game.GameFunctions.*;

public class Menu {
    private Menu() {}

    public static final List<String> OPTIONS = Arrays.asList(
            "Вихід",
            "Створити нового дроїда",
            "Показати створених дроїдів",
            "Бій 1 на 1",
            "Бій команда на команду",
            "Запис створених дроїдів у файл",
            "Зчитування дроїдів з файлу"
    );

    public static void printMenu(Logger logger) {
        for (int i = 1; i < OPTIONS.size(); i++) {
            logger.log(i + " - " + OPTIONS.get(i));
        }
        logger.log(0 + " - " + OPTIONS.get(0));
    }

    public static boolean execute(int index, Scanner scanner, Logger logger) {
        switch (index) {
            case 1:
                createDroid(scanner, logger);
                break;
            case 2:
                showDroids(logger);
                break;
            case 3:
                oneOnOneBattle(scanner, logger);
                break;
            case 4:
                teamOnTeamBattle(scanner, logger);
                break;
            case 5:
                writeDroidsToFile(logger);
                break;
            case 6:
                readDroidsFromFile(scanner, logger);
                break;
            case 0:
                return false;
        }
        return true;
    }
}