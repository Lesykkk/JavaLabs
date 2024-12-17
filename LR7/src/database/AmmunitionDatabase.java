package database;

import ammunition.Ammunition;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AmmunitionDatabase {
    private AmmunitionDatabase() {}
    private static String FILE_PATH = "LR7\\ammunition.dat";
    private static int size = -1;

    public static int size() {
        size = getAmmunition().size();
        return size;
    }

    public static void setFilePath(String newFilePath) {
        FILE_PATH = newFilePath;
    }

    public static boolean isEmpty() {
        return size() == 0;
    }

    public static void addAmmunition(Ammunition ammunition) {
        Map<Integer, Ammunition> ammunitionMap = getAmmunition();
        ammunitionMap.put(size() + 1, ammunition);
        saveAmmunition(ammunitionMap);
        size = ammunitionMap.size();
    }

    public static void removeAmmunition(int id) {
        Map<Integer, Ammunition> ammunitionMap = getAmmunition();
        for (; id < ammunitionMap.size(); id++) {
            ammunitionMap.put(id, ammunitionMap.get(id + 1));
        }
        ammunitionMap.remove(id);
        saveAmmunition(ammunitionMap);
        size = ammunitionMap.size();
    }

    public static Map<Integer, Ammunition> getAmmunition() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Map<Integer, Ammunition>)in.readObject();
//            System.out.println("Ammunition data loaded from database.");
        } catch (Exception e) {
//            System.out.println("Error loading ammunition data.");
        }
        return new HashMap<>();
    }

    public static void saveAmmunition(Map<Integer, Ammunition> ammunitionMap) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(ammunitionMap);
            size = ammunitionMap.size();
//            System.out.println("Ammunition data saved to database.");
        } catch (Exception e) {
            System.out.println("Error saving ammunition data.");
        }

    }
}
