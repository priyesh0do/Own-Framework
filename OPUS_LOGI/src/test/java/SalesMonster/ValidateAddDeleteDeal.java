package SalesMonster;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Driver.BrowserOpen;
import ObjectReposiotrySalesMonster.SalesMonster;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;


public class ValidateAddDeleteDeal extends BaseClass{

	SalesMonster salesMonsterObj;	
	@Test(groups = "SalesMonster", dataProvider = "SalesMonsterData")
	public void Validate_SummaryDashboard_ReportValidation_COM(String Scenario,String RunMode) throws Exception {	
	if(Scenario.equalsIgnoreCase("Validate_SalesMonster_Application") & RunMode.equals("Yes"))
		{
		
		    test = Report.testCreate(extent, test, "Validate_SalesMonster");
		    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			salesMonsterObj=new SalesMonster(driver, test);
		    salesMonsterObj.RedirectedtoLoginPage();
		    salesMonsterObj.SalesMonsterLogin(configProp.getProperty("userId"), configProp.getProperty("Password"));
		    
		}			
		
	}
	@DataProvider(name = "SalesMonsterData")
	public Object[][] testData() throws Exception {
		Excel ex = new Excel();
		Object[][] arrayObject = ex.getTableArray(configProp.getProperty("SalesMonsterExcel"),
				configProp.getProperty("salesMonsterDataSheet"));
		return arrayObject;
	}
	
	
	
	
}
