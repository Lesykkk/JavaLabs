package game;

import droid.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private static final String LOG_FILE = "LR3\\game_logs.txt"; // Файл для логів
    public static Logger logger;

    private Game() {}

    static List<Droid> droids = new ArrayList<>();

    public static void start() {
        logger = new Logger(LOG_FILE);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            logger.log("\nОберіть дію:");
            Menu.printMenu(logger);

            int choice = scanner.nextInt();
            if (choice < 0 || choice >= Menu.OPTIONS.size()) {
                logger.log("Невірний вибір! Спробуйте знову.");
                continue;
            }

            logger.log("Ви вибрали: " + Menu.OPTIONS.get(choice));
            if (!Menu.execute(choice, scanner, logger)) {
                break;
            }
        }
        scanner.close();
        logger.close();
    }
}