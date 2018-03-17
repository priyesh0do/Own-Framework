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

public class ValidateTheTopAndBottom5DriversCom_Resi_RO extends BaseClass {
	
	
	Login login;
	HomePage sdiMegaObj;
	RMDashboardReport rmDashboardObj;
	FilterSection sdoFilterObj;
	CommercialFlashReport sdoCommObj;
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify RM Dashboard for top and bottom 5 drivers
	 * 
	 */
	@Test(groups = "Opus")
	public void VerifyTopAndBottom5Drivers() throws Exception {
	if (!Util.isTestCaseExecutable("Verify the Top and Bottom 5 driver of Commercial, Residential and Roll Off",GlobalVariables.sdoTestCase))
	{
		test = Report.testCreate(extent, test, "Verify the Top and Bottom 5 driver of Commercial, Residential and Roll Off");
		Report.SkipTest(test, "Skipping the test as runmode is NO");			
	}
	else
	{
		test = Report.testCreate(extent, test, "Verify the Top and Bottom 5 driver of Commercial, Residential and Roll Off");
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
		sdoFilterObj.selectDateToDatePicker("04/05/2017");
		sdoFilterObj.selectGO();
		rmDashboardObj=new RMDashboardReport(driver, test);
		//Switch to AM Huddle Frame
		Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
		rmDashboardObj.validateTop5Driver();
		rmDashboardObj.validateBottom5Driver();
		driver.quit();
	}
	}
}
