package example;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import swinger.ui.button.Button.ButtonBuilder;
import static swinger.ui.button.Button.ButtonBuilder.*;

public class MenuPanel extends JPanel {
  @SuppressWarnings("unchecked")
  public MenuPanel() {
    super();
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    setBackground(Color.black);
    add(Box.createVerticalGlue());
    add(ButtonBuilder.create(
      withText("Hello"),
      withBgImage("example/button_atlas.png"),
      withHorizontalAligment(CENTER_ALIGNMENT)
      ));
    add(Box.createVerticalGlue());
  }
}
