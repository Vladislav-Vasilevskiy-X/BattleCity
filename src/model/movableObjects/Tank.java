package model.movableObjects;

import check.Check;
import adds.Vector2F;
import adds.Constants;
import graphics.TankAnimator;
import model.gameState.Player;

import java.awt.*;

/**
 * Created by Vladislav on 19-Sep-15.
 */
public class Tank extends MovableObject {

    private Player player;

    private boolean alive;
    private int playerId;

    private Bullet bullet;
    private TankAnimator tankAnimator;

    public Tank(int x, int y, Color color, int playerId, Player player) {

        super(x, y, Constants.TANK_WIDTH,
                Constants.TANK_HEIGHT,
                0, Constants.TANK_SPEED,
                Constants.TANK_SLOWDOWN);

        this.playerId = playerId;
        this.player = player;
        bullet = new Bullet(this, x, y, 1);
        tankAnimator = new TankAnimator(color);
    }

    public void init() {

        alive = true;

        bullet.init();

        currentDirection = UP_DIRECTION;
    }

    public void reset() {
        alive = true;
        if(playerId == Constants.PLAYER_ONE_ID) {
            position.xPos = Constants.PLAYER1_START_X_POSITION;
            position.yPos = Constants.PLAYER1_START_Y_POSITION;
        }
        if(playerId == Constants.PLAYER_TWO_ID) {
            position.xPos = Constants.PLAYER2_START_X_POSITION;
            position.yPos = Constants.PLAYER2_START_Y_POSITION;
        }
    }

    public void reload() {

        bullet.updateLocationDependingOnTankLocation();
    }

    public void update(double deltaTime) {

        bullet.update(deltaTime);

        reload();

        float upMoveAmount = (float) (speedUp * fixDt);
        float downMoveAmount = (float) (speedDown * fixDt);
        float leftMoveAmount = (float) (speedLeft * fixDt);
        float rightMoveAmount = (float) (speedRight * fixDt);

        if (up) {
            currentDirection = UP_DIRECTION;
            Point[] points = getEdgePointsInMovement(upMoveAmount);
            if (!Check.collisionMovableObjectToBlock(points[0], points[1]) &&
                    !Check.collisionTankToTank(this)) {
                moveUp(upMoveAmount);
            } else {
                speedUp = 0;
            }
        } else {
//            glideUp(upMoveAmount);
        }


        if (down) {
            currentDirection = DOWN_DIRECTION;
            Point[] points = getEdgePointsInMovement(downMoveAmount);
            if (!Check.collisionMovableObjectToBlock(points[0], points[1]) &&
                    !Check.collisionTankToTank(this)) {
                moveDown(downMoveAmount);

            } else {
                speedDown = 0;
            }

        } else {
//            glideDown(downMoveAmount);
        }

        if (left) {
            currentDirection = LEFT_DIRECTION;
            Point[] points = getEdgePointsInMovement(leftMoveAmount);
            if (!Check.collisionMovableObjectToBlock(points[0], points[1]) &&
                    !Check.collisionTankToTank(this)) {
                moveLeft(leftMoveAmount);
            } else {
                speedLeft = 0;
            }

        } else {
//            glideLeft(leftMoveAmount);
        }

        if (right) {
            currentDirection = RIGHT_DIRECTION;
            Point[] points = getEdgePointsInMovement(rightMoveAmount);
            if (!Check.collisionMovableObjectToBlock(points[0], points[1]) &&
                    !Check.collisionTankToTank(this)) {
                moveRight(rightMoveAmount);
            } else {
                speedRight = 0;
            }

        } else {
//            glideRight(rightMoveAmount);
        }

    }

    public void render(Graphics2D g) {

        bullet.render(g);

        if (alive) {

            if (currentDirection == UP_DIRECTION) {
                g.drawImage(tankAnimator.getAnimatorUp().sprite, (int) position.xPos, (int) position.yPos, width, height, null);
                if (up) {
                    tankAnimator.getAnimatorUp().update(System.currentTimeMillis());
                }
            }
            if (currentDirection == DOWN_DIRECTION) {
                g.drawImage(tankAnimator.getAnimatorDown().sprite, (int) position.xPos, (int) position.yPos, width, height, null);
                if (down) {
                    tankAnimator.getAnimatorDown().update(System.currentTimeMillis());
                }
            }
            if (currentDirection == RIGHT_DIRECTION) {
                g.drawImage(tankAnimator.getAnimatorRight().sprite, (int) position.xPos, (int) position.yPos, width, height, null);
                if (right) {
                    tankAnimator.getAnimatorRight().update(System.currentTimeMillis());
                }
            }
            if (currentDirection == LEFT_DIRECTION) {
                g.drawImage(tankAnimator.getAnimatorLeft().sprite, (int) position.xPos, (int) position.yPos, width, height, null);
                if (left) {
                    tankAnimator.getAnimatorLeft().update(System.currentTimeMillis());
                }
            }
        } else {
            if (!tankAnimator.getExplosionAnimator().isLastCadrShowing()) {
                g.drawImage(tankAnimator.getExplosionAnimator().sprite,
                        (int) position.xPos,
                        (int) position.yPos,
                        tankAnimator.getExplosionAnimator().sprite.getWidth(),
                        tankAnimator.getExplosionAnimator().sprite.getHeight(), null);

                tankAnimator.getExplosionAnimator().update(System.currentTimeMillis());
            } else {
                tankAnimator.getExplosionAnimator().reset();
                reset();
            }
        }

    }

    public void shot() {
        if (!bullet.isAlive()) {
            bullet.setAlive(true);
        }
    }

    public void makeDead() {
        alive = false;
        bullet.kill();
    }


    public void setOneDirection(boolean up, boolean down, boolean left, boolean right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }


    public boolean isAlive() {
        return alive;
    }

    public int getDirection() {
        return currentDirection;
    }

    public Vector2F getPosition() {
        return position;
    }

    public Player getPlayer() {
        return player;
    }
}
