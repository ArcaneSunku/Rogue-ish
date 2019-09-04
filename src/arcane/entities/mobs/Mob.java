package arcane.entities.mobs;

import arcane.entities.Entity;

public abstract class Mob extends Entity {

    protected int health, maxHealth;

    public Mob(String name) {
        this(name, 0, 0);
    }

    public Mob(String name, float x, float y) {
        super(name, x, y);

        maxHealth = 5;
        health = maxHealth;
        speed = 2.0f;
    }

    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }

}
