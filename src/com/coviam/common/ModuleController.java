package com.coviam.common;
/**
*
* @author avinash.t
* @version 1.0
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;

import com.coviam.library.xlstoxml.XlsToXmlConvert;

import xls.ShineXlsReader;

public class ModuleController   
{
	public static 	ShineXlsReader Mxls=null;
	public static int imain;
	public static int j;
	public static String SuiteName;
	public static List<String> projectNameListTemp= new ArrayList<String>();
	public static Set<String> projectNameList= new HashSet<>();
	public static List<String> suiteNameListTemp= new ArrayList<String>();
	public static Set<String> suiteNameList= new HashSet<>();
	public static String suiteId;
	public static List<String> suiteIdListTemp= new ArrayList<String>();
	public static Set<String> suiteIdList= new HashSet<>();
	
	public static List<String> testCaseNameListTemp= new ArrayList<String>();
	public static Set<String> testCaseNameList= new HashSet<>();
	public static String projectName;
	public static String suiteName;
	public static String testCaseName;
	public static int testcount;
	public static Logger APP_LOGS=null;
	public static String OutputPath=null; 

@SuppressWarnings("unchecked")
public static void ModuleControl( String path,String filePath) throws ParserConfigurationException, TransformerException
{
	APP_LOGS=Logger.getLogger("TestLinkLogger");
		
	OutputPath=filePath;
		Mxls = new ShineXlsReader(path);
			int Modulecount = Mxls.getRowCount("Mainsheet");
			int Modulecount_temp=Modulecount-1; 
		APP_LOGS.debug("XLS file has been loaded...");
		APP_LOGS.debug("");
		int projectCount=0;
			for(imain=2;imain<=Modulecount;imain++)
			{
				projectName = Mxls.getCellData("Mainsheet",0,imain).trim();
				projectNameListTemp.add(projectName);
				projectNameList.addAll(projectNameListTemp);
				
			}
			projectNameListTemp.clear();
			projectNameListTemp.addAll(projectNameList);
			projectCount=projectNameListTemp.size();
			System.out.println(projectNameListTemp);
			APP_LOGS.debug("There are "+projectCount+" projects added.");
			
			
			int suiteCount=0;
			int suiteCountTemp = Mxls.getRowCount("Suite_Id_Mapping");
			for(imain=2;imain<=suiteCountTemp;imain++)
			{
				SuiteName = Mxls.getCellData("Suite_Id_Mapping",2,imain).trim();
				suiteNameListTemp.add(SuiteName);
				suiteNameList.addAll(suiteNameListTemp);	
				suiteId = Mxls.getCellData("Suite_Id_Mapping",0,imain).trim();
				suiteIdListTemp.add(suiteId);
				suiteIdList.addAll(suiteIdListTemp);			
			}
		
			suiteCount=suiteIdListTemp.size();
			APP_LOGS.debug("There are "+suiteCount+" suites added.");
			APP_LOGS.debug(suiteIdListTemp);
			APP_LOGS.debug(suiteNameListTemp);
			for(int pcount=0; pcount<projectCount;pcount++)
			{
				String projectToCreate=projectNameListTemp.get(pcount);
				APP_LOGS.debug("");APP_LOGS.debug("");
				APP_LOGS.debug(" ********************Project considered : "+projectToCreate+"***************************");
				APP_LOGS.debug("");APP_LOGS.debug("");
				for(int scount=0; scount<suiteCount;scount++)
				{		
					if(Mxls.getCellData("Suite_Id_Mapping",1,scount+2).trim().equals(projectToCreate))
					{	
						APP_LOGS.debug("Suite Created to be created :  "+suiteNameListTemp.get(scount));APP_LOGS.debug("");
						APP_LOGS.debug("Suite Id will  be : "+suiteIdListTemp.get(scount));APP_LOGS.debug("");
						XlsToXmlConvert.XlsToXmlConvertor(suiteNameListTemp.get(scount), suiteIdListTemp.get(scount),projectToCreate);
					}	
				}
			}
			
}
			//calling for xml creation
			
					

}
		


