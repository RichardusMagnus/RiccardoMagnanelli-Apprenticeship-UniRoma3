package app.processing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FilePrinter {
	
	public static void printStructuresOnFile(String path, int[][] am, int[][] sm, int[] rl, int[] dd, int[][][] allMatrixes, int stations, int jobs) throws IOException {
		int[][] activityMatrix = am;
		int[][] stationMatrix = sm;
		int[] releases = rl;
		int[] dueDates = dd;
		int[][][] allSetUpMatrixes = allMatrixes;
		
		
		int n = stations;
		int m = jobs;
		
		
		
		
		
		File f = new File(path);
		if (f.exists()) {
			System.out.println("File already exhists: it will be overwritten.");
			f.delete();
			f.createNewFile();
		}
		else f.createNewFile();
		
		FileWriter fw = new FileWriter(f);
		
		/*graph dimensions*/
		fw.write(n + " " + m + "\n");
		
		/*activity matrix*/
		for (int job=m-1; job>=0; job--) {
			for(int act=0; act<n; act++) {
				fw.write(" " + stationMatrix[act][job]);
				fw.write(" " + activityMatrix[act][job]);
			}
			fw.write("\n");
		}
		fw.write("\n");
		
		/*releases and due dates*/
		for (int j=0; j<m; j++) {
			fw.write(" " + releases[m-j-1]);
		}
		fw.write("\n");
		for (int j=0; j<m; j++) {
			fw.write(" " + dueDates[m-j-1]);
		}
		fw.write("\n");
		fw.write("\n");
		
		/*all set up matrixes*/
		for (int station=0; station<n; station++) {
			int[][] stpMatrix = allSetUpMatrixes[station];
			for (int i=m-1; i>=0; i--) {
				for(int j=m-1; j>=0; j--) {
					fw.write(" " + stpMatrix[i][j]);
				}
				fw.write("\n");
			}
			fw.write("\n");
		}
		
		fw.close();
	}
	

}
