package options;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import run.Runner;
import static run.Constants.Pics.*;

/**
 * Class of the Main-menu, implements the methods of the options
 */
public class Menu implements OptionMethods {
	
	private Buttons[] buttons=new Buttons[3];
	private Runner runner;
	/**
	 * Constructor, loads the buttons
	 * @param runner - the running game
	 */
	public Menu(Runner runner) {
		this.runner=runner;
		buttons[0]=new Buttons(630,400,0,Options.PLAY);
		buttons[1]=new Buttons(630,500,1,Options.EDITOR);
		buttons[2]=new Buttons(630,600,2,Options.QUIT);	
	}

	/**
	 * Paints the menu screen(background,title,buttons)
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(menubackgroundImg, 0, 0, null);
		g.drawImage(cimImg, 0, 0, null);
		for(Buttons b: buttons)
			b.paint(g);
	}

	/**
	 * Updates the buttons
	 */
	@Override
	public void update() {
		for(Buttons b: buttons)
			b.update();
	}

	/**
	 * Resets the buttons
	 */
	private void reset() {
		for(Buttons b: buttons) {
			b.reset();
		}
	}
	/**
	 * If we moved the mouse over a button, this sets that buttons state to over 
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		for(Buttons b: buttons)
			b.setOver(false);
		for(Buttons b: buttons)
			if(isIn(e,b)) {
				b.setOver(true);
				break;
			}
	}

	/**
	 * If we moved the mouse over a button, this sets that buttons state to press 
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		for(Buttons b: buttons)
			if(isIn(e,b)) {
				b.setPress(true);
				break;
			}
	}
	/**
	 * Checks whether we are inside a buttons hitbox
	 * @param e - our mouse
	 * @param b - the button we are checking
	 * @return true if we are inside, else it is false
	 */
	private boolean isIn(MouseEvent e, Buttons b) {
		
		return b.getBox().contains(e.getX(),e.getY());
	}

	/**
	 * If we pressed a button(pressed and released), this sets the option accordingly, resets the buttons
	 * (if we pressed play, we have to get a file-chooser, that sets the map)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		for(Buttons b: buttons)
		{
			if(isIn(e,b)) {
				if(b.isPress()) {
					
					if(b==buttons[0]) {
						File selectedFile=new File("easy4");
						JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getParentDirectory(selectedFile));
						int returnValue = jfc.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) 
							selectedFile = jfc.getSelectedFile();
						runner.getPlay().setMap(selectedFile.getName());
						}
					b.setOption();
				}
				runner.getPanel().requestFocusInWindow();
				break;
			}
		}
		reset();
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
