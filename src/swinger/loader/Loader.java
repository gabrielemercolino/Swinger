package swinger.loader;

import java.awt.Image;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Loader {
  private static ClassLoader classLoader = Loader.class.getClassLoader();

  public static ImageIcon loadImageIcon(String path) {
    Image img = null;
    try (InputStream is = classLoader.getResourceAsStream(path)) {
      img = ImageIO.read(is);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ImageIcon(img);
  }
}
