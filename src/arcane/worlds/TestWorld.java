package arcane.worlds;

import arcane.entities.Entity;
import arcane.entities.mobs.Player;
import arcane.tiles.Grass;
import arcane.tiles.Tile;
import arcane.tiles.Tree;
import arcane.utils.Handler;

import java.awt.*;
import java.util.Random;

public class TestWorld extends World {

    public TestWorld() {
        super(100, 100);

        Random rand = new Random();

        for(int i = 0; i < height; i++) {
            rand.setSeed(System.nanoTime());
            for(int j = 0; j < width; j++) {
                Tile t;
                int temp = rand.nextInt(35) + 1;

                if (temp >= 32) {
                    t = new Tree();
                } else {
                    t = new Grass();
                }

                t.setPosition(j * t.getWidth(), i * t.getHeight());
                tiles[j][i] = t;
            }
        }

        addEntity(new Player());
    }

    @Override
    public void update() {
        for(int i = 0; i < entities.size(); i++) {
            if(entities.get(i).isDead()) {
                removeEntity(entities.get(i));
                return;
            }

            entities.get(i).update();
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
