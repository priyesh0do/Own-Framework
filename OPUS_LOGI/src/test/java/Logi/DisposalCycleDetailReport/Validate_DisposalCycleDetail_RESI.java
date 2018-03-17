package Logi.DisposalCycleDetailReport;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.DisposalCycleDetailReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class Validate_DisposalCycleDetail_RESI extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	DisposalCycleDetailReport dispCycleDetailObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_DisposalCycleDetailReport_RESI(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		if(Scenario.equalsIgnoreCase("Validate_DisposalCycleDetailReport_RESI") & RunMode.equals("Yes"))
		{

		    test = Report.testCreate(extent, test, "Validate_DisposalCycleDetailReport_RESI");
		    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));		
		    login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			sdiMegaObj = new HomePage(driver, test);
			sdoFilterObj = new FilterSection(driver, test);
			dispCycleDetailObj = new DisposalCycleDetailReport(driver, test);
			// Select Report			
			sdiMegaObj.ClickOnReport(ReportName);
			
			//Select filter criteria for Validation
			sdoFilterObj.selectFilterCriteria(SiteID,"","",LOB,Route,Driver,FromDate,ToDate);
			
			Util.pageScroll(0, 500);
			
			//Summary Table Data
			Map<String, List<String>> actualSummaryTable = dispCycleDetailObj.getActualDisposalCycleSummaryTableData();	
			
			//Exception Tab Performance SubView Data
			Map<String, List<String>> actualDetailExceptionsTabPerformanceSubView = dispCycleDetailObj.getActualDisposalCycleDetailExceptionsTabTableData();
			
			//Disposal Time Drilldown of Exception tab Performance Subview Data
			Map<String,Map<String, List<String>>> actualDisposalTimeDrilldownExceptionsTabPerformanceSubView =dispCycleDetailObj.getActualDisposalTimeExceptionTabDrilldownTableData(Route);
				
			// Switch To Actual SubView
			dispCycleDetailObj.clickOnActualSubViewExceptionTab();
			
			Util.wait_inMinutes(0.1);
			
			//Exception Tab Actual SubView Data
			Map<String, List<String>> actualDetailExceptionsTabActualSubView = dispCycleDetailObj.getActualDisposalCycleDetailExceptionsTabTableData();
			
			//Disposal Time Drilldown Exception tab Actual SubView Data
			Map<String,Map<String, List<String>>> actualDisposalTimeDrilldownExceptionsTabActualSubView =dispCycleDetailObj.getActualDisposalTimeExceptionTabDrilldownTableData(Route);
			
			//Click on All tab
			dispCycleDetailObj.clickOnAllTab();
			
			Util.pageScroll(0, 500);
			
			//All Tab Performance SubView Data
			Map<String, List<String>> actualDetailAllTabPerformanceSubView = dispCycleDetailObj.getActualDisposalCycleDetailAllTabTableData();
			
			//Disposal Time Drilldown of All tab Performance Subview Data
			Map<String,Map<String, List<String>>> actualDisposalTimeDrilldownAllTabPerformanceSubView =dispCycleDetailObj.getActualDisposalTimeAllTabDrilldownTableData(Route);
			
			// Switch To Actual SubView
			dispCycleDetailObj.clickOnActualSubView();
			
			//All Tab Actual SubView Data
			Map<String, List<String>> actualDetailAllTabActualSubView = dispCycleDetailObj.getActualDisposalCycleDetailAllTabTableData();
			
			//Disposal Time Drilldown All tab Actual SubView Data
			Map<String,Map<String, List<String>>> actualDisposalTimeDrilldownAllTabActualSubView =dispCycleDetailObj.getActualDisposalTimeAllTabDrilldownTableData(Route);
			
			
			//Validate Summary Data
			dispCycleDetailObj.validateSummaryData(actualSummaryTable,actualDetailExceptionsTabPerformanceSubView,actualDetailAllTabPerformanceSubView,actualDisposalTimeDrilldownExceptionsTabPerformanceSubView,actualDisposalTimeDrilldownAllTabPerformanceSubView);
			
			//Validate Detail Data
			dispCycleDetailObj.validateDetailData(actualDetailExceptionsTabPerformanceSubView,actualDetailExceptionsTabActualSubView,
					actualDetailAllTabPerformanceSubView,actualDetailAllTabActualSubView,actualDisposalTimeDrilldownExceptionsTabPerformanceSubView,
					actualDisposalTimeDrilldownExceptionsTabActualSubView,actualDisposalTimeDrilldownAllTabPerformanceSubView,
					actualDisposalTimeDrilldownAllTabActualSubView,
					SiteID,LOB, Route, Driver, FromDate, ToDate);
			
			//Validate Drilldown Data
			dispCycleDetailObj.validateDisposalTimeDrilldownData(actualDetailExceptionsTabPerformanceSubView,actualDetailExceptionsTabActualSubView,
			actualDetailAllTabPerformanceSubView,actualDetailAllTabActualSubView,actualDisposalTimeDrilldownExceptionsTabPerformanceSubView,
			actualDisposalTimeDrilldownExceptionsTabActualSubView,actualDisposalTimeDrilldownAllTabPerformanceSubView,
			actualDisposalTimeDrilldownAllTabActualSubView,SiteID,LOB, Route, Driver, FromDate, ToDate);
			
			
			
			
			driver.quit();
		 }	
		}
	@DataProvider(name = "LOGI_Reports_Data")
	public Object[][] testData() throws Exception {
		Excel ex = new Excel();
		Object[][] arrayObject = ex.getTableArray(configProp.getProperty("LOGITestCaseExcel"),
				configProp.getProperty("LOGITestDataSheet"));
		return arrayObject;
	}

}
