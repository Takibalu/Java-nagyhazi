package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import static run.Constants.Pics.*;
import static run.Constants.PlayerConsts.*;

public class Player{
	
	private Rectangle hitbox;
	private Map map;

	private BufferedImage[][] chAnimation=new BufferedImage[4][4];
	private boolean fel,le,jobbra,balra=false;
	private boolean guggol,death,win=false;	
	private boolean move=false;
	private int animationtick,animationindex,animationspeed;
	private int deathtick;
	private int x,y;
	private double speed,fallspeed;

	/**
	 * Player constructor, makes his hitbox, loads the animations
	 * @param x - horizontal pos
	 * @param y - vertical pos
	 */
	public Player(int x, int y) {
		this.x=x;
		this.y=y;
		animationLoad();
	}
	/**
	 * Initialize attributes of player
	 */
	public void init() {
		hitbox=new Rectangle(x+HITBOXSTARTX,y+HITBOXSTARTY,HITBOXWIDTH,HITBOXHEIGHT);
		stop();
		death=false;
		win=false;
		guggol=false;
		move=false;
		animationtick=20;
		animationindex=0;
		animationspeed=20;
		deathtick=0;
		speed=0;
		fallspeed=0;
		
		for(GamePiece pcs: map.getPieces()) {
			pcs.setTimer(0);
		}
	}

	/**
	 * Updates the player animation and movement, checks if the player is dead or won the game 
	 */
	public void update() {
		changeAnimation();
		death();
		win();
		if(!death)move();
		
	}
	/**
	 * Reset the player, if the game is restarted
	 * @param starty - vertical pos
	 * @param startx - horizontal pos
	 */
	public void reset(int startx, int starty,Map map) {
		this.map=map;
		this.x=startx;
		this.y=starty;
		init();
	}
	
	/**
	 * Checks whether the player won
	 */
	private void win() {
		for(GamePiece pcs: map.getPieces()) 
			if(pcs.isEnd() && pcs.getRectangle().intersects(hitbox)) 				
					win=true;
	}

	
	/**
	 * Checks whether the player is dead, if is makes him fly
	 */
	public void death() {
		if(y+SIZE_CH>=WINDOW_H) {	
			death=true;
		}
		hitbox.y++;
		for(GamePiece pcs: map.getPieces()) {
			if(pcs.isTrap() && pcs.getTrapRect().intersects(hitbox)) {
				death=true;
			}
		}
		hitbox.y--;
		
		if(death) y--;
	}

	/**
	 * Paints the player, sets its hitbox, different for different states
	 * @param g - Graphics element
	 */
	public void paint(Graphics g) {
		
		if (death) {
			if((int)(deathtick/animationspeed)<4)
			g.drawImage(chAnimation[(int)(deathtick/animationspeed)][3],x,y,null);
		
		}
		else if (guggol) {
			g.drawImage(chAnimation[animationindex][2],x,y,null);
			setHitbox(new Rectangle(x+HITBOXSTARTX,y+HITBOXSTARTY+40,HITBOXWIDTH,HITBOXHEIGHT-40));
		}else if(!move) {
			g.drawImage(chAnimation[animationindex][1],x,y,null);
			setHitbox(new Rectangle(x+HITBOXSTARTX,y+HITBOXSTARTY,HITBOXWIDTH,HITBOXHEIGHT));
		}else {
			g.drawImage(chAnimation[animationindex][0],x,y,null);
		setHitbox(new Rectangle(x+HITBOXSTARTX,y+HITBOXSTARTY,HITBOXWIDTH,HITBOXHEIGHT));
		}
		//Hitbox visualization
//		g.setColor(Color.RED);
//		paintHitbox(g,hitbox);
//		for(GamePiece pcs: map.getPieces())
//			paintHitbox(g,pcs.getRectangle());
	}
	
	//Hitbox visualization
//	public void paintHitbox(Graphics g, Rectangle hitbox) {
//		g.drawRect(hitbox.x, hitbox.y, hitbox.width,hitbox.height);
//	}
	
	
	/**
	 * Moves the player
	 */
	public void move() {
		//No animation of movement
		move=false;
		//Horizontal movement
		if(jobbra&&balra||!jobbra&&!balra) speed*=GRIP;
		else {
			speed+=((jobbra)?1:0)-((balra)?1:0);
			move=true;
		}
		
		//Slower, smooth movement
		if(speed>SPEED_LIMIT_TOP)speed=5;
		if(speed<-SPEED_LIMIT_TOP)speed=-5;
		if(speed>0 && speed<SPEED_LIMIT_BOTTOM) speed=0;
		if(speed<0 && speed>-SPEED_LIMIT_BOTTOM) speed=0;
	
		//Jump
		if(fel) {
			hitbox.y++;
			for(GamePiece pcs: map.getPieces()) {
				if(!pcs.isEnd() && !pcs.isStart())
					if(pcs.getRectangle().intersects(hitbox)) {
						fallspeed=-JUMP;
					}
			}
			hitbox.y--;
			move=true;
		}
		
		//Gravity
		fallspeed+=GRAVITY;

		//Crouch
		if(le) {
			guggol=true;
		}
		else {
			boolean nemtudfelallni=false;
			if(guggol) {
				hitbox.y-=40;
				for(GamePiece pcs: map.getPieces()) {			
					if(!pcs.isEnd() && !pcs.isStart())
						if(hitbox.intersects(pcs.getRectangle())) {
							nemtudfelallni=true;
						}
					}
				hitbox.y+=40;
			}
			if(!nemtudfelallni)
				guggol=false;
			
		}
		
		//Vertical check
		hitbox.y+=fallspeed;
		for(GamePiece pcs: map.getPieces()) {
			if(!pcs.isEnd() && !pcs.isStart())
				if(hitbox.intersects(pcs.getRectangle())) {
					hitbox.y-=fallspeed;
					hitbox.y-=((fallspeed>0)?1:0)-((fallspeed<0)?1:0);
					fallspeed=0;
				}
		}
		
		//Horizontal check
		hitbox.x+=speed;
		for(GamePiece pcs: map.getPieces()) {
			if(!pcs.isEnd() && !pcs.isStart())
				if(hitbox.intersects(pcs.getRectangle())) {
					hitbox.x-=speed;
					hitbox.x-=((speed>0)?2:0)-((speed<0)?2:0);
					speed=0;
				}
		}

		//Movement save
		x+=speed;
		y+=fallspeed;
	
		
	}
	
	
	/**
	 * Change the animation of different states
	 */
	public void changeAnimation() {
		if(!death){
			animationtick+=(move)?2:1;
			if(animationtick>=animationspeed) {
				animationtick=0;
				animationindex++;
				if(animationindex>=4) animationindex=0;
			}
		} else {
			deathtick++;
		}
	}
	
	/**
	 * Loads the animations
	 */
	private void animationLoad() {
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				chAnimation[i][j]=characterImg.getSubimage(1+i*(SIZE_CH+2),1+j*(SIZE_CH+2),SIZE_CH,SIZE_CH);
		
	}
	
	
	/**
	 * Stops the movement, for clicking out of the window and for init
	 */
	public void stop() {
		fel=false;
		le=false;
		jobbra=false;
		balra=false;
		
	}

/**
 * 	Getters/Setters
 */
	public void setFel(boolean fel) {
		this.fel = fel;
	}
	public void setLe(boolean le) {	
		this.le = le;
	}
	public void setJobbra(boolean jobbra) {
		this.jobbra = jobbra;
	}
	public void setBalra(boolean balra) {
		this.balra = balra;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public Rectangle getHitbox() {
		return hitbox;
	}
	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}
	public boolean isWin() {
		return win;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isDeath() {
		return death;
	}

	

}
