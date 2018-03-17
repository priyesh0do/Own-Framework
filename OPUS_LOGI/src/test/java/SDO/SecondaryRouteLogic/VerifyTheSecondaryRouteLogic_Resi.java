package SDO.SecondaryRouteLogic;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.SDO.ResidentialFlashReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalTestData;

public class VerifyTheSecondaryRouteLogic_Resi extends BaseClass {
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	ResidentialFlashReport sdoResiObj;

	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify Plan Units, Pan Driver Hours and Plan Efficiency are populating from EMAP in residential flash report
	 * 
	 */

	@Test(groups = "Opus", dataProvider = "residentialFlashReport")
	public void OpusSDO_SecondaryRouteLogicCommercialFlashReport(String ReportName, String siteID, String LOB, String FromDate,
			String ToDate, String Route, String Driver, String ClockInTime,String ClockOutTime) throws Exception {
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
			sdoResiObj.validatePlanUnits(actualDetailTable,"Planned");
			sdoResiObj.validatePlanDriverHours(actualEfficiencyTable,"Planned");
			sdoResiObj.validatePlanEfficiency(actualEfficiencyTable,"Planned");
			sdoResiObj.validateEfficiencyPlanDriverHours(actualEfficiencyTable, actualDetailTable);
            driver.quit();
		}

	}

	@DataProvider(name = "residentialFlashReport")
	public Object[][] testData() throws Exception {
		Excel ex = new Excel();
		Object[][] arrayObject = ex.getTableArray(configProp.getProperty("SDOTestCaseExcel"),
				configProp.getProperty("SDOTestDataSheetResi"));
		return arrayObject;
	}


}
