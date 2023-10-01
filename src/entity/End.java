package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import options.Options;
import static run.Constants.Pics.*;
import static run.Constants.PiecesConsts.*;

/**
 * The class of the end-piece
 */
public class End extends GamePiece {
	/**
	 * Constructor
	 * @param x - Horizontal position of the piece
	 * @param y	- Vertical position of the piece
	 */
	public End(int x, int y) {
		super(x,y);
	}
	/**
	 * Paints the end-piece, in the editor is fixed, but in the play, its animated
	 */
	public void paint(Graphics g) {
		if(Options.option==Options.EDITOR)
			g.drawImage(endImg.getSubimage(3*TARDIS_W+6, 0, TARDIS_W, TARDIS_H),x,y , null);
		else
		g.drawImage(endImg.getSubimage((int)(timer/30)*(TARDIS_W+2), 0, TARDIS_W, TARDIS_H),x,y,null);
	}
	
	/**
	 * Paints the end-piece in the editor to visualize the place where we want to place it
	 */
	public void paint(Graphics g,int x,int y) {
		g.drawImage(endImg.getSubimage(3*TARDIS_W+6, 0, TARDIS_W, TARDIS_H),x-27,y-TARDIS_H/2 , null);
	}
	/**
	 * Sets the positions
	 */
	public void setValues(int x, int y) {
		this.x=x-27;
		this.y=y-TARDIS_H/2;
		
	}
	
	/**
	 * Changes the animation of the end-piece
	 */
	public void changeAnimation() {
		if (timer/30<3) timer++;
	}
	/**
	 * Gives back the box of the end-piece
	 */
	public Rectangle getRectangle() {
		return (new Rectangle(x,y,TARDIS_W,TARDIS_H));
	}
	
	public boolean isEnd() {
		return true;
	}
	public boolean isStart() {
		return false;
	}
	public boolean isTrap() {
		return false;
	}
	public Rectangle getTrapRect() {
		return (new Rectangle(x,y,TARDIS_W,TARDIS_H));
	}

}