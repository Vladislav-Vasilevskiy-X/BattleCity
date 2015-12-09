package model.movableObjects;

import java.awt.Point;

import adds.Vector2F;

public class MovableObject {

    protected Vector2F position;

    protected int width;
    protected int height;

    public final static int UP_DIRECTION = 0;
    public final static int DOWN_DIRECTION = 1;
    public final static int RIGHT_DIRECTION = 2;
    public final static int LEFT_DIRECTION = 3;

    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;

    private float slowdown;
    private float speed;

    protected float speedUp = 0;
    protected float speedDown = 0;
    protected float speedLeft = 0;
    protected float speedRight = 0;

    protected float fixDt = 47f / 60f;

    public int currentDirection;

    public MovableObject(int startXCoord, int startYCoord,
                         int objectWidth, int objectHeight,
                         int currentDirection, float speed, float slowdown) {

        position = new Vector2F(startXCoord, startYCoord);
        this.width = objectWidth;
        this.height = objectHeight;
        this.currentDirection = currentDirection;
        this.speed = speed;
        this.slowdown = slowdown;
//        init();
    }

    public void init() {
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

    public void setOneDirection(boolean up, boolean down, boolean left, boolean right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public void moveUp(float speed) {
        if (speedUp < this.speed) {
            speedUp += slowdown;
        } else {
            speedUp = this.speed;
        }
        position.yPos -= speed;
    }

    public void moveDown(float speed) {
        if (speedDown < this.speed) {
            speedDown += slowdown;
        } else {
            speedDown = this.speed;
        }

        position.yPos += speed;
    }

//    public void glideDown(float speed) {
//        if (!down) {
//            if (!Check.collisionMovableObjectToBlock(
//
//                    new Point((int) (position.xPos + GameLoop.map.xPos),
//                            (int) (position.yPos + GameLoop.map.yPos + height + speed)),
//
//                    new Point((int) (position.xPos + GameLoop.map.xPos + width),
//                            (int) (position.yPos + GameLoop.map.yPos + height + speed)))) {
//
//
//                if (speedDown != 0) {
//                    speedDown -= .05f;
//
//                    if (speedDown < 0) {
//                        speedDown = 0;
//                    }
//                }
//                position.yPos += speed;
//            } else {
//                speedDown = 0;
//            }
//        }
//    }

    public void moveLeft(float speed) {

        if (speedLeft < this.speed) {
            speedLeft += slowdown;
        } else {
            speedLeft = this.speed;
        }
        position.xPos -= speed;

    }

    public void moveRight(float speed) {
        if (speedRight < this.speed) {
            speedRight += slowdown;
        } else {
            speedRight = this.speed;
        }
        position.xPos += speed;
    }

    public Point[] getEdgePointsInMovement(float moveAmount) {

        float[] coordinates = new float[4];

        float x = position.xPos;
        float xPlusWidth = position.xPos + width;
        float y = position.yPos;
        float yPlusHeight = position.yPos + height;

        switch (currentDirection) {
            case UP_DIRECTION:
                coordinates[0] = x;
                coordinates[1] = y - moveAmount;
                coordinates[2] = xPlusWidth;
                coordinates[3] = y - moveAmount;
                break;
            case DOWN_DIRECTION:
                coordinates[0] = x;
                coordinates[1] = yPlusHeight + moveAmount;
                coordinates[2] = xPlusWidth;
                coordinates[3] = yPlusHeight + moveAmount;
                break;
            case LEFT_DIRECTION:
                coordinates[0] = x - moveAmount;
                coordinates[1] = y;
                coordinates[2] = x - moveAmount;
                coordinates[3] = yPlusHeight;
                break;
            case RIGHT_DIRECTION:
                coordinates[0] = xPlusWidth + moveAmount;
                coordinates[1] = y;
                coordinates[2] = xPlusWidth + moveAmount;
                coordinates[3] = yPlusHeight;
                break;
        }

        Point[] points = new Point[2];
        points[0] = new Point((int) coordinates[0], (int) coordinates[1]);
        points[1] = new Point((int) coordinates[2], (int) coordinates[3]);

        return points;
    }

    public static float average(float number1, float number2) {
        return (number1 + number2) / 2;
    }

    public Vector2F getPosition() {
        return position;
    }
}
