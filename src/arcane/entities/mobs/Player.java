package arcane.entities.mobs;

import arcane.utils.Handler;
import arcane.utils.Input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Player extends Mob {

    private String facing = "v";
    private int facingWidth = 0, facingHeight = 0;
    private int nameWidth = 0, nameHeight = 0;

    public Player() {
        this(0, 0);
    }

    public Player(float x, float y) {
        super("Player", x, y);
        Random rand = new Random(System.nanoTime());
        int id = rand.nextInt(100) + 1;

        name = "Player - " + id;
        System.out.println(name);
    }

    @Override
    public void update() {
        switch (direction) {
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

        if(Input.isKeyPressed(KeyEvent.VK_UP))
            move(0, -.5f);
        else if(Input.isKeyPressed(KeyEvent.VK_DOWN))
            move(0, .5f);

        if(Input.isKeyPressed(KeyEvent.VK_LEFT))
            move(-.5f, 0);
        else if(Input.isKeyPressed(KeyEvent.VK_RIGHT))
            move(.5f, 0);

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

        facingWidth = g.getFontMetrics(g.getFont()).stringWidth(facing);

        g.drawString(facing, (x + getWidth() / 2f) - (facingWidth / 2f), (y + getHeight()) + (facingHeight / 2f));
    }
}
