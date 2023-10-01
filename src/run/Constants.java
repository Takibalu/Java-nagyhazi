package run;

import java.awt.image.BufferedImage;
import background.LoadImg;
/**
 * Class that stores all the constants
 */
public class Constants {
	/**
	 * Piecenames->int
	 */
	public static class Pieces{
		public static final int STARTP=0;
		public static final int ENDP=1;
		public static final int TRAPP=2;
		public static final int PLATFORMP=3;
		public static final int DELETE=4;
	}
	/**
	 * The size of the buttons
	 */
	public static class ButtonSizes{
		public static final int TALL=85;
		public static final int WIDE=219;
	}
	/**
	 * Constant sizes of the GamePieces
	 */
	public static class PiecesConsts{
		public static final int WALL_W=146;
		public static final int WALL_H=53;
		public static final int TARDIS_W=55;
		public static final int TARDIS_H=88;
	}
	/**
	 * Picture loads + some sizes
	 */
	public static class Pics{
		public static final int WINDOW_W=1260;
		public static final int WINDOW_H=765;
		
		public static final BufferedImage endImg=LoadImg.LoadImage("/end.png").getSubimage(1,1,227,88);
		public static final BufferedImage startImg=LoadImg.LoadImage("/start.png").getSubimage(1,1,227,88);
		public static final BufferedImage trapImg=LoadImg.LoadImage("/trap.png").getSubimage(7,5,146,53);
		public static final BufferedImage platformImg=LoadImg.LoadImage("/platform.png").getSubimage(7,5,146,53);
	
		public static final BufferedImage characterImg=LoadImg.LoadImage("/character.png");
		public static final BufferedImage menusImg=LoadImg.LoadImage("/menus.png");
		public static final BufferedImage playbuttonsImg=LoadImg.LoadImage("/playbuttons.png").getSubimage(0,0,657,170);
		public static final BufferedImage cimImg=LoadImg.LoadImage("/cim.png").getSubimage(0,100,WINDOW_W,300);
		public static final BufferedImage editmenuImg=LoadImg.LoadImage("/editmenu.png").getSubimage(0,0,160,210);
	
		public static final BufferedImage menubackgroundImg=LoadImg.LoadImage("/menubackground.png").getSubimage(0,0,WINDOW_W,WINDOW_H);
		public static final BufferedImage backgroundImg=LoadImg.LoadImage("/background.png").getSubimage(0,0,WINDOW_W,WINDOW_H);

		public static final BufferedImage winImg=LoadImg.LoadImage("/win.png").getSubimage(0,0,WINDOW_W,400);
		public static final BufferedImage deathImg=LoadImg.LoadImage("/deathscreen.png").getSubimage(0,0,WINDOW_W,400);
	}
	/**
	 * Constants of the Player 
	 */
	public static class PlayerConsts{
		public static final int SIZE_CH=90;
		public static final int HITBOXSTARTX=25;
		public static final int HITBOXSTARTY=20;
		public static final int HITBOXWIDTH=30;
		public static final int HITBOXHEIGHT=66;
		public static final int JUMP=9;
		public static final double GRAVITY=0.4;
		public static final double GRIP=0.8;
		public static final double SPEED_LIMIT_TOP=5;
		public static final double SPEED_LIMIT_BOTTOM=0.5;
	}
	
}
