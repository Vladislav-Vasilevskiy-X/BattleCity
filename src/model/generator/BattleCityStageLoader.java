package model.generator;

import adds.KeySet;
import adds.Constants;
import model.gameState.GameState;
import model.gameState.GameStateManager;
import model.gameState.Player;
import model.movableObjects.Tank;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Vladislav on 11-Sep-15.
 */
public class BattleCityStageLoader extends GameState {

    private Map map;

    private JFrame frame;

    private Player player1;
    private Player player2;

    public static ArrayList<Tank> tanks = new ArrayList<>();

    public BattleCityStageLoader(GameStateManager gsm, JFrame frame) {
        super(gsm);
        this.frame = frame;
    }

    @Override
    public void init() {
        map = new Map();
        map.init();

        player1 = new Player(KeySet.Type.KEY_SET_TYPE_LETTERS, Color.YELLOW, Constants.PLAYER_ONE_ID);
        player2 = new Player(KeySet.Type.KEY_SET_TYPE_NUMBERS, Color.PINK, Constants.PLAYER_TWO_ID);

        player1.initialize();
        player2.initialize();

        tanks.add(player1.getTank());
        tanks.add(player2.getTank());

        frame.addKeyListener(player1.getListener());
        frame.addKeyListener(player2.getListener());
    }

    @Override
    public void update(double deltaTime) {
        player1.update(deltaTime);
        player2.update(deltaTime);

        map.update(deltaTime);
    }

    @Override
    public void render(Graphics2D g) {
        player1.render(g);
        player2.render(g);

        map.render(g);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}

