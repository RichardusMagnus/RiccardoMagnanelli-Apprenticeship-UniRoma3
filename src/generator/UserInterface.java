package generator;

import java.io.IOException;
import java.util.*;

import arc.Arc;
import filemanager.DataParser;
import filemanager.FileGenerator;

public class UserInterface {

	int n;
	int m;
	String graphFilePath;
	String stationFilePath;
	String outputFilePath;


	List<Arc> listOfAllArcs = null;
	ArcManager arcManager = null;

	int[][] stationMatrix = null;
	
	final String menu = "0 - back\n"
			+ "1 - random\n"
			+ "2 - max\n"
			+ "3 - min\n"
			+ "4 - over percentile\n"
			+ "5 - below percentile\n"
			+ "6 - exact percentile\n";

	public UserInterface() {


	}

	public void mainMenu() throws IOException {
		IOstream.println("*********************GRAPH GENERATOR***********************");
		IOstream.println("\nDefine graph extention.");

		IOstream.printf("n = ");
		setN(IOstream.readInteger());
		IOstream.printf("m = ");
		setM(IOstream.readInteger());

		IOstream.println("Type input file path. Separate steps with double slashes.\n"
				+ "(example: C:\\Users\\Kyky\\Desktop\\abz5.txt_complete_graph.txt");
		setGraphFilePath(IOstream.readFromKB());

		IOstream.println("Type stations file path, separating steps with double slashes.\n"
				+ "(example: C:\\Users\\Kyky\\Desktop\\stations.txt)");
		setStationFilePath(IOstream.readFromKB());

		IOstream.println("Type a new name for output files. Older namesake files will be overwritten.\n"
				+ "Output will be found among program resources.");
		setOutputFilePath(IOstream.readFromKB());

		this.listOfAllArcs = DataParser.extraxtArcs(graphFilePath);
		this.stationMatrix = DataParser.createStationMatrix(stationFilePath, n, m);
		this.arcManager = new ArcManager(listOfAllArcs, stationMatrix, n, m);

		boolean exit = false;
		IOstream.println(menu);

		int choice;
		while (!exit) {
			choice = IOstream.readInteger();
			switch (choice) {
			case 0:
				IOstream.println("\n\n");
				exit = true;
				break;
			case 1:
				this.generaFileRandom(arcManager);
				IOstream.println("Random file done! You may generate another file.");
				IOstream.println(menu);
				break;
			case 2:
				this.generaFileAlto(arcManager);
				IOstream.println("Max file done! You may generate another file.");
				IOstream.println(menu);
				break;
			case 3:
				this.generaFileBasso(arcManager);
				IOstream.println("Min file done! You may generate another file.");
				IOstream.println(menu);
				break;
			case 4:
				IOstream.printf("Choose a percentile: ");
				int a = IOstream.readInteger();
				this.generaFileOverPercentile(arcManager, a);
				IOstream.println("Over perc file done! You may generate another file.");
				IOstream.println(menu);
				break;
			case 5:
				IOstream.printf("Choose a percentile: ");
				int b = IOstream.readInteger();
				this.generaFileBelowPercentile(arcManager, b);
				IOstream.println("Below perc done! You may generate another file.");
				IOstream.println(menu);
				break;
			case 6:
				IOstream.printf("Choose a percentile: ");
				int c = IOstream.readInteger();
				this.generaFileExactPercentile(arcManager, c);
				IOstream.println("Exact perc done! You may generate another file.");
				IOstream.println(menu);
				break;
			}
			
		}
	}






	public void generaFileRandom(ArcManager structureMaker) throws IOException {
		structureMaker.determinateAllCosts_random();
		int[][] activityMatrix = structureMaker.createActivityMatrix();
		int[] releases = structureMaker.createReleaseArray();
		int[] dueDates = structureMaker.createDueDatesArray();
		int[][][] allMatrixes = structureMaker.createSetUpMatrixes();

		FileGenerator fg = new FileGenerator(activityMatrix, this.stationMatrix, releases, dueDates, allMatrixes, n, m);
		fg.generateFile(this.outputFilePath + "_random.txt");	
	}

	public void generaFileAlto(ArcManager structureMaker) throws IOException {
		structureMaker.determinateAllCosts_highest();
		int[][] activityMatrix = structureMaker.createActivityMatrix();
		int[] releases = structureMaker.createReleaseArray();
		int[] dueDates = structureMaker.createDueDatesArray();
		int[][][] allMatrixes = structureMaker.createSetUpMatrixes();

		FileGenerator fg = new FileGenerator(activityMatrix, this.stationMatrix, releases, dueDates, allMatrixes, n, m);
		fg.generateFile(this.outputFilePath + "_maxValue.txt");	
	}

	public void generaFileBasso(ArcManager structureMaker) throws IOException {
		structureMaker.determinateAllCosts_lowest();
		int[][] activityMatrix = structureMaker.createActivityMatrix();
		int[] releases = structureMaker.createReleaseArray();
		int[] dueDates = structureMaker.createDueDatesArray();
		int[][][] allMatrixes = structureMaker.createSetUpMatrixes();

		FileGenerator fg = new FileGenerator(activityMatrix, this.stationMatrix, releases, dueDates, allMatrixes, n, m);
		fg.generateFile(this.outputFilePath + "_minValue.txt");	
	}

	public void generaFileOverPercentile(ArcManager structureMaker, int x) throws IOException {
		structureMaker.determinateAllCosts_overPercentile(x);
		int[][] activityMatrix = structureMaker.createActivityMatrix();
		int[] releases = structureMaker.createReleaseArray();
		int[] dueDates = structureMaker.createDueDatesArray();
		int[][][] allMatrixes = structureMaker.createSetUpMatrixes();

		FileGenerator fg = new FileGenerator(activityMatrix, this.stationMatrix, releases, dueDates, allMatrixes, n, m);
		fg.generateFile(this.outputFilePath + "_over" + x + "Percentile.txt");	
	}

	public void generaFileBelowPercentile(ArcManager structureMaker, int x) throws IOException {
		structureMaker.determinateAllCosts_belowPercentile(x);
		int[][] activityMatrix = structureMaker.createActivityMatrix();
		int[] releases = structureMaker.createReleaseArray();
		int[] dueDates = structureMaker.createDueDatesArray();
		int[][][] allMatrixes = structureMaker.createSetUpMatrixes();

		FileGenerator fg = new FileGenerator(activityMatrix, this.stationMatrix, releases, dueDates, allMatrixes, n, m);
		fg.generateFile(this.outputFilePath + "_below" + x + "Percentile.txt");	
	}

	public void generaFileExactPercentile(ArcManager structureMaker, int x) throws IOException {
		structureMaker.determinateAllCosts_exactPercentile(x);
		int[][] activityMatrix = structureMaker.createActivityMatrix();
		int[] releases = structureMaker.createReleaseArray();
		int[] dueDates = structureMaker.createDueDatesArray();
		int[][][] allMatrixes = structureMaker.createSetUpMatrixes();

		FileGenerator fg = new FileGenerator(activityMatrix, this.stationMatrix, releases, dueDates, allMatrixes, n, m);
		fg.generateFile(this.outputFilePath + "_at" + x + "Percentile.txt");	
	}








	public int getN() {
		return n;
	}

	public int getM() {
		return m;
	}

	public String getGraphFilePath() {
		return graphFilePath;
	}

	public String getStationFilePath() {
		return stationFilePath;
	}
	
	public String getOutputFilePath() {
		return outputFilePath;
	}

	public void setN(int n) {
		this.n = n;
	}

	public void setM(int m) {
		this.m = m;
	}

	public void setGraphFilePath(String graphFilePath) {
		this.graphFilePath = graphFilePath;
	}

	public void setStationFilePath(String stationFilePath) {
		this.stationFilePath = stationFilePath;
	}

	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}

}
