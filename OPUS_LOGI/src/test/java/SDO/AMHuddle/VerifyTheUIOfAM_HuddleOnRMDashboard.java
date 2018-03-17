package SDO.AMHuddle;

import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.SDO.CommercialFlashReport;
import ObjectRepository.SDO.RMDashboardReport;
import Opus.BaseClass;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalTestData;

public class VerifyTheUIOfAM_HuddleOnRMDashboard extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	RMDashboardReport rmDashboardObj;
	FilterSection sdoFilterObj;
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify RM Dashboard for top and bottom 5 drivers
	 * 
	 */
	@Test(groups = "Opus")
	public void Verify_UI_Of_AM_Huddle_On_RMDashboard() throws Exception {
	if (!Util.isTestCaseExecutable("Verify the UI of AM Huddle on RM Dashboard",GlobalVariables.sdoTestCase))
	{
		test = Report.testCreate(extent, test, "Verify the UI of AM Huddle on RM Dashboard");
		Report.SkipTest(test, "Skipping the test as runmode is NO");			
	}
	else
	{
		test = Report.testCreate(extent, test, "Verify the UI of AM Huddle on RM Dashboard");
		driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
		login = new Login(driver, test);
		login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
		System.out.println("Login successfully done");		
		// Select RM Dashboard
		sdiMegaObj = new HomePage(driver, test);
		//sdiMegaObj.verifyAllLinks();
		sdiMegaObj.ClickOnReport(GlobalTestData.RMDashBoardReport);
		sdoFilterObj = new FilterSection(driver, test);
		sdoFilterObj.UI_validateRMDashboardFilters();
		sdoFilterObj.clickReset();
		sdoFilterObj.selectSite(GlobalTestData.siteID);
		sdoFilterObj.selectDateToDatePicker("04/05/2017");
		sdoFilterObj.selectGO();
		Util.pageScroll(0,300);
		rmDashboardObj=new RMDashboardReport(driver, test);
		//Switch to AM Huddle Frame
		Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
		rmDashboardObj.UI_ValidateGraphSection();	
		Util.switchToDefaultWindow();
		Util.pageScroll(0,450);
		Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
		rmDashboardObj.UI_ValidateWeekToDateTopAndBottom5DriverSection();
		rmDashboardObj.UI_ValidateWalkAndTalkSection();
		rmDashboardObj.UI_ValidateCustomerFocusSection();
		driver.quit();
	}
	}

}
