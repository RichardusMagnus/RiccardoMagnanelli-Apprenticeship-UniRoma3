package generator;

import java.io.IOException;


/******************* ASSUNZIONI *********************/
/* ogni job usa tutte le macchine una e una sola volta
 * 
 */



public class Main {
	public static void main(String[] args) throws IOException {
		UserInterface rm = new UserInterface();
		rm.mainMenu();
		
		
		
		IOstream.closeAllScanners();
	}
}
	