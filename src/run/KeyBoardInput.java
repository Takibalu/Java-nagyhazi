package run;

import java.awt.event.*;
import options.Options;
/**
 * Class that implements KeyListener, listens to the keyboard
 */
public class KeyBoardInput implements KeyListener{
	
	private Panel panel;

	public KeyBoardInput(Panel p) {
		panel=p;
	}
	/**
	 * If a key is pressed, this directs to the current option's method
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch(Options.option) {
		case MENU:
			panel.getRunner().getMenu().keyPressed(e);
			break;
		case PLAY:
			panel.getRunner().getPlay().keyPressed(e);
			break;
		case EDITOR:
			panel.getRunner().getEditor().keyPressed(e);
			break;
		default:
			break;
		}
	}
	/**
	 * If a key is released, this directs to the current option's method
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		switch(Options.option) {
		case MENU:
			panel.getRunner().getMenu().keyReleased(e);
			break;
		case PLAY:
			panel.getRunner().getPlay().keyReleased(e);
			break;
		case EDITOR:
			panel.getRunner().getEditor().keyReleased(e);
			break;
		default:
			break;
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
}