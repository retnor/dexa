package kempot.trialcode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import norsys.netica.Environ;
import norsys.netica.Net;
import norsys.netica.NeticaException;
import norsys.netica.Streamer;
import norsys.neticaEx.aliases.Node;

import java.util.Set;

public class threedhashtwo {
	private static String[][][] isi = new String[5][3][2];
	//private static String[][][] isi = new String[4][3][2];
	//private static String[][][] isi = new String[3][3][2];
	private static HashMap<String, ArrayList<States>> map = new HashMap<String, ArrayList<States>>();
	
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		try {
			System.out.println ("\nWelcome to Netica-J !\n");
	
	    	Node.setConstructorClass ("norsys.neticaEx.aliases.Node");
	    	//Environ env = 
	    	new Environ (null);
			
			Net   net          = new Net();
	    	net.setName("dexa2");
	    	
	    	Node  tuberculosis   = new Node ("Tuberculosis",     "AtRisk, NotAtRisk",    net);
	    	Node  anthrax		 = new Node ("Anthrax", "AtRisk, NotAtRisk", net);
	    	Node  eye		= new Node ("EyeColour", "Hazel, Brown", net);
	    	Node  tempnode = null;
	    	
	    	anthrax.addLink(eye);
		
	    	NetsProperties arrc = new NetsProperties();
	    	isi = arrc.isic;
	    	
	    	int row = isi.length; //4
	    	int col = isi[0].length; //3
	    	int dens = isi[0][0].length; //2
			
			for(int i=0; i<row; i++){
				ArrayList<States> statenames = new ArrayList<States>();
				
				for(int j=0; j<dens; j++){
					statenames.add(new States(isi[i][j+1][0], Float.parseFloat(isi[i][j+1][1])));
				}
				
				map.put(isi[i][0][0], statenames);
				
			}
			
	    	float cpts [] = new float [dens];
	    	
			for ( String key : map.keySet() ) {

				ArrayList<States> prints = map.get(key);
				
				String temp="";
				for (int j = 0; j<prints.size(); j++){
					temp = temp+prints.get(j).getStateName()+",";
					
					cpts[j] = prints.get(j).getPercentages();
				}
				
				String statecols = temp.substring(0, (temp.length()-1));
				
				tempnode = new Node (key, statecols, net);
				tuberculosis.addLink(tempnode);
				tempnode.setCPTable(cpts);
			}
			
//			//3 nodes
//			String stemp="";
//			for (int r=0; r<2; r++){
//				for (int s=0; s<2; s++){
//					for (int t=0; t<2; t++){
////						for (int u=0; u<2; u++){
//							stemp = map.get("EyeColor").get(r).getStateName()+","+map.get("SkinColor").get(s).getStateName()+","+map.get("Race").get(t).getStateName();
//							tuberculosis.setCPTable(stemp, 0.03, 0.97);
////							System.out.println(stemp);
////						}
//					}
//				}
//			}
			
//			//4 nodes
//			String stemp="";
//			for (int r=0; r<2; r++){
//				for (int s=0; s<2; s++){
//					for (int t=0; t<2; t++){
//						for (int u=0; u<2; u++){
//							stemp = map.get("EyeColor").get(r).getStateName()+","+map.get("SkinColor").get(s).getStateName()+","+map.get("Race").get(t).getStateName()+","+map.get("TongueTie").get(u).getStateName();
//							tuberculosis.setCPTable(stemp, 0.03, 0.97);
//							//System.out.println(stemp);
//						}
//					}
//				}
//			}
			
			//5 nodes
			String stemp="";
			for (int r=0; r<2; r++){
				for (int s=0; s<2; s++){
					for (int t=0; t<2; t++){
						for (int u=0; u<2; u++){
							for (int v=0; v<2; v++){
								stemp = map.get("EyeColor").get(r).getStateName()+","+map.get("SkinColor").get(s).getStateName()+","+map.get("Race").get(t).getStateName()+","+map.get("TongueTie").get(u).getStateName()+","+map.get("Gender").get(v).getStateName();
								tuberculosis.setCPTable(stemp, 0.03, 0.97);
								//System.out.println(stemp);
							}
						}
					}
				}
			}
			
			Streamer stream = new Streamer ("Dexa2.dne");
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

