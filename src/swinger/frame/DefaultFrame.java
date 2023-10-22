package swinger.frame;

import java.awt.Dimension;

import javax.swing.JFrame;

public class DefaultFrame extends JFrame{
  public DefaultFrame() {
    super();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(1080, 720));
    setMinimumSize(new Dimension(720, 480));
    setLocationRelativeTo(null);
    setVisible(true);
  }
}
