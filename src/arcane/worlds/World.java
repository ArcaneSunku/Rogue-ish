package arcane.worlds;

import arcane.entities.Entity;
import arcane.tiles.Tile;
import arcane.utils.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class World {

    protected Tile[][] tiles;
    protected List<Entity> entities;

    protected int width, height;
    protected float xOffset = 0, yOffset = 0;

    public World(int width, int height) {
        this(new Tile[width][height], new ArrayList<>());
    }

    public World(Tile[][] tiles, List<Entity> entities) {
        this.tiles = tiles;
        this.entities = entities;

        this.width = tiles.length;
        this.height = tiles[0].length;
    }

    public abstract void update();
    public abstract void render(Graphics2D g);

    public void move(float xDir, float yDir) {
        xOffset -= (xDir * 1.0f);
        yOffset -= (yDir * 1.0f);

        if(xOffset > 0)
            xOffset = 0;
        if(yOffset > 0)
            yOffset = 0;

        if(xOffset + (width * tiles[0][0].getWidth()) <= Handler.getWidth())
            xOffset = Handler.getWidth() - (width * tiles[0][0].getWidth());
        if(yOffset + (height * tiles[0][0].getHeight()) <= Handler.getHeight())
            yOffset = Handler.getHeight() - (height * tiles[0][0].getHeight());

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                getTile(j, i).setPosition(xOffset + (getTile(j, i).getWidth() * j), yOffset + (getTile(j, i).getHeight() * i));
            }
        }
    }

    public void addEntity(Entity entity, int x, int y) {
        if((x < 0 || x > width) || (y < 0 || y > height))
            return;

        if(!getTile(x, y).isSolid()) {
            entity.setPosition(getTile(x, y).getX(), getTile(x, y).getY());
            addEntity(entity);
        } else if (getTile(x, y).isSolid()) {
            for(int i = y; i < height; i++) {
                for(int j = x; j < width; j++) {
                    if(!getTile(j, i).isSolid()) {
                        entity.setPosition(getTile(j, i).getX(), getTile(j, i).getY());
                        addEntity(entity);
                        return;
                    }
                }
            }
        }
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public float xOffset() { return xOffset; }
    public float yOffset() { return yOffset; }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

}
