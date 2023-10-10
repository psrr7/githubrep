package driverfactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Commonfunctions.Functionlibrary;
import Utilities.Excelmethods;
import config.Apputil;

public class driverscripts extends Apputil{
String inputpath="./Fileinput/Datadriven.xlsx";
String outputpath="./Fileoutput/datadrivenResults.xlsx";
Boolean results=false;
ExtentReports report;
ExtentTest logger;
@Test
public void startTest() throws Throwable
{
	//define path of html
	report=new ExtentReports("./Report/login.html");
	//cerate object for excelfileutil class
	Excelmethods xl=new Excelmethods(inputpath);
	//count no of rows in login sheet
	int rows=xl.rowcount("login");
	Reporter.log("no of rows are:::"+rows,true);
	//itterate all rows in login sheet
	for(int i=1;i<=rows;i++)
	{
		//test case starts here
		logger=report.startTest("validate login");
		//read username and password cells
		String username=xl.getcelldata("login", i, 0);
	    String password=xl.getcelldata("login", i, 1);
	    //call login method from function library class
	    results=Functionlibrary.verify_login(username, password);
	    if(results)
	    {
	    	//if result is true write as login sucesses inti results cell
	    	xl.setcelldata("login", i, 2, "login sucesses",outputpath);
	    	//write pass inti status cell
	    	xl.setcelldata("login", i, 3, "PASS",outputpath);
	    	logger.log(LogStatus.PASS, "valid username and password");
		 }
	    else
	    {
	    	//take screenshot for fail test cases
	    	File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    	FileUtils.copyFile(screen, new File("./screenshot/iterations/"+i+"loginpage.png"));
	    	//capture error message
	    	String error_message=driver.findElement(By.xpath(sp.getProperty("objerror"))).getText();
	    	xl.setcelldata("login", i, 2,error_message,outputpath);
	    	xl.setcelldata("login", i,3,"FAIL",outputpath);
	    	logger.log(LogStatus.FAIL,error_message); 
	   	}
	    report.endTest(logger);
	    report.flush();
	}
}
}





















