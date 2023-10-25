package swinger.loader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Helper class for loading assets
 */
public class Loader {
	private static final ClassLoader classLoader = Loader.class.getClassLoader();

	public static ImageIcon loadImageIcon(String path) {
		Image img = null;
		try (InputStream is = classLoader.getResourceAsStream(path)) {
			img = ImageIO.read(is);
		} catch (NullPointerException | IOException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ImageIcon(img);
	}
}
