package droid;

import java.io.Serializable;
import java.util.Random;

public abstract class Droid implements Serializable {
    protected String name;
    protected String type;
    protected int health;
    protected int damage;
    private final int MIN_HEALTH;
    private final int MAX_HEALTH;
    private final int MIN_DAMAGE;
    private final int MAX_DAMAGE;
    protected boolean boostUsed = false;
    protected double accuracy = 0.1;

    Droid(String name, String type, int MIN_HEALTH, int MAX_HEALTH,
            int MIN_DAMAGE, int MAX_DAMAGE, int health, int damage) {
        this.name = name;
        this.type = type;
        this.MIN_HEALTH = MIN_HEALTH;
        this.MAX_HEALTH = MAX_HEALTH;
        this.MIN_DAMAGE = MIN_DAMAGE;
        this.MAX_DAMAGE = MAX_DAMAGE;
        this.health = checkHealthRange(health);
        this.damage = checkDamageRange(damage);
    }

    Droid(String name, String type, int MIN_HEALTH, int MAX_HEALTH,
            int MIN_DAMAGE, int MAX_DAMAGE) {
        this.name = name;
        this.type = type;
        this.MIN_HEALTH = MIN_HEALTH;
        this.MAX_HEALTH = MAX_HEALTH;
        this.MIN_DAMAGE = MIN_DAMAGE;
        this.MAX_DAMAGE = MAX_DAMAGE;
        Random random = new Random();
        this.health = random.nextInt(this.MIN_HEALTH, this.MAX_HEALTH);
        this.damage = random.nextInt(this.MIN_DAMAGE, this.MAX_DAMAGE);
    }

    public String toString() {
        return "[Ім'я: " + this.name + ", тип: " + this.type + ", здоров'я: " + this.health + ", шкода: " + this.damage + "]";
    }

    protected int checkHealthRange(int health) {
        if (health < MIN_HEALTH) {
            return MIN_HEALTH;
        } else if (health > MAX_HEALTH) {
            return MAX_HEALTH;
        }
        return health;
    }

    protected int checkDamageRange(int damage) {
        if (damage < MIN_DAMAGE) {
            return MIN_DAMAGE;
        } else if (damage > MAX_DAMAGE) {
            return MAX_DAMAGE;
        }
        return damage;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public final int takeDamage(int damage) {
        int oldHealth = this.health;
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
        return oldHealth - this.health;
    }

    public final int attack() {
        this.useSkill();
        if (accuracy != 0) {
            Random random = new Random();
            return (int)(this.damage - this.damage * random.nextDouble(0.01, accuracy));
        } else {
            return this.damage;
        }
    }

    public abstract void useSkill();

    public String getName() {
        return this.name;
    }
}