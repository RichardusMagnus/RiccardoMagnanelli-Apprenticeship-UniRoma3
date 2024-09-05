package generator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import arc.*;


public class ArcManager {
	private List<Arc> arcs = null;
	private List<Arc> activities = null;
	private List<Arc> setUps = null;
	private List<Arc> dueDates = null;
	private List<Arc> releases = null;

	private int n; //stations number
	private int m; //jobs number

	public ArcManager(List<Arc> allArcs, int n, int m) {
		this.arcs = allArcs;
		this.n = n;
		this.m = m;
		this.arcManagerConstructor_ausiliary();

	}

	/**
	 * Identifica gli archi in base alla loro funzione,
	 * se archi di attività, set up, release o due time.
	 */
	public void arcManagerConstructor_ausiliary() {
		List <Arc> allActivities = new ArrayList<>();
		List<Arc> allSetUps = new ArrayList<>();
		List <Arc> allDueDates = new ArrayList<>();
		List <Arc> allReleases = new ArrayList<>();

		for (Arc arc : this.arcs) {
			if (arc.getID_or() == -1)
				allReleases.add(arc);
			else if (arc.getID_dest() == -2)
				allDueDates.add(arc);
			else if (calculate_j(arc.getID_or()) != calculate_j(arc.getID_dest()))
				allSetUps.add(arc);
			else allActivities.add(arc);
		}

		this.activities = allActivities;
		this.setUps = allSetUps;
		this.dueDates = allDueDates;
		this.releases = allReleases;
	}	

	
	
	
	
	/*STRUCTURE CONSTRUCTION METHODS*/
	
	/**
	 * Creates a n*m matrix in which each job is represented by a row
	 * and every number is the cost of a certain activity of that job.
	 * @return activity matrix
	 */
	public int[][] createActivityMatrix() {
		int[][] actMatrix = new int[this.n][this.m];

		for(int q=0; q<n; q++) for(int r=0; r<m; r++) actMatrix[q][r] = 0;

		for (Arc activity : this.activities) {
			int pos = activity.getID_or();
			int i = this.calculate_i(pos);
			int j = this.calculate_j(pos);

			actMatrix[i][j] = activity.getCost();
		}
		
		return actMatrix;
	}

	/**
	 * Creates a n*m matrix in which each job is represented by a row
	 * and every number is ID of the station that operates the
	 * correspondent activity of that job. It takes as input the path to the
	 * file that contains the same matrix.
	 * @return station matrix
	 */
	public int[][] createStationMatrix(String path) {
		int[][] stationMatrix = new int[this.n][this.m];

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				String[] s = line.split(" ");
				for (int q=0; q<s.length; q++) {
					stationMatrix[q][i] = Integer.parseInt(s[q]);
				}
				i++;
			}
		} catch (IOException e) {
			System.out.println("Il file non esiste oppure è vuoto.");
			e.printStackTrace();

		}

		return stationMatrix;
	}

	public int calculate_j(int pos) {
		return (pos-1)/(n+1);
	}

	public int calculate_i(int pos) {
		return pos%(n+1)-1;
	}
	
	public int calculate_ID(int i, int j) {
		return j*(this.n+1) + 1 + i;
	}



	public int[] createReleaseArray() {
		int[] relArray = new int[n];

		for (Arc rel : this.releases) {
			int pos = rel.getID_dest();
			int j = calculate_j(pos);
			relArray[j] = rel.getCost();
		}

		return relArray;
	}

	public int[] createDueDatesArray() {
		int[] dueArray = new int[n];

		for (Arc rel : this.dueDates) {
			int pos = rel.getID_or();
			int j = calculate_j(pos);
			dueArray[j] = rel.getCost();
		}

		return dueArray;
	}


	public int[][][] createSetUpMatrixes(String path) {
		int[][][] allSetUpMatrixes = new int[n][m][m];
		for (int machine = 0; machine < n; machine++) {
            for (int i = 0; i < m; i++) {
                Arrays.fill(allSetUpMatrixes[machine][i], 0); // o altro valore
            }
        }
		
		int[][] stationMatrix = this.createStationMatrix(path);		
		for (Arc stp : this.setUps) {
			int station = stationMatrix[this.calculate_i(stp.getID_dest())][this.calculate_j(stp.getID_dest())];
			int job_or = this.calculate_j(stp.getID_or());
			int job_dest = this.calculate_j(stp.getID_dest());
			
			allSetUpMatrixes[station][job_or][job_dest] = stp.getCost();
		}
		
		return allSetUpMatrixes;
	}
	
	
	
	
	
	
	
	
	
	/*COST DETERMINATION METHODS*/

	/**
	 * Selects a random admissible value for each acr.
	 */
	public void determinateAllCosts_random() {
		for(Arc arc : this.arcs) {
			arc.determinateCost_random();
		}
	}
	
	/**
	 * Selects the highest admissible value for each acr.
	 */
	public void determinateAllCosts_highest() {
		for(Arc arc : this.arcs) {
			arc.determinateCost_highest();
		}
	}
	
	/**
	 * Selects the lowest admissible value for each acr.
	 */
	public void determinateAllCosts_lowest() {
		for(Arc arc : this.arcs) {
			arc.determinateCost_lowest();
		}
	}
	
	/**
	 * Selects a random admissible value OVER a
	 * certain percentiile for each acr.
	 */
	public void determinateAllCosts_overPercentile(float p) {
		for(Arc arc : this.arcs) {
			arc.determinateCost_overPercentile(p);
		}
	}
	
	/**
	 * Selects a random admissible value BELOW
	 * a certain percentiile for each acr.
	 */
	public void determinateAllCosts_belowPercentile(float p) {
		for(Arc arc : this.arcs) {
			arc.determinateCost_belowPercentile(p);
		}
	}
	
	/**
	 * Selects a certain percentiile value for each acr.
	 */
	public void determinateAllCosts_exactPercentile(float p) {
		for(Arc arc : this.arcs) {
			arc.determinateCost_exactPercentile(p);
		}
	}



	
	
	

	/*getters and setters*/

	public List<Arc> getArcs() {
		return arcs;
	}

	public List<Arc> getActivities() {
		return activities;
	}

	public List<Arc> getDueDates() {
		return dueDates;
	}

	public List<Arc> getReleases() {
		return releases;
	}

	public List<Arc> getSetUps() {
		return setUps;
	}


}
