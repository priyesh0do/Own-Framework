package SDO.SecondaryRouteLogic;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.SDO.CommercialFlashReport;
import ObjectRepository.SDO.ResidentialFlashReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalTestData;

public class VerifyTheSecondaryRouteLogicForUnplannedRoute extends BaseClass {


	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	ResidentialFlashReport sdoResiObj;
	CommercialFlashReport sdoCommObj;

	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify Plan Units, Pan Driver Hours and Plan Efficiency for UnPlanned routes
	 * 
	 */

	@Test(groups = "Opus", dataProvider = "unPlannedRoutes")
	public void OpusSDO_SecondaryRouteLogicUnPlannedRoutes(String ReportName, String siteID, String LOB, String FromDate,
			String ToDate, String Route, String Driver) throws Exception {
		if (!Util.isTestCaseExecutable("Verify the Secondary Route Logic_Resi",GlobalVariables.sdoTestCase))
		{
			test = Report.testCreate(extent, test, "Verify the Secondary Route Logic_Resi");
			Report.SkipTest(test, "Skipping the test as runmode is NO");			
		}
		else
		{
			test = Report.testCreate(extent, test, "Verify the Secondary Route Logic_Resi");
			driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			// Select RM Dashboard
			sdiMegaObj = new HomePage(driver, test);
			sdiMegaObj.ClickOnReport(GlobalTestData.RMDashBoardReport);
			if(ReportName.equals("Residential Flash Report"))
			{
			sdoResiObj = new ResidentialFlashReport(driver, test);
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
			// Click on commercial Flash report
			sdoResiObj.clickResidentialFlashReport();
			sdoFilterObj = new FilterSection(driver, test);
			// select site
			sdoFilterObj.selectSite(siteID);
			//Select LOB
			sdoFilterObj.selectLOB(LOB);
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
			Map<String, List<String>> actualEfficiencyTable = sdoResiObj.getActualEfficiencyTableData();
			sdoResiObj.selectDetailSubView();
			Thread.sleep(2000);
			Map<String, List<String>> actualDetailTable = sdoResiObj.getActualDetailTableData();
			sdoResiObj.validatePlanUnits(actualDetailTable,"Un-Planned");
			sdoResiObj.validatePlanDriverHours(actualEfficiencyTable,"Un-Planned");
			sdoResiObj.validatePlanEfficiency(actualEfficiencyTable,"Un-Planned");
			}
			else if(ReportName.equals("Commercial Flash Report"))
			{
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
				Map<String, List<String>> actualEfficiencyTable = sdoCommObj.getActualEfficiencyTableData();
				sdoCommObj.selectDetailSubView();
				Thread.sleep(2000);
				Map<String, List<String>> actualDetailTable = sdoCommObj.getActualDetailTableData();
				sdoCommObj.validatePlanUnits(actualDetailTable,"Un-Planned");
				sdoCommObj.validatePlanDriverHours(actualEfficiencyTable,"Un-Planned");
				sdoCommObj.validatePlanEfficiency(actualEfficiencyTable,"Un-Planned");
				driver.quit();
			}

		}

	}

	@DataProvider(name = "unPlannedRoutes")
	public Object[][] testData() throws Exception {
		Excel ex = new Excel();
		Object[][] arrayObject = ex.getTableArray(configProp.getProperty("SDOTestCaseExcel"),
				configProp.getProperty("SDOTestDataSheetUnplannedRoute"));
		return arrayObject;
	}

	
}
