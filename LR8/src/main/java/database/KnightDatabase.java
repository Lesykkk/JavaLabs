package database;

import knight.Knight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class KnightDatabase {
    private KnightDatabase() {}

    private static final Logger LOG = LogManager.getLogger(KnightDatabase.class);
    private static String FILE_PATH = "LR8\\knights.dat";
    private static int size = -1;

    public static int size() {
        size = getKnights().size();
        LOG.debug("Knight database size: {}", size);
        return size;
    }

    public static void setFilePath(String newFilePath) {
        FILE_PATH = newFilePath;
        LOG.debug("Knight database file path set to: {}", newFilePath);
    }

    public static boolean isEmpty() {
        boolean empty = size() == 0;
        LOG.debug("Knight database empty: {}", empty);
        return empty;
    }

    public static void addKnight(Knight knight) {
        Map<Integer, Knight> knightsMap = getKnights();
        knightsMap.put(size() + 1, knight);
        saveKnights(knightsMap);
        LOG.info("Added new knight to database: {}", knight);
        size = knightsMap.size();
    }

    public static void removeKnight(int id) {
        Map<Integer, Knight> knightsMap = getKnights();
        if (!knightsMap.containsKey(id)) {
            LOG.warn("Attempted to remove nonexistent knight with ID: {}", id);
            return;
        }
        for (; id < knightsMap.size(); id++) {
            knightsMap.put(id, knightsMap.get(id + 1));
        }
        knightsMap.remove(id);
        saveKnights(knightsMap);
        LOG.info("Removed knight with ID: {}", id);
        size = knightsMap.size();
    }

    public static void replaceKnight(int id, Knight newKnight) {
        Map<Integer, Knight> knightsMap = getKnights();
        if (!knightsMap.containsKey(id)) {
            LOG.warn("Attempted to replace nonexistent knight with ID: {}", id);
            return;
        }
        knightsMap.replace(id, newKnight);
        saveKnights(knightsMap);
        LOG.info("Replaced knight at ID {} with new knight: {}", id, newKnight);
    }

    public static Map<Integer, Knight> getKnights() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Map<Integer, Knight> knights = (Map<Integer, Knight>) in.readObject();
            LOG.info("Knight data successfully loaded from database.");
            return knights;
        } catch (Exception e) {
            LOG.error("Error loading knights data from file: {}", FILE_PATH, e);
        }
        return new HashMap<>();
    }

    public static void saveKnights(Map<Integer, Knight> knightsMap) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(knightsMap);
            size = knightsMap.size();
            LOG.info("Knight data successfully saved to database.");
        } catch (Exception e) {
            LOG.error("Error saving knights data to file: {}", FILE_PATH, e);
        }
    }
}
