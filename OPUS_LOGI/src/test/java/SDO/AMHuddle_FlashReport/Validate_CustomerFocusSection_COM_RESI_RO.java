package SDO.AMHuddle_FlashReport;



import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.SDO.RMDashboardReport;
import Opus.BaseClass;

import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalTestData;

public class Validate_CustomerFocusSection_COM_RESI_RO extends BaseClass {

	Login login;
	HomePage sdiMegaObj;
	RMDashboardReport rmDashboardObj;
	FilterSection sdoFilterObj;
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify Customer Focus Section for all the LOBs
	 * 
	 */
	@Test(groups = "Opus",dataProvider ="rmDashboardReport")
	public void ValidateCustomerFocusSection_COM_RESI_RO(String siteID, String Date) throws Exception {
		
		if (!Util.isTestCaseExecutable("Validate_customerFocusSection_COM_RESI_RO",GlobalVariables.sdoTestCase))
		{
			test = Report.testCreate(extent, test, "Validate_customerFocusSection_COM_RESI_RO");
			Report.SkipTest(test, "Skipping the test as runmode is NO");			
		}
		else
		{
		//Login into OPUS Application
		test = Report.testCreate(extent, test, "Validate_CustomerFocusSection_COM_RESI_RO");
		driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
		login = new Login(driver, test);
		login.LoginSDI(configProp.getProperty("userId"),configProp.getProperty("Password"));
		System.out.println("Login successfully done");
		//Selecting the report
		sdiMegaObj = new HomePage(driver,test);
		sdiMegaObj.ClickOnReport(GlobalTestData.RMDashBoardReport);
		
		//Select the site from the filter section
		sdoFilterObj = new FilterSection(driver,test);
		sdoFilterObj.selectSite(siteID);
		
		//Select the Date from the filter section
		sdoFilterObj.selectToDate(Date);
		
		//Hit Go button
		sdoFilterObj.selectGO();
		
		//Verify if the Customer Focus Table is getting displayed or not
		RMDashboardReport rmDashboardObj = new RMDashboardReport(driver,test);
		Util.pageScroll(0, 800);
		Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
		//rmDashboardObj.VerifyCustomerFocusTable();
		
		//Verify the column names in the Customer focus table
		//rmDashboardObj.VerifyColumnName_CustomerFocusTable(GlobalTestData.ArColumnName);
		
	/*	//Sample DB Connection - working fine
		String ezpay = DB.GetEzpayID(GlobalTestData.companyID, GlobalTestData.companynumber);
		System.out.println(ezpay);*/
		
		//Verify the rows with LOBs are getting displayed
		//rmDashboardObj.VerifyNoOfRows();
		
		//Read the Customer Focus Section Table 
		//rmDashboardObj.ReadCustomerFocusSectionTable();
		rmDashboardObj.ReadCustomerFocusSectionTable();
		
		
		
		
		//verify the values which are getting displayed in the customer focus section
		rmDashboardObj.getDatafromDBandApp();
		
		
		
		}
	
	}
	
	@DataProvider(name = "rmDashboardReport")
	public Object[][] testData() throws Exception {
		Excel ex = new Excel();
		Object[][] arrayObject = ex.getTableArray("R:\\Automation\\OPUS\\OPUS_SDO\\Test Data\\RM DashBoard Report.xlsx","RMDashBoardReport");
				return arrayObject;
		}

}

//Click on the required report

