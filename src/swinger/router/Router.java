package swinger.router;

import swinger.events.Event;
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
 * <br/>
 * Register a <code>PropertyChangeListener</code> to listen for events.
 * <code><pre>
 *   router.addListener(Events.CHANGED_PANEL, theListener);
 * </pre></code>
 *
 * @author Gabriele Mercolino
 * @version 1.0
 */
public class Router {
	/**
	 * Holds the only possible instance
	 */
	private static Router instance;
	/**
	 * The JFrame used
	 */
	private JFrame frame;
	/**
	 * Collects and pairs an identifier to the correspondent JPanel generator
	 */
	private final Map<String, Supplier<JPanel>> routes;
	/**
	 * References the current JPanel
	 */
	private JPanel currentPanel;
	/**
	 * Support for firing events
	 */
	private final PropertyChangeSupport support;

	/**
	 * Private constructor as for Singleton
	 */
	private Router() {
		frame = new DefaultFrame();
		routes = new HashMap<>();
		support = new PropertyChangeSupport(this);
	}

	/**
	 * Returns the only instance and creates it if not initialized
	 *
	 * @return instance
	 */
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

	/**
	 * Changes the page according to the identifier. <br/>
	 * If the identifier is incorrect throws a <code>RouteNotFoundException</code>.
	 * As a <code>RuntimeException</code> doesn't have to be used and will just happen nothing
	 *
	 * @param name The identifier
	 * @throws RouteNotFoundException If identifier incorrect
	 */
	public void navigate(String name) throws RouteNotFoundException {
		if (!routes.containsKey(name)) throw new RouteNotFoundException();

		JPanel newPanel = routes.get(name).get();
		JPanel oldPanel = currentPanel;

		setContent(frame, newPanel);

		currentPanel = newPanel;

		support.firePropertyChange(Event.CHANGED_PANEL.toString(), oldPanel, newPanel);
	}

	/**
	 * Sets the content of the Frame
	 *
	 * @param page the container to show
	 * @see Container
	 * @see JFrame
	 */
	private void setContent(JFrame frame, Container page) {
		SwingUtilities.invokeLater(
				() -> {
					frame.getContentPane().removeAll();
					frame.setContentPane(page);
					frame.revalidate();
					frame.setVisible(true);
					frame.getContentPane().requestFocusInWindow();
				});
	}

	/**
	 * @return currentPanel
	 */
	public JPanel getCurrentPanel() {
		return currentPanel;
	}

	/**
	 * Add a <code>PropertyChangeListener</code> to listen for <code>Events</code>
	 *
	 * @param listener The <code>PropertyChangeListener</code>
	 * @param events   The events that the listener should handle
	 */
	public void addListener(PropertyChangeListener listener, Event... events) {
		for (Event event : events)
			support.addPropertyChangeListener(event.toString(), listener);
	}

	/**
	 * Removes the listener
	 *
	 * @param listener The <code>PropertyChangedListener</code>
	 */
	public void removeListener(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}
}