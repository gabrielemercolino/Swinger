package swinger.ui.button;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Button extends JLabel {
  String text;
  ImageIcon normalBgImage;

  private Button() {
    super();
    setHorizontalTextPosition(CENTER);
    setVerticalTextPosition(CENTER);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    drawCenteredString(g2d, text, getVisibleRect(), g2d.getFont());
  }

  /**
   * Draw a String centered in the middle of a Rectangle.
   *
   * @author Not me
   * @param g The Graphics instance.
   * @param text The String to draw.
   * @param rect The Rectangle to center the text in.
   */
  public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
    // Get the FontMetrics
    FontMetrics metrics = g.getFontMetrics(font);
    // Determine the X coordinate for the text
    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
    // Determine the Y coordinate for the text (note we add the ascent, as in java
    // 2d 0 is top of the screen)
    int y = rect.y
            + ((rect.height - metrics.getHeight()) / 2)
            + metrics.getAscent();
    // Set the font
    g.setFont(font);
    // Draw the String
    g.drawString(text, x, y);
  }

  public static class ButtonBuilder{
    @SuppressWarnings("unchecked")
    public static Button create(Consumer<Button>... mods){
      Button button = new Button();
      for (Consumer<Button> mod : mods) {
        mod.accept(button);
      }

      if (button.normalBgImage != null) button.setIcon(button.normalBgImage);
      
      return button;
    }
  }
}
