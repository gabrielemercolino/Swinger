package swinger.ui.button;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class ButtonMouseHandler extends MouseAdapter {
	private Consumer<MouseEvent> onClick, onHover, onExit;
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
