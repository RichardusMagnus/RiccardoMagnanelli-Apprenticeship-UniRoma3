package generator;

import java.io.IOException;
import java.util.*;

import arc.Arc;
import filemanager.DataParser;
import filemanager.FileGenerator;


/******************* ASSUNZIONI *********************/
/* ogni job usa tutte le macchine una e una sola volta
 * 
 */



public class Main {
	private static int n = 10;
	private static int m = 10;

	public static void main(String[] args) throws IOException {
		System.out.println("Prima viene letto il file, poi si possono generare diversi dati deterministici.");
		
		String fileName = "C:\\Users\\Kyky\\Desktop\\abz5.txt_complete_graph.txt";
		DataParser rawDataReader = new DataParser(fileName);
		List<Arc> listOfAllArcs = new ArrayList<Arc>();
		
		listOfAllArcs = rawDataReader.extraxtArcs();
		
		ArcManager structureMaker = new ArcManager(listOfAllArcs, n, m);
		
		generaFileRandom(structureMaker);
		generaFileBasso(structureMaker);
		generaFileAlto(structureMaker);
			
	}
	
	public static void generaFileRandom(ArcManager structureMaker) throws IOException {
		structureMaker.determinateAllCosts_random();
		int[][] stationMatrix = structureMaker.createStationMatrix("C:\\Users\\Kyky\\Desktop\\macchine.txt");
		int[][] activityMatrix = structureMaker.createActivityMatrix();
		int[] releases = structureMaker.createReleaseArray();
		int[] dueDates = structureMaker.createDueDatesArray();
		int[][][] allMatrixes = structureMaker.createSetUpMatrixes("C:\\Users\\Kyky\\Desktop\\macchine.txt");
		
		FileGenerator fg = new FileGenerator(activityMatrix, stationMatrix, releases, dueDates, allMatrixes, n, m);
		fg.generateFile("NUOVOFILE_random.txt");	
	}
	
	public static void generaFileAlto(ArcManager structureMaker) throws IOException {
		structureMaker.determinateAllCosts_highest();
		int[][] stationMatrix = structureMaker.createStationMatrix("C:\\Users\\Kyky\\Desktop\\macchine.txt");
		int[][] activityMatrix = structureMaker.createActivityMatrix();
		int[] releases = structureMaker.createReleaseArray();
		int[] dueDates = structureMaker.createDueDatesArray();
		int[][][] allMatrixes = structureMaker.createSetUpMatrixes("C:\\Users\\Kyky\\Desktop\\macchine.txt");
		
		FileGenerator fg = new FileGenerator(activityMatrix, stationMatrix, releases, dueDates, allMatrixes, n, m);
		fg.generateFile("NUOVOFILE_alto.txt");	
	}
	
	public static void generaFileBasso(ArcManager structureMaker) throws IOException {
		structureMaker.determinateAllCosts_lowest();
		int[][] stationMatrix = structureMaker.createStationMatrix("C:\\Users\\Kyky\\Desktop\\macchine.txt");
		int[][] activityMatrix = structureMaker.createActivityMatrix();
		int[] releases = structureMaker.createReleaseArray();
		int[] dueDates = structureMaker.createDueDatesArray();
		int[][][] allMatrixes = structureMaker.createSetUpMatrixes("C:\\Users\\Kyky\\Desktop\\macchine.txt");
		
		FileGenerator fg = new FileGenerator(activityMatrix, stationMatrix, releases, dueDates, allMatrixes, n, m);
		fg.generateFile("NUOVOFILE_basso.txt");	
	}

}
