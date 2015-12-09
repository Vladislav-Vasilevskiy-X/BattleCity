package model.gameState;

import model.generator.BattleCityStageLoader;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * Created by Vladislav on 10-Sep-15.
 */
public class GameStateManager {

    public static Stack<GameState> states;
    private BattleCityStageLoader battleCityStageLoader;

    public GameStateManager(JFrame frame) {
        states = new Stack<GameState>();
        battleCityStageLoader = new BattleCityStageLoader(this, frame);
        states.push(battleCityStageLoader);
    }

    public void tick(double deltaTime) {
        states.peek().update(deltaTime);
    }

    public void render(Graphics2D g) {
        states.peek().render(g);
    }

    public void init() {
        states.peek().init();
    }

    public BattleCityStageLoader getBattleCityStageLoader() {
        return battleCityStageLoader;
    }
}
