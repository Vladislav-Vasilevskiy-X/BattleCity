package graphics;

import adds.images.Images;

import java.awt.image.BufferedImage;

/**
 * Created by Vladislav on 13-Sep-15.
 */
public class Assets {


    SpriteSheet spriteSheets = new SpriteSheet();

    public static SpriteSheet player = new SpriteSheet();
    public static SpriteSheet bullet = new SpriteSheet();

    public static BufferedImage wallImage;
    public static BufferedImage waterImage;
    public static BufferedImage grassImage;
    public static BufferedImage steelWallImage;

    public void init() {

        spriteSheets.setSpriteSheet(LoadImageFrom.LoadImageFrom(Images.class, "BattleCitySpriteSheets.png"));
        player.setSpriteSheet(LoadImageFrom.LoadImageFrom(Images.class, "BattleCitySpriteSheets.png"));
        bullet.setSpriteSheet(LoadImageFrom.LoadImageFrom(Images.class, "BattleCitySpriteSheets.png"));

        wallImage = spriteSheets.getTile(256,0,16,16);
        waterImage = spriteSheets.getTile(256,32,16,16);
        grassImage = spriteSheets.getTile(272,32,16,16);
        steelWallImage = spriteSheets.getTile(256,16,16,16);
    }

    public static BufferedImage getWallImage() {
        return wallImage;
    }

    public static BufferedImage getGrassImage() {
        return grassImage;
    }

    public static BufferedImage getWaterImage() {
        return waterImage;
    }

    public static BufferedImage getSteelWallImage() {
        return steelWallImage;
    }
}
