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
	 * Creates a station sequence for each job.
	 * @return station matrix
	 */
	public static int[][] extractStationMatrix(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));

		//gets dimensions
		String[] dimensions = reader.readLine().trim().split(" ");
		int stations = Integer.parseInt(dimensions[0]);
		int jobs = Integer.parseInt(dimensions[1]);

		//creates matrix
		int[][] matrix = new int[stations][jobs];

		String line;
		int currentJob = jobs-1;

		while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
			int[] newJobSeq = extractJobSequence(line);

			for (int step = 0; step<stations; step++)
				matrix[step][currentJob] = newJobSeq[step];
			
			currentJob--;
		}

		reader.close();
		return matrix;


	}



	public static int[] extractJobSequence(String job) {

		String[] all = job.trim().split(" ");
		int[] jobSequence = new int[all.length/2];
		int ordinativo = 0;
		for(int i=0; i<all.length; i+=2) {
			jobSequence[ordinativo] = Integer.parseInt(all[i]);
			ordinativo++;
		}
		return jobSequence;
	}

}
