package model.generator;

import adds.Constants;
import adds.Vector2F;
import graphics.Assets;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/*
 * Created by Vladislav on 13-Sep-15.
 */
public class Block extends Rectangle {

	public Vector2F position = new Vector2F();

    private BlockType blockType;
    private BufferedImage image;

    public boolean solid;

    public Block(Vector2F position, BlockType blockType, boolean solid) {

        setBounds((int) position.xPos, (int) position.yPos, Constants.BLOCK_SIZE,  Constants.BLOCK_SIZE);
        this.solid = solid;
        this.position = position;
        this.blockType = blockType;
        init();

    }

    public enum BlockType {
        WALL,
        WATER,
        GRASS,
        STEEL_WALL
    }

    
    public void init() {
        switch (blockType) {
            case WALL:
                image = Assets.getWallImage();
                break;
            case WATER:
                image = Assets.getWaterImage();
                break;
            case GRASS:
                image = Assets.getGrassImage();
                break;
            case STEEL_WALL:
                image = Assets.getSteelWallImage();
        }
    }

    public void tick(double deltaTime) {

    }

    public void render(Graphics2D g) {
        g.drawImage(image, (int) position.getWorldLocation().xPos,
                (int) position.getWorldLocation().yPos,  Constants.BLOCK_SIZE,  Constants.BLOCK_SIZE, null);

    }
    
    public boolean isSolid() {
        return solid;
    }

    public BlockType getBlockType() {
        return blockType;
    }
}
