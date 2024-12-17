package knight;

import ammunition.Ammunition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Knight implements Serializable {
    private String name;
    private List<Ammunition> equipment;

    public Knight(String name) {
        this.name = name;
        this.equipment = new ArrayList<>();
    }

    public List<Ammunition> getEquipment() {
        return equipment;
    }

    public void addEquipment(Ammunition equipment) {
        this.equipment.add(equipment);
    }

    public void removeEquipment(int index) {
        this.equipment.remove(index);
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Ammunition e: equipment) {
            totalPrice += e.getPrice();
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder equipmentList = new StringBuilder();

        if (equipment.isEmpty()) {
            equipmentList.append("No equipment.");
        } else {
            for (Ammunition e : equipment) {
                equipmentList.append("\n\t\t\t").append(e.toString());
            }
        }

        return String.format("\tName: %s\n\t\tEquipment: %s\n\t\t\tTotal Price: %.2f ",
                name, equipmentList, getTotalPrice());
    }
}
