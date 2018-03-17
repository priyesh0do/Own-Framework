package SDO.IndDriverDetailRO;

import java.util.List;
import java.util.Map;

import org.testng.SkipException;
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
import TestData.GlobalExpectedColumns;
import TestData.GlobalTestData;

public class VerifyTheExportToPDFoptionInTheDriverAccordion extends BaseClass {

	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	RollOffFlashReport sdoRollOffObj;
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify PDF option in Accordion in Roll off flash report for RO Drivers.
	 * 
	 */
	
	@Test(groups = "Opus", dataProvider = "rollOffFlashReport")
	public void OpusSDO_PDFOptionFlashReportRO(String ReportName, String siteID, String LOB, String FromDate,
			String ToDate, String Route, String Driver, String Ticket) throws Exception {
		if (!Util.isTestCaseExecutable("Verify the Export to PDF option in the Driver Accordion",GlobalVariables.sdoTestCase))
			throw new SkipException("Skipping the test as runmode is NO");	
		    test = Report.testCreate(extent, test, "OpusSDO_PDFOptionFlashReportRO");
		    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			// Select RM Dashboard
			sdiMegaObj = new HomePage(driver, test);
			sdiMegaObj.ClickOnReport(GlobalTestData.ReportName);
			sdoRollOffObj = new RollOffFlashReport(driver, test);
			// Switch to frame
			//Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
			// Click on commercial Flash report
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
			Util.pageScroll(0, 300);
			//Click on the first row Driver from the Efficiency Subview
			sdoRollOffObj.ClickAccordianLinkOnTable("Driver");
			sdoRollOffObj.ClickOnPDFLinkInSubView("Driver");
			sdoRollOffObj.verifyPDFOpensInNewTab("PDF is getting open in new tab for Driver in Efficiency Subview");
			
			//Click on the first row Route from Efficiency Subview
			sdoRollOffObj.ClickAccordianLinkOnTable("Route");
			sdoRollOffObj.ClickOnPDFLinkInSubView("Route");
			sdoRollOffObj.verifyPDFOpensInNewTab("PDF is getting open in new tab for Route in Efficiency Subview");
			
			//click on first row driver from Detail Subview
			Util.selectFrame("opusReportContainer,subReportContainer");
			sdoRollOffObj.selectDetailSubView();
			
			sdoRollOffObj.ClickAccordianLinkOnTable("Driver");
			sdoRollOffObj.ClickOnPDFLinkInSubView("Driver");
			sdoRollOffObj.verifyPDFOpensInNewTab("PDF is getting open in new tab for Driver in Detail Subview");
			
			//Click on first row Route from Detail Subview
			sdoRollOffObj.ClickAccordianLinkOnTable("Route");
			sdoRollOffObj.ClickOnPDFLinkInSubView("Route");
			sdoRollOffObj.verifyPDFOpensInNewTab("PDF is getting open in new tab for Route in Detail Subview");
			driver.quit();
	}

	@DataProvider(name = "rollOffFlashReport")
	public Object[][] testData() throws Exception {
		Excel ex = new Excel();
		Object[][] arrayObject = ex.getTableArray(configProp.getProperty("SDOTestCaseExcel"),
				configProp.getProperty("SDOTestDataSheetRO"));
		return arrayObject;
	}
}
