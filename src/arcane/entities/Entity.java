package arcane.entities;

import arcane.utils.Handler;

import java.awt.*;

public abstract class Entity {

    protected enum Direction {
        DOWN, LEFT, RIGHT, UP
    }

    protected Direction direction;
    protected String name;

    protected float x, y;
    protected float speed = 1.0f;
    protected int width = 16, height = 16;
    protected boolean dead = false, moving = false;


    public Entity(String name) {
        this(name, 0, 0);
    }

    public Entity(String name, float x, float y) {
        this.name = name;

        this.x = x;
        this.y = y;

        direction = Direction.DOWN;
    }

    public abstract void update();
    public abstract void render(Graphics2D g);

    public void move(float xDir, float yDir) {
        if(xDir != 0 && yDir != 0) {
            move(xDir, 0);
            move(0, yDir);
        }

        x += xDir;
        y += yDir;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public String getName() { return name; }

    public float getX() { return x; }
    public float getY() { return y; }

    public float getSpeed() { return speed; }

    public int getWidth() { return width * Handler.getScale(); }
    public int getHeight() { return height * Handler.getScale(); }

    public boolean isDead() { return dead; }
    public boolean isMoving() { return moving; }

}
