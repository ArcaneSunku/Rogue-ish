package arcane.entities.mobs;

import arcane.utils.Handler;
import arcane.utils.Input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.ThreadLocalRandom;

public class Player extends Mob {

    private String facing = "v";
    private int facingWidth = 0, facingHeight = 0;
    private int nameWidth = 0, nameHeight = 0;

    public Player() {
        this(0, 0);
    }

    public Player(float x, float y) {
        super("Player", x, y);
        int id = ThreadLocalRandom.current().nextInt(100) + 1;

        name = String.format("Player - %d", id);
        speed = 3.0f;
    }

    @Override
    public void update() {
        switch (direction.ordinal()) {
            case 0:
                facing = "v";
                break;
            case 1:
                facing = "<";
                break;
            case 2:
                facing = ">";
                break;
            case 3:
                facing = "^";
                break;
        }

        if(Input.isKeyPressed(KeyEvent.VK_UP)) {
            moving = true;
            move(0, -.5f * speed);
            direction = Direction.UP;
        } else if(Input.isKeyPressed(KeyEvent.VK_DOWN)) {
            moving = true;
            move(0, .5f * speed);
            direction = Direction.DOWN;
        } else if(Input.isKeyPressed(KeyEvent.VK_LEFT)) {
            moving = true;
            move(-.5f * speed, 0);
            direction = Direction.LEFT;
        } else if(Input.isKeyPressed(KeyEvent.VK_RIGHT)) {
            moving = true;
            move(.5f * speed, 0);
            direction = Direction.RIGHT;
        } else {
            moving = false;
        }

        if(x < 0)
            x = 0;

        if(y < 0)
            y = 0;

        if(x + getWidth() >= Handler.getWidth())
            x = Handler.getWidth() - getWidth();

        if(y + getHeight() >= Handler.getHeight())
            y = Handler.getHeight() - getHeight();
    }

    @Override
    public void render(Graphics2D g) {
        g.setFont(Handler.getFont("pixel", 5f));

        if(nameWidth == 0 || nameHeight == 0) {
            nameWidth = g.getFontMetrics(g.getFont()).stringWidth(name);
            nameHeight = g.getFontMetrics(g.getFont()).getHeight();
        }



        g.drawImage(Handler.getImage("player"), (int) x, (int) y, getWidth(), getHeight(), null);

        g.setColor(Color.RED);
        g.drawString(name, (x + getWidth() / 2f) - (nameWidth / 2f), (y - nameHeight / 2f));

        g.setFont(Handler.getFont("square", 16f));
        if(facingHeight == 0)
            facingHeight = g.getFontMetrics(g.getFont()).getHeight();

        if(facingWidth == 0)
            facingWidth = g.getFontMetrics(g.getFont()).stringWidth(facing);

        g.drawString(facing, (x + getWidth() / 2f) - (facingWidth / 2f), (y + getHeight()) + (facingHeight / 2f));
    }
}
