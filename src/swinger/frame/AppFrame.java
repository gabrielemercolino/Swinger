package swinger.frame;

import java.awt.Dimension;

import javax.swing.JFrame;

public class AppFrame extends JFrame{
  public AppFrame() {
    super();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Dimension d = new Dimension(1080, 720);
    setPreferredSize(d);
    setMinimumSize(new Dimension(720, 480));
    setLocationRelativeTo(null);
    setVisible(true);
  }
}
