package arcane.scenes;

import arcane.utils.Handler;
import arcane.utils.Input;

import java.awt.*;
import java.awt.event.KeyEvent;

public class IntroScene extends Scene {

    public IntroScene(SceneManager manager) {
        super(manager, "Intro");
    }

    @Override
    public void update() {
        if(Input.keyJustPressed(KeyEvent.VK_SPACE)) {
            SceneManager.swapState("Test");
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setFont(Handler.getFont("epic", 20f * Handler.getScale()));
        g.setColor(Color.RED);

        g.drawImage(Handler.getImage("test"), Handler.getWidth() - 74, Handler.getHeight() - 74, 64, 64, null);

        g.drawString("Welcome to Rogue-ish!", Handler.getWidth() / 4, Handler.getHeight() / 2);
    }
}
