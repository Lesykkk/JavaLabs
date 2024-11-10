package droid;

public class Mechanic extends Droid {
    public Mechanic(String name, int health, int damage) {
        super(name, "Механік",96,120,20,26, health, damage);
    }

    public Mechanic(String name) {
        super(name, "Механік",96,120,20,26);
    }

    @Override
    public void useSkill() {
        if (this.health <= 20 && !boostUsed) {
            this.health += 10;
            this.boostUsed = true;
        }
    }
}
