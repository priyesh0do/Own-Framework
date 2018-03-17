package ObjectRepository.Confirmation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class Confirmation_Route_Detail_Resi {
	
	WebDriver driver;
	ExtentTest test;
	
	
	public Confirmation_Route_Detail_Resi(WebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);

}

	////////////////Elements In Header //////////////////////
	// Confirmation Route Details  Landing page 
	@FindBy(xpath="html/body/div[2]/form/div[1]/div[1]/h1")
	WebElement ConfirmationRouteDetails;
	
	//Back To Site Details Link
	@FindBy(xpath="//a[@class='clickCancel returnsiteDetail' and @target='_top']")
	WebElement BacktoSiteDetails;
	
	///////////////////Elements Present Inside of General Information Tab////////////////////////
	//SwitchToManualMode
	@FindBy(xpath="//*[@id='switch-to-manual-mode-button' and @type='button']")
	WebElement SwitchToManualMode;
	
	//See CustomerDetails
	@FindBy(xpath="//*[@id='seeCustomerDetails' and @class='customerDetails']")
	WebElement SeeCustomerDetails;
	
	//SiteDetails
	@FindBy(xpath="//*[@id='haulingSite' and @type='text']")
	WebElement SiteDetails;
	
	
	//RouteDetails
	@FindBy(xpath="//*[@id='route' and @type='text']")
	WebElement RouteDetails; 
	
	
	//PrimaryRouteCheckBox
	@FindBy(xpath="//*[@id='primary-route' and @type='checkbox']")
	WebElement PrimaryRouteCheckBox;
	
	
	//RouteAssist
	@FindBy(xpath="//*[@id='route-assist' and @type='checkbox']")
	WebElement RouteAssist;
	
	
	//LOBText
	@FindBy(xpath="//*[@id='LOB' and @type='text']")
	WebElement LOBText;
	
	
	//ServiceStartDate
	@FindBy(xpath="//*[@id='serviceDate' and @type='text']")
	WebElement ServiceStartDate;
	
	
	//ConfirmationStatus
	@FindBy(xpath="//*[@id='status' and @type='text']")
	WebElement ConfirmationStatus;
	
	
	//LeaveYard
	@FindBy(xpath="//*[@id='leaveYard' and @type='text']")
	WebElement LeaveYard;
	
	
	//ArriveYard
	@FindBy(xpath="//*[@id='arriveYard' and @type='text']")
	WebElement ArriveYard;
	
	
	
	
	////////////////////////// Elements Present Inside of Helper Tab //////////////////////////////
	
	//HelperTab
	@FindBy(xpath="//*[@id='ui-accordion-accordion-header-0' and @role='tab']")
	WebElement HelperTab;
	
	//AddResourceLink
	@FindBy(xpath="//*[@id='ui-accordion-accordion-header-0']/p/a")
	WebElement AddResourceLink;
	
	
	
	  // Element Present Inside of  Add Resource //
	//Employee1
	@FindBy(xpath="//*[@id='employee1' and @name='employee']")
	WebElement Employee1; 
	
	
	//StartTime1
	@FindBy(xpath="//*[@id='helperStartTime1' and @type='text']")
	WebElement StartTime1;
		
	//EndTime1
	@FindBy(xpath="//*[@id='helperEndTime1' and @type='text']")
	WebElement EndTime1;
		
	
	//Close1
	@FindBy(xpath="//*[@id='1']/span")
	WebElement Close1;
	
	
	//Employee2
	@FindBy(xpath="//*[@id='employee2' and @name='employee']")
	WebElement Employee2;
	
	
	//StartTime2
	@FindBy(xpath="//*[@id='helperStartTime2' and @type='text']")
	WebElement StartTime2;
		
	
	//EndTime2
	@FindBy(xpath="//*[@id='helperEndTime2' and @type='text']")
	WebElement EndTime2;
		
	
	//Close2
	@FindBy(xpath="//*[@id='2']/span")
	WebElement Close2;
	
	
	
	//Employee3
	@FindBy(xpath="//*[@id='employee3' and @name='employee']")
	WebElement Employee3;
	
		
	//StartTime3
	@FindBy(xpath="//*[@id='helperStartTime3' and @type='text']")
	WebElement StartTime3;
	
			
	//EndTime3
	@FindBy(xpath="//*[@id='helperEndTime3' and @type='text']")
	WebElement EndTime3;
	

   //Close3
	@FindBy(xpath="//*[@id='3']/span")
	WebElement Close3;
	
	
	
	/////////////////////////////Elements Present Inside of Meal////////////////
	//MealTab
	@FindBy(xpath="//*[@id='ui-accordion-accordion-header-1' and @role='tab']")
	WebElement MealTab;
	
  ////////////////////Elements Present Inside of Vehicle Tab////////
	
	//VehicleTab
	@FindBy(xpath="//*[@id='ui-accordion-accordion-header-2' and @role='tab']")
	WebElement VehicleTab;
	
	//VehichleSectionErrorMessage
	@FindBy(xpath="//*[@id='ui-accordion-accordion-header-2']/span[2]")
	WebElement VehichleSectionErrorMessage;
	
	
	//VehichleId
	@FindBy(xpath="//*[@id='selectedVehicleId1' and @name='vehicleId']")
	WebElement VehichleId;
	
	
	//Vehicle_Fuel
	@FindBy(xpath="//*[@id='fuel1' and @name='fuel']")
	WebElement Vehicle_Fuel;
	
	
	//Vehicle_OdometerStart
	@FindBy(xpath="//*[@id='odometerStart1' and @name='odometerStart']")
	WebElement Vehicle_OdometerStart;
	
	
	//Vehicle_OdometerEnd
	@FindBy(xpath="//*[@id='odometerEnd1' and @name='odometerEnd']")
	WebElement Vehicle_OdometerEnd;
	
	
	//Vehicle_Distance
	@FindBy(xpath="//*[@id='odometerDistance1' and @name='odometerDistance']")
	WebElement Vehicle_Distance;
	
	
	//Vehicle_EngineStart
	@FindBy(xpath="//*[@id='engineStart1' and @name='engineStart']")
	WebElement Vehicle_EngineStart;
	
	
	//Vehicle_EngineEnd
	@FindBy(xpath="//*[@id='engineEnd1' and @name='engineEnd']")
	WebElement Vehicle_EngineEnd;
	
	
	
	//DisposalTab
	@FindBy(xpath="//*[@id='ui-accordion-accordion-header-3' and @role='tab']")
	WebElement DisposalTab;
	
	
	//UnitsTab
	@FindBy(xpath="//*[@id='units' and @role='tab']")
	WebElement UnitsTab;
	
	
	//CommentsTab
	@FindBy(xpath="//*[@id='comments' and @role='tab']")
	WebElement CommentsTab;
	
	
	
	
	
}
