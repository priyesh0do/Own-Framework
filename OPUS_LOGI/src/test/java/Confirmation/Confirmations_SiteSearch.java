package Confirmation;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.Test;

import ObjectRepository.General.FilterSection;
import ObjectRepository.General.Login;
import Opus.BaseClass;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class Confirmations_SiteSearch  extends BaseClass{

	Login login;
	
	@Test(priority=1,groups="OpusConfirmations")
	public void Verify_Confirmations_SiteSearch() throws InterruptedException, IOException, ParseException {
		
		
		//sitedetail.Opus_Confirmations_FindDayBetweenDates("11/08/2017","11/18/2018");  //Date Must be in MM/dd/YYYY format oldDate Then Current Date
		
		  test = Report.testCreate(extent, test, "Confirmations_SiteSearch ");
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
		
			
			//Opus_Confirmations_Site_Detail sitedetail=new Opus_Confirmations_Site_Detail(driver, test);
			FilterSection sitedetail=new FilterSection(driver, test);
			
			sitedetail.RedirectedtoConfirmation();
			Thread.sleep(2000);
			
			//sitedetail.Opus_Confirmation_Reset();
			
			sitedetail.Opus_Confirmations_SelectSite("2878");
			Thread.sleep(2000);
			
			
			/*sitedetail.Opus_Confirmations_SelectLOB("", "Com", "Res", "Rolloff", "Bagster");
			Thread.sleep(2000);
			
			sitedetail.Opus_Confirmations_SelectConfirmationStatus("", "Confirmed", "Flagged", "Open");
			Thread.sleep(2000);
			
			
			sitedetail.Opus_Confirmations_FromDate("10/30/2017");
			Thread.sleep(2000);
			
			sitedetail.Opus_Confirmations_ToDate("11/01/2017");
			Thread.sleep(2000);*/
			
			sitedetail.Opus_Confirmations_selectDateFromDatePicker("10/31/2017");
			Thread.sleep(2000);
			sitedetail.Opus_Confirmations_selectDateToDatePicker("11/07/2017");
			Thread.sleep(2000);
			sitedetail.Opus_Confirmations_SelectGo();
			Thread.sleep(2000);
			/*sitedetail.Opus_Confirmations_VerifyRouteCount();
			Thread.sleep(2000);
			sitedetail.Opus_Confirmations_VerifyRecordsInsideofRoute();
			Thread.sleep(2000);
		    sitedetail.Opus_Confirmations_crazysearch("working");
		    Thread.sleep(2000);
		    sitedetail.Opus_Confirmations_ClickOnFirstRoute();*/
			
			/*sitedetail.Opus_Confirmations_PaginationFunctionality();
			Thread.sleep(2000);*/
			/*driver.switchTo().frame("opusReportContainer");
			Util.FilterSorting("tblRouteSummary", "LOB");
			Thread.sleep(2000);*/
			/*sitedetail.Opus_Confirmations_VerifyConfirmationStatus("All");
			Thread.sleep(2000);*/
			
			/*sitedetail.Opus_Confirmations_VerifyConfirmationStatus("Confirmed");
			Thread.sleep(1000);*/
			//sitedetail.Opus_Confirmations_VerifyWorkStatus();
			/*sitedetail.Opus_Confirmations_VerifyLOB();
			Thread.sleep(1000);*/
			/*sitedetail.Opus_Confirmations_VerifyRoute();
			Thread.sleep(1000);*/
			/*sitedetail.Opus_Confirmations_VerifyDriveDetails();
			Thread.sleep(1000);*/
			sitedetail.Opus_Confirmations_Verify_VehicleIDDetails();
			Thread.sleep(1000);
			
	}
	
	
	
}
