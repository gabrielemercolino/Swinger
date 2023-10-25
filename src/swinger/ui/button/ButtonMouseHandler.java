package swinger.ui.button;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

/**
 * Handles the behaviour of the <code>Button</code>
 *
 * @see Button
 */
public class ButtonMouseHandler extends MouseAdapter {
	/**
	 * The action to do when the button is clicked
	 */
	private Consumer<MouseEvent> onClick;
	/**
	 * The action to do when the cursor enters the <code>Button</code> bounds
	 */
	private Consumer<MouseEvent> onHover;
	/**
	 * The action to do when the cursor exits the <code>Button</code> bounds
	 */
	private Consumer<MouseEvent> onExit;
	/**
	 * Used to determine if the cursor is inside the <code>Button</code> bounds
	 */
	private boolean inside;

	public void mouseReleased(MouseEvent e) {
		if (!inside) return;
		doAction(onClick, e);
	}

	public void mouseEntered(MouseEvent e) {
		inside = true;
		doAction(onHover, e);
	}

	public void mouseExited(MouseEvent e) {
		inside = false;
		doAction(onExit, e);
	}

	private void doAction(Consumer<MouseEvent> action, MouseEvent e) {
		if (action != null) action.accept(e);
	}

	public void setOnClick(Consumer<MouseEvent> onClick) {
		this.onClick = onClick;
	}

	public void setOnHover(Consumer<MouseEvent> onHover) {
		this.onHover = onHover;
	}

	public void setOnExit(Consumer<MouseEvent> onExit) {
		this.onExit = onExit;
	}
}
