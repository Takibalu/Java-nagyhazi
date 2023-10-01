package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
/**
 * Abstract class for all the gamepieces, implements Serializable because we have to save them with the Map
 */
public abstract class GamePiece implements Serializable{
	
	protected int timer=0;
	protected int x;
	protected int y;
	
	public GamePiece(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setTimer(int timer) {
		this.timer = timer;
	}
	public int getTimer() {
		return timer;
	}

	abstract public void paint(Graphics g);
	abstract public void paint(Graphics g, int x, int y);
	abstract public void setValues(int x, int y);
	abstract public boolean isStart();
	abstract public boolean isEnd();
	abstract public boolean isTrap();
	abstract public Rectangle getRectangle();
	abstract public Rectangle getTrapRect();
	


}
