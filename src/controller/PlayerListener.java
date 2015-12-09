package controller;

import adds.KeySet;
import model.gameState.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Vladislav on 19-Sep-15.
 */
public class PlayerListener implements KeyListener {

    private Player player;
    private KeySet keySet;

    public PlayerListener(Player player, KeySet.Type type) {
        this.player = player;
        this.keySet = new KeySet(type);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == keySet.SHOT)
            player.getTank().shot();
        if (key == keySet.UP)
            player.getTank().setOneDirection(true, false, false, false);
        if (key == keySet.DOWN)
            player.getTank().setOneDirection(false, true, false, false);
        if (key == keySet.LEFT)
            player.getTank().setOneDirection(false, false, true, false);
        if (key == keySet.RIGHT)
            player.getTank().setOneDirection(false, false, false, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == keySet.UP)
            player.getTank().up = false;
        if (key == keySet.DOWN)
            player.getTank().down = false;
        if (key == keySet.LEFT)
            player.getTank().left = false;
        if (key == keySet.RIGHT)
            player.getTank().right = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
