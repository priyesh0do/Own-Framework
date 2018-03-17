package ObjectRepository.Confirmation;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.joda.time.Days;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import SupportLibraries.Report;
import SupportLibraries.Util;

public class Opus_Confirmations_Site_Detail {
	
	
	WebDriver driver;
	ExtentTest test;
	
	
	public Opus_Confirmations_Site_Detail(WebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);

}
	

	// Site Detail
	//selectedSite
	@FindBy(xpath="//*[@id='siteFilterdiv']/button")
	WebElement Opus_Conf_Site;
	
	//selectedLOB
	@FindBy(xpath="//*[@id='LOBFilterdiv']/button")
	WebElement Opus_Conf_Site_selectedLOB;
	
	//selectedStatus
	@FindBy(xpath="//*[@id='statusFilterdiv']/button")
	WebElement Opus_Conf_Site_selectedStatus;
	
	//Services start date 
	@FindBy(xpath="//*[@id='from']")
	WebElement Opus_Conf_Site_StartDate;
	
	//Services End Date
	@FindBy(xpath="//*[@id='to']")
	WebElement Opus_Conf_Site_EndDate;
	
	//GoButton
	@FindBy(id="getRouteSummary")
	WebElement Opus_Conf_Site_Go;
	
	//Reset
	@FindBy(xpath="//*[@id='resetHaulingsiteInfo']")
	WebElement Opus_Conf_Site_Reset;
	
	//SiteSearch
	@FindBy(xpath="(//*[@class='ui-multiselect-filter']/input)[1]")
	////*[@id='ng-app']/body/div[5]/div/div/input
	//(//*[@class='ui-multiselect-filter']/input)[1]
	//(//*[@class='ui-multiselect-filter'])[1]
	WebElement Opus_Conf_Site_SiteSearch;
	
	//SiteSearchClose
	@FindBy(xpath="(//*[@class='ui-icon ui-icon-circle-close'])[1]")
	WebElement Opus_Conf_Site_SiteSearchClose;
	
  //LobSearch
	@FindBy(xpath="(//*[@class='ui-multiselect-filter']/input)[2]")
	WebElement Opus_Conf_Site_LobSearch;
	
	//ConfirmationStatus
	@FindBy(xpath="(//*[@class='ui-multiselect-filter'])[3]")
	WebElement Opus_Conf_Site_ConfirmationStatus;
	
	
	//LobSearchClose
	@FindBy(xpath="(//*[@class='ui-icon ui-icon-circle-close'])[2]")
	WebElement Opus_Conf_Site_LobSearchClose;
	
	//ConfirmationStatusClose
	@FindBy(xpath="(//*[@class='ui-icon ui-icon-circle-close'])[3]")
	WebElement Opus_Conf_Site_ConfirmationStatusClose;
	
	//ConfirmationTab
	@FindBy(xpath="//*[@href='/opus-report/report/report?reportname=Confirmation' and contains(text(),'Confirmation')]")
	WebElement ConfirmationsTab;
	
	//NoRoute
	@FindBy(xpath="//*[@class='mesg_row' and contains(text(),'No  Route Summary found.')]")
	WebElement NoRecords;
	
	//All
	@FindBy(xpath="//*[@id='ui-multiselect-selectedLOB-option-0' and @type='checkbox']")
	WebElement Allchekbox;
	
	
	//Commercial
	@FindBy(xpath="//*[@id='ui-multiselect-selectedLOB-option-1' and @type='checkbox']")
	WebElement Commercialchkbox;
	
	
	//Residential
	@FindBy(xpath="//*[@id='ui-multiselect-selectedLOB-option-2' and @type='checkbox']")
	WebElement Residentialchekbox;
	
	
	//RollOff
	@FindBy(xpath="//*[@id='ui-multiselect-selectedLOB-option-3' and @type='checkbox']")
	WebElement RollOffbox;
	
	
	//Bagster
	@FindBy(xpath="//*[@id='ui-multiselect-selectedLOB-option-4' and @type='checkbox']")
	WebElement Bagsterchekbox;
	
	//ConfirmationStatusAll
	@FindBy(xpath="//*[@id='ui-multiselect-selectedStatus-option-0' and@type='checkbox']")
	WebElement ConfirmationStatusAll;
	
	
	//ConfirmationStatusConfirmed
	@FindBy(xpath="//*[@id='ui-multiselect-selectedStatus-option-1' and@type='checkbox']")
	WebElement ConfirmationStatusConfirmed;
	
	
	//ConfirmationStatusFlagged
	@FindBy(xpath="//*[@id='ui-multiselect-selectedStatus-option-2' and@type='checkbox']")
	WebElement ConfirmationStatusFlagged;
		
	
	//ConfirmationStatusOpen
	@FindBy(xpath="//*[@id='ui-multiselect-selectedStatus-option-3' and@type='checkbox']")
	WebElement ConfirmationStatusOpen;
	
	//AfterResetGoButtonDisbale
	@FindBy(xpath="//*[@id='getRouteSummary' and @type='button']")
	WebElement AfterResetGoButtonDisbale;
	//CalenderWebelement
	
	@FindBy(xpath="//*[@id='ui-datepicker-div']")
	WebElement CalenderWebelement;
	
	@FindBy(className = "ui-datepicker-month")
	WebElement datePickerMonth;

	@FindBy(className = "ui-datepicker-year")
	WebElement datePickerYear;
	
	@FindBy(xpath="//*[@id='tblRouteSummary' and @role='grid']")
    WebElement Validate;
	
	@FindBy(id="confirmedCount")
	WebElement confirmedCount;
	
	@FindBy(id="flaggedCount")
	WebElement flaggedCount;
	
	@FindBy(id="openCount")
	WebElement openCount;
	
	@FindBy(id="totalCount")
	WebElement totalCount;
	
	@FindBy(xpath="//*[@id='tblRouteSummary' and @role='grid']/tbody/tr[1]")
	WebElement VerifyRecordsInRouteSummary;
	
	@FindBy(xpath="//*[@id='printCoverSheet']")
	WebElement printCoverSheet;
	
	@FindBy(xpath="//*[@id='printRouteSheet']")
	WebElement CustomerTicket;
	
	@FindBy(xpath="//*[@id='changeLog']")
	WebElement ShowLog;
	
	@FindBy(xpath="//*[@id='workload']")
	WebElement ShowZeroWorkload;
	
	@FindBy(xpath="//*[@id='tblRouteSummary_filter']/label/input")
	WebElement SearchFilter;
	
	@FindBy(xpath="//*[@id='tblRouteSummary']/tbody/tr/td[5]/a")
	WebElement ClickFirstRouteID;
	
		
	public void RedirectedtoConfirmation() throws InterruptedException {

try {

		Thread.sleep(2000);	
		ConfirmationsTab.click();
		Thread.sleep(2000);
}
		catch(Exception e)
		{
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Not able to Click on Confirmation Tab");
		}
		
	}

	public void Opus_Confirmation_Reset() throws InterruptedException {
 try {
		 
		driver.switchTo().frame("opusReportContainer");
		
		Thread.sleep(1000);
		Opus_Conf_Site_Reset.click();
		
		Thread.sleep(1000);
		
	boolean exp=AfterResetGoButtonDisbale.getAttribute("disabled").equals("true");
	
	//String Exp1="true";
	 //System.out.println("Exp Text >>>" + Exp);
	
	  if (exp==true) {
		System.out.println("Reset Working Fine");
	}
	  else {
		  System.out.println("Reset Not Working Fine");
	}
	  driver.switchTo().defaultContent();
 }
	  catch(Exception e)
		{
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Not able to Click on Reset Button");
		}
	  
	} 
	 
	public void Opus_Confirmations_SelectSite(String SiteName) throws InterruptedException {

	try {	
		/// Redirected to Opus Report Container Frame
			
		driver.switchTo().frame("opusReportContainer");
		Opus_Conf_Site.click();
		Thread.sleep(2000);
		
		// Enter Data inside of Select Search
		//Opus_Conf_Site_SiteSearch.clear();
		if (Opus_Conf_Site_SiteSearch.isDisplayed() && Opus_Conf_Site_SiteSearch.isEnabled() ) {
			Thread.sleep(2000);
			Opus_Conf_Site_SiteSearch.sendKeys(SiteName);
					
		}
		Thread.sleep(2000);
		
		driver.findElement(By.xpath(
				"//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'" + SiteName + "')]"))
				.click();
		Thread.sleep(2000);
		
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
	}
        catch(Exception e)
		{
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Not working fine for Site >>" + SiteName);
		}
	}
	
	
    public void Opus_Confirmations_SelectLOB(String All, String Com , String Res, String RollOff, String Bagster) {

  try {
    	
    	driver.switchTo().frame("opusReportContainer");
    	
    	Opus_Conf_Site_selectedLOB.click();
    	
    	Thread.sleep(1000);
    	
    	if (All !="") {
   
    		Allchekbox.click();
    		Thread.sleep(1000);
    		Allchekbox.click();
    		
		}
    	
    	if (Com !="") {
    		Thread.sleep(1000);
    		Commercialchkbox.click();
		}
    	
    	if (Res !="") {
    		Thread.sleep(1000);
    		Residentialchekbox.click();
		}
    	
    	if (RollOff !="") {
    		Thread.sleep(1000);
    		RollOffbox.click();
		}
    	
    	if (Bagster !="") {
    		Thread.sleep(1000);
    		Bagsterchekbox.click();
		}
    	
    	Thread.sleep(2000);
    
    Opus_Conf_Site_LobSearchClose.click();
    Thread.sleep(1000);
    	driver.switchTo().defaultContent();
  }
    	catch(Exception e)
		{
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Not working fine for LOB " );
		}
    }
   
    
    public void Opus_Confirmations_SelectConfirmationStatus(String All, String Confirmed, String Flagged, String Open) {

  try {
    	
    	driver.switchTo().frame("opusReportContainer");
    	Thread.sleep(1000);
    	
    	Opus_Conf_Site_selectedStatus.click();
    	Thread.sleep(1000);
    	if (All!="") {
    		ConfirmationStatusAll.click();
    		Thread.sleep(1000);
    		ConfirmationStatusAll.click();
		}
    	if (Confirmed!="") {
    		ConfirmationStatusConfirmed.click();
    		Thread.sleep(1000);
		}
    	if (Flagged!="") {
    		ConfirmationStatusFlagged.click();
    		Thread.sleep(1000);
			
		}
    	if (Open!="") {
    		ConfirmationStatusOpen.click();
    		Thread.sleep(1000);
		}
    	
    	Thread.sleep(1000);
    	Opus_Conf_Site_ConfirmationStatusClose.click();
    	Thread.sleep(1000);
    	driver.switchTo().defaultContent();
    }
    	catch(Exception e)
		{
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Not working fine for ConfirmationStatus " );
		}
    }
    
    public void Opus_Confirmations_FromDate(String FromDate) {
    	
    	driver.switchTo().frame("opusReportContainer");
    	Opus_Conf_Site_StartDate.click();
    	//Opus_Conf_Site_StartDate.clear();
    	Opus_Conf_Site_StartDate.sendKeys(FromDate);
    	
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	driver.switchTo().defaultContent();
    	
    	
    }

    public void Opus_Confirmations_ToDate(String FromDate) {

    	
    	driver.switchTo().frame("opusReportContainer");
    	Opus_Conf_Site_EndDate.click();
    	//Opus_Conf_Site_EndDate.clear();
    	Opus_Conf_Site_EndDate.sendKeys(FromDate);
    	
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	driver.switchTo().defaultContent();
    	
    	
    }
 
  
   public void Opus_Confirmations_selectDateFromDatePicker(String inputDate) throws InterruptedException, IOException, ParseException {
		try {
	
			driver.switchTo().frame("opusReportContainer");
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date date = format.parse(inputDate);
			SimpleDateFormat dayFormat = new SimpleDateFormat("d");
			String day = dayFormat.format(date);
			SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
			String month = monthFormat.format(date);
			SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
			String year = yearFormat.format(date);
			Opus_Conf_Site_StartDate.click();
			Thread.sleep(1000);
			Util.SelectOption(driver, datePickerYear, year);
			Util.SelectOption(driver, datePickerMonth, month);
			driver.findElement(By.linkText(day)).click();
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			Report.PassTest(test, "Date : " + inputDate + " was entered in To Date");

		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Not able to select date from datepicker" + inputDate );

		}
		
		driver.switchTo().defaultContent();
	}
   
   
   public void Opus_Confirmations_selectDateToDatePicker(String inputDate) throws InterruptedException, IOException, ParseException {
		try {
			driver.switchTo().frame("opusReportContainer");
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date date = format.parse(inputDate);
			SimpleDateFormat dayFormat = new SimpleDateFormat("d");
			String day = dayFormat.format(date);
			SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
			String month = monthFormat.format(date);
			SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
			String year = yearFormat.format(date);
			Opus_Conf_Site_EndDate.click();
			Thread.sleep(1000);
			Util.SelectOption(driver, datePickerYear, year);
			Util.SelectOption(driver, datePickerMonth, month);
			driver.findElement(By.linkText(day)).click();
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			Report.PassTest(test, "Date : " + inputDate + " was entered in To Date");

		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Not able to select date from datepicker" + inputDate);

		}
		  driver.switchTo().defaultContent();
	}
                   
   public void Opus_Confirmations_SelectGo() throws InterruptedException {


try {
	   
	   driver.switchTo().frame("opusReportContainer");
	   Thread.sleep(2000);
	    Opus_Conf_Site_Go.click();
	    Thread.sleep(2000);
	    WebDriverWait w=new WebDriverWait(driver, 60);
	    w.until(ExpectedConditions.elementToBeClickable(Validate));
	    Thread.sleep(2000);
	    driver.switchTo().defaultContent();
}
catch(Exception e)
{
	Report.FailTest(test, e.getMessage());
	Report.FailTest(test, "Unable to click on Go button" );
}

	    
   }
   
   public void Opus_Confirmations_VerifyRouteCount() throws InterruptedException {
 try {
	   
	   driver.switchTo().frame("opusReportContainer");
	   Thread.sleep(2000);
	   
	  String CC=confirmedCount.getText();
	   int resultcc = Integer.parseInt(CC);
	   if (resultcc!=0) {
		   System.out.println(resultcc);
	}
	   else {
		   System.out.println("0 Records for ConfirmedCount ");
	}
	  
	  
	  String FC=flaggedCount.getText();
	  
	  int resultfc = Integer.parseInt(FC);
	  if (resultfc!=0) {
		  System.out.println(resultfc);
	}
	  else {
		  System.out.println("0 Records for Flagged");
	}
	 
	    String OC=openCount.getText();
	      int  resultoc=Integer.parseInt(OC);
	      if (resultoc!=0) {
			System.out.println(resultoc);
		}
	      else {
	    	  System.out.println("0 Records for Open");
		}
	  
	     String TC=totalCount.getText();
	     int  resulttc=Integer.parseInt(TC); 
	     if (resultcc + resultfc + resultoc == resulttc) {
	    
		    System.out.println("Total Nuber of Records is >> " + resulttc);	
		
		}
	     else {
			System.out.println("No Records availabel");
		}
	    	driver.switchTo().defaultContent(); 
   }
	    	catch(Exception e)
	    	{
	    		Report.FailTest(test, e.getMessage());
	    		Report.FailTest(test, "Unable to Verify Count" );
	    	}
   }
   
   public void Opus_Confirmations_VerifyRecordsInsideofRoute() throws InterruptedException {
	  try { 
	   driver.switchTo().frame("opusReportContainer");
	   Thread.sleep(2000);
	   if (driver.findElements(By.xpath("//*[@id='tblRouteSummary' and @role='grid']/tbody/tr[1]")).size()!=0) {
		System.out.println("Records Available");
		String Exp=VerifyRecordsInRouteSummary.getText();
		System.out.println(Exp);
	}
	   else {
		System.out.println("No Records Available");
	}
	   driver.switchTo().defaultContent();
   }
	   catch(Exception e)
	   {
	   	Report.FailTest(test, e.getMessage());
	   	Report.FailTest(test, "No Records Inside of Route" );
	   }
   }
   
   
   public void Opus_Confirmations_crazysearch(String SearchBy) throws InterruptedException {
try {
	   driver.switchTo().frame("opusReportContainer");
	   Thread.sleep(2000); 
	   if (SearchFilter.isDisplayed() && SearchFilter.isEnabled()) {
		   SearchFilter.sendKeys(SearchBy);
		   Thread.sleep(2000);
		   
	}
	   else {
		System.out.println("Unable to Found Crazy Search Filter");
	}
	   
	   driver.switchTo().defaultContent();
   }
	   catch(Exception e)
	   {
	   	Report.FailTest(test, e.getMessage());
	   	Report.FailTest(test, "crazysearch is not working fine for >>" + SearchBy );
	   }
	   
   }

  public void Opus_Confirmations_ClickOnFirstRoute() throws InterruptedException {
	  try {
	  driver.switchTo().frame("opusReportContainer");
	   Thread.sleep(2000);
	   if (ClickFirstRouteID.isDisplayed() && ClickFirstRouteID.isEnabled()) {
		   ClickFirstRouteID.click();
	}
	   else {
		System.out.println("Unable to found Route Id");
	}
	   
	   Thread.sleep(1000);
  }
	   catch(Exception e)
	   {
	   	Report.FailTest(test, e.getMessage());
	   	Report.FailTest(test, "No Data available after searching" );
	   }
	   
	   
  }
  
 public void Opus_Confirmations_FindDayBetweenDates(String OldDate, String NewDate) {
	 
	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	 String Date1=OldDate;   // Old Date
	 LocalDate localDate1 = LocalDate.parse(Date1, formatter);
	 
	 String Date2=NewDate;  // New Date
	 LocalDate localDate2 = LocalDate.parse(Date2, formatter);
	 
	 System.out.println("Days Gap Between >> "+ OldDate + "  and  "+ NewDate+ " is --> " + ChronoUnit.DAYS.between(localDate1, localDate2));
	 
	 
	 
 }


}

	
	

