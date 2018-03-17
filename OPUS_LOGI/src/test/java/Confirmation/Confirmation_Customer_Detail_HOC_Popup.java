package Confirmation;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.Test;

import ObjectRepository.Confirmation.Customer_Details;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.Login;
import Opus.BaseClass;
import SupportLibraries.Report;

public class Confirmation_Customer_Detail_HOC_Popup extends BaseClass {

	    Login login;
	
	@Test(priority=1,groups="ConfirmationDetailsHOCPopup")
	public void Verify_Confirmation_Customer_Detail_HOC_Popup() throws InterruptedException, IOException, ParseException {
		
		    test = Report.testCreate(extent, test, "ConfirmationDetailsHOCPopup ");
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
		
			FilterSection fs=new FilterSection(driver, test);
			Customer_Details cd=new Customer_Details(driver, test);
			fs.RedirectedtoConfirmation();
			Thread.sleep(1000);
			/*fs.Opus_Confirmation_Reset();
			Thread.sleep(1000);*/
			fs.Opus_Confirmations_SelectSite("2878");
			Thread.sleep(1000);
			fs.Opus_Confirmations_SelectLOB("", "Com", "", "","");
			Thread.sleep(1000);
			fs.Opus_Confirmations_SelectConfirmationStatus("", "", "Flagged","");
			Thread.sleep(1000);
			fs.Opus_Confirmations_selectDateFromDatePicker("10/31/2017");
			Thread.sleep(1000);
			fs.Opus_Confirmations_selectDateToDatePicker("11/07/2017");
			Thread.sleep(1000);
			fs.Opus_Confirmations_SelectGo();
			Thread.sleep(1000);
		   fs.SearchHOCandExtraUnitNotZero();
		   Thread.sleep(1000);
		   fs.SeeCustomerDetailsBtn();
		   Thread.sleep(1000);
		   cd.Opus_Confirmation_CustomerDetails("All");
		   Thread.sleep(1000);
			
	}
}
