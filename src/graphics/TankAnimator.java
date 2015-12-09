package graphics;

import adds.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Vladislav on 20-Sep-15.
 */
public class TankAnimator {

    private Color color;

    private Animator animatorUp;
    private Animator animatorDown;
    private Animator animatorLeft;
    private Animator animatorRight;
    private Animator explosionAnimator;

    private ArrayList<BufferedImage> listUp;
    private ArrayList<BufferedImage> listDown;
    private ArrayList<BufferedImage> listLeft;
    private ArrayList<BufferedImage> listRight;
    private ArrayList<BufferedImage> listTankExplosion;

    public TankAnimator(Color color){
        initialize(color);
    }

    public void initialize(Color color) {

        listUp=new ArrayList<BufferedImage>();
        listDown=new ArrayList<BufferedImage>();
        listLeft=new ArrayList<BufferedImage>();
        listRight=new ArrayList<BufferedImage>();
        listTankExplosion=new ArrayList<BufferedImage>();

        listTankExplosion.add(Assets.player.getTile(Constants.TILE_EXPLOSION1_X_COORDINATE,
                Constants.TILE_EXPLOSION1_Y_COORDINATE,
                Constants.TILE_EXPLOSION1_SIZE,
                Constants.TILE_EXPLOSION1_SIZE));
        listTankExplosion.add(Assets.player.getTile(Constants.TILE_EXPLOSION2_X_COORDINATE,
                Constants.TILE_EXPLOSION2_Y_COORDINATE,
                Constants.TILE_EXPLOSION2_WIDTH,
                Constants.TILE_EXPLOSION2_HEIGHT));
        listTankExplosion.add(Assets.player.getTile(Constants.TILE_EXPLOSION3_X_COORDINATE,
                Constants.TILE_EXPLOSION3_Y_COORDINATE,
                Constants.TILE_EXPLOSION3_WIDTH,
                Constants.TILE_EXPLOSION3_HEIGHT));
        listTankExplosion.add(Assets.player.getTile(Constants.TILE_EXPLOSION4_X_COORDINATE,
                Constants.TILE_EXPLOSION4_Y_COORDINATE,
                Constants.TILE_EXPLOSION4_WIDTH,
                Constants.TILE_EXPLOSION4_HEIGHT));
        listTankExplosion.add(Assets.player.getTile(Constants.TILE_EXPLOSION5_X_COORDINATE,
                Constants.TILE_EXPLOSION5_Y_COORDINATE,
                Constants.TILE_EXPLOSION5_WIDTH,
                Constants.TILE_EXPLOSION5_HEIGHT));


        if(color == Color.YELLOW) {
            listUp.add(Assets.player.getTile(Constants.TILE1_UP_YELLOW_TANK_X_COORDINATE,
                    Constants.TILE_UP_YELLOW_TANK_Y_COORDINATE,
                    Constants.TILE_TANK_SIZE,
                    Constants.TILE_TANK_SIZE));
            listUp.add(Assets.player.getTile(Constants.TILE2_UP_YELLOW_TANK_X_COORDINATE,
                    Constants.TILE_UP_YELLOW_TANK_Y_COORDINATE,
                    Constants.TILE_TANK_SIZE,
                    Constants.TILE_TANK_SIZE));

            listLeft.add(Assets.player.getTile(Constants.TILE1_LEFT_YELLOW_TANK_X_COORDINATE,
                    Constants.TILE_YELLOW_TANK_Y_COORDINATE,
                    Constants.TILE_TANK_SIZE,
                    Constants.TILE_TANK_SIZE));
            listLeft.add(Assets.player.getTile(Constants.TILE2_LEFT_YELLOW_TANK_X_COORDINATE,
                    Constants.TILE_YELLOW_TANK_Y_COORDINATE,
                    Constants.TILE_TANK_SIZE,
                    Constants.TILE_TANK_SIZE));

            listDown.add(Assets.player.getTile(Constants.TILE1_DOWN_YELLOW_TANK_X_COORDINATE,
                    Constants.TILE_YELLOW_TANK_Y_COORDINATE,
                    Constants.TILE_TANK_SIZE,
                    Constants.TILE_TANK_SIZE));
            listDown.add(Assets.player.getTile(Constants.TILE2_DOWN_YELLOW_TANK_X_COORDINATE,
                    Constants.TILE_YELLOW_TANK_Y_COORDINATE,
                    Constants.TILE_TANK_SIZE,
                    Constants.TILE_TANK_SIZE));

            listRight.add(Assets.player.getTile(Constants.TILE1_RIGHT_YELLOW_TANK_X_COORDINATE,
                    Constants.TILE_YELLOW_TANK_Y_COORDINATE,
                    Constants.TILE_TANK_SIZE,
                    Constants.TILE_TANK_SIZE));
            listRight.add(Assets.player.getTile(Constants.TILE2_RIGHT_YELLOW_TANK_X_COORDINATE,
                    Constants.TILE_YELLOW_TANK_Y_COORDINATE,
                    Constants.TILE_TANK_SIZE,
                    Constants.TILE_TANK_SIZE));
        }
        if(color == Color.PINK) {
            listUp.add(Assets.player.getTile(Constants.TILE1_UP_PURPLE_TANK_X_COORDINATE,
                    Constants.TILE_UP_PURPLE_TANK_Y_COORDINATE,
                    Constants.TILE_TANK_SIZE,
                    Constants.TILE_TANK_SIZE));
            listUp.add(Assets.player.getTile(Constants.TILE2_UP_PURPLE_TANK_X_COORDINATE,
                    Constants.TILE_UP_PURPLE_TANK_Y_COORDINATE,
                    Constants.TILE_TANK_SIZE,
                    Constants.TILE_TANK_SIZE));

            listLeft.add(Assets.player.getTile(Constants.TILE1_LEFT_PURPLE_TANK_X_COORDINATE,
                    Constants.TILE_PURPLE_TANK_Y_COORDINATE,
                    Constants.TILE_TANK_SIZE,
                    Constants.TILE_TANK_SIZE));
            listLeft.add(Assets.player.getTile(Constants.TILE2_LEFT_PURPLE_TANK_X_COORDINATE,
                    Constants.TILE_PURPLE_TANK_Y_COORDINATE,
                    Constants.TILE_TANK_SIZE,
                    Constants.TILE_TANK_SIZE));

            listDown.add(Assets.player.getTile(Constants.TILE1_DOWN_PURPLE_TANK_X_COORDINATE,
                    Constants.TILE_PURPLE_TANK_Y_COORDINATE,
                    Constants.TILE_TANK_SIZE,
                    Constants.TILE_TANK_SIZE));
            listDown.add(Assets.player.getTile(Constants.TILE2_DOWN_PURPLE_TANK_X_COORDINATE,
                    Constants.TILE_PURPLE_TANK_Y_COORDINATE,
                    Constants.TILE_TANK_SIZE,
                    Constants.TILE_TANK_SIZE));

            listRight.add(Assets.player.getTile(Constants.TILE1_RIGHT_PURPLE_TANK_X_COORDINATE,
                    Constants.TILE_PURPLE_TANK_Y_COORDINATE,
                    Constants.TILE_TANK_SIZE,
                    Constants.TILE_TANK_SIZE));
            listRight.add(Assets.player.getTile(Constants.TILE2_RIGHT_PURPLE_TANK_X_COORDINATE,
                    Constants.TILE_PURPLE_TANK_Y_COORDINATE,
                    Constants.TILE_TANK_SIZE,
                    Constants.TILE_TANK_SIZE));
        }

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

        explosionAnimator = new Animator(listTankExplosion);
        explosionAnimator.setSpeed(120);
        explosionAnimator.play();
        explosionAnimator.update(System.currentTimeMillis());
    }

    public Animator getAnimatorUp() {
        return animatorUp;
    }

    public Animator getAnimatorDown() {
        return animatorDown;
    }

    public Animator getAnimatorLeft() {
        return animatorLeft;
    }

    public Animator getAnimatorRight() {
        return animatorRight;
    }

    public Animator getExplosionAnimator() {
        return explosionAnimator;
    }
}
