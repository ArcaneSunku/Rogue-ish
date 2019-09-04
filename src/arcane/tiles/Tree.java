package arcane.tiles;

import arcane.utils.Handler;

import java.awt.*;

public class Tree extends Tile {

    public Tree() {
        this(0.0f, 0.0f);
    }

    public Tree(float x, float y) {
        this.x = x;
        this.y = y;

        this.solid = true;
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(Handler.getImage("grass"), (int) x, (int) y, getWidth(), getHeight(), null);
        g.drawImage(Handler.getImage("tree"), (int) x, (int) y, getWidth(), getHeight(), null);
    }
}
