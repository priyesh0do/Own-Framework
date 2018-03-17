package SDO.DataValidation;

import java.util.List;
import java.util.Map;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.SDO.RollOffFlashReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalTestData;

public class VerifyTheDataInTheColumnsOfAccordionInFlashReportWithSiteDetail_RO extends BaseClass {

	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	RollOffFlashReport sdoRollOffObj;
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify Data of accordion for Roll off flash report
	 * 
	 */
	
	@Test(groups = "Opus", dataProvider = "rollOffFlashReport")
	public void OpusSDO_AccordionFlashReportWithSiteDetail(String ReportName, String siteID, String LOB, String FromDate,
			String ToDate, String Route, String Driver,String Ticket) throws Exception {	
		if (!Util.isTestCaseExecutable("Verify the data in the columns of Accordion in Flash Report with Site detail_RO",GlobalVariables.sdoTestCase))
		{
			test = Report.testCreate(extent, test, "Verify the data in the columns of Accordion in Flash Report with Site detail_RO");
			Report.SkipTest(test, "Skipping the test as runmode is NO");			
		}
		else
		{
		    test = Report.testCreate(extent, test, "Verify the data in the columns of Accordion in Flash Report with Site detail_RO");
		    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			// Select RM Dashboard
			sdiMegaObj = new HomePage(driver, test);
			sdiMegaObj.ClickOnReport(GlobalTestData.RMDashBoardReport);
			sdoRollOffObj = new RollOffFlashReport(driver, test);
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
			// Click on Roll Off Flash report
			sdoRollOffObj.clickRollOffFlashReport();
			sdoFilterObj = new FilterSection(driver, test);
			// select site
			sdoFilterObj.selectSite(siteID);
			//Select LOB
			sdoFilterObj.selectLOB(LOB);
			// Select Route
			sdoFilterObj.selectRoute(Route);
			// Select Driver
			sdoFilterObj.selectDriver(Driver);
			// Select Date Range
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			Util.pageScroll(0, 250);
			Map<String,Map<String, List<String>>> actualTicketInformationTable =sdoRollOffObj.getActualTicketInformationTableData(Route);			
			Map<String,Map<String, List<String>>> actualServiceInformationTable =sdoRollOffObj.getActualServiceInformation(Route);
			Thread.sleep(2000);
			sdoRollOffObj.validateTicketInformationData(actualTicketInformationTable,Ticket);
			driver.quit();
		}
	}

	@DataProvider(name = "rollOffFlashReport")
	public Object[][] testData() throws Exception {
		Excel ex = new Excel();
		Object[][] arrayObject = ex.getTableArray(configProp.getProperty("SDOTestCaseExcel"),
				configProp.getProperty("SDOTestDataSheetRO"));
		return arrayObject;
	}
	
	
}
