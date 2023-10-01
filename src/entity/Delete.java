package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * This class is existing for the editor, to delete pieces from the map, it extends GamePiece, because its easier to use this way
 */
public class Delete extends GamePiece {
	/**
	 * Constructor
	 * @param x - Horizontal position of the center of the delete-block
	 * @param y	- Vertical position of the center of the delete-block
	 */
	public Delete(int x, int y) {
		super(x, y);
	}
	/**
	 * Paints the delete-block in the editor 
	 */
	@Override
	public void paint(Graphics g, int x, int y) {
		g.setColor(Color.RED);
		g.drawLine(x-30, y-30, x+30, y+30);
		g.drawLine(x+30, y-30, x-30, y+30);
		g.drawRect(getRectangle().x, getRectangle().y,getRectangle().width,getRectangle().height);
	}
	/**
	 * Sets the positions
	 */
	@Override
	public void setValues(int x, int y) {
		this.x=x;
		this.y=y;
	}

	/**
	 * Gives back the box of the delete-block
	 */
	@Override
	public Rectangle getRectangle() {
		return (new Rectangle(x-30,y-30,60,60));
	}

	/**
	 * Not used, but have to exist
	 */
	@Override
	public Rectangle getTrapRect() {
		return (new Rectangle(x-30,y-30,60,60));
	}
	@Override
	public void paint(Graphics g) {
	}
	@Override
	public boolean isStart() {
		return false;
	}
	@Override
	public boolean isEnd() {
		return false;
	}
	@Override
	public boolean isTrap() {
		return false;
	}
}
