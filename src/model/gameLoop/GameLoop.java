package model.gameLoop;

import controller.ControlWindow;
import graphics.Assets;
import adds.Vector2F;
import model.gameState.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/*
 * Created by Vladislav on 10-Sep-15.
 */
public class GameLoop extends JPanel implements Runnable {

    private Thread thread;
    private boolean running;
    private boolean paused;
    private ControlWindow controlWindow = null;
    private int maxFrags;

    private int width;
    private int height;

    public Graphics2D graphics2D;
    private BufferedImage img;

    public static double currentFPS = 120D;
	
	private GameStateManager gameStateManager;
    private JFrame frame;

    public static Assets assets = new Assets();
    public static Vector2F map = new Vector2F();

    public GameLoop(JFrame frame,int width, int height) {
        this.width = width;
        this.height = height;
        this.frame = frame;

        setPreferredSize(new Dimension(width,height));
        setFocusable(false);
        requestFocus();
    }

    public void run() {

        init();

        long lastTime = System.nanoTime();
        double nanoSecondsPerTick = 1000000000 / currentFPS;;
        long timer = System.currentTimeMillis();
        double deltaTime = 0;

        while (running) {
            if(!isPaused()) {
                long now = System.nanoTime();
                deltaTime += (now - lastTime) / nanoSecondsPerTick;
                lastTime = now;
                boolean rendering = false;

                while (deltaTime >= 1) {
                    tick(deltaTime);
                    deltaTime--;
                    rendering = true;
                }

                if (rendering) {
                /*RENDER*/
                    render();
                }

                //sleepy
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                }
            }
        }
    }

    public boolean isPaused() {
        return paused;
    }

    public void changePaused()
    {
        if(paused == false) paused = true;
        else paused = false;
    }

    public void init() {

        running = true;
        paused = false;

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics2D = (Graphics2D) img.getGraphics();

        assets.init();
        Vector2F.setWorldVariables(map.xPos, map.yPos);

        gameStateManager = new GameStateManager(frame);
        gameStateManager.init();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void setRunning(boolean value)
    {
     running = value;
    }

    public void tick(double deltaTime) {
        Vector2F.setWorldVariables(map.xPos, map.yPos);
        gameStateManager.tick(deltaTime);
        if(controlWindow != null)
            controlWindow.updateInfo();
        if (controlWindow.checkForWinner()){
            setRunning(false);
        }
    }

    public void render() {
        graphics2D.clearRect(0, 0, width, height);
        gameStateManager.render(graphics2D);
        clear();
    }

    public void clear() {
        Graphics g2 = getGraphics();
        if(img != null)
            g2.drawImage(img, 0, 0, null);
        g2.dispose();
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public void setControlWindow(ControlWindow controlWindow) {
        this.controlWindow = controlWindow;
        maxFrags = Integer.parseInt(controlWindow.getTextFieldFrags().getText());
    }

    public int getMaxFrags() {
        return maxFrags;
    }
}
