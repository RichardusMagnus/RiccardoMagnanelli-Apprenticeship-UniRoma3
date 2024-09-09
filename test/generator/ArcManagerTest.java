package generator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import arc.*;
import filemanager.DataParser;

import java.util.*;

public class ArcManagerTest {
	
	DataParser dp = new DataParser("C:\\Users\\Kyky\\Desktop\\grafo_origine.txt");
	List<Arc> archi = new ArrayList<Arc>();
	ArcManager am = null;
	
	
	@Before
	public void creaListaTest() {
		archi.addAll(dp.extraxtArcs());
		am = new ArcManager(archi, 4, 2);
	}
	
	@Test
	public void testClassifyAllArcs0() {
		
		System.out.println(am.getActivities().get(0).getID_or() + ", " + am.getActivities().get(0).getID_dest());
		System.out.println(am.getActivities().get(1).getID_or() + ", " + am.getActivities().get(1).getID_dest());
		System.out.println(am.getActivities().get(2).getID_or() + ", " + am.getActivities().get(2).getID_dest());
		System.out.println(am.getActivities().get(3).getID_or() + ", " + am.getActivities().get(3).getID_dest());
		assertEquals(4, am.getActivities().size());
	}
	
	@Test
	public void testClassifyAllArcs1() {
		assertEquals(2, am.getSetUps().size());
	}
	
	@Test
	public void testClassifyAllArcs2() {
		System.out.println(am.getReleases().get(0).getID_or() + ", " + am.getReleases().get(0).getID_dest());
		System.out.println(am.getReleases().get(1).getID_or() + ", " + am.getReleases().get(1).getID_dest());
		assertEquals(2, am.getReleases().size());
	}
	
	@Test
	public void testClassifyAllArcs3() {
		System.out.println(am.getDueDates().get(0).getID_or() + ", " + am.getDueDates().get(0).getID_dest());
		System.out.println(am.getDueDates().get(1).getID_or() + ", " + am.getDueDates().get(1).getID_dest());
		assertEquals(2, am.getDueDates().size());
	}

	@Test
	public void testCreateActivityMatrix() {
		am.determinateAllCosts("100:");
		int[][] mat = am.createActivityMatrix();
		System.out.println(mat[0][0] + " " + mat[0][1]);
		System.out.println(mat[1][0] + " " + mat[1][1]);
		
		assertTrue(true);
	}

	@Test
	public void testCalculate_j() {
		int x = am.calculate_job(10);
		int y = am.calculate_job(35);
		assertNotEquals(x, y);

		
	}

	@Test
	public void testCalculate_i() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCalculateID() {
		int x = am.calculate_ID(1, 1);
		assertEquals(5, x);
	}

	@Test
	public void testCreateSetUpMatrixes() {
		am.determinateAllCosts_highest();
		List<int[][]> listaMatrici = am.createSetUpMatrixes("C:\\Users\\Kyky\\Desktop\\macchinetest.txt");
		
		int[][] matrice0 = listaMatrici.get(0);
		int[][] matrice1 = listaMatrici.get(1);
		
		System.out.println("MARAMEO");
		System.out.println(matrice0[0][0] + " " + matrice0[0][1]);
		System.out.println(matrice0[1][0] + " " + matrice0[1][1]);
		System.out.println("MARAMEO");
		System.out.println("MARAMEO");
		System.out.println(matrice1[0][0] + " " + matrice1[0][1]);
		System.out.println(matrice1[1][0] + " " + matrice1[1][1]);
		System.out.println("MARAMEO");
		
		
		assertTrue(true);
	}

	
	
	@Test
	public void testcreateStationMatrix() {
		int[][] matrice = {{1,2, 3, 4},{2,3, 1, 4}};
		assertArrayEquals(matrice, am.createStationMatrix("C:\\Users\\Kyky\\Desktop\\macchinetest.txt"));
	}
	

}
