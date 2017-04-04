package kempot.trialcode;

import norsys.netica.Environ;
import norsys.netica.Net;
import norsys.netica.NeticaException;
import norsys.netica.Streamer;
import norsys.neticaEx.aliases.Node;

public class DexArray {
	private static String[][][] isi = new String[3][6][2];
	
	public static void main(String [] args){
		
		long startTime = System.currentTimeMillis();
		
		try {
			System.out.println ("\nWelcome to Netica-J !\n");

	    	Node.setConstructorClass ("norsys.neticaEx.aliases.Node");
	    	//Environ env = 
	    	new Environ (null);
	    	
	    	Net   net          = new Net();
	    	net.setName("dexa");
	    	
	    	Node  tuberculosis   = new Node ("Tuberculosis",     "AtRisk, NotAtRisk",    net);
	    	Node  tempnode = null;
	    	
	    	
	    	NetsProperties arrc = new NetsProperties();
	    	isi = arrc.isic;
	    	
	    	int row = isi.length;
	    	int col = isi[0].length; //numOfState
	    	int statenum = col-1;
	    	
	    	for (int i=0; i<row; i++){
	    		String nodenames = isi[i][0][0]; //assume always put on the first col
	    		
	    		//building the states arg for Node declaration
	    		String states="";
	    		float cpts [] = new float [col];
	    		for (int j=1; j<col; j++){
	    			states = states+isi[i][j][0]+",";
	    			cpts[j-1] = (Float.parseFloat(isi[i][j][1]));
	    		}
	    		
	    		//Node declaration statement
	    		states = states.substring(0, (states.length()-1));
	    		tempnode = new Node (nodenames, states, net);
	    		
	    		//creating the structure: adding parent nodes to a child node
	    		tuberculosis.addLink(tempnode);
	    		
	    		//building the float array arg to fill in the default values of parent nodes
	    		
//	    		for (int k=0; k<statenum; k++){
//	    			cpts[k] = (Float.parseFloat(isi[i][k+1][1]));
//	    		}
	    		
	    		//filling in the default values of the parent nodes
	    		tempnode.setCPTable(cpts);
	    	}
	    	
	    	//14 nodes
	    	String stemp="";
		    for (int r=1; r<=statenum; r++){
				for (int s=1; s<=statenum; s++){
					for (int t=1; t<=statenum; t++){
//						for (int u=1; u<=statenum; u++){
//							for (int v=1; v<=statenum; v++){
//								for (int w=1; w<=statenum; w++){
//									for (int x=1; x<=statenum; x++){
//										for (int y=1; y<=statenum; y++){
//											for (int z=1; z<=statenum; z++){
//												for (int aa=1; aa<=statenum; aa++){
//													for (int ab=1; ab<=statenum; ab++){
//														for (int ac=1; ac<=statenum; ac++){
//															for (int ad=1; ad<=statenum; ad++){
//																for (int ae=1; ae<=3; ae++){
						stemp = isi[0][r][0]+","+isi[1][s][0]+","+isi[2][t][0];
						tuberculosis.setCPTable(stemp, 0.04, 0.96);
						//System.out.println(stemp);
//																}
//															}
//														}
//													}
//												}
//											}
//										}
//									}
//								}
//							}
//						}
					}
				}
			}
	    	
	    	Streamer stream = new Streamer ("Dexa.dne");
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
