package arcane.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {

    public static BufferedImage loadImage(String fileName) {
        try {
            return ImageIO.read(Images.class.getResourceAsStream("/assets/textures/" + fileName));
        } catch (IOException e) {
            System.err.printf("Failed to load image: %s", fileName);
            e.printStackTrace();
            return null;
        }
    }

}
