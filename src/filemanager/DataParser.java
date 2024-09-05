package filemanager;

import java.io.*;
import java.util.*;
import arc.Arc;

public class DataParser {
	
	String sourcePath = null;
	
	public DataParser(String f) {
		this.sourcePath = f;
	}
	
	public List<Arc> extraxtArcs() {
		List<Arc> allArcs = new ArrayList<Arc>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(sourcePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                allArcs.add(this.parse(line));
            }
        } catch (IOException e) {
        	System.out.println("Il file non esiste oppure è vuoto.");
            e.printStackTrace();
        }
		
		
		return allArcs;		
	}

	public Arc parse(String arcRawData) {
		int ID_or;
		int ID_dest;
		ArrayList<Integer> stoc_costs = new ArrayList<Integer>();
		
		String[] rawDataPartitioned = this.divide(arcRawData);

		ID_or = Integer.parseInt(rawDataPartitioned[0]);
		ID_dest = Integer.parseInt(rawDataPartitioned[1]);
		
		for (int i = rawDataPartitioned.length-1; i >= 2; i--) {
			int occ = Integer.parseInt(rawDataPartitioned[i]);
			i--;
			for(int j=1; j<=occ; j++) {
				stoc_costs.add(Integer.parseInt(rawDataPartitioned[i]));
			}
		}
		
		Arc newArc = new Arc(ID_or, ID_dest, stoc_costs);
		
		return newArc;
	}
	
	public String[] divide(String s) {
		String[] elements = s.substring(2).split(" ");//considera la stringa partire dal secondo numero, ignorando la a e lo spazio seguente
		return elements;
	}

}
