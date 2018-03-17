package ObjectRepository.General;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.aventstack.extentreports.ExtentTest;
import com.google.common.base.Function;

import SupportLibraries.Report;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import SupportLibraries.Util;
public class Login {
	
	ExtentTest test;
	WebDriver driver;

	public Login(WebDriver driver, ExtentTest test)
	{
		this.driver = driver;
		this.test =test;
		AjaxElementLocatorFactory factory=new AjaxElementLocatorFactory(driver, 20);
		PageFactory.initElements(factory, this);
	}
	

	@FindBy (id="user")
	WebElement Login_UserName;
	
	@FindBy (xpath="//*[@id='password']")
	WebElement Login_Password;
	
	@FindBy (xpath="//*[@id='login-form']/input[7]")
	WebElement Login_LoginButton;
	

	public void LoginSDI(String username, String password)  	
	{	
			
		    //WebDriverWait wait = new WebDriverWait(driver,30);
	        //wait.until(ExpectedConditions.visibilityOf(Login_UserName));
	    	System.out.println("Hello - About to login");
	    	Login_UserName.sendKeys(username);
	    	Login_Password.sendKeys(password);
	    	Login_LoginButton.click();
	    	System.out.println("Logged in");
	    	Report.PassTest(test, "User logged in with Username : "+username +" and Password : "+password);
	    }

}
