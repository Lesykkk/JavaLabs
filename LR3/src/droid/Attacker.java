package droid;

public class Attacker extends Droid {
    public Attacker(String name, int health, int damage) {
        super(name, "Штурмовик",76,90,24,30, health, damage);
    }

    public Attacker(String name) {
        super(name, "Штурмовик",76,90,24,30);
    }

    @Override
    public void useSkill() {
        if (this.health <= 20 && !boostUsed) {
            this.damage += 5;
            this.boostUsed = true;
        }
    }
}