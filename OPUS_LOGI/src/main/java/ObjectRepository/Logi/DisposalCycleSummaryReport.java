package ObjectRepository.Logi;

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

public class DisposalCycleSummaryReport {
	
	ExtentTest test;
	WebDriver driver;

	public DisposalCycleSummaryReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//table[@id='dtDisposalTimeSummary']/thead/tr/th")
	List<WebElement> dispoalCycleSummaryTableColumns;

	@FindBy(xpath = "//table[@id='dtDisposalTimeSummary']/tbody/tr")
	List<WebElement> dispoalCycleSummaryTableRows;
	
	@FindBy(xpath = "//table[@id='dtDisposalTimeDetail']/thead/tr/th")
	List<WebElement> dispoalCycleDetailTableColumns;

	@FindBy(xpath = "//table[@id='dtDisposalTimeDetail']/tbody/tr")
	List<WebElement> dispoalCycleDetailTableRows;
	
	
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
	    
	
	public Map<String, List<String>> getActualSummaryTableData() {
		System.out.println("inside Actual Summary Data");
		Map<String, List<String>> summaryTableData = new HashMap<>();
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		try {
			summaryTableData = readTable(dispoalCycleSummaryTableColumns, dispoalCycleSummaryTableRows, "dtDisposalTimeSummary");
			Report.PassTestScreenshot(test, driver, "Summary table data read successfully", "Disposal Cycle Summary Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read summary table data");
		}

		for (Entry<String, List<String>> entry : summaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return summaryTableData;
	}

	public Map<String, List<String>> getActualDetailTableData() {
		
		System.out.println("inside Actual Dispoal Cycle Summary Detail Table Data");
		
		Map<String, List<String>> dispoalCycleSummaryDetailTableData = new HashMap<>();
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		
		try {
			dispoalCycleSummaryDetailTableData = readTable(dispoalCycleDetailTableColumns, dispoalCycleDetailTableRows, "dtDisposalTimeDetail");
			Report.PassTestScreenshot(test, driver, "Actual Disposal Cycle Summary Detail table data read successfully",
					"Disposal Cycle Summary Detail Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Disposal Cycle Summary Detail table data");
		}
		for (Entry<String, List<String>> entry : dispoalCycleSummaryDetailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return dispoalCycleSummaryDetailTableData;
	}
	
	public Map<String, List<String>> readTable(List<WebElement> columns, List<WebElement> rows, String tableName) {
			Map<String, List<String>> objTable = new HashMap<>();			
			for (int iCount = 1; iCount <= columns.size(); iCount++) {
				List<String> rowValues = new ArrayList<>();
				for (int row = 1; row <= rows.size(); row++) {

					try {
						rowValues.add(driver.findElement(By.xpath(
								"//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + iCount + "]/span"))
								.getText());
					} catch (Exception e) {
						rowValues.add(driver
								.findElement(By.xpath(
										"//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + iCount + "]"))
								.getText());
					}
				}
				objTable.put(columns.get(iCount - 1).getText(), rowValues);
			}
			return objTable;
	}
	
	public void validateDisposalCycleSummaryData(Map<String, List<String>> actualDisposalCycleSummaryTable,
			Map<String, List<String>> actualDisposalCycleSummaryDetailTable, String lOB) {


		validateSummaryTotalExceptionTime(actualDisposalCycleSummaryTable,actualDisposalCycleSummaryDetailTable);
		validateSummaryTotalDisposalLoadsWithException(actualDisposalCycleSummaryTable,actualDisposalCycleSummaryDetailTable);
		validateSummaryTotalDisposalLoads(actualDisposalCycleSummaryTable,actualDisposalCycleSummaryDetailTable);
		validateSummaryExceptionTimePercent(actualDisposalCycleSummaryTable,actualDisposalCycleSummaryDetailTable);
		validateSummaryVarianceFromThresholdPercent(actualDisposalCycleSummaryTable,actualDisposalCycleSummaryDetailTable);
		validateSummaryNoOfSites(actualDisposalCycleSummaryTable,actualDisposalCycleSummaryDetailTable);
		
		
	}

	private void validateSummaryTotalExceptionTime(Map<String, List<String>> actualDisposalCycleSummaryTable,
			Map<String, List<String>> actualDisposalCycleSummaryDetailTable) {
		
		String actualSummaryTotalExceptionTime = null;
		String expectedSummaryTotalExceptionTime = null;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : actualDisposalCycleSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Exception Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualSummaryTotalExceptionTime = "99:99";
						} else {
							actualSummaryTotalExceptionTime = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDisposalCycleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Exception Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins += mins;
						}

					}
				}
			}

			// Validate Time Format of Actual Driver Hours
			Util.validateTimeFormat(actualSummaryTotalExceptionTime, "Exception Time (h:m) in Summary Section");

			// expectedNetIdleTime=sum of idle time of each
			// routes
			// expectedActualDriverHours=String.format("%02d",(int)Math.floor(totalMins/60))
			// + ":" +String.format("%02d",( totalMins % 60 ));
			expectedSummaryTotalExceptionTime = Util.convertMinsToHours(totalMins);

			if (actualSummaryTotalExceptionTime.equals("99:99")) {
				Report.FailTest(test,
						"Total Exception Time (h:m) is not calculated as expected in summary report Actual is : -- and Expected is : "
								+ expectedSummaryTotalExceptionTime);
			} else {

				if (Util.compareTime(actualSummaryTotalExceptionTime, expectedSummaryTotalExceptionTime)) {
					Report.InfoTest(test, "Total Exception Time (h:m) is correct in summary report Actual is : "
							+ actualSummaryTotalExceptionTime + " and Expected is : " + expectedSummaryTotalExceptionTime);
					Report.PassTest(test, "Total Exception Time (h:m) is as expected in Summary report");
				} else {
					Report.FailTest(test, "Total Exception Time (h:m) is not as expected in summary report Actual is : "
							+ actualSummaryTotalExceptionTime + " and Expected is : " + expectedSummaryTotalExceptionTime);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Exception Time (h:m) is not as expected in summary report Actual is : "
					+ actualSummaryTotalExceptionTime + " and Expected is : " + expectedSummaryTotalExceptionTime);
		}
		
		
	}

	private void validateSummaryTotalDisposalLoadsWithException(
			Map<String, List<String>> actualDisposalCycleSummaryTable,
			Map<String, List<String>> actualDisposalCycleSummaryDetailTable) {
		
		int actualSummaryTotalDisposalLoadsWithException = 0;
		int expectedSummaryTotalDisposalLoadsWithException = 0;	
		try {
			for (Entry<String, List<String>> entry : actualDisposalCycleSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Disposal Loads w/ Exception")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualSummaryTotalDisposalLoadsWithException = 999;
						} else {
							actualSummaryTotalDisposalLoadsWithException = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualSummaryTotalDisposalLoadsWithException, "Total Disposal Loads w/ Exception in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualDisposalCycleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("# of Disposal Loads w/ Exception")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedSummaryTotalDisposalLoadsWithException = expectedSummaryTotalDisposalLoadsWithException + Integer.parseInt("0.0");
						} else {
							expectedSummaryTotalDisposalLoadsWithException = expectedSummaryTotalDisposalLoadsWithException
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			// Summary Total Disposal Loads w/ Exception = sum of idle occurences of all routes
			
			if (actualSummaryTotalDisposalLoadsWithException == 999) {
				Report.FailTest(test,
						"Total Disposal Loads w/ Exception is not calculated as expected in Disposal Cycle summary report Actual is : -- and Expected is : "
								+ expectedSummaryTotalDisposalLoadsWithException);
			} else {
				if (Util.compareNumber(actualSummaryTotalDisposalLoadsWithException, expectedSummaryTotalDisposalLoadsWithException)) {
					Report.InfoTest(test, "Total Disposal Loads w/ Exception is correct in Disposal Cycle summary report Actual is : "
							+ actualSummaryTotalDisposalLoadsWithException + " and Expected is : " + expectedSummaryTotalDisposalLoadsWithException);
					Report.PassTest(test, "Total Disposal Loads w/ Exception is as expected in Disposal Cycle Summary report");
				} else {
					Report.FailTest(test, "Total Disposal Loads w/ Exception is not as expected in Disposal Cycle summary report Actual is : " + actualSummaryTotalDisposalLoadsWithException
							+ " and Expected is : " + expectedSummaryTotalDisposalLoadsWithException);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Disposal Loads w/ Exception is not as expected in Disposal Cycle summary report Actual is : " + actualSummaryTotalDisposalLoadsWithException
					+ " and Expected is : " + expectedSummaryTotalDisposalLoadsWithException);
		}
		
		
	}

	private void validateSummaryTotalDisposalLoads(Map<String, List<String>> actualDisposalCycleSummaryTable,
			Map<String, List<String>> actualDisposalCycleSummaryDetailTable) {
		
		int actualSummaryTotalDisposalLoads = 0;
		int expectedSummaryTotalDisposalLoads = 0;	
		try {
			for (Entry<String, List<String>> entry : actualDisposalCycleSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Disposal Loads")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualSummaryTotalDisposalLoads = 999;
						} else {
							actualSummaryTotalDisposalLoads = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualSummaryTotalDisposalLoads, "Total Disposal Loads in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualDisposalCycleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("# of Disposal Loads")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedSummaryTotalDisposalLoads = expectedSummaryTotalDisposalLoads + Integer.parseInt("0.0");
						} else {
							expectedSummaryTotalDisposalLoads = expectedSummaryTotalDisposalLoads
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			// Summary Total Disposal Loads = sum of idle occurences of all routes
			
			if (actualSummaryTotalDisposalLoads == 999) {
				Report.FailTest(test,
						"Total Disposal Loads is not calculated as expected in Disposal Cycle summary report Actual is : -- and Expected is : "
								+ expectedSummaryTotalDisposalLoads);
			} else {
				if (Util.compareNumber(actualSummaryTotalDisposalLoads, expectedSummaryTotalDisposalLoads)) {
					Report.InfoTest(test, "Total Disposal Loads is correct in Disposal Cycle summary report Actual is : "
							+ actualSummaryTotalDisposalLoads + " and Expected is : " + expectedSummaryTotalDisposalLoads);
					Report.PassTest(test, "Total Disposal Loads is as expected in Disposal Cycle Summary report");
				} else {
					Report.FailTest(test, "Total Disposal Loads is not as expected in Disposal Cycle summary report Actual is : " + actualSummaryTotalDisposalLoads
							+ " and Expected is : " + expectedSummaryTotalDisposalLoads);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Disposal Loads is not as expected in Disposal Cycle summary report Actual is : " + actualSummaryTotalDisposalLoads
					+ " and Expected is : " + expectedSummaryTotalDisposalLoads);
		}
		
	}

	private void validateSummaryExceptionTimePercent(Map<String, List<String>> actualDisposalCycleSummaryTable,
			Map<String, List<String>> actualDisposalCycleSummaryDetailTable) {
		//Exception Time Percent = Total Exception time/Total Disposal Time
		//Total Actual Disposal Cycle Time = SUM[(Exception Time * 100)/Exception Time %]
		
		int actualSummaryTotalExceptionTimePercent = 0;
		int expectedSummaryTotalExceptionTimePercent = 0;	
		List<String> exceptionTime=new ArrayList<>();
		List<Integer> exceptionTimePercent=new ArrayList<>();
		try {
			for (Entry<String, List<String>> entry : actualDisposalCycleSummaryTable.entrySet()) {
				if (entry.getKey().equals("Exception Time %")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualSummaryTotalExceptionTimePercent = 999;
						} else {
							actualSummaryTotalExceptionTimePercent = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualSummaryTotalExceptionTimePercent, "Exception Time %  in Summary Section");
						}
					}
				}
			}
						
			
			for (Entry<String, List<String>> entry : actualDisposalCycleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Exception Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							exceptionTime.add("00:00");
						} else {
							exceptionTime.add(entry.getValue().get(i).trim());
							
						}
					}

				
				}
			}
			for (Entry<String, List<String>> entry : actualDisposalCycleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Exception Time %")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							exceptionTimePercent.add(0);
						} else {
							exceptionTimePercent.add(Integer.parseInt(entry.getValue().get(i).trim()));
							
						}
					}
				}
			}
			
			List<Integer> exceptionTimeInMins=new ArrayList<>();
			
		
			for(int i=0;i<exceptionTime.size();i++)
			{
				exceptionTimeInMins.add(Util.convertHoursToMins(exceptionTime.get(i)));
				
			}
			
			for(int i=0;i<exceptionTime.size();i++)
			{				
				if(!(exceptionTimeInMins.get(i)==0))
				{				
				 expectedSummaryTotalExceptionTimePercent+=((exceptionTimeInMins.get(i)*100)/((exceptionTimeInMins.get(i)*100)/exceptionTimePercent.get(i)));
				}				
			}
	
			if (actualSummaryTotalExceptionTimePercent == 999) {
				Report.FailTest(test,
						"Total Exception Time % is not calculated as expected in Disposal Cycle summary report Actual is : -- and Expected is : "
								+ expectedSummaryTotalExceptionTimePercent);
			} else {
				if (Util.compareNumber(actualSummaryTotalExceptionTimePercent, expectedSummaryTotalExceptionTimePercent)) {
					Report.InfoTest(test, "Total Exception Time % is correct in Disposal Cycle summary report Actual is : "
							+ actualSummaryTotalExceptionTimePercent + " and Expected is : " + expectedSummaryTotalExceptionTimePercent);
					Report.PassTest(test, "Total Exception Time % is as expected in Disposal Cycle Summary report");
				} else {
					Report.FailTest(test, "Total Exception Time % is not as expected in Disposal Cycle summary report Actual is : " + actualSummaryTotalExceptionTimePercent
							+ " and Expected is : " + expectedSummaryTotalExceptionTimePercent);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Exception Time % is not as expected in Disposal Cycle summary report Actual is : " + actualSummaryTotalExceptionTimePercent
					+ " and Expected is : " + expectedSummaryTotalExceptionTimePercent);
		}
		
		
	}

	private void validateSummaryVarianceFromThresholdPercent(Map<String, List<String>> actualDisposalCycleSummaryTable,
			Map<String, List<String>> actualDisposalCycleSummaryDetailTable) {
		
		//	Variance from Threshold % = SUM[Variance from Threshold %]/no Of Sites
				
				
				double actualSummaryVarianceFromThresholdPercent = 0;
				double expectedSummaryVarianceFromThresholdPercent = 0;
				double totalVarianceFromThresholdPercent = 0;
				int noOfSites=0;				
				try {
					for (Entry<String, List<String>> entry : actualDisposalCycleSummaryTable.entrySet()) {
						if (entry.getKey().equals("Variance from Threshold %")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualSummaryVarianceFromThresholdPercent = 999;
								} else {
									actualSummaryVarianceFromThresholdPercent = Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
									Util.validateFieldDecimalPlaces(entry.getValue().get(i).trim().replaceAll(",", ""), 1,"Variance from Threshold %  in Summary Section");
								}
							}
						}
					}
					for (Entry<String, List<String>> entry : actualDisposalCycleSummaryDetailTable.entrySet()) {
						if (entry.getKey().equals("Variance from Threshold %")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									totalVarianceFromThresholdPercent+=0.0;
								} else {
									totalVarianceFromThresholdPercent+=Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
									
								}
							}
						}
					}
					for (Entry<String, List<String>> entry : actualDisposalCycleSummaryDetailTable.entrySet()) {
						if (entry.getKey().equals("Site")) {							
								noOfSites=entry.getValue().size();						
						}
					}
					

				expectedSummaryVarianceFromThresholdPercent=totalVarianceFromThresholdPercent/noOfSites;
			
					if (actualSummaryVarianceFromThresholdPercent == 999) {
						Report.FailTest(test,
								"Variance from Threshold % is not calculated as expected in Disposal Cycle summary report Actual is : -- and Expected is : "
										+ expectedSummaryVarianceFromThresholdPercent);
					} else {
						if (Util.compareNumber(actualSummaryVarianceFromThresholdPercent, expectedSummaryVarianceFromThresholdPercent)) {
							Report.InfoTest(test, "Variance from Threshold % is correct in Disposal Cycle summary report Actual is : "
									+ actualSummaryVarianceFromThresholdPercent + " and Expected is : " + expectedSummaryVarianceFromThresholdPercent);
							Report.PassTest(test, "Variance from Threshold % is as expected in Disposal Cycle Summary report");
						} else {
							Report.FailTest(test, "Variance from Threshold % is not as expected in Disposal Cycle summary report Actual is : " + actualSummaryVarianceFromThresholdPercent
									+ " and Expected is : " + expectedSummaryVarianceFromThresholdPercent);
						}
					}
				} catch (Exception e) {
					Report.FailTest(test, "Variance from Threshold % is not as expected in Disposal Cycle summary report Actual is : " + actualSummaryVarianceFromThresholdPercent
							+ " and Expected is : " + expectedSummaryVarianceFromThresholdPercent);
				}
		
	}

	private void validateSummaryNoOfSites(Map<String, List<String>> actualDisposalCycleSummaryTable,
			Map<String, List<String>> actualDisposalCycleSummaryDetailTable) {
	
		// # of sites = sites displayed in the detail section
				int actualNumberOfSites = 0;
				int expectedNumberOfSites = 0;
				
				try {
					for (Entry<String, List<String>> entry : actualDisposalCycleSummaryTable.entrySet()) {
						if (entry.getKey().equals("# of Sites")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualNumberOfSites = 0;
								} else {
									actualNumberOfSites = Integer.parseInt(entry.getValue().get(i));
									Util.validateWholeNumber(actualNumberOfSites, "# of Sites in Summary Section");
								}
							}
						}
					}
					for (Entry<String, List<String>> entry : actualDisposalCycleSummaryDetailTable.entrySet()) {
						if (entry.getKey().equals("Site")) {		
							expectedNumberOfSites=entry.getValue().size();
						}
					}	
						if (Util.compareNumber(actualNumberOfSites, expectedNumberOfSites)) {
							Report.InfoTest(test, "# of Sites is correct in Disposal Cycle summary report Actual is : "
									+ actualNumberOfSites + " and Expected is : " + expectedNumberOfSites);
							Report.PassTest(test, "# of Sites is as expected in Disposal Cycle Summary report");
						} else {
							Report.FailTest(test, "# of Sites is not as expected in Disposal Cycle summary report Actual is : " + actualNumberOfSites
									+ " and Expected is : " + expectedNumberOfSites);
						}
			
				} catch (Exception e) {
					Report.FailTest(test, "# of Sites is not as expected in Disposal Cycle summary report Actual is : " + actualNumberOfSites
							+ " and Expected is : " + expectedNumberOfSites);
				}
		
	}

	public void validateDisposalCycleSummaryDetailData(Map<String, List<String>> actualDisposalCycleSummaryDetailTable,
			Map<String, List<String>> actualDisposalCycleDetailSummaryTable, String site, String lOB) {
		
		//validateDetailTier(actualDisposalCycleSummaryDetailTable,site);
		validateDetailArea(actualDisposalCycleSummaryDetailTable,site);
		validateDetailBU(actualDisposalCycleSummaryDetailTable,site);
		validateDetailSite(actualDisposalCycleSummaryDetailTable,site);	
		validateDetailExceptionTime(actualDisposalCycleSummaryDetailTable,actualDisposalCycleDetailSummaryTable);	
		validateDetailNoOfDisposalLoadsWithException(actualDisposalCycleSummaryDetailTable,actualDisposalCycleDetailSummaryTable);	
		validateDetailNoOfDisposalLoads(actualDisposalCycleSummaryDetailTable,actualDisposalCycleDetailSummaryTable);
		validateDetailExceptionTime(actualDisposalCycleSummaryDetailTable,actualDisposalCycleDetailSummaryTable);	
		validateDetailVarianceFromThreshold(actualDisposalCycleSummaryDetailTable,actualDisposalCycleDetailSummaryTable);	
		validateDetailAvgActual(actualDisposalCycleSummaryDetailTable,actualDisposalCycleDetailSummaryTable);
		validateDetailAvgThreshold(actualDisposalCycleSummaryDetailTable,actualDisposalCycleDetailSummaryTable);
		
	}

	private void validateDetailArea(Map<String, List<String>> actualDisposalCycleSummaryDetailTable, String site) {
		
		String actualAreaName=null;
		List<String> expectedAreaName = null;
		String siteID=site.substring(0, 6);
		try {
			
			for (Entry<String, List<String>> entry : actualDisposalCycleSummaryDetailTable.entrySet()) {
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
					Report.InfoTest(test, "Area is correct in Disposal Cycle Summary report Detail Section. Actual is : "
							+ actualAreaName + " and Expected is : " + expectedAreaName.get(0));
					Report.PassTest(test, "Area is as expected in Disposal Cycle Summary report Detail Section");
				} else {
					Report.FailTest(test, "Area is not as expected in Disposal Cycle Summary report Detail Section. Actual is : " + actualAreaName
							+ " and Expected is : " + expectedAreaName.get(0));
				}
			
		} catch (Exception e) {
			Report.FailTest(test, "Area is not as expected in Disposal Cycle Summary report Detail Section. Actual is : " + actualAreaName
					+ " and Expected is : " + expectedAreaName.get(0));
		}
		
	}

	private void validateDetailBU(Map<String, List<String>> actualDisposalCycleSummaryDetailTable, String site) {
		
		String actualBUName=null;
		List<String> expectedBUName = null;
		String siteID=site.substring(0, 6);
		try {
			
			for (Entry<String, List<String>> entry : actualDisposalCycleSummaryDetailTable.entrySet()) {
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
					Report.InfoTest(test, "BU is correct in Disposal Cycle Summary report Detail Section. Actual is : "
							+ actualBUName + " and Expected is : " + expectedBUName.get(0));
					Report.PassTest(test, "BU is as expected in Disposal Cycle Summary report Detail Section");
				} else {
					Report.FailTest(test, "BU is not as expected in Disposal Cycle Summary report Detail Section. Actual is : " + actualBUName
							+ " and Expected is : " + expectedBUName.get(0));
				}
			
		} catch (Exception e) {
			Report.FailTest(test, "BU is not as expected in Disposal Cycle Summary report Detail Section. Actual is : " + actualBUName
					+ " and Expected is : " + expectedBUName.get(0));
		}
		
	}

	private void validateDetailSite(Map<String, List<String>> actualDisposalCycleSummaryDetailTable, String site) {

		String actualSiteName=null;
		String expectedSiteName = site;
			
		try {
			
			for (Entry<String, List<String>> entry : actualDisposalCycleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Site")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!(entry.getValue().get(i).equals("--"))) {							
							actualSiteName = entry.getValue().get(i).toString();
						}
					}
				}
			}

			
				if (actualSiteName.equals(expectedSiteName)) {
					Report.InfoTest(test, "Site is correct in Disposal Cycle Summary report Detail Section. Actual is : "
							+ actualSiteName + " and Expected is : " + expectedSiteName);
					Report.PassTest(test, "Site is as expected in Disposal Cycle Summary report Detail Section");
				} else {
					Report.FailTest(test, "Site is not as expected in Disposal Cycle Summary report Detail Section. Actual is : " + actualSiteName
							+ " and Expected is : " + expectedSiteName);
				}
			
		} catch (Exception e) {
			Report.FailTest(test, "Site is not as expected in Disposal Cycle Summary report Detail Section. Actual is : " + actualSiteName
					+ " and Expected is : " + expectedSiteName);
		}
		
	}

	private void validateDetailExceptionTime(Map<String, List<String>> actualDisposalCycleSummaryDetailTable,
			Map<String, List<String>> actualDisposalCycleDetailSummaryTable) {
		String actualExceptionTime = null;
		String expectedExceptionTime = null;
		
		try {
			for (Entry<String, List<String>> entry : actualDisposalCycleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Exception Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualExceptionTime = "99:99";
						} else {
							actualExceptionTime = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDisposalCycleDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Exception Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedExceptionTime = "00:00";
						} else {
							expectedExceptionTime = entry.getValue().get(i).toString();
						}
					

					}
				}
			}

			// Validate Time Format of Exception Time
			Util.validateTimeFormat(actualExceptionTime, "Exception Time in Detail Section");


			if (actualExceptionTime.equals("99:99")) {
				Report.FailTest(test,
						"Exception Time is not calculated as expected in Disposal Cycle Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedExceptionTime);
			} else {

				if (actualExceptionTime.equals(expectedExceptionTime)) {
					Report.InfoTest(test, "Exception Time is correct in Disposal Cycle Summary report Detail Section. Actual is : "
							+ actualExceptionTime + " and Expected is : " + expectedExceptionTime);
					Report.PassTest(test, "Exception Time is as expected in Summary report");
				} else {
					Report.FailTest(test, "Exception Time is not as expected in Disposal Cycle Summary report Detail Section. Actual is : "
							+ actualExceptionTime + " and Expected is : " + expectedExceptionTime);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Exception Time is not as expected in Disposal Cycle Summary report Detail Section. Actual is : "
					+ actualExceptionTime + " and Expected is : " + expectedExceptionTime);
		}
		
	}
	
	private void validateDetailNoOfDisposalLoadsWithException(
			Map<String, List<String>> actualDisposalCycleSummaryDetailTable,
			Map<String, List<String>> actualDisposalCycleDetailSummaryTable) {
		
		int actualNumberOfDisposalLoadsWithException = 0;
		int expectedNumberOfDisposalLoadsWithException = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualDisposalCycleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("# of Disposal Loads w/ Exception")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualNumberOfDisposalLoadsWithException =999;
						} else {
							actualNumberOfDisposalLoadsWithException = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualNumberOfDisposalLoadsWithException, "# of Disposal Loads w/ Exception");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDisposalCycleDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Disposal Loads w/ Exception")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedNumberOfDisposalLoadsWithException = 0;
						} else {
							expectedNumberOfDisposalLoadsWithException = Integer.parseInt(entry.getValue().get(i));
						}
					

					}
				}
			}

		
			

			// # of Disposal Loads w/ Exception = Total Disposal Loads w/ Exception for corresponding site in detail report;
			

			if (actualNumberOfDisposalLoadsWithException==999) {
				Report.FailTest(test,
						"# of Disposal Loads w/ Exception is not calculated as expected in Disposal Cycle Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedNumberOfDisposalLoadsWithException);
			} else {

				if (Util.compareNumber(actualNumberOfDisposalLoadsWithException,expectedNumberOfDisposalLoadsWithException)) {
					Report.InfoTest(test, "# of Disposal Loads w/ Exception is correct in Disposal Cycle Summary report Detail Section. Actual is : "
							+ actualNumberOfDisposalLoadsWithException + " and Expected is : " + expectedNumberOfDisposalLoadsWithException);
					Report.PassTest(test, "# of Disposal Loads w/ Exception is as expected in Summary report");
				} else {
					Report.FailTest(test, "# of Disposal Loads w/ Exception is not as expected in Disposal Cycle Summary report Detail Section. Actual is : "
							+ actualNumberOfDisposalLoadsWithException + " and Expected is : " + expectedNumberOfDisposalLoadsWithException);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "# of Disposal Loads w/ Exception is not as expected in Disposal Cycle Summary report Detail Section. Actual is : "
					+ actualNumberOfDisposalLoadsWithException + " and Expected is : " + expectedNumberOfDisposalLoadsWithException);
		}
		
	}

	private void validateDetailNoOfDisposalLoads(Map<String, List<String>> actualDisposalCycleSummaryDetailTable,
			Map<String, List<String>> actualDisposalCycleDetailSummaryTable) {
		
		int actualNumberOfDisposalLoads = 0;
		int expectedNumberOfDisposalLoads = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualDisposalCycleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("# of Disposal Loads")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualNumberOfDisposalLoads = 0;
						} else {
							actualNumberOfDisposalLoads = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualNumberOfDisposalLoads, "# of Disposal Loads");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDisposalCycleDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Disposal Loads")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedNumberOfDisposalLoads = 0;
						} else {
							expectedNumberOfDisposalLoads = Integer.parseInt(entry.getValue().get(i));
						}
					

					}
				}
			}

		
			

			// # of Disposal Loads = Total Disposal Loads for corresponding site in detail report;
			

			if (actualNumberOfDisposalLoads==0) {
				Report.FailTest(test,
						"# of Disposal Loads is not calculated as expected in Disposal Cycle Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedNumberOfDisposalLoads);
			} else {

				if (Util.compareNumber(actualNumberOfDisposalLoads,expectedNumberOfDisposalLoads)) {
					Report.InfoTest(test, "# of Disposal Loads is correct in Disposal Cycle Summary report Detail Section. Actual is : "
							+ actualNumberOfDisposalLoads + " and Expected is : " + expectedNumberOfDisposalLoads);
					Report.PassTest(test, "# of Disposal Loads is as expected in Summary report");
				} else {
					Report.FailTest(test, "# of Disposal Loads is not as expected in Disposal Cycle Summary report Detail Section. Actual is : "
							+ actualNumberOfDisposalLoads + " and Expected is : " + expectedNumberOfDisposalLoads);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "# of Disposal Loads is not as expected in Disposal Cycle Summary report Detail Section. Actual is : "
					+ actualNumberOfDisposalLoads + " and Expected is : " + expectedNumberOfDisposalLoads);
		}
		
	}

	

	private void validateDetailVarianceFromThreshold(Map<String, List<String>> actualDisposalCycleSummaryDetailTable,
			Map<String, List<String>> actualDisposalCycleDetailSummaryTable) {
		
		
	}

	private void validateDetailAvgActual(Map<String, List<String>> actualDisposalCycleSummaryDetailTable,
			Map<String, List<String>> actualDisposalCycleDetailSummaryTable) {
		
		String actualAvgActual = null;
		String expectedAvgActual = null;
		
		try {
			for (Entry<String, List<String>> entry : actualDisposalCycleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Avg Actual (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualAvgActual = "00:00";
						} else {
							actualAvgActual = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDisposalCycleDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("Avg Actual Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedAvgActual = "00:00";
						} else {
							expectedAvgActual = entry.getValue().get(i).toString();
						}
					

					}
				}
			}

			// Validate Time Format of Avg Actual Time (h:m)
			Util.validateTimeFormat(actualAvgActual, "Avg Actual (h:m) in Detail Section");


			if (actualAvgActual.equals("00:00")) {
				Report.FailTest(test,
						"Avg Actual (h:m) is not calculated as expected in Disposal Cycle Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedAvgActual);
			} else {

				if (actualAvgActual.equals(expectedAvgActual)) {
					Report.InfoTest(test, "Avg Actual (h:m) is correct in Disposal Cycle Summary report Detail Section. Actual is : "
							+ actualAvgActual + " and Expected is : " + expectedAvgActual);
					Report.PassTest(test, "Avg Actual (h:m) is as expected in Summary report");
				} else {
					Report.FailTest(test, "Avg Actual (h:m) is not as expected in Disposal Cycle Summary report Detail Section. Actual is : "
							+ actualAvgActual + " and Expected is : " + expectedAvgActual);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Avg Actual (h:m) is not as expected in Disposal Cycle Summary report Detail Section. Actual is : "
					+ actualAvgActual + " and Expected is : " + expectedAvgActual);
		}
		
	}

	private void validateDetailAvgThreshold(Map<String, List<String>> actualDisposalCycleSummaryDetailTable,
			Map<String, List<String>> actualDisposalCycleDetailSummaryTable) {
	
		String actualAvgThreshold = null;
		String expectedAvgThreshold = null;
		
		try {
			for (Entry<String, List<String>> entry : actualDisposalCycleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Avg Threshold (h:m) ")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualAvgThreshold = "00:00";
						} else {
							actualAvgThreshold = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDisposalCycleDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("Avg Threshold Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedAvgThreshold = "00:00";
						} else {
							expectedAvgThreshold = entry.getValue().get(i).toString();
						}
					

					}
				}
			}

			// Validate Time Format of Avg Threshold Time (h:m)
			Util.validateTimeFormat(actualAvgThreshold, "Avg Threshold (h:m)  in Detail Section");


			if (actualAvgThreshold.equals("00:00")) {
				Report.FailTest(test,
						"Avg Threshold (h:m)  is not calculated as expected in Disposal Cycle Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedAvgThreshold);
			} else {

				if (actualAvgThreshold.equals(expectedAvgThreshold)) {
					Report.InfoTest(test, "Avg Threshold (h:m)  is correct in Disposal Cycle Summary report Detail Section. Actual is : "
							+ actualAvgThreshold + " and Expected is : " + expectedAvgThreshold);
					Report.PassTest(test, "Avg Threshold (h:m)  is as expected in Summary report");
				} else {
					Report.FailTest(test, "Avg Threshold (h:m)  is not as expected in Disposal Cycle Summary report Detail Section. Actual is : "
							+ actualAvgThreshold + " and Expected is : " + expectedAvgThreshold);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Avg Threshold (h:m)  is not as expected in Disposal Cycle Summary report Detail Section. Actual is : "
					+ actualAvgThreshold + " and Expected is : " + expectedAvgThreshold);
		}
	}

	public void ValidatePopUp(String Site, String FromDate, String ToDate,String ReportName,String downloadFilePath) {
    	Popupclick("dtDisposalTimeDetail");
    	ValidatefixedText(Site, FromDate, ToDate);  	
    	ValidateColumnNameinPopUp();
    	ValidateExportExcelButton(ReportName, downloadFilePath);
    	ValidatePopUpDatainDetailReport("Disposal Cycle Detail","Disposal Cycle Summary","dtDisposalTimeSummary");
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
    		Util.validateColumns(actualpopupColumns, GlobalExpectedColumns.PreRouteSummaryPopUpColumns, "Disposal Cycle Summary pop Up columns");
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
