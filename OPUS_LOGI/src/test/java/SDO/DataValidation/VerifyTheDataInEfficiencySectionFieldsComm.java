package SDO.DataValidation;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.util.HSSFColor.GOLD;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.SDO.CommercialFlashReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.LogClass;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalTestData;


public class VerifyTheDataInEfficiencySectionFieldsComm extends BaseClass {

	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	CommercialFlashReport sdoCommObj;

	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify commercial flash report
	 * 
	 */

	@Test(groups = "Opus", dataProvider = "commercialFlashReport")
	public void OpusSDO_CommercialFlashReportTestE2E(String ReportName, String siteID, String LOB, String FromDate,
			String ToDate, String Route, String Driver, String ClockInTime,String ClockOutTime) throws Exception {
		if (!Util.isTestCaseExecutable("Verify the data in Efficiency section fields Comm",GlobalVariables.sdoTestCase))
		{
			test = Report.testCreate(extent, test, "OpusSDO_CommercialFlashReportTestE2E");
			Report.SkipTest(test, "Skipping the test as runmode is NO");			
		}
		else
		{
			LogClass.startTestCase(Thread.currentThread().getStackTrace()[1].getMethodName());
			test = Report.testCreate(extent, test, "OpusSDO_CommercialFlashReportTestE2E");
			driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
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
			Map<String, List<String>> actualSummaryTable = sdoCommObj.getActualSummaryTableData();
			Map<String, List<String>> actualEfficiencyTable = sdoCommObj.getActualEfficiencyTableData();
			sdoCommObj.selectDetailSubView();
			Thread.sleep(2000);
			Map<String, List<String>> actualDetailTable = sdoCommObj.getActualDetailTableData();
			sdoCommObj.validateSummaryData(actualSummaryTable, actualEfficiencyTable, actualDetailTable);
			sdoCommObj.validateEfficiencyData(actualEfficiencyTable,actualDetailTable,LOB,Route,Driver,ClockInTime,ClockOutTime);
			sdoCommObj.validateDetailData(actualEfficiencyTable,actualDetailTable,LOB,Route,Driver,ClockInTime,ClockOutTime);
			LogClass.endTestCase(Thread.currentThread().getStackTrace()[1].getMethodName());
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
