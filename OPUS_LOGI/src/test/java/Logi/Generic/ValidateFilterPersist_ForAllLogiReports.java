package Logi.Generic;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.EfficiencyPerformanceReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class ValidateFilterPersist_ForAllLogiReports extends BaseClass {
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	EfficiencyPerformanceReport effPerformanceObj;
	private String ClockOutTime;
	@Test(groups = "Opus", dataProvider = "Filter_Data")
	public void Validation_FilterPersist_ForAllLogiReports(String Scenario,String RunMode,String Tier,String Area,String BU,String SiteID, String LOB,String SubLOB,String Route,String Driver,
			String RouteManager,String RouteType,String ReportGroup,String FromDate, String ToDate ) throws Exception {	
		
		if(Scenario.equalsIgnoreCase("ValidateFilterPersist_ForAllLogiReports") & RunMode.equals("Yes"))
		{
		
		    test = Report.testCreate(extent, test, "Validate_EfficiencyPerformanceReport_RO");
		    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			// Select RM Dashboard
			sdiMegaObj = new HomePage(driver, test);
			sdiMegaObj.ClickOnReport("Route Summary");
			sdoFilterObj = new FilterSection(driver, test);
			
			
			
			sdoFilterObj.selectTier(Tier);
			sdoFilterObj.selectArea(Area);
			sdoFilterObj.selectBU(BU);
			sdoFilterObj.selectSite(SiteID);
			sdoFilterObj.selectMultiLOB(LOB);
			sdoFilterObj.selectSubLOB(SubLOB);
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			
			
			sdoFilterObj.validateFilterPersist("Route Summary","Efficiency Summary");
			sdoFilterObj.validateFilterPersist("Route Summary","Pre-Route Summary");
			sdoFilterObj.validateFilterPersist("Route Summary","Post-Route Summary");
			sdoFilterObj.validateFilterPersist("Route Summary","Idle Summary");
			sdoFilterObj.validateFilterPersist("Route Summary","Disposal Cycle Summary");
			sdoFilterObj.validateFilterPersist("Route Summary","Sequence Compliance Summary");
			sdoFilterObj.validateFilterPersist("Route Summary","Dispatch Summary");
			sdoFilterObj.validateFilterPersist("Route Summary","Service Exceptions Summary");
			sdoFilterObj.validateFilterPersist("Route Summary","Summary Dashboard");
			sdoFilterObj.validateFilterPersist("Route Summary","Pre/Post/Idle Summary Dashboard");
			
			Util.openReportFromMegaMenu("Efficiency Performance");
			
			sdoFilterObj.selectSite(SiteID);
			sdoFilterObj.selectLOB(LOB);
			sdoFilterObj.selectSubLOB(SubLOB);
			sdoFilterObj.selectRouteManager(RouteManager);
			sdoFilterObj.selectRouteType(RouteType);
			sdoFilterObj.selectRoute(Route);
			sdoFilterObj.selectDriver(Driver);
			sdoFilterObj.selectReportGroup(ReportGroup);
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			
			sdoFilterObj.validateFilterPersist("Efficiency Performance","Pre-Route Detail");
			sdoFilterObj.validateFilterPersist("Efficiency Performance","Post-Route Detail");
			sdoFilterObj.validateFilterPersist("Efficiency Performance","Idle Detail");
			sdoFilterObj.validateFilterPersist("Efficiency Performance","Disposal Cycle Detail");
			sdoFilterObj.validateFilterPersist("Efficiency Performance","Downtime Detail");
			sdoFilterObj.validateFilterPersist("Efficiency Performance","Customer Property Detail");
			sdoFilterObj.validateFilterPersist("Efficiency Performance","Sequence Compliance Detail");
			sdoFilterObj.validateFilterPersist("Efficiency Performance","Service Exceptions Detail");
			sdoFilterObj.validateFilterPersist("Efficiency Performance","Flash Report");
			sdoFilterObj.validateFilterPersist("Efficiency Performance","RM Dashboard");
			
			
			driver.close();
		 }	
		}
	@DataProvider(name = "Filter_Data")
	public Object[][] testData() throws Exception {
		Excel ex = new Excel();
		Object[][] arrayObject = ex.getTableArray("R:\\Automation\\OPUS_LOGI\\TestData\\Opus_LOGI_TestData.xlsx",
				"Filter_Data");
		return arrayObject;
	}

}
