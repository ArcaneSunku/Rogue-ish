package arcane.scenes;

import arcane.worlds.TestWorld;
import arcane.worlds.World;

import java.awt.*;

public class TestScene extends Scene {

    private World test;

    public TestScene(SceneManager manager) {
        super(manager, "Test");

        test = new TestWorld();
    }

    @Override
    public void update() {
        test.update();
    }

    @Override
    public void render(Graphics2D g) {
        test.render(g);
    }
}
