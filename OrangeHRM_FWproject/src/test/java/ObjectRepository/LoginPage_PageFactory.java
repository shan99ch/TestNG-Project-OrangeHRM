package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage_PageFactory {
	WebDriver driver;
	
	public LoginPage_PageFactory(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this); //this is Standard code</
	}
	@FindBy(id="txtUsername")
	WebElement username;
	
	@FindBy(how=How.ID,using="txtPassword")
	WebElement password;
	
	@FindBy(id="btnLogin")
	WebElement login;
	
	public void login_factory(String ID, String PW)
	{
		username.sendKeys(ID);
		password.sendKeys(PW);
	                                                                                                          //Assert.assertTrue(false);   //Import Assert (org.testng)
		login.click();
	}

}

//btnLogin
