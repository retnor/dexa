package kempot.domparser;

import java.util.HashMap;

import com.google.common.collect.Multimap;

public class CPTconstruction {
	
	public static float jprob;
	public static String alteration;

	public CPTconstruction(String input, String disease) {
		jprob = 0;
		ReadXMLFile2 rules = new ReadXMLFile2();
		Multimap<String,String> rulemap = rules.ruleList;
		HashMap<String, Rules> ruleObjmap = rules.ruleListObj;
		
		//String input = "Southern,Male,River,Windy,Military,Influenza,Lake,Newborns,US,Winter,SoreThroat,Vegan";
		//String disease = "Anthrax";
		
		String[] itemList = input.split(",");
		boolean pathogenflag = false;
		float tempPathogen = 0;
		
		for(String item : itemList){
			
			boolean result = rulemap.containsEntry(disease, item);
			
			
			//System.out.println(item+": "+result);
			//get the joint probs
			if(result){
				Rules colrul = ruleObjmap.get(disease+"-"+item);
				
				alteration = colrul.getRuleAlter();
				//System.out.println(alteration);
				if(alteration.equals("addRisk")){
					jprob = jprob + colrul.getProb();
				}
				else if(alteration.equals("reduceRisk")){
					jprob = jprob - colrul.getProb();
				}
				else if(alteration.equals("setRisk")){
					pathogenflag = true;
					tempPathogen = colrul.getProb();
				}
				
				//jprob = jprob - 1;
			}
		}
		
		if(pathogenflag){
			jprob = tempPathogen;
		}
		//System.out.println("hasil--- "+jprob);
	}
}
