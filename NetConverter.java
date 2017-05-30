package kempot.domparser;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Multimap;

import kempot.trialcode.MemoryCheck;
import norsys.netica.Environ;
import norsys.netica.Net;
import norsys.netica.NeticaException;
import norsys.netica.Streamer;
import norsys.neticaEx.aliases.Node;

public class NetConverter {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		
		ReadXMLFile network = new ReadXMLFile();
		Multimap<String,String> map = network.networkmap;
		List<Integer> statenums = new ArrayList<Integer>();
		
		try {
			System.out.println ("\nWelcome to Netica-J !\n");
	
	    	Node.setConstructorClass ("norsys.neticaEx.aliases.Node");
	    	//Environ env = 
	    	new Environ (null);
			
			Net   net          = new Net();
	    	net.setName("dexa4");
	    	
	    	Node  tuberculosis   = new Node ("Tuberculosis",     "AtRisk, NotAtRisk",    net);
	    	Node tempID = null;
	    	Node  tempnode = null;
//		
//	    	NetsProperties arrc = new NetsProperties();
	    	
	    	//String nodename = null;
	    	
	    	for (String key : map.keySet()) {
	    		 String statenames = "";
	    		 String statecols = "";
	    		 String statecolsID = "";
	    		 
	    		 //if ID
	    		 if(key.equals("InfectiousDiseases")){
//	    			 statecolsID = "AtRisk, NotAtRisk";
//	    			 	List<String> valuesID = (List<String>) map.get(key);
//				     
//	    			 	for (String valueID : valuesID){
//	    			 	tempID = new Node (valueID, statecolsID, net);
//	    			 	System.out.println(valueID+" "+statecolsID);
//				     }
	    			 continue;
	    		 }
	    		 else{
				     List<String> values = (List<String>) map.get(key);
				     int statenum = values.size();
				     statenums.add(statenum);
				     for (String value : values){
				    	 statenames = statenames+value+",";
				     }
				     
				     statecols = statenames.substring(0, (statenames.length()-1));
//				     System.out.println(statecols);
				     tempnode = new Node (key, statecols, net);
				     
	    		 }
	    		 
			     tuberculosis.addLink(tempnode);
			}
	    	   	
			CPTconstruction setcpt;
			float atrisk;
			//12 nodes
			String stemp="";
			for (int r=0; r<statenums.get(0); r++){
				for (int s=0; s<statenums.get(1); s++){
					for (int t=0; t<statenums.get(2); t++){
						for (int u=0; u<statenums.get(3); u++){
							for (int v=0; v<statenums.get(4); v++){
								for (int w=0; w<statenums.get(5); w++){
									for (int x=0; x<statenums.get(6); x++){
										for (int y=0; y<statenums.get(7); y++){
											for (int z=0; z<statenums.get(8); z++){
												for (int aa=0; aa<statenums.get(9); aa++){
													for (int ab=0; ab<statenums.get(10); ab++){
														for (int ac=0; ac<statenums.get(11); ac++){
								stemp = ((List<String>) map.get("Hemisphere")).get(r)+","+((List<String>) map.get("Gender")).get(s)+","+
										((List<String>) map.get("Natural")).get(t)+","+((List<String>) map.get("Weather")).get(u)+","+
										((List<String>) map.get("Profession")).get(v)+","+((List<String>) map.get("PreExistingIllness")).get(w)+","+
										((List<String>) map.get("ManMade")).get(x)+","+((List<String>) map.get("DevelopmentStage")).get(y)+","+
										((List<String>) map.get("Country")).get(z)+","+((List<String>) map.get("Season")).get(aa)+","+
										((List<String>) map.get("Symptom")).get(ab)+","+((List<String>) map.get("Habits")).get(ac);
								
								//System.out.println(stemp);
								setcpt = new CPTconstruction(stemp, "Tuberculosis");
								atrisk = setcpt.jprob;
								System.out.println(stemp+": "+atrisk);
								//tuberculosis.setCPTable(stemp, 0.03, 0.97);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
//				}
//			}
			
			Streamer stream = new Streamer ("Dexa4.dne");
	    	net.write(stream);

		} catch (NeticaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MemoryCheck mem = new MemoryCheck();
		System.out.println(mem.maxMemory());
		System.out.println(mem.allocatedMemory());
		System.out.println(mem.freeMemory());
		System.out.println(mem.usedMemory());
		
		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime) + " milliseconds");
	}

}
