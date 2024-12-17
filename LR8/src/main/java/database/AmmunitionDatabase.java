package database;

import ammunition.Ammunition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class AmmunitionDatabase {
    private AmmunitionDatabase() {}

    private static final Logger LOG = LogManager.getLogger(AmmunitionDatabase.class);
    private static String FILE_PATH = "LR8\\ammunition.dat";
    private static int size = -1;

    public static int size() {
        size = getAmmunition().size();
        LOG.debug("Ammunition database size: {}", size);
        return size;
    }

    public static void setFilePath(String newFilePath) {
        FILE_PATH = newFilePath;
        LOG.debug("Ammunition database file path set to: {}", newFilePath);
    }

    public static boolean isEmpty() {
        boolean empty = size() == 0;
        LOG.debug("Ammunition database empty: {}", empty);
        return empty;
    }

    public static void addAmmunition(Ammunition ammunition) {
        Map<Integer, Ammunition> ammunitionMap = getAmmunition();
        ammunitionMap.put(size() + 1, ammunition);
        saveAmmunition(ammunitionMap);
        LOG.info("Added new ammunition to database: {}", ammunition);
        size = ammunitionMap.size();
    }

    public static void removeAmmunition(int id) {
        Map<Integer, Ammunition> ammunitionMap = getAmmunition();
        if (!ammunitionMap.containsKey(id)) {
            LOG.warn("Attempted to remove nonexistent ammunition with ID: {}", id);
            return;
        }
        for (; id < ammunitionMap.size(); id++) {
            ammunitionMap.put(id, ammunitionMap.get(id + 1));
        }
        ammunitionMap.remove(id);
        saveAmmunition(ammunitionMap);
        LOG.info("Removed ammunition with ID: {}", id);
        size = ammunitionMap.size();
    }

    public static Map<Integer, Ammunition> getAmmunition() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Map<Integer, Ammunition> ammunition = (Map<Integer, Ammunition>) in.readObject();
            LOG.info("Ammunition data successfully loaded from database.");
            return ammunition;
        } catch (Exception e) {
            LOG.error("Error loading ammunition data from file: {}", FILE_PATH, e);
        }
        return new HashMap<>();
    }

    public static void saveAmmunition(Map<Integer, Ammunition> ammunitionMap) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(ammunitionMap);
            size = ammunitionMap.size();
            LOG.info("Ammunition data successfully saved to database.");
        } catch (Exception e) {
            LOG.error("Error saving ammunition data to file: {}", FILE_PATH, e);
        }
    }
}
