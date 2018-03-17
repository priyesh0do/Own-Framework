package SDO.AMHuddle_FlashReport;

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
import net.sourceforge.htmlunit.corejs.javascript.tools.shell.Global;

public class VerifyExportPDF_ColumnNames_FlashReport_Resi_RO extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	ResidentialFlashReport sdoResiObj;
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify Column Names in the PDF for Resi and RO LOB
	 * 
	 */
	
	@Test(groups = "Opus")
	public void VerifyExportPDFColumnNamesResiRO() throws Exception {
		if (!Util.isTestCaseExecutable("VerifyExportPDF_ColumnNames_FlashReport_Resi_RO",GlobalVariables.sdoTestCase))
		{
			test = Report.testCreate(extent, test, "VerifyExportPDF_ColumnNames_FlashReport_Resi_RO");
			driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			Report.SkipTest(test, "Skipping the test as runmode is NO");			
		}
		else
		{
			test = Report.testCreate(extent, test, "VerifyExportPDF_ColumnNames_FlashReport_Resi_RO");
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			
			// Select Flash Report
			sdiMegaObj = new HomePage(driver, test);
			//sdiMegaObj.verifyAllLinks();
			sdiMegaObj.ClickOnReport(GlobalTestData.ReportName);
			sdoResiObj = new ResidentialFlashReport(driver, test);
			// Switch to frame
			//Util.selectFrame("opusReportContainer");
			//subRptRMHuddle"
			sdoFilterObj=new FilterSection(driver, test);
			sdoFilterObj.selectSite(GlobalTestData.siteID);
			sdoFilterObj.selectLOB(GlobalTestData.LOBResidential);
			sdoFilterObj.verifyReportTitle(GlobalTestData.expectedResidentialFlashReportTitle);
			sdoFilterObj.selectFromDate(GlobalTestData.FromDate);
			sdoFilterObj.selectToDate(GlobalTestData.ToDate);
			sdoFilterObj.selectGO();
			
			String downloadFilePath=systemDir + "\\downloads";
			//Click on the PDF and verify column names for Resi
			sdoResiObj.verifyPDF_ColumnNames(downloadFilePath,GlobalExpectedColumns.expectedResidentialFlashReportAllColumnNames);
			
			//Select RO LOB
			//Util.switchToDefaultWindow();
			//Util.selectFrame("opusReportContainer");
			sdoFilterObj.selectLOB(GlobalTestData.LOBRollOff);
			sdoFilterObj.selectGO();
			
			sdoResiObj.verifyPDF_ColumnNames(downloadFilePath,GlobalExpectedColumns.expectedRollOffFlashReportAllColumnNames);
			driver.quit();
			
		}	
			
	}


}
