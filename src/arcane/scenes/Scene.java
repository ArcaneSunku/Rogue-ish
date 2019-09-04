package arcane.scenes;

import java.awt.*;

public abstract class Scene {

    public Scene(SceneManager manager, String name) {
        manager.addScene(name, this);
    }

    public void init() {}

    public abstract void update();
    public abstract void render(Graphics2D g);

}
