package ObjectRepository.SDI;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
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
import TestData.GlobalTestData;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import SupportLibraries.Util;

public class ServiceDeliveryInsight {
	
	ExtentTest test;
	WebDriver driver;

	public ServiceDeliveryInsight(WebDriver driver, ExtentTest test)
	{
		this.driver = driver;
		this.test =test;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy (xpath="//*[@id='tblServiceStatistics']/caption")
	WebElement Statistics_60Days;
	
	@FindBy (xpath="//*[@id='tblServiceHistory']/caption")
	WebElement History_60Days;
	
	public void VerifyFields() throws IOException  	
	{	
			//Switching to the correct frame
			driver.switchTo().frame("opusReportContainer");
			
			Report.InfoTest(test, "Verify if 60days Statistics and history field is getting displayed");
			Util.ElementExist(driver,Statistics_60Days);
			Util.ElementExist(driver, History_60Days);
			
			driver.switchTo().defaultContent();
			
	    }

}
