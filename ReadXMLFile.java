package kempot.domparser;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.io.File;
import java.util.List;

public class ReadXMLFile {
	public static Multimap<String,String> networkmap = ArrayListMultimap.create();
	public static int nodecounter;

	public ReadXMLFile() {
		//this class is intended to retrieve nodes and state names
	    try {
	    	List<String> values = null;

			File fXmlFile = new File("network2.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			XPath xPath =  XPathFactory.newInstance().newXPath();
			
			String stateExpression, nodeExpression;
			
			nodeExpression = "/rdf-RDF/owl-NamedIndividual/rdf-type/@rdf-resource"; //nodes
			stateExpression = "/rdf-RDF/owl-NamedIndividual/@rdf-about"; //states
			
			
			NodeList nodeList = (NodeList) xPath.compile(nodeExpression).evaluate(doc, XPathConstants.NODESET);
			NodeList stateList = (NodeList) xPath.compile(stateExpression).evaluate(doc, XPathConstants.NODESET);
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				String roughnodes = nodeList.item(i).getFirstChild().getNodeValue();
				String roughstates = stateList.item(i).getFirstChild().getNodeValue();
			    String nodes[]=roughnodes.split("#");
			    String states[]=roughstates.split("#");
			    networkmap.put(nodes[1], states[1]);
//			    System.out.println(nodes[1]+": "+states[1]);
//			    System.out.println(states[1]);
			}
			
			// Display content
			for (String key : networkmap.keySet()) {
			     values = (List<String>) networkmap.get(key);
//			     System.out.println(values.size()); //ukuran statenum
//			     System.out.println(key+" "+values); //print node: states
			}
			//System.out.println(nodecounter);
			
				
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }

}