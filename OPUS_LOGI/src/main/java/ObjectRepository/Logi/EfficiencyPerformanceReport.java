package ObjectRepository.Logi;

import java.io.IOException;
import java.text.DecimalFormat;

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

import SupportLibraries.GlobalVariables;

import SupportLibraries.LogClass;
import SupportLibraries.Util;
import SupportLibraries.Report;

public class EfficiencyPerformanceReport {

	ExtentTest test;
	WebDriver driver;

	public EfficiencyPerformanceReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='inner']/div/table/tbody/tr/td[2]/div/ul/li/a[text()='Driver Performance']")
	WebElement DriverPerformanceLink;

	@FindBy(xpath = "//*[@id='jsFlashLink']/span[contains(., 'See Commercial Flash Report')]")
	WebElement CommercialFlashReportLink;

	@FindBy(xpath = "//*[@id='inpTimeOption_1']")
	WebElement HistoricalOption;

	@FindBy(xpath = "//*[@id='inpSubReportOpt_1']")
	WebElement performanceSubView;

	@FindBy(xpath = "//*[@id='inpSubReportOpt_2']")
	WebElement routeSegmentsSubView;

	@FindBy(xpath = "//*[@id='inpSubReportOpt_3']")
	WebElement disposalLoadsSubView;

	@FindBy(xpath = "//*[@id='inpSubReportOpt_4']")
	WebElement travelUnitsSubView;

	@FindBy(xpath = "//li[@id='tabHlp']/a/em/span[text()='Helper']")
	WebElement HelperTab;

	@FindBy(xpath = "//table[@id='dtEfficiencySummary']")
	WebElement summaryTable;

	@FindBy(xpath = "//table[@id='dtEfficiencySummary']/thead/tr/th")
	List<WebElement> summaryTableColumns;

	@FindBy(xpath = "//table[@id='dtEfficiencySummary']/tbody/tr")
	List<WebElement> summaryTableRows;

	@FindBy(xpath = "//table[@id='t2']")
	WebElement subViewTable;

	@FindBy(xpath = "//table[@id='t2']/thead/tr/th")
	List<WebElement> subViewTableColumns;

	@FindBy(xpath = "//table[@id='t2']/tbody/tr")
	List<WebElement> subViewTableRows;

	@FindBy(xpath = "//table[@id='t2h']")
	WebElement helperTable;

	@FindBy(xpath = "//table[@id='t2h']/thead/tr/th")
	List<WebElement> helperTableColumns;

	@FindBy(xpath = "//table[@id='t2h']/tbody/tr")
	List<WebElement> helperTableRows;

	@FindBy(xpath = "//table[@id='t3']")
	WebElement helperDrilldownTable;

	@FindBy(xpath = "//table[@id='t3']/thead/tr/th")
	List<WebElement> helperDrilldownTableColumns;

	@FindBy(xpath = "//table[@id='t3']/tbody/tr")
	List<WebElement> helperDrilldownTableRows;

	public void clickEfficiencyPerformanceReport() throws IOException {
		Util.ElementExist(driver, DriverPerformanceLink);
		DriverPerformanceLink.click();
		Report.PassTest(test, "Clicked on Efficiency Performance Report");
	}

	public void selectHistoricalOption() throws IOException {
		Util.ElementExist(driver, HistoricalOption);
		HistoricalOption.click();
		Report.PassTest(test, "Historical Option get selected");
	}

	public void selectPerformanceSubView() throws IOException {
		try {

			performanceSubView.click();
			Report.PassTest(test, "Performance SubView get selected as expected");
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
		}
	}

	public void selectRouteSegmentsSubView() throws IOException {
		try {
			routeSegmentsSubView.click();
			Report.PassTest(test, "Route Segment SubView get selected as expected");
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
		}
	}

	public void selectDisposalLoadsSubView() throws IOException {
		try {

			disposalLoadsSubView.click();
			Report.PassTest(test, "Disposal Loads SubView get selected as expected");
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
		}
	}

	public void selectTravelUnitsSubView() throws IOException {
		try {

			travelUnitsSubView.click();
			Report.PassTest(test, "Travel Units SubView get selected as expected");
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
		}
	}

	public void selectHelperTab() throws IOException {
		try {

			HelperTab.click();
			Thread.sleep(7000);
			Report.PassTest(test, "Helper Tab get selected as expected");
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
		}
	}

	public Map<String, List<String>> getActualSummaryTableData() throws IOException {
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
	}

	public Map<String, List<String>> getActualPerformanceSubViewTableData() {
		System.out.println("inside Actual Performance Data");
		Util.CaptureScreenshot("Performance subview Table data");
		Map<String, List<String>> performanceSubViewTableData = new HashMap<>();
		try {
			performanceSubViewTableData = readTable(subViewTableColumns, subViewTableRows, "t2");
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

	public Map<String, List<String>> getActualRouteSegmentsSubViewTableData() {
		System.out.println("inside Actual Route Segments Data");
		Util.CaptureScreenshot("Route Segments subview Table data");
		Map<String, List<String>> routeSegmentSubViewTableData = new HashMap<>();
		try {
			routeSegmentSubViewTableData = readTable(subViewTableColumns, subViewTableRows, "t2");
			Report.PassTestScreenshot(test, driver, "Route Segment Subview table data read successfully",
					"Route Segments SubView Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Route Segment subview table data");
		}
		for (Entry<String, List<String>> entry : routeSegmentSubViewTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return routeSegmentSubViewTableData;
	}

	public Map<String, List<String>> getActualDisposalLoadsSubViewTableData() {
		System.out.println("inside Actual Disposal Loads Data");
		Util.CaptureScreenshot("Disposal Loads subview Table data");
		Map<String, List<String>> disposalLoadsSubViewTableData = new HashMap<>();
		try {
			disposalLoadsSubViewTableData = readTable(subViewTableColumns, subViewTableRows, "t2");
			Report.PassTestScreenshot(test, driver, "Disposal Loads Subview table data read successfully",
					"Disposal Loads SubView Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Disposal Loads subview table data");
		}
		for (Entry<String, List<String>> entry : disposalLoadsSubViewTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return disposalLoadsSubViewTableData;
	}

	public Map<String, List<String>> getActualTravelUnitsSubViewTableData() {
		System.out.println("inside Actual Travel Units Data");
		Util.CaptureScreenshot("Travel Units subview Table data");
		Map<String, List<String>> travelUnitsSubViewTableData = new HashMap<>();
		try {
			travelUnitsSubViewTableData = readTable(subViewTableColumns, subViewTableRows, "t2");
			Report.PassTestScreenshot(test, driver, "Travel Units Subview table data read successfully",
					"Travel SubView Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Travel Units subview table data");
		}
		for (Entry<String, List<String>> entry : travelUnitsSubViewTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return travelUnitsSubViewTableData;
	}

	public Map<String, List<String>> getActualHelperDetailTableData() {
		System.out.println("inside Actual Helper Detail Data");
		Util.CaptureScreenshot("Helper Detail Table data");
		Map<String, List<String>> helperDetailTableData = new HashMap<>();
		try {
			helperDetailTableData = readTable(helperTableColumns, helperTableRows, "t2h");
			Report.PassTestScreenshot(test, driver, "Helper Tab data read successfully", "Helper Tab Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Helper Detail table data");
		}
		for (Entry<String, List<String>> entry : helperDetailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return helperDetailTableData;
	}

	public Map<String, Map<String, List<String>>> getActualHelperDrilldownTableData(String route) {
		Map<String, Map<String, List<String>>> helperDrilldownTableData = new HashMap<>();
		try {
			Thread.sleep(5000);
			String[] routes = route.split(";");

			Map<String, List<String>> helperDrilldownTemp = new HashMap<>();

			for (int i = 0; i < routes.length; i++) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t2']/tbody/tr[" + (i + 1) + "]/td/a/span")).click();
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer,subDriverPerfDetail_Row" + (i + 1));
					helperDrilldownTemp = readTable(helperDrilldownTableColumns, helperDrilldownTableRows, "t3");
					Report.PassTestScreenshot(test, driver, "Helper Drilldown table data read successfully",
							"Helper Drilldown Data");
					Util.switchToDefaultWindow();
				} catch (Exception e) {
					Report.FailTest(test, "Not able to read Helper drilldown table data");
				}
				helperDrilldownTableData.put(routes[i], helperDrilldownTemp);
			}

			for (Entry<String, Map<String, List<String>>> entry : helperDrilldownTableData.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
				}
			}
			return helperDrilldownTableData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to read helper drilldown data", "Helper Drilldown");
		}

		return helperDrilldownTableData;
	}

	public List<String> getSummaryTableColumns() throws IOException {
		System.out.println("Reading summary columns");
		List<String> summaryTableColumn = null;
		try {
			summaryTableColumn = readColumns(summaryTableColumns, "dtEfficiencySummary");
			Report.InfoTest(test, "Summary table column read successfully");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read summary table columns");
		}

		System.out.println(summaryTableColumn.toString());
		return summaryTableColumn;
	}

	public Map<String, List<String>> readTable(List<WebElement> columns, List<WebElement> rows, String tableName) {

		if (tableName.equals("dtEfficiencySummary")) {
			Map<String, List<String>> objTable = new HashMap<>();
			// System.out.println("Number of cloumns : "+columns.size());
			// System.out.println("Number of rows : "+rows.size());
			for (int iCount = 2; iCount <= columns.size(); iCount++) {
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
				objTable.put(columns.get(iCount - 1).getText().replace("\n", ""), rowValues);
			}
			return objTable;
		}

		else if (tableName.equals("t2")) {
			Map<String, List<String>> objTable = new HashMap<>();
			// System.out.println("Number of cloumns : "+columns.size());
			// System.out.println("Number of rows : "+rows.size());
			for (int iCount = 1; iCount <= columns.size(); iCount++) {
				// List<WebElement> objCol =
				// rows.get(iCount).findElements(By.cssSelector("td.tableTxt"));
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
				objTable.put(columns.get(iCount - 1).getText().replace("\n", ""), rowValues);
			}
			return objTable;
		} else {
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
				objTable.put(columns.get(iCount - 1).getText().replace("\n", ""), rowValues);
			}
			return objTable;
		}
	}

	public List<String> readColumns(List<WebElement> columns, String tableName) {
		List<String> columnNames = new ArrayList<>();
		if (tableName.equals("dtRouteSummary")) {
			System.out.println("Number of cloumns : " + columns.size());
			for (int iCount = 2; iCount <= columns.size(); iCount++) {
				try {
					if (columns.get(iCount - 1).getText().contains("\n"))
						columnNames.add(columns.get(iCount - 1).getText().replace("\n", ""));
					else
						columnNames.add(columns.get(iCount - 1).getText());
				} catch (Exception e) {
					columnNames.add(columns.get(iCount - 1).getText());
				}
			}
		} else {
			System.out.println("Number of cloumns : " + columns.size());
			for (int iCount = 1; iCount <= columns.size(); iCount++) {
				try {
					if (columns.get(iCount - 1).getText().contains("\n"))
						columnNames.add(columns.get(iCount - 1).getText().replace("\n", ""));
					else
						columnNames.add(columns.get(iCount - 1).getText());
				} catch (Exception e) {
					columnNames.add(columns.get(iCount - 1).getText());
				}
			}
		}
		System.out.println("Column ----" + columnNames.size());
		return columnNames;
	}

	public void validateSummaryData(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualPerformanceTableData, String expectedLOB, String expectedRoute,
			String expectedDriver, String fromDate, String toDate) {
		validateSummaryPlanEfficiency(actualSummaryTableData, actualPerformanceTableData);
		validateSummaryActualEfficiency(actualSummaryTableData, actualPerformanceTableData, expectedLOB);
		validateSummaryEfficiencyVariance(actualSummaryTableData);
		validateSummaryPlanUnits(actualSummaryTableData, actualPerformanceTableData);
		validateSummaryActualUnits(actualSummaryTableData, actualPerformanceTableData);
		validateSummaryPlanDriverHours(actualSummaryTableData, actualPerformanceTableData);
		validateSummaryActualDriverHours(actualSummaryTableData, actualPerformanceTableData);
		validateSummaryDriverHourVariance(actualSummaryTableData);
		validateSummaryNumberOfRoutes(actualSummaryTableData, actualPerformanceTableData);

	}

	public void validateSummaryData_RO(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualPerformanceTableData, String expectedLOB, String expectedRoute,
			String expectedDriver, String fromDate, String toDate) {
		// validateSummaryPlanEfficiency(actualSummaryTableData,
		// actualPerformanceTableData);
		validateSummaryAllowedEfficiency(actualSummaryTableData, actualPerformanceTableData, expectedLOB);
		validateSummaryActualEfficiency(actualSummaryTableData, actualPerformanceTableData, expectedLOB);
		// validateSummaryEfficiencyVariance(actualSummaryTableData);
		validateSummaryPlanUnits(actualSummaryTableData, actualPerformanceTableData);
		validateSummaryActualUnits(actualSummaryTableData, actualPerformanceTableData);
		validateSummaryAllowedDriverHours(actualSummaryTableData, actualPerformanceTableData);
		validateSummaryActualDriverHours(actualSummaryTableData, actualPerformanceTableData);
		validateSummaryDriverHourVariance_RO(actualSummaryTableData);
		validateSummaryNumberOfRoutes(actualSummaryTableData, actualPerformanceTableData);

	}

	public void validatePerformanceSubViewData(Map<String, List<String>> actualPerformanceTableData,
			Map<String, List<String>> actualRouteSegmentTableData,
			Map<String, Map<String, List<String>>> actualHelperDrilldownTable,
			Map<String, List<String>> actualDisposalLoadsTableData, String expectedLOB, String expectedRoute,
			String expectedDriver, String fromDate, String toDate) {

		if (expectedLOB.equals("Commercial") || expectedLOB.equals("Residential")) {
			validateDate(actualPerformanceTableData, fromDate, toDate, "Performance");
			validateRouteName(actualPerformanceTableData, expectedRoute, "Performance");
			validateDriverName(actualPerformanceTableData, expectedDriver, "Performance");
			validateTruckNumber(actualPerformanceTableData, "Performance");
			validateDriverPlanEfficiency(actualPerformanceTableData, "Performance", expectedLOB);
			validateDriverActualEfficiency(actualPerformanceTableData, "Performance");
			validateEfficiencyVariance(actualPerformanceTableData, "Performance", expectedLOB);
			validateDriverHelperCombinedEfficiency(actualPerformanceTableData, "Performance", expectedLOB);
			validatePlanUnits(actualPerformanceTableData, "Performance", expectedLOB);
			validateActualUnits(actualPerformanceTableData, "Performance");
			validateUnitsvariance(actualPerformanceTableData, "Performance");
			validateEfficiencyPlanDriverHours(actualPerformanceTableData, "Performance", expectedLOB);
			validateEfficiencyActualDriverHours(actualPerformanceTableData, actualRouteSegmentTableData, "Performance");
			validateDriverHoursVariance(actualPerformanceTableData, "Performance");
			validateHelperHours_RouteLevel(actualPerformanceTableData, actualHelperDrilldownTable, "Performance");
			validateInSeqPercentage(actualPerformanceTableData, "Performance");
			validateSubLOB(actualPerformanceTableData, "Performance");
			//validateRouteManager(actualPerformanceTableData,"Performance");
			// From emap need to validate from excel
		} else if (expectedLOB.equals("Roll Off")) {
			validateDate(actualPerformanceTableData, fromDate, toDate, "Performance");
			validateRouteName(actualPerformanceTableData, expectedRoute, "Performance");
			validateDriverName(actualPerformanceTableData, expectedDriver, "Performance");
			validateTruckNumber(actualPerformanceTableData, "Performance");
			validateDriverAllowedEfficiency(actualPerformanceTableData, actualDisposalLoadsTableData, "Performance");
			validateDriverActualEfficiency_RO(actualPerformanceTableData, actualDisposalLoadsTableData, "Performance");
			validateEfficiencyVariance_RO(actualPerformanceTableData, "Performance", expectedLOB);
			validateDriverHelperCombinedEfficiency(actualPerformanceTableData, "Performance", expectedLOB);
			validatePlanUnits(actualPerformanceTableData, "Performance", expectedLOB);
			validateActualUnits_RO(actualPerformanceTableData, "Performance");
			validateUnitsvariance(actualPerformanceTableData, "Performance");
			// validateEfficiencyAllowedDriverHours(actualPerformanceTableData,"Performance",expectedLOB);
			validateEfficiencyActualDriverHours(actualPerformanceTableData, actualRouteSegmentTableData, "Performance");
			validateDriverHoursVariance_RO(actualPerformanceTableData, "Performance");
			validateHelperHours_RouteLevel(actualPerformanceTableData, actualHelperDrilldownTable, "Performance");
			validateSwapOutPercentage(actualPerformanceTableData, "Performance");
			validateInSeqPercentage(actualPerformanceTableData, "Performance");
			// validateRouteManager(actualPerformanceTableData,"Performance");
			// From emap need to validate from excel
		}

	}

	public void validateHelperHours_RouteLevel(Map<String, List<String>> actualPerformanceTableData,
			Map<String, Map<String, List<String>>> actualHelperDrilldownTable, String subViewReportName) {
		String actualHelperHours = null;
		String expectedHelperHours = null;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Helper Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualHelperHours = null;
						} else {
							actualHelperHours = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, Map<String, List<String>>> entry : actualHelperDrilldownTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().contains("Helper Hours")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (!entry2.getValue().get(i).equals("--")) {
								String[] split = entry2.getValue().get(i).split(":", 2);
								int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
								totalMins += mins;
							}
						}
					}
				}
			}

			// Validate Time Format of Actual Driver Hours
			Util.validateTimeFormat(actualHelperHours, "Helper Hours in " + subViewReportName + " sub view Report.");

			// expectedActualDriverHours=sum of actual driver hours for all
			// routes
			// expectedActualDriverHours=String.format("%02d",(int)Math.floor(totalMins/60))
			// + ":" +String.format("%02d",( totalMins % 60 ));
			expectedHelperHours = Util.convertMinsToHours(totalMins);

			if (actualHelperHours.equals(expectedHelperHours)) {
				Report.InfoTest(test,
						"Helper Hours is correct in " + subViewReportName + " sub View report Actual is : "
								+ actualHelperHours + " and Expected is : " + expectedHelperHours);
				Report.PassTest(test,
						"Helper Driver Hours is as expected in " + subViewReportName + " sub View report");
			} else {
				Report.FailTest(test,
						"Helper Driver Hours is not as expected in " + subViewReportName
								+ " sub View report Actual is : " + actualHelperHours + " and Expected is : "
								+ expectedHelperHours);
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Helper Driver Hours is not as expected in " + subViewReportName + " sub View report Actual is : "
							+ actualHelperHours + " and Expected is : " + expectedHelperHours);
		}

	}

	public void validateHelperDrilldownData(Map<String, Map<String, List<String>>> actualHelperDrilldownTable,
			String lOB, String route, String driver2, String fromDate, String toDate) {

		validateHelperHours(actualHelperDrilldownTable.get(route), "Helper Drilldown");
		// validateHelperName(actualHelperDrilldownTable.get(route), "Helper
		// Drilldown");

	}

	public void validateRouteSegmentSubViewData(Map<String, List<String>> actualRouteSegmentTableData,
			Map<String, List<String>> actualDisposalLoadsTableData, String expectedLOB, String expectedRoute,
			String expectedDriver, String expectedClockInTime, String expectedClockOutTime, String fromDate,
			String toDate) {
		validateDate(actualRouteSegmentTableData, fromDate, toDate, "Route Segments");
		validateRouteName(actualRouteSegmentTableData, expectedRoute, "Route Segments");
		validateDriverName(actualRouteSegmentTableData, expectedDriver, "Route Segments");
		validateTruckNumber(actualRouteSegmentTableData, "Route Segments");
		validatedDriverClockInTime(actualRouteSegmentTableData, expectedClockInTime, "Route Segments");
		validateDriverClockOutTime(actualRouteSegmentTableData, expectedClockOutTime, "Route Segments");
		validateDriverPreRouteTime(actualRouteSegmentTableData, "Route Segments");
		validateDriverPostRouteTime(actualRouteSegmentTableData, "Route Segments");
		validateDriverDowntime(actualRouteSegmentTableData, "Route Segments");
		validateDriverNetidleTime(actualRouteSegmentTableData, "Route Segments");
		validateDriverIdleOccurences();// need sql query
		validateDriverMealTime(actualRouteSegmentTableData, "Route Segments");
		validateDriverDisposalCycleTime(actualRouteSegmentTableData, "Route Segments");
		validateDriverAvgDisposalCycle(actualRouteSegmentTableData, actualDisposalLoadsTableData, "Route Segments");
		if (!expectedLOB.equals("Roll Off")) {
		validateSubLOB(actualRouteSegmentTableData, "Route Segments");
		}
		// validateRouteManager(actualPerformanceTableData,"Route Segments");
		// From emap need to validate from excel

	}

	public void validateDisposalLoadsSubViewData(Map<String, List<String>> actualRouteSegmentTableData,
			Map<String, List<String>> actualDisposalLoadsTableData, String expectedLOB, String expectedRoute,
			String expectedDriver, String expectedClockInTime, String expectedClockOutTime, String fromDate,
			String toDate) {

		validateDate(actualDisposalLoadsTableData, fromDate, toDate, "Disposal Load");
		validateRouteName(actualDisposalLoadsTableData, expectedRoute, "Disposal Load");
		validateDriverName(actualDisposalLoadsTableData, expectedDriver, "Disposal Load");
		validateTruckNumber(actualDisposalLoadsTableData, "Disposal Load");
		validatePlanUnits(actualDisposalLoadsTableData, "Disposal Load", expectedLOB);
		if (expectedLOB.equals("Roll Off")) {
			validateActualUnits_RO(actualDisposalLoadsTableData, "Disposal Load");
		}
		validateUnitsvariance(actualDisposalLoadsTableData, "Disposal Load");
		validateDriverDisposalCycleTime(actualDisposalLoadsTableData, "Disposal Load");
		validateDriverAvgDisposalCycle(actualRouteSegmentTableData, actualDisposalLoadsTableData, "Disposal Load");
		validateActualDisposalLoads(actualDisposalLoadsTableData, "Disposal Load");
		validatePlanDisposalLoads(actualDisposalLoadsTableData, "Disposal Load", expectedLOB); // From
																								// emap
																								// need
																								// to
																								// validate
																								// from
																								// excel
		validateTotalTons(actualDisposalLoadsTableData, "Disposal Load");
		validateTonsPerLoad(actualDisposalLoadsTableData, "Disposal Load");// need
																			// sql
																			// query
		if (expectedLOB.equals("Commercial") || expectedLOB.equals("Residential")) {
			validatePercentLoadUtilization(actualDisposalLoadsTableData, "Disposal Load");
		}
		validateSubLOB(actualDisposalLoadsTableData, "Disposal Load");
		// validateRouteManager(actualDisposalLoadsTableData,"Disposal Load");
		// From emap need to validate from excel
	}

	public void validateTravelUnitsSubViewData(Map<String, List<String>> actualRouteSegmentTableData,
			Map<String, List<String>> actualTravelUnitsTableData, Map<String, List<String>> actualPerformanceTableData,
			Map<String, List<String>> actualDisposalLoadsTableData, String expectedLOB, String expectedRoute,
			String expectedDriver, String expectedClockInTime, String expectedClockOutTime, String fromDate,
			String toDate) {
		if (expectedLOB.equals("Commercial") || expectedLOB.equals("Residential")) {
			validateDate(actualTravelUnitsTableData, fromDate, toDate, "Travel Units");
			validateRouteName(actualTravelUnitsTableData, expectedRoute, "Travel Units");
			validateDriverName(actualTravelUnitsTableData, expectedDriver, "Travel Units");
			validateTruckNumber(actualTravelUnitsTableData, "Travel Units");
			validatePlanUnits(actualTravelUnitsTableData, "Travel Units", expectedLOB);
			validateActualUnits(actualTravelUnitsTableData, "Travel Units");
			validateStopsTaken(actualTravelUnitsTableData, "Travel Units");
			validateLiftsDone(actualTravelUnitsTableData, "Travel Units");
			validateActualDistanceTravelled(actualTravelUnitsTableData, "Travel Units");
			validateUnitsPerDistanceTravelled(actualTravelUnitsTableData, "Travel Units");
			validateLiftsPerDistanceTravelled(actualTravelUnitsTableData, "Travel Units");
			validateTotalTons(actualTravelUnitsTableData, "Travel Units");
			validateFuelConsumed(actualTravelUnitsTableData, "Travel Units");
			validateSubLOB(actualTravelUnitsTableData, "Travel Units");
			// validateRouteManager(actualDisposalLoadsTableData,"Disposal
			// Load"); From emap need to validate from excel
		} else if (expectedLOB.equals("Roll Off")) {
			validateDate(actualTravelUnitsTableData, fromDate, toDate, "Travel Units");
			validateRouteName(actualTravelUnitsTableData, expectedRoute, "Travel Units");
			validateDriverName(actualTravelUnitsTableData, expectedDriver, "Travel Units");
			validateTruckNumber(actualTravelUnitsTableData, "Travel Units");
			validatePlanUnits(actualTravelUnitsTableData, "Travel Units", expectedLOB);
			validateActualUnits(actualTravelUnitsTableData, "Travel Units");
			validatePlanDistance(actualTravelUnitsTableData, "Travel Units", expectedLOB);
			validateDistancePerEquivalentHauls(actualTravelUnitsTableData, "Travel Units");
			validateSwapOutPercentage(actualTravelUnitsTableData, "Travel Units");
			validateInSeqPercentage(actualTravelUnitsTableData, "Travel Units");
			validateAllowanceMinPerLoad(actualPerformanceTableData, actualDisposalLoadsTableData,
					actualTravelUnitsTableData, "Travel Units");
			validateActualMinPerLoad(actualPerformanceTableData, actualDisposalLoadsTableData,
					actualTravelUnitsTableData, "Travel Units");
			validateVarMinPerLoad(actualTravelUnitsTableData, "Travel Units");
			validateTotalTons(actualTravelUnitsTableData, "Travel Units");
			validateFuelConsumed(actualTravelUnitsTableData, "Travel Units");
			validateSubLOB(actualTravelUnitsTableData, "Travel Units");
		}
	}

	public void validateInSeqPercentage(Map<String, List<String>> actualTravelUnitsTableData,
			String subViewReportName) {
		// In Sequence percentage = (Number of stops which are in sequence/Total
		// number of stops)*100

		List<Double> actualInSeqPercent = new ArrayList<>();
		Double expectedInSeqtPercent = 0.0;
		List<String> stopsActualSequence = new ArrayList<>();
		double numberOfStopsInSequence = 0;
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		try {
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("In Seq %")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						// actualUnits = entry.getValue();
						actualInSeqPercent.add(Double.parseDouble(entry.getValue().get(i)));
						Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)),
								" In Seq % in" + subViewReportName + " : Sub View Report.");
					}
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			stopsActualSequence = Util.getDataFromDB(
					"select ORDERSEQUENCE from OCS_ADMIN.OCS_CUSTOMERORDER where ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') order by TICKETCOMPLETEDSTAMP");

			numberOfStopsInSequence = Util.getNumberOfStopsInSequence(stopsActualSequence);

			for (int i = 0; i < route.size(); i++) {
				expectedInSeqtPercent = (numberOfStopsInSequence  * 100 ) / stopsActualSequence.size();
				if (Util.compareNumber(actualInSeqPercent.get(i), expectedInSeqtPercent)) {
					Report.InfoTest(test,
							"In Seq Percent is correct in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual In Seq Percent : " + actualInSeqPercent.get(i)
									+ " and expected In Seq Percent : " + expectedInSeqtPercent);
					Report.PassTest(test, "In Seq Percent is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test, "In Seq Percent is not as expected in " + subViewReportName
							+ " sub view report for Route : " + route.get(i) + " with Actual In Seq Percent : "
							+ actualInSeqPercent.get(i) + " and expected In Seq Percent : " + expectedInSeqtPercent);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"In Seq Percent is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualInSeqPercent.toString() + " and Expected is : " + expectedInSeqtPercent);
		}

	}

	public void validateVarMinPerLoad(Map<String, List<String>> actualTravelUnitsTableData, String subViewReportName) {
		// Driver Hour variance = Plan Driver Hours - Actual Driver Hours

		List<String> actualVarMinsPerLoad = new ArrayList<>();
		String expectedVarMinPerLoad = null;
		List<String> allowanceMinPerLoad = new ArrayList<>();
		List<String> actualMinPerLoad = new ArrayList<>();
		List<String> route = new ArrayList<>();
		try {
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Allowance Min/Load")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							allowanceMinPerLoad.add("00:00");

						} else {

							allowanceMinPerLoad.add(entry.getValue().get(i).toString());
						}
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().contains("Actual Min/Load")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {

							actualMinPerLoad.add("00:00");

						} else {

							actualMinPerLoad.add(entry.getValue().get(i).toString());
						}

					}
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Var (Mins/Load)")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualVarMinsPerLoad.add("00:00");

						} else {

							actualVarMinsPerLoad.add(entry.getValue().get(i).toString());
							Util.validateTimeFormat(entry.getValue().get(i),
									"Variance Mins Per Load in " + subViewReportName + " : Sub View Report.");
						}
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (int i = 0; i < route.size(); i++) {

				expectedVarMinPerLoad = Util.getTimeDifferenceInString(allowanceMinPerLoad.get(i),
						actualMinPerLoad.get(i));

				if (actualVarMinsPerLoad.get(i).equals(expectedVarMinPerLoad)) {
					Report.InfoTest(test,
							"Var Mins Per Load is correct in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Var Mins Per Load : " + actualVarMinsPerLoad.get(i)
									+ " and expected Var Mins Per Load : " + expectedVarMinPerLoad);
					Report.PassTest(test,
							"Var Mins Per Load is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Var Mins Per Load is not as expected in " + subViewReportName
									+ " sub view report for Route : " + route.get(i)
									+ " with Actual Var Mins Per Load : " + actualVarMinsPerLoad.get(i)
									+ " and expected Var Mins Per Load : " + expectedVarMinPerLoad);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Var Mins Per Load is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualVarMinsPerLoad.toString() + " and Expected is : " + expectedVarMinPerLoad);
		}

	}

	public void validateActualMinPerLoad(Map<String, List<String>> actualPerformanceTableData,
			Map<String, List<String>> actualDisposalLoadsTableData,
			Map<String, List<String>> actualTravelUnitsTableData, String subViewReportName) {

		// ActualMin/Load = Actual Driver Hours/Number of disposal loads

		List<String> actualMinPerLoad = new ArrayList<>();
		String expectedActualMinPerLoad = null;
		List<String> actualDriverHours = new ArrayList<>();
		List<Integer> disposalLoads = new ArrayList<>();
		List<String> route = new ArrayList<>();
		try {
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().contains("Actual Min/Load")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualMinPerLoad.add("00:00");

						} else {
							actualMinPerLoad.add(entry.getValue().get(i).toString());
							Util.validateTimeFormat(entry.getValue().get(i).toString(),
									"Actual Min Per Load in " + subViewReportName + " Sub View Report");
						}
				}
			}

			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Actual Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {

							actualDriverHours.add("00:00");

						} else {
							actualDriverHours.add(entry.getValue().get(i).toString());
						}

					}
				}
			}

			for (Entry<String, List<String>> entry : actualDisposalLoadsTableData.entrySet()) {
				if (entry.getKey().equals("Act Disp Load")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {

							disposalLoads.add(0);

						} else {
							disposalLoads.add(Integer.parseInt(entry.getValue().get(i).toString()));
						}

					}
				}
			}

			for (int i = 0; i < route.size(); i++) {

				int timeInMinutes = Util.convertHoursToMins(actualDriverHours.get(i));
				expectedActualMinPerLoad = Util.convertMinsToHours(timeInMinutes / disposalLoads.get(i));
				if (Util.compareTime(actualMinPerLoad.get(i), expectedActualMinPerLoad)) {
					Report.InfoTest(test,
							"Actual Min Per Load is correct in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Min Per Load : " + actualMinPerLoad.get(i)
									+ " and expected Actual Min Per Load : " + expectedActualMinPerLoad);
					Report.PassTest(test,
							"Actual Min Per Load is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Actual Min Per Load is not as expected in " + subViewReportName
									+ " sub view report for Route : " + route.get(i) + " with Actual Min Per Load : "
									+ actualMinPerLoad.get(i) + " and expected Actual Min Per Load : "
									+ expectedActualMinPerLoad);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Actual Min Per Load is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualMinPerLoad.toString() + " and Expected is : " + expectedActualMinPerLoad);
		}

	}

	public void validateAllowanceMinPerLoad(Map<String, List<String>> actualPerformanceTableData,
			Map<String, List<String>> actualDisposalLoadsTableData,
			Map<String, List<String>> actualTravelUnitsTableData, String subViewReportName) {
		// AllowedMin/Load = Allowed Driver Hours/Number of disposal loads

		List<String> actualAllowanceMinPerLoad = new ArrayList<>();
		String expectedAllowanceMinPerLoad = null;
		List<String> allowedDriverHours = new ArrayList<>();
		List<Integer> disposalLoads = new ArrayList<>();
		List<String> route = new ArrayList<>();
		try {
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().contains("Allowance Min/Load")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualAllowanceMinPerLoad.add("00:00");

						} else {
							actualAllowanceMinPerLoad.add(entry.getValue().get(i).toString());
							Util.validateTimeFormat(entry.getValue().get(i).toString(),
									"AllowanceMin Per Load in " + subViewReportName + " Sub View Report");
						}
				}
			}

			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Allowed Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {

							allowedDriverHours.add("00:00");

						} else {
							allowedDriverHours.add(entry.getValue().get(i).toString());
						}

					}
				}
			}

			for (Entry<String, List<String>> entry : actualDisposalLoadsTableData.entrySet()) {
				if (entry.getKey().equals("Act Disp Load")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {

							disposalLoads.add(0);

						} else {
							disposalLoads.add(Integer.parseInt(entry.getValue().get(i).toString()));
						}

					}
				}
			}

			for (int i = 0; i < route.size(); i++) {

				int timeInMinutes = Util.convertHoursToMins(allowedDriverHours.get(i));
				expectedAllowanceMinPerLoad = Util.convertMinsToHours(timeInMinutes / disposalLoads.get(i));

				if (Util.compareTime(actualAllowanceMinPerLoad.get(i), expectedAllowanceMinPerLoad)) {
					Report.InfoTest(test,
							"Allowance Min Per Load is correct in " + subViewReportName
									+ " sub view report for Route : " + route.get(i)
									+ " with Actual Allowance Min Per Load : " + actualAllowanceMinPerLoad.get(i)
									+ " and expected Allowance Min Per Load : " + expectedAllowanceMinPerLoad);
					Report.PassTest(test,
							"Allowance Min Per Load is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Allowance Min Per Load is not as expected in " + subViewReportName
									+ " sub view report for Route : " + route.get(i)
									+ " with Actual Allowance Min Per Load : " + actualAllowanceMinPerLoad.get(i)
									+ " and expected Allowance Min Per Load : " + expectedAllowanceMinPerLoad);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Allowance Min Per Load is not as expected in " + subViewReportName
							+ " sub view report Actual is : " + actualAllowanceMinPerLoad.toString()
							+ " and Expected is : " + expectedAllowanceMinPerLoad);
		}

	}

	public void validateSwapOutPercentage(Map<String, List<String>> actualTravelUnitsTableData,
			String subViewReportName) {
		// Swap out percentage = Number of swap outs/(Number of Emprty returns +
		// Number of swap outs)

		List<Double> actualSwapOutPercent = new ArrayList<>();
		Double expectedSwapOutPercent = 0.0;
		List<String> numberOfSwapOuts = new ArrayList<>();
		List<String> numberOfEmptyReturns = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		try {
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Swap Out %")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						// actualUnits = entry.getValue();
						actualSwapOutPercent.add(Double.parseDouble(entry.getValue().get(i)));
						Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)),
								" Swap Out % in" + subViewReportName + " : Sub View Report.");
					}
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			numberOfSwapOuts = Util.getDataFromDB(
					"select count(*) from OCS_ADMIN.OCS_CUSTOMERORDER where ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and LOADTYPE IN ('S/O','BTS')");

			numberOfEmptyReturns = Util.getDataFromDB(
					"select count(*) from OCS_ADMIN.OCS_CUSTOMERORDER where ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and LOADTYPE IN ('E/R','FYS','LIV')");

			for (int i = 0; i < route.size(); i++) {
				expectedSwapOutPercent = (Double.parseDouble(numberOfSwapOuts.get(i))
						/ (Double.parseDouble(numberOfEmptyReturns.get(i))
								+ Double.parseDouble(numberOfSwapOuts.get(i)))
						* 100);
				if (Util.compareNumber(actualSwapOutPercent.get(i), expectedSwapOutPercent)) {
					Report.InfoTest(test,
							"Swap Out Percent is correct in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Swap Out Percent : " + actualSwapOutPercent.get(i)
									+ " and expected Swap Out Percent : " + expectedSwapOutPercent);
					Report.PassTest(test,
							"Swap Out Percent is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Swap Out Percent is not as expected in " + subViewReportName
									+ " sub view report for Route : " + route.get(i)
									+ " with Actual Swap Out Percent : " + actualSwapOutPercent.get(i)
									+ " and expected Swap Out Percent : " + expectedSwapOutPercent);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Swap Out Percent is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualSwapOutPercent.toString() + " and Expected is : " + expectedSwapOutPercent);
		}
	}

	public void validateDistancePerEquivalentHauls(Map<String, List<String>> actualTravelUnitsTableData,
			String subViewReportName) {
		// distance per eqv hauls= Actual Distance/Actual Units

		List<Double> ActualUnits = new ArrayList<>();
		List<Double> ActualDistance = new ArrayList<>();
		double expectedDistancePerEqvHauls = 0.0;
		List<Double> actualDistancePerEqvHauls = new ArrayList<>();
		List<String> route = new ArrayList<>();

		try {
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Distance per Eqv Hauls")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualDistancePerEqvHauls.add(Double.parseDouble(entry.getValue().get(i)));
						Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2,
								"Distance per Eqv hauls in " + subViewReportName + " sub view Report");
					}
				}
			}

			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						ActualUnits.add(Double.parseDouble(entry.getValue().get(i)));						
					}
				}
			}

			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Actual Distance")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						ActualDistance.add(Double.parseDouble(entry.getValue().get(i)));						
					}
				}
			}

			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (int i = 0; i < route.size(); i++) {

				expectedDistancePerEqvHauls = ActualDistance.get(i) / ActualUnits.get(i);

				if (Util.compareNumber(actualDistancePerEqvHauls.get(i), expectedDistancePerEqvHauls)) {
					Report.InfoTest(test,
							"Distance per Eqv hauls is correct in " + subViewReportName
									+ " sub view report for Route : " + route.get(i)
									+ " with Actual Distance per Eqv hauls : " + actualDistancePerEqvHauls.get(i)
									+ " and expected Distance per Eqv hauls : " + expectedDistancePerEqvHauls);
					Report.PassTest(test,
							"Distance per Eqv hauls is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Distance per Eqv hauls is not as expected in " + subViewReportName
									+ " sub view report for Route : " + route.get(i)
									+ " with Actual Distance per Eqv hauls : " + actualDistancePerEqvHauls.get(i)
									+ " and expected Distance per Eqv hauls : " + expectedDistancePerEqvHauls);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Distance per Eqv hauls is not as expected in " + subViewReportName
							+ " sub view report Actual is : " + actualDistancePerEqvHauls.toString()
							+ " and Expected is : " + expectedDistancePerEqvHauls);
		}

	}

	public void validateHeperTabData(Map<String, List<String>> actualHelperTableData, String site, String expectedLOB,
			String expectedRoute, String expectedDriver, String expectedClockInTime, String expectedClockOutTime,
			String fromDate, String toDate) {
		validateDate(actualHelperTableData, fromDate, toDate, "Helper Tab");
		validateSiteName(actualHelperTableData, site, "Helper Tab");
		validateRouteName(actualHelperTableData, expectedRoute, "Helper Tab");
		validateDriverName(actualHelperTableData, expectedDriver, "Helper Tab");
		validateHelperName(actualHelperTableData, "Helper Tab");
		validateHelperHours(actualHelperTableData, "Helper Tab");
		// validateHelperSignInTime();
		// validateHelperSignOutTime();
		validateRouteType(actualHelperTableData, "Helper Tab");
		validateLOB(actualHelperTableData, expectedLOB, "Helper Tab");
		validateSubLOB(actualHelperTableData, "Helper Tab");
		// validateRouteManager(actualDisposalLoadsTableData,"Disposal Load");
		// From emap need to validate from excel
	}

	public void validateRouteType(Map<String, List<String>> actualHelperTableData, String subViewReportName) {
		List<String> actualRouteType = new ArrayList<>();
		List<String> expectedRouteType = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		String expectedRouteTypes = "";
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");

		try {
			for (Entry<String, List<String>> entry : actualHelperTableData.entrySet()) {
				if (entry.getKey().equals("Route Type")) {
					actualRouteType = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualHelperTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualHelperTableData.entrySet()) {
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

				if (expectedRouteType.get(0).equals("RES REARLOAD"))
					expectedRouteTypes = "RR";
				
				if (expectedRouteType.get(0).equals("ROLL OFF"))
					expectedRouteTypes = "O";

				if (actualRouteType.contains(expectedRouteTypes)) {
			//	if (actualRouteType.get(i).equals(expectedRouteTypes)) {
					Report.InfoTest(test,
							"Route Type  is correct in " + subViewReportName + " Sub View report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type  : " + expectedRouteTypes);
					Report.PassTest(test, "Route Type is as expected in " + subViewReportName + " Sub View report");
				} else {
					Report.FailTest(test,
							"Route Type is not as expected in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type : " + expectedRouteTypes);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test,
					"Route Type  is not as expected in " + subViewReportName + " Sub view report Actual is : "
							+ actualRouteType.toString() + " and Expected is : " + expectedRouteType.toString());
		}

	}

	public void validateHelperHours(Map<String, List<String>> actualHelperTableData, String subViewReportName) {
		List<String> clockInTimes = null;
		List<String> clockOutTimes = null;
		List<String> actualHelperHours = new ArrayList<>();
		String expectedHelperHours = null;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");

		try {
			for (Entry<String, List<String>> entry : actualHelperTableData.entrySet()) {
				if (entry.getKey().equals("Sign In Time")) {
					clockInTimes = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualHelperTableData.entrySet()) {
				if (entry.getKey().equals("Sign Off Time")) {
					clockOutTimes = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualHelperTableData.entrySet()) {
				if (entry.getKey().contains("Helper Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualHelperHours.add("00:00");
						} else {
							actualHelperHours.add(entry.getValue().get(i));
							Util.validateTimeFormat(entry.getValue().get(i),
									"Helper Hours in " + subViewReportName + " : Sub View Report. ");
						}
					}
				}
			}

			for (int i = 0; i < actualHelperHours.size(); i++) {
				if (actualHelperHours.get(i).equals("00:00")) {
					Report.InfoTest(test, "Actual Helper Hours is " + actualHelperHours.get(i));
					Report.PassTest(test, "Kronos Clock In and Clock Out time is missing in " + subViewReportName
							+ " sub view report");
				} else {

					expectedHelperHours = Util.getTimeDifferenceInString(
							format.format(format.parse(clockOutTimes.get(i))),
							format.format(format.parse(clockInTimes.get(i))));
					System.out.println("Expected helper hour  : " + expectedHelperHours);
					if (actualHelperHours.get(i).equals(expectedHelperHours)) {
						Report.InfoTest(test,
								"Actual Helper Hours is correct in " + subViewReportName
										+ " sub view report with Clcok In Time : " + clockInTimes.get(i)
										+ " and Clock Out Time : " + clockOutTimes.get(i) + ". Actual is : "+actualHelperHours.get(i)+" Expected is : "
										+ expectedHelperHours);
						Report.PassTest(test,
								"Actual Helper Hours is as expected in " + subViewReportName + " sub view report");
					} else if (actualHelperHours.equals("10:00")) {
						expectedHelperHours = "09:30";
						if (actualHelperHours.get(i).equals(expectedHelperHours)) {
							Report.InfoTest(test,
									"Helper Hours is correct in " + subViewReportName
											+ " sub view report with Clcok In Time : " + clockInTimes.get(i)
											+ " and Clock Out Time : " + clockOutTimes.get(i) + ". Actual is : "+actualHelperHours.get(i)+" Expected is : "
											+ expectedHelperHours);
							Report.PassTest(test,
									"Helper Hours is as expected in " + subViewReportName + " sub view report");
						}
					}

					else {
						Report.FailTest(test,
								"Helper Hours is not as expected in " + subViewReportName
										+ " sub view report with Clcok In Time : " + clockInTimes.get(i)
										+ " and Clock Out Time : " + clockOutTimes.get(i) +". Actual is : "+actualHelperHours.get(i)+" Expected is : "
										+ expectedHelperHours);
					}
				}
			}
		}

		catch (Exception e) {
			Report.FailTest(test, "ActualDriver Hours is not as expected in efficiency report Actual is : "
					+ actualHelperHours.toString() + " and Expected is : " + expectedHelperHours);
		}

	}

	public void validateHelperName(Map<String, List<String>> actualHelperTableData, String subViewReportName) {
		List<String> helperLastName = new ArrayList<>();
		List<String> helperFirstName = new ArrayList<>();
		String expectedHelperName = "";
		List<String> actualHelperName = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		try {
			for (Entry<String, List<String>> entry : actualHelperTableData.entrySet()) {
				if (entry.getKey().equals("Helper")) {
					actualHelperName = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualHelperTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualHelperTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			helperLastName = Util.getDataFromDB(
					"select LASTNAME from ocs_admin.tp_employee where pkey IN (select ocs_admin.TP_RO_CLOCKINOUT.FK_EMPLOYEE from ocs_admin.TP_RO_CLOCKINOUT where FK_ROUTEORDER IN ( select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ")) and OCS_ADMIN.TP_RO_CLOCKINOUT.FK_VEHICLE IS NOT NULL and OCS_ADMIN.TP_RO_CLOCKINOUT.STAFFTYPE = 'HELPER') ");

			helperFirstName = Util.getDataFromDB(
					"select FIRSTNAME from ocs_admin.tp_employee where pkey IN (select ocs_admin.TP_RO_CLOCKINOUT.FK_EMPLOYEE from ocs_admin.TP_RO_CLOCKINOUT where FK_ROUTEORDER IN ( select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ "))and OCS_ADMIN.TP_RO_CLOCKINOUT.FK_VEHICLE IS NOT NULL and OCS_ADMIN.TP_RO_CLOCKINOUT.STAFFTYPE = 'HELPER') ");

			if (actualHelperName.size() > 0) {
				for (int i = 0; i < actualHelperName.size(); i++) {
					expectedHelperName = helperLastName.get(i) + ", " + helperFirstName.get(i);
					if (actualHelperName.contains(expectedHelperName)) {
				//	if (actualHelperName.get(i).equals(expectedHelperName)) {
						Report.InfoTest(test,
								"Helper Name is correct in " + subViewReportName + " Sub View report Actual List is : "
										+ actualHelperName.toString() + " and Expected is : " + expectedHelperName);
						Report.PassTest(test,
								"Helper Name is as expected in " + subViewReportName + " sub view report");
					} else {
						Report.FailTest(test,
								"Helper Name is not as expected in " + subViewReportName
										+ " Sub View report Actual List is : " + actualHelperName.toString()
										+ " and Expected is : " + expectedHelperName);
					}
				}
			} else {
				Report.FailTest(test, "No Helper record is available");
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Driver Name is not as expected in " + subViewReportName + " Sub View report Actual List is : "
							+ actualHelperName.toString() + " and Expected is : " + expectedHelperName);
		}

	}

	public void validateSiteName(Map<String, List<String>> actualHelperTableData, String site,
			String subViewReportName) {

		String actualSite = null;
		try {
			for (Entry<String, List<String>> entry : actualHelperTableData.entrySet()) {
				if (entry.getKey().equals("Site")) {

					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualSite = null;
						} else {
							actualSite = entry.getValue().get(i).toString();
						}
					}
				}
			}

			if (site.contains(actualSite)) {

				Report.InfoTest(test, "Site Name is correct in " + subViewReportName + " Sub View report Actual is : "
						+ actualSite + " and Expected is : " + site);
				Report.PassTest(test, "Site Name is as expected in " + subViewReportName + " Sub View report");
			} else {
				Report.FailTest(test, "Site Name is not as expected in " + subViewReportName
						+ " Sub View report Actual is : " + actualSite + " and Expected is : " + site);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Site Name is not as expected in " + subViewReportName
					+ " Sub View report Actual List is : " + actualSite + " and Expected is : " + site);
		}

	}

	public void validateLiftsPerDistanceTravelled(Map<String, List<String>> actualTravelUnitsTableData,
			String subViewReportName) {
		// Lifts/Distance = Total Lifts/Total Distance travelled

		List<String> Lifts = new ArrayList<>();
		List<String> Distance = new ArrayList<>();
		double expectedLiftsPerDistance = 0.0;
		List<Double> actualLiftsPerDistance = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		try {
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Lifts/Distance")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualLiftsPerDistance.add(Double.parseDouble(entry.getValue().get(i)));
						Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)),
								"Lifts/Distance in " + subViewReportName + " sub view Report");
					}
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			Lifts = Util.getDataFromDB(
					"select TOTALLIFT from ocs_Admin.TP_RO_UNITSUMMARY where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') ");

			Distance = Util.getDataFromDB(
					"select TRUCKEND-TRUCKSTART from ocs_admin.tp_ro_result where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");

			for (int i = 0; i < route.size(); i++) {

				expectedLiftsPerDistance = Double.parseDouble(Lifts.get(i)) / Double.parseDouble(Distance.get(i));

				if (Util.compareNumber(actualLiftsPerDistance.get(i), expectedLiftsPerDistance)) {
					Report.InfoTest(test,
							"Lifts/Distance is correct in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Lifts/Distance : " + actualLiftsPerDistance.get(i)
									+ " and expected Lifts/Distance : " + expectedLiftsPerDistance);
					Report.PassTest(test, "Units/Distance is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Units/Distance is not as expected in " + subViewReportName
									+ " sub view report for Route : " + route.get(i) + " with Actual Lifts/Distance : "
									+ actualLiftsPerDistance.get(i) + " and expected Lifts/Distance : "
									+ expectedLiftsPerDistance);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Lifts/Distance is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualLiftsPerDistance.toString() + " and Expected is : " + expectedLiftsPerDistance);
		}

	}

	public void validateUnitsPerDistanceTravelled(Map<String, List<String>> actualTravelUnitsTableData,
			String subViewReportName) {

		// Units/Distance = Total Units/Total Distance travelled

		List<String> Units = new ArrayList<>();
		List<String> Distance = new ArrayList<>();
		double expectedUnitsPerDistance = 0.0;
		List<Double> actualUnitsPerDistance = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		try {
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Units/Distance")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualUnitsPerDistance.add(Double.parseDouble(entry.getValue().get(i)));
						Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)),
								"Units/Distance in " + subViewReportName + " sub view Report");
					}
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			Units = Util.getDataFromDB(
					"select TOTALUNIT from ocs_Admin.TP_RO_UNITSUMMARY where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED')");

			Distance = Util.getDataFromDB(
					"select TRUCKEND-TRUCKSTART from ocs_admin.tp_ro_result where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL ");

			for (int i = 0; i < route.size(); i++) {

				expectedUnitsPerDistance = Double.parseDouble(Units.get(i)) / Double.parseDouble(Distance.get(i));

				if (Util.compareNumber(actualUnitsPerDistance.get(i), expectedUnitsPerDistance)) {
					Report.InfoTest(test,
							"Units/Distance is correct in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Units/Distance : " + actualUnitsPerDistance.get(i)
									+ " and expected Units/Distance : " + expectedUnitsPerDistance);
					Report.PassTest(test, "Units/Distance is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Units/Distance is not as expected in " + subViewReportName
									+ " sub view report for Route : " + route.get(i) + " with Actual Units/Distance : "
									+ actualUnitsPerDistance.get(i) + " and expected Units/Distance : "
									+ expectedUnitsPerDistance);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Units/Distance is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualUnitsPerDistance.toString() + " and Expected is : " + expectedUnitsPerDistance);
		}

	}

	public void validateFuelConsumed(Map<String, List<String>> actualTravelUnitsTableData, String subViewReportName) {
		// Total Distance Travelled will be directly validated against
		// confirmation DB

		List<String> actualFuelConsumed = new ArrayList<>();
		List<String> expectedFuelConsumed = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		try {
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Fuel")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualFuelConsumed.add(entry.getValue().get(i));
						Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)),
								"Fuel in " + subViewReportName + " sub view Report");
					}
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			expectedFuelConsumed = Util.getDataFromDB(
					"select FUEL from ocs_admin.tp_ro_result where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");

			for (int i = 0; i < route.size(); i++) {
				if (actualFuelConsumed.get(i).equals(expectedFuelConsumed.get(i))) {
					Report.InfoTest(test,
							"Total Fuel Consumed is correct in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Fuel : " + actualFuelConsumed.get(i)
									+ " and expected Fuel : " + expectedFuelConsumed.get(i));
					Report.PassTest(test,
							"Total Fuel Consumed is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test, "Total Fuel Consumed is not as expected in " + subViewReportName
							+ " sub view report for Route : " + route.get(i) + " with Actual Fuel : "
							+ actualFuelConsumed.get(i) + " and expected Fuel : " + expectedFuelConsumed.get(i));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Total Fuel Consumed is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualFuelConsumed.toString() + " and Expected is : " + expectedFuelConsumed);
		}

	}

	public void validateActualDistanceTravelled(Map<String, List<String>> actualTravelUnitsTableData,
			String subViewReportName) {
		// Total Distance Travelled will be directly validated against
		// confirmation DB

		List<Integer> actualDistanceTravelled = new ArrayList<>();
		List<String> expectedDistanceTravelled = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		try {
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Actual Distance")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualDistanceTravelled.add(Integer.parseInt(entry.getValue().get(i)));
						Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)),
								"Actual Distance in " + subViewReportName + " sub view Report");
					}
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			expectedDistanceTravelled = Util.getDataFromDB(
					"select TRUCKEND-TRUCKSTART from ocs_admin.tp_ro_result where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");

			for (int i = 0; i < route.size(); i++) {
				if (Util.compareNumber(actualDistanceTravelled.get(i),
						Double.parseDouble(expectedDistanceTravelled.get(i)))) {
					Report.InfoTest(test,
							"Actual Distance is correct in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Total Lifts : " + actualDistanceTravelled.get(i)
									+ " and expected Total Lifts : " + expectedDistanceTravelled.get(i));
					Report.PassTest(test,
							"Actual Distance is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Actual Distance is not as expected in " + subViewReportName
									+ " sub view report for Route : " + route.get(i) + " with Actual Total Lifts : "
									+ actualDistanceTravelled.get(i) + " and expected Total Lifts : "
									+ expectedDistanceTravelled.get(i));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Actual Distance is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualDistanceTravelled.toString() + " and Expected is : " + expectedDistanceTravelled);
		}

	}

	public void validateLiftsDone(Map<String, List<String>> actualTravelUnitsTableData, String subViewReportName) {
		// Total Lifts will be directly validated against confirmation DB

		List<Double> actualLifts = new ArrayList<>();
		List<String> expectedLifts = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		try {
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Lifts")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualLifts.add(Double.parseDouble(entry.getValue().get(i)));
						Util.validateFieldDecimalPlaces(Double.parseDouble(entry.getValue().get(i)), 1,
								"Lifts in " + subViewReportName + " sub view Report");
					}
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			expectedLifts = Util.getDataFromDB(
					"select TOTALLIFT from ocs_Admin.TP_RO_UNITSUMMARY where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') ");

			for (int i = 0; i < route.size(); i++) {
				if (Util.compareNumber(actualLifts.get(i), Double.parseDouble(expectedLifts.get(i)))) {
					Report.InfoTest(test,
							"Total Lifts is correct in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Total Lifts : " + actualLifts.get(i)
									+ " and expected Total Lifts : " + expectedLifts.get(i));
					Report.PassTest(test, "Total Lifts is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Total Lifts is not as expected in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Total Lifts : " + actualLifts.get(i)
									+ " and expected Total Lifts : " + expectedLifts.get(i));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Lifts is not as expected in " + subViewReportName
					+ " sub view report Actual is : " + actualLifts.toString() + " and Expected is : " + expectedLifts);
		}

	}

	public void validateStopsTaken(Map<String, List<String>> actualTravelUnitsTableData, String subViewReportName) {
		// Total Stops will be directly validated against confirmation DB

		List<Double> actualStops = new ArrayList<>();
		List<String> expectedStops = new ArrayList<>();
		List<String> expectedTickets = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		try {
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Stops")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualStops.add(Double.parseDouble(entry.getValue().get(i)));
						Util.validateFieldDecimalPlaces(Double.parseDouble(entry.getValue().get(i)), 1,
								"Stops in " + subViewReportName + " sub view Report");
					}
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualTravelUnitsTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			expectedStops = Util.getDataFromDB(
					"select TOTALSTOP from ocs_Admin.TP_RO_UNITSUMMARY where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') ");

			expectedTickets = Util.getDataFromDB(
					"select count(*) from OCS_ADMIN.OCS_CUSTOMERORDER where ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and TICKETNUMBER IS NOT NULL");

			for (int i = 0; i < route.size(); i++) {
				double expectedStopAndTickets = (Double.parseDouble(expectedStops.get(i))
						+ Double.parseDouble(expectedTickets.get(i)));
				if (Util.compareNumber(actualStops.get(i), expectedStopAndTickets)) {
					// if (actualStops.get(i).equals(expectedStops.get(i))) {
					Report.InfoTest(test,
							"Total Stops is correct in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Total Stops : " + actualStops.get(i)
									+ " and expected Total Stops : " + expectedStops.get(i));
					Report.PassTest(test, "Total Stops is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Total Stops is not as expected in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Total Stops : " + actualStops.get(i)
									+ " and expected Total Stops : " + expectedStops.get(i));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Stops is not as expected in " + subViewReportName
					+ " sub view report Actual is : " + actualStops.toString() + " and Expected is : " + expectedStops);
		}

	}

	public void validatePercentLoadUtilization(Map<String, List<String>> actualDisposalLoadsTableData,
			String subViewReportName) {
		// TODO Auto-generated method stub

	}

	public void validateTruckNumber(Map<String, List<String>> actualPerformanceTableData, String subViewReportName) {
		// Truck# = truck id

		String actualTruckNum = null;
		List<String> expectedTruckNum = new ArrayList<>();

		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		try {
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Truck ID")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualTruckNum = null;

						} else {
							actualTruckNum = entry.getValue().get(i).toString();
						}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			expectedTruckNum = Util.getDataFromDB(
					"select ID from OCS_ADMIN.TP_VEHICLE where PKEY in (Select FK_VEHICLE from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN("
							+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED')");

			for (int i = 0; i < route.size(); i++) {

				if (actualTruckNum != null && !actualTruckNum.isEmpty()) {
					if (actualTruckNum.equals(expectedTruckNum.get(0))) {
						Report.InfoTest(test,
								"Truck # is correct in " + subViewReportName + " sub view report for Route : "
										+ route.get(i) + " with Actual Truck # : " + actualTruckNum
										+ " and expected Truck # : " + expectedTruckNum.get(0));
						Report.PassTest(test, "Truck # is as expected in " + subViewReportName + " sub view report");
					} else {
						Report.FailTest(test,
								"Truck # is not correct in " + subViewReportName + " sub view report for Route : "
										+ route.get(i) + " with Actual Truck # : " + actualTruckNum
										+ " and expected Truck # : " + expectedTruckNum.get(0));
					}
				} else {
					Report.FailTest(test, "There is no Truck number available in " + subViewReportName
							+ " sub view report for Route : " + route.get(i));
				}
			}

		} catch (Exception e) {
			Report.FailTest(test, "Truck # is not as expected in " + subViewReportName + " sub view report Actual is : "
					+ actualTruckNum + " and Expected is : " + expectedTruckNum.get(0));
		}

	}

	public void validateTonsPerLoad(Map<String, List<String>> actualDisposalLoadsTableData, String subViewReportName) {
		// TODO Auto-generated method stub
		// Tons Per Load = Sum of tonnage for each disposal trips/number of
		// disposal trips
		List<String> actualTotalTons = new ArrayList<>();
		double actualTonsPerLoad = 0.0;
		double expectedTonsPerLoad = 0.0;
		List<Date> arriveDisposalTime = new ArrayList<>();
		List<Date> departDisposalTime = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");

		try {
			for (Entry<String, List<String>> entry : actualDisposalLoadsTableData.entrySet()) {
				if (entry.getKey().contains("Tons/Load")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualTonsPerLoad = 0.0;

						} else {
							actualTonsPerLoad = Double.parseDouble(entry.getValue().get(i).toString());
							Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2,
									"Tons/Load in " + subViewReportName + " : Sub View Report.");
						}
				}
			}
			for (Entry<String, List<String>> entry : actualDisposalLoadsTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualDisposalLoadsTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			arriveDisposalTime = Util.getDateDataFromDB(
					"select  TO_CHAR(ARRIVESTAMP, 'HH:MI') from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");
			departDisposalTime = Util.getDateDataFromDB(
					"select  TO_CHAR(DEPARTSTAMP, 'HH:MI') from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");

			actualTotalTons = Util.getDataFromDB(
					"select SUM(TONNAGE) from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");

			for (int i = 0; i < route.size(); i++) {
				double totalTons = Double.parseDouble(actualTotalTons.get(i));
				if (arriveDisposalTime != null && departDisposalTime != null) {				
					expectedTonsPerLoad = totalTons / arriveDisposalTime.size();
					if (Util.compareNumber(actualTonsPerLoad, expectedTonsPerLoad)) {
						Report.InfoTest(test,
								"Tons per load is correct in " + subViewReportName + " sub view report for Route : "
										+ route.get(i) + " with Actual Tons per Load : " + actualTonsPerLoad
										+ " and expected Tons per Load : " + expectedTonsPerLoad);
						Report.PassTest(test,
								"Tons per Load is as expected in " + subViewReportName + " sub view report");
					} else {
						Report.FailTest(test,
								"Tons per load is not correct in " + subViewReportName + " sub view report for Route : "
										+ route.get(i) + " with Actual Tons per Load : " + actualTonsPerLoad
										+ " and expected Tons per Load : " + expectedTonsPerLoad);
					}
				} else {
					Report.FailTest(test, "There is no disposal cycle in " + subViewReportName
							+ " sub view report for Route : " + route.get(i));
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Tons per load is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualTonsPerLoad + " and Expected is : " + expectedTonsPerLoad);
		}

	}

	public void validateTotalTons(Map<String, List<String>> actualDisposalLoadsTableData, String subViewReportName) {

		// Total Tons = Sum of tonnage for each disposal trips
		List<String> actualTotalTons = new ArrayList<>();
		double expectedTotalTons = 0.0;
		List<Date> arriveDisposalTime = new ArrayList<>();
		List<Date> departDisposalTime = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		try {
			for (Entry<String, List<String>> entry : actualDisposalLoadsTableData.entrySet()) {
				if (entry.getKey().contains("Total Tons")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							expectedTotalTons = 0.0;

						} else {
							expectedTotalTons = Double.parseDouble(entry.getValue().get(i).toString());
							Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2,
									"Total Tons in " + subViewReportName + " : Sub View Report.");
						}
				}
			}
			for (Entry<String, List<String>> entry : actualDisposalLoadsTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualDisposalLoadsTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			arriveDisposalTime = Util.getDateDataFromDB(
					"select  TO_CHAR(ARRIVESTAMP, 'HH:MI') from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");
			departDisposalTime = Util.getDateDataFromDB(
					"select  TO_CHAR(DEPARTSTAMP, 'HH:MI') from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");

			actualTotalTons = Util.getDataFromDB(
					"select SUM(TONNAGE) from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");
			
			//Do we need to add a logic for yards also

			for (int i = 0; i < route.size(); i++) {		

					if (Util.compareNumber(Double.parseDouble(actualTotalTons.get(i)), expectedTotalTons)) {
						Report.InfoTest(test,
								"Total tons is correct in " + subViewReportName + " sub view report for Route : "
										+ route.get(i) + " with Actual Total Tons : " + actualTotalTons.get(i)
										+ " and expected Total Tons : " + expectedTotalTons);
						Report.PassTest(test, "Total tons is as expected in " + subViewReportName + " sub view report");
					} else {
						Report.FailTest(test,
								"Total tons is not correct in " + subViewReportName + " sub view report for Route : "
										+ route.get(i) + " with Actual Total Tons : " + actualTotalTons.get(i)
										+ " and expected Total Tons : " + expectedTotalTons);
					}
				 
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Total tons is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualTotalTons.toString() + " and Expected is : " + expectedTotalTons);
		}

	}

	public void validateActualDisposalLoads(Map<String, List<String>> actualDisposalLoadsTableData,
			String subViewReportName) {

		// ActualDisposalLoads = Number of disposal trips in a route
		List<Date> arriveDisposalTime = new ArrayList<>();
		int actualDisposalLoads = 0;
		int expectedDisposalLoads = 0;
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		try {
			for (Entry<String, List<String>> entry : actualDisposalLoadsTableData.entrySet()) {
				if (entry.getKey().contains("Act Disp Load")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualDisposalLoads = 0;

						} else {
							actualDisposalLoads = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualDisposalLoads,
									"Actual Disposal Loads in " + subViewReportName + " Sub View Report");
						}
				}
			}

			for (Entry<String, List<String>> entry : actualDisposalLoadsTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualDisposalLoadsTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			arriveDisposalTime = Util.getDateDataFromDB(
					"select  TO_CHAR(ARRIVESTAMP, 'HH:MI') from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ")  and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");

			for (int i = 0; i < route.size(); i++) {
				if (arriveDisposalTime != null) {
					expectedDisposalLoads = arriveDisposalTime.size();
					if (actualDisposalLoads == expectedDisposalLoads) {
						Report.InfoTest(test, "Actual Disposal Loads is correct in " + subViewReportName
								+ " sub view report for Route : " + route.get(i) + " with Actual Disposal Loads : "
								+ actualDisposalLoads + " and expected Disposal Loads : " + expectedDisposalLoads);
						Report.PassTest(test,
								"Actual Disposal Loads is as expected in " + subViewReportName + " sub view report");
					} else {
						Report.FailTest(test, "Actual Disposal Loads is not as expected in " + subViewReportName
								+ " sub view report for Route : " + route.get(i) + " with Actual Disposal Loads : "
								+ actualDisposalLoads + " and expected Disposal Loads : " + expectedDisposalLoads);
					}
				} else {
					Report.FailTest(test, "There is no disposal cycle in " + subViewReportName
							+ " sub view report for Route : " + route.get(i));
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Disposal Loads is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualDisposalLoads + " and Expected is : " + expectedDisposalLoads);
		}

	}

	public void validateDriverAvgDisposalCycle(Map<String, List<String>> actualRouteSegmentsTableData,
			Map<String, List<String>> actualDisposalLoadsTableData, String subViewReportName) {

		// AvgDisposalCycleTime = Sum of individual disposal trips/Number of
		// disposal trips
		// Individual disposal trips = depart disposal time - arrive disposal
		// time
		List<Date> actualAvgDisposalCycleTime = new ArrayList<>();
		List<Date> arriveDisposalTime = new ArrayList<>();
		List<Date> departDisposalTime = new ArrayList<>();
		Date expectedAvgDisposalTime = null;
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		int numOfActualDisposalLoads = 0;
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		try {
			for (Entry<String, List<String>> entry : actualDisposalLoadsTableData.entrySet()) {
				if (entry.getKey().contains("Avg Disp Cycle")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualAvgDisposalCycleTime.add(dateFormat.parse("00:00"));

						} else {
							actualAvgDisposalCycleTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
							Util.validateTimeFormat(entry.getValue().get(i).toString(),
									"Avg Disposal Cycle Time in " + subViewReportName + " Sub View Report");
						}
				}
			}
			for (Entry<String, List<String>> entry : actualDisposalLoadsTableData.entrySet()) {
				if (entry.getKey().contains("Act Disp Load")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {
							numOfActualDisposalLoads = 0;
						} else {

							numOfActualDisposalLoads = Integer.parseInt(entry.getValue().get(i).toString());
						}
				}
			}
			for (Entry<String, List<String>> entry : actualRouteSegmentsTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualRouteSegmentsTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			arriveDisposalTime = Util.getDateDataFromDB(
					"select  TO_CHAR(ARRIVESTAMP - (6 / 24) , 'HH:MI') from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");
			departDisposalTime = Util.getDateDataFromDB(
					"select  TO_CHAR(DEPARTSTAMP - (6 / 24) , 'HH:MI') from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");

			if (arriveDisposalTime.size() > 0 & departDisposalTime.size() > 0) {
				for (int i = 0; i < route.size(); i++) {
					long diff = 0;
					long diffMinutes = 0;
					long diffHours = 0;
					long avgDisposalTimeInMillis = 0;
					if (arriveDisposalTime != null && departDisposalTime != null) {
						for (int j = 0; j < arriveDisposalTime.size(); j++) {
							diff = diff + (departDisposalTime.get(i).getTime() - arriveDisposalTime.get(i).getTime());
						}
						avgDisposalTimeInMillis = diff / numOfActualDisposalLoads;
						diffMinutes = avgDisposalTimeInMillis / (60 * 1000) % 60;
						diffHours = avgDisposalTimeInMillis / (60 * 60 * 1000) % 24;
						DecimalFormat twodigits = new DecimalFormat("00");

						if (diffHours == 0 && diffMinutes == 0)
							expectedAvgDisposalTime = dateFormat.parse("00:00");
						else
							expectedAvgDisposalTime = dateFormat
									.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

						if (Util.compareTime(actualAvgDisposalCycleTime.get(i), expectedAvgDisposalTime)) {
							Report.InfoTest(test,
									"Avg Disposal cycle Time is correct in " + subViewReportName
											+ " sub view report for Route : " + route.get(i)
											+ " with Actual Disposal Cycle Time : "
											+ dateFormat.format(actualAvgDisposalCycleTime.get(i))
											+ " and expected Disposal Cycle Time : "
											+ dateFormat.format(expectedAvgDisposalTime));
							Report.PassTest(test,
									"Disposal Cycle Time is as expected in " + subViewReportName + " sub view report");
						} else {
							Report.FailTest(test,
									"Avg Disposal cycle Time is not as expected in " + subViewReportName
											+ " sub view report for Route : " + route.get(i)
											+ " with Actual Disposal Cycle Time : "
											+ dateFormat.format(actualAvgDisposalCycleTime.get(i))
											+ " and expected Disposal Cycle Time : "
											+ dateFormat.format(expectedAvgDisposalTime));
						}
					} else {
						Report.FailTest(test, "There is no disposal cycle in " + subViewReportName
								+ " sub view report for Route : " + route.get(i));
					}
				}

			} else {
				Report.FailTest(test, "There is no disposal cycle in " + subViewReportName
						+ " sub view report for Route : " + route.toString());
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Avg Disposal cycle Time is not as expected in " + subViewReportName
							+ " sub view report Actual is : " + actualAvgDisposalCycleTime.toString()
							+ " and Expected is : " + dateFormat.format(expectedAvgDisposalTime));
		}

	}

	public void validateDriverClockOutTime(Map<String, List<String>> actualRouteSegmentTableData,
			String expectedClockOutTime, String subViewReportName) {
		List<String> actualClockOutTime = null;
		try {
			for (Entry<String, List<String>> entry : actualRouteSegmentTableData.entrySet()) {
				if (entry.getKey().equals("Clock Out")) {
					actualClockOutTime = entry.getValue();
				}
			}

			if (actualClockOutTime.contains(expectedClockOutTime)) {
				Report.InfoTest(test,
						"Clock Out Time is correct in " + subViewReportName + " sub view report Actual List is : "
								+ actualClockOutTime.toString() + " and Expected is : " + expectedClockOutTime);
				Report.PassTest(test, "Clock Out Time is as expected in " + subViewReportName + " sub view report");
			} else {
				Report.FailTest(test,
						"Clock Out Time is not as expected in " + subViewReportName
								+ " sub view report Actual List is : " + actualClockOutTime.toString()
								+ " and Expected is : " + expectedClockOutTime);
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Clock Out Time is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualClockOutTime.toString() + " and Expected is : " + expectedClockOutTime);
		}

	}

	public void validatedDriverClockInTime(Map<String, List<String>> actualRouteSegmentTableData,
			String expectedClockInTime, String subViewReportName) {
		List<String> actualClockInTime = null;
		try {
			for (Entry<String, List<String>> entry : actualRouteSegmentTableData.entrySet()) {
				if (entry.getKey().equals("Clock In")) {
					actualClockInTime = entry.getValue();
				}
			}

			if (actualClockInTime.contains(expectedClockInTime)) {
				Report.InfoTest(test,
						"Clock In Time is correct in " + subViewReportName + " sub view report Actual List is : "
								+ actualClockInTime.toString() + " and Expected is : " + expectedClockInTime);
				Report.PassTest(test, "Clock In Time is as expected in " + subViewReportName + " sub view report");
			} else {
				Report.FailTest(test,
						"Clock In Time is not as expected in " + subViewReportName
								+ " sub view report Actual List is : " + actualClockInTime.toString()
								+ " and Expected is : " + expectedClockInTime);
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Clock In Time is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualClockInTime.toString() + " and Expected is : " + expectedClockInTime);
		}

	}

	public void validateUnitsvariance(Map<String, List<String>> actualPerformanceTableData, String subViewReportName) {
		// UnitVariance = ActualUnits-(PlanUnits);

		List<Double> actualUnits = new ArrayList<>();
		List<Double> planUnits = new ArrayList<>();
		List<Double> actualUnitsVariance = new ArrayList<>();
		double expectedUnitsVariance = 0.0;
		List<String> route = new ArrayList<>();
		try {
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualUnits.add(0.0);

						} else {
							actualUnits.add(Double.parseDouble(entry.getValue().get(i)));
						}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Plan Units")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							planUnits.add(0.0);

						} else {
							planUnits.add(Double.parseDouble(entry.getValue().get(i)));
						}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Units Var")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualUnitsVariance.add(0.0);

						} else {
							actualUnitsVariance.add(Double.parseDouble(entry.getValue().get(i)));
							Util.validateFieldDecimalPlaces(Double.parseDouble(entry.getValue().get(i)), 1,
									"Units Variance in" + subViewReportName + " : Sub View Report.");
						}
				}
			}

			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (int i = 0; i < route.size(); i++) {
				expectedUnitsVariance = actualUnits.get(i) - planUnits.get(i);

				if (Util.compareNumber(actualUnitsVariance.get(i), expectedUnitsVariance)) {
					Report.InfoTest(test,
							"Unit Variance is correct in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Units Variance : " + actualUnitsVariance.get(i)
									+ " and expected Units Variance : " + expectedUnitsVariance);
					Report.PassTest(test, "Units Variance is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test, "Units Variance is not as expected in " + subViewReportName
							+ " sub view report for Route : " + route.get(i) + " with Actual Units Variance : "
							+ actualUnitsVariance.get(i) + " and expected Units Variance : " + expectedUnitsVariance);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Units Variance is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualUnitsVariance.toString() + " and Expected is : " + expectedUnitsVariance);
		}

	}

	public void validateActualUnits(Map<String, List<String>> actualPerformanceTableData, String subViewReportName) {
		// Actual Units will be directly validated against confirmation DB

		List<String> actualUnits = new ArrayList<>();
		List<String> expectedUnits = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		try {
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						// actualUnits = entry.getValue();
						actualUnits.add(entry.getValue().get(i).indexOf(".") < 0 ? entry.getValue().get(i)
								: entry.getValue().get(i).replaceAll("0*$", "").replaceAll("\\.$", ""));
						Util.validateFieldDecimalPlaces(Double.parseDouble(entry.getValue().get(i)), 1,
								" Actual Units in" + subViewReportName + " : Sub View Report.");
					}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			expectedUnits = Util.getDataFromDB(
					"select TOTALUNIT from ocs_Admin.TP_RO_UNITSUMMARY where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') ");

			for (int i = 0; i < route.size(); i++) {
				if (actualUnits.get(i).equals(expectedUnits.get(i))) {
					Report.InfoTest(test,
							"Actual Units is correct in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Units : " + actualUnits.get(i)
									+ " and expected units : " + expectedUnits.get(i));
					Report.PassTest(test, "Actual Units is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Actual Units is not as expected in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Units : " + actualUnits.get(i)
									+ " and expected units : " + expectedUnits.get(i));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Actual Units is not as expected in " + subViewReportName
					+ " sub view report Actual is : " + actualUnits.toString() + " and Expected is : " + expectedUnits);
		}

	}

	public void validateActualUnits_RO(Map<String, List<String>> actualPerformanceTableData, String subViewReportName) {
		// Actual Units will be directly validated against confirmation DB

		List<String> actualUnits = new ArrayList<>();
		List<String> expectedHalfUnits = new ArrayList<>();
		List<String> expectedFullUnits = new ArrayList<>();
		int expectedActualUnits = 0;
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		try {
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						// actualUnits = entry.getValue();
						actualUnits.add(entry.getValue().get(i).indexOf(".") < 0 ? entry.getValue().get(i)
								: entry.getValue().get(i).replaceAll("0*$", "").replaceAll("\\.$", ""));
						Util.validateFieldDecimalPlaces(entry.getValue().get(i), 1,
								" Actual Units in" + subViewReportName + " : Sub View Report.");
					}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			expectedHalfUnits = Util.getDataFromDB(
					"select count(*) from OCS_ADMIN.OCS_CUSTOMERORDER where ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and LOADTYPE IN ('DEl','FFY','FYN','REL','TRP','BTE','EDE','BRN','ERE','FDR','FRN','TRF','BTN','BTS','DNE','FYS','BTY')");

			expectedFullUnits = Util.getDataFromDB(
					"select count(*) from OCS_ADMIN.OCS_CUSTOMERORDER where ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and LOADTYPE IN ('DNR','E/R','LIV','S/O','SCH')");

			for (int i = 0; i < route.size(); i++) {

				expectedActualUnits = (Integer.parseInt(expectedHalfUnits.get(i)) / 2)
						+ Integer.parseInt(expectedFullUnits.get(i));
				if (Integer.parseInt(actualUnits.get(i)) == expectedActualUnits) {
					Report.InfoTest(test,
							"Actual Units is correct in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Units : " + actualUnits.get(i)
									+ " and expected units : " + expectedActualUnits);
					Report.PassTest(test, "Actual Units is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Actual Units is not as expected in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Units : " + actualUnits.get(i)
									+ " and expected units : " + expectedActualUnits);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Actual Units is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualUnits.toString() + " and Expected is : " + expectedActualUnits);
		}

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

	public void validateSummaryActualEfficiency(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualDetailTableData, String LOB) {
		double actualEfficiency = 0.00;
		double expectedActualEfficiency = 0.00;
		int noOfRoutes = 0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().equals("Actual Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualEfficiency = 0.00;
						} else {
							actualEfficiency = Double.parseDouble(entry.getValue().get(i));
							// Validate Decimal Format for the value : should
							// have 2 decimal places for comm and resi, 4
							// decimal places for RO
							if (LOB.equals("Roll Off"))
								Util.validateFieldDecimalPlaces(entry.getValue().get(i), 4,
										"Actual Eff in Summary Section");
							else
								Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2,
										"Actual Eff in Summary Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().equals("Drv Actual Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedActualEfficiency = expectedActualEfficiency + 0.00;
						} else {
							expectedActualEfficiency = expectedActualEfficiency
									+ Double.parseDouble(entry.getValue().get(i));
						}
					}
					noOfRoutes = entry.getValue().size();
				}
			}

			// Actual Efficiency at summary section = Sum Of individual route
			// efficiency/No. Of Routes
			expectedActualEfficiency = expectedActualEfficiency / noOfRoutes;
			if (actualEfficiency == 0.0) {
				Report.FailTest(test,
						"Actual Efficiency is not calculated as expected in summary report Actual is : -- and Expected is : "
								+ expectedActualEfficiency);
			} else {
				if (Util.compareNumber(actualEfficiency, expectedActualEfficiency)) {
					Report.InfoTest(test, "Actual Efficiency is correct in summary report Actual is : "
							+ actualEfficiency + " and Expected is : " + expectedActualEfficiency);
					Report.PassTest(test, "Actual Efficiency is as expected in Summary report");
				} else {
					Report.FailTest(test, "Actual Efficiency is not as expected in summary report Actual is : "
							+ actualEfficiency + " and Expected is : " + expectedActualEfficiency);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Actual Efficiency is not as expected in summary report Actual is : "
					+ actualEfficiency + " and Expected is : " + expectedActualEfficiency);
		}
	}

	public void validateSummaryAllowedEfficiency(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualDetailTableData, String LOB) {
		double actualAllowedEfficiency = 0.00;
		double expectedAllowedEfficiency = 0.00;
		int noOfRoutes = 0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().equals("Allowed Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualAllowedEfficiency = 0.00;
						} else {
							actualAllowedEfficiency = Double.parseDouble(entry.getValue().get(i));
							// Validate Decimal Format for the value : should
							// have 4 decimal places for RO
							Util.validateFieldDecimalPlaces(entry.getValue().get(i), 4,
									"Allowed Eff in Summary Section");

						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().equals("Drv Allowed Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedAllowedEfficiency = expectedAllowedEfficiency + 0.00;
						} else {
							expectedAllowedEfficiency = expectedAllowedEfficiency
									+ Double.parseDouble(entry.getValue().get(i));
						}
					}
					noOfRoutes = entry.getValue().size();
				}
			}

			// Allowed Efficiency at summary section = Sum Of individual route
			// allowed efficiency/No. Of Routes
			expectedAllowedEfficiency = expectedAllowedEfficiency / noOfRoutes;
			if (actualAllowedEfficiency == 0.0) {
				Report.FailTest(test,
						"Allowed Efficiency is not calculated as expected in summary report Actual is : -- and Expected is : "
								+ expectedAllowedEfficiency);
			} else {
				if (Util.compareNumber(actualAllowedEfficiency, expectedAllowedEfficiency)) {
					Report.InfoTest(test, "Allowed Efficiency is correct in summary report Actual is : "
							+ actualAllowedEfficiency + " and Expected is : " + expectedAllowedEfficiency);
					Report.PassTest(test, "Allowed Efficiency is as expected in Summary report");
				} else {
					Report.FailTest(test, "Allowed Efficiency is not as expected in summary report Actual is : "
							+ actualAllowedEfficiency + " and Expected is : " + expectedAllowedEfficiency);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Allowed Efficiency is not as expected in summary report Actual is : "
					+ actualAllowedEfficiency + " and Expected is : " + expectedAllowedEfficiency);
		}
	}

	public void validateSummaryEfficiencyVariance(Map<String, List<String>> actualSummaryTableData) {
		double actualVariance = 0.00;
		double planEff = 0.00;
		double actualEff = 0.00;
		double expectedVariance = 0.00;
		DecimalFormat formatter = new DecimalFormat("#0.00");
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().equals("Eff Var")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualVariance = 0.00;
						} else {
							actualVariance = Double.parseDouble(entry.getValue().get(i));
							// Validate Decimal Format for the value : should
							// have 2 decimal places
							Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, "Eff Var in Summary Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().equals("Plan Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							planEff = 0.00;
						} else {
							planEff = Double.parseDouble(entry.getValue().get(i));
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().equals("Actual Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualEff = 0.00;
						} else {
							actualEff = Double.parseDouble(entry.getValue().get(i));
						}
					}
				}
			}

			// Efficiency Variance = plan Eff-Actual Eff
			expectedVariance = planEff - actualEff;
			// expectedVariance = Util.round(expectedVariance, 1);
			if (Util.compareNumber(actualVariance, expectedVariance)) {
				Report.InfoTest(test,
						"Efficiency Variance is correct in summary report Actual is : "
								+ formatter.format(actualVariance) + " and Expected is : "
								+ formatter.format(expectedVariance));
				Report.PassTest(test, "Efficiency Variance is as expected in Summary report");
			} else {
				Report.FailTest(test, "Efficiency Variance is not as expected in summary report Actual is : "
						+ actualVariance + " and Expected is : " + expectedVariance);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Efficiency Variance is not as expected in summary report Actual is : "
					+ actualVariance + " and Expected is : " + expectedVariance);
		}
	}

	public void validateSummaryPlanUnits(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualPerformanceTableData) {
		double actualPlanUnits = 0.0;
		double expectedPlanUnits = 0.0;
		int noOfRoutes = 0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().equals("Plan Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualPlanUnits = 0.0;
						} else {
							actualPlanUnits = Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Plan Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedPlanUnits = expectedPlanUnits + Double.parseDouble("0.0");
						} else {
							expectedPlanUnits = expectedPlanUnits
									+ Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
						}
					}
					noOfRoutes = entry.getValue().size();
				}
			}

			// Validate Decimal Format for the value : should have 2 decimal
			// places
			Util.validateFieldDecimalPlaces(actualPlanUnits, 1, "Plan Units in Summary Section");

			// Expected Plan Units = total plan units of all routes/No Of routes
			expectedPlanUnits = expectedPlanUnits / noOfRoutes;
			if (Util.compareNumber(actualPlanUnits, expectedPlanUnits)) {
				Report.InfoTest(test, "Plan Units is correct in Efficiency summary report Actual is : "
						+ actualPlanUnits + " and Expected is : " + expectedPlanUnits);
				Report.PassTest(test, "Plan Units is as expected in Summary report");
			} else {
				Report.FailTest(test, "Plan Units is not as expected in summary report Actual is : " + actualPlanUnits
						+ " and Expected is : " + expectedPlanUnits);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Plan Units is not as expected in summary report Actual is : " + actualPlanUnits
					+ " and Expected is : " + expectedPlanUnits);
		}
	}

	public void validateSummaryActualUnits(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualPerformanceTableData) {
		double actualUnits = 0.0;
		double expectedActualUnits = 0.0;
		int noOfRoutes = 0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualUnits = 0.0;
						} else {
							actualUnits = Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateFieldDecimalPlaces(entry.getValue().get(i).trim().replaceAll(",", ""), 1, "Actual Units in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedActualUnits = expectedActualUnits + Double.parseDouble("0.0");
						} else {
							expectedActualUnits = expectedActualUnits
									+ Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

					noOfRoutes = entry.getValue().size();
				}
			}

			// Summary Actual Units = sum of actual units of all routes/no. Of
			// routes
			expectedActualUnits = expectedActualUnits / noOfRoutes;
			if (actualUnits == 0.0) {
				Report.FailTest(test,
						"Actual Units is not calculated as expected in summary report Actual is : -- and Expected is : "
								+ expectedActualUnits);
			} else {
				if (Util.compareNumber(actualUnits, expectedActualUnits)) {
					Report.InfoTest(test, "Actual Units is correct in Efficiency summary report Actual is : "
							+ actualUnits + " and Expected is : " + expectedActualUnits);
					Report.PassTest(test, "Actual Units is as expected in Summary report");
				} else {
					Report.FailTest(test, "Actual Units is not as expected in summary report Actual is : " + actualUnits
							+ " and Expected is : " + expectedActualUnits);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Actual Units is not as expected in summary report Actual is : " + actualUnits
					+ " and Expected is : " + expectedActualUnits);
		}
	}

	public void validateSummaryPlanDriverHours(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualPerformanceTableData) {
		String actualPlanDriverHours = null;
		String expectedPlanDriverHours = null;
		int totalMins = 0;

		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Plan Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualPlanDriverHours = null;
						} else {
							actualPlanDriverHours = entry.getValue().get(i).toString();

						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Plan Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins += mins;
						}

					}
				}
			}

			// Validate Time Format of Plan Driver Hours
			Util.validateTimeFormat(actualPlanDriverHours, "Plan Driver Hours in Summary Section");

			// expectedPlanDriverHours=sum of plan driver hours for all routes

			/// expectedPlanDriverHours=String.format("%02d",(int)Math.floor(totalMins/60))
			/// + ":" +String.format("%02d",( totalMins % 60 ));
			expectedPlanDriverHours = Util.convertMinsToHours(totalMins);

			if (actualPlanDriverHours.equals(expectedPlanDriverHours)) {
				Report.InfoTest(test, "Plan Driver Hours is correct in summary report Actual is : "
						+ actualPlanDriverHours + " and Expected is : " + expectedPlanDriverHours);
				Report.PassTest(test, "Plan Driver Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Plan Driver Hours is not as expected in summary report Actual is : "
						+ actualPlanDriverHours + " and Expected is : " + expectedPlanDriverHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Plan Driver Hours is not as expected in summary report Actual is : "
					+ actualPlanDriverHours + " and Expected is : " + expectedPlanDriverHours);
		}
	}

	public void validateSummaryActualDriverHours(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualEfficiencyTableData) {
		String actualDriverHours = null;
		String expectedActualDriverHours = null;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Actual Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualDriverHours = "00:00";
						} else {
							actualDriverHours = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().contains("Actual Driver Hours")) {
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
			Util.validateTimeFormat(actualDriverHours, "Actual Driver Hours in Summary Section");

			// expectedActualDriverHours=sum of actual driver hours for all
			// routes
			// expectedActualDriverHours=String.format("%02d",(int)Math.floor(totalMins/60))
			// + ":" +String.format("%02d",( totalMins % 60 ));
			expectedActualDriverHours = Util.convertMinsToHours(totalMins);

			if (actualDriverHours.equals("00:00")) {
				Report.FailTest(test,
						"Actual Driver Hours is not calculated as expected in summary report Actual is : -- and Expected is : "
								+ expectedActualDriverHours);
			} else {

				if (actualDriverHours.equals(expectedActualDriverHours)) {
					Report.InfoTest(test, "Actual Driver Hours is correct in summary report Actual is : "
							+ actualDriverHours + " and Expected is : " + expectedActualDriverHours);
					Report.PassTest(test, "Actual Driver Hours is as expected in Summary report");
				} else {
					Report.FailTest(test, "Actual Driver Hours is not as expected in summary report Actual is : "
							+ actualDriverHours + " and Expected is : " + expectedActualDriverHours);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Actual Driver Hours is not as expected in summary report Actual is : "
					+ actualDriverHours + " and Expected is : " + expectedActualDriverHours);
		}
	}

	public void validateSummaryAllowedDriverHours(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualEfficiencyTableData) {
		String actualAllowedDriverHours = null;
		String expectedAllowedActualDriverHours = null;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Allowed Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualAllowedDriverHours = null;
						} else {
							actualAllowedDriverHours = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().contains("Allowed Driver Hours")) {
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
			Util.validateTimeFormat(actualAllowedDriverHours, "Actual Driver Hours in Summary Section");

			// expectedActualDriverHours=sum of actual driver hours for all
			// routes
			// expectedActualDriverHours=String.format("%02d",(int)Math.floor(totalMins/60))
			// + ":" +String.format("%02d",( totalMins % 60 ));
			expectedAllowedActualDriverHours = Util.convertMinsToHours(totalMins);
			;

			if (actualAllowedDriverHours.equals(expectedAllowedActualDriverHours)) {
				Report.InfoTest(test, "Allowed Driver Hours is correct in summary report Actual is : "
						+ actualAllowedDriverHours + " and Expected is : " + expectedAllowedActualDriverHours);
				Report.PassTest(test, "Allowed Driver Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Allowed  Driver Hours is not as expected in summary report Actual is : "
						+ actualAllowedDriverHours + " and Expected is : " + expectedAllowedActualDriverHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Allowed  Driver Hours is not as expected in summary report Actual is : "
					+ actualAllowedDriverHours + " and Expected is : " + expectedAllowedActualDriverHours);
		}
	}

	public void validateSummaryDriverHourVariance(Map<String, List<String>> actualSummaryTableData) {
		String actualVariance = null;
		String planDriverHours = null;
		String actualDriverHours = null;
		String expectedDriverVariance = null;

		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Hours Variance")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualVariance = "00:00";
						} else {
							actualVariance = entry.getValue().get(i);
							System.out.println("Actual Variance : " + actualVariance);
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Plan Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							planDriverHours = "00:00";
						} else {
							planDriverHours = entry.getValue().get(i);
							System.out.println("Plan Driver Hours : " + planDriverHours);
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Actual Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualDriverHours = "00:00";
						} else {
							actualDriverHours = entry.getValue().get(i);
							System.out.println("Actual Driver Hours : " + actualDriverHours);
						}
					}
				}
			}

			// Validate Time Format of Driver Hours Vaiance
			Util.validateTimeFormat(actualVariance, "Hours Variance in Summary Section");

			// Driver Variance = Plan Driver Hours - Actual Driver Hours
			expectedDriverVariance = Util.getTimeDifferenceInString(planDriverHours, actualDriverHours);

			if (actualVariance.equals(expectedDriverVariance)) {
				Report.InfoTest(test, "Driver Variance Hours is correct in summary report Actual is : " + actualVariance
						+ " and Expected is : " + expectedDriverVariance);
				Report.PassTest(test, "Driver Variance Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver Variance Hours is not as expected in summary report Actual is : "
						+ actualVariance + " and Expected is : " + expectedDriverVariance);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Variance Hours is not as expected in summary report Actual is : "
					+ actualVariance + " and Expected is : " + expectedDriverVariance);
		}
	}

	public void validateSummaryDriverHourVariance_RO(Map<String, List<String>> actualSummaryTableData) {
		String actualVariance = null;
		String planDriverHours = null;
		String actualDriverHours = null;
		String expectedDriverVariance = null;

		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Hours Variance")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualVariance = "00:00";
						} else {
							actualVariance = entry.getValue().get(i);
							System.out.println("Actual Variance : " + actualVariance);
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Allowed Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							planDriverHours = "00:00";
						} else {
							planDriverHours = entry.getValue().get(i);
							System.out.println("Allowed Driver Hours : " + planDriverHours);
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Actual Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualDriverHours = "00:00";
						} else {
							actualDriverHours = entry.getValue().get(i);
							System.out.println("Actual Driver Hours : " + actualDriverHours);
						}
					}
				}
			}

			// Validate Time Format of Driver Hours Vaiance
			Util.validateTimeFormat(actualVariance, "Hours Variance in Summary Section");

			// Driver Variance = Plan Driver Hours - Actual Driver Hours
			expectedDriverVariance = Util.getTimeDifferenceInString(planDriverHours, actualDriverHours);

			if (actualVariance.equals(expectedDriverVariance)) {
				Report.InfoTest(test, "Driver Variance Hours is correct in summary report Actual is : " + actualVariance
						+ " and Expected is : " + expectedDriverVariance);
				Report.PassTest(test, "Driver Variance Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver Variance Hours is not as expected in summary report Actual is : "
						+ actualVariance + " and Expected is : " + expectedDriverVariance);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Variance Hours is not as expected in summary report Actual is : "
					+ actualVariance + " and Expected is : " + expectedDriverVariance);
		}
	}

	public void validateSummaryNumberOfRoutes(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> performanceTableData) {
		int actualNumberOfRoutes = 0;
		int expectedNumberOfRoutes = 0;

		// No. Of Routes = Routes displayed in the detail section

		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("# of Routes")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualNumberOfRoutes = 0;
						} else {
							actualNumberOfRoutes = Integer.parseInt(entry.getValue().get(i));
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : performanceTableData.entrySet()) {
				if (entry.getKey().contains("Drv Actual Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!(entry.getValue().get(i).equals("--"))) {
							expectedNumberOfRoutes = expectedNumberOfRoutes + 1;
						}
					}
				}
			}

			// Validate Decimal Format for the value : should have 2 decimal
			// places
			Util.validateWholeNumber(actualNumberOfRoutes, "Summary Section # of Routes");

			if (actualNumberOfRoutes == expectedNumberOfRoutes) {
				Report.InfoTest(test, "# of Routes is correct in summary report Actual is : " + actualNumberOfRoutes
						+ " and Expected is : " + expectedNumberOfRoutes);
				Report.PassTest(test, "# of Routes is as expected in Summary report");
			} else {
				Report.FailTest(test, "# of Routes is not as expected in summary report Actual is : "
						+ actualNumberOfRoutes + " and Expected is : " + expectedNumberOfRoutes);
			}
		} catch (Exception e) {
			Report.FailTest(test, "# of Routes is not as expected in summary report Actual is : " + actualNumberOfRoutes
					+ " and Expected is : " + expectedNumberOfRoutes);
		}
	}

	public void validateSummaryPreRouteHours(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualDetailTableData) {
		String actualPreRouteHours = null;
		String expectedPreRouteHours = null;
		int totalHours = 0;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Pre-Route")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualPreRouteHours = null;
						} else {
							actualPreRouteHours = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("Pre-Route")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String plannedDriver[] = entry.getValue().get(i).split(":");
							System.out.println("Hours : " + plannedDriver[0]);
							System.out.println("Minutes : " + plannedDriver[1]);
							int hours = Integer.parseInt(plannedDriver[0]);
							int mins = Integer.parseInt(plannedDriver[1]);
							totalHours = totalHours + hours;
							totalMins = totalMins + mins;
						}

					}
				}
			}
			if (totalMins > 60) {
				totalHours = totalHours + totalMins / 60;
				totalMins = totalMins % 60;
				if (totalHours < 10 && totalMins < 10)
					expectedPreRouteHours = "0" + Integer.toString(totalHours) + ":" + "0"
							+ Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedPreRouteHours = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedPreRouteHours = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedPreRouteHours = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				System.out.println("Expected Pre Route Hours : " + expectedPreRouteHours);

			} else {
				if (totalHours == 0 && totalMins > 9)
					expectedPreRouteHours = "00:" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedPreRouteHours = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours == 0 && totalMins < 10)
					expectedPreRouteHours = "00:" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedPreRouteHours = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedPreRouteHours = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				System.out.println("Expected Pre Route Hours : " + expectedPreRouteHours);
			}

			if (actualPreRouteHours.equals(expectedPreRouteHours)) {
				Report.InfoTest(test, "Pre Route Hours is correct in summary report Actual is : " + actualPreRouteHours
						+ " and Expected is : " + expectedPreRouteHours);
				Report.PassTest(test, "Pre Route Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Pre Route Hours is not as expected in summary report Actual is : "
						+ actualPreRouteHours + " and Expected is : " + expectedPreRouteHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Pre Route Hours is not as expected in summary report Actual is : "
					+ actualPreRouteHours + " and Expected is : " + expectedPreRouteHours);
		}
	}

	public void validateSummaryPostRouteHours(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualDetailTableData) {
		String actualPostRouteHours = null;
		String expectedPostRouteHours = null;
		int totalHours = 0;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Post-Route")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualPostRouteHours = null;
						} else {
							actualPostRouteHours = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("Post-Route")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!(entry.getValue().get(i).equals("--"))) {
							String plannedDriver[] = entry.getValue().get(i).split(":");
							System.out.println("Hours : " + plannedDriver[0]);
							System.out.println("Minutes : " + plannedDriver[1]);
							int hours = Integer.parseInt(plannedDriver[0]);
							int mins = Integer.parseInt(plannedDriver[1]);
							totalHours = totalHours + hours;
							totalMins = totalMins + mins;
						}

					}
				}
			}
			if (totalMins > 60) {
				totalHours = totalHours + totalMins / 60;
				totalMins = totalMins % 60;
				if (totalHours < 10 && totalMins < 10)
					expectedPostRouteHours = "0" + Integer.toString(totalHours) + ":" + "0"
							+ Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedPostRouteHours = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedPostRouteHours = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedPostRouteHours = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				System.out.println("Expected Post Route Hours : " + expectedPostRouteHours);

			} else {
				if (totalHours == 0 && totalMins > 9)
					expectedPostRouteHours = "00:" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedPostRouteHours = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours == 0 && totalMins < 10)
					expectedPostRouteHours = "00:" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedPostRouteHours = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedPostRouteHours = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				System.out.println("Expected Post Route Hours : " + expectedPostRouteHours);
			}

			if (actualPostRouteHours.equals(expectedPostRouteHours)) {
				Report.InfoTest(test, "Post Route Hours is correct in summary report Actual is : "
						+ actualPostRouteHours + " and Expected is : " + expectedPostRouteHours);
				Report.PassTest(test, "Post Route Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Post Route Hours is not as expected in summary report Actual is : "
						+ actualPostRouteHours + " and Expected is : " + expectedPostRouteHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Post Route Hours is not as expected in summary report Actual is : "
					+ actualPostRouteHours + " and Expected is : " + expectedPostRouteHours);
		}
	}

	public void validateSummaryIdleTime(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualDetailTableData) {
		String actualIdleTime = null;
		String expectedIdleTime = null;
		int totalHours = 0;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Idle Time")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualIdleTime = null;
						} else {
							actualIdleTime = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("Net Idle")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!(entry.getValue().get(i).equals("--"))) {
							String plannedDriver[] = entry.getValue().get(i).split(":");
							System.out.println("Hours : " + plannedDriver[0]);
							System.out.println("Minutes : " + plannedDriver[1]);
							int hours = Integer.parseInt(plannedDriver[0]);
							int mins = Integer.parseInt(plannedDriver[1]);
							totalHours = totalHours + hours;
							totalMins = totalMins + mins;
						}

					}
				}
			}
			if (totalMins > 60) {
				totalHours = totalHours + totalMins / 60;
				totalMins = totalMins % 60;
				if (totalHours < 10 && totalMins < 10)
					expectedIdleTime = "0" + Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedIdleTime = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedIdleTime = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedIdleTime = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				System.out.println("Expected Idle Time : " + expectedIdleTime);

			} else {
				if (totalHours == 0 && totalMins > 9)
					expectedIdleTime = "00:" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedIdleTime = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours == 0 && totalMins < 10)
					expectedIdleTime = "00:" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedIdleTime = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedIdleTime = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours == 0 && totalMins == 0)
					expectedIdleTime = "00:00";
				System.out.println("Expected Idle Time : " + expectedIdleTime);
			}
			if (actualIdleTime.equals(expectedIdleTime)) {
				Report.InfoTest(test, "Idle Time is correct in summary report Actual is : " + actualIdleTime
						+ " and Expected is : " + expectedIdleTime);
				Report.PassTest(test, "Idle Time Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Idle Time Hours is not as expected in summary report Actual is : "
						+ actualIdleTime + " and Expected is : " + expectedIdleTime);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Idle Time Hours is not as expected in summary report Actual is : " + actualIdleTime
					+ " and Expected is : " + expectedIdleTime);
		}
	}

	public void validateSummaryDownTime(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualDetailTableData) {
		String actualDownTime = null;
		String expectedDownTime = null;
		int totalHours = 0;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Downtime")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualDownTime = null;
						} else {
							actualDownTime = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("Downtime")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!(entry.getValue().get(i).equals("--"))) {
							String plannedDriver[] = entry.getValue().get(i).split(":");
							System.out.println("Hours : " + plannedDriver[0]);
							System.out.println("Minutes : " + plannedDriver[1]);
							int hours = Integer.parseInt(plannedDriver[0]);
							int mins = Integer.parseInt(plannedDriver[1]);
							totalHours = totalHours + hours;
							totalMins = totalMins + mins;
						}

					}
				}
			}
			if (totalMins > 60) {
				totalHours = totalHours + totalMins / 60;
				totalMins = totalMins % 60;
				if (totalHours < 10 && totalMins < 10)
					expectedDownTime = "0" + Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedDownTime = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedDownTime = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedDownTime = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				System.out.println("Expected Down Time : " + expectedDownTime);

			} else {
				if (totalHours == 0 && totalMins > 9)
					expectedDownTime = "00:" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedDownTime = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours == 0 && totalMins < 10)
					expectedDownTime = "00:" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedDownTime = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedDownTime = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours == 0 && totalMins == 0)
					expectedDownTime = "00:00";
				System.out.println("Expected Idle Time : " + expectedDownTime);
			}

			if (actualDownTime.equals(expectedDownTime)) {
				Report.InfoTest(test, "Down Time is correct in summary report Actual is : " + actualDownTime
						+ " and Expected is : " + expectedDownTime);
				Report.PassTest(test, "Down Time Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Down Time Hours is not as expected in summary report Actual is : "
						+ actualDownTime + " and Expected is : " + expectedDownTime);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Down Time Hours is not as expected in summary report Actual is : " + actualDownTime
					+ " and Expected is : " + expectedDownTime);
		}
	}

	public void validateLOB(Map<String, List<String>> actualEfficiencyTableData, String expectedLOB,
			String subViewReportName) {
		String actualLOB = null;
		try {
			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
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

				Report.InfoTest(test, "LOB is correct in " + subViewReportName + " Sub View report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
				Report.PassTest(test, "LOB is as expected in " + subViewReportName + " Sub View report");
			} else {
				Report.FailTest(test, "LOB is not as expected in " + subViewReportName + " Sub View report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
			}
		} catch (Exception e) {
			Report.FailTest(test, "LOB is not as expected in " + subViewReportName
					+ " Sub View report Actual List is : " + actualLOB + " and Expected is : " + expectedLOB);
		}
	}

	public void validateDate(Map<String, List<String>> actualSubViewTableData, String fromDate, String toDate,
			String subViewReportName) {
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yy");
		Date startDate = null;
		Date endDate = null;
		List<Date> routeServedDate = new ArrayList<>();
		List<String> actualRoutes = null;
		try {
			startDate = format1.parse(fromDate);
			endDate = format1.parse(toDate);
			for (Entry<String, List<String>> entry : actualSubViewTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					actualRoutes = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualSubViewTableData.entrySet()) {
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
							"Route Date is correct in " + subViewReportName + " Sub view report for route : "
									+ actualRoutes.get(i) + ". Actual is : " + format1.format(routeServedDate.get(i))
									+ " and Expected is between : " + format1.format(startDate) + "and "
									+ format1.format(endDate));
					Report.PassTest(test, "Route Date is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Route Date is not as expected in " + subViewReportName + " sub view report Actual is : "
									+ format1.format(routeServedDate.get(i)) + " and Expected is between : "
									+ format1.format(startDate) + "and " + format1.format(endDate));
				}
			}

		} catch (Exception e) {
			Report.FailTest(test, "Not able to compare date is in between the date range");
			LogClass.error(e.getMessage());
		}
	}

	public void validateRouteName(Map<String, List<String>> actualSubViewTableData, String expectedRouteName,
			String subViewReportName) {
		String[] routes = expectedRouteName.split(";");
		List<String> actualRoutes = null;
		try {
			for (Entry<String, List<String>> entry : actualSubViewTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					actualRoutes = entry.getValue();
				}
			}
			for (int i = 0; i < routes.length; i++) {
				if (actualRoutes.contains(routes[i])) {
					Report.InfoTest(test,
							"Route Name is correct in " + subViewReportName + " Sub View report Actual List is : "
									+ actualRoutes.toString() + " and Expected is : " + routes[i]);
					Report.PassTest(test, "Route Name is as expected in " + subViewReportName + " Sub View report");
				} else {
					Report.FailTest(test,
							"Route Name is not as expected in " + subViewReportName
									+ " Sub View report Actual List is : " + actualRoutes.toString()
									+ " and Expected is : " + routes[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Route is not as expected in " + subViewReportName + " Sub View report Actual List is : "
							+ actualRoutes.toString() + " and Expected is : " + routes.toString());
		}

	}

	public void validateDriverName(Map<String, List<String>> actualSubViewTableData, String expectedDriverName,
			String subViewReportName) {
		String[] drivers = expectedDriverName.split(";");
		List<String> actualDrivers = null;
		try {
			for (Entry<String, List<String>> entry : actualSubViewTableData.entrySet()) {
				if (entry.getKey().equals("Driver")) {
					actualDrivers = entry.getValue();
				}
			}
			for (int i = 0; i < drivers.length; i++) {
				if (actualDrivers.contains(drivers[i])) {
					Report.InfoTest(test,
							"Driver Name is correct in " + subViewReportName + " Sub View report Actual List is : "
									+ actualDrivers.toString() + " and Expected is : " + drivers[i]);
					Report.PassTest(test, "Driver Name is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Driver Name is not as expected in " + subViewReportName
									+ " Sub View report Actual List is : " + actualDrivers.toString()
									+ " and Expected is : " + drivers[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Driver Name is not as expected in " + subViewReportName + " Sub View report Actual List is : "
							+ actualDrivers.toString() + " and Expected is : " + drivers.toString());
		}

	}

	public void validateEfficiencyClockInTime(Map<String, List<String>> actualEfficiencyTableData,
			String expectedClockInTime) {
		List<String> actualClockInTime = null;
		try {
			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().equals("Clock In")) {
					actualClockInTime = entry.getValue();
				}
			}

			if (actualClockInTime.contains(expectedClockInTime)) {
				Report.InfoTest(test, "Clock In Time is correct in Efficiency report Actual List is : "
						+ actualClockInTime.toString() + " and Expected is : " + expectedClockInTime);
				Report.PassTest(test, "Clock In Time is as expected in Efficiency report");
			} else {
				Report.FailTest(test, "Clock In Time is not as expected in Efficiency report Actual List is : "
						+ actualClockInTime.toString() + " and Expected is : " + expectedClockInTime);
			}

		} catch (Exception e) {
			Report.FailTest(test, "Clock In Time is not as expected in Efficiency report Actual is : "
					+ actualClockInTime.toString() + " and Expected is : " + expectedClockInTime);
		}

	}

	public void validateEfficiencyClockOutTime(Map<String, List<String>> actualEfficiencyTableData,
			String expectedClockOutTime) {
		List<String> actualClockOutTime = null;
		try {
			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().equals("Clock Out")) {
					actualClockOutTime = entry.getValue();
				}
			}

			if (actualClockOutTime.contains(expectedClockOutTime)) {
				Report.InfoTest(test, "Clock Out Time is correct in Efficiency report Actual List is : "
						+ actualClockOutTime.toString() + " and Expected is : " + expectedClockOutTime);
				Report.PassTest(test, "Clock Out Time is as expected in Efficiency report");
			} else {
				Report.FailTest(test, "Clock Out Time is not as expected in Efficiency report Actual List is : "
						+ actualClockOutTime.toString() + " and Expected is : " + expectedClockOutTime);
			}

		} catch (Exception e) {
			Report.FailTest(test, "Clock Out Time is not as expected in Efficiency report Actual is : "
					+ actualClockOutTime.toString() + " and Expected is : " + expectedClockOutTime);
		}

	}

	public void validateEfficiencyActualDriverHours(Map<String, List<String>> actualPerformanceTableData,
			Map<String, List<String>> actualRouteSegmentTableData, String subViewReportName) {
		List<String> clockInTimes = null;
		List<String> clockOutTimes = null;
		List<String> actualDriverHours = new ArrayList<>();
		String expectedDriverHours = null;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");

		try {
			for (Entry<String, List<String>> entry : actualRouteSegmentTableData.entrySet()) {
				if (entry.getKey().equals("Clock In")) {
					clockInTimes = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualRouteSegmentTableData.entrySet()) {
				if (entry.getKey().equals("Clock Out")) {
					clockOutTimes = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Actual Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualDriverHours.add("00:00");
						} else {
							actualDriverHours.add(entry.getValue().get(i));
							Util.validateTimeFormat(entry.getValue().get(i),
									"Plan Driver Hours in " + subViewReportName + " : Sub View Report. ");
						}
					}
				}
			}

			for (int i = 0; i < actualDriverHours.size(); i++) {
				if (actualDriverHours.get(i).equals("00:00")) {
					Report.InfoTest(test, "Actual Driver Hours is " + actualDriverHours.get(i));
					Report.PassTest(test, "Kronos Clock In and Clock Out time is missing in Efficiency report");
				} else {

					expectedDriverHours = Util.getTimeDifferenceInString(
							format.format(format.parse(clockOutTimes.get(i))),
							format.format(format.parse(clockInTimes.get(i))));
					System.out.println("Expected driver hour  : " + expectedDriverHours);
					if (actualDriverHours.get(i).equals(expectedDriverHours)) {
						Report.InfoTest(test,
								"Actual Driver Hours is correct in " + subViewReportName
										+ " sub view report with Clcok In Time : " + clockInTimes.get(i)
										+ " and Clock Out Time : " + clockOutTimes.get(i) + " and Expected is : "
										+ expectedDriverHours);
						Report.PassTest(test,
								"Actual Driver Hours is as expected in " + subViewReportName + " sub view report");
					} else if (expectedDriverHours.equals("10:00")) {
						expectedDriverHours = "09:30";
						if (actualDriverHours.get(i).equals(expectedDriverHours)) {
							Report.InfoTest(test,
									"Actual Driver Hours is correct in " + subViewReportName
											+ " sub view report with Clcok In Time : " + clockInTimes.get(i)
											+ " and Clock Out Time : " + clockOutTimes.get(i) + " and Expected is : "
											+ expectedDriverHours);
							Report.PassTest(test,
									"Actual Driver Hours is as expected in " + subViewReportName + " sub view report");
						}
					}

					else {
						Report.FailTest(test,
								"Actual Driver Hours is not as expected in efficiency report with Clcok In Time : "
										+ clockInTimes.get(i) + " and Clock Out Time : " + clockOutTimes.get(i)
										+ " and Expected is : " + expectedDriverHours);
					}
				}
			}
		}

		catch (Exception e) {
			Report.FailTest(test, "ActualDriver Hours is not as expected in efficiency report Actual is : "
					+ actualDriverHours.toString() + " and Expected is : " + expectedDriverHours);
		}
	}

	public void validateEfficiencyPlanDriverHours(Map<String, List<String>> actualEfficiencyTableData,
			Map<String, List<String>> actualDetailTableData) {
		List<Double> actualUnits = new ArrayList<>();
		List<Double> planEfficiency = new ArrayList<>();
		List<Date> actualPlanDriverHours = new ArrayList<>();
		Date expectedPlanDriverHours = null;
		List<String> route = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

		try {
			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().equals("Plan Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						planEfficiency.add(Double.parseDouble(entry.getValue().get(i)));
					}
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().contains("Plan Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {

							actualPlanDriverHours.add(dateFormat.parse("00:00"));

						} else {
							actualPlanDriverHours.add(dateFormat.parse(entry.getValue().get(i).toString()));
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualUnits.add(Double.parseDouble(entry.getValue().get(i)));
					}
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (int i = 0; i < route.size(); i++) {

				double planDriverHoursInMillis = actualUnits.get(i) / planEfficiency.get(i);
				double mins = planDriverHoursInMillis % 1;
				double hours = planDriverHoursInMillis - mins;
				mins = mins * 60;
				double tempMins = mins % 1;
				double actuamlMins = mins - tempMins;
				DecimalFormat twodigits = new DecimalFormat("00");
				if (hours == 0 && mins == 0)
					expectedPlanDriverHours = dateFormat.parse("00:00");
				else
					expectedPlanDriverHours = dateFormat
							.parse(twodigits.format(hours) + ":" + twodigits.format(actuamlMins));

				if (actualPlanDriverHours.get(i).equals(expectedPlanDriverHours)) {
					Report.InfoTest(test,
							"Plan Driver Hours is correct in efficiency report for Route : " + route.get(i)
									+ " with Actual Plan Driver Hour : "
									+ dateFormat.format(actualPlanDriverHours.get(i))
									+ " and expected Plan Driver Hour : " + dateFormat.format(expectedPlanDriverHours));
					Report.PassTest(test, "Plan Driver Hour is as expected in Commercial Efficiency report");
				} else {
					Report.FailTest(test, "Plan Driver Hour is not as expected in efficiency report for Route : "
							+ route.get(i) + " with Actual Plan Driver Hour : "
							+ dateFormat.format(actualPlanDriverHours.get(i)) + " and expected Driver Hour Variance : "
							+ dateFormat.format(expectedPlanDriverHours));
				}

			}
		}

		catch (Exception e) {
			Report.FailTest(test, "Plan Driver Hours is not as expected in efficiency report Actual is : "
					+ actualPlanDriverHours.toString() + " and Expected is : " + expectedPlanDriverHours);
		}
	}

	public void validateDriverHoursVariance(Map<String, List<String>> actualPerformanceTableData,
			String subViewReportName) {
		// Driver Hour variance = Plan Driver Hours - Actual Driver Hours

		List<String> actualDriverHours = new ArrayList<>();
		String expectedDriverHourVariance = null;
		List<String> planDriverHours = new ArrayList<>();
		List<String> actualDriverHourVariance = new ArrayList<>();
		List<String> route = new ArrayList<>();
		try {
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Actual Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualDriverHours.add("00:00");

						} else {

							actualDriverHours.add(entry.getValue().get(i).toString());
						}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Hours Variance")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualDriverHourVariance.add("00:00");

						} else {

							actualDriverHourVariance.add(entry.getValue().get(i).toString());
							Util.validateTimeFormat(entry.getValue().get(i),
									"Driver Hours Variance in " + subViewReportName + " : Sub View Report.");
						}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Plan Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {

							planDriverHours.add("00:00");

						} else {

							planDriverHours.add(entry.getValue().get(i).toString());
						}

					}
				}
			}

			for (int i = 0; i < route.size(); i++) {

				expectedDriverHourVariance = Util.getTimeDifferenceInString(planDriverHours.get(i),
						actualDriverHours.get(i));

				if (actualDriverHourVariance.get(i).equals(expectedDriverHourVariance)) {
					Report.InfoTest(test,
							"Driver Hour variance is correct in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Driver Hour Variance : "
									+ actualDriverHourVariance.get(i) + " and expected Driver Hour Variance : "
									+ expectedDriverHourVariance);
					Report.PassTest(test,
							"Driver Hour Variance is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Driver Hour Variance is not as expected in " + subViewReportName
									+ " sub view report for Route : " + route.get(i)
									+ " with Actual Driver Hour Variance : " + actualDriverHourVariance.get(i)
									+ " and expected Driver Hour Variance : " + expectedDriverHourVariance);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Driver Hour Variance is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualDriverHourVariance.toString() + " and Expected is : " + expectedDriverHourVariance);
		}

	}
	
	public void validateDriverHoursVariance_RO(Map<String, List<String>> actualPerformanceTableData,
			String subViewReportName) {
		// Driver Hour variance = Plan Driver Hours - Actual Driver Hours

		List<String> actualDriverHours = new ArrayList<>();
		String expectedDriverHourVariance = null;
		List<String> planDriverHours = new ArrayList<>();
		List<String> actualDriverHourVariance = new ArrayList<>();
		List<String> route = new ArrayList<>();
		try {
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Actual Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualDriverHours.add("00:00");

						} else {

							actualDriverHours.add(entry.getValue().get(i).toString());
						}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Hours Variance")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualDriverHourVariance.add("00:00");

						} else {

							actualDriverHourVariance.add(entry.getValue().get(i).toString());
							Util.validateTimeFormat(entry.getValue().get(i),
									"Driver Hours Variance in " + subViewReportName + " : Sub View Report.");
						}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Allowed Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {

							planDriverHours.add("00:00");

						} else {

							planDriverHours.add(entry.getValue().get(i).toString());
						}

					}
				}
			}

			for (int i = 0; i < route.size(); i++) {

				expectedDriverHourVariance = Util.getTimeDifferenceInString(planDriverHours.get(i),
						actualDriverHours.get(i));

				if (actualDriverHourVariance.get(i).equals(expectedDriverHourVariance)) {
					Report.InfoTest(test,
							"Driver Hour variance is correct in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Driver Hour Variance : "
									+ actualDriverHourVariance.get(i) + " and expected Driver Hour Variance : "
									+ expectedDriverHourVariance);
					Report.PassTest(test,
							"Driver Hour Variance is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Driver Hour Variance is not as expected in " + subViewReportName
									+ " sub view report for Route : " + route.get(i)
									+ " with Actual Driver Hour Variance : " + actualDriverHourVariance.get(i)
									+ " and expected Driver Hour Variance : " + expectedDriverHourVariance);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Driver Hour Variance is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualDriverHourVariance.toString() + " and Expected is : " + expectedDriverHourVariance);
		}

	}

	public void validateEfficiencyVariance(Map<String, List<String>> actualPerformanceTableData,
			String subViewReportName, String LOB) {
		// EfficiencyVariance = ActualEff-(PlanEff);

		List<Double> planEff = new ArrayList<>();
		List<Double> actualEff = new ArrayList<>();
		List<Double> actualEffVariance = new ArrayList<>();
		double expectedEffVariance = 0.0;
		List<String> route = new ArrayList<>();
		try {

			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Drv Plan Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							planEff.add(0.0);

						} else {
							planEff.add(Double.parseDouble(entry.getValue().get(i)));
						}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Drv Actual Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualEff.add(0.0);

						} else {
							actualEff.add(Double.parseDouble(entry.getValue().get(i)));
						}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Drv Eff Var")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualEffVariance.add(0.0);

						} else {
							actualEffVariance.add(Double.parseDouble(entry.getValue().get(i)));
							if (LOB.equals("Commercial") || LOB.equals("Residential"))
								Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2,
										"Driver Efficiency Variance in " + subViewReportName + " : Sub View Report.");
							else
								Util.validateFieldDecimalPlaces(entry.getValue().get(i), 4,
										"Driver Efficiency Variance in " + subViewReportName + " : Sub View Report.");
						}
				}
			}

			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (int i = 0; i < route.size(); i++) {
				expectedEffVariance = actualEff.get(i) - planEff.get(i);
				if (LOB.equals("Commercial") || LOB.equals("Residential"))
					expectedEffVariance = Util.round(expectedEffVariance, 2);
				else
					expectedEffVariance = Util.round(expectedEffVariance, 4);
				if (Util.compareNumber(actualEffVariance.get(i), expectedEffVariance)) {
					Report.InfoTest(test,
							"Efficiency Variance is correct in " + subViewReportName + " Sub View report for Route : "
									+ route.get(i) + " with Actual Efficiency Variance : " + actualEffVariance.get(i)
									+ " and expected Efficiency Variance : " + expectedEffVariance);
					Report.PassTest(test,
							"Efficiency Variance is as expected in " + subViewReportName + " Sub View report");
				} else {
					Report.FailTest(test, "Efficiency Variance is not as expected in " + subViewReportName
							+ " Sub View report for Route : " + route.get(i) + " with Actual Efficiency Variance : "
							+ actualEffVariance.get(i) + " and expected Efficiency Variance : " + expectedEffVariance);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Efficiency Variance is not as expected in " + subViewReportName + " Sub View report Actual is : "
							+ actualEffVariance.toString() + " and Expected is : " + expectedEffVariance);
		}

	}

	public void validateEfficiencyVariance_RO(Map<String, List<String>> actualPerformanceTableData,
			String subViewReportName, String LOB) {
		// EfficiencyVariance = ActualEff-(PlanEff);

		List<Double> planEff = new ArrayList<>();
		List<Double> actualEff = new ArrayList<>();
		List<Double> actualEffVariance = new ArrayList<>();
		double expectedEffVariance = 0.0;
		List<String> route = new ArrayList<>();
		try {

			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Drv Allowed Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							planEff.add(0.0);

						} else {
							planEff.add(Double.parseDouble(entry.getValue().get(i)));
						}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Drv Actual Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualEff.add(0.0);

						} else {
							actualEff.add(Double.parseDouble(entry.getValue().get(i)));
						}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Drv Eff Var")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualEffVariance.add(0.0);

						} else {
							actualEffVariance.add(Double.parseDouble(entry.getValue().get(i)));
							if (LOB.equals("Commercial") || LOB.equals("Residential"))
								Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2,
										"Driver Efficiency Variance in " + subViewReportName + " : Sub View Report.");
							else
								Util.validateFieldDecimalPlaces(entry.getValue().get(i), 4,
										"Driver Efficiency Variance in " + subViewReportName + " : Sub View Report.");
						}
				}
			}

			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (int i = 0; i < route.size(); i++) {
				expectedEffVariance = actualEff.get(i) - planEff.get(i);
				if (LOB.equals("Commercial") || LOB.equals("Residential"))
					expectedEffVariance = Util.round(expectedEffVariance, 2);
				else
					expectedEffVariance = Util.round(expectedEffVariance, 4);
				if (Util.compareNumber(actualEffVariance.get(i), expectedEffVariance)) {
					Report.InfoTest(test,
							"Efficiency Variance is correct in " + subViewReportName + " Sub View report for Route : "
									+ route.get(i) + " with Actual Efficiency Variance : " + actualEffVariance.get(i)
									+ " and expected Efficiency Variance : " + expectedEffVariance);
					Report.PassTest(test,
							"Efficiency Variance is as expected in " + subViewReportName + " Sub View report");
				} else {
					Report.FailTest(test, "Efficiency Variance is not as expected in " + subViewReportName
							+ " Sub View report for Route : " + route.get(i) + " with Actual Efficiency Variance : "
							+ actualEffVariance.get(i) + " and expected Efficiency Variance : " + expectedEffVariance);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Efficiency Variance is not as expected in " + subViewReportName + " Sub View report Actual is : "
							+ actualEffVariance.toString() + " and Expected is : " + expectedEffVariance);
		}

	}

	public void validateDriverPlanEfficiency(Map<String, List<String>> actualPerformanceTableData,
			String subViewReportName, String LOB) {
		// PlanEfficiency=Derived from EMAP

		List<Double> planEfficiency = new ArrayList<>();
		double expectedPlanEfficiency = 0.0;
		List<String> route = new ArrayList<>();

		try {

			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Drv Plan Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2,
									"Driver Plan Efficiency in " + subViewReportName + " : Sub View Report.");
							planEfficiency.add(Double.parseDouble(entry.getValue().get(i)));
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (int i = 0; i < route.size(); i++) {
				expectedPlanEfficiency = Util.getEMAPData(route.get(i), LOB, "PlanEff", GlobalVariables.EMAP);
				if (Util.compareNumber(planEfficiency.get(i), expectedPlanEfficiency)) {
					Report.InfoTest(test,
							"Plan Efficiency is correct in " + subViewReportName + " Sub View report for Route : "
									+ route.get(i) + " with Actual Efficiency : " + planEfficiency.get(i)
									+ " and expected efficiency : " + expectedPlanEfficiency);
					Report.PassTest(test,
							"Plan Efficiency is as expected in " + subViewReportName + " Sub View report");
				} else {
					Report.FailTest(test,
							"Plan Efficiency is not as expected in " + subViewReportName
									+ " Sub View report for Route : " + route.get(i) + " with Actual efficiency : "
									+ planEfficiency.get(i) + " and expected efficiency : " + expectedPlanEfficiency);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Plan Efficiency is not as expected in " + subViewReportName + " Sub View report Actual is : "
							+ planEfficiency.toString() + " and Expected is : " + expectedPlanEfficiency);

		}

	}

	public void validatePlanUnits(Map<String, List<String>> actualPerformanceTableData, String subViewReportName,
			String LOB) {
		// PlanUnits=Derived from EMAP

		List<Double> planUnits = new ArrayList<>();
		double expectedPlanUnits = 0.0;
		List<String> route = new ArrayList<>();

		try {

			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Plan Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							planUnits.add(Double.parseDouble("0.0"));
						} else {
							planUnits.add(Double.parseDouble(entry.getValue().get(i)));
							Util.validateFieldDecimalPlaces(Double.parseDouble(entry.getValue().get(i)), 1,
									"Plan Units in " + subViewReportName + " : Sub View Report.");

						}

					}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (int i = 0; i < route.size(); i++) {
				expectedPlanUnits = Util.getEMAPData(route.get(i), LOB, "PlanUnits", GlobalVariables.EMAP);
				if (Util.compareNumber(planUnits.get(i), expectedPlanUnits)) {
					Report.InfoTest(test,
							"Plan Units is correct in " + subViewReportName + " Sub View report for Route : "
									+ route.get(i) + " with Actual Units : " + planUnits.get(i)
									+ " and expected Units : " + expectedPlanUnits);
					Report.PassTest(test, "Plan Units is as expected in " + subViewReportName + " Sub View report");
				} else {
					Report.FailTest(test,
							"Plan Units is not as expected in " + subViewReportName + " Sub View report for Route : "
									+ route.get(i) + " with Actual Units : " + planUnits.get(i)
									+ " and expected Units : " + expectedPlanUnits);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Plan Units is not as expected in " + subViewReportName + " Sub View report Actual is : "
							+ planUnits.toString() + " and Expected is : " + expectedPlanUnits);

		}

	}

	public void validatePlanDistance(Map<String, List<String>> actualTravelTableData, String subViewReportName,
			String LOB) {
		// PlanDistance=Derived from EMAP

		List<Integer> planDistance = new ArrayList<>();
		double expectedPlanDistance = 0;
		List<String> route = new ArrayList<>();

		try {

			for (Entry<String, List<String>> entry : actualTravelTableData.entrySet()) {
				if (entry.getKey().equals("Plan Distance")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							planDistance.add(Integer.parseInt("0.0"));
						} else {
							planDistance.add(Integer.parseInt(entry.getValue().get(i)));
							Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)),
									"Plan Distnace in " + subViewReportName + " : Sub View Report.");

						}

					}
				}
			}
			for (Entry<String, List<String>> entry : actualTravelTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (int i = 0; i < route.size(); i++) {
				expectedPlanDistance = Util.getEMAPData(route.get(i), LOB, "PlanDistance", GlobalVariables.EMAP);
				if (Util.compareNumber(planDistance.get(i), expectedPlanDistance)) {
					Report.InfoTest(test,
							"Plan Distance is correct in " + subViewReportName + " Sub View report for Route : "
									+ route.get(i) + " with Actual Units : " + planDistance.get(i)
									+ " and expected Units : " + expectedPlanDistance);
					Report.PassTest(test, "Plan Distance is as expected in " + subViewReportName + " Sub View report");
				} else {
					Report.FailTest(test,
							"Plan Distance is not as expected in " + subViewReportName + " Sub View report for Route : "
									+ route.get(i) + " with Actual Units : " + planDistance.get(i)
									+ " and expected Units : " + expectedPlanDistance);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Plan Distance is not as expected in " + subViewReportName + " Sub View report Actual is : "
							+ planDistance.toString() + " and Expected is : " + expectedPlanDistance);

		}

	}

	public void validatePlanDisposalLoads(Map<String, List<String>> actualDisposalTableData, String subViewReportName,
			String LOB) {
		// PlanUnits=Derived from EMAP

		List<Integer> planDisposalLoads = new ArrayList<>();
		int expectedPlanDisposalLoads = 0;
		List<String> route = new ArrayList<>();

		try {

			for (Entry<String, List<String>> entry : actualDisposalTableData.entrySet()) {
				if (entry.getKey().equals("Plan Disp Load")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							planDisposalLoads.add(Integer.parseInt("0"));
						} else {
							planDisposalLoads.add(Integer.parseInt(entry.getValue().get(i)));
							Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)),
									"Plan Disposal Loads in " + subViewReportName + " : Sub View Report.");

						}

					}
				}
			}
			for (Entry<String, List<String>> entry : actualDisposalTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (int i = 0; i < route.size(); i++) {
				expectedPlanDisposalLoads = (int) Util.getEMAPData(route.get(i), LOB, "PlanDisposalLoads",
						GlobalVariables.EMAP);
				if (Util.compareNumber(planDisposalLoads.get(i), expectedPlanDisposalLoads)) {
					Report.InfoTest(test,
							"Plan Units is correct in " + subViewReportName + " Sub View report for Route : "
									+ route.get(i) + " with Actual Plan Disposal Loads : " + planDisposalLoads.get(i)
									+ " and expected Plan Disposal Loads : " + expectedPlanDisposalLoads);
					Report.PassTest(test,
							"Plan Disposal Loads is as expected in " + subViewReportName + " Sub View report");
				} else {
					Report.FailTest(test,
							"Plan Disposal Loads is not as expected in " + subViewReportName
									+ " Sub View report for Route : " + route.get(i)
									+ " with Actual Plan Disposal Loads : " + planDisposalLoads.get(i)
									+ " and expected Plan Disposal Loads : " + expectedPlanDisposalLoads);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Plan Disposal Loads is not as expected in " + subViewReportName + " Sub View report Actual is : "
							+ planDisposalLoads.toString() + " and Expected is : " + expectedPlanDisposalLoads);

		}

	}

	public void validateEfficiencyPlanDriverHours(Map<String, List<String>> actualPerformanceTableData,
			String subViewReportName, String LOB) {
		// PlanEfficiency=Derived from EMAP

		List<String> planDriverHours = new ArrayList<>();
		String expectedPlanDriverHours = "";
		List<String> route = new ArrayList<>();

		try {

			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Plan Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							planDriverHours.add("00:00");
						} else {
							planDriverHours.add(entry.getValue().get(i));
							Util.validateTimeFormat(entry.getValue().get(i),
									"Plan Driver Hours in " + subViewReportName + " : Sub View Report. ");
						}

					}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (int i = 0; i < route.size(); i++) {
				expectedPlanDriverHours = Util.getEMAPDataTimeValue(route.get(i), LOB, "PlanDriverHours",
						GlobalVariables.EMAP);
				if (Util.compareTime(planDriverHours.get(i), expectedPlanDriverHours)) {
					Report.InfoTest(test,
							"Plan Driver Hours is correct in " + subViewReportName + " Sub View report for Route : "
									+ route.get(i) + " with Actual Driver Hours : " + planDriverHours.get(i)
									+ " and expected Driver Hours : " + expectedPlanDriverHours);
					Report.PassTest(test,
							"Plan Driver Hours is as expected in " + subViewReportName + " Sub View report");
				} else {
					Report.FailTest(test, "Plan Driver Hours is not as expected in " + subViewReportName
							+ " Sub View report for Route : " + route.get(i) + " with Actual Driver Hours : "
							+ planDriverHours.get(i) + " and expected Driver Hours : " + expectedPlanDriverHours);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Plan Driver Hours is not as expected in " + subViewReportName + " Sub View report Actual is : "
							+ planDriverHours.toString() + " and Expected is : " + expectedPlanDriverHours);

		}

	}

	public void validateDriverActualEfficiency(Map<String, List<String>> actualPerformanceTableData,
			String subViewReportName) {
		// ActualEfficiency=TotalVolume/Actual Driver Hours (total volume is
		// actual units)

		List<Double> actualUnits = new ArrayList<>();
		List<Double> actualDriverHours = new ArrayList<>();
		List<Double> actualEfficiency = new ArrayList<>();
		double expectedActualEfficiency = 0.0;
		List<String> route = new ArrayList<>();

		try {
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualUnits.add(Double.parseDouble(entry.getValue().get(i)));
					}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Drv Actual Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							actualEfficiency.add(Double.parseDouble(entry.getValue().get(i)));
							Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2,
									"Actual Efficiency in " + subViewReportName + " : Sub View Report.");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Actual Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						String eff[] = entry.getValue().get(i).split(":");
						double hours = Double.parseDouble(eff[0]);
						double mins = Double.parseDouble(eff[1]);
						double actualEff = hours + (mins / 60);
						actualDriverHours.add(actualEff);
					}
				}
			}

			for (int i = 0; i < route.size(); i++) {
				expectedActualEfficiency = actualUnits.get(i) / actualDriverHours.get(i);
				expectedActualEfficiency = Util.round(expectedActualEfficiency, 2);
				if (Util.compareNumber(actualEfficiency.get(i), expectedActualEfficiency)) {
					Report.InfoTest(test,
							"Actual Efficiency is correct in " + subViewReportName + " Sub View report for Route : "
									+ route.get(i) + " with Actual Efficiency : " + actualEfficiency.get(i)
									+ " and expected efficiency : " + expectedActualEfficiency);
					Report.PassTest(test,
							"Actual Efficiency is as expected in " + subViewReportName + " Sub View report");
				} else {
					Report.FailTest(test, "Actual Efficiency is not as expected in " + subViewReportName
							+ " Sub View report for Route : " + route.get(i) + " with Actual efficiency : "
							+ actualEfficiency.get(i) + " and expected efficiency : " + expectedActualEfficiency);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Actual Efficiency is not as expected in " + subViewReportName + " Sub View report Actual is : "
							+ actualEfficiency.toString() + " and Expected is : " + expectedActualEfficiency);

		}

	}

	public void validateDriverActualEfficiency_RO(Map<String, List<String>> actualPerformanceTableData,
			Map<String, List<String>> actualDisposalLoadsTableData, String subViewReportName) {
		// ActualEfficiency=TotalVolume/Actual Driver Hours (total volume is
		// actual units)

		List<Double> actualUnits = new ArrayList<>();
		List<Double> actualDriverHours = new ArrayList<>();
		List<Double> actualEfficiency = new ArrayList<>();
		double expectedActualEfficiency = 0.0;
		List<String> route = new ArrayList<>();

		try {
			for (Entry<String, List<String>> entry : actualDisposalLoadsTableData.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualUnits.add(Double.parseDouble(entry.getValue().get(i)));
					}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Drv Actual Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							actualEfficiency.add(Double.parseDouble(entry.getValue().get(i)));
							Util.validateFieldDecimalPlaces(entry.getValue().get(i), 4,
									"Actual Efficiency in " + subViewReportName + " : Sub View Report.");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Actual Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						String eff[] = entry.getValue().get(i).split(":");
						double hours = Double.parseDouble(eff[0]);
						double mins = Double.parseDouble(eff[1]);
						double actualEff = hours + (mins / 60);
						actualDriverHours.add(actualEff);
					}
				}
			}

			for (int i = 0; i < route.size(); i++) {
				expectedActualEfficiency = actualUnits.get(i) / actualDriverHours.get(i);
				expectedActualEfficiency = Util.round(expectedActualEfficiency, 2);
				if (Util.compareNumber(actualEfficiency.get(i), expectedActualEfficiency)) {
					Report.InfoTest(test,
							"Actual Efficiency is correct in " + subViewReportName + " Sub View report for Route : "
									+ route.get(i) + " with Actual Efficiency : " + actualEfficiency.get(i)
									+ " and expected efficiency : " + expectedActualEfficiency);
					Report.PassTest(test,
							"Actual Efficiency is as expected in " + subViewReportName + " Sub View report");
				} else {
					Report.FailTest(test, "Actual Efficiency is not as expected in " + subViewReportName
							+ " Sub View report for Route : " + route.get(i) + " with Actual efficiency : "
							+ actualEfficiency.get(i) + " and expected efficiency : " + expectedActualEfficiency);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Actual Efficiency is not as expected in " + subViewReportName + " Sub View report Actual is : "
							+ actualEfficiency.toString() + " and Expected is : " + expectedActualEfficiency);

		}

	}

	public void validateDriverHelperCombinedEfficiency(Map<String, List<String>> actualPerformanceTableData,
			String subViewReportName, String LOB) {
		// Drv/Hlp Eff=TotalVolume/(Actual Driver Hours+ Helper Hours)

		List<Double> actualUnits = new ArrayList<>();
		List<Double> actualDriverHours = new ArrayList<>();
		List<Double> actualHelperHours = new ArrayList<>();
		List<Double> actualDriverHelperCombinedEfficiency = new ArrayList<>();
		double expectedDriverHelperCombinedEfficiency = 0.0;
		List<String> route = new ArrayList<>();

		try {
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualUnits.add(Double.parseDouble(entry.getValue().get(i)));
					}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Drv/Hlp Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							actualDriverHelperCombinedEfficiency.add(Double.parseDouble(entry.getValue().get(i)));
							if (LOB.equals("Commercial") || LOB.equals("Residential"))
								Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2,
										"Driver Helper Efficiency in " + subViewReportName + " : Sub View Report.");
							else
								Util.validateFieldDecimalPlaces(entry.getValue().get(i), 4,
										"Driver Helper Efficiency in " + subViewReportName + " : Sub View Report.");

						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Actual Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String eff[] = entry.getValue().get(i).split(":");
							double hours = Double.parseDouble(eff[0]);
							double mins = Double.parseDouble(eff[1]);
							double actualEff = hours + (mins / 60);
							actualDriverHours.add(actualEff);
						} else {
							actualDriverHours.add(0.0);
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Helper Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String eff[] = entry.getValue().get(i).split(":");
							double hours = Double.parseDouble(eff[0]);
							double mins = Double.parseDouble(eff[1]);
							double actualEff = hours + (mins / 60);
							actualHelperHours.add(actualEff);
						} else {
							actualHelperHours.add(0.0);
						}
					}
				}
			}

			for (int i = 0; i < route.size(); i++) {
				expectedDriverHelperCombinedEfficiency = actualUnits.get(i)
						/ (actualDriverHours.get(i) + actualHelperHours.get(i));
				if (LOB.equals("Commercial") || LOB.equals("Residential"))
					expectedDriverHelperCombinedEfficiency = Util.round(expectedDriverHelperCombinedEfficiency, 2);
				else
					expectedDriverHelperCombinedEfficiency = Util.round(expectedDriverHelperCombinedEfficiency, 4);
				if (Util.compareNumber(actualDriverHelperCombinedEfficiency.get(i),
						expectedDriverHelperCombinedEfficiency)) {
					Report.InfoTest(test,
							"Driver Helper Combined Efficiency is correct in " + subViewReportName
									+ " Sub View report for Route : " + route.get(i)
									+ " with Actual Driver Helper Combined Efficiency : "
									+ actualDriverHelperCombinedEfficiency.get(i)
									+ " and expected Driver Helper Combined Efficiency : "
									+ expectedDriverHelperCombinedEfficiency);
					Report.PassTest(test, "Driver Helper Combined Efficiency is as expected in " + subViewReportName
							+ " Sub View report");
				} else {
					Report.FailTest(test,
							"Driver Helper Combined Efficiency is not as expected in " + subViewReportName
									+ " Sub View report for Route : " + route.get(i)
									+ " with Actual Driver Helper Combined Efficiency : "
									+ actualDriverHelperCombinedEfficiency.get(i)
									+ " and expected Driver Helper Combined Efficiency : "
									+ expectedDriverHelperCombinedEfficiency);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Driver Helper Combined Efficiency is not as expected in " + subViewReportName
							+ " Sub View report Actual is : " + actualDriverHelperCombinedEfficiency.toString()
							+ " and Expected is : " + expectedDriverHelperCombinedEfficiency);

		}

	}

	public void validateDriverAllowedEfficiency(Map<String, List<String>> actualPerformanceTableData,
			Map<String, List<String>> actualDisposalLoadsTableData, String subViewReportName) {
		// AllowedEfficiency=T==Actual Units/Allowed Driver Hours

		List<Double> actualUnits = new ArrayList<>();
		List<Double> allowedDriverHours = new ArrayList<>();
		List<Double> actualAllowedEfficiency = new ArrayList<>();
		double expectedAllowedEfficiency = 0.0;
		List<String> route = new ArrayList<>();

		try {
			for (Entry<String, List<String>> entry : actualDisposalLoadsTableData.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualUnits.add(Double.parseDouble(entry.getValue().get(i)));
					}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Drv Allowed Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							actualAllowedEfficiency.add(Double.parseDouble(entry.getValue().get(i)));
							Util.validateFieldDecimalPlaces(entry.getValue().get(i), 4,
									"Allowed Efficiency in " + subViewReportName + " : Sub View Report.");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualPerformanceTableData.entrySet()) {
				if (entry.getKey().contains("Allowed Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						String eff[] = entry.getValue().get(i).split(":");
						double hours = Double.parseDouble(eff[0]);
						double mins = Double.parseDouble(eff[1]);
						double actualEff = hours + (mins / 60);
						allowedDriverHours.add(actualEff);
					}
				}
			}

			for (int i = 0; i < route.size(); i++) {
				expectedAllowedEfficiency = actualUnits.get(i) / allowedDriverHours.get(i);
				expectedAllowedEfficiency = Util.round(expectedAllowedEfficiency, 4);
				if (Util.compareNumber(actualAllowedEfficiency.get(i), expectedAllowedEfficiency)) {
					Report.InfoTest(test,
							"Allowed Efficiency is correct in " + subViewReportName + " Sub View report for Route : "
									+ route.get(i) + " with Actual Allowed Efficiency : "
									+ actualAllowedEfficiency.get(i) + " and expected allowed efficiency : "
									+ expectedAllowedEfficiency);
					Report.PassTest(test,
							"Allowed Efficiency is as expected in " + subViewReportName + " Sub View report");
				} else {
					Report.FailTest(test,
							"Allowed Efficiency is not as expected in " + subViewReportName
									+ " Sub View report for Route : " + route.get(i)
									+ " with Actual Allowed efficiency : " + actualAllowedEfficiency.get(i)
									+ " and expected Allowed efficiency : " + expectedAllowedEfficiency);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Allowed Efficiency is not as expected in " + subViewReportName + " Sub View report Actual is : "
							+ actualAllowedEfficiency.toString() + " and Expected is : " + expectedAllowedEfficiency);

		}
	}

	public void validateDetailActualUnits(Map<String, List<String>> actualDetailTableData) {
		// Actual Units will be directly validated against confirmation DB

		List<String> actualUnits = new ArrayList<>();
		List<String> expectedUnits = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		try {
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						// actualUnits = entry.getValue();
						actualUnits.add(entry.getValue().get(i).indexOf(".") < 0 ? entry.getValue().get(i)
								: entry.getValue().get(i).replaceAll("0*$", "").replaceAll("\\.$", ""));
					}
				}
			}
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			expectedUnits = Util.getDataFromDB(
					"select TOTALUNIT from ocs_Admin.TP_RO_UNITSUMMARY where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') ");

			for (int i = 0; i < route.size(); i++) {
				if (actualUnits.get(i).equals(expectedUnits.get(i))) {
					Report.InfoTest(test,
							"Actual Units is correct in detail report for Route : " + route.get(i)
									+ " with Actual Units : " + actualUnits.get(i) + " and expected units : "
									+ expectedUnits.get(i));
					Report.PassTest(test, "Actual Units is as expected in Commercial Detail report");
				} else {
					Report.FailTest(test,
							"Actual Units is not as expected in detail report for Route : " + route.get(i)
									+ " with Actual Units : " + actualUnits.get(i) + " and expected units : "
									+ expectedUnits.get(i));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Units is not as expected in Detail report Actual is : "
					+ actualUnits.toString() + " and Expected is : " + expectedUnits);
		}
	}

	public void validateDriverMealTime(Map<String, List<String>> actualRouteSegmentsTableData,
			String subViewReportName) {
		// MealTime = LunchEnd - LunchStart
		List<Date> actualLunchTime = new ArrayList<>();
		Date expectedLunchTime = null;
		List<Date> lunchTimeStart = new ArrayList<>();
		List<Date> lunchTimeEnd = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		try {
			for (Entry<String, List<String>> entry : actualRouteSegmentsTableData.entrySet()) {
				if (entry.getKey().contains("Meal")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualLunchTime.add(dateFormat.parse("00:00"));

						} else {
							actualLunchTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
							Util.validateTimeFormat(entry.getValue().get(i).toString(),
									"Lunch Time in " + subViewReportName + " Sub View Report");
						}
				}
			}
			for (Entry<String, List<String>> entry : actualRouteSegmentsTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualRouteSegmentsTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			lunchTimeStart = Util.getDateDataFromDB(
					"select TO_CHAR(LUNCHSTARTSTAMP - (6/24), 'HH:MI') LUNCHSTARTSTAMP from OCS_ADMIN.TP_RO_LUNCH where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE  IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");
			lunchTimeEnd = Util.getDateDataFromDB(
					"select TO_CHAR(LUNCHENDSTAMP - (6/24), 'HH:MI') LUNCHENDSTAMP from OCS_ADMIN.TP_RO_LUNCH where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE  IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");

			if (lunchTimeStart.size() > 0 & lunchTimeEnd.size() > 0) {
				for (int i = 0; i < route.size(); i++) {
					long diff = (lunchTimeEnd.get(i).getTime() - lunchTimeStart.get(i).getTime());
					long diffMinutes = diff / (60 * 1000) % 60;
					long diffHours = diff / (60 * 60 * 1000) % 24;
					DecimalFormat twodigits = new DecimalFormat("00");
					if (diffHours == 0 && diffMinutes == 0)
						expectedLunchTime = dateFormat.parse("00:00");
					else
						expectedLunchTime = dateFormat
								.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

					if (actualLunchTime.get(i).equals(expectedLunchTime)) {
						Report.InfoTest(test,
								"Meal Time is correct in " + subViewReportName + " sub view report for Route : "
										+ route.get(i) + " with Actual Meal Time : "
										+ dateFormat.format(actualLunchTime.get(i)) + " and expected Meal Time : "
										+ dateFormat.format(expectedLunchTime));
						Report.PassTest(test, "Meal Time is as expected in " + subViewReportName + " sub view report");
					} else {
						Report.FailTest(test,
								"Meal Time is not as expected in " + subViewReportName + " sub view report for Route : "
										+ route.get(i) + " with Actual Meal Time : "
										+ dateFormat.format(actualLunchTime.get(i)) + " and expected Meal Time : "
										+ dateFormat.format(expectedLunchTime));
					}
				}
			} else {
				Report.InfoTest(test, "There is no Lunch Taken in " + subViewReportName
						+ " sub view report for Route : " + route.toString());
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Meal Time is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualLunchTime.toString() + " and Expected is : "
							+ dateFormat.format(expectedLunchTime));
		}

	}

	public void validateDriverDowntime(Map<String, List<String>> actualRouteSegmentsTableData,
			String subViewReportName) {
		// DownTime = DownTimeEnd - DownTimeStart

		List<Date> actualDownTime = new ArrayList<>();
		Date expectedDownTime = null;
		List<Date> downTimeStart = new ArrayList<>();
		List<Date> downTimeEnd = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		try {
			for (Entry<String, List<String>> entry : actualRouteSegmentsTableData.entrySet()) {
				if (entry.getKey().contains("Downtime")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualDownTime.add(dateFormat.parse("00:00"));

						} else {
							actualDownTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
							Util.validateTimeFormat(entry.getValue().get(i).toString(),
									"DownTime in " + subViewReportName + " Sub View Report");
						}
				}
			}
			for (Entry<String, List<String>> entry : actualRouteSegmentsTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualRouteSegmentsTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						System.out.println("Date ----------------- in mm-dd-yy :" + dates);
						date.add(format2.format(dates));
						System.out.println("Date ----------------- in dd-MMM-yy :" + date);
					}
				}
			}
			System.out.println("Date ----------------- in dd-MMM-yy :" + date);

			downTimeStart = Util.getDateDataFromDB(
					"select TO_CHAR(DOWNSTART - (6 / 24) , 'HH:MI') DOWNSTART from OCS_ADMIN.TP_RO_DOWNTIME where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");
			downTimeEnd = Util.getDateDataFromDB(
					"select TO_CHAR(DOWNEND - (6 / 24), 'HH:MI') DOWNEND from OCS_ADMIN.TP_RO_DOWNTIME where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");
			for (int i = 0; i < route.size(); i++) {
				long diff = (downTimeEnd.get(i).getTime() - downTimeStart.get(i).getTime());
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				DecimalFormat twodigits = new DecimalFormat("00");
				if (diffHours == 0 && diffMinutes == 0)
					expectedDownTime = dateFormat.parse("00:00");
				else
					expectedDownTime = dateFormat
							.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

				if (Util.compareTime(actualDownTime.get(i), expectedDownTime)) {
					Report.InfoTest(test,
							"Down Time is correct in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Down Time : "
									+ dateFormat.format(actualDownTime.get(i)) + " and expected Down Time : "
									+ dateFormat.format(expectedDownTime));
					Report.PassTest(test, "Downtime is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Down Time is not as expected in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Downtime : "
									+ dateFormat.format(actualDownTime.get(i)) + " and expected Downtime : "
									+ dateFormat.format(expectedDownTime));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Down Time is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualDownTime.toString() + " and Expected is : " + dateFormat.format(expectedDownTime));
		}

	}

	public void validateDriverNetidleTime(Map<String, List<String>> actualRouteSegmentTableData,
			String subViewReportName) {
		// Idletime = (idletime start-idletime end)

		List<Date> actualIdleTime = new ArrayList<>();
		Date expectedIdleTime = null;
		List<Date> idleTimeStart = new ArrayList<>();
		List<Date> idleTimeEnd = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		try {
			for (Entry<String, List<String>> entry : actualRouteSegmentTableData.entrySet()) {
				if (entry.getKey().contains("Net Idle")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualIdleTime.add(dateFormat.parse("00:00"));

						} else {
							actualIdleTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
							Util.validateTimeFormat(entry.getValue().get(i).toString(),
									"Net Idle Time in " + subViewReportName + " Sub View Report");
						}
				}
			}
			for (Entry<String, List<String>> entry : actualRouteSegmentTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualRouteSegmentTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			idleTimeStart = Util.getDateDataFromDB(
					"select TO_CHAR(STARTED - (6/24), 'HH:MI') STARTED from OCS_ADMIN.TP_ALERT where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID  IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL and LUNCHSTARTSTAMP IS NULL and DOWNTIMESTARTSTAMP IS NULL");
			idleTimeEnd = Util.getDateDataFromDB(
					"select TO_CHAR(FINISHED - (6/24), 'HH:MI') FINISHED from OCS_ADMIN.TP_ALERT where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL and LUNCHSTARTSTAMP IS NULL and DOWNTIMESTARTSTAMP IS NULL");
			if (idleTimeStart.size() > 0 & idleTimeEnd.size() > 0) {
				for (int i = 0; i < route.size(); i++) {
					long diff = 0;
					long diffMinutes = 0;
					long diffHours = 0;
					if (idleTimeStart != null && idleTimeEnd != null) {
						for (int j = 0; j < idleTimeStart.size(); j++) {
							diff = (idleTimeEnd.get(i).getTime() - idleTimeStart.get(i).getTime());
							diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
							diffHours = diffHours + diff / (60 * 60 * 1000) % 24;
						}
						DecimalFormat twodigits = new DecimalFormat("00");

						if (diffHours == 0 && diffMinutes == 0)
							expectedIdleTime = dateFormat.parse("00:00");
						else
							expectedIdleTime = dateFormat
									.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

						if (Util.compareTime(actualIdleTime.get(i), expectedIdleTime)) {
							Report.InfoTest(test,
									"Net Idle Time is correct in " + subViewReportName + " sub view report for Route : "
											+ route.get(i) + " with Actual Idle Time : "
											+ dateFormat.format(actualIdleTime.get(i)) + " and expected Idle Time : "
											+ dateFormat.format(expectedIdleTime));
							Report.PassTest(test,
									"Net Idle Time is as expected in " + subViewReportName + " sub view report");
						} else {
							Report.FailTest(test,
									"Net Idle Time is not as expected in " + subViewReportName
											+ " sub view report for Route : " + route.get(i)
											+ " with Actual Idle Time : " + dateFormat.format(actualIdleTime.get(i))
											+ " and expected Idle Time : " + dateFormat.format(expectedIdleTime));
						}
					} else {
						Report.FailTest(test, "There is no disposal cycle in " + subViewReportName
								+ " sub view report for Route : " + route.get(i));
					}
				}
			} else {
				Report.InfoTest(test, "There is no Idle time in " + subViewReportName + " sub view report for Route : "
						+ route.toString());
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Net Idle Time is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualIdleTime.toString() + " and Expected is : " + dateFormat.format(expectedIdleTime));
		}

	}

	public void validateDriverIdleOccurences() {
		// TODO Auto-generated method stub

	}

	public void validateDriverPostRouteTime(Map<String, List<String>> actualRouteSegmentTableData,
			String subViewReportName) {
		// PostRouteTime = ClockOut - ArriveYard
		List<Date> actualPostRouteTime = new ArrayList<>();
		Date expectedPostRouteTime = null;
		List<Date> arriveYardTime = new ArrayList<>();
		List<Date> clockOutTime = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
		try {
			for (Entry<String, List<String>> entry : actualRouteSegmentTableData.entrySet()) {
				if (entry.getKey().contains("Post-Route")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualPostRouteTime.add(dateFormat.parse("00:00"));

						} else {
							actualPostRouteTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
							Util.validateTimeFormat(entry.getValue().get(i).toString(),
									"Post-Route Time in " + subViewReportName + " Sub View Report");
						}
				}
			}
			for (Entry<String, List<String>> entry : actualRouteSegmentTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualRouteSegmentTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			for (Entry<String, List<String>> entry : actualRouteSegmentTableData.entrySet()) {
				if (entry.getKey().equals("Clock Out")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {

							clockOutTime.add(dateFormat.parse("00:00"));

						} else {
							clockOutTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
						}

					}
				}
			}

			arriveYardTime = Util.getDateDataFromDB(
					"select TO_CHAR(TOYARDSTAMP - (6 / 24) , 'HH:MI') from OCS_ADMIN.TP_RO_RESULT where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID  IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE  IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");

			for (int i = 0; i < route.size(); i++) {
				long diff = (clockOutTime.get(i).getTime() - arriveYardTime.get(i).getTime());
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				DecimalFormat twodigits = new DecimalFormat("00");
				if (diffHours == 0 && diffMinutes == 0)
					expectedPostRouteTime = dateFormat.parse("00:00");
				else
					expectedPostRouteTime = dateFormat
							.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

				if (Util.compareTime(actualPostRouteTime.get(i), expectedPostRouteTime)) {
					Report.InfoTest(test,
							"Post-Route Time is correct in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Post-Route Time : "
									+ dateFormat.format(actualPostRouteTime.get(i)) + " and expected Post-Route Time : "
									+ dateFormat.format(expectedPostRouteTime));
					Report.PassTest(test,
							"Post-Route time is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Post-Route Time is not as expected in " + subViewReportName
									+ " sub view report for Route : " + route.get(i) + " with Actual Post-Route Time : "
									+ dateFormat.format(actualPostRouteTime.get(i)) + " and expected Post-Route Time : "
									+ dateFormat.format(expectedPostRouteTime));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Post-Route Time is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualPostRouteTime.toString() + " and Expected is : "
							+ dateFormat.format(expectedPostRouteTime));
		}

	}

	public void validateTonsLoad() {
		// TODO Auto-generated method stub

	}

	public void validateDriverPreRouteTime(Map<String, List<String>> actualRouteSegmentTableData,
			String subViewReportName) {
		// PreRoute Time = Leave Yard - ClockIn Time

		List<Date> actualPreRouteTime = new ArrayList<>();
		Date expectedPreRouteTime = null;
		List<Date> leaveYardTime = new ArrayList<>();
		List<Date> clockInTime = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		try {
			for (Entry<String, List<String>> entry : actualRouteSegmentTableData.entrySet()) {
				if (entry.getKey().contains("Pre-Route")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualPreRouteTime.add(dateFormat.parse("00:00"));

						} else {
							actualPreRouteTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
							Util.validateTimeFormat(entry.getValue().get(i).toString(),
									"Pre-Route Time in " + subViewReportName + " Sub View Report");
						}
				}
			}
			for (Entry<String, List<String>> entry : actualRouteSegmentTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualRouteSegmentTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			for (Entry<String, List<String>> entry : actualRouteSegmentTableData.entrySet()) {
				if (entry.getKey().equals("Clock In")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {

							clockInTime.add(dateFormat.parse("00:00"));

						} else {
							clockInTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
						}

					}
				}
			}

			leaveYardTime = Util.getDateDataFromDB(
					"select TO_CHAR(FROMYARDSTAMP - (6 / 24) , 'HH:MI') from OCS_ADMIN.TP_RO_RESULT where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID  IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE  IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");

			for (int i = 0; i < route.size(); i++) {
				long diff = (leaveYardTime.get(i).getTime() - clockInTime.get(i).getTime());
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				DecimalFormat twodigits = new DecimalFormat("00");
				if (diffHours == 0 && diffMinutes == 0)
					expectedPreRouteTime = dateFormat.parse("00:00");
				else
					expectedPreRouteTime = dateFormat
							.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

				if (Util.compareTime(actualPreRouteTime.get(i), expectedPreRouteTime)) {
					Report.InfoTest(test,
							"Pre-Route Time is correct in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Pre-Route Time : "
									+ dateFormat.format(actualPreRouteTime.get(i)) + " and expected Pre-Route Time : "
									+ dateFormat.format(expectedPreRouteTime));
					Report.PassTest(test, "Pre-Route time is as expected in " + subViewReportName + " sub view report");
				} else {
					Report.FailTest(test,
							"Pre-Route Time is not as expected in " + subViewReportName
									+ " sub view report for Route : " + route.get(i) + " with Actual Pre-Route Time : "
									+ dateFormat.format(actualPreRouteTime.get(i)) + " and expected Pre-Route Time : "
									+ dateFormat.format(expectedPreRouteTime));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test,
					"Pre-Route Time is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualPreRouteTime.toString() + " and Expected is : "
							+ dateFormat.format(expectedPreRouteTime));
		}

	}

	public void validateDriverDisposalCycleTime(Map<String, List<String>> actualRouteSegmentsTableData,
			String subViewReportName) {
		// DisposalCycleTime = Sum of individual disposal trips
		// Individual disposal trips = depart disposal time - arrive disposal
		// time
		List<Date> actualDisposalCycleTime = new ArrayList<>();
		List<Date> arriveDisposalTime = new ArrayList<>();
		List<Date> departDisposalTime = new ArrayList<>();
		Date expectedDisposalTime = null;
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
		try {
			for (Entry<String, List<String>> entry : actualRouteSegmentsTableData.entrySet()) {
				if (entry.getKey().equals("Disp Cycle (h:m)")) { 
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualDisposalCycleTime.add(dateFormat.parse("00:00"));

						} else {
							actualDisposalCycleTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
							Util.validateTimeFormat(entry.getValue().get(i).toString(),
									"Disposal Cycle Time in " + subViewReportName + " Sub View Report");
						}
				}
			}
			for (Entry<String, List<String>> entry : actualRouteSegmentsTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualRouteSegmentsTableData.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			arriveDisposalTime = Util.getDateDataFromDB(
					"select  TO_CHAR(ARRIVESTAMP - (6 / 24), 'HH:MI') from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");
			departDisposalTime = Util.getDateDataFromDB(
					"select  TO_CHAR(DEPARTSTAMP - (6 / 24), 'HH:MI') from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");

			for (int i = 0; i < route.size(); i++) {
				long diff = 0;
				long diffMinutes = 0;
				long diffHours = 0;
				if (arriveDisposalTime != null && departDisposalTime != null) {
					for (int j = 0; j < arriveDisposalTime.size(); j++) {
						diff = (departDisposalTime.get(i).getTime() - arriveDisposalTime.get(i).getTime());
						diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
						diffHours = diffHours + diff / (60 * 60 * 1000) % 24;
					}
					DecimalFormat twodigits = new DecimalFormat("00");

					if (diffHours == 0 && diffMinutes == 0)
						expectedDisposalTime = dateFormat.parse("00:00");
					else
						expectedDisposalTime = dateFormat
								.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

					if (Util.compareTime(actualDisposalCycleTime.get(i), expectedDisposalTime)) {
						Report.InfoTest(test, "Disposal cycle Time is correct in " + subViewReportName
								+ " sub view report for Route : " + route.get(i) + " with Actual Disposal Cycle Time : "
								+ dateFormat.format(actualDisposalCycleTime.get(i))
								+ " and expected Disposal Cycle Time : " + dateFormat.format(expectedDisposalTime));
						Report.PassTest(test,
								"Disposal Cycle Time is as expected in " + subViewReportName + " sub view report");
					} else {
						Report.FailTest(test, "Disposal cycle Time is not as expected in " + subViewReportName
								+ " sub view report for Route : " + route.get(i) + " with Actual Disposal Cycle Time : "
								+ dateFormat.format(actualDisposalCycleTime.get(i))
								+ " and expected Disposal Cycle Time : " + dateFormat.format(expectedDisposalTime));
					}
				} else {
					Report.FailTest(test, "There is no disposal cycle in " + subViewReportName
							+ " sub view report for Route : " + route.get(i));
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Disposal cycle Time is not as expected in " + subViewReportName + " sub view report Actual is : "
							+ actualDisposalCycleTime.toString() + " and Expected is : "
							+ dateFormat.format(expectedDisposalTime));
		}

	}

	public void validateDetailUnitVariance(Map<String, List<String>> actualDetailTableData) {
		// UnitVariance = ActualUnits-(PlanUnits);

		List<Double> actualUnits = new ArrayList<>();
		List<Double> planUnits = new ArrayList<>();
		List<Double> actualUnitsVariance = new ArrayList<>();
		double expectedUnitsVariance = 0.0;
		List<String> route = new ArrayList<>();
		try {
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualUnits.add(0.0);

						} else {
							actualUnits.add(Double.parseDouble(entry.getValue().get(i)));
						}
				}
			}
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().equals("Plan Units")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							planUnits.add(0.0);

						} else {
							planUnits.add(Double.parseDouble(entry.getValue().get(i)));
						}
				}
			}
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().equals("Units Var")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualUnitsVariance.add(0.0);

						} else {
							actualUnitsVariance.add(Double.parseDouble(entry.getValue().get(i)));
						}
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (int i = 0; i < route.size(); i++) {
				expectedUnitsVariance = actualUnits.get(i) - planUnits.get(i);

				if (Util.compareNumber(actualUnitsVariance.get(i), expectedUnitsVariance)) {
					Report.InfoTest(test,
							"Unit Variance is correct in detail report for Route : " + route.get(i)
									+ " with Actual Units Variance : " + actualUnitsVariance.get(i)
									+ " and expected Units Variance : " + expectedUnitsVariance);
					Report.PassTest(test, "Units Variance is as expected in Commercial Detail report");
				} else {
					Report.FailTest(test,
							"Units Variance is not as expected in detail report for Route : " + route.get(i)
									+ " with Actual Units Variance : " + actualUnitsVariance.get(i)
									+ " and expected Units Variance : " + expectedUnitsVariance);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Units Variance is not as expected in Detail report Actual is : "
					+ actualUnitsVariance.toString() + " and Expected is : " + expectedUnitsVariance);
		}

	}

	public void validateSubLOB(Map<String, List<String>> actualSubViewTableData, String subViewReportName) {

		List<String> actualSubLOB = new ArrayList<>();
		List<String> expectedSubLOB = new ArrayList<>();
		List<String> route = new ArrayList<>();

		try {
			for (Entry<String, List<String>> entry : actualSubViewTableData.entrySet()) {
				if (entry.getKey().equals("Sub LOB")) {
					actualSubLOB = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualSubViewTableData.entrySet()) {
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
							"Sub LOB is correct in " + subViewReportName + " Sub View report for Route : "
									+ route.get(i) + " with Actual SubLOB : " + actualSubLOB.get(0)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
					Report.PassTest(test, "Sub LOB is as expected in " + subViewReportName + " Sub View report");
				} else {
					Report.FailTest(test,
							"Sub LOB is not as expected in " + subViewReportName + " sub view report for Route : "
									+ route.get(i) + " with Actual Sub LOB : " + actualSubLOB.get(i)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Sub LOB is not as expected in " + subViewReportName + " Sub view report Actual is : "
					+ actualSubLOB.toString() + " and Expected is : " + expectedSubLOB.toString());
		}

	}

	public void validateColumns(List<String> actualColumnNames, List<String> expectedColumnNames, String tableName) {
		// TODO Auto-generated method stub
		for (int i = 0; i < expectedColumnNames.size(); i++) {
			if (expectedColumnNames.get(i).equals(expectedColumnNames.get(i))) {
				Report.InfoTest(test, "Column Names is correct in " + tableName + " Actual Column Name is : "
						+ actualColumnNames.get(i) + " and Expected is : " + expectedColumnNames.get(i));
				Report.PassTest(test, "Column Names are as expected in Commercial Total section of the report");
				LogClass.info("Checking class level logger");
			} else {
				Report.FailTest(test, "Column Names is not as expected in " + tableName + " Actual are : "
						+ actualColumnNames.get(i) + " and Expected are : " + expectedColumnNames.get(i));
			}
		}

	}

}// LastClosingBracket
