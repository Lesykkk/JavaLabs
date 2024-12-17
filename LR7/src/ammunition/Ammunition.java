package ammunition;

import java.io.Serializable;

public abstract class Ammunition implements Serializable {
    private String type;
    private double weight;
    private double price;

    public Ammunition(String type, double weight, double price) {
        this.type = type;
        this.weight = weight;
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("[%s] weight = %.2fkg, price = %.2f$", type, weight, price);
    }
}
