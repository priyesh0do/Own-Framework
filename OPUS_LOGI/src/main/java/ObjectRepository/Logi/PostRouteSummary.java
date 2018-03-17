package ObjectRepository.Logi;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class PostRouteSummary {

	ExtentTest test;
	WebDriver driver;

	public PostRouteSummary(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	//PostRouteSummaryReportDetailColumn
		@FindBy(xpath="//*[@id='dtPostRouteDetail']/thead/tr/th")
		List<WebElement> PostRouteSummaryReportDetailColumn;
		

		//PostRouteSummaryReportDetailRow
		@FindBy(xpath="//*[@id='dtPostRouteDetail']/tbody/tr")
		List<WebElement> PostRouteSummaryReportDetailRow;
		
		@FindBy(xpath="//*[@id='dtPostRouteSummary']/thead/tr/th")
		List<WebElement> PostRoteSummaryTableColumn;
		
		//PostRouteSummaryTableRow
		@FindBy(xpath="//*[@id='dtPostRouteSummary']/tbody/tr")
		List<WebElement> PostRouteSummaryTableRow;
		
		//ExpectedExceptionTime
		@FindBy(xpath="//*[@id='dtPostRouteSummary']/tbody/tr/td[contains(@id,'colExcpTimePct')]/span")
		WebElement ExpectedExceptionTime;
		
		///ActualExceptionTime
		@FindBy(xpath="//*[@id='dtPostRouteDetail']/tbody/tr/td[contains(@id,'colExcpTimePct_')]/span")
		WebElement ActualExceptionTime;
		
		//ExpectedVarianceFromPlan
		@FindBy(xpath="//*[@id='dtPostRouteSummary']/tbody/tr/td[contains(@id,'colVarFromPlanPct_')]/span")
		WebElement ExpectedVarianceFromPlan;
		
		
		//ActualVarianceFromPlan
		@FindBy(xpath="//*[@id='dtPostRouteDetail']/tbody/tr/td[contains(@id,'colVarFromPlanPct_')]/span")
		WebElement ActualVarianceFromPlan;
		
		//ExpectedNumberOfSites
		@FindBy(xpath="//*[@id='dtPostRouteSummary']/tbody/tr/td[contains(@id,'colNumSites')]/span")
		WebElement ExpectedNumberOfSites;
		
		//ActualNumberofRows
		@FindBy(xpath="//*[@id='dtPostRouteDetail']/tbody/tr")
		List<WebElement> ActualNumberofRows;
		
		//PostRouteDetailReportDetailSectionColumn
		@FindBy(xpath="//*[@id='t2e']/thead/tr/th")
		List<WebElement> PostRouteDetailReportDetailSectionColumn;
		
		//PostRouteDetailReportDetailSectionRow
		@FindBy(xpath="//*[@id='t2e']/tbody/tr")
		List<WebElement> PostRouteDetailReportDetailSectionRow;
		
		 @FindBy(xpath="(//*[@id='dtExcpPopUp']/tbody/tr/td[contains(@id,'colRteMgr_Row1')]/span)[1]")
	     WebElement ExpectedRoutemanagerinPopUp;
	     
	     @FindBy(xpath="(//*[@id='dtExcpPopUp']/tbody/tr/td[contains(@id,'colDriver_Row1')]/a/span)[1]")
	     WebElement ExpectedDriverInPopUp;
	     
	     @FindBy(xpath="(//*[@id='dtExcpPopUp']/tbody/tr/td[contains(@id,'colExcpTime_Row1')]/span)[1]")
	     WebElement ExpectedExceptionTimeinPopUp;
	     
	     @FindBy(xpath="//*[@id='dtExcpPopUp']/tbody/tr/td[contains(@id,'colDriver_Row1')]/a")
	     WebElement ClickOnFirstDriver;
	     
	     @FindBy(xpath="//*[@id='lblReportTitle']")
	     WebElement GetDetailtableTitle;
	     
	     @FindBy(xpath="//*[@id='divRMFilter']//span")
	     WebElement GetRouteManageName;
	     
	     @FindBy(xpath="//*[@id='divDriverFilter']//span")
	     WebElement GetDriverName;
	     
	     @FindBy(xpath="//*[@id='lblCrumb1']")
	     WebElement BacktoPreRouteSummaryReport;
	     
	     @FindBy(xpath="//*[@id='lblSite']") 
	     WebElement ValidateSite;
	     
	     @FindBy(xpath="//*[@id='lblExcpDateRange']")
	     WebElement ValidateDateRange;
		
		
		public Map<String, List<String>> DataFromPostRouteDetailReportSummarySection() throws IOException {
			Map<String, List<String>> preRouteSummaryTableData = new HashMap<>();
			try {
				preRouteSummaryTableData = readTable(PostRoteSummaryTableColumn, PostRouteSummaryTableRow,
						"dtPostRouteSummary");
				Report.PassTestScreenshot(test, driver, "Post Route Detail report Summary table data read successfully",
						"Pre Route Detail report");
			} catch (Exception e) {
				Report.FailTest(test, "Not able to read Post Route  Detail Report summary table data");
			}

			for (Entry<String, List<String>> entry : preRouteSummaryTableData.entrySet()) {
				System.out.println(entry.getKey() + ":" + entry.getValue().toString());
			}
			return preRouteSummaryTableData;
		}
	   
		public Map<String, List<String>> DataFromPostRouteDetailReportDetailSection() throws IOException {
			Map<String, List<String>> preRouteSummaryTableData = new HashMap<>();
			try {
				preRouteSummaryTableData = readTable(PostRouteDetailReportDetailSectionColumn, PostRouteDetailReportDetailSectionRow,
						"t2e");
				Report.PassTestScreenshot(test, driver, "Post Route Detail report Summary table data read successfully",
						"Pre Route Detail report");
			} catch (Exception e) {
				Report.FailTest(test, "Not able to read Post Route  Detail Report summary table data");
			}

			for (Entry<String, List<String>> entry : preRouteSummaryTableData.entrySet()) {
				System.out.println(entry.getKey() + ":" + entry.getValue().toString());
			}
			return preRouteSummaryTableData;
		}
	   
		
		
		
		public Map<String, List<String>> readTable(List<WebElement> columns, List<WebElement> rows, String tableName) {

			Map<String, List<String>> objTable = new HashMap<>();
			System.out.println("Number of cloumns : " + columns.size());
			System.out.println("Number of rows : " + rows.size());
			for (int iCount = 1; iCount <= columns.size(); iCount++) {
				// List<WebElement> objCol =
				// rows.get(iCount).findElements(By.cssSelector("td.tableTxt"));
				List<String> rowValues = new ArrayList<>();
				for (int row = 1; row <= rows.size(); row++) {
					try {
						rowValues.add(driver
								.findElement(By.xpath(
										"//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + iCount + "]/span"))
								.getText());
					} catch (Exception e) {
						rowValues.add(driver
								.findElement(By
										.xpath("//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + iCount + "]"))
								.getText());
					}
				}
				objTable.put(columns.get(iCount - 1).getText(), rowValues);
			}
			return objTable;

		}
		
	
	public Map<String, List<String>> getPostRouteSummaryTableDetailData() throws IOException {
		Map<String, List<String>> PostRouteSummaryTableDetailData = new HashMap<>();
		try {
			PostRouteSummaryTableDetailData = readTable(PostRouteSummaryReportDetailColumn, PostRouteSummaryReportDetailRow,
					"dtPostRouteDetail");
			Report.PassTestScreenshot(test, driver, "Post RouteSummaryReport Detail table data read successfully",
					"Pre Route Detail report");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read PostRoutesummaryReport Detail table data");
		}

		for (Entry<String, List<String>> entry : PostRouteSummaryTableDetailData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return PostRouteSummaryTableDetailData;
	}

	
	public Map<String, List<String>> getPostRouteSummaryTableData() throws IOException {
		Map<String, List<String>> preRouteSummaryTableData = new HashMap<>();
		try {
			preRouteSummaryTableData = readTable(PostRoteSummaryTableColumn, PostRouteSummaryTableRow,
					"dtPostRouteSummary");
			Report.PassTestScreenshot(test, driver, "Post Route Summary table data read successfully",
					"Pre Route Detail report");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Post Route summary table data");
		}

		for (Entry<String, List<String>> entry : preRouteSummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return preRouteSummaryTableData;
	}
   
	
	
	  
	   public void ValidatePostRouteSummaryReportSummaryData(Map<String, List<String>> getPostRouteSummaryTableData,
				Map<String, List<String>> getPostRouteSummaryTableDetailData) {
		   validateTotalExceptionTime(getPostRouteSummaryTableData,getPostRouteSummaryTableDetailData);
		   ValidateTotalPostRoutesWithExceptionData(getPostRouteSummaryTableData, getPostRouteSummaryTableDetailData);
		   ValidateTotalPostRoutes(getPostRouteSummaryTableData, getPostRouteSummaryTableDetailData);
		   ValidateExceptionTimepercentages();
		   ValidateVarianceFormPlan();
		   ValidateNumberoffsites();
		   
	   }
	   
	   public void validateTotalExceptionTime(Map<String, List<String>> getPostRouteSummaryTableData,
				Map<String, List<String>> getPostRouteSummaryTableDetailData) {

			// TotalException TimeHours=sum of actual Exception Time hours for all sites
			String driverTotalActualHours = null;
			String expectedDriverTotalActualHours = null;
			int totalMins = 0;

			try {
				for (Entry<String, List<String>> entry : getPostRouteSummaryTableData.entrySet()) {
					if (entry.getKey().contains("Total Exception Time (h:m)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								driverTotalActualHours = null;
							} else {
								driverTotalActualHours = entry.getValue().get(i).toString();
								Util.validateTimeFormat(driverTotalActualHours, "Exception Time Hours in Summary Section");
							}
						}
					}
				}

				for (Entry<String, List<String>> entry : getPostRouteSummaryTableDetailData.entrySet()) {
					if (entry.getKey().contains("Exception Time (h:m)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (!entry.getValue().get(i).equals("--")) {
								String[] split = entry.getValue().get(i).split(":", 2);
								int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
								totalMins += mins;
							}

						}
					}
				}

				Util.convertMinsToHours(totalMins);

				expectedDriverTotalActualHours = Util.convertMinsToHours(totalMins);

				if (driverTotalActualHours.equals(expectedDriverTotalActualHours)) {
					Report.InfoTest(test, "Exception Time Hours is correct in Post Route summary report Actual is : "
							+ driverTotalActualHours + " and Expected is : " + expectedDriverTotalActualHours);
					Report.PassTest(test, "Exception Time Hours is as expected in Post Route Summary report");
				} else {
					Report.FailTest(test, "Exception Time Hours is not as expected in Post Route summary report Actual is : "
							+ driverTotalActualHours + " and Expected is : " + expectedDriverTotalActualHours);
				}
			} catch (Exception e) {
				Report.FailTest(test, "Exception Time Hours is not as expected in Post Route summary report Actual is : "
						+ driverTotalActualHours + " and Expected is : " + expectedDriverTotalActualHours);
			}

		}
	   
	   
	   
	   
	   public void ValidateTotalPostRoutesWithExceptionData(Map<String, List<String>> getPostRouteSummaryTableData, Map<String, List<String>> getPostRouteSummaryTableDetailData) {
			int ActualTotalPostRouteWihException=0;
			int ExpectedTotalPostRouteWithException=0;
		   
		   try {
				for (Entry<String, List<String>> entry : getPostRouteSummaryTableData.entrySet()) {
					if (entry.getKey().equals("Total Post-Routes w/ Exception")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								ActualTotalPostRouteWihException = 0;
							} else {
								ActualTotalPostRouteWihException = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
								Util.validateWholeNumber(ActualTotalPostRouteWihException, "Total Idle Events in Summary Section");
							}
						}
					}
				}
				for (Entry<String, List<String>> entry : getPostRouteSummaryTableDetailData.entrySet()) {
					if (entry.getKey().equals("# of Post-Routes w/ Exception")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								ExpectedTotalPostRouteWithException = ExpectedTotalPostRouteWithException + Integer.parseInt("0.0");
							} else {
								ExpectedTotalPostRouteWithException = ExpectedTotalPostRouteWithException
										+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
								
							}
						}

					
					}
				}

				// Summary Post-Routes w/ Exception = sum of # of Post-Routes w/ Exception
				
				if (ActualTotalPostRouteWihException == 0) {
					Report.FailTest(test,
							"Total Post-Routes w/ Exception is not calculated as expected in Post Route summary report Actual is : -- and Expected is : "
									+ ExpectedTotalPostRouteWithException);
				} else {
					if (Util.compareNumber(ActualTotalPostRouteWihException, ExpectedTotalPostRouteWithException)) {
						Report.InfoTest(test, "Total Post-Routes w/ Exception is correct in Post Route  summary report Actual is : "
								+ ActualTotalPostRouteWihException + " and Expected is : " + ExpectedTotalPostRouteWithException);
						Report.PassTest(test, "Total Post-Routes w/ Exception is as expected in Post Route  Summary report");
					} else {
						Report.FailTest(test, "Total Post-Routes w/ Exception is not as expected in Post Route summary report Actual is : " + ActualTotalPostRouteWihException
								+ " and Expected is : " + ExpectedTotalPostRouteWithException);
					}
				}
			} catch (Exception e) {
				Report.FailTest(test, "Total Post-Routes w/ Exception is not as expected in Post Route  summary report Actual is : " + ActualTotalPostRouteWihException
						+ " and Expected is : " + ExpectedTotalPostRouteWithException);
			}

		}
	   
	   public void ValidateTotalPostRoutes(Map<String, List<String>> getPostRouteSummaryTableData, Map<String, List<String>> getPostRouteSummaryTableDetailData) {
			int ActualTotalPostRoute=0;
			int ExpectedTotalPostRoute=0;
		   
		   try {
				for (Entry<String, List<String>> entry : getPostRouteSummaryTableData.entrySet()) {
					if (entry.getKey().equals("Total Post-Routes")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								ActualTotalPostRoute = 0;
							} else {
								ActualTotalPostRoute = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
								Util.validateWholeNumber(ActualTotalPostRoute, "Total Post-Routes Summary Section");
							}
						}
					}
				}
				for (Entry<String, List<String>> entry : getPostRouteSummaryTableDetailData.entrySet()) {
					if (entry.getKey().equals("# of Post-Routes")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								ExpectedTotalPostRoute = ExpectedTotalPostRoute + Integer.parseInt("0.0");
							} else {
								ExpectedTotalPostRoute = ExpectedTotalPostRoute
										+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
								
							}
						}

					
					}
				}

				// Summary Post-Routes = sum of # of Post-Routes 
				
				if (ActualTotalPostRoute == 0) {
					Report.FailTest(test,
							"Total Post-Routes  is not calculated as expected in Post Route summary report Actual is : -- and Expected is : "
									+ ExpectedTotalPostRoute);
				} else {
					if (Util.compareNumber(ActualTotalPostRoute, ExpectedTotalPostRoute)) {
						Report.InfoTest(test, "Total Post-Routes is correct in Post Route  summary report Actual is : "
								+ ActualTotalPostRoute + " and Expected is : " + ExpectedTotalPostRoute);
						Report.PassTest(test, "Total Post-Routes  is as expected in Post Route  Summary report");
					} else {
						Report.FailTest(test, "Total Post-Routes  is not as expected in Post Route summary report Actual is : " + ActualTotalPostRoute
								+ " and Expected is : " + ExpectedTotalPostRoute);
					}
				}
			} catch (Exception e) {
				Report.FailTest(test, "Total Post-Routes is not as expected in Post Route  summary report Actual is : " + ActualTotalPostRoute
						+ " and Expected is : " + ExpectedTotalPostRoute);
			}

		}
	    
	   public void ValidateExceptionTimepercentages(){
		   String ExpExcTime=null;
		   String ActualExpTime=null;
		   int ExpExcTimeValue=0;
		   int ActualExpTimeValue=0;
		   
		   try {
			   ExpExcTime=ExpectedExceptionTime.getText();
			   ExpExcTimeValue=Integer.parseInt(ExpExcTime);
			   ActualExpTime=ActualExceptionTime.getText();
			   ActualExpTimeValue=Integer.parseInt(ActualExpTime);
			   
			   if (ExpExcTimeValue !=0) {
				if (ExpExcTimeValue==ActualExpTimeValue) {
					Report.PassTestScreenshot(test, driver, "Verified Exception Time % Expected is > " +ExpExcTimeValue + "  And Actual is > "+ ActualExpTimeValue, "PassedExceptionTime%");
					
				}
				else {
					Report.FailTestSnapshot(test, driver, "Unable to Verifi Exception Time % Expected is > " +ExpExcTimeValue + "  And Actual is > "+ ActualExpTimeValue, "FailedExceptionTime%");
				}
				
			}
			   else {
				Report.InfoTest(test, "Unable to Verify Exception time % Actual is " + ActualExpTimeValue);
			}
			   
		} 
		   catch (Exception e) {
			Report.FailTestSnapshot(test,driver, "Unable to Verify Exception time % Actual is " + ActualExpTimeValue,"FailedExceptiontime");
		}
		   
		   
	   }
	
	   public void ValidateVarianceFormPlan() {
		   
		   String ExpectedVarianceFormPlan=null;
		   String ActualVarianceFormPlan=null;
		   float ExpectedValue=0;
		   float ActualValue=0;
		   
		   
		   try {
			   ExpectedVarianceFormPlan=ExpectedVarianceFromPlan.getText();
			   //ExpectedValue=Double.parseDouble(ExpectedVarianceFormPlan);
			   ExpectedValue=Float.valueOf(ExpectedVarianceFormPlan);
			   ActualVarianceFormPlan=ActualVarianceFromPlan.getText();
			  // ActualValue=Double.parseDouble(ActualVarianceFormPlan);
			   ActualValue=Float.valueOf(ActualVarianceFormPlan);
			   
			    
			   
			              
			   
			   if (ExpectedValue!=0) {
				if (ExpectedValue==ActualValue) {
					Report.PassTestScreenshot(test, driver, "Validated Variance Form Plan % Expected is > " + ExpectedValue + " And Actual is " +ActualValue, "PassedVarianceFormPlan");
				}
				else {
					Report.FailTestSnapshot(test, driver, "Unable to Validate Variance Form Plan % Expected is > " + ExpectedValue + " And Actual is " +ActualValue, "FailedVarianceFormPlan");
				}
			}
			   else {
				Report.InfoTest(test, "Unable to Validate Variance Form Plan % Expected is > " + ExpectedValue + " And Actual is " +ActualValue);
			}
			   
		} catch (Exception e) {
			Report.FailTest(test, "Unable to Validate Variance Form Plan % Expected is > " + ExpectedValue + " And Actual is " +ActualValue);
			
		}
		   
	   }
	   
	   public void ValidateNumberoffsites() {
		   
		   String ExpNumOfSites=null;
		   int ExpNumOfSite=0;
		   int ActualNumofRows=0;
		   try {
			   ExpNumOfSites=ExpectedNumberOfSites.getText();
			   ExpNumOfSite=Integer.parseInt(ExpNumOfSites);
			   ActualNumofRows=ActualNumberofRows.size();
			   if (ActualNumberofRows.size()!=0) {
				if (ExpNumOfSite==ActualNumofRows) {
					Report.PassTestScreenshot(test, driver, "Passed Number of Sites Expected is " + ExpNumOfSite + " and actual is >" +ActualNumofRows , "PassedNumOfSites");
				}
				else {
					Report.FailTestSnapshot(test, driver, "Failed Number of Sites Expected is " + ExpNumOfSite + " and actual is >" +ActualNumofRows , "FailedNumOfSites");
				}
		      	}
			   else {
				   Report.InfoTest(test, "Failed Number of Sites Expected is " + ExpNumOfSite + " and actual is >" +ActualNumofRows);
			}
			   
		} catch (Exception e) {
			Report.FailTest(test, "Failed Number of Sites Expected is " + ExpNumOfSite + " and actual is >" +ActualNumofRows);
		}
		   
		   
		   
	   }
	   

       
     public void validatePostRouteSummaryReportDetailTableData(Map<String, List<String>> getPostRouteSummaryTableDetailData,Map<String, List<String>> DataFromPostRouteDetailReportSummarySection,Map<String, List<String>> DataFromPostRouteDetailReportDetailSection,String site) {
    	 ValidateTierName(getPostRouteSummaryTableDetailData);
    	 validateDetailArea(getPostRouteSummaryTableDetailData, site);
    	 validateDetailBU(getPostRouteSummaryTableDetailData, site);
    	 validateDetailSite(getPostRouteSummaryTableDetailData, site);
    	 ValidateExceptionTimeHours(getPostRouteSummaryTableDetailData, DataFromPostRouteDetailReportSummarySection);
    	 ValidatePostRouteWException(getPostRouteSummaryTableDetailData,DataFromPostRouteDetailReportSummarySection);
    	 ValidateNumofPostRoute(getPostRouteSummaryTableDetailData, DataFromPostRouteDetailReportSummarySection);
    	 ValidateExceptionTimeDetail(getPostRouteSummaryTableDetailData, DataFromPostRouteDetailReportSummarySection);
    	 ValidateVarianceFromPlan(getPostRouteSummaryTableDetailData, DataFromPostRouteDetailReportDetailSection);
    	 ValidateAvgActualHours(getPostRouteSummaryTableDetailData, DataFromPostRouteDetailReportSummarySection);
    	 ValidateAvgPlanHours(getPostRouteSummaryTableDetailData, DataFromPostRouteDetailReportSummarySection);
     }


     private void ValidateTierName( Map<String, List<String>> getPostRouteSummaryTableDetailData) {
     	String actualTierName=null;
 		
 			
 		try {
 			
 			for (Entry<String, List<String>> entry : getPostRouteSummaryTableDetailData.entrySet()) {
 				if (entry.getKey().equals("Tier")) {
 					for (int i = 0; i < entry.getValue().size(); i++) {
 						if (entry.getValue().get(i).equals("--")) {
 							actualTierName = "00:00";
 						} else {
 							actualTierName = entry.getValue().get(i).toString();
 						}
 					}
 				}
 			}

 			
 				if (actualTierName.equals("South") || actualTierName.equals("North")) {
 					Report.InfoTest(test, "Tier is correct in Post Route Summary report Detail Section. Actual is : "
 							+ actualTierName + " and Expected is : North Or South");
 					Report.PassTest(test, "Tier is as expected in Post Route Summary report Detail Section");
 				} else {
 					Report.FailTest(test, "Tier is not as expected in Post Route Summary report Detail Section. Actual is : " + actualTierName
 							+ " and Expected is : North Or South ");
 				}
 			
 		} catch (Exception e) {
 			Report.FailTest(test, "Tier is not as expected in Pre Route Summary report Detail Section. Actual is : " + actualTierName
 					+ " and Expected is : North Or South");
 		}
 		
     	
     	
     }
     private void validateDetailArea(Map<String, List<String>> getPostRouteSummaryTableDetailData, String site) {
 		
 		String actualAreaName=null;
 		List<String> expectedAreaName = null;
 		String siteID=site.substring(0, 6);
 		try {
 			
 			for (Entry<String, List<String>> entry : getPostRouteSummaryTableDetailData.entrySet()) {
 				if (entry.getKey().equals("Area")) {
 					for (int i = 0; i < entry.getValue().size(); i++) {
 						if (entry.getValue().get(i).equals("--")) {
 							actualAreaName = "--";
 						} else {
 							actualAreaName = entry.getValue().get(i).toString();
 						}
 					}
 				}
 			}

 			expectedAreaName=Util.getDataFromDB("select t.CMARKETAREA.NAME from ocs_admin.vp_site t where pkey in (select PKEY from ocs_admin.tp_site where UNIQUEID like '%"+ siteID +"%')");
 				
 				if (actualAreaName.equals(expectedAreaName.get(0))) {
 					Report.InfoTest(test, "Area is correct in Post Route Summary report Detail Section. Actual is : "
 							+ actualAreaName + " and Expected is : " + expectedAreaName.get(0));
 					Report.PassTest(test, "Area is as expected in Post Route Summary report Detail Section");
 				} else {
 					Report.FailTest(test, "Area is not as expected in Post Route Summary report Detail Section. Actual is : " + actualAreaName
 							+ " and Expected is : " + expectedAreaName.get(0));
 				}
 			
 		} catch (Exception e) {
 			Report.FailTest(test, "Area is not as expected in post Route Summary report Detail Section. Actual is : " + actualAreaName
 					+ " and Expected is : " + expectedAreaName.get(0));
 		}
 		
 		
 		
 	}
  
     private void validateDetailBU(Map<String, List<String>> getPostRouteSummaryTableDetailData, String site) {
 		
 		String actualBUName=null;
 		List<String> expectedBUName = null;
 		String siteID=site.substring(0, 6);
 		try {
 			
 			for (Entry<String, List<String>> entry : getPostRouteSummaryTableDetailData.entrySet()) {
 				if (entry.getKey().equals("BU")) {
 					for (int i = 0; i < entry.getValue().size(); i++) {
 						if (entry.getValue().get(i).equals("--")) {
 							actualBUName = "--";
 						} else {
 							actualBUName = entry.getValue().get(i).toString();
 						}
 					}
 				}
 			}

 		expectedBUName=Util.getDataFromDB("select t.CBUSINESSUNIT.NAME from ocs_admin.vp_site t where pkey in (select PKEY from ocs_admin.tp_site where UNIQUEID like '%"+ siteID +"%')");
 				
 				if (actualBUName.equals(expectedBUName.get(0))) {
 					Report.InfoTest(test, "BU is correct in post Route Summary report Detail Section. Actual is : "
 							+ actualBUName + " and Expected is : " + expectedBUName.get(0));
 					Report.PassTest(test, "BU is as expected in post Route Summary report Detail Section");
 				} else {
 					Report.FailTest(test, "BU is not as expected in Post Route Summary report Detail Section. Actual is : " + actualBUName
 							+ " and Expected is : " + expectedBUName.get(0));
 				}
 			
 		} catch (Exception e) {
 			Report.FailTest(test, "BU is not as expected in Post Route Summary report Detail Section. Actual is : " + actualBUName
 					+ " and Expected is : " + expectedBUName.get(0));
 		}
 		
 	}

     private void validateDetailSite(Map<String, List<String>> getPostRouteSummaryTableDetailData, String site) {
 		
 		String actualSiteName=null;
 		String expectedSiteName = site;
 			
 		try {
 			
 			for (Entry<String, List<String>> entry : getPostRouteSummaryTableDetailData.entrySet()) {
 				if (entry.getKey().equals("Site")) {
 					for (int i = 0; i < entry.getValue().size(); i++) {
 						if (entry.getValue().get(i).equals("--")) {
 							actualSiteName = "00:00";
 						} else {
 							actualSiteName = entry.getValue().get(i).toString();
 						}
 					}
 				}
 			}

 			
 				if (actualSiteName.equals(expectedSiteName)) {
 					Report.InfoTest(test, "Site is correct in Post Route Summary report Detail Section. Actual is : "
 							+ actualSiteName + " and Expected is : " + expectedSiteName);
 					Report.PassTest(test, "Site is as expected in Post Route Summary report Detail Section");
 				} else {
 					Report.FailTest(test, "Site is not as expected in Post Route Summary report Detail Section. Actual is : " + actualSiteName
 							+ " and Expected is : " + expectedSiteName);
 				}
 			
 		} catch (Exception e) {
 			Report.FailTest(test, "Site is not as expected in Post Route Summary report Detail Section. Actual is : " + actualSiteName
 					+ " and Expected is : " + expectedSiteName);
 		}
 		
 	}

     
     public void ValidateExceptionTimeHours(Map<String, List<String>> getPostRouteSummaryTableDetailData,Map<String, List<String>> DataFromPostRouteDetailReportSummarySection) {
     	String ActualExceptionHours=null;
     	String TotalExceptionHours=null;
     	try {

         	for (Entry<String, List<String>> entry : getPostRouteSummaryTableDetailData.entrySet()) {
     			if (entry.getKey().contains("Exception Time (h:m)")) {
     				for (int i = 0; i < entry.getValue().size(); i++) {
     					if (entry.getValue().get(i).equals("--")) {
     						ActualExceptionHours = null;
     					} else {
     						ActualExceptionHours = entry.getValue().get(i).toString();
     						Util.validateTimeFormat(ActualExceptionHours, "Exception Time Hours in Detail Section");
     					}
     				}
     			}
     		}
         	
         	
         	for (Entry<String, List<String>> entry : DataFromPostRouteDetailReportSummarySection.entrySet()) {
     			if (entry.getKey().contains("Total Exception Time (h:m)")) {
     				for (int i = 0; i < entry.getValue().size(); i++) {
     					if (entry.getValue().get(i).equals("--")) {
     						TotalExceptionHours = null;
     					} else {
     						TotalExceptionHours = entry.getValue().get(i).toString();
     						Util.validateTimeFormat(TotalExceptionHours, "Exception Time Hours in Detail Section");
     					}
     				}
     			}
     		}
         	if (Util.compareTime(ActualExceptionHours, TotalExceptionHours)) {
     			Report.PassTestScreenshot(test, driver, "Validated Exception Time Hours Expected is >" + TotalExceptionHours + " and Actual is >" + ActualExceptionHours, "PassedExceptionTime");
     		}
         	else {
     			Report.FailTestSnapshot(test, driver, "Unable to Validate Exception Time Hours Expected is >" + TotalExceptionHours + " and Actual is >" + ActualExceptionHours, "FailedExceptionTime");
     		}
 		} catch (Exception e) {
 			Report.FailTest(test, "Unable to validate Exception time ");
 		}
     	
     	
     }

     public void ValidatePostRouteWException(Map<String, List<String>> getPostRouteSummaryTableDetailData,Map<String, List<String>> DataFromPostRouteDetailReportSummarySection) {
     	
     	int actualPreRouteWorkException = 0;
 		int expectedPreRouteWorkException = 0;	
 		try {
 			for (Entry<String, List<String>> entry : getPostRouteSummaryTableDetailData.entrySet()) {
 				if (entry.getKey().equals("# of Post-Routes w/ Exception")) {
 					for (int i = 0; i < entry.getValue().size(); i++) {
 						if (entry.getValue().get(i).equals("--")) {
 							actualPreRouteWorkException = 0;
 						} else {
 							actualPreRouteWorkException = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
 							Util.validateWholeNumber(actualPreRouteWorkException, "Total Post-Routes w/ Exception in Summary Section");
 						}
 					}
 				}
 			}
 			for (Entry<String, List<String>> entry : DataFromPostRouteDetailReportSummarySection.entrySet()) {
 				if (entry.getKey().equals("Total Post-Routes w/ Exception")) {
 					for (int i = 0; i < entry.getValue().size(); i++) {
 						if (entry.getValue().get(i).equals("--")) {
 							expectedPreRouteWorkException = expectedPreRouteWorkException + Integer.parseInt("0.0");
 						} else {
 							expectedPreRouteWorkException = expectedPreRouteWorkException
 									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
 							
 						}
 					}

 				
 				}
 			}

 			if (Util.compareNumber(actualPreRouteWorkException, expectedPreRouteWorkException)) {
 				Report.PassTestScreenshot(test, driver, "Verified # of Post-Routes w/ Exception Actual is > " + actualPreRouteWorkException + "And Expected is >" + expectedPreRouteWorkException, "PassedPostRouteWException");
 			}
 			else {
 				Report.FailTestSnapshot(test, driver, "Un-Verified # of Post-Routes w/ Exception Actual is > " + actualPreRouteWorkException + "And Expected is >" + expectedPreRouteWorkException, "FailedPostRouteWException");
 			}
 			
 			
 		} catch (Exception e) {
 			Report.FailTestSnapshot(test, driver, "Un-Verified # of Post-Routes w/ Exception Actual is > " + actualPreRouteWorkException + "And Expected is >" + expectedPreRouteWorkException, "FailedPostRouteWException");
 		}        	
     	
     	
     	




     }

     public void ValidateNumofPostRoute(Map<String, List<String>> getPostRouteSummaryTableDetailData,Map<String, List<String>> DataFromPostRouteDetailReportSummarySection) {
     	
     	int actualPreRoute = 0;
 		int expectedPreRoute = 0;	
 		try {
 			for (Entry<String, List<String>> entry : getPostRouteSummaryTableDetailData.entrySet()) {
 				if (entry.getKey().equals("# of Post-Routes")) {
 					for (int i = 0; i < entry.getValue().size(); i++) {
 						if (entry.getValue().get(i).equals("--")) {
 							actualPreRoute = 0;
 						} else {
 							actualPreRoute = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
 							Util.validateWholeNumber(actualPreRoute, "Total Pre Route in Detail Section");
 						}
 					}
 				}
 			}
 			for (Entry<String, List<String>> entry : DataFromPostRouteDetailReportSummarySection.entrySet()) {
 				if (entry.getKey().equals("Total Post-Routes")) {
 					for (int i = 0; i < entry.getValue().size(); i++) {
 						if (entry.getValue().get(i).equals("--")) {
 							expectedPreRoute = expectedPreRoute + Integer.parseInt("0.0");
 						} else {
 							expectedPreRoute = expectedPreRoute
 									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
 							
 						}
 					}

 				
 				}
 			}

 			if (Util.compareNumber(actualPreRoute, expectedPreRoute)) {
 				Report.PassTestScreenshot(test, driver, "Verified # of Post-Routes  Actual is > " + actualPreRoute + "And Expected is >" + expectedPreRoute, "PassedPostRoute");
 			}
 			else {
 				Report.FailTestSnapshot(test, driver, "Un-Verified # of Post-RoutesActual is > " + actualPreRoute + "And Expected is >" + expectedPreRoute, "FailedPostRoute");
 			}
 			
 			
 		} catch (Exception e) {
 			Report.FailTestSnapshot(test, driver, "Un-Verified # of Post-Routes  Actual is > " + actualPreRoute + "And Expected is >" + expectedPreRoute, "FailedPostRoute");
 		}	
     }
     
     public void ValidateExceptionTimeDetail(Map<String, List<String>> getPostRouteSummaryTableDetailData,Map<String, List<String>> DataFromPostRouteDetailReportSummarySection) {
     	
     	int actualExceptionTime  = 0;
 		int expectedExceptionTime = 0;	
 		try {
 			for (Entry<String, List<String>> entry : getPostRouteSummaryTableDetailData.entrySet()) {
 				if (entry.getKey().equals("Exception Time %")) {
 					for (int i = 0; i < entry.getValue().size(); i++) {
 						if (entry.getValue().get(i).equals("--")) {
 							actualExceptionTime = 0;
 						} else {
 							actualExceptionTime = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
 							Util.validateWholeNumber(actualExceptionTime, "Total ExceptionTime %  in Detail Section");
 						}
 					}
 				}
 			}
 			for (Entry<String, List<String>> entry : DataFromPostRouteDetailReportSummarySection.entrySet()) {
 				if (entry.getKey().equals("Exception Time %")) {
 					for (int i = 0; i < entry.getValue().size(); i++) {
 						if (entry.getValue().get(i).equals("--")) {
 							expectedExceptionTime = expectedExceptionTime + Integer.parseInt("0.0");
 						} else {
 							expectedExceptionTime = expectedExceptionTime
 									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
 							
 						}
 					}

 				
 				}
 			}

 			if (Util.compareNumber(actualExceptionTime, expectedExceptionTime)) {
 				Report.PassTestScreenshot(test, driver, "Verified ExceptionTime %  Actual is > " + actualExceptionTime + "And Expected is >" + expectedExceptionTime, "PassedExceptionTime");
 			}
 			else {
 				Report.FailTestSnapshot(test, driver, "Un-Verified  ExceptionTime %  Actual is > " + actualExceptionTime + "And Expected is >" + expectedExceptionTime, "FailedExceptionTime");
 			}
 			
 			
 		} catch (Exception e) {
 			Report.FailTestSnapshot(test, driver, "Un-Verified ExceptionTime %   Actual is > " + actualExceptionTime + "And Expected is >" + expectedExceptionTime, "FailedExceptionTime");
 		}	
     }

     public void ValidateVarianceFromPlan(Map<String, List<String>> getPostRouteSummaryTableDetailData,Map<String, List<String>> DataFromPostRouteDetailReportDetailSection) {
    	 
    	double actualVarianceFromPlan = 0.0;
     	int ActualHours=0;
     	int PlanHours=0;
     	
     	for (Entry<String, List<String>> entry : getPostRouteSummaryTableDetailData.entrySet()) {
 			if (entry.getKey().equals("Variance from Plan %")) {
 				for (int i = 0; i < entry.getValue().size(); i++) {
 					if (entry.getValue().get(i).equals("--")) {
 						actualVarianceFromPlan=0.00;
 					} 
 					
 					else {
 						actualVarianceFromPlan=Double.parseDouble(entry.getValue().get(i));
 						Util.validateFieldDecimalPlaces(entry.getValue().get(i), 1, "Actual Variance from Plan % in Detail Section");
 					}
 				}
 			}
 		}
     	
     	for (Entry<String, List<String>> entry : DataFromPostRouteDetailReportDetailSection.entrySet()) {
 			if (entry.getKey().contains("Actual (h:m)")) {
 				for (int i = 0; i < entry.getValue().size(); i++) {
 					if (!entry.getValue().get(i).equals("--")) {
 						String[] split = entry.getValue().get(i).split(":", 2);
 						int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
 						ActualHours += mins;
 					}

 				}
 			}
 		}
     	
     	for (Entry<String, List<String>> entry : DataFromPostRouteDetailReportDetailSection.entrySet()) {
 			if (entry.getKey().contains("Plan (h:m)")) {
 				for (int i = 0; i < entry.getValue().size(); i++) {
 					if (!entry.getValue().get(i).equals("--")) {
 						String[] split = entry.getValue().get(i).split(":", 2);
 						int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
 						PlanHours += mins;
 					}

 				}
 			}
 		}
     	
     	
     	
     	float ExpectedData = (((float)ActualHours - (float)PlanHours ) /(float)PlanHours)*100;
 		//double ActData=actualdata;

     	DecimalFormat Onedigit = new DecimalFormat("0");
     	String ExpDataStr=Onedigit.format(ExpectedData);
     	double ExpectedVarianceFromPlam=Double.parseDouble(ExpDataStr);
 		
 		if (Util.compareNumber(ExpectedVarianceFromPlam, actualVarianceFromPlan)) {
 			Report.PassTestScreenshot(test, driver, "Verified VariancefromPlan Actual is " +actualVarianceFromPlan + " And Expected is >" + ExpectedVarianceFromPlam , "PassedVariancefromPlan");
 			
 		}
 		else {
 			Report.FailTestSnapshot(test, driver, "Unable to Verified VariancefromPlan Actual is " +actualVarianceFromPlan + " And Expected is >" + ExpectedVarianceFromPlam , "FailedVariancefromPlan");
 		}
     	  
     	
    	 
 		
    	 
    	 
    	 
    	 
    	 
    	 
     }

     public void ValidateAvgActualHours(Map<String, List<String>> getPostRouteSummaryTableDetailData,Map<String, List<String>> DataFromPostRouteDetailReportSummarySection) {
     	String ActualAvgActual=null;
     	String ExpectedAvgActual=null;
     	try {

         	for (Entry<String, List<String>> entry : getPostRouteSummaryTableDetailData.entrySet()) {
     			if (entry.getKey().contains("Avg Actual (h:m)")) {
     				for (int i = 0; i < entry.getValue().size(); i++) {
     					if (entry.getValue().get(i).equals("--")) {
     						ActualAvgActual = null;
     					} else {
     						ActualAvgActual = entry.getValue().get(i).toString();
     						Util.validateTimeFormat(ActualAvgActual, "Avg Actual (h:m)  in Detail Section");
     					}
     				}
     			}
     		}
         	
         	
         	for (Entry<String, List<String>> entry : DataFromPostRouteDetailReportSummarySection.entrySet()) {
     			if (entry.getKey().contains("Avg Actual (h:m)")) {
     				for (int i = 0; i < entry.getValue().size(); i++) {
     					if (entry.getValue().get(i).equals("--")) {
     						ExpectedAvgActual = null;
     					} else {
     						ExpectedAvgActual = entry.getValue().get(i).toString();
     						Util.validateTimeFormat(ExpectedAvgActual, "Exception Time Hours in Detail Section");
     					}
     				}
     			}
     		}
         	if (Util.compareTime(ActualAvgActual, ExpectedAvgActual)) {
     			Report.PassTestScreenshot(test, driver, "Validated Avg Actual Hours Expected is >" + ExpectedAvgActual + " and Actual is >" + ActualAvgActual, "PassedAvgActual");
     		}
         	else {
     			Report.FailTestSnapshot(test, driver, "Unable to Validate Avg Actual Hours Expected is >" + ExpectedAvgActual + " and Actual is >" + ActualAvgActual, "FailedAvgActual");
     		}
 		} catch (Exception e) {
 			Report.FailTest(test, "Unable to validate AvgActual ");
 		}
     	
     	
     }

     public void ValidateAvgPlanHours(Map<String, List<String>> getPostRouteSummaryTableDetailData,Map<String, List<String>> DataFromPostRouteDetailReportSummarySection) {
     	String ActualAvgPlan=null;
     	String ExpectedAvgPlan=null;
     	try {

         	for (Entry<String, List<String>> entry : getPostRouteSummaryTableDetailData.entrySet()) {
     			if (entry.getKey().contains("Avg Plan (h:m)")) {
     				for (int i = 0; i < entry.getValue().size(); i++) {
     					if (entry.getValue().get(i).equals("--")) {
     						ActualAvgPlan = null;
     					} else {
     						ActualAvgPlan = entry.getValue().get(i).toString();
     						Util.validateTimeFormat(ActualAvgPlan, "Avg Plan (h:m) in Detail Section");
     					}
     				}
     			}
     		}
         	
         	
         	for (Entry<String, List<String>> entry : DataFromPostRouteDetailReportSummarySection.entrySet()) {
     			if (entry.getKey().contains("Avg Plan (h:m)")) {
     				for (int i = 0; i < entry.getValue().size(); i++) {
     					if (entry.getValue().get(i).equals("--")) {
     						ExpectedAvgPlan = null;
     					} else {
     						ExpectedAvgPlan = entry.getValue().get(i).toString();
     						Util.validateTimeFormat(ExpectedAvgPlan, "Avg Plan Hours in Detail Section");
     					}
     				}
     			}
     		}
         	if (Util.compareTime(ActualAvgPlan, ExpectedAvgPlan)) {
     			Report.PassTestScreenshot(test, driver, "Validated Avg Plan (h:m)Expected is >" + ExpectedAvgPlan + " and Actual is >" + ActualAvgPlan, "PassedAvgPlan");
     		}
         	else {
     			Report.FailTestSnapshot(test, driver, "Unable to Validate Avg Plan (h:m) Expected is >" + ExpectedAvgPlan + " and Actual is >" + ActualAvgPlan, "FailedAvgPlan");
     		}
 		} catch (Exception e) {
 			Report.FailTest(test, "Unable to validate AvgPlan ");
 		}
     	
     	
     }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     public void ValidatePopUp(String Site, String FromDate, String ToDate,String ReportName,String downloadFilePath) {
     	Popupclick("dtPostRouteDetail");
     	ValidatefixedText(Site, FromDate, ToDate);  	
     	ValidateColumnNameinPopUp();
     	ValidateExportExcelButton(ReportName, downloadFilePath);
     	ValidatePopUpDatailsDetailReport("Post-Route Detail","Post-Route Summary","dtPostRouteSummary");
     }  
     
     
     
    
     
     
     
     private void Popupclick(String DetailTableName) {
     	int NumofRecords=0;
     	
     	try {
     		Util.switchToDefaultWindow();
     		Util.selectFrame("opusReportContainer,subReportContainer");
     		NumofRecords=driver.findElements(By.xpath("//*[@id='"+DetailTableName+"']/tbody/tr")).size();
     		if (NumofRecords!=0) {
     			//ClickOnFirstRecord.click();
     			driver.findElement(By.xpath("//*[@id='"+DetailTableName+"']/tbody/tr/td[contains(@id,'colSite_Row1')]/span")).click();
     			Thread.sleep(2000);
     			Util.pageScroll(0, 400);
     			Report.PassTestScreenshot(test, driver, "Clicked on first record", "PassedClickedFirstRecord");
     			
     			
 			}
     		else {
 				Report.FailTestSnapshot(test, driver, "Not Found Any Record", "FailedNoRecordsExist");
 			}
     		
 		} catch (Exception e) {
 			Report.FailTest(test, "popup click Failed");
 		}
     	
     	
     	
     	
     }

     private void ValidatefixedText(String Site, String FromDate, String ToDate) {
     	String ActualSite=null;
     	String dateRangeText=null;
     	String actualFromDate=null;
 		String actualToDate=null;
 		String actualSiteName=null;
     	try {
     		Util.switchToDefaultWindow();
     		Util.selectFrame("opusReportContainer,subReportContainer");
     		ActualSite=ValidateSite.getText();
     		String SitePrts[]=ActualSite.split("for");  
     		actualSiteName=SitePrts[1].substring(1,27);
     		if (ActualSite.contains(Site)) {
     			Report.PassTestScreenshot(test, driver, "Validated site Actual site is " + actualSiteName + " and Expected Site is" +Site , "PassedSiteVerification");
 				
 			}
     		else {
     			Report.FailTestSnapshot(test, driver, "Unable to validate site Actual site is " + actualSiteName + " and Expected Site is" +Site , "PassedSiteVerification");
 			}
     		
     		dateRangeText=ValidateDateRange.getText();
     		actualFromDate=dateRangeText.substring(20,30);
     		actualToDate=dateRangeText.substring(34,44);
     		    		
     		if (actualFromDate.contains(FromDate) && actualToDate.contains(ToDate) ) {
 				Report.PassTestScreenshot(test, driver, "Validated From and To Date Expected From Date is " + FromDate +" actual From Date is"+actualFromDate + "Expected To Date is " + ToDate + "and Actual To Date is" +actualToDate, "PassedDateRangeVerification");
 			}
     		else {
     			Report.FailTestSnapshot(test, driver, "Unable to validate From and To Date Expected From Date is " + FromDate +" actual From Date is"+actualFromDate + "Expected To Date is " + ToDate + "and Actual To Date is" +actualToDate, "FailedDateRangeVerification");
 			}
     		
 		} catch (Exception e) {
 			Report.FailTest(test, "Failed Validate fixed Text");
 		}
     	
     	
     }

     private void ValidateColumnNameinPopUp() {
     	List<String> actualpopupColumns=new ArrayList<>();
     	
     	try {
     		actualpopupColumns = Util.getColumnNames("dtExcpPopUp");
     		Util.validateColumns(actualpopupColumns, GlobalExpectedColumns.PreRouteSummaryPopUpColumns, "Post Route Summary pop Up columns");
 		} catch (Exception e) {
 			Report.FailTest(test, "Validate Column Name in PopUp Failed");
 		}
     	
     	
     	
     }

     private void ValidateExportExcelButton(String ReportName,String downloadFilePath) {

     	List<String> UIDataList=new ArrayList<>();
     	   	
     	try {
     		
 			Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsPreRouteSummaryPopUp, downloadFilePath,1);
 		
 			UIDataList=Util.getUIColumnData(ReportName,"PopUp","RteMgr","dtExcpPopUp");
 			Util.validateExportExcelData(downloadFilePath, ReportName, "Route Manager", 0, UIDataList);
 			
 			
 			
 			UIDataList=Util.getUIColumnData(ReportName,"PopUp","Driver","dtExcpPopUp");
 			Util.validateExportExcelData(downloadFilePath, ReportName, "Driver", 0, UIDataList);
 			
 			UIDataList=Util.getUIColumnData(ReportName,"PopUp","ExcpTime","dtExcpPopUp");
 			Util.validateExportExcelData(downloadFilePath, ReportName, "Exception Time", 0, UIDataList);
     	
     	
     	} catch (Exception e) {
 			Report.FailTestSnapshot(test, driver, "Validate Export Excel Button Failed", "ExportToExcelFailed");
 		}
     	
     }
     
    
     
     
    private void ValidatePopUpDatailsDetailReport(String expectedDetailReportTitle,String expectedSummaryReportTitle,String SummaryTableName) {
 	     String expectedRouteManager=null;
 	     String expectedDriver=null;
 	     String exceptionTime=null;
 	     String actualDetailReportTitle=null;
 	     String actualRouteManager=null;
 	     String actualDriver=null;
 	     String actualExceptionTime=null;
 	     String actualSummaryReportTitlePreRouteSummary=null;
 	   
 	   try {
 		   expectedRouteManager=ExpectedRoutemanagerinPopUp.getText();
 		   expectedDriver=ExpectedDriverInPopUp.getText();
 		   exceptionTime=ExpectedExceptionTimeinPopUp.getText();
 		   ClickOnFirstDriver.click();
 		   Thread.sleep(10000);
 		   Util.switchToDefaultWindow();
 		   Util.selectFrame("opusReportContainer");
 		   actualDetailReportTitle=GetDetailtableTitle.getText();
 		   if (actualDetailReportTitle.contains(expectedDetailReportTitle)) {
 			   Report.PassTestScreenshot(test, driver, "Navigation working fine Actual page title is " +actualDetailReportTitle + " and Expected Page title is "  + expectedDetailReportTitle, "PassedPageTitleValidateion");
 			
 		} else {
 			Report.FailTestSnapshot(test, driver, "Navigation Not working fine Actual page title is " +actualDetailReportTitle + " and Expected Page title is "  + expectedDetailReportTitle, "FailedPageTitleValidateion");
 		}
 		   
 		   
 		   actualRouteManager=GetRouteManageName.getText();
 		   actualDriver=GetDriverName.getText();
 		   Util.switchToDefaultWindow();
 		   Util.selectFrame("opusReportContainer,subReportContainer");
 		   actualExceptionTime=driver.findElement(By.xpath("//*[@id='"+SummaryTableName+"']/tbody/tr/td[contains(@id,'colTtlExcpTm_Row1')]/span")).getText();
 		   if (expectedRouteManager.contains(actualRouteManager) && expectedDriver.contains(actualDriver) && exceptionTime.contains(actualExceptionTime)) {
 			Report.PassTestScreenshot(test, driver, "Validated Route Manager Name Expected is "+ expectedRouteManager + " and Actaul is "+actualRouteManager + " Validated Driver Name Expected is " + expectedDriver+ " and actual is " + actualDriver + "Validated Exception Time Expected Exception time is " + exceptionTime + " and Actual Exception time is " +actualExceptionTime, "PassedRMDriverAndExceptionTime");
 		}
 		   else {
 			   Report.PassTestScreenshot(test, driver, "Unable to Validate Route Manager Name Expected is "+ expectedRouteManager + " and Actaul is "+actualRouteManager + " Unable to Validate Driver Name Expected is " + expectedDriver+ " and actual is " + actualDriver + "Unable to Validate Exception time is Expected Exception time is " + exceptionTime + " and Actual Exception time is " +actualExceptionTime, "FailedRMDriverAndExceptionTime");
 		}
 		   
 		   Util.switchToDefaultWindow();
 		   Util.selectFrame("opusReportContainer");
 		   BacktoPreRouteSummaryReport.click();
 		   Thread.sleep(3000);
 		   Util.switchToDefaultWindow();
 		   Util.selectFrame("opusReportContainer");
 		   actualSummaryReportTitlePreRouteSummary=GetDetailtableTitle.getText();
 		   if (actualSummaryReportTitlePreRouteSummary.contains(expectedSummaryReportTitle)) {
 			   Report.PassTest(test, "Validated Page title actual is " +actualSummaryReportTitlePreRouteSummary + " and Expected is" +expectedSummaryReportTitle);
 			
 		} else {
 			 Report.FailTest(test, "Unable to Validate Page title actual is " +actualSummaryReportTitlePreRouteSummary + " and Expected is" +expectedSummaryReportTitle);
 		}
 		       
 		   
 		   
 	} catch (Exception e) {
 		 Report.FailTest(test, "Failed ValidatePopUpDatainDetailReport");
 	}
 	   
    }

     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
}
