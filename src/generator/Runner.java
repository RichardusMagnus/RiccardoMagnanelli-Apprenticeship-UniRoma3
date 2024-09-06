package generator;

import java.io.IOException;
import java.util.List;

import arc.Arc;
import filemanager.DataParser;
import filemanager.FileGenerator;

/**
 * Runs the main logic of the program
 * @author ricca
 *
 */
public class Runner {

	/*input parametres*/
	int n;
	int m;
	String graphFilePath;
	String stationFilePath;
	String outputFileName;
	int choice;

	/*output parametres*/
	String outputFileName_definitive;
	int[][] activityMatrix;
	int[][] stationMatrix;
	int[] releases;
	int[] dueDates;
	int[][][] allMatrixes;


	public Runner(int n, int m, String graphFilePath, String stationFilePath, String outputFileName, int choice) {
		this.n = n;
		this.m = m;
		this.graphFilePath = graphFilePath;
		this.stationFilePath = stationFilePath;
		this.outputFileName = outputFileName;
		this.choice = choice;
	}
	
	
	public void RUN_MAIN_LOGIC() {
		List<Arc> listOfAllArcs = DataParser.extraxtArcs(graphFilePath);
		this.stationMatrix = DataParser.createStationMatrix(stationFilePath, n, m);
		ArcManager arcManager = new ArcManager(listOfAllArcs, stationMatrix, n, m);
		
		this.CHOOSE_MODE(arcManager);
		this.GENERATE_STRUCTURES(arcManager);
		this.PRINT_STRUCTURES_ON_FILE();
	}
	
	
	public void CHOOSE_MODE(ArcManager arcManager) {
		switch (this.choice) {
		case 1 : {
			this.setOutputFileName_definitive("random");
			arcManager.determinateAllCosts_random();
			break;
		}
		case 2 : {
			this.setOutputFileName_definitive("max");
			arcManager.determinateAllCosts_highest();
			break;
		}
		case 3 : {
			this.setOutputFileName_definitive("min");
			arcManager.determinateAllCosts_lowest();
			break;
		}
		case 4 : {
			int perc = UserInterface.dialogueRetrievePercentile();
			this.setOutputFileName_definitive("over" + perc + "percentile");
			arcManager.determinateAllCosts_overPercentile(perc);
			break;
		}
		case 5 : {
			int perc = UserInterface.dialogueRetrievePercentile();
			this.setOutputFileName_definitive("below" + perc + "percentile");
			arcManager.determinateAllCosts_belowPercentile(perc);
			break;
		}
		case 6 : {
			int perc = UserInterface.dialogueRetrievePercentile();
			this.setOutputFileName_definitive("at" + perc + "percentile");
			arcManager.determinateAllCosts_exactPercentile(perc);
			break;
		}
		default : UserInterface.printNotValid();

		}
	}



	public void GENERATE_STRUCTURES(ArcManager structureMaker) {
		this.activityMatrix = structureMaker.createActivityMatrix();
		this.releases = structureMaker.createReleaseArray();
		this.dueDates = structureMaker.createDueDatesArray();
		this.allMatrixes = structureMaker.createSetUpMatrixes();

	}
	
	
	public void PRINT_STRUCTURES_ON_FILE() {
		FileGenerator fg = new FileGenerator(this.activityMatrix, this.stationMatrix, this.releases, this.dueDates, this.allMatrixes, this.n, this.m);
		try {
			fg.generateFile(this.outputFileName_definitive);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}






	public void setOutputFileName_definitive(String mode) {
		this.outputFileName_definitive = outputFileName + "_" + mode + ".txt";
	}



}
