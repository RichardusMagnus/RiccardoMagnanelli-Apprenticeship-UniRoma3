package generator;

import java.io.IOException;
import java.util.*;

import javax.xml.crypto.Data;

import arc.Arc;
import filemanager.DataParser;


/******************* ASSUNZIONI *********************/
/* ogni job usa tutte le macchine una e una sola volta
 * 
 */



public class Main {
	public static void main(String[] args) throws IOException {
		
		/*LETTURA DA RIGA DI COMANDO*/
		//args: PERCORSO DEL FILE ABZ \ PERCORSO DEL FILE CON L'ELENCO DEGLI ARCHI \ ELENCO PERCENTILI \ NOME OUTPUT
		
		/*args = new String[4];
		
		args[0] = "C:\\Users\\Kyky\\Desktop\\abz5.txt_complete_graph.txt";
		args[1] = "C:\\Users\\Kyky\\Desktop\\abz5.txt";
		args[2] = "35:65.0:100.:0";
		args[3] = "CIAONE";
		*/
		
		
		Runner MAIN_RUNNER = new Runner(args[0], args[1], args[2], args[3]);
		MAIN_RUNNER.RUN_MAIN_LOGIC();
		
		

		
		
		
		
		
		
		
	}
}
