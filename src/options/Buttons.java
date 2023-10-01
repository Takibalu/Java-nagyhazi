package options;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static run.Constants.ButtonSizes.*;
import static run.Constants.Pics.*;
/**
 * Class of the menu-buttons
 */
public class Buttons {
	
	private BufferedImage[] menuimgs;
	private Rectangle box;
	private int x,y,row,idx;
	private Options opt;
	private boolean over,press;
	/**
	 * Constructor, loads the image and makes the box of the button
	 * @param x - Horizontal position of the center of the button
	 * @param y	- Vertical position of the center of the button
	 * @param row - in the images which row is this button
	 * @param opt - when we push it, which option will execute
	 */
	public Buttons(int x, int y, int row, Options opt) {
		this.x = x;
		this.y = y;
		this.row = row;
		this.opt = opt;		
		load();
		boxMake();
	}

	/**
	 * Makes the box of the button 
	 */
	private void boxMake() {
		box=new Rectangle(x-WIDE/2,y,WIDE,TALL); 
		
	}
	/**
	 * Loads the images of the button
	 */
	private void load() {
		menuimgs= new BufferedImage[3];
		for(int i=0; i<3;i++) {
			if(row>=3)
				menuimgs[i]=playbuttonsImg.getSubimage(i*WIDE, (row-3)*TALL, WIDE, TALL);
			else 
				menuimgs[i]= menusImg.getSubimage(i*WIDE, row*TALL, WIDE, TALL);
		}
	}
	/**
	 * Resets the button when we dont interact with it
	 */
	public void reset() {
		over=false;
		press=false;
	}
	/**
	 * Paints the button according to the interaction 
	 */
	public void paint(Graphics g) {
		g.drawImage(menuimgs[idx], x-WIDE/2, y, WIDE, TALL, null);
	}
	
	/**
	 * Updates the button according to the interaction 
	 */
	public void update() {
		idx=0;
		if(over) idx=1;
		if(press) idx=2;
	}

	public boolean isOver() {
		return over;
	}
	public void setOver(boolean over) {
		this.over = over;
	}
	public boolean isPress() {
		return press;
	}
	public void setPress(boolean press) {
		this.press = press;
	}
	public Rectangle getBox() {
		return box;
	}
	public void setBox(Rectangle box) {
		this.box = box;
	}
	public void setOption() {
		Options.option=opt;
	}
	
}
