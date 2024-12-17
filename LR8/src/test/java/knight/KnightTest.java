package knight;

import ammunition.Ammunition;
import ammunition.Armor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    private Knight knight;
    private Ammunition armor1;
    private Ammunition armor2;

    @BeforeEach
    void setUp() {
        knight = new Knight("Sir Arthur");
        armor1 = new Armor(10.5, 150.0); // weight: 10.5kg, price: $150.0
        armor2 = new Armor(15.0, 200.0); // weight: 15.0kg, price: $200.0
    }

    @Test
    void testAddEquipment() {
        knight.addEquipment(armor1);

        List<Ammunition> equipment = knight.getEquipment();
        assertEquals(1, equipment.size());
        assertEquals(armor1, equipment.get(0));
    }

    @Test
    void testRemoveEquipment() {
        knight.addEquipment(armor1);
        knight.addEquipment(armor2);

        knight.removeEquipment(0); // Remove first item
        List<Ammunition> equipment = knight.getEquipment();

        assertEquals(1, equipment.size());
        assertEquals(armor2, equipment.get(0));
    }

    @Test
    void testGetTotalPrice() {
        knight.addEquipment(armor1);
        knight.addEquipment(armor2);

        double expectedTotalPrice = 150.0 + 200.0;
        assertEquals(expectedTotalPrice, knight.getTotalPrice(), 0.01);
    }

    @Test
    void testToStringWithNoEquipment() {
        String expectedOutput = "\tName: Sir Arthur\n\t\tEquipment: No equipment.\n\t\t\tTotal Price: 0,00 ";
        assertEquals(expectedOutput, knight.toString());
    }

    @Test
    void testToStringWithEquipment() {
        knight.addEquipment(armor1);
        knight.addEquipment(armor2);

        String expectedOutput = "\tName: Sir Arthur\n\t\tEquipment: \n" +
                "\t\t\t[armor] weight = 10,50kg, price = 150,00$\n" +
                "\t\t\t[armor] weight = 15,00kg, price = 200,00$\n" +
                "\t\t\tTotal Price: 350,00 ";

        assertEquals(expectedOutput, knight.toString());
    }
}
