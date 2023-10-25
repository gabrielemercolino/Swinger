package swinger.frame;

import javax.swing.*;
import java.awt.*;

/**
 * The default JFrame for <code>Swinger</code>
 */
public class DefaultFrame extends JFrame {
	public DefaultFrame() {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1080, 720));
		setMinimumSize(new Dimension(720, 480));
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
