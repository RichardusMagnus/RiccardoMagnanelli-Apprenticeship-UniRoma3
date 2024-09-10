package app.arc;

import java.util.*;
import java.util.Random;

public class Arc {

	private final int ID_or;
	private final int ID_dest;
	private ArrayList<Integer> stoc_costs;
	private int deter_cost;
	
	public Arc(int ID_or, int ID_dest, ArrayList<Integer> stoc_costs) {
		this.ID_or = ID_or;
		this.ID_dest = ID_dest;
		this.stoc_costs = stoc_costs;
		this.deter_cost = 0;
	}
	
	
	
	/*COST DETERMINATION METHODS*/
	public void determinateCost(String range) {
		
		int[] bounds = this.extractBounds(range);
		int LB = bounds[0];
		int UB = bounds[1];
		
		int minIndex = (int) Math.floor((LB/100)*(this.stoc_costs.size() - 1));
		int maxIndex = (int) Math.floor((UB/100)*(this.stoc_costs.size() - 1));
		
		if (minIndex==maxIndex) this.setDeter_cost(minIndex);
		else {
			int rndIndex = new Random().nextInt(minIndex, maxIndex);
			this.setDeter_cost(rndIndex);
		}
	}
	
	public int[] extractBounds(String range) {
		String[] parameters = range.split(":", -1);
		int LB;
		int UB;
		
		if (parameters[0].equals("")) LB = 0;
		else LB = Integer.parseInt(parameters[0]);
		
		if (parameters[1].equals("")) UB = 100;
		else UB = Integer.parseInt(parameters[1]);
		
		int[] bounds = {LB, UB};
		return bounds;
		
	}
	
	
	
	
	
	/*getter and setters*/
	
	public int getCost() {
		return deter_cost;
	}
	
	public int getID_dest() {
		return ID_dest;
	}
	public int getID_or() {
		return ID_or;
	}
	
	public ArrayList<Integer> getStoc_costs() {
		return stoc_costs;
	}
	
	public void setStoc_costs(ArrayList<Integer> stoc_costs) {
		this.stoc_costs = stoc_costs;
	}
	
	public void setDeter_cost(int ind) {
		this.deter_cost = this.stoc_costs.get(ind);
	}
	
	
	
	
	
	
	@Override
	public boolean equals(Object obj) {
		Arc q = (Arc) obj;
		return (this.ID_or == q.ID_or && this.ID_dest == q.ID_dest);
	}

}
