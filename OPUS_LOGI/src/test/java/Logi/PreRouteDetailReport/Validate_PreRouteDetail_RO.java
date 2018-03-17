package Logi.PreRouteDetailReport;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.PreRouteDetailReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class Validate_PreRouteDetail_RO extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	PreRouteDetailReport PerroutedetailObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_PreRouteDetailReport_DataValidation_RO(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {
		
		if(Scenario.equalsIgnoreCase("Validate_PreRouteDetailReport_RO") & RunMode.equals("Yes"))
		{
			test = Report.testCreate(extent, test, "Validate_PreRouteDetailReport_RO");
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
			sdoFilterObj.selectSubLOB("Sub LOBs incl B, O"); 
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			PerroutedetailObj=new PreRouteDetailReport(driver, test);
			Util.pageScroll(0, 250);
			Util.selectFrame("opusReportContainer,subReportContainer");
			Map<String, List<String>> actualPreRouteSummaryTableData=PerroutedetailObj.getActualPreRouteSummaryTableData();
			Map<String, List<String>> actualPreRouteDetailTableData=PerroutedetailObj.getActualPreRouteDetailTableData();
			PerroutedetailObj.RedirectedtoActualSubview();
			Map<String, List<String>> actualPreRouteDetailTableDataActualSubview=PerroutedetailObj.PreRouteSummaryDetailTableDataActualSubview();
			PerroutedetailObj.RedirectedToPerformanceSubview();
			PerroutedetailObj.RedirectedtoAllTab();
			Map<String, List<String>> getActualPreRouteDetailTableDataAllTab=PerroutedetailObj.getActualPreRouteDetailTableDataAllTab();
			PerroutedetailObj.RedirectedtoActualSubviewAllTab();
			Map<String, List<String>> getActualPreRouteDetailTableDataAllTabActualSubview=PerroutedetailObj.getActualPreRouteDetailTableDataAllTabActualSubview();
			PerroutedetailObj.RedirectedtoPerformanceSubviewAllTab();
			PerroutedetailObj.RedirectedtoExceptionsTab();
			PerroutedetailObj.validateRouteSummaryData(actualPreRouteSummaryTableData, actualPreRouteDetailTableData,getActualPreRouteDetailTableDataAllTab,LOB);
			PerroutedetailObj.ValidatePreRouteDetailData(actualPreRouteDetailTableData,getActualPreRouteDetailTableDataAllTab,actualPreRouteDetailTableDataActualSubview,getActualPreRouteDetailTableDataAllTabActualSubview,FromDate, ToDate,Route,Driver,LOB,ClockInTime);
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
