package filereader;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import arc.Arc;
import filemanager.*;

public class DataParserTest {
	DataParser dp = new DataParser();

	@Test
	public void testExtraxtArcs() {
		List<Arc> tutti = dp.extraxtArcs();

		assertTrue(tutti.size()==12);

	}

	@Test
	public void testParse() {
		String s = "a 3 4 5 2 4 3 3 1 2 1";
		Arc output = dp.parseArcData(s);
		Arc expexted = new Arc(3, 4, null);
		assertEquals(expexted, output);

		ArrayList<Integer> ex = new ArrayList<Integer>();
		ex.add(2);
		ex.add(3);
		ex.add(4);
		ex.add(4);
		ex.add(4);
		ex.add(5);
		ex.add(5);

		assertEquals(ex, output.getStoc_costs());
	}

	@Test
	public void testDivide() {
		String s = "ABC DEF GHI J K L M";
		String[] els = dp.divide(s);
		assertTrue(els[0].equals("C"));
		assertTrue(els[1].equals("DEF"));
		assertTrue(els[2].equals("GHI"));
		assertFalse(els[3].equals(" "));

	}

	@Test
	public void testExtractStationMatrix() throws IOException {
		String path = "C:\\Users\\Kyky\\Desktop\\sparapani.txt";
		int n = 3;
		int m = 4;
		int[][] otpt = DataParser.extractStationMatrix(path);

		for(int a=0; a<otpt.length; a++) {
			for(int b=0; b<otpt[a].length; b++) {
				System.out.print(otpt[a][b]);
			}
			System.out.println("\n");
		}

		int[][] exp = {{1, 2, 3},{4, 5, 6},{7, 8, 9},{10, 11, 12}};
		
		assertArrayEquals(exp, otpt);
	}

}
