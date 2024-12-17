package command;

import database.KnightDatabase;
import knight.Knight;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ShowKnightsCommandTest {
    private static String testFilePath;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

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
        KnightDatabase.saveKnights(new HashMap<>());
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void restoreSystemOut() {
        System.setOut(originalOut);
    }

    @Test
    void testShowKnightsCommand_WhenDatabaseIsEmpty() {
        ShowKnightsCommand command = new ShowKnightsCommand();
        command.execute();

        String expectedOutput = "Knight database is empty.";
        assertTrue(outputStream.toString().contains(expectedOutput), "Output should indicate that the knight database is empty.");
    }

    @Test
    void testShowKnightsCommand_WhenDatabaseIsNotEmpty() {
        Knight knight1 = new Knight("Arthur");
        Knight knight2 = new Knight("Lancelot");
        KnightDatabase.addKnight(knight1);
        KnightDatabase.addKnight(knight2);

        ShowKnightsCommand command = new ShowKnightsCommand();
        command.execute();

        String expectedOutput = "ID [1] \tName: Arthur\n" +
                "\t\tEquipment: No equipment.\n" +
                "\t\t\tTotal Price: 0,00 \r\n" +
                "ID [2] \tName: Lancelot\n" +
                "\t\tEquipment: No equipment.\n" +
                "\t\t\tTotal Price: 0,00";
        assertTrue(outputStream.toString().contains(expectedOutput),"Output should list all knights in the database.");
    }
}
