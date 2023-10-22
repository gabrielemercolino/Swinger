package swinger.router;

import java.awt.Container;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import swinger.events.Events;
import swinger.exceptions.RouteNotFoundException;
import swinger.frame.DefaultFrame;

/**
 * 
 * @author Gabriele Mercolino
 * @version 1.0
 */
public class Router{
  private static Router instance;

  private JFrame frame;
  private Map<String, Supplier<JPanel>> routes;
  private JPanel currentPanel;
  private PropertyChangeSupport support;

  private Router(){
    frame = new DefaultFrame();
    routes = new HashMap<>();
    support = new PropertyChangeSupport(this);
    support.firePropertyChange(Events.CHANGED_PANEL.toString(), instance, currentPanel);
  }
  
  public static Router getInstance() {
    if (instance == null) instance = new Router();
    return instance;
  }

  public void setFrame(JFrame frame) {
    this.frame = frame;
  }

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
   * @see Container
   * @see JFrame
   * @param page
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