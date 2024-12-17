package command;

import ammunition.Armor;
import database.KnightDatabase;
import knight.Knight;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RemoveEquipmentFromKnightCommandTest {
    private static String testFilePathKnight;
    private static String testFilePathAmmo;
    private Scanner mockScanner;
    private Command mockShowKnightsCommand;
    private RemoveEquipmentFromKnightCommand removeEquipmentFromKnightCommand;

    @BeforeAll
    static void createTempFile() throws IOException {
        testFilePathKnight = Files.createTempFile("knight_test", ".dat").toString();
        testFilePathAmmo = Files.createTempFile("ammunition_test", ".dat").toString();
        KnightDatabase.setFilePath(testFilePathKnight);
    }

    @AfterAll
    static void removeTempFile() throws IOException {
        Files.deleteIfExists(Path.of(testFilePathKnight));
        Files.deleteIfExists(Path.of(testFilePathAmmo));
    }

    @BeforeEach
    void setUp() {
        KnightDatabase.saveKnights(new HashMap<>());
        mockScanner = mock(Scanner.class);
        mockShowKnightsCommand = mock(Command.class);
        removeEquipmentFromKnightCommand = new RemoveEquipmentFromKnightCommand(mockScanner, mockShowKnightsCommand);
    }

    @Test
    void testRemoveEquipmentFromKnight() {
        Knight knight = new Knight("Arthur");
        knight.addEquipment(new Armor(10.0, 100.0));
        knight.addEquipment(new Armor(5.0, 50.0));
        KnightDatabase.addKnight(knight);
        when(mockScanner.nextInt()).thenReturn(1, 1);
        assertEquals(2, KnightDatabase.getKnights().get(1).getEquipment().size(), "Knight should have 2 pieces of equipment before removal.");
        removeEquipmentFromKnightCommand.execute();
        assertEquals(1, KnightDatabase.getKnights().get(1).getEquipment().size(), "Knight should have 1 piece of equipment after removal.");
    }
}
