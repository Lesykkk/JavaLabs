package command;

import database.KnightDatabase;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddKnightCommandTest {
    private static String testFilePath;
    private Scanner mockScanner;
    private AddKnightCommand addKnightCommand;

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
        addKnightCommand = new AddKnightCommand(mockScanner);
        KnightDatabase.saveKnights(new HashMap<>());
    }

    @Test
    void testAddSingleKnight() {
        when(mockScanner.next()).thenReturn("Arthur");
        assertEquals(0, KnightDatabase.size(), "Database should initially be empty");
        addKnightCommand.execute();
        assertEquals(1, KnightDatabase.size(), "Database should contain 1 knight after addition");
    }

    @Test
    void testAddMultipleKnights() {
        when(mockScanner.next()).thenReturn("Arthur", "Lancelot");
        assertEquals(0, KnightDatabase.size(), "Database should initially be empty");
        addKnightCommand.execute();
        assertEquals(1, KnightDatabase.size(), "Database should contain 1 knight after first addition");
        addKnightCommand.execute();
        assertEquals(2, KnightDatabase.size(), "Database should contain 2 knights after second addition");
    }
}
