package ObjectRepository.General;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.aventstack.extentreports.ExtentTest;
import com.google.common.base.Function;

import SupportLibraries.Report;
import TestData.GlobalTestData;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import SupportLibraries.Util;

public class HomePage {
	
	ExtentTest test;
	WebDriver driver;

	public HomePage(WebDriver driver, ExtentTest test)
	{
		this.driver = driver;
		this.test =test;
		PageFactory.initElements(driver, this);
	}
	
	String ReportName;

	public void ClickOnReport(String reportname) throws InterruptedException  	
	{	
		Report.InfoTest(test, "Click on "+reportname+" Link");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='inner']/div/table/tbody/tr/td/div/ul/li/a[(text())='"+reportname+"']")).click();
		System.out.println(reportname+" Link was clicked");
		Report.PassTest(test, "User is able to click on the " +reportname+" Report");
	}
	
	public void verifyAllLinks()
	{
		List<WebElement> links=findAllLinks(driver);
		System.out.println("Total number of links on the page : "+links.size());
		for( int i=0;i<links.size();i++){
			WebElement element= links.get(i); 
	    	try

	    	{
				String url=element.getAttribute("href");
		        System.out.println("URL: " + element.getAttribute("href")+ " " + verifyLinkActive(url));
	    	}
	    	catch(Exception exp)

	    	{
	    		System.out.println("At " + element.getAttribute("innerHTML") + " Exception occured -&gt; " + exp.getMessage());	    		
	    	}
	    }
	}
	
	 public static List<WebElement> findAllLinks(WebDriver driver)
	 
	  {
		  List<WebElement> elementList = new ArrayList();
	 
		  elementList = driver.findElements(By.tagName("a"));
	 
		  elementList.addAll(driver.findElements(By.tagName("img")));
	 
		  List<WebElement> finalList = new ArrayList(); ;
	 
		  for (WebElement element : elementList)
	 
		  {	 
			  if(element.getAttribute("href") != null)
	 
			  {	 
				  finalList.add(element);	 
			  }		  	 
		  }	
	 
		  return finalList;	 
	  }
	 
	 public static String verifyLinkActive(String linkUrl)
		{
	        try 
	        {
	           URL url = new URL(linkUrl);
	           
	           HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
	           
	           httpURLConnect.setConnectTimeout(3000);
	           
	           httpURLConnect.connect();
	           
	           if(httpURLConnect.getResponseCode()==200)
	           {
	               //System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
	        	   
	               return httpURLConnect.getResponseCode()+" "+httpURLConnect.getResponseMessage();
	            }
	          if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)  
	           {
	               //System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
	               return httpURLConnect.getResponseCode()+" "+httpURLConnect.getResponseMessage();
	            }
	          else
	          {
	        	  return httpURLConnect.getResponseCode()+" "+httpURLConnect.getResponseMessage();
	          }
	        } catch (Exception e) {
	          
	        	System.out.println(e.getMessage());
	               return "Link is not working";
	        }
	    } 

}
