package arcane;

import arcane.scenes.SceneManager;
import arcane.utils.Handler;
import arcane.utils.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private JFrame mFrame;
    private Thread mThread;

    private Input mInput;
    private SceneManager mSceneManager;

    private String mTitle;
    private int mWidth, mHeight, mScale;
    private boolean mRunning;

    public Game(String title, int width, int height, int scale) {
        Dimension dimension = new Dimension(width, height);

        mTitle = title;
        mWidth = width;
        mHeight = height;
        mScale = scale;

        mRunning = false;

        setMinimumSize(dimension);
        setMaximumSize(dimension);
        setPreferredSize(dimension);
    }

    private void init() {
        mFrame = new JFrame(mTitle);

        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setLayout(new BorderLayout());
        mFrame.add(this, BorderLayout.CENTER);
        mFrame.pack();
        mFrame.setResizable(false);
        mFrame.setLocationRelativeTo(null);
        mFrame.setVisible(true);
        mFrame.setFocusable(false);

        setFocusable(true);
        requestFocus();

        Handler.initializeAssets(this);

        mInput = new Input(this);
        mSceneManager = new SceneManager();
    }

    private void update() {
        mSceneManager.update();
    }

    private void render() {
        BufferStrategy buffer_strategy = getBufferStrategy();

        if(buffer_strategy == null) {
            createBufferStrategy(2);
            return;
        }

        Graphics graphics = buffer_strategy.getDrawGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        mSceneManager.render((Graphics2D) graphics);

        graphics.dispose();
        buffer_strategy.show();
    }

    @Override
    public void run() {
        init();

        long lastTime = System.nanoTime();
        double tickLimit = 60.0D;
        double ns = 1000000000.0D / tickLimit;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(mRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            boolean shouldRender = false;

            while(delta >= 1) {
                mInput.update();
                update();
                shouldRender = true;
                delta -= 1;
            }

            if(shouldRender) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                render();
                frames++;
            }

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                mFrame.setTitle(mTitle + " | FPS: " + frames);
                frames = 0;
            }
        }

        stop();
    }

    public synchronized void start() {
        if(mRunning) return;

        mThread = new Thread(this, "Game_Thread");
        mRunning = true;
        mThread.start();
    }

    private synchronized void stop() {
        try {
            mFrame.dispose();
            mThread.join(1);
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void toggleRunning() { mRunning = !mRunning; }

    public String getTitle() { return mTitle; }

    public int width() { return mWidth; }
    public int height() { return mHeight; }
    public int scale() { return mScale; }

    public boolean isRunning() { return mRunning; }

}
