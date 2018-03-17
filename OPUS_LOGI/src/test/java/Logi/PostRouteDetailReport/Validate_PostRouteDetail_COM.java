package Logi.PostRouteDetailReport;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.PostRouteDetailReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class Validate_PostRouteDetail_COM extends BaseClass{
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	PostRouteDetailReport PostroutedetailObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_PostRouteDetailReport_DataValidation_COM(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {
		
		if(Scenario.equalsIgnoreCase("Validate_PostRouteDetailReport_COM") & RunMode.equals("Yes"))
		{
			test = Report.testCreate(extent, test, "Validate_PostRouteDetailReport_COM");
		    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			// Select RM Dashboard
			sdiMegaObj = new HomePage(driver, test);
			sdiMegaObj.ClickOnReport(ReportName);
			sdoFilterObj = new FilterSection(driver, test);
			// select site
			sdoFilterObj.selectSite(SiteID);
			sdoFilterObj.selectMultiLOB(LOB);		
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			PostroutedetailObj= new PostRouteDetailReport(driver, test);
			Util.pageScroll(0, 250);
			Util.selectFrame("opusReportContainer,subReportContainer");
			Map<String, List<String>> PostRouteSummaryTableData=PostroutedetailObj.getPostRouteSummaryTableData();
			Map<String, List<String>> PostRouteDetailTableDataExceptionTab=PostroutedetailObj.getPostRouteDetailTableDataExceptionTab();
			Map<String, List<String>> PostRouteDetailTableDataExceptionTabActualSubview=PostroutedetailObj.getPostRouteDetailTableDataExceptionTabActualSubView();
			PostroutedetailObj.RedirectedtoAllTab();
			Map<String, List<String>> PostRouteDetailTableDataAllTab=PostroutedetailObj.getPostRouteDetailTableDataAllTab();
			Map<String, List<String>> PostRouteDetailTableDataAllTabActualSubview=PostroutedetailObj.getPostRouteDetailTableDataAllTabActualSubview();
			PostroutedetailObj.RedirectedtoExceptionTab();
			PostroutedetailObj.ValidatePostRouteSummaryData(PostRouteSummaryTableData, PostRouteDetailTableDataExceptionTab,PostRouteDetailTableDataAllTab);
			PostroutedetailObj.ValidateExceptionTabPerformanceSubviewData(PostRouteDetailTableDataExceptionTab, FromDate, ToDate, Route, Driver, LOB);
			PostroutedetailObj.ValidateExceptionTabActualSubviewData(PostRouteDetailTableDataExceptionTabActualSubview, FromDate, ToDate, Route, Driver, LOB, ClockOutTime);
			PostroutedetailObj.ValidateAllTabPerformanceSubViewData(PostRouteDetailTableDataAllTab, FromDate, ToDate, Route, Driver, LOB);
			PostroutedetailObj.ValidateAllTabActualSubViewData(PostRouteDetailTableDataAllTabActualSubview, FromDate, ToDate, Route, Driver, LOB, ClockOutTime);
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
