package run;

import java.awt.event.*;
import javax.swing.*;

/**
 * Represents the game window
 */
public class Window extends JFrame{
	
	private Panel panel;
	private TopMenu menu;

	public Window(Panel panel,TopMenu menu) {
		super("Platformer");
		this.panel=panel;
		this.menu=menu;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * Makes the window, adds the panel, watches if we click out of the window
	 */
	public void setWindow() {
		setJMenuBar(menu.getMenuBar());
		add(panel);
		pack();
		setResizable(false);
		setVisible(true);
		addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowGainedFocus(WindowEvent e) {
				System.out.println("Show time");
			}

			@Override
			public void windowLostFocus(WindowEvent e) {
				System.out.println("Ezt most miert csinaltad oregapam?");
				panel.getRunner().clickOut();
				
			}
			
		});
	}	
}