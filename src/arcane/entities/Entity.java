package arcane.entities;

import arcane.utils.Handler;

import java.awt.*;

public abstract class Entity {

    protected String name;
    protected float x, y;
    protected int width = 16, height = 16, direction = 0;
    protected boolean dead = false;


    public Entity(String name) {
        this(name, 0, 0);
    }

    public Entity(String name, float x, float y) {
        this.name = name;

        this.x = x;
        this.y = y;
    }

    public abstract void update();
    public abstract void render(Graphics2D g);

    public String getName() { return name; }

    public float getX() { return x; }
    public float getY() { return y; }

    public int getWidth() { return width * Handler.getScale(); }
    public int getHeight() { return height * Handler.getScale(); }

    public boolean isDead() { return dead; }

}
