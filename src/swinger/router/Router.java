package swinger.router;

import swinger.events.Events;
import swinger.exceptions.RouteNotFoundException;
import swinger.frame.DefaultFrame;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Handles the navigation between pages
 * <br/>
 * To be used you can get a reference by calling the <code>getInstance</code> method:
 * <code><pre>
 *   Router router = Router.getInstance();
 *   router.register("Menu", Menu::new);
 *   router.navigate("Menu");
 * </pre></code>
 *
 * @author Gabriele Mercolino
 * @version 1.0
 */
public class Router {
	private static Router instance;

	private JFrame frame;
	private final Map<String, Supplier<JPanel>> routes;
	private JPanel currentPanel;
	private final PropertyChangeSupport support;

	private Router() {
		frame = new DefaultFrame();
		routes = new HashMap<>();
		support = new PropertyChangeSupport(this);
	}

	public static Router getInstance() {
		if (instance == null) instance = new Router();
		return instance;
	}

	/**
	 * Change the default <code>JFrame</code> with a custom one
	 *
	 * @param frame The new JFrame
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Registers a new page with the identifier.
	 * <br/>
	 * For example, assuming <code>Page</code> a class that extends <code>JPanel</code>:
	 * <pre>
	 *   router.register("Page", Page::new);
	 * </pre>
	 *
	 * @param name        The identifier of the page
	 * @param constructor The constructor that will be called when needed
	 * @see JPanel
	 */
	public void register(String name, Supplier<JPanel> constructor) {
		routes.put(name, constructor);
	}

	public void navigate(String name) throws RouteNotFoundException {
		if (!routes.containsKey(name)) throw new RouteNotFoundException();

		JPanel newPanel = routes.get(name).get();
		JPanel oldPanel = currentPanel;

		setContent(frame, newPanel);

		currentPanel = newPanel;

		support.firePropertyChange(Events.CHANGED_PANEL.toString(), oldPanel, newPanel);
	}

	/**
	 * Sets the content of the Frame
	 *
	 * @param page the container to show
	 * @see Container
	 * @see JFrame
	 */
	public void setContent(JFrame frame, Container page) {
		SwingUtilities.invokeLater(
				() -> {
					frame.getContentPane().removeAll();
					frame.setContentPane(page);
					frame.revalidate();
					frame.setVisible(true);
					frame.getContentPane().requestFocusInWindow();
				});
	}

	public JPanel getCurrentPanel() {
		return currentPanel;
	}

	public void addListener(String event, PropertyChangeListener listener) {
		support.addPropertyChangeListener(event, listener);
	}

	public void removeListener(PropertyChangeListener l) {
		support.removePropertyChangeListener(l);
	}
}