package model.generator;

import adds.Constants;
import adds.TileManager;
import adds.Vector2F;
import adds.images.Images;
import graphics.LoadImageFrom;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Vladislav on 13-Sep-15.
 */
public class Map {

    private TileManager tiles = new TileManager();

    public void init() {

        BufferedImage mapImage = LoadImageFrom.LoadImageFrom(Images.class, "myMap.png");

        for (int x = 0; x < Constants.MAP_WIDTH; x++) {
            for (int y = 0; y < Constants.MAP_HEIGHT; y++) {

                int columns = mapImage.getRGB(x, y);

                switch (columns & 0xFFFFFF) {
                    case 0x7F3300:
                        TileManager.blocks.add(new Block(new Vector2F(x * Constants.BLOCK_SIZE, y * Constants.BLOCK_SIZE), Block.BlockType.WALL, true));
                        break;
                    case 0x0094FF:
                        TileManager.blocks.add(new Block(new Vector2F(x * Constants.BLOCK_SIZE, y * Constants.BLOCK_SIZE), Block.BlockType.WATER, true));
                        break;
                    case 0x4CFF00:
                        TileManager.blocks.add(new Block(new Vector2F(x * Constants.BLOCK_SIZE, y * Constants.BLOCK_SIZE), Block.BlockType.GRASS, false));
                        break;
                    case 0x808080:
                        TileManager.blocks.add(new Block(new Vector2F(x * Constants.BLOCK_SIZE, y * Constants.BLOCK_SIZE), Block.BlockType.STEEL_WALL, true));
                        break;
                }


            }
        }
    }

    public void update(double deltaTime) {
        tiles.update(deltaTime);
    }

    public void render(Graphics2D g) {
        tiles.render(g);
    }

}
