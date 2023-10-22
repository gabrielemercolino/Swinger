package example;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import swinger.ui.button.Button.ButtonBuilder;
import static swinger.ui.button.Helpers.*;

public class MenuPanel extends JPanel {
  @SuppressWarnings("unchecked")
  public MenuPanel() {
    super();
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    setBackground(Color.black);
    add(Box.createVerticalGlue());
    add(ButtonBuilder.create(
      setText("Hello"),
      setNormalBg("example/button_atlas.png"),
      setHorizontalAligment(CENTER_ALIGNMENT)
      ));
    add(Box.createVerticalGlue());
  }
}
