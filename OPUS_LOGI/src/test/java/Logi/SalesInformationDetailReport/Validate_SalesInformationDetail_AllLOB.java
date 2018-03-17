package Logi.SalesInformationDetailReport;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.SlaesInformationDetailReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class Validate_SalesInformationDetail_AllLOB extends BaseClass{

	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	SlaesInformationDetailReport salesInformationDetailObj;
	
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_SalesInformationDetailReport_AllLOB(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		if(Scenario.equalsIgnoreCase("Validate_SalesInformationDetail_AllLOB") & RunMode.equals("Yes"))
		{
			    test = Report.testCreate(extent, test, "Validate_SalesInformationDetail_AllLOB");
			    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
				login = new Login(driver, test);
				login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
				System.out.println("Login successfully done");
				// Select RM Dashboard
				sdiMegaObj = new HomePage(driver, test);
				sdiMegaObj.ClickOnReport(ReportName);
				sdoFilterObj = new FilterSection(driver, test);
				// select site
				String DriverComment="(Sales) Sales Opportunity/Lead.";
				sdoFilterObj.selectSite(SiteID);
				sdoFilterObj.selectMultiLOB(LOB);
				sdoFilterObj.selectFromDate(FromDate);
				sdoFilterObj.selectToDate(ToDate);
				sdoFilterObj.selectDriverComment(DriverComment);
				sdoFilterObj.selectGO();
				salesInformationDetailObj=new SlaesInformationDetailReport(driver, test);
				Util.pageScroll(0, 250);
				Util.selectFrame("opusReportContainer,subReportContainer");
				Map<String, List<String>> actualDetailTableData=salesInformationDetailObj.getActualSalesInformationDetailTableData();
				salesInformationDetailObj.ValidateDetailTableData(actualDetailTableData, FromDate, ToDate, Route, Driver, LOB,DriverComment);
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
