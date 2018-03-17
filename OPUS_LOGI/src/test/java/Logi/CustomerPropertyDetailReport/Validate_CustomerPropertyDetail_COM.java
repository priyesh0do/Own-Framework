package Logi.CustomerPropertyDetailReport;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.CustomerPropertyDetailReport;
import ObjectRepository.Logi.IdleDetailReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class Validate_CustomerPropertyDetail_COM extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	CustomerPropertyDetailReport CustPropDetailObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_CustomerPropertyDetail_COM(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		if(Scenario.equalsIgnoreCase("Validate_CustomerPropertyDetail_COM") & RunMode.equals("Yes"))
		{
			   test = Report.testCreate(extent, test, "Validate_CustomerPropertyDetail_COM");
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
				sdoFilterObj.selectLOB(LOB);
				sdoFilterObj.selectFromDate(FromDate);
				sdoFilterObj.selectToDate(ToDate);
				sdoFilterObj.selectRoute(Route);
				sdoFilterObj.selectGO();
				CustPropDetailObj=new CustomerPropertyDetailReport(driver, test);
				Util.pageScroll(0, 250);
				// Switch to frame
				Util.selectFrame("opusReportContainer,subReportContainer");	
				Map<String, List<String>> ActualSummaryData=CustPropDetailObj.CustomerPropertyDetailReportSummaryData();
				Map<String, List<String>> ActualDetailDataExceptionTab=CustPropDetailObj.CustomerPropertyDetailReportDetailDataExceptionsTab();
				Map<String, Map<String, List<String>>>CustomerPropertyTimeDrillDownDataExceptionsTabData=CustPropDetailObj.CustomerPropertyTimeDrillDownDataExceptionsTab(Route);
				Util.selectFrame("opusReportContainer,subReportContainer");	
				CustPropDetailObj.RedirectedToAllTab();
				Map<String, List<String>> ActualDetailDataAllTab=CustPropDetailObj.CustomerPropertyDetailReportDetailDataAllTab();
				Map<String, Map<String, List<String>>>CustomerPropertyTimeDrillDownDataAllTabData=CustPropDetailObj.CustomerPropertyTimeDrillDownDataAllTab(Route);
				CustPropDetailObj.ValidateCustomerPropertyDetailReport_SummaryTabledata(ActualSummaryData, ActualDetailDataExceptionTab, CustomerPropertyTimeDrillDownDataExceptionsTabData, ActualDetailDataAllTab);  				
				CustPropDetailObj.ValidateCustomerPropertyDetailReport_DetailTabledataExceptionTab(ActualDetailDataExceptionTab, CustomerPropertyTimeDrillDownDataExceptionsTabData, FromDate, ToDate, Route, Driver, LOB);
				CustPropDetailObj.ValidateCustomerPropertyDetailReport_DetailTabledataAllTab(ActualDetailDataAllTab, CustomerPropertyTimeDrillDownDataAllTabData, FromDate, ToDate, Route, Driver, LOB);
				CustPropDetailObj.ValidateCustomerPropertyDetailReport_DrillDownDataAllTab(ActualDetailDataAllTab, CustomerPropertyTimeDrillDownDataAllTabData);
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
