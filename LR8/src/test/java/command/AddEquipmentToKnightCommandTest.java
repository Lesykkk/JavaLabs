package command;

import ammunition.Armor;
import database.AmmunitionDatabase;
import database.KnightDatabase;
import knight.Knight;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddEquipmentToKnightCommandTest {
    private static String testFilePathAmmo;
    private static String testFilePathKnight;
    private Scanner mockScanner;
    private Command mockShowKnightsCommand;
    private Command mockShowAmmunitionCommand;
    private AddEquipmentToKnightCommand addEquipmentToKnightCommand;

    @BeforeAll
    static void createTempFile() throws IOException {
        testFilePathAmmo = Files.createTempFile("ammunition_test", ".dat").toString();
        testFilePathKnight = Files.createTempFile("knight_test", ".dat").toString();
        KnightDatabase.setFilePath(testFilePathKnight);
        AmmunitionDatabase.setFilePath(testFilePathAmmo);
    }

    @AfterAll
    static void removeTempFile() throws IOException {
        Files.deleteIfExists(Path.of(testFilePathAmmo));
        Files.deleteIfExists(Path.of(testFilePathKnight));
    }

    @BeforeEach
    void setUp() {
        KnightDatabase.saveKnights(new HashMap<>());
        AmmunitionDatabase.saveAmmunition(new HashMap<>());
        mockScanner = mock(Scanner.class);
        mockShowKnightsCommand = mock(Command.class);
        mockShowAmmunitionCommand = mock(Command.class);
        addEquipmentToKnightCommand = new AddEquipmentToKnightCommand(mockScanner, mockShowKnightsCommand, mockShowAmmunitionCommand);
    }

    @Test
    void testAddEquipmentToKnight() {
        Knight knight = new Knight("Arthur");
        KnightDatabase.addKnight(knight);

        Armor armor = new Armor(10.0, 100.0);
        AmmunitionDatabase.addAmmunition(armor);

        when(mockScanner.nextInt()).thenReturn(1, 1);
        assertEquals(1, KnightDatabase.size(), "There should be 1 knight in the database before execution.");
        assertEquals(1, AmmunitionDatabase.size(), "There should be 1 ammunition in the database before execution.");
        assertTrue(knight.getEquipment().isEmpty(), "Knight should have no equipment initially.");
        addEquipmentToKnightCommand.execute();
        assertEquals(1, KnightDatabase.getKnights().get(1).getEquipment().size(), "Knight should have 1 equipment after execution.");
    }
}
