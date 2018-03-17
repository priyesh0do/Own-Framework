package SDO.IndDriverDetailRO;

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
import TestData.GlobalExpectedColumns;
import TestData.GlobalTestData;

public class ValidateTheColumnsOfAccordionInFlashReportRO extends BaseClass {

	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	RollOffFlashReport sdoRollOffObj;
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify columns of Accordion in Roll off flash report
	 * 
	 */
	
	@Test(groups = "Opus", dataProvider = "rollOffFlashReport")
	public void OpusSDO_ColumnsAccordionFlashReportRO(String ReportName, String siteID, String LOB, String FromDate,
			String ToDate, String Route, String Driver,String Ticket) throws Exception {	
		if (!Util.isTestCaseExecutable("Validate the columns of Accordion in Flash Report RO",GlobalVariables.sdoTestCase))
		{
			test = Report.testCreate(extent, test, "Validate the columns of Accordion in Flash Report RO");
			Report.SkipTest(test, "Skipping the test as runmode is NO");			
		}
		else
		{
		    test = Report.testCreate(extent, test, "OpusSDO_ColumnsAccordionFlashReportRO");
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
			// Click on commercial Flash report
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
			List<String> actualTicketInformationTableColumns = sdoRollOffObj.getTicketInformationTableColumns(Route);
			List<String> actualServiceInformationTableColumns = sdoRollOffObj.getServiceInformationTableColumns(Route);
			List<String> actualDisposalInformationTableColumns = sdoRollOffObj.getDisposalInformationTableColumns(Route);
			List<String> actualTravelInformationTableColumns = sdoRollOffObj.getTravelInformationTableColumns(Driver);
			List<String> actualOnPropertyInformationTableColumns = sdoRollOffObj.getOnPropertyInformationTableColumns(Driver);
			sdoRollOffObj.validateColumns(actualTicketInformationTableColumns,GlobalExpectedColumns.expectedRollOffFlashReportTicketInformationColumns,"Ticket Information Table");
			sdoRollOffObj.validateColumns(actualServiceInformationTableColumns,GlobalExpectedColumns.expectedRollOffFlashReportServiceInformationColumns,"Service Information Table");
			sdoRollOffObj.validateColumns(actualDisposalInformationTableColumns,GlobalExpectedColumns.expectedRollOffFlashReportDisposalInformationColumns,"Disposal Information Table");
			sdoRollOffObj.validateColumns(actualTravelInformationTableColumns,GlobalExpectedColumns.expectedRollOffFlashReportTravelInformationColumns,"Travel Information Table");
			sdoRollOffObj.validateColumns(actualOnPropertyInformationTableColumns,GlobalExpectedColumns.expectedRollOffFlashReportOnPropertyInformationColumns,"on Property Information Table");
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
