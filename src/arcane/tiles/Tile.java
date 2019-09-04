package arcane.tiles;

import arcane.utils.Handler;

import java.awt.*;

public abstract class Tile {

    protected float x, y;
    protected int width = 16, height = 16;
    protected boolean solid = false;

    public void update() {}
    public abstract void render(Graphics2D g);

    public void translate(float xDir, float yDir) {
        x += (xDir * 1.0f);
        y += (yDir * 1.0f);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() { return x; }
    public float getY() { return y; }

    public int getWidth() { return width * Handler.getScale(); }
    public int getHeight() { return height * Handler.getScale(); }

    public boolean isSolid() { return solid; }

}
