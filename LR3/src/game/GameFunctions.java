package game;

import battle.*;
import droid.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
import static game.Game.droids;

public class GameFunctions {
    private GameFunctions() {}

    static void createDroid(Scanner scanner, Logger logger) {
        scanner.nextLine();
        Droid droid;
        logger.log("Введіть ім'я: ");
        String name = scanner.next();
        int choice;
        while (true) {
            logger.log("1 - Задати характеристики вручну");
            logger.log("2 - Згенерувати характеристики");
            choice = scanner.nextInt();
            if (choice != 1 && choice != 2) {
                logger.log("Вибір неправильний!");
                continue;
            }
            break;
        }

        if (choice == 1) {
            while (true) {
                int type;
                logger.log("Оберіть тип дроїда: 1 - Штурмовик, 2 - Механік, 3 - Стрілець");
                type = scanner.nextInt();
                int health, damage;
                switch (type) {
                    case 1:
                        logger.log("Введіть здоров'я (76-90): ");
                        health = scanner.nextInt();
                        logger.log("Введіть шкоду (24-30): ");
                        damage = scanner.nextInt();
                        droid = new Attacker(name, health, damage);
                        break;
                    case 2:
                        logger.log("Введіть здоров'я (96-120): ");
                        health = scanner.nextInt();
                        logger.log("Введіть шкоду (20-26): ");
                        damage = scanner.nextInt();
                        droid = new Mechanic(name, health, damage);
                        break;
                    case 3:
                        logger.log("Введіть здоров'я (62-76): ");
                        health = scanner.nextInt();
                        logger.log("Введіть шкоду (27-33): ");
                        damage = scanner.nextInt();
                        droid = new Archer(name, health, damage);
                        break;
                    default:
                        logger.log("Вибір неправильний!");
                        continue;
                }
                break;
            }
        } else {
            Random random = new Random();
            int type = random.nextInt(1, 4);
            if (type == 1) {
                droid = new Attacker(name);
            } else if (type == 2) {
                droid = new Mechanic(name);
            } else {
                droid = new Archer(name);
            }
        }
        droids.add(droid);
        logger.log("Дроїд створений: " + droid);
    }

    static void showDroids(Logger logger) {
        if (droids.isEmpty()) {
            logger.log("Немає створених дроїдів.");
        } else {
            logger.log("Список створених дроїдів:");
            for (int i = 0; i < droids.size(); i++) {
                logger.log((i + 1) + ". " + droids.get(i));
            }
        }
    }

    static void oneOnOneBattle(Scanner scanner, Logger logger) {
        scanner.nextLine();
        if (droids.size() < 2) {
            logger.log("Потрібно хоча б 2 дроїди для бою.");
            return;
        }

        Droid droid1;
        Droid droid2;

        logger.log("Оберіть першого дроїда:");
        showDroids(logger);
        int droid1Index = scanner.nextInt() - 1;
        if (droid1Index < 0 || droid1Index >= droids.size()) {
            logger.log("Невірний вибір!.");
            return;
        }
        droid1 = droids.get(droid1Index);
        droids.remove(droid1Index);

        logger.log("Оберіть другого дроїда:");
        showDroids(logger);
        int droid2Index = scanner.nextInt() - 1;
        if (droid2Index < 0 || droid2Index >= droids.size()) {
            logger.log("Невірний вибір!.");
            return;
        }
        droid2 = droids.get(droid2Index);
        droids.remove(droid2Index);


        OneOnOne battle = new OneOnOne(droid1, droid2);
        battle.startBattle(logger);
    }

    static void teamOnTeamBattle(Scanner scanner, Logger logger) {
        scanner.nextLine();
        if (droids.size() < 4) {
            logger.log("Потрібно хоча б 4 дроїди для бою.");
            return;
        }

        logger.log("Скільки дроїдів буде в кожній команді? (мінімум 2): ");
        int teamSize = scanner.nextInt();
        if (teamSize < 2 || teamSize * 2 > droids.size()) {
            logger.log("Невірний вибір!");
            return;
        }

        List<Droid> redTeam = new ArrayList<>();
        List<Droid> blueTeam = new ArrayList<>();

        logger.log("\u001B[31mЧервона команда:");
        pickDroidsForTeam(scanner, redTeam, teamSize, logger);

        logger.log("\u001B[34mСиня команда:");
        pickDroidsForTeam(scanner, blueTeam, teamSize, logger);

        logger.log("\u001B[0m");

        TeamOnTeam battle = new TeamOnTeam(redTeam, blueTeam);
        battle.startBattle(logger);
    }

    static void writeDroidsToFile(Logger logger) {
        if (droids.isEmpty()) {
            logger.log("Немає створених дроїдів!");
            return;
        }

        File file = new File("LR3\\droids.bin");

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(droids);
            logger.log("Дроїдів успішно записано у файл \"" + file.getAbsolutePath() + "\"");
        } catch (IOException e) {
            logger.log("Помилка під час запису у файл: " + e.getMessage());
        }
    }

    static void readDroidsFromFile(Scanner scanner, Logger logger) {
        scanner.nextLine();
        logger.log("Введіть шлях до файлу для зчитування дроїдів:");
        String filePath = scanner.nextLine();
        File file = new File(filePath);

        if (!file.exists()) {
            logger.log("Файл за вказаним шляхом не знайдено: " + filePath);
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            List<Droid> readDroids = (List<Droid>) in.readObject();
            droids.addAll(readDroids);
            logger.log("Дроїдів успішно зчитано з файлу \"" + file.getAbsolutePath() + "\"");
        } catch (IOException | ClassNotFoundException e) {
            logger.log("Помилка під час зчитування з файлу: " + e.getMessage());
        }
    }

    private static void pickDroidsForTeam(Scanner scanner, List<Droid> team, int teamSize, Logger logger) {
        scanner.nextLine();
        for (int i = 0; i < teamSize; i++) {
            showDroids(logger);
            logger.log("Оберіть дроїда для команди: ");
            int index = scanner.nextInt() - 1;
            if (index < 0 || index >= droids.size()) {
                logger.log("Невірний вибір!");
                i--;
                continue;
            }
            team.add(droids.get(index));
            droids.remove(index);
        }
    }
}