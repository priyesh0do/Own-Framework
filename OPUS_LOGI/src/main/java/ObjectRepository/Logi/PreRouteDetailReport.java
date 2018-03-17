package ObjectRepository.Logi;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import SupportLibraries.GlobalVariables;
import SupportLibraries.LogClass;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class PreRouteDetailReport {

	ExtentTest test;
	WebDriver driver;

	public PreRouteDetailReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//table[@id='dtPreRouteSummary']/thead/tr/th")
	List<WebElement> preRouteSummaryTableColumns;

	@FindBy(xpath = "//table[@id='dtPreRouteSummary']/tbody/tr")
	List<WebElement> preRouteSummaryTableRows;

	///////////////// Pre-RouteDetail
	@FindBy(xpath = "(.//*[@class='report' and contains(text(),'Pre-Route Detail')])[2]")
	WebElement Pre_RouteDetail;

	//// MultiselectDropDown
	@FindBy(xpath = "//*[@class='ui-multiselect ui-widget ui-state-default ui-corner-all' and @type='button']")
	WebElement MultiselectDropDown;

	/// loader
	@FindBy(id = "rdWaitImage")
	WebElement loader;

	//////////// PreRouteHeader
	@FindBy(xpath = "//*[@id='rdDataTableDiv-dtPreRouteSummary']/table/thead/tr")
	WebElement PreRouteHeader;

	//////////// PreRouteBody
	@FindBy(xpath = "//*[@id='rdDataTableDiv-dtPreRouteSummary']/table/tbody/tr")
	WebElement PreRouteBdy;

	///////////// ExceptionsAll
	@FindBy(xpath = "//*[@id='rdTabs']/ul")
	WebElement ExceptionsAll;

	//////////// SubViewRadiobutton
	@FindBy(xpath = "//*[@id='rdInputRadioOptions']")
	WebElement SubViewRadiobutton;

	//////////// VerifySubViewCol
	@FindBy(xpath = "//*[@id='t2e']/colgroup")
	WebElement VerifySubViewCol;

	////// DetailTable
	@FindBy(xpath = "//*[@id='t2e']/tbody")
	WebElement DetailTable;

	//// DetailTableIdle
	@FindBy(xpath = "//*[@id='t2']/tbody")
	WebElement DetailTableIdle;

	///////////////// Redirected to DashBoard///
	@FindBy(xpath = "//a[@href='/opus-report/report/report?reportname=Dashboard' and contains(text(),'Dashboard')]")
	WebElement Dashboard;

	////////////////// ToDate
	@FindBy(xpath = "//*[@id='inpToDate' and @name='inpToDate']")
	WebElement ToDate;

	////////// From Date
	@FindBy(xpath = "//*[@id='inpFromDate' and @name='inpFromDate']")
	WebElement FromDate;

	///// Reset Button /////////////////////
	@FindBy(xpath = "//*[@id='btnReset' and @type='button']")
	WebElement ResetBtn;
	/// Go Btn
	@FindBy(xpath = "//*[@id='btnSubmitGo' and @type='button']")
	WebElement GoBtn;

	// SiteFilter
	@FindBy(xpath = "//*[@id='divSiteFilter']/span/button")
	WebElement SiteFilter;

	// SelectLOB
	@FindBy(xpath = "//*[@id='divLOBFilter']/span/button")
	WebElement SelectLOB;

	/// SelectLOBCustPropDetail
	@FindBy(xpath = "//*[@id='divLOBFilterS']/span/button")
	WebElement SelectLOBCustPropDetail;

	/// SiteFilterSearch
	@FindBy(xpath = "(//*[@class='ui-multiselect-filter']/input)[1]")
	WebElement SiteFilterSearch;

	// LOBSearchBox
	@FindBy(xpath = "(//*[@class='ui-multiselect-filter']/input)[2]")
	WebElement LOBSearchBox;

	/// LObSearchBoxForPreRouteSummary
	@FindBy(xpath = "(//*[@class='ui-multiselect-filter']/input)[5]")
	WebElement LOBSearchBoxForPreRouteSummary;
	// CheckAllLOB
	@FindBy(xpath = "//*[@id='ui-multiselect-islLOBFilter-option-0']")
	WebElement CheckAllLOB;

	// CheckCom
	@FindBy(xpath = "//*[@id='ui-multiselect-islLOBFilter-option-1']")
	WebElement CheckCom;

	// CheckResi
	@FindBy(xpath = "//*[@id='ui-multiselect-islLOBFilter-option-2']")
	WebElement CheckResi;

	// CheckRollOff
	@FindBy(xpath = "//*[@id='ui-multiselect-islLOBFilter-option-3']")
	WebElement CheckRollOff;

	/// ActualSubview
	@FindBy(xpath = "//*[@id='inpSubReportOptEx_2']")
	WebElement ActualSubview;

	// ActualSubviewAllTab
	@FindBy(xpath = ".//*[@id='inpSubReportOpt_2']")
	WebElement ActualSubviewAllTab;

	// AllTab
	@FindBy(xpath = ".//*[@id='tabAll']/a/em/span")
	WebElement AllTab;

	// PreRouteDetailTableColumn
	@FindBy(xpath = "//*[@id='t2e']/thead/tr/th")
	List<WebElement> PreRouteDetailTableColumn;

	// PreRouteDetailTableColumnAllTab
	@FindBy(xpath = "//*[@id='t2']/thead/tr/th")
	List<WebElement> PreRouteDetailTableColumnAllTab;

	// PreRouteDetailTableRow
	@FindBy(xpath = "//*[@id='t2e']/tbody/tr")
	List<WebElement> PreRouteDetailTableRow;

	// PreRouteDetailTableRow
	@FindBy(xpath = "//*[@id='t2']/tbody/tr")
	List<WebElement> PreRouteDetailTableRowAllTab;

	// GetTextof
	@FindBy(xpath = "//*[@id='dtPreRouteSummary']/tbody/tr/td[contains(@id,'NumPreRteExcp')]/span")
	WebElement TotalPreRoutesWorkException;

	// NumofRows
	@FindBy(xpath = "//*[@id='t2e']/tbody/tr")
	List<WebElement> NumofRows;

	// ClickonALLTab
	@FindBy(xpath = "//*[@id='tabAll']")
	WebElement ClickonALLTab;

	// ClickonExceptionTab
	@FindBy(xpath = "//*[@id='tabExceptions']")
	WebElement ClickonExceptionTab;

	// TotalActualNumberofColums
	@FindBy(xpath = "//*[@id='t2']/tbody/tr/td[contains(@id,'colActTm')]/span")
	List<WebElement> TotalActualNumberofColums;

	// ExpectedPreRoutesCount
	@FindBy(xpath = "//*[@id='dtPreRouteSummary']/tbody/tr/td[contains(@id,'colNumPreRte_Row1')]/span")
	WebElement ExpectedPreRoutesCount;

	/// GetExptime
	@FindBy(xpath = "//*[@id='t2']/tbody/tr/td[contains(@id,'colExcpTm')]/span")
	List<WebElement> GetExptime;

	// GetActualExceptiontime
	@FindBy(xpath = "//*[@id='dtPreRouteSummary']/tbody/tr/td[contains(@id,'colExcpTimePct')]/span")
	WebElement GetActualExceptiontime;
	
	//TotalNumbersofActualHoursRows
	@FindBy(xpath="//*[@id='t2']/tbody/tr/td[contains(@id,'colActTm_')]/span")
	List<WebElement> TotalNumbersofActualHoursRows;
	
	//TotalNumberofPlanHoursRows
	@FindBy(xpath="//*[@id='t2']/tbody/tr/td[contains(@id,'colPlanTm_')]/span")
	List<WebElement> TotalNumberofPlanHoursRows;
	
	//ClickOnActualSubView
	@FindBy(xpath="//*[@id='inpSubReportOptEx_2']")
	WebElement ClickOnActualSubView;
	
	//RedirectedtoPerformanceSubviewAllTab
	@FindBy(xpath="//*[@id='inpSubReportOpt_1']")
	WebElement RedirectedtoPerformanceSubviewAllTab;
	
	//RedirectedToPerformanceSubview
	@FindBy(xpath="//*[@id='inpSubReportOptEx_1']")
	WebElement RedirectedToPerformanceSubview;
	
	
	
	
	//getActualPreRouteDetailTableDataAllTabActualSubview
	public void ValidatePreRouteDetailData(Map<String, List<String>> getActualPreRouteDetailTableData,Map<String,List <String>> getActualPreRouteDetailTableDataAllTab,
			Map<String,List<String>> getActualPreRouteDetailTableDataActualSubview,Map<String,List <String>> getActualPreRouteDetailTableDataAllTabActualSubview,String fromDate , String toDate, 
			String expectedRouteName, String expectedDriverName, String expectedLOB, String ClockInTime ) {
		
		ValidateDateExceptionsTabPerformanceSubview(getActualPreRouteDetailTableData, fromDate, toDate);
		validateRouteNameExceptionsTabPerformanceSubview(getActualPreRouteDetailTableData, expectedRouteName);
		validateDriverNameExceptionsTabPerformanceSubview(getActualPreRouteDetailTableData, expectedDriverName);
		validateLOBExceptionsTabPerformanceSubview(getActualPreRouteDetailTableData, expectedLOB);
		validateSubLOBExceptionsTabPerformanceSubview(getActualPreRouteDetailTableData);
		validateRouteTypeExceptionsTabPerformanceSubview(getActualPreRouteDetailTableData);
		//ValidateRouteManagerExceptionsTabPerformanceSubview(getActualPreRouteDetailTableData);
		validatePlanHoursExceptionsTabPerformanceSubview(getActualPreRouteDetailTableData, expectedLOB);
		validateExceptionTimeHoursExceptionsTabPerformanceSubview(getActualPreRouteDetailTableData);
		ValidateActualHoursExceptionsTabPerformanceSubview(getActualPreRouteDetailTableData);
		RedirectedtoActualSubViewExceptionTab();
		
		ValidateDateExceptionsTabActualSubview(getActualPreRouteDetailTableDataActualSubview, fromDate, toDate);
		validateRouteNameExceptionsTabActualSubview(getActualPreRouteDetailTableDataActualSubview, expectedRouteName);
		validateDriverNameExceptionsTabActualSubview(getActualPreRouteDetailTableDataActualSubview, expectedDriverName);
		validateLOBExceptionsTabActualSubview(getActualPreRouteDetailTableDataActualSubview, expectedLOB);
		validateSubLOBExceptionsTabActualSubview(getActualPreRouteDetailTableDataActualSubview);
		validateRouteTypeExceptionsTabActualSubview(getActualPreRouteDetailTableDataActualSubview);
		validatedClockInTimeExceptionTabActualSubview(getActualPreRouteDetailTableDataActualSubview, ClockInTime);
		validateLeaveYardTimeExceptionTabActualSubview(getActualPreRouteDetailTableDataActualSubview);
		
		
		RedirectedtoAllTab();
		RedirectedToAllTabPerformanceSubview();
		
		ValidateDateAllTabPerformanceSubview(getActualPreRouteDetailTableDataAllTab, fromDate, toDate);
		validateRouteNameAllTabPerformanceSubview(getActualPreRouteDetailTableDataAllTab, expectedRouteName);
		validateDriverNameAllTabPerformanceSubview(getActualPreRouteDetailTableDataAllTab, expectedDriverName);
		validateLOBAllTabPerformanceSubview(getActualPreRouteDetailTableDataAllTab, expectedLOB);
		validateSubLOBAllTabPerformanceSubview(getActualPreRouteDetailTableDataAllTab);
		validateRouteTypeAllTabPerformanceSubview(getActualPreRouteDetailTableDataAllTab);
		validatePlanHoursAllTabPerformanceSubview(getActualPreRouteDetailTableDataAllTab, expectedLOB);
		validateExceptionTimeHoursAllTabPerformanceSubview(getActualPreRouteDetailTableDataAllTab);
		ValidateActualHoursExceptionsTabPerformanceSubview(getActualPreRouteDetailTableDataAllTab);
		
		RedirectetoActualSubviewAllTab();
		
		
		ValidateDateAllTabActualSubview(getActualPreRouteDetailTableDataAllTabActualSubview, fromDate, toDate);
		validateRouteNameAllTabActualSubview(getActualPreRouteDetailTableDataAllTabActualSubview, expectedRouteName);
		validateDriverNameAllTabActualSubview(getActualPreRouteDetailTableDataAllTabActualSubview, expectedDriverName);
		validateLOBAllTabActualSubview(getActualPreRouteDetailTableDataAllTabActualSubview, expectedLOB);
		validateSubLOBAllTabActualSubview(getActualPreRouteDetailTableDataAllTabActualSubview);
		validateRouteTypeAllTabActualSubview(getActualPreRouteDetailTableDataAllTabActualSubview);
		validatedClockInTimeAllTabActualSubview(getActualPreRouteDetailTableDataAllTabActualSubview, ClockInTime);
		validateLeaveYardTimeAllTabActualSubview(getActualPreRouteDetailTableDataAllTabActualSubview);
		
		
		
		
		
	}
	
	private void ValidateActualHoursExceptionsTabPerformanceSubview(Map<String, List<String>> getActualPreRouteDetailTableData) {
		//ActualHours=exceptionhours+planhours
		int exceptionhours=0;
		int planhours=0;
		int actualHours=0;
		int expectedActualHours=0;
		
		
		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().contains("Plan (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							planhours += mins;
						}

					}
				}
			}
			
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().contains("Exception Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							exceptionhours += mins;
						}

					}
				}
			}
			
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().contains("Actual (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							actualHours += mins;
						}

					}
				}
			}
			
			
			expectedActualHours=exceptionhours+planhours;
			if (actualHours==expectedActualHours) {
				Report.PassTestScreenshot(test, driver, "Validated Actual hours Expected is " + expectedActualHours + "and actual is >" +actualHours , "Passed Actual Hours");
			} else {
				Report.FailTestSnapshot(test, driver, "Unable to validate Actual hours Expected is " + expectedActualHours + "and actual is >" +actualHours , "Failed Actual Hours");
			}
			
			
		} catch (Exception e) {
			Report.FailTest(test, "Unable to validate Actual hours");
		
		}
		
		
		
	}
	
	
	
	public void RedirectedtoPerformanceSubviewAllTab() {
		try {
			RedirectedtoPerformanceSubviewAllTab.click();
			Report.PassTest(test, "Redirected to Performance Subview All Tab");
		} catch (Exception e) {
			Report.FailTest(test, "Unable to Redirect to Performance Subview All Tab");
		}
		
	}
	
	public void RedirectedtoActualSubviewAllTab() {
		try {
			ActualSubviewAllTab.click();
			Thread.sleep(2000);
			Report.PassTest(test, "Redirected to Actual Subview All Tab");
		} catch (Exception e) {
			Report.FailTest(test, "Unable to Redirect Actual Subview All Tab");
		}
	}
	
	public void RedirectedToPerformanceSubview() {
		try {
			RedirectedToPerformanceSubview.click();
			Thread.sleep(2000);
			Report.PassTest(test, "Redirected to Performance Subview ");
			
		} catch (Exception e) {
			Report.PassTest(test, "Unable to Redirect to Performance Subview ");
		}
		
	}
	
	public void RedirectedtoActualSubview() {
		
		try {
			ActualSubview.click();
			Thread.sleep(3000);
			Report.PassTest(test, "Redirected to Actual Subview to read column name");
			
		} catch (Exception e) {
			Report.PassTest(test, "Failed to Redirect Actual Subview to read column name");
		}
	}
	
	 public void validateLeaveYardTimeAllTabActualSubview(Map<String, List<String>> getActualPreRouteDetailTableDataAllTabActualSubview) {
			
		 /*
		 		List<Date> actualPreRouteTime = new ArrayList<>();
		 		Date expectedPreRouteTime = null;*/
		 		List<Date> leaveYardTime = new ArrayList<>();
		 		List<Date> clockInTime = new ArrayList<>();
		 		List<String> route = new ArrayList<>();
		 		List<String> date = new ArrayList<>();
		 		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		 		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		 		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		 		try {
		 			
		 			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTabActualSubview.entrySet()) {
		 				if (entry.getKey().equals("Route")) {
		 					route = entry.getValue();
		 				}
		 			}
		 			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTabActualSubview.entrySet()) {
		 				if (entry.getKey().contains("Date")) {
		 					for (int i = 0; i < entry.getValue().size(); i++) {
		 						Date dates = format1.parse(entry.getValue().get(i));
		 						date.add(format2.format(dates));
		 					}
		 				}
		 			}

		 			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTabActualSubview.entrySet()) {
		 				if (entry.getKey().equals("Leave Yard")) {
		 					for (int i = 0; i < entry.getValue().size(); i++) {
		 						if (entry.getValue().get(i).equals("--")) {

		 							clockInTime.add(dateFormat.parse("00:00"));

		 						} else {
		 							clockInTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
		 						}

		 					}
		 				}
		 			}

		 			leaveYardTime = Util.getDateDataFromDB(
		 					"select TO_CHAR(FROMYARDSTAMP - (6 / 24) , 'HH:MI') from OCS_ADMIN.TP_RO_RESULT where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID  IN  ("
		 							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE  IN ("
		 							+ Util.sqlFormatedList(date)
		 							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");

		 			for (int i = 0; i < route.size(); i++) {
		 				if (leaveYardTime.equals(clockInTime)) {
		 					Report.PassTestScreenshot(test, driver, "Validated Leave Yard Time Sucessfully Actual is" + clockInTime + "Expected is "+ leaveYardTime , "PassedLeaveYardTime");
		 					
		 				}
		 				else {
		 					Report.FailTestSnapshot(test, driver,"Unable to Validate Leave Yard Time Actual is" + clockInTime + "Expected is "+ leaveYardTime , "FailedLeaveYardTime");
		 				}
		 				
		 			}
		 		} catch (Exception e) {
		 			Report.FailTest(test,
		 					"Unable to Validate Leave Yard Time Actual is" + clockInTime + "Expected is "+ leaveYardTime);
		 		}

		 	}
	
	
	public void validatedClockInTimeAllTabActualSubview(Map<String, List<String>> getActualPreRouteDetailTableDataAllTabActualSubview,
			String expectedClockInTime) {
		List<String> actualClockInTime = null;
		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTabActualSubview.entrySet()) {
				if (entry.getKey().equals("Clock In")) {
					actualClockInTime = entry.getValue();
				}
			}

			if (actualClockInTime.contains(expectedClockInTime)) {
				Report.InfoTest(test,
						"Clock In Time is correct in Actual sub view report Actual List is : "
								+ actualClockInTime.toString() + " and Expected is : " + expectedClockInTime);
				Report.PassTest(test, "Clock In Time is as expected in Actual sub view report");
			} else {
				Report.FailTest(test,
						"Clock In Time is not as expected in Actual sub view report Actual List is : " + actualClockInTime.toString()
								+ " and Expected is : " + expectedClockInTime);
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Clock In Time is not as expected in Actual sub view report Actual is : "
							+ actualClockInTime.toString() + " and Expected is : " + expectedClockInTime);
		}

	}
	
	public void validateRouteTypeAllTabActualSubview(Map<String, List<String>> getActualPreRouteDetailTableDataAllTabActualSubview) {
		List<String> actualRouteType = new ArrayList<>();
		List<String> expectedRouteType = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		String expectedRouteTypes = "";
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");

		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTabActualSubview.entrySet()) {
				if (entry.getKey().equals("Route Type")) {
					actualRouteType = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTabActualSubview.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTabActualSubview.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			expectedRouteType = Util.getDataFromDB(
					"select name from ocs_admin.tp_vehicletype where pkey in (select fk_vehicletype from ocs_admin.tp_vehicle where pkey in (select fk_vehicle from ocs_admin.tp_routeorder where FK_ROUTE IN ( select PKEY from OCS_ADMIN.TP_ROUTE where ID IN ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + "))) ");

			for (int i = 0; i < route.size(); i++) {
				if (expectedRouteType.get(0).equals("COMM FRONT LOAD"))
					expectedRouteTypes = "FC";

				if (expectedRouteType.get(0).equals("RES REARLOAD"))
					expectedRouteTypes = "RR";
				
				if (expectedRouteType.get(0).equals("ROLL OFF"))
					expectedRouteTypes = "O";

				if (actualRouteType.contains(expectedRouteTypes)) {
			//	if (actualRouteType.get(i).equals(expectedRouteTypes)) {
					Report.InfoTest(test,
							"Route Type  is correct in Actual Sub View Pre Route Detail report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type  : " + expectedRouteTypes);
					Report.PassTest(test, "Route Type is as expected in Actual Sub View Pre Route Detail report");
				} else {
					Report.FailTest(test,
							"Route Type is not as expected in Actual sub view Pre Route Detail report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type : " + expectedRouteTypes);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test,
					"Route Type  is not as expected in Actual Sub view Pre Route Detail report Actual is : "
							+ actualRouteType.toString() + " and Expected is : " + expectedRouteType.toString());
		}

	}
	
	
	public void validateSubLOBAllTabActualSubview(Map<String, List<String>> getActualPreRouteDetailTableDataAllTabActualSubview) {

		List<String> actualSubLOB = new ArrayList<>();
		List<String> expectedSubLOB = new ArrayList<>();
		List<String> route = new ArrayList<>();

		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTabActualSubview.entrySet()) {
				if (entry.getKey().equals("Sub LOB")) {
					actualSubLOB = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTabActualSubview.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			expectedSubLOB = Util.getDataFromDB(
					"select UNIQUEID from OCS_ADMIN.TP_SUBLOB where PKEY IN (select OCS_ADMIN.TP_ROUTE.FK_SUBLOB from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + "))");

			for (int i = 0; i < route.size(); i++) {
				if (actualSubLOB.contains(expectedSubLOB.get(0))) {
				//if (actualSubLOB.get(i).equals(expectedSubLOB.get(i))) {
					Report.InfoTest(test,
							"Sub LOB is correct in Actual Sub View Pre Route Detail report for Route : "
									+ route.get(i) + " with Actual SubLOB : " + actualSubLOB.get(0)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
					Report.PassTest(test, "Sub LOB is as expected in Actual Sub View report");
				} else {
					Report.FailTest(test,
							"Sub LOB is not as expected in Actual sub view Pre Route report for Route : "
									+ route.get(i) + " with Actual Sub LOB : " + actualSubLOB.get(i)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Sub LOB is not as expected in Actual Sub view Pre Route Detail report Actual is : "
					+ actualSubLOB.toString() + " and Expected is : " + expectedSubLOB.toString());
		}

	}
	
	
	public void validateLOBAllTabActualSubview(Map<String, List<String>> getActualPreRouteDetailTableDataAllTabActualSubview, String expectedLOB) {
		String actualLOB = null;
		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTabActualSubview.entrySet()) {
				if (entry.getKey().equals("LOB")) {

					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualLOB = null;
						} else {
							actualLOB = entry.getValue().get(i).toString();
						}
					}
				}
			}
			if (actualLOB.equals("RES"))
				actualLOB = "Residential";
			if (actualLOB.equals("COM"))
				actualLOB = "Commercial";
			if (actualLOB.equals("ROL"))
				actualLOB = "Roll Off";

			if (actualLOB.equals(expectedLOB)) {

				Report.InfoTest(test, "LOB is correct in Actual Sub View Pre Route Detail report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
				Report.PassTest(test, "LOB is as expected in Actual Sub View report");
			} else {
				Report.FailTest(test, "LOB is not as expected in Actual Sub View Pre Route Detail report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
			}
		} catch (Exception e) {
			Report.FailTest(test, "LOB is not as expected in Actual Sub View Pre Route Detail report Actual List is : " + actualLOB + " and Expected is : " + expectedLOB);
		}
	}
	
	
	public void validateDriverNameAllTabActualSubview(Map<String, List<String>> getActualPreRouteDetailTableDataAllTabActualSubview, String expectedDriverName) {
		String[] drivers = expectedDriverName.split(";");
		List<String> actualDrivers = null;
		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTabActualSubview.entrySet()) {
				if (entry.getKey().equals("Driver")) {
					actualDrivers = entry.getValue();
				}
			}
			for (int i = 0; i < drivers.length; i++) {
				if (actualDrivers.contains(drivers[i])) {
					Report.InfoTest(test,
							"Driver Name is correct in Actual Sub View Pre Route Detail report Actual List is : "
									+ actualDrivers.toString() + " and Expected is : " + drivers[i]);
					Report.PassTest(test, "Driver Name is as expected in Actual sub view Pre Route Detail Report");
				} else {
					Report.FailTest(test,
							"Driver Name is not as expected in Actual Sub View Pre Route Detail Report Actual List is : " + actualDrivers.toString()
									+ " and Expected is : " + drivers[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Driver Name is not as expected in Actual Sub View  Pre Route Detail Report Actual List is : "
							+ actualDrivers.toString() + " and Expected is : " + drivers.toString());
		}

	}
	
	
	 public void validateRouteNameAllTabActualSubview(Map<String, List<String>> getActualPreRouteDetailTableDataAllTabActualSubview, String expectedRouteName
				) {
			String[] routes = expectedRouteName.split(";");
			List<String> actualRoutes = null;
			try {
				for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTabActualSubview.entrySet()) {
					if (entry.getKey().equals("Route")) {
						actualRoutes = entry.getValue();
					}
				}
				for (int i = 0; i < routes.length; i++) {
					if (actualRoutes.contains(routes[i])) {
						Report.InfoTest(test,
								"Route Name is correct in Actual Sub View Pre Route Detail Report Actual List is : "
										+ actualRoutes.toString() + " and Expected is : " + routes[i]);
						Report.PassTest(test, "Route Name is as expected in Actual Sub View Pre Route Detail Report");
					} else {
						Report.FailTest(test,
								"Route Name is not as expected in Actual Sub View Pre Route Detail Report Actual List is : " + actualRoutes.toString()
										+ " and Expected is : " + routes[i]);
					}
				}

			} catch (Exception e) {
				Report.FailTest(test,
						"Route is not as expected in Actual Sub View Pre Route Detail Report Actual List is : "
								+ actualRoutes.toString() + " and Expected is : " + routes.toString());
			}

		}
	
	
	public void ValidateDateAllTabActualSubview(Map<String, List<String>> getActualPreRouteDetailTableDataAllTabActualSubview,String fromDate , String toDate) {
		
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yy");
		Date startDate = null;
		Date endDate = null;
		List<Date> routeServedDate = new ArrayList<>();
		List<String> actualRoutes = null;
		try {
			startDate = format1.parse(fromDate);
			endDate = format1.parse(toDate);
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTabActualSubview.entrySet()) {
				if (entry.getKey().equals("Route")) {
					actualRoutes = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTabActualSubview.entrySet()) {
				if (entry.getKey().equals("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						routeServedDate.add(format2.parse(entry.getValue().get(i).toString()));
					}
				}
			}

			for (int i = 0; i < actualRoutes.size(); i++) {
				if (routeServedDate.get(i).equals(startDate) || routeServedDate.get(i).equals(endDate)
						|| (routeServedDate.get(i).after(startDate) && routeServedDate.get(i).before(endDate))) {
					Report.InfoTest(test,
							"Route Date is correct in Pre Route Detail  Actual subview report for route : "
									+ actualRoutes.get(i) + ". Actual is : " + format1.format(routeServedDate.get(i))
									+ " and Expected is between : " + format1.format(startDate) + "and "
									+ format1.format(endDate));
					Report.PassTest(test, "Route Date is as expected in Pre Route Detail Report All Tab Actual subview ");
				} else {
					Report.FailTest(test,
							"Route Date is not as expected in Pre Route Detail Report All Tab Actual subview Actual is : "
									+ format1.format(routeServedDate.get(i)) + " and Expected is between : "
									+ format1.format(startDate) + "and " + format1.format(endDate));
				}
			}

		} catch (Exception e) {
			Report.FailTest(test, "Not able to compare date is in between the date range");
			LogClass.error(e.getMessage());
		}
		
		
		
	}
	
	
	
	public void RedirectetoActualSubviewAllTab() {
		
		try {
			ActualSubviewAllTab.click();
			Thread.sleep(2000);
			Report.PassTest(test, "Redirected to Actual Subview in All Tab");
		} catch (Exception e) {
			Report.FailTest(test, "Unable to Redirect to Actual Subview in All Tab");
		}
	}
	
	
	public void validateExceptionTimeHoursAllTabPerformanceSubview(Map<String, List<String>> getActualPreRouteDetailTableDataAllTab) {
		/*If Actual or Plan is Null, Excp Time is NULL
		If Actual or Plan <0, Excp Time is NULL
		If Actual <= Plan, the Excp Time = 0
		Else Excp Time = Actual - Planhh:mm*/
		
		int actualhours=0;
		int planhours=0;
		int exceptionhours=0;
		
		try {	
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
			if (entry.getKey().contains("Actual (h:m)")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (!entry.getValue().get(i).equals("--")) {
						String[] split = entry.getValue().get(i).split(":", 2);
						int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
						actualhours += mins;
					}

				}
			}
		}
			
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
				if (entry.getKey().contains("Plan (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							planhours += mins;
						}

					}
				}
			}
			
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
				if (entry.getKey().contains("Exception Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							exceptionhours += mins;
						}

					}
				}
			}
		if ((actualhours <=0) && (planhours<=0)) {
			Report.PassTestScreenshot(test, driver, "Actual and Plan Hours is less or equal to 0 , Expected Exception time is" + exceptionhours, "Passedexceptionhours");
			
		}
		else if (actualhours <=planhours) {
			Report.PassTestScreenshot(test, driver, "Actual hours  <= Plan hours, Expected Exception time is " + exceptionhours , "Passedexceptionhours");
		}
		else if (exceptionhours==(actualhours-planhours)) {
			Report.PassTestScreenshot(test, driver, "Difference between Actual Hours "+actualhours+ "And Plan Hours"+ planhours +"Expected Excetion hours is" +exceptionhours , "Passedexceptionhours");
		}
		else {
			Report.FailTestSnapshot(test, driver, "Unable to validate Exeception Time Hours", "Failedexceptionhours");
		}
		
		      
		
		
		} catch (Exception e) {
			Report.FailTest(test, "Unable to validate Exception time hours");
		}
		
		
	}
	
	
	public void validatePlanHoursAllTabPerformanceSubview(Map<String, List<String>> getActualPreRouteDetailTableDataAllTab,String LOB) {
		// PlanHours=Derived from EMAP

		List<Double> planUnits = new ArrayList<>();
		double expectedPlanUnits = 0.0;
		List<String> route = new ArrayList<>();

		try {

			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
				if (entry.getKey().equals("Plan (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							planUnits.add(Double.parseDouble("0.0"));
						} else {
							planUnits.add(Double.parseDouble(entry.getValue().get(i)));
							Util.validateFieldDecimalPlaces(Double.parseDouble(entry.getValue().get(i)), 1,
									"Plan Hours in Performance Sub View Pre Route Detail Report.");

						}

					}
				}
			}
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (int i = 0; i < route.size(); i++) {
				expectedPlanUnits = Util.getEMAPData(route.get(i), LOB, "PreRouteHours", GlobalVariables.EMAP);
				if (Util.compareNumber(planUnits.get(i), expectedPlanUnits)) {
					Report.InfoTest(test,
							"Plan hours is correct in Performance Sub View report for Route : "
									+ route.get(i) + " with Actual hours : " + planUnits.get(i)
									+ " and expected Hours : " + expectedPlanUnits);
					Report.PassTest(test, "Plan hours is as expected in Performance Sub View report");
				} else {
					Report.FailTest(test,
							"Plan hours is not as expected in Performance Sub View report for Route : "
									+ route.get(i) + " with Actual hours : " + planUnits.get(i)
									+ " and expected Hours : " + expectedPlanUnits);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Plan Hours is not as expected in Performance Sub View report Actual is : "
							+ planUnits.toString() + " and Expected is : " + expectedPlanUnits);

		}

	}

	
	public void validateRouteTypeAllTabPerformanceSubview(Map<String, List<String>> getActualPreRouteDetailTableDataAllTab) {
		List<String> actualRouteType = new ArrayList<>();
		List<String> expectedRouteType = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		String expectedRouteTypes = "";
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");

		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
				if (entry.getKey().equals("Route Type")) {
					actualRouteType = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			expectedRouteType = Util.getDataFromDB(
					"select name from ocs_admin.tp_vehicletype where pkey in (select fk_vehicletype from ocs_admin.tp_vehicle where pkey in (select fk_vehicle from ocs_admin.tp_routeorder where FK_ROUTE IN ( select PKEY from OCS_ADMIN.TP_ROUTE where ID IN ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + "))) ");

			for (int i = 0; i < route.size(); i++) {
				if (expectedRouteType.get(0).equals("COMM FRONT LOAD"))
					expectedRouteTypes = "FC";

				if (expectedRouteType.get(0).equals("RES REARLOAD"))
					expectedRouteTypes = "RR";
				
				if (expectedRouteType.get(0).equals("ROLL OFF"))
					expectedRouteTypes = "O";

				if (actualRouteType.contains(expectedRouteTypes)) {
			//	if (actualRouteType.get(i).equals(expectedRouteTypes)) {
					Report.InfoTest(test,
							"Route Type  is correct in Performance Sub View Pre Route Detail report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type  : " + expectedRouteTypes);
					Report.PassTest(test, "Route Type is as expected in Performance Sub View Pre Route Detail report");
				} else {
					Report.FailTest(test,
							"Route Type is not as expected in Performance sub view Pre Route Detail report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type : " + expectedRouteTypes);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test,
					"Route Type  is not as expected in Performance Sub view Pre Route Detail report Actual is : "
							+ actualRouteType.toString() + " and Expected is : " + expectedRouteType.toString());
		}

	}
	
	
	public void validateSubLOBAllTabPerformanceSubview(Map<String, List<String>> getActualPreRouteDetailTableDataAllTab) {

		List<String> actualSubLOB = new ArrayList<>();
		List<String> expectedSubLOB = new ArrayList<>();
		List<String> route = new ArrayList<>();

		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
				if (entry.getKey().equals("Sub LOB")) {
					actualSubLOB = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			expectedSubLOB = Util.getDataFromDB(
					"select UNIQUEID from OCS_ADMIN.TP_SUBLOB where PKEY IN (select OCS_ADMIN.TP_ROUTE.FK_SUBLOB from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + "))");

			for (int i = 0; i < route.size(); i++) {
				if (actualSubLOB.contains(expectedSubLOB.get(0))) {
				//if (actualSubLOB.get(i).equals(expectedSubLOB.get(i))) {
					Report.InfoTest(test,
							"Sub LOB is correct in Performance Sub View Pre Route Detail report for Route : "
									+ route.get(i) + " with Actual SubLOB : " + actualSubLOB.get(0)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
					Report.PassTest(test, "Sub LOB is as expected in Performance Sub View report");
				} else {
					Report.FailTest(test,
							"Sub LOB is not as expected in Performance sub view Pre Route report for Route : "
									+ route.get(i) + " with Actual Sub LOB : " + actualSubLOB.get(i)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Sub LOB is not as expected in Performance Sub view Pre Route Detail report Actual is : "
					+ actualSubLOB.toString() + " and Expected is : " + expectedSubLOB.toString());
		}

	}
	
	public void validateLOBAllTabPerformanceSubview(Map<String, List<String>> getActualPreRouteDetailTableDataAllTab, String expectedLOB) {
		String actualLOB = null;
		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
				if (entry.getKey().equals("LOB")) {

					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualLOB = null;
						} else {
							actualLOB = entry.getValue().get(i).toString();
						}
					}
				}
			}
			if (actualLOB.equals("RES"))
				actualLOB = "Residential";
			if (actualLOB.equals("COM"))
				actualLOB = "Commercial";
			if (actualLOB.equals("ROL"))
				actualLOB = "Roll Off";

			if (actualLOB.equals(expectedLOB)) {

				Report.InfoTest(test, "LOB is correct in Performance Sub View Pre Route Detail report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
				Report.PassTest(test, "LOB is as expected in Performance Sub View report");
			} else {
				Report.FailTest(test, "LOB is not as expected in Performance Sub View Pre Route Detail report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
			}
		} catch (Exception e) {
			Report.FailTest(test, "LOB is not as expected in Performance Sub View Pre Route Detail report Actual List is : " + actualLOB + " and Expected is : " + expectedLOB);
		}
	}
	
	
	public void validateDriverNameAllTabPerformanceSubview(Map<String, List<String>> getActualPreRouteDetailTableDataAllTab, String expectedDriverName) {
		String[] drivers = expectedDriverName.split(";");
		List<String> actualDrivers = null;
		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
				if (entry.getKey().equals("Driver")) {
					actualDrivers = entry.getValue();
				}
			}
			for (int i = 0; i < drivers.length; i++) {
				if (actualDrivers.contains(drivers[i])) {
					Report.InfoTest(test,
							"Driver Name is correct in Performance Sub View Pre Route Detail report Actual List is : "
									+ actualDrivers.toString() + " and Expected is : " + drivers[i]);
					Report.PassTest(test, "Driver Name is as expected in Performance sub view Pre Route Detail Report");
				} else {
					Report.FailTest(test,
							"Driver Name is not as expected in Performance Sub View Pre Route Detail Report Actual List is : " + actualDrivers.toString()
									+ " and Expected is : " + drivers[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Driver Name is not as expected in Performance Sub View  Pre Route Detail Report Actual List is : "
							+ actualDrivers.toString() + " and Expected is : " + drivers.toString());
		}

	}
	
	
	public void validateRouteNameAllTabPerformanceSubview(Map<String, List<String>> getActualPreRouteDetailTableDataAllTab, String expectedRouteName
				) {
			String[] routes = expectedRouteName.split(";");
			List<String> actualRoutes = null;
			try {
				for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
					if (entry.getKey().equals("Route")) {
						actualRoutes = entry.getValue();
					}
				}
				for (int i = 0; i < routes.length; i++) {
					if (actualRoutes.contains(routes[i])) {
						Report.InfoTest(test,
								"Route Name is correct in Performance Sub View Pre Route Detail Report Actual List is : "
										+ actualRoutes.toString() + " and Expected is : " + routes[i]);
						Report.PassTest(test, "Route Name is as expected in Performance Sub View Pre Route Detail Report");
					} else {
						Report.FailTest(test,
								"Route Name is not as expected in Performance Sub View Pre Route Detail Report Actual List is : " + actualRoutes.toString()
										+ " and Expected is : " + routes[i]);
					}
				}

			} catch (Exception e) {
				Report.FailTest(test,
						"Route is not as expected in Performance Sub View Pre Route Detail Report Actual List is : "
								+ actualRoutes.toString() + " and Expected is : " + routes.toString());
			}

		}
	public void ValidateDateAllTabPerformanceSubview(Map<String, List<String>> getActualPreRouteDetailTableDataAllTab,String fromDate , String toDate) {
		
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yy");
		Date startDate = null;
		Date endDate = null;
		List<Date> routeServedDate = new ArrayList<>();
		List<String> actualRoutes = null;
		try {
			startDate = format1.parse(fromDate);
			endDate = format1.parse(toDate);
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
				if (entry.getKey().equals("Route")) {
					actualRoutes = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
				if (entry.getKey().equals("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						routeServedDate.add(format2.parse(entry.getValue().get(i).toString()));
					}
				}
			}

			for (int i = 0; i < actualRoutes.size(); i++) {
				if (routeServedDate.get(i).equals(startDate) || routeServedDate.get(i).equals(endDate)
						|| (routeServedDate.get(i).after(startDate) && routeServedDate.get(i).before(endDate))) {
					Report.InfoTest(test,
							"Route Date is correct in Pre Route Detail All Tab Performance subview report for route : "
									+ actualRoutes.get(i) + ". Actual is : " + format1.format(routeServedDate.get(i))
									+ " and Expected is between : " + format1.format(startDate) + "and "
									+ format1.format(endDate));
					Report.PassTest(test, "Route Date is as expected in Pre Route Detail Report All Tab Performance subview ");
				} else {
					Report.FailTest(test,
							"Route Date is not as expected in Pre Route Detail Report All Tab Performance subview Actual is : "
									+ format1.format(routeServedDate.get(i)) + " and Expected is between : "
									+ format1.format(startDate) + "and " + format1.format(endDate));
				}
			}

		} catch (Exception e) {
			Report.FailTest(test, "Not able to compare date is in between the date range");
			LogClass.error(e.getMessage());
		}
		
		
		
	}
	
	
	
	public void RedirectedToAllTabPerformanceSubview() {
		
		try {
			RedirectedtoPerformanceSubviewAllTab.click();
			Report.PassTest(test, "Redirected to All Tab Performance Subview");
			Thread.sleep(2000);
		} catch (Exception e) {
			Report.FailTest(test, "Unable to Redirect to All Tab Performance Subview");
			
		}
		
	}
	
	
	public void validatedClockInTimeExceptionTabActualSubview(Map<String, List<String>> getActualPreRouteDetailTableDataActualSubview,
			String expectedClockInTime) {
		List<String> actualClockInTime = null;
		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataActualSubview.entrySet()) {
				if (entry.getKey().equals("Clock In")) {
					actualClockInTime = entry.getValue();
				}
			}

			if (actualClockInTime.contains(expectedClockInTime)) {
				Report.InfoTest(test,
						"Clock In Time is correct in Actual sub view report Actual List is : "
								+ actualClockInTime.toString() + " and Expected is : " + expectedClockInTime);
				Report.PassTest(test, "Clock In Time is as expected in Actual sub view report");
			} else {
				Report.FailTest(test,
						"Clock In Time is not as expected in Actual sub view report Actual List is : " + actualClockInTime.toString()
								+ " and Expected is : " + expectedClockInTime);
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Clock In Time is not as expected in Actual sub view report Actual is : "+ actualClockInTime.toString() + " and Expected is : " + expectedClockInTime);
		}

	}
	
	
	public void RedirectedtoActualSubViewExceptionTab() {
		try {
			ClickOnActualSubView.click();
			Thread.sleep(1000);
		   Report.PassTest(test, "Redirected to Actual Subview Exception Tab"); 
		} catch (Exception e) {
			Report.FailTest(test, "Unable to Redirect to Actual Subview Exception Tab");
		}
		
	}

	public void validateExceptionTimeHoursExceptionsTabPerformanceSubview(Map<String, List<String>> getActualPreRouteDetailTableData) {
		/*If Actual or Plan is Null, Excp Time is NULL
		If Actual or Plan <0, Excp Time is NULL
		If Actual <= Plan, the Excp Time = 0
		Else Excp Time = Actual - Planhh:mm*/
		
		int actualhours=0;
		int planhours=0;
		int exceptionhours=0;
		
		try {	
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
			if (entry.getKey().contains("Actual (h:m)")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (!entry.getValue().get(i).equals("--")) {
						String[] split = entry.getValue().get(i).split(":", 2);
						int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
						actualhours += mins;
					}

				}
			}
		}
			
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().contains("Plan (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							planhours += mins;
						}

					}
				}
			}
			
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().contains("Exception Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							exceptionhours += mins;
						}

					}
				}
			}
		if ((actualhours <=0) && (planhours<=0)) {
			Report.PassTestScreenshot(test, driver, "Actual and Plan Hours is less or equal to 0 , Expected Exception time is" + exceptionhours, "Passedexceptionhours");
			
		}
		else if (actualhours <=planhours) {
			Report.PassTestScreenshot(test, driver, "Actual hours  <= Plan hours, Expected Exception time is " + exceptionhours , "Passedexceptionhours");
		}
		else if (exceptionhours==(actualhours-planhours)) {
			Report.PassTestScreenshot(test, driver, "Difference between Actual Hours "+actualhours+ "And Plan Hours"+ planhours +"Expected Excetion hours is" +exceptionhours , "Passedexceptionhours");
		}
		else {
			Report.FailTestSnapshot(test, driver, "Unable to validate Exeception Time Hours", "Failedexceptionhours");
		}
		
		      
		
		
		} catch (Exception e) {
			Report.FailTest(test, "Unable to validate Exception time hours");
		}
		
		
	}
	
	
	
	public void validateRouteTypeExceptionsTabPerformanceSubview(Map<String, List<String>> getActualPreRouteDetailTableData) {
		List<String> actualRouteType = new ArrayList<>();
		List<String> expectedRouteType = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		String expectedRouteTypes = "";
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");

		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().equals("Route Type")) {
					actualRouteType = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			expectedRouteType = Util.getDataFromDB(
					"select name from ocs_admin.tp_vehicletype where pkey in (select fk_vehicletype from ocs_admin.tp_vehicle where pkey in (select fk_vehicle from ocs_admin.tp_routeorder where FK_ROUTE IN ( select PKEY from OCS_ADMIN.TP_ROUTE where ID IN ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + "))) ");

			for (int i = 0; i < route.size(); i++) {
				if (expectedRouteType.get(0).equals("COMM FRONT LOAD"))
					expectedRouteTypes = "FC";

				if (expectedRouteType.get(0).equals("RES REARLOAD"))
					expectedRouteTypes = "RR";
				
				if (expectedRouteType.get(0).equals("ROLL OFF"))
					expectedRouteTypes = "O";

				if (actualRouteType.contains(expectedRouteTypes)) {
			//	if (actualRouteType.get(i).equals(expectedRouteTypes)) {
					Report.InfoTest(test,
							"Route Type  is correct in Performance Sub View Pre Route Detail report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type  : " + expectedRouteTypes);
					Report.PassTest(test, "Route Type is as expected in Performance Sub View Pre Route Detail report");
				} else {
					Report.FailTest(test,
							"Route Type is not as expected in Performance sub view Pre Route Detail report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type : " + expectedRouteTypes);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test,
					"Route Type  is not as expected in Performance Sub view Pre Route Detail report Actual is : "
							+ actualRouteType.toString() + " and Expected is : " + expectedRouteType.toString());
		}

	}
	
	public void validateSubLOBExceptionsTabPerformanceSubview(Map<String, List<String>> getActualPreRouteDetailTableData) {

		List<String> actualSubLOB = new ArrayList<>();
		List<String> expectedSubLOB = new ArrayList<>();
		List<String> route = new ArrayList<>();

		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().equals("Sub LOB")) {
					actualSubLOB = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			expectedSubLOB = Util.getDataFromDB(
					"select UNIQUEID from OCS_ADMIN.TP_SUBLOB where PKEY IN (select OCS_ADMIN.TP_ROUTE.FK_SUBLOB from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + "))");

			for (int i = 0; i < route.size(); i++) {
				if (actualSubLOB.contains(expectedSubLOB.get(0))) {
				//if (actualSubLOB.get(i).equals(expectedSubLOB.get(i))) {
					Report.InfoTest(test,
							"Sub LOB is correct in Performance Sub View Pre Route Detail report for Route : "
									+ route.get(i) + " with Actual SubLOB : " + actualSubLOB.get(0)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
					Report.PassTest(test, "Sub LOB is as expected in Performance Sub View report");
				} else {
					Report.FailTest(test,
							"Sub LOB is not as expected in Performance sub view Pre Route report for Route : "
									+ route.get(i) + " with Actual Sub LOB : " + actualSubLOB.get(i)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Sub LOB is not as expected in Performance Sub view Pre Route Detail report Actual is : "
					+ actualSubLOB.toString() + " and Expected is : " + expectedSubLOB.toString());
		}

	}
		
	public void validateLOBExceptionsTabPerformanceSubview(Map<String, List<String>> getActualPreRouteDetailTableData, String expectedLOB) {
		String actualLOB = null;
		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().equals("LOB")) {

					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualLOB = null;
						} else {
							actualLOB = entry.getValue().get(i).toString();
						}
					}
				}
			}
			if (actualLOB.equals("RES"))
				actualLOB = "Residential";
			if (actualLOB.equals("COM"))
				actualLOB = "Commercial";
			if (actualLOB.equals("ROL"))
				actualLOB = "Roll Off";

			if (actualLOB.equals(expectedLOB)) {

				Report.InfoTest(test, "LOB is correct in Performance Sub View Pre Route Detail report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
				Report.PassTest(test, "LOB is as expected in Performance Sub View report");
			} else {
				Report.FailTest(test, "LOB is not as expected in Performance Sub View Pre Route Detail report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
			}
		} catch (Exception e) {
			Report.FailTest(test, "LOB is not as expected in Performance Sub View Pre Route Detail report Actual List is : " + actualLOB + " and Expected is : " + expectedLOB);
		}
	}
	
	public void validateDriverNameExceptionsTabPerformanceSubview(Map<String, List<String>> getActualPreRouteDetailTableData, String expectedDriverName) {
		String[] drivers = expectedDriverName.split(";");
		List<String> actualDrivers = null;
		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().equals("Driver")) {
					actualDrivers = entry.getValue();
				}
			}
			for (int i = 0; i < drivers.length; i++) {
				if (actualDrivers.contains(drivers[i])) {
					Report.InfoTest(test,
							"Driver Name is correct in Performance Sub View Pre Route Detail report Actual List is : "
									+ actualDrivers.toString() + " and Expected is : " + drivers[i]);
					Report.PassTest(test, "Driver Name is as expected in Performance sub view Pre Route Detail Report");
				} else {
					Report.FailTest(test,
							"Driver Name is not as expected in Performance Sub View Pre Route Detail Report Actual List is : " + actualDrivers.toString()
									+ " and Expected is : " + drivers[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Driver Name is not as expected in Performance Sub View  Pre Route Detail Report Actual List is : "
							+ actualDrivers.toString() + " and Expected is : " + drivers.toString());
		}

	}
	
	public Map<String, List<String>> getActualPreRouteSummaryTableData() throws IOException {
		Map<String, List<String>> preRouteSummaryTableData = new HashMap<>();
		try {
			preRouteSummaryTableData = readTable(preRouteSummaryTableColumns, preRouteSummaryTableRows,
					"dtPreRouteSummary");
			Report.PassTestScreenshot(test, driver, "Pre Route Summary table data read successfully",
					"Pre Route Detail report");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Pre Route summary table data");
		}

		for (Entry<String, List<String>> entry : preRouteSummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return preRouteSummaryTableData;
	}

	public Map<String, List<String>> getActualPreRouteDetailTableData() {
		System.out.println("inside Actual Pre Route Summary Detail Table Data");
		Util.CaptureScreenshot("Actual Pre Route Summary Detail Table Data");
		Map<String, List<String>> PreRouteSummaryDetailTableData = new HashMap<>();
		try {
			PreRouteSummaryDetailTableData = readTable(PreRouteDetailTableColumn, PreRouteDetailTableRow, "t2e");
			Report.PassTestScreenshot(test, driver, "Pre Route Detail table data read successfully",
					"Pre Route Summary Detail Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Pre Route Detail table data");
		}
		for (Entry<String, List<String>> entry : PreRouteSummaryDetailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return PreRouteSummaryDetailTableData;
	}

	// getActualPreRouteDetailTableDataAllTab

	public Map<String, List<String>> getActualPreRouteDetailTableDataAllTab() {
		System.out.println("inside Actual Pre Route Summary Detail Table Data All Tab");
		Util.CaptureScreenshot("Actual Pre Route Summary Detail Table Data All Tab");
		Map<String, List<String>> PreRouteDetailTableDataAllTab = new HashMap<>();
		try {
			PreRouteDetailTableDataAllTab = readTable(PreRouteDetailTableColumnAllTab, PreRouteDetailTableRowAllTab,"t2");
			Report.PassTestScreenshot(test, driver, "Pre Route Detail table All Tab data read successfully",
					"Pre Route Summary Detail Data All Tab");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Pre Route Detail table data All TAb");
		}
		for (Entry<String, List<String>> entry : PreRouteDetailTableDataAllTab.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return PreRouteDetailTableDataAllTab;
	}
	
	//getActualPreRouteDetailTableDataAllTabActualSubview
	public Map<String, List<String>> getActualPreRouteDetailTableDataAllTabActualSubview() {
		System.out.println("inside Actual Pre Route Summary Detail Table Data All Tab Actual Subview");
		Util.CaptureScreenshot("Actual Pre Route Summary Detail Table Data All Tab Actual Subview");
		Map<String, List<String>> PreRouteDetailTableDataAllTab = new HashMap<>();
		try {
			PreRouteDetailTableDataAllTab = readTable(PreRouteDetailTableColumnAllTab, PreRouteDetailTableRowAllTab,"t2");
			Report.PassTestScreenshot(test, driver, "Pre Route Detail table All Tab Actual Subview data read successfully",
					"Pre Route Summary Detail Data All Tab");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Pre Route Detail table data All TAb Actual Subview");
		}
		for (Entry<String, List<String>> entry : PreRouteDetailTableDataAllTab.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return PreRouteDetailTableDataAllTab;
	}
	
	

	public void RedirectedtoAllTab() {

		ClickonALLTab.click();
		Report.PassTest(test, "Redirected to All Tab");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Report.FailTest(test, "Unable to Redirect to All Tab");
		}

	}

	public void RedirectedtoExceptionsTab() {
		ClickonExceptionTab.click();
		Report.PassTest(test, "Redirected to Exception Tab");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Report.FailTest(test, "Redirected to Exception Tab Failed");
			
		}

	}

	public void validateRouteNameExceptionsTabPerformanceSubview(Map<String, List<String>> getActualPreRouteDetailTableData, String expectedRouteName
			) {
		String[] routes = expectedRouteName.split(";");
		List<String> actualRoutes = null;
		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					actualRoutes = entry.getValue();
				}
			}
			for (int i = 0; i < routes.length; i++) {
				if (actualRoutes.contains(routes[i])) {
					Report.InfoTest(test,
							"Route Name is correct in Performance Sub View Pre Route Detail Report Actual List is : "
									+ actualRoutes.toString() + " and Expected is : " + routes[i]);
					Report.PassTest(test, "Route Name is as expected in Performance Sub View Pre Route Detail Report");
				} else {
					Report.FailTest(test,
							"Route Name is not as expected in Performance Sub View Pre Route Detail Report Actual List is : " + actualRoutes.toString()
									+ " and Expected is : " + routes[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Route is not as expected in Performance Sub View Pre Route Detail Report Actual List is : "
							+ actualRoutes.toString() + " and Expected is : " + routes.toString());
		}

	}
	
	public void ValidateAvgActualHours(Map<String, List<String>> getActualPreRouteDetailTableDataAllTab,Map<String, List<String>> actualPreRouteSummaryTableData, String lOB ) {
		///AvgActual =  Sum of all Actual (h:m) / Number of Exception time Rows
		int TotalplanHours=0;
		int AvgActualhours=0;
		int TotalNumbersofActualhrsRows=0;
		this.RedirectedtoAllTab();
		
		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
				if (entry.getKey().contains("Actual (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							TotalplanHours += mins;
						}

					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualPreRouteSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Avg Actual (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							AvgActualhours += mins;
						}

					}
				}
			}
			
			//FoundNumber of Exception time rows.
			if (TotalNumbersofActualHoursRows.size()!=0) {
				TotalNumbersofActualhrsRows=TotalNumbersofActualHoursRows.size();
				float actualdata = ((float) TotalplanHours / (float) TotalNumbersofActualhrsRows) ;
				int ActData=Math.round(actualdata);
				if (Util.compareNumber(ActData, AvgActualhours)) {
					Report.PassTestScreenshot(test, driver, "Passed Avg Actual Hours Actual is > " + ActData + "Minutes and Expected is" + AvgActualhours + " Minutes", "Passed Avg Actual");
				}
				else {
					Report.FailTestSnapshot(test, driver,"Failed Avg Actual Hours Actual is > " + ActData + "Minutes and Expected is" + AvgActualhours + " Minutes " , "Failed Avg Actual");
				}
			}
			else {
				Report.FailTestSnapshot(test, driver, "0 records available", "Failed Avg Actual");
			}
			
			
			
			
			
		} catch (Exception e) {
			Report.FailTest(test, "Unable to Validate Avg Actual");
		}
		this.RedirectedtoExceptionsTab();
	}
	
	public void ValidateAvgPlanHours(Map<String, List<String>> getActualPreRouteDetailTableDataAllTab, Map<String, List<String>>actualPreRouteSummaryTableData, String lOB) {
		
		int TotalplanHours=0;
		int AvgPlanhours=0;
		int TotalNumbersofActualhrsRows=0;
		this.RedirectedtoAllTab();
		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
				if (entry.getKey().contains("Plan (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							TotalplanHours += mins;
						}

					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualPreRouteSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Avg Plan (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							AvgPlanhours += mins;
						}

					}
				}
			}
			
			//FoundNumber of Exception time rows.
			if (TotalNumberofPlanHoursRows.size()!=0) {
				TotalNumbersofActualhrsRows=TotalNumberofPlanHoursRows.size();
				float actualdata = ((float) TotalplanHours / (float) TotalNumbersofActualhrsRows) ;
				int ActData=Math.round(actualdata);
				if (Util.compareNumber(ActData, AvgPlanhours)) {
					Report.PassTestScreenshot(test, driver, "Passed Avg Plan Hours Actual  is > " + ActData + "  Minutes and Expected is" + AvgPlanhours + " Minutes ", "Passed Avg Plan hours");
				}
				else {
					Report.FailTestSnapshot(test, driver,"Failed Avg Plan Hours Actual  is > " + ActData + " Minutes and Expected is" + AvgPlanhours + " Minutes " , "Failed Avg Plan Hours");
				}
			}
			else {
				Report.FailTestSnapshot(test, driver, "0 records available", "Failed Avg Plan hours");
			}
			
			
			
			
			
		} catch (Exception e) {
			Report.FailTest(test, "Unable to Validate Avg Plan Hours");
		}
		this.RedirectedtoExceptionsTab();
		
		
		
	}
	
	
	public Map<String, List<String>> readTable(List<WebElement> columns, List<WebElement> rows, String tableName) {

		Map<String, List<String>> objTable = new HashMap<>();
		System.out.println("Number of cloumns : " + columns.size());
		System.out.println("Number of rows : " + rows.size());
		for (int iCount = 1; iCount <= columns.size(); iCount++) {
			// List<WebElement> objCol =
			// rows.get(iCount).findElements(By.cssSelector("td.tableTxt"));
			List<String> rowValues = new ArrayList<>();
			for (int row = 1; row <= rows.size(); row++) {
				try {
					rowValues.add(driver
							.findElement(By.xpath(
									"//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + iCount + "]/span"))
							.getText());
				} catch (Exception e) {
					rowValues.add(driver
							.findElement(By
									.xpath("//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + iCount + "]"))
							.getText());
				}
			}
			objTable.put(columns.get(iCount - 1).getText(), rowValues);
		}
		return objTable;

	}

	public void validateRouteSummaryData(Map<String, List<String>> actualPreRouteSummaryTableData,
			Map<String, List<String>> actualPreRouteDetailTableData,
			Map<String, List<String>> getActualPreRouteDetailTableDataAllTab, String lOB) {

		validateTotalExceptionTime(actualPreRouteSummaryTableData, actualPreRouteDetailTableData, lOB);
		ValidateTotalPreRoutesWithException();
		ValidateTotalPreRoutes();
		ValidateTotalExceptionTimePersentage(getActualPreRouteDetailTableDataAllTab, lOB);
		ValidateAvgActualHours(getActualPreRouteDetailTableDataAllTab, actualPreRouteSummaryTableData, lOB);
		ValidateAvgPlanHours(getActualPreRouteDetailTableDataAllTab, actualPreRouteSummaryTableData, lOB);
	}
	
	public void ValidateDateExceptionsTabPerformanceSubview(Map<String, List<String>> getActualPreRouteDetailTableData,String fromDate , String toDate) {
		
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yy");
		Date startDate = null;
		Date endDate = null;
		List<Date> routeServedDate = new ArrayList<>();
		List<String> actualRoutes = null;
		try {
			startDate = format1.parse(fromDate);
			endDate = format1.parse(toDate);
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					actualRoutes = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().equals("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						routeServedDate.add(format2.parse(entry.getValue().get(i).toString()));
					}
				}
			}

			for (int i = 0; i < actualRoutes.size(); i++) {
				if (routeServedDate.get(i).equals(startDate) || routeServedDate.get(i).equals(endDate)
						|| (routeServedDate.get(i).after(startDate) && routeServedDate.get(i).before(endDate))) {
					Report.InfoTest(test,
							"Route Date is correct in Pre Route Detail Exceptions Performance subview report for route : "
									+ actualRoutes.get(i) + ". Actual is : " + format1.format(routeServedDate.get(i))
									+ " and Expected is between : " + format1.format(startDate) + "and "
									+ format1.format(endDate));
					Report.PassTest(test, "Route Date is as expected in Pre Route Detail Report Exceptions Tab Performance subview ");
				} else {
					Report.FailTest(test,
							"Route Date is not as expected in Pre Route Detail Report Exceptions Tab Performance subview Actual is : "
									+ format1.format(routeServedDate.get(i)) + " and Expected is between : "
									+ format1.format(startDate) + "and " + format1.format(endDate));
				}
			}

		} catch (Exception e) {
			Report.FailTest(test, "Not able to compare date is in between the date range");
			LogClass.error(e.getMessage());
		}
		
		
		
	}
	
	

	public void ValidateTotalExceptionTimePersentage(Map<String, List<String>> getActualPreRouteDetailTableDataAllTab,
			String lOB) {
		// (Total Exception Time / Total Pre-Route Time) ==Percentage; Whole Number
		int TotalExpTime = 0;
		int TotalActTime = 0;
		String ActualExceptiontime = null;
		int ActualExpTime = 0;
			
		this.RedirectedtoAllTab();

		try {
			
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
				if (entry.getKey().contains("Exception Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							TotalExpTime += mins;
						}

					}
				}
			}
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataAllTab.entrySet()) {
				if (entry.getKey().contains("Actual (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							TotalActTime += mins;
						}

					}
				}
			}

			ActualExceptiontime = GetActualExceptiontime.getText();
			ActualExpTime = Integer.parseInt(ActualExceptiontime);
			float actualdata = ((float) TotalExpTime / (float) TotalActTime) * 100;
			int ActData=Math.round(actualdata);
			if (ActualExpTime != 0) {
				if (Util.compareNumber(ActData, ActualExpTime)) {
					Report.PassTestScreenshot(test, driver, "Passed Total Actual Exception Time is  > " + ActualExpTime
							+ "Expected Exception time is > " + ActData, "PassedExceptiontime");
				}
				else {
					Report.FailTestSnapshot(test, driver, "Failed Total Actual Exception Time is  > " + ActualExpTime
							+ "Expected Exception time is > " + ActData, "PassedExceptiontimeFail");
				}
				
			} else {
				Report.InfoTest(test, "Exception time is Zero Unable to validate please change search criteria");
			}

		} catch (Exception e) {
			Report.FailTest(test, "Failed to Validate Total Exception Time Persentage");
		}
		this.RedirectedtoExceptionsTab();

	}

	public void ValidateTotalPreRoutesWithException() {
		String ActualTotalPreRoutesWorkE = TotalPreRoutesWorkException.getText();
		int Actualresult = Integer.parseInt(ActualTotalPreRoutesWorkE);
		if (Actualresult != 0) {
			int Expectedresult = NumofRows.size();
			if (Expectedresult == Actualresult) {
				Report.PassTestScreenshot(test, driver,
						"Total Pre-Routes w/ Exception is correct for PreRoute Detail Report",
						"Pre Route Total Pre-Routes w/ Exception ");
			} else {
				Report.FailTestSnapshot(test, driver,
						"Total Pre-Routes w/ Exception is Incorrect for PreRoute Detail Report",
						"Pre Route Total Pre-Routes w/ Exception Fail");
			}
		} else {
			Report.InfoTest(test, "Total Pre-Routes w/ Exception is >>" + Actualresult);
		}

	}

	public void ValidateTotalPreRoutes() {
		int actualPreRoutes = 0;
		int ExpectedPreRoute = 0;
		this.RedirectedtoAllTab();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Report.PassTest(test, "Redirected to All Tab");
		for (int i = 0; i < TotalActualNumberofColums.size(); i++) {
			if (!TotalActualNumberofColums.get(i).getText().contains("--")) {
				actualPreRoutes = TotalActualNumberofColums.size();

			}

		}
		String ExpCount = ExpectedPreRoutesCount.getText();
		ExpectedPreRoute = Integer.parseInt(ExpCount);
		if (ExpectedPreRoute != 0) {
			if (ExpectedPreRoute == actualPreRoutes) {
				Report.PassTestScreenshot(test, driver, "Total Pre-Routes Count is correct for Pre Route Detail Table",
						"Total Pre-Routes Passed");
			} else {
				Report.FailTestSnapshot(test, driver, "Total Pre-Routes Count is Incorrect for Pre Route Detail Table",
						"Total Pre-Routes Failed");
			}

		} else {
			Report.InfoTest(test, "Total Pre Routes Count is >> " + ExpectedPreRoute);
		}
		this.RedirectedtoExceptionsTab();

	}

	public void validateTotalExceptionTime(Map<String, List<String>> actualPreRouteSummaryTableData,
			Map<String, List<String>> actualPreRouteDetailTableData, String lOB) {

		// TotalException TimeHours=sum of actual Exception Time hours for all sites
		String driverTotalActualHours = null;
		String expectedDriverTotalActualHours = null;
		int totalMins = 0;

		try {
			for (Entry<String, List<String>> entry : actualPreRouteSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Total Exception Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverTotalActualHours = null;
						} else {
							driverTotalActualHours = entry.getValue().get(i).toString();
							Util.validateTimeFormat(driverTotalActualHours, "Exception Time Hours in Summary Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().contains("Exception Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins += mins;
						}

					}
				}
			}

			Util.convertMinsToHours(totalMins);

			expectedDriverTotalActualHours = Util.convertMinsToHours(totalMins);

			if (driverTotalActualHours.equals(expectedDriverTotalActualHours)) {
				Report.InfoTest(test, "Exception Time Hours is correct in Pre Route summary report Actual is : "
						+ driverTotalActualHours + " and Expected is : " + expectedDriverTotalActualHours);
				Report.PassTest(test, "Exception Time Hours is as expected in Pre Route Summary report");
			} else {
				Report.FailTest(test, "Exception Time Hours is not as expected in Pre Route summary report Actual is : "
						+ driverTotalActualHours + " and Expected is : " + expectedDriverTotalActualHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Exception Time Hours is not as expected in Pre Route summary report Actual is : "
					+ driverTotalActualHours + " and Expected is : " + expectedDriverTotalActualHours);
		}

	}

	public void ValidateExceptionTime() {
		int Exptime = 0;
		ClickonALLTab.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Exptime = GetExptime.size();
		if (Exptime != 0) {
			for (int i = 0; i < GetExptime.size(); i++) {

			}

		}

	}

	public void Verify_Pre_Route_Detail_UI() throws InterruptedException {

		try {
			///////////////////// Redirected to PreRoute Page From Dashboard///////////////
			Pre_RouteDetail.click();
			Report.PassTest(test, "Redirected to Pre-Route Detail Page");
			Thread.sleep(2000);
			////////////// Verify All drop down multi_select

			driver.switchTo().frame("opusReportContainer");
			Thread.sleep(2000);
			try {
				List<WebElement> Multioption = driver.findElements(By.xpath(
						"//*[@class='ui-multiselect ui-widget ui-state-default ui-corner-all' and @type='button']"));
				// System.out.println(Multioption.size());
				for (int i = 0; i < Multioption.size(); i++) {
					// System.out.println(Multioption.get(i).getAttribute("title"));
					Report.PassTest(test,
							"Pre Route Detail Filter Section are >>  " + Multioption.get(i).getAttribute("title"));
					if (!Multioption.get(i).getAttribute("title").isEmpty()) {
						Multioption.get(i).click();
						System.out.println(Multioption.get(i).getText() + "Is clickable");
					} else {
						System.out.println(Multioption.get(i).getText() + "Is not clickable");
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				Report.FailTestSnapshot(test, driver, "Unable to Validate Pre Route Detail Filter Section",
						"PreRouteFilterFail");
			}

			///////////////////// Verify Historical and Day of week Radio Button
			try {
				List<WebElement> RadioButton = driver.findElements(By.xpath("//*[@id='rdInputRadioOptions']/label"));
				for (int j = 0; j < RadioButton.size(); j++) {
					// System.out.println(RadioButton.get(j).getText());
					Report.PassTest(test, "Verified Radio buttons are >>" + RadioButton.get(j).getText());
					if (driver.findElements(By.xpath("//*[@id='rdInputRadioOptions']/input[@checked='True']"))
							.size() != 0) {
						RadioButton.get(j + 1).click();
					}

				}
			} catch (Exception e) {
				Report.FailTestSnapshot(test, driver, "Unable to Verify Historical and Day of week Radio Button",
						"RadioButtonVelidationFailed");

			}

			/////////////////// Get PreRoute Summary Data
			///////// Head////////////////
			driver.switchTo().frame("subReportContainer");
			try {
				if (driver.findElements(By.xpath("//*[@id='rdDataTableDiv-dtPreRouteSummary']/table")).size() != 0) {

					List<WebElement> PrerouteHead = PreRouteHeader.findElements(By.tagName("th"));

					for (int k = 0; k < PrerouteHead.size(); k++) {
						// System.out.println(PrerouteHead.get(k).getText());
						Report.PassTest(test, "PreRouteColumns are >>" + PrerouteHead.get(k).getText());
						if (!PrerouteHead.get(k).getText().isEmpty()) {
							System.out.println("PreRoute Summary Head >" + PrerouteHead.get(k).getText());
						} else {
							System.out.println("PreRoute Summary Head failed>" + PrerouteHead.get(k).getText());
						}
					}

				}
			} catch (Exception e) {
				Report.FailTestSnapshot(test, driver, "Unable to Validate Pre Route Columns ", "FailedPreRouteColumns");
			}

			/////////////////////// Verify Sub Views Tabs/////////////////////
			try {
				List<WebElement> SubViewTabs = ExceptionsAll.findElements(By.tagName("li"));
				for (int l = 0; l < SubViewTabs.size(); l++) {
					// System.out.println(SubViewTabs.get(l).getAttribute("id"));
					Report.PassTest(test, "SubView Tabs Are >> " + SubViewTabs.get(l).getAttribute("id"));
					if (!SubViewTabs.get(l).getAttribute("id").isEmpty()) {
						SubViewTabs.get(l).click();
						System.out.println(SubViewTabs.get(l).getText() + "is clickable");
					} else {
						System.out.println(SubViewTabs.get(l).getText() + "is Not clickable");
					}

				}
			} catch (Exception e) {
				Report.FailTestSnapshot(test, driver, "Unable to Verify SubView Tabs ", "SubViewTabsFailed");
			}
			/////// Verify Sub Vie Radio Button //////////////////////////
			try {
				List<WebElement> SubvieRadioButton = SubViewRadiobutton.findElements(By.tagName("label"));
				for (int m = 0; m < SubvieRadioButton.size(); m++) {
					// System.out.println(SubvieRadioButton.get(m).getText());
					Report.PassTest(test, "Verified Subview Radio Button >>" + SubvieRadioButton.get(m).getText());
					if (driver.findElements(By.xpath("//*[@id='rdInputRadioOptions']/input[@checked='True']"))
							.size() != 0) {
						SubvieRadioButton.get(m + 1).click();
						System.out.println(SubvieRadioButton.get(m).getText() + "is clickable");
					} else {
						System.out.println(SubvieRadioButton.get(m).getText() + "is not clickable");
					}

				}
			} catch (Exception e) {
				Report.FailTestSnapshot(test, driver, "Unable to Verify SubviewRadioButton ",
						"Failed SubviewRadioButton");
			}

			////////////////// Verify SubView Column
			try {
				List<WebElement> SubviewColumn = VerifySubViewCol.findElements(By.tagName("col"));
				for (int n = 0; n < SubviewColumn.size(); n++) {
					// System.out.println(SubviewColumn.get(n).getAttribute("id"));
					Report.PassTest(test, "SubView Column are >>" + SubviewColumn.get(n).getAttribute("id"));
					if (!SubviewColumn.get(n).getAttribute("id").isEmpty()) {
						System.out.println(SubviewColumn.get(n).getAttribute("id") + " Coloumn are available");
					} else {
						System.out.println(SubviewColumn.get(n).getAttribute("id") + " Coloumn are Not available");
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				Report.FailTestSnapshot(test, driver, "Unable to Verify SubView Column > ", "FailedSubViewColumn");

			}

			driver.switchTo().defaultContent();

			/////////////////// Redirected Back to Dashboard //////////

			driver.navigate().back();
			Thread.sleep(1000);
			Report.PassTest(test, "Redirected to DashBoard");
		} catch (Exception e) {
			// TODO: handle exception
			Report.FailTest(test, "Pre_Route_Detail_UI Verification Failed");
		}
	}

	public void Verify_Pre_Route_Detail_DateSelector(String Site, String FromDateValue, String ToDateValue)
			throws InterruptedException {
		///////////////////// Redirected to PreRoute Page From Dashboard///////////////
		Pre_RouteDetail.click();
		// Report.PassTest(test,"Redirected to Pre-Route Detail Page");
		Thread.sleep(2000);
		////////////// Verify All drop down multi_select

		driver.switchTo().frame("opusReportContainer");
		Thread.sleep(2000);
		////////////// Click on Reset Button ///////////////
		ResetBtn.click();
		Thread.sleep(2000);

		//////////////////// Verify To Date Must Contains the Previous Date
		try {
			String InputToDate = ToDate.getAttribute("value");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

			LocalDate localDate1 = LocalDate.parse(InputToDate, formatter);

			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			String data = df.format(new Date());
			// System Current Date
			LocalDate localDate2 = LocalDate.parse(data, formatter);
			// System.out.println("Old Date Was" + localDate1 + "Current Date is > " +
			// localDate2);
			// System.out.println("Days Gap Between >> "+ localDate1 + " and "+ localDate2+
			// " is --> " + ChronoUnit.DAYS.between(localDate1, localDate2));
			Report.PassTest(test, "Days Gap Between >> " + localDate1 + "  and  " + localDate2 + " is --> "
					+ ChronoUnit.DAYS.between(localDate1, localDate2));
		} catch (Exception e) {
			// TODO: handle exception
			Report.FailTestSnapshot(test, driver, "Current Date and To Date is Unable to Verified ",
					"ToDateNotValidated");

		}
		//////////////////////// Data is visible as selected date///////////////////
		SiteFilter.click();
		Thread.sleep(1000);
		SiteFilterSearch.click();
		SiteFilterSearch.sendKeys(Site);
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"
						+ Site + "')]"))
				.click();
		Thread.sleep(1000);

		FromDate.clear();
		FromDate.sendKeys(FromDateValue);
		ToDate.clear();
		ToDate.sendKeys(ToDateValue);
		Thread.sleep(1000);
		GoBtn.click();
		Thread.sleep(5000);
		//////////////////////////////// Validate Records
		driver.switchTo().frame("subReportContainer");
		Thread.sleep(2000);

		List<WebElement> DataRow = DetailTable.findElements(By.tagName("tr"));
		System.out.println("RowSize" + DataRow.size());
		List<WebElement> DataCol = DetailTable.findElements(By.tagName("td"));
		System.out.println("ColSize" + DataCol.size());
		for (int i = 0; i < DataRow.size(); i++) {
			for (int j = 1; j <= 5; j++) {
				if (j != 2) {
					System.out.print(DataCol.get(j).getText() + " ");
				}

			}
			System.out.println();
		}

	}

	public void Click_PreRouteDetailLink() throws InterruptedException {
		Pre_RouteDetail.click();
		Report.PassTest(test, "Redirected to Pre-Route Detail Page");
		Thread.sleep(2000);

	}

	public void Pre_Route_VerifySiteFilter(String Site) throws InterruptedException {

		try {
			driver.switchTo().frame("opusReportContainer");
			SiteFilter.click();
			Thread.sleep(1000);
			SiteFilterSearch.click();
			SiteFilterSearch.sendKeys(Site);
			Thread.sleep(2000);
			System.out.println(driver.findElement(
					By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"
							+ Site + "')]"))
					.getText());
			if (driver.findElement(
					By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"
							+ Site + "')]"))
					.getText().contains(Site)) {

				Report.PassTest(test, "Site filter is working as expected when site is >>" + Site);
			} else {

				Report.FailTestSnapshot(test, driver, "Site filter is Not working as expected when site is >>" + Site,
						"SiteFilterCheckFailed");
			}
			Thread.sleep(1000);
			driver.findElement(
					By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"
							+ Site + "')]"))
					.click();
			Thread.sleep(1000);

		} catch (Exception e) {
			Report.FailTestSnapshot(test, driver, "Unable to Verify Site Detail Filter", "SiteDetailFilterFailed");
		}

		driver.switchTo().defaultContent();
	}

	public void Pre_Route_VerifyLOBFilter(String Com, String Resi, String RollOff) {
		driver.switchTo().frame("opusReportContainer");
		SelectLOB.click();
		LOBSearchBox.click();

		if (Com != "") {
			CheckCom.click();
		}
		if (Resi != "") {
			CheckResi.click();

		}
		if (RollOff != "") {
			CheckRollOff.click();
		}

	}

	public void SelectDate(String FromDateValue, String ToDateValue) {
		driver.switchTo().frame("opusReportContainer");
		FromDate.clear();
		FromDate.sendKeys(FromDateValue);
		ToDate.clear();
		ToDate.sendKeys(ToDateValue);
		driver.switchTo().defaultContent();

	}

	public void selectActualSubView() throws IOException {
		try {
			ActualSubview.click();
			Report.PassTest(test, "Actual SubView get selected as expected");
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
		}
	}

	public void selectAllTab() throws IOException {
		try {

			AllTab.click();
			Thread.sleep(7000);
			Report.PassTest(test, "All Tab get selected as expected");
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
		}
	}

	public void selectActualSubviewAllTab() throws IOException {
		try {

			ActualSubviewAllTab.click();
			Thread.sleep(7000);
			Report.PassTest(test, "All Tab get selected as expected");
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
		}
	}

	public void validatePlanHoursExceptionsTabPerformanceSubview(Map<String, List<String>> getActualPreRouteDetailTableData,String LOB) {
		// PlanHours=Derived from EMAP

		List<Double> planUnits = new ArrayList<>();
		double expectedPlanUnits = 0.0;
		List<String> route = new ArrayList<>();

		try {

			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().equals("Plan (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							planUnits.add(Double.parseDouble("0.0"));
						} else {
							planUnits.add(Double.parseDouble(entry.getValue().get(i)));
							Util.validateFieldDecimalPlaces(Double.parseDouble(entry.getValue().get(i)), 1,
									"Plan Hours in Performance Sub View Pre Route Detail Report.");

						}

					}
				}
			}
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (int i = 0; i < route.size(); i++) {
				expectedPlanUnits = Util.getEMAPData(route.get(i), LOB, "PreRouteHours", GlobalVariables.EMAP);
				if (Util.compareNumber(planUnits.get(i), expectedPlanUnits)) {
					Report.InfoTest(test,
							"Plan hours is correct in Performance Sub View report for Route : "
									+ route.get(i) + " with Actual hours : " + planUnits.get(i)
									+ " and expected Hours : " + expectedPlanUnits);
					Report.PassTest(test, "Plan hours is as expected in Performance Sub View report");
				} else {
					Report.FailTest(test,
							"Plan hours is not as expected in Performance Sub View report for Route : "
									+ route.get(i) + " with Actual hours : " + planUnits.get(i)
									+ " and expected Hours : " + expectedPlanUnits);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Plan Hours is not as expected in Performance Sub View report Actual is : "
							+ planUnits.toString() + " and Expected is : " + expectedPlanUnits);

		}

	}

   public void ValidateDateExceptionsTabActualSubview(Map<String, List<String>> getActualPreRouteDetailTableDataActualSubview,String fromDate , String toDate) {
		
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yy");
		Date startDate = null;
		Date endDate = null;
		List<Date> routeServedDate = new ArrayList<>();
		List<String> actualRoutes = null;
		try {
			startDate = format1.parse(fromDate);
			endDate = format1.parse(toDate);
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataActualSubview.entrySet()) {
				if (entry.getKey().equals("Route")) {
					actualRoutes = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataActualSubview.entrySet()) {
				if (entry.getKey().equals("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						routeServedDate.add(format2.parse(entry.getValue().get(i).toString()));
					}
				}
			}

			for (int i = 0; i < actualRoutes.size(); i++) {
				if (routeServedDate.get(i).equals(startDate) || routeServedDate.get(i).equals(endDate)
						|| (routeServedDate.get(i).after(startDate) && routeServedDate.get(i).before(endDate))) {
					Report.InfoTest(test,
							"Route Date is correct in Pre Route Detail  Actual subview report for route : "
									+ actualRoutes.get(i) + ". Actual is : " + format1.format(routeServedDate.get(i))
									+ " and Expected is between : " + format1.format(startDate) + "and "
									+ format1.format(endDate));
					Report.PassTest(test, "Route Date is as expected in Pre Route Detail Report Exceptions Tab Actual subview ");
				} else {
					Report.FailTest(test,
							"Route Date is not as expected in Pre Route Detail Report Exceptions Tab Actual subview Actual is : "
									+ format1.format(routeServedDate.get(i)) + " and Expected is between : "
									+ format1.format(startDate) + "and " + format1.format(endDate));
				}
			}

		} catch (Exception e) {
			Report.FailTest(test, "Not able to compare date is in between the date range");
			LogClass.error(e.getMessage());
		}
		
		
		
	}
	
	
   public void validateRouteNameExceptionsTabActualSubview(Map<String, List<String>> getActualPreRouteDetailTableDataActualSubview, String expectedRouteName
		) {
	String[] routes = expectedRouteName.split(";");
	List<String> actualRoutes = null;
	try {
		for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataActualSubview.entrySet()) {
			if (entry.getKey().equals("Route")) {
				actualRoutes = entry.getValue();
			}
		}
		for (int i = 0; i < routes.length; i++) {
			if (actualRoutes.contains(routes[i])) {
				Report.InfoTest(test,
						"Route Name is correct in Actual Sub View Pre Route Detail Report Actual List is : "
								+ actualRoutes.toString() + " and Expected is : " + routes[i]);
				Report.PassTest(test, "Route Name is as expected in Actual Sub View Pre Route Detail Report");
			} else {
				Report.FailTest(test,
						"Route Name is not as expected in Actual Sub View Pre Route Detail Report Actual List is : " + actualRoutes.toString()
								+ " and Expected is : " + routes[i]);
			}
		}

	} catch (Exception e) {
		Report.FailTest(test,
				"Route is not as expected in Actual Sub View Pre Route Detail Report Actual List is : "
						+ actualRoutes.toString() + " and Expected is : " + routes.toString());
	}

}
	
   public void validateDriverNameExceptionsTabActualSubview(Map<String, List<String>> getActualPreRouteDetailTableDataActualSubview, String expectedDriverName) {
		String[] drivers = expectedDriverName.split(";");
		List<String> actualDrivers = null;
		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataActualSubview.entrySet()) {
				if (entry.getKey().equals("Driver")) {
					actualDrivers = entry.getValue();
				}
			}
			for (int i = 0; i < drivers.length; i++) {
				if (actualDrivers.contains(drivers[i])) {
					Report.InfoTest(test,
							"Driver Name is correct in Actual Sub View Pre Route Detail report Actual List is : "
									+ actualDrivers.toString() + " and Expected is : " + drivers[i]);
					Report.PassTest(test, "Driver Name is as expected in Actual sub view Pre Route Detail Report");
				} else {
					Report.FailTest(test,
							"Driver Name is not as expected in Actual Sub View Pre Route Detail Report Actual List is : " + actualDrivers.toString()
									+ " and Expected is : " + drivers[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Driver Name is not as expected in Actual Sub View  Pre Route Detail Report Actual List is : "
							+ actualDrivers.toString() + " and Expected is : " + drivers.toString());
		}

	}
	
   public void validateLOBExceptionsTabActualSubview(Map<String, List<String>> getActualPreRouteDetailTableDataActualSubview, String expectedLOB) {
		String actualLOB = null;
		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataActualSubview.entrySet()) {
				if (entry.getKey().equals("LOB")) {

					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualLOB = null;
						} else {
							actualLOB = entry.getValue().get(i).toString();
						}
					}
				}
			}
			if (actualLOB.equals("RES"))
				actualLOB = "Residential";
			if (actualLOB.equals("COM"))
				actualLOB = "Commercial";
			if (actualLOB.equals("ROL"))
				actualLOB = "Roll Off";

			if (actualLOB.equals(expectedLOB)) {

				Report.InfoTest(test, "LOB is correct in Actual Sub View Pre Route Detail report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
				Report.PassTest(test, "LOB is as expected in Actual Sub View report");
			} else {
				Report.FailTest(test, "LOB is not as expected in Actual Sub View Pre Route Detail report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
			}
		} catch (Exception e) {
			Report.FailTest(test, "LOB is not as expected in Actual Sub View Pre Route Detail report Actual List is : " + actualLOB + " and Expected is : " + expectedLOB);
		}
	}
 
   public void validateSubLOBExceptionsTabActualSubview(Map<String, List<String>> getActualPreRouteDetailTableDataActualSubview) {

		List<String> actualSubLOB = new ArrayList<>();
		List<String> expectedSubLOB = new ArrayList<>();
		List<String> route = new ArrayList<>();

		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataActualSubview.entrySet()) {
				if (entry.getKey().equals("Sub LOB")) {
					actualSubLOB = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataActualSubview.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			expectedSubLOB = Util.getDataFromDB(
					"select UNIQUEID from OCS_ADMIN.TP_SUBLOB where PKEY IN (select OCS_ADMIN.TP_ROUTE.FK_SUBLOB from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + "))");

			for (int i = 0; i < route.size(); i++) {
				if (actualSubLOB.contains(expectedSubLOB.get(0))) {
				//if (actualSubLOB.get(i).equals(expectedSubLOB.get(i))) {
					Report.InfoTest(test,
							"Sub LOB is correct in Actual Sub View Pre Route Detail report for Route : "
									+ route.get(i) + " with Actual SubLOB : " + actualSubLOB.get(0)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
					Report.PassTest(test, "Sub LOB is as expected in Actual Sub View report");
				} else {
					Report.FailTest(test,
							"Sub LOB is not as expected in Actual sub view Pre Route report for Route : "
									+ route.get(i) + " with Actual Sub LOB : " + actualSubLOB.get(i)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Sub LOB is not as expected in Actual Sub view Pre Route Detail report Actual is : "
					+ actualSubLOB.toString() + " and Expected is : " + expectedSubLOB.toString());
		}

	}

   public void validateRouteTypeExceptionsTabActualSubview(Map<String, List<String>> getActualPreRouteDetailTableDataActualSubview) {
		List<String> actualRouteType = new ArrayList<>();
		List<String> expectedRouteType = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		String expectedRouteTypes = "";
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");

		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataActualSubview.entrySet()) {
				if (entry.getKey().equals("Route Type")) {
					actualRouteType = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataActualSubview.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataActualSubview.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			expectedRouteType = Util.getDataFromDB(
					"select name from ocs_admin.tp_vehicletype where pkey in (select fk_vehicletype from ocs_admin.tp_vehicle where pkey in (select fk_vehicle from ocs_admin.tp_routeorder where FK_ROUTE IN ( select PKEY from OCS_ADMIN.TP_ROUTE where ID IN ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + "))) ");

			for (int i = 0; i < route.size(); i++) {
				if (expectedRouteType.get(0).equals("COMM FRONT LOAD"))
					expectedRouteTypes = "FC";

				if (expectedRouteType.get(0).equals("RES REARLOAD"))
					expectedRouteTypes = "RR";
				
				if (expectedRouteType.get(0).equals("ROLL OFF"))
					expectedRouteTypes = "O";

				if (actualRouteType.contains(expectedRouteTypes)) {
			//	if (actualRouteType.get(i).equals(expectedRouteTypes)) {
					Report.InfoTest(test,
							"Route Type  is correct in Actual Sub View Pre Route Detail report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type  : " + expectedRouteTypes);
					Report.PassTest(test, "Route Type is as expected in Actual Sub View Pre Route Detail report");
				} else {
					Report.FailTest(test,
							"Route Type is not as expected in Actual sub view Pre Route Detail report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type : " + expectedRouteTypes);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test,
					"Route Type  is not as expected in Actual Sub view Pre Route Detail report Actual is : "
							+ actualRouteType.toString() + " and Expected is : " + expectedRouteType.toString());
		}

	}

   public void validatePlanHoursExceptionsTabActualSubview(Map<String, List<String>> getActualPreRouteDetailTableData,String LOB) {
		// PlanHours=Derived from EMAP

		List<Double> planUnits = new ArrayList<>();
		double expectedPlanUnits = 0.0;
		List<String> route = new ArrayList<>();

		try {

			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().equals("Plan (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							planUnits.add(Double.parseDouble("0.0"));
						} else {
							planUnits.add(Double.parseDouble(entry.getValue().get(i)));
							Util.validateFieldDecimalPlaces(Double.parseDouble(entry.getValue().get(i)), 1,
									"Plan Hours in Actual Sub View Pre Route Detail Report.");

						}

					}
				}
			}
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (int i = 0; i < route.size(); i++) {
				expectedPlanUnits = Util.getEMAPData(route.get(i), LOB, "PreRouteHours", GlobalVariables.EMAP);
				if (Util.compareNumber(planUnits.get(i), expectedPlanUnits)) {
					Report.InfoTest(test,
							"Plan hours is correct in Actual Sub View report for Route : "
									+ route.get(i) + " with Actual hours : " + planUnits.get(i)
									+ " and expected Hours : " + expectedPlanUnits);
					Report.PassTest(test, "Plan hours is as expected in Actual Sub View report");
				} else {
					Report.FailTest(test,
							"Plan hours is not as expected in Actual Sub View report for Route : "
									+ route.get(i) + " with Actual hours : " + planUnits.get(i)
									+ " and expected Hours : " + expectedPlanUnits);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Plan Hours is not as expected in Actual Sub View report Actual is : "
							+ planUnits.toString() + " and Expected is : " + expectedPlanUnits);

		}

	}

   public void validateLeaveYardTimeExceptionTabActualSubview(Map<String, List<String>> getActualPreRouteDetailTableDataActualSubview) {
		
/*
		List<Date> actualPreRouteTime = new ArrayList<>();
		Date expectedPreRouteTime = null;*/
		List<Date> leaveYardTime = new ArrayList<>();
		List<Date> clockInTime = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		try {
			
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataActualSubview.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataActualSubview.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableDataActualSubview.entrySet()) {
				if (entry.getKey().equals("Leave Yard")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {

							clockInTime.add(dateFormat.parse("00:00"));

						} else {
							clockInTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
						}

					}
				}
			}

			leaveYardTime = Util.getDateDataFromDB(
					"select TO_CHAR(FROMYARDSTAMP - (6 / 24) , 'HH:MI') from OCS_ADMIN.TP_RO_RESULT where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID  IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE  IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");

			for (int i = 0; i < route.size(); i++) {
				if (leaveYardTime.equals(clockInTime)) {
					Report.PassTestScreenshot(test, driver, "Validated Leave Yard Time Sucessfully Actual is" + clockInTime + "Expected is "+ leaveYardTime , "PassedLeaveYardTime");
					
				}
				else {
					Report.FailTestSnapshot(test, driver,"Unable to Validate Leave Yard Time Actual is" + clockInTime + "Expected is "+ leaveYardTime , "FailedLeaveYardTime");
				}
				
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Unable to Validate Leave Yard Time Actual is" + clockInTime + "Expected is "+ leaveYardTime);
		}

	}
   
   public Map<String, List<String>> PreRouteSummaryDetailTableDataActualSubview() {
		System.out.println("inside Actual Pre Route Summary Detail Table Data Actual Subview");
		Util.CaptureScreenshot("Actual Pre Route Summary Detail Table Data");
		Map<String, List<String>> PreRouteSummaryDetailTableDataActualSubview = new HashMap<>();
		try {
			PreRouteSummaryDetailTableDataActualSubview = readTable(PreRouteDetailTableColumn, PreRouteDetailTableRow, "t2e");
			Report.PassTestScreenshot(test, driver, "Pre Route Detail table Actual Subview data read successfully",
					"Pre Route Summary Detail Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Pre Route Detail table Actual Subview data");
		}
		for (Entry<String, List<String>> entry : PreRouteSummaryDetailTableDataActualSubview.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return PreRouteSummaryDetailTableDataActualSubview;
	}
   
   
}
