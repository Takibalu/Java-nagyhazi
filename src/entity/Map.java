package entity;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * The class of the Map, that contains the pieces. We have to save it, so it is serializable
 */
public class Map implements Serializable {
	
	//all the pieces
	private ArrayList<GamePiece> pieces=new ArrayList<>();
	
	public Map(ArrayList<GamePiece> pieces) {
		this.pieces = pieces;
	}

	/**
	 * Paints all the pieces on the Map
	 * @param g
	 */
	public void paint(Graphics g){
		for(int i=0;i<pieces.size();i++)
			pieces.get(i).paint(g);
	}
	
	public ArrayList<GamePiece> getPieces() {
		return pieces;
	}
	public void setPieces(ArrayList<GamePiece> pieces) {
		this.pieces = pieces;
	}

	
	
	
	
	
}
