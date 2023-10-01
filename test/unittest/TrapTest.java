package unittest;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.Trap;

public class TrapTest {
	private Trap t;
	private Rectangle uj,trap;
	@Before
	public void constract() {
		t=new Trap(200,300);
	}
	@Test
	public void testSetValues() {
		t.setValues(300,200);
		Assert.assertEquals(t.getX(), 300-73);
		Assert.assertEquals(t.getY(), 200-26);
	}
	@Test
	public void testRect() {
		uj=t.getRectangle();
		Assert.assertEquals(t.getX(), (int)uj.getX());
		Assert.assertEquals(t.getY(), (int)uj.getY());
	}
	@Test
	public void testTrap() {
		trap=t.getTrapRect();
		Assert.assertEquals(t.getX()+35,(int) trap.getX());

		Assert.assertEquals(t.getY(),(int) trap.getY());
	}
}
