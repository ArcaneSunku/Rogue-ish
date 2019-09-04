package arcane.entities.mobs;

import arcane.entities.Entity;

public abstract class Mob extends Entity {

    protected int health, maxHealth;
    protected float speed = 5.0f;

    public Mob(String name) {
        this(name, 0, 0);
    }

    public Mob(String name, float x, float y) {
        super(name, x, y);

        maxHealth = 5;
        health = maxHealth;
    }


    public void move(float xDir, float yDir) {
        if(xDir != 0 && yDir != 0) {
            move(xDir, 0);
            move(0, yDir);
        }

        if(xDir < 0)
            direction = 1;
        else if(xDir > 0)
            direction = 2;
        else if(yDir < 0)
            direction = 3;
        else if(yDir > 0)
            direction = 0;

        x += xDir * speed;
        y += yDir * speed;
    }

    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }

    public float getSpeed() { return speed; }

}
