package filemanager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileGenerator {
	int[][] activityMatrix = null;
	int[][] stationMatrix = null;
	int[] releases = null;
	int[] dueDates = null;
	int[][][] allSetUpMatrixes = null;
	
	
	int n;
	int m;
	
	public FileGenerator(int[][] am, int[][] sm, int[] rl, int[] dd, int[][][] allMatrixes, int n, int m) {
		this.activityMatrix = am;
		this.stationMatrix = sm;
		this.releases = rl;
		this.dueDates = dd;
		this.allSetUpMatrixes = allMatrixes;
		
		this.n = n;
		this.m = m;
		
	}
	
	public void generateFile(String path) throws IOException {
		File f = new File(path);
		if (f.exists()) {
			System.out.println("Il file esiste già e verrà sovrascritto.");
			f.delete();
			f.createNewFile();
		}
		else f.createNewFile();
		
		FileWriter fw = new FileWriter(f);
		
		/*dimensione problema*/
		fw.write(n + " " + m + "\n");
		
		/*scrittura matrice 2n*m delle attività*/
		for (int job=m-1; job>=0; job--) {
			for(int act=0; act<n; act++) {
				fw.write(" " + stationMatrix[act][job]);
				fw.write(" " + activityMatrix[act][job]);
			}
			fw.write("\n");
		}
		fw.write("\n");
		
		/*scrittura costi delle release e dei due_dates*/
		for (int j=0; j<m; j++) {
			fw.write(" " + releases[m-j-1]);
		}
		fw.write("\n");
		for (int j=0; j<m; j++) {
			fw.write(" " + dueDates[m-j-1]);
		}
		fw.write("\n");
		fw.write("\n");
		
		/*scrittura di tutte le matrici dei set up*/
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
