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

	public static void main(String argv[]) {
		Multimap<String,String> myMultimap = ArrayListMultimap.create();

	    try {

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
			    myMultimap.put(nodes[1], states[1]);
			    System.out.println(nodes[1]+": "+states[1]);
//			    System.out.println(states[1]);
			}
			
			// Display content 	
			for (String key : myMultimap.keySet()) {
			     List<String> values = (List<String>) myMultimap.get(key);
			     System.out.println(key + ": " + values);
			}
				
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }

}