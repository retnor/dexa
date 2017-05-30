package kempot.domparser;

public class Rules {
	private String rulenames;
	private String disease;
	private String ruleAtt;
	private String ruletype;
	private String rulealter;
	private float ruleProb;
	
	Rules(){
		
	}
	
	Rules(String name, String type, String attribute, String alter, String dis, String prob){
		rulenames = name;
		disease = dis;
		ruletype = type;
		ruleAtt = attribute;
		rulealter = alter;
		ruleProb = Float.parseFloat(prob);
	}
	
	public String getRuleAlter(){
		return rulealter;
	}
	
	public String getRuleDisease(){
		return disease;
	}
	
	public String getRuleType(){
		return ruletype;
	}
	
	public float getProb(){
		return ruleProb;
	}
	
}
