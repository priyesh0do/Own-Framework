package SDO.AMHuddle;

import java.util.List;

import org.testng.SkipException;
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
import TestData.GlobalExpectedColumns;
import TestData.GlobalTestData;

public class Display_Total_section_Resi_2 extends BaseClass{

	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	ResidentialFlashReport sdoResiObj;
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify commercial flash report total section columns
	 * 
	 */
	
	@Test(groups = "Opus")
	public void DisplayTotalSectionResidential() throws Exception {
		if (!Util.isTestCaseExecutable("Display_Total_section_Resi_2",GlobalVariables.sdoTestCase))
		{
			test = Report.testCreate(extent, test, "Display_Total_section_Resi_2");
			Report.SkipTest(test, "Skipping the test as runmode is NO");			
		}
		else
		{
			test = Report.testCreate(extent, test, "Display_Total_section_Resi_2");
			driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			// Select RM Dashboard
			sdiMegaObj = new HomePage(driver, test);
			sdiMegaObj.ClickOnReport(GlobalTestData.RMDashBoardReport);
			sdoResiObj = new ResidentialFlashReport(driver, test);
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
			// Click on commercial Flash report
			sdoResiObj.clickResidentialFlashReport();
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer");
			List<String> actualSummaryTableColumns = sdoResiObj.getSummaryTableColumns();
			List<String> actualEfficiencyTableColumns = sdoResiObj.getEfficiencyTableColumns();
			sdoResiObj.selectDetailSubView();
			Thread.sleep(2000);
			List<String> actualDetailTableColumns = sdoResiObj.getDetailTableColumns();
			sdoResiObj.validateColumns(actualSummaryTableColumns,GlobalExpectedColumns.expectedResidentialFlashReportTotalSectionColumns,"Summary Table");	
			sdoResiObj.validateColumns(actualEfficiencyTableColumns, GlobalExpectedColumns.expectedResidentialFlashReportEfficiencyColumns,"Efficiency Table");
			sdoResiObj.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.expectedResidentialFlashReportEDetailColumns,"Detail Table");
			driver.quit();
	}
	}
}
