package SDO.ROTravelProperty;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.SDO.RollOffFlashReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalTestData;

public class Validate_ExportPDF_AccordionSection extends BaseClass {
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	RollOffFlashReport sdoRollOffObj;
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify PDF Export for Accordian Menu
	 * 
	 */
	
	@Test(groups = "Opus", dataProvider = "rollOffTravelProperty")
	public void Validate_ExportPDF_Accordion(String ReportName, String siteID, String LOB, String FromDate,
			String ToDate, String Route, String Driver,String Ticket,String TicketType) throws Exception {	
		if (!Util.isTestCaseExecutable("Validate_ExportPDF_AccordionSection",GlobalVariables.sdoTestCase))
		{
			test = Report.testCreate(extent, test, "Validate_ExportPDF_AccordionSection");
			Report.SkipTest(test, "Skipping the test as runmode is NO");			
		}
		else
		{
			    test = Report.testCreate(extent, test, "Validate_ExportPDF_AccordionSection");
			    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
				login = new Login(driver, test);
				login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
				System.out.println("Login successfully done");
				// Select RM Dashboard
				sdiMegaObj = new HomePage(driver, test);
				sdiMegaObj.ClickOnReport(GlobalTestData.ReportName);
				sdoRollOffObj = new RollOffFlashReport(driver, test);
				// Switch to frame
				Util.switchToDefaultWindow();
				// Click on Roll Off Flash report
				//sdoRollOffObj.clickRollOffFlashReport();
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
				//Thread.sleep(2000);
				Util.selectFrame("opusReportContainer,subReportContainer");
				//Click on the Driver Accordian menu
				sdoRollOffObj.ClickAccordianLinkOnTable("Driver");
				sdoRollOffObj.ClickOnPDFLinkInSubView("Driver");
				sdoRollOffObj.ClickAccordianLinkOnTable("Driver");
				sdoRollOffObj.verifyPDFOpensInNewTab("RO Driver Flash Report is open in a new tab");
				driver.quit();

		}	

	}

	@DataProvider(name = "rollOffTravelProperty")
	public Object[][] testData() throws Exception {
		Excel ex = new Excel();
		Object[][] arrayObject = ex.getTableArray(configProp.getProperty("SDOTestCaseExcel"),
				configProp.getProperty("SDOTestDataSheetROTravelProperty"));
		return arrayObject;
	}

}
