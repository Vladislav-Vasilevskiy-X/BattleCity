package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vladislav on 03-Sep-15.
 */
public class GameWindow extends JFrame{

	private static final long serialVersionUID = 1L;

//    private int width;
//    private int height;
	
//    boolean fullscreenE = false;
//    int fullscreenMode = 0;
//    GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[1];

    public GameWindow(String title, int width, int height) {
        setTitle(title);
        setSize(new Dimension(width, height));
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(WindowConstants.);
        setResizable(false);
        setFocusable(true);
        setVisible(true);
    }
}
