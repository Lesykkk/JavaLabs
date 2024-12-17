package command;

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

class RemoveKnightCommandTest {
    private static String testFilePath;
    private Scanner mockScanner;
    private Command mockShowKnightsCommand;
    private RemoveKnightCommand removeKnightCommand;

    @BeforeAll
    static void createTempFile() throws IOException {
        testFilePath = Files.createTempFile("knight_test", ".dat").toString();
        KnightDatabase.setFilePath(testFilePath);
    }

    @AfterAll
    static void removeTempFile() throws IOException {
        Files.deleteIfExists(Path.of(testFilePath));
    }

    @BeforeEach
    void setUp() {
        mockScanner = mock(Scanner.class);
        mockShowKnightsCommand = mock(Command.class);
        removeKnightCommand = new RemoveKnightCommand(mockScanner, mockShowKnightsCommand);
        KnightDatabase.saveKnights(new HashMap<>());
    }

    @Test
    void testRemoveKnightSuccess() {
        Knight knight = new Knight("Arthur");
        KnightDatabase.addKnight(knight);
        when(mockScanner.nextInt()).thenReturn(1);
        assertEquals(1, KnightDatabase.size(), "Database should contain 1 knight before removal");
        removeKnightCommand.execute();
        assertEquals(0, KnightDatabase.size(), "Database should contain 0 knights after removal");
    }

    @Test
    void testRemoveKnightInvalidId() {
        KnightDatabase.addKnight(new Knight("Arthur"));
        KnightDatabase.addKnight(new Knight("Lancelot"));
        when(mockScanner.nextInt()).thenReturn(5, 1);
        assertEquals(2, KnightDatabase.size(), "Database should contain 2 knights before removal");
        removeKnightCommand.execute();
        assertEquals(1, KnightDatabase.size(), "Database should contain 1 knight after removal");
    }
}
