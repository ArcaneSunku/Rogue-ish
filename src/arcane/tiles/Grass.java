package arcane.tiles;

import arcane.utils.Handler;

import java.awt.*;

public class Grass extends Tile {

    public Grass() {
        this(0.0f, 0.0f);
    }

    public Grass(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(Handler.getImage("grass"), (int) x, (int) y, getWidth(), getHeight(), null);
    }
}
