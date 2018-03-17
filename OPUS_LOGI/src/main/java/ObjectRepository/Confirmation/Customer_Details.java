package ObjectRepository.Confirmation;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.springframework.expression.spel.ast.BooleanLiteral;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import SupportLibraries.Report;
import SupportLibraries.Util;

public class Customer_Details extends RouteDetails{
	

	WebDriver driver;
	ExtentTest test;
	
	
	public Customer_Details(WebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);

}
	///CustomerDetailsTitle
	@FindBy(xpath="//div[@class='titleDiv']/h1[@class='formTitle']")
    WebElement CustomerDetailsTitle;
	
	//BacktoRouteDetail
	  @FindBy(xpath="//a[@class='clickCancel returntoRoute' and contains(text(),'Back to Route Detail')]")
	  WebElement BacktoRouteDetail;
	
	//OrderStatus
	@FindBy(xpath="//*[@id='orderstatus']")
	WebElement OrderStatus;
	
	//HOC chkbox
	@FindBy(xpath="//*[@id='HOC' and @type='checkbox']")
	WebElement HOCchkbox;
	
	//ExtraUnit ChkBox
	@FindBy(xpath="//*[@id='extraunit' and @type='checkbox']")
	WebElement ExtraUnitChkBox;
	
	//GoButton
	@FindBy(xpath="//*[@id='getRouteSummary']")
	WebElement GoButton;
	
	//Reset Btn
	@FindBy(xpath="//*[@id='resetCustomersiteInfo' and @type='button']")
	WebElement Reset;
	
	////////////////Elements inside of General Information//////////////////
	
	//Site
	@FindBy(xpath="//*[@id='haulingSite']")
	WebElement Site;
	
	//Route
	@FindBy(xpath="//*[@id='route']")
	WebElement Route;
	
	//Driver
	@FindBy(xpath="//*[@id='driver']")
	WebElement Driver;
	
	//LOB
	@FindBy(xpath="//*[@id='LOB']")
	WebElement LOB;
	
	//ServicStartDate
	@FindBy(xpath="//*[@id='serviceDate' and @name='serviceDate']")
	WebElement ServicStartDate;
	
	//ConfirmationStatus
	@FindBy(xpath="//*[@id='status' and @name='status']")
	WebElement ConfirmationStatus;
	
	//////////////////Elements Present Inside of Customer Information////
	
	//RefreshCustInfo
	@FindBy(xpath="//*[@id='refreshCustomerInfo']")
	WebElement RefreshCustInfo;
	
	//CrazySearch
	@FindBy(xpath="//*[@id='tblCustomerInfo_filter']/label/input")
	WebElement CrazySearch;
	
	//OrderStatus
	@FindBy(xpath="//*[@id='tblCustomerInfo']/tbody/tr[1]/td[3]/select")
	WebElement OrderStatusCustomerInfo;
	
	//SaveBtn
	@FindBy(xpath="//*[@id='confirmBtnSave']")
	WebElement SaveBtn;
	
	//ExtraUnit Link
	@FindBy(xpath="//*[@id='EULink']")
	WebElement ExtraUnitCustInfo;
	
	//HOC CustInfo
	@FindBy(xpath="//*[@id='tblCustomerInfo']/tbody/tr/td[14]/a")
	WebElement HOCCustInfo;
	
	//////Extra Unit Pop Up////////
	
	//ExtraUnitPopUpAddBtn
	@FindBy(xpath="//*[@id='addButton']")
	WebElement ExtraUnitPopUpAddBtn;
	
	//ExtraUnitPopUpSaveBtn
	@FindBy(xpath="//*[@id='EUnit_save' and @type='button']")
	WebElement ExtraUnitPopUpSaveBtn;
	
	//ExtraUnitPopUpCloseSign
	@FindBy(xpath="//*[@class='closeEUimg']/span")
	WebElement ExtraUnitPopUpCloseSign;
	
	//Table
	@FindBy(xpath="//*[@id='tblCustomerInfo_wrapper']/table/tbody")
	WebElement Table;
	
	////////////////////////HOC pop Up///////////////////
	
	//CloseHocPopUP
	@FindBy(xpath="//*[@class='closeimg']/span")
	WebElement CloseHocPopUP;
	
	//////////////Errors//////////
	@FindBy(xpath="//*[@id='errorDiv']")
	WebElement Errors;
	
	////////////////////////HOC pop up//////////////////
	//Service Description 
	@FindBy(xpath="//*[@id='HOC_sdesc0' and @class='HOC_sdesc']")
	WebElement ServiceDesctiptionBox;

 //////////////////HOC Description /////////////////////
	@FindBy(xpath="//*[@id='HOC_desc0' and @class='HOC_desc']")
	WebElement HOCDescription;
	
	/////////////////// HOC Time ///////////////////
	@FindBy(xpath="//*[@id='HOC_time0' and @type='text']")
	WebElement HOCTime;
	
	////////////////Negative Confirm/////////////
	@FindBy(xpath="//*[@id='neg_conf0' and @type='checkbox']")
	WebElement NegativeConfirm;
	
	//////////Pictures///////////
	@FindBy(xpath="//*[@id='pics0' and @type='checkbox']")
	WebElement Pictures;
	
	////////////SafetyIssues/////////////////
	@FindBy(xpath="//*[@id='safety_issues0' and @type='checkbox']")
	WebElement SafetyIssues;
	
	///////////HOC Pop up Close//////////////////////
	@FindBy(xpath="//*[@class='ui-icon ui-icon-close HOCclose']")
	WebElement HOCPopUpClose;
	
	/////////Extra Units Pop Up Elements ////////////////
	///////SerialNumber
	@FindBy(xpath="//*[@id='EU_serv0' and @type='text']")
	WebElement SerialNumberValue;
	
	////////ExtraUnit/////////////
	@FindBy(xpath="//*[@id='EU_EUval0' and @type='text']")
	WebElement ExtraUnitValue;
	
	///////UOM////////////////
	@FindBy(xpath="//*[@id='EUUOM0']") 
	WebElement UOMOptions;
	
	////////CloseExtraUnitPopUp//////
	@FindBy(xpath="//*[@class='ui-icon ui-icon-close EUclose']")
	WebElement CloseExtraUnitPopUp;
	
	////FirstRow
	@FindBy(xpath="//*[@id='tblCustomerInfo']/tbody/tr[2]")
	WebElement FirstRow;
	
	//ExtraunitinPopup
	@FindBy(xpath="//*[@id='EU_EUval0' and @type='text']")
     WebElement ExtraunitinPopup;	
	
	//ExtraUnitPopUpSave
	@FindBy(xpath="//*[@id='EUnit_save' and @type='button']")
	WebElement ExtraUnitPopUpSave;
	
	//ResponseAlert
	@FindBy(xpath="//*[@id='response_alert']")
	WebElement ResponseAlert;
	
	//////ExtraUnitFirst
	@FindBy(xpath="//*[@id='tblCustomerInfo']/tbody/tr[1]/td[12]/input")
	WebElement ExtraUnitFirst;
	
	///////HOCFirst
	@FindBy(xpath="//*[@id='tblCustomerInfo']/tbody/tr[1]/td[14]/a")
	WebElement HOCFirst;
	
	////////SaveFirst
	@FindBy(xpath="//*[@id='tblCustomerInfo']/tbody/tr[1]/td[16]/a")
	WebElement SaveFirst;
	
	//////////ResponseAlertOk
	@FindBy(xpath="//*[@id='alertOk' and @type='button']")
	WebElement ResponseAlertOk;
	
	////////////OrderStatusText
	@FindBy(xpath="//*[@id='tblCustomerInfo']/tbody/tr[1]/td[3]/select/option[@selected='selected']")
	WebElement OrderStatusText;
	
	//////////FirstCustomerID
	@FindBy(xpath="//*[@id='tblCustomerInfo']/tbody/tr[1]/td[4]")
	WebElement FirstCustomerID;
	
	////OrderStatusBox
	@FindBy(xpath="//*[@id='tblCustomerInfo']/tbody/tr[1]/td[3]/select")
     WebElement OrderStatusBox;
	
	////CustinfoBody
	@FindBy(xpath="//*[@id='tblCustomerInfo']/tbody")
	WebElement CustinfoBody;
	
	///MealBody
	@FindBy(xpath="//*[@class='mealInfo']")
	WebElement MealBody;
	
	public void Opus_Confirmation_CustomerDetails(String Status) throws InterruptedException, IOException {
	try {	 
	  driver.switchTo().frame("opusReportContainer");
	  
	  Thread.sleep(2000);
	  try {
		Util.SelectOption(driver, OrderStatus, Status);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  GoButton.click();
	  Thread.sleep(3000);
      driver.switchTo().defaultContent();
      Thread.sleep(2000);
      Util.PageScrollDown(0,1000);
      
      driver.switchTo().frame("opusReportContainer");
	     List<WebElement>Records=Table.findElements(By.tagName("tr"));
	     String ExtraUnit = null;
   	  int ExtraUnitCount = 0;
	    // System.out.println("Total Numbers of records > " + Records + " when the Status is >"+ Status );
	     //////////////////////Validate HOC Pop up Records ///////////////////////
	     for (int i = 1; i <=Records.size(); i++) {
				    	 
	         String HOC=driver.findElement(By.xpath("//*[@id='tblCustomerInfo']/tbody/tr["+i+"]/td[14]")).getText();
	         System.out.println("HOC Text is >>" + HOC);
	         
	    	  int HOCcount=Integer.parseInt(HOC);
	    	  if (HOCcount > 0) {
	    		  driver.findElement(By.xpath("//*[@id='tblCustomerInfo']/tbody/tr["+i+"]/td[14]/a")).click();
	    		  Thread.sleep(1000);
	    		  /////////Validate Services Description////////////////
	    		  if (ServiceDesctiptionBox.isDisplayed() && HOCDescription.isDisplayed() &&  HOCTime.isDisplayed() && NegativeConfirm.isDisplayed() && Pictures.isDisplayed() && SafetyIssues.isDisplayed()) {
	    			 String ServiceBoxText= ServiceDesctiptionBox.getText();
	    			 String	 HocBoxText= HOCDescription.getText();
	    			 String HocTimeText= HOCTime.getAttribute("value");
	    			 System.out.println("Data Present Inside of HOC Pop is > " +ServiceBoxText + "and " + HocBoxText  + "and " + HocTimeText);
	    			  
				}
	    		  else {
					System.out.println("No Data Present Inside of HOC Pop up");
				}
	    		  HOCPopUpClose.click();
	    		  Thread.sleep(1000);
	    	     	  
	    		  
			}	
	    	  else {
				System.out.println("Unable to found HOC");
			}
	    	  
	    	///////////////////////////Validate  Extra Unit Pop up/////////////////
	    	  
	    	  Util.switchToDefaultWindow();
	    	  Util.selectFrame("opusReportContainer");
	    	  /*String ExtraUnit;
	    	  int ExtraUnitCount = 0;*/
	    	  if (driver.findElements(By.xpath("//*[@id='tblCustomerInfo']/tbody/tr["+i+"]/td[12]")).size()>0)
	    	  {
	    	  ExtraUnit=driver.findElement(By.xpath("//*[@id='tblCustomerInfo']/tbody/tr["+i+"]/td[12]/input")).getAttribute("value");
	    	   System.out.println("Extra Unit Count is >>" + ExtraUnit );
	    	   ExtraUnitCount=Integer.parseInt(ExtraUnit);
	    	  }
	    	 
	    	 if (ExtraUnitCount >0) {
				
	    		 driver.findElement(By.xpath("//*[@id='tblCustomerInfo']/tbody/tr["+i+"]/td[12]/input")).click();
	    		 Thread.sleep(1000);
	    		 
	    		 if (SerialNumberValue.isDisplayed() && ExtraUnitValue.isDisplayed() && UOMOptions.isDisplayed() ) {
					
	    			String SerNum=SerialNumberValue.getAttribute("value");
	    			String ExtUni=ExtraUnitValue.getAttribute("value");
	    			
	    			Select dropdown= new Select(UOMOptions);
	    			WebElement option = dropdown.getFirstSelectedOption();
	    			 String DropDownValue=option.getText();
	    			System.out.println("Elements present inside of Extra unit pop is >" + SerNum + " and " +ExtUni+ " and " +  DropDownValue);
				}
	    		 
	    		 else {
					System.out.println("Unable to Found Records inside of ExtraUnit pop up");
				}
	    		 
	    		 CloseExtraUnitPopUp.click();
	    		 Thread.sleep(2000);
	    		 break;
	    		 
			}
	    	 
	    	 else {
				System.out.println("Unable to found Extra Unit");
			}
	    	  
	    	 
		}
	  
	 
	  Thread.sleep(1000);
	  driver.switchTo().defaultContent();
	}
	  catch(Exception e)
	   {
	   	Report.FailTest(test, e.getMessage());
	   	Report.FailTest(test, "Unable to Verify Customer Details" );
	   }
 
 }
	
	
	public void Opus_Confirmation_VerifyCustomer_Detail_Screen_COM(String Status, boolean  HOC , boolean ExtraUnit ) throws InterruptedException, IOException {
		
	try {	
		driver.switchTo().frame("opusReportContainer");
		Thread.sleep(1000); 
		Util.SelectOption(driver, OrderStatus, Status);
		Thread.sleep(1000);
		if (HOC) {
			HOCchkbox.click();
			Thread.sleep(1000);
		}
		else {
			System.out.println("Not Clicked on HOC Chk box");
		}
		if (ExtraUnit) {
			ExtraUnitChkBox.click();
			Thread.sleep(1000);
		}
		
		else {
			System.out.println("Not Clicked on ExtraUnit Check box");
		}
		
		
		GoButton.click();
		Thread.sleep(2000);
		
		if (driver.findElements(By.xpath("//tr[@class='default']/td[contains(text(),'No  Customer Summary found.')]")).size()==0) {
			CrazySearch.click();
			Thread.sleep(1000);
			//System.out.println("Records is available then only Crazy search filter is visible");
			Report.PassTest(test, "Records is available and Crazy search filter is visible");
		}
		
		else {
			//System.out.println("Records is not available thats why Crazy search filter is not visible");
			Report.InfoTest(test, "Records is not available & Crazy search filter is not visible");
		}
		driver.switchTo().defaultContent();
	}
		catch(Exception e)
		   {
		   	Report.FailTest(test, e.getMessage());
		   	Report.FailTest(test, "Unable to Verify Customer_Detail_Screen_COM" );
		   }
		
	}
	
	public void Opus_Confirmation_VerifyCustomer_Information_Customer_Detail_Screen_COM(String Status , String ExtraUnit) throws InterruptedException{
		try {
		driver.switchTo().frame("opusReportContainer");
		Thread.sleep(1000);
		///////////////////Validate Data in General Information Section ////////////////////
		for (int i = 3; i <=8; i++) {
			String ReadOnlyField=driver.findElement(By.xpath("//*[@class='generalInfowrap']/div["+i+"]/input[@type='text']")).getAttribute("value");
			//System.out.println("Read Only Fields Data >>" + ReadOnlyField);
			Report.PassTest(test, "Read Only Fields Data >>" + ReadOnlyField);
		}
		
		//////////////////////////////// Select Order Status and click on go button/////////////
		try {
			Util.SelectOption(driver, OrderStatus, Status);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		   GoButton.click();
		  Thread.sleep(3000);
		  ////////////////// If Records is present inside of Table Click on first Extra unit change the data and click on save////
		  if (driver.findElements(By.xpath("//tr[@class='default']/td[contains(text(),'No  Customer Summary found.')]")).size()==0) {
				CrazySearch.click();
				Thread.sleep(1000);
				
				Report.PassTest(test, "Records is available and Crazy search filter is also visible");
				
				////Select first Extra Unit and change the data in extra unit and click on save
			String Before=driver.findElement(By.xpath("//*[@id='tblCustomerInfo']/tbody/tr[1]/td[12]/input")).getAttribute("value");
			 Report.InfoTest(test, "Before Change Records is > " + Before);
			driver.findElement(By.xpath("//*[@id='tblCustomerInfo']/tbody/tr[1]/td[12]/input")).click();
         Thread.sleep(1000);
         ExtraunitinPopup.clear();
         Thread.sleep(1000);
         ExtraunitinPopup.sendKeys(ExtraUnit);
         ExtraUnitPopUpSave.click();
         Util.waitForElement(driver,ResponseAlert);
        String SaveMsg=ResponseAlert.getText();
        
        Report.InfoTest(test, "Data is Saved > " + SaveMsg);
        Thread.sleep(3000);
        CrazySearch.click();
        Thread.sleep(1000);
        RefreshCustInfo.click();
        Thread.sleep(1000);
        String After=driver.findElement(By.xpath("//*[@id='tblCustomerInfo']/tbody/tr[1]/td[12]/input")).getAttribute("value");
		 ////////////////////Validate data is reflected as same as input data /////////
		 Report.InfoTest(test, "Affter Change Records is > " + After);
       if (After.equals(ExtraUnit)) {
		
		Report.PassTest(test, "Data is validated sucessfully");
	        }
       else {
		
		Report.FailTestSnapshot(test, driver, "Unable to Validate data", "Unabletosavedata");
	}
       
			}
		  else {
			System.out.println("Unable to found any record please check search criteria");
		}
		  
		  
	}
		  catch(Exception e)
		   {
		   	Report.FailTest(test, e.getMessage());
		   	Report.FailTest(test, "Unable to Verify Customer_Information_Customer_Detail_Screen_COM" );
		   }
		
		
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		
	}
	
	public void Opus_Confirmation_VerifyEdit_Confirmation_Customer_Details_COM(String OdrStatus  ,String SelectUOM) throws InterruptedException, IOException {
	
		try {
		
		driver.switchTo().frame("opusReportContainer");
		Thread.sleep(1000);
  		try {
			Util.SelectOption(driver, OrderStatus, OdrStatus);
			Report.PassTest(test, "Selected order status " + OdrStatus);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Report.FailTestSnapshot(test, driver, "Unable to select Order Status", "unableToSelectOrderStatus>>"+ OdrStatus );
		}
  		GoButton.click();
  		Report.PassTest(test, "Clicked On Go button");
		Thread.sleep(5000);
		
		if (driver.findElements(By.xpath("//tr[@class='default']/td[contains(text(),'No  Customer Summary found.')]")).size()==0) {
			////////////////////Select Order Status Inside of Customer Information/////
			  String OrdrStatusText=OrderStatusText.getText();
			  //System.out.println("Order Status Text > " + OrdrStatusText );
			  Report.PassTest(test, "Before Change Order Status  > " + OrdrStatusText);
			  Thread.sleep(1000);
			 String CustIdTExt=FirstCustomerID.getText();
			 //System.out.println("Cust Id Text >" + CustIdTExt );
			 Report.PassTest(test, "Customer Id >" + CustIdTExt);
			 
			 Thread.sleep(1000);
			 if (OrdrStatusText.equals("SERVICED")) {
				 Thread.sleep(1000);
				 Util.SelectOption(driver, OrderStatusCustomerInfo, "NOT SERVICED");
			}
			 if (OrdrStatusText.equals("NOT SERVICED")) {
				 Thread.sleep(1000);
				 Util.SelectOption(driver, OrderStatusCustomerInfo, "INCOMPLETE");
			}
			 if (OrdrStatusText.equals("INCOMPLETE")) {
				 Thread.sleep(1000);
				 Util.SelectOption(driver, OrderStatusCustomerInfo, "SERVICED"); 
			}
			
			 Thread.sleep(2000);
			 
		     List<WebElement> custinfobody=CustinfoBody.findElements(By.tagName("tr"));
			 
			 for (int i = 1; i <custinfobody.size();) {
				String Extraunitcount=driver.findElement(By.xpath("//*[@id='tblCustomerInfo']/tbody/tr["+i+"]/td[12]/input")).getAttribute("value");
				//System.out.println("Extra Unit count >" + Extraunitcount);
				Report.PassTest(test, "Extra Unit count >" + Extraunitcount);
				int extraunitvalue=Integer.parseInt(Extraunitcount);
				if (extraunitvalue > 0) {
					ExtraUnitFirst.click();
					Report.PassTest(test,"Selected Extra unit from first Row");
					Thread.sleep(1000);
					break;
				}
				else {
					System.out.println("Extra unit is zero");
					i++;
				}
						
			} 
			 
						
			Thread.sleep(1000);
			String ExtraUnitPopupTextbefore=ExtraunitinPopup.getAttribute("value");
			Report.PassTest(test, "ExtraUnitPopupTextbefore>" + ExtraUnitPopupTextbefore);
			      int ExtraValue=Integer.parseInt(ExtraUnitPopupTextbefore);
			     int ExtraValueAfterIncrement=ExtraValue+2;
			     String ExtraUnitPopupTextAfter=Integer.toString(ExtraValueAfterIncrement);
			      Report.PassTest(test, "After Text in Extra unit pop up >>" + ExtraUnitPopupTextAfter);
			     ExtraunitinPopup.clear();
			     ExtraunitinPopup.sendKeys(ExtraUnitPopupTextAfter);
			Thread.sleep(2000);
			////////////// Change Value of UOM drop down///////			
			try {
				Util.SelectOption(driver, UOMOptions, "--");
				Util.SelectOption(driver, UOMOptions, SelectUOM);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.sleep(2000);
			ExtraUnitPopUpSave.click();
			Thread.sleep(5000);
		    /*System.out.println(ResponseAlertOk.getText());
			Thread.sleep(5000);*/
			///////////////////Validate Alert message without saving////////////
			BacktoRouteDetail.click();
			Thread.sleep(10000);
			//Util.waitForElementInvisible(driver, ResponseAlert);
			if (ResponseAlert.isDisplayed()) {
				
				//System.out.println(ResponseAlert.getText());
				Report.PassTest(test, ResponseAlert.getText());
				ResponseAlertOk.click();
			}
			else {
				//System.out.println("Unable to found Respose alert");
				Report.FailTestSnapshot(test, driver, "Unable to found Respose alert", "UnabletofoundResposealert");
			}
			//////////Refresh Cust Info
			/*RefreshCustInfo.click();
			Thread.sleep(1000);*/
			/////Click on Final Save and check response alert msg
			SaveFirst.click();
		   	Thread.sleep(2000);
		   //System.out.println(ResponseAlert.getText());
		   Report.PassTest(test, ResponseAlert.getText());
		   ////////////Refresh Data//////////////////
		   Thread.sleep(1000);
		   RefreshCustInfo.click();
			Thread.sleep(1000);
		   //////////////////Go to crazy search
		   CrazySearch.sendKeys(CustIdTExt);
		   Thread.sleep(1000);
		   String AfterSearchStatus=OrderStatusText.getText();
		   //System.out.println("After Search Status is >> "  + AfterSearchStatus );
		   if (!AfterSearchStatus.equals(OrdrStatusText)) {
			//System.out.println("Complete Pass");
			Report.PassTest(test, "Validated Order status & Alert messages");
		}
		   else {
			//System.out.println("Complete Fail");
			Report.FailTestSnapshot(test, driver, "Unable to Validated Order status & Alert messages ", "UnabletoValidateOrderStatus");
		}
		   
			
			
			Report.PassTest(test, "Pass Confirmation_Customer_Details_COM ");
		}
	
		else {
			//System.out.println("Records is not available thats why Crazy search filter is not visible");
			Report.InfoTest(test, "Fail Confirmation_Customer_Details_COM");
		}
	}
		 catch(Exception e)
		   {
		   	Report.FailTest(test, e.getMessage());
		   	Report.FailTest(test, "Unable to Verify Edit_Confirmation_Customer_Details_COM" );
		
	}
		  driver.switchTo().defaultContent();
	
}

    public void Opus_confirmation_Verify_Search_Customer_Data_For_Route_On_Confirmation_Customer_Detail_Screen_COM(String Status , boolean HOC, boolean ExtraUnit) throws IOException, InterruptedException {
try {
	           driver.switchTo().frame("opusReportContainer");
	           
	           Thread.sleep(2000);	           
	           if (HOC && ExtraUnit) {
	        	   Reset.click();
	        	   Thread.sleep(1000);
	        	   Util.SelectOption(driver, OrderStatus, Status);
	        	   Thread.sleep(1000);
	        	   HOCchkbox.click();
	        	   Thread.sleep(1000);
	        	   ExtraUnitChkBox.click();
	        	   Thread.sleep(1000);
	        	   GoButton.click();
	        	   Thread.sleep(2000);
	        	   if (driver.findElements(By.xpath("//tr[@class='default']/td[contains(text(),'No  Customer Summary found.')]")).size()==0) {
	        		   int sum =0;
	    	           int sum1 =0;
	        		   //////////////////find HOC & Extra unit count 
	        		   List<WebElement> Records=CustinfoBody.findElements(By.tagName("tr"));
	        		   //System.out.println(Records.size());
	        		   Report.InfoTest(test, "Total Numbers of Records when HOC and Extra Unit both are checked and Status is  > " +Status +">>=" + Records.size());
	        		   for (int i = 1; i <=Records.size(); i++) {
	        			   /////Find Extra unit count
	        			     String Extraunitcount=driver.findElement(By.xpath("//*[@id='tblCustomerInfo']/tbody/tr["+i+"]/td[12]/input")).getAttribute("value");
	        			     int[] num = new int[Records.size()];
	        			     num [i-1]=Integer.parseInt(Extraunitcount);
	        			      //System.out.println("Add ExtraUnitIntCount >>" + num [i-1] );
	        			      Report.InfoTest(test, "Extra unit count is >>" + num [i-1]);
	        			      sum= sum + num [i-1];
	        			      Thread.sleep(2000);
	        			      
	        			 /////////////Find HOC Count
	        			      String HOCcount=driver.findElement(By.xpath("//*[@id='tblCustomerInfo']/tbody/tr["+i+"]/td[14]/a")).getText();
	        			      int[] num2 = new int[Records.size()];
						        num2 [i-1] =Integer.parseInt(HOCcount); 
						       // System.out.println("Add HOC >>" + num2 [i-1]);
						        Report.InfoTest(test, "HOC Count is >>" + num2 [i-1]);
						        sum1= sum1 + num2 [i-1];
						        Thread.sleep(2000);
					} 
	        		   //System.out.println("Total Count of extra unit > " + sum);
	        		   Report.PassTest(test,"Total Count of extra unit > " + sum);
	        		   //System.out.println("Total Count of HOC unit > " + sum1);
	        		   Report.PassTest(test, "Total Count of HOC unit > " + sum1);
	        		   Report.PassTest(test, "Passed When HOC and Extra unit Checked");
			}
	        	   else {
					//System.out.println("Else Part Program fail");
					Report.FailTestSnapshot(test, driver, "Not found any record", "NORecords");
				}
	               
	             	 
	         }
	          /////////////////////////////////////When only HOC is checked//////////////////// 
	          if (HOC && !ExtraUnit)
	          {
	        	  Reset.click();
	        	   Thread.sleep(1000);
	        	  Util.SelectOption(driver, OrderStatus, Status);
	        	   Thread.sleep(1000);
	        	   HOCchkbox.click();
	        	   Thread.sleep(1000);
	        	   GoButton.click();
	        	   Thread.sleep(2000);
	        	   if (driver.findElements(By.xpath("//tr[@class='default']/td[contains(text(),'No  Customer Summary found.')]")).size()==0) {
	        		   int sum1=0;
	        		   //// Find HOC count ///////////////////
	        		   List<WebElement> Records=CustinfoBody.findElements(By.tagName("tr"));
	        		  // System.out.println(Records.size());
	        		   Report.InfoTest(test, "Total Number of Records when HOC is checked and status is > " + Status + ">>" + Records.size() );
	        		   
	        		   for (int i = 1; i <=Records.size(); i++) {
	        			   String HOCcount=driver.findElement(By.xpath("//*[@id='tblCustomerInfo']/tbody/tr["+i+"]/td[14]/a")).getText();
	        			      int[] num2 = new int[Records.size()];
						        num2 [i-1] =Integer.parseInt(HOCcount); 
						      // System.out.println("Add HOC >>" + num2 [i-1]);
						       Report.InfoTest(test, "HOC Count is >>" +  + num2 [i-1]);
						        //Report.InfoTest(test, "HOC Count is >>" + num2 [i-1]);
						        sum1= sum1 + num2 [i-1];
						        Thread.sleep(2000);  
	        			   
	        		   }
	        		  // System.out.println("Total Count of HOC is > " + sum1);
	        		   Report.PassTest(test,"Total Count of HOC is > " + sum1);
	        	   }
	        	   
	        	   else {
					Report.FailTestSnapshot(test, driver, "Not Found any record when HOC is checked", "NoRecordWhenHOCChecked");
				}
			} 
	           
	         ////////////////////////////When Only Extra Unit is checked//////////////////////
	          
	          if (ExtraUnit && !HOC) {
	        	  Reset.click();
	        	   Thread.sleep(1000);
	        	  Util.SelectOption(driver, OrderStatus, Status);
	        	   Thread.sleep(1000);
	        	   ExtraUnitChkBox.click();
	        	   Thread.sleep(1000);
	        	   GoButton.click();
	        	   Thread.sleep(2000);
	        	   if (driver.findElements(By.xpath("//tr[@class='default']/td[contains(text(),'No  Customer Summary found.')]")).size()==0) {
	        		   int sum=0;
	        		   List<WebElement> Records=CustinfoBody.findElements(By.tagName("tr"));
	        		   //System.out.println(Records.size()); 
	        		   Report.InfoTest(test, "Total Number of records when only Extra Unit is checked");
	        		   for (int i = 1; i <=Records.size(); i++) {
	        			  	        		
	        			   /////Find Extra unit count////////////////////
	        			     String Extraunitcount=driver.findElement(By.xpath("//*[@id='tblCustomerInfo']/tbody/tr["+i+"]/td[12]/input")).getAttribute("value");
	        			     int[] num = new int[Records.size()];
	        			     num [i-1]=Integer.parseInt(Extraunitcount);
	        			      //System.out.println("Add ExtraUnitIntCount >>" + num [i-1] );
	        			      Report.InfoTest(test, "Extra Unit Count is >>" + num [i-1]);
	        			     // Report.InfoTest(test, "Extra unit count is >>" + num [i-1]);
	        			      sum= sum + num [i-1];
	        			      Thread.sleep(2000);
	        			      
	        		   
	        		   
	        	   }
	        	            // System.out.println("Total count of ExtraUnit is >  " + sum);
	        	  Report.PassTest(test, "Total count of ExtraUnit is >  " + sum);
	        	  
	        	  
			}
	        	   else {
					//System.out.println("Unable to found any records when extraunit check box is checked");
					Report.FailTestSnapshot(test, driver, "Unable to found any records when extraunit check box is checked", "NoRecordsWhenExtraUnitChecked");
				}
	  
                     Report.PassTest(test,"Working Fine when only Extra Unit Check box is checked");
	          
	          
	          }
	          
	          
  
  }
	          catch(Exception e)
			   {
			   	Report.FailTest(test, e.getMessage());
			   	Report.FailTest(test, "Unable to Verify Search_Customer_Data_For_Route_On_Confirmation_Customer_Detail_Screen_COM" );
			   }
			
			
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
	          
	          
  }
       
    public void Route_Detail_Errors() throws InterruptedException {
    	
    	driver.switchTo().frame("opusReportContainer");
    	Thread.sleep(1000);
    	            List<WebElement> Errors=driver.findElements(By.xpath("//*[@class='error_Message']"));
    	            //////////////////Find All the Errors////////////
    	            for (int i = 0; i < Errors.size(); i++) {
    	            	/////////////Capture Link which is not empty/////////////
    	            	if (!Errors.get(i).getText().isEmpty()) {
    	            		System.out.println(Errors.get(i).getText());
    	            		
    	            	///////////////////////////////meal section error///
    	            		if (Errors.get(i).getText().equals("Meal Section has errors")) {
        	            		MealTab.click();
        	            		Thread.sleep(1000);
        	            	List<WebElement>TotalMeals=	MealBody.findElements(By.xpath("//*[@class='mealInfo']/input"));
        	            	System.out.println("Total Meal Size >>" + TotalMeals.size() );
        	            		for (int j = 1; j <=TotalMeals.size(); j++) {
        	            			
        	            	///Take Soft Stop Status///	
        	            			driver.findElement(By.xpath("//*[@id='ui-accordion-accordion-panel-1']/div[1]/div["+(j+1)+"]/div[3]/span[2]/span")).click();
        	            			Thread.sleep(1000);
        	            
    		String MealSoftStop=driver.findElement(By.xpath("//*[@id='ui-accordion-accordion-panel-1']/div[1]/div["+(j+1)+"]/div[3]/span[2]/span")).getText();
    			System.out.println("SoftStop Status >" + MealSoftStop);
    							}
        	            	       		
    						}
        	            	  
    	            		
    	            		////////////////Disposal Section Error////////
    	            		if (Errors.get(i).getText().equals("Disposal Section has errors")) {
        	            		DisposalTab.click();
        	            		Thread.sleep(1000);
    						}
    	            		
    	            		
    	            		
						}
    	            	else {
							System.out.println("Not Found Any Error");
						}
    	            	Thread.sleep(2000);
    	            	  	            	
    	            	
    	            	
    	            	
    	            	
    	            	
    	            	
    	            	
					}
    }
    
}
