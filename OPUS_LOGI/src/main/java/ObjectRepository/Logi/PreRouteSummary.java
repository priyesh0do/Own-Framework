package ObjectRepository.Logi;

import java.io.IOException;
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

public class PreRouteSummary {

	WebDriver driver;
	ExtentTest test;
	
	public  PreRouteSummary(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
		}
	@FindBy(xpath = "//table[@id='dtPreRouteSummary']/thead/tr/th")
	List<WebElement> preRouteSummaryTableColumns;
	
	@FindBy(xpath="//table[@id='t2']/thead/tr/th")
	List<WebElement> PreRouteDetailDataAllTabColumns;
	
	@FindBy(xpath = "//table[@id='dtPreRouteSummary']/tbody/tr")
	List<WebElement> preRouteSummaryTableRows;
	
	@FindBy(xpath="//table[@id='t2']/tbody/tr")
	List<WebElement> PreRouteDetailDataAllTabRows;
	
	
	
	@FindBy(xpath="//*[@id='dtPreRouteDetail']/thead/tr/th")
			List<WebElement> PreRouteDetailTableColumn;		
	
	@FindBy(xpath = "//*[@id='dtPreRouteDetail']/tbody/tr")
	List<WebElement> PreRouteDetailTableRow;
	
	@FindBy(xpath = "//*[@id='dtPreRouteSummary']/tbody/tr/td[contains(@id,'NumPreRteExcp')]/span")
	WebElement TotalPreRoutesWorkException;
	
	@FindBy(xpath = "//*[@id='dtPreRouteDetail']/tbody/tr")
	List<WebElement> NumofRows;
	
	
	@FindBy(xpath = "//*[@id='dtPreRouteDetail']/tbody/tr/td[contains(@id,'colNumPreRte_')]/span")
	List<WebElement> TotalActualNumberofColums;
	
	@FindBy(xpath = "//*[@id='dtPreRouteSummary']/tbody/tr/td[contains(@id,'colNumPreRte_Row1')]/span")
	WebElement ExpectedPreRoutesCount;
	
	///ExceptionTimeSummary
	@FindBy(xpath="//*[@id='dtPreRouteSummary']/tbody/tr/td[contains(@id,'colExcpTimePct')]/span")
	WebElement ExceptionTimeSummary;
	
	///ExceptionTimeDetail
	@FindBy(xpath="//*[@id='dtPreRouteDetail']/tbody/tr/td[contains(@id,'colExcpTimePct_Row')]/span")
	WebElement ExceptionTimeDetail;
	
	///VariancefromPlanInSummary
	@FindBy(xpath="//*[@id='dtPreRouteSummary']/tbody/tr/td[contains(@id,'colVarFromPlanPct_')]/span")
	WebElement VariancefromPlan;
	
	//VarienceFromPlanDetail
	@FindBy(xpath="//*[@id='dtPreRouteDetail']/tbody/tr/td[contains(@id,'colVarFromPlanPct')]/span")
	WebElement VarienceFromPlanDetail;
	
	//NumofSiteSummary
	@FindBy(xpath="//*[@id='dtPreRouteSummary']/tbody/tr/td[contains(@id,'colNumSites')]/span")
	WebElement NumofSiteSummary;
	
	//NumofRows
	
	//RedirectedtoAllTab
	@FindBy(xpath="//*[@id='tabAll']/a/em/span")
	WebElement RedirectedtoAllTab;
	
	
	//RedirectedtoActualSubView
	@FindBy(xpath="//*[@id='inpSubReportOpt_2']")
	WebElement RedirectedtoActualSubView;
	
	//ValidateRecordsExistOrNot
	@FindBy(xpath="//*[@id='dtPreRouteDetail']/tbody/tr")
	List<WebElement> ValidateRecordsExistOrNot;
	
	//ClickOnFirstRecord
	@FindBy(xpath="//*[@id='dtPreRouteDetail']/tbody/tr/td[contains(@id,'colSite_Row1')]/span")
	WebElement ClickOnFirstRecord;
	
	//ValidateSite
	@FindBy(xpath="//*[@id='lblSite']")
	WebElement ValidateSite;
	
	//ValidateDateRange
	@FindBy(xpath="//*[@id='lblExcpDateRange']")
	WebElement ValidateDateRange;
	
	//ClickOnFirstDriver
	@FindBy(xpath="//*[@id='dtExcpPopUp']/tbody/tr/td[contains(@id,'colDriver_Row1')]/a")
	WebElement ClickOnFirstDriver;
	//ExpectedRoutemanagerinPopUp
    @FindBy(xpath="(//*[@id='dtExcpPopUp']/tbody/tr/td[contains(@id,'colRteMgr_Row1')]/span)[1]")
    WebElement ExpectedRoutemanagerinPopUp;
    
    //ExpectedDriverInPopUp
    @FindBy(xpath="(//*[@id='dtExcpPopUp']/tbody/tr/td[contains(@id,'colDriver_Row1')]/a/span)[1]")
    WebElement ExpectedDriverInPopUp;
    
    //ExpectedExceptionTimeinPopUp
    @FindBy(xpath="(//*[@id='dtExcpPopUp']/tbody/tr/td[contains(@id,'colExcpTime_Row1')]/span)[1]")
    WebElement ExpectedExceptionTimeinPopUp;
    
    //GetDetailtableTitle
    @FindBy(xpath="//*[@id='lblReportTitle']") 
   WebElement GetDetailtableTitle;
    
    //GetExceptionTimeFromDetailReport
    @FindBy(xpath="//*[@id='dtPreRouteSummary']/tbody/tr/td[contains(@id,'colTtlExcpTm_Row1')]/span")
    WebElement GetExceptionTimeFromDetailReport;
    
    //BacktoPreRouteSummaryReport
    @FindBy(xpath="//*[@id='lblCrumb1']")
    WebElement BacktoPreRouteSummaryReport;
    
    //GetRouteManageName
    @FindBy(xpath="//*[@id='divRMFilter']//span")
    WebElement GetRouteManageName;
    
    //GetDriverName
    @FindBy(xpath="//*[@id='divDriverFilter']//span")
    WebElement GetDriverName; 
    
	
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
	
	public Map<String, List<String>> getActualPreRouteSummaryTableData() throws IOException {
		Map<String, List<String>> preRouteSummaryTableData = new HashMap<>();
		try {
			preRouteSummaryTableData = readTable(preRouteSummaryTableColumns, preRouteSummaryTableRows,
					"dtPreRouteSummary");
			Report.PassTestScreenshot(test, driver, "Pre Route Summary table data read successfully",
					"Pre Route Detail report");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Pre Route summary table data");
		}

		for (Entry<String, List<String>> entry : preRouteSummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return preRouteSummaryTableData;
	}
	
	public Map<String, List<String>> getActualPreRouteDetailTableData() {
		System.out.println("inside Actual Pre Route Summary Detail Table Data");
		Util.CaptureScreenshot("Actual Pre Route Summary Detail Table Data");
		Map<String, List<String>> PreRouteSummaryDetailTableData = new HashMap<>();
		try {
			PreRouteSummaryDetailTableData = readTable(PreRouteDetailTableColumn, PreRouteDetailTableRow, "dtPreRouteDetail");
			Report.PassTestScreenshot(test, driver, "Pre Route Detail table data read successfully",
					"Pre Route Summary Detail Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Pre Route Detail table data");
		}
		for (Entry<String, List<String>> entry : PreRouteSummaryDetailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return PreRouteSummaryDetailTableData;
	}
  
	public void validateTotalExceptionTime(Map<String, List<String>> getActualPreRouteSummaryTableData,
			Map<String, List<String>> getActualPreRouteDetailTableData, String lOB) {

		// TotalException TimeHours=sum of actual Exception Time hours for all sites
		String driverTotalActualHours = null;
		String expectedDriverTotalActualHours = null;
		int totalMins = 0;

		try {
			for (Entry<String, List<String>> entry : getActualPreRouteSummaryTableData.entrySet()) {
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

			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
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
				Report.InfoTest(test, "Exception Time Hours is correct in Pre Route summary report Actual is : "
						+ driverTotalActualHours + " and Expected is : " + expectedDriverTotalActualHours);
				Report.PassTest(test, "Exception Time Hours is as expected in Pre Route Summary report");
			} else {
				Report.FailTest(test, "Exception Time Hours is not as expected in Pre Route summary report Actual is : "
						+ driverTotalActualHours + " and Expected is : " + expectedDriverTotalActualHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Exception Time Hours is not as expected in Pre Route summary report Actual is : "
					+ driverTotalActualHours + " and Expected is : " + expectedDriverTotalActualHours);
		}

	}

	public void ValidateTotalPreRoutesWithException() {
		String ActualTotalPreRoutesWorkE = TotalPreRoutesWorkException.getText();
		int Actualresult = Integer.parseInt(ActualTotalPreRoutesWorkE);
		if (Actualresult != 0) {
			int Expectedresult = NumofRows.size();
			if (Expectedresult == Actualresult) {
				Report.PassTestScreenshot(test, driver,
						"Total Pre-Routes w/ Exception is correct for PreRoute Detail Report",
						"Pre Route Total Pre-Routes w/ Exception ");
			} else {
				Report.FailTestSnapshot(test, driver,
						"Total Pre-Routes w/ Exception is Incorrect for PreRoute Detail Report",
						"Pre Route Total Pre-Routes w/ Exception Fail");
			}
		} else {
			Report.InfoTest(test, "Total Pre-Routes w/ Exception is >>" + Actualresult);
		}

	}
 
	public void ValidateTotalPreRoutes() {
		int actualPreRoutes = 0;
		int ExpectedPreRoute = 0;
		
		for (int i = 0; i < TotalActualNumberofColums.size(); i++) {
			if (!TotalActualNumberofColums.get(i).getText().contains("--")) {
				actualPreRoutes = TotalActualNumberofColums.size();

			}

		}
		String ExpCount = ExpectedPreRoutesCount.getText();
		ExpectedPreRoute = Integer.parseInt(ExpCount);
		if (ExpectedPreRoute != 0) {
			if (ExpectedPreRoute == actualPreRoutes) {
				Report.PassTestScreenshot(test, driver, "Total Pre-Routes Count is correct for Pre Route Detail Table",
						"Total Pre-Routes Passed");
			} else {
				Report.FailTestSnapshot(test, driver, "Total Pre-Routes Count is Incorrect for Pre Route Detail Table",
						"Total Pre-Routes Failed");
			}

		} else {
			Report.InfoTest(test, "Total Pre Routes Count is >> " + ExpectedPreRoute);
		}
		

	}
	
	public void ValidateExceptionTimePersentage() {
		String SummaryExceptionTime=null;
		String DetailExceptionTime=null;
		int SummaryExcTime=0;
		int DetailExcTime=0;
		try {
			
			SummaryExceptionTime=ExceptionTimeSummary.getText();
			SummaryExcTime=Integer.parseInt(SummaryExceptionTime);
			
		    DetailExceptionTime=ExceptionTimeDetail.getText();
		    DetailExcTime=Integer.parseInt(DetailExceptionTime);
		    if (SummaryExcTime!=0) {
				if (SummaryExcTime == DetailExcTime) {
					Report.PassTestScreenshot(test, driver, "Validated Exception Time % Actual is >" + SummaryExcTime + " And Expected is >" +DetailExcTime , "PassedExceptionTime%");
				}
				else {
					Report.FailTestSnapshot(test, driver, "Unable to Validate Exception Time % Actual is >" + SummaryExcTime + " And Expected is >" +DetailExcTime , "FailedExceptionTime%");
				}
			}
		    
		} catch (Exception e) {
			Report.FailTest(test, "unable to Validate Exception Time % ");
		}
		     
		     
		     
		
		
	}
 
	public void ValidateVarianceFromPlan() {
		String SummaryVariencePlan=null;
		String DetailVariencePlan=null;
		double SummaryVp=0.0;
		double detailVp=0.0;
		
		try {
			SummaryVariencePlan=VariancefromPlan.getText();
			SummaryVp=Double.parseDouble(SummaryVariencePlan);
			DetailVariencePlan=VarienceFromPlanDetail.getText();
			detailVp=Double.parseDouble(DetailVariencePlan);
			
			if (SummaryVp!=0.0) {
				if (SummaryVp==detailVp) {
					Report.PassTestScreenshot(test, driver, "Validated Variance from Plan % Actual is " + SummaryVp + "And Expected is " + detailVp , "PassedVariencePlan");
				}
				else {
					Report.FailTestSnapshot(test, driver, "Unable to validate Variance from Plan % Actual is " + SummaryVp + "And Expected is " + detailVp , "FailedVariencePlan");
				}
				
			}
			else {
				Report.InfoTest(test, "Variance from Plan % is " + SummaryVp );
			}
		} catch (Exception e) {
			Report.FailTest(test, "Unable to Validate Varience from  Plan %");
		}
		
		
		
		
	}
	 
    public void ValidateNumOfSite() {
    	String SummaryNumofSite=null;
    	int sites=0;
    	int ActualSite=0;
    	try {
    		SummaryNumofSite=NumofSiteSummary.getText(); 
    		sites=Integer.parseInt(SummaryNumofSite);
        	if (NumofRows.size()!=0) {
        		ActualSite=NumofRows.size();
        		if (ActualSite==sites) {
        			Report.PassTestScreenshot(test, driver, "Validated Num of Sites expected is >" + sites + "And Actual is >" +ActualSite, "PassedSite");
					
				}
        		else {
        			Report.FailTestSnapshot(test, driver, "unable to Validate Num of Sites expected is >" + sites + "And Actual is >" +ActualSite, "FailedSite");
				}
    		}
        	else {
        		Report.FailTestSnapshot(test, driver, "unable to Validate Num of Sites expected is >" + sites + "And Actual is >" +ActualSite, "FailedSite");
			}
        	
		} catch (Exception e) {
			Report.FailTest(test, "Unable to Validate Sites");
		}
    	
    	
    	
    }

    public void validatePreRouteSummaryData(Map<String, List<String>> getActualPreRouteSummaryTableData,
			Map<String, List<String>> getActualPreRouteDetailTableData, String Lob) {
    	validateTotalExceptionTime(getActualPreRouteSummaryTableData, getActualPreRouteDetailTableData, Lob);
    	ValidateTotalPreRoutesWithException();
    	ValidateTotalPreRoutes();
    	ValidateExceptionTimePersentage();
    	ValidateVarianceFromPlan();
    	ValidateNumOfSite();
	}
   
    public void ValidateTier(Map<String, List<String>> getActualPreRouteDetailTableData) {
    	
    	
    	
    }
    
    
    private void validateDetailArea(Map<String, List<String>> getActualPreRouteDetailTableData, String site) {
		
		String actualAreaName=null;
		List<String> expectedAreaName = null;
		String siteID=site.substring(0, 6);
		try {
			
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
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
					Report.InfoTest(test, "Area is correct in Pre Route Summary report Detail Section. Actual is : "
							+ actualAreaName + " and Expected is : " + expectedAreaName.get(0));
					Report.PassTest(test, "Area is as expected in Pre Route Summary report Detail Section");
				} else {
					Report.FailTest(test, "Area is not as expected in Pre Route Summary report Detail Section. Actual is : " + actualAreaName
							+ " and Expected is : " + expectedAreaName.get(0));
				}
			
		} catch (Exception e) {
			Report.FailTest(test, "Area is not as expected in Pre Route Summary report Detail Section. Actual is : " + actualAreaName
					+ " and Expected is : " + expectedAreaName.get(0));
		}
		
		
		
	}

    private void validateDetailBU(Map<String, List<String>> getActualPreRouteDetailTableData, String site) {
		
		String actualBUName=null;
		List<String> expectedBUName = null;
		String siteID=site.substring(0, 6);
		try {
			
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
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
					Report.InfoTest(test, "BU is correct in Pre Route Summary report Detail Section. Actual is : "
							+ actualBUName + " and Expected is : " + expectedBUName.get(0));
					Report.PassTest(test, "BU is as expected in Pre Route Summary report Detail Section");
				} else {
					Report.FailTest(test, "BU is not as expected in Pre Route Summary report Detail Section. Actual is : " + actualBUName
							+ " and Expected is : " + expectedBUName.get(0));
				}
			
		} catch (Exception e) {
			Report.FailTest(test, "BU is not as expected in Pre Route Summary report Detail Section. Actual is : " + actualBUName
					+ " and Expected is : " + expectedBUName.get(0));
		}
		
	}

    private void validateDetailSite(Map<String, List<String>> getActualPreRouteDetailTableData, String site) {
		
		String actualSiteName=null;
		String expectedSiteName = site;
			
		try {
			
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
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
					Report.InfoTest(test, "Site is correct in Pre Route Summary report Detail Section. Actual is : "
							+ actualSiteName + " and Expected is : " + expectedSiteName);
					Report.PassTest(test, "Site is as expected in Pre Route Summary report Detail Section");
				} else {
					Report.FailTest(test, "Site is not as expected in Pre Route Summary report Detail Section. Actual is : " + actualSiteName
							+ " and Expected is : " + expectedSiteName);
				}
			
		} catch (Exception e) {
			Report.FailTest(test, "Site is not as expected in Pre Route Summary report Detail Section. Actual is : " + actualSiteName
					+ " and Expected is : " + expectedSiteName);
		}
		
	}
   
    public Map<String, List<String>> getPreRouteDetailReportSummaryTableData() throws IOException {
		Map<String, List<String>> preRouteSummaryTableData = new HashMap<>();
		try {
			preRouteSummaryTableData = readTable(preRouteSummaryTableColumns, preRouteSummaryTableRows,
					"dtPreRouteSummary");
			Report.PassTestScreenshot(test, driver, "Pre Route Summary table data read successfully",
					"Pre Route Detail report");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Pre Route summary table data");
		}

		for (Entry<String, List<String>> entry : preRouteSummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return preRouteSummaryTableData;
	}
  
    public Map<String, List<String>> getPreRouteDetailReportDetailTableDataAllTabPerformanceSubview() throws IOException {
		Map<String, List<String>> preRouteSummaryTableData = new HashMap<>();
		try {
			preRouteSummaryTableData = readTable(PreRouteDetailDataAllTabColumns, PreRouteDetailDataAllTabRows,
					"t2");
			Report.PassTestScreenshot(test, driver, "Pre Route Summary table data read successfully",
					"Pre Route Detail report");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Pre Route summary table data");
		}

		for (Entry<String, List<String>> entry : preRouteSummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return preRouteSummaryTableData;
	}
    
    public void RedirectedtoAllTAb() {
    	try {
    		RedirectedtoAllTab.click();
    		Thread.sleep(1000);
    		
    		Report.PassTest(test, "Redirected to All Tab Performance Subview");
    	
		} catch (Exception e) {
			Report.FailTest(test, "Unable to Redirect All Tab Performance Subview");
		}
    	
    }
    
    public void validatePreRouteSummaryDatailData(Map<String, List<String>> getActualPreRouteDetailTableData,Map<String, List<String>> getPreRouteDetailReportSummaryTableData, Map<String, List<String>>getPreRouteDetailReportDetailTableDataAllTabPerformanceSubview,String Site) {
    	ValidateTierName(getActualPreRouteDetailTableData);
    	validateDetailArea(getActualPreRouteDetailTableData, Site);
    	validateDetailBU(getActualPreRouteDetailTableData, Site);
    	validateDetailSite(getActualPreRouteDetailTableData, Site);
    	ValidateExceptionTimeHours(getActualPreRouteDetailTableData, getPreRouteDetailReportSummaryTableData);
    	ValidatePreRouteWException(getActualPreRouteDetailTableData, getPreRouteDetailReportSummaryTableData);
    	ValidateNumofPreRoute(getActualPreRouteDetailTableData, getPreRouteDetailReportSummaryTableData);
    	ValidateExceptionTimeDetail(getActualPreRouteDetailTableData, getPreRouteDetailReportSummaryTableData);
    	ValidateAvgActualHours(getActualPreRouteDetailTableData, getPreRouteDetailReportSummaryTableData);
    	ValidateAvgPlanHours(getActualPreRouteDetailTableData, getPreRouteDetailReportSummaryTableData);
    	ValidateVariancefromPlan(getActualPreRouteDetailTableData, getPreRouteDetailReportDetailTableDataAllTabPerformanceSubview);
    	
    }
   
    
    private void ValidateTierName( Map<String, List<String>> getActualPreRouteDetailTableData) {
    	String actualTierName=null;
		
			
		try {
			
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
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
					Report.InfoTest(test, "Tier is correct in Pre Route Summary report Detail Section. Actual is : "
							+ actualTierName + " and Expected is : North Or South");
					Report.PassTest(test, "Tier is as expected in Pre Route Summary report Detail Section");
				} else {
					Report.FailTest(test, "Tier is not as expected in Pre Route Summary report Detail Section. Actual is : " + actualTierName
							+ " and Expected is : North Or South ");
				}
			
		} catch (Exception e) {
			Report.FailTest(test, "Tier is not as expected in Pre Route Summary report Detail Section. Actual is : " + actualTierName
					+ " and Expected is : North Or South");
		}
		
    	
    	
    }
    
    public void ValidateVariancefromPlan(Map<String, List<String>> getActualPreRouteDetailTableData,Map<String, List<String>> getPreRouteDetailReportDetailTableDataAllTabPerformanceSubview) {
    	/*(Total Actual Pre-Route Time - Total Plan Pre-Route Time) / (Total Plan Pre-Route Time)
    	Only consider records where both Plan and Actual exist in calculating Plan/Actual time*/

    	
    	List<Double> actualVarianceFromPlan = new ArrayList<>();
    	int ActualHours=0;
    	int PlanHours=0;
    	
    	for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
			if (entry.getKey().equals("Variance from Plan %")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualVarianceFromPlan.add(0.00);
					} 
					
					else {
						actualVarianceFromPlan.add(Double.parseDouble(entry.getValue().get(i)));
						Util.validateFieldDecimalPlaces(entry.getValue().get(i), 1, "Actual Variance from Plan % in Detail Section");
					}
				}
			}
		}
    	
    	for (Entry<String, List<String>> entry : getPreRouteDetailReportDetailTableDataAllTabPerformanceSubview.entrySet()) {
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
    	
    	for (Entry<String, List<String>> entry : getPreRouteDetailReportDetailTableDataAllTabPerformanceSubview.entrySet()) {
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
    	
    	float actualdata = (((float)ActualHours - (float)PlanHours ) /(float)PlanHours)*100;
		double ActData=actualdata;
		if (actualVarianceFromPlan.contains(ActData)) {
			Report.PassTestScreenshot(test, driver, "Verified VariancefromPlan Actual is " +actualVarianceFromPlan + " And Expected is >" + ActData , "PassedVariancefromPlan");
			
		}
		else {
			Report.FailTestSnapshot(test, driver, "Unable to Verified VariancefromPlan Actual is " +actualVarianceFromPlan + " And Expected is >" + ActData , "FailedVariancefromPlan");
		}
    	  
    	
    	
    	
    	
    	
    }
    
    public void ValidateExceptionTimeHours(Map<String, List<String>> getActualPreRouteDetailTableData,Map<String, List<String>> getPreRouteDetailReportSummaryTableData) {
    	String ActualExceptionHours=null;
    	String TotalExceptionHours=null;
    	try {

        	for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
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
        	
        	
        	for (Entry<String, List<String>> entry : getPreRouteDetailReportSummaryTableData.entrySet()) {
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

    public void ValidatePreRouteWException(Map<String, List<String>> getActualPreRouteDetailTableData,Map<String, List<String>> getPreRouteDetailReportSummaryTableData) {
    	
    	int actualPreRouteWorkException = 0;
		int expectedPreRouteWorkException = 0;	
		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().equals("# of Pre-Routes w/ Exception")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualPreRouteWorkException = 0;
						} else {
							actualPreRouteWorkException = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualPreRouteWorkException, "Total Pre-Routes w/ Exception in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : getPreRouteDetailReportSummaryTableData.entrySet()) {
				if (entry.getKey().equals("Total Pre-Routes w/ Exception")) {
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
				Report.PassTestScreenshot(test, driver, "Verified # of Pre-Routes w/ Exception Actual is > " + actualPreRouteWorkException + "And Expected is >" + expectedPreRouteWorkException, "PassedPreRouteWException");
			}
			else {
				Report.FailTestSnapshot(test, driver, "Un-Verified # of Pre-Routes w/ Exception Actual is > " + actualPreRouteWorkException + "And Expected is >" + expectedPreRouteWorkException, "FailedPreRouteWException");
			}
			
			
		} catch (Exception e) {
			Report.FailTestSnapshot(test, driver, "Un-Verified # of Pre-Routes w/ Exception Actual is > " + actualPreRouteWorkException + "And Expected is >" + expectedPreRouteWorkException, "FailedPreRouteWException");
		}        	
    	
    	
    	
    }
    
    public void ValidateNumofPreRoute(Map<String, List<String>> getActualPreRouteDetailTableData,Map<String, List<String>> getPreRouteDetailReportSummaryTableData) {
    	
    	int actualPreRoute = 0;
		int expectedPreRoute = 0;	
		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
				if (entry.getKey().equals("# of Pre-Routes")) {
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
			for (Entry<String, List<String>> entry : getPreRouteDetailReportSummaryTableData.entrySet()) {
				if (entry.getKey().equals("Total Pre-Routes")) {
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
				Report.PassTestScreenshot(test, driver, "Verified # of Pre-Routes  Actual is > " + actualPreRoute + "And Expected is >" + expectedPreRoute, "PassedPreRoute");
			}
			else {
				Report.FailTestSnapshot(test, driver, "Un-Verified # of Pre-RoutesActual is > " + actualPreRoute + "And Expected is >" + expectedPreRoute, "FailedPreRoute");
			}
			
			
		} catch (Exception e) {
			Report.FailTestSnapshot(test, driver, "Un-Verified # of Pre-Routes  Actual is > " + actualPreRoute + "And Expected is >" + expectedPreRoute, "FailedPreRoute");
		}	
    }

    public void ValidateExceptionTimeDetail(Map<String, List<String>> getActualPreRouteDetailTableData,Map<String, List<String>> getPreRouteDetailReportSummaryTableData) {
    	
    	int actualExceptionTime  = 0;
		int expectedExceptionTime = 0;	
		try {
			for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
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
			for (Entry<String, List<String>> entry : getPreRouteDetailReportSummaryTableData.entrySet()) {
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

    public void ValidateAvgActualHours(Map<String, List<String>> getActualPreRouteDetailTableData,Map<String, List<String>> getPreRouteDetailReportSummaryTableData) {
    	String ActualAvgActual=null;
    	String ExpectedAvgActual=null;
    	try {

        	for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
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
        	
        	
        	for (Entry<String, List<String>> entry : getPreRouteDetailReportSummaryTableData.entrySet()) {
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

    public void ValidateAvgPlanHours(Map<String, List<String>> getActualPreRouteDetailTableData,Map<String, List<String>> getPreRouteDetailReportSummaryTableData) {
    	String ActualAvgPlan=null;
    	String ExpectedAvgPlan=null;
    	try {

        	for (Entry<String, List<String>> entry : getActualPreRouteDetailTableData.entrySet()) {
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
        	
        	
        	for (Entry<String, List<String>> entry : getPreRouteDetailReportSummaryTableData.entrySet()) {
    			if (entry.getKey().contains("Avg Plan (h:m)")) {
    				for (int i = 0; i < entry.getValue().size(); i++) {
    					if (entry.getValue().get(i).equals("--")) {
    						ExpectedAvgPlan = null;
    					} else {
    						
    						ExpectedAvgPlan = entry.getValue().get(i).toString();
    						Util.validateTimeFormat(ExpectedAvgPlan, "Avg PlanH ours in Detail Section");
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
    	Popupclick("dtPreRouteDetail");
    	ValidatefixedText(Site, FromDate, ToDate);  	
    	ValidateColumnNameinPopUp();
    	ValidateExportExcelButton(ReportName, downloadFilePath);
    	ValidatePopUpDatainDetailReport("Pre-Route Detail","Pre-Route Summary","dtPreRouteSummary");
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
    		Util.validateColumns(actualpopupColumns, GlobalExpectedColumns.PreRouteSummaryPopUpColumns, "Pre Route Summary pop Up columns");
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
    
   
    
    
   private void ValidatePopUpDatainDetailReport(String expectedDetailReportTitle,String expectedSummaryReportTitle,String SummaryTableName) {
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
