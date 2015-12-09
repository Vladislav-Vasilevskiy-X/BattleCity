package model.movableObjects;

import graphics.Animator;
import graphics.Assets;
import check.Check;
import adds.Vector2F;
import adds.Constants;
import adds.TileManager;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Vladislav on 17-Sep-15.
 */
public class Bullet extends MovableObject {

    private Tank tank;

    private boolean alive = false;

    private ArrayList<BufferedImage> listUp;
    Animator animatorUp;
    private ArrayList<BufferedImage> listDown;
    Animator animatorDown;
    private ArrayList<BufferedImage> listLeft;
    Animator animatorLeft;
    private ArrayList<BufferedImage> listRight;
    Animator animatorRight;

    public Bullet(Tank tank, int x, int y, int currentDirection) {
        
        super(x, y, Constants.BULLET_WIDTH,
                Constants.BULLET_HEIGHT,
                currentDirection,
                Constants.BULLET_SPEED,
                Constants.BULLET_SLOWDOWN);
        this.tank = tank;
        position = new Vector2F(x, y);
        this.currentDirection = currentDirection;
        
    }

    public void init() {

        listUp = new ArrayList<BufferedImage>();
        listDown = new ArrayList<BufferedImage>();
        listLeft = new ArrayList<BufferedImage>();
        listRight = new ArrayList<BufferedImage>();

        listUp.add(Assets.player.getTile(323, 102, 3, 4));

        listLeft.add(Assets.player.getTile(330, 102, 4, 3));

        listDown.add(Assets.player.getTile(339, 102, 3, 4));

        listRight.add(Assets.player.getTile(346, 102, 4, 3));


        //UP
        animatorUp = new Animator(listUp);
        animatorUp.setSpeed(180);
        animatorUp.play();
        animatorUp.update(System.currentTimeMillis());

        //DOWN
        animatorDown = new Animator(listDown);
        animatorDown.setSpeed(180);
        animatorDown.play();
        animatorDown.update(System.currentTimeMillis());

        //RIGHT
        animatorRight = new Animator(listRight);
        animatorRight.setSpeed(180);
        animatorRight.play();
        animatorRight.update(System.currentTimeMillis());

        //LEFT
        animatorLeft = new Animator(listLeft);
        animatorLeft.setSpeed(180);
        animatorLeft.play();
        animatorLeft.update(System.currentTimeMillis());


    }

    public void update(double deltaTime) {
        float upMoveAmount = (float) (speedUp * fixDt);
        float downMoveAmount = (float) (speedDown * fixDt);
        float leftMoveAmount = (float) (speedLeft * fixDt);
        float rightMoveAmount = (float) (speedRight * fixDt);

        if (alive) {
            if (up) {
                currentDirection = UP_DIRECTION;
                Point[] points = getEdgePointsInMovement(upMoveAmount);

                if (!Check.collisionMovableObjectToBlock(points[0], points[1])) {
                    if (!Check.collisionBulletTank(this)) {
                        moveUp(upMoveAmount);
                    } else {
                        Check.markedTank.makeDead();
                        kill();
                    }
                } else {
                    switch (Check.markedBlock.getBlockType()) {
                        case WALL:
                            TileManager.blocks.remove(Check.markedBlock);
                            kill();
                            break;
                        case WATER:
                            moveUp(upMoveAmount);
                            break;
                        case STEEL_WALL:
                            kill();
                            break;
                    }
                }
            }


            if (down) {
                currentDirection = DOWN_DIRECTION;
                Point[] points = getEdgePointsInMovement(downMoveAmount);

                if (!Check.collisionMovableObjectToBlock(points[0], points[1])) {
                    if (!Check.collisionBulletTank(this)) {
                        moveDown(downMoveAmount);
                    } else {
                        Check.markedTank.makeDead();
                        kill();
                    }
                } else {
                    switch (Check.markedBlock.getBlockType()) {
                        case WALL:
                            TileManager.blocks.remove(Check.markedBlock);
                            kill();
                            break;
                        case WATER:
                            moveDown(downMoveAmount);
                            break;
                        case STEEL_WALL:
                            kill();
                            break;
                    }
                }
            }

            if (left) {
                currentDirection = LEFT_DIRECTION;
                Point[] points = getEdgePointsInMovement(leftMoveAmount);

                if (!Check.collisionMovableObjectToBlock(points[0], points[1])) {
                    if (!Check.collisionBulletTank(this)) {
                        moveLeft(leftMoveAmount);
                    } else {
                        Check.markedTank.makeDead();
                        kill();
                    }
                } else {
                    switch (Check.markedBlock.getBlockType()) {
                        case WALL:
                            TileManager.blocks.remove(Check.markedBlock);
                            kill();
                            break;
                        case WATER:
                            moveLeft(leftMoveAmount);
                            break;
                        case STEEL_WALL:
                            kill();
                            break;
                    }
                }
            }


            if (right) {
                currentDirection = RIGHT_DIRECTION;
                Point[] points = getEdgePointsInMovement(rightMoveAmount);

                if (!Check.collisionMovableObjectToBlock(points[0], points[1])) {
                    if (!Check.collisionBulletTank(this)) {
                        moveRight(rightMoveAmount);
                    } else {
                        Check.markedTank.makeDead();
                        kill();
                    }
                } else {
                    switch (Check.markedBlock.getBlockType()) {
                        case WALL:
                            TileManager.blocks.remove(Check.markedBlock);
                            kill();
                            break;
                        case WATER:
                            moveRight(rightMoveAmount);
                            break;
                        case STEEL_WALL:
                            kill();
                            break;
                    }
                }
            }
        }
//        System.out.println(position.xPos + " " + position.yPos);
    }

    public void render(Graphics2D g) {
        if (alive) {
            if (currentDirection == UP_DIRECTION) {
                g.drawImage(animatorUp.sprite, (int) position.xPos, (int) position.yPos, width, height, null);
                if (up) {
                    animatorUp.update(System.currentTimeMillis());
                }
            }
            if (currentDirection == DOWN_DIRECTION) {
                g.drawImage(animatorDown.sprite, (int) position.xPos, (int) position.yPos, width, height, null);
                if (down) {
                    animatorDown.update(System.currentTimeMillis());
                }
            }
            if (currentDirection == RIGHT_DIRECTION) {
                g.drawImage(animatorRight.sprite, (int) position.xPos, (int) position.yPos, width, height, null);
                if (right) {
                    animatorRight.update(System.currentTimeMillis());
                }
            }
            if (currentDirection == LEFT_DIRECTION) {
                g.drawImage(animatorLeft.sprite, (int) position.xPos, (int) position.yPos, width, height, null);
                if (left) {
                    animatorLeft.update(System.currentTimeMillis());
                }
            }
        }

    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {

        alive = false;

        updateLocationDependingOnTankLocation();
    }

    public void updateLocationDependingOnTankLocation() {

            switch (tank.getDirection()) {
                case UP_DIRECTION:
                    newLocation(new Vector2F(average(tank.position.xPos, tank.position.xPos + tank.width),
                            tank.position.yPos), tank.currentDirection);
                    break;
                case DOWN_DIRECTION:
                    newLocation(new Vector2F(average(tank.position.xPos, tank.position.xPos + tank.width),
                            tank.position.yPos + height), tank.currentDirection);
                    break;
                case LEFT_DIRECTION:
                    newLocation(new Vector2F(tank.position.xPos,
                            average(tank.position.yPos, tank.position.yPos + tank.height)), tank.currentDirection);
                    break;
                case RIGHT_DIRECTION:
                    newLocation(new Vector2F(tank.position.xPos + width,
                            average(tank.position.yPos, tank.position.yPos + tank.height)), tank.currentDirection);
                    break;
            }

    }

    public void newLocation(Vector2F position, int currentDirection) {

        if (!alive) {
            this.position.xPos = position.xPos;
            this.position.yPos = position.yPos;

            this.currentDirection = currentDirection;

            switch (currentDirection) {
                case UP_DIRECTION:
                    setOneDirection(true, false, false, false);
                    break;
                case DOWN_DIRECTION:
                    setOneDirection(false, true, false, false);
                    break;
                case LEFT_DIRECTION:
                    setOneDirection(false, false, true, false);
                    break;
                case RIGHT_DIRECTION:
                    setOneDirection(false, false, false, true);
                    break;
            }
        }
    }


    public void setOneDirection(boolean up, boolean down, boolean left, boolean right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public Vector2F getPosition() {
        return position;
    }

    public Tank getTank() {
        return tank;
    }
}
