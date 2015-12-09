package check;

import model.generator.Block;
import adds.Constants;
import adds.TileManager;
import adds.Vector2F;
import model.generator.BattleCityStageLoader;
import model.movableObjects.Bullet;
//import model.movableObjects.Player;
import model.movableObjects.Tank;

import java.awt.*;

/**
 * Created by Vladislav on 14-Sep-15.
 */
public class Check {

    public static Block markedBlock;
    public static Tank markedTank;

    public static boolean collisionMovableObjectToBlock(Point point1, Point point2) {
        for (Block block : TileManager.blocks) {
            if (block.isSolid()) {
                if (block.contains(point1) || block.contains(point2)) {
                    markedBlock = block;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean collisionTankToTank(Tank player1) {

        Point points[] = player1.getEdgePointsInMovement(0);
        for (Tank tank : BattleCityStageLoader.tanks) {
            if (!tank.equals(player1)) {
                Vector2F pos = tank.getPosition();
                Rectangle playerRectangle = new Rectangle((int) pos.xPos, (int) pos.yPos, Constants.TANK_WIDTH, Constants.TANK_HEIGHT);
                if (playerRectangle.contains(points[0]) || playerRectangle.contains(points[1])) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean collisionBulletBlock(Point point1, Point point2) {
        for (Block block : TileManager.blocks) {
            if (block.isSolid()) {
                if (block.contains(point1) || block.contains(point2)) {
                    markedBlock = block;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean collisionBulletTank(Bullet bullet) {

        Point points[] = bullet.getEdgePointsInMovement(0);

        for (Tank tank : BattleCityStageLoader.tanks) {
            Vector2F pos = tank.getPosition();
            Rectangle tankRectangle = new Rectangle((int) pos.xPos, (int) pos.yPos, Constants.TANK_WIDTH, Constants.TANK_HEIGHT);
            if ((!tank.equals(bullet.getTank())) && (tankRectangle.contains(points[0]) || tankRectangle.contains(points[1]))) {
                markedTank = tank;
                tank.getPlayer().addPointsToScore(1);
                return true;
            }
        }

        return false;
    }

    public static Block.BlockType typeOfMarkedBlock() {
        return markedBlock.getBlockType();
    }


}
