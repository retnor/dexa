import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadXMLFile2 {

	public static void main(String argv[]) {
		listUnwantedVars unwanted = new listUnwantedVars();
		List<String> swrlbody = new ArrayList<String>();
		List<String> swrlhead = new ArrayList<String>();

	    try {

			File fXmlFile = new File("rule4.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			XPath xPath =  XPathFactory.newInstance().newXPath();
			
			String rulenamesEx, rulecommentsEx, rulebodyEx, ruleheadEx, rulealterEx, rulebaseEx, ruleprobEx; //all eXpressions
			
			//taking the name of rules
			rulenamesEx = "/rdf-RDF/rdf-Description/rdfs-label"; 
			rulecommentsEx = "/rdf-RDF/rdf-Description/rdfs-comment";
			rulebodyEx = "/rdf-RDF/rdf-Description/swrl-body/.//swrl-classPredicate/@rdf-resource"; //Environment, Person
			rulebaseEx = "/rdf-RDF/rdf-Description/swrl-body/.//swrl-propertyPredicate/@rdf-resource"; //Base
			rulealterEx = "/rdf-RDF/rdf-Description/swrl-head/.//swrl-propertyPredicate/@rdf-resource"; //Increase, Decrease, Set
			ruleheadEx = "/rdf-RDF/rdf-Description/swrl-head/.//swrl-argument1/@rdf-resource"; //Disease
			ruleprobEx = "/rdf-RDF/rdf-Description/swrl-head/.//swrl-argument2"; //Probability
			
			NodeList rulenamesList = (NodeList) xPath.compile(rulenamesEx).evaluate(doc, XPathConstants.NODESET);
			NodeList rulecommentsList = (NodeList) xPath.compile(rulecommentsEx).evaluate(doc, XPathConstants.NODESET);
			NodeList rulebodyList = (NodeList) xPath.compile(rulebodyEx).evaluate(doc, XPathConstants.NODESET);
			NodeList rulebaseList = (NodeList) xPath.compile(rulebaseEx).evaluate(doc, XPathConstants.NODESET);
			NodeList rulealterList = (NodeList) xPath.compile(rulealterEx).evaluate(doc, XPathConstants.NODESET);
			NodeList ruleheadList = (NodeList) xPath.compile(ruleheadEx).evaluate(doc, XPathConstants.NODESET);
			NodeList ruleprobList = (NodeList) xPath.compile(ruleprobEx).evaluate(doc, XPathConstants.NODESET);
			
			int numOfRules = rulenamesList.getLength();
			
			for (int i = 0; i < numOfRules ; i++) {
			    System.out.println(rulenamesList.item(i).getFirstChild().getNodeValue());
			    System.out.println(rulecommentsList.item(i).getFirstChild().getNodeValue());
			    String temprulebody[]=rulebodyList.item(i).getFirstChild().getNodeValue().split("#");
			    System.out.println(temprulebody[1]);
			    String temprulebase[]=rulebaseList.item(i).getFirstChild().getNodeValue().split("#");
			    System.out.println(temprulebase[1]);
			    String temprulealter[]=rulealterList.item(i).getFirstChild().getNodeValue().split("#");
			    System.out.println(temprulealter[1]);
			    String temprulehead[]=ruleheadList.item(i).getFirstChild().getNodeValue().split("#");
			    System.out.println(temprulehead[1]);
			    System.out.println(ruleprobList.item(i).getFirstChild().getNodeValue());
			}
			
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }

}