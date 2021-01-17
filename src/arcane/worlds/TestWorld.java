package arcane.worlds;

import arcane.entities.Entity;
import arcane.entities.mobs.Player;
import arcane.tiles.Grass;
import arcane.tiles.Tile;
import arcane.tiles.Tree;
import arcane.utils.Handler;
import arcane.utils.Input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestWorld extends World {

    public TestWorld() {
        super(50, 50);

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                Tile t;
                float temp = (ThreadLocalRandom.current().nextInt(100) + 1) / 100f;

                if (temp >= .95f) {
                    t = new Tree();
                } else {
                    t = new Grass();
                }

                t.setPosition(j * t.getWidth(), i * t.getHeight());
                tiles[j][i] = t;
            }
        }

        addEntity(new Player(), 0, 0);
    }

    @Override
    public void update() {
        if(Input.isKeyPressed(KeyEvent.VK_W)) {
            scroll(0f, -2.0f);
        } else if(Input.isKeyPressed(KeyEvent.VK_S)) {
            scroll(0f, 2.0f);
        }

        if(Input.isKeyPressed(KeyEvent.VK_A)) {
            scroll(-2.0f, 0f);
        } else if(Input.isKeyPressed(KeyEvent.VK_D)) {
            scroll(2.0f, 0f);
        }

        for (Entity entity : entities) {
            if (entity.isDead()) {
                removeEntity(entity);
                return;
            }

            entity.update();
        }
    }

    @Override
    public void render(Graphics2D g) {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(!((getTile(j, i).getX() + getTile(j, i).getWidth()) <= 0 || getTile(j, i).getX() > Handler.getWidth()) &&
                        !((getTile(j, i).getY() + getTile(j, i).getHeight()) <= 0 || getTile(j, i).getY() > Handler.getHeight()))
                    getTile(j, i).render(g);
            }
        }

        for(Entity e : entities) {
            e.render(g);
        }
    }
}
