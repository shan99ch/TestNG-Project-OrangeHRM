package Utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScreenShot_Images {
	
	public void images(WebDriver driver) throws IOException
	{
	TakesScreenshot img = (TakesScreenshot)driver;
	File source=img.getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(source,new File("C:\\Users\\admin\\Desktop\\ScreenShot\\screen"+System.currentTimeMillis()+"jpg"));
	}
}
