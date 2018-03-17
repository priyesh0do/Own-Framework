package ObjectRepository.SDO;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.runtime.directive.Parse;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import SupportLibraries.Excel;
import SupportLibraries.KeywordImplementation;
import SupportLibraries.LogClass;
import SupportLibraries.Util;
import SupportLibraries.Report;

public class CommercialFlashReport {

	ExtentTest test;
	WebDriver driver;

	public CommercialFlashReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='jsFlashLink']/span[contains(., 'See Commercial Flash Report')]")
	WebElement CommercialFlashReportLink;

	@FindBy(xpath = "//*[@id='inpTimeOption_1']")
	WebElement HistoricalOption;

	@FindBy(xpath = "//table[@id='dtRouteSummary']")
	WebElement summaryTable;

	@FindBy(xpath = "//table[@id='t2']")
	WebElement efficiencyTable;

	@FindBy(xpath = "//table[@id='t2']")
	WebElement detailTable;

	@FindBy(xpath = "//table[@id='dtRouteSummary']/thead/tr/th")
	List<WebElement> summaryTableColumns;

	@FindBy(xpath = "//table[@id='dtRouteSummary']/tbody/tr")
	List<WebElement> summaryTableRows;

	@FindBy(xpath = "//table[@id='t2']/thead/tr/th")
	List<WebElement> efficiencyTableColumns;

	@FindBy(xpath = "//table[@id='t2']/tbody/tr")
	List<WebElement> efficiencyTableRows;

	@FindBy(xpath = "//table[@id='t2']/thead/tr/th")
	List<WebElement> detailTableColumns;

	@FindBy(xpath = "//table[@id='t2']/tbody/tr")
	List<WebElement> detailTableRows;

	@FindBy(xpath = "//*[@id='inpSubReportOpt_2']")
	WebElement detailSubView;

	@FindBy(xpath = "//*[@id='inpSubReportOpt_1']")
	WebElement efficiencySubView;

	@FindBy(xpath = ".//*[@class='highcharts-container']/*[@xmlns='http://www.w3.org/2000/svg']/*[@class='highcharts-legend']/*[@id]/*[@id]/*[@class='highcharts-legend-item']/*[text()='Actual']")
	WebElement GraphLegendActual;

	@FindBy(xpath = ".//*[@class='highcharts-container']/*[@xmlns='http://www.w3.org/2000/svg']/*[@class='highcharts-legend']/*[@id]/*[@id]/*[@class='highcharts-legend-item']/*[text()='Plan']")
	WebElement GraphLegendPlan;

	@FindBy(xpath = ".//*[@class='highcharts-container']/*[@xmlns='http://www.w3.org/2000/svg']/*[@class='highcharts-legend']/*[@id]/*[@id]/*[@class='highcharts-legend-item']/*[text()='Target']")
	WebElement GraphLegendTarget;

	@FindBy(xpath = ".//*[@class='highcharts-container']/*[@xmlns='http://www.w3.org/2000/svg']/*[@class='highcharts-axis']/*[@class=' highcharts-yaxis-title']/*")
	WebElement GraphYAxis;

	@FindBy(xpath = ".//*[@class='highcharts-container']/*[@xmlns='http://www.w3.org/2000/svg']/*[@class='highcharts-series-group']/*[@class='highcharts-markers highcharts-tracker']/*[@fill='#77B310'][1]")
	WebElement GraphPointerActual;

	@FindBy(xpath = ".//*[@class='highcharts-tooltip']/span/div/div[1]")
	WebElement GraphPointerActualOverLayHeader;

	@FindBy(xpath = ".//*[@class='highcharts-tooltip']/span/div/div[2]/table/tbody/tr[1]/td[2]")
	WebElement GraphPointerActualOverLayActual;

	@FindBy(xpath = ".//*[@class='highcharts-tooltip']/span/div/div[2]/table/tbody/tr[2]/td[2]")
	WebElement GraphPointerActualOverLayPlan;

	@FindBy(xpath = ".//*[@class='highcharts-tooltip']/span/div/div[2]/table/tbody/tr[3]/td[2]")
	WebElement GraphPointerActualOverLayTarget;
	
	@FindBy(id = "actExportXLS")
	WebElement exportToExcelCommFlashReport;

	public void clickCommercialFlashReport() throws IOException {
		Util.ElementExist(driver, CommercialFlashReportLink);
		CommercialFlashReportLink.click();
		Report.PassTest(test, "Clicked on Commercial Flash Report");
	}

	public void selectHistoricalOption() throws IOException {
		Util.ElementExist(driver, HistoricalOption);
		HistoricalOption.click();
		Report.PassTest(test, "Historical Option get selected");
	}

	public void selectDetailSubView() throws IOException {
		Util.ElementExist(driver, detailSubView);
		detailSubView.click();
		Report.PassTest(test, "Detail SubView get selected as expected");
	}

	public Map<String, List<String>> getActualSummaryTableData() throws IOException {
		System.out.println("inside Actual Summary Data");
		Map<String, List<String>> summaryTableData = new HashMap<>();
		try {
			summaryTableData = readTable(summaryTableColumns, summaryTableRows, "dtRouteSummary");
			Report.InfoTest(test, "Summary table data read successfully");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read summary table data");
		}

		for (Entry<String, List<String>> entry : summaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return summaryTableData;
	}

	public Map<String, List<String>> getActualEfficiencyTableData() {
		System.out.println("inside Actual Efficiency Data");
		Util.CaptureScreenshot("Efficiency Table data");
		Map<String, List<String>> efficiencyTableData = new HashMap<>();
		try {
			efficiencyTableData = readTable(efficiencyTableColumns, efficiencyTableRows, "t2");
			Report.InfoTest(test, "Efficiency table data read successfully");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Efficiency table data");
		}
		for (Entry<String, List<String>> entry : efficiencyTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return efficiencyTableData;
	}

	public Map<String, List<String>> getActualDetailTableData() {
		System.out.println("inside Actual Detail Data");
		Util.CaptureScreenshot("Detail Table data");
		Map<String, List<String>> detailTableData = new HashMap<>();
		try {
			detailTableData = readTable(detailTableColumns, detailTableRows, "t2");
			Report.InfoTest(test, "Detail table data read successfully");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Detail table data");
		}
		for (Entry<String, List<String>> entry : detailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return detailTableData;
	}

	public List<String> getSummaryTableColumns() throws IOException {
		System.out.println("Reading summary columns");
		List<String> summaryTableColumn = null;
		try {
			summaryTableColumn = readColumns(summaryTableColumns, "dtRouteSummary");
			Report.InfoTest(test, "Summary table column read successfully");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read summary table columns");
		}

		System.out.println(summaryTableColumn.toString());
		return summaryTableColumn;
	}

	public List<String> getEfficiencyTableColumns() throws IOException {
		System.out.println("Reading Efficiency columns");
		List<String> efficiencyTableColumn = null;
		try {
			efficiencyTableColumn = readColumns(efficiencyTableColumns, "t2");
			Report.InfoTest(test, "Efficiency table column read successfully");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Efficiency table columns");
		}

		System.out.println(efficiencyTableColumn.toString());
		return efficiencyTableColumn;
	}

	public List<String> getDetailTableColumns() throws IOException {
		System.out.println("Reading Detail columns");
		List<String> detailTableColumn = null;
		try {
			detailTableColumn = readColumns(detailTableColumns, "t2");
			Report.InfoTest(test, "Detail table column read successfully");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Detail table columns");
		}

		System.out.println(detailTableColumn.toString());
		return detailTableColumn;
	}

	public Map<String, List<String>> readTable(List<WebElement> columns, List<WebElement> rows, String tableName) {

		if (tableName.equals("dtRouteSummary")) {
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
				objTable.put(columns.get(iCount - 1).getText(), rowValues);
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
				for (int row = 1; row < rows.size(); row = row + 4) {
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
			Map<String, List<String>> actualEfficiencyTableData, Map<String, List<String>> actualDetailTableData) {
		validateSummaryPlanEfficiency(actualSummaryTableData, actualDetailTableData);
		validateSummaryActualEfficiency(actualSummaryTableData, actualDetailTableData);
		validateSummaryEfficiencyVariance(actualSummaryTableData);
		validateSummaryTargetEfficiency(actualSummaryTableData, actualEfficiencyTableData);
		validateSummaryPlanDriverHours(actualSummaryTableData, actualEfficiencyTableData);
		validateSummaryActualDriverHours(actualSummaryTableData, actualEfficiencyTableData);
		validateSummaryDriverHourVariance(actualSummaryTableData);
		validateSummaryPreRouteHours(actualSummaryTableData, actualDetailTableData);
		validateSummaryPostRouteHours(actualSummaryTableData, actualDetailTableData);
		validateSummaryIdleTime(actualSummaryTableData, actualDetailTableData);
		validateSummaryDownTime(actualSummaryTableData, actualDetailTableData);
	}

	public void validateEfficiencyData(Map<String, List<String>> actualEfficiencyTableData,
			Map<String, List<String>> actualDetailTableData, String expectedLOB, String expectedRoute,
			String expectedDriver, String expectedClockInTime, String expectedClockOutTime) {
		validateLOB(actualEfficiencyTableData, expectedLOB);
		validateSubLOB(actualEfficiencyTableData);
		validateRouteName(actualEfficiencyTableData, expectedRoute);
		validateDriverName(actualEfficiencyTableData, expectedDriver);
		validateEfficiencyClockInTime(actualEfficiencyTableData, expectedClockInTime);
		validateEfficiencyClockOutTime(actualEfficiencyTableData, expectedClockOutTime);
		validateEfficiencyActualDriverHours(actualEfficiencyTableData);
		validateEfficiencyPlanDriverHours(actualEfficiencyTableData, actualDetailTableData);
		validateRouteActualEfficiency(actualEfficiencyTableData, actualDetailTableData);
		validateEfficiencyVariance(actualEfficiencyTableData);
		// validateActualOutofRange();
		validateDriverHoursVariance(actualEfficiencyTableData);

	}

	public void validateDetailData(Map<String, List<String>> actualEfficiencyTableData,
			Map<String, List<String>> actualDetailTableData, String expectedLOB, String expectedRoute,
			String expectedDriver, String expectedClockInTime, String expectedClockOutTime) {
		validateLOB(actualDetailTableData, expectedLOB);
		validateSubLOB(actualDetailTableData);
		validateRouteName(actualDetailTableData, expectedRoute);
		validateDriverName(actualDetailTableData, expectedDriver);
		validateDetailPreRouteTime(actualEfficiencyTableData, actualDetailTableData);
		validateDetailOnRouteTime(actualEfficiencyTableData, actualDetailTableData);
		validateDetailDisposalCycleTime(actualDetailTableData);
		validateDetailTonsLoad();// need sql query
		validateDetailPostRouteTime(actualEfficiencyTableData, actualDetailTableData);
		validateDetailIdleOccurences();// need sql query
		validateDetailNetidleTime(actualDetailTableData);
		validateDetailDowntime(actualDetailTableData);
		validateDetailMealTime(actualDetailTableData);
		validateDetailActualUnits(actualDetailTableData);
		validateDetailUnitVariance(actualDetailTableData);
	}

	public void validateSummaryPlanEfficiency(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualDetailTableData) {
		double actualPlanEfficiency = 0.00;
		double expectedPlanEfficiency = 0.00;
		double totalVolume = 0.00;
		String planDriverDuration = null;
		double planDriverHours = 0.00;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().equals("Plan Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualPlanEfficiency = 0.00;
						} else {
							actualPlanEfficiency = Double.parseDouble(entry.getValue().get(i));
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Plan Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							planDriverDuration = "00:00";
						} else {
							planDriverDuration = entry.getValue().get(i);
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							totalVolume = totalVolume + 0.00;
						} else {
							totalVolume = totalVolume + Double.parseDouble(entry.getValue().get(i));
						}

					}
				}
			}
			if (!(planDriverDuration.equals("00:00"))) {
				String plannedDriver[] = planDriverDuration.split(":");
				System.out.println("Hours : " + plannedDriver[0]);
				System.out.println("Minutes : " + plannedDriver[1]);
				double hours = Double.parseDouble(plannedDriver[0]);
				double mins = Double.parseDouble(plannedDriver[1]);
				planDriverHours = hours + (mins / 60);
				expectedPlanEfficiency = totalVolume / planDriverHours;
				expectedPlanEfficiency = Util.round(expectedPlanEfficiency, 2);
				System.out.println("Total Volume : " + totalVolume);
				System.out.println("Plan Driver Hours : " + planDriverHours);
				System.out.println("Expected Plan Efficiency : " + expectedPlanEfficiency);
			}

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
			Map<String, List<String>> actualDetailTableData) {
		double actualEfficiency = 0.00;
		double expectedActualEfficiency = 0.00;
		double totalVolume = 0.00;
		String actualDriverDuration = null;
		double actualDriverHours = 0.00;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().equals("Actual Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualEfficiency = 0.00;
						} else {
							actualEfficiency = Double.parseDouble(entry.getValue().get(i));
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Actual Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualDriverDuration = "00:00";
						} else {
							actualDriverDuration = entry.getValue().get(i);
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							totalVolume = totalVolume + 0.00;
						} else {
							totalVolume = totalVolume + Double.parseDouble(entry.getValue().get(i));
						}

					}
				}
			}
			if (!(actualDriverDuration.equals("00:00"))) {
				String plannedDriver[] = actualDriverDuration.split(":");
				System.out.println("Hours : " + plannedDriver[0]);
				System.out.println("Minutes : " + plannedDriver[1]);
				double hours = Double.parseDouble(plannedDriver[0]);
				double mins = Double.parseDouble(plannedDriver[1]);
				actualDriverHours = hours + (mins / 60);
				expectedActualEfficiency = totalVolume / actualDriverHours;
				expectedActualEfficiency = Util.round(expectedActualEfficiency, 2);
				System.out.println("Total Volume : " + totalVolume);
				System.out.println("Plan Driver Hours : " + actualDriverHours);
				System.out.println("Expected Plan Efficiency : " + expectedActualEfficiency);
			}

			if (Util.compareNumber(actualEfficiency, expectedActualEfficiency)) {
				Report.InfoTest(test, "Actual Efficiency is correct in summary report Actual is : " + actualEfficiency
						+ " and Expected is : " + expectedActualEfficiency);
				Report.PassTest(test, "Actual Efficiency is as expected in Summary report");
			} else {
				Report.FailTest(test, "Actual Efficiency is not as expected in summary report Actual is : "
						+ actualEfficiency + " and Expected is : " + expectedActualEfficiency);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Actual Efficiency is not as expected in summary report Actual is : "
					+ actualEfficiency + " and Expected is : " + expectedActualEfficiency);
		}
	}

	public void validateSummaryEfficiencyVariance(Map<String, List<String>> actualSummaryTableData) {
		double actualVariance = 0.00;
		double planEff = 0.00;
		double actualEff = 0.00;
		double expectedVariance = 0.00;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().equals("Variance")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualVariance = 0.00;
						} else {
							actualVariance = Double.parseDouble(entry.getValue().get(i));
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
			expectedVariance = actualEff - planEff;
			expectedVariance = Util.round(expectedVariance, 1);
			if (Util.compareNumber(actualVariance, expectedVariance)) {
				Report.InfoTest(test, "Efficiency Variance is correct in summary report Actual is : " + actualVariance
						+ " and Expected is : " + expectedVariance);
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

	public void validateSummaryTargetEfficiency(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualEfficiencyTableData) {
		double actualTargetEff = 0.00;
		double expectedTargetEff = 0.00;
		double totalTargetEff = 0.00;
		double noOfRoutes = 0.00;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().equals("Target Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualTargetEff = 0.00;
						} else {
							actualTargetEff = Double.parseDouble(entry.getValue().get(i));
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().equals("Target")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							totalTargetEff = totalTargetEff + 0.00;
						} else {
							totalTargetEff = totalTargetEff + Double.parseDouble(entry.getValue().get(i));
							noOfRoutes++;
						}
					}
				}
			}

			expectedTargetEff = totalTargetEff / noOfRoutes;
			expectedTargetEff = Util.round(expectedTargetEff, 2);
			if (Util.compareNumber(actualTargetEff, expectedTargetEff)) {
				Report.InfoTest(test, "Target Efficiency is correct in summary report Actual is : " + actualTargetEff
						+ " and Expected is : " + expectedTargetEff);
				Report.PassTest(test, "Target Efficiency is as expected in Summary report");
			} else {
				Report.FailTest(test, "Target Efficiency is not as expected in summary report Actual is : "
						+ actualTargetEff + " and Expected is : " + expectedTargetEff);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Target Efficiency is not as expected in summary report Actual is : "
					+ actualTargetEff + " and Expected is : " + expectedTargetEff);
		}
	}

	public void validateSummaryPlanDriverHours(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualEfficiencyTableData) {
		String actualPlanDriverHours = null;
		String expectedPlanDriverHours = null;
		int totalHours = 0;
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

			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().contains("Plan Driver Hours")) {
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
					expectedPlanDriverHours = "0" + Integer.toString(totalHours) + ":" + "0"
							+ Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedPlanDriverHours = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedPlanDriverHours = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedPlanDriverHours = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				System.out.println("Expected Plan Driver Hours : " + expectedPlanDriverHours);

			} else {
				if (totalHours == 0 && totalMins > 9)
					expectedPlanDriverHours = "00:" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedPlanDriverHours = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours == 0 && totalMins < 10)
					expectedPlanDriverHours = "00:" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedPlanDriverHours = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedPlanDriverHours = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				System.out.println("Expected Plan Driver Hours : " + expectedPlanDriverHours);
			}

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
		int totalHours = 0;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Actual Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualDriverHours = null;
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
				expectedActualDriverHours = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				System.out.println("Expected Actual Driver Hours : " + expectedActualDriverHours);

			} else {

				if (totalHours == 0 && totalMins > 9)
					expectedActualDriverHours = "00:" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedActualDriverHours = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours == 0 && totalMins < 10)
					expectedActualDriverHours = "00:" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedActualDriverHours = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedActualDriverHours = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				System.out.println("Expected Actual Driver Hours : " + expectedActualDriverHours);
			}

			if (actualDriverHours.equals(expectedActualDriverHours)) {
				Report.InfoTest(test, "Actual Driver Hours is correct in summary report Actual is : "
						+ actualDriverHours + " and Expected is : " + expectedActualDriverHours);
				Report.PassTest(test, "Actual Driver Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Actual Driver Hours is not as expected in summary report Actual is : "
						+ actualDriverHours + " and Expected is : " + expectedActualDriverHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Actual Driver Hours is not as expected in summary report Actual is : "
					+ actualDriverHours + " and Expected is : " + expectedActualDriverHours);
		}
	}

	public void validateSummaryDriverHourVariance(Map<String, List<String>> actualSummaryTableData) {
		String actualVariance = null;
		String planDriverHours = null;
		String actualDriverHours = null;
		String expectedDriverVariance = null;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");

		Date d1 = null;
		Date d2 = null;
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

			if (!(planDriverHours.equals("00:00") && actualDriverHours.equals("00:00"))) {
				d1 = format.parse(planDriverHours);
				d2 = format.parse(actualDriverHours);
				long diff = d1.getTime() - d2.getTime();
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				if (diffHours < 0 || diffMinutes < 0) {
					if ((diffHours > -9) && (diffMinutes > -9)) {
						diffMinutes = -(diffMinutes);
						diffHours = -(diffHours);
						expectedDriverVariance = "-0" + Long.toString(diffHours) + ":0" + Long.toString(diffMinutes);
					} else if ((diffHours > -9) && (diffMinutes < -9)) {
						diffMinutes = -(diffMinutes);
						diffHours = -(diffHours);
						expectedDriverVariance = "-0" + Long.toString(diffHours) + ":" + Long.toString(diffMinutes);
					} else if ((diffHours < -9) && (diffMinutes > -9)) {
						diffMinutes = -(diffMinutes);
						diffHours = -(diffHours);
						expectedDriverVariance = "-" + Long.toString(diffHours) + ":0" + Long.toString(diffMinutes);
					} else {
						diffMinutes = -(diffMinutes);
						expectedDriverVariance = Long.toString(diffHours) + ":" + Long.toString(diffMinutes);
					}
				} else {
					if (!(diffHours > 9)) {
						expectedDriverVariance = "0" + Long.toString(diffHours) + ":" + Long.toString(diffMinutes);
					} else {
						expectedDriverVariance = Long.toString(diffHours) + ":" + Long.toString(diffMinutes);
					}
				}
			}
			System.out.println("Expected driver hour variance : " + expectedDriverVariance);
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

	public void validateLOB(Map<String, List<String>> actualEfficiencyTableData, String expectedLOB) {
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
				Report.InfoTest(test, "LOB is correct in efficiency report Actual is : " + actualLOB
						+ " and Expected is : " + expectedLOB);
				Report.PassTest(test, "LOB is as expected in Efficiency report");
			} else {
				Report.FailTest(test, "LOB is not as expected in summary report Actual is : " + actualLOB
						+ " and Expected is : " + expectedLOB);
			}
		} catch (Exception e) {
			Report.FailTest(test, "LOB is not as expected in summary report Actual is : " + actualLOB
					+ " and Expected is : " + expectedLOB);
		}

	}

	public void validateRouteName(Map<String, List<String>> actualEfficiencyTableData, String expectedRouteName) {
		String[] routes = expectedRouteName.split(";");
		List<String> actualRoutes = null;
		try {
			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					actualRoutes = entry.getValue();
				}
			}
			for (int i = 0; i < routes.length; i++) {
				if (actualRoutes.contains(routes[i])) {
					Report.InfoTest(test, "Route is correct in efficiency report Actual List is : "
							+ actualRoutes.toString() + " and Expected is : " + routes[i]);
					Report.PassTest(test, "Route is as expected in Efficiency report");
				} else {
					Report.FailTest(test, "Route is not as expected in summary report Actual List is : "
							+ actualRoutes.toString() + " and Expected is : " + routes[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test, "Route is not as expected in summary report Actual List is : "
					+ actualRoutes.toString() + " and Expected is : " + routes.toString());
		}

	}

	public void validateDriverName(Map<String, List<String>> actualEfficiencyTableData, String expectedDriverName) {
		String[] drivers = expectedDriverName.split(";");
		List<String> actualDrivers = null;
		try {
			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().equals("Driver")) {
					actualDrivers = entry.getValue();
				}
			}
			for (int i = 0; i < drivers.length; i++) {
				if (actualDrivers.contains(drivers[i])) {
					Report.InfoTest(test, "Driver Name is correct in efficiency report Actual List is : "
							+ actualDrivers.toString() + " and Expected is : " + drivers[i]);
					Report.PassTest(test, "Driver Name is as expected in Efficiency report");
				} else {
					Report.FailTest(test, "Driver Name is not as expected in summary report Actual List is : "
							+ actualDrivers.toString() + " and Expected is : " + drivers[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test, "Driver Name is not as expected in summary report Actual List is : "
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

	public void validateEfficiencyActualDriverHours(Map<String, List<String>> actualEfficiencyTableData) {
		List<String> clockInTimes = null;
		List<String> clockOutTimes = null;
		List<String> actualDriverHours = null;
		String expectedDriverHours = null;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");

		Date d1 = null;
		Date d2 = null;
		try {
			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().equals("Clock In")) {
					clockInTimes = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().equals("Clock Out")) {
					clockOutTimes = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().contains("Actual Driver Hours")) {
					actualDriverHours = entry.getValue();
				}
			}

			for (int i = 0; i < actualDriverHours.size(); i++) {
				if (actualDriverHours.get(i).equals("--")) {
					Report.InfoTest(test, "Actual Driver Hours is " + actualDriverHours.get(i));
					Report.PassTest(test, "Kronos Clock In and Clock Out time is missing in Efficiency report");
				} else {
					d1 = format.parse(clockOutTimes.get(i));
					d2 = format.parse(clockInTimes.get(i));
					long diff = d1.getTime() - d2.getTime();
					long diffMinutes = diff / (60 * 1000) % 60;
					long diffHours = diff / (60 * 60 * 1000) % 24;
					DecimalFormat twodigits = new DecimalFormat("00");
					Date expectedDriverHour = format
							.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));
					System.out
							.println("expected driver hours in date format : ---------------- :" + expectedDriverHour);
					if (diffHours < 10 && diffMinutes < 10)
						expectedDriverHours = "0" + diffHours + ":0" + diffMinutes;
					else if (diffHours < 10 && diffMinutes > 10)
						expectedDriverHours = "0" + diffHours + ":" + diffMinutes;
					else if (diffHours >= 10 && diffMinutes < 10)
						expectedDriverHours = diffHours + ":0" + diffMinutes;
					else
						expectedDriverHours = diffHours + ":" + diffMinutes;
					System.out.println("Expected driver hour  : " + expectedDriverHours);
					if (actualDriverHours.get(i).equals(expectedDriverHours)) {
						Report.InfoTest(test,
								"Actual Driver Hours is correct in efficiency report with Clcok In Time : "
										+ clockInTimes.get(i) + " and Clock Out Time : " + clockOutTimes.get(i)
										+ " and Expected is : " + expectedDriverHours);
						Report.PassTest(test, "Actual Driver Hours is as expected in Efficiency report");
					} else if (expectedDriverHours.equals("10:00")) {
						expectedDriverHours = "09:30";
						if (actualDriverHours.get(i).equals(expectedDriverHours)) {
							Report.InfoTest(test,
									"Actual Driver Hours is correct in efficiency report with Clcok In Time : "
											+ clockInTimes.get(i) + " and Clock Out Time : " + clockOutTimes.get(i)
											+ " and Expected is : " + expectedDriverHours);
							Report.PassTest(test, "Actual Driver Hours is as expected in Efficiency report");
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

	public void validateDriverHoursVariance(Map<String, List<String>> actualEfficiencyTableData) {
		// Driver Hour variance = Plan Driver Hours - Actual Driver Hours

		List<Date> actualDriverHours = new ArrayList<>();
		Date expectedDriverHourVariance = null;
		List<Date> planDriverHours = new ArrayList<>();
		List<Date> actualDriverHourVariance = new ArrayList<>();
		List<String> route = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
		try {
			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().contains("Actual Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualDriverHours.add(dateFormat.parse("00:00"));

						} else {
							actualDriverHours.add(dateFormat.parse(entry.getValue().get(i).toString()));
						}
				}
			}
			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().contains("Hours Variance")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualDriverHourVariance.add(dateFormat.parse("00:00"));

						} else {
							actualDriverHourVariance.add(dateFormat.parse(entry.getValue().get(i).toString()));
						}
				}
			}
			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().contains("Plan Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {

							planDriverHours.add(dateFormat.parse("00:00"));

						} else {
							planDriverHours.add(dateFormat.parse(entry.getValue().get(i).toString()));
						}

					}
				}
			}

			for (int i = 0; i < route.size(); i++) {
				long diff = (planDriverHours.get(i).getTime() - actualDriverHours.get(i).getTime());
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				DecimalFormat twodigits = new DecimalFormat("00");
				if (diffHours == 0 && diffMinutes == 0)
					expectedDriverHourVariance = dateFormat.parse("00:00");
				else
					expectedDriverHourVariance = dateFormat
							.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

				if (actualDriverHourVariance.get(i).equals(expectedDriverHourVariance)) {
					Report.InfoTest(test, "Driver Hour variance is correct in efficiency report for Route : "
							+ route.get(i) + " with Actual Driver Hour Variance : "
							+ dateFormat.format(actualDriverHourVariance.get(i))
							+ " and expected Driver Hour Variance : " + dateFormat.format(expectedDriverHourVariance));
					Report.PassTest(test, "Driver Hour Variance is as expected in Commercial Efficiency report");
				} else {
					Report.FailTest(test, "Driver Hour Variance is not as expected in efficiency report for Route : "
							+ route.get(i) + " with Actual Driver Hour Variance : "
							+ dateFormat.format(actualDriverHourVariance.get(i))
							+ " and expected Driver Hour Variance : " + dateFormat.format(expectedDriverHourVariance));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Hour Variance is not as expected in efficiency report Actual is : "
					+ actualDriverHourVariance.toString() + " and Expected is : " + expectedDriverHourVariance);
		}

	}

	public void validateEfficiencyVariance(Map<String, List<String>> actualEfficiencyTableData) {
		// EfficiencyVariance = ActualEff-(PlanEff);

		List<Double> actualEff = new ArrayList<>();
		List<Double> planEff = new ArrayList<>();
		List<Double> actualEffVariance = new ArrayList<>();
		double expectedEffVariance = 0.0;
		List<String> route = new ArrayList<>();
		try {
			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().equals("Actual Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualEff.add(0.0);

						} else {
							actualEff.add(Double.parseDouble(entry.getValue().get(i)));
						}
				}
			}
			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().equals("Plan Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							planEff.add(0.0);

						} else {
							planEff.add(Double.parseDouble(entry.getValue().get(i)));
						}
				}
			}
			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().equals("Variance")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualEffVariance.add(0.0);

						} else {
							actualEffVariance.add(Double.parseDouble(entry.getValue().get(i)));
						}
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (int i = 0; i < route.size(); i++) {
				expectedEffVariance = actualEff.get(i) - planEff.get(i);
				expectedEffVariance = Util.round(expectedEffVariance, 2);
				if (Util.compareNumber(actualEffVariance.get(i), expectedEffVariance)) {
					Report.InfoTest(test,
							"Efficiency Variance is correct in efficiency report for Route : " + route.get(i)
									+ " with Actual Efficiency Variance : " + actualEffVariance.get(i)
									+ " and expected Efficiency Variance : " + expectedEffVariance);
					Report.PassTest(test, "Units Variance is as expected in Commercial Detail report");
				} else {
					Report.FailTest(test,
							"Efficiency Variance is not as expected in Efficiency report for Route : " + route.get(i)
									+ " with Actual Efficiency Variance : " + actualEffVariance.get(i)
									+ " and expected Efficiency Variance : " + expectedEffVariance);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Efficiency Variance is not as expected in Efficiency report Actual is : "
					+ actualEffVariance.toString() + " and Expected is : " + expectedEffVariance);
		}

	}

	public void validateRouteActualEfficiency(Map<String, List<String>> actualEfficiencyTableData,
			Map<String, List<String>> actualDetailTableData) {
		// ActualEfficiency=TotalVolume/Actual Driver Hours (total volume is
		// actual units)

		List<Double> actualUnits = new ArrayList<>();
		List<Double> actualDriverHours = new ArrayList<>();
		List<Double> actualEfficiency = new ArrayList<>();
		double expectedActualEfficiency = 0.0;
		List<String> route = new ArrayList<>();

		try {
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualUnits.add(Double.parseDouble(entry.getValue().get(i)));
					}
				}
			}
			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().equals("Actual Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualEfficiency.add(Double.parseDouble(entry.getValue().get(i)));
					}
				}
			}
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
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
				expectedActualEfficiency=Util.round(expectedActualEfficiency, 2);
				if (Util.compareNumber(actualEfficiency.get(i), expectedActualEfficiency)) {
					Report.InfoTest(test,
							"Actual Efficiency is correct in Efficiency report for Route : " + route.get(i)
									+ " with Actual Efficiency : " + actualEfficiency.get(i)
									+ " and expected efficiency : " + actualEfficiency);
					Report.PassTest(test, "Actual Efficiency is as expected in Commercial Efficiency report");
				} else {
					Report.FailTest(test,
							"Actual Efficiency is not as expected in Efficiency report for Route : " + route.get(i)
									+ " with Actual efficiency : " + actualEfficiency.get(i)
									+ " and expected efficiency : " + actualEfficiency);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Actual Efficiency is not as expected in Efficiency report Actual is : "
					+ actualEfficiency.toString() + " and Expected is : " + expectedActualEfficiency);

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
						//actualUnits = entry.getValue();
						actualUnits.add(entry.getValue().get(i).indexOf(".") < 0 ? entry.getValue().get(i) : entry.getValue().get(i).replaceAll("0*$", "").replaceAll("\\.$", ""));
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
							+ Util.sqlFormatedList(date) + ")) ");

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

	public void validateDetailMealTime(Map<String, List<String>> actualDetailTableData) {
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
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("Meal")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualLunchTime.add(dateFormat.parse("00:00"));

						} else {
							actualLunchTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
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

			lunchTimeStart = Util.getDateDataFromDB(
					"select TO_CHAR(LUNCHSTARTSTAMP , 'HH:MI') LUNCHSTARTSTAMP from OCS_ADMIN.TP_RO_LUNCH where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE  IN ("
							+ Util.sqlFormatedList(date) + ")) and FK_VEHICLE IS NOT NULL");
			lunchTimeEnd = Util.getDateDataFromDB(
					"select TO_CHAR(LUNCHENDSTAMP , 'HH:MI') LUNCHENDSTAMP from OCS_ADMIN.TP_RO_LUNCH where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE  IN ("
							+ Util.sqlFormatedList(date) + ")) and FK_VEHICLE IS NOT NULL");

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
							"Meal Time is correct in detail report for Route : " + route.get(i)
									+ " with Actual Meal Time : " + dateFormat.format(actualLunchTime.get(i))
									+ " and expected Meal Time : " + dateFormat.format(expectedLunchTime));
					Report.PassTest(test, "Meal Time is as expected in Commercial Detail report");
				} else {
					Report.FailTest(test,
							"Meal Time is not as expected in detail report for Route : " + route.get(i)
									+ " with Actual Meal Time : " + dateFormat.format(actualLunchTime.get(i))
									+ " and expected Meal Time : " + dateFormat.format(expectedLunchTime));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Meal Time is not as expected in Detail report Actual is : "
					+ actualLunchTime.toString() + " and Expected is : " + dateFormat.format(expectedLunchTime));
		}

	}

	public void validateDetailDowntime(Map<String, List<String>> actualDetailTableData) {
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
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("Downtime")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualDownTime.add(dateFormat.parse("00:00"));

						} else {
							actualDownTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
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
						System.out.println("Date ----------------- in mm-dd-yy :" + dates);
						date.add(format2.format(dates));
						System.out.println("Date ----------------- in dd-MMM-yy :" + date);
					}
				}
			}
			System.out.println("Date ----------------- in dd-MMM-yy :" + date);

			downTimeStart = Util.getDateDataFromDB(
					"select TO_CHAR(DOWNSTART , 'HH:MI') DOWNSTART from OCS_ADMIN.TP_RO_DOWNTIME where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ")) and FK_VEHICLE IS NOT NULL");
			downTimeEnd = Util.getDateDataFromDB(
					"select TO_CHAR(DOWNEND , 'HH:MI') DOWNEND from OCS_ADMIN.TP_RO_DOWNTIME where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ")) and FK_VEHICLE IS NOT NULL");
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

				if (actualDownTime.get(i).equals(expectedDownTime)) {
					Report.InfoTest(test,
							"Down Time is correct in detail report for Route : " + route.get(i)
									+ " with Actual Down Time : " + dateFormat.format(actualDownTime.get(i)) + " and expected Down Time : "
									+ dateFormat.format(expectedDownTime));
					Report.PassTest(test, "Downtime is as expected in Commercial Detail report");
				} else {
					Report.FailTest(test,
							"Down Time is not as expected in detail report for Route : " + route.get(i)
									+ " with Actual Downtime : " + dateFormat.format(actualDownTime.get(i)) + " and expected Downtime : "
									+ dateFormat.format(expectedDownTime));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Down Time is not as expected in Detail report Actual is : "
					+ actualDownTime.toString() + " and Expected is : " + dateFormat.format(expectedDownTime));
		}

	}

	public void validateDetailNetidleTime(Map<String, List<String>> actualDetailTableData) {
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
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("Net Idle")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualIdleTime.add(dateFormat.parse("00:00"));

						} else {
							actualIdleTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
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

			idleTimeStart = Util.getDateDataFromDB(
					"select TO_CHAR(STARTED , 'HH:MI') STARTED from OCS_ADMIN.TP_ALERT where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID  IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ")) and FK_VEHICLE IS NOT NULL");
			idleTimeEnd = Util.getDateDataFromDB(
					"select TO_CHAR(FINISHED , 'HH:MI') FINISHED from OCS_ADMIN.TP_ALERT where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ")) and FK_VEHICLE IS NOT NULL");

			for (int i = 0; i < route.size(); i++) {
				long diff = (idleTimeEnd.get(i).getTime() - idleTimeStart.get(i).getTime());
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				DecimalFormat twodigits = new DecimalFormat("00");
				if (diffHours == 0 && diffMinutes == 0)
					expectedIdleTime = dateFormat.parse("00:00");
				else
					expectedIdleTime = dateFormat
							.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

				if (actualIdleTime.get(i).equals(expectedIdleTime)) {
					Report.InfoTest(test,
							"Net Idle Time is correct in detail report for Route : " + route.get(i)
									+ " with Actual Idle Time : " + dateFormat.format(actualIdleTime.get(i)) + " and expected Idle Time : "
									+ dateFormat.format(expectedIdleTime));
					Report.PassTest(test, "Net Idle Time is as expected in Commercial Detail report");
				} else {
					Report.FailTest(test,
							"Net Idle Time is not as expected in detail report for Route : " + route.get(i)
									+ " with Actual Idle Time : " + dateFormat.format(actualIdleTime.get(i)) + " and expected Idle Time : "
									+ dateFormat.format(expectedIdleTime));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Net Idle Time is not as expected in Detail report Actual is : "
					+ actualIdleTime.toString() + " and Expected is : " + dateFormat.format(expectedIdleTime));
		}

	}

	public void validateDetailIdleOccurences() {
		// TODO Auto-generated method stub

	}

	public void validateDetailPostRouteTime(Map<String, List<String>> actualEfficiencyTableData,
			Map<String, List<String>> actualDetailTableData) {
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
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("Post-Route")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualPostRouteTime.add(dateFormat.parse("00:00"));

						} else {
							actualPostRouteTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
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

			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
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
					"select TO_CHAR(TOYARDSTAMP - (5 / 24) , 'HH:MI') from OCS_ADMIN.TP_RO_RESULT where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID  IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE  IN ("
							+ Util.sqlFormatedList(date) + ")) and FK_VEHICLE IS NOT NULL");

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

				if (actualPostRouteTime.get(i).equals(expectedPostRouteTime)) {
					Report.InfoTest(test,
							"Post-Route Time is correct in detail report for Route : " + route.get(i)
									+ " with Actual Post-Route Time : " + dateFormat.format(actualPostRouteTime.get(i))
									+ " and expected Post-Route Time : " + dateFormat.format(expectedPostRouteTime));
					Report.PassTest(test, "Post-Route time is as expected in Commercial Detail report");
				} else {
					Report.FailTest(test,
							"Post-Route Time is not as expected in detail report for Route : " + route.get(i)
									+ " with Actual Post-Route Time : " + dateFormat.format(actualPostRouteTime.get(i))
									+ " and expected Post-Route Time : " + dateFormat.format(expectedPostRouteTime));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Post-Route Time is not as expected in Detail report Actual is : "
					+ actualPostRouteTime.toString() + " and Expected is : " + dateFormat.format(expectedPostRouteTime));
		}

	}

	public void validateDetailTonsLoad() {
		// TODO Auto-generated method stub

	}

	public void validateDetailPreRouteTime(Map<String, List<String>> actualEfficiencyTableData,
			Map<String, List<String>> actualDetailTableData) {
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
		SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
		try {
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("Pre-Route")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualPreRouteTime.add(dateFormat.parse("00:00"));

						} else {
							actualPreRouteTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
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

			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
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
							+ Util.sqlFormatedList(date) + ")) and FK_VEHICLE IS NOT NULL");

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

				if (actualPreRouteTime.get(i).equals(expectedPreRouteTime)) {
					Report.InfoTest(test,
							"Pre-Route Time is correct in detail report for Route : " + route.get(i)
									+ " with Actual Pre-Route Time : " + dateFormat.format(actualPreRouteTime.get(i))
									+ " and expected Pre-Route Time : " + dateFormat.format(expectedPreRouteTime));
					Report.PassTest(test, "Pre-Route time is as expected in Commercial Detail report");
				} else {
					Report.FailTest(test,
							"Pre-Route Time is not as expected in detail report for Route : " + route.get(i)
									+ " with Actual Pre-Route Time : " + dateFormat.format(actualPreRouteTime.get(i))
									+ " and expected Pre-Route Time : " + dateFormat.format(expectedPreRouteTime));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Pre-Route Time is not as expected in Detail report Actual is : "
					+ actualPreRouteTime.toString() + " and Expected is : " + dateFormat.format(expectedPreRouteTime));
		}

	}

	public void validateDetailOnRouteTime(Map<String, List<String>> actualEfficiencyTableData,
			Map<String, List<String>> actualDetailTableData) {
		// OnRouteTime = ActualDriverHours-(PreRoute+PostRoute+DispCycle)

		List<Date> actualDriverHours = new ArrayList<>();
		List<Date> disposalCycle = new ArrayList<>();
		List<Date> preRouteTime = new ArrayList<>();
		List<Date> postRouteTime = new ArrayList<>();
		List<Date> actualOnRouteTime = new ArrayList<>();
		Date expectedOnRouteTime = null;
		List<String> route = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
		try {
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("Pre-Route")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							preRouteTime.add(dateFormat.parse("00:00"));

						} else {
							preRouteTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
						}
				}
			}
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("Post-Route")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							postRouteTime.add(dateFormat.parse("00:00"));

						} else {
							postRouteTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
						}
				}
			}
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("On Route")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualOnRouteTime.add(dateFormat.parse("00:00"));

						} else {
							actualOnRouteTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
						}
				}
			}
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("Disp Cycle")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							disposalCycle.add(dateFormat.parse("00:00"));

						} else {
							disposalCycle.add(dateFormat.parse(entry.getValue().get(i).toString()));
						}
				}
			}
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().contains("Actual Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {

							actualDriverHours.add(dateFormat.parse("00:00"));

						} else {
							actualDriverHours.add(dateFormat.parse(entry.getValue().get(i).toString()));
						}

					}
				}
			}

			for (int i = 0; i < route.size(); i++) {
				long diff = (actualDriverHours.get(i).getTime() - (preRouteTime.get(i).getTime()
						+ postRouteTime.get(i).getTime() + disposalCycle.get(i).getTime()));
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				DecimalFormat twodigits = new DecimalFormat("00");
				if (diffHours == 0 && diffMinutes == 0)
					expectedOnRouteTime = dateFormat.parse("00:00");
				else
					expectedOnRouteTime = dateFormat
							.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

				if (actualOnRouteTime.get(i).equals(expectedOnRouteTime)) {
					Report.InfoTest(test,
							"On-Route Time is correct in detail report for Route : " + route.get(i)
									+ " with Actual On-Route Time : " + dateFormat.format(actualOnRouteTime.get(i))
									+ " and expected Pre-Route Time : " + dateFormat.format(expectedOnRouteTime));
					Report.PassTest(test, "On-Route time is as expected in Commercial Detail report");
				} else {
					Report.FailTest(test,
							"On-Route Time is not as expected in detail report for Route : " + route.get(i)
									+ " with Actual On-Route Time : " + dateFormat.format(actualOnRouteTime.get(i))
									+ " and expected On-Route Time : " + dateFormat.format(expectedOnRouteTime));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "On-Route Time is not as expected in Detail report Actual is : "
					+ actualOnRouteTime.toString() + " and Expected is : " + dateFormat.format(expectedOnRouteTime));
		}

	}

	public void validateDetailDisposalCycleTime(Map<String, List<String>> actualDetailTableData) {
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
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("Disp Cycle")) {
					for (int i = 0; i < entry.getValue().size(); i++)
						if (entry.getValue().get(i).equals("--")) {

							actualDisposalCycleTime.add(dateFormat.parse("00:00"));

						} else {
							actualDisposalCycleTime.add(dateFormat.parse(entry.getValue().get(i).toString()));
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

			arriveDisposalTime = Util.getDateDataFromDB(
					"select  TO_CHAR(ARRIVESTAMP, 'HH:MI') from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ")) and FK_VEHICLE IS NOT NULL");
			departDisposalTime = Util.getDateDataFromDB(
					"select  TO_CHAR(DEPARTSTAMP, 'HH:MI') from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ")) and FK_VEHICLE IS NOT NULL");

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

					if (actualDisposalCycleTime.get(i).equals(expectedDisposalTime)) {
						Report.InfoTest(test,
								"Disposal cycle Time is correct in detail report for Route : " + route.get(i)
										+ " with Actual Disposal Cycle Time : " + dateFormat.format(actualDisposalCycleTime.get(i))
										+ " and expected Disposal Cycle Time : " + dateFormat.format(expectedDisposalTime));
						Report.PassTest(test, "Disposal Cycle Time is as expected in Commercial Detail report");
					} else {
						Report.FailTest(test,
								"Disposal cycle Time is not as expected in detail report for Route : " + route.get(i)
										+ " with Actual Disposal Cycle Time : " + dateFormat.format(actualDisposalCycleTime.get(i))
										+ " and expected Disposal Cycle Time : " + dateFormat.format(expectedDisposalTime));
					}
				} else {
					Report.FailTest(test, "There is no disposal cycle in detail report for Route : " + route.get(i));
				}
			}

		} catch (Exception e) {
			Report.FailTest(test, "Disposal cycle Time is not as expected in Detail report Actual is : "
					+ actualDisposalCycleTime.toString() + " and Expected is : " + dateFormat.format(expectedDisposalTime));
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

	public void validateSubLOB(Map<String, List<String>> actualDetailTableData) {

		List<String> actualSubLOB = new ArrayList<>();
		List<String> expectedSubLOB = new ArrayList<>();
		List<String> route = new ArrayList<>();

		try {
			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().equals("Sub LOB")) {
					actualSubLOB = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			expectedSubLOB = Util.getDataFromDB(
					"select UNIQUEID from OCS_ADMIN.TP_SUBLOB where PKEY IN (select OCS_ADMIN.TP_ROUTE.FK_SUBLOB from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + "))");

			for (int i = 0; i < route.size(); i++) {
				if (actualSubLOB.get(i).equals(expectedSubLOB.get(i))) {
					Report.InfoTest(test,
							"Sub LOB is correct in Commercial Flash report for Route : " + route.get(i)
									+ " with Actual SubLOB : " + actualSubLOB.get(i) + " and expected Sub LOB : "
									+ expectedSubLOB.get(i));
					Report.PassTest(test, "Sub LOB is as expected in Commercial Flash report");
				} else {
					Report.FailTest(test,
							"Sub LOB is not as expected in detail report for Route : " + route.get(i)
									+ " with Actual Sub LOB : " + actualSubLOB.get(i) + " and expected Sub LOB : "
									+ expectedSubLOB.get(i));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Sub LOB is not as expected in Commercial Flash report Actual is : "
					+ actualSubLOB.toString() + " and Expected is : " + expectedSubLOB.toString());
		}

	}

	public void validatePlanUnits(Map<String, List<String>> actualDetailTableData, String routeEMAPStatus) {
		List<String> planUnits = new ArrayList<>();
		List<String> route = new ArrayList<>();
		try {
			if (routeEMAPStatus.equals("Planned")) {
				for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
					if (entry.getKey().equals("Plan Units")) {
						planUnits = entry.getValue();
					}
				}

				for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
					if (entry.getKey().equals("Route")) {
						route = entry.getValue();
					}
				}

				for (int i = 0; i < route.size(); i++) {
					if (!planUnits.get(i).equals("--")) {
						Report.InfoTest(test,
								"Plan Units is as per EMAP in Commercial Flash report for Planned Route : "
										+ route.get(i) + " with Plan Units : " + planUnits.get(i));
						Report.PassTest(test, "Plan Units is as expected in Commercial Flash report");
					} else {
						Report.FailTest(test, "Plan Units is not as expected in detail report for Planned Route : "
								+ route.get(i) + " with Plan Units : " + planUnits.get(i));
					}
				}

			} else if (routeEMAPStatus.equals("Un-Planned")) {
				for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
					if (entry.getKey().equals("Plan Units")) {
						planUnits = entry.getValue();
					}
				}

				for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
					if (entry.getKey().equals("Route")) {
						route = entry.getValue();
					}
				}

				for (int i = 0; i < route.size(); i++) {
					if (planUnits.get(i).equals("--")) {
						Report.InfoTest(test,
								"Plan Units is as per EMAP in Commercial Flash report for Un-Planned Route : "
										+ route.get(i) + " with Plan Units : " + planUnits.get(i));
						Report.PassTest(test, "Plan Units is as expected in Commercial Flash report");
					} else {
						Report.FailTest(test, "Plan Units is not as expected in detail report for Un-Planned Route : "
								+ route.get(i) + " with Plan Units : " + planUnits.get(i));
					}
				}
			} else {
				Report.FailTest(test,
						"Route status should be either Planned or Un-Planned. Please check method calling.");
			}

		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test,
					"Plan Units is not as expected in Commercial Flash report Actual is : " + planUnits.toString());
		}

	}

	public void validatePlanDriverHours(Map<String, List<String>> actualEfficiencyTableData, String routeEMAPStatus) {
		List<String> planDriverHours = new ArrayList<>();
		List<String> route = new ArrayList<>();
		try {
			if (routeEMAPStatus.equals("Planned")) {
				for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
					if (entry.getKey().contains("Plan Driver Hours")) {
						planDriverHours = entry.getValue();
					}
				}

				for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
					if (entry.getKey().equals("Route")) {
						route = entry.getValue();
					}
				}

				for (int i = 0; i < route.size(); i++) {
					if (!planDriverHours.get(i).equals("--")) {
						Report.InfoTest(test,
								"Plan Driver Hours is as per EMAP in Commercial Flash report for Planned Route : "
										+ route.get(i) + " with Plan Driver Hours : " + planDriverHours.get(i));
						Report.PassTest(test, "Plan Driver Hours is as expected in Commercial Flash report");
					} else {
						Report.FailTest(test,
								"Plan Driver Hours is not as expected in efficiency report for Planned Route : "
										+ route.get(i) + " with Plan Driver Hours : " + planDriverHours.get(i));
					}
				}

			} else if (routeEMAPStatus.equals("Un-Planned")) {
				for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
					if (entry.getKey().contains("Plan Driver Hours")) {
						planDriverHours = entry.getValue();
					}
				}

				for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
					if (entry.getKey().equals("Route")) {
						route = entry.getValue();
					}
				}

				for (int i = 0; i < route.size(); i++) {
					if (planDriverHours.get(i).equals("--")) {
						Report.InfoTest(test,
								"Plan Driver Hours is as per EMAP in Commercial Flash report for Un-Planned Route : "
										+ route.get(i) + " with Plan Driver Hours : " + planDriverHours.get(i));
						Report.PassTest(test, "Plan Driver Hours is as expected in Commercial Flash report");
					} else {
						Report.FailTest(test,
								"Plan Driver Hours is not as expected in efficiency report for Un-Planned Route : "
										+ route.get(i) + " with Plan Driver Hours : " + planDriverHours.get(i));
					}
				}
			} else {
				Report.FailTest(test,
						"Route status should be either Planned or Un-Planned. Please check method calling.");
			}

		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Plan Driver Hours is not as expected in Commercial Flash report Actual is : "
					+ planDriverHours.toString());
		}

	}

	public void validatePlanEfficiency(Map<String, List<String>> actualEfficiencyTableData, String routeEMAPStatus) {
		List<String> planEfficiency = new ArrayList<>();
		List<String> route = new ArrayList<>();
		try {
			if (routeEMAPStatus.equals("Planned")) {
				for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
					if (entry.getKey().equals("Plan Eff")) {
						planEfficiency = entry.getValue();
					}
				}

				for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
					if (entry.getKey().equals("Route")) {
						route = entry.getValue();
					}
				}

				for (int i = 0; i < route.size(); i++) {
					if (!planEfficiency.get(i).equals("--")) {
						Report.InfoTest(test,
								"Plan Efficiency is as per EMAP in Commercial Flash report for Planned Route : "
										+ route.get(i) + " with Plan Efficiency : " + planEfficiency.get(i));
						Report.PassTest(test, "Plan Efficiency is as expected in Commercial Flash report");
					} else {
						Report.FailTest(test,
								"Plan Efficiency is not as expected in efficiency report for Planned Route : "
										+ route.get(i) + " with Plan Efficiency : " + planEfficiency.get(i));
					}
				}

			} else if (routeEMAPStatus.equals("Un-Planned")) {
				for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
					if (entry.getKey().equals("Plan Eff")) {
						planEfficiency = entry.getValue();
					}
				}

				for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
					if (entry.getKey().equals("Route")) {
						route = entry.getValue();
					}
				}

				for (int i = 0; i < route.size(); i++) {
					if (planEfficiency.get(i).equals("--")) {
						Report.InfoTest(test,
								"Plan Efficiency is as per EMAP in Commercial Flash report for Un-Planned Route : "
										+ route.get(i) + " with Plan Efficiency : " + planEfficiency.get(i));
						Report.PassTest(test, "Plan Efficiency is as expected in Commercial Flash report");
					} else {
						Report.FailTest(test,
								"Plan Efficiency is not as expected in detail report for Un-Planned Route : "
										+ route.get(i) + " with Plan Efficiency : " + planEfficiency.get(i));
					}
				}
			} else {
				Report.FailTest(test,
						"Route status should be either Planned or Un-Planned. Please check method calling.");
			}

		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Plan Efficiency is not as expected in Commercial Flash report Actual is : "
					+ planEfficiency.toString());
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

	public void validateLegendInGraph() throws IOException, InterruptedException {
		
		Thread.sleep(5000);
		String[] AllLegends = { "Actual", "Plan", "Target" };
				
		int noOfLegends = driver.findElements(By.xpath(".//*[@class='highcharts-legend']/*/*/*[@class='highcharts-legend-item']/*[3]")).size();
		System.out.println("There are "+noOfLegends+" Legends in the graph");
				
		for (int i = 1;i<=noOfLegends;i++)
		{
			String LegendVal = driver.findElement(By.xpath(".//*[@class='highcharts-legend']/*/*/*[@class='highcharts-legend-item']["+i+"]/*[3]")).getText();
			if (LegendVal.equals(AllLegends[i-1]))
			{
				Report.PassTest(test, AllLegends[i-1]+" is getting displayed in the Graph Legend");
			}else Report.FailTest(test, AllLegends[i-1]+" is not getting displayed in the Graph Legend");
			
			//String Beforeclick = driver.findElement(By.xpath(".//*[@class='highcharts-legend']/*/*/*[@class='highcharts-legend-item']["+i+"]/*[3]")).getAttribute("style");
			//System.out.println(Beforeclick);
			
			driver.findElement(By.xpath(".//*[@class='highcharts-legend']/*/*/*[@class='highcharts-legend-item']["+i+"]/*[3]")).click();
			Thread.sleep(5000);
			
			String AfterClick = driver.findElement(By.xpath(".//*[@class='highcharts-legend']/*/*/*[@class='highcharts-legend-item']["+i+"]/*[3]")).getAttribute("style");
			System.out.println(AfterClick);
			
			if (driver.findElement(By.xpath(".//*[@class='highcharts-legend']/*/*/*[@class='highcharts-legend-item']["+i+"]/*[3]")).getAttribute("style").contains("rgb(51, 51, 51)"))
			{	
				Report.FailTest(test, AllLegends[i] + " Legend Item is not clickable");
			}else Report.PassTest(test, AllLegends[i] + " Legend is getting displayed and clickable");
			
			driver.findElement(By.xpath(".//*[@class='highcharts-legend']/*/*/*[@class='highcharts-legend-item']["+i+"]/*[3]")).click();
			Thread.sleep(1000);
		}
			
	}

	public void validateAxisInGraph(String FromDate, String ToDate) throws IOException, ParseException {

		// Validate the Y Axis is having Units Per Hour
		if (GraphYAxis.getText().equals("Units Per Hour")) {
			Report.PassTest(test, "Units per Hour is getting displayed in the Y- Axis for the Graph");
		} else
			Report.FailTest(test, "Units per Hour is not getting displayed in the Y-Axis of the Graph ");

		// Validate the X Axis is having Date range
		int noOfDatesXAxis = driver
				.findElements(By
						.xpath(".//*[@class='highcharts-container']/div[@class='highcharts-axis-labels highcharts-xaxis-labels']/span"))
				.size();
		String firstDateXAxis = driver
				.findElement(By
						.xpath(".//*[@class='highcharts-container']/div[@class='highcharts-axis-labels highcharts-xaxis-labels']/span[1]"))
				.getText();
		String lastDateXAxis = driver.findElement(By
				.xpath(".//*[@class='highcharts-container']/div[@class='highcharts-axis-labels highcharts-xaxis-labels']/span["
						+ (noOfDatesXAxis) + "]"))
				.getText();

		// String formatFirstDateXAxis

		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yy");
		Date FromDateFormat = format1.parse(FromDate);
		Date ToDateForamt = format1.parse(ToDate);
		Date firstDateXAxisFormat = format1.parse(firstDateXAxis);
		Date lastDateXAxisFormat = format1.parse(lastDateXAxis);

		if ((firstDateXAxisFormat.getTime() >= FromDateFormat.getTime())
				&& (lastDateXAxisFormat.getTime() <= ToDateForamt.getTime()))

		{
			Report.PassTest(test,
					firstDateXAxis + " and " + lastDateXAxis + " are falling under" + FromDate + " and " + FromDate);
		} else
			Report.FailTest(test, firstDateXAxis + " and " + lastDateXAxis + " are not falling under" + FromDate
					+ " and " + FromDate);

	}

	public void validateTheGraphPoints() throws IOException {
		
		String actualEff = null;
		String planEff = null;
		String targetEff = null;
		
		Util.HoverAndGetText(driver, GraphPointerActual);
		String HeaderDate = GraphPointerActualOverLayHeader.getText();
		String ActualOverLay = GraphPointerActualOverLayActual.getText();
		String PlanOverLay = GraphPointerActualOverLayPlan.getText();
		String TargetOverLay = GraphPointerActualOverLayTarget.getText();
		
		if (HeaderDate!=null)
		{
			Report.PassTest(test, HeaderDate+" : Date is getting Displayed in the Graph OverLay");
		}else
			Report.FailTest(test, "Date is not getting displayed in the Graph OverLay");
		
		Map<String, List<String>> SummaryTableData = getActualSummaryTableData();
		
		for (Entry<String, List<String>> entry : SummaryTableData.entrySet()) 
		{
			if (entry.getKey().equals("Actual Eff")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualEff = "";
					} else {
						actualEff = entry.getValue().get(i);
					}
				}
			}
		}
		for (Entry<String, List<String>> entry : SummaryTableData.entrySet()) 
		{
			if (entry.getKey().equals("Plan Eff")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						planEff = "";
					} else {
						planEff = entry.getValue().get(i);
					}
				}
			}
		}
		for (Entry<String, List<String>> entry : SummaryTableData.entrySet()) 
		{
			if (entry.getKey().equals("Target Eff")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						targetEff = "";
					} else {
						targetEff = entry.getValue().get(i);
					}
				}
			}
		}
		
		if (ActualOverLay.equals(actualEff))
		{
			Report.PassTest(test, ActualOverLay+" is getting displayed in the Summary Section and the Graph Overlay");
		}else Report.FailTest(test, ActualOverLay+" is getting displayed on the Overlay which is not same as in the Summary Section");
		
		if (PlanOverLay.equals(planEff))
		{
			Report.PassTest(test, PlanOverLay+" is getting displayed in the Summary Section and the Graph Overlay");
		}else Report.FailTest(test, PlanOverLay+" is getting displayed on the Overlay which is not same as in the Summary Section");
		
		if (TargetOverLay.equals(targetEff))
		{
			Report.PassTest(test, TargetOverLay+" is getting displayed in the Summary Section and the Graph Overlay");
		}else Report.FailTest(test, TargetOverLay+" is getting displayed on the Overlay which is not same as in the Summary Section");
		
	}
	
public void validateExportToExcelColumns(List<String> excelexportcolumnscommandresiflashreport,String dirPath) {
		
		try
		{
			exportToExcelCommFlashReport.click();
			File recentFile=Util.getLatestFilefromDir(dirPath);
			Excel exportExcelFile = new Excel(dirPath+"/"+recentFile.getName());
			validateColumnNamesInExcel(exportExcelFile,excelexportcolumnscommandresiflashreport,19);
		}
		catch(Exception e)
		{
			Report.FailTest(test, e.getMessage());
			LogClass.error(e.getMessage());
			Report.FailTest(test, "Export to excel is not as expected for commercial flash report");
		}
		
	}

	public void validateColumnNamesInExcel(Excel exportExcelFile,
			List<String> excelexportcolumns,int headerRowNum) {
		
		String expectedExcelColumn="";
		
		try
		{
			for(int i=0;i<excelexportcolumns.size();i++)
			{
				expectedExcelColumn=exportExcelFile.getCellData(headerRowNum-1, i);
				if(expectedExcelColumn.equals(excelexportcolumns.get(i)))
				{
					Report.PassTest(test, "Column name is correct in Excel Export File. Actual is "+excelexportcolumns.get(i)+" and Expected is "+expectedExcelColumn);
				}
				else
				{
					Report.FailTest(test, "Column name is incorrect in excel file. Actual is "+excelexportcolumns.get(i)+" and Expected is "+expectedExcelColumn);
				}
			}
		}
		catch(Exception e)
		{
			Report.FailTest(test, e.getMessage());
			LogClass.error(e.getMessage());
			Report.FailTest(test, "Export to excel file is not as expected");
		}
		
	}

}// LastClosingBracket