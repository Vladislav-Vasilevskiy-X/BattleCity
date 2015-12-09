package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Vladislav on 10-Sep-15.
 */
public class LoadImageFrom {

	public LoadImageFrom() {
		
	}
	
    public static BufferedImage LoadImageFrom(Class<?> classfile, String path) {
        URL url = classfile.getResource(path);
        BufferedImage image = null;

        try {
            image = ImageIO.read(url);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}