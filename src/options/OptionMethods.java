package options;

import java.awt.Graphics;
import java.awt.event.*;
/**
 * Interface for the Options
 */
public interface OptionMethods{
	public void paint(Graphics g);
	public void update();
	public void keyPressed(KeyEvent e);
	public void keyReleased(KeyEvent e);
	public void mouseMoved(MouseEvent e);
	public void mousePressed(MouseEvent e);
	public void mouseReleased(MouseEvent e);
}
