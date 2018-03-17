package SDO.AMHuddle_FlashReport;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.SDO.RMDashboardReport;

import Opus.BaseClass;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalTestData;

public class VerifyExportPDF_AM_Huddle_RMDashboard extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	RMDashboardReport rmDashboardObj;
	FilterSection sdoFilterObj;
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify Export PDF for all the activities under Walk and Talk Section
	 * 
	 */
	@Test(groups = "Opus")
	public void Verify_UI_Of_AM_Huddle_On_RMDashboard() throws Exception {
	if (!Util.isTestCaseExecutable("Verify Export to PDF of AM Huddle on RM Dashboard",GlobalVariables.sdoTestCase))
	{
		test = Report.testCreate(extent, test, "Verify Export to PDF of AM Huddle on RM Dashboard");
		Report.SkipTest(test, "Skipping the test as runmode is NO");			
	}
	else
	{
		test = Report.testCreate(extent, test, "Verify Export to PDF of AM Huddle on RM Dashboard");
		driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
		FileUtils.cleanDirectory(new File("C:\\Working\\PDFDownloads"));
		login = new Login(driver, test);
		login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
		System.out.println("Login successfully done");		
		// Select RM Dashboard
		sdiMegaObj = new HomePage(driver, test);
		//sdiMegaObj.verifyAllLinks();
		sdiMegaObj.ClickOnReport(GlobalTestData.RMDashBoardReport);
		sdoFilterObj = new FilterSection(driver, test);
		//sdoFilterObj.UI_validateRMDashboardFilters();
		sdoFilterObj.clickReset();
		sdoFilterObj.selectSite(GlobalTestData.siteID);
		sdoFilterObj.selectDateToDatePicker("08/04/2017");
		sdoFilterObj.selectGO();
		rmDashboardObj=new RMDashboardReport(driver, test);
		//Switch to AM Huddle Frame
		//Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
		String downloadFilePath=systemDir + "\\downloads";
		rmDashboardObj.UI_ValidateWalkAndTalkSection_ExportPDF(downloadFilePath);
		driver.quit();
		
	}
	}

}
