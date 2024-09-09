package generator;

import java.io.IOException;
import java.util.List;

import javax.xml.crypto.Data;

import arc.Arc;
import filemanager.DataParser;
import filemanager.FileGenerator;

/**
 * Runs the main logic of the program
 * @author ricca
 *
 */
public class Runner {

	/*input parameters*/
	int n;
	int m;
	String abz5_completeGraph_filePath;
	String abz5_filePath;
	String outputFileName;
	String[] allBounds;

	/*main operator*/
	ArcManager structureBuilder;

	/*output parameters*/
	int[][] activityMatrix;
	int[][] stationMatrix;
	int[] releases;
	int[] dueDates;
	int[][][] allMatrixes;


	public Runner(String abz5_cg_path, String abz5_filePath, String allBounds, String name) {
		this.abz5_completeGraph_filePath = abz5_cg_path;
		this.abz5_filePath = abz5_filePath;
		this.allBounds = allBounds.trim().split("\\.");
		this.outputFileName = "abz5_" + name + "_";
	}
	
	
	


	/**
	 * Core of the program: after the first set up, it creates different files.
	 * @throws IOException
	 */
	public void RUN_MAIN_LOGIC() throws IOException {
		
		this.SET_UP();
		IOstream.println("sono di nuovo in main logic.");

		int i=0;
		for (String bounds : this.allBounds) {
			IOstream.println("Sono nel for");
			this.GENERATE_STRUCTURES(this.structureBuilder, bounds);
			this.PRINT_STRUCTURES_ON_FILE(i);
			i++;
		}
		
	}
	
	
	
	
	

	/*LOGICAL STEPS*/ 
	
	/**
	 * Sets once and for all main input parameters (n, m), creates a station
	 * matrix and reads all the arcs.
	 * @throws IOException
	 */
	public void SET_UP() throws IOException {
		IOstream.println("SETTING UP...");
		this.stationMatrix = DataParser.extractStationMatrix(abz5_filePath);

		this.m = this.stationMatrix.length;
		this.n = this.stationMatrix[0].length;

		List<Arc> listOfAllArcs = DataParser.extraxtArcs(this.abz5_completeGraph_filePath);
		this.structureBuilder = new ArcManager(listOfAllArcs, this.stationMatrix, this.n, this.m);
		IOstream.println("Set up done.");
	}



	/**
	 * Builds the output to be printed on the text file.
	 * @param structureBuilder
	 * @param bounds
	 */
	public void GENERATE_STRUCTURES(ArcManager structureBuilder, String bounds) {

		IOstream.println("GENERATING...");
		structureBuilder.determinateAllCosts(bounds);
		this.activityMatrix = structureBuilder.createActivityMatrix();
		this.releases = structureBuilder.createReleaseArray();
		this.dueDates = structureBuilder.createDueDatesArray();
		this.allMatrixes = structureBuilder.createSetUpMatrixes();

	}


	/**
	 * Prints the structures previously created.
	 * @param i
	 */
	public void PRINT_STRUCTURES_ON_FILE(int i) {
				
		IOstream.println("PRINTING...");
		String outputFileName_definitive = this.outputFileName + i + ".txt";
		
		FileGenerator fg = new FileGenerator(this.activityMatrix, this.stationMatrix, this.releases, this.dueDates, this.allMatrixes, this.n, this.m);
		try {
			fg.generateFile(outputFileName_definitive);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
