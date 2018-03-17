package ObjectRepository.Logi;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import SupportLibraries.LogClass;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class SequenceComplianceDetailReport {
	
	ExtentTest test;
	WebDriver driver;

	public SequenceComplianceDetailReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//table[@id='dtSeqComplianceSummary']/thead/tr/th")
	List<WebElement> sequenceComplianceSummaryTableColumns;

	@FindBy(xpath = "//table[@id='dtSeqComplianceSummary']/tbody/tr")
	List<WebElement> sequenceComplianceSummaryTableRows;
	
	@FindBy(xpath = "//table[@id='t2']/thead/tr/th")
	List<WebElement> sequenceComplianceDetailTableColumns;

	@FindBy(xpath = "//table[@id='t2']/tbody/tr")
	List<WebElement> sequenceComplianceDetailTableRows;
	
	@FindBy(xpath = "//table[@id='t2e']/thead/tr/th")
	List<WebElement> sequenceComplianceExceptionDetailTableColumns;

	@FindBy(xpath = "//table[@id='t2e']/tbody/tr")
	List<WebElement> sequenceComplianceExceptionDetailTableRows;
	
	
	@FindBy(xpath = "//table[@id='t3']/thead/tr/th")
	List<WebElement> totalStopsDrilldownTableColumns;

	@FindBy(xpath = "//table[@id='t3']/tbody/tr")
	List<WebElement> totalStopsDrilldownTableRows;
	
	@FindBy(id = "tabAll")
	WebElement tabAll;
	
	
	
	
	
	
	public Map<String, List<String>> getActualSequenceComplianceSummaryTableData() throws IOException {
		Map<String, List<String>> sequenceComplianceSummaryTableData = new HashMap<>();
		
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		try {
			sequenceComplianceSummaryTableData = readTable(sequenceComplianceSummaryTableColumns, sequenceComplianceSummaryTableRows, "dtSeqComplianceSummary");
			Report.PassTestScreenshot(test, driver, "Sequence Compliance Summary table data read successfully", "Sequence Compliance Summary Table Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Sequence Compliance summary table data");
		}

		for (Entry<String, List<String>> entry : sequenceComplianceSummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return sequenceComplianceSummaryTableData;
	}
	

	public Map<String, List<String>> getActualSequenceComplianceAllTabDetailTableData() {
		System.out.println("inside Actual Sequence Compliance All tab Detail Table Data");
		Map<String, List<String>> sequenceComplianceDetailTableData = new HashMap<>();
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		try {
			sequenceComplianceDetailTableData = readTable(sequenceComplianceDetailTableColumns, sequenceComplianceDetailTableRows, "t2");
			Report.PassTestScreenshot(test, driver, "Sequence Compliance All Tab Detail table data read successfully",
					"Sequence Compliance All Tab Detail Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Sequence Compliance All Tab Detail table data");
		}
		for (Entry<String, List<String>> entry : sequenceComplianceDetailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return sequenceComplianceDetailTableData;
	}
	
	public Map<String, List<String>> getActualSequenceComplianceExceptionTabDetailTableData() {
		System.out.println("inside Actual Sequence Compliance Exception tab Detail Table Data");
		Map<String, List<String>> sequenceComplianceExceptionDetailTableData = new HashMap<>();
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		try {
			sequenceComplianceExceptionDetailTableData = readTable(sequenceComplianceExceptionDetailTableColumns, sequenceComplianceExceptionDetailTableRows, "t2e");
			Report.PassTestScreenshot(test, driver, "Sequence Compliance Exception Tab Detail table data read successfully",
					"Sequence Compliance Exception Tab Detail Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Sequence Compliance Exception Tab Detail table data");
		}
		for (Entry<String, List<String>> entry : sequenceComplianceExceptionDetailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return sequenceComplianceExceptionDetailTableData;
	}

	public Map<String, Map<String, List<String>>> getActualTotalStopsDrilldownAllTabTableData(String route) {
		Map<String, Map<String, List<String>>> totalStopsDrilldownTableData = new HashMap<>();
		try {
			Thread.sleep(5000);
			String[] routes = route.split(";");

			Map<String, List<String>> totalStopsDrilldownTemp = new HashMap<>();

			for (int i = 0; i < routes.length; i++) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t2']/tbody/tr[@row='" + (i + 1) + "']/td/a/span")).click();
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer,subSeqComplianceDetail_Row" + (i + 1));
					totalStopsDrilldownTemp = readTable(totalStopsDrilldownTableColumns, totalStopsDrilldownTableRows, "t3");
					Report.PassTestScreenshot(test, driver, "Total Stops Drilldown All Tab table data read successfully",
							"Total Stops Drilldown All Tab Data");
					Util.switchToDefaultWindow();
				} catch (Exception e) {
					Report.FailTest(test, "Not able to read Total Stops Drill down All Tab table data");
				}
				totalStopsDrilldownTableData.put(routes[i], totalStopsDrilldownTemp);
			}

			for (Entry<String, Map<String, List<String>>> entry : totalStopsDrilldownTableData.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
				}
			}
			return totalStopsDrilldownTableData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to read Total Stops drilldown All Tab data", "Total Stops Drilldown All Tab");
		}

		return totalStopsDrilldownTableData;
	}
	
	public Map<String, Map<String, List<String>>> getActualTotalStopsDrilldownExceptionTabTableData(String route) {
		Map<String, Map<String, List<String>>> totalStopsDrilldownTableData = new HashMap<>();
		try {
			Thread.sleep(5000);
			String[] routes = route.split(";");

			Map<String, List<String>> totalStopsDrilldownTemp = new HashMap<>();

			for (int i = 0; i < routes.length; i++) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t2e']/tbody/tr[@row='" + (i + 1) + "']/td/a/span")).click();
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer,subSeqComplianceDetailEx_Row" + (i + 1));
					totalStopsDrilldownTemp = readTable(totalStopsDrilldownTableColumns, totalStopsDrilldownTableRows, "t3");
					Report.PassTestScreenshot(test, driver, "Total Stops Drilldown Exception Tab table data read successfully",
							"Total Stops Drilldown Exception Tab Data");
					Util.switchToDefaultWindow();
				} catch (Exception e) {
					Report.FailTest(test, "Not able to read Total Stops Drill down Exception Tab table data");
				}
				totalStopsDrilldownTableData.put(routes[i], totalStopsDrilldownTemp);
			}

			for (Entry<String, Map<String, List<String>>> entry : totalStopsDrilldownTableData.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
				}
			}
			return totalStopsDrilldownTableData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to read Total Stops drilldown Exception Tab data ", "Total Stops Drilldown Exception Tab");
		}

		return totalStopsDrilldownTableData;
	}
	
	public Map<String, List<String>> readTable(List<WebElement> columns, List<WebElement> rows, String tableName) {

	
		 if (tableName.equals("t2") || tableName.equals("t2e")) {
				Map<String, List<String>> objTable = new HashMap<>();
			
				for (int iCount = 1; iCount <= columns.size(); iCount++) {
					
					List<String> rowValues = new ArrayList<>();
					for (int row = 1; row < rows.size(); row = row + 2) {
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
		 else
		 {
			Map<String, List<String>> objTable = new HashMap<>();
			// System.out.println("Number of cloumns : "+columns.size());
			// System.out.println("Number of rows : "+rows.size());
			for (int iCount = 1; iCount <= columns.size(); iCount++) {
				// List<WebElement> objCol =
				// rows.get(iCount).findElements(By.cssSelector("td.tableTxt"));
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

	}


	public void clickOnAllTab() {
		
		try
		{
			Util.switchToDefaultTab();
			Util.selectFrame("opusReportContainer,subReportContainer");
			tabAll.click();
			Thread.sleep(3000);
			Report.PassTest(test, "User successfully switched from exception tab to All Tab");
			
		}
		catch(Exception e)
		{
			Report.FailTest(test, "Not able to switch to All Tab from Exception Tab");
		}
		
	}


	public void validateSummaryData(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualAllTabDetailTable) {
		
		
		validateSummaryTotalStops(actualSummaryTable,actualAllTabDetailTable);
		validateSummaryOutofSeqPercent(actualSummaryTable,actualAllTabDetailTable);
		validateSummaryTotalStopsOutofSeq(actualSummaryTable,actualAllTabDetailTable);
		validateSummaryInSeqPercent(actualSummaryTable,actualAllTabDetailTable);
		validateSummaryTotalStopsInSeq(actualSummaryTable,actualAllTabDetailTable);
		validateSummaryTotalMASStopsWithoutSeqNumber(actualSummaryTable,actualAllTabDetailTable);
		
		
	}


	private void validateSummaryTotalStops(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualAllTabDetailTable) {
		
		int actualTotalStops = 0;
		int expectedTotalStops = 0;	
		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Stops")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualTotalStops = 0;
						} else {
							actualTotalStops = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualTotalStops, "Total Stops in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Total Stops")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedTotalStops = expectedTotalStops + 0;
						} else {
							expectedTotalStops = expectedTotalStops
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			// Summary Total Stops = sum of Total Stops of all routes in the selected filter criteria
			
			if (actualTotalStops == 0) {
				Report.FailTest(test,
						"Total Stops is not calculated as expected in Summary section of Sequence Compliance Detail Report. Actual is : -- and Expected is : "
								+ expectedTotalStops);
			} else {
				if (Util.compareNumber(actualTotalStops, expectedTotalStops)) {
					Report.InfoTest(test, "Total Stops is correct in Summary section of Sequence Compliance Detail Report. Actual is : "
							+ actualTotalStops + " and Expected is : " + expectedTotalStops);
					Report.PassTest(test, "Total Stops is as expected in Summary section of Sequence Compliance Detail Report");
				} else {
					Report.FailTest(test, "Total Stops is not as expected in Summary section of Sequence Compliance Detail Report. Actual is : " + actualTotalStops
							+ " and Expected is : " + expectedTotalStops);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Stops is not as expected in Summary section of Sequence Compliance Detail Report. Actual is : " + actualTotalStops
					+ " and Expected is : " + expectedTotalStops);
		}
		
	}


	private void validateSummaryOutofSeqPercent(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualAllTabDetailTable) {
		
		// out of Seq % = (# of Stops Out of Sequence) / (Total # of Stops)
		int totalStops = 0;
		int numberOfStopsOutOfSeq = 0;
		int actualOutOfSeqPercent=0;
		int expectedOutOfSeqPercent=0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Out of Seq %")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualOutOfSeqPercent = 0;
						} else {
							actualOutOfSeqPercent = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualOutOfSeqPercent, "Out Of Seq % in Summary Section");
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Stops")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							totalStops = 0;
						} else {
							totalStops = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Stops Out of Seq")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							numberOfStopsOutOfSeq = 0;
						} else {
							numberOfStopsOutOfSeq = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}
				}
			}
			
			

			expectedOutOfSeqPercent= (numberOfStopsOutOfSeq*100)/totalStops;			
			if (actualOutOfSeqPercent == 0) {
				Report.FailTest(test,
						"Out Of Seq % is not calculated as expected in Summary section of Sequence Compliance Detail Report. Actual is : -- and Expected is : "
								+ expectedOutOfSeqPercent);
			} else {
				
				if (Util.compareNumber(actualOutOfSeqPercent, expectedOutOfSeqPercent)) {
					Report.InfoTest(test, "Out Of Seq % is correct in Summary section of Sequence Compliance Detail Report. Actual is : "
							+ actualOutOfSeqPercent + " and Expected is : " + expectedOutOfSeqPercent);
					Report.PassTest(test, "Out Of Seq % is as expected in Summary section of Sequence Compliance Detail Report");
				} else {
					Report.FailTest(test, "Out Of Seq % is not as expected in Summary section of Sequence Compliance Detail Report. Actual is : " + actualOutOfSeqPercent
							+ " and Expected is : " + expectedOutOfSeqPercent);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Out Of Seq % is not as expected in Summary section of Sequence Compliance Detail Report. Actual is : " + actualOutOfSeqPercent
					+ " and Expected is : " + expectedOutOfSeqPercent);
		}
		
	}


	private void validateSummaryTotalStopsOutofSeq(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualAllTabDetailTable) {
		
		int actualTotalStopsOutOfSeq = 0;
		int expectedTotalStopsOutOfSeq = 0;	
		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Stops Out of Seq")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualTotalStopsOutOfSeq = 0;
						} else {
							actualTotalStopsOutOfSeq = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualTotalStopsOutOfSeq, "Total Stops Out of Seq in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("# of Stops Out of Seq")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedTotalStopsOutOfSeq = expectedTotalStopsOutOfSeq + 0;
						} else {
							expectedTotalStopsOutOfSeq = expectedTotalStopsOutOfSeq
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			// Summary Total Stops Out Of Seq = sum of Total Stops Out Of Seq of all routes in the selected filter criteria
			
			if (actualTotalStopsOutOfSeq == 0) {
				Report.FailTest(test,
						"Total Stops Out Of Seq is not calculated as expected in Summary section of Sequence Compliance Detail Report. Actual is : -- and Expected is : "
								+ expectedTotalStopsOutOfSeq);
			} else {
				if (Util.compareNumber(actualTotalStopsOutOfSeq, expectedTotalStopsOutOfSeq)) {
					Report.InfoTest(test, "Total Stops Out Of Seq is correct in Summary section of Sequence Compliance Detail Report. Actual is : "
							+ actualTotalStopsOutOfSeq + " and Expected is : " + expectedTotalStopsOutOfSeq);
					Report.PassTest(test, "Total Stops Out Of Seq is as expected in Summary section of Sequence Compliance Detail Report");
				} else {
					Report.FailTest(test, "Total Stops Out Of Seq is not as expected in Summary section of Sequence Compliance Detail Report. Actual is : " + actualTotalStopsOutOfSeq
							+ " and Expected is : " + expectedTotalStopsOutOfSeq);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Stops Out Of Seq is not as expected in Summary section of Sequence Compliance Detail Report. Actual is : " + actualTotalStopsOutOfSeq
					+ " and Expected is : " + expectedTotalStopsOutOfSeq);
		}
		
	}


	private void validateSummaryInSeqPercent(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualAllTabDetailTable) {
		// In Seq % = (# of Stops In of Sequence) / (Total # of Stops)
				int totalStops = 0;
				int numberOfStopsInSeq = 0;
				int actualInSeqPercent=0;
				int expectedInSeqPercent=0;
				try {
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
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
					
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("Total Stops")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									totalStops = 0;
								} else {
									totalStops = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
									
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("Total Stops In Seq")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									numberOfStopsInSeq = 0;
								} else {
									numberOfStopsInSeq = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
									
								}
							}
						}
					}
					
					

					expectedInSeqPercent=(numberOfStopsInSeq*100)/totalStops;
					
					if (actualInSeqPercent == 0) {
						Report.FailTest(test,
								"In Seq % is not calculated as expected in Summary section of Sequence Compliance Detail Report. Actual is : -- and Expected is : "
										+ expectedInSeqPercent);
					} else {
						
						if (Util.compareNumber(actualInSeqPercent, expectedInSeqPercent)) {
							Report.InfoTest(test, "In Seq % is correct in Summary section of Sequence Compliance Detail Report. Actual is : "
									+ actualInSeqPercent + " and Expected is : " + expectedInSeqPercent);
							Report.PassTest(test, "In Seq % is as expected in Summary section of Sequence Compliance Detail Report");
						} else {
							Report.FailTest(test, "In Seq % is not as expected in Summary section of Sequence Compliance Detail Report. Actual is : " + actualInSeqPercent
									+ " and Expected is : " + expectedInSeqPercent);
						}
					}
				} catch (Exception e) {
					Report.FailTest(test, "In Seq % is not as expected in Summary section of Sequence Compliance Detail Report. Actual is : " + actualInSeqPercent
							+ " and Expected is : " + expectedInSeqPercent);
				}
		
	}


	private void validateSummaryTotalStopsInSeq(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualAllTabDetailTable) {
		
		int actualTotalStopsInSeq = 0;
		int expectedTotalStopsInSeq = 0;	
		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Stops In Seq")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualTotalStopsInSeq = 0;
						} else {
							actualTotalStopsInSeq = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualTotalStopsInSeq, "Total Stops In Seq in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("# of Stops In Seq")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedTotalStopsInSeq = expectedTotalStopsInSeq + 0;
						} else {
							expectedTotalStopsInSeq = expectedTotalStopsInSeq
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			// Summary Total Stops In Seq = sum of Total Stops In Seq of all routes in the selected filter criteria
			
			if (actualTotalStopsInSeq == 0) {
				Report.FailTest(test,
						"Total Stops In Seq is not calculated as expected in Summary section of Sequence Compliance Detail Report. Actual is : -- and Expected is : "
								+ expectedTotalStopsInSeq);
			} else {
				if (Util.compareNumber(actualTotalStopsInSeq, expectedTotalStopsInSeq)) {
					Report.InfoTest(test, "Total Stops In Seq is correct in Summary section of Sequence Compliance Detail Report. Actual is : "
							+ actualTotalStopsInSeq + " and Expected is : " + expectedTotalStopsInSeq);
					Report.PassTest(test, "Total Stops In Seq is as expected in Summary section of Sequence Compliance Detail Report");
				} else {
					Report.FailTest(test, "Total Stops In Seq is not as expected in Summary section of Sequence Compliance Detail Report. Actual is : " + actualTotalStopsInSeq
							+ " and Expected is : " + expectedTotalStopsInSeq);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Stops In Seq is not as expected in Summary section of Sequence Compliance Detail Report. Actual is : " + actualTotalStopsInSeq
					+ " and Expected is : " + expectedTotalStopsInSeq);
		}
		
	}


	private void validateSummaryTotalMASStopsWithoutSeqNumber(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualAllTabDetailTable) {
		
		int actualTotalMASStopsWithoutSeq = 0;
		int expectedTotalMASStopsWithoutSeq = 0;	
		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total MAS Stops w/o Seq #")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualTotalMASStopsWithoutSeq = 0;
						} else {
							actualTotalMASStopsWithoutSeq = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualTotalMASStopsWithoutSeq, "Total MAS Stops w/o Seq # in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("MAS Stops w/o Seq #")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedTotalMASStopsWithoutSeq = expectedTotalMASStopsWithoutSeq + 0;
						} else {
							expectedTotalMASStopsWithoutSeq = expectedTotalMASStopsWithoutSeq
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			// Summary 	Total MAS Stops w/o Seq # = sum of Total MAS Stops w/o Seq # of all routes in the selected filter criteria
			
			if (actualTotalMASStopsWithoutSeq == 0) {
				Report.FailTest(test,
						"Total MAS Stops w/o Seq # is not calculated as expected in Summary section of Sequence Compliance Detail Report. Actual is : -- and Expected is : "
								+ expectedTotalMASStopsWithoutSeq);
			} else {
				if (Util.compareNumber(actualTotalMASStopsWithoutSeq, expectedTotalMASStopsWithoutSeq)) {
					Report.InfoTest(test, "Total MAS Stops w/o Seq # is correct in Summary section of Sequence Compliance Detail Report. Actual is : "
							+ actualTotalMASStopsWithoutSeq + " and Expected is : " + expectedTotalMASStopsWithoutSeq);
					Report.PassTest(test, "Total MAS Stops w/o Seq #q is as expected in Summary section of Sequence Compliance Detail Report");
				} else {
					Report.FailTest(test, "Total MAS Stops w/o Seq # is not as expected in Summary section of Sequence Compliance Detail Report. Actual is : " + actualTotalMASStopsWithoutSeq
							+ " and Expected is : " + expectedTotalMASStopsWithoutSeq);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total MAS Stops w/o Seq # is not as expected in Summary section of Sequence Compliance Detail Report. Actual is : " + actualTotalMASStopsWithoutSeq
					+ " and Expected is : " + expectedTotalMASStopsWithoutSeq);
		}
		
	}


	public void validateDetailData(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalStopsDrilldownAllTabTable, String siteID, String lOB,
			String route, String Driver, String fromDate, String toDate) {
	
		validateDetailDate(actualAllTabDetailTable,fromDate,toDate);	
		validateDetailRoute(actualAllTabDetailTable,route);
		validateDetailDriver(actualAllTabDetailTable,Driver);
		validateDetailOutofSeqPercent(actualAllTabDetailTable,actualTotalStopsDrilldownAllTabTable);
		validateDetailNumberofStopsOutofSeq(actualAllTabDetailTable,actualTotalStopsDrilldownAllTabTable);
		validateDetailInSeqPercent(actualAllTabDetailTable,actualTotalStopsDrilldownAllTabTable);
		validateDetailNumberofStopsInSeq(actualAllTabDetailTable,actualTotalStopsDrilldownAllTabTable);
		validateDetailTotalStops(actualAllTabDetailTable,actualTotalStopsDrilldownAllTabTable);
		validateDetailMASStopsWithoutSeqNumber(actualAllTabDetailTable,actualTotalStopsDrilldownAllTabTable);
		validateDetailLOB(actualAllTabDetailTable,lOB);
		validateDetailSubLOB(actualAllTabDetailTable);
		validateDetailRouteType(actualAllTabDetailTable);
		//validateDetailRouteManager(actualAllTabDetailTable);
		
	}
	
	private void validateDetailRouteType(Map<String, List<String>> actualDetailTable) {
		
		List<String> actualRouteType = new ArrayList<>();
		List<String> expectedRouteType = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		String expectedRouteTypes = "";
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");

		try {
			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().equals("Route Type")) {
					actualRouteType = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			expectedRouteType = Util.getDataFromDB(
					"select name from ocs_admin.tp_vehicletype where pkey in (select fk_vehicletype from ocs_admin.tp_vehicle where pkey in (select fk_vehicle from ocs_admin.tp_routeorder where FK_ROUTE IN ( select PKEY from OCS_ADMIN.TP_ROUTE where ID IN ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + "))) ");

			for (int i = 0; i < route.size(); i++) {
				if (expectedRouteType.get(0).equals("COMM FRONT LOAD"))
					expectedRouteTypes = "FC";
				
				if (expectedRouteType.get(0).equals("COMMERCIAL FRONT LOAD"))
					expectedRouteTypes = "FC";
				
				if (expectedRouteType.get(0).equals("RES REARLOAD"))
					expectedRouteTypes = "RR";
				
				if (expectedRouteType.get(0).equals("ROLL OFF"))
					expectedRouteTypes = "O";

				if (actualRouteType.contains(expectedRouteTypes)) {
			//	if (actualRouteType.get(i).equals(expectedRouteTypes)) {
					Report.InfoTest(test,
							"Route Type  is correct in Sequence Compliance Detail report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type  : " + expectedRouteTypes);
					Report.PassTest(test, "Route Type is as expected in Sequence Compliance Detail Sub View report");
				} else {
					Report.FailTest(test,
							"Route Type is not as expected in Sequence Compliance Detail report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type : " + expectedRouteTypes);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test,
					"Route Type  is not as expected in Sequence Compliance Detail report Actual is : "
							+ actualRouteType.toString() + " and Expected is : " + expectedRouteType.toString());
		}
	
}

	private void validateDetailSubLOB(Map<String, List<String>> actualDetailTable) {
		List<String> actualSubLOB = new ArrayList<>();
		List<String> expectedSubLOB = new ArrayList<>();
		List<String> route = new ArrayList<>();

		try {
			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().equals("Sub LOB")) {
					actualSubLOB = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			expectedSubLOB = Util.getDataFromDB(
					"select UNIQUEID from OCS_ADMIN.TP_SUBLOB where PKEY IN (select OCS_ADMIN.TP_ROUTE.FK_SUBLOB from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + "))");

			for (int i = 0; i < route.size(); i++) {
				if (actualSubLOB.contains(expectedSubLOB.get(0))) {
				//if (actualSubLOB.get(i).equals(expectedSubLOB.get(i))) {
					Report.InfoTest(test,
							"Sub LOB is correct in Sequence Compliance Detail report for Route : "
									+ route.get(i) + " with Actual SubLOB : " + actualSubLOB.get(0)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
					Report.PassTest(test, "Sub LOB is as expected in Sequence Compliance Detail report");
				} else {
					Report.FailTest(test,
							"Sub LOB is not as expected in Sequence Compliance Detail report for Route : "
									+ route.get(i) + " with Actual Sub LOB : " + actualSubLOB.get(i)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Sub LOB is not as expected in Sequence Compliance Detail report Actual is : "
					+ actualSubLOB.toString() + " and Expected is : " + expectedSubLOB.toString());
		}
	
}

	public void validateDetailLOB(Map<String, List<String>> actualDetailTable, String expectedLOB) {
		String actualLOB = null;
		try {
			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().equals("LOB")) {

					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualLOB = null;
						} else {
							actualLOB = entry.getValue().get(i).toString();
						}
					}
				}
			}
			if (actualLOB.equals("RES"))
				actualLOB = "Residential";
			if (actualLOB.equals("COM"))
				actualLOB = "Commercial";
			if (actualLOB.equals("ROL"))
				actualLOB = "Roll Off";

			if (actualLOB.equals(expectedLOB)) {

				Report.InfoTest(test, "LOB is correct in Sequence Compliance Detail report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
				Report.PassTest(test, "LOB is as expected in Sequence Compliance Detail report");
			} else {
				Report.FailTest(test, "LOB is not as expected in Sequence Compliance Detail report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
			}
		} catch (Exception e) {
			Report.FailTest(test, "LOB is not as expected in Sequence Compliance Detail report Actual List is : " + actualLOB + " and Expected is : " + expectedLOB);
		}	
}
	
	
private void validateDetailMASStopsWithoutSeqNumber(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalStopsDrilldownAllTabTable) {
	// MAS Stops w/o Seq # = Sum of MAS Seq # having value UNK in the total stops drilldown

	int expectedMASStopsWithoutSeqNumber=0;			
	List<Integer> actualMASStopsWithoutSeqNumber=new ArrayList<>();
	List<String> route = new ArrayList<>();
	try {
		for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
			if (entry.getKey().equals("Route")) {
				route = entry.getValue();
			}
		}
		
		for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
			if (entry.getKey().equals("MAS Stops w/o Seq #")) {
				for (int i = 0; i < entry.getValue().size(); i++) {						
					actualMASStopsWithoutSeqNumber.add(Integer.parseInt(entry.getValue().get(i)));
					Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)), "MAS Stops w/o Seq # in Sequence Compliance Detail Report");
				}
			}
		}
		

		for (int j=0;j<route.size();j++)
		{
			expectedMASStopsWithoutSeqNumber=0;
			for (Entry<String, Map<String, List<String>>> entry : actualTotalStopsDrilldownAllTabTable.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("MAS Seq #")) {		
						for (int i = 0; i < entry2.getValue().size(); i++) {
						if(entry2.getValue().get(i).equals("UNK"))
						{
							expectedMASStopsWithoutSeqNumber=expectedMASStopsWithoutSeqNumber+1;
						}
						}
					}
				}
			}

		if (Util.compareNumber(actualMASStopsWithoutSeqNumber.get(j), expectedMASStopsWithoutSeqNumber)) {
			Report.InfoTest(test,
					"MAS Stops w/o Seq # is correct in detail section of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
							+ actualMASStopsWithoutSeqNumber.get(j) + " and Expected is : "
							+ expectedMASStopsWithoutSeqNumber);
			Report.PassTest(test, "MAS Stops w/o Seq # is as expected in detail section of Sequence Compliance Detail report for Route : "+route.get(j));
		} else {
			Report.FailTest(test, "MAS Stops w/o Seq # is not as expected in detail section of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
					+ actualMASStopsWithoutSeqNumber.get(j) + " and Expected is : " + expectedMASStopsWithoutSeqNumber);
		}
	}

}catch (Exception e) {
		Report.FailTest(test, "MAS Stops w/o Seq # is not as expected in detail section of Sequence Compliance Detail report Actual is : "
				+ actualMASStopsWithoutSeqNumber + " and Expected is : " + expectedMASStopsWithoutSeqNumber);
	}
		
	}


private void validateDetailTotalStops(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalStopsDrilldownAllTabTable) {
		
	// Total Stops = # of customers in Total Stops drilldown;

				int expectedTotalStops=0;			
				List<Integer> actualTotalStops=new ArrayList<>();
				List<String> route = new ArrayList<>();
				try {
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().equals("Route")) {
							route = entry.getValue();
						}
					}
					
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().equals("Total Stops")) {
							for (int i = 0; i < entry.getValue().size(); i++) {						
								actualTotalStops.add(Integer.parseInt(entry.getValue().get(i)));
								Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)), "Total Stops in Sequence Compliance Detail Report");
							}
						}
					}
					

					for (int j=0;j<route.size();j++)
					{
						
						
						for (Entry<String, Map<String, List<String>>> entry : actualTotalStopsDrilldownAllTabTable.entrySet()) {
							
							if(entry.getKey().equals(route.get(j)))
							{
								
								for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
									
								if (entry2.getKey().equals("Actual Seq #")) {							
									expectedTotalStops=entry2.getValue().size();							
								}
							}
							}
						}		
			
					if (Util.compareNumber(actualTotalStops.get(j), expectedTotalStops)) {
						Report.InfoTest(test,
								"Total Stops is correct in detail section of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
										+ actualTotalStops.get(j) + " and Expected is : "
										+ expectedTotalStops);
						Report.PassTest(test, "Total Stops is as expected in detail section of Sequence Compliance Detail report for Route : "+route.get(j));
					} else {
						Report.FailTest(test, "Total Stops is not as expected in detail section of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
								+ actualTotalStops.get(j) + " and Expected is : " + expectedTotalStops);
					}
				}
			
			}catch (Exception e) {
					Report.FailTest(test, "Total Stops is not as expected in detail section of Sequence Compliance Detail report Actual is : "
							+ actualTotalStops + " and Expected is : " + expectedTotalStops);
				}
		
	}


private void validateDetailNumberofStopsInSeq(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalStopsDrilldownAllTabTable) {
		
	// number of stops In sequence = Number of stops in sequence validated against DB

		List<Integer> actualNumberOfStopsInSequence = new ArrayList<>();
		List<Integer> totalStops = new ArrayList<>();
		int expectedNumberOfStopsInSequence = 0;
		
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		try {
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("# of Stops In Seq")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						
						actualNumberOfStopsInSequence.add(Integer.parseInt(entry.getValue().get(i)));
						Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)),
								"# of Stops In Seq in Sequence Compliance Detail Report.");
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Total Stops")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						
						totalStops.add(Integer.parseInt(entry.getValue().get(i)));
						
					}
				}
			}
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

		

			for (int i = 0; i < route.size(); i++) {
				
				List<String> stopsActualSequence = new ArrayList<>();
				
				for (Entry<String, Map<String, List<String>>> entry : actualTotalStopsDrilldownAllTabTable.entrySet()) {
					if(entry.getKey().equals(route.get(i)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Plan Seq #")) {
							for (int j = 0; j < entry2.getValue().size(); j++) {
								if (entry2.getValue().get(i).equals("--")) {
									stopsActualSequence.add("NA");
								} else {
									stopsActualSequence.add(entry2.getValue().get(i).trim());
								}
							}
						}
					}
				}
				
				/*stopsActualSequence = Util.getDataFromDB(
						"select ORDERSEQUENCE from OCS_ADMIN.OCS_CUSTOMERORDER where ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID =  '"
								+ route.get(i) + "') and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
								+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') order by TICKETCOMPLETEDSTAMP");
*/
			
				
				expectedNumberOfStopsInSequence = Util.getNumberOfStopsInSequence(stopsActualSequence);
			
				
				
				
				if (Util.compareNumber(actualNumberOfStopsInSequence.get(i), expectedNumberOfStopsInSequence)) {
					Report.InfoTest(test,
							"# of Stops In Seq is correct in Detail section of Sequence Compliance report for Route : "
									+ route.get(i) + " with Actual : " + actualNumberOfStopsInSequence.get(i)
									+ " and expected : " + expectedNumberOfStopsInSequence);
					Report.PassTest(test, "# of Stops In Seq is as expected in Detail section of Sequence Compliance report");
				} else {
					Report.FailTest(test, "# of Stops In Seq is not as expected in Detail section of Sequence Compliance report for Route : " + route.get(i) + " with Actual : "
							+ actualNumberOfStopsInSequence.get(i) + " and expected : " + expectedNumberOfStopsInSequence);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"# of Stops In Seq is not as expected in Detail section of Sequence Compliance report Actual is : "
							+ actualNumberOfStopsInSequence.toString() + " and Expected is : " + expectedNumberOfStopsInSequence);
		}
		
	}


private void validateDetailInSeqPercent(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalStopsDrilldownAllTabTable) {
		
	// In Seq Percent = (# of Stops In Sequence / Total Stops) *100;

				int expectedInSeqPercent=0;			
				List<Integer> actualInSeqPercent=new ArrayList<>();
				List<Integer> numberOfStopsInSeq=new ArrayList<>();
				List<Integer> totalStops=new ArrayList<>();
				List<String> route = new ArrayList<>();
				try {
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().equals("Route")) {
							route = entry.getValue();
						}
					}
					
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().equals("In Seq %")) {
							for (int i = 0; i < entry.getValue().size(); i++) {						
								actualInSeqPercent.add(Integer.parseInt(entry.getValue().get(i)));
								Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)), "In Seq % in Sequence Compliance Detail Report");
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().equals("# of Stops In Seq")) {
							for (int i = 0; i < entry.getValue().size(); i++) {		
								if(entry.getValue().get(i).equals("--"))
								{
									numberOfStopsInSeq.add(0);
								}
								else
								{
									numberOfStopsInSeq.add(Integer.parseInt(entry.getValue().get(i)));
								}
								
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().equals("Total Stops")) {
							for (int i = 0; i < entry.getValue().size(); i++) {		
								if(entry.getValue().get(i).equals("--"))
								{
									totalStops.add(0);
								}
								else
								{
									totalStops.add(Integer.parseInt(entry.getValue().get(i)));
								}
								
							}
						}
					}
					
					for (int j=0;j<route.size();j++)
					{
					
					expectedInSeqPercent=(numberOfStopsInSeq.get(j)*100)/totalStops.get(j);
						
					if (Util.compareNumber(actualInSeqPercent.get(j), expectedInSeqPercent)) {
						Report.InfoTest(test,
								"In Seq % is correct in detail section of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
										+ actualInSeqPercent.get(j) + " and Expected is : "
										+ expectedInSeqPercent);
						Report.PassTest(test, "In Seq % is as expected in detail section of Sequence Compliance Detail report for Route : "+route.get(j));
					} else {
						Report.FailTest(test, "In Seq % is not as expected in detail section of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
								+ actualInSeqPercent.get(j) + " and Expected is : " + expectedInSeqPercent);
					}
				}
			
			}catch (Exception e) {
					Report.FailTest(test, "In Seq % is not as expected in detail section of Sequence Compliance Detail report Actual is : "
							+ actualInSeqPercent + " and Expected is : " + expectedInSeqPercent);
				}
		
	}


private void validateDetailNumberofStopsOutofSeq(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalStopsDrilldownAllTabTable) {
		
	// number of stops out of sequence = Total Stops - Number of stops in sequence

	List<Integer> actualNumberOfStopsOutOfSequence = new ArrayList<>();
	List<Integer> totalStops = new ArrayList<>();
	int expectedNumberOfStopsOutOfSequence = 0;
	List<String> stopsActualSequence = new ArrayList<>();
	int numberOfStopsInSequence = 0;
	List<String> route = new ArrayList<>();
	List<String> date = new ArrayList<>();
	SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
	try {
		for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
			if (entry.getKey().equals("# of Stops Out of Seq")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					
					actualNumberOfStopsOutOfSequence.add(Integer.parseInt(entry.getValue().get(i)));
					Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)),
							"# of Stops Out of Seq in Sequence Compliance Detail Report.");
				}
			}
		}
		
		for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
			if (entry.getKey().equals("Total Stops")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					
					totalStops.add(Integer.parseInt(entry.getValue().get(i)));
					
				}
			}
		}
		for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
			if (entry.getKey().equals("Route")) {
				route = entry.getValue();
			}
		}
		for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
			if (entry.getKey().contains("Date")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					Date dates = format1.parse(entry.getValue().get(i));
					date.add(format2.format(dates));
				}
			}
		}

	

		for (int i = 0; i < route.size(); i++) {
			
			stopsActualSequence = Util.getDataFromDB(
					"select OCS_ADMIN.TP_CUSTOMERORDER.ORDER_SEQUENCE from  OCS_ADMIN.TP_CUSTOMERORDER join OCS_ADMIN.TP_ROUTEORDER  on OCS_ADMIN.TP_CUSTOMERORDER.FK_ROUTEORDER = OCS_ADMIN.TP_ROUTEORDER.PKEY join OCS_ADMIN.TP_ROUTE on OCS_ADMIN.TP_ROUTEORDER.FK_ROUTE = OCS_ADMIN.TP_ROUTE.PKEY where OCS_ADMIN.TP_ROUTE.ID = '"+ route.get(i) + "' and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ('"+ Util.sqlFormatedList(date) + "') and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE = 'CONFIRMED' order by OCS_ADMIN.TP_CUSTOMERORDER.AUDIT_CREATE_DT");

			numberOfStopsInSequence = Util.getNumberOfStopsInSequence(stopsActualSequence);
		
			
			expectedNumberOfStopsOutOfSequence = totalStops.get(i)-numberOfStopsInSequence;
			
			if (Util.compareNumber(actualNumberOfStopsOutOfSequence.get(i), expectedNumberOfStopsOutOfSequence)) {
				Report.InfoTest(test,
						"# of Stops Out of Seq is correct in Detail section of Sequence Compliance report for Route : "
								+ route.get(i) + " with Actual : " + actualNumberOfStopsOutOfSequence.get(i)
								+ " and expected : " + expectedNumberOfStopsOutOfSequence);
				Report.PassTest(test, "# of Stops Out of Seq is as expected in Detail section of Sequence Compliance report");
			} else {
				Report.FailTest(test, "# of Stops Out of Seq is not as expected in Detail section of Sequence Compliance report for Route : " + route.get(i) + " with Actual : "
						+ actualNumberOfStopsOutOfSequence.get(i) + " and expected : " + expectedNumberOfStopsOutOfSequence);
			}
		}
	} catch (Exception e) {
		Report.FailTest(test,
				"# of Stops Out of Seq is not as expected in Detail section of Sequence Compliance report Actual is : "
						+ actualNumberOfStopsOutOfSequence.toString() + " and Expected is : " + expectedNumberOfStopsOutOfSequence);
	}
		
	}


private void validateDetailOutofSeqPercent(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalStopsDrilldownAllTabTable) {
	// Out Of Seq Percent = (# of Stops Out of Sequence / Total Stops) *100;

			int expectedOutOfSeqPercent=0;			
			List<Integer> actualOutOFSeqPercent=new ArrayList<>();
			List<Integer> numberOfStopsOutOfSeq=new ArrayList<>();
			List<Integer> totalStops=new ArrayList<>();
			List<String> route = new ArrayList<>();
			try {
				for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
					if (entry.getKey().equals("Route")) {
						route = entry.getValue();
					}
				}
				
				for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
					if (entry.getKey().equals("Out of Seq %")) {
						for (int i = 0; i < entry.getValue().size(); i++) {						
							actualOutOFSeqPercent.add(Integer.parseInt(entry.getValue().get(i)));
							Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)), "Out Of Seq % in Sequence Compliance Detail Report");
						}
					}
				}
				
				for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
					if (entry.getKey().equals("# of Stops Out of Seq")) {
						for (int i = 0; i < entry.getValue().size(); i++) {		
							if(entry.getValue().get(i).equals("--"))
							{
								numberOfStopsOutOfSeq.add(0);
							}
							else
							{
								numberOfStopsOutOfSeq.add(Integer.parseInt(entry.getValue().get(i)));
							}
							
						}
					}
				}
				
				for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
					if (entry.getKey().equals("Total Stops")) {
						for (int i = 0; i < entry.getValue().size(); i++) {		
							if(entry.getValue().get(i).equals("--"))
							{
								totalStops.add(0);
							}
							else
							{
								totalStops.add(Integer.parseInt(entry.getValue().get(i)));
							}
							
						}
					}
				}
				
				for (int j=0;j<route.size();j++)
				{
				
				expectedOutOfSeqPercent=(numberOfStopsOutOfSeq.get(j)*100)/totalStops.get(j);
					
				if (Util.compareNumber(actualOutOFSeqPercent.get(j), expectedOutOfSeqPercent)) {
					Report.InfoTest(test,
							"Out of Seq % is correct in detail section of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
									+ actualOutOFSeqPercent.get(j) + " and Expected is : "
									+ expectedOutOfSeqPercent);
					Report.PassTest(test, "Out of Seq % is as expected in detail section of Sequence Compliance Detail report for Route : "+route.get(j));
				} else {
					Report.FailTest(test, "Out of Seq % is not as expected in detail section of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
							+ actualOutOFSeqPercent.get(j) + " and Expected is : " + expectedOutOfSeqPercent);
				}
			}
		
		}catch (Exception e) {
				Report.FailTest(test, "Out of Seq % is not as expected in detail section of Sequence Compliance Detail report Actual is : "
						+ actualOutOFSeqPercent + " and Expected is : " + expectedOutOfSeqPercent);
			}
		
	}


private void validateDetailDriver(Map<String, List<String>> actualDetailTable, String expectedDriverName) {
		
		String[] drivers = expectedDriverName.split(";");
		List<String> actualDrivers = null;
		try {
			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().equals("Driver")) {
					actualDrivers = entry.getValue();
				}
			}
			for (int i = 0; i < drivers.length; i++) {
				if (actualDrivers.contains(drivers[i])) {
					Report.InfoTest(test,
							"Driver Name is correct in Sequence Compliance Detail report Actual List is : "
									+ actualDrivers.toString() + " and Expected is : " + drivers[i]);
					Report.PassTest(test, "Driver Name is as expected in Sequence Compliance Detail sub view report");
				} else {
					Report.FailTest(test,
							"Driver Name is not as expected in Sequence Compliance Detail report Actual List is : " + actualDrivers.toString()
									+ " and Expected is : " + drivers[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Driver Name is not as expected in Sequence Compliance Detail report Actual List is : "
							+ actualDrivers.toString() + " and Expected is : " + drivers.toString());
		}
		
	}

	private void validateDetailRoute(Map<String, List<String>> actualDetailTable, String expectedRouteName) {
		String[] routes = expectedRouteName.split(";");
		List<String> actualRoutes = null;
		try {
			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					actualRoutes = entry.getValue();
				}
			}
			for (int i = 0; i < routes.length; i++) {
				if (actualRoutes.contains(routes[i])) {
					Report.InfoTest(test,
							"Route Name is correct in Sequence Compliance Detail report Actual List is : "
									+ actualRoutes.toString() + " and Expected is : " + routes[i]);
					Report.PassTest(test, "Route Name is as expected in Sequence Compliance Detail report");
				} else {
					Report.FailTest(test,
							"Route Name is not as expected in Sequence Compliance Detail report Actual List is : " + actualRoutes.toString()
									+ " and Expected is : " + routes[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Route is not as expected in Sequence Compliance Detail report Actual List is : "
							+ actualRoutes.toString() + " and Expected is : " + routes.toString());
		}
		
	}

	private void validateDetailDate(Map<String, List<String>> actualDetailTable, String fromDate, String toDate) {
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yy");
		Date startDate = null;
		Date endDate = null;
		List<Date> routeServedDate = new ArrayList<>();
		List<String> actualRoutes = null;
		try {
			startDate = format1.parse(fromDate);
			endDate = format1.parse(toDate);
			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					actualRoutes = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().equals("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						routeServedDate.add(format2.parse(entry.getValue().get(i).toString()));
					}
				}
			}

			for (int i = 0; i < actualRoutes.size(); i++) {
				if (routeServedDate.get(i).equals(startDate) || routeServedDate.get(i).equals(endDate)
						|| (routeServedDate.get(i).after(startDate) && routeServedDate.get(i).before(endDate))) {
					Report.InfoTest(test,
							"Route Date is correct in Sequence Compliance Detail report for route : "
									+ actualRoutes.get(i) + ". Actual is : " + format1.format(routeServedDate.get(i))
									+ " and Expected is between : " + format1.format(startDate) + "and "
									+ format1.format(endDate));
					Report.PassTest(test, "Route Date is as expected in Sequence Compliance Detail Report");
				} else {
					Report.FailTest(test,
							"Route Date is not as expected in Sequence Compliance Detail report Actual is : "
									+ format1.format(routeServedDate.get(i)) + " and Expected is between : "
									+ format1.format(startDate) + "and " + format1.format(endDate));
				}
			}

		} catch (Exception e) {
			Report.FailTest(test, "Not able to compare date is in between the date range");
			LogClass.error(e.getMessage());
		}
		
	}


	public void validateTotalStopsDrilldownData(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalStopsDrilldownAllTabTable) {
		
		validateDrilldownInSeqIndicator(actualAllTabDetailTable,actualTotalStopsDrilldownAllTabTable);
		validateDrilldownActualSeqNumber(actualAllTabDetailTable,actualTotalStopsDrilldownAllTabTable);
		validateDrilldownPlanSeqNumber(actualAllTabDetailTable,actualTotalStopsDrilldownAllTabTable);
		validateDrilldownCustID(actualAllTabDetailTable,actualTotalStopsDrilldownAllTabTable);
		validateDrilldownCustName(actualAllTabDetailTable,actualTotalStopsDrilldownAllTabTable);
		validateDrilldownMASSeqNumber(actualAllTabDetailTable,actualTotalStopsDrilldownAllTabTable);
		
	}


	private void validateDrilldownMASSeqNumber(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalStopsDrilldownAllTabTable) {
		
		// Cust ID  = Customer ID in OCS.
		
		List<Integer> noOfStops=new ArrayList<>();
		List<String> route = new ArrayList<>();
		
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
	
		try {
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Total Stops")) {
					for (int i = 0; i < entry.getValue().size(); i++) {						
						noOfStops.add(Integer.parseInt(entry.getValue().get(i)));					
					}
				}
			}
			
			for (int j=0;j<route.size();j++)
			{
				List<String> actualMASSequenceNumber = new ArrayList<>();
				
				List<String> customerID = new ArrayList<>();
			
				for (Entry<String, Map<String, List<String>>> entry : actualTotalStopsDrilldownAllTabTable.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("MAS Seq #")) {
							for (int i = 0; i < entry2.getValue().size(); i++) {
								if (entry2.getValue().get(i).equals("--")) {
									actualMASSequenceNumber.add("NA");
								} else {
									actualMASSequenceNumber.add(entry2.getValue().get(i).trim());
								}
							}
						}
					}
				}
				
				for (Entry<String, Map<String, List<String>>> entry : actualTotalStopsDrilldownAllTabTable.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Cust ID")) {
							for (int i = 0; i < entry2.getValue().size(); i++) {
								if (entry2.getValue().get(i).equals("--")) {
									customerID.add("NA");
								} else {
									customerID.add(entry2.getValue().get(i).trim());
								}
							}
						}
					}
				}
				
				//expectedMASSequenceNumber = Util.getDataFromDB("select OCS_ADMIN.OCS_CUSTOMERORDER.MASSEQUENCE from OCS_ADMIN.OCS_CUSTOMERORDER join OCS_ADMIN.TP_ROUTEORDER  on  OCS_ADMIN.OCS_CUSTOMERORDER.ROUTEORDER = OCS_ADMIN.TP_ROUTEORDER.PKEY join OCS_ADMIN.TP_ROUTE on OCS_ADMIN.TP_ROUTEORDER.FK_ROUTE = OCS_ADMIN.TP_ROUTE.PKEY where OCS_ADMIN.TP_ROUTE.ID ='"+route.get(j)+"' and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("+ Util.sqlFormatedList(date) +") order by OCS_ADMIN.OCS_CUSTOMERORDER.TICKETCOMPLETEDSTAMP");

				for(int i=0;i<noOfStops.size();i++)
				{
					List<String> expectedMASSequenceNumber = new ArrayList<>();
					expectedMASSequenceNumber=Util.getDataFromDB("select vpc.ORDER_SEQUENCE from  OCS_ADMIN.VP_CUSTOMERORDER  vpc inner join OCS_ADMIN.TP_ROUTEORDER tpr on vpc.ROUTEORDER.PKEY = tpr.PKEY inner join OCS_ADMIN.TP_ROUTE on tpr.FK_ROUTE = OCS_ADMIN.TP_ROUTE.PKEY where OCS_ADMIN.TP_ROUTE.ID = '"+ route.get(j) +"' and tpr.ORDERDATE IN ('"+ Util.sqlFormatedList(date) +"') and tpr.STATUS_CODE = 'CONFIRMED' and vpc.CUSTOMER.COMPANYID || '-' || vpc.CUSTOMER.COMPANYNUMBER IN ('"+ Util.sqlFormatedList(customerID) +"')");
					
					if(expectedMASSequenceNumber.get(0).equals("0000000") || expectedMASSequenceNumber.get(i)==null)
					{
						if (actualMASSequenceNumber.get(i).equals("UNK")) {
							Report.InfoTest(test,
							"MAS Seq # is correct in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
									+ actualMASSequenceNumber.get(i) + " and Expected is : UNK");
							Report.PassTest(test, "MAS Seq # is as expected in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j));
				}
						else {
							Report.FailTest(test, "MAS Seq # is not as expected in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
							+ actualMASSequenceNumber.get(i) + " and Expected is : UNK");
				}
				}
					else
						{
						
						
							if (actualMASSequenceNumber.get(i).equals(expectedMASSequenceNumber.get(0))) {
							Report.InfoTest(test,
							"MAS Seq # is correct in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
									+ actualMASSequenceNumber.get(i) + " and Expected is : "
									+ expectedMASSequenceNumber.get(0));
							Report.PassTest(test, "MAS Seq # is as expected in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j));
				}
						else {
							Report.FailTest(test, "MAS Seq # is not as expected in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
							+ actualMASSequenceNumber.get(i) + " and Expected is : " + expectedMASSequenceNumber.get(0));
				}
		 
				}
			}
	}
		}catch (Exception e) {
			Report.FailTest(test, "MAS Seq # is not as expected in drill down of Total Stops of Sequence Compliance Detail report.");
		}
		
	}


	private void validateDrilldownCustName(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalStopsDrilldownAllTabTable) {
		// Cust Name  = Customer Name in OCS.
		
		List<Integer> noOfStops=new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
	
		try {
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Total Stops")) {
					for (int i = 0; i < entry.getValue().size(); i++) {						
						noOfStops.add(Integer.parseInt(entry.getValue().get(i)));					
					}
				}
			}
			
			for (int j=0;j<route.size();j++)
			{
				List<String> actualCustomerName = new ArrayList<>();
				List<String> expectedCustomerName = new ArrayList<>();
			
				for (Entry<String, Map<String, List<String>>> entry : actualTotalStopsDrilldownAllTabTable.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Cust Name")) {
							for (int i = 0; i < entry2.getValue().size(); i++) {
								if (entry2.getValue().get(i).equals("--")) {
									actualCustomerName.add("NA");
								} else {
									actualCustomerName.add(entry2.getValue().get(i).trim());
								}
							}
						}
					}
				}
				
				expectedCustomerName = Util.getDataFromDB("select OCS_ADMIN.TP_CUSTOMER.NAME from OCS_ADMIN.TP_CUSTOMER join OCS_ADMIN.TP_CUSTOMERORDER on OCS_ADMIN.TP_CUSTOMER.PKEY = OCS_ADMIN.TP_CUSTOMERORDER.FK_CUSTOMER join OCS_ADMIN.TP_ROUTEORDER  on OCS_ADMIN.TP_CUSTOMERORDER.FK_ROUTEORDER = OCS_ADMIN.TP_ROUTEORDER.PKEY join OCS_ADMIN.TP_ROUTE on OCS_ADMIN.TP_ROUTEORDER.FK_ROUTE = OCS_ADMIN.TP_ROUTE.PKEY where OCS_ADMIN.TP_ROUTE.ID = '"+route.get(j)+"' and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("+ Util.sqlFormatedList(date) +") order by OCS_ADMIN.TP_CUSTOMERORDER.AUDIT_CREATE_DT");

				
				if (actualCustomerName.equals(expectedCustomerName)) {
				Report.InfoTest(test,
						"Cust Name is correct in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
								+ actualCustomerName + " and Expected is : "
								+ expectedCustomerName);
				Report.PassTest(test, "Cust Name is as expected in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j));
			} else {
				Report.FailTest(test, "Cust Name is not as expected in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
						+ actualCustomerName + " and Expected is : " + expectedCustomerName);
			}
	 
			}
	}catch (Exception e) {
			Report.FailTest(test, "Cust Name is not as expected in drill down of Total Stops of Sequence Compliance Detail report.");
		}
		
	}


	private void validateDrilldownCustID(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalStopsDrilldownAllTabTable) {

		// Cust ID  = Customer ID in OCS.
		
				List<Integer> noOfStops=new ArrayList<>();
				List<String> route = new ArrayList<>();
				List<String> date = new ArrayList<>();
				SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
				SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
			
				try {
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().equals("Route")) {
							route = entry.getValue();
						}
					}
					
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().contains("Date")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								Date dates = format1.parse(entry.getValue().get(i));
								date.add(format2.format(dates));
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().equals("Total Stops")) {
							for (int i = 0; i < entry.getValue().size(); i++) {						
								noOfStops.add(Integer.parseInt(entry.getValue().get(i)));					
							}
						}
					}
					
					for (int j=0;j<route.size();j++)
					{
						List<String> actualCustomerID = new ArrayList<>();
						List<String> expectedCustomerID = new ArrayList<>();
					
						for (Entry<String, Map<String, List<String>>> entry : actualTotalStopsDrilldownAllTabTable.entrySet()) {
							if(entry.getKey().equals(route.get(j)))
							for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
								if (entry2.getKey().equals("Cust ID")) {
									for (int i = 0; i < entry2.getValue().size(); i++) {
										if (entry2.getValue().get(i).equals("--")) {
											actualCustomerID.add("NA");
										} else {
											actualCustomerID.add(entry2.getValue().get(i).trim());
										}
									}
								}
							}
						}
						
						expectedCustomerID = Util.getDataFromDB("select OCS_ADMIN.TP_CUSTOMER.COMPANYID || '-' || OCS_ADMIN.TP_CUSTOMER.COMPANYNUMBER as CustomerID from OCS_ADMIN.TP_CUSTOMER join OCS_ADMIN.TP_CUSTOMERORDER on OCS_ADMIN.TP_CUSTOMER.PKEY = OCS_ADMIN.TP_CUSTOMERORDER.FK_CUSTOMER join OCS_ADMIN.TP_ROUTEORDER  on OCS_ADMIN.TP_CUSTOMERORDER.FK_ROUTEORDER = OCS_ADMIN.TP_ROUTEORDER.PKEY join OCS_ADMIN.TP_ROUTE on OCS_ADMIN.TP_ROUTEORDER.FK_ROUTE = OCS_ADMIN.TP_ROUTE.PKEY where OCS_ADMIN.TP_ROUTE.ID = '"+route.get(j)+"' and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("+ Util.sqlFormatedList(date) +") order by OCS_ADMIN.TP_CUSTOMERORDER.AUDIT_CREATE_DT");
						
						if (actualCustomerID.equals(expectedCustomerID)) {
						Report.InfoTest(test,
								"Cust ID is correct in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
										+ actualCustomerID + " and Expected is : "
										+ expectedCustomerID);
						Report.PassTest(test, "Cust ID is as expected in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j));
					} else {
						Report.FailTest(test, "Cust ID is not as expected in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
								+ actualCustomerID + " and Expected is : " + expectedCustomerID);
					}
			 
					}
			}catch (Exception e) {
					Report.FailTest(test, "Cust ID is not as expected in drill down of Total Stops of Sequence Compliance Detail report.");
				}
		
	}


	private void validateDrilldownPlanSeqNumber(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalStopsDrilldownAllTabTable) {
		
		// Plan Sequence = Stops planned in OCS.
		
				List<Integer> noOfStops=new ArrayList<>();
				List<String> route = new ArrayList<>();
				List<String> date = new ArrayList<>();
				
				SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
				SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
			
				try {
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().equals("Route")) {
							route = entry.getValue();
						}
					}
					
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().contains("Date")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								Date dates = format1.parse(entry.getValue().get(i));
								date.add(format2.format(dates));
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().equals("Total Stops")) {
							for (int i = 0; i < entry.getValue().size(); i++) {						
								noOfStops.add(Integer.parseInt(entry.getValue().get(i)));					
							}
						}
					}
					
					for (int j=0;j<route.size();j++)
					{
						List<String> planSequenceNumber = new ArrayList<>();
						List<String> customerID = new ArrayList<>();
					
						for (Entry<String, Map<String, List<String>>> entry : actualTotalStopsDrilldownAllTabTable.entrySet()) {
							if(entry.getKey().equals(route.get(j)))
							for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
								if (entry2.getKey().equals("Plan Seq #")) {
									for (int i = 0; i < entry2.getValue().size(); i++) {
										if (entry2.getValue().get(i).equals("--")) {
											planSequenceNumber.add("NA");
										} else {
											planSequenceNumber.add(entry2.getValue().get(i).trim());
										}
									}
								}
							}
						}
						
						for (Entry<String, Map<String, List<String>>> entry : actualTotalStopsDrilldownAllTabTable.entrySet()) {
							if(entry.getKey().equals(route.get(j)))
							for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
								if (entry2.getKey().equals("Cust ID")) {
									for (int i = 0; i < entry2.getValue().size(); i++) {
										if (entry2.getValue().get(i).equals("--")) {
											customerID.add("NA");
										} else {
											customerID.add(entry2.getValue().get(i).trim());
										}
									}
								}
							}
						}
					
						for(int k=0;k<noOfStops.get(j);k++)
						{

						List<String> expectedPlanSequenceNumber = new ArrayList<>();
						
						
						/*expectedPlanSequenceNumber = Util.getDataFromDB(
								"select ORDERSEQUENCE from OCS_ADMIN.OCS_CUSTOMERORDER where ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID =  '"
										+ route.get(j) + "') and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
										+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') order by TICKETCOMPLETEDSTAMP");
*/
					
						expectedPlanSequenceNumber=Util.getDataFromDB("select vpc.ORDER_SEQUENCE from  OCS_ADMIN.VP_CUSTOMERORDER  vpc inner join OCS_ADMIN.TP_ROUTEORDER tpr on vpc.ROUTEORDER.PKEY = tpr.PKEY inner join OCS_ADMIN.TP_ROUTE on tpr.FK_ROUTE = OCS_ADMIN.TP_ROUTE.PKEY where OCS_ADMIN.TP_ROUTE.ID = '"+ route.get(j) +"' and tpr.ORDERDATE IN ('"+ Util.sqlFormatedList(date) +"') and tpr.STATUS_CODE = 'CONFIRMED' and vpc.CUSTOMER.COMPANYID || '-' || vpc.CUSTOMER.COMPANYNUMBER IN ('"+ Util.sqlFormatedList(customerID) +"')");
						
						if (planSequenceNumber.get(k).equals(expectedPlanSequenceNumber.get(0))) {
						Report.InfoTest(test,
								"Plan Seq # is correct in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
										+ planSequenceNumber + " and Expected is : "
										+ expectedPlanSequenceNumber);
						Report.PassTest(test, "Plan Seq # is as expected in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j));
					} else {
						Report.FailTest(test, "Plan Seq # is not as expected in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
								+ planSequenceNumber + " and Expected is : " + expectedPlanSequenceNumber);
					}
						}
					}
			}catch (Exception e) {
					Report.FailTest(test, "Plan Seq # is not as expected in drill down of Total Stops of Sequence Compliance Detail report.");
				}
		
	}


	private void validateDrilldownActualSeqNumber(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalStopsDrilldownAllTabTable) {
		// Actual Sequence = Sequence in which stops are served.
		
		List<Integer> noOfStops=new ArrayList<>();
		List<String> route = new ArrayList<>();
		
	
		try {
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Total Stops")) {
					for (int i = 0; i < entry.getValue().size(); i++) {						
						noOfStops.add(Integer.parseInt(entry.getValue().get(i)));					
					}
				}
			}
			
			for (int j=0;j<route.size();j++)
			{
				List<String> actualSequenceNumber = new ArrayList<>();
				List<String> expectedActualSequenceNumber = new ArrayList<>();
			
				for (Entry<String, Map<String, List<String>>> entry : actualTotalStopsDrilldownAllTabTable.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Actual Seq #")) {
							for (int i = 0; i < entry2.getValue().size(); i++) {
								if (entry2.getValue().get(i).equals("--")) {
									actualSequenceNumber.add("NA");
								} else {
									actualSequenceNumber.add(entry2.getValue().get(i).trim());
								}
							}
						}
					}
				}

				expectedActualSequenceNumber = getActualSequenceNumber(noOfStops.get(j));
				if (actualSequenceNumber.equals(expectedActualSequenceNumber)) {
				Report.InfoTest(test,
						"Actual Seq # is correct in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
								+ actualSequenceNumber + " and Expected is : "
								+ expectedActualSequenceNumber);
				Report.PassTest(test, "Actual Seq # is as expected in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j));
			} else {
				Report.FailTest(test, "Actual Seq # is not as expected in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
						+ actualSequenceNumber + " and Expected is : " + expectedActualSequenceNumber);
			}
	 
			}
	}catch (Exception e) {
			Report.FailTest(test, "Actual Seq # is not as expected in drill down of Total Stops of Sequence Compliance Detail report.");
		}
		
	}


	private List<String> getActualSequenceNumber(int noOfStops) {
		
		List<String> actualSequencNumber=new ArrayList<>();
		try
		{
			for(int i=1;i<=noOfStops;i++)
			{
				actualSequencNumber.add(String.valueOf(i));
			}
		}
		catch(Exception e)
		{
			Report.FailTest(test, "Not able to generate actual sequence number");
		}
		return actualSequencNumber;
	}


	private void validateDrilldownInSeqIndicator(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalStopsDrilldownAllTabTable) {
		
		//In Seq Indicator
		
	
		List<Integer> noOfStops=new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		List<String> stopsActualSequence = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		try {
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Total Stops")) {
					for (int i = 0; i < entry.getValue().size(); i++) {						
						noOfStops.add(Integer.parseInt(entry.getValue().get(i)));					
					}
				}
			}
			
			for (int j=0;j<route.size();j++)
			{
				List<String> actualSequenceIndicator=new ArrayList<>();
				List<String> expectedSequenceIndicator=new ArrayList<>();
			
				for (Entry<String, Map<String, List<String>>> entry : actualTotalStopsDrilldownAllTabTable.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("In Seq Indicator ")) {
							for (int i = 0; i < entry2.getValue().size(); i++) {
								if (entry2.getValue().get(i).equals("--")) {
									actualSequenceIndicator.add("NA");
								} else {
									actualSequenceIndicator.add(entry2.getValue().get(i).trim());
								}
							}
						}
					}
				}
				
				for (Entry<String, Map<String, List<String>>> entry : actualTotalStopsDrilldownAllTabTable.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Plan Seq #")) {
							for (int i = 0; i < entry2.getValue().size(); i++) {
								if (entry2.getValue().get(i).equals("--")) {
									stopsActualSequence.add("NA");
								} else {
									stopsActualSequence.add(entry2.getValue().get(i).trim());
								}
							}
						}
					}
				}

				/*stopsActualSequence = Util.getDataFromDB(
						"select ORDERSEQUENCE from OCS_ADMIN.OCS_CUSTOMERORDER where ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID =  '"
								+ route.get(j) + "') and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
								+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') order by TICKETCOMPLETEDSTAMP");
*/
				
				expectedSequenceIndicator = getInSeqIndicatorValues(stopsActualSequence);
				if (actualSequenceIndicator.equals(expectedSequenceIndicator)) {
				Report.InfoTest(test,
						"In Seq Indicator is correct in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
								+ actualSequenceIndicator + " and Expected is : "
								+ expectedSequenceIndicator);
				Report.PassTest(test, "In Seq Indicator is as expected in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j));
			} else {
				Report.FailTest(test, "In Seq Indicator is not as expected in drill down of Total Stops of Sequence Compliance Detail report for Route : "+route.get(j)+". Actual is : "
						+ actualSequenceIndicator + " and Expected is : " + expectedSequenceIndicator);
			}
	 
			}
	}catch (Exception e) {
			Report.FailTest(test, "In Seq Indicator is not as expected in drill down of Total Stops of Sequence Compliance Detail report.");
		}
		
	}


	private List<String> getInSeqIndicatorValues(List<String> stopsActualSequence) {
		
		List<String> seqIndicatorValues=new ArrayList<>();
		int inSeqStopsCounter = 0;
		List<Integer> intList = new ArrayList<>();
		try {

			for (String s : stopsActualSequence) {
				intList.add(Integer.valueOf(s));
			}

			for (int i = 0; i < intList.size(); i++) {
				if (i == 0) {
					if (intList.get(i) == 2)
					{
						inSeqStopsCounter++;
						seqIndicatorValues.add("Y");
					}
					else
					{
						seqIndicatorValues.add("N");
					}
				} else {
					if (intList.get(i) - intList.get(i - 1) == 2) {
						inSeqStopsCounter++;
						seqIndicatorValues.add("Y");
					} else {
						int tempCounter = 0;
						for (int j = i + 1; j < intList.size(); j++) {
							if (!(intList.get(i) < intList.get(j))) {
								tempCounter++;
							}
						}

						if (tempCounter == 0)
						{
							inSeqStopsCounter++;
							seqIndicatorValues.add("Y");							
						}
						else
						{
							seqIndicatorValues.add("N");
						}
					}
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTest(test, "Not able to identify Number of stops in sequence");
		}

		return seqIndicatorValues;
	}


	public void clickTotalStopsDrilldown() {

		try {
			
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t2']/tbody/tr[1]/td/a/span")).click();
				
			}
		
		 catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to Click on Total Stops", "Total Stops Drilldown");
		}
		
	}
}


