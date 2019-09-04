package arcane.entities.mobs;

import arcane.utils.Handler;

import java.awt.*;

public class Fungus extends Mob {

    public Fungus() {
        this(0, 0);
    }

    public Fungus(float x, float y) {
        super("Fungus", x, y);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(Handler.getImage("fungus"), (int) x, (int) y, getWidth(), getHeight(), null);
    }
}
