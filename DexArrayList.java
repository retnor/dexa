package kempot.trialcode;

import java.text.NumberFormat;
import java.util.ArrayList;

import norsys.netica.Environ;
import norsys.netica.Net;
import norsys.netica.NeticaException;
import norsys.netica.Streamer;
import norsys.neticaEx.aliases.Node;

public class DexArrayList {
	
	private static String[][][] isi = new String[4][6][2];
	private static ArrayList<Nodes> nodenames = new ArrayList<Nodes>();
	private static ArrayList<States> statenames = new ArrayList<States>();

	public static void main(String [] args){
		
		long startTime = System.currentTimeMillis();
		
		try {
			System.out.println ("\nWelcome to Netica-J !\n");

	    	Node.setConstructorClass ("norsys.neticaEx.aliases.Node");
	    	new Environ (null);
	    	
	    	Net   net          = new Net();
	    	net.setName("dexa1");
	    	
	    	Node  tuberculosis   = new Node ("Tuberculosis",     "AtRisk, NotAtRisk",    net);
	    	Node  tempnode = null;
	    	
	    	NetsProperties arrc = new NetsProperties();
	    	isi = arrc.isic;
	    	
	    	int row = isi.length; //4
	    	int col = isi[0].length; //3
	    	int statenum = col-1;
	    	
	    	float cpts [] = new float [(col-1)];
	    	int pointer=0;
	    	
	    	//loading the nodes
			for(int i=0; i<row; i++){
				for(int j=0; j<statenum; j++){
					statenames.add(new States(isi[i][j+1][0], Float.parseFloat(isi[i][j+1][1])));
					//System.out.println(isi[i][j+1][0]);
				}
				nodenames.add(new Nodes(isi[i][0][0], (col-1)));
			}
			
			for (int j=0; j<nodenames.size(); j++){
				Nodes n = nodenames.get(j);
				
				String nodetemp = n.getNodeName();
				int numst = n.getNumStates();
				//System.out.println(nodetemp);
				
				numst=pointer+statenum; //loop as the number of states in each node
				
				String statecol="";
				for(int i=pointer, k=0; i<numst; i++, k++){
					States s = statenames.get(i);
					//System.out.println(pointer+","+numst);
					statecol=statecol+s.getStateName()+",";
					
		    		cpts[k] = s.getPercentages();
		    		
					pointer++;
				}
				
				
				//Node declaration statement
				String statecols = statecol.substring(0, (statecol.length()-1));
	    		tempnode = new Node (nodetemp, statecols, net);
	    		
	    		//creating the structure: adding parent nodes to a child node
	    		tuberculosis.addLink(tempnode);
				
	    		//filling in the default values of the parent nodes
	    		tempnode.setCPTable(cpts);
			}
			
			//8 nodes
			String stemp="";
			//int x = 0;
			for (int q=0; q<statenum; q++){
				for (int r=0; r<statenum; r++){
					for (int s=0; s<statenum; s++){
						for (int t=0; t<statenum; t++){
							//for (int u=0; u<statenum; u++){
								//for (int v=0; v<statenum; v++){
									//for (int w=0; w<statenum; w++){
										//for (int y=0; y<statenum; y++){
											//for (int z=0; z<statenum; z++){
												//for (int aa=0; aa<statenum; aa++){
													//for (int ab=0; ab<statenum; ab++){
														//for (int ac=0; ac<statenum; ac++){
															//for (int ad=0; ad<statenum; ad++){
																//for (int ae=0; ae<3; ae++){
						stemp = statenames.get(q).getStateName()+","+statenames.get(5+r).getStateName()+
								","+statenames.get(10+s).getStateName()+","+statenames.get(15+t).getStateName();
						tuberculosis.setCPTable(stemp, 0.04, 0.96);
						//System.out.println(stemp);
																//}
															//}
														//}
													//}
												//}
											//}
										//}
									//}
								//}
							//}
						}
					}
				}
			}
	    	
	    	Streamer stream = new Streamer ("Dexa1.dne");
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
