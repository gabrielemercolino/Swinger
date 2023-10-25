package example;

import swinger.router.Router;
import swinger.ui.button.Button.ButtonBuilder;

import javax.swing.*;
import java.awt.*;

import static swinger.ui.button.Helpers.*;

public class MenuPanel extends JPanel {
	public MenuPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(Color.black);
		add(Box.createVerticalGlue());
		add(ButtonBuilder.create(
				setText("Hello form menu"),
				setNormalBg("example/button_atlas.png"),
				setHorizontalAlignment(CENTER_ALIGNMENT),
				setOnClick(e -> Router.getInstance().navigate("Another")),
				setOnHover(e -> System.out.println("Hovering")),
				setOnExit(e -> System.out.println("Exited"))
		));
		add(Box.createVerticalGlue());
	}
}
