package ObjectRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

public class CrossBrowsers {
	
	
	String browser="chrome";
	WebDriver driver;
	@BeforeTest
	//@Parameters("browser")
	public WebDriver setupTest() throws Exception
	{
		//this.browser= browser;
		Properties p = new Properties();
		File propfile = new File("C:\\home\\AST\\Selenium\\OrangeHRM_FWproject\\FMconfig.Properties");
		FileInputStream propfis = new FileInputStream(propfile);
		p.load(propfis);
		//Verify if parameter passed from TestNG is Internet Explorer
		if(browser.equalsIgnoreCase("ie"))
		{
		    //create IE instance
			System.setProperty("webdriver.ie.driver",p.getProperty("ie"));
			driver = new InternetExplorerDriver();
		}		
		
		//Verify if parameter passed from TestNG is Chrome
		else if(browser.equalsIgnoreCase("chrome"))
		{
			
			System.setProperty("webdriver.chrome.driver",p.getProperty("chrome"));
			driver = new ChromeDriver();
			
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			
			System.setProperty("webdriver.gecko.driver",p.getProperty("firefox"));
			driver = new FirefoxDriver();
		}
		else
		{
			//If no browser passed throw exception
			throw new Exception("No browser specified");
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void RunCrossBrowserScript() throws InterruptedException
	{
		driver.get("https://opensource-demo.orangehrmlive.com/");		
	}
}