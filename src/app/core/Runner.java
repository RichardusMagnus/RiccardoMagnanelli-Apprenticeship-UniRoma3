package app.core;

import java.io.IOException;
import java.util.List;

import app.arc.Arc;
import app.processing.*;

/**
 * Runs the main logic of the program
 * @author Riccardo Magnanelli
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
	StructureBuilder structureBuilder;

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

		int i=0;
		for (String bounds : this.allBounds) {
			this.GENERATE_STRUCTURES(this.structureBuilder, bounds);
			this.PRINT_STRUCTURES_ON_FILE(i);
			IOstream.println("Done " + bounds + "\nFile no: " + i);
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
		this.stationMatrix = InputExtractor.extractStationMatrix(abz5_filePath);

		this.m = this.stationMatrix.length;
		this.n = this.stationMatrix[0].length;

		List<Arc> listOfAllArcs = InputExtractor.extraxtArcs(this.abz5_completeGraph_filePath);
		this.structureBuilder = new StructureBuilder(listOfAllArcs, this.stationMatrix, this.n, this.m);
	}



	/**
	 * Builds the output to be printed on the text file.
	 * @param structureBuilder
	 * @param bounds
	 */
	public void GENERATE_STRUCTURES(StructureBuilder structureBuilder, String bounds) {
		structureBuilder.determinateAllCosts(bounds);
		this.activityMatrix = structureBuilder.buildActivityMatrix();
		this.releases = structureBuilder.buildReleaseArray();
		this.dueDates = structureBuilder.buildDueDatesArray();
		this.allMatrixes = structureBuilder.buildSetUpMatrixes();

	}


	/**
	 * Prints the structures previously created.
	 * @param i
	 */
	public void PRINT_STRUCTURES_ON_FILE(int i) {
		String outputFileName_definitive = this.outputFileName + i + ".txt";
		
		try {
			FilePrinter.printStructuresOnFile(outputFileName_definitive, this.activityMatrix, this.stationMatrix, this.releases, this.dueDates, this.allMatrixes, this.n, this.m);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
