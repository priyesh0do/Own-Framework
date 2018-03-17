		package ObjectRepository.General;
		
		import org.openqa.selenium.By;
		import org.openqa.selenium.Capabilities;
		import org.openqa.selenium.Keys;
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
		import java.net.MalformedURLException;
		import java.net.URL;
		import java.text.ParseException;
		import java.text.SimpleDateFormat;
		import java.time.LocalDate;
		import java.time.format.DateTimeFormatter;
		import java.time.temporal.ChronoUnit;
		import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
		import java.util.Date;
import java.util.HashMap;
import java.util.List;
		import java.util.Locale;
import java.util.Map;

import java.util.stream.IntStream;

import java.util.Map.Entry;


import com.aventstack.extentreports.ExtentTest;
		import com.google.common.base.Function;
		import com.sun.tools.javac.util.*;
		
		import ObjectRepository.Confirmation.RouteDetails;
		import TestData.GlobalTestData;
		import io.appium.java_client.pagefactory.AndroidFindBy;
		import io.appium.java_client.pagefactory.iOSFindBy;
import net.sourceforge.cobertura.reporting.ReportName;
import SupportLibraries.*;
		
		public class FilterSection extends RouteDetails{
		
			ExtentTest test;
			WebDriver driver;
		
			public FilterSection(WebDriver driver, ExtentTest test) {
				this.driver = driver;
				this.test = test;
				PageFactory.initElements(driver, this);
			}
			@FindBy(xpath = "//table[@id='dtPreRouteSummary']/thead/tr/th")
			List<WebElement> preRouteSummaryTableColumns;
		
			@FindBy(xpath = "//table[@id='dtPreRouteSummary']/tbody/tr")
			List<WebElement> preRouteSummaryTableRows;
			
			/////////////////Pre-RouteDetail
			@FindBy(xpath="(.//*[@class='report' and contains(text(),'Pre-Route Detail')])[2]")
			WebElement Pre_RouteDetail;
			
			////MultiselectDropDown
			@FindBy(xpath="//*[@class='ui-multiselect ui-widget ui-state-default ui-corner-all' and @type='button']")
			WebElement MultiselectDropDown;
			
			///loader
			@FindBy(id="rdWaitImage")
			WebElement loader;
			
			////////////PreRouteHeader
			@FindBy(xpath="//*[@id='rdDataTableDiv-dtPreRouteSummary']/table/thead/tr")
			WebElement PreRouteHeader;
			
			////////////PreRouteBody
			@FindBy(xpath="//*[@id='rdDataTableDiv-dtPreRouteSummary']/table/tbody/tr")
			WebElement PreRouteBdy;
			
			/////////////ExceptionsAll
			@FindBy(xpath="//*[@id='rdTabs']/ul")
			WebElement ExceptionsAll;
			
			////////////SubViewRadiobutton
			@FindBy(xpath="//*[@id='rdInputRadioOptions']")
			WebElement SubViewRadiobutton;
			
			////////////VerifySubViewCol
			@FindBy(xpath="//*[@id='t2e']/colgroup")
			WebElement VerifySubViewCol;
			
			////RouteDetailTable
			@FindBy(xpath="//*[@id='dtRouteDetail']/tbody")
			WebElement RouteDetailTable;
			
			///PreRouteDetailTable
			@FindBy(xpath="//*[@id='dtPreRouteDetail']/tbody")
			WebElement PreRouteDetailTable;
			
			//dtPostRouteDetail
			///PostRouteDetailTable
			@FindBy(xpath="//*[@id='dtPostRouteDetail']/tbody")
			WebElement PostRouteDetailTable;
			
			///dtIdleTmDetail
			////IdelSummaryTable
			@FindBy(xpath="//*[@id='dtIdleTmDetail']/tbody")
			WebElement IdleSummaryTable;
			
			//dtDisposalTimeDetail
			@FindBy(xpath="//*[@id='dtDisposalTimeDetail']/tbody")
			WebElement dtDisposalTimeDetail;
			
			//dtSeqComplianceDetail
			@FindBy(xpath="//*[@id='dtSeqComplianceDetail']/tbody")
			WebElement dtSeqComplianceDetail;
			
			//////DetailTable
			@FindBy(xpath="//*[@id='t2e']/tbody")
			WebElement DetailTable;
			
			////DetailTableIdle
			@FindBy(xpath="//*[@id='t2']/tbody")
			WebElement DetailTableIdle;
			
			////DetailTableEffPerformance
			@FindBy(xpath="//*[@id='t2h']/tbody")
			WebElement DetailTableEffPerformance;
			
			/////////////////Redirected to DashBoard///
			@FindBy(xpath="//a[@href='/opus-report/report/report?reportname=Dashboard' and contains(text(),'Dashboard')]")
			WebElement Dashboard;
			
			//////////////////ToDate
			@FindBy(xpath="//*[@id='inpToDate' and @name='inpToDate']")
			WebElement ToDate;
			
			//////////From Date
			@FindBy(xpath="//*[@id='inpFromDate' and @name='inpFromDate']")
			WebElement FromDate;
			
			/////Reset Button /////////////////////
			@FindBy(xpath="//*[@id='btnReset' and @type='button']")
			WebElement ResetBtn;
			///Go Btn
			@FindBy(xpath="//*[@id='btnSubmitGo' and @type='button']")
			WebElement GoBtn;
			
			//SiteFilter
			@FindBy(xpath="//*[@id='divSiteFilter']/span/button")
			WebElement SiteFilter;
		
			//SelectLOB
			@FindBy(xpath="//*[@id='divLOBFilter']/span/button")
			WebElement SelectLOB;
			
			///SelectLOBCustPropDetail
			@FindBy(xpath="//*[@id='divLOBFilterS']/span/button")
			WebElement SelectLOBCustPropDetail;
			
			///SiteFilterSearch
			@FindBy(xpath="(//*[@class='ui-multiselect-filter']/input)[1]")
			WebElement SiteFilterSearch;
			
			//LOBSearchBox
			@FindBy(xpath="(//*[@class='ui-multiselect-filter']/input)[2]")
			WebElement LOBSearchBox;
			
			///MultipleSite
			@FindBy(xpath="(//*[@class='ui-multiselect-filter']/input[@type='search'])[4]")
			WebElement MultipleSite;
			
			///LObSearchBoxForPreRouteSummary
			@FindBy(xpath="(//*[@class='ui-multiselect-filter']/input)[5]")
			WebElement LOBSearchBoxForPreRouteSummary;
			//CheckAllLOB
			@FindBy(xpath="//*[@id='ui-multiselect-islLOBFilter-option-0']")
			WebElement CheckAllLOB;
			
			//CheckCom
			@FindBy(xpath="//*[@id='ui-multiselect-islLOBFilter-option-1']")
			WebElement CheckCom;
			
			//CheckResi
			@FindBy(xpath="//*[@id='ui-multiselect-islLOBFilter-option-2']")
			WebElement CheckResi;
			
			//CheckRollOff
			@FindBy(xpath="//*[@id='ui-multiselect-islLOBFilter-option-3']")
			WebElement CheckRollOff;
			@FindBy(xpath = ".//*[@id='divSiteFilter']/span/button/span[1]")
			WebElement siteFilterarrow;
		
			@FindBy(xpath = ".//*[@id='divRteFilter']/span/button/span[1]")
			WebElement routeFilterarrow;
		
			@FindBy(xpath = ".//div[13]/div/ul/li[2]/a/span[@class='ui-icon ui-icon-circle-close']")
			WebElement routePopupClose;
		
			@FindBy(xpath = ".//*[@id='divDriverFilter']/span/button/span[1]")
			WebElement driverFilterarrow;
			
			@FindBy(xpath = "//*[@id='divDriverFilter']/span/button")
			WebElement driverFilterButton;
			
			@FindBy(xpath = "//*[@id='divDoWFilter']/span/button")
			WebElement dayOfWeekFilterButton;
			
			@FindBy(xpath = "//*[@id='divDoWFilter']/span/button/span[1]")
			WebElement dayOfWeekFilterarrow;
			
			@FindBy(xpath = ".//div[20]/div/ul/li[2]/a/span[@class='ui-icon ui-icon-circle-close']")
			WebElement dayOfWeekPopupClose;
			
			@FindBy(xpath = ".//div[14]/div/ul/li[2]/a/span[@class='ui-icon ui-icon-circle-close']")
			WebElement driverPopupClose;
		
			@FindBy(xpath = "//*[@id='divLOBFilter']/span/button/span[1]")
			WebElement multiLOBDropdownButton;
			
			@FindBy(xpath = "//*[contains(@id,'divLOB')]/span/button")
			WebElement LOBDropdownButton;
			
			@FindBy(xpath = "//*[@id='divSubLOBFilter']/span/button/span[1]")
			WebElement subLOBDropdownButton;
			
			@FindBy(xpath = "//*[@id='divRMFilter']/span/button/span[1]")
			WebElement routeManagerDropdownButton;
			
			@FindBy(xpath = "//*[@id='divRteTypeFilter']/span/button/span[1]")
			WebElement routeTypeDropdownButton;
			
			@FindBy(xpath = "//*[@id='divRptGrpFilter2']/span/button/span[1]")
			WebElement reportGroupDropdownButton;
		
			@FindBy(xpath = "//div[contains(@style,'display: block')]//a[@class='ui-multiselect-close']/span")
			WebElement filterPopupClose;
			
			@FindBy(xpath = ".//div[10]/div/ul/li[2]/a/span[@class='ui-icon ui-icon-circle-close']")
			WebElement subLOBPopupClose;
		
			@FindBy(xpath = ".//*[@id='inpToDate']")
			WebElement toDateTextBox;
		
			@FindBy(xpath = ".//*[@id='inpFromDate']")
			WebElement fromDatetextBox;
			
			@FindBy(id = "inpTimeOption_2")
			WebElement dayOfWeekRadio;
		
			@FindBy(xpath = ".//*[@id='lblDayOfWeekValue']")
			WebElement dayOfWeekLabel;
		
			@FindBy(id = "ui-multiselect-islLOBFilterS-option-0")
			WebElement commercialLOB;
		
			@FindBy(xpath = "//input[@id='ui-multiselect-islLOBFilterS-option-1']")
			WebElement residentialLOB;
		
			@FindBy(id = "ui-multiselect-islLOBFilterS-option-2")
			WebElement rollOffLOB;
		
			@FindBy(xpath = ".//*[@id='btnSubmitGo']")
			WebElement GoButton;
		
			@FindBy(xpath = ".//*[@id='btnReset']")
			WebElement ResetButton;
		
			@FindBy(id = "lblReportTitle")
			WebElement reportTitle;
			
			@FindBy(id = "lblCrumb1")
			WebElement backToRMDashboard;
			
			
			@FindBy(xpath = "//*[@id='divSiteFilter']/span/button/span[2]")
			WebElement siteFilterValue;
			
			@FindBy(xpath = ".//*[@id='divRMFilter']/span/button/span[1]")
			WebElement routeManagerFilterarrow;
			
			@FindBy(xpath = "//*[@id='divRMFilter']/span/button/span[2]")
			WebElement routeManagerFilterValue;
			
			@FindBy(xpath = ".//*[@id='divRptGrpFilter1']/span/button/span[1]")
			WebElement reportGroupFilterarrow;
			
			@FindBy(xpath = "//*[@id='divRptGrpFilter1']/span/button/span[2]")
			WebElement reportGroupFilterValue;
			
			@FindBy(xpath = "//*[@id='divRptGrpFilter2']/span/button/span[2]")
			WebElement multiReportGroupFilterValue;
			
			@FindBy(xpath = "//*[@id='divGroupFilter']/span/button/span[2]")
			WebElement tierFilterValue;
			
			@FindBy(xpath = "//*[@id='divAreaFilter']/span/button/span[2]")
			WebElement areaFilterValue;
			
			@FindBy(xpath = "//*[@id='divBUFilter']/span/button/span[2]")
			WebElement BUFilterValue;		
			
			@FindBy(xpath = "//*[@id='divLOBFilterS']/span/button/span[2]")
			WebElement LOBFilterValue;	
			
			@FindBy(xpath = "//*[@id='divLOBFilter']/span/button/span[2]")
			WebElement multiLOBFilterValue;	
			
			@FindBy(xpath = "//*[@id='divSubLOBFilter']/span/button/span[2]")
			WebElement subLOBFilterValue;	
			
			@FindBy(xpath = "//*[@id='divRteTypeFilter']/span/button/span[2]")
			WebElement routeTypeFilterValue;
			
			@FindBy(xpath = "//*[@id='divRteFilter']/span/button/span[2]")
			WebElement routeFilterValue;
			
			@FindBy(xpath = "//*[@id='divDriverFilter']/span/button/span[2]")
			WebElement driverFilterValue;
			
			
			
			@FindBy(xpath= "//*[@id='main-nav']/ul/li[1]/a")
			WebElement dashboardMenu;
			
			@FindBy(xpath= "//*[@id='main-nav']/ul/li[2]/a")
			WebElement performanceMenu;
			
			@FindBy(xpath= "//*[@id='main-nav']/ul/li[3]/a")
			WebElement confirmationMenu;
			
			//////SelectArea
			@FindBy(xpath="//*[@id='divAreaFilter']/span/button")
			WebElement SelectArea;
			
			//SelectBusinessUnit
			@FindBy(xpath="//*[@id='divBUFilter']/span/button")
			WebElement SelectBusinessUnit;
			
			//SelectTier
			@FindBy(xpath="//*[@id='divGroupFilter']/span/button")
			WebElement SelectTier;
			
			///EnterArea
			@FindBy(xpath="(//*[@class='ui-multiselect-filter']/input[@type='search'])[2]")
			WebElement EnterArea;
			
			////EnterBu
			@FindBy(xpath="(//*[@class='ui-multiselect-filter']/input[@type='search'])[3]")
			WebElement EnterBu;
			
			/////EnterTier
			@FindBy(xpath="(//*[@class='ui-multiselect-filter']/input[@type='search'])[1]")
			WebElement EnterTier;
			
			///////CloseTier
			@FindBy(xpath="(//*[@class='ui-multiselect-close']/span[@class='ui-icon ui-icon-circle-close'])[1]")
			WebElement CloseTier;
			
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
			
			//ui-datepicker-month
			@FindBy(className = "ui-datepicker-month")
			WebElement datePickerMonth;
			
		    // ui-datepicker-year
			@FindBy(className = "ui-datepicker-year")
			WebElement datePickerYear;
			
			//CompleteRouteSummaryGrid
			@FindBy(xpath="//*[@id='tblRouteSummary' and @role='grid']")
		    WebElement Validate;
			
			//confirmedRouteCount
			@FindBy(id="confirmedCount")
			WebElement confirmedCount;
			
			//flagged Route Count
			@FindBy(id="flaggedCount")
			WebElement flaggedCount;
			
			//open Route Count
			@FindBy(id="openCount")
			WebElement openCount;
			
			//TotalRouteCount
			@FindBy(id="totalCount")
			WebElement totalCount;
			
			//First Row Under RouteSummary If records is available
			@FindBy(xpath="//*[@id='tblRouteSummary' and @role='grid']/tbody/tr[1]")
			WebElement VerifyRecordsInRouteSummary;
			
			//printCoverSheet Under ReportSummary
			@FindBy(xpath="//*[@id='printCoverSheet']")
			WebElement printCoverSheet;
			
			//CustomerTicket Under ReportSummary
			@FindBy(xpath="//*[@id='printRouteSheet']")
			WebElement CustomerTicket;
			
			//ShowLog Under ReportSummary
			@FindBy(xpath="//*[@id='changeLog']")
			WebElement ShowLog;
			
			//ShowZeroWorkload  Under ReportSummary
			@FindBy(xpath="//*[@id='workload']")
			WebElement ShowZeroWorkload;
			
			//CrazyFilter Under ReportSummary
			@FindBy(xpath="//*[@id='tblRouteSummary_filter']/label/input")
			WebElement SearchFilter;
			
			//SelectFirstRouteAfterSearching
			@FindBy(xpath="//*[@id='tblRouteSummary']/tbody/tr/td[5]/a")
			WebElement ClickFirstRouteID;
			
			//ReportRequestsSubmitted
		  @FindBy(xpath="//*[@id='reportreqs']")
		  WebElement ReportRequestsSubmitted;
		  
		  //RefreshReport Under Report Requests Submitted
		
		  @FindBy(xpath="//*[@id='refreshReports']")
		  WebElement RefreshReport;
		  
		  //PaginationTotalcount
		  @FindBy(xpath="//*[@id='tblRouteSummary_paginate']/span[5]")
		  WebElement PaginationTotalcount;
		  
		  //PaginationNext
		  @FindBy(xpath="//*[@id='tblRouteSummary_paginate']/span[6]")
		  WebElement PaginationNext;
		  
		  //PaginationFirst
		  @FindBy(xpath="//*[@id='tblRouteSummary_paginate']/span[1]")
		  WebElement PaginationFirst;
		  
		  //PageCountText
		  @FindBy(xpath="//*[@id='tblRouteSummary_paginate']/input")
		  WebElement PageCountText;
		  
		  //TotalNumberOfPages
		  @FindBy(xpath="//*[@class='paginate_total']")
		  WebElement TotalNumberOfPages;   
		  
		  //VerifyStatus
		  @FindBy(xpath="//*[@id='tblRouteSummary_wrapper']/table/tbody/tr/td[2]")
		  WebElement VerifyStatus; 
		  
		  //GetWorkStatus
		  @FindBy(xpath="//*[@id='tblRouteSummary_wrapper']/table/tbody/tr/td[3]")
		  WebElement GetWorkStatus; 
		  
		  ///WebTable Body
		  @FindBy(xpath="//*[@id='tblRouteSummary']/tbody")
		  WebElement Table;
		  
		  //// Multiselect
		  @FindBy(xpath="//*[@id='multiCheck' and @type='checkbox']")
		  WebElement Multiselect;
		  
		  //ConfirmPrintNo
		  @FindBy(xpath="//*[@id='confirmPrintNo' and @type='button']")
		  WebElement ConfirmPrintNo;
		  
		  //ConfirmPrintYes
		  @FindBy(xpath="//*[@id='confirmPrintYes' and @type='button']")
		  WebElement ConfirmPrintYes;
		  
		  //AllCustomerTicket
		  @FindBy(xpath="//*[@id='printCustomerTicketAll' and contains(text(),'All Customer Tickets')]")
		  WebElement AllCustomerTicket;
		  
		  ////SignedCustomerTicket
		  @FindBy(xpath="//*[@id='printCustomerTicketSigned' and contains(text(),'Signed Customer Tickets')]")
		  WebElement SignedCustomerTicket;
		  
		  ///GenralInforrmation
		  @FindBy(xpath="//*[@id='generalInfo_groupContainer']")
		  WebElement GenralInforrmation; 
		  
		  ////CustDetailsPage
		  @FindBy(xpath="//*[@class='generalInfowrap']/div[@class='generalInfo customerInfo']")
		  WebElement CustDetailsPage;  
		  
		  //BacktoRouteDetail
		  @FindBy(xpath="//a[@class='clickCancel returntoRoute' and contains(text(),'Back to Route Detail')]")
		  WebElement BacktoRouteDetail;
		  
		  //WaitRouteSummary
		  @FindBy(xpath="//*[@id='tblRouteSummary_wrapper']")
		  WebElement WaitRouteSummary;
		  
		  //SwitchToManualMode
		  @FindBy(xpath="//*[@id='switch-to-manual-mode-button' and contains(text(),'Switch To Manual Mode')]")
		  WebElement SwitchToManualMode;
		  
		//YesSwitchToManualMode
		  @FindBy(xpath="//*[@id='continue-manual-mode' and @type='button']")
		   WebElement  YesSwitchToManualMode;
		  
		  
		//HelperTab
		  @FindBy(xpath=".//*[@id='rdCaption_tabHlp']")
		  WebElement HelperTab;
		  
		  //GraphTextHigh
		  @FindBy(xpath="//*[@id='cht001']/div[1]/div[1]/span[1]")
		  WebElement GraphTextHigh;
			
		  //SubLob
		  @FindBy(xpath="//*[@id='divSubLOBFilter']/span/button")
		  WebElement SubLobBtn;
		  
		  //RouteTypeBtn
		  @FindBy(xpath="//*[@id='divRteTypeFilter']/span/button")
		  WebElement RouteTypeBtn;
		  
		  ////SelectDriverBtn
		  @FindBy(xpath="//*[@id='divDriverFilter']/span/button")
		  WebElement SelectDriverBtn;
		  
		  
		  //SelectRM
		  @FindBy(xpath="//*[@id='divRMFilter']/span/button")
		  WebElement SelectRM;
		  
		  //SubLobSearch
		  @FindBy(xpath="//div[contains(@class, 'ui-multiselect-menu') and contains(@style, 'display: block')]//input[@type='search']")
		  WebElement SubLobSearch;
		  
		  //CommonSearchClick
		  @FindBy(xpath="//div[contains(@class, 'ui-multiselect-menu') and contains(@style, 'display: block')]//input[@type='search']")
		  WebElement CommonSearchClick;
		  
		  //RouteTypeSearch
		  @FindBy(xpath="(//*[@class='ui-multiselect-filter']/input[@type='search'])[5]")
		  WebElement RouteTypeSearch;
		  
		  ///DriverSearch
		  @FindBy(xpath="(//*[@class='ui-multiselect-filter']/input[@type='search'])[7]")
		  WebElement DriverSearch;
		  
		  ////RMSearch
		  @FindBy(xpath="(//*[@class='ui-multiselect-filter']/input[@type='search'])[4]")
		  WebElement RMSearch;
		  
		  ////ALLTabLink
		  @FindBy(xpath="//*[@id='rdCaption_tabAll']")
		  WebElement ALLTabLink;
		  
		  
		  ///SelectTruck
		  @FindBy(xpath="//*[@id='divTruckFilter']/span/button")
		  WebElement SelectTruck;
		  
		  //SelectTruckClick
		  @FindBy(xpath="(//*[@class='ui-multiselect-filter']/input[@type='search'])[9]")
		  WebElement SelectTruckClick;
		  
				  
		  
		  //SelectCategories
		  @FindBy(xpath="//*[@id='divCatFilter']/span/button")
		  WebElement SelectCategories;
		  
		  //SelectCategoriesClick
		  @FindBy(xpath="(//*[@class='ui-multiselect-filter']/input[@type='search'])[10]")
		  WebElement SelectCategoriesClick;
		  
		  
		  //SelectReason
		  @FindBy(xpath="//*[@id='divRsnFilter']/span/button")
		  WebElement SelectReason;
		  
		  //SelectReasonClick
		  @FindBy(xpath="(//*[@class='ui-multiselect-filter']/input[@type='search'])[11]")
		  WebElement SelectReasonClick;
		  
		  ///SelectFirstDownTime
		  @FindBy(xpath="//*[@id='t2']/tbody/tr/td[contains(@id,'colDownTm_Row1')]/a")
		  WebElement SelectFirstDownTime;
		  
		  //GetFirstTruckId
		  @FindBy(xpath="//*[@id='t2']/tbody/tr/td[contains(@id,'colTruckId_Row1')]/span")
		  WebElement GetFirstTruckId;
		  
		  //GetFirstCategory
		  @FindBy(xpath="//*[@id='t2']/tbody/tr/td[contains(@id,'colCategory_Row1')]/span")
		  WebElement GetFirstCategory;
		  
		  //GetFirstReason
		  @FindBy(xpath="//*[@id='t2']/tbody/tr/td[contains(@id,'ColReason_Row1')]/span")
		  WebElement GetFirstReason;
		  
		  //SelectSegmentSummary
		  @FindBy(xpath="//*[@id='inpSubView_3']")
		  WebElement SelectSegmentSummary;
		  
		  //RouteButton
		  @FindBy(xpath="//*[@id='divRteFilter']/span/button")
		  WebElement RouteButton;
		  
		  //RouteButtonclick
		  @FindBy(xpath="(//*[@class='ui-multiselect-filter']/input[@type='search'])[6]")
		  WebElement RouteButtonclick;
		  
		  //GetLOBText
		  @FindBy(xpath="//*[@id='lblReportTitle' and @class='header1']")
		  WebElement GetLOBText;
		  
		  ///ClickonHelperTab
		  @FindBy(xpath="//*[@id='rdCaption_tabHlp']")
		  WebElement ClickonHelperTab;
		  
			@FindBy(xpath="//*[@id='divDriverComments']/span/button")
			WebElement driverCommentutton;
		  
		
			public void selectSite(String siteId) throws InterruptedException, IOException {
		
				try {
					Thread.sleep(2000);
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
					siteFilterarrow.click();
					Thread.sleep(1000);
					driver.findElement(By.xpath(
							".//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[text()='" + siteId + "']"))
							.click();
					try
					{
					filterPopupClose.click();
					}
					catch(Exception e)
					{
						System.out.println("Single site can be selected only");
					}
					Thread.sleep(1000);
					driver.switchTo().defaultContent();
					Report.PassTest(test, "Site : " + siteId + " was selected successfully");
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to select Site");
		
				}
		
			}
		
			public void selectLOB(String LOB) throws InterruptedException, IOException {
				try {
					Thread.sleep(2000);
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");					
					LOBDropdownButton.click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//span[text()='" + LOB + "']")).click();
					//filterPopupClose.click();
					//System.out.println("LOB : " + LOB + " was selected successfully");
					Thread.sleep(2000);
					driver.switchTo().defaultContent();
					Report.PassTest(test, "LOB : " + LOB + " was selected successfully");
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to select LOB");
		
				}
		
			}
			
			public void selectMultiLOB(String LOB) throws InterruptedException, IOException {
				try {
					Thread.sleep(2000);
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");					
					multiLOBDropdownButton.click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//span[text()='" + LOB + "']")).click();
					try
					{
						filterPopupClose.click();
					}
					catch(Exception e)
					{
						System.out.println("Only single LOB can be selected");
					}
					//System.out.println("LOB : " + LOB + " was selected successfully");
					//Thread.sleep(2000);
					driver.switchTo().defaultContent();
					Report.PassTest(test, "LOB : " + LOB + " was selected successfully");
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to select LOB");
		
				}
		
			}
			
			public void selectSubLOB(String subLOB) {
				try {
					Thread.sleep(2000);
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");					
					subLOBDropdownButton.click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//span[text()='" + subLOB + "']")).click();
					filterPopupClose.click();
					//System.out.println("Sub LOB : " + subLOB + " was selected successfully");
					Thread.sleep(2000);
					driver.switchTo().defaultContent();
					Report.PassTest(test, "Sub LOB : " + subLOB + " was selected successfully");
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to select Sub LOB");
		
				}
				
			}

			public void selectRouteManager(String routeManager) {
				
				try {
					Thread.sleep(2000);
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");					
					//routeManagerDropdownButton.click();
					driver.findElement(By.id("divRMFilter")).click();
					// driver.findElement(By.cssSelector("label.ui-corner-all.ui-state-hover
					// > span")).click();
					Thread.sleep(2000);
					if(routeManager.equals("NA"))
					{
						driver.findElement(By.id("ui-multiselect-islRMFilter-option-1")).click();
						
					}
					else
					{
					driver.findElement(By.xpath("//span[text()='" + routeManager + "']")).click();
					}
					filterPopupClose.click();
					//System.out.println("Route Manager : " + routeManager + " was selected successfully");
					Thread.sleep(2000);
					driver.switchTo().defaultContent();
					Report.PassTest(test, "Route Manager : " + routeManager + " was selected successfully");
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to select Route Manager");
		
				}
				
			}

			public void selectRouteType(String routeType) {
				try {
					Thread.sleep(2000);
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");					
					routeTypeDropdownButton.click();
					// driver.findElement(By.cssSelector("label.ui-corner-all.ui-state-hover
					// > span")).click();
					Thread.sleep(2000);
					if(routeType.equals("NA"))
					{
						driver.findElement(By.id("ui-multiselect-islRteTypeFilter-option-1")).click();
						
					}
					else
					{
					driver.findElement(By.xpath("//span[text()='" + routeType + "']")).click();
					}
					filterPopupClose.click();
					//System.out.println("Route Type : " + routeType + " was selected successfully");
					Thread.sleep(2000);
					driver.switchTo().defaultContent();
					Report.PassTest(test, "Route Type : " + routeType + " was selected successfully");
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to select Route Type");
		
				}
				
			}

			public void selectReportGroup(String reportGroup) {
			try
			{
				Thread.sleep(2000);
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");					
				//reportGroupDropdownButton.click();
				driver.findElement(By.id("divRptGrpFilter2")).click();
				// driver.findElement(By.cssSelector("label.ui-corner-all.ui-state-hover
				// > span")).click();
				Thread.sleep(2000);
				if(reportGroup.equals("NA"))
				{
					driver.findElement(By.id("ui-multiselect-islRptGrpFilter-option-1")).click();
					
				}
				else
				{
				driver.findElement(By.xpath("//span[text()='" + reportGroup + "']")).click();
				}
				filterPopupClose.click();
				//System.out.println("Report Group : " + reportGroup + " was selected successfully");
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
				Report.PassTest(test, "Report Group : " + reportGroup + " was selected successfully");
			} catch (Exception e) {
				Report.FailTest(test, e.getMessage());
				Report.FailTest(test, "Not able to select Report Group");
	
			}
				
			}
		
		
			public void selectRoute(String route) throws InterruptedException, IOException {
		
				try {
					String[] routes = route.split(";");
					//System.out.println("Number of routes : " + routes.length);
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
					routeFilterarrow.click();
					Thread.sleep(1000);
					if(route.equals("NA"))
					{
						driver.findElement(By.id("ui-multiselect-islRteFilter-option-1")).click();
					}
					else
					{
					for (int i = 0; i < routes.length; i++) {
						System.out.println("Route to select : " + routes[i]);
						// driver.findElement(By.xpath(".//*[@class='ui-multiselect-checkboxes
						// ui-helper-reset']/li/label/span[text()='"+routes[i]+"']")).click();
						driver.findElement(By.xpath(".//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/input[@title='"+routes[i]+"']")).click();
						Thread.sleep(1000);
		
					}
					}
					filterPopupClose.click();
					Thread.sleep(1000);
					driver.switchTo().defaultContent();
					Report.PassTest(test, "Route : " + route + " was selected successfully");
		
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to select route");
		
				}
		
			}
		
			public void selectDriver(String Driver) throws InterruptedException, IOException {
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
					String[] drivers = Driver.split(";");
					//System.out.println("Number of drivers : " + drivers.length);				
					driverFilterarrow.click();
					if(Driver.equals("NA"))
					{
						driver.findElement(By.id("ui-multiselect-islDriverFilter-option-1")).click();
					}
					else
					{
					
					for (int i = 0; i < drivers.length; i++) {
						
						//driver.findElement(By.xpath("html/body/div[12]/div/div/input")).clear();
						//driver.findElement(By.xpath("html/body/div[12]/div/div/input")).sendKeys(drivers[i]);			
						driver.findElement(
								By.xpath(".//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[text()='"
										+ drivers[i] + "']"))
								.click();
					}
					}
					filterPopupClose.click();
					Thread.sleep(1000);
					driver.switchTo().defaultContent();
					Report.PassTest(test, "Driver : " + Driver + " was selected successfully");
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to Select Driver");
		
				}
		
			}
			
public void selectTier(String Tier) {
				
				try {
					Thread.sleep(2000);
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");					
					SelectTier.click();
					Thread.sleep(2000);
					if(Tier.equals("NA"))
					{
						driver.findElement(By.id("ui-multiselect-islGroupFilter-option-1")).click();
						
					}
					else
					{
					driver.findElement(By.xpath("//span[text()='" + Tier + "']")).click();
					}
					filterPopupClose.click();
					//System.out.println("Tier : " + Tier + " was selected successfully");
					Thread.sleep(2000);
					driver.switchTo().defaultContent();
					Report.PassTest(test, "Tier : " + Tier + " was selected successfully");
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to select Tier");
		
				}
				
			}

public void selectArea(String Area) {
	
	try {
		Thread.sleep(2000);
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer");					
		SelectArea.click();
		Thread.sleep(2000);
		if(Area.equals("NA"))
		{
			driver.findElement(By.id("ui-multiselect-islAreaFilter-option-1")).click();
			
		}
		else
		{
		driver.findElement(By.xpath("//span[text()='" + Area + "']")).click();
		}
		filterPopupClose.click();
		//System.out.println("Area : " + Area + " was selected successfully");
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		Report.PassTest(test, "Area : " + Area + " was selected successfully");
	} catch (Exception e) {
		Report.FailTest(test, e.getMessage());
		Report.FailTest(test, "Not able to select Area");

	}
	
}

public void selectBU(String BU) {
	
	try {
		Thread.sleep(2000);
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer");					
		SelectBusinessUnit.click();
		Thread.sleep(2000);
		if(BU.equals("NA"))
		{
			driver.findElement(By.id("ui-multiselect-islBUFilter-option-1")).click();
			
		}
		else
		{
		driver.findElement(By.xpath("//span[text()='" + BU + "']")).click();
		}
		filterPopupClose.click();
		//System.out.println("BU : " + BU + " was selected successfully");
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		Report.PassTest(test, "BU : " + BU + " was selected successfully");
	} catch (Exception e) {
		Report.FailTest(test, e.getMessage());
		Report.FailTest(test, "Not able to select BU");

	}
	
}
		
			public void selectToDate(String Date) throws InterruptedException, IOException {
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
					toDateTextBox.clear();
					Thread.sleep(1000);
					toDateTextBox.sendKeys(Date);
					Thread.sleep(1000);
					driver.switchTo().defaultContent();
					Report.PassTest(test, "Date : " + Date + " was entered in To Date");
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to select To Date");
		
				}
			}
		
			
		
			
			public void selectDriverComment(String DriverComments) throws InterruptedException, IOException {
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
					Thread.sleep(1000);
					driverCommentutton.click();
					Thread.sleep(1000);
					CommonSearchClick.sendKeys(DriverComments);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//*[contains(@id,'ui-multiselect-islDrvrCmtFilter')]/following-sibling::span[contains(text(),'"+DriverComments+"')]")).click();
					filterPopupClose.click();
					driver.switchTo().defaultContent();
					Report.PassTest(test, "Driver Comments : " + DriverComments + " was entered in To DriverComments");
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to select Driver Comments");
		
				}
			}
			
			public void selectDateFromDatePicker(String inputDate) throws InterruptedException, IOException, ParseException {
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
					Date date = format.parse(inputDate);
					SimpleDateFormat dayFormat = new SimpleDateFormat("d");
					String day = dayFormat.format(date);
					SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
					String month = monthFormat.format(date);
					SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
					String year = yearFormat.format(date);
					fromDatetextBox.click();
					Thread.sleep(1000);
					Util.SelectOption(driver, datePickerYear, year);
					Util.SelectOption(driver, datePickerMonth, month);
					driver.findElement(By.linkText(day)).click();
					Thread.sleep(1000);
					driver.switchTo().defaultContent();
					Report.PassTest(test, "Date : " + inputDate + " was entered in To Date");
		
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to select date from datepicker");
		
				}
			}
			
			public void selectDateToDatePicker(String inputDate) throws InterruptedException, IOException, ParseException {
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
					Date date = format.parse(inputDate);
					SimpleDateFormat dayFormat = new SimpleDateFormat("d");
					String day = dayFormat.format(date);
					SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
					String month = monthFormat.format(date);
					SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
					String year = yearFormat.format(date);
					toDateTextBox.click();
					Thread.sleep(1000);
					Util.SelectOption(driver, datePickerYear, year);
					Util.SelectOption(driver, datePickerMonth, month);
					driver.findElement(By.linkText(day)).click();
					Thread.sleep(1000);
					driver.switchTo().defaultContent();
					Report.PassTest(test, "Date : " + inputDate + " was entered in To Date");
		
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to select date from datepicker");
		
				}
			}
			
			
		
			public void selectFromDate(String Date) throws InterruptedException, IOException {
		
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
					fromDatetextBox.clear();
					Thread.sleep(1000);
					fromDatetextBox.sendKeys(Date);
					Thread.sleep(1000);
					driver.switchTo().defaultContent();
					Report.PassTest(test, "Date : " + Date + " was entered in From Date");
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to select From Date");
		
				}
			}
		
			public void selectGO() throws InterruptedException, IOException {
				try {
					Report.InfoTest(test, "Click on Go button");
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
					Thread.sleep(1000);
					GoButton.click();
					driver.switchTo().defaultContent();
					Thread.sleep(5000);
					Report.PassTest(test, "Go button was clicked to generate the report");
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to click on Go button");
		
				}
		
			}
		
			public void clickReset() throws InterruptedException, IOException {
		
				try {
					Report.InfoTest(test, "Click on Reset button");
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
					Thread.sleep(1000);
					ResetButton.click();
					driver.switchTo().defaultContent();
					Thread.sleep(4000);
					Report.PassTest(test, "Reset button was clicked");
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to click on Reset button");
		
				}
		
			}
			
			public void clickBackToRMDashBoard() throws InterruptedException, IOException {
		
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
					Thread.sleep(1000);
					backToRMDashboard.click();
					driver.switchTo().defaultContent();
					Thread.sleep(1000);
					Report.PassTest(test, "Navigation Back to RM DashBoard successfully");
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to click on Back To RM Dash board link");
		
				}
		
			}
		
		
			public void verifyReportTitle(String expectedReportTitle) {
				String actualReportTitle = null;
		
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
					actualReportTitle = reportTitle.getText();
					//System.out.println("Title of the report : " + actualReportTitle);
					if (actualReportTitle.equals(expectedReportTitle)) {
						Report.PassTest(test, "Title of the Report is as expected Actual is : " + actualReportTitle
								+ " and Expected is : " + expectedReportTitle);
					} else {
						Report.FailTest(test, "Title of the Report is not as expected Actual is : " + actualReportTitle
								+ " and Expected is : " + expectedReportTitle);
					}
					driver.switchTo().defaultContent();
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Title of the Report is not as expected Actual is : " + actualReportTitle
							+ " and Expected is : " + expectedReportTitle);
				}
		
			}
			
			public void isFilterExist(String filterType)
			{
				try
				{
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
					if(filterType.contains("Driver"))
					{
						if(driverFilterButton.getAttribute("aria-haspopup").equals("true"))
						{
							Report.PassTest(test, "Filter exist for : "+filterType);
						}
						else
						{
							Report.FailTest(test, "Filter doesn't exist for :"+filterType);
						}
					}
					else if(filterType.contains("Day Of Week"))
					{
						if(dayOfWeekFilterButton.getAttribute("aria-haspopup").equals("true"))
						{
							Report.PassTest(test, "Filter exist for : "+filterType);
						}
						else
						{
							Report.FailTest(test, "Filter doesn't exist for :"+filterType);
						}
					}
					else
					{
						Report.FailTest(test, "Filter for "+filterType+" : there is no such filter in OPUS application ");
					}
					driver.switchTo().defaultContent();
				}
				catch(Exception e)
				{
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Filter doesn't exist for :"+filterType);
				}
			}
			
			public void selectDayOfWeekOption() throws IOException
			{
				try
				{
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
				if(dayOfWeekRadio.isDisplayed())
				{
					Util.highLightElement(driver, dayOfWeekRadio);
					dayOfWeekRadio.click();
					Report.PassTest(test, "Day of Week option get selected");
				
				}
				else
				{
					Report.FailTest(test, "Day Of Week Option is not visible on web page");
				}
				driver.switchTo().defaultContent();
				}
				catch(Exception e)
				{
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to select day of week option");
				}
			}
			
			public void selectDay(String day) throws InterruptedException, IOException {
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
					Thread.sleep(1000);
					dayOfWeekFilterarrow.click();
					
						driver.findElement(
								By.xpath(".//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[text()='"
										+ day + "']"))
								.click();
						//dayOfWeekPopupClose.click();
					Thread.sleep(1000);
					driver.switchTo().defaultContent();
					Report.PassTest(test, day + " : was selected successfully from Day of week drop down");
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Not able to Select : "+day+" from Day of week drop down");
		
				}
		
			}
			
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

					 	Util.selectFrame("opusReportContainer");
						Thread.sleep(1000);
						Opus_Conf_Site_Reset.click();
						
						Thread.sleep(1000);
						
					boolean exp=AfterResetGoButtonDisbale.getAttribute("disabled").equals("true");
					
					//String Exp1="true";
					 ////System.out.println("Exp Text >>>" + Exp);
					
					  if (exp==true) {
						//System.out.println("Reset Working Fine");
					}
					  else {
						  //System.out.println("Reset Not Working Fine");
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
							
						Util.selectFrame("opusReportContainer");
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
				    	
					  Util.selectFrame("opusReportContainer");
				    	
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
				    	
					  Util.selectFrame("opusReportContainer");
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
				    	
				    	Util.selectFrame("opusReportContainer");
				    	Opus_Conf_Site_StartDate.click();
				    	//Opus_Conf_Site_StartDate.clear();
				    	Opus_Conf_Site_StartDate.sendKeys(FromDate);
				    	
				    	try {
							Thread.sleep(1000);
						} catch (Exception e) {
		    	 			Report.FailTest(test, e.getMessage());
						}
				    	driver.switchTo().defaultContent();
				    	
				    	
				    }
		
				    public void Opus_Confirmations_ToDate(String FromDate) {
		
				    	
				    	Util.selectFrame("opusReportContainer");
				    	Opus_Conf_Site_EndDate.click();
				    	//Opus_Conf_Site_EndDate.clear();
				    	Opus_Conf_Site_EndDate.sendKeys(FromDate);
				    	
				    	try {
							Thread.sleep(1000);
						} catch (Exception e) {
		    	 			Report.FailTest(test, e.getMessage());
						}
				    	driver.switchTo().defaultContent();
				    	
				    	
				    }
				 
				  
				    public void Opus_Confirmations_selectDateFromDatePicker(String inputDate) throws InterruptedException, IOException, ParseException {
						try {
					
							Util.selectFrame("opusReportContainer");
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
							Util.selectFrame("opusReportContainer");
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
					   
					Util.selectFrame("opusReportContainer");
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
					   
					 Util.selectFrame("opusReportContainer");
					   Thread.sleep(2000);
					   
					  String CC=confirmedCount.getText();
					   int resultcc = Integer.parseInt(CC);
					   if (resultcc!=0) {
						   //System.out.println(resultcc);
					}
					   else {
						   //System.out.println("0 Records for ConfirmedCount ");
					}
					  
					  
					  String FC=flaggedCount.getText();
					  
					  int resultfc = Integer.parseInt(FC);
					  if (resultfc!=0) {
						  //System.out.println(resultfc);
					}
					  else {
						  //System.out.println("0 Records for Flagged");
					}
					 
					    String OC=openCount.getText();
					      int  resultoc=Integer.parseInt(OC);
					      if (resultoc!=0) {
							//System.out.println(resultoc);
						}
					      else {
					    	  //System.out.println("0 Records for Open");
						}
					  
					     String TC=totalCount.getText();
					     int  resulttc=Integer.parseInt(TC); 
					     if (resultcc + resultfc + resultoc == resulttc) {
					    
						    //System.out.println("Total Nuber of Records is >> " + resulttc);	
						
						}
					     else {
							//System.out.println("No Records availabel");
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
						  Util.selectFrame("opusReportContainer");
					   Thread.sleep(2000);
					   if (driver.findElements(By.xpath("//*[@id='tblRouteSummary' and @role='grid']/tbody/tr[1]")).size()!=0) {
						//System.out.println("Records Available");
						String Exp=VerifyRecordsInRouteSummary.getText();
						//System.out.println(Exp);
					}
					   else {
						//System.out.println("No Records Available");
					}
					   driver.switchTo().defaultContent();
				   }
					   catch(Exception e)
					   {
					   	Report.FailTest(test, e.getMessage());
					   	Report.FailTest(test, "No Records Inside of Route" );
					   }
				   }
				   
				   
				   public void GoBtn() throws InterruptedException {
				    	
					   Util.switchToDefaultWindow();
					   Util.selectFrame("opusReportContainer");
				    	GoBtn.click();
				    	Thread.sleep(5000);
				    	driver.switchTo().defaultContent();
				   }
				   
				   public void Opus_Confirmations_crazysearch(String SearchBy) throws InterruptedException {
				try {
					Util.selectFrame("opusReportContainer");
					   Thread.sleep(2000); 
					   if (SearchFilter.isDisplayed() && SearchFilter.isEnabled()) {
						   SearchFilter.sendKeys(SearchBy);
						   Thread.sleep(2000);
						   
					}
					   else {
						//System.out.println("Unable to Found Crazy Search Filter");
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
						  Util.selectFrame("opusReportContainer");
					   Thread.sleep(2000);
					   if (ClickFirstRouteID.isDisplayed() && ClickFirstRouteID.isEnabled()) {
						   ClickFirstRouteID.click();
						   //Util.waitForElement(driver,GenralInforrmation);
						   Thread.sleep(3000);
						   
					}
					   else {
						//System.out.println("Unable to found Route Id");
					}
					   
					   Thread.sleep(1000);
					   
					   driver.switchTo().defaultContent();
				  }
					   catch(Exception e)
					   {
					   	Report.FailTest(test, e.getMessage());
					   	Report.FailTest(test, "No Data available after searching" );
					   }
					   
					   
				  }
				  
				  public void Opus_Confirmations_FindDayBetweenDates(String PreviousDate, String CurrentDate) {
					 
					 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					 String Date1=PreviousDate;   // Old Date
					 LocalDate localDate1 = LocalDate.parse(Date1, formatter);
					 
					 String Date2=CurrentDate;  // New Date
					 LocalDate localDate2 = LocalDate.parse(Date2, formatter);
					 
					 
					 
					 //System.out.println("Days Gap Between >> "+ PreviousDate + "  and  "+ CurrentDate+ " is --> " + ChronoUnit.DAYS.between(localDate1, localDate2));
					 
					 
					 
				 }
		
				 
				  public void Opus_Confirmations_PaginationFunctionality() {
		
		
					try { 
						
						Util.PageScrollDown(0,1000);
						Util.selectFrame("opusReportContainer");
					 
					 String TC=totalCount.getText();
				     int  resulttc=Integer.parseInt(TC); 
				     
				     
				     if (resulttc>10) {    
				    	 
					    ////System.out.println("Total Number of Records is >> " + resulttc);	
					    Report.PassTest(test, "Total Number of Records is >> " + resulttc);
					    String NumbersofPages=PaginationTotalcount.getText();
					    ////System.out.println("Pagination Count Text >> " + NumbersofPages);
					    Report.PassTest(test, "Pagination Count Text >> " + NumbersofPages);
					    
					       int Pagecount=Integer.parseInt(NumbersofPages); 
					       for (int i = 0; i < Pagecount; i++) {
					    	   
					    	 String PageCount=PageCountText.getAttribute("value");
					    	 
					    	 ////System.out.println("We are in Page >>" + PageCount);
					    	 Report.PassTest(test, "We are in Page >>" + PageCount);
					    	   PaginationNext.click();
					    	   Thread.sleep(2000);
						}
					       PaginationFirst.click();
					       Thread.sleep(1000);
					
					}
				     else {
						////System.out.println("No Records available");
						Report.FailTestSnapshot(test, driver, "No Reocords", "No Records available");
					}
				    	driver.switchTo().defaultContent(); 
				    	Thread.sleep(1000);
				    	Util.PageScrollUp(0,-1000);
				}
					
				    	catch(Exception e)
				    	{
				    		Report.FailTest(test, e.getMessage());
				    		Report.FailTest(test, "Pagination is Not Working fine.");
				    	}
					 
				 }
		
				  public void Opus_Confirmations_VerifyConfirmationStatus(String ConfirmationStatus) throws InterruptedException {
		
				try {
					  
					Util.selectFrame("opusReportContainer");
				  	  Thread.sleep(1000);
				  	  Opus_Conf_Site_selectedStatus.click();
				  	  Thread.sleep(1000);
				  	  //All, Confirmed, Flagged, Open
				  	  if (ConfirmationStatus!="All") {		
					
				  	  driver.findElement(By.xpath("//input[@type='checkbox']/following-sibling::span[contains(text(),'"+ConfirmationStatus+"')]")).click();
				  	  
				  	  
				  	  }
				  	  
				  	  //////////////////// Click on Go button /////////////////////
				  	    Opus_Conf_Site_Go.click();
					    Thread.sleep(2000);
					    WebDriverWait w=new WebDriverWait(driver, 60);
					    w.until(ExpectedConditions.elementToBeClickable(Validate));
					    Thread.sleep(2000);
				  	  ////////////// For Page Scroll Down ///////////////
					   driver.switchTo().defaultContent();
					    Util.PageScrollDown(0,1000);
					    Util.selectFrame("opusReportContainer");
				/////////////////Validate Records /////////////////// 
				      String TotalNumofPages=TotalNumberOfPages.getText();
				      //  When Number of pages is more then 1
				       	
					            int TNOP=Integer.parseInt(TotalNumofPages);
					            for (int i = 0; i <TNOP; i++) {
					            	
					            
					   			 List<WebElement> NumbersofColumn=Table.findElements(By.tagName("tr"));
					   			 for (int j = 1; j <=NumbersofColumn.size(); j++) {
					   				 if (!driver.findElement(By.xpath("//*[@id='tblRouteSummary_wrapper']/table/tbody/tr["+j+"]/td[2]")).getText().isEmpty()) {
					   					String ConfStatus=driver.findElement(By.xpath("//*[@id='tblRouteSummary_wrapper']/table/tbody/tr["+j+"]/td[2]")).getText();
					   		   			////System.out.println("Confirmation Status is >> "+ConfStatus);
					   		   			Report.PassTest(test, "Confirmation Status is >> "+ConfStatus);
					   		   			Thread.sleep(1000);
									}
					   				 else {
										Report.FailTestSnapshot(test, driver, "Confirmation Status is Blank","BlankConfirmationStatus");
									}
					   			}	
								
					        	   String PageCount=PageCountText.getAttribute("value");
					            	Thread.sleep(1000);
					            	////System.out.println("We are in page >> " + PageCount);
					            	Report.PassTest(test, "We are in page >> " + PageCount);
					            	PaginationNext.click();
					            	Thread.sleep(1000);
					      
					          
					            }
					            
								//	//System.out.println("Records available in a single page No pagination Found");
								
					     PaginationFirst.click();
					 	 Thread.sleep(1000);
					 	 driver.switchTo().defaultContent();
					 	Util.PageScrollUp(0,-1000);
					 	Thread.sleep(1000);
					 	
				}
				catch(Exception e)
				{
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Pagination is Not Working fine for Confirmaion Status." + ConfirmationStatus);
				}
		
					  
				  }
				  
				  
				  public void Opus_Confirmations_VerifyWorkStatus() throws InterruptedException {
					try {  
					  
					  
					  ///////////////////Page Scroll Down ///////////////
					  
					  Util.PageScrollDown(0, 1000);
					  Util.selectFrame("opusReportContainer");
					  Thread.sleep(1000);
					  
					  ////////////////Verify Total Numbers of records /////////////////
					  String TotalNumofPages=TotalNumberOfPages.getText();
				      //  When Number of pages is more then 1    
				       int TNOP=Integer.parseInt(TotalNumofPages);
					   for (int j = 0; j <TNOP; j++) {
						   ///////// Xpath of Entire table//////////////////
							
							 List<WebElement> NumbersofColumn=Table.findElements(By.tagName("tr"));
							 for (int i = 1; i <=NumbersofColumn.size(); i++) {
								 if (!driver.findElement(By.xpath("//*[@id='tblRouteSummary_wrapper']/table/tbody/tr["+i+"]/td[3]/div/img")).getAttribute("title").isEmpty()) {
									 String WorkStatus=driver.findElement(By.xpath("//*[@id='tblRouteSummary_wrapper']/table/tbody/tr["+i+"]/td[3]/div/img")).getAttribute("title");
										////System.out.println("Work Status is >> "+WorkStatus);
										Report.PassTest(test, "Work Status is >> "+WorkStatus);
										Thread.sleep(1000);	
										
								}
								 else {
									Report.FailTestSnapshot(test, driver, "Blank Work Status","BlankWorkStatus");
								}
							
					        } 
					   
							 PaginationNext.click();
					    	   Thread.sleep(2000);
					  
					}
					   PaginationFirst.click();
					   Thread.sleep(2000);
					   driver.switchTo().defaultContent();
					   Util.PageScrollUp(0, -1000);
					   Thread.sleep(2000);
					}
					catch(Exception e)
					{
						Report.FailTest(test, e.getMessage());
						Report.FailTest(test, "Pagination is Not Working fine for Work Status.");
					}
					   
				  }
		
				  public void Opus_Confirmations_VerifyLOB() throws InterruptedException {
		
					try {  
					     Util.PageScrollDown(0,1000);
					     Util.selectFrame("opusReportContainer");
						  Thread.sleep(1000);
						  /////////////////Validate Pages Count and click on Next ////////////
						  String TotalNumofPages=TotalNumberOfPages.getText();
					      //  When Number of pages is more then 1    
					       int TNOP=Integer.parseInt(TotalNumofPages);
					       for (int j = 0; j <TNOP; j++) {
								
						  
						  // Find Entire Table//////////////////////
						
					         List<WebElement> Lob=Table.findElements(By.tagName("tr"));
					     for (int i = 1; i <=Lob.size(); i++) {
					    	 if (!driver.findElement(By.xpath("//*[@id='tblRouteSummary']/tbody/tr["+i+"]/td[4]")).getText().isEmpty()) {
								////System.out.println("LOB Contains >> " + driver.findElement(By.xpath("//*[@id='tblRouteSummary']/tbody/tr["+i+"]/td[4]")).getText() );  
					   		  Report.PassTest(test, "LOB Contains >> " + driver.findElement(By.xpath("//*[@id='tblRouteSummary']/tbody/tr["+i+"]/td[4]")).getText());
							}
					    	 else {
								Report.FailTestSnapshot(test, driver, "LOB Blank", "Blank LOB Found");
							}
					     }
					     PaginationNext.click();
				  	   Thread.sleep(2000);
					     
					       }
					       
					       PaginationFirst.click();
						   Thread.sleep(2000);
						   driver.switchTo().defaultContent();
						   Util.PageScrollUp(0, -1000);
						   Thread.sleep(2000);
						   
				  }
					catch(Exception e)
					{
						Report.FailTest(test, e.getMessage());
						Report.FailTest(test, "Pagination is Not Working fine for LOB.");
					}
					                
				  }
				  
				  public void Opus_Confirmations_VerifyRoute() throws InterruptedException {
		
				try {
					  
					  Util.PageScrollDown(0,1000);
					  Util.selectFrame("opusReportContainer");
						  Thread.sleep(1000);
						  /////////////////Validate Pages Count and click on Next ////////////
						  String TotalNumofPages=TotalNumberOfPages.getText();
					      //  When Number of pages is more then 1    
					       int TNOP=Integer.parseInt(TotalNumofPages);
					       for (int j = 0; j <TNOP; j++) {
					    	   
					        List<WebElement> Route=Table.findElements(By.tagName("tr"));
					        for (int i = 1; i <=Route.size(); i++) {
								if (!driver.findElement(By.xpath("//*[@id='tblRouteSummary']/tbody/tr["+i+"]/td[5]")).getText().isEmpty()) {
									////System.out.println(driver.findElement(By.xpath("//*[@id='tblRouteSummary']/tbody/tr["+i+"]/td[5]")).getText());	
									Report.PassTest(test, driver.findElement(By.xpath("//*[@id='tblRouteSummary']/tbody/tr["+i+"]/td[5]")).getText());
								}
								else {
									////System.out.println("No Record Found");
									Report.FailTestSnapshot(test, driver, "Blank Route is available", "BlankRoute");
								} 
					        	        	
							}
					       
					        PaginationNext.click();
					   	   Thread.sleep(2000);
					   	         
					       }
					  
					       PaginationFirst.click();
						   Thread.sleep(2000);
						   driver.switchTo().defaultContent();
						   Util.PageScrollUp(0, -1000);
						   Thread.sleep(2000);
				  }
						   catch(Exception e)
						   {
						   	Report.FailTest(test, e.getMessage());
						   	Report.FailTest(test, "Pagination is Not Working fine for Route." );
						   }
					  
				  }
				  
				  
				  public void Opus_Confirmations_VerifyDriveDetails() throws InterruptedException {
					try {  
					  Util.PageScrollDown(0,1000);
					  Util.selectFrame("opusReportContainer");
						  Thread.sleep(1000);
						  /////////////////Validate Pages Count and click on Next ////////////
						  String TotalNumofPages=TotalNumberOfPages.getText();
					      //  When Number of pages is more then 1    
					       int TNOP=Integer.parseInt(TotalNumofPages);
					       for (int j = 0; j <TNOP; j++) {
					    	   
					    	List<WebElement> DriverDetails=Table.findElements(By.tagName("tr"));
					    	for (int i = 1; i <=DriverDetails.size(); i++) {
								if (!driver.findElement(By.xpath("//*[@id='tblRouteSummary']/tbody/tr["+i+"]/td[6]")).getText().isEmpty()) {
									
									////System.out.println("Driver Details >" + driver.findElement(By.xpath("//*[@id='tblRouteSummary']/tbody/tr["+i+"]/td[6]")).getText());
								Report.PassTest(test, "Driver Details >" + driver.findElement(By.xpath("//*[@id='tblRouteSummary']/tbody/tr["+i+"]/td[6]")).getText());
								}   
								else {
									////System.out.println("No Records available");
									Report.FailTestSnapshot(test, driver, "Blank Driver Details", "BlankDriverDetails");
								}
					    		
					    		
							}
					    	
					    	 PaginationNext.click();
						   	   Thread.sleep(2000);
					    	     
					       }
					       PaginationFirst.click();
						   Thread.sleep(2000);
						   driver.switchTo().defaultContent();
						   Util.PageScrollUp(0, -1000);
						   Thread.sleep(2000);
					}
						   catch(Exception e)
						   {
						   	Report.FailTest(test, e.getMessage());
						   	Report.FailTest(test, "Pagination is Not Working fine for Route." );
						   }
					  
					  
				  }
		
				  public void Opus_Confirmations_Verify_VehicleIDDetails() throws InterruptedException {
					try {  
					  Util.PageScrollDown(0,1000);
					  Util.selectFrame("opusReportContainer");
						  Thread.sleep(1000);
						  /////////////////Validate Pages Count and click on Next ////////////
						  String TotalNumofPages=TotalNumberOfPages.getText();
					      //  When Number of pages is more then 1    
					       int TNOP=Integer.parseInt(TotalNumofPages);
					       for (int j = 0; j <TNOP; j++) {
					    	   
					    	List<WebElement> VehicleIDDetails=Table.findElements(By.tagName("tr"));
					    	for (int i = 1; i <=VehicleIDDetails.size(); i++) {
								if (!driver.findElement(By.xpath("//*[@id='tblRouteSummary']/tbody/tr["+i+"]/td[7]")).getText().isEmpty()) {
									////System.out.println("VehicleID Details is >>" + driver.findElement(By.xpath("//*[@id='tblRouteSummary']/tbody/tr["+i+"]/td[7]")).getText() );
							Report.PassTest(test, "VehicleID Details is >>" + driver.findElement(By.xpath("//*[@id='tblRouteSummary']/tbody/tr["+i+"]/td[7]")).getText());	
								}
								
								else {
								Report.FailTestSnapshot(test, driver, "VehicleId Details is blank", "BlankVehicleID");
									////System.out.println("Empty Vehicle Id is present");
								}
								
								
							}
					    	PaginationNext.click();
						   	   Thread.sleep(2000); 
					    	   
					       }
					       PaginationFirst.click();
						   Thread.sleep(2000);
						   driver.switchTo().defaultContent();
						   Util.PageScrollUp(0, -1000);
						   Thread.sleep(2000);
				  }
						   catch(Exception e)
						   {
						   	Report.FailTest(test, e.getMessage());
						   	Report.FailTest(test, "Pagination is Not Working fine for VehicleDetails ID." );
						   }
						   
					  
				  }
		
				  public void SearchHOCandExtraUnitNotZero() throws InterruptedException {
					  try {
						  Util.selectFrame("opusReportContainer");
					      Thread.sleep(2000);
					       List<WebElement>NotZero=Table.findElements(By.tagName("tr"));
					       
					       for (int i = 1; i <=NotZero.size(); i++) {
						String EXU=driver.findElement(By.xpath("//*[@id='tblRouteSummary']/tbody/tr["+i+"]/td[12]")).getText();
						
						String HOC=driver.findElement(By.xpath("//*[@id='tblRouteSummary']/tbody/tr["+i+"]/td[13]")).getText();
						
					    	    int HOCresult=Integer.parseInt(HOC);
					    	    int EXUResult=Integer.parseInt(EXU);
					    	    if (HOCresult != 0 && EXUResult !=0) {
									driver.findElement(By.xpath("//*[@id='tblRouteSummary']/tbody/tr["+i+"]/td[5]/a")).click();
									Thread.sleep(2000);
									Report.PassTest(test,"Records are available with HOC and Extra Unit");
								}
					    	    else {
									////System.out.println("No Records Available");
									Report.FailTestSnapshot(test, driver, "Records are not available with HOC and ExtraUnit", "SearchHOCandExtraUnitNotZero");
								}
						}
					       Thread.sleep(2000);
					       driver.switchTo().defaultContent();
					  }
					       catch(Exception e)
						   {
						   	Report.FailTest(test, e.getMessage());
						   	Report.FailTest(test, "Unable to found Search HOC and ExtraUnit is Zero" );
						   }
					       
				  }
		
				  public void SeeCustomerDetailsBtn() throws InterruptedException {
					try {
						Util.selectFrame("opusReportContainer");
					  
					  if (SwitchToManualMode.isDisplayed() && SwitchToManualMode.isEnabled()) {
						  SwitchToManualMode.click();
						  Thread.sleep(2000);
						  YesSwitchToManualMode.click();
						  Thread.sleep(2000);
						  //System.out.println("Clicked on Switch to manual mode button");
					}  
					  else {
						//System.out.println("Switch to manual mode button is diabale");
					}
					  
					  Thread.sleep(1000);
					  if (SeeCustomerDetails.isDisplayed() && SeeCustomerDetails.isEnabled()) {
						  SeeCustomerDetails.click();
						  Report.PassTest(test,"Clicked on See Customer Details Button");
						  //Util.waitForElement(driver,CustDetailsPage);
						  Thread.sleep(3000);
						  
					}
					  else {
						Report.FailTestSnapshot(test, driver,"See Customer Details Btn is not enable", "SeeCustomerDetailsBtn");
					}
					  driver.switchTo().defaultContent();
					}
					  catch(Exception e)
					   {
					   	Report.FailTest(test, e.getMessage());
					   	Report.FailTest(test, "See Customer Details Button is Not Visible" );
					   }
				  }
				  
				  
				  public void BacktoSiteDetail() throws InterruptedException {
					  try {
						  Util.selectFrame("opusReportContainer");
					  BacktoSiteDetails.click();
					  //Util.waitForElement(driver,WaitRouteSummary);
					  Thread.sleep(3000);
					  Util.selectFrame("opusReportContainer");
					  if (driver.findElements(By.xpath("//*[@id='tblRouteSummary_wrapper']")).size()!=0) {
						////System.out.println("BacktoSiteDetail PAss");
						Report.PassTest(test, "Back to Site Detail Link is working");
					}
					  else {
						  ////System.out.println("BacktoSiteDetail Fail");
						  Report.FailTestSnapshot(test, driver, "Back to Site Detail Link Fail", "BacktoSiteDetail");
					}
					  
					  }
					  catch(Exception e)
					   {
					   	Report.FailTest(test, e.getMessage());
					   	Report.FailTest(test, "Back To Site Detail Button is Not displayed" );
					   }
					  
					  driver.switchTo().defaultContent();
				  }
				  
				  public void BacktoRouteDetail() throws InterruptedException {
					   try {
						   Util.selectFrame("opusReportContainer");
					    BacktoRouteDetail.click();
					    //Util.waitForElement(driver,GenralInforrmation);
					    Thread.sleep(3000);
					    Util.selectFrame("opusReportContainer");    
					    if (driver.findElements(By.xpath("//*[@id='generalInfo_groupContainer']")).size()!=0) {
							Report.PassTest(test, "Back to Route Detail Link is working fine");
							////System.out.println("Back to Route Detail Link is working fine");
						}
					    else {
							Report.FailTestSnapshot(test, driver, "Back to RouteDetail is not working fine", "BacktoRouteDetail");
							////System.out.println("Back to RouteDetail is not working fine");
						}
					   }
				 
				  catch(Exception e)
				   {
				   	Report.FailTest(test, e.getMessage());
				   	Report.FailTest(test, "Back To Route Detail Link is not working fine" );
				   }
					    
					    
					    driver.switchTo().defaultContent();
					    
				  }
				  
				  
				  public void VerifyStartAndEndDateAfterRedirection(String FromDate,String ToDate) throws IOException, InterruptedException, ParseException {
					try {  
					  this.Opus_Confirmations_selectDateFromDatePicker(FromDate);
					  Thread.sleep(1000);
					  this.Opus_Confirmations_selectDateToDatePicker(ToDate);
					  Thread.sleep(1000);
					  this.Opus_Confirmations_SelectGo();
					  Thread.sleep(2000);
					  this.Opus_Confirmations_VerifyRouteCount();
					  Thread.sleep(1000);
					  this.Opus_Confirmations_VerifyRecordsInsideofRoute();
					  Thread.sleep(1000);
					  this.Opus_Confirmations_ClickOnFirstRoute();
					  Thread.sleep(1000);
					  this.BacktoSiteDetail();
					  Thread.sleep(1000);
					  
					  /////////////////// Validate Dates inside of Site Details Pages///
					  Util.selectFrame("opusReportContainer");
					  Thread.sleep(1000);
				                 String FromDates= Opus_Conf_Site_StartDate.getAttribute("value");
				                 String ToDates=Opus_Conf_Site_EndDate.getAttribute("value");
					  if (FromDates.contains(FromDates) && ToDates.contains(ToDates) ) {
						////System.out.println("Dates Are Validated Sucessfully");
						Report.PassTest(test, "Dates Are Validated Sucessfully");
					}
					  else {
						////System.out.println("Both are not same");
						Report.FailTestSnapshot(test, driver, "Both are not same", "DatesValidationFailed");
					}
					  driver.switchTo().defaultContent(); 
				 
				  }
					  catch(Exception e)
					   {
					   	Report.FailTest(test, e.getMessage());
					   	Report.FailTest(test, "Unable to Verify Start And End Date After Redirection" );
					   }
				  }
				  
				  public void VerifyRouteCoverSheet() throws InterruptedException {
					  try {
						  Util.selectFrame("opusReportContainer");
					       Thread.sleep(2000);
					       String TotalCount=totalCount.getText();
					        int RouteCount=Integer.parseInt(TotalCount);
					              
					        if (RouteCount>10) {
					        	////////////////////With out Record////////////////////
					        	printCoverSheet.click();
					        	Thread.sleep(1000);	
					        	String Actual=driver.switchTo().alert().getText();
					        	String Expected="You don't have any routes selected for Cover Sheet";
					        	if (Expected.contains(Actual)) {
									////System.out.println("Without any records working fine");
									Report.PassTest(test, "Working fine when we select no records");
								}
					        	else {
					        		////System.out.println("Without any records not working fine");
					        		Report.FailTestSnapshot(test, driver, "Without any records not working fine", "ForNoRecordNotWrokingFine");
								}
					        	////System.out.println("Actual String is > " + Actual);
					        	driver.switchTo().alert().accept();
					        	Thread.sleep(1000);		
					        	////////////// For Multiple Select/////////////      	
					        	Multiselect.click();
					        	Thread.sleep(1000);
					        	printCoverSheet.click();
					        	String Exp="You have selected 10 route(s) for Cover Sheet. Do you want to proceed?";
					        	
					        	////////// Alert text///////////////////
					        	String Act=driver.findElement(By.xpath("//*[@class='confirmInfo print_mesg']/p")).getText();
					        	////System.out.println("Actual String > " + Act);
					        	if (Exp.contains(Act)) {
									////System.out.println("Working fine for 10 records");
									Report.PassTest(test, "Working fine for 10 records");
								}
					        	else {
									////System.out.println("Not Working Fine for 10 records");
									Report.FailTestSnapshot(test, driver, "Not Working Fine for 10 records", "NoMore10Records");
								}
					        	ConfirmPrintNo.click();
							  
					        }
					        
					        else {
								   Report.FailTestSnapshot(test, driver, "RecordsisNotMoreThen10", "RecordsisNotMoreThen10");
							}
					            driver.switchTo().defaultContent();
				  }
					        catch(Exception e)
					 	   {
					 	   	Report.FailTest(test, e.getMessage());
					 	   	Report.FailTest(test, "Unable to Verify Route Cover Sheet" );
					 	   }
				  }
				  
				  public void verifyCustomerTicket() throws InterruptedException {
				try {
					Util.selectFrame("opusReportContainer");
					  String TC=totalCount.getText();
					     int Totalcount=Integer.parseInt(TC);
					  if (Totalcount > 0) {
						
					/////////////////////// For All and Signed Customer /////////////
					  Thread.sleep(1000);
					  for (int i = 1; i <=2; i++) {
						  CustomerTicket.click();
						  if (AllCustomerTicket.isDisplayed() && SignedCustomerTicket.isDisplayed()) {
						 driver.findElement(By.xpath("//*[@class='ticketselection_wrap']/a["+i+"]")).click();
						  Thread.sleep(1000);
						  String AlertText=driver.switchTo().alert().getText();
						  if (AlertText.contains("You don't have any routes selected for All Customer Tickets") || AlertText.contains("You don't have any routes selected for Signed Customer Tickets") ) {
							 // //System.out.println("Pass When record is zero");
							  Report.PassTest(test,"Pass when No record is selected");
						}
						  else {
							  ////System.out.println("Fail When record is zero");
							  Report.FailTestSnapshot(test, driver, "Fail When No record is selected","NoRecordSelected");
						}
						  driver.switchTo().alert().accept();
							Thread.sleep(2000);
							////System.out.println("AlertText is >" +AlertText );
							Report.PassTest(test, "AlertText is >" +AlertText );
						  }
						  
					}
					  
					  
					   for (int j = 1; j <=2; j++) {
						   Multiselect.click();
						  Thread.sleep(1000);
						  CustomerTicket.click();
						  if (AllCustomerTicket.isDisplayed() && SignedCustomerTicket.isDisplayed()) {
							  driver.findElement(By.xpath("//*[@class='ticketselection_wrap']/a["+j+"]")).click();
							  Thread.sleep(1000);
							      String WarningMsg=driver.findElement(By.xpath("//*[@class='confirmInfo print_mesg']/p")).getText();
								Thread.sleep(2000);
								////System.out.println("AlertText is >" +WarningMsg );
								Report.PassTest(test, "AlertText is >" +WarningMsg);
								ConfirmPrintYes.click();
								Thread.sleep(5000);
								 String AlertText1=driver.switchTo().alert().getText();
								 if (AlertText1.contains("All Customer Tickets request has been submitted") || AlertText1.contains("Signed Customer Tickets request has been submitted")) {
									////System.out.println("Pass When record is more then zero");
									Report.PassTest(test, "Pass When selected records is more then zero");
								}
								 else {
									 ////System.out.println("Fail When record is more then zero");
									 Report.FailTestSnapshot(test, driver, "Fail When Selected  records is more then zero", "MoreTheZeroRecordsSelected");
								}
								  driver.switchTo().alert().accept();
									Thread.sleep(2000);
									////System.out.println("AlertText is >" +AlertText1 ); 
									Report.PassTest(test, "AlertText is >" +AlertText1);
									Multiselect.click();
						  }
					}   
					  
					  
					  
					}
					  driver.switchTo().defaultContent();
						Thread.sleep(2000);
					   
				  }
						catch(Exception e)
					 	   {
					 	   	Report.FailTest(test, e.getMessage());
					 	   	Report.FailTest(test, "Unable to Verify Customer Ticket" );
					 	   }
					  
					  }
				  public void UI_validateRMDashboardFilters() {
						
						String expectedDefaultSite="S00173 - Pasadena Building";
						String expectedDefaultRouteManager="All Route Managers";
						String expectedDefaultRouteGroups="All Report Groups";		
						String yesterday=new SimpleDateFormat("EEEE", Locale.ENGLISH).format(yesterday());
						try
						{
							Thread.sleep(2000);
							Util.selectFrame("opusReportContainer");
							if(expectedDefaultSite.equals(siteFilterValue.getText()))
							{
								Util.highLightElement(driver, siteFilterValue);
								Report.PassTest(test, "Default site in RM Dashboard filter is as expected. Actual is : "+siteFilterValue.getText()+" and Expected is : "+expectedDefaultSite);
							}
							else
							{
								Util.highLightElement(driver, siteFilterValue);
								Report.FailTest(test, "Default site in RM Dashboard filter is as not as expected. Actual is : "+siteFilterValue.getText()+" and Expected is : "+expectedDefaultSite);
							}
							if(expectedDefaultRouteManager.equals(routeManagerFilterValue.getText()))
							{
								Util.highLightElement(driver, routeManagerFilterValue);
								Report.PassTest(test, "Default Route Manager in RM Dashboard filter is as expected. Actual is : "+routeManagerFilterValue.getText()+" and Expected is : "+expectedDefaultRouteManager);
							}
							else
							{
								Util.highLightElement(driver, routeManagerFilterValue);
								Report.FailTest(test, "Default Route Manager in RM Dashboard filter is as not as expected. Actual is : "+routeManagerFilterValue.getText()+" and Expected is : "+expectedDefaultRouteManager);
							}
							if(expectedDefaultRouteGroups.equals(reportGroupFilterValue.getText()))
							{
								Util.highLightElement(driver, reportGroupFilterValue);
								Report.PassTest(test, "Default Route Group in RM Dashboard filter is as expected. Actual is : "+reportGroupFilterValue.getText()+" and Expected is : "+expectedDefaultRouteGroups);
							}
							else
							{
								Util.highLightElement(driver, reportGroupFilterValue);
								Report.FailTest(test, "Default Route Group in RM Dashboard filter is as not as expected. Actual is : "+reportGroupFilterValue.getText()+" and Expected is : "+expectedDefaultRouteGroups);
							}
							
							if(yesterday.equals(dayOfWeekLabel.getText()))
							{
								Util.highLightElement(driver, dayOfWeekLabel);
								Report.PassTest(test, "Day of week in RM Dashboard filter is as expected. Actual is : "+dayOfWeekLabel.getText()+" and Expected is : "+yesterday);
							}
							else
							{
								Util.highLightElement(driver, dayOfWeekLabel);
								Report.FailTest(test, "Day of week in RM Dashboard filter is as not as expected. Actual is : "+dayOfWeekLabel.getText()+" and Expected is : "+yesterday);
							}
							driver.switchTo().defaultContent();
						}
						catch(Exception e)
						{
							Report.FailTest(test, e.getMessage());
							Report.FailTestSnapshot(test, driver, "RM Dashboard Filter section validation", "RM Dashboard filter section");
						}
						
					}
				  private Date yesterday() {
					    final Calendar cal = Calendar.getInstance();
					    cal.add(Calendar.DATE, -1);
					    return cal.getTime();
					}
				  
			public void SelectLOB(String LOB, String ReportName) throws InterruptedException {
						  
						  String Reports=ReportName;
						  switch (Reports) {
						  case "Pre-Route Detail":
						  case "Post-Route Detail":
						  case "Disposal Cycle Detail":
						  case "Sequence Compliance Detail":
						  try {	
							  Util.switchToDefaultWindow();
								String[] LOBs = LOB.split(";");
								Util.selectFrame("opusReportContainer");
								SelectLOB.click();
								CommonSearchClick.click();
								for (int i = 0; i < LOBs.length; i++) {
									if (driver.findElements(
								By.xpath(".//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/input[@title='"+LOBs[i]+"']")).size()!=0)
									{
							driver.findElement(By.xpath(".//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/input[@title='"+LOBs[i]+"']")).click();
								
									}
									else {
										Report.InfoTest(test, "Selected LOB is not Displayed in List >" + LOBs[i]+ "And Report is >" +ReportName );
									}
									
								}
								driver.switchTo().defaultContent();
								Report.PassTest(test, "LOB : " + LOB + " was selected successfully");
			      			    this.GoBtn();
			      			    Thread.sleep(3000);
			      			  Util.selectFrame("opusReportContainer,subReportContainer");
						     if (driver.findElements(By.xpath("//*[@id='t2e']/tbody")).size()!=0) {
						    	 Thread.sleep(2000);
							  List<WebElement>cols=driver.findElements(By.xpath("//*[@id='t2e']/tbody/tr/td[contains(@id,'colLOB')]/span"));
								for (int j = 0; j <cols.size(); j++) {
									String ActualData=cols.get(j).getText();
									boolean FoundLOB = false;
									  for(int ArrLp = 0; ArrLp <LOBs.length ;ArrLp++  ) {
										String  ExpLob = LOBs[ArrLp];
										if(ExpLob.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
											FoundLOB = true;
											break;
										}									  
									  }
									if (FoundLOB) {
									Report.PassTest(test, "Found Record in Summary Table when Report  is "+ ReportName +" and Actual LOB is >"  + ActualData.toString() +"  and Expected LOB is > " + Arrays.asList(LOBs).toString()  );
									}
									else {
										Report.FailTestSnapshot(test, driver, "Unable to Found Record in Summary Table when Report  is "+ ReportName +" and Actual LOB is >"  + ActualData.toString() +"  and Expected LOB is > " + Arrays.asList(LOBs).toString() , "NORecords");
									}
								}
						     }
						     else {
								Report.InfoTest(test, "Not Found records Please change search criteria when report is >" + ReportName);
							}
							      
						  }
						       catch (Exception e) {
									Report.FailTest(test, e.getMessage());
									Report.FailTest(test, "Not able to select route");
			
								}   
						  driver.switchTo().defaultContent();
						  break;
						  
						  case "Pre-Route Summary":
						  case "Post-Route Summary":
						  case "Idle Summary":
						  case "Disposal Cycle Summary":
						  case "Sequence Compliance Summary":
						  case "Dispatch Summary":
						  case "Efficiency Summary":
							  
							  try {
								  
								  String[] LOBs = LOB.split(";");
								Util.selectFrame("opusReportContainer");
									SelectLOB.click();
									CommonSearchClick.click();
									for (int i = 1; i < LOBs.length; i++) {
										
										if (driver.findElements(
												By.xpath(".//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/input[@title='"
														+ LOBs[i] + "']")).size()!=0) {
											driver.findElement(
													By.xpath(".//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/input[@title='"
															+ LOBs[i] + "']"))
													.click();
										}
										else {
											Report.InfoTest(test, "Selected LOB is not Displayed in List >" + LOBs[i]+ "And Report is >" +ReportName );
										}
										
			
									}
									
									Thread.sleep(1000);
									driver.switchTo().defaultContent();
									Report.PassTest(test, "LOB : " + LOB + " was selected successfully");
									    this.GoBtn();
								
							    Report.InfoTest(test,"In Subview Detail table LOB is not displayed when Report Is  " + ReportName + "and LOB is >" + LOB);
								
							} catch (Exception e) {
								
								Report.FailTestSnapshot(test, driver, "No Records available in Summary Table When Report is " + ReportName + "and LOB is >" + LOB, "Failed "+ReportName+" "+LOB);
							}
							  driver.switchTo().defaultContent();  
							  
						 break;
							  
													  
						  case "Idle Detail":	
						  case "Downtime Detail":
						  case "Flash Report":
						  case "District Dashboard - Beta":
							try {  
							  String[] LOBs = LOB.split(";");
							
							  Util.selectFrame("opusReportContainer");
								SelectLOB.click();
								CommonSearchClick.click();
								for (int i = 0; i < LOBs.length; i++) {
									if (driver.findElements(
											By.xpath(".//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/input[@title='"+LOBs[i]+"']")).size()!=0)
												{
										driver.findElement(By.xpath(".//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/input[@title='"+LOBs[i]+"']")).click();
											
												}
												else {
													Report.InfoTest(test, "Selected LOB is not Displayed in List >" + LOBs[i]+ "And Report is >" +ReportName );
												}
			
								}
								
								Thread.sleep(1000);
								driver.switchTo().defaultContent();
								Report.PassTest(test, "LOB : " + LOB + " was selected successfully");
						    this.GoBtn(); 
						    Thread.sleep(2000);
						    Util.selectFrame("opusReportContainer,subReportContainer");
						    List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='t2']/tbody/tr/td[contains(@id,'colLOB')]/span"));
									for (int j = 0; j <Cols.size(); j++) {
										String ActualData=Cols.get(j).getText();
										boolean FoundLOB = false;
										  for(int ArrLp = 0; ArrLp <LOBs.length ;ArrLp++  ) {
											String  ExpLob = LOBs[ArrLp];
											if(ExpLob.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
												FoundLOB = true;
												break;
											}									  
										  }
										if (FoundLOB) {
										Report.PassTest(test, "Found Record in Summary Table when Report  is "+ ReportName + "and LOB is >" + LOB  );
										}
										else {
											Report.FailTestSnapshot(test, driver, "Unable to found Record in Summary Table when Report  is "+ ReportName + "and LOB is >" + LOB, "NORecords");
										}
									}
								
							} 
							catch (Exception e) {
								Report.FailTestSnapshot(test, driver, "Failed Filter Validation when Report  is "+ ReportName + "and LOB is >" + LOB, "IdelReportFailed");
							}
							 
							driver.switchTo().defaultContent();
							
							break;
										
							  
						  case "Customer Property Detail":
							  try {
								
								  String[] LOBs = LOB.split(";");
									
								  Util.selectFrame("opusReportContainer");
									SelectLOBCustPropDetail.click();
									CommonSearchClick.click();
									CommonSearchClick.sendKeys(LOB);
									Thread.sleep(1000);
									driver.switchTo().defaultContent();
									Report.PassTest(test, "LOB : " + LOB + " was selected successfully");
			
								 
									 Thread.sleep(2000);
							    this.GoBtn(); 
							    Thread.sleep(2000);
							    Util.selectFrame("opusReportContainer,subReportContainer");
							     List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='t3']/tbody/tr/td[contains(@id,'colLOB_Row')]/span"));
										for (int j = 0; j <Cols.size(); j++) {
											String ActualData=Cols.get(j).getText();
											boolean FoundLOB = false;
											  for(int ArrLp = 0; ArrLp <LOBs.length ;ArrLp++  ) {
												String  ExpLob = LOBs[ArrLp];
												if(ExpLob.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
													FoundLOB = true;
													break;
												}									  
											  }
											if (FoundLOB) {
											Report.PassTest(test, "Found Record in Summary Table when Report  is "+ ReportName + "and LOB is >" + LOB  );
											}
											else {
												Report.FailTestSnapshot(test, driver, "Unable to found Record in Summary Table when Report  is "+ ReportName + "and LOB is >" + LOB, "NORecords");
											}
										}
															  
							 
							  }
							  catch (Exception e) {
								
								  Report.FailTestSnapshot(test, driver, "Unable to Validate When Report  is "+ ReportName + "and LOB is >" + LOB, "CUSTOMERPROPERTYDETAILFailed");
							}
							  driver.switchTo().defaultContent();
							  break;
						
								
							case "Efficiency Performance":
								try {
									
									Util.selectFrame("opusReportContainer");
									SelectLOBCustPropDetail.click();
									LOBSearchBox.click();
									CommonSearchClick.click();
									CommonSearchClick.sendKeys(LOB);	
									if (driver.findElements(By.xpath(".//*[@name='multiselect_islLOBFilterS' and @title='"+LOB+"']/following-sibling::span[contains(text(),'"+LOB+"')]")).size()!=0) {
										driver.findElement(By.xpath(".//*[@name='multiselect_islLOBFilterS' and @title='"+LOB+"']/following-sibling::span[contains(text(),'"+LOB+"')]")).click();	
									}
									
									driver.switchTo().defaultContent();
									this.GoBtn(); 
								    Thread.sleep(2000);
								    Util.selectFrame("opusReportContainer,subReportContainer");
								     HelperTab.click();
								     Thread.sleep(2000);
							       String Exp=LOB.substring(0,3);
							  List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='t2h']/tbody/tr/td[contains(@id,'colLOB_Row')]/span"));
										for (int j = 0; j <Cols.size(); j++) {
											if (Cols.get(j).getText().contains(Exp.trim().toUpperCase())) {
										Report.PassTest(test,"Records Validated Sucessfully when Report is >" + ReportName +"and Lob is >" + LOB); 
											}
											else {
												Report.FailTestSnapshot(test, driver, "Unable to Validate Records when Report is >" + ReportName +"and Lob is >" + LOB, "Failed EFFICIENCYPERFORMANCE");
											}
										}						
									
								} catch (Exception e) {
									
									Report.FailTestSnapshot(test, driver, "Unable to Validate Records when Report is >" + ReportName +"and Lob is >" + LOB, "Failed EFFICIENCYPERFORMANCE");
								}
								
								driver.switchTo().defaultContent();
								
								
								break;
								
							case "Flash Report - Beta":
								try {	
								  Util.switchToDefaultWindow();
									String[] LOBs = LOB.split(";");
									Util.selectFrame("opusReportContainer");
									SelectLOB.click();
									LOBSearchBox.click();
									for (int i = 0; i < LOBs.length; i++) {
										if (driver.findElements(
												By.xpath(".//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/input[@title='"+LOBs[i]+"']")).size()!=0)
													{
											driver.findElement(By.xpath(".//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/input[@title='"+LOBs[i]+"']")).click();
												
													}
													else {
														Report.InfoTest(test, "Selected LOB is not Displayed in List >" + LOBs[i]+ "And Report is >" +ReportName );
													}
				
									}
									driver.switchTo().defaultContent();
									Report.PassTest(test, "LOB : " + LOB + " was selected successfully");
				      			    this.GoBtn();
				      			    Thread.sleep(3000);
				      			  Util.selectFrame("opusReportContainer,subReportContainer");
							     if (driver.findElements(By.xpath("//*[@id='t2e']/tbody")).size()!=0) {
							    	 Thread.sleep(2000);
								  List<WebElement>cols=driver.findElements(By.xpath("//*[@id='dtScorecardSubview']/tbody/tr/td[contains(@id,'colLOB')]/span"));
									for (int j = 0; j <cols.size(); j++) {
										String ActualData=cols.get(j).getText();
										boolean FoundLOB = false;
										  for(int ArrLp = 0; ArrLp <LOBs.length ;ArrLp++  ) {
											String  ExpLob = LOBs[ArrLp];
											if(ExpLob.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
												FoundLOB = true;
												break;
											}									  
										  }
										if (FoundLOB) {
										Report.PassTest(test, "Found Record in Summary Table when Report  is "+ ReportName +" and Actual LOB is >"  + ActualData.toString() +"  and Expected LOB is > " + Arrays.asList(LOBs).toString()  );
										}
										else {
											Report.FailTestSnapshot(test, driver, "Unable to Found Record in Summary Table when Report  is "+ ReportName +" and Actual LOB is >"  + ActualData.toString() +"  and Expected LOB is > " + Arrays.asList(LOBs).toString() , "NORecords");
										}
									}
							     }
							     else {
									Report.InfoTest(test, "Not Found records Please change search criteria when report is >" + ReportName);
								}
								      
							  }
							       catch (Exception e) {
										Report.FailTest(test, e.getMessage());
										Report.FailTest(test, "Not able to select route");
				
									}   
							  driver.switchTo().defaultContent();
								break;
								
								
								
							default:
					             throw new IllegalArgumentException("Invalid ReportName:  " + ReportName );
									  }		  
									  
										  
										  
									  
								  }
		
			public void SelectSite(String Site, String ReportName) {
				
				   String Reports=ReportName;
				   
					
				  switch (Reports) {
				  
				  case "Route Summary":
				  try { 		  
				    	String[] Sites = Site.split(";");
					
						Util.switchToDefaultWindow();
						Util.selectFrame("opusReportContainer");
						SiteFilter.click();
						CommonSearchClick.click();
						for (int i = 0; i < Sites.length; i++) {
							if (driver.findElements(
						By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).size()!=0) {
								driver.findElement(
										By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).click();
							
							}
							else {
								Report.InfoTest(test, "Selected Site is not displayed in the List when site is " +Sites[i]+ "and Report is " +ReportName );
							}
							
						}
						driver.switchTo().defaultContent();
						Thread.sleep(1000);
					    this.GoBtn();
						 Thread.sleep(3000);
						 Util.selectFrame("opusReportContainer,subReportContainer");
					 if (driver.findElements(By.xpath("//*[@id='dtRouteDetail']/tbody")).size()!=0) {
						 List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtRouteDetail']/tbody/tr/td[contains(@id,'colSite')]/span"));
					  for (int j = 0; j <Cols.size(); j++) {
						String ActualData=Cols.get(j).getText();
						boolean FoundSite = false;
						  for(int ArrLp = 0; ArrLp<Sites.length;ArrLp++  ) {
							String  ExpSite = Sites[ArrLp];
							if(  ActualData.trim().contains(ExpSite)) {
								FoundSite = true;
								break;
							}									  
						  }
						  
						  if (FoundSite) {
								Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Actual Site is >"  + ActualData.toString() +"  and Expected Site is > " + Arrays.asList(Sites).toString());
							}
							else {
								Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Actual Site is >"  + ActualData.toString() +"  and Expected Site is > " + Arrays.asList(Sites).toString(), "FailedRouteSummary");
							}
						}
						
					
					 }
					 else {
							Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
					}
				  }
				  catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
				 
				  driver.switchTo().defaultContent();
				  
				  
				  break; 
				  
				  case "Pre-Route Summary":
					  try {
						  Util.switchToDefaultWindow();
						    String[] Sites = Site.split(";");
						    Util.selectFrame("opusReportContainer");
							SiteFilter.click();
							CommonSearchClick.click();
							for (int i = 0; i < Sites.length; i++) {
								if (driver.findElements(
										By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).size()!=0) {
												driver.findElement(
														By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).click();
											
											}
											else {
												Report.InfoTest(test, "Selected Site is not displayed in the List when site is " +Sites[i]+ "and Report is " +ReportName );
											}
							}
							driver.switchTo().defaultContent();
							Thread.sleep(1000);
						   this.GoBtn();
							 Thread.sleep(3000);
							 Util.selectFrame("opusReportContainer,subReportContainer");
						 if (driver.findElements(By.xpath("//*[@id='dtPreRouteDetail']/tbody")).size()!=0) {					
						 List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtPreRouteDetail']/tbody/tr/td[contains(@id,'colSite')]/span"));
							  for (int j = 0; j <Cols.size(); j++) {
								  String ActualData=Cols.get(j).getText();
								  boolean FoundSite = false;
								  for(int ArrLp = 0; ArrLp<Sites.length;ArrLp++  ) {
									String  ExpSite = Sites[ArrLp];
									if(  ActualData.trim().contains(ExpSite)) {
										FoundSite = true;
										break;
									}									  
								  }
								  
								  if (FoundSite) {
										Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Actual Site is >"  + ActualData.toString() +"  and Expected Site is > " + Arrays.asList(Sites).toString());
									}
									else {
										Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Actual Site is >"  + ActualData.toString() +"  and Expected Site is > " + Arrays.asList(Sites).toString(), "FailedRouteSummary");
									}
							}
							
						
						 }
						 else {
							Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
						}
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
				  
					  driver.switchTo().defaultContent();
					  
					  
					  break;
				  
				  case "Post-Route Summary":
					  try {
						  Util.switchToDefaultWindow();
						  String[] Sites = Site.split(";");
						  Util.selectFrame("opusReportContainer");
							SiteFilter.click();
							CommonSearchClick.click();
							for (int i = 0; i < Sites.length; i++) {
								if (driver.findElements(
										By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).size()!=0) {
												driver.findElement(
														By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).click();
											
											}
											else {
												Report.InfoTest(test, "Selected Site is not displayed in the List when site is " +Sites[i]+ "and Report is " +ReportName );
											}
							}
							driver.switchTo().defaultContent();
							Thread.sleep(1000);
							 this.GoBtn();
							 Thread.sleep(3000);
							 Util.selectFrame("opusReportContainer,subReportContainer");
						 if (driver.findElements(By.xpath("//*[@id='dtPostRouteDetail']/tbody")).size()!=0) {					
						     List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtPostRouteDetail']/tbody/tr/td[contains(@id,'colSite')]/span"));
						   for (int j = 0; j <Cols.size(); j++) {
							   String ActualData=Cols.get(j).getText();
							    boolean FoundSite = false;
								  for(int ArrLp = 0; ArrLp<Sites.length;ArrLp++  ) {
									String  ExpSite = Sites[ArrLp];
									if(  ActualData.trim().contains(ExpSite)) {
										FoundSite = true;
										break;
									}									  
								  }
								  
								  if (FoundSite) {
										Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Actual Site is >"  + ActualData.toString() +"  and Expected Site is > " + Arrays.asList(Sites).toString());
									}
									else {
										Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Actual Site is >"  + ActualData.toString() +"  and Expected Site is > " + Arrays.asList(Sites).toString(), "FailedRouteSummary");
									}
							}
							
						
						 }
						 else {
							Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
						}
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
				  
					  driver.switchTo().defaultContent();
					  
					  
					  break;
				  
				 case "Idle Summary":
					  
					  try {
						  Util.switchToDefaultWindow();
						  String[] Sites = Site.split(";");
						  Util.selectFrame("opusReportContainer");
							SiteFilter.click();
							CommonSearchClick.click();
							for (int i = 0; i < Sites.length; i++) {
								if (driver.findElements(
										By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).size()!=0) {
												driver.findElement(
														By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).click();
											
											}
											else {
												Report.InfoTest(test, "Selected Site is not displayed in the List when site is " +Sites[i]+ "and Report is " +ReportName );
											}
							}
							driver.switchTo().defaultContent();
							Thread.sleep(1000);
						   this.GoBtn();
							 Thread.sleep(3000);
							 Util.selectFrame("opusReportContainer,subReportContainer");
						 if (driver.findElements(By.xpath("//*[@id='dtIdleTmDetail']/tbody")).size()!=0) {					
						   List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtIdleTmDetail']/tbody/tr/td[contains(@id,'colSite')]/span"));
						    for (int j = 0; j <Cols.size(); j++) {
						    	String ActualData=Cols.get(j).getText();
						    	boolean FoundSite = false;
								  for(int ArrLp = 0; ArrLp<Sites.length;ArrLp++  ) {
									String  ExpSite = Sites[ArrLp];
									if(  ActualData.trim().contains(ExpSite)) {
										FoundSite = true;
										break;
									}									  
								  }
								  
								  if (FoundSite) {
										Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Actual Site is >"  + ActualData.toString() +"  and Expected Site is > " + Arrays.asList(Sites).toString());
									}
									else {
										Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Actual Site is >"  + ActualData.toString() +"  and Expected Site is > " + Arrays.asList(Sites).toString(), "FailedRouteSummary");
									}
							}
							
						
						 }
						 else {
							Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
						}
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
				  
					  driver.switchTo().defaultContent();
					  
					  
					  break;
				  
				  
				  case "Disposal Cycle Summary":
					 
					  			  
					  try {
						  Util.switchToDefaultWindow();
						  String[] Sites = Site.split(";");
						  Util.selectFrame("opusReportContainer");
							SiteFilter.click();
							CommonSearchClick.click();
							for (int i = 0; i < Sites.length; i++) {
								if (driver.findElements(
										By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).size()!=0) {
												driver.findElement(
														By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).click();
											
											}
											else {
												Report.InfoTest(test, "Selected Site is not displayed in the List when site is " +Sites[i]+ "and Report is " +ReportName );
											}
							}
							driver.switchTo().defaultContent();
							Thread.sleep(1000);
							this.GoBtn();
							 Thread.sleep(3000);
							 Util.selectFrame("opusReportContainer,subReportContainer");
						 if (driver.findElements(By.xpath("//*[@id='dtDisposalTimeDetail']/tbody")).size()!=0) {					
						   List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtDisposalTimeDetail']/tbody/tr/td[contains(@id,'colSite')]/span"));
						   for (int j = 0; j <Cols.size(); j++) {
							   String ActualData=Cols.get(j).getText();
							   boolean FoundSite = false;
								  for(int ArrLp = 0; ArrLp<Sites.length;ArrLp++  ) {
									String  ExpSite = Sites[ArrLp];
									if(  ActualData.trim().contains(ExpSite)) {
										FoundSite = true;
										break;
									}									  
								  }
								  
								  if (FoundSite) {
										Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Actual Site is >"  + ActualData.toString() +"  and Expected Site is > " + Arrays.asList(Sites).toString());
									}
									else {
										Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Actual Site is >"  + ActualData.toString() +"  and Expected Site is > " + Arrays.asList(Sites).toString(), "FailedRouteSummary");
									}
							}
							
						
						 }
						 else {
							Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
						}
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
				  
					  driver.switchTo().defaultContent();
					  
					  
					  break;
				  
				  
				  case "Sequence Compliance Summary":
				  
					  try {
						  Util.switchToDefaultWindow();
						  String[] Sites = Site.split(";");
						  Util.selectFrame("opusReportContainer");
							SiteFilter.click();
							CommonSearchClick.click();
							for (int i = 0; i < Sites.length; i++) {
								if (driver.findElements(
										By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).size()!=0) {
												driver.findElement(
														By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).click();
											
											}
											else {
												Report.InfoTest(test, "Selected Site is not displayed in the List when site is " +Sites[i]+ "and Report is " +ReportName );
											}
							}
							
							driver.switchTo().defaultContent();
							Thread.sleep(1000);
						    this.GoBtn();
							 Thread.sleep(3000);
							 Util.selectFrame("opusReportContainer,subReportContainer");
						 if (driver.findElements(By.xpath("//*[@id='dtSeqComplianceDetail']/tbody")).size()!=0) {					
						   List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtSeqComplianceDetail']/tbody/tr/td[contains(@id,'colSite')]/span"));
						   for (int j = 0; j <Cols.size(); j++) {
							   String ActualData=Cols.get(j).getText();
							   boolean FoundSite = false;
								  for(int ArrLp = 0; ArrLp<Sites.length;ArrLp++  ) {
									String  ExpSite = Sites[ArrLp];
									if(  ActualData.trim().contains(ExpSite)) {
										FoundSite = true;
										break;
									}									  
								  }
								  
								  if (FoundSite) {
										Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Actual Site is >"  + ActualData.toString() +"  and Expected Site is > " + Arrays.asList(Sites).toString());
									}
									else {
										Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Actual Site is >"  + ActualData.toString() +"  and Expected Site is > " + Arrays.asList(Sites).toString(), "FailedRouteSummary");
									}
							}
							
						
						 }
						 else {
							Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
						}
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
				  
					  driver.switchTo().defaultContent();
					  
					  
					  break;
						  
				  case "Dispatch Summary":
					  try {
						  Util.switchToDefaultWindow();
						  String[] Sites = Site.split(";");
						  Util.selectFrame("opusReportContainer");
							SiteFilter.click();
							CommonSearchClick.click();
							for (int i = 0; i < Sites.length; i++) {
								if (driver.findElements(
										By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).size()!=0) {
												driver.findElement(
														By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).click();
											
											}
											else {
												Report.InfoTest(test, "Selected Site is not displayed in the List when site is " +Sites[i]+ "and Report is " +ReportName );
											}
							}
							
							driver.switchTo().defaultContent();
							Thread.sleep(1000);
							 this.GoBtn();
							 Thread.sleep(3000);
							 Util.selectFrame("opusReportContainer,subReportContainer");
						 if (driver.findElements(By.xpath("//*[@id='t2']/tbody")).size()!=0) {					
						    List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='t2']/tbody/tr/td[contains(@id,'colSite')]/span"));
						    for (int j = 0; j <Cols.size(); j++) {
						    	String ActualData=Cols.get(j).getText();
						    	boolean FoundSite = false;
								  for(int ArrLp = 0; ArrLp<Sites.length;ArrLp++  ) {
									String  ExpSite = Sites[ArrLp];
									if(  ActualData.trim().contains(ExpSite)) {
										FoundSite = true;
										break;
									}									  
								  }
								  
								  if (FoundSite) {
										Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Actual Site is >"  + ActualData.toString() +"  and Expected Site is > " + Arrays.asList(Sites).toString());
									}
									else {
										Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Actual Site is >"  + ActualData.toString() +"  and Expected Site is > " + Arrays.asList(Sites).toString(), "FailedRouteSummary");
									}
							}
							
						
						 }
						 else {
							
							Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
						}
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
				  
					  driver.switchTo().defaultContent();
					  
					  
					  break;
					  
				  case "Efficiency Performance":
					   
					  try {
						  Util.switchToDefaultWindow();
						  String[] Sites = Site.split(";");
						  Util.selectFrame("opusReportContainer");
							SiteFilter.click();
							CommonSearchClick.click();
							for (int i = 0; i < Sites.length; i++) {
								if (driver.findElements(
										By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).size()!=0) {
												driver.findElement(
														By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).click();
											
											}
											else {
												Report.InfoTest(test, "Selected Site is not displayed in the List when site is " +Sites[i]+ "and Report is " +ReportName );
											}
							}
							
							driver.switchTo().defaultContent();
							Thread.sleep(1000);
							 this.GoBtn();
							 Thread.sleep(3000);
							 Util.selectFrame("opusReportContainer,subReportContainer");
						     ClickonHelperTab.click();
						     Thread.sleep(2000);
						 if (driver.findElements(By.xpath("//*[@id='t2h']/tbody")).size()!=0) {					
						    List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='t2h']/tbody/tr/td[contains(@id,'colSite')]/span"));
						    for (int j = 0; j <Cols.size(); j++) {
						    	String ActualData=Cols.get(j).getText();
						    	boolean FoundSite = false;
								  for(int ArrLp = 0; ArrLp<Sites.length;ArrLp++) {
									String  ExpSite = Sites[ArrLp];
									if(ExpSite.trim().contains(ActualData)) {
										FoundSite = true;
										break;
									}									  
								  }
								  
								  if (FoundSite) {
										Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Actual Site is >"  + ActualData.toString() +"  and Expected Site is > " + Arrays.asList(Sites).toString());
									}
									else {
										Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Actual Site is >"  + ActualData.toString() +"  and Expected Site is > " + Arrays.asList(Sites).toString(), "FailedRouteSummary");
									}
							}
							
						
						 }
						 else {
							
							Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
						}
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
				  
					  driver.switchTo().defaultContent();
					  
					  
					  break;  
					  
				  case "Efficiency Summary":
					   
					  try {
						  Util.switchToDefaultWindow();
						  String[] Sites = Site.split(";");
							driver.switchTo().frame("opusReportContainer");
							SiteFilter.click();
							MultipleSite.click();
							for (int i = 0; i < Sites.length; i++) {
								if (driver.findElements(
										By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).size()!=0) {
												driver.findElement(
														By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).click();
											
											}
											else {
												Report.InfoTest(test, "Selected Site is not displayed in the List when site is " +Sites[i]+ "and Report is " +ReportName );
											}
							}
							
							driver.switchTo().defaultContent();
							Thread.sleep(1000);
							 this.GoBtn();
							 Thread.sleep(3000);
							 Util.switchToDefaultWindow();
						     Util.selectFrame("opusReportContainer,subReportContainer");

						     Thread.sleep(2000);
						 if (driver.findElements(By.xpath("//*[@id='dtEfficiencyDetail']/tbody")).size()!=0) {					
						    List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtEfficiencyDetail']/tbody/tr/td[contains(@id,'colSite')]/span"));
						    for (int j = 0; j <Cols.size(); j++) {
						    	String ActualData=Cols.get(j).getText();
						    	boolean FoundSite = false;
								  for(int ArrLp = 0; ArrLp<Sites.length;ArrLp++  ) {
									String  ExpSite = Sites[ArrLp];
									if(ActualData.trim().contains(ExpSite)) {
										FoundSite = true;
										break;
									}									  
								  }
								  
								  if (FoundSite) {
										Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Actual Site is >"  + ActualData.toString() +"  and Expected Site is > " + Arrays.asList(Sites).toString());
									}
									else {
										Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Actual Site is >"  + ActualData.toString() +"  and Expected Site is > " + Arrays.asList(Sites).toString(), "FailedRouteSummary");
									}
							}
							
						
						 }
						 else {
							
							Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
						}
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
				  
					  driver.switchTo().defaultContent();
					  
					  
					  break;	  
					  
					  
				  					  
				  
				  case "Pre-Route Detail": 
				  case "Idle Detail":
				  case "Disposal Cycle Detail":
				  case "Post-Route Detail":
				  case "Downtime Detail":
				  case "Customer Property Detail":
				  case "Sequence Compliance Detail": 	
				  case "Fastlane Disposal":
				  case "Flash Report":
				  case "District Dashboard - Beta":
				  case "Flash Report - Beta ":
					  try {
						  Util.switchToDefaultWindow();
						  Util.selectFrame("opusReportContainer");
					    	SiteFilter.click();
							Thread.sleep(1000);
							SiteFilterSearch.click();
							SiteFilterSearch.sendKeys(Site);
							driver.findElement(By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'" + Site + "')]")).click();
							Thread.sleep(2000);
							GoBtn.click();
							Thread.sleep(3000);
							 Report.InfoTest(test,"Unable to Validate Site in Subview Detail Section When Report is > " + ReportName + "and Site is >" + Site);
							driver.switchTo().defaultContent();
						  
					} catch (Exception e) {
						
						Report.FailTestSnapshot(test, driver, "Unable to Validate Site in Subview Detail Section When Report is > " + ReportName + "and Site is >" + Site, "FailedPREROUTEDETAIL");
					}  
					 
					  
					break;
					
				  					 
				
					
				  default:
			             throw new IllegalArgumentException("Invalid ReportName:  " + ReportName );
					  
					 
			             
				  
			}
			}
			
			public void SelectArea(String Area, String ReportName) throws InterruptedException {
				
				String Reports=ReportName;
				String[] Areas = Area.split(";");
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				SelectArea.click();
				CommonSearchClick.click();
				for (int i = 0; i < Areas.length; i++) {
					if (driver.findElements(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Areas[i]+"')])[1]")).size()!=0) {
			    		driver.findElement(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Areas[i]+"')])[1]")).click();
					}	
					else {
						Report.InfoTest(test, "Selected Area is not diaplyed in the List when area is "+Areas[i]+ "and Report Name is " +ReportName );
					}	
				}
				driver.switchTo().defaultContent();
				Thread.sleep(1000);
			   this.GoBtn();
				 Thread.sleep(5000);
				 Util.selectFrame("opusReportContainer,subReportContainer");
				
				  switch (Reports) {
				  
				  case "Route Summary":
				 
				  try {					
					 if (driver.findElements(By.xpath("//*[@id='dtRouteDetail']/tbody")).size()!=0) {
						List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtRouteDetail']/tbody/tr/td[contains(@id,'colArea')]/span"));
					     for (int j = 0; j <Cols.size(); j++) {
					    	 String ActualData=Cols.get(j).getText();	
					    	   boolean FoundArea = false;
								  for(int ArrLp = 0; ArrLp<Areas.length;ArrLp++  ) {
									String  ExpSite = Areas[ArrLp];
									if(ExpSite.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
										FoundArea = true;
										break;
									}									  
								  }
							   
								if (FoundArea) {
									
									Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Area is >"  + ActualData.toString() +"  and Expected Area is > " + Arrays.asList(Areas).toString() );
								}
								else {
									Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Area is >"  + ActualData.toString() +"  and Expected Area is > " + Arrays.asList(Areas).toString(), "FailedRouteSummary");
								}
						}
						
				
					 }
					 else {
						
						Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
					}
				
			}
				  catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
				
				  break;
				  
				  case "Pre-Route Summary":
					  try {
						  
						 if (driver.findElements(By.xpath("//*[@id='dtPreRouteDetail']/tbody")).size()!=0) {					
					List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtPreRouteDetail']/tbody/tr/td[contains(@id,'colArea')]/span"));  
						   for (int j = 0; j <Cols.size(); j++) {
							   String ActualData=Cols.get(j).getText();	
							   boolean FoundArea = false;
								  for(int ArrLp = 0; ArrLp<Areas.length;ArrLp++  ) {
									String  ExpSite = Areas[ArrLp];
									if(ExpSite.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
										FoundArea = true;
										break;
									}									  
								  }
							   
								if (FoundArea) {
									
									Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Area is >"  + ActualData.toString() +"  and Expected Area is > " + Arrays.asList(Areas).toString() );
								}
								else {
									Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Area is >"  + ActualData.toString() +"  and Expected Area is > " + Arrays.asList(Areas).toString(), "FailedRouteSummary");
								}
							}
							}
						 else {
								Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
						}
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
				  
					  driver.switchTo().defaultContent();
					  
					  
					  break; 
				  
				  case "Post-Route Summary":
					  try {
						  if (driver.findElements(By.xpath("//*[@id='dtPostRouteDetail']/tbody")).size()!=0) {					
						List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtPostRouteDetail']/tbody/tr/td[contains(@id,'colArea')]/span"));   
							   for (int j = 0; j <Cols.size(); j++) {
								   String ActualData=Cols.get(j).getText();	
								   boolean FoundArea = false;
									  for(int ArrLp = 0; ArrLp<Areas.length;ArrLp++  ) {
										String  ExpSite = Areas[ArrLp];
										if(ExpSite.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
											FoundArea = true;
											break;
										}									  
									  }
								   
									if (FoundArea) {
										
										Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Area is >"  + ActualData.toString() +"  and Expected Area is > " + Arrays.asList(Areas).toString() );
									}
									else {
										Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Area is >"  + ActualData.toString() +"  and Expected Area is > " + Arrays.asList(Areas).toString(), "FailedRouteSummary");
									}
								}
								
							
							 }
							 else {
								Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
					} 
					  }
					  catch (Exception e) {
		    	 			Report.FailTest(test, e.getMessage());
						}
					  driver.switchTo().defaultContent();
					  
					  
					  break; 
				  case "Idle Summary":
					  try {
						  if (driver.findElements(By.xpath("//*[@id='dtIdleTmDetail']/tbody")).size()!=0) {					
							List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtIdleTmDetail']/tbody/tr/td[contains(@id,'colArea')]/span"));
								  for (int j = 0; j <Cols.size(); j++) {
									  String ActualData=Cols.get(j).getText();	
									   boolean FoundArea = false;
										  for(int ArrLp = 0; ArrLp<Areas.length;ArrLp++  ) {
											String  ExpSite = Areas[ArrLp];
											if(ExpSite.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
												FoundArea = true;
												break;
											}									  
										  }
									   
										if (FoundArea) {
											
											Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Area is >"  + ActualData.toString() +"  and Expected Area is > " + Arrays.asList(Areas).toString() );
										}
										else {
											Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Area is >"  + ActualData.toString() +"  and Expected Area is > " + Arrays.asList(Areas).toString(), "FailedRouteSummary");
										}
								}
								
							
							 }
							 else {
								Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
							}
						  
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
				  
					  driver.switchTo().defaultContent();
					  			  
					  break;
					  
				  case "Disposal Cycle Summary":
					  try {
						  if (driver.findElements(By.xpath("//*[@id='dtDisposalTimeDetail']/tbody")).size()!=0) {					
							List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtDisposalTimeDetail']/tbody/tr/td[contains(@id,'colArea')]/span"));
								  for (int j = 0; j <Cols.size(); j++) {
									  String ActualData=Cols.get(j).getText();	
									    boolean FoundArea = false;
										  for(int ArrLp = 0; ArrLp<Areas.length;ArrLp++  ) {
											String  ExpSite = Areas[ArrLp];
											if(ExpSite.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
												FoundArea = true;
												break;
											}									  
										  }
									   
										if (FoundArea) {
											
											Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Area is >"  + ActualData.toString() +"  and Expected Area is > " + Arrays.asList(Areas).toString() );
										}
										else {
											Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Area is >"  + ActualData.toString() +"  and Expected Area is > " + Arrays.asList(Areas).toString(), "FailedRouteSummary");
										}
								}
								
							
							 }
							 else {
								Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
							}
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
					  driver.switchTo().defaultContent();
		  			  
					  break;
					  
				  case "Sequence Compliance Summary":
					  try {
						  if (driver.findElements(By.xpath("//*[@id='dtSeqComplianceDetail']/tbody")).size()!=0) {					
							List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtSeqComplianceDetail']/tbody/tr/td[contains(@id,'colArea')]/span"));  
							    for (int j = 0; j <Cols.size(); j++) {
							    	String ActualData=Cols.get(j).getText();	
									   boolean FoundArea = false;
										  for(int ArrLp = 0; ArrLp<Areas.length;ArrLp++  ) {
											String  ExpSite = Areas[ArrLp];
											if(ExpSite.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
												FoundArea = true;
												break;
											}									  
										  }
									   
										if (FoundArea) {
											
											Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Area is >"  + ActualData.toString() +"  and Expected Area is > " + Arrays.asList(Areas).toString() );
										}
										else {
											Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Area is >"  + ActualData.toString() +"  and Expected Area is > " + Arrays.asList(Areas).toString(), "FailedRouteSummary");
										}
								}
								
							
							 }
							 else {
							
								Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
							}
						  
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
					  driver.switchTo().defaultContent();
		  			  
					  break;
					
				  case "Efficiency Summary":
					  try {
						  if (driver.findElements(By.xpath("//*[@id='dtEfficiencyDetail']/tbody")).size()!=0) {					
							List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtEfficiencyDetail']/tbody/tr/td[contains(@id,'colArea')]/span"));  
							    for (int j = 0; j <Cols.size(); j++) {
							    	String ActualData=Cols.get(j).getText();	
									   boolean FoundArea = false;
										  for(int ArrLp = 0; ArrLp<Areas.length;ArrLp++  ) {
											String  ExpSite = Areas[ArrLp];
											if(ExpSite.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
												FoundArea = true;
												break;
											}									  
										  }
									   
										if (FoundArea) {
											
											Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Area is >"  + ActualData.toString() +"  and Expected Area is > " + Arrays.asList(Areas).toString() );
										}
										else {
											Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Area is >"  + ActualData.toString() +"  and Expected Area is > " + Arrays.asList(Areas).toString(), "FailedRouteSummary");
										}
								}
								
							
							 }
							 else {
							
								Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
							}
						  
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
					  driver.switchTo().defaultContent();
		  			  
					  break;
					  
					  
				  case "Dispatch Summary":
					 			  
					Report.InfoTest(test, "Unable to found Area in Subview Details  when Report is "+ ReportName);
					break;
					
					
				  default:
			             throw new IllegalArgumentException("Invalid ReportName:  " + ReportName );
		}
			}
		
		    public void SelectBUs(String BusinessUnit, String ReportName) throws InterruptedException {
		    	
				String Reports=ReportName;
				String[] BusinessUnits = BusinessUnit.split(";");
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				SelectBusinessUnit.click();
				CommonSearchClick.click();
				for (int i = 0; i < BusinessUnits.length; i++) {
					if (driver.findElements(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+BusinessUnits[i]+"')])[1]")).size()!=0) {
			    		driver.findElement(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+BusinessUnits[i]+"')])[1]")).click();
					}
					else {
						Report.InfoTest(test, "Selected Business Unit is not dispalyed in the list when Business unit is" +BusinessUnits[i]+ "And Report is " + ReportName );
					}
							
				}
				driver.switchTo().defaultContent();
				Thread.sleep(1000);
			   this.GoBtn();
				 Thread.sleep(5000);
				 Util.selectFrame("opusReportContainer,subReportContainer");
				
				  switch (Reports) {
				  
				  case "Route Summary":
				 
				  try {
					   if (driver.findElements(By.xpath("//*[@id='dtRouteDetail']/tbody")).size()!=0) {
					List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtRouteDetail']/tbody/tr/td[contains(@id,'colBU_Row')]/span"));  
					    for (int j = 0; j <Cols.size(); j++) {
					    	String ActualData=Cols.get(j).getText();
					    	boolean FoundBU = false;
							  for(int ArrLp = 0; ArrLp <BusinessUnits.length ;ArrLp++  ) {
								String  ExpBU = BusinessUnits[ArrLp];
								if(ExpBU.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
									FoundBU = true;
									break;
								}									  
							  }
						if (FoundBU) {
							Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Actual Business Unit is >"  + ActualData.toString() +"  and Expected BU is > " + Arrays.asList(BusinessUnits).toString()  );
						}
						else {
							Report.FailTestSnapshot(test, driver,"Not Found Records in SubView Details when Report is "+ ReportName+" and Actual Business Unit is >"  + ActualData.toString() +"  and Expected BU is > " + Arrays.asList(BusinessUnits).toString(), "FailedRouteSummary");
						}
						}
						
					
					 }
					 else {
						
						Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
					}
				
			}
				  catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
				
				  break;
				  
				
				  case "Pre-Route Summary":
					  try {
						  
						 if (driver.findElements(By.xpath("//*[@id='dtPreRouteDetail']/tbody")).size()!=0) {
							List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtPreRouteDetail']/tbody/tr/td[contains(@id,'colBU')]/span"));
						    for (int j = 0; j <Cols.size(); j++) {
						    	String ActualData=Cols.get(j).getText();
						    	boolean FoundBU = false;
								  for(int ArrLp = 0; ArrLp <BusinessUnits.length ;ArrLp++  ) {
									String  ExpBU = BusinessUnits[ArrLp];
									if(ExpBU.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
										FoundBU = true;
										break;
									}									  
								  }
							if (FoundBU) {
								Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Actual Business Unit is >"  + ActualData.toString() +"  and Expected BU is > " + Arrays.asList(BusinessUnits).toString()  );
							}
							else {
								Report.FailTestSnapshot(test, driver,"Not Found Records in SubView Details when Report is "+ ReportName+" and Actual Business Unit is >"  + ActualData.toString() +"  and Expected BU is > " + Arrays.asList(BusinessUnits).toString(), "FailedRouteSummary");
							}
							}
							 }
						 else {
							Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
						}
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
				  
					  driver.switchTo().defaultContent();
					  
					  
					  break; 
			
				  case "Post-Route Summary":
					  try {
						  if (driver.findElements(By.xpath("//*[@id='dtPostRouteDetail']/tbody")).size()!=0) {					
						List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtPostRouteDetail']/tbody/tr/td[contains(@id,'colBU')]/span"));
								  for (int j = 0; j <Cols.size(); j++) {
									  String ActualData=Cols.get(j).getText();
									  boolean FoundBU = false;
									  for(int ArrLp = 0; ArrLp <BusinessUnits.length ;ArrLp++  ) {
										String  ExpBU = BusinessUnits[ArrLp];
										if(ExpBU.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
											FoundBU = true;
											break;
										}									  
									  }
								if (FoundBU) {
									Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Actual Business Unit is >"  + ActualData.toString() +"  and Expected BU is > " + Arrays.asList(BusinessUnits).toString()  );
								}
								else {
									Report.FailTestSnapshot(test, driver,"Not Found Records in SubView Details when Report is "+ ReportName+" and Actual Business Unit is >"  + ActualData.toString() +"  and Expected BU is > " + Arrays.asList(BusinessUnits).toString(), "FailedRouteSummary");
								}
								}
								
							
							 }
							 else {
								Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
					} 
					  }
					  catch (Exception e) {
		    	 			Report.FailTest(test, e.getMessage());
						}
					  driver.switchTo().defaultContent();
					  
					  
					  break; 
					
				  case "Idle Summary":
					  try {
						  if (driver.findElements(By.xpath("//*[@id='dtIdleTmDetail']/tbody")).size()!=0) {					
							List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtIdleTmDetail']/tbody/tr/td[contains(@id,'colBU')]/span"));
								  for (int j = 0; j <Cols.size(); j++) {
									  String ActualData=Cols.get(j).getText();
									  boolean FoundBU = false;
									  for(int ArrLp = 0; ArrLp <BusinessUnits.length ;ArrLp++  ) {
										String  ExpBU = BusinessUnits[ArrLp];
										if(ExpBU.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
											FoundBU = true;
											break;
										}									  
									  }
								if (FoundBU) {
									Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Actual Business Unit is >"  + ActualData.toString() +"  and Expected BU is > " + Arrays.asList(BusinessUnits).toString()  );
								}
								else {
									Report.FailTestSnapshot(test, driver,"Not Found Records in SubView Details when Report is "+ ReportName+" and Actual Business Unit is >"  + ActualData.toString() +"  and Expected BU is > " + Arrays.asList(BusinessUnits).toString(), "FailedRouteSummary");
								}
								}
											
							 }
							 else {
							
								Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
							}
						  
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
				  
					  driver.switchTo().defaultContent();
					  			  
					  break;
					
				  case "Disposal Cycle Summary":
					  try {
						  if (driver.findElements(By.xpath("//*[@id='dtDisposalTimeDetail']/tbody")).size()!=0) {					
						List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtDisposalTimeDetail']/tbody/tr/td[contains(@id,'colBU')]/span"));   
							   for (int j = 0; j <=Cols.size(); j++) {
								   String ActualData=Cols.get(j).getText();
								   boolean FoundBU = false;
									  for(int ArrLp = 0; ArrLp <BusinessUnits.length ;ArrLp++  ) {
										String  ExpBU = BusinessUnits[ArrLp];
										if(ExpBU.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
											FoundBU = true;
											break;
										}									  
									  }
								if (FoundBU) {
									Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Actual Business Unit is >"  + ActualData.toString() +"  and Expected BU is > " + Arrays.asList(BusinessUnits).toString()  );
								}
								else {
									Report.FailTestSnapshot(test, driver,"Not Found Records in SubView Details when Report is "+ ReportName+" and Actual Business Unit is >"  + ActualData.toString() +"  and Expected BU is > " + Arrays.asList(BusinessUnits).toString(), "FailedRouteSummary");
								}
								}
								
							}
							
							 else {
								
								Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
							}
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
					  driver.switchTo().defaultContent();
		  			  
					  break;
					  
				  case "Sequence Compliance Summary":
					  try {
						  if (driver.findElements(By.xpath("//*[@id='dtSeqComplianceDetail']/tbody")).size()!=0) {					
							 List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtSeqComplianceDetail']/tbody/tr/td[contains(@id,'colBU')]/span"));
							  for (int j = 0; j <Cols.size(); j++) {
								  String ActualData=Cols.get(j).getText();
								  boolean FoundBU = false;
								  for(int ArrLp = 0; ArrLp <BusinessUnits.length ;ArrLp++  ) {
									String  ExpBU = BusinessUnits[ArrLp];
									if(ExpBU.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
										FoundBU = true;
										break;
									}									  
								  }
							if (FoundBU) {
								Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Actual Business Unit is >"  + ActualData.toString() +"  and Expected BU is > " + Arrays.asList(BusinessUnits).toString()  );
							}
							else {
								Report.FailTestSnapshot(test, driver,"Not Found Records in SubView Details when Report is "+ ReportName+" and Actual Business Unit is >"  + ActualData.toString() +"  and Expected BU is > " + Arrays.asList(BusinessUnits).toString(), "FailedRouteSummary");
							}
								}
							 }
							 else {
								Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
							}
						  
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
					  driver.switchTo().defaultContent();
		  			  
					  break;
					  
				  case "Efficiency Summary":
					  try {
						  if (driver.findElements(By.xpath("//*[@id='dtEfficiencyDetail']/tbody")).size()!=0) {					
							 List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtEfficiencyDetail']/tbody/tr/td[contains(@id,'colBU')]/span"));
							  for (int j = 0; j <Cols.size(); j++) {
								  String ActualData=Cols.get(j).getText();
								  boolean FoundBU = false;
								  for(int ArrLp = 0; ArrLp <BusinessUnits.length ;ArrLp++  ) {
									String  ExpBU = BusinessUnits[ArrLp];
									if(ExpBU.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
										FoundBU = true;
										break;
									}									  
								  }
							if (FoundBU) {
								Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Actual Business Unit is >"  + ActualData.toString() +"  and Expected BU is > " + Arrays.asList(BusinessUnits).toString()  );
							}
							else {
								Report.FailTestSnapshot(test, driver,"Not Found Records in SubView Details when Report is "+ ReportName+" and Actual Business Unit is >"  + ActualData.toString() +"  and Expected BU is > " + Arrays.asList(BusinessUnits).toString(), "FailedRouteSummary");
							}
								}
							 }
							 else {
								Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
							}
						  
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
					  driver.switchTo().defaultContent();
		  			  
					  break;  
					  
					  
					  
					  
					  
					  
					  
					  
					  
					  
					  
					  
					  
					  
					  
				  case "Dispatch Summary":
					 			  
					Report.InfoTest(test, "Unable to found Area in Subview Details  when Report is "+ ReportName);
					break;
					
					
				  default:
			             throw new IllegalArgumentException("Invalid ReportName:  " + ReportName );
		}
		    	
		    	
		    	
		    	
		    }

		    public void SelectTiers(String Tier, String ReportName) throws InterruptedException {
		    	
		    	String Reports=ReportName;
				String[] Tiers = Tier.split(";");
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				SelectTier.click();
				EnterTier.click();
				for (int i = 0; i < Tiers.length; i++) {
					if (driver.findElements(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Tiers[i]+"')])[1]")).size()!=0) {
			    		driver.findElement(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Tiers[i]+"')])[1]")).click();
					}
					else {
						Report.InfoTest(test, "Tier is not displayed in the list when Ties is  " +Tiers[i]+ "And Report is " + ReportName);
					}
						
				}
				filterPopupClose.click();
				driver.switchTo().defaultContent();
				Thread.sleep(1000);
				this.GoBtn();
				 Thread.sleep(5000);
				Util.selectFrame("opusReportContainer,subReportContainer");
				
				
				  switch (Reports) {
				  
				  case "Route Summary":
				 
					  try {
						 					
						 if (driver.findElements(By.xpath("//*[@id='dtRouteDetail']/tbody")).size()!=0) {
						 List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtRouteDetail']/tbody/tr/td[contains(@id,'colTier_Row')]/span"));
						  for (int j = 0; j <Cols.size(); j++) {
							  String ActualData=Cols.get(j).getText();
							  boolean FoundTier = false;
							  for(int ArrLp = 0; ArrLp <Tiers.length ;ArrLp++  ) {
								String  ExpTiers = Tiers[ArrLp];
								if(ExpTiers.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
									FoundTier = true;
									break;
								}									  
							  }
							if (FoundTier) {
							Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Tier is >"  + ActualData.toString() +"  and Expected Tier is > " + Arrays.asList(Tiers).toString()  );
							}
							else {
								Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Tier is >"  + ActualData.toString() +"  and Expected Tier is > " + Arrays.asList(Tiers).toString(), "FailedSlectTiers");
							}
							}
											
						 }
						 else {
							Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
						}
					
					  }
					  catch (Exception e) {
		    	 			Report.FailTest(test, e.getMessage());
						}
					
					  break;
				  
				  
				  case "Pre-Route Summary":
					  try {
						  
						 if (driver.findElements(By.xpath("//*[@id='dtPreRouteDetail']/tbody")).size()!=0) {					
						  	List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtPreRouteDetail']/tbody/tr/td[contains(@id,'colGrp_Row')]/span"));		  
						   for (int j = 0; j <Cols.size(); j++) {
							   String ActualData=Cols.get(j).getText();
							   boolean FoundTier = false;
								  for(int ArrLp = 0; ArrLp <Tiers.length ;ArrLp++  ) {
									String  ExpTiers = Tiers[ArrLp];
									if(ExpTiers.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
										FoundTier = true;
										break;
									}									  
								  }
								if (FoundTier) {
								Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Tier is >"  + ActualData.toString() +"  and Expected Tier is > " + Arrays.asList(Tiers).toString()  );
								}
								else {
									Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Tier is >"  + ActualData.toString() +"  and Expected Tier is > " + Arrays.asList(Tiers).toString(), "FailedSlectTiers");
								}
							}
							
					
						 }
						 else {
							Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
						}
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
				  
					  driver.switchTo().defaultContent();
					  
					  
					  break; 
				  
					
				  case "Post-Route Summary":
					  try {
						  if (driver.findElements(By.xpath("//*[@id='dtPostRouteDetail']/tbody")).size()!=0) {					
							List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtPostRouteDetail']/tbody/tr/td[contains(@id,'colGrp_Row')]/span"));
								for (int j = 0; j <Cols.size(); j++) {
									String ActualData=Cols.get(j).getText();
									boolean FoundTier = false;
									  for(int ArrLp = 0; ArrLp <Tiers.length ;ArrLp++  ) {
										String  ExpTiers = Tiers[ArrLp];
										if(ExpTiers.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
											FoundTier = true;
											break;
										}									  
									  }
									if (FoundTier) {
									Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Tier is >"  + ActualData.toString() +"  and Expected Tier is > " + Arrays.asList(Tiers).toString()  );
									}
									else {
										Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Tier is >"  + ActualData.toString() +"  and Expected Tier is > " + Arrays.asList(Tiers).toString(), "FailedSlectTiers");
									}
								}
								
							
							 }
							 else {
								
								Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
					} 
					  }
					  catch (Exception e) {
		    	 			Report.FailTest(test, e.getMessage());
						}
					  driver.switchTo().defaultContent();
					  
					  
					  break; 
				  case "Idle Summary":
					  try {
						  if (driver.findElements(By.xpath("//*[@id='dtIdleTmDetail']/tbody")).size()!=0) {					
					List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtIdleTmDetail']/tbody/tr/td[contains(@id,'colGrp_Row')]/span"));   
							    for (int j = 0; j <Cols.size(); j++) {
							    	String ActualData=Cols.get(j).getText();
							    	boolean FoundTier = false;
									  for(int ArrLp = 0; ArrLp <Tiers.length ;ArrLp++  ) {
										String  ExpTiers = Tiers[ArrLp];
										if(ExpTiers.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
											FoundTier = true;
											break;
										}									  
									  }
									if (FoundTier) {
									Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Tier is >"  + ActualData.toString() +"  and Expected Tier is > " + Arrays.asList(Tiers).toString()  );
									}
									else {
										Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Tier is >"  + ActualData.toString() +"  and Expected Tier is > " + Arrays.asList(Tiers).toString(), "FailedSlectTiers");
									}
								}
								
							
							 }
							 else {
								
								Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
							}
						  
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
				  
					  driver.switchTo().defaultContent();
					  			  
					  break;
					  
				  case "Disposal Cycle Summary":
					  try {
						  if (driver.findElements(By.xpath("//*[@id='dtDisposalTimeDetail']/tbody")).size()!=0) {					
						List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtDisposalTimeDetail']/tbody/tr/td[contains(@id,'colGrp_Row')]/span")); 
							for (int j = 0; j <Cols.size(); j++) {
								String ActualData=Cols.get(j).getText();
								boolean FoundTier = false;
								  for(int ArrLp = 0; ArrLp <Tiers.length ;ArrLp++  ) {
									String  ExpTiers = Tiers[ArrLp];
									if(ExpTiers.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
										FoundTier = true;
										break;
									}									  
								  }
								if (FoundTier) {
								Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Tier is >"  + ActualData.toString() +"  and Expected Tier is > " + Arrays.asList(Tiers).toString()  );
								}
								else {
									Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Tier is >"  + ActualData.toString() +"  and Expected Tier is > " + Arrays.asList(Tiers).toString(), "FailedSlectTiers");
								}
								}
								
							
							 }
							 else {
								Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
							}
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
					  driver.switchTo().defaultContent();
		  			  
					  break;
					
				  case "Sequence Compliance Summary":
					  try {
						  if (driver.findElements(By.xpath("//*[@id='dtSeqComplianceDetail']/tbody")).size()!=0) {					
							List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtSeqComplianceDetail']/tbody/tr/td[contains(@id,'colGrp_Row')]/span"));    
							  for (int j = 0; j <Cols.size(); j++) {
								  String ActualData=Cols.get(j).getText();
								  boolean FoundTier = false;
								  for(int ArrLp = 0; ArrLp <Tiers.length ;ArrLp++  ) {
									String  ExpTiers = Tiers[ArrLp];
									if(ExpTiers.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
										FoundTier = true;
										break;
									}									  
								  }
								if (FoundTier) {
								Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Tier is >"  + ActualData.toString() +"  and Expected Tier is > " + Arrays.asList(Tiers).toString()  );
								}
								else {
									Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Tier is >"  + ActualData.toString() +"  and Expected Tier is > " + Arrays.asList(Tiers).toString(), "FailedSlectTiers");
								}
								}
								
							
							 }
							 else {
								
								Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
							}
						  
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
					  driver.switchTo().defaultContent();
		  			  
					  break;
					  
				  case "Efficiency Summary":
					  try {
						  if (driver.findElements(By.xpath("//*[@id='dtEfficiencyDetail']/tbody")).size()!=0) {					
							List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtEfficiencyDetail']/tbody/tr/td[contains(@id,'colGrp_Row')]/span"));    
							  for (int j = 0; j <Cols.size(); j++) {
								  String ActualData=Cols.get(j).getText();
								  boolean FoundTier = false;
								  for(int ArrLp = 0; ArrLp <Tiers.length ;ArrLp++  ) {
									String  ExpTiers = Tiers[ArrLp];
									if(ExpTiers.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
										FoundTier = true;
										break;
									}									  
								  }
								if (FoundTier) {
								Report.PassTest(test, "Found Records in SubView Details when Report is "+ ReportName+" and Tier is >"  + ActualData.toString() +"  and Expected Tier is > " + Arrays.asList(Tiers).toString()  );
								}
								else {
									Report.FailTestSnapshot(test, driver, "Not Found Records in SubView Details when Report is "+ ReportName+" and Tier is >"  + ActualData.toString() +"  and Expected Tier is > " + Arrays.asList(Tiers).toString(), "FailedSlectTiers");
								}
								}
								
							
							 }
							 else {
								
								Report.InfoTest(test, "Unable to found Any record please change search criteria when Report is "+ ReportName);
							}
						  
					} catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
					  driver.switchTo().defaultContent();
		  			  
					  break;  
				  case "Dispatch Summary":
					 			  
					Report.InfoTest(test, "Unable to found Tier in Subview Details when Report is "+ ReportName);
					break;
					
					
				  default:
			             throw new IllegalArgumentException("Invalid ReportName:  " + ReportName );
				  }
		  		    	
		    }
		
		    public void SelectDate(String StartDate, String EndDate, String ReportName) throws InterruptedException, ParseException {
		    	String Reports=ReportName;
		    	SimpleDateFormat fr=new SimpleDateFormat("MM/dd/yy");
		        Date Local1=fr.parse(StartDate);
		        Date Local2=fr.parse(EndDate);
		    	try {
		    		Util.switchToDefaultWindow();
		    		Util.selectFrame("opusReportContainer");
			    	FromDate.clear();
					FromDate.sendKeys(StartDate);
					ToDate.clear();
					ToDate.sendKeys(EndDate);
					Thread.sleep(2000);
					GoBtn.click();
					Thread.sleep(4000);
					driver.switchTo().defaultContent();
					Util.selectFrame("opusReportContainer,subReportContainer");
				     switch (Reports) {
					  
					  case "Pre-Route Detail":
					  case "Post-Route Detail":
					  case "Idle Detail":
					  case "Disposal Cycle Detail":
					  case "Downtime Detail":
					  case "Customer Property Detail":
					  case "Sequence Compliance Detail":
					  case "Efficiency Performance":	
						  try {
						  ALLTabLink.click();
						     Thread.sleep(2000);
						if (driver.findElements(By.xpath("//*[@id='t2']/tbody")).size()!=0) {
				  		List<WebElement>Cols=DetailTableIdle.findElements(By.tagName("td"));
				  		for (int i = 0; i <=0; i++) {
							for (int j = 0; j <=0; j++) {
							String ExpectedDate=Cols.get(j).getText();
							 Date ExpDate=fr.parse(ExpectedDate);
							 if (!ExpDate.before(Local1) && !ExpDate.after(Local2)){
								 Report.PassTest(test,"Data is available in Subview Details When Report is > " + ReportName+  "  and Date Range is >  " + StartDate + " & "+ EndDate);
							 }
							 else {
								 Report.FailTestSnapshot(test, driver, "Data is available in Subview Details When Report is > " + ReportName+  "  and Date Range is >  " + StartDate + " & " + EndDate, "FailedDateVerification");
							}     
				  				  			
						}
						   }
				  		
				  		  		
		                  	}
						else {
							 Report.InfoTest(test,"No Records available When Report is > " + ReportName+  "  and Date Range is >  " + StartDate + " & "+ EndDate );
						}
						
						  }
						  catch (Exception e) {
			    	 			Report.FailTest(test, e.getMessage());
							}
						
						  break;
						  
						  
						  case "Flash Report":
							  try {
							  if (driver.findElements(By.xpath("//*[@id='t2']/tbody")).size()!=0) {
							  		List<WebElement>Cols=DetailTableIdle.findElements(By.tagName("td"));
							  		for (int i = 0; i <=0; i++) {
									 for (int j = 0; j <=0; j++) {
										String ExpectedDate=Cols.get(j).getText();
										 Date ExpDate=fr.parse(ExpectedDate);
										 if (!ExpDate.before(Local1) && !ExpDate.after(Local2)){
											 Report.PassTest(test,"Data is available in Subview Details When Report is > " + ReportName+  "  and Date Range is >  " + StartDate + " & "+ EndDate);
										 }
										 else {
											 Report.FailTestSnapshot(test, driver, "Data is available in Subview Details When Report is > " + ReportName+  "  and Date Range is >  " + StartDate + " & " + EndDate, "FailedDateVerification");
										}     
							  			
							  			
									}
									   }
							  		
							  		  		
					                  	}  
							  else {
								  Report.InfoTest(test,"No Records available When Report is > " + ReportName+  "  and Date Range is >  " + StartDate + " & "+ EndDate );
							}
							 
							  }
							  catch (Exception e) {
				    	 			Report.FailTest(test, e.getMessage());
								}
							  break;
							  
							  
						  case "Pre-Route Summary":
						  case "Post-Route Summary":
						  case "Idle Summary":
						  case "Disposal Cycle Summary":
						  case "Sequence Compliance Summary": 
						  case "Dispatch Summary":
						  case "Fastlane Disposal":
						  case "Efficiency Summary":
						  case "District Dashboard - Beta":
						  case "Flash Report - Beta":
						  case "Route Summary":
							  try {
							  if (driver.findElements(By.xpath("//*[@id='cht001']/div[1]/div[1]/span")).size()!=0) {
								 String ExpDate=GraphTextHigh.getText();
								      Date ExpDates=fr.parse(ExpDate);
								      if (ExpDates.equals(Local1)) {
										Report.PassTest(test,"Data is available in Subview Details When Report is > " + ReportName+  "  and Date Range is >  " + StartDate + " & "+ EndDate);
									}
								      else {
										Report.FailTestSnapshot(test, driver, "Data is available in Subview Details When Report is > " + ReportName+  "  and Date Range is >  " + StartDate + " & " + EndDate, "FailedDateVerification");
									}
							}
							  else {
								  Report.InfoTest(test,"No Records available When Report is > " + ReportName+  "  and Date Range is >  " + StartDate + " & "+ EndDate );
							}
							
							  }
							  catch (Exception e) {
				    	 			Report.FailTest(test, e.getMessage());
								}
							  break; 
							 
						  default:
					             throw new IllegalArgumentException("Invalid ReportName:  " + ReportName ); 
							 
					     }
					
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
				}	  
					  
				  
		    }

			public void validateFilterPersist(String currentReportName, String switchToReportName) {
				
				Map<String,String> currentFilterValues=new HashMap<>();
				Map<String,String> switchToFilterValues=new HashMap<>();
				Util.switchToDefaultWindow();
				try
				{
					Report.PassTestScreenshot(test, driver, currentReportName+" Filter Values", currentReportName+" Filter Values");
					currentFilterValues=getFilterValues(currentReportName);
					Actions action = new Actions(driver);
					if(switchToReportName.equals("RM Dashboard") || switchToReportName.equals("Summary Dashboard") || switchToReportName.equals("Pre/Post/Idle Summary Dashboard"))

					{	
						Util.switchToDefaultWindow();
						action.moveToElement(dashboardMenu).perform();
						Util.openLinkInNewTab(switchToReportName);
						Util.switchToNewTab();
						Thread.sleep(5000);
					}
					else
					{
						Util.switchToDefaultWindow();
						action.moveToElement(performanceMenu).perform();
						Util.openLinkInNewTab(switchToReportName);
						Util.switchToNewTab();
						Thread.sleep(5000);
					}
					 Report.PassTestScreenshot(test, driver, switchToReportName+" Filter Values", switchToReportName+" Filter Values");
					 switchToFilterValues=getFilterValues(switchToReportName);
					 
					 for (Entry<String, String> entry : currentFilterValues.entrySet()) {
							if (switchToFilterValues.containsKey(entry.getKey())) {
								
								if(switchToFilterValues.get(entry.getKey()).equals(entry.getValue()))
								{
									Report.PassTest(test, entry.getKey()+" Filter Persist from "+currentReportName+" to "+switchToReportName+" with value : "+entry.getValue());
								}
								else
								{
									Report.FailTest(test, entry.getKey()+" Filter does not persist from "+currentReportName+" to "+switchToReportName+" with actual value : "+switchToFilterValues.get(entry.getKey())+ " and expected value : "+entry.getValue());
								}
								
							}
							else
							{
								Report.InfoTest(test, entry.getKey()+" Filter does not exist in  "+switchToReportName+" with actual value : "+entry.getValue());
							}
						}
					 
				driver.close();	 
				Util.switchToDefaultTab();	 
				}
				catch(Exception e)
				{
					
				}
				
			}

			public Map<String, String> getFilterValues(String ReportName) {
				
				Map<String,String> filterMap=new HashMap<>();
				
				
				try
				{
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
					if(ReportName.equals("Efficiency Summary") || ReportName.equals("Route Summary") || ReportName.equals("Pre-Route Summary") || ReportName.equals("Post-Route Summary") || ReportName.equals("Idle Summary") || ReportName.equals("Disposal Cycle Summary") || ReportName.equals("Sequence Compliance Summary") || ReportName.equals("Dispatch Summary") || ReportName.equals("Service Exceptions Summary"))
					{
						filterMap.put("Tier", tierFilterValue.getText());
						filterMap.put("Area", areaFilterValue.getText());
						filterMap.put("BU", BUFilterValue.getText());
						filterMap.put("Site", siteFilterValue.getText());
						if(ReportName.equals("Efficiency Summary") || ReportName.equals("Sequence Compliance Summary") || ReportName.equals("Dispatch Summary") || ReportName.equals("Service Exceptions Summary"))
						filterMap.put("LOB", LOBFilterValue.getText());
						else
						filterMap.put("LOB", multiLOBFilterValue.getText());	
						filterMap.put("SubLOB", subLOBFilterValue.getText());
						filterMap.put("FromDate", fromDatetextBox.getAttribute("value"));
						filterMap.put("ToDate", toDateTextBox.getAttribute("value"));						
					}
					
					else if(ReportName.equals("Summary Dashboard") || ReportName.equals("Pre/Post/Idle Summary Dashboard"))
					{
						filterMap.put("Tier", tierFilterValue.getText());
						filterMap.put("Area", areaFilterValue.getText());
						filterMap.put("BU", BUFilterValue.getText());
						filterMap.put("Site", siteFilterValue.getText());
						filterMap.put("FromDate", fromDatetextBox.getAttribute("value"));
						filterMap.put("ToDate", toDateTextBox.getAttribute("value"));
					}
					
					else if(ReportName.equals("Efficiency Performance") || ReportName.equals("Pre-Route Detail") || ReportName.equals("Post-Route Detail") || ReportName.equals("Idle Detail")  || ReportName.equals("Disposal Cycle Detail")  || ReportName.equals("Downtime Detail")  || ReportName.equals("Customer Property Detail")  || ReportName.equals("Sequence Compliance Detail")  || ReportName.equals("Flash Report") || ReportName.equals("Service Exceptions Detail"))
					{
						filterMap.put("Site", siteFilterValue.getText());
						if(ReportName.equals("Efficiency Performance") || ReportName.equals("Customer Property Detail") || ReportName.equals("Sequence Compliance Detail")  || ReportName.equals("Flash Report") || ReportName.equals("Service Exceptions Detail"))
						filterMap.put("LOB", LOBFilterValue.getText());						
						else												
						filterMap.put("LOB", multiLOBFilterValue.getText());
						filterMap.put("Route Manager", routeManagerFilterValue.getText());
						filterMap.put("Route Type", routeTypeFilterValue.getText());
						filterMap.put("Routes", routeFilterValue.getText());
						filterMap.put("Driver", driverFilterValue.getText());
						filterMap.put("Report Group", multiReportGroupFilterValue.getText());
						filterMap.put("FromDate", fromDatetextBox.getAttribute("value"));
						filterMap.put("ToDate", toDateTextBox.getAttribute("value"));	
					}
					
					else if(ReportName.equals("RM Dashboard"))
					{
						filterMap.put("Site", siteFilterValue.getText());
						filterMap.put("Route Manager", routeManagerFilterValue.getText());
						filterMap.put("Report Group", reportGroupFilterValue.getText());
						filterMap.put("ToDate", toDateTextBox.getAttribute("value"));
					}
					
					
				}
			
				catch(Exception e)
				{
					
				}
				return filterMap;	
			}

		
		    public void SelectSubLobs(String SubLob, String ReportName) throws InterruptedException {
		    	try { 
		    	Util.switchToDefaultWindow();
		    	 String[] SubLobs = SubLob.split(";");
		    	 String Reports=ReportName;
		    	 Util.selectFrame("opusReportContainer");
		    	 SubLobBtn.click();
		    	 CommonSearchClick.click();
		    	 Thread.sleep(2000);
		    	 for (int i = 0; i < SubLobs.length; i++) {
		    		 if (driver.findElements(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+SubLobs[i]+"')])[1]")).size()!=0) {
				    		driver.findElement(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+SubLobs[i]+"')])[1]")).click();
						}
		    		 else {
						Report.InfoTest(test, "SubLob is not dispalyed in the List when sub lob is " +SubLobs[i]+ "And Report is  " + ReportName );
					}
						    	 
               
    	 		}
		    	 filterPopupClose.click();
		    	 Thread.sleep(2000);
		    	 driver.switchTo().defaultContent();
					Thread.sleep(1000);
			     	 this.GoBtn();
					 Thread.sleep(5000);
					 Util.selectFrame("opusReportContainer,subReportContainer"); 		
				     
		    	 switch (Reports) {
		    	 
		    	 case "Pre-Route Detail":
		    	 case "Post-Route Detail":
		    	 case "Disposal Cycle Detail":
		    	 case "Customer Property Detail":
		    	 case "Sequence Compliance Detail":
		    		try {
		    		 ALLTabLink.click();
				     Thread.sleep(2000);
		    		 if (driver.findElements(By.xpath("//*[@id='t2']/tbody")).size()!=0) {
		    			  List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='t2']/tbody/tr/td[contains(@id,'colSubLOB_Row')]/span"));
					  		 for (int j = 0; j <Cols.size(); j++) {
							 String ActualData=Cols.get(j).getText();
								 						
								if(        (ActualData.equals("CMFL") && SubLob.equals("Commercial Frontload"))  
										|| (ActualData.equals("CMFY") && SubLob.equals("Commercial Frontload Recycling"))
										|| (ActualData.equals("CMRL") && SubLob.equals("Commercial Rearload"))
										|| (ActualData.equals("CMCD") && SubLob.equals("Container Delivery"))
									    || (ActualData.equals("RSRL") && SubLob.equals("Residential Rearload"))
									    || (ActualData.equals("RO") && SubLob.equals("Rolloff"))
									    || (ActualData.equals("CMSL") && SubLob.equals("Commercial Side Load"))
									    || (ActualData.equals("CMOR") && SubLob.equals("Commercial Organics")) )
								{
									
									Report.PassTest(test, "Found Records in Subview Details When Report is > " + ReportName +" and SubLob  is " + SubLob +  "and Expected SubLob  is > " + Arrays.asList(SubLob).toString());
								}
								else {
									Report.FailTestSnapshot(test, driver, "Not found records in Subview Details When Report is > " + ReportName +" and SubLob  is " + SubLob +  "and Expected SubLob  is > " + Arrays.asList(SubLob).toString(), "FailedSubLob");
								}	
								}
							  } 
		    		 else {
						Report.InfoTest(test,"Not Found any SubLob Please check search criteria, When Report is > " + ReportName);
					}
		    		}
		    		catch (Exception e) {
		    			Report.FailTest(test, e.getMessage());
					}
		    		 
		    		 break;
		    		 
		    	 case "Idle Detail":
		    	 case "Downtime Detail":
		    	 case "Efficiency Performance":
		    	 case "District Dashboard - Beta":
		    		try {
		    			if (driver.findElements(By.xpath("//*[@id='t2']/tbody")).size()!=0) {
			    			  List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='t2']/tbody/tr/td[contains(@id,'colSubLOB_Row')]/span"));
						  		 for (int j = 0; j <Cols.size(); j++) {
								 String ActualData=Cols.get(j).getText();
									 						
									if(        (ActualData.equals("CMFL") && SubLob.equals("Commercial Frontload"))  
											|| (ActualData.equals("CMFY") && SubLob.equals("Commercial Frontload Recycling"))
											|| (ActualData.equals("CMRL") && SubLob.equals("Commercial Rearload"))
											|| (ActualData.equals("CMCD") && SubLob.equals("Container Delivery"))
										    || (ActualData.equals("RSRL") && SubLob.equals("Residential Rearload"))
										    || (ActualData.equals("RO") && SubLob.equals("Rolloff"))
										    || (ActualData.equals("CMSL") && SubLob.equals("Commercial Side Load"))
										    || (ActualData.equals("CMOR") && SubLob.equals("Commercial Organics")) )
									{
										
										Report.PassTest(test, "Found Records in Subview Details When Report is > " + ReportName +" and SubLob  is " + SubLob +  "and Expected SubLob  is > " + Arrays.asList(SubLob).toString());
									}
									else {
										Report.FailTestSnapshot(test, driver, "Not found records in Subview Details When Report is > " + ReportName +" and SubLob  is " + SubLob +  "and Expected SubLob  is > " + Arrays.asList(SubLob).toString(), "FailedSubLob");
									}	
									}
								  } 
			    		 else {
							Report.InfoTest(test,"Not Found any SubLob Please check search criteria, When Report is > " + ReportName);
						}
		    			
					} catch (Exception e) {
						Report.FailTest(test, e.getMessage());
					}
		    		
		    		 break;	
		    		 
		    		 
		    	 case "Flash Report - Beta":
		    		 try {
			    			if (driver.findElements(By.xpath("//*[@id='dtScorecardSubview']/tbody")).size()!=0) {
				    			  List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtScorecardSubview']/tbody/tr/td[contains(@id,'colSubLOB_Row')]/span"));
							  		 for (int j = 0; j <Cols.size(); j++) {
									 String ActualData=Cols.get(j).getText();
										 						
										   if((ActualData.equals("CMFL") && SubLob.equals("Commercial Frontload"))  
												|| (ActualData.equals("CMFY") && SubLob.equals("Commercial Frontload Recycling"))
												|| (ActualData.equals("CMRL") && SubLob.equals("Commercial Rearload"))
												|| (ActualData.equals("CMCD") && SubLob.equals("Container Delivery"))
											    || (ActualData.equals("RSRL") && SubLob.equals("Residential Rearload"))
											    || (ActualData.equals("RO") && SubLob.equals("Rolloff"))
											    || (ActualData.equals("CMSL") && SubLob.equals("Commercial Side Load"))
											    || (ActualData.equals("CMOR") && SubLob.equals("Commercial Organics")) )
										{
											
											Report.PassTest(test, "Found Records in Subview Details When Report is > " + ReportName +" and SubLob  is " + SubLob +  "and Expected SubLob  is > " + Arrays.asList(SubLob).toString());
										}
										else {
											Report.FailTestSnapshot(test, driver, "Not found records in Subview Details When Report is > " + ReportName +" and SubLob  is " + SubLob +  "and Expected SubLob  is > " + Arrays.asList(SubLob).toString(), "FailedSubLob");
										}	
										}
									  } 
				    		 else {
								Report.InfoTest(test,"Not Found any SubLob Please check search criteria, When Report is > " + ReportName);
							}
			    			
						} catch (Exception e) {
							Report.FailTest(test, e.getMessage());
						}
		    		 break;	
		    		 
		    	 case "Efficiency Summary":
		    	 case "Route Summary":
		    	 case "Pre-Route Summary":
		    	 case "Post-Route Summary":
		    	 case "Idle Summary":
		    	 case "Disposal Cycle Summary":
		    	 case "Sequence Compliance Summary":	 
		    	 case "Dispatch Summary":	
		    		 
		    		 Report.InfoTest(test, "In Subview Section There has no column to validate SubLobs For Report >" + ReportName);
		    		 Util.switchToDefaultWindow();
		    		 break;
		    	 default:
		             throw new IllegalArgumentException("Invalid ReportName:  " + ReportName );		    		 
		    	 
		    	 }
		    	}
		    	catch (Exception e) {
    	 			Report.FailTest(test, e.getMessage());
				}
		    	 
		    	 
		    	 
		     }
		
		   public void SelectRouteType(String RouteType, String ReportName) {
			   String[] RouteTypes = RouteType.split(";");
		    	 String Reports=ReportName;
		    	 
  	 		try {    
  	 			Util.switchToDefaultWindow();
  	 			Util.selectFrame("opusReportContainer");
		    	 RouteTypeBtn.click();
		    	 CommonSearchClick.click();
		    	 Thread.sleep(2000);
		    	 for (int i = 0; i < RouteTypes.length; i++) {
		    		 if (driver.findElements(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+RouteTypes[i]+"')])[1]")).size()!=0) {
				    		driver.findElement(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+RouteTypes[i]+"')])[1]")).click();
						} 
		    		 else {
						Report.InfoTest(test, "Route Type is not Dispalyed in the list when Route Type is > " +RouteTypes[i]+ "and Report is " +ReportName );
					}
												
						
					}
		    	 driver.switchTo().defaultContent();
					Thread.sleep(1000);
			     	 this.GoBtn();
			    Thread.sleep(4000);
			    Util.selectFrame("opusReportContainer,subReportContainer");
			         
			    switch (Reports) {
			    case "Pre-Route Detail":
			    case "Post-Route Detail":
			    case "Disposal Cycle Detail":
			    case "Customer Property Detail":
			    case "Sequence Compliance Detail":
			    	try {
			    	ALLTabLink.click();
				    Thread.sleep(2000);
				    if (driver.findElements(By.xpath("//*[@id='t2']/tbody")).size()!=0) {
				   	List<WebElement> Cols1=driver.findElements(By.xpath("//*[@id='t2']/tbody/tr/td[contains(@id,'colRteType_Row')]/span"));
				    		for (int j = 0; j <Cols1.size(); j++) {
								String ActualData=Cols1.get(j).getText();
								String ExpData=RouteType.substring(0, 2);
							if (ExpData.trim().toUpperCase().equals(ActualData)) {
								Report.PassTest(test, "Route is displayed is Subview detail section when Report is > "+ ReportName +" and Route is " + RouteType +  "and Expected Route type  is > " + Arrays.asList(RouteTypes).toString());
								}
							else {
								Report.FailTestSnapshot(test, driver, "Route is Not displayed is Subview detail section when Report is > "+ ReportName +" and Route is " + RouteType +  "and Expected Route type  is > " + Arrays.asList(RouteTypes).toString(), "FailedRouteType");
							}
							}
							
						
				    }	
				    else {
				    	Report.InfoTest(test,"Please change search criteria for Report > " + ReportName);
					}
			    	
			    	}
			    	catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
			    	break;
			    				    	
			    	
			    case "Idle Detail":
			    case "Downtime Detail": 
			    	try {
			    		    if (driver.findElements(By.xpath("//*[@id='t2']/tbody")).size()!=0) {
			    		    	List<WebElement> Cols1=driver.findElements(By.xpath("//*[@id='t2']/tbody/tr/td[contains(@id,'colRteType_Row')]/span"));
						    		for (int j = 0; j <Cols1.size(); j++) {
										String ActualData=Cols1.get(j).getText();
									String ExpData=RouteType.substring(0, 2);
									if (ExpData.trim().toUpperCase().equals(ActualData)) {
										Report.PassTest(test, "Route is displayed is Subview detail section when Report is > "+ ReportName +" and Route is " + RouteType +  "and Expected Route type  is > " + Arrays.asList(RouteTypes).toString());
										}
									else {
										Report.FailTestSnapshot(test, driver, "Route is Not displayed is Subview detail section when Report is > "+ ReportName +" and Route is " + RouteType +  "and Expected Route type  is > " + Arrays.asList(RouteTypes).toString(), "FailedRouteType");
									}
									}
									
								
					    }
			    		    else {
						    	Report.InfoTest(test,"Please change search criteria for Report > " + ReportName);
							} 
				    	
			    	}
			    	catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
			    	
			    	break;
			   			    	
			    case "Efficiency Performance":
			    	
			    	try {
			    		ClickonHelperTab.click();
			    		Thread.sleep(2000);
		    		    if (driver.findElements(By.xpath("//*[@id='t2']/tbody")).size()!=0) {
		    		    	List<WebElement> Cols1=driver.findElements(By.xpath("//*[@id='t2h']/tbody/tr/td[contains(@id,'colRteType_Row')]/span"));
					    		for (int j = 0; j <Cols1.size(); j++) {
									String ActualData=Cols1.get(j).getText();
								String ExpData=RouteType.substring(0, 2);
								if (ExpData.trim().toUpperCase().equals(ActualData)) {
									Report.PassTest(test, "Route is displayed is Subview detail section when Report is > "+ ReportName +" and Route is " + RouteType +  "and Expected Route type  is > " + Arrays.asList(RouteTypes).toString());
									}
								else {
									Report.FailTestSnapshot(test, driver, "Route is Not displayed is Subview detail section when Report is > "+ ReportName +" and Route is " + RouteType +  "and Expected Route type  is > " + Arrays.asList(RouteTypes).toString(), "FailedRouteType");
								}
								}
								
							
				    }
		    		    else {
					    	Report.InfoTest(test,"Please change search criteria for Report > " + ReportName);
						} 
			    	
		    	}
		    	catch (Exception e) {
    	 			Report.FailTest(test, e.getMessage());
				}
		    	
		    	break;
			   	    	
			    	
			    	
			    case "District Dashboard - Beta":
			    case "Flash Report - Beta":
			    	Report.InfoTest(test, "Selected Route is Not Displayed in any column in Subview Details for Report >" + ReportName);
			    	break;
			    
			    default:
		             throw new IllegalArgumentException("Invalid ReportName:  " + ReportName );	
			    	
			    }
			    
			   
  	 		}
  	 		catch (Exception e) {
  	 			Report.FailTest(test, e.getMessage());
			}
		   }
		   
		   public void SelectDrivers(String Driver,String ReportName) {
			   String[] Drivers = Driver.split(";");
			   	 String Reports=ReportName; 
		    	 try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
		    		 SelectDriverBtn.click();
		    		 CommonSearchClick.click();
			    	 Thread.sleep(2000);
			    	 for (int i = 0; i < Drivers.length; i++) {
			    	if (driver.findElements(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Drivers[i]+"')])[1]")).size()!=0) {
			    		driver.findElement(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Drivers[i]+"')])[1]")).click();
					}
			    	else {
						Report.InfoTest(test, "Driver is not displayed in the list when driver is >"+Drivers[i]+ "and Report is " + ReportName );
					}
			    	
										}
			    	 Thread.sleep(2000);
			    	 driver.switchTo().defaultContent();
						Thread.sleep(1000);
				    this.GoBtn();
				    Thread.sleep(4000);
				    Util.selectFrame("opusReportContainer,subReportContainer");
				    				    
				    switch (Reports) {
				    
				    case "Pre-Route Detail":
				    case "Post-Route Detail":
				    case "Disposal Cycle Detail":
				    case "Customer Property Detail":
				    case "Sequence Compliance Detail":
				    try {
				    	ALLTabLink.click();
				    	Thread.sleep(2000);
				    	if (driver.findElements(By.xpath("//*[@id='t2']/tbody")).size()!=0) {
				    	List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='t2']/tbody/tr/td[contains(@id,'colDriver_Row')]/span"));
					    			for (int j = 0; j <Cols.size(); j++) {
									String ActualData=Cols.get(j).getText();
									boolean FoundDriver = false;
									  for(int ArrLp = 0; ArrLp <Drivers.length ;ArrLp++  ) {
										String  ExpLob = Drivers[ArrLp];
										if(ExpLob.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
											FoundDriver = true;
											break;
										}									  
									  }
									
									if(FoundDriver) {
										Report.PassTest(test,"Records available in Subview Details When Report is > " + ReportName +" and Driver is >"  + ActualData.toString() +"  and Expected Driver is > " + Arrays.asList(Drivers).toString());
									}
									else {
										Report.FailTestSnapshot(test, driver, "Records Not available in Subview Details When Report is > " + ReportName +"   and Driver is >"  + ActualData.toString() +"  and Expected Driver is > " + Arrays.asList(Drivers).toString(), "FailedDriver");
									}
								
								}
					    		
					    }
					    else {
							Report.InfoTest(test, "Please change search criteria for Report > " + ReportName);
						}
				    	
				    	
				    	
				    }
				    catch (Exception e) {
	    	 			Report.FailTest(test, e.getMessage());
					}
				    break;
				    
				    case "Idle Detail":
				    case "Downtime Detail":
				    case "Efficiency Performance":
				    	try {
				    	if (driver.findElements(By.xpath("//*[@id='t2']/tbody")).size()!=0) {
				    		List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='t2']/tbody/tr/td[contains(@id,'colDriver_Row')]/span"));
				    			for (int j = 0; j <Cols.size(); j++) {
								String ActualData=Cols.get(j).getText();
								boolean FoundDriver = false;
								  for(int ArrLp = 0; ArrLp <Drivers.length ;ArrLp++  ) {
									String  ExpLob = Drivers[ArrLp];
									if(ExpLob.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
										FoundDriver = true;
										break;
									}									  
								  }
								if(FoundDriver) {
									Report.PassTest(test,"Records available in Subview Details When Report is > " + ReportName +" and Driver is >"  + ActualData.toString() +"  and Expected Driver is > " + Arrays.asList(Drivers).toString());
								}
								else {
									Report.FailTestSnapshot(test, driver, "Records Not available in Subview Details When Report is > " + ReportName +"   and Driver is >"  + ActualData.toString() +"  and Expected Driver is > " + Arrays.asList(Drivers).toString(), "FailedDriver");
								}
							
							}
				    			
					    }
					    else {
							Report.InfoTest(test, "Please change search criteria for Report > " + ReportName);
						}
				    	
				    
				   
				    	}
				    	catch (Exception e) {
		    	 			Report.FailTest(test, e.getMessage());
						}
				    	
				    	 break;
				    case "District Dashboard - Beta":
				    	try {
				    		if (driver.findElements(By.xpath("//*[@id='t2']/tbody")).size()!=0) {
					    		List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='t2']/tbody/tr/td[contains(@id,'colDriver_Row')]/a/span"));
					    			for (int j = 0; j <Cols.size(); j++) {
									String ActualData=Cols.get(j).getText();
									boolean FoundDriver = false;
									  for(int ArrLp = 0; ArrLp <Drivers.length ;ArrLp++  ) {
										String  ExpLob = Drivers[ArrLp];
										if(ExpLob.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
											FoundDriver = true;
											break;
										}									  
									  }
									if(FoundDriver) {
										Report.PassTest(test,"Records available in Subview Details When Report is > " + ReportName +" and Driver is >"  + ActualData.toString() +"  and Expected Driver is > " + Arrays.asList(Drivers).toString());
									}
									else {
										Report.FailTestSnapshot(test, driver, "Records Not available in Subview Details When Report is > " + ReportName +"   and Driver is >"  + ActualData.toString() +"  and Expected Driver is > " + Arrays.asList(Drivers).toString(), "FailedDriver");
									}
								
								}
					    			
						    }
						    else {
								Report.InfoTest(test, "Please change search criteria for Report > " + ReportName);
							}    		
				    		
				    	
						} catch (Exception e) {
							Report.InfoTest(test, "Please change search criteria for Report > " + ReportName);
						}
				    	break;
				    	
				    	
				    case "Flash Report - Beta":
				    	try {
				    		if (driver.findElements(By.xpath("//*[@id='dtScorecardSubview']/tbody")).size()!=0) {
					    		List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='dtScorecardSubview']/tbody/tr/td[contains(@id,'colDriver_Row')]/a/span"));
					    			for (int j = 0; j <Cols.size(); j++) {
									String ActualData=Cols.get(j).getText();
									boolean FoundDriver = false;
									  for(int ArrLp = 0; ArrLp <Drivers.length ;ArrLp++  ) {
										String  ExpLob = Drivers[ArrLp];
										if(ExpLob.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
											FoundDriver = true;
											break;
										}									  
									  }
									if(FoundDriver) {
										Report.PassTest(test,"Records available in Subview Details When Report is > " + ReportName +" and Driver is >"  + ActualData.toString() +"  and Expected Driver is > " + Arrays.asList(Drivers).toString());
									}
									else {
										Report.FailTestSnapshot(test, driver, "Records Not available in Subview Details When Report is > " + ReportName +"   and Driver is >"  + ActualData.toString() +"  and Expected Driver is > " + Arrays.asList(Drivers).toString(), "FailedDriver");
									}
								
								}
					    			
						    }
						    else {
								Report.InfoTest(test, "Please change search criteria for Report > " + ReportName);
							}    		
				    		
				    	
						} catch (Exception e) {
							Report.InfoTest(test, "Please change search criteria for Report > " + ReportName);
						}
				    	
				    	break;
				    	
				    default:
			             throw new IllegalArgumentException("Invalid ReportName:  " + ReportName );
				    	
				        }
				    		    		 
				} 
		    	 catch (Exception e) {

				}
			   
		   }
		
		  
		   public void SelectRouteManagers(String RouteMgr, String ReportName) {
			   String[] RouteMgrs = RouteMgr.split(";");
			    String Reports=ReportName; 
		    	 try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
		    		 SelectRM.click();
		    		 CommonSearchClick.click();
		    		 Thread.sleep(2000);
			    	 for (int i = 0; i < RouteMgrs.length; i++) {
			    		 if (driver.findElements(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+RouteMgrs[i]+"')])[1]")).size()!=0) {
					    		driver.findElement(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+RouteMgrs[i]+"')])[1]")).click();
							}
			    		 else {
							Report.InfoTest(test, "Route Manager in not dispalyed in the list when Route Manager is > " +RouteMgrs[i]+ "and Report is " + ReportName );
						}
			    	  	
										}
			    	 Thread.sleep(2000);
			    	 driver.switchTo().defaultContent();
					Thread.sleep(1000);
				    this.GoBtn();
				    Thread.sleep(4000);
				    Util.selectFrame("opusReportContainer,subReportContainer");
				    
				    switch (Reports) {
				         
				    case "Pre-Route Detail":
				    case "Post-Route Detail":
				    case "Disposal Cycle Detail":
				    case "Customer Property Detail":
				    case "Sequence Compliance Detail":
				       	ALLTabLink.click();
				    	Thread.sleep(2000);
				    	if (driver.findElements(By.xpath("//*[@id='t2']/tbody")).size()!=0) {
				    		List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='t2']/tbody/tr/td[contains(@id,'colRteManager_Row')]/span"));
							 for (int j = 0; j < Cols.size(); j++) {
								String ActualData=Cols.get(j).getText();
								boolean FoundRM = false;
								  for(int ArrLp = 0; ArrLp <RouteMgrs.length ;ArrLp++  ) {
									String  ExpRM = RouteMgrs[ArrLp];
									if(ExpRM.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
										FoundRM = true;
										break;
									}									  
								  }							
								if(FoundRM) {
									Report.PassTest(test,"Records available in Subview Details When Report is > " + ReportName +" and Route Manager is >"  + ActualData.toString() +"  and Expected Route Manager is > " + Arrays.asList(RouteMgrs).toString());
								}
								else {
									Report.FailTestSnapshot(test, driver, "Records Not available in Subview Details When Report is > " + ReportName +"   and Route Manager is >"  + ActualData.toString() +"  and Expected Route Manager is > " + Arrays.asList(RouteMgrs).toString(), "FailedDriver");
								}
								
							}							 
							 }		    		
				    	
				    	else {
							Report.InfoTest(test,"Please change search criteria for Report > " + ReportName);
						}
				     break;
				     
				    case "Idle Detail":
				    case "Downtime Detail":
				    case "Efficiency Performance":
				    	Thread.sleep(2000);
				    	if (driver.findElements(By.xpath("//*[@id='t2']/tbody")).size()!=0) {
				    		List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='t2']/tbody/tr/td[contains(@id,'colRteManager_Row')]/span"));
							 for (int j = 0; j < Cols.size(); j++) {
								String ActualData=Cols.get(j).getText();
								boolean FoundRM = false;
								  for(int ArrLp = 0; ArrLp <RouteMgrs.length ;ArrLp++  ) {
									String  ExpRM = RouteMgrs[ArrLp];
									if(ExpRM.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
										FoundRM = true;
										break;
									}									  
								  }																
								if(FoundRM) 
								{
									Report.PassTest(test,"Records available in Subview Details When Report is > " + ReportName +" and Route Manager is >"  + ActualData.toString() +"  and Expected Route Manager is > " + Arrays.asList(RouteMgrs).toString());
								}
								else {
									Report.FailTestSnapshot(test, driver, "Records Not available in Subview Details When Report is > " + ReportName +"   and Route Manager is >"  + ActualData.toString() +"  and Expected Route Manager is > " + Arrays.asList(RouteMgrs).toString(), "FailedDriver");
								}
								
							}							 
							 }		    		
				    	
				    	else {
							Report.InfoTest(test,"Please change search criteria for Report > " + ReportName);
						}	
				    	
				    	
				    break;
				     
				    case "District Dashboard - Beta":
				    	SelectSegmentSummary.click();
				    	Thread.sleep(1000);
				    	if (driver.findElements(By.xpath("//*[@id='t3']/tbody")).size()!=0) {
						List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='t3']/tbody/tr/td[contains(@id,'colRteManager_Row')]/span"));
						for (int i = 0; i < Cols.size(); i++) {
							String ActualData=Cols.get(i).getText();
							boolean FoundRM = false;
							  for(int ArrLp = 0; ArrLp <RouteMgrs.length ;ArrLp++  ) {
								String  ExpRM = RouteMgrs[ArrLp];
								if(ExpRM.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
									FoundRM = true;
									break;
								}									  
							  }															
							if(FoundRM) 
							{
								Report.PassTest(test,"Records available in Subview Details When Report is > " + ReportName +" and Route Manager is >"  + ActualData.toString() +"  and Expected Route Manager is > " + Arrays.asList(RouteMgrs).toString());
							}
							else {
								Report.FailTestSnapshot(test, driver, "Records Not available in Subview Details When Report is > " + ReportName +"   and Route Manager is >"  + ActualData.toString() +"  and Expected Route Manager is > " + Arrays.asList(RouteMgrs).toString(), "FailedDriver");
							}	
						}		    	
				    	 	}
				    	
				    	else {
							Report.InfoTest(test,"Please change search criteria for Report > " + ReportName);
						}	
				    	break;
				    	   
				    case "Flash Report - Beta":
				    	Report.InfoTest(test,"Unable to Validate Route Manager When Report is > " + ReportName);
				    	break;
				    	
				    	
				    	
				    default:
			             throw new IllegalArgumentException("Invalid ReportName:  " + ReportName );
				    }
			           
			   
		    	 }
		    	 catch (Exception e) {
		    		 Report.FailTest(test, e.getMessage());
				}
		
		
		}
		
		   public void DownTimeDetailFilterValidation(String Truck, String Categorie, String Reason) {
			   String[] Trucks = Truck.split(";");
			   String[] Categories = Categorie.split(";");
			   String[] Reasons = Reason.split(";");
			   try {
				   Util.switchToDefaultWindow();
				   Util.selectFrame("opusReportContainer");
		    		 SelectTruck.click();
		    		 CommonSearchClick.click();
		    		 for (int i = 0; i < Trucks.length; i++) {
		    			 if (driver.findElements(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Trucks[i]+"')])[1]")).size()!=0) {
					    		driver.findElement(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Trucks[i]+"')])[1]")).click();
							}
		    			 else {
							Report.InfoTest(test, "Truck is not displayed in the list when Truck is > " +Trucks[i] );
						}
					}
		    		 Thread.sleep(2000);
		    		 SelectCategories.click();
		    		 CommonSearchClick.click();    		 
		    		 
		    		 for (int j = 0; j < Categories.length; j++) {
		    			 if (driver.findElements(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Categories[j]+"')])[1]")).size()!=0) {
					    		driver.findElement(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Categories[j]+"')])[1]")).click();
							}
		    			 else {
								Report.InfoTest(test, "Category is not displayed in the list when Category is > " + Categories[j] );
							}
		    			 
					}
		    		 Thread.sleep(2000);
		    		 SelectReason.click();
		    		 CommonSearchClick.click();		    		 
		    		 for (int k = 0; k < Reasons.length; k++) {
		    			 if (driver.findElements(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Reasons[k]+"')])[1]")).size()!=0) {
					    		driver.findElement(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Reasons[k]+"')])[1]")).click();
							}
		    			 else {
								Report.InfoTest(test, "Reason is not displayed in the list when Reason is > " +Reasons[k] );
							}
					}
		    		 Thread.sleep(2000);
		    		 Util.switchToDefaultWindow();
						Thread.sleep(1000);
					    this.GoBtn();
					    Thread.sleep(4000);
					    Util.selectFrame("opusReportContainer,subReportContainer");
                         if (driver.findElements(By.xpath("//*[@id='t2']/tbody")).size()!=0) {
                        	 SelectFirstDownTime.click();
                        	 Thread.sleep(5000);
                        	 Util.selectFrame("subIDownDetail_Row1");
                        	String ActualTruckId=GetFirstTruckId.getText();
                         String ActualCategory=GetFirstCategory.getText();
                         String ActualReason=GetFirstReason.getText();
                         if(Arrays.asList(Trucks).contains(ActualTruckId) && Arrays.asList(Categories).contains(ActualCategory) && Arrays.asList(Reasons).contains(ActualReason)) {
								Report.PassTest(test,"Records available in Subview Details When Report is DownTime Detail and Truck Id is >"  + ActualTruckId.toString() +"  and Expected Truck Id is > " + Arrays.asList(Trucks).toString()
										+ "Category is " + ActualCategory.toString() + "and Expected Category is >"+ Arrays.asList(Categories).toString() +  "   Reason is " + ActualReason.toString() + "and Expected Reason is " + Arrays.asList(Reasons).toString()  );
							}
							else {
								Report.FailTestSnapshot(test, driver,"No Records available in Subview Details When Report is DownTime Detail and Truck Id is >"  + ActualTruckId.toString() +"  and Expected Truck Id is > " + Arrays.asList(Trucks).toString()
										+ "Category is " + ActualCategory.toString() + "and Expected Category is >"+ Arrays.asList(Categories).toString() +  "   Reason is " + ActualReason.toString() + "and Expected Reason is " + Arrays.asList(Reasons).toString()   , "FailedDriver");
							}
                        	 
						}
					    
				
			} 
			   catch (Exception e) {
				Report.FailTest(test, e.getMessage());
			}
			   
			   
		   }

		   public void openReportInNewTab(String reportName) {
			try
			{
				
				Actions action = new Actions(driver);
				Util.switchToDefaultWindow();
				action.moveToElement(performanceMenu).perform();			
				Util.openLinkInNewTab(reportName);
				Util.switchToNewTab();
				Thread.sleep(5000);
			}
			catch(Exception e)
			{
				Report.FailTest(test, "Not able to switch to new tab");
			}
			
		}
		   
		   public void SelectRoute(String Route,String ReportName) {
			   try {
			   String Reports=ReportName;
				String[] Routes = Route.split(";");
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				//GetLOB Text Data for Flash report and Flash Report Beta
				String LOBTextData=GetLOBText.getText();
				RouteButton.click();
				CommonSearchClick.click();
				for (int i = 0; i < Routes.length; i++) {
					if (driver.findElements(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Routes[i]+"')])[1]")).size()!=0) {
			    		driver.findElement(By.xpath("(//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Routes[i]+"')])[1]")).click();
					}	
					else {
						Report.InfoTest(test, "Selected Route is not diaplyed in the List when Route is "+Routes[i]+ "and Report Name is " +ReportName );
					}	
				}
				driver.switchTo().defaultContent();
				Thread.sleep(1000);
			   this.GoBtn();
				 Thread.sleep(5000);
				 Util.selectFrame("opusReportContainer,subReportContainer");
			     
			     switch (Reports) {
			     
			     case "Efficiency Performance":
			     case "Idle Detail":
			     case "Downtime Detail":
			      try {
			    	  Thread.sleep(2000);
					    	if (driver.findElements(By.xpath("//*[@id='t2']/tbody")).size()!=0) {
					    	List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='t2']/tbody/tr/td[contains(@id,'colRte_Row')]/span"));
						    			for (int j = 0; j <Cols.size(); j++) {
										String ActualData=Cols.get(j).getText();
										boolean FoundRoute = false;
										  for(int ArrLp = 0; ArrLp <Routes.length ;ArrLp++  ) {
											String  ExpLob = Routes[ArrLp];
											if(ExpLob.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
												FoundRoute = true;
												break;
											}									  
										  }
										if(FoundRoute) {
											Report.PassTest(test,"Records available in Subview Details When Report is > " + ReportName +" and Actual Route is >"  + ActualData.toString() +"  and Expected Route is > " + Arrays.asList(Routes).toString());
										}
										else {
											Report.FailTestSnapshot(test, driver, "Records Not available in Subview Details When Report is > " + ReportName +"   and Actual Route is >"  + ActualData.toString() +"  and Expected Driver is > " + Arrays.asList(Routes).toString(), "FailedRoute");
										}
									
									}
						    		
						    }
						    else {
								Report.InfoTest(test, "Please change search criteria for Report > " + ReportName);
							} 
					    	 break;
			    		     		 
					} catch (Exception e) {
						Report.FailTest(test, e.getMessage());
					}
			    	 
			     case "Pre-Route Detail":
			     case "Post-Route Detail":
			     case "Disposal Cycle Detail":
			     case "Customer Property Detail":
			     case "Sequence Compliance Detail":
			    	 try {						
			    		 ALLTabLink.click();
			    		 Thread.sleep(2000);
					    	if (driver.findElements(By.xpath("//*[@id='t2']/tbody")).size()!=0) {
					    	List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='t2']/tbody/tr/td[contains(@id,'colRte_Row')]/span"));
						    			for (int j = 0; j <Cols.size(); j++) {
										String ActualData=Cols.get(j).getText();
										boolean FoundRoute = false;
										  for(int ArrLp = 0; ArrLp <Routes.length ;ArrLp++  ) {
											String  ExpLob = Routes[ArrLp];
											if(ExpLob.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
												FoundRoute = true;
												break;
											}									  
										  }
										if(FoundRoute) {
											Report.PassTest(test,"Records available in Subview Details When Report is > " + ReportName +" and Actual Route is >"  + ActualData.toString() +"  and Expected Route is > " + Arrays.asList(Routes).toString());
										}
										else {
											Report.FailTestSnapshot(test, driver, "Records Not available in Subview Details When Report is > " + ReportName +"   and Actual Route is >"  + ActualData.toString() +"  and Expected Driver is > " + Arrays.asList(Routes).toString(), "FailedRoute");
										}
									
									}
						    		
						    }
						    else {
								Report.InfoTest(test, "Please change search criteria for Report > " + ReportName);
							} 
					    	 break;
			    		  	
   		 		    		 
			    		 
					} catch (Exception e) {
						Report.FailTest(test, e.getMessage());
					}
			     case "Flash Report":
			     case "Flash Report - Beta": 
			    	 try {
			    		 Thread.sleep(2000);
					    	if (driver.findElements(By.xpath("//*[@id='t2']/tbody")).size()!=0) {
					    		if (LOBTextData.contains("Roll Off")) {
					    			List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='t2']/tbody/tr/td[contains(@id,'colRteRolloff_Row')]/a/span"));
										    	
						    			for (int j = 0; j <Cols.size(); j++) {
										String ActualData=Cols.get(j).getText();
										boolean FoundRoute = false;
										  for(int ArrLp = 0; ArrLp <Routes.length ;ArrLp++  ) {
											String  ExpLob = Routes[ArrLp];
											if(ExpLob.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
												FoundRoute = true;
												break;
											}									  
										  }
						    			
										if(FoundRoute) {
											Report.PassTest(test,"Records available in Subview Details When Report is > " + ReportName +" and Actual Route is >"  + ActualData.toString() +"  and Expected Route is > " + Arrays.asList(Routes).toString());
										}
										else {
											Report.FailTestSnapshot(test, driver, "Records Not available in Subview Details When Report is > " + ReportName +"   and Actual Route is >"  + ActualData.toString() +"  and Expected Driver is > " + Arrays.asList(Routes).toString(), "FailedRoute");
										}
									
									}
					    		}
					    		else {
									Report.InfoTest(test, "Selected LOb is not diaplayed in the list" + LOBTextData);
								}
					    		
					    		if (LOBTextData.contains("Commercial") || LOBTextData.contains("Residential")) {
					    			List<WebElement>Cols=driver.findElements(By.xpath("//*[@id='t2']/tbody/tr/td[contains(@id,'colRte_Row')]/span"));
										    	
						    			for (int j = 0; j <Cols.size(); j++) {
										String ActualData=Cols.get(j).getText();
										boolean FoundRoute = false;
										  for(int ArrLp = 0; ArrLp <Routes.length ;ArrLp++  ) {
											String  ExpLob = Routes[ArrLp];
											if(ExpLob.toUpperCase().trim().contains(ActualData.trim().toUpperCase())) {
												FoundRoute = true;
												break;
											}									  
										  }
						    			
										if(FoundRoute) {
											Report.PassTest(test,"Records available in Subview Details When Report is > " + ReportName +" and Actual Route is >"  + ActualData.toString() +"  and Expected Route is > " + Arrays.asList(Routes).toString());
										}
										else {
											Report.FailTestSnapshot(test, driver, "Records Not available in Subview Details When Report is > " + ReportName +"   and Actual Route is >"  + ActualData.toString() +"  and Expected Driver is > " + Arrays.asList(Routes).toString(), "FailedRoute");
										}
									
									}
					    		}
					    		else {
									Report.InfoTest(test, "Selected LOb is not diaplayed in the list" + LOBTextData);
								}
					    		
					    		
						    		
						    }
						    else {
								Report.InfoTest(test, "Please change search criteria for Report > " + ReportName);
							}	 
			    		   		 
			    		 break;	
					} catch (Exception e) {
						Report.FailTest(test, e.getMessage());
					}
			    	 
			    	     
			     }
			     
			   }
			   
			   catch (Exception e) {
				   Report.FailTest(test, e.getMessage());
				
			}
			     
			   
		   }

		   
		   /**
		    * Select filter criteria based on the the parameters.
		    * Pass "" if you don't want to select particular filter option.
		    *  
		    *  @author kpatel7
		    * 
		    * @param
		    * 
		    * @return void
		    * 
		    * @throws none
		    * 
		    * @ImplementedDate 12/25/2017
		    * 
		    * @LastModifiedDate N/A
		    * 
		    */  
		   
		public void selectFilterCriteria(String siteID,String multiSite, String singleLOB, String multiLOB, String route, String driverName,
				String fromDate, String toDate) {
			
			try
			{
				if(!siteID.equals("")){
					
					selectSite(siteID);
				}
               /* if(!multiSite.equals("")){
					
					selectMultiSite(siteID);
				}*/
				if(!singleLOB.equals("")){
									
					selectLOB(singleLOB);
								}
				if(!multiLOB.equals("")){
					
					selectMultiLOB(multiLOB);
				}
				if(!route.equals("")){
					
					selectRoute(route);
				}
				if(!driverName.equals("")){
					
					selectDriver(driverName);
				}
				if(!fromDate.equals("")){
					
					selectFromDate(fromDate);
				}
				if(!toDate.equals("")){
					
					selectToDate(toDate);
				}
				
				
				selectGO();
		
				
			}catch(Exception e)
			{
				Report.FailTest(test, "Not able to select the filter option with following filter criteria : Site : "+siteID+"Single LOB : "+ singleLOB+
						" Multi LOB : "+multiLOB+" Route Name : "+route+" Driver Name : "+driverName+" From Date : "+fromDate+" To Date : "+toDate);
			}
			
			
			
		}
		   
		   public void SelectMutipleSites(String SiteID, String ReportName)
		   {
			   try
			   {
			   	String[] Sites = SiteID.split(";");
				
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				SiteFilter.click();
				for (int i = 0; i < Sites.length; i++) {
					if (driver.findElements(
				By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).size()!=0) {
						driver.findElement(
								By.xpath("//*[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span[contains(text(),'"+Sites[i]+"')]")).click();
					}
					else {
						Report.InfoTest(test, "Selected Site is not displayed in the List when site is " +Sites[i]+ "and Report is " +ReportName );
					}
					
				}
				driver.switchTo().defaultContent();
				Thread.sleep(1000);
			   }
			   catch (Exception e)
			   {
				   System.out.println(e.getMessage()); 
			   }
		   }
}