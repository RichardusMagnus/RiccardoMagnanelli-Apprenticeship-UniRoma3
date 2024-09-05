package arc;

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
	
	public void determinateCost_random() {
		int rnd_index = new Random().nextInt(this.stoc_costs.size());
		int deter_cost = stoc_costs.get(rnd_index);
		this.setDeter_cost(deter_cost);
	}
	
	public void determinateCost_highest() {
		int deter_cost = this.stoc_costs.get(stoc_costs.size()-1);
		this.setDeter_cost(deter_cost);
	}
	
	public void determinateCost_lowest() {
		int deter_cost = this.stoc_costs.get(0);
		this.setDeter_cost(deter_cost);
	}
	
	public void determinateCost_overPercentile(float p) {
		int minIndex = (int) Math.floor((p/100)*(this.stoc_costs.size() - 1));
		int rnd_index = new Random().nextInt(minIndex, this.stoc_costs.size());
		int deter_cost = stoc_costs.get(rnd_index);
		this.setDeter_cost(deter_cost);
	}
	
	public void determinateCost_belowPercentile(float p) {
		int maxIndex = (int) Math.floor((p/100)*(this.stoc_costs.size() - 1));
		int rnd_index = new Random().nextInt(0, maxIndex);
		int deter_cost = stoc_costs.get(rnd_index);
		this.setDeter_cost(deter_cost);
	}
	
	public void determinateCost_exactPercentile(float p) {
		int index = (int) Math.floor((p/100)*(this.stoc_costs.size() - 1));
		int deter_cost = stoc_costs.get(index);
		this.setDeter_cost(deter_cost);
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
	
	public void setDeter_cost(int deter_cost) {
		this.deter_cost = deter_cost;
	}
	
	
	
	
	
	
	@Override
	public boolean equals(Object obj) {
		Arc q = (Arc) obj;
		return (this.ID_or == q.ID_or && this.ID_dest == q.ID_dest);
	}

}
