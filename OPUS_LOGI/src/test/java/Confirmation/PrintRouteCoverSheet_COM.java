package Confirmation;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.Test;


import ObjectRepository.General.FilterSection;
import ObjectRepository.General.Login;
import Opus.BaseClass;
import SupportLibraries.Report;

public class PrintRouteCoverSheet_COM extends BaseClass {
	
Login login;
	
	@Test(priority=1)
	
	public void RouteCountsectiononSiteDetailscreen() throws InterruptedException, IOException, ParseException {
		
		test = Report.testCreate(extent, test, "Confirmations_SiteSearch ");
		login = new Login(driver, test);
		login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
		System.out.println("Login successfully done");
		
		FilterSection sitedetail=new FilterSection(driver, test);
		sitedetail.RedirectedtoConfirmation();
		Thread.sleep(2000);
		
		//sitedetail.Opus_Confirmation_Reset();
		
		sitedetail.Opus_Confirmations_SelectSite("2878");
		Thread.sleep(2000);
		sitedetail.Opus_Confirmations_selectDateFromDatePicker("10/31/2017");
		Thread.sleep(1000);
		sitedetail.Opus_Confirmations_selectDateToDatePicker("11/07/2017");
		Thread.sleep(1000);
		sitedetail.Opus_Confirmations_SelectGo();
		Thread.sleep(2000);

		
		sitedetail.VerifyRouteCoverSheet();
		Thread.sleep(2000);
}
}