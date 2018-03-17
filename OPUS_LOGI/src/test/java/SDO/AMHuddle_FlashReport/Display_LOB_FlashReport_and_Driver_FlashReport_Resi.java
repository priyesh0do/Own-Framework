package SDO.AMHuddle_FlashReport;

import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.SDO.ResidentialFlashReport;
import Opus.BaseClass;

import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalTestData;

public class Display_LOB_FlashReport_and_Driver_FlashReport_Resi extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	ResidentialFlashReport sdoResiObj;
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify heading of flash report based on LOB selected
	 * 
	 */
	
	@Test(groups = "Opus")
	public void DisplayFlashAndDriverReportTitleResi() throws Exception {
		if (!Util.isTestCaseExecutable("Display_LOB Flash report_and_Driver Flash report_Resi",GlobalVariables.sdoTestCase))
		{
			test = Report.testCreate(extent, test, "Display_LOB Flash report_and_Driver Flash report_Resi");
			Report.SkipTest(test, "Skipping the test as runmode is NO");			
		}
		else
		{
			test = Report.testCreate(extent, test, "Display_LOB Flash report_and_Driver Flash report_Resi");
			driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			
			// Select RM Dashboard
			sdiMegaObj = new HomePage(driver, test);
			//sdiMegaObj.verifyAllLinks();
			sdiMegaObj.ClickOnReport(GlobalTestData.RMDashBoardReport);
			sdoResiObj = new ResidentialFlashReport(driver, test);
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
			// Click on commercial Flash report
			sdoResiObj.clickResidentialFlashReport();
			sdoFilterObj=new FilterSection(driver, test);
			sdoFilterObj.verifyReportTitle(GlobalTestData.expectedResidentialFlashReportTitle);
			sdoFilterObj.selectSite(GlobalTestData.siteID);
			sdoFilterObj.selectLOB(GlobalTestData.LOBResidential);
			sdoFilterObj.isFilterExist("Driver");
			sdoFilterObj.selectDriver(GlobalTestData.RANDOMRESIDENTIALDRIVER1);
			sdoFilterObj.selectGO();
			sdoFilterObj.verifyReportTitle(GlobalTestData.expectedDriverFlashReportTitle);
			sdoFilterObj.selectDayOfWeekOption();
			sdoFilterObj.isFilterExist("Day Of Week");
			sdoFilterObj.selectDay("Monday");
			sdoFilterObj.selectGO();
			sdoFilterObj.verifyReportTitle(GlobalTestData.expectedDriverFlashReportTitle);
			sdoFilterObj.selectDriver(GlobalTestData.RANDOMRESIDENTIALDRIVER2);
			sdoFilterObj.selectGO();
			sdoFilterObj.verifyReportTitle(GlobalTestData.expectedResidentialFlashReportTitle);
			sdoFilterObj.selectDriver(GlobalTestData.RANDOMRESIDENTIALDRIVER2);
			sdoFilterObj.selectGO();
			sdoFilterObj.verifyReportTitle(GlobalTestData.expectedDriverFlashReportTitle);
			sdoFilterObj.clickBackToRMDashBoard();
			driver.quit();
		}	
			
	}


}
