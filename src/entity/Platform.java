package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import static run.Constants.Pics.*;
import static run.Constants.PiecesConsts.*;
/**
 * The class of the platform
 */
public class Platform extends GamePiece{
	/**
	 * Constructor
	 * @param x - Horizontal position of the piece
	 * @param y	- Vertical position of the piece
	 */
	public Platform(int x, int y) {
		super(x, y);
	}
	/**
	 * Paints the platform in a fix place
	 */
	public void paint(Graphics g) {
		g.drawImage(platformImg,x,y,null);
	}
	/**
	 * Paints the platform in the editor to visualize the place where we want to place it
	 */
	public void paint(Graphics g,int x,int y) {
		g.drawImage(platformImg,x-WALL_W/2,y-26 , null);
	}
	
	/**
	 * Sets the positions
	 */
	public void setValues(int x, int y) {
		this.x=x-WALL_W/2;
		this.y=y-26;
	}
	/**
	 * Gives back the hitbox of the platform
	 */
	public Rectangle getRectangle() {
		return (new Rectangle(x,y,WALL_W,WALL_H));
	}
	
	public boolean isStart() {
		return false;
	}
	public boolean isEnd() {
		return false;
	}
	public boolean isTrap() {
		return false;
	}
	public Rectangle getTrapRect() {
		return (new Rectangle(x,y,WALL_W,WALL_H));
	}
	
}
