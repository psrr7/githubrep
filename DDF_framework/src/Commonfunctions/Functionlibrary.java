package Commonfunctions;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.Apputil;

public class Functionlibrary extends Apputil{
public static boolean verify_login(String username,String password)
{
	driver.get(sp.getProperty("url"));
	driver.findElement(By.xpath(sp.getProperty("objusername"))).sendKeys(username);
	driver.findElement(By.xpath(sp.getProperty("objpassword"))).sendKeys(password);
	driver.findElement(By.xpath(sp.getProperty("objlogin"))).click();
	String expected="dashboard";
	String actual=driver.getCurrentUrl();
	if(actual.contains(expected))
	{
		Reporter.log("login sucesses:::"+expected+" "+actual,true);
		return true;
	}
	else
	{
		Reporter.log("login failed:::"+expected+" "+actual,true);
	    return false;
	}
}
}
