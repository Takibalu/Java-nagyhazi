package unittest;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.*;

public class Playertest {
	private Player p;
	private Map map;
	private ArrayList<GamePiece> pieces=new ArrayList<>();
@Before
public void construct() {		
	p=new Player(200,200);
	Platform pf=new Platform(500,300); 
	pieces.add(pf);
	map=new Map(pieces);
	p.setMap(map);
	p.init();
}
	
@Test
public void testHitbox() {
	Assert.assertEquals(p.getHitbox().x,225);
	Assert.assertEquals(p.getHitbox().y,220);
	Assert.assertEquals(p.getHitbox().width, 30);
	Assert.assertEquals(p.getHitbox().height, 66);
}
@Test
public void testDeath() {
	p.setY(p.getY()+500);
	p.death();
	Assert.assertTrue(p.isDeath());
}
@Test
public void testMovement() {
	p.stop();
	double sumoffallspeed=0;
	int sumoffall=0;
	for(int i=0;i<20;i++) {sumoffallspeed+=0.4; p.move();sumoffall+=sumoffallspeed;}
	Assert.assertEquals((int)(p.getY()),(int)(200+sumoffall));
}


}
