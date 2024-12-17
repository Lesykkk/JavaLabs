package database;

import ammunition.Ammunition;
import ammunition.Armor;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AmmunitionDatabaseTest {
    private static String testFilePath;

    @BeforeAll
    static void createTempFile() throws IOException {
        testFilePath = Files.createTempFile("ammunition_test", ".dat").toString();
        AmmunitionDatabase.setFilePath(testFilePath);
    }

    @AfterAll
    static void removeTempFile() throws IOException {
        Files.deleteIfExists(Path.of(testFilePath));
    }

    @BeforeEach
    void setUp() throws IOException {
        AmmunitionDatabase.saveAmmunition(new HashMap<>());
        AmmunitionDatabase.size();
    }

    @Test
    void testIsEmpty() {
        assertTrue(AmmunitionDatabase.isEmpty(), "Database should be empty");
    }

    @Test
    void testAddAmmunition() {
        Ammunition armor = new Armor(10.0, 50.0);
        AmmunitionDatabase.addAmmunition(armor);

        assertFalse(AmmunitionDatabase.isEmpty(), "Database should not be empty after adding ammunition");
        assertEquals(1, AmmunitionDatabase.size(), "Database size should be 1 after adding one ammunition");

        Map<Integer, Ammunition> ammunitionMap = AmmunitionDatabase.getAmmunition();
        assertEquals(1, ammunitionMap.size(), "Map size should match");
        assertEquals(armor.toString(), ammunitionMap.get(1).toString(), "Ammunition data should match");
    }

    @Test
    void testRemoveAmmunition() {
        Ammunition armor = new Armor(10.0, 50.0);

        AmmunitionDatabase.addAmmunition(armor);
        assertEquals(1, AmmunitionDatabase.size(), "Database size should be 1");

        AmmunitionDatabase.removeAmmunition(1);
        assertEquals(0, AmmunitionDatabase.size(), "Database size should be 0 after removal");
    }
}
