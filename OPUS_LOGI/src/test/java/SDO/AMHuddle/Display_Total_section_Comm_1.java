package SDO.AMHuddle;

import java.util.List;
import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.SkipException;
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
import SupportLibraries.LogClass;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;
import TestData.GlobalTestData;

public class Display_Total_section_Comm_1 extends BaseClass {
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	CommercialFlashReport sdoCommObj;
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify commercial flash report total section columns
	 * 
	 */
	
	@Test(groups = "Opus")
	public void OpusSDO_DisplayTotalSectionCommercial() throws Exception {
		if (!Util.isTestCaseExecutable("Display_Total_section_Comm_1",GlobalVariables.sdoTestCase))
		{
			test = Report.testCreate(extent, test, "Display_Total_section_Comm_1");
			Report.SkipTest(test, "Skipping the test as runmode is NO");			
		}
		else
		{
			LogClass.startTestCase(Thread.currentThread().getStackTrace()[1].getMethodName());
			test = Report.testCreate(extent, test, "Display_Total_section_Comm_1");
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
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer");
			List<String> actualSummaryTableColumns = sdoCommObj.getSummaryTableColumns();
			List<String> actualEfficiencyTableColumns = sdoCommObj.getEfficiencyTableColumns();
			sdoCommObj.selectDetailSubView();
			Thread.sleep(2000);
			List<String> actualDetailTableColumns = sdoCommObj.getDetailTableColumns();
			sdoCommObj.validateColumns(actualSummaryTableColumns,GlobalExpectedColumns.expectedCommercialFlashReportTotalSectionColumns,"Summary Table");
			sdoCommObj.validateColumns(actualEfficiencyTableColumns, GlobalExpectedColumns.expectedCommercialFlashReportEfficiencyColumns,"Efficiency Table");
			sdoCommObj.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.expectedCommercialFlashReportEDetailColumns,"Detail Table");
			LogClass.endTestCase(Thread.currentThread().getStackTrace()[1].getMethodName());
			driver.quit();
	}
	}
}
