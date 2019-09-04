package arcane.utils;

import arcane.Game;
import arcane.graphics.Fonts;
import arcane.graphics.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.Map;

public class Handler {

    private static Game mGame;

    private static Map<String, Font> mFontCache;
    private static Map<String, BufferedImage> mImageCache;

    public static void initializeAssets(Game game) {
        mGame = game;

        mFontCache = new LinkedHashMap<>();

        mFontCache.putIfAbsent("pixel", Fonts.loadTTF("Pixeled.ttf"));
        mFontCache.putIfAbsent("minecraft", Fonts.loadTTF("Minecraft.ttf"));
        mFontCache.putIfAbsent("epic", Fonts.loadTTF("EnchantedSword.ttf"));

        mFontCache.putIfAbsent("square", Fonts.loadTTF("Squarewave.ttf"));
        mFontCache.putIfAbsent("square-italic", Fonts.loadTTF("Squarewave-Italic.ttf"));
        mFontCache.putIfAbsent("square-bold", Fonts.loadTTF("Squarewave-Bold.ttf"));

        mImageCache = new LinkedHashMap<>();

        mImageCache.putIfAbsent("test", Images.loadImage("test.png"));

        mImageCache.putIfAbsent("player", Images.loadImage("entities/player.png"));
        mImageCache.putIfAbsent("fungus", Images.loadImage("entities/fungus.png"));

        mImageCache.putIfAbsent("knife", Images.loadImage("items/knife.png"));

        mImageCache.putIfAbsent("grass", Images.loadImage("tiles/grass.png"));
        mImageCache.putIfAbsent("tree", Images.loadImage("tiles/tree.png"));
    }

    public static void stopGame() { mGame.toggleRunning(); }

    public static Font getFont(String name) { return mFontCache.get(name); }
    public static Font getFont(String name, int style) { return mFontCache.get(name).deriveFont(style); }
    public static Font getFont(String name, float size) { return mFontCache.get(name).deriveFont(size); }
    public static Font getFont(String name, int style, float size) { return mFontCache.get(name).deriveFont(style, size); }

    public static BufferedImage getImage(String name) { return mImageCache.get(name); }

    public static int getScale() { return mGame.scale(); }
    public static int getWidth() { return mGame.getWidth(); }
    public static int getHeight() { return mGame.getHeight(); }

}
