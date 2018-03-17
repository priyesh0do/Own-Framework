package SDO.AMHuddle_FlashReport;

import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.SDO.RollOffFlashReport;
import Opus.BaseClass;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalTestData;

public class Display_LOB_FlashReport_and_Driver_FlashReport_RO extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	RollOffFlashReport sdoRollOffObj;
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify heading of flash report based on LOB selected
	 * 
	 */
	
	@Test(groups = "Opus")
	public void DisplayFlashAndDriverReportTitleRO() throws Exception {
		if (!Util.isTestCaseExecutable("Display_LOB Flash report_and_Driver Flash report_Com",GlobalVariables.sdoTestCase))
		{
			test = Report.testCreate(extent, test, "Display_LOB Flash report_and_Driver Flash report_Com");
			Report.SkipTest(test, "Skipping the test as runmode is NO");			
		}
		else
		{
			test = Report.testCreate(extent, test, "Display_LOB Flash report_and_Driver Flash report_Com");
			driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			
			// Select RM Dashboard
			sdiMegaObj = new HomePage(driver, test);
			//sdiMegaObj.verifyAllLinks();
			sdiMegaObj.ClickOnReport(GlobalTestData.RMDashBoardReport);
			sdoRollOffObj = new RollOffFlashReport(driver, test);
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
			// Click on commercial Flash report
			sdoRollOffObj.clickRollOffFlashReport();
			sdoFilterObj=new FilterSection(driver, test);
			sdoFilterObj.verifyReportTitle(GlobalTestData.expectedRollOffFlashReportTitle);
			sdoFilterObj.selectSite(GlobalTestData.siteID);
			sdoFilterObj.selectLOB(GlobalTestData.LOBRollOff);
			sdoFilterObj.isFilterExist("Driver");
			sdoFilterObj.selectDriver(GlobalTestData.RANDOMROLLOFFDRIVER1);
			sdoFilterObj.selectGO();
			sdoFilterObj.verifyReportTitle(GlobalTestData.expectedDriverFlashReportTitle);
			sdoFilterObj.selectDayOfWeekOption();
			sdoFilterObj.isFilterExist("Day Of Week");
			sdoFilterObj.selectDay("Monday");
			sdoFilterObj.selectGO();
			sdoFilterObj.verifyReportTitle(GlobalTestData.expectedDriverFlashReportTitle);
			sdoFilterObj.selectDriver(GlobalTestData.RANDOMROLLOFFDRIVER2);
			sdoFilterObj.selectGO();
			sdoFilterObj.verifyReportTitle(GlobalTestData.expectedRollOffFlashReportTitle);
			sdoFilterObj.selectDriver(GlobalTestData.RANDOMROLLOFFDRIVER2);
			sdoFilterObj.selectGO();
			sdoFilterObj.verifyReportTitle(GlobalTestData.expectedDriverFlashReportTitle);
			sdoFilterObj.clickBackToRMDashBoard();
			driver.quit();
		}	
			
	}
	
}
