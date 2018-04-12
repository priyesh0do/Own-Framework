package ObjectReposiotrySalesMonster;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import SupportLibraries.Report;

public class SalesMonster {

	ExtentTest test;
	WebDriver driver;
	
	public SalesMonster(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='email']")
	WebElement loginTextBox;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement passwordTextBox;
	
	@FindBy(xpath="//button[@type='submit' and contains(text(),'Log In')]")
	WebElement logInButton;
	
	@FindBy(xpath=".//a[contains(text(),'Sign in')]")
	WebElement loginLink;
	
	
	public void SalesMonsterLogin(String UserId,String Password) {
		
		try {
			loginTextBox.click();	
			loginTextBox.sendKeys(UserId);
			/*JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].value='"+UserId+"'", loginTextBox);*/
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].value='"+UserId+"';", loginTextBox);
			passwordTextBox.click();
			js.executeScript("arguments[0].value='"+Password+"';", passwordTextBox);
			//executor.executeScript("arguments[0].value='"+Password+"'", passwordTextBox);
			//logInButton.click();
			//Thread.sleep(2000);
			Report.PassTestScreenshot(test, driver, "Login Done Successfully with UserId "+UserId + " Password "+Password, "PassedLogin");
						
		} 
		catch (Exception e) {
			Report.FailTest(test, "Sales Monster Login Failed");
		}
		
	}
	
	public void RedirectedtoLoginPage() {
		
		try {
			loginLink.click();
			Report.PassTestScreenshot(test, driver, "Redirected to Login Page", "LoginPageRedirectionPassed");
		} catch (Exception e) {
			Report.FailTest(test, "Redirected to Login Page Failed");
		}
		
	}
	
}
