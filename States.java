package kempot.trialcode;

public class States implements Comparable<States> {
	private String statenames;
	private float percentages;
	States(){
		
	}
	
	States(String s, float p){
		statenames = s;
		percentages = p;
	}
	
	public String getStateName(){
		return statenames;
	}
	
	public float getPercentages(){
		return percentages;
	}
	
	@Override
	public int compareTo(States s) {
		return (this.statenames).compareTo(s.statenames);
	}
}
