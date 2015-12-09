package controller;

import model.gameLoop.GameLoop;
import model.gameState.GameStateManager;
import view.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Created by user on 13-Oct-15.
 */
public class ControlWindow extends JFrame {

    private final int CONTROL_WINDOW_HEIGHT = 150;
    private final int CONTROL_WINDOW_WIDTH = 140;
    private final int GAME_WINDOW_HEIGHT = 768;
    private final int GAME_WINDOW_WIDTH = 1024;

    private GameLoop gameLoop = null;
    private GameWindow frame = null;

    private JLabel playerOneScore;
    private JLabel playerTwoScore;

    private JButton buttonReset;
    private JButton buttonPause;

    private JTextField textFieldTimer;
    private JTextField textFieldFrags;

    public ControlWindow() {
        init();
    }

    public ControlWindow(GameLoop gameLoop) {
        setGameLoop(gameLoop);
        init();
    }

    public void init() {

        this.setSize(CONTROL_WINDOW_WIDTH, CONTROL_WINDOW_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocation(1,1);
        this.setLayout(new GridLayout(5,2));

        playerOneScore = new JLabel("0");
        playerTwoScore = new JLabel("0");

        buttonReset = new JButton("reset");

        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                //gameLoop.init();
            }
        });

        buttonPause = new JButton("pause");

        buttonPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameLoop != null)
                    gameLoop.changePaused();
            }
        });
        textFieldFrags = new JTextField("10",2);
        textFieldTimer = new JTextField("1",2);

        JLabel timeLabel = new JLabel("Time: ");
        JLabel fragsLabel = new JLabel("Frags: ");
        JLabel playerOneLabel = new JLabel("Player 1");
        JLabel playerTwoLabel = new JLabel("Player 2");

        this.add(playerOneLabel);
        this.add(playerTwoLabel);
        this.add(playerOneScore);
        this.add(playerTwoScore);
        this.add(timeLabel);
        this.add(textFieldTimer);
        this.add(fragsLabel);
        this.add(textFieldFrags);
        this.add(buttonReset);
        this.add(buttonPause);

        this.setVisible(true);
    }

    public void updateInfo(){
        playerOneScore.setText(Integer.toString(gameLoop.getGameStateManager().getBattleCityStageLoader().getPlayer1().getScore()));
        playerTwoScore.setText(Integer.toString(gameLoop.getGameStateManager().getBattleCityStageLoader().getPlayer2().getScore()));
    }

    public void reset()
    {
        if(gameLoop != null)
            gameLoop.setRunning(false);
        if(frame!= null)
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        frame = new GameWindow("Battle city 2D", GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
        gameLoop = new GameLoop(frame, GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
        gameLoop.setControlWindow(this);
        frame.add(gameLoop);
    }

    public boolean checkForWinner()
    {
        if (Integer.parseInt(playerOneScore.getText()) >= gameLoop.getMaxFrags()) {
            JOptionPane.showMessageDialog(this, "Player1 wins!");
            return true;
        }
        if (Integer.parseInt(playerTwoScore.getText()) >= gameLoop.getMaxFrags()) {
            JOptionPane.showMessageDialog(this, "Player2 wins!");
            return true;
        }
        return false;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }

    public JTextField getTextFieldFrags() {
        return textFieldFrags;
    }
}
