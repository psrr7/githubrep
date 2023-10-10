package config;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Apputil {
public static WebDriver driver;
public static Properties sp;
@BeforeTest
public static void setup() throws Throwable
{
	sp=new Properties();
	sp.load(new FileInputStream("./Propertyfile/datadriven.properties"));
	if(sp.getProperty("browser").equalsIgnoreCase("Chrome"))
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	else if(sp.getProperty("browser").equalsIgnoreCase("firefox"))
	{
		driver=new FirefoxDriver();
	}
	else
	{
		System.out.println("browser value is not matching");
	}
	
}
@AfterTest
public static void teardown()
{
	driver.quit();
}
}
