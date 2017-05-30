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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class ReadXMLFile2 {
	//this class is intended to decipher the SWRLs
	//public static ArrayList<Rules> ruleList = new ArrayList<Rules>();
	public static Multimap<String, String> ruleList = ArrayListMultimap.create();
	public static HashMap<String, Rules> ruleListObj = new HashMap<String, Rules>();
	public float Probs;
	public Rules rules;

	public ReadXMLFile2() {
		
	    try {
	    	
			File fXmlFile = new File("rule4.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			XPath xPath =  XPathFactory.newInstance().newXPath();
			
			String rulenamesEx, rulecommentsEx, rulebodyAttEx, rulebodyEx, ruleheadEx, rulealterEx, rulebaseEx, ruleprobEx; //all eXpressions
			
			//taking the name of rules
			rulenamesEx = "/rdf-RDF/rdf-Description/rdfs-label"; 
			//rulecommentsEx = "/rdf-RDF/rdf-Description/rdfs-comment";
			rulebodyEx = "/rdf-RDF/rdf-Description/swrl-body/.//swrl-classPredicate/@rdf-resource"; //Environment, Person
			rulebodyAttEx = "/rdf-RDF/rdf-Description/swrl-body/.//swrl-argument2/@rdf-resource"; //Male, Winter, Adults 
			//rulebaseEx = "/rdf-RDF/rdf-Description/swrl-body/.//swrl-propertyPredicate/@rdf-resource"; //Base
			rulealterEx = "/rdf-RDF/rdf-Description/swrl-head/.//swrl-propertyPredicate/@rdf-resource"; //Increase, Decrease, Set
			ruleheadEx = "/rdf-RDF/rdf-Description/swrl-head/.//swrl-argument1/@rdf-resource"; //Disease
			ruleprobEx = "/rdf-RDF/rdf-Description/swrl-head/.//swrl-argument2"; //Probability
			
			NodeList rulenamesList = (NodeList) xPath.compile(rulenamesEx).evaluate(doc, XPathConstants.NODESET);
			//NodeList rulecommentsList = (NodeList) xPath.compile(rulecommentsEx).evaluate(doc, XPathConstants.NODESET);
			NodeList rulebodyList = (NodeList) xPath.compile(rulebodyEx).evaluate(doc, XPathConstants.NODESET);
			NodeList rulebodyAttList = (NodeList) xPath.compile(rulebodyAttEx).evaluate(doc, XPathConstants.NODESET);
			//NodeList rulebaseList = (NodeList) xPath.compile(rulebaseEx).evaluate(doc, XPathConstants.NODESET);
			NodeList rulealterList = (NodeList) xPath.compile(rulealterEx).evaluate(doc, XPathConstants.NODESET);
			NodeList ruleheadList = (NodeList) xPath.compile(ruleheadEx).evaluate(doc, XPathConstants.NODESET);
			NodeList ruleprobList = (NodeList) xPath.compile(ruleprobEx).evaluate(doc, XPathConstants.NODESET);
			
			int numOfRules = rulenamesList.getLength();
			
			String keyrule, ruletype, ruleAtt, rulealter, ruleID, ruleIDprob;
			
			for (int i = 0; i < numOfRules ; i++) {
			    keyrule = rulenamesList.item(i).getFirstChild().getNodeValue();
			   // System.out.println(rulecommentsList.item(i).getFirstChild().getNodeValue());
			    String temprulebody[]=rulebodyList.item(i).getFirstChild().getNodeValue().split("#");
			    ruletype = "Normal";
			    
			//    String temprulebase[]=rulebaseList.item(i).getFirstChild().getNodeValue().split("#");
			    
			//    rulemap.put(keyrule, ruletype);
			    
			    String tempruleAtt[]=rulebodyAttList.item(i).getFirstChild().getNodeValue().split("#");
			    ruleAtt = tempruleAtt[1];
			    
			//    rulemap.put(keyrule, ruleAtt);
			    
			    String temprulealter[]=rulealterList.item(i).getFirstChild().getNodeValue().split("#");
			    rulealter = temprulealter[1];
			    
			    if(temprulebody[1].equals("Environment") && temprulealter[1].equals("setRisk")){
			    	ruletype = "Pathogen";
			    }
			    
			//    rulemap.put(keyrule, rulealter);
			    
			    String temprulehead[]=ruleheadList.item(i).getFirstChild().getNodeValue().split("#");
			    ruleID = temprulehead[1];
			    
			//    rulemap.put(keyrule, ruleID);
			    
			    ruleIDprob = ruleprobList.item(i).getFirstChild().getNodeValue();
			    
			//    rulemap.put(keyrule, ruleIDprob);
			    
			    //System.out.println(keyrule+" "+ruletype+" "+ruleAtt+" "+rulealter+" "+ruleID+" "+ruleIDprob);
			    
			    ruleList.put(ruleID, ruletype);
			    ruleList.put(ruleID, ruleAtt);
			    ruleList.put(ruleID, rulealter);
			    
			    ruleListObj.put(ruleID+"-"+ruleAtt, new Rules(keyrule, ruletype, ruleAtt, rulealter, ruleID, ruleIDprob)); 
			} 
			
			
			
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }

}