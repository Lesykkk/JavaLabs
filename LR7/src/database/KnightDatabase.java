package database;

import knight.Knight;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class KnightDatabase {
    private KnightDatabase() {}
    private static String FILE_PATH = "LR7\\knights.dat";
    private static int size = -1;

    public static int size() {
        size = getKnights().size();
        return size;
    }

    public static void setFilePath(String newFilePath) {
        FILE_PATH = newFilePath;
    }

    public static boolean isEmpty() {
        return size() == 0;
    }

    public static void addKnight(Knight knight) {
        Map<Integer, Knight> knightsMap = getKnights();
        knightsMap.put(size() + 1, knight);
        saveKnights(knightsMap);
        size = knightsMap.size();
    }

    public static void removeKnight(int id) {
        Map<Integer, Knight> knightsMap = getKnights();
        for (; id < knightsMap.size(); id++) {
            knightsMap.put(id, knightsMap.get(id + 1));
        }
        knightsMap.remove(id);
        saveKnights(knightsMap);
        size = knightsMap.size();
    }

    public static void replaceKnight(int id, Knight newKnight) {
        Map<Integer, Knight> knightsMap = getKnights();
        knightsMap.replace(id, newKnight);
        saveKnights(knightsMap);
    }

    public static Map<Integer, Knight> getKnights() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Map<Integer, Knight>)in.readObject();
//            System.out.println("Knights data loaded from database.");
        } catch (Exception e) {
//            System.out.println("Error loading knights data.");
        }
        return new HashMap<>();
    }

    public static void saveKnights(Map<Integer, Knight> knightsMap) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(knightsMap);
            size = knightsMap.size();
//            System.out.println("Knight data saved to database.");
        } catch (Exception e) {
            System.out.println("Error saving knights data.");
        }
    }
}
