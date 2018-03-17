package ObjectRepository.Confirmation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RouteDetails {
	
////////////////Elements In Header //////////////////////
// Confirmation Route Details  Landing page 
@FindBy(xpath="html/body/div[2]/form/div[1]/div[1]/h1")
WebElement ConfirmationRouteDetails;

//Back To Site Details Link
@FindBy(xpath="//a[@class='clickCancel returnsiteDetail' and @target='_top']")
protected
WebElement BacktoSiteDetails;

///////////////////Elements Present Inside of General Information Tab////////////////////////
//SwitchToManualMode
@FindBy(xpath="//*[@id='switch-to-manual-mode-button' and @type='button']")
WebElement SwitchToManualMode;

//See CustomerDetails
@FindBy(xpath="//*[@id='seeCustomerDetails' and @class='customerDetails']")
protected
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

//ArriveYardHardStop
@FindBy(xpath="//*[@id='generalInfo_groupContainer']/div[12]/span/div")
WebElement ArriveYardHardStop;

//SaveGeneral
@FindBy(xpath="//*[@id='saveGeneral']")
WebElement SaveGeneral;

//UndoGeneral
@FindBy(xpath="//*[@id='generalUndo']")
WebElement UndoGeneral;


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


//SaveHelper
@FindBy(xpath="//*[@id='saveHelper']")
WebElement SaveHelper;

//UndoHelper
@FindBy(xpath="//*[@id='helperUndo']")
WebElement UndoHelper;



/////////////////////////////Elements Present Inside of Meal////////////////
//MealTab
@FindBy(xpath="//*[@id='ui-accordion-accordion-header-1' and @role='tab']")
WebElement MealTab;


//MealTabError
@FindBy(xpath="//*[@id='mealErrorMessage' and contains(text(),'Meal Section has errors')]")
WebElement MealSectionhaserror;

//AddMealLink
@FindBy(xpath="//*[@id='ui-accordion-accordion-header-1']/p/a[@href='javascript:void(0)']")
WebElement AddMealLink;

//MealStart
@FindBy(xpath="//*[@id='mealStart1' and @name='mealStart']")
WebElement MealStart;

//MealEnd
@FindBy(xpath="//*[@id='mealEnd1' and @name='mealEnd']")
WebElement MealEnd;

//MealDuration
@FindBy(xpath="//*[@id='mealDuration1' and @name='duration']")
WebElement MealDuration;

//MealSoftStop
@FindBy(xpath="//*[@class='ui-icon-yellow ui-icon-alert']/div[contains(text(),'Meal Duration should be between 27- 33 mins')]")
WebElement MealSoftStop;

//DeleteMeal
@FindBy(xpath="//*[@class='mealLegend']/div[2]/div[4]")
WebElement DeleteMeal;

//SaveMeal
@FindBy(xpath="//*[@id='saveMeal']")
WebElement SaveMeal;

//UndoMeal
@FindBy(xpath="//*[@id='mealUndo']")
WebElement UndoMeal;



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

//SaveVehicle
@FindBy(xpath="//*[@id='saveVehicle']")
WebElement SaveVehicle;

//UndoVehicle
@FindBy(xpath="//*[@id='vehicleUndo']")
WebElement UndoVehicle;


////////////////////Elements Present Inside of Disposal Tab /////////////////////////
//DisposalTab
@FindBy(xpath="//*[@id='ui-accordion-accordion-header-3' and @role='tab']")
WebElement DisposalTab;

//DisposalTabError
@FindBy(xpath="//*[@id='disposalErrorMessage' and @class='error_Message']")
WebElement DisposalTabError;

//AddDisposalTrip
@FindBy(xpath="//*[@id='ui-accordion-accordion-header-3']/p/a")
WebElement AddDisposalTrip;

//OnRoute
@FindBy(xpath="//*[@id='onRoute1' and @identifier='onRoute']")
WebElement OnRoute;

//OffRoute
@FindBy(xpath="//*[@id='offRoute1' and @identifier='offRoute']")
WebElement OffRoute;

//DisposalCode
@FindBy(xpath="//*[@id='selectedDisposalCode1' and @name='disposalCode']")
WebElement DisposalCode;

//DisposalPicture
@FindBy(xpath="//*[@id='disposal-picture1']/i")
WebElement DisposalPicture;

//Arrive
@FindBy(xpath="//*[@id='arrive1' and @type='text']")
WebElement Arrive;

//ArriveSoftStop
@FindBy(xpath="//*[@id='ui-accordion-accordion-panel-3']/div[1]/div[1]/ul[1]/li[4]/div[2]/span")
WebElement ArriveSoftStop;

//Depart
@FindBy(xpath="//*[@id='depart1' and @type='text']")
WebElement Depart;

//DepartSoftStop
@FindBy(xpath="//*[@id='ui-accordion-accordion-panel-3']/div[1]/div[1]/ul[1]/li[5]/div[2]/span")
WebElement DepartSoftStop;

//DisposalType
@FindBy(xpath="//*[@id='selectedDisposalType1' and @name='disposalType']")
WebElement DisposalType;

//MatrialType
@FindBy(xpath="//*[@id='selectedMaterial1' and @name='materialType']")
WebElement MatrialType;

//DisposalTicket
@FindBy(xpath="//*[@id='disposalTicket1' and @type='text']")
WebElement DisposalTicket;

//Volume
@FindBy(xpath="//*[@id='volume1' and @type='text']")
WebElement Volume;

//UOM
@FindBy(xpath="//*[@id='UOM1' and @name='UOM']")
WebElement UOM;

//NonDisposalCode
@FindBy(xpath="//*[@id='smart-disposal-code1']")
WebElement NonDisposalCode;

//UnitCost
@FindBy(xpath="//*[@id='unit-cost1' and @identifier='unit-cost']")
WebElement UnitCost;

//TotalCost
@FindBy(xpath="//*[@id='cost1' and @identifier='total-cost']")
WebElement TotalCost;

//OrganicChkBox
@FindBy(xpath="//*[@id='organic1' and @type='checkbox']")
WebElement OrganicChkBox;

//CommitedChkBox
@FindBy(xpath="//*[@id='contaminated1' and @type='checkbox']")
WebElement CommitedChkBox;

//DigOut
@FindBy(xpath="//*[@id='digout1' and @type='checkbox']")
WebElement DigOut;

//Liner
@FindBy(xpath="//*[@id='liner1' and @type='checkbox']")
WebElement Liner;

//SaveDisposal
@FindBy(xpath="//*[@id='saveDisposal']")
WebElement SaveDisposal;

//UndoDisposal
@FindBy(xpath="//*[@id='disposalUndo']")
WebElement UndoDisposal;

/////////////////////////Element Present Inside of Unit Tab///////////////////////
//UnitsTab
@FindBy(xpath="//*[@id='units' and @role='tab']")
WebElement UnitsTab;

//SaveUnits
@FindBy(xpath="//*[@id='saveUnits']")
WebElement SaveUnits;

//UndoUnits
@FindBy(xpath="//*[@id='UnitsUndo']")
WebElement UndoUnits;

/////////////////Elements Present Inside of Comments Tab///////////////////

//CommentsTab
@FindBy(xpath="//*[@id='comments' and @role='tab']")
WebElement CommentsTab;

//SaveComments
@FindBy(xpath="//*[@id='saveComments']")
WebElement SaveComments;

//UndoComment
@FindBy(xpath="//*[@id='commentsUndo']")
WebElement UndoComment;


	////////////////////Alerts//////////////////////////

//UnsavedChangesAlert
@FindBy(xpath="//*[@id='alert_wrap']")
WebElement UnsavedChangesAlert;

//AlretOkBtn
@FindBy(xpath="//*[@id='alertOk' and @type='button']")
WebElement AlretOkBtn;

 /////////////Validations.////////////////////
@FindBy(xpath="//*[@id='errorDiv']")
WebElement Errors;



}
