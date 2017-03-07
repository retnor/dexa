package kempot.trialcode;

import java.text.NumberFormat;
import java.util.ArrayList;

import norsys.netica.Environ;
import norsys.netica.Net;
import norsys.netica.NeticaException;
import norsys.netica.Streamer;
import norsys.neticaEx.aliases.Node;

public class tridarraylist {
	
	private static String[][][] isi = new String[5][3][2];
	//private static String[][][] isi = new String[4][3][2];
	//private static String[][][] isi = new String[3][3][2];
	private static ArrayList<Nodes> nodenames = new ArrayList<Nodes>();
	private static ArrayList<States> statenames = new ArrayList<States>();

	public static void main(String [] args){
		
		long startTime = System.currentTimeMillis();
		
		try {
			System.out.println ("\nWelcome to Netica-J !\n");

	    	Node.setConstructorClass ("norsys.neticaEx.aliases.Node");
	    	//Environ env = 
	    	new Environ (null);
	    	
	    	
	    	Net   net          = new Net();
	    	net.setName("dexa1");
	    	
	    	Node  tuberculosis   = new Node ("Tuberculosis",     "AtRisk, NotAtRisk",    net);
	    	Node  anthrax		 = new Node ("Anthrax", "AtRisk, NotAtRisk", net);
	    	Node  eye		= new Node ("EyeColour", "Hazel, Brown", net);
	    	Node  tempnode = null;
	    	
	    	anthrax.addLink(eye);
	    	
	    	NetsProperties arrc = new NetsProperties();
	    	isi = arrc.isic;
	    	
	    	int row = isi.length; //4
	    	int col = isi[0].length; //3
	    	int dens = 2; //numOfStates
	    	
	    	float cpts [] = new float [dens];
	    	int pointer=0;
	    	
	    	//loading the nodes
			for(int i=0; i<row; i++){
				for(int j=0; j<dens; j++){
					statenames.add(new States(isi[i][j+1][0], Float.parseFloat(isi[i][j+1][1])));
				}
				nodenames.add(new Nodes(isi[i][0][0], dens));
			}
			
			for (int j=0; j<nodenames.size(); j++){
				Nodes n = nodenames.get(j);
				
				String nodetemp = n.getNodeName();
				int numst = n.getNumStates();
				//System.out.println(nodetemp);
				
				numst=pointer+dens; //loop as the number of states in each node
				
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
			
			
//			//3 nodes
//			String stemp="";
//			int x = 0;
//			for (int q=0; q<2; q++){
//				for (int r=0; r<2; r++){
//					for (int s=0; s<2; s++){
////						for (int t=0; t<2; t++){
////							for (int u=0; u<4; u++){
//							stemp = statenames.get(x+q).getStateName()+","+statenames.get(x+2+r).getStateName()+","+statenames.get(x+4+s).getStateName();
//							tuberculosis.setCPTable(stemp, 0.02, 0.98);
//							//System.out.println(stemp);
////							}
////						}
//					}
//				}
//			}
			
			
//			//4 nodes
//			String stemp="";
//			int x = 0;
//			for (int q=0; q<2; q++){
//				for (int r=0; r<2; r++){
//					for (int s=0; s<2; s++){
//						for (int t=0; t<2; t++){
//							stemp = statenames.get(x+q).getStateName()+","+statenames.get(x+2+r).getStateName()+","+statenames.get(x+4+s).getStateName()+","+statenames.get(x+6+t).getStateName();
//							tuberculosis.setCPTable(stemp, 0.04, 0.96);
//							//System.out.println(stemp);
//						}
//					}
//				}
//			}
			
			//5 nodes
			String stemp="";
			int x = 0;
			for (int q=0; q<2; q++){
				for (int r=0; r<2; r++){
					for (int s=0; s<2; s++){
						for (int t=0; t<2; t++){
							for (int u=0; u<2; u++){
							stemp = statenames.get(x+q).getStateName()+","+statenames.get(x+2+r).getStateName()+","+statenames.get(x+4+s).getStateName()+","+statenames.get(x+6+t).getStateName()+","+statenames.get(x+8+u).getStateName();
							
							tuberculosis.setCPTable(stemp, 0.04, 0.96);
							//System.out.println(stemp);
							}
						}
					}
				}
			}
		    
	    	anthrax.setCPTable("Hazel", 0.05, 0.95);
	    	
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
