package kempot.trialcode;

import java.text.NumberFormat;

import norsys.netica.Environ;
import norsys.netica.Net;
import norsys.netica.NeticaException;
import norsys.netica.Streamer;
import norsys.neticaEx.aliases.Node;

public class threedarray {
	private static String[][][] isi = new String[5][4][2];
	//private static String[][][] isi = new String[4][3][2];
	//private static String[][][] isi = new String[3][3][2];
	
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
	    	Node  anthrax		 = new Node ("Anthrax", "AtRisk, NotAtRisk", net);
	    	Node  eye		= new Node ("EyeColour", "Hazel, Brown", net);
	    	Node  tempnode = null;
	    	
	    	anthrax.addLink(eye);
	    	
	    	NetsProperties arrc = new NetsProperties();
	    	isi = arrc.isic;
	    	
	    	int row = isi.length;
	    	int col = isi[0].length; //3
	    	int dens = 2; //numOfStates
	    	
	    	for (int i=0; i<row; i++){
	    		String nodenames = isi[i][0][0]; //assume always put on the first col
	    		
	    		//building the states arg for Node declaration
	    		String states="";
	    		for (int j=1; j<col; j++){
	    			states = states+isi[i][j][0]+",";
	    		}
	    		
	    		//Node declaration statement
	    		states = states.substring(0, (states.length()-1));
	    		tempnode = new Node (nodenames, states, net);
	    		
	    		//creating the structure: adding parent nodes to a child node
	    		tuberculosis.addLink(tempnode);
	    		
	    		//building the float array arg to fill in the default values of parent nodes
	    		float cpts [] = new float [dens];
	    		for (int k=0; k<dens; k++){
	    			cpts[k] = (Float.parseFloat(isi[i][k+1][1]));
	    		}
	    		
	    		//filling in the default values of the parent nodes
	    		tempnode.setCPTable(cpts);
	    	}
	    	
//	    	//3 nodes
//	    	String stemp="";
//		    for (int r=1; r<=2; r++){
//				for (int s=1; s<=2; s++){
//					for (int t=1; t<=2; t++){
//						//for (int u=1; u<=2; u++){
//							stemp = isi[0][r][0]+","+isi[1][s][0]+","+isi[2][t][0];
//							tuberculosis.setCPTable(stemp, 0.04, 0.96);
//							//System.out.println(stemp);
//						//}
//					}
//				}
//			}
	    	
//	    	//4 nodes
//	    	String stemp="";
//		    for (int r=1; r<=2; r++){
//				for (int s=1; s<=2; s++){
//					for (int t=1; t<=2; t++){
//						for (int u=1; u<=2; u++){
//							stemp = isi[0][r][0]+","+isi[1][s][0]+","+isi[2][t][0]+","+isi[3][u][0];
//							tuberculosis.setCPTable(stemp, 0.04, 0.96);
//							//System.out.println(stemp);
//						}
//					}
//				}
//			}
		    
	    	//5 nodes
	    	String stemp="";
		    for (int r=1; r<=2; r++){
				for (int s=1; s<=2; s++){
					for (int t=1; t<=2; t++){
						for (int u=1; u<=2; u++){
							for (int v=1; v<=2; v++){
								stemp = isi[0][r][0]+","+isi[1][s][0]+","+isi[2][t][0]+","+isi[3][u][0]+","+isi[4][v][0];
								tuberculosis.setCPTable(stemp, 0.04, 0.96);
								//System.out.println(stemp);
							}
						}
					}
				}
			}
	    	
	    	anthrax.setCPTable("Hazel", 0.05, 0.95);
	    	
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
