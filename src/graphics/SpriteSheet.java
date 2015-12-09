package graphics;

import java.awt.image.BufferedImage;

/*
 * Created by Vladislav on 10-Sep-15.
 */
public class SpriteSheet {

    private BufferedImage spriteSheet;

    public void setSpriteSheet(BufferedImage spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public BufferedImage getTile(int xTile, int yTile, int width, int height) {
        BufferedImage sprite = spriteSheet.getSubimage(xTile, yTile, width, height);
        return sprite;
    }
}
