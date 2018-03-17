package SDO.AMHuddle_FlashReport;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.SDO.CommercialFlashReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalTestData;

public class Display_LOB_FlashReport_and_Driver_FlashReport_Com extends BaseClass {
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	CommercialFlashReport sdoCommObj;
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify heading of flash report based on LOB selected
	 * 
	 */
	
	@Test(groups = "Opus")
	public void DisplayFlashAndDriverReportTitleComm() throws Exception {
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
			sdoCommObj = new CommercialFlashReport(driver, test);
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
			// Click on commercial Flash report
			sdoCommObj.clickCommercialFlashReport();
			sdoFilterObj=new FilterSection(driver, test);
			sdoFilterObj.verifyReportTitle(GlobalTestData.expectedCommercialFlashReportTitle);
			sdoFilterObj.selectSite(GlobalTestData.siteID);
			sdoFilterObj.isFilterExist("Driver");
			sdoFilterObj.selectDriver(GlobalTestData.RANDOMCOMMERCIALDRIVER1);
			sdoFilterObj.selectGO();
			sdoFilterObj.verifyReportTitle(GlobalTestData.expectedDriverFlashReportTitle);
			sdoFilterObj.selectDayOfWeekOption();
			sdoFilterObj.isFilterExist("Day Of Week");
			sdoFilterObj.selectDay("Monday");
			sdoFilterObj.selectGO();
			sdoFilterObj.verifyReportTitle(GlobalTestData.expectedDriverFlashReportTitle);
			sdoFilterObj.selectDriver(GlobalTestData.RANDOMCOMMERCIALDRIVER2);
			sdoFilterObj.selectGO();
			sdoFilterObj.verifyReportTitle(GlobalTestData.expectedCommercialFlashReportTitle);
			sdoFilterObj.selectDriver(GlobalTestData.RANDOMCOMMERCIALDRIVER2);
			sdoFilterObj.selectGO();
			sdoFilterObj.verifyReportTitle(GlobalTestData.expectedDriverFlashReportTitle);
			sdoFilterObj.clickBackToRMDashBoard();
			driver.quit();
		}	
			
	}

}
