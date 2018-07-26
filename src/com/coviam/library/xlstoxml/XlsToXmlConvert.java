package com.coviam.library.xlstoxml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.coviam.common.ModuleController;
import java.util.Random;
import static com.coviam.main.StartTestLinkAutomation.autoNumber;
public class XlsToXmlConvert extends ModuleController {
	
	//declare variables
	public  List<String> testCaseNameList= new ArrayList<String>();
	public  Set<String> testCaseNameListTemp= new HashSet<>();
	public static String testCaseName;
	public static int testCaseListCount=0;
  
	//
	
  public static int getNumber() {
      int tmp = autoNumber;
      autoNumber++;
      return tmp;
  }
	
	public static void XlsToXmlConvertor(String suiteName,String suiteId,String projectToCreate) throws ParserConfigurationException, TransformerException
	{
		 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		  Document doc = dBuilder.newDocument();
		  Random r = new Random();
		//xml code for test suite
	        Element rootElement = doc.createElement("testsuite");
	        doc.appendChild(rootElement);
	        Attr rootElementAttr1 = doc.createAttribute("id");
	        rootElementAttr1.setValue(String.valueOf(getNumber()));
	        rootElement.setAttributeNode(rootElementAttr1);
	        Attr rootElementAttr2= doc.createAttribute("name");
	        rootElementAttr2.setValue(suiteName);
	        rootElement.setAttributeNode(rootElementAttr2);
	        	//xml code for suite attribute 1
	        Element elementNodeOrder = doc.createElement("node_order");
	        elementNodeOrder.appendChild(
	         doc.createCDATASection("1"));
	        rootElement.appendChild(elementNodeOrder);
	        	//xml code for suite attribute 2
	        Element elementNodeOrder2 = doc.createElement("details");
	        elementNodeOrder2.appendChild(
	         doc.createCDATASection(" <p> "+suiteName+" </p> "));
	        rootElement.appendChild(elementNodeOrder2);
	        

	      //end of xml code
		  List<String> testCaseNameList= new ArrayList<String>();
		  Set<String> testCaseNameListTemp= new HashSet<>();
		
		  List<String> testDescriptionList= new ArrayList<String>();
		  Set<String> testDescriptionListTemp= new HashSet<>();
		  
		  List<String> testOutputList= new ArrayList<String>();
		  Set<String> testOutputListTemp= new HashSet<>();
		  
		testCaseListCount=0;
		
		APP_LOGS.debug(" Entered Convertor Module for suite : "+suiteId);
		
		int testCaseCount = Mxls.getRowCount(suiteId);
		
		for(int i=2;i<=testCaseCount;i++)
		{
			testCaseName = Mxls.getCellData(suiteId,0,i).trim();
			testCaseNameList.add(testCaseName);
			testCaseNameListTemp.addAll(testCaseNameList);		
		}
		
		
		testCaseCount=testCaseCount-1;
		testCaseNameList.clear();
		testCaseNameList.addAll(testCaseNameListTemp);
		testCaseListCount=testCaseNameListTemp.size();
		
	int tmpNodeOrder=5000;
	int tmpExternalId=0;
	for(int i=0;i<testCaseListCount;i++)
	{
		APP_LOGS.debug("Test Case Description and Action for Test Case Name:  "+testCaseNameList.get(i));APP_LOGS.debug("");
		
		//xml writer for Test Case Attribute
		Element testCaseElement = doc.createElement("testcase");
		rootElement.appendChild(testCaseElement);
        Attr testCaseElementAttr1 = doc.createAttribute("id");
        testCaseElementAttr1.setValue(String.valueOf(getNumber()));
        
        testCaseElement.setAttributeNode(testCaseElementAttr1);
        Attr testCaseElementAttr2= doc.createAttribute("name");
        testCaseElementAttr2.setValue(testCaseNameList.get(i));
        testCaseElement.setAttributeNode(testCaseElementAttr2);
        
        	//xml code for test case attribute 1
        tmpNodeOrder++;
        Element testCaseNodeOrder = doc.createElement("node_order");
        testCaseNodeOrder.appendChild(
         doc.createCDATASection(""+tmpNodeOrder+""));
        testCaseElement.appendChild(testCaseNodeOrder);
        	//xml code for test case attribute 2
        tmpExternalId++;
        Element testCaseNodeOrder2 = doc.createElement("external_id");
        testCaseNodeOrder2.appendChild(
         doc.createCDATASection(""+tmpExternalId+""));
        testCaseElement.appendChild(testCaseNodeOrder2);
      //xml code for test case attribute 3
        Element testCaseNodeOrder3 = doc.createElement("version");
        testCaseNodeOrder3.appendChild(
         doc.createCDATASection("1"));
        testCaseElement.appendChild(testCaseNodeOrder3);
        //xml code for test case attribute 4
        Element testCaseNodeOrder4 = doc.createElement("summary");
        testCaseNodeOrder4.appendChild(
         doc.createCDATASection(" "));
        testCaseElement.appendChild(testCaseNodeOrder4);
        //xml code for test case attribute 5
        Element testCaseNodeOrder5 = doc.createElement("preconditions");
        testCaseNodeOrder5.appendChild(
         doc.createCDATASection(" "));
        testCaseElement.appendChild(testCaseNodeOrder5);
        //xml code for test case attribute 6
        Element testCaseNodeOrder6 = doc.createElement("execution_type");
        testCaseNodeOrder6.appendChild(
         doc.createCDATASection("1"));
        testCaseElement.appendChild(testCaseNodeOrder6); 
        //xml code for test case attribute 7
        Element testCaseNodeOrder7 = doc.createElement("importance");
        testCaseNodeOrder7.appendChild(
         doc.createCDATASection("1"));
        testCaseElement.appendChild(testCaseNodeOrder7); 
        //xml code for test case attribute 8
        Element testCaseNodeOrder8 = doc.createElement("estimated_exec_duration");
        testCaseElement.appendChild(testCaseNodeOrder8);
    
      //xml code for test case attribute 10
        Element testCaseNodeOrder10 = doc.createElement("status");
        testCaseNodeOrder10.appendChild(
		         doc.createTextNode("1"));
        testCaseElement.appendChild(testCaseNodeOrder10);  
        //xml code for test case attribute 10
        Element testCaseNodeOrder11 = doc.createElement("is_open");
        testCaseNodeOrder11.appendChild(
		         doc.createTextNode("1"));
        testCaseElement.appendChild(testCaseNodeOrder11);
        //xml code for test case attribute 11
        Element testCaseNodeOrder12 = doc.createElement("active");
        testCaseNodeOrder12.appendChild(
		         doc.createTextNode("1"));
        testCaseElement.appendChild(testCaseNodeOrder12);
        
      //xml writer for Test Steps Attribute
		Element testStepsElement = doc.createElement("steps");
		testCaseElement.appendChild(testStepsElement);
        
		int stepTemp=1;
		for(int j=0;j<testCaseCount  ;j++)
		{		
			
			
				if(Mxls.getCellData(suiteId,0,j+2).trim().equals(testCaseNameList.get(i)))
				{		
					
					
					//System.out.println(" inside if ");
					int temp=i+1;			
					String testDescription = Mxls.getCellData(suiteId,1,j+2).trim();
					testDescriptionList.add(testDescription);
					//testDescriptionListTemp.addAll(testDescriptionList);
					
					String testOutput = Mxls.getCellData(suiteId,2,j+2).trim();
					testOutputList.add(testOutput);
					//testOutputListTemp.addAll(testOutputList);
					
					//xml writer for Test Step  
					Element testStepElement = doc.createElement("step");
					testStepsElement.appendChild(testStepElement);
					
					//xml code for test Step attribute 1
			        Element testStepNumber = doc.createElement("step_number");
			        testStepNumber.appendChild(
			         doc.createCDATASection(""+stepTemp+""));
			        testStepElement.appendChild(testStepNumber);
			        stepTemp++;
			      //xml code for test Step attribute 2
			        Element testStepActions = doc.createElement("actions");
			        testStepActions.appendChild(
			         doc.createCDATASection(" <p> "+testDescription+" </p> "));
			        testStepElement.appendChild(testStepActions);
			       
			      //xml code for test Step attribute 3
			        Element testStepOutput = doc.createElement("expectedresults");
			        testStepOutput.appendChild(
			         doc.createCDATASection(" <p> "+testOutput+" </p> "));
			        testStepElement.appendChild(testStepOutput);
			        //xml code for test Step attribute 4
			        Element testStepExecutionType = doc.createElement("execution_type");
			        testStepExecutionType.appendChild(
			         doc.createCDATASection("1"));
			        testStepElement.appendChild(testStepExecutionType); 
				}	
				
		}
		APP_LOGS.debug("");
		APP_LOGS.debug(" testDescriptionList : "+testDescriptionList);	
		APP_LOGS.debug(" testOutputList : "+testOutputList);
	
		testOutputList.clear();		
		testDescriptionList.clear();
			
		}	
	
 	//writing to file
	TransformerFactory transformerFactory =
    TransformerFactory.newInstance();
    Transformer transformer =
    transformerFactory.newTransformer();
    DOMSource source = new DOMSource(doc);
    StreamResult result =
    new StreamResult(new File(ModuleController.OutputPath+projectToCreate+"_"+suiteName+"_"+suiteId+".xml"));
    transformer.transform(source, result);
    // Output to console for testing
    StreamResult consoleResult =
    new StreamResult(System.out);
    transformer.transform(source, consoleResult);  
	
	}	
	
	
	public void XmlWriter(String suiteId,String projectToCreate,String testCaseDescription, String testCaseOutput)
	{
		
	}
	

}
