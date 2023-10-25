package swinger.ui.button;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Implementation of a button meant to substitute <code>JButton</code> <br/>
 * To create a <code>Button</code> you need to use its builder <code>ButtonBuilder</code>:
 * <code><pre>
 *   Button b = Button.ButtonBuilder.create(...);
 * </pre></code>
 *
 * @see ButtonBuilder
 */
public class Button extends JLabel {
	String text;
	ImageIcon normalBgImage;
	ButtonMouseHandler buttonMouseHandler;

	private Button() {
		super();
		setHorizontalTextPosition(CENTER);
		setVerticalTextPosition(CENTER);
		buttonMouseHandler = new ButtonMouseHandler();
		addMouseListener(buttonMouseHandler);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// draw text on top
		drawCenteredString(g2d, text, getVisibleRect(), g2d.getFont());
	}

	/**
	 * Draw a String centered in the middle of a Rectangle.
	 *
	 * @param g    The Graphics instance.
	 * @param text The String to draw.
	 * @param rect The Rectangle to center the text in.
	 * @author <a href="https://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java">Daniel Kvist</a>
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

	/**
	 * The class meant to instantiate a <code>Button</code>
	 *
	 * @see Button
	 */
	public static class ButtonBuilder {
		/**
		 * The method takes an arbitrary number of <code>Consumer&lt;Button&gt;</code> to modify the look and behaviour of the <code>Button</code> which will be returned
		 *
		 * @param mods An arbitrary number of behaviours
		 * @return button
		 */
		@SafeVarargs
		public static Button create(Consumer<Button>... mods) {
			Button button = new Button();
			for (Consumer<Button> mod : mods) {
				mod.accept(button);
			}
			if (button.normalBgImage != null) button.setIcon(button.normalBgImage);
			return button;
		}
	}
}
