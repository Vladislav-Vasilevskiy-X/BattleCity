package adds;

import model.generator.Block;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Vladislav on 13-Sep-15.
 */
public class TileManager {

    public static ArrayList<Block> blocks = new ArrayList<Block>();

    public TileManager() {

    }

    public void update(double deltaTime) {
        for(Block block : blocks) {
            block.tick(deltaTime);
        }
    }

    public void render(Graphics2D g) {
        for(Block block : blocks) {
            block.render(g);
        }
    }

}
