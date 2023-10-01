package unittest;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.End;

public class testEnd {
	private End e;
	private Rectangle uj;
	@Before
	public void constract() {
		e=new End(200,300);
	}
	@Test
	public void testSetValues() {
		e.setValues(300,200);
		Assert.assertEquals(e.getX(), 300-27);
		Assert.assertEquals(e.getY(), 200-44);
	}
	@Test
	public void testRect() {
		uj=e.getRectangle();
		Assert.assertEquals(e.getX(), (int)uj.getX());
		Assert.assertEquals(e.getY(), (int)uj.getY());
	}
	@Test
	public void testIfEnd() {
		Assert.assertTrue(e.isEnd());
	}
	@Test
	public void testAnimation() {
		for(int i=0;i<100;i++)
		e.changeAnimation();
		Assert.assertEquals(e.getTimer(), 90);
		
	}
}
