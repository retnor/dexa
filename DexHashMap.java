package kempot.trialcode;

import java.util.ArrayList;
import java.util.HashMap;

import norsys.netica.Environ;
import norsys.netica.Net;
import norsys.netica.NeticaException;
import norsys.netica.Streamer;
import norsys.neticaEx.aliases.Node;

public class DexHashMap {
	private static String[][][] isi = new String[3][6][2];
	private static HashMap<String, ArrayList<States>> map = new HashMap<String, ArrayList<States>>();
	private static ArrayList<String> queue = new ArrayList<String>();
	
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
	    	Node  tempnode = null;
		
	    	NetsProperties arrc = new NetsProperties();
	    	isi = arrc.isic;
	    	
	    	int row = isi.length; //4
	    	int col = isi[0].length; //3
	    	//int dens = isi[0][0].length; //2
	    	int statenum = col-1;
			
			for(int i=0; i<row; i++){
				ArrayList<States> statenames = new ArrayList<States>();
				
				for(int j=0; j<statenum; j++){

					statenames.add(new States(isi[i][j+1][0], Float.parseFloat(isi[i][j+1][1])));
				}
				
				map.put(isi[i][0][0], statenames);
				
			}
			
	    	float cpts [] = new float [statenum];
	    	
			for ( String key : map.keySet() ) {

				ArrayList<States> prints = map.get(key);
				
				String temp="";
				for (int j = 0; j<prints.size(); j++){
					temp = temp+prints.get(j).getStateName()+",";
					
					cpts[j] = prints.get(j).getPercentages();
				}
				
				String statecols = temp.substring(0, (temp.length()-1));
				
				tempnode = new Node (key, statecols, net);
				queue.add(key);
				tuberculosis.addLink(tempnode);
				tempnode.setCPTable(cpts);
			}
			
			//12 nodes
			String stemp="";
			for (int r=0; r<statenum; r++){
				for (int s=0; s<statenum; s++){
					for (int t=0; t<statenum; t++){
		//				for (int u=0; u<statenum; u++){
		//					for (int v=0; v<statenum; v++){
		//						for (int w=0; w<statenum; w++){
								//	for (int x=0; x<statenum; x++){
								//		for (int y=0; y<statenum; y++){
								//			for (int z=0; z<statenum; z++){
								//				for (int aa=0; aa<statenum; aa++){
								//					for (int ab=0; ab<statenum; ab++){
								//						for (int ac=0; ac<statenum; ac++){
								//							for (int ad=0; ad<statenum; ad++){
								//								for (int ae=0; ae<statenum; ae++){
								stemp = map.get(queue.get(0)).get(r).getStateName()+","+map.get(queue.get(1)).get(s).getStateName()+
										","+map.get(queue.get(2)).get(t).getStateName();
								tuberculosis.setCPTable(stemp, 0.03, 0.97);
								//								}
								//System.out.println(stemp);
								//							}
								//						}
								//					}
								//				}
								//			}
								//		}
								//	}
		//						}
		//					}
		//				}
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

