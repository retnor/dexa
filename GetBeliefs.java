package kempot.domparser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import norsys.netica.*;

public class GetBeliefs {
	public static List<String> inputtedBeliefs = new ArrayList<String>();
	
	public static void main(String args[]) throws NeticaException{
		double prob = 0.0;
		
		inputtedBeliefs.add("Carnivore");	//Eating
		inputtedBeliefs.add("Equatorial");	//Hemisphere
		inputtedBeliefs.add("Female");		//Gender
		inputtedBeliefs.add("Forest");		//Natural
		inputtedBeliefs.add("Humid");		//Weather
		inputtedBeliefs.add("Jobless");		//Occupation
		inputtedBeliefs.add("Influenza");	//PreExistingIllness
		inputtedBeliefs.add("Lake");		//ManMade
		inputtedBeliefs.add("Children");	//DevelopmentStage
		inputtedBeliefs.add("Indonesia");	//Country
		inputtedBeliefs.add("Rain");		//Season
		inputtedBeliefs.add("Rash");		//Symptom
		inputtedBeliefs.add("Smoker");		//Smoking
		
		prob = calculate();
		
		System.out.println(prob);
	}

	public static double calculate() throws NeticaException{
		Environ env = new Environ (null);
		
		Net net = new Net (new Streamer ("Anthrax.dne"));
		
		Node disease	= net.getNode("Anthrax");
		
		//Read in the net
		NodeList nodes = net.getNodes();
		int numNodes = nodes.size();

		for(int i=0; i<(numNodes-1); i++){
			Node temporary = nodes.getNode(i);			
			temporary.finding().clear();
			temporary.finding().enterState(inputtedBeliefs.get(i));
		}
		
		net.compile();
		
		return disease.getBelief("AtRisk");
	}
}
