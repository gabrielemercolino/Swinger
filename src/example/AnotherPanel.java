package example;

import swinger.router.Router;
import swinger.ui.button.Button.ButtonBuilder;

import javax.swing.*;
import java.awt.*;

import static swinger.ui.button.Helpers.*;

public class AnotherPanel extends JPanel {
	@SuppressWarnings("unchecked")
	public AnotherPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(Color.black);
		add(Box.createVerticalGlue());
		add(ButtonBuilder.create(
				setText("Hello from another panel"),
				setNormalBg("example/button_atlas.png"),
				setHorizontalAligment(CENTER_ALIGNMENT),
				setOnClick(e -> Router.getInstance().navigate("Menu")),
				setOnHover(e -> System.out.println("Hovering")),
				setOnExit(e -> System.out.println("Exited"))
		));
		add(Box.createVerticalGlue());
	}
}
