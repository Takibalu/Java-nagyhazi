package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import static run.Constants.Pics.*;
import static run.Constants.PiecesConsts.*;

/**
 * The class of the start-piece
 */
public class Start extends GamePiece {
	/**
	 * Constructor
	 * @param x - Horizontal position of the piece
	 * @param y	- Vertical position of the piece
	 */
	public Start(int x, int y) {
		super(x,y);
	}
	/**
	 * Paints the start-piece in a fix place, its animated
	 */
	public void paint(Graphics g) {
		if(timer/30<4)
		g.drawImage(startImg.getSubimage((int)(timer/30)*(TARDIS_W+2), 0, TARDIS_W, TARDIS_H),x,y,null);
	}
	/**
	 * Paints the start-piece in the editor to visualize the place where we want to place it
	 */
	public void paint(Graphics g,int x,int y) {
		g.drawImage(startImg.getSubimage(0, 0, TARDIS_W, TARDIS_H),x-27,y-TARDIS_H/2 , null);
	}
	/**
	 * Sets the positions
	 */
	public void setValues(int x, int y) {
		this.x=x-27;
		this.y=y-TARDIS_H/2;
		
	}
	
	/**
	 * Changes the animation of the start-piece
	 */
	public void changeAnimation() {
		if (timer/30<5)
		timer++;
	}
	/**
	 * Gives back the box of the start-piece
	 */
	public Rectangle getRectangle() {
		return (new Rectangle(x,y,TARDIS_W,TARDIS_H));
	}
	public boolean isStart() {
		return true;
	}
	public boolean isEnd() {
		return false;
	}
	public boolean isTrap() {
		return false;
	}
	
	public Rectangle getTrapRect() {
		return (new Rectangle(x,y,TARDIS_W,TARDIS_H));
	}
}
