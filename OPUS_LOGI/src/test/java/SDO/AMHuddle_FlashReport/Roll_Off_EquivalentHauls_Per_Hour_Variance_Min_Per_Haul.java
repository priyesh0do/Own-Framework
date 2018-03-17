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

public class Roll_Off_EquivalentHauls_Per_Hour_Variance_Min_Per_Haul extends BaseClass{
	
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
	public void VerifyWalk_n_Talk_section_AM_Huddle_Resi(String ReportName, String siteID, String LOB, String FromDate,
			String ToDate, String Route, String Driver,String Ticket) throws Exception {
	if (!Util.isTestCaseExecutable("Roll_Off_EquivalentHauls_Per_Hour_Variance_Min_Per_Haul",GlobalVariables.sdoTestCase))
	{
		test = Report.testCreate(extent, test, "Roll_Off_EquivalentHauls_Per_Hour_Variance_Min_Per_Haul");
		Report.SkipTest(test, "Skipping the test as runmode is NO");			
	}
	else
	{
		test = Report.testCreate(extent, test, "Roll_Off_EquivalentHauls_Per_Hour_Variance_Min_Per_Haul");
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
		sdoFilterObj.selectSite(siteID);
		sdoFilterObj.selectDateToDatePicker(ToDate);
		sdoFilterObj.selectGO();
		rmDashboardObj=new RMDashboardReport(driver, test);
		//Switch to AM Huddle Frame
		Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
		rmDashboardObj.validateRollOffGraphDropdown(GlobalTestData.expectedRollOffGraphEquivalentHaulsPerHour);
		rmDashboardObj.selectRollOffGraphType(GlobalTestData.expectedRollOffGraphVarianceMinPerHaul);
		rmDashboardObj.validateRollOffGraphDropdown(GlobalTestData.expectedRollOffGraphVarianceMinPerHaul);
		rmDashboardObj.validateRollOffGraphDateRange(ToDate);
		rmDashboardObj.validateRollOffGraphVarianceMinPerHaul(ToDate);
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
