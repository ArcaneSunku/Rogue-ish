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
    private boolean xScroll = true, yScroll = true;

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

    public void scroll(float xDir, float yDir) {
        xOffset -= (xDir * 1.0f);
        yOffset -= (yDir * 1.0f);

        xScroll = true;
        yScroll = true;

        if(xOffset > 0) {
            xOffset = 0;
            xScroll = false;
        }

        if(yOffset > 0) {
            yOffset = 0;
            yScroll = false;
        }

        if(xOffset + (width * tiles[0][0].getWidth()) <= Handler.getWidth()) {
            xOffset = Handler.getWidth() - (width * tiles[0][0].getWidth());
            xScroll = false;
        }

        if(yOffset + (height * tiles[0][0].getHeight()) <= Handler.getHeight()) {
            yOffset = Handler.getHeight() - (height * tiles[0][0].getHeight());
            yScroll = false;
        }

        for(Entity e : entities)  {
            if(!e.isMoving()) {
                if(!xScroll && !yScroll)
                    return;
                else if(xScroll && yScroll)
                    e.move(-(xDir * 1.0f), -(yDir * 1.0f));

                if(!xScroll)
                    e.move(0, -(yDir * 1.0f));
                else if(!yScroll)
                    e.move(-(xDir * 1.0f), 0);
            }
        }

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

    public Tile getTileRaw(float x, float y) {
        return getTile((int) (xOffset - (getTile(0, 0).getWidth() * x)), (int) (yOffset - (getTile(0, 0).getHeight() * y)));
    }

    public float xOffset() { return xOffset; }
    public float yOffset() { return yOffset; }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

}
