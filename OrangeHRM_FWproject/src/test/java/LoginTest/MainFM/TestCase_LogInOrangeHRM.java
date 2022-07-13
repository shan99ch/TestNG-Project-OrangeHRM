package LoginTest.MainFM;

import org.testng.annotations.Test;

import ObjectRepository.LoginPage_PageFactory;
import ObjectRepository.CrossBrowsers;
import Utilities.ScreenShot_Images;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class TestCase_LogInOrangeHRM {
	WebDriver driver1; 
	FileInputStream fis;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	DataFormatter formatter = new DataFormatter();
	
  @Test(dataProvider="getdata")   // Username , Password & login method
  public void login_TestCase(String ID, String PW) {
	  LoginPage_PageFactory lpf = new LoginPage_PageFactory(driver1) ;
	  lpf.login_factory(ID, PW);
	  
  }
 
   @BeforeMethod
  // @Parameters("driver1")
  public void setUp() throws Exception {     //webdriver & Url 
	   
  	 CrossBrowsers cb = new CrossBrowsers();
	driver1 =cb.setupTest();
  	driver1.manage().window().maximize();
  	  cb.RunCrossBrowserScript();
  	  Thread.sleep(2000);
  	  ScreenShot_Images ss = new ScreenShot_Images();
  	  Thread.sleep(2000);
  	  ss.images(driver1);
  }
 
  @AfterMethod
  public void tearDown() {
	  driver1.close();
  }
  
 @DataProvider(name="getdata")
  public Object[][] getdata() throws IOException {  //getting Data from Excel Sheet
	 File f = new File("C:\\home\\AST\\Selenium\\OrangeHRM_FWproject\\src\\test\\java\\Utilities\\HRM_Login.xlsx");
    fis = new FileInputStream(f);
	workbook = new XSSFWorkbook(fis); 
	sheet = workbook.getSheetAt(0);
	Row row1 = sheet.getRow(0);
	int RowNum = sheet.getPhysicalNumberOfRows();  // to find no. of rows
	int ColNum = row1.getLastCellNum();  // to find no. columns
	Object[][] Data = new Object[RowNum-1][ColNum];
	
	for(int i=0;i<RowNum-1;i++)
	{
		Row row = sheet.getRow(i+1);
		for(int j=0;j<ColNum;j++)
		{
			if(row==null)
			{
				Data[i][j]="";
			}
			else
			{
				Cell cell=row.getCell(j);
				if(cell==null)
				Data[i][j]="";
				
				else
				{
					 String value=formatter.formatCellValue(cell);  // formatter is a object name.//DataFormatter formatter = new DataFormatter();
					   Data[i][j]=value;  
				}
			}
			
		}
	}
	
	 return Data ;
  
	  
  }

 
}



