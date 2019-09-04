package arcane.graphics;

import java.awt.*;
import java.io.IOException;

public class Fonts {

    public static Font loadTTF(String fontName) {
        return loadFont(fontName, true);
    }

    public static Font loadFont(String fontName, boolean ttf) {
        Font font;

        try {
            if(ttf)
                font = Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream("/assets/fonts/" +fontName));
            else
                font = Font.createFont(Font.TYPE1_FONT, Fonts.class.getResourceAsStream("/assets/fonts/" +fontName));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.err.printf("Couldn't load file \"%s\"\n", fontName);
            font = null;
        }

        return font;
    }

}
