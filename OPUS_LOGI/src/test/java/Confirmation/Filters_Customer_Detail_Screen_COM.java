package Confirmation;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.Test;

import ObjectRepository.Confirmation.Customer_Details;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.Login;
import Opus.BaseClass;
import SupportLibraries.Report;

public class Filters_Customer_Detail_Screen_COM extends BaseClass {
	
	
Login login;
	
	@Test(priority=1)
	
	public void VerifyFilters_Customer_Detail_Screen_COM() throws InterruptedException, IOException, ParseException {
		
		test = Report.testCreate(extent, test, "Filters_Customer_Detail_Screen_COM ");
		login = new Login(driver, test);
		login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
		System.out.println("Login successfully done");
		
		FilterSection sitedetail=new FilterSection(driver, test);
		Customer_Details cd=new Customer_Details(driver, test);
		sitedetail.RedirectedtoConfirmation();
		Thread.sleep(2000);
		
		//sitedetail.Opus_Confirmation_Reset();
		
		sitedetail.Opus_Confirmations_SelectSite("2878");
		Thread.sleep(2000);
		sitedetail.Opus_Confirmations_selectDateFromDatePicker("10/31/2017");
		Thread.sleep(1000);
		sitedetail.Opus_Confirmations_selectDateToDatePicker("11/07/2017");
		Thread.sleep(1000);
		sitedetail.Opus_Confirmations_SelectLOB("", "Com", "","","");
		Thread.sleep(1000);
		sitedetail.Opus_Confirmations_SelectGo();
		Thread.sleep(2000);
		sitedetail.Opus_Confirmations_VerifyRouteCount();
		Thread.sleep(1000);
		sitedetail.Opus_Confirmations_ClickOnFirstRoute();
		Thread.sleep(1000);
		sitedetail.SeeCustomerDetailsBtn();
		Thread.sleep(1000);
		cd.Opus_Confirmation_VerifyCustomer_Detail_Screen_COM("All", true , false);
		Thread.sleep(1000);
	
	}

}
