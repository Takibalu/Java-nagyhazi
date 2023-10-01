package options;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import entity.*;
import run.Runner;
import static run.Constants.Pics.*;
import static run.Constants.Pieces.*;


/**
 * Class of the Editor option, implements the methods of the options
 */
public class Editor implements OptionMethods{

	private Runner runner;
	private int x,y;
	private GamePiece piece;
	private GamePiece deleteOne;
	private static ArrayList<GamePiece> pieces=new ArrayList<>();
	private	static Map map=new Map(pieces);
	private Rectangle[] opts=new Rectangle[5];
	private int chosen=-1;
	/**
	 * Constructor, makes the hitboxes of the piece-chooser
	 * @param runner - the running game
	 */
	public Editor(Runner runner) {
		this.runner=runner;
		opts[0]=new Rectangle(WINDOW_W-160,WINDOW_H-210,160,60);
		opts[1]=new Rectangle(WINDOW_W-160,WINDOW_H-210+60,160,60);
		opts[2]=new Rectangle(WINDOW_W-160,WINDOW_H-210+120,57,90);
		opts[3]=new Rectangle(WINDOW_W-160+103,WINDOW_H-210+120,57,90);
		opts[4]=new Rectangle(WINDOW_W-160+57,WINDOW_H-210+120,46,90);
		
	}
	/**
	 *Paints the editor screen(piece-chooser,map,the piece that we want to place) 
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(editmenuImg,1260-160,765-210,null);
		map.paint(g);
		if(chosen!=-1)
		piece.paint(g,x,y);
	}
	/**
	 * If mouse is moved its sets the values of the piece we want to place to the cursor
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		x=e.getX();
		y=e.getY();
		if(chosen!=-1) piece.setValues(x,y);
	}
	/**
	 * Puts the piece, if its an empty space, makes a new piece
	 */
	private void put() {
		boolean nincsOtt=true;
		for(GamePiece pcs:pieces){
			if(pcs.getRectangle().intersects(piece.getRectangle()))
				nincsOtt=false;
		}
		if(nincsOtt) {
			pieces.add(piece);
			map.setPieces(pieces);
				switch(chosen) {
				case STARTP:
					piece=new Start(x,y);
					break;
				case ENDP:
					piece=new End(x,y);
					break;
				case TRAPP:
					piece=new Trap(x,y);
					break;
				case PLATFORMP:
				default:
					piece=new Platform(x,y);
					break;
				}
		}
	}
	
	/**
	 * If we chose a different piece, this construct it and sets which piece we choose 
	 * @param pie - the piece that we choose
	 */
	private void chosen(int pie) {
		if(pie==STARTP && chosen!=STARTP) {
			piece=new Start(0,0);
			chosen=STARTP;
		} else if(pie==ENDP && chosen!=ENDP) {
			piece=new End(0,0);
			chosen=ENDP;
		} else if(pie==TRAPP && chosen!=TRAPP) {
			piece=new Trap(0,0);
			chosen=TRAPP;
		} else if(pie==PLATFORMP && chosen!=PLATFORMP) {
			piece=new Platform(0,0);
			chosen=PLATFORMP;
		} else if(pie==DELETE && chosen!=DELETE) {
			piece=new Delete(0,0);
			chosen=DELETE;
		} 
		
	}
	/**
	 * Saves the pieces of the map to a file
	 * @param file - the name of the file in which it is saved
	 */
	public void save(String file) {
		try {
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(pieces);
			out.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * Loads the pieces of a map from a file
	 * @param file - the name of the file
	 * @return all the pieces in the map
	 */
	public ArrayList<GamePiece> load(String file) {
		ArrayList<GamePiece> pcs=new ArrayList<>();
		try {
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
			pcs=(ArrayList<GamePiece>)in.readObject();
			in.close();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}
		return pcs;
	}

	/**
	 * Sets the chosen piece if we clicked on the piece chooser or puts/deletes a piece to the map
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(opts[0].contains(e.getX(),e.getY())){
			chosen(PLATFORMP);
		} else if(opts[1].contains(e.getX(),e.getY())){
			chosen(TRAPP);
		}else if(opts[2].contains(e.getX(),e.getY())){
			chosen(STARTP);
		}else if(opts[3].contains(e.getX(),e.getY())){
			chosen(ENDP);
		} else if(opts[4].contains(e.getX(),e.getY())){
			chosen(DELETE);
		}
		else if(chosen!=-1 && chosen!=DELETE) {
			put();
		} else delete(e);
		
	}
	/**
	 * Deletes the piece, that we have clicked on
	 * @param e
	 */
	private void delete(MouseEvent e) {
		for(GamePiece pcs:pieces)
		{
			if(pcs.getRectangle().intersects(piece.getRectangle()))
				deleteOne=pcs;
		}
		
		pieces.remove(deleteOne);
		map.setPieces(pieces);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void update() {	
	}
	@Override
	public void keyPressed(KeyEvent e) {	
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}



	
}
