package droid;

public class Archer extends Droid {
    public Archer(String name, int health, int damage) {
        super(name, "Стрілець",62,76,27,33, health, damage);
    }

    public Archer(String name) {
        super(name, "Стрілець",62,76,27,33);
    }

    @Override
    public void useSkill() {
        if (this.health <= 20 && !boostUsed) {
            this.accuracy = 0;
            this.boostUsed = true;
        }
    }
}
