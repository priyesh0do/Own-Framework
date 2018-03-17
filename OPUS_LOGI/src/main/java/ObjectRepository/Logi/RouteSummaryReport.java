package ObjectRepository.Logi;

import java.io.IOException;
import java.text.DecimalFormat;
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


public class RouteSummaryReport {
	WebDriver driver;
	ExtentTest test;
	
	public  RouteSummaryReport(WebDriver driver, ExtentTest test) {
	this.driver = driver;
	this.test = test;
	PageFactory.initElements(driver, this);
	}
	
	//summaryTableColumns
	@FindBy(xpath="//table[@id='dtRouteSummary']/thead/tr/th")
	List<WebElement> summaryTableColumns;
	
	//SumarryTableRows
	@FindBy(xpath = "//table[@id='dtRouteSummary']/tbody/tr")
	List<WebElement> summaryTableRows;
	
	//DetailTableColumns
	@FindBy(xpath="//table[@id='dtRouteDetail']/thead/tr/th")
	List<WebElement> DetailTableColumns;
	
	//DetailTableRows
	@FindBy(xpath = "//table[@id='dtRouteDetail']/tbody/tr")
	List<WebElement> DetailTableRows;
	
	//DetailTableBody
	@FindBy(xpath = "//table[@id='dtRouteDetail']/tbody")
	List<WebElement> DetailTableBody;
	
	
	public Map<String, List<String>> readTable(List<WebElement> columns, List<WebElement> rows, String tableName) {

		if (tableName.equals("dtRouteSummary")) {
			Map<String, List<String>> objTable = new HashMap<>();
			for (int iCount = 2; iCount <= columns.size(); iCount++) {

				for (int row = 1; row <= rows.size(); row++) {

					List<String> rowValues=new ArrayList<>();
					try {
						rowValues.add(driver.findElement(By.xpath(
								"//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + iCount + "]/span"))
								.getText());
						String summaryItem = driver.findElement(By.xpath("//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + 1 + "]/span")).getText();
						objTable.put(summaryItem+" "+columns.get(iCount - 1).getText().replace("\n", ""), rowValues);
					} catch (Exception e) {
						rowValues.add(driver
								.findElement(By.xpath(
										"//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + iCount + "]"))
								.getText());
						String summaryItem = driver.findElement(By.xpath("//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + 1 + "]/span")).getText();
						objTable.put(summaryItem+" "+columns.get(iCount - 1).getText().replace("\n", ""), rowValues);
					}
				}			
			}
			return objTable;
		}
		

		else if (tableName.equals("dtRouteDetail")) {
			Map<String, List<String>> objTable = new HashMap<>();
			for (int iCount = 1; iCount <= columns.size(); iCount++) {
				List<String> rowValues = new ArrayList<>();
				for (int row = 1; row <= rows.size(); row = row + 1) {
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
		} else {
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
	}
	
	public Map<String, List<String>> getActualSummaryTableData() throws IOException {
		System.out.println("inside Actual Summary Data");
		Map<String, List<String>> summaryTableData = new HashMap<>();
		try {
			summaryTableData = readTable(summaryTableColumns, summaryTableRows, "dtRouteSummary");
			Report.PassTestScreenshot(test, driver, "Summary table data read successfully", "Summary Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read summary table data");
		}

		for (Entry<String, List<String>> entry : summaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return summaryTableData;
	}
	
	
	/*public Map<String, List<String>> getActualEffSummaryTableData() throws IOException {
		System.out.println("inside Actual Summary Data");
		Map<String, List<String>> summaryTableData = new HashMap<>();
		try {
			summaryTableData = readTable(summaryTableColumns, summaryTableRows, "dtEfficiencySummary");
			Report.PassTestScreenshot(test, driver, "Summary table data read successfully", "Summary Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read summary table data");
		}

		for (Entry<String, List<String>> entry : summaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return summaryTableData;
	}*/
	
	public Map<String, List<String>> getActualDetailTableData() {
		System.out.println("inside Actual Performance Data");
		Util.CaptureScreenshot("Performance subview Table data");
		Map<String, List<String>> performanceSubViewTableData = new HashMap<>();
		try {
			performanceSubViewTableData = readTable(DetailTableColumns, DetailTableRows, "dtRouteDetail");
			Report.PassTestScreenshot(test, driver, "Performance Subview table data read successfully",
					"Performance SubView Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Performance subview table data");
		}
		for (Entry<String, List<String>> entry : performanceSubViewTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return performanceSubViewTableData;
	}
	
	public void validateSummaryData(Map<String, List<String>> actualSummaryTableData,

			Map<String, List<String>> actualPerformanceTableData, String expectedLOB, String expectedRoute,
			String expectedDriver, String fromDate, String toDate) {
		
		
		
		
		
	}

	public void validateRouteSummaryData(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualDetailTable,String lOB) {
		
		validateTotalEffVar(actualSummaryTable,actualDetailTable,lOB);
		validateTotalDriverHelperEffVar(actualSummaryTable, actualDetailTable, lOB);
		validateTotalUnitVar(actualSummaryTable, actualDetailTable, lOB);
		validateTotalPreRouteHours(actualSummaryTable, actualDetailTable, lOB);
		validateTotalPostRouteHours(actualSummaryTable, actualDetailTable, lOB);
		validateTotalDispCycleHours(actualSummaryTable, actualDetailTable, lOB);
		validateTotalDowntimeHours(actualSummaryTable, actualDetailTable, lOB);
		validateTotalNetIdleHours(actualSummaryTable, actualDetailTable, lOB);
		validateTotalMealHours(actualSummaryTable, actualDetailTable, lOB);
		validateInSeqPercent(actualSummaryTable, lOB);
		validateTotalMiles(actualSummaryTable, actualDetailTable, lOB);
		validateTotalMiles(actualSummaryTable, actualDetailTable, lOB);
		validateAvgDriverHelperEffVar(actualSummaryTable, lOB);
		validateAvgUnitVar(actualSummaryTable, lOB);
		validateAvgPreRouteHours(actualSummaryTable, lOB);
		validateAvgPostRouteHours(actualSummaryTable, lOB);
		validateAvgDispCycleHours(actualSummaryTable, lOB);
		validateAvgDowntimeHours(actualSummaryTable, lOB);
		validateAvgNetIdleHours(actualSummaryTable, lOB);
		validateAvgMealHours(actualSummaryTable, lOB);
		validateDriverAvgMiles(actualSummaryTable, lOB);
		
		
	}
	public void validateRouteSummaryDetailData(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualDetailTable,String lOB) {
		
		
		
		
		
	}
	
	public void validateSummaryPlanEfficiency(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualDetailTableData) {
		double actualPlanEfficiency = 0.00;
		double expectedPlanEfficiency = 0.00;
		int noOfRoutes = 0;
		try {

			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().equals("Plan Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualPlanEfficiency = 0.00;
						} else {
							actualPlanEfficiency = Double.parseDouble(entry.getValue().get(i));
							// Validate Decimal Format for the value : should
							// have 2 decimal places
							Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, "Plan Eff in Summary Section");
						}
					}

				}

			}

			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().equals("Drv Plan Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedPlanEfficiency = expectedPlanEfficiency + 0.00;
						} else {
							expectedPlanEfficiency = expectedPlanEfficiency
									+ Double.parseDouble(entry.getValue().get(i));
						}
					}
					noOfRoutes = entry.getValue().size();
				}
			}

			// Efficiency at summary section = Sum Of individual route
			// efficiency/No. Of Routes
			expectedPlanEfficiency = expectedPlanEfficiency / noOfRoutes;

			if (Util.compareNumber(actualPlanEfficiency, expectedPlanEfficiency)) {
				Report.InfoTest(test, "Plan Efficiency is correct in summary report Actual is : " + actualPlanEfficiency
						+ " and Expected is : " + expectedPlanEfficiency);
				Report.PassTest(test, "Plan Efficiency is as expected in Summary report");
			} else {
				Report.FailTest(test, "Plan Efficiency is not as expected in summary report Actual is : "
						+ actualPlanEfficiency + " and Expected is : " + expectedPlanEfficiency);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Plan Efficiency is not as expected in summary report Actual is : "
					+ actualPlanEfficiency + " and Expected is : " + expectedPlanEfficiency);
		}
	}
	
	
	public void validateTotalEffVar(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualDetailTable, String lOB) {
		// Summary Actual Units = sum of actual units of all sites
		double TotalEffVar = 0.0;
		double expectedTotalEffVar = 0.0;
		
		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Eff Var (in hrs)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							TotalEffVar = 0.0;
						} else {
							TotalEffVar = Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateFieldDecimalPlaces(entry.getValue().get(i).trim().replaceAll(",", ""), 2, "Total Eff Var in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().equals("Eff Var (in hrs)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedTotalEffVar = expectedTotalEffVar + Double.parseDouble("0.0");
						} else {
							expectedTotalEffVar = expectedTotalEffVar
									+ Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
						}
					}

				}
			}	
		
			if (expectedTotalEffVar == 0.0) {
				Report.FailTest(test,
						"Total Actual Units is not calculated as expected in summary report Actual is : -- and Expected is : "
								+ expectedTotalEffVar);
			} else {
				if (Util.compareNumber(TotalEffVar, expectedTotalEffVar)) {
					Report.InfoTest(test, "Total Eff Var is correct in Efficiency summary report Actual is : "
							+ TotalEffVar + " and Expected is : " + expectedTotalEffVar);
					Report.PassTest(test, "Total Eff Var is as expected in Summary report");
				} else {
					Report.FailTest(test, "Total Eff Var is not as expected in summary report Actual is : " + TotalEffVar
							+ " and Expected is : " + expectedTotalEffVar);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Eff Var is not as expected in summary report Actual is : " + TotalEffVar
					+ " and Expected is : " + expectedTotalEffVar);
		}
		
	}
 
	public void validateTotalDriverHelperEffVar(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualDetailTable, String lOB) {
		// Summary Actual Units = sum of actual units of all sites
		double TotalDriverHelperEffVar = 0.0;
		double expectedDriverHelperEffVar = 0.0;
		DecimalFormat df = new DecimalFormat("#0.00");
		
		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Driver & Helper Eff Var (in hrs)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							TotalDriverHelperEffVar = 0.0;
						} else {
							TotalDriverHelperEffVar = Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateFieldDecimalPlaces(entry.getValue().get(i).trim().replaceAll(",", ""), 2, "Total Driver & Helper Eff Var in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().equals("Driver & Helper Eff Var (in hrs)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedDriverHelperEffVar = expectedDriverHelperEffVar + Double.parseDouble("0.0");
						} else {
							expectedDriverHelperEffVar = expectedDriverHelperEffVar
									+ Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
						}
					}

				}
			}	
		
			if (expectedDriverHelperEffVar == 0.0) {
				Report.FailTest(test,
						"Total Driver & Helper Eff Var is not calculated as expected in Route Summary Report Actual is : -- and Expected is : "
								+ expectedDriverHelperEffVar);
			} else {
				expectedDriverHelperEffVar = Double.parseDouble(df.format(expectedDriverHelperEffVar));
				
				if (Util.compareNumber(TotalDriverHelperEffVar,expectedDriverHelperEffVar)) {
					Report.InfoTest(test, "Total Driver & Helper Eff Var is correct in Route  Summary report Actual is : "
							+ TotalDriverHelperEffVar + " and Expected is : " + expectedDriverHelperEffVar);
					Report.PassTest(test, "Total Driver & Helper Eff Var is as expected in Route Summary Report");
				} else {
					Report.FailTest(test, "Total Driver & Helper Eff Var is not as expected in Route Summary Report Actual is : " + TotalDriverHelperEffVar
							+ " and Expected is : " + expectedDriverHelperEffVar);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Driver & Helper Eff Var is not as expected in Route Summary Rport Actual is : " + TotalDriverHelperEffVar
					+ " and Expected is : " + expectedDriverHelperEffVar);
		}
		
	}
	
	public void validateTotalUnitVar(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualDetailTable, String lOB) {
		// Summary Actual Units = sum of actual units of all sites
		double TotalUnitVar = 0.0;
		double expectedTotalUnitVar = 0.0;
		
		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Units Var")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							TotalUnitVar = 0.0;
						} else {
							TotalUnitVar = Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateFieldDecimalPlaces(entry.getValue().get(i).trim().replaceAll(",", ""), 1, "Total Unit Var in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().equals("Units Var")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedTotalUnitVar = expectedTotalUnitVar + Double.parseDouble("0.0");
						} else {
							expectedTotalUnitVar = expectedTotalUnitVar
									+ Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
						}
					}

				}
			}	
		
			if (expectedTotalUnitVar == 0.0) {
				Report.FailTest(test,
						"Total Units Var is not calculated as expected in Route Summary Report Actual is : -- and Expected is : "
								+ expectedTotalUnitVar);
			} else {
				if (Util.compareNumber(TotalUnitVar, expectedTotalUnitVar)) {
					Report.InfoTest(test, "Total Units Var is correct in Route Summary Report Actual is : "
							+ TotalUnitVar + " and Expected is : " + expectedTotalUnitVar);
					Report.PassTest(test, "Total Units Var is as expected in Route Summary Report");
				} else {
					Report.FailTest(test, "Total Units Var is not as expected in Route Summary Report Actual is : " + TotalUnitVar
							+ " and Expected is : " + expectedTotalUnitVar);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Units Var is not as expected in Route Summary Report Actual is : " + TotalUnitVar
					+ " and Expected is : " + expectedTotalUnitVar);
		}
		
	}

	private void validateTotalPreRouteHours(Map<String, List<String>> actualSummaryTable,Map<String, List<String>> actualDetailTable,
			String lOB) {

		// TotalPreRoute=sum of actual PreRoute hours for all sites
		String TotalPreRouteHours = null;
		String expectedTotalPreRouteHours = null;
		int totalMins = 0;

		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().contains("Total Pre-Route (h:m)" )) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							TotalPreRouteHours = null;
						} else {
							TotalPreRouteHours = entry.getValue().get(i).toString();
							Util.validateTimeFormat(TotalPreRouteHours, " Total Pre Route Hours in Summary Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().contains("Pre-Route (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins += mins;
						}

					}
				}
			}

			expectedTotalPreRouteHours = Util.convertMinsToHours(totalMins);

			if (TotalPreRouteHours.equals(expectedTotalPreRouteHours)) {
				Report.InfoTest(test, "Total Pre Route Hours is correct in summary report Actual is : "
						+ TotalPreRouteHours + " and Expected is : " + expectedTotalPreRouteHours);
				Report.PassTest(test, "Total Pre Route Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Total Pre Route Hours is not as expected in summary report Actual is : "
						+ TotalPreRouteHours + " and Expected is : " + expectedTotalPreRouteHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Pre Route Hours is not as expected in summary report Actual is : "
					+ TotalPreRouteHours + " and Expected is : " + expectedTotalPreRouteHours);
		}
		
	}

	private void validateTotalPostRouteHours(Map<String, List<String>> actualSummaryTable,Map<String, List<String>> actualDetailTable,
			String lOB) {

		// TotalPreRoute=sum of actual PreRoute hours for all sites
		String TotalPostRouteHours = null;
		String expectedTotalPostRouteHours = null;
		int totalMins = 0;

		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().contains("Total Post-Route (h:m)" )) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							TotalPostRouteHours = null;
						} else {
							TotalPostRouteHours = entry.getValue().get(i).toString();
							Util.validateTimeFormat(TotalPostRouteHours, " Total Post Route Hours in Summary Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().contains("Post-Route (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins += mins;
						}

					}
				}
			}

			expectedTotalPostRouteHours = Util.convertMinsToHours(totalMins);

			if (TotalPostRouteHours.equals(expectedTotalPostRouteHours)) {
				Report.InfoTest(test, "Total Post Route Hours is correct in summary report Actual is : "
						+ TotalPostRouteHours + " and Expected is : " + expectedTotalPostRouteHours);
				Report.PassTest(test, "Total Post Route Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Total Post Route Hours is not as expected in summary report Actual is : "
						+ TotalPostRouteHours + " and Expected is : " + expectedTotalPostRouteHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Post Route Hours is not as expected in summary report Actual is : "
					+ TotalPostRouteHours + " and Expected is : " + expectedTotalPostRouteHours);
		}
		
	}
	
	private void validateTotalDispCycleHours(Map<String, List<String>> actualSummaryTable,Map<String, List<String>> actualDetailTable,
			String lOB) {

		// TotalPreRoute=sum of actual PreRoute hours for all sites
		String TotalDispCycleHours = null;
		String expectedTotalDispCycleHours = null;
		int totalMins = 0;

		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().contains("Total Disp Cycle (h:m)" )) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							TotalDispCycleHours = null;
						} else {
							TotalDispCycleHours = entry.getValue().get(i).toString();
							Util.validateTimeFormat(TotalDispCycleHours, " Total Disp Cycle Hours in Summary Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().contains("Disp Cycle (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins += mins;
						}

					}
				}
			}

			expectedTotalDispCycleHours = Util.convertMinsToHours(totalMins);

			if (TotalDispCycleHours.equals(expectedTotalDispCycleHours)) {
				Report.InfoTest(test, "Total Disp Cycle Hours is correct in summary report Actual is : "
						+ TotalDispCycleHours + " and Expected is : " + expectedTotalDispCycleHours);
				Report.PassTest(test, "Total Disp Cycle Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Total Disp Cycle Hours is not as expected in summary report Actual is : "
						+ TotalDispCycleHours + " and Expected is : " + expectedTotalDispCycleHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Disp Cycle Hours is not as expected in summary report Actual is : "
					+ TotalDispCycleHours + " and Expected is : " + expectedTotalDispCycleHours);
		}
		
	}

	private void validateTotalDowntimeHours(Map<String, List<String>> actualSummaryTable,Map<String, List<String>> actualDetailTable,
			String lOB) {

		// TotalPreRoute=sum of actual PreRoute hours for all sites
		String TotalDowntimeHours = null;
		String expectedTotalDowntimeHours = null;
		int totalMins = 0;

		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().contains("Total Downtime (h:m)" )) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							TotalDowntimeHours = null;
						} else {
							TotalDowntimeHours = entry.getValue().get(i).toString();
							Util.validateTimeFormat(TotalDowntimeHours, "Downtime Hours in Summary Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().contains("Down time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins += mins;
						}

					}
				}
			}

			expectedTotalDowntimeHours = Util.convertMinsToHours(totalMins);

			if (TotalDowntimeHours.equals(expectedTotalDowntimeHours)) {
				Report.InfoTest(test, "Downtime Hours is correct in summary report Actual is : "
						+ TotalDowntimeHours + " and Expected is : " + expectedTotalDowntimeHours);
				Report.PassTest(test, "Downtime Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Downtime Hours is not as expected in summary report Actual is : "
						+ TotalDowntimeHours + " and Expected is : " + expectedTotalDowntimeHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Downtime Hours is not as expected in summary report Actual is : "
					+ TotalDowntimeHours + " and Expected is : " + expectedTotalDowntimeHours);
		}
		
	}

	private void validateTotalNetIdleHours(Map<String, List<String>> actualSummaryTable,Map<String, List<String>> actualDetailTable,
			String lOB) {

		// TotalPreRoute=sum of actual PreRoute hours for all sites
		String TotalNetIdleHours = null;
		String expectedTotalNetIdleHours = null;
		int totalMins = 0;

		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().contains("Total Net Idle (h:m)" )) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							TotalNetIdleHours = null;
						} else {
							TotalNetIdleHours = entry.getValue().get(i).toString();
							Util.validateTimeFormat(TotalNetIdleHours, "Net Idle Hours in Summary Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().contains("Net Idle (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins += mins;
						}

					}
				}
			}

			expectedTotalNetIdleHours = Util.convertMinsToHours(totalMins);

			if (TotalNetIdleHours.equals(expectedTotalNetIdleHours)) {
				Report.InfoTest(test, "Net Idle Hours is correct in summary report Actual is : "
						+ TotalNetIdleHours + " and Expected is : " + expectedTotalNetIdleHours);
				Report.PassTest(test, "Net Idle Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Net Idle Hours is not as expected in summary report Actual is : "
						+ TotalNetIdleHours + " and Expected is : " + expectedTotalNetIdleHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Net Idle Hours is not as expected in summary report Actual is : "
					+ TotalNetIdleHours + " and Expected is : " + expectedTotalNetIdleHours);
		}
		
	}

	private void validateTotalMealHours(Map<String, List<String>> actualSummaryTable,Map<String, List<String>> actualDetailTable,
			String lOB) {

		// TotalPreRoute=sum of actual PreRoute hours for all sites
		String TotalMealHours = null;
		String expectedTotalMealHours = null;
		int totalMins = 0;

		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().contains("Total Meal (h:m)" )) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							TotalMealHours = null;
						} else {
							TotalMealHours = entry.getValue().get(i).toString();
							Util.validateTimeFormat(TotalMealHours, "Meal Hours in Summary Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().contains("Meal (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins += mins;
						}

					}
				}
			}

			expectedTotalMealHours = Util.convertMinsToHours(totalMins);

			if (TotalMealHours.equals(expectedTotalMealHours)) {
				Report.InfoTest(test, "Meal Hours is correct in summary report Actual is : "
						+ TotalMealHours + " and Expected is : " + expectedTotalMealHours);
				Report.PassTest(test, "Meal Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Meal Hours is not as expected in summary report Actual is : "
						+ TotalMealHours + " and Expected is : " + expectedTotalMealHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Meal Hours is not as expected in summary report Actual is : "
					+ TotalMealHours + " and Expected is : " + expectedTotalMealHours);
		}
		
	}
	
	private void validateInSeqPercent(Map<String, List<String>> actualSummaryTable, String lOB) {
		String TotalInSeqPercent = null;
		String expectedTotalInSeqPercent="N/A";

		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total In Seq %")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						TotalInSeqPercent=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (TotalInSeqPercent.equals(expectedTotalInSeqPercent)) {
				Report.InfoTest(test,
						"Total In Seq % is correct in summary report Actual is : "
								+ TotalInSeqPercent + " and Expected is : "
								+ expectedTotalInSeqPercent);
				Report.PassTest(test, "Total In Seq % is as expected in Summary report");
			} else {
				Report.FailTest(test, "Total In Seq % is not as expected in summary report Actual is : "
						+ TotalInSeqPercent + " and Expected is : " + expectedTotalInSeqPercent);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total In Seq % is not as expected in summary report Actual is : "
					+ TotalInSeqPercent + " and Expected is : " + expectedTotalInSeqPercent);
		}
		
	}
   
	private void validateTotalMiles(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualDetailTable, String lOB) {
		// Summary Actual Units = sum of actual units of all sites
				int driverTotalMiles = 0;
				int expectedDriverTotalMiles = 0;
				
				try {
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("Total Miles")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									driverTotalMiles = 0;
								} else {
									driverTotalMiles = Integer.parseInt(entry.getValue().get(i));
									Util.validateWholeNumber(driverTotalMiles, "Total Miles in Summary Section");
								}
							}
						}
					}
					for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
						if (entry.getKey().equals("Miles")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									expectedDriverTotalMiles = expectedDriverTotalMiles + Integer.parseInt("0.0");
								} else {
									expectedDriverTotalMiles = expectedDriverTotalMiles
											+ Integer.parseInt(entry.getValue().get(i));
								}
							}

						}
					}

					
				

					
				
					if (expectedDriverTotalMiles == 0.0) {
						Report.FailTest(test,
								"Driver Total Miles is not calculated as expected in summary report Actual is : -- and Expected is : "
										+ expectedDriverTotalMiles);
					} else {
						if (Util.compareNumber(driverTotalMiles, expectedDriverTotalMiles)) {
							Report.InfoTest(test, "Driver Total Miles is correct in Efficiency summary report Actual is : "
									+ driverTotalMiles + " and Expected is : " + expectedDriverTotalMiles);
							Report.PassTest(test, "Driver Total Miles is as expected in Summary report");
						} else {
							Report.FailTest(test, "Driver Total Miles is not as expected in summary report Actual is : " + driverTotalMiles
									+ " and Expected is : " + expectedDriverTotalMiles);
						}
					}
				} catch (Exception e) {
					Report.FailTest(test, "Driver Total Miles is not as expected in summary report Actual is : " + driverTotalMiles
							+ " and Expected is : " + expectedDriverTotalMiles);
				}
		
	
}

    public void validateAvgEffVar(Map<String, List<String>> actualSummaryTable, String lOB) {
		
		//[Total Eff Var/No of Sites]
		
				double TotalEffVar = 0.00;
				int noOfSites=0;
				double expectedAvgEffVar = 0.00;
				double actualAvgEffVar = 0.00;

				DecimalFormat formatter = new DecimalFormat("#0.00");
				try {
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("Total Eff Var (in hrs)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									TotalEffVar = 0.00;
								} else {
									TotalEffVar = Double.parseDouble(entry.getValue().get(i));
									
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().contains("Avg Eff Var (in hrs)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualAvgEffVar = 0.00;
								} else {
									actualAvgEffVar = Double.parseDouble(entry.getValue().get(i));
									// Validate Decimal Format for the value : should
									// have 2 decimal places
									Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, "Avg Eff Var in Summary Section");

								}
							}
						}
					}
					
					
					
						if (DetailTableBody.size()!=0) {
							noOfSites=DetailTableRows.size();
						}

						else {
							Report.InfoTest(test, "No Records in Detail table");
						}
								
					expectedAvgEffVar = TotalEffVar/noOfSites;			
					if (Util.compareNumber(actualAvgEffVar, expectedAvgEffVar)) {
						Report.InfoTest(test,
								"Avg Efficiency Variance is correct in summary report Actual is : "
										+ formatter.format(actualAvgEffVar) + " and Expected is : "
										+ formatter.format(expectedAvgEffVar));
						Report.PassTest(test, "Avg Efficiency Variance is as expected in Summary report");
					} else {
						Report.FailTest(test, "Avg Efficiency Variance is not as expected in summary report Actual is : "
								+ actualAvgEffVar + " and Expected is : " + expectedAvgEffVar);
					}
				} catch (Exception e) {
					Report.FailTest(test, "Avg Efficiency Variance is not as expected in summary report Actual is : "
							+ actualAvgEffVar + " and Expected is : " + expectedAvgEffVar);
				}
		
	}
	
    public void validateAvgDriverHelperEffVar(Map<String, List<String>> actualSummaryTable, String lOB) {
    	/////Total Driver & Helper Eff Var / Num of sites
    	double TotalDriverandHelperEffVar = 0.00;
		int noOfSites=0;
		double expectedDriverandHelperAvgEffVar = 0.00;
		double actualDriverandHelperAvgEffVar = 0.00;

		DecimalFormat formatter = new DecimalFormat("#0.00");
		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Driver & Helper Eff Var (in hrs)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							TotalDriverandHelperEffVar = 0.00;
						} else {
							TotalDriverandHelperEffVar = Double.parseDouble(entry.getValue().get(i));
							
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().contains("Avg Driver & Helper Eff Var (in hrs)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualDriverandHelperAvgEffVar = 0.00;
						} else {
							actualDriverandHelperAvgEffVar = Double.parseDouble(entry.getValue().get(i));
							// Validate Decimal Format for the value : should
							// have 2 decimal places
							Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, "Avg Driver & Helper Eff Var in Summary Section");

						}
					}
				}
			}
			
			
			
				if (DetailTableBody.size()!=0) {
					noOfSites=DetailTableRows.size();
				}

				else {
					Report.InfoTest(test, "No Records in Detail table");
				}
						
				expectedDriverandHelperAvgEffVar = TotalDriverandHelperEffVar/noOfSites;			
			if (Util.compareNumber(actualDriverandHelperAvgEffVar, expectedDriverandHelperAvgEffVar)) {
				Report.InfoTest(test,
						"Avg Driver & Helper Efficiency Variance is correct in summary report Actual is : "
								+ formatter.format(actualDriverandHelperAvgEffVar) + " and Expected is : "
								+ formatter.format(expectedDriverandHelperAvgEffVar));
				Report.PassTest(test, "Avg Driver & Helper Efficiency Variance is as expected in Summary report");
			} else {
				Report.FailTest(test, "Avg Driver & Helper Efficiency Variance is not as expected in summary report Actual is : "
						+ actualDriverandHelperAvgEffVar + " and Expected is : " + expectedDriverandHelperAvgEffVar);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Avg Driver & Helper Efficiency Variance is not as expected in summary report Actual is : "
					+ actualDriverandHelperAvgEffVar + " and Expected is : " + expectedDriverandHelperAvgEffVar);
		}

}
  
	
    public void validateAvgUnitVar(Map<String, List<String>> actualSummaryTable, String lOB) {
    	/////Total Units Var / Num of sites
    	double TotalUnitVar = 0.00;
		int noOfSites=0;
		double expectedUnitVar = 0.00;
		double actualUnitVar = 0.00;

		DecimalFormat formatter = new DecimalFormat("#0.00");
		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Units Var")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							TotalUnitVar = 0.00;
						} else {
							TotalUnitVar = Double.parseDouble(entry.getValue().get(i).replace(",", ""));
							
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().contains("Avg Units Var")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualUnitVar = 0.00;
						} else {
							actualUnitVar = Double.parseDouble(entry.getValue().get(i).replace(",",""));
							// Validate Decimal Format for the value : should
							// have 2 decimal places
							Util.validateFieldDecimalPlaces(entry.getValue().get(i), 1, "Avg Units Var in Summary Section");

						}
					}
				}
			}
			
			
			
				if (DetailTableBody.size()!=0) {
					noOfSites=DetailTableRows.size();
				}

				else {
					Report.InfoTest(test, "No Records in Detail table");
				}
						
				expectedUnitVar = TotalUnitVar/noOfSites;			
			if (Util.compareNumber(actualUnitVar, expectedUnitVar)) {
				Report.InfoTest(test,
						"Avg Unit Variance is correct in summary report Actual is : "
								+ formatter.format(actualUnitVar) + " and Expected is : "
								+ formatter.format(expectedUnitVar));
				Report.PassTest(test, "Avg Units Variance is as expected in Summary report");
			} else {
				Report.FailTest(test, "Avg Units Variance is not as expected in summary report Actual is : "
						+ actualUnitVar + " and Expected is : " + expectedUnitVar);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Avg Units Variance is not as expected in summary report Actual is : "
					+ actualUnitVar + " and Expected is : " + expectedUnitVar);
		}

} 

    private void validateAvgPreRouteHours(Map<String, List<String>> actualSummaryTable, String lOB) {
		// AvgPreRouteHours=sum of PreRouteHours hours for all sites/No of Sites
				String AvgPreRouteHours = null;
				String expectedAvgPreRouteHours = null;
				int noOfSites=0;
				int totalMins = 0;

				try {
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().contains("Avg Pre-Route (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									AvgPreRouteHours = null;
								} else {
									AvgPreRouteHours = entry.getValue().get(i).toString();
									Util.validateTimeFormat(AvgPreRouteHours, "Avg Pre-Route Hours in Summary Section");
								}
							}
						}
					}

					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().contains("Total Pre-Route (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (!entry.getValue().get(i).equals("--")) {
									String[] split = entry.getValue().get(i).split(":", 2);
									int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
									totalMins += mins;
								}
							}
						}
					}
					
					if (DetailTableBody.size()!=0) {
						noOfSites=DetailTableRows.size();
					}

					else {
						Report.InfoTest(test, "No Records in Detail table");
					}
					
					


					totalMins=totalMins/noOfSites;
					expectedAvgPreRouteHours = Util.convertMinsToHours(totalMins);

					if (AvgPreRouteHours.equals(expectedAvgPreRouteHours)) {
						Report.InfoTest(test, "Avg Pre-Route Hours is correct in summary report Actual is : "
								+ AvgPreRouteHours + " and Expected is : " + expectedAvgPreRouteHours);
						Report.PassTest(test, "Avg Pre-Route Actual Hours is as expected in Summary report");
					} else {
						Report.FailTest(test, "Avg Pre-Route Hours is not as expected in summary report Actual is : "
								+ AvgPreRouteHours + " and Expected is : " + expectedAvgPreRouteHours);
					}
				} catch (Exception e) {
					Report.FailTest(test, "Avg Pre-Route Hours is not as expected in summary report Actual is : "
							+ AvgPreRouteHours + " and Expected is : " + expectedAvgPreRouteHours);
				}
		
	}

    private void validateAvgPostRouteHours(Map<String, List<String>> actualSummaryTable, String lOB) {
		// AvgPostRouteHours=sum of PostRouteHours hours for all sites/No of Sites
				String AvgPostRouteHours = null;
				String expectedAvgPostRouteHours = null;
				int noOfSites=0;
				int totalMins = 0;

				try {
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().contains("Avg Post-Route (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									AvgPostRouteHours = null;
								} else {
									AvgPostRouteHours = entry.getValue().get(i).toString();
									Util.validateTimeFormat(AvgPostRouteHours, "Avg Post-Route Hours in Summary Section");
								}
							}
						}
					}

					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().contains("Total Post-Route (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (!entry.getValue().get(i).equals("--")) {
									String[] split = entry.getValue().get(i).split(":", 2);
									int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
									totalMins += mins;
								}
							}
						}
					}
					
					if (DetailTableBody.size()!=0) {
						noOfSites=DetailTableRows.size();
					}

					else {
						Report.InfoTest(test, "No Records in Detail table");
					}
					
					


					totalMins=totalMins/noOfSites;
					expectedAvgPostRouteHours = Util.convertMinsToHours(totalMins);

					if (AvgPostRouteHours.equals(expectedAvgPostRouteHours)) {
						Report.InfoTest(test, "Avg Post-Route Hours is correct in summary report Actual is : "
								+ AvgPostRouteHours + " and Expected is : " + expectedAvgPostRouteHours);
						Report.PassTest(test, "Avg Post-Route Actual Hours is as expected in Summary report");
					} else {
						Report.FailTest(test, "Avg Post-Route Hours is not as expected in summary report Actual is : "
								+ AvgPostRouteHours + " and Expected is : " + expectedAvgPostRouteHours);
					}
				} catch (Exception e) {
					Report.FailTest(test, "Avg Post-Route Hours is not as expected in summary report Actual is : "
							+ AvgPostRouteHours + " and Expected is : " + expectedAvgPostRouteHours);
				}
		
	}

    private void validateAvgDispCycleHours(Map<String, List<String>> actualSummaryTable, String lOB) {
		// AvgDisp Cycle=sum of Disp Cycle hours for all sites/No of Sites
				String AvgDispCycleHours = null;
				String expectedAvgDispCycleHours= null;
				int noOfSites=0;
				int totalMins = 0;

				try {
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().contains("Avg Disp Cycle (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									AvgDispCycleHours = null;
								} else {
									AvgDispCycleHours = entry.getValue().get(i).toString();
									Util.validateTimeFormat(AvgDispCycleHours, "Avg Disp Cycle Hours in Summary Section");
								}
							}
						}
					}

					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().contains("Total Disp Cycle (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (!entry.getValue().get(i).equals("--")) {
									String[] split = entry.getValue().get(i).split(":", 2);
									int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
									totalMins += mins;
								}
							}
						}
					}
					
					if (DetailTableBody.size()!=0) {
						noOfSites=DetailTableRows.size();
					}

					else {
						Report.InfoTest(test, "No Records in Detail table");
					}
					
					


					totalMins=totalMins/noOfSites;
					expectedAvgDispCycleHours = Util.convertMinsToHours(totalMins);

					if (AvgDispCycleHours.equals(expectedAvgDispCycleHours)) {
						Report.InfoTest(test, "Avg Disp Cycle Hours is correct in summary report Actual is : "
								+ AvgDispCycleHours + " and Expected is : " + expectedAvgDispCycleHours);
						Report.PassTest(test, "Avg Disp Cycle Actual Hours is as expected in Summary report");
					} else {
						Report.FailTest(test, "Avg Disp Cycle Hours is not as expected in summary report Actual is : "
								+ AvgDispCycleHours + " and Expected is : " + expectedAvgDispCycleHours);
					}
				} catch (Exception e) {
					Report.FailTest(test, "Avg Disp Cycle Hours is not as expected in summary report Actual is : "
							+ AvgDispCycleHours + " and Expected is : " + expectedAvgDispCycleHours);
				}
		
	}

    private void validateAvgDowntimeHours(Map<String, List<String>> actualSummaryTable, String lOB) {
		// AvgDowntime=sum Downtime hours for all sites/No of Sites
				String AvgDownTimeHours = null;
				String expectedAvgDownTimeHours= null;
				int noOfSites=0;
				int totalMins = 0;

				try {
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().contains("Avg Downtime (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									AvgDownTimeHours = null;
								} else {
									AvgDownTimeHours = entry.getValue().get(i).toString();
									Util.validateTimeFormat(AvgDownTimeHours, "Avg Disp Cycle Hours in Summary Section");
								}
							}
						}
					}

					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().contains("Total Downtime (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (!entry.getValue().get(i).equals("--")) {
									String[] split = entry.getValue().get(i).split(":", 2);
									int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
									totalMins += mins;
								}
							}
						}
					}
					
					if (DetailTableBody.size()!=0) {
						noOfSites=DetailTableRows.size();
					}

					else {
						Report.InfoTest(test, "No Records in Detail table");
					}
					
					


					totalMins=totalMins/noOfSites;
					expectedAvgDownTimeHours = Util.convertMinsToHours(totalMins);

					if (AvgDownTimeHours.equals(expectedAvgDownTimeHours)) {
						Report.InfoTest(test, "Avg Downtime Hours is correct in summary report Actual is : "
								+ AvgDownTimeHours + " and Expected is : " + expectedAvgDownTimeHours);
						Report.PassTest(test, "Avg Downtime Actual Hours is as expected in Summary report");
					} else {
						Report.FailTest(test, "Avg Downtime Hours is not as expected in summary report Actual is : "
								+ AvgDownTimeHours + " and Expected is : " + expectedAvgDownTimeHours);
					}
				} catch (Exception e) {
					Report.FailTest(test, "Avg Downtime Hours is not as expected in summary report Actual is : "
							+ AvgDownTimeHours + " and Expected is : " + expectedAvgDownTimeHours);
				}
		
	}

    private void validateAvgNetIdleHours(Map<String, List<String>> actualSummaryTable, String lOB) {
		// AvgNet Idle=sum Net Idle hours for all sites/No of Sites
				String AvgNetIdleHours = null;
				String expectedAvgNetIdleHours= null;
				int noOfSites=0;
				int totalMins = 0;

				try {
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().contains("Avg Net Idle (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									AvgNetIdleHours = null;
								} else {
									AvgNetIdleHours = entry.getValue().get(i).toString();
									Util.validateTimeFormat(AvgNetIdleHours, "Avg Net Idle Hours in Summary Section");
								}
							}
						}
					}

					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().contains("Total Net Idle (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (!entry.getValue().get(i).equals("--")) {
									String[] split = entry.getValue().get(i).split(":", 2);
									int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
									totalMins += mins;
								}
							}
						}
					}
					
					if (DetailTableBody.size()!=0) {
						noOfSites=DetailTableRows.size();
					}

					else {
						Report.InfoTest(test, "No Records in Detail table");
					}
					
					totalMins=totalMins/noOfSites;
					expectedAvgNetIdleHours = Util.convertMinsToHours(totalMins);

					if (AvgNetIdleHours.equals(expectedAvgNetIdleHours)) {
						Report.InfoTest(test, "Avg Net Idle Hours is correct in summary report Actual is : "
								+ AvgNetIdleHours + " and Expected is : " + expectedAvgNetIdleHours);
						Report.PassTest(test, "Avg Net Idle Actual Hours is as expected in Summary report");
					} else {
						Report.FailTest(test, "Avg Net Idle Hours is not as expected in summary report Actual is : "
								+ AvgNetIdleHours + " and Expected is : " + expectedAvgNetIdleHours);
					}
				} catch (Exception e) {
					Report.FailTest(test, "Avg Net Idle Hours is not as expected in summary report Actual is : "
							+ AvgNetIdleHours + " and Expected is : " + expectedAvgNetIdleHours);
				}
		
	}

    private void validateAvgMealHours(Map<String, List<String>> actualSummaryTable, String lOB) {
		// AvgMeal=sum oh Meal hours for all sites/No of Sites
				String AvgMealHours = null;
				String expectedAvgMealHours= null;
				int noOfSites=0;
				int totalMins = 0;

				try {
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().contains("Avg Meal (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									AvgMealHours = null;
								} else {
									AvgMealHours = entry.getValue().get(i).toString();
									Util.validateTimeFormat(AvgMealHours, "Avg Meal Hours in Summary Section");
								}
							}
						}
					}

					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().contains("Total Meal (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (!entry.getValue().get(i).equals("--")) {
									String[] split = entry.getValue().get(i).split(":", 2);
									int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
									totalMins += mins;
								}
							}
						}
					}
					
					if (DetailTableBody.size()!=0) {
						noOfSites=DetailTableRows.size();
					}

					else {
						Report.InfoTest(test, "No Records in Detail table");
					}
					
					totalMins=totalMins/noOfSites;
					expectedAvgMealHours = Util.convertMinsToHours(totalMins);

					if (AvgMealHours.equals(expectedAvgMealHours)) {
						Report.InfoTest(test, "Avg MealHours is correct in summary report Actual is : "
								+ AvgMealHours + " and Expected is : " + expectedAvgMealHours);
						Report.PassTest(test, "Avg Meal Actual Hours is as expected in Summary report");
					} else {
						Report.FailTest(test, "Avg Meal Hours is not as expected in summary report Actual is : "
								+ AvgMealHours + " and Expected is : " + expectedAvgMealHours);
					}
				} catch (Exception e) {
					Report.FailTest(test, "Avg Meal Hours is not as expected in summary report Actual is : "
							+ AvgMealHours + " and Expected is : " + expectedAvgMealHours);
				}
		
	}

    private void validateDriverAvgMiles(Map<String, List<String>> actualSummaryTable, String lOB) {
		//[Total Miles/No of Sites]
		
		int AvgMiles = 0;
		int noOfSites=0;
		int expectedAvgMiles= 0;
		int TotalMiles= 0;

		DecimalFormat formatter = new DecimalFormat("#0.00");
		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Avg Miles")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							AvgMiles = 0;
						} else {
							AvgMiles = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(TotalMiles, "Avg Miles in Summary Section");
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Miles")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							TotalMiles = 0;
						} else {
							TotalMiles = Integer.parseInt(entry.getValue().get(i));
							
						}
					}
				}
			}
			
			if (DetailTableBody.size()!=0) {
				noOfSites=DetailTableRows.size();
			}

			else {
				Report.InfoTest(test, "No Records in Detail table");
			}

			
			expectedAvgMiles = TotalMiles/noOfSites;			
			if (Util.compareNumber(AvgMiles, expectedAvgMiles)) {
				Report.InfoTest(test,
						"Avg Miles is correct in summary report Actual is : "
								+ formatter.format(AvgMiles) + " and Expected is : "
								+ formatter.format(expectedAvgMiles));
				Report.PassTest(test, "Avg Miles is as expected in Summary report");
			} else {
				Report.FailTest(test, "Avg Miles is not as expected in summary report Actual is : "
						+ AvgMiles + " and Expected is : " + expectedAvgMiles);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Avg Miles is not as expected in summary report Actual is : "
					+ AvgMiles + " and Expected is : " + expectedAvgMiles);
		}
		
	}

    	private void validateDetailEffVar(Map<String, List<String>> actualSummaryTable, String lOB) {
		
	
	// [(Sum of Actual Units - Sum Of Plan Units) / (Plan Efficiency)] in minutes*60 + [Sum of Plan Drvr Hours - Sum of Act  Drvr Hours]
	//[(actualUnits-planUnits)/(planUnits)]* 60 + [planDriverHours-actualDriverHours]
	
	            List<Double> actualEffVariance = new ArrayList<>();
				List<Double> actualUnits= new ArrayList<>();
				List<Double> planUnits= new ArrayList<>();
				List<String> planDriverHours=new ArrayList<>();
				List<String> actualDriverHours=new ArrayList<>();
				List<String> DriverPlanEff=new ArrayList<>();
				double expectedEffVariance = 0.00;
				int noOfSites=0;
				DecimalFormat formatter = new DecimalFormat("#0.00");
				try {
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("Eff Var (in hrs)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualEffVariance.add(0.00);
								} else {
									actualEffVariance.add(Double.parseDouble(entry.getValue().get(i)));
									// Validate Decimal Format for the value : should
									// have 2 decimal places
									Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, "Actual Units in Detail section");
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("Actual Units")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualUnits.add(0.00);
								} else {
									actualUnits.add(Double.parseDouble(entry.getValue().get(i)));
									// Validate Decimal Format for the value : should
									// have 2 decimal places
									Util.validateFieldDecimalPlaces(entry.getValue().get(i), 1, "Actual Units in Detail section");
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("Plan Units)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									planUnits.add(0.00);
								} else {
									planUnits.add(Double.parseDouble(entry.getValue().get(i)));
									// Validate Decimal Format for the value : should
									// have 2 decimal places
									Util.validateFieldDecimalPlaces(entry.getValue().get(i), 1, "Plan Units in Detail section");
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().contains("Plan Driver Hours (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									planDriverHours.add("00:00");
								} else {
									planDriverHours.add(entry.getValue().get(i).toString());
									
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().contains("Actual Driver Hours (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualDriverHours.add("00:00");
								} else {
									actualDriverHours.add(entry.getValue().get(i).toString());

								}
							}
						}
					}

					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("Drv Plan Eff")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									planUnits.add(0.0);
								} else {
									planUnits.add(Double.parseDouble(entry.getValue().get(i)));
								}
							}
						}
					}
					
					
					
					List<Double> planHoursInNumeric=new ArrayList<>();
					List<Double> actualHourInNumeric=new ArrayList<>();
					
					for(int i=0;i<planDriverHours.size();i++)
					{
						planHoursInNumeric.add(Util.convertTimeToHours(planDriverHours.get(i)));
					}
					
					for(int i=0;i<actualDriverHours.size();i++)
					{
						actualHourInNumeric.add(Util.convertTimeToHours(actualDriverHours.get(i)));
					}

				
				
				for(int i=0;i<noOfSites;i++)
				{
					expectedEffVariance = ((actualUnits.get(i)-planUnits.get(i))/(planUnits.get(i) * 60))+(planHoursInNumeric.get(i)-actualHourInNumeric.get(i));
					
					if (Util.compareNumber(actualEffVariance.get(i), expectedEffVariance)) {
						Report.InfoTest(test,
								"Efficiency Variance is correct in detail section of Route Summary report Actual is : "
										+ formatter.format(actualEffVariance.get(i)) + " and Expected is : "
										+ formatter.format(expectedEffVariance));
						Report.PassTest(test, "Efficiency Variance is as expected in detail section of Route Summary report");
					} else {
						Report.FailTest(test, "Efficiency Variance is not as expected in detail section of Route Summary report Actual is : "
								+ actualEffVariance.get(i) + " and Expected is : " + expectedEffVariance);
					}
				} 
			}catch (Exception e) {
					Report.FailTest(test, "Efficiency Variance is not as expected in detail section of Route Summary report Actual is : "
							+ actualEffVariance + " and Expected is : " + expectedEffVariance);
				}
		
	}
    
    	public void validateRouteSummaryDetailSection(String SiteID,Map<String,List<String>> actualDetailTable,Map<String,List<String>> efficiencySummaryComTableData,
    			Map<String,List<String>> efficiencyPerformanceComPerformanceTableData,Map<String,List<String>> efficiencyPerformanceComRouteSegmentTableData,
    			Map<String,List<String>>sequenceComplianceComTableData)
    	{           
    		//Tier - DB Query
    		validateTier(SiteID,actualDetailTable);
    		//Area - DB Query
    		validateArea(SiteID,actualDetailTable);
    		//BU - DB Query
    		validateBU(SiteID,actualDetailTable);
    		//Site - DB Query
    		//Eff Var - from Efficiency Summary
    		validateEffVarInHrs(actualDetailTable,efficiencySummaryComTableData);
    		//Driver and Helper Eff - From Efficiency Summary
    		validateDriverAndHelperEffVarInHrs(actualDetailTable,efficiencySummaryComTableData);
    		//Unit Var - Eff Perfomance - Summary Section
    		validateUnitVar(actualDetailTable,efficiencySummaryComTableData);
    		//Pre-Route - Route Segement subview of efficiency Performance
    		validatePreRouteHM(actualDetailTable,efficiencyPerformanceComRouteSegmentTableData);
    		//post-Route - Post-Route Detail - from Detail section sum of all post route time
    		validatePostRouteHM(actualDetailTable,efficiencyPerformanceComRouteSegmentTableData);
    		//Disp Cycle - Eff perf - Route Segment Disp Load section - Sum of Disposal Cycle h:m
    		validateDisposalCycleHM(actualDetailTable,efficiencyPerformanceComRouteSegmentTableData);
    		//Downtime - Eff perf - Route Segment section - Sum of downtime
    		validateDowntimeHM(actualDetailTable,efficiencyPerformanceComRouteSegmentTableData);
    		//Net Idle - Route Segment
    		validateNetIdleTimeHM(actualDetailTable,efficiencyPerformanceComRouteSegmentTableData);
    		//Meal - Route Segment
    		validateMealHM(actualDetailTable,efficiencyPerformanceComRouteSegmentTableData);
    		//In Seq - Performance section
    		validateInSeqPercent(actualDetailTable,sequenceComplianceComTableData);
    		//miles - eff Summary - Summary Section
    		validateMiles(actualDetailTable,efficiencySummaryComTableData);
    		
    	}
    	
    	
    	public void validateTier(String SiteID,Map<String,List<String>>actualDetailTable)
    	{
    		String actualTier = null;
    		List<String> expectedTier = null;
    		try
    		{
    			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
					if (entry.getKey().equals("Tier")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								actualTier = null;
							} else {
								actualTier = entry.getValue().get(i);
							}
						}
					}
				}
    			//expectedTier = Util.getDataFromDB("query to get the tier");
    			
    			if ((actualTier.equals("North"))||(actualTier.equals("South")))    			
    			{
    				
    				Report.PassTest(test, "Tier is same as expected - " + actualTier);
    			}
    			else
    			{
    				Report.FailTest(test, "Tier is not same as expected. Actual -  " + actualTier);
    			}
    			
    		}
    		catch (Exception e)
    		{
    			System.out.println(e.getMessage());
    		}
    	}
    	
    	public void validateArea(String SiteID,Map<String,List<String>>actualDetailTable)
    	{
    		String actualAreaName=null;
    		List<String> expectedAreaName = null;
    		String siteID=SiteID.substring(0, 6);
    		try {
    			
    			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
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
    					Report.InfoTest(test, "Area is correct in Route Summary report Detail Section. Actual is : "
    							+ actualAreaName + " and Expected is : " + expectedAreaName.get(0));
    					Report.PassTest(test, "Area is as expected in Route Summary report Detail Section");
    				} else {
    					Report.FailTest(test, "Area is not as expected in Route Summary report Detail Section. Actual is : " + actualAreaName
    							+ " and Expected is : " + expectedAreaName.get(0));
    				}
    			
    		} catch (Exception e) {
    			Report.FailTest(test, "Area is not as expected in Route Summary report Detail Section. Actual is : " + actualAreaName
    					+ " and Expected is : " + expectedAreaName.get(0));
    		}
    	}
    	
    	public void validateBU(String SiteID,Map<String,List<String>>actualDetailTable)
    	{
    		String actualBUName=null;
    		List<String> expectedBUName = null;
    		String siteID=SiteID.substring(0, 6);
    		try {
    			
    			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
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
    					Report.InfoTest(test, "BU is correct in Route Summary report Detail Section. Actual is : "
    							+ actualBUName + " and Expected is : " + expectedBUName.get(0));
    					Report.PassTest(test, "BU is as expected in Route Summary report Detail Section");
    				} else {
    					Report.FailTest(test, "BU is not as expected in Route Summary report Detail Section. Actual is : " + actualBUName
    							+ " and Expected is : " + expectedBUName.get(0));
    				}
    			
    		} catch (Exception e) {
    			Report.FailTest(test, "BU is not as expected in Route Summary report Detail Section. Actual is : " + actualBUName
    					+ " and Expected is : " + expectedBUName.get(0));
    		}
    	}
    	
    	public void validateEffVarInHrs(Map<String,List<String>> actualDetailTable,Map<String,List<String>>efficiencySummaryComTableData)
    	{
    		List<Double> actualEffVarInHours = new ArrayList<>();
    		List<Double> expectedEffVarInHours = new ArrayList<>();
    		try
    		{
    			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
					if (entry.getKey().equals("Eff Var (in hrs)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								actualEffVarInHours.add(0.00);
							} else {
								actualEffVarInHours.add(Double.parseDouble(entry.getValue().get(i)));
								// Validate Decimal Format for the value : should
								// have 2 decimal places
								Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2,"Eff Var (in hrs)");
							}
						}
					}
				}
    			for (Entry<String, List<String>> entry : efficiencySummaryComTableData.entrySet()) {
					if (entry.getKey().equals("Driver Total Eff Var (in hrs)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								expectedEffVarInHours.add(0.00);
							} else {
								expectedEffVarInHours.add(Double.parseDouble(entry.getValue().get(i)));
								
							}
						}
					}
				}
    			if(actualEffVarInHours.equals(expectedEffVarInHours))
    			{
    				Report.PassTest(test, "Eff Var in Hours for Route Summary - "+actualEffVarInHours+" is same as in Efficiency Summary " + expectedEffVarInHours ); 
    				System.out.println("Eff Var in Hours for Route Summary - "+actualEffVarInHours+" is same as in Efficiency Summary " + expectedEffVarInHours);
    			}	
    		
	    		else
	    		{
	    			Report.FailTest(test, "Eff Var in Hours for Route Summary - "+actualEffVarInHours+" is NOT same as in Efficiency Summary " + expectedEffVarInHours );
	    			System.out.println("Eff Var in Hours for Route Summary - "+actualEffVarInHours+" is NOT same as in Efficiency Summary " + expectedEffVarInHours);
	    		}
	    		
	    	}
    		catch (Exception e)
    		{
    			System.out.println(e.getMessage());
    		}
    	}
    	
    	public void validateDriverAndHelperEffVarInHrs(Map<String,List<String>> actualDetailTable,Map<String,List<String>>efficiencySummaryComTableData)
    	{
    		List<Double> actualDriverAndHelperEffVarInHours = new ArrayList<>();
    		List<Double> expectedDriverAndHelperEffVarInHours = new ArrayList<>();
    		try
    		{
    			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
					if (entry.getKey().equals("Driver & Helper Eff Var (in hrs)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								actualDriverAndHelperEffVarInHours.add(0.00);
							} else {
								actualDriverAndHelperEffVarInHours.add(Double.parseDouble(entry.getValue().get(i)));
								// Validate Decimal Format for the value : should
								// have 2 decimal places
								Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2,"Driver & Helper Eff Var (in hrs)");
							}
						}
					}
				}
    			for (Entry<String, List<String>> entry : efficiencySummaryComTableData.entrySet()) {
					if (entry.getKey().equals("Driver & Helper Total Eff Var (in hrs)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								expectedDriverAndHelperEffVarInHours.add(0.00);
							} else {
								expectedDriverAndHelperEffVarInHours.add(Double.parseDouble(entry.getValue().get(i)));
								
							}
						}
					}
				}
    			if(actualDriverAndHelperEffVarInHours.equals(expectedDriverAndHelperEffVarInHours))
    			{
    				Report.PassTest(test, "Driver and Helper Eff Var in Hours for Route Summary - "+actualDriverAndHelperEffVarInHours+" is same as in Efficiency Summary " + expectedDriverAndHelperEffVarInHours ); 
    				System.out.println("Driver and Helper Eff Var in Hours for Route Summary - "+actualDriverAndHelperEffVarInHours+" is same as in Efficiency Summary " + expectedDriverAndHelperEffVarInHours);
    			}	
    		
	    		else
	    		{
	    			Report.FailTest(test, "Driver and Helper Eff Var in Hours for Route Summary - "+actualDriverAndHelperEffVarInHours+" is NOT same as in Efficiency Summary " + expectedDriverAndHelperEffVarInHours );
	    			System.out.println("Driver and Helper Eff Var in Hours for Route Summary - "+actualDriverAndHelperEffVarInHours+" is NOT same as in Efficiency Summary " + expectedDriverAndHelperEffVarInHours);
	    		}
	    		
	    	}
    		catch (Exception e)
    		{
    			System.out.println(e.getMessage());
    		}
    	}

    	public void validateUnitVar(Map<String,List<String>> actualDetailTable,Map<String,List<String>>efficiencySummaryComTableData)
    	{
    		List<Double> actualUnitVar = new ArrayList<>();
    		List<Double> expectedActualUnits = new ArrayList<>();
    		List<Double> expectedPlanUnits = new ArrayList<>();
    		Double expectedUnitVar = 0.0;
    		try
    		{
    			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
					if (entry.getKey().equals("Units Var")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								actualUnitVar.add(0.0);
							} else {
								actualUnitVar.add(Double.parseDouble(entry.getValue().get(i)));
								// Validate Decimal Format for the value : should
								// have 2 decimal places
								Util.validateFieldDecimalPlaces(entry.getValue().get(i), 1,"Units Var");
							}
						}
					}
				}
    			for (Entry<String, List<String>> entry : efficiencySummaryComTableData.entrySet()) {
					if (entry.getKey().equals("Driver Total Actual Units")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								expectedActualUnits.add(0.00);
							} else {
								expectedActualUnits.add(Double.parseDouble(entry.getValue().get(i)));
								
							}
						}
					}
				}
    			
    			for (Entry<String, List<String>> entry : efficiencySummaryComTableData.entrySet()) {
					if (entry.getKey().equals("Driver Total Plan Units")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								expectedPlanUnits.add(0.00);
							} else {
								expectedPlanUnits.add(Double.parseDouble(entry.getValue().get(i)));
								
							}
						}
					}
				}
    			
    			expectedUnitVar = expectedActualUnits.get(0) - expectedPlanUnits.get(0);
    			if(actualUnitVar.get(0).equals(expectedUnitVar))
    			{
    				Report.PassTest(test, "Unit Var for Route Summary - "+actualUnitVar.get(0)+" is same as in Efficiency Summary " + expectedUnitVar ); 
    				System.out.println("Unit Var for Route Summary - "+actualUnitVar.get(0)+" is same as in Efficiency Summary " + expectedUnitVar);
    			}	
    		
	    		else
	    		{
	    			Report.FailTest(test, "Unit Var for Route Summary - "+actualUnitVar.get(0)+" is NOT same as in Efficiency Summary " + expectedUnitVar );
	    			System.out.println("Unit Var for Route Summary - "+actualUnitVar.get(0)+" is NOT same as in Efficiency Summary " + expectedUnitVar);
	    		}
	    		
	    	}
    		catch (Exception e)
    		{
    			System.out.println(e.getMessage());
    		}
    	}

    	public void validatePreRouteHM(Map<String,List<String>> actualDetailTable,Map<String,List<String>>efficiencyPerformanceComRouteSegmentTableData)
    	{
    		// TotalPreRoute=sum of actual PreRoute hours for all sites
    		String actualPreRouteHM = null;
    		String expectedPreRouteHM = null;
    		int totalMins = 0;

    		try {
    			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
    				if (entry.getKey().contains("Pre-Route (h:m)" )) {
    					for (int i = 0; i < entry.getValue().size(); i++) {
    						if (entry.getValue().get(i).equals("--")) {
    							actualPreRouteHM = "00:00";
    						} else {
    							actualPreRouteHM = entry.getValue().get(i).toString();
    							Util.validateTimeFormat(actualPreRouteHM, "Pre-Route (h:m)");
    						}
    					}
    				}
    			}

    			for (Entry<String, List<String>> entry : efficiencyPerformanceComRouteSegmentTableData.entrySet()) {
    				if (entry.getKey().contains("Pre-Route (h:m)")) {
    					for (int i = 0; i < entry.getValue().size(); i++) {
    						if (!entry.getValue().get(i).equals("--")) {
    							String[] split = entry.getValue().get(i).split(":", 2);
    							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
    							totalMins += mins;
    						}

    					}
    				}
    			}

    			expectedPreRouteHM = Util.convertMinsToHours(totalMins);

    			if (actualPreRouteHM.equals(expectedPreRouteHM)) {
    				Report.InfoTest(test, "Total Pre Route Hours is correct in summary report Actual is : "
    						+ actualPreRouteHM + " and Expected is : " + expectedPreRouteHM);
    				Report.PassTest(test, "Total Pre Route Hours is as expected in Summary report");
    			} else {
    				Report.FailTest(test, "Total Pre Route Hours is not as expected in summary report Actual is : "
    						+ actualPreRouteHM + " and Expected is : " + expectedPreRouteHM);
    			}
    		} catch (Exception e) {
    			Report.FailTest(test, "Total Pre Route Hours is not as expected in summary report Actual is : "
    					+ actualPreRouteHM + " and Expected is : " + expectedPreRouteHM);
    		}
    	}
    	
    	public void validatePostRouteHM(Map<String,List<String>> actualDetailTable,Map<String,List<String>>efficiencyPerformanceComRouteSegmentTableData)
    	{
    		// TotalPostRoute=sum of actual PreRoute hours for all sites
    		String actualPostRouteHM = null;
    		String expectedPostRouteHM = null;
    		int totalMins = 0;

    		try {
    			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
    				if (entry.getKey().contains("Post-Route (h:m)" )) {
    					for (int i = 0; i < entry.getValue().size(); i++) {
    						if (entry.getValue().get(i).equals("--")) {
    							actualPostRouteHM = "00:00";
    						} else {
    							actualPostRouteHM = entry.getValue().get(i).toString();
    							Util.validateTimeFormat(actualPostRouteHM, "Post-Route (h:m)");
    						}
    					}
    				}
    			}

    			for (Entry<String, List<String>> entry : efficiencyPerformanceComRouteSegmentTableData.entrySet()) {
    				if (entry.getKey().contains("Post-Route (h:m)")) {
    					for (int i = 0; i < entry.getValue().size(); i++) {
    						if (!entry.getValue().get(i).equals("--")) {
    							String[] split = entry.getValue().get(i).split(":", 2);
    							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
    							totalMins += mins;
    						}

    					}
    				}
    			}

    			expectedPostRouteHM = Util.convertMinsToHours(totalMins);

    			if (actualPostRouteHM.equals(expectedPostRouteHM)) {
    				Report.InfoTest(test, "Total Post Route Hours is correct in summary report Actual is : "
    						+ actualPostRouteHM + " and Expected is : " + expectedPostRouteHM);
    				Report.PassTest(test, "Total Post Route Hours is as expected in Summary report");
    			} else {
    				Report.FailTest(test, "Total Post Route Hours is not as expected in summary report Actual is : "
    						+ actualPostRouteHM + " and Expected is : " + expectedPostRouteHM);
    			}
    		} catch (Exception e) {
    			Report.FailTest(test, "Total Post Route Hours is not as expected in summary report Actual is : "
    					+ actualPostRouteHM + " and Expected is : " + expectedPostRouteHM);
    		}
    	}
    	
    	public void validateDisposalCycleHM(Map<String,List<String>> actualDetailTable,Map<String,List<String>>efficiencyPerformanceComRouteSegmentTableData)
    	{
    		// TotalPostRoute=sum of actual PreRoute hours for all sites
    		String actualDispCycleHM = null;
    		String expectedDispCycleHM = null;
    		int totalMins = 0;

    		try {
    			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
    				if (entry.getKey().contains("Disp Cycle (h:m)" )) {
    					for (int i = 0; i < entry.getValue().size(); i++) {
    						if (entry.getValue().get(i).equals("--")) {
    							actualDispCycleHM = null;
    						} else {
    							actualDispCycleHM = entry.getValue().get(i).toString();
    							Util.validateTimeFormat(actualDispCycleHM, "Disp Cycle (h:m)");
    						}
    					}
    				}
    			}

    			for (Entry<String, List<String>> entry : efficiencyPerformanceComRouteSegmentTableData.entrySet()) {
    				if (entry.getKey().equals("Disp Cycle (h:m)")) {
    					for (int i = 0; i < entry.getValue().size(); i++) {
    						if (!entry.getValue().get(i).equals("--")) {
    							String[] split = entry.getValue().get(i).split(":", 2);
    							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
    							totalMins += mins;
    						}

    					}
    				}
    			}

    			expectedDispCycleHM = Util.convertMinsToHours(totalMins);

    			if (actualDispCycleHM.equals(expectedDispCycleHM)) {
    				Report.InfoTest(test, "Total Disposal Cycle time Hours is correct in summary report Actual is : "
    						+ actualDispCycleHM + " and Expected is : " + expectedDispCycleHM);
    				Report.PassTest(test, "Total Disposal Cycle time Hours is as expected in Summary report");
    			} else {
    				Report.FailTest(test, "Total Disposal Cycle time Hours is not as expected in summary report Actual is : "
    						+ actualDispCycleHM + " and Expected is : " + expectedDispCycleHM);
    			}
    		} catch (Exception e) {
    			Report.FailTest(test, "Total Disposal Cycle time Hours is not as expected in summary report Actual is : "
    					+ actualDispCycleHM + " and Expected is : " + expectedDispCycleHM);
    		}
    	}
    	
    	public void validateDowntimeHM(Map<String,List<String>> actualDetailTable,Map<String,List<String>>efficiencyPerformanceComRouteSegmentTableData)
    	{
    		// TotalPostRoute=sum of actual PreRoute hours for all sites
    		String actualDowntimeHM = null;
    		String expectedDowntimeHM = null;
    		int totalMins = 0;

    		try {
    			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
    				if (entry.getKey().contains("Down time (h:m)" )) {
    					for (int i = 0; i < entry.getValue().size(); i++) {
    						if (entry.getValue().get(i).equals("--")) {
    							actualDowntimeHM = null;
    						} else {
    							actualDowntimeHM = entry.getValue().get(i).toString();
    							Util.validateTimeFormat(actualDowntimeHM, "Down time (h:m)");
    						}
    					}
    				}
    			}

    			for (Entry<String, List<String>> entry : efficiencyPerformanceComRouteSegmentTableData.entrySet()) {
    				if (entry.getKey().equals("Downtime (h:m)")) {
    					for (int i = 0; i < entry.getValue().size(); i++) {
    						if (!entry.getValue().get(i).equals("--")) {
    							String[] split = entry.getValue().get(i).split(":", 2);
    							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
    							totalMins += mins;
    						}

    					}
    				}
    			}

    			expectedDowntimeHM = Util.convertMinsToHours(totalMins);

    			if (actualDowntimeHM.equals(expectedDowntimeHM)) {
    				Report.InfoTest(test, "Total Down time Hours is correct in summary report Actual is : "
    						+ actualDowntimeHM + " and Expected is : " + expectedDowntimeHM);
    				Report.PassTest(test, "Total Down time Hours is as expected in Summary report");
    			} else {
    				Report.FailTest(test, "Total Down time Hours is not as expected in summary report Actual is : "
    						+ actualDowntimeHM + " and Expected is : " + expectedDowntimeHM);
    			}
    		} catch (Exception e) {
    			Report.FailTest(test, "Total Down time Hours is not as expected in summary report Actual is : "
    					+ actualDowntimeHM + " and Expected is : " + expectedDowntimeHM);
    		}
    	}

    	public void validateNetIdleTimeHM(Map<String,List<String>> actualDetailTable,Map<String,List<String>>efficiencyPerformanceComRouteSegmentTableData)
    	{
    		// TotalPostRoute=sum of actual PreRoute hours for all sites
    		String actualIdleTimeHM = null;
    		String expectedIdleTimeHM = null;
    		int totalMins = 0;

    		try {
    			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
    				if (entry.getKey().contains("Net Idle (h:m)" )) {
    					for (int i = 0; i < entry.getValue().size(); i++) {
    						if (entry.getValue().get(i).equals("--")) {
    							actualIdleTimeHM = null;
    						} else {
    							actualIdleTimeHM = entry.getValue().get(i).toString();
    							Util.validateTimeFormat(actualIdleTimeHM, "Net Idle (h:m)");
    						}
    					}
    				}
    			}

    			for (Entry<String, List<String>> entry : efficiencyPerformanceComRouteSegmentTableData.entrySet()) {
    				if (entry.getKey().equals("Net Idle (h:m)")) {
    					for (int i = 0; i < entry.getValue().size(); i++) {
    						if (!entry.getValue().get(i).equals("--")) {
    							String[] split = entry.getValue().get(i).split(":", 2);
    							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
    							totalMins += mins;
    						}

    					}
    				}
    			}

    			expectedIdleTimeHM = Util.convertMinsToHours(totalMins);

    			if (actualIdleTimeHM.equals(expectedIdleTimeHM)) {
    				Report.InfoTest(test, "Net Idle time Hours is correct in summary report Actual is : "
    						+ actualIdleTimeHM + " and Expected is : " + expectedIdleTimeHM);
    				Report.PassTest(test, "Net Idle time Hours is as expected in Summary report");
    			} else {
    				Report.FailTest(test, "Net Idle time Hours is not as expected in summary report Actual is : "
    						+ actualIdleTimeHM + " and Expected is : " + expectedIdleTimeHM);
    			}
    		} catch (Exception e) {
    			Report.FailTest(test, "Net Idle time Hours is not as expected in summary report Actual is : "
    					+ actualIdleTimeHM + " and Expected is : " + expectedIdleTimeHM);
    		}
    	}

    	public void validateMealHM(Map<String,List<String>> actualDetailTable,Map<String,List<String>>efficiencyPerformanceComRouteSegmentTableData)
    	{
    		// TotalPostRoute=sum of actual PreRoute hours for all sites
    		String actualMealHM = null;
    		String expectedMealHM = null;
    		int totalMins = 0;

    		try {
    			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
    				if (entry.getKey().contains("Meal (h:m)" )) {
    					for (int i = 0; i < entry.getValue().size(); i++) {
    						if (entry.getValue().get(i).equals("--")) {
    							actualMealHM = null;
    						} else {
    							actualMealHM = entry.getValue().get(i).toString();
    							Util.validateTimeFormat(actualMealHM, "Meal (h:m)");
    						}
    					}
    				}
    			}

    			for (Entry<String, List<String>> entry : efficiencyPerformanceComRouteSegmentTableData.entrySet()) {
    				if (entry.getKey().equals("Meal (h:m)")) {
    					for (int i = 0; i < entry.getValue().size(); i++) {
    						if (!entry.getValue().get(i).equals("--")) {
    							String[] split = entry.getValue().get(i).split(":", 2);
    							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
    							totalMins += mins;
    						}

    					}
    				}
    			}

    			expectedMealHM = Util.convertMinsToHours(totalMins);

    			if (actualMealHM.equals(expectedMealHM)) {
    				Report.InfoTest(test, "Meal time is correct in summary report Actual is : "
    						+ actualMealHM + " and Expected is : " + expectedMealHM);
    				Report.PassTest(test, "Meal time is as expected in Summary report");
    			} else {
    				Report.FailTest(test, "Meal time is not as expected in summary report Actual is : "
    						+ actualMealHM + " and Expected is : " + expectedMealHM);
    			}
    		} catch (Exception e) {
    			Report.FailTest(test, "Meal time is not as expected in summary report Actual is : "
    					+ actualMealHM + " and Expected is : " + expectedMealHM);
    		}
    	}

    	public void validateInSeqPercent(Map<String,List<String>> actualDetailTable,Map<String,List<String>>sequenceComplianceComTableData)
    	{
    		int actualInSeq = 0; 
    		int expectedInSeq = 0;
    		try
    		{
    			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
					if (entry.getKey().equals("In Seq %")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								actualInSeq = 0;
							} else {
								actualInSeq = Integer.parseInt(entry.getValue().get(i));
								// Validate Decimal Format for the value : should
								// have 2 decimal places
								Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)),"In Seq %");
							}
						}
					}
				}
    			for (Entry<String, List<String>> entry : sequenceComplianceComTableData.entrySet()) {
					if (entry.getKey().equals("In Seq %")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								expectedInSeq = 0;
							} else {
								expectedInSeq = Integer.parseInt(entry.getValue().get(i));
								
							}
						}
					}
				}
    			if(actualInSeq==expectedInSeq)
    			{
    				Report.PassTest(test, "In Seq % for Route Summary - "+actualInSeq+" is same as in Efficiency Summary " + expectedInSeq ); 
    				System.out.println("In Seq % for Route Summary - "+actualInSeq+" is same as in Efficiency Summary " + expectedInSeq);
    			}	
    		
	    		else
	    		{
	    			Report.FailTest(test, "In Seq % for Route Summary - "+actualInSeq+" is NOT same as in Efficiency Summary " + expectedInSeq );
	    			System.out.println("In Seq % for Route Summary - "+actualInSeq+" is NOT same as in Efficiency Summary " + expectedInSeq);
	    		}
	    		
	    	}
    		catch (Exception e)
    		{
    			System.out.println(e.getMessage());
    		}
    	}

    	public void validateMiles(Map<String,List<String>>actualDetailTable,Map<String,List<String>>efficiencySummaryComTableData)
    	{
    		int actualMiles = 0; 
    		int expectedMiles = 0;
    		try
    		{
    			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
					if (entry.getKey().equals("Miles")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								actualMiles = 0;
							} else {
								actualMiles = Integer.parseInt(entry.getValue().get(i));
								// Validate Decimal Format for the value : should
								// have 2 decimal places
								Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)),"Miles");
							}
						}
					}
				}
    			for (Entry<String, List<String>> entry : efficiencySummaryComTableData.entrySet()) {
					if (entry.getKey().equals("Driver Total Miles")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								expectedMiles = 0;
							} else {
								expectedMiles = Integer.parseInt(entry.getValue().get(i));
								
							}
						}
					}
				}
    			if(actualMiles==expectedMiles)
    			{
    				Report.PassTest(test, "Miles for Route Summary - "+actualMiles+" is same as in Efficiency Summary " + expectedMiles ); 
    				System.out.println("Miles for Route Summary - "+actualMiles+" is same as in Efficiency Summary " + expectedMiles);
    			}	
    		
	    		else
	    		{
	    			Report.FailTest(test, "Miles for Route Summary - "+actualMiles+" is NOT same as in Efficiency Summary " + expectedMiles );
	    			System.out.println("Miles for Route Summary - "+actualMiles+" is NOT same as in Efficiency Summary " + expectedMiles);
	    		}
	    		
	    	}
    		catch (Exception e)
    		{
    			System.out.println(e.getMessage());
    		}
    	}


}//Last  Closing Bracket

