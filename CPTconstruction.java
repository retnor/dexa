package kempot.domparser;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.google.common.collect.Multimap;

public class CPTconstruction {
	
	public static float jprob;
	public static String alteration;

	public CPTconstruction(String input, String disease) throws ParserConfigurationException, SAXException, IOException{
		//ReadXMLFile2 rules = new ReadXMLFile2();
		ReadOntology rules = new ReadOntology();
		rules.execRule();
		HashMap<String, Rules> ruleObjmap = rules.ruleListObj;
		
		//input: Equatorial,Female,Forest,Cold,Farmers,Influenza,Farms,Adults,Indonesia,Autumn,Nausea,EatingMeats
		
		//initial probability of a person contracting a disease
		jprob = 0;		
		String[] itemList = input.split(",");
		
		//if there is a certain condition will make pathogen inactive (e.g. weather, season, location)
		//for example, Anthrax pathogen is inactive during wintertime
		//pathogenflag will be true if the rule alteration is 'setRisk' or rule type is 'Pathogen'
		boolean pathogenflag = false;
		float tempPathogen = 0;
		
		for(String item : itemList){
			
			//checking whether each attribute within a state combination appears in rules or not 
			boolean result = ruleObjmap.containsKey(disease+"-"+item);
			
			//get the conditional probs
			if(result){
				Rules colrul = ruleObjmap.get(disease+"-"+item);
				
				alteration = colrul.getRuleAlter();
				if(alteration.equals("addRisk"))
					jprob = jprob + colrul.getProb();
				else if(alteration.equals("reduceRisk"))
					jprob = jprob - colrul.getProb();
				else if(alteration.equals("setRisk")){
					pathogenflag = true;
					tempPathogen = colrul.getProb();
				}
			}
			
			//checking that any probabilities would go between 0 and 1
			//this condition happens when a person owns all disease-increasing-risk attributes 
			//and the sum product of all probabilities in all rules are exceed than 1
			if(jprob >= 1)
				jprob = jprob - 1;
			
			//this condition happens when there is more disease-reducing-risk attributes 
			//rather than disease-increasing-risk attributes owned by a person
			else if(jprob < 0)
				jprob = 0;
		}
		
		//assigning the end result of jprob with prob value given in setRisk, only if there is Pathogen rule type found
		if(pathogenflag){
			jprob = tempPathogen;
		}
	}
}
