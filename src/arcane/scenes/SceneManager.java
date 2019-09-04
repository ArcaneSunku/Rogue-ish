package arcane.scenes;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class SceneManager {

    private Map<String, Scene> mScenes;
    private static String mCurrentScene;

    public SceneManager() {
        mCurrentScene = null;
        mScenes = new LinkedHashMap<>();

        new IntroScene(this);
        new TestScene(this);
    }

    public void update() {
        mScenes.get(mCurrentScene).update();
    }

    public void render(Graphics2D g) {
        mScenes.get(mCurrentScene).render(g);
    }

    public void addScene(String name, Scene scene) {
        if(mCurrentScene == null)
            mCurrentScene = name;

        mScenes.putIfAbsent(name, scene);
    }

    public void removeScene(Scene scene) {
        mScenes.remove(scene);
    }

    public static void swapState(String nextScene) {
        mCurrentScene = nextScene;
    }

}
