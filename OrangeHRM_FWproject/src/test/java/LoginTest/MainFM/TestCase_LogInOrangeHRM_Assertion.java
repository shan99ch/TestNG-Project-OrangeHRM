package LoginTest.MainFM;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ObjectRepository.CrossBrowsers;
import ObjectRepository.LoginPage_PageFactory;
import Utilities.ExcelData;
import Utilities.ScreenShot_Images;

public class TestCase_LogInOrangeHRM_Assertion    {
	
	WebDriver driver1; 
	
	FileInputStream fis;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	
	
	DataFormatter formatter= new DataFormatter();
	
	  @Test(dataProvider="getdata") 
	  public void login(String ID, String PW) throws IOException  {
		 
		 LoginPage_PageFactory lpf = new LoginPage_PageFactory(driver1) ;
		lpf.login_factory(ID, PW);
		
		    }
	  
	  @BeforeMethod
	  //@Parameters("browser")
	  public void setUp() throws Exception {
		 
	  	 CrossBrowsers cb = new CrossBrowsers();
		driver1=cb.setupTest();
	  	driver1.manage().window().maximize();
	  	  cb.RunCrossBrowserScript();
	  	  Thread.sleep(2000);
	  	
	  	 
	  }
	 
	  @AfterMethod
	  public void tearDown(ITestResult result) throws InterruptedException, IOException {
		  if(result.FAILURE==result.getStatus())
		  {
			  ScreenShot_Images ss = new ScreenShot_Images();
		  	  ss.images(driver1);
		  	
		  }
		  driver1.close();
	  }
	  @DataProvider(name="getdata")
	  public Object[][] getdata() throws IOException {
		  ExcelData ed = new ExcelData();
		  return ed.getdata();
	  }
		 

}
