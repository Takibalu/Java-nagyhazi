package run;

import java.awt.event.*;
import options.Options;
/**
 * Class that implements MouseListener and MouseMotionListener, listens to the mouse
 */
public class MouseInput implements MouseListener, MouseMotionListener{

	private Panel panel;
	
	public MouseInput(Panel p) {
		panel=p;
	}
	/**
	 * If the mouse is moved, this directs to the current option's method
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		switch(Options.option) {
		case MENU: 
			panel.getRunner().getMenu().mouseMoved(e);
			break;
		case PLAY:
			panel.getRunner().getPlay().mouseMoved(e);
			break;
		case EDITOR:
			panel.getRunner().getEditor().mouseMoved(e);
			break;
		default:
			break;
		}
	}
	
	/**
	 * If the mouse is pressed, this directs to the current option's method
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		switch(Options.option) {
		case MENU: 
			panel.getRunner().getMenu().mousePressed(e);
			break;
		case PLAY:
			panel.getRunner().getPlay().mousePressed(e);
			break;
		case EDITOR:
			panel.getRunner().getEditor().mousePressed(e);
			break;
		default:
			break;
		}
		
	}
	/**
	 * If the mouse is released, this directs to the current option's method
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		switch(Options.option) {
		case MENU: 
			panel.getRunner().getMenu().mouseReleased(e);
			break;
		case PLAY:
			panel.getRunner().getPlay().mouseReleased(e);
			break;
		case EDITOR:
			panel.getRunner().getEditor().mouseReleased(e);
			break;
		default:
			break;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {	
	}
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}



	

}
