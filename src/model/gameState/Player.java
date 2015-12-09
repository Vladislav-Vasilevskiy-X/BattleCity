package model.gameState;

import controller.PlayerListener;
import adds.KeySet;
import adds.Constants;
import model.movableObjects.Tank;

import java.awt.*;

/**
 * Created by Vladislav on 19-Sep-15.
 */
public class Player {

    private int id;
    private Color color;
    private Tank tank;
    private PlayerListener listener;
    private int score;
    private KeySet.Type type;

    public Player(KeySet.Type type, Color color, int id) {
        this.color = color;
        this.id = id;
        this.type = type;
    }

    public void initialize() {
        if (id == Constants.PLAYER_ONE_ID) {

            tank = new Tank(Constants.PLAYER1_START_X_POSITION,
                    Constants.PLAYER1_START_Y_POSITION, color, id, this);
        }

        if (id == Constants.PLAYER_TWO_ID) {

            tank = new Tank(Constants.PLAYER2_START_X_POSITION,
                    Constants.PLAYER2_START_Y_POSITION, color, id, this);
        }

        listener = new PlayerListener(this, type);
        score = 0;

        tank.init();
    }

    public void update(double deltaTime) {
         tank.update(deltaTime);
    }

    public void render(Graphics2D g) {
        tank.render(g);
    }

    public Tank getTank() {
        return tank;
    }

    public PlayerListener getListener() {
        return listener;
    }

    public int getScore() {
        return score;
    }

    public void addPointsToScore(int points) {
        score += points;
    }

    public void resetScore() {
        score = 0;
    }
}
