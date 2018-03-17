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

public class SequenceComplianceSummaryReport {
	
	ExtentTest test;
	WebDriver driver;

	public SequenceComplianceSummaryReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//table[@id='dtSeqComplianceSummary']/thead/tr/th")
	List<WebElement> sequenceComplianceSummaryTableColumns;

	@FindBy(xpath = "//table[@id='dtSeqComplianceSummary']/tbody/tr")
	List<WebElement> sequenceComplianceSummaryTableRows;
	
	@FindBy(xpath = "//table[@id='dtSeqComplianceDetail']/thead/tr/th")
	List<WebElement> sequenceComplinaceSummaryDetailTableColumns;

	@FindBy(xpath = "//table[@id='dtSeqComplianceDetail']/tbody/tr")
	List<WebElement> sequenceComplinaceSummaryDetailTableRows;
	
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
    
    //ExpectedInSeqPercentages
    @FindBy(xpath="(//*[@id='dtExcpPopUp']/tbody/tr/td[contains(@id,'colPctInSeq_Row1')]/span)[1]")
    WebElement ExpectedInSeqPercentages;
    
    
    //GetDetailtableTitle
    @FindBy(xpath="//*[@id='lblReportTitle']") 
   WebElement GetDetailtableTitle;
    
    //GetExceptionTimeFromDetailReport
    @FindBy(xpath="//*[@id='dtPreRouteSummary']/tbody/tr/td[contains(@id,'colTtlExcpTm_Row1')]/span")
    WebElement GetExceptionTimeFromDetailReport;
    
    //BacktoPreRouteSummaryReport
    @FindBy(xpath="//*[@id='lblCrumb1']")
    WebElement BacktoPreRouteSummaryReport;
    
    //GetRouteManagerName
    @FindBy(xpath="//*[@id='divRMFilter']//span")
    WebElement GetRouteManagerName;
    
    //GetDriverName
    @FindBy(xpath="//*[@id='divDriverFilter']//span")
    WebElement GetDriverName; 

	public Map<String, List<String>> getActualSummaryTableData() {
		System.out.println("inside Actual Summary Data");
		Map<String, List<String>> summaryTableData = new HashMap<>();
		try {
			summaryTableData = readTable(sequenceComplianceSummaryTableColumns, sequenceComplianceSummaryTableRows, "dtSeqComplianceSummary");
			Report.PassTestScreenshot(test, driver, "Summary table data read successfully", "Sequence Compliance Summary Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read summary table data");
		}

		for (Entry<String, List<String>> entry : summaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return summaryTableData;
	}
	
	public Map<String, List<String>> getActualDetailTableData() {
		System.out.println("inside Actual Sequence Compliance Summary Detail Table Data");
		Map<String, List<String>> sequenceComplianceSummaryDetailTableData = new HashMap<>();
		try {
			sequenceComplianceSummaryDetailTableData = readTable(sequenceComplinaceSummaryDetailTableColumns, sequenceComplinaceSummaryDetailTableRows, "dtSeqComplianceDetail");
			Report.PassTestScreenshot(test, driver, "Actual Sequence Compliance Summary Detail table data read successfully",
					"Sequence Compliance Summary Detail Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Sequence Compliance Summary Detail table data");
		}
		for (Entry<String, List<String>> entry : sequenceComplianceSummaryDetailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return sequenceComplianceSummaryDetailTableData;
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
			objTable.put(columns.get(iCount - 1).getText().replace("\n", ""), rowValues);
		}
		return objTable;
}

	public void validateSequenceComplianceSummaryData(Map<String, List<String>> actualSequenceComplianceSummaryTable,
			Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable, String lOB) {
		
		validateSummaryTotalStops(actualSequenceComplianceSummaryTable,actualSequenceComplinaceSummaryDetailTable);
		validateSummaryOutofSeqPercent(actualSequenceComplianceSummaryTable,actualSequenceComplinaceSummaryDetailTable);
		validateSummaryAvgStopsOutofSeq(actualSequenceComplianceSummaryTable,actualSequenceComplinaceSummaryDetailTable);
		validateSummaryInSeqPercent(actualSequenceComplianceSummaryTable,actualSequenceComplinaceSummaryDetailTable);
		validateSummaryAvgStopsInSeq(actualSequenceComplianceSummaryTable,actualSequenceComplinaceSummaryDetailTable);
		validateSummaryAvgStops(actualSequenceComplianceSummaryTable,actualSequenceComplinaceSummaryDetailTable);
		validateSummaryAvgMASStopsWithoutSeqNum(actualSequenceComplianceSummaryTable,actualSequenceComplinaceSummaryDetailTable);
		validateSummaryNumberofSites(actualSequenceComplianceSummaryTable,actualSequenceComplinaceSummaryDetailTable);
	}

	private void validateSummaryTotalStops(Map<String, List<String>> actualSequenceComplianceSummaryTable,
			Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable) {
		int actualSummaryTotalStops = 0;
		int expectedSummaryTotalStops = 0;	
		try {
			for (Entry<String, List<String>> entry : actualSequenceComplianceSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Stops")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualSummaryTotalStops = 0;
						} else {
							actualSummaryTotalStops = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualSummaryTotalStops, "Total Stops in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Total Stops")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedSummaryTotalStops = expectedSummaryTotalStops + Integer.parseInt("0.0");
						} else {
							expectedSummaryTotalStops = expectedSummaryTotalStops
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			// Summary Total Stops = sum of total stops of all sites
			
			if (actualSummaryTotalStops == 0) {
				Report.FailTest(test,
						"Total Stops is not calculated as expected in Sequence Compliance summary report Actual is : -- and Expected is : "
								+ expectedSummaryTotalStops);
			} else {
				if (Util.compareNumber(actualSummaryTotalStops, expectedSummaryTotalStops)) {
					Report.InfoTest(test, "Total Stops is correct in Sequence Compliance summary report Actual is : "
							+ actualSummaryTotalStops + " and Expected is : " + expectedSummaryTotalStops);
					Report.PassTest(test, "Total Stops is as expected in Sequence Compliance Summary report");
				} else {
					Report.FailTest(test, "Total Stops is not as expected in Sequence Compliance summary report Actual is : " + actualSummaryTotalStops
							+ " and Expected is : " + expectedSummaryTotalStops);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Stops is not as expected in Sequence Compliance summary report Actual is : " + actualSummaryTotalStops
					+ " and Expected is : " + expectedSummaryTotalStops);
		}
		
	}

	private void validateSummaryOutofSeqPercent(Map<String, List<String>> actualSequenceComplianceSummaryTable,
			Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable) {
		
		int totalStops = 0;
		int numberOfStopsOutOfSeq=0;
		int actualOutOfSeqPercent=0;
		int expectedOutOfSeqPercent = 0;	
		try {
			for (Entry<String, List<String>> entry : actualSequenceComplianceSummaryTable.entrySet()) {
				if (entry.getKey().equals("Out of Seq %")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualOutOfSeqPercent = 0;
						} else {
							actualOutOfSeqPercent = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualOutOfSeqPercent, "Out of Seq % in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Total Stops")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							totalStops = totalStops +0;
						} else {
							totalStops = totalStops
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}
			
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("# of Stops Out of Seq")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							numberOfStopsOutOfSeq = numberOfStopsOutOfSeq + 0;
						} else {
							numberOfStopsOutOfSeq = numberOfStopsOutOfSeq
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			// Out of Seq % = (# of Stops Out of Sequence) / (Total # of Stops)
			expectedOutOfSeqPercent=(numberOfStopsOutOfSeq*100)/totalStops;
			if (actualOutOfSeqPercent == 0) {
				Report.FailTest(test,
						"Out of Seq % is not calculated as expected in Sequence Compliance summary report Actual is : -- and Expected is : "
								+ expectedOutOfSeqPercent);
			} else {
				
				
				if (Util.compareNumber(actualOutOfSeqPercent, expectedOutOfSeqPercent)) {
					Report.InfoTest(test, "Out of Seq % is correct in Sequence Compliance summary report Actual is : "
							+ actualOutOfSeqPercent + " and Expected is : " + expectedOutOfSeqPercent);
					Report.PassTest(test, "Out of Seq % is as expected in Sequence Compliance Summary report");
				} else {
					Report.FailTest(test, "Out of Seq % is not as expected in Sequence Compliance summary report Actual is : " + actualOutOfSeqPercent
							+ " and Expected is : " + expectedOutOfSeqPercent);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Out of Seq % is not as expected in Sequence Compliance summary report Actual is : " + actualOutOfSeqPercent
					+ " and Expected is : " + expectedOutOfSeqPercent);
		}
		
	}

	private void validateSummaryAvgStopsOutofSeq(Map<String, List<String>> actualSequenceComplianceSummaryTable,
			Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable) {
		

		
		int numberOfStopsOutOfSeq=0;
		int actualAvgStopsOutofSeq=0;
		int expectedAvgStopsOutofSeq = 0;	
		int numberOfSites=0;
		try {
			for (Entry<String, List<String>> entry : actualSequenceComplianceSummaryTable.entrySet()) {
				if (entry.getKey().equals("Avg Stops Out of Seq")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualAvgStopsOutofSeq = 0;
						} else {
							actualAvgStopsOutofSeq = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualAvgStopsOutofSeq, "Avg Stops Out of Seq in Summary Section");
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualSequenceComplianceSummaryTable.entrySet()) {
				if (entry.getKey().equals("# of Sites")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							numberOfSites = 0;
						} else {
							numberOfSites = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}
				}
			}
		
			
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("# of Stops Out of Seq")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							numberOfStopsOutOfSeq = numberOfStopsOutOfSeq + 0;
						} else {
							numberOfStopsOutOfSeq = numberOfStopsOutOfSeq
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			//Avg Stops Out of Seq = AVERAGE(# of Stops Out of Seq) for all sites
			expectedAvgStopsOutofSeq=numberOfStopsOutOfSeq/numberOfSites;
			if (actualAvgStopsOutofSeq == 0) {
				Report.FailTest(test,
						"Avg Stops Out of Seq is not calculated as expected in Sequence Compliance summary report Actual is : -- and Expected is : "
								+ expectedAvgStopsOutofSeq);
			} else {
				
				
				if (Util.compareNumber(actualAvgStopsOutofSeq, expectedAvgStopsOutofSeq)) {
					Report.InfoTest(test, "Avg Stops Out of Seq is correct in Sequence Compliance summary report Actual is : "
							+ actualAvgStopsOutofSeq + " and Expected is : " + expectedAvgStopsOutofSeq);
					Report.PassTest(test, "Avg Stops Out of Seq is as expected in Sequence Compliance Summary report");
				} else {
					Report.FailTest(test, "Avg Stops Out of Seq is not as expected in Sequence Compliance summary report Actual is : " + actualAvgStopsOutofSeq
							+ " and Expected is : " + expectedAvgStopsOutofSeq);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Avg Stops Out of Seq is not as expected in Sequence Compliance summary report Actual is : " + actualAvgStopsOutofSeq
					+ " and Expected is : " + expectedAvgStopsOutofSeq);
		}
		
	}

	private void validateSummaryInSeqPercent(Map<String, List<String>> actualSequenceComplianceSummaryTable,
			Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable) {
		int totalStops = 0;
		int numberOfStopsInSeq=0;
		int actualInSeqPercent=0;
		int expectedInSeqPercent = 0;	
		try {
			for (Entry<String, List<String>> entry : actualSequenceComplianceSummaryTable.entrySet()) {
				if (entry.getKey().equals("In Seq %")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualInSeqPercent = 0;
						} else {
							actualInSeqPercent = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualInSeqPercent, "In Seq % in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Total Stops")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							totalStops = totalStops +0;
						} else {
							totalStops = totalStops
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}
			
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("# of Stops In Seq")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							numberOfStopsInSeq = numberOfStopsInSeq + 0;
						} else {
							numberOfStopsInSeq = numberOfStopsInSeq
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			// In Seq % = (# of Stops In Sequence) / (Total # of Stops)
			expectedInSeqPercent=(numberOfStopsInSeq*100)/totalStops;
			if (actualInSeqPercent == 0) {
				Report.FailTest(test,
						"In Seq % is not calculated as expected in Sequence Compliance summary report Actual is : -- and Expected is : "
								+ expectedInSeqPercent);
			} else {
				
				
				if (Util.compareNumber(actualInSeqPercent, expectedInSeqPercent)) {
					Report.InfoTest(test, "In Seq % is correct in Sequence Compliance summary report Actual is : "
							+ actualInSeqPercent + " and Expected is : " + expectedInSeqPercent);
					Report.PassTest(test, "In Seq % is as expected in Sequence Compliance Summary report");
				} else {
					Report.FailTest(test, "In Seq % is not as expected in Sequence Compliance summary report Actual is : " + actualInSeqPercent
							+ " and Expected is : " + expectedInSeqPercent);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "In Seq % is not as expected in Sequence Compliance summary report Actual is : " + actualInSeqPercent
					+ " and Expected is : " + expectedInSeqPercent);
		}
		
	}

	private void validateSummaryAvgStopsInSeq(Map<String, List<String>> actualSequenceComplianceSummaryTable,
			Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable) {
		
		
		int numberOfStopsInSeq=0;
		int actualAvgStopsInSeq=0;
		int expectedAvgStopsInSeq = 0;	
		int numberOfSites=0;
		try {
			for (Entry<String, List<String>> entry : actualSequenceComplianceSummaryTable.entrySet()) {
				if (entry.getKey().equals("Avg Stops In Seq")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualAvgStopsInSeq = 0;
						} else {
							actualAvgStopsInSeq = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualAvgStopsInSeq, "Avg Stops In Seq in Summary Section");
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualSequenceComplianceSummaryTable.entrySet()) {
				if (entry.getKey().equals("# of Sites")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							numberOfSites = 0;
						} else {
							numberOfSites = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}
				}
			}
		
			
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("# of Stops In Seq")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							numberOfStopsInSeq = numberOfStopsInSeq + 0;
						} else {
							numberOfStopsInSeq = numberOfStopsInSeq
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			//Avg Stops In Seq = AVERAGE(# of Stops In Seq) for all sites
			expectedAvgStopsInSeq=numberOfStopsInSeq/numberOfSites;
			if (actualAvgStopsInSeq == 0) {
				Report.FailTest(test,
						"Avg Stops In Seq is not calculated as expected in Sequence Compliance summary report Actual is : -- and Expected is : "
								+ expectedAvgStopsInSeq);
			} else {
				
				
				if (Util.compareNumber(actualAvgStopsInSeq, expectedAvgStopsInSeq)) {
					Report.InfoTest(test, "Avg Stops In Seq is correct in Sequence Compliance summary report Actual is : "
							+ actualAvgStopsInSeq + " and Expected is : " + expectedAvgStopsInSeq);
					Report.PassTest(test, "Avg Stops In Seq is as expected in Sequence Compliance Summary report");
				} else {
					Report.FailTest(test, "Avg Stops In Seq is not as expected in Sequence Compliance summary report Actual is : " + actualAvgStopsInSeq
							+ " and Expected is : " + expectedAvgStopsInSeq);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Avg Stops In Seq is not as expected in Sequence Compliance summary report Actual is : " + actualAvgStopsInSeq
					+ " and Expected is : " + expectedAvgStopsInSeq);
		}
		
	}

	private void validateSummaryAvgStops(Map<String, List<String>> actualSequenceComplianceSummaryTable,
			Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable) {
		
		int totalStops=0;
		int actualAvgStops=0;
		int expectedAvgStops = 0;	
		int numberOfSites=0;
		try {
			for (Entry<String, List<String>> entry : actualSequenceComplianceSummaryTable.entrySet()) {
				if (entry.getKey().equals("Avg Stops")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualAvgStops = 0;
						} else {
							actualAvgStops = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualAvgStops, "Avg Stops in Summary Section");
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualSequenceComplianceSummaryTable.entrySet()) {
				if (entry.getKey().equals("# of Sites")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							numberOfSites = 0;
						} else {
							numberOfSites = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}
				}
			}
		
			
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Total Stops")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							totalStops = totalStops + 0;
						} else {
							totalStops = totalStops
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			//Avg Stops = AVERAGE(# of Stops) for all sites
			expectedAvgStops=totalStops/numberOfSites;
			if (actualAvgStops == 0) {
				Report.FailTest(test,
						"Avg Stops is not calculated as expected in Sequence Compliance summary report Actual is : -- and Expected is : "
								+ expectedAvgStops);
			} else {
				
				
				if (Util.compareNumber(actualAvgStops, expectedAvgStops)) {
					Report.InfoTest(test, "Avg Stops is correct in Sequence Compliance summary report Actual is : "
							+ actualAvgStops + " and Expected is : " + expectedAvgStops);
					Report.PassTest(test, "Avg Stops In Seq is as expected in Sequence Compliance Summary report");
				} else {
					Report.FailTest(test, "Avg Stops is not as expected in Sequence Compliance summary report Actual is : " + actualAvgStops
							+ " and Expected is : " + expectedAvgStops);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Avg Stops is not as expected in Sequence Compliance summary report Actual is : " + actualAvgStops
					+ " and Expected is : " + expectedAvgStops);
		}
		
	}

	private void validateSummaryAvgMASStopsWithoutSeqNum(Map<String, List<String>> actualSequenceComplianceSummaryTable,
			Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable) {
		
		int totalMASStopsWithoutSeqNum=0;
		int actualAvgMASStopsWithoutSeqNum=0;
		int expectedAvgMASStopsWithoutSeqNum = 0;	
		int numberOfSites=0;
		try {
			for (Entry<String, List<String>> entry : actualSequenceComplianceSummaryTable.entrySet()) {
				if (entry.getKey().equals("Avg MAS Stops w/o Seq #")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualAvgMASStopsWithoutSeqNum = 0;
						} else {
							actualAvgMASStopsWithoutSeqNum = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualAvgMASStopsWithoutSeqNum, "Avg MAS Stops w/o Seq # in Summary Section");
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualSequenceComplianceSummaryTable.entrySet()) {
				if (entry.getKey().equals("# of Sites")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							numberOfSites = 0;
						} else {
							numberOfSites = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}
				}
			}
		
			
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Total MAS Stops w/o Seq # ")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--") || entry.getValue().get(i).equals("UNK")) {
							totalMASStopsWithoutSeqNum = totalMASStopsWithoutSeqNum + 0;
						} else {
							totalMASStopsWithoutSeqNum = totalMASStopsWithoutSeqNum
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			//Avg MAS Stops w/o Seq # = SUM(Total MAS Stops w/o Seq #) / # of Sites
			expectedAvgMASStopsWithoutSeqNum=totalMASStopsWithoutSeqNum/numberOfSites;
			if (actualAvgMASStopsWithoutSeqNum == 0) {
				Report.FailTest(test,
						"Avg MAS Stops w/o Seq # is not calculated as expected in Sequence Compliance summary report Actual is : -- and Expected is : "
								+ expectedAvgMASStopsWithoutSeqNum);
			} else {
				
				
				if (Util.compareNumber(actualAvgMASStopsWithoutSeqNum, expectedAvgMASStopsWithoutSeqNum)) {
					Report.InfoTest(test, "Avg MAS Stops w/o Seq # is correct in Sequence Compliance summary report Actual is : "
							+ actualAvgMASStopsWithoutSeqNum + " and Expected is : " + expectedAvgMASStopsWithoutSeqNum);
					Report.PassTest(test, "Avg MAS Stops w/o Seq # In Seq is as expected in Sequence Compliance Summary report");
				} else {
					Report.FailTest(test, "Avg MAS Stops w/o Seq # is not as expected in Sequence Compliance summary report Actual is : " + actualAvgMASStopsWithoutSeqNum
							+ " and Expected is : " + expectedAvgMASStopsWithoutSeqNum);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Avg MAS Stops w/o Seq # is not as expected in Sequence Compliance summary report Actual is : " + actualAvgMASStopsWithoutSeqNum
					+ " and Expected is : " + expectedAvgMASStopsWithoutSeqNum);
		}
		
	}

	private void validateSummaryNumberofSites(Map<String, List<String>> actualSequenceComplianceSummaryTable,
			Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable) {
		
		// # of sites = sites displayed in the detail section
				int actualNumberOfSites = 0;
				int expectedNumberOfSites = 0;
				
				try {
					for (Entry<String, List<String>> entry : actualSequenceComplianceSummaryTable.entrySet()) {
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
					for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
						if (entry.getKey().equals("Site")) {
							
							expectedNumberOfSites=entry.getValue().size();
						}
					}	
						if (Util.compareNumber(actualNumberOfSites, expectedNumberOfSites)) {
							Report.InfoTest(test, "# of Sites is correct in Sequence Compliance summary report Actual is : "
									+ actualNumberOfSites + " and Expected is : " + expectedNumberOfSites);
							Report.PassTest(test, "# of Sites is as expected in Sequence Compliance Summary report");
						} else {
							Report.FailTest(test, "# of Sites is not as expected in Sequence Compliance summary report Actual is : " + actualNumberOfSites
									+ " and Expected is : " + expectedNumberOfSites);
						}
			
				} catch (Exception e) {
					Report.FailTest(test, "# of Sites is not as expected in Sequence Compliance summary report Actual is : " + actualNumberOfSites
							+ " and Expected is : " + expectedNumberOfSites);
				}
		
	}

	public void validateSequenceComplianceSummaryDetailData(
			Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable,
			Map<String, List<String>> actualSequenceComplinaceDetailSummaryTable, String siteID, String lOB) {
		
		//validateDetailTier(actualSequenceComplinaceSummaryDetailTable,siteID);
		validateDetailArea(actualSequenceComplinaceSummaryDetailTable,siteID);
		validateDetailBU(actualSequenceComplinaceSummaryDetailTable,siteID);
		validateDetailSite(actualSequenceComplinaceSummaryDetailTable,siteID);	
		validateDetailOutofSeqPercent(actualSequenceComplinaceSummaryDetailTable,actualSequenceComplinaceDetailSummaryTable);	
		validateDetailNumberofStopsOutofSeq(actualSequenceComplinaceSummaryDetailTable,actualSequenceComplinaceDetailSummaryTable);
		validateDetailInSeqPercent(actualSequenceComplinaceSummaryDetailTable,actualSequenceComplinaceDetailSummaryTable);
		validateDetailNumberofStopsInSeq(actualSequenceComplinaceSummaryDetailTable,actualSequenceComplinaceDetailSummaryTable);
		validateDetailTotalStops(actualSequenceComplinaceSummaryDetailTable,actualSequenceComplinaceDetailSummaryTable);
		validateDetailTotalMASStopsWithoutSeqNumber(actualSequenceComplinaceSummaryDetailTable,actualSequenceComplinaceDetailSummaryTable);
		
	}

	private void validateDetailArea(Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable,
			String site) {
		String actualAreaName=null;
		List<String> expectedAreaName = null;
		String siteID=site.substring(0, 6);
		try {
			
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
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
					Report.InfoTest(test, "Area is correct in Sequence Compliance Summary report Detail Section. Actual is : "
							+ actualAreaName + " and Expected is : " + expectedAreaName.get(0));
					Report.PassTest(test, "Area is as expected in Sequence Compliance Summary report Detail Section");
				} else {
					Report.FailTest(test, "Area is not as expected in Sequence Compliance Summary report Detail Section. Actual is : " + actualAreaName
							+ " and Expected is : " + expectedAreaName.get(0));
				}
			
		} catch (Exception e) {
			Report.FailTest(test, "Area is not as expected in Sequence Compliance Summary report Detail Section. Actual is : " + actualAreaName
					+ " and Expected is : " + expectedAreaName.get(0));
		}
		
	}

	private void validateDetailBU(Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable, String site) {
		
		String actualBUName=null;
		List<String> expectedBUName = null;
		String siteID=site.substring(0, 6);
		try {
			
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
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
					Report.InfoTest(test, "BU is correct in Sequence Compliance Summary report Detail Section. Actual is : "
							+ actualBUName + " and Expected is : " + expectedBUName.get(0));
					Report.PassTest(test, "BU is as expected in Sequence Compliance Summary report Detail Section");
				} else {
					Report.FailTest(test, "BU is not as expected in Sequence Compliance Summary report Detail Section. Actual is : " + actualBUName
							+ " and Expected is : " + expectedBUName.get(0));
				}
			
		} catch (Exception e) {
			Report.FailTest(test, "BU is not as expected in Sequence Compliance Summary report Detail Section. Actual is : " + actualBUName
					+ " and Expected is : " + expectedBUName.get(0));
		}
		
	}

	private void validateDetailSite(Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable,
			String site) {
		String actualSiteName=null;
		String expectedSiteName = site;
			
		try {
			
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Site")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualSiteName = "--";
						} else {
							actualSiteName = entry.getValue().get(i).toString();
						}
					}
				}
			}

			
				if (actualSiteName.equals(expectedSiteName)) {
					Report.InfoTest(test, "Site is correct in Sequence Compliance Summary report Detail Section. Actual is : "
							+ actualSiteName + " and Expected is : " + expectedSiteName);
					Report.PassTest(test, "Site is as expected in Sequence Compliance Summary report Detail Section");
				} else {
					Report.FailTest(test, "Site is not as expected in Sequence Compliance Summary report Detail Section. Actual is : " + actualSiteName
							+ " and Expected is : " + expectedSiteName);
				}
			
		} catch (Exception e) {
			Report.FailTest(test, "Site is not as expected in Sequence Compliance Summary report Detail Section. Actual is : " + actualSiteName
					+ " and Expected is : " + expectedSiteName);
		}
		
	}

	private void validateDetailOutofSeqPercent(Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable,
			Map<String, List<String>> actualSequenceComplinaceDetailSummaryTable) {
		
		int actualOutofSeqPercent = 0;
		int expectedOutofSeqPercent = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Out of Seq %")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualOutofSeqPercent = 0;
						} else {
							actualOutofSeqPercent = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualOutofSeqPercent, "Out of Seq % in Detail Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualSequenceComplinaceDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("Out of Seq %")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedOutofSeqPercent = 0;
						} else {
							expectedOutofSeqPercent = Integer.parseInt(entry.getValue().get(i));
						}
					

					}
				}
			}

		
			

			// Out of Seq % = Out of Seq % for corresponding site in detail report;
			

			if (actualOutofSeqPercent==0) {
				Report.FailTest(test,
						"Out of Seq % is not calculated as expected in Sequence Compliance Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedOutofSeqPercent);
			} else {

				if (Util.compareNumber(actualOutofSeqPercent,expectedOutofSeqPercent)) {
					Report.InfoTest(test, "Out of Seq % is correct in Sequence Compliance Summary report Detail Section. Actual is : "
							+ actualOutofSeqPercent + " and Expected is : " + expectedOutofSeqPercent);
					Report.PassTest(test, "Out of Seq % is as expected in Sequence Compliance Summary report");
				} else {
					Report.FailTest(test, "Out of Seq % is not as expected in Sequence Compliance Summary report Detail Section. Actual is : "
							+ actualOutofSeqPercent + " and Expected is : " + expectedOutofSeqPercent);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Out of Seq % is not as expected in Sequence Compliance Summary report Detail Section. Actual is : "
					+ actualOutofSeqPercent + " and Expected is : " + expectedOutofSeqPercent);
		}
		
	}

	private void validateDetailNumberofStopsOutofSeq(
			Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable,
			Map<String, List<String>> actualSequenceComplinaceDetailSummaryTable) {
		
		int actualNumberofStopsOutofSeq = 0;
		int expectedNumberofStopsOutofSeq = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("# of Stops Out of Seq")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualNumberofStopsOutofSeq = 0;
						} else {
							actualNumberofStopsOutofSeq = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualNumberofStopsOutofSeq, "# of Stops Out of Seq in Detail Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualSequenceComplinaceDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Stops Out of Seq")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedNumberofStopsOutofSeq = 0;
						} else {
							expectedNumberofStopsOutofSeq = Integer.parseInt(entry.getValue().get(i));
						}
					

					}
				}
			}

		
			

			// # of Stops Out of Seq = # of Stops Out of Seq for corresponding site in detail report;
			

			if (actualNumberofStopsOutofSeq==0) {
				Report.FailTest(test,
						"# of Stops Out of Seq is not calculated as expected in Sequence Compliance Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedNumberofStopsOutofSeq);
			} else {

				if (Util.compareNumber(actualNumberofStopsOutofSeq,expectedNumberofStopsOutofSeq)) {
					Report.InfoTest(test, "# of Stops Out of Seq is correct in Sequence Compliance Summary report Detail Section. Actual is : "
							+ actualNumberofStopsOutofSeq + " and Expected is : " + expectedNumberofStopsOutofSeq);
					Report.PassTest(test, "# of Stops Out of Seq is as expected in Sequence Compliance Summary report");
				} else {
					Report.FailTest(test, "# of Stops Out of Seq is not as expected in Sequence Compliance Summary report Detail Section. Actual is : "
							+ actualNumberofStopsOutofSeq + " and Expected is : " + expectedNumberofStopsOutofSeq);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "# of Stops Out of Seq is not as expected in Sequence Compliance Summary report Detail Section. Actual is : "
					+ actualNumberofStopsOutofSeq + " and Expected is : " + expectedNumberofStopsOutofSeq);
		}
		
	}

	private void validateDetailInSeqPercent(Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable,
			Map<String, List<String>> actualSequenceComplinaceDetailSummaryTable) {
		
		int actualInSeqPercent = 0;
		int expectedInSeqPercent = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("In Seq %")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualInSeqPercent = 0;
						} else {
							actualInSeqPercent = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualInSeqPercent, "In Seq % in Detail Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualSequenceComplinaceDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("In Seq %")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedInSeqPercent = 0;
						} else {
							expectedInSeqPercent = Integer.parseInt(entry.getValue().get(i));
						}
					

					}
				}
			}

		
			

			// In Seq % = In Seq % for corresponding site in detail report;
			

			if (actualInSeqPercent==0) {
				Report.FailTest(test,
						"In Seq % is not calculated as expected in Sequence Compliance Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedInSeqPercent);
			} else {

				if (Util.compareNumber(actualInSeqPercent,expectedInSeqPercent)) {
					Report.InfoTest(test, "In Seq % is correct in Sequence Compliance Summary report Detail Section. Actual is : "
							+ actualInSeqPercent + " and Expected is : " + expectedInSeqPercent);
					Report.PassTest(test, "In Seq % is as expected in Sequence Compliance Summary report");
				} else {
					Report.FailTest(test, "In Seq % is not as expected in Sequence Compliance Summary report Detail Section. Actual is : "
							+ actualInSeqPercent + " and Expected is : " + expectedInSeqPercent);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "In Seq % is not as expected in Sequence Compliance Summary report Detail Section. Actual is : "
					+ actualInSeqPercent + " and Expected is : " + expectedInSeqPercent);
		}
		
	}

	private void validateDetailNumberofStopsInSeq(Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable,
			Map<String, List<String>> actualSequenceComplinaceDetailSummaryTable) {
		
		int actualNumberofStopsInSeq = 0;
		int expectedNumberofStopsInSeq = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("# of Stops In Seq")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualNumberofStopsInSeq = 0;
						} else {
							actualNumberofStopsInSeq = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualNumberofStopsInSeq, "# of Stops In Seq in Detail Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualSequenceComplinaceDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Stops In Seq")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedNumberofStopsInSeq = 0;
						} else {
							expectedNumberofStopsInSeq = Integer.parseInt(entry.getValue().get(i));
						}
					

					}
				}
			}

		
			

			// # of Stops In Seq = # of Stops In Seq for corresponding site in detail report;
			

			if (actualNumberofStopsInSeq==0) {
				Report.FailTest(test,
						"# of Stops In Seq is not calculated as expected in Sequence Compliance Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedNumberofStopsInSeq);
			} else {

				if (Util.compareNumber(actualNumberofStopsInSeq,expectedNumberofStopsInSeq)) {
					Report.InfoTest(test, "# of Stops In Seq is correct in Sequence Compliance Summary report Detail Section. Actual is : "
							+ actualNumberofStopsInSeq + " and Expected is : " + expectedNumberofStopsInSeq);
					Report.PassTest(test, "# of Stops In Seq is as expected in Sequence Compliance Summary report");
				} else {
					Report.FailTest(test, "# of Stops In Seq is not as expected in Sequence Compliance Summary report Detail Section. Actual is : "
							+ actualNumberofStopsInSeq + " and Expected is : " + expectedNumberofStopsInSeq);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "# of Stops In Seq is not as expected in Sequence Compliance Summary report Detail Section. Actual is : "
					+ actualNumberofStopsInSeq + " and Expected is : " + expectedNumberofStopsInSeq);
		}
		
	}

	private void validateDetailTotalStops(Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable,
			Map<String, List<String>> actualSequenceComplinaceDetailSummaryTable) {
		
		int actualTotalStops = 0;
		int expectedTotalStops = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Total Stops")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualTotalStops = 0;
						} else {
							actualTotalStops = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualTotalStops, "Total Stops in Detail Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualSequenceComplinaceDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Stops")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedTotalStops = 0;
						} else {
							expectedTotalStops = Integer.parseInt(entry.getValue().get(i));
						}
					

					}
				}
			}

		
			

			// Total Stops = Total Stops for corresponding site in detail report;
			

			if (actualTotalStops==0) {
				Report.FailTest(test,
						"Total Stops is not calculated as expected in Sequence Compliance Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedTotalStops);
			} else {

				if (Util.compareNumber(actualTotalStops,expectedTotalStops)) {
					Report.InfoTest(test, "Total Stops is correct in Sequence Compliance Summary report Detail Section. Actual is : "
							+ actualTotalStops + " and Expected is : " + expectedTotalStops);
					Report.PassTest(test, "Total Stops is as expected in Sequence Compliance Summary report");
				} else {
					Report.FailTest(test, "Total Stops is not as expected in Sequence Compliance Summary report Detail Section. Actual is : "
							+ actualTotalStops + " and Expected is : " + expectedTotalStops);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Stops is not as expected in Sequence Compliance Summary report Detail Section. Actual is : "
					+ actualTotalStops + " and Expected is : " + expectedTotalStops);
		}
		
	}

	private void validateDetailTotalMASStopsWithoutSeqNumber(
			Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable,
			Map<String, List<String>> actualSequenceComplinaceDetailSummaryTable) {
		
		int actualTotalMASStopsWithoutSeqNumber = 0;  
		int expectedTotalMASStopsWithoutSeqNumber = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Total MAS Stops w/o Seq # ")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualTotalMASStopsWithoutSeqNumber = 0;
						} else {
							actualTotalMASStopsWithoutSeqNumber = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualTotalMASStopsWithoutSeqNumber, "Total MAS Stops w/o Seq # in Detail Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualSequenceComplinaceDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total MAS Stops w/o Seq #")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedTotalMASStopsWithoutSeqNumber = 0;
						} else {
							expectedTotalMASStopsWithoutSeqNumber = Integer.parseInt(entry.getValue().get(i));
						}
					

					}
				}
			}

		
			

			// Total MAS Stops w/o Seq # = Total MAS Stops w/o Seq # for corresponding site in detail report;
			

			if (actualTotalMASStopsWithoutSeqNumber==0) {
				Report.FailTest(test,
						"Total MAS Stops w/o Seq # is not calculated as expected in Sequence Compliance Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedTotalMASStopsWithoutSeqNumber);
			} else {

				if (Util.compareNumber(actualTotalMASStopsWithoutSeqNumber,expectedTotalMASStopsWithoutSeqNumber)) {
					Report.InfoTest(test, "Total MAS Stops w/o Seq # is correct in Sequence Compliance Summary report Detail Section. Actual is : "
							+ actualTotalMASStopsWithoutSeqNumber + " and Expected is : " + expectedTotalMASStopsWithoutSeqNumber);
					Report.PassTest(test, "Total MAS Stops w/o Seq # is as expected in Sequence Compliance Summary report");
				} else {
					Report.FailTest(test, "Total MAS Stops w/o Seq # is not as expected in Sequence Compliance Summary report Detail Section. Actual is : "
							+ actualTotalMASStopsWithoutSeqNumber + " and Expected is : " + expectedTotalMASStopsWithoutSeqNumber);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total MAS Stops w/o Seq # is not as expected in Sequence Compliance Summary report Detail Section. Actual is : "
					+ actualTotalMASStopsWithoutSeqNumber + " and Expected is : " + expectedTotalMASStopsWithoutSeqNumber);
		}
		
	}

	
	public void ValidatePopUp(String Site, String FromDate, String ToDate,String ReportName,String downloadFilePath) {
    	Popupclick("dtSeqComplianceDetail");
    	ValidatefixedText(Site, FromDate, ToDate);  	
    	ValidateColumnNameinPopUp();
    	ValidateExportExcelButton(ReportName, downloadFilePath);
    	ValidatePopUpDatainDetailReport("Sequence Compliance Detail","Sequence Compliance Summary","dtSeqComplianceSummary");
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
    		Util.validateColumns(actualpopupColumns, GlobalExpectedColumns.sequenceComplianceSummaryPopUpColumns, "Sequence Compliance Summary pop Up columns");
		} catch (Exception e) {
			Report.FailTest(test, "Validate Column Name in PopUp Failed");
		}
    	
    	
    	
    }

    private void ValidateExportExcelButton(String ReportName,String downloadFilePath) {

    	List<String> UIDataList=new ArrayList<>();
    	   	
    	try {
    		
			Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsseqComplianceSumamryPopUp, downloadFilePath,1);
		
			UIDataList=Util.getUIColumnData(ReportName,"PopUp","RteMgr","dtExcpPopUp");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Route Manager", 0, UIDataList);
			
			
			
			UIDataList=Util.getUIColumnData(ReportName,"PopUp","Driver","dtExcpPopUp");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Driver", 0, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"PopUp","PctInSeq","dtExcpPopUp");
			Util.validateExportExcelData(downloadFilePath, ReportName, "In Seq %", 0, UIDataList);
    	
    	
    	} catch (Exception e) {
			Report.FailTestSnapshot(test, driver, "Validate Export Excel Button Failed", "ExportToExcelFailed");
		}
    	
    }
    
   
    
    
   private void ValidatePopUpDatainDetailReport(String expectedDetailReportTitle,String expectedSummaryReportTitle,String SummaryTableName) {
	     String expectedRouteManager=null;
	     String expectedDriver=null;
	     String inSeqpercentage=null;
	     String actualDetailReportTitle=null;
	     String actualRouteManager=null;
	     String actualDriver=null;
	     String actualInSeqPercentage=null;
	     String actualSummaryReportTitlePreRouteSummary=null;
	   
	   try {
		   expectedRouteManager=ExpectedRoutemanagerinPopUp.getText();
		   expectedDriver=ExpectedDriverInPopUp.getText();
		   inSeqpercentage=ExpectedInSeqPercentages.getText();
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
		   
		   
		   actualRouteManager=GetRouteManagerName.getText();
		   actualDriver=GetDriverName.getText();
		   Util.switchToDefaultWindow();
		   Util.selectFrame("opusReportContainer,subReportContainer");
		   actualInSeqPercentage=driver.findElement(By.xpath("//*[@id='"+SummaryTableName+"']/tbody/tr/td[contains(@id,'colPctInSeq_Row1')]/span")).getText();
		   if (expectedRouteManager.contains(actualRouteManager) && expectedDriver.contains(actualDriver) && inSeqpercentage.contains(actualInSeqPercentage)) {
			Report.PassTestScreenshot(test, driver, "Validated Route Manager Name Expected is "+ expectedRouteManager + " and Actaul is "+actualRouteManager + " Validated Driver Name Expected is " + expectedDriver+ " and actual is " + actualDriver + "Validated In Seq % Expected Exception time is " + inSeqpercentage + " and Actual In Seq % time is " +actualInSeqPercentage, "PassedRMDriverAndExceptionTime");
		}
		   else {
			   Report.PassTestScreenshot(test, driver, "Unable to Validate Route Manager Name Expected is "+ expectedRouteManager + " and Actaul is "+actualRouteManager + " Unable to Validate Driver Name Expected is " + expectedDriver+ " and actual is " + actualDriver + "Unable to Validate In Seq % is Expected Exception time is " + inSeqpercentage + " and Actual In Seq % is " +actualInSeqPercentage, "FailedRMDriverAndExceptionTime");
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
