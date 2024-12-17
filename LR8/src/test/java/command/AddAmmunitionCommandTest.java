package command;

import ammunition.*;
import database.AmmunitionDatabase;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddAmmunitionCommandTest {
    private static String testFilePath;
    private Scanner mockScanner;
    private AddAmmunitionCommand addAmmunitionCommand;

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
        addAmmunitionCommand = new AddAmmunitionCommand(mockScanner);
        AmmunitionDatabase.saveAmmunition(new HashMap<>());
    }

    @Test
    void testAddArmor() {
        when(mockScanner.nextInt()).thenReturn(1);
        when(mockScanner.nextDouble()).thenReturn(10.5, 150.0);

        addAmmunitionCommand.execute();

        Map<Integer, Ammunition> ammoMap = AmmunitionDatabase.getAmmunition();
        assertEquals(1, ammoMap.size(), "Database should contain 1 item");
        Ammunition ammo = ammoMap.get(1);
        assertInstanceOf(Armor.class, ammo, "Added item should be of type Armor");
        assertEquals(10.5, ammo.getWeight(), "Weight should be 10.5");
        assertEquals(150.0, ammo.getPrice(), "Price should be 150.0");
    }

    @Test
    void testAddHelmet() {
        when(mockScanner.nextInt()).thenReturn(2);
        when(mockScanner.nextDouble()).thenReturn(5.2, 80.0);

        addAmmunitionCommand.execute();

        Map<Integer, Ammunition> ammoMap = AmmunitionDatabase.getAmmunition();
        assertEquals(1, ammoMap.size(), "Database should contain 1 item");
        Ammunition ammo = ammoMap.get(1);
        assertInstanceOf(Helmet.class, ammo, "Added item should be of type Helmet");
        assertEquals(5.2, ammo.getWeight(),"Weight should be 5.2");
        assertEquals(80.0, ammo.getPrice(),"Price should be 80.0");
    }

    @Test
    void testAddSword() {
        when(mockScanner.nextInt()).thenReturn(4);
        when(mockScanner.nextDouble()).thenReturn(7.3, 200.0);

        addAmmunitionCommand.execute();

        Map<Integer, Ammunition> ammoMap = AmmunitionDatabase.getAmmunition();
        assertEquals(1, ammoMap.size(), "Database should contain 1 item");
        Ammunition ammo = ammoMap.get(1);
        assertInstanceOf(Sword.class, ammo, "Added item should be of type Sword");
        assertEquals(7.3, ammo.getWeight(),"Weight should be 7.3");
        assertEquals(200.0, ammo.getPrice(), "Price should be 200.0");
    }

    @Test
    void testInvalidTypeInput() {
        when(mockScanner.nextInt()).thenReturn(-1, 5, 3);
        when(mockScanner.nextDouble()).thenReturn(15.0, 100.0);

        addAmmunitionCommand.execute();

        Map<Integer, Ammunition> ammoMap = AmmunitionDatabase.getAmmunition();
        assertEquals(1, ammoMap.size(), "Database should contain 1 item");
        Ammunition ammo = ammoMap.get(1);
        assertInstanceOf(Shield.class, ammo, "Added item should be of type Shield");
        assertEquals(15.0, ammo.getWeight(),"Weight should be 15.0");
        assertEquals(100.0, ammo.getPrice(),"Price should be 100.0");

        verify(mockScanner, times(3)).nextInt();
    }
}
