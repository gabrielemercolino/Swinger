package swinger.ui.button;

import swinger.loader.Loader;

import java.awt.event.MouseEvent;
import java.util.function.Consumer;

/**
 * Gives basic control for the <code>Button</code> behaviour
 */
public class Helpers {
	public static Consumer<Button> setText(String text) {
		return b -> b.text = text;
	}

	public static Consumer<Button> setNormalBg(String path) {
		return b -> b.normalBgImage = Loader.loadImageIcon(path);
	}

	public static Consumer<Button> setHorizontalAlignment(float alignment) {
		return b -> b.setAlignmentX(alignment);
	}

	public static Consumer<Button> setOnClick(Consumer<MouseEvent> action) {
		return b -> b.buttonMouseHandler.setOnClick(action);
	}

	public static Consumer<Button> setOnHover(Consumer<MouseEvent> action) {
		return b -> b.buttonMouseHandler.setOnHover(action);
	}

	public static Consumer<Button> setOnExit(Consumer<MouseEvent> action) {
		return b -> b.buttonMouseHandler.setOnExit(action);
	}
}
