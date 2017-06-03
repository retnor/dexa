package kempot.domparser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class ReadOntology {
	
	public Multimap<String,String> networkmap = ArrayListMultimap.create();
	public int nodecounter;
	public HashMap<String, Rules> ruleListObj = new HashMap<String, Rules>();
	public float Probs;
	public Rules rules;
	
	private List<String> values = null;
	private File fXmlFile = new File("rule2.xml");
	private DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	private DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	private Document doc = dBuilder.parse(fXmlFile);
		
	private XPath xPath =  XPathFactory.newInstance().newXPath();
	
	public ReadOntology() throws ParserConfigurationException, SAXException, IOException{
		try {   		
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
			}
			
			// Display content network
			for (String key : networkmap.keySet()) {
			     values = (List<String>) networkmap.get(key);
			     //System.out.println(key+": "+values);
			}
		
		} catch (Exception e) {
		e.printStackTrace();
	    }
	}
		
	public void execRule(){
		try{
			
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
//			    System.out.println(ruleID+"-"+ruleAtt);
//			    System.out.println(keyrule);
//			    System.out.println(rulealter);
//			    System.out.println(ruleIDprob);
			} 
			
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }
	}
