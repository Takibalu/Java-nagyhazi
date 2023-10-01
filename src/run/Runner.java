package run;

import java.awt.Graphics;
import java.util.ArrayList;
import entity.*;
import options.*;
/**
 *Class that runs the program
 */
public class Runner {
	
	private	TopMenu topmenu;
	private	Panel panel;
	private Player matt;
	private Play play;
	private Menu menu;
	private Editor editor;
	private Window win;
	private Map map;
	private boolean running=true;

	
	/**
	 * Constructor, initializes and starts the gameloop
	 */
	public Runner()
	{
		init();
		win.setWindow();
		run();
	}
	/**
	 * Initializes, loads the map
	 */
	public void init() {
		editor=new Editor(this);
		topmenu=new TopMenu(this);
		panel=new Panel(this);
		win=new Window(panel,topmenu);
		menu=new Menu(this);
		ArrayList<GamePiece> pieces=editor.load("easy4");
		map=new Map(pieces);
		play=new Play(this);
		
	}

	/**
	 * Responsible for the gameloop
	 */
	public void run() {
		double timePF= 1000000000.0/120;
		long previous=System.nanoTime();
		long last=System.currentTimeMillis();
		int frames=0;
		double deltaFrame=0;
		while(running) {
			deltaFrame+=(System.nanoTime()-previous)/timePF;
			previous=System.nanoTime();
			
			if(deltaFrame>=1) {
				panel.repaint();
				update();
				frames++;
				deltaFrame--;
				
			}
			
			if(System.currentTimeMillis()-last>=1000) {
				System.out.println(frames);
				last=System.currentTimeMillis();
				frames=0;
			}
		}
		
	}

	/**
	 * Updates the screen according to the option 
	 */
	private void update() {
		
		switch(Options.option) {
		case MENU:
			menu.update();
			break;
		case PLAY:
			play.update();
			break;
		case EDITOR:
			editor.update();
			break;
		default:
			break;
			
			
		}
			
	}
	/**
	 * Paints the screen according to the option
	 * @param g - responsible for the graphics
	 */
	public void paint(Graphics g) {
		switch(Options.option) {
		case MENU:
			menu.paint(g);
			break;
		case PLAY:
			play.paint(g);
			break;
		case EDITOR:
			editor.paint(g);
			break;
		case QUIT:
			System.exit(0);
		default:
			break;
		}
	}
	
	/**
	 * If we click out of the window in the play, 
	 * this method stops the player-movement(which we control) 
	 */
	public void clickOut() {
		if(Options.option==Options.PLAY)
			play.clickOut();
	}
	
	public Panel getPanel() {
		return panel;
	}
	public Player getPlayer() {
		return matt;
	}
	public Menu getMenu() {
		return menu;
	}
	public Play getPlay() {	
		return play;
	}
	public Editor getEditor() {
		return editor;
	}
	public Map getMap() {
		return map;
	}
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
	public void setMap(Map map) {
		this.map = map;
	}
}
