package run;

import java.awt.*;
import javax.swing.*;
import static run.Constants.Pics.*;

/**
 * Class that represent the panel
 */
public class Panel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private MouseInput minput=new MouseInput(this);
	private Runner runner;

	/**
	 * Constructor, sets size of the panel, adds the listeners
	 * @param runner - the running game
	 */
	public Panel(Runner runner) {
		this.runner=runner;
		setSizeOfPanel();
		addKeyListener(new KeyBoardInput(this));
		setFocusable(true);
		requestFocusInWindow();
		addMouseListener(minput);
		addMouseMotionListener(minput);
		
	}
	/**
	 * Sets size of panel
	 */
	public void setSizeOfPanel() {
		Dimension dim= new Dimension(1260,765);
		setMaximumSize(dim);
		setMinimumSize(dim);
		setPreferredSize(dim);
	/**
	 * Paints the Panel(background, game)	
	 */
	}
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImg, 0, 0, null);
		runner.paint(g);
	}
	public Runner getRunner() {
		return runner;
	}

}
