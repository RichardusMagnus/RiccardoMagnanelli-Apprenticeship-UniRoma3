package arc;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
	}

	@Test
	public void testDeterminateCost_random() {
		
		this.arco.setStoc_costs(costi);
		
		this.arco.determinateCost_random();
		int x = this.arco.getCost();
		assertTrue(x>=1 && x<=3);
	}
	
	@Test
	public void testDeterminateCost_highest() {
		
		this.arco.setStoc_costs(costi);
		
		this.arco.determinateCost_highest();
		int x =(this.arco.getCost());
		assertTrue(x==3);
	}
	
	@Test
	public void testDeterminateCost_lowest() {
		
		this.arco.setStoc_costs(costi);
		
		this.arco.determinateCost_lowest();
		int x =(this.arco.getCost());
		assertTrue(x==1);
	}
	
	@Test
	public void testCalculateIndex() {
		this.arco.setStoc_costs(costi);
		int w = this.arco.calculateIndex(97);
		System.out.println("OLLLLEEEE");
		System.out.print(w);
	}
	
	

}
