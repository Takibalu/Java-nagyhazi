package options;

import static run.Constants.Pics.*;
import java.awt.Graphics;
import java.awt.event.*;
import entity.*;
import run.*;
/**
 * Class of the Play option, implements the methods of the options
 */
public class Play implements OptionMethods{

	private Runner runner;
	private Map map;
	private Player matt;
	private int starty,startx=10;
	private Start start;
	private End end;
	private int winWait, deathWait=0;
	private Buttons menu=new Buttons(830,600,3,Options.MENU);
	private Buttons restart=new Buttons(430,600,4,Options.PLAY);
	
	/**
	 * Constructor, sets the map and the player to default
	 * @param runner - the running game
	 */
	public Play(Runner runner) {
		this.runner=runner;
		map=runner.getMap();
		for(int i=0;i<map.getPieces().size();i++) {
			if(map.getPieces().get(i).isStart())
			{
				start=(Start) map.getPieces().get(i);
				startx=start.getX();
				starty=start.getY();
			}
			if(map.getPieces().get(i).isEnd())
				end=(End) map.getPieces().get(i);
		}
		matt=new Player(startx,starty);
		matt.setMap(map);
		matt.init();
		
	}
	public Player getMatt() {
		return matt;
	}
	/**
	 * Stops the player if we click out of the window
	 */
	public void clickOut() {
		matt.stop();
	}
	/**
	 * Paints the play screen(map and player),
	 * checks if the player won or is dead,
	 * changes the animation of the start and end piece
	 */
	@Override
	public void paint(Graphics g) {
		if(matt.isWin())
		{
			if(winWait>15) {
			g.drawImage(winImg,0,150,null);
			menu.paint(g);
			restart.paint(g);
			}
			else
				winWait++;
		}
		else if(matt.isDeath())	
		{
			if(deathWait>70) {
				g.drawImage(deathImg, 0, 150, null);
				
				menu.paint(g);
				restart.paint(g);
			}
			else {
				deathWait++;
				map.paint(g);
				matt.paint(g);
			}
		}
		else{
		map.paint(g);
		start.changeAnimation();
		end.changeAnimation();
		matt.paint(g);
		}
		
	}
	/**
	 * Updates the screen(player), updates the buttons
	 */
	@Override
	public void update() {
		matt.update();
		if(matt.isWin()||matt.isDeath()) {
			menu.update();
			restart.update();
		}
		
		
	}
	/**
	 * If we press the movement buttons, this sets true the corresponding direction
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		
		case KeyEvent.VK_W:
			matt.setFel(true);
			break;
		case KeyEvent.VK_A: 
			matt.setBalra(true);
			break;
		case KeyEvent.VK_S: 
			matt.setLe(true);
			break;
		case KeyEvent.VK_D: 
			matt.setJobbra(true);
			break;
		}
		
	}
	/**
	 * If we release the movement buttons, this sets false the corresponding direction
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){

		case KeyEvent.VK_W:
			matt.setFel(false);
			break;
		case KeyEvent.VK_A: 
			matt.setBalra(false);
			break;
		case KeyEvent.VK_S: 
			matt.setLe(false);
			break;
		case KeyEvent.VK_D: 
			matt.setJobbra(false);
			break;
		}
		
	}
	/**
	 * In the death/win screen, this sets that button's state to over, which we moved the mouse on
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		
			menu.setOver(false);
			restart.setOver(false);
			if(isIn(e,restart))
				restart.setOver(true);
			if(isIn(e,menu))
				menu.setOver(true);
		
	}
	/**
	 * In the death/win screen, this sets that button's state to press, which we pressed
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(matt.isWin()||matt.isDeath()) {
			if(isIn(e,menu)) 
				menu.setPress(true);
			if(isIn(e,restart))
				restart.setPress(true);
		}
	}
	/**
	 * In the death/win screen, if we pressed(and released) a button, this sets the option of that button 
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		
		if(isIn(e,restart)) {
			if(restart.isPress()) {
				reset();
				restart.setOption();
			}
			runner.getPanel().requestFocusInWindow();
		}
		
		if(isIn(e,menu)) {
			if(menu.isPress()) {
				reset();
				menu.setOption();
			}
			runner.getPanel().requestFocusInWindow();
		}
		resetbuttons();
	}
	
	/**
	 * Resets the map and the player (for the restart)
	 */
	private void reset() {
		map=runner.getMap();
		for(int i=0;i<map.getPieces().size();i++) {
			if(map.getPieces().get(i).isStart())
			{
				start=(Start) map.getPieces().get(i);
				startx=start.getX();
				starty=start.getY();
			}
			if(map.getPieces().get(i).isEnd())
				end=(End) map.getPieces().get(i);
		}
		matt.reset(startx,starty,map);
		deathWait=0;
		winWait=0;
		
	}
	/**
	 * Resets the buttons
	 */
	public void resetbuttons() {
		menu.reset();
		restart.reset();
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
	 * Loads a map and sets it to play, resets the map
	 * @param name - the name of the file that we loads
	 */
	public void setMap(String name) {
		map.setPieces(runner.getEditor().load(name));
		runner.setMap(map);
		reset();
	}
}
