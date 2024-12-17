package command;

import ammunition.Armor;
import ammunition.Sword;
import database.AmmunitionDatabase;
import ammunition.Ammunition;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RemoveAmmunitionCommandTest {
    private static String testFilePath;
    private Scanner mockScanner;
    private Command mockShowCommand;
    private RemoveAmmunitionCommand removeAmmunitionCommand;

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
        mockScanner = mock(Scanner.class);
        mockShowCommand = mock(Command.class);
        removeAmmunitionCommand = new RemoveAmmunitionCommand(mockScanner, mockShowCommand);
        AmmunitionDatabase.saveAmmunition(new HashMap<>());
    }

    @Test
    void testRemoveAmmunitionSuccess() {
        Map<Integer, Ammunition> ammoMap = new HashMap<>();
        ammoMap.put(1, new Sword(10.0, 100.0));
        AmmunitionDatabase.saveAmmunition(ammoMap);

        when(mockScanner.nextInt()).thenReturn(1);

        assertEquals(1, AmmunitionDatabase.size(), "Database should contain 1 item before removal");
        removeAmmunitionCommand.execute();
        assertEquals(0, AmmunitionDatabase.size(), "Database should contain 0 item after removal");
    }

    @Test
    void testRemoveAmmunitionInvalidId() {
        Map<Integer, Ammunition> ammoMap = new HashMap<>();
        ammoMap.put(1, new Armor(12.0, 120.0));
        AmmunitionDatabase.saveAmmunition(ammoMap);

        when(mockScanner.nextInt()).thenReturn(-1, 5, 1); // Invalid, invalid, then valid

        assertEquals(1, AmmunitionDatabase.size(), "Database should contain 1 item before removal");
        removeAmmunitionCommand.execute();
        assertEquals(0, AmmunitionDatabase.size(), "Database should contain 0 item after removal");
        verify(mockScanner, times(3)).nextInt();
    }
}
