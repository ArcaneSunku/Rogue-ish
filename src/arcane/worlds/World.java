package arcane.worlds;

import arcane.entities.Entity;
import arcane.tiles.Tile;

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

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

}
