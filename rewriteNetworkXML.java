package kempot.domparser;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileInputStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class rewriteNetworkXML {

	public static void main(String argv[]) throws IOException {
	    FileInputStream f = new FileInputStream("network1.xml");
	    BufferedReader br = new BufferedReader(new InputStreamReader(f));
	    String strline, ar;
	   
	    FileWriter fw = new FileWriter("network2.xml");
	    BufferedWriter bw = new BufferedWriter(fw);
	    try {
			while ((strline = br.readLine()) != null)
			{
			    ar =strline.replace(':', '-');
			    //System.out.println(ar);
				bw.write(ar);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    br.close();
	    bw.close();
	}
}