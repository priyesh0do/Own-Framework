package Opus;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Driver.BrowserOpen;
import SupportLibraries.Excel;
import SupportLibraries.LogClass;
import SupportLibraries.MailSupport;
import SupportLibraries.PropertiesRead;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalTestData;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseClass {
	
	public static WebDriver driver;
	public static ExtentTest test;
	public static ExtentReports extent;
	public static ExtentHtmlReporter htmlReporter;
	MailSupport ms = new MailSupport();
	public static String systemDir = System.getProperty("user.dir");
	public static String ExcelPath;
	public static Properties configProp;
	
	@BeforeSuite
	public void initializeWebdriver() throws IOException {
		configProp = PropertiesRead.readConfigProperty();
		htmlReporter = Report.StartHtmlReport(htmlReporter,GlobalTestData.browserName);
		extent = Report.StartExtentReport(htmlReporter,extent);
		DOMConfigurator.configure("log4j.xml");
	}
		
	/*@BeforeMethod
	public void launchApplication() throws MalformedURLException {
		{driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));}	
	}
	
	@AfterMethod
	public void quitWebdriver() {

		driver.quit();
		
	}
*/

	@AfterSuite
	public void sendReportEmail() {
		//ms.SendResultEmail();
		Report.endReport(extent);
	}
	
	


}
