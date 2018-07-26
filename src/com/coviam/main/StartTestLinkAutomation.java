package com.coviam.main;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.coviam.common.ModuleController;


public class StartTestLinkAutomation extends ModuleController {
	public static int autoNumber=500000;
	public static String Controller(String getPath,String filePath) throws ParserConfigurationException, TransformerException {
		// TODO Auto-generated method stub
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		//String path="//Users//avinash.t//Avinash//Automation//TestLink//DraftTemplate.xlsx";
		ModuleControl(getPath,filePath);
		APP_LOGS.debug("End Of XML Conversion");
		
		String logLocation=System.getProperty("user.home") + "/Desktop";
		System.setProperty("userApp.testlinkconv", logLocation);
		return ("Please check ");
	}

}
