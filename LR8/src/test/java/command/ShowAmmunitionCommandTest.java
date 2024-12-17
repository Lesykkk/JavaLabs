package command;

import ammunition.Ammunition;
import ammunition.Armor;
import database.AmmunitionDatabase;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShowAmmunitionCommandTest {
    private static String testFilePath;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private Scanner mockScanner;
    private ShowAmmunitionCommand showAmmunitionCommand;

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
    void setUp() {
        // Clear the ammunition database
        AmmunitionDatabase.saveAmmunition(new HashMap<>());

        // Redirect System.out to capture printed messages
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Mock scanner input
        mockScanner = mock(Scanner.class);
        showAmmunitionCommand = new ShowAmmunitionCommand(mockScanner);
    }

    @AfterEach
    void restoreSystemOut() {
        // Restore original System.out after each test
        System.setOut(originalOut);
    }

    @Test
    void testShowAmmunition_WhenDatabaseIsEmpty() {
        showAmmunitionCommand.execute();
        String expectedOutput = "Ammunition database is empty.\r\n";
        assertEquals(expectedOutput, outputStream.toString(), "Output should indicate that the ammunition database is empty.");
    }

    @Test
    void testShowAmmunitionWithoutSorting() {
        Ammunition armor1 = new Armor(15.0, 100.0);
        Ammunition armor2 = new Armor(20.0, 150.0);
        AmmunitionDatabase.addAmmunition(armor1);
        AmmunitionDatabase.addAmmunition(armor2);
        when(mockScanner.next()).thenReturn("no", "no");
        showAmmunitionCommand.execute();
        String expectedOutput = "ID [1] Ammunition: " + armor1 + "\r\n" +
                "ID [2] Ammunition: " + armor2 + "\r\n";
        assertTrue(outputStream.toString().contains(expectedOutput), "Output should display all ammunition without sorting or filtering.");
    }

    @Test
    void testShowAmmunitionWithSortingByWeight() {
        Ammunition armor1 = new Armor(20.0, 150.0);
        Ammunition armor2 = new Armor(15.0, 100.0);
        AmmunitionDatabase.addAmmunition(armor1);
        AmmunitionDatabase.addAmmunition(armor2);
        when(mockScanner.next()).thenReturn("yes", "no");
        showAmmunitionCommand.execute();
        String expectedOutput = "ID [2] Ammunition: " + armor2 + "\r\n" +
                "ID [1] Ammunition: " + armor1 + "\r\n";
        assertTrue(outputStream.toString().contains(expectedOutput), "Output should display ammunition sorted by weight.");
    }
}
