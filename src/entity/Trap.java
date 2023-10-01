package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import static run.Constants.Pics.*;
import static run.Constants.PiecesConsts.*;
/**
 * The class of the trap
 */
public class Trap extends GamePiece {
	/**
	 * Constructor
	 * @param x - Horizontal position of the piece
	 * @param y	- Vertical position of the piece
	 */
	public Trap(int x, int y) {
		super(x,y);
	}
	/**
	 * Paints the trap in a fix place
	 */
	public void paint(Graphics g) {
		g.drawImage(trapImg,x,y,null);
	}
	/**
	 * Paints the trap in the editor to visualize the place where we want to place it
	 */
	public void paint(Graphics g,int x,int y) {
		g.drawImage(trapImg,x-WALL_W/2,y-26 , null);
	}
	/**
	 * Sets the positions
	 */
	public void setValues(int x, int y) {
		this.x=x-WALL_W/2;
		this.y=y-26;
		
	}
	/**
	 * Gives back the hitbox of the whole trap
	 */
	public Rectangle getRectangle() {
		return (new Rectangle(x,y,WALL_W,WALL_H));
	}
	/**
	 * Gives back the hitbox of the area of the trap which is deadly
	 */
	public Rectangle getTrapRect() {
		return(new Rectangle(x+35,y,77,20));
	}
	public boolean isTrap() {
		return true;
	}
	public boolean isStart() {
		return false;
	}
	public boolean isEnd() {
		return false;
	}
	
	
}
