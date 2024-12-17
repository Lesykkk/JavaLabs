package database;

import knight.Knight;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class KnightDatabaseTest {
    private static String testFilePath;

    @BeforeAll
    static void createTempFile() throws IOException {
        testFilePath = Files.createTempFile("knights_test", ".dat").toString();
        KnightDatabase.setFilePath(testFilePath);
    }

    @AfterAll
    static void removeTempFile() throws IOException {
        Files.deleteIfExists(Path.of(testFilePath));
    }

    @BeforeEach
    void setUp() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(testFilePath));
        out.writeObject(new HashMap<Integer, Knight>());
        out.close();
        KnightDatabase.size();
    }

    @Test
    void testIsEmpty() {
        assertTrue(KnightDatabase.isEmpty(), "Database should be empty initially");
    }

    @Test
    void testAddKnight() {
        Knight knight = new Knight("Arthur");
        KnightDatabase.addKnight(knight);

        assertFalse(KnightDatabase.isEmpty(), "Database should not be empty after adding a knight");
        assertEquals(1, KnightDatabase.size(), "Database size should be 1 after adding one knight");

        Map<Integer, Knight> knightsMap = KnightDatabase.getKnights();
        assertEquals(1, knightsMap.size(), "Map size should match");
        assertEquals(knight.toString(), knightsMap.get(1).toString(), "Knight data should match");
    }

    @Test
    void testRemoveKnight() {
        Knight knight = new Knight("Lancelot");

        KnightDatabase.addKnight(knight);
        assertEquals(1, KnightDatabase.size(), "Database size should be 1 after adding two knights");

        KnightDatabase.removeKnight(1);
        assertEquals(0, KnightDatabase.size(), "Database size should be 0 after removal");
    }

    @Test
    void testReplaceKnight() {
        Knight knight1 = new Knight("Percival");
        Knight knight2 = new Knight("Tristan");

        KnightDatabase.addKnight(knight1);
        KnightDatabase.replaceKnight(1, knight2);

        Map<Integer, Knight> knightsMap = KnightDatabase.getKnights();
        assertEquals(1, knightsMap.size(), "Database size should remain 1 after replacement");
        assertEquals(knight2.toString(), knightsMap.get(1).toString(), "Replaced knight data should match knight2");
    }
}
