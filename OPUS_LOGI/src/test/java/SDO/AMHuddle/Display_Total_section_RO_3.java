package SDO.AMHuddle;

import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.SDO.CommercialFlashReport;
import ObjectRepository.SDO.RollOffFlashReport;
import Opus.BaseClass;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;
import TestData.GlobalTestData;

public class Display_Total_section_RO_3 extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	RollOffFlashReport sdoRollOffObj;
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify commercial flash report total section columns
	 * 
	 */
	
	@Test(groups = "Opus")
	public void DisplayTotalSectionRollOff() throws Exception {
		if (!Util.isTestCaseExecutable("Display_Total_section_RO_3",GlobalVariables.sdoTestCase))
		{
			test = Report.testCreate(extent, test, "Display_Total_section_RO_3");
			Report.SkipTest(test, "Skipping the test as runmode is NO");			
		}
		else
		{
			test = Report.testCreate(extent, test, "OpusSDO_DisplayTotalSectionRollOff");
			driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			// Select RM Dashboard
			sdiMegaObj = new HomePage(driver, test);
			sdiMegaObj.ClickOnReport(GlobalTestData.RMDashBoardReport);
			sdoRollOffObj = new RollOffFlashReport(driver, test);
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
			// Click on commercial Flash report
			sdoRollOffObj.clickRollOffFlashReport();
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer");
			List<String> actualSummaryTableColumns = sdoRollOffObj.getSummaryTableColumns();
			List<String> actualEfficiencyTableColumns = sdoRollOffObj.getEfficiencyTableColumns();
			sdoRollOffObj.selectDetailSubView();
			Thread.sleep(2000);
			List<String> actualDetailTableColumns = sdoRollOffObj.getDetailTableColumns();
			sdoRollOffObj.validateColumns(actualSummaryTableColumns,GlobalExpectedColumns.expectedRollOffFlashReportTotalSectionColumns,"Summary Table");	
			sdoRollOffObj.validateColumns(actualEfficiencyTableColumns, GlobalExpectedColumns.expectedRollOffFlashReportEfficiencyColumns,"Efficiency Table");
			sdoRollOffObj.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.expectedRollOffFlashReportEDetailColumns,"Detail Table");
			driver.quit();
		}
	}
}
