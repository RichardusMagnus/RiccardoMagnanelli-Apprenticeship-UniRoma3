package filemanager;

import java.io.*;
import java.util.*;
import arc.Arc;

public class DataParser {
	
	public static List<Arc> extraxtArcs(String sourcePath) {
		List<Arc> allArcs = new ArrayList<Arc>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(sourcePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                allArcs.add(parseArcData(line));
            }
        } catch (IOException e) {
        	System.out.println("Input file path not found or void.");
            e.printStackTrace();
        }
		
		
		return allArcs;		
	}

	public static Arc parseArcData(String arcRawData) {
		int ID_or;
		int ID_dest;
		ArrayList<Integer> stoc_costs = new ArrayList<Integer>();
		
		String[] rawDataPartitioned = divide(arcRawData);

		ID_or = Integer.parseInt(rawDataPartitioned[0]);
		ID_dest = Integer.parseInt(rawDataPartitioned[1]);
		
		for (int i = rawDataPartitioned.length-1; i >= 2; i--) {
			int occ = Integer.parseInt(rawDataPartitioned[i]);
			i--;
			for(int j=1; j<=occ; j++) {
				stoc_costs.add(Integer.parseInt(rawDataPartitioned[i]));
			}
		}
		
		Arc newArc = new Arc(ID_or, ID_dest, stoc_costs);
		
		return newArc;
	}
	
	public static String[] divide(String s) {
		String[] elements = s.substring(2).split(" ");//considera la stringa partire dal secondo numero, ignorando la a e lo spazio seguente
		return elements;
	}
	
	/**
	 * Creates a n*m matrix in which each job is represented by a row
	 * and every number is ID of the station that operates the
	 * correspondent activity of that job. It takes as input the path to the
	 * file that contains the same matrix.
	 * @return station matrix
	 */
	public static int[][] createStationMatrix(String path, int n, int m) {
		int[][] stationMatrix = new int[n][m];

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				String[] s = line.split(" ");
				for (int q=0; q<s.length; q++) {
					stationMatrix[q][i] = Integer.parseInt(s[q]);
				}
				i++;
			}
		} catch (IOException e) {
			System.out.println("Station file path not found or void.");
			e.printStackTrace();

		}

		return stationMatrix;
	}

}
