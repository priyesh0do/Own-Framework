package SDO.AMHuddle_FlashReport;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.SDO.RMDashboardReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalTestData;

public class Radio_buttons_in_Walk_n_Talk_section_AM_Huddle_RO extends BaseClass {
	
	Login login;
	HomePage sdiMegaObj;
	RMDashboardReport rmDashboardObj;
	FilterSection sdoFilterObj;
	
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify Walk and Talk section of AM Huddle Flash Report Roll Off
	 * 
	 */
	@Test(groups = "Opus", dataProvider = "rollOffFlashReport")
	public void VerifyWalk_n_Talk_section_AM_Huddle_RO(String ReportName, String siteID, String LOB, String FromDate,
			String ToDate, String Route, String Driver,String Ticket) throws Exception {
	if (!Util.isTestCaseExecutable("Radio_buttons_in_Walk_n_Talk_section_AM_Huddle_RO",GlobalVariables.sdoTestCase))
	{
		test = Report.testCreate(extent, test, "Radio_buttons_in_Walk_n_Talk_section_AM_Huddle_RO");
		Report.SkipTest(test, "Skipping the test as runmode is NO");			
	}
	else
	{
		test = Report.testCreate(extent, test, "Radio_buttons_in_Walk_n_Talk_section_AM_Huddle_RO");
		driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
		login = new Login(driver, test);
		login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
		System.out.println("Login successfully done");		
		// Select RM Dashboard
		sdiMegaObj = new HomePage(driver, test);
		//sdiMegaObj.verifyAllLinks();
		sdiMegaObj.ClickOnReport(GlobalTestData.RMDashBoardReport);
		sdoFilterObj = new FilterSection(driver, test);
		sdoFilterObj.clickReset();
		sdoFilterObj.selectSite(GlobalTestData.siteID);
		sdoFilterObj.selectDateToDatePicker(ToDate);
		//sdoFilterObj.selectDateFromDatePicker("04/05/2017");
		sdoFilterObj.selectGO();
		rmDashboardObj=new RMDashboardReport(driver, test);
		Util.pageScroll(0, 800);
		//Switch to AM Huddle Frame
		Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
		rmDashboardObj.validateWalkAndTalkSectionExist();
		rmDashboardObj.validateOpportunitiesLabels();
		rmDashboardObj.validateNetIdleTopOpportunities(LOB,Route);
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
		rmDashboardObj.validatePreRouteTopOpportunities(LOB,Route);
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
		rmDashboardObj.validatePostRouteTopOpportunities(LOB,Route);	
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
		rmDashboardObj.validateSequenceComplianceTopOpportunities(LOB,Route);
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
		rmDashboardObj.validateDowntimeTopOpportunities(LOB,Route);
		driver.quit();
	}
	}

	@DataProvider(name = "rollOffFlashReport")
	public Object[][] testData() throws Exception {
		Excel ex = new Excel();
		Object[][] arrayObject = ex.getTableArray("R:\\Automation\\OPUS\\OPUS_SDO\\Test Data\\Flash Report.xlsx",
				"RollOffFlashReport");
		return arrayObject;
	}
}
