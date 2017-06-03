package kempot.domparser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import norsys.netica.*;

public class GetBeliefs {
	public static List<String> inputtedBeliefs = new ArrayList<String>();
	
	public static void main(String args[]) throws NeticaException{
		double prob = 0.0;
		
		inputtedBeliefs.add("Equatorial");	//Hemisphere
		inputtedBeliefs.add("Female");		//Gender
		inputtedBeliefs.add("Forest");		//Natural
		inputtedBeliefs.add("Humid");		//Weather
		inputtedBeliefs.add("Military");	//Profession
		inputtedBeliefs.add("Influenza");	//PreExistingIllness
		inputtedBeliefs.add("Lake");		//ManMade
		inputtedBeliefs.add("Adults");		//DevelopmentStage
		inputtedBeliefs.add("Indonesia");	//Country
		inputtedBeliefs.add("Winter");		//Season
		inputtedBeliefs.add("Rash");		//Symptom
		inputtedBeliefs.add("Smoking");		//Habits
		
		prob = calculate();
		
		System.out.println(prob);
	}

	public static double calculate() throws NeticaException{
		Environ env = new Environ (null);
		
		Net net = new Net (new Streamer ("Dexa4.dne"));
		
		Node tuberculosis	= net.getNode("Tuberculosis");
		
		//Read in the net
		NodeList nodes = net.getNodes();
		int numNodes = nodes.size();

		for(int i=0; i<(numNodes-1); i++){
			Node temporary = nodes.getNode(i);
			//System.out.println(temporary);
			temporary.finding().clear();
			temporary.finding().enterState(inputtedBeliefs.get(i));
		}
		
		net.compile();
		
		return tuberculosis.getBelief("AtRisk");
	}
}
