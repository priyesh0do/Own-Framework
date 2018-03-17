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

public class Data_Validation_For_EnR_Stopped_Time extends BaseClass {
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	RollOffFlashReport sdoRollOffObj;
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify Roll off flash report
	 * 
	 */
	
	@Test(groups = "Opus", dataProvider = "rollOffTravelProperty")
	public void DataValidation_For_RO_TicketType_EnR(String ReportName, String siteID, String LOB, String FromDate,
			String ToDate, String Route, String Driver,String Ticket,String TicketType) throws Exception {	
		if (!Util.isTestCaseExecutable("Data_Validation_For_EnR_Stopped_Time",GlobalVariables.sdoTestCase))
		{
			test = Report.testCreate(extent, test, "Data_Validation_For_EnR_Stopped_Time");
			Report.SkipTest(test, "Skipping the test as runmode is NO");			
		}
		else
		{
			if(TicketType.equalsIgnoreCase("E/R"))
			{
			    test = Report.testCreate(extent, test, "Data_Validation_For_EnR_Stopped_Time");
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
				// Click on Roll Off Flash report
				sdoRollOffObj.clickRollOffFlashReport();
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
				Map<String, List<String>> actualEfficiencyTable = sdoRollOffObj.getActualEfficiencyTableData();
				Util.switchToDefaultWindow();
				Util.pageScroll(0, 250);
				Map<String, Map<String,List<String>>> actualTicketInformationTable = sdoRollOffObj.getActualTicketInformationTableData(Route);
				Map<String, Map<String,List<String>>> actualServiceInformationTable = sdoRollOffObj.getActualServiceInformation(Route);
				Map<String, Map<String,List<String>>> actualTravelInformationTable = sdoRollOffObj.getActualTravelInformation(Driver);
				Map<String, Map<String,List<String>>> actualOnPropertyInformationTable = sdoRollOffObj.getActualOnPropertyInformation(Driver);
				Map<String, Map<String,List<String>>> actualDriverDisposalInformationTable = sdoRollOffObj.getActualDriverDisposalInformation(Driver);
				sdoRollOffObj.validateTicketInformationData(actualTicketInformationTable, Ticket);
				sdoRollOffObj.validateTravelInformationData(actualEfficiencyTable,actualTravelInformationTable,actualTicketInformationTable);
				sdoRollOffObj.validateOnPropertyInformationData(actualEfficiencyTable,actualOnPropertyInformationTable,actualTicketInformationTable);
				sdoRollOffObj.validateDisposalInformationData(actualEfficiencyTable,actualDriverDisposalInformationTable,actualTicketInformationTable);
			    driver.quit();
			}

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
