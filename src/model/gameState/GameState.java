package model.gameState;

import java.awt.*;

/*
 * Created by Vladislav on 10-Sep-15.
 */
public abstract class GameState {

    private GameStateManager gameStateManager;

    public GameState(GameStateManager stateManager) {
        this.setGameStateManager(stateManager);
    }
    
    public abstract void init();
    public abstract void update(double deltaTime);
    public abstract void render(Graphics2D g);

	public GameStateManager getGameStateManager() {
		return gameStateManager;
	}

	public void setGameStateManager(GameStateManager gameStateManager) {
		this.gameStateManager = gameStateManager;
	}
}
