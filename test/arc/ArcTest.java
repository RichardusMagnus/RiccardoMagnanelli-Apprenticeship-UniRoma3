package arc;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class ArcTest {
	public Arc arco = new Arc(0, 0, null);
	ArrayList<Integer> costi = new ArrayList<Integer>();
	
	@Before
	public void setup() {
		
		costi.add(1);
		costi.add(2);
		costi.add(2);
		costi.add(3);
		

		
		System.out.println(costi.get(0));
		System.out.println(costi.get(1));
		System.out.println(costi.get(2));
		System.out.println(costi.get(3));
		
		arco.setStoc_costs(costi);
	}

	

	
	@Test
	public void testExtractBounds() {
		String range = "25:75";
		int expt[] = {25,75};
		int otpt[] = arco.extractBounds(range);
		
		assertArrayEquals(expt, otpt);
	}
	
	@Test
	public void testExtractBounds0x() {
		String range = ":75";
		int expt[] = {0,75};
		int otpt[] = arco.extractBounds(range);
		
		assertArrayEquals(expt, otpt);
	}
	
	@Test
	public void testExtractBoundsx100() {
		String range = "25:100";
		int expt[] = {25,100};
		int otpt[] = arco.extractBounds(range);
		
		assertArrayEquals(expt, otpt);
	}
	
	@Test
	public void testExtractBounds0100() {
		String range = ":";
		int expt[] = {0,100};
		int otpt[] = arco.extractBounds(range);
		
		assertArrayEquals(expt, otpt);
	}
	
	
	
	@Test
	public void testDeterminateCost() {
		arco.determinateCost("100:");
		assertEquals(3, arco.getCost());
	}
	

}
