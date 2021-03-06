package kempot.domparser;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.HashMap;


public class ReadXMLFile2 {
	//this class is intended to decipher the SWRLs
	
	public static HashMap<String, Rules> ruleListObj = new HashMap<String, Rules>();
	public float Probs;
	public Rules rules;

	public ReadXMLFile2(){
		
	    try {   	
			File fXmlFile = new File("rule4.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			XPath xPath =  XPathFactory.newInstance().newXPath();
			
			String rulenamesEx, rulebodyAttEx, ruleheadEx, rulealterEx, ruleprobEx; //all eXpressions
			
			//taking the name of rules
			rulenamesEx = "/rdf-RDF/rdf-Description/rdfs-label"; 
			rulebodyAttEx = "/rdf-RDF/rdf-Description/swrl-body/.//swrl-argument2/@rdf-resource"; //Male, Winter, Adults 
			rulealterEx = "/rdf-RDF/rdf-Description/swrl-head/.//swrl-propertyPredicate/@rdf-resource"; //Increase, Decrease, Set
			ruleheadEx = "/rdf-RDF/rdf-Description/swrl-head/.//swrl-argument1/@rdf-resource"; //Disease
			ruleprobEx = "/rdf-RDF/rdf-Description/swrl-head/.//swrl-argument2"; //Probability
			
			NodeList rulenamesList = (NodeList) xPath.compile(rulenamesEx).evaluate(doc, XPathConstants.NODESET);
			NodeList rulebodyAttList = (NodeList) xPath.compile(rulebodyAttEx).evaluate(doc, XPathConstants.NODESET);
			NodeList rulealterList = (NodeList) xPath.compile(rulealterEx).evaluate(doc, XPathConstants.NODESET);
			NodeList ruleheadList = (NodeList) xPath.compile(ruleheadEx).evaluate(doc, XPathConstants.NODESET);
			NodeList ruleprobList = (NodeList) xPath.compile(ruleprobEx).evaluate(doc, XPathConstants.NODESET);
			
			int numOfRules = rulenamesList.getLength();
			
			String keyrule, ruleAtt, rulealter, ruleID, ruleIDprob;
			
			for (int i = 0; i < numOfRules ; i++) {
			    keyrule = rulenamesList.item(i).getFirstChild().getNodeValue();	    
			    String tempruleAtt[]=rulebodyAttList.item(i).getFirstChild().getNodeValue().split("#");
			    ruleAtt = tempruleAtt[1];
			    String temprulealter[]=rulealterList.item(i).getFirstChild().getNodeValue().split("#");
			    rulealter = temprulealter[1];
			    String temprulehead[]=ruleheadList.item(i).getFirstChild().getNodeValue().split("#");
			    ruleID = temprulehead[1];   
			    ruleIDprob = ruleprobList.item(i).getFirstChild().getNodeValue();
			    ruleListObj.put(ruleID+"-"+ruleAtt, new Rules(keyrule, ruleAtt, rulealter, ruleID, ruleIDprob)); 
			} 
			
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }

}