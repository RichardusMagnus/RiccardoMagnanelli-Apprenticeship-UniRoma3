package generator;

import java.io.IOException;
import java.util.*;

import arc.Arc;
import filemanager.DataParser;


/******************* ASSUNZIONI *********************/
/* ogni job usa tutte le macchine una e una sola volta
 * 
 */



public class Main {
	public static void main(String[] args) throws IOException {

		UserInterface.printGreetings();
		int n = UserInterface.dialogueRetrieveN();
		int m = UserInterface.dialogueRetrieveM();
		String graphFilePath = UserInterface.dialogueRetrieveInputPath();
		String stationFilePath = UserInterface.dialogueRetrieveStationPath();
		String outputFileName = UserInterface.dialogueChooseOutputName();

		boolean exitGeneration = false;
		while (!exitGeneration) {
			int choice = UserInterface.dialogueRetrieveChoice();
			Runner run = new Runner(n, m, graphFilePath, stationFilePath, outputFileName, choice);
			run.RUN_MAIN_LOGIC();

			switch (choice) {
			case 1 : {
				UserInterface.printRandomDone();
				break;
			}
			case 2 : {
				UserInterface.printMaxDone();
				break;
			}
			case 3 : {
				UserInterface.printMinDone();
				break;
			}
			case 4 : {
				UserInterface.printOverPercDone();
				break;
			}
			case 5 : {
				UserInterface.printBelowPercDone();
				break;
			}
			case 6 : {
				UserInterface.printExactPercDone();
				break;
			}
			}

			if (UserInterface.dialogueAskKeepUsingThisFile() == 0) exitGeneration = true;
		}

		UserInterface.printEnd();
		IOstream.closeAllScanners();
	}
}
