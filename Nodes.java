package kempot.trialcode;

public class Nodes implements Comparable<Nodes> {
	private String nodenames;
	private int numofstates;
	
	Nodes(){
		
	}
	
	Nodes(String n){
		nodenames = n;
	}
	
	Nodes(String n, int a){
		nodenames = n;
		numofstates = a;
	}
	
	public String getNodeName(){
		return nodenames;
	}
	
	public int getNumStates(){
		return numofstates;
	}
	
	@Override
	public int compareTo(Nodes n) {
		return (this.nodenames).compareTo(n.nodenames);
	}
}
