package SDO.FlashReports;


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
import TestData.GlobalExpectedColumns;
import TestData.GlobalTestData;

public class VerifyExportToExcelForEfficiencyAndDetailSubViewSectionComm extends BaseClass {

	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	CommercialFlashReport sdoCommObj;

	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify commercial flash report Detail SubView
	 * 
	 */

	@Test(groups = "Opus", dataProvider = "commercialFlashReport")
	public void OpusSDO_validateExportToExcelCommercialFlashReport(String ReportName, String siteID, String LOB, String FromDate,
			String ToDate, String Route, String Driver, String ClockInTime,String ClockOutTime) throws Exception {
		if (!Util.isTestCaseExecutable("VerifyExportToExcelForEfficiencyAndDetailSubViewSectionComm",GlobalVariables.sdoTestCase))
		{
			test = Report.testCreate(extent, test, "Verify Export To Excel For Efficiency And Detail SubView Commercial Flash Report");
			Report.SkipTest(test, "Skipping the test as runmode is NO");			
		}
		else
		{
			test = Report.testCreate(extent, test, "Verify Export To Excel For Efficiency And Detail SubView Commercial Flash Report");
			driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			String downloadFilePath=systemDir + "\\downloads";
			//FileUtils.cleanDirectory(new File(downloadFilePath));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			// Select RM Dashboard
			sdiMegaObj = new HomePage(driver, test);
			sdiMegaObj.ClickOnReport(GlobalTestData.RMDashBoardReport);
			
			sdoCommObj = new CommercialFlashReport(driver, test);
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");	
			// Click on commercial Flash report
			sdoCommObj.clickCommercialFlashReport();
			sdoFilterObj = new FilterSection(driver, test);
			// select site
			sdoFilterObj.selectSite(siteID);
			// Select Route
			sdoFilterObj.selectRoute(Route);
			// Select Driver
			sdoFilterObj.selectDriver(Driver);
			// Select Date Range
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer");
			sdoCommObj.validateExportToExcelColumns(GlobalExpectedColumns.excelExportColumnsCommAndResiFlashReport,downloadFilePath);
			driver.quit();

		}

	}

	@DataProvider(name = "commercialFlashReport")
	public Object[][] testData() throws Exception {
		Excel ex = new Excel();
		Object[][] arrayObject = ex.getTableArray(configProp.getProperty("SDOTestCaseExcel"),
				configProp.getProperty("SDOTestDataSheetComm"));
		return arrayObject;
	}

	
	
	
}
