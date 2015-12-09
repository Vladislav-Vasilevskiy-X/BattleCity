package graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Vladislav on 14-Sep-15.
 */
public class Animator {

    private ArrayList<BufferedImage> frames;
    private volatile boolean running = false;
    public BufferedImage sprite;

    private long previousTime;
    private long speed;
    private int frameAtPause;
    private int currentFrame;

    public Animator(ArrayList<BufferedImage> frames) {
        this.frames = frames;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public void update(long time) {
        if (running) {
            if (time - previousTime >= speed) {
                currentFrame++;
                try {
                    sprite = frames.get(currentFrame);
                } catch (IndexOutOfBoundsException e){
                    currentFrame = 0;
                    sprite = frames.get(currentFrame);
                    reset();
                }
                previousTime = time;
            }
        }
    }

    public void play() {
        running = true;
        previousTime = 0;
        frameAtPause = 0;
        currentFrame = 0;
    }

    public void stop() {
        running = false;
        previousTime = 0;
        frameAtPause = 0;
        currentFrame = 0;
    }

    public void pause() {
        frameAtPause = currentFrame;
        running = false;
    }

    public void resume() {
        currentFrame = frameAtPause;
    }

    public void reset() {
        currentFrame = 0;
    }

    public boolean isLastCadrShowing() {
        if (currentFrame == frames.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isRunning() {
        return running;
    }
}
