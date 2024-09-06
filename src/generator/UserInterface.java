package generator;

import java.io.IOException;
import java.util.*;

import arc.Arc;
import filemanager.DataParser;
import filemanager.FileGenerator;

public class UserInterface {
	
	private final static String menu = "0 - back\n"
			+ "1 - random\n"
			+ "2 - max\n"
			+ "3 - min\n"
			+ "4 - over percentile\n"
			+ "5 - below percentile\n"
			+ "6 - exact percentile\n";
	
	private static final String greetings = "*********************GRAPH GENERATOR***********************" 
			+ "\nDefine graph extention.";
	
	private static final String done = "file done! You may generate another file.";
	
	
	
	
	public static String dialogueChooseOutputName() {
		IOstream.println("Type a new name for output files. Older namesake files will be overwritten.\n"
				+ "Output will be found among program resources.");
		return (IOstream.readFromKB());
	}

	public static String dialogueRetrieveStationPath() {
		IOstream.println("Type stations file path, separating steps with double slashes.\n"
				+ "(example: C:\\Users\\Kyky\\Desktop\\stations.txt)");
		return (IOstream.readFromKB());
	}

	public static String dialogueRetrieveInputPath() {
		IOstream.println("Type input file path. Separate steps with double slashes.\n"
				+ "(example: C:\\Users\\Kyky\\Desktop\\abz5.txt_complete_graph.txt");
		return (IOstream.readFromKB());
	}

	public static int dialogueRetrieveM() {
		IOstream.printf("m = ");
		return (IOstream.readInteger());
	}

	public static int dialogueRetrieveN() {
		IOstream.printf("n = ");
		return (IOstream.readInteger());
	}
	
	public static int dialogueRetrievePercentile() {
		IOstream.printf("Percentile (from 0 to 100) = ");
		return (IOstream.readInteger());
	}
	
	public static int dialogueRetrieveChoice() {
		IOstream.println("Choose a generation mode. You will find the output in the program resources, named accordingly.");
		IOstream.println(menu);
		return (IOstream.readInteger());
	}
	
	public static int dialogueAskKeepUsingThisFile() {
		IOstream.printf("Keep this input = 1; Stop program = 0: ");
		return IOstream.readInteger();
	}

	
	
	
	
	
	
	
	public static void printGreetings() {
		IOstream.println(greetings);
	}
	
	public static void printRandomDone() {
		IOstream.println("Random " + done);
	}
	
	public static void printMaxDone() {
		IOstream.println("Max " + done);
	}
	
	public static void printMinDone() {
		IOstream.println("Min " + done);
	}
	
	public static void printOverPercDone() {
		IOstream.println("Over perc " + done);
	}
	
	public static void printBelowPercDone() {
		IOstream.println("Below perc " + done);
	}
	
	public static void printExactPercDone() {
		IOstream.println("Exact perc " + done);
	}
	
	public static void printNotValid() {
		IOstream.println("Invalid choice, try again.");
	}
	
	public static void printEnd() {
		IOstream.println("\n\n****End of programme****\n\n");
	}
}
