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

public class DisposalCycleDetailReport {
	
	ExtentTest test;
	WebDriver driver;

	public DisposalCycleDetailReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//table[@id='dtDisposalTimeSummary']/thead/tr/th")
	List<WebElement> disposalCycleSummaryTableColumns;

	@FindBy(xpath = "//table[@id='dtDisposalTimeSummary']/tbody/tr")
	List<WebElement> disposalCycleSummaryTableRows;
	
	@FindBy(xpath = "//table[@id='t2e']/thead/tr/th")
	List<WebElement> disposalCycleExceptionsTabDetailTableColumns;

	@FindBy(xpath = "//table[@id='t2e']/tbody/tr")
	List<WebElement> disposalCycleExceptionsTabDetailTableRows;
	
	
	@FindBy(xpath = "//table[@id='t2']/thead/tr/th")
	List<WebElement> disposalCycleAllTabDetailTableColumns;

	@FindBy(xpath = "//table[@id='t2']/tbody/tr")
	List<WebElement> disposalCycleAllTabDetailTableRows;
	
	@FindBy(xpath = "//table[@id='t2e']/thead/tr/th")
	List<WebElement> disposalCycleTimeDrilldownTableColumns;

	@FindBy(xpath = "//table[@id='t2e']/tbody/tr")
	List<WebElement> disposalCycleTimeDrilldownTableRows;
	
	@FindBy(id = "inpSubReportOpt_2")
	WebElement actualSubView;
	
	@FindBy(id = "inpSubReportOptEx_2")
	WebElement actualSubViewExceptionTab;
	

	@FindBy(id = "inpSubReportOpt_1")
	WebElement performanceSubView;
	
	@FindBy(id = "tabExceptions")
	WebElement exceptionTab;

	@FindBy(id = "tabAll")
	WebElement allTab;

	
	public Map<String, List<String>> getActualDisposalCycleSummaryTableData() throws IOException {
		System.out.println("inside Actual Summary Data");
		Map<String, List<String>> disposalCycleSummaryTableData = new HashMap<>();
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		
		try {
			disposalCycleSummaryTableData = readTable(disposalCycleSummaryTableColumns, disposalCycleSummaryTableRows, "dtDisposalTimeSummary");
			Report.PassTestScreenshot(test, driver, "Disposal Cycle Summary table data read successfully", "Disposal Cycle detail report Summary section");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Disposal Cycle Detalil summary table data");
		}

		for (Entry<String, List<String>> entry : disposalCycleSummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return disposalCycleSummaryTableData;
	}
	
	public Map<String, List<String>> getActualDisposalCycleDetailExceptionsTabTableData() {
		System.out.println("inside Actual Disposal Cycle Detail Exceptions Tab Table Data");
		Map<String, List<String>> disposalCycleDetailTableData = new HashMap<>();
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		
		try {
			disposalCycleDetailTableData = readTable(disposalCycleExceptionsTabDetailTableColumns, disposalCycleExceptionsTabDetailTableRows, "t2e");
			Report.PassTestScreenshot(test, driver, "Actual Disposal Cycle Detail Exceptions tab table data read successfully",
					"Actual Disposal Cycle Detail Exceptions tab Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Actual Disposal Cycle Detail Exceptions tab table data");
		}
		for (Entry<String, List<String>> entry : disposalCycleDetailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return disposalCycleDetailTableData;
	}
	
	public Map<String, List<String>> getActualDisposalCycleDetailAllTabTableData() {
		System.out.println("inside Actual Disposal Cycle Detail All tab Table Data");
		Map<String, List<String>> disposalCycleDetailTableData = new HashMap<>();
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		
		try {
			disposalCycleDetailTableData = readTable(disposalCycleAllTabDetailTableColumns, disposalCycleAllTabDetailTableRows, "t2");
			Report.PassTestScreenshot(test, driver, "Actual Disposal Cycle Detail All tab table data read successfully",
					"Actual Disposal Cycle Detail All tab Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Actual Disposal Cycle Detail All tab table data");
		}
		for (Entry<String, List<String>> entry : disposalCycleDetailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return disposalCycleDetailTableData;
	}
	
	public Map<String, List<String>> readTable(List<WebElement> columns, List<WebElement> rows, String tableName) {

	
		 if (tableName.equals("t2") || tableName.equals("t2e") ) {
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
		 
		 else if (tableName.equals("disposal drill down") ) {
			 Map<String, List<String>> objTable = new HashMap<>();			
				for (int iCount = 1; iCount <= columns.size(); iCount++) {
					List<String> rowValues = new ArrayList<>();
					for (int row = 1; row <= rows.size(); row++) {

						try {
							rowValues.add(driver.findElement(By.xpath(
									"//table[@id='t2e']/tbody/tr[" + row + "]/td[" + iCount + "]/span"))
									.getText());
						} catch (Exception e) {
							rowValues.add(driver
									.findElement(By.xpath(
											"//table[@id='t2e']/tbody/tr[" + row + "]/td[" + iCount + "]"))
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
		
	}
	
	public Map<String, Map<String, List<String>>> getActualDisposalTimeExceptionTabDrilldownTableData(String route) {
		Map<String, Map<String, List<String>>> disposalTimeExceptionsTabDrilldownTableData = new HashMap<>();
		try {
			Thread.sleep(5000);
			String[] routes = route.split(";");

			Map<String, List<String>> disposalTimeDrilldownTemp = new HashMap<>();

			for (int i = 0; i < routes.length; i++) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t2e']/tbody/tr[" + (i + 1) + "]/td/a/span")).click();
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer,subIDspDetail_Row" + (i + 1));
					disposalTimeDrilldownTemp = readTable(disposalCycleTimeDrilldownTableColumns, disposalCycleTimeDrilldownTableRows, "disposal drill down");
					Report.PassTestScreenshot(test, driver, "Disposal Time Drilldown Exception Tab table data read successfully",
							"Disposal Time Drilldown Exception Tab table Data");
					Util.switchToDefaultWindow();
				} catch (Exception e) {
					Report.FailTest(test, "Not able to read Disposal Time Drilldown Exception Tab table");
				}
				disposalTimeExceptionsTabDrilldownTableData.put(routes[i], disposalTimeDrilldownTemp);
			}

			for (Entry<String, Map<String, List<String>>> entry : disposalTimeExceptionsTabDrilldownTableData.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
				}
			}
			return disposalTimeExceptionsTabDrilldownTableData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to read Disposal Time Drilldown Exception Tab data", "Disposal Time Drilldown Exception Tab");
		}

		return disposalTimeExceptionsTabDrilldownTableData;
	}
	
	public Map<String, Map<String, List<String>>> getActualDisposalTimeAllTabDrilldownTableData(String route) {
		Map<String, Map<String, List<String>>> disposalTimeAllTabDrilldownTableData = new HashMap<>();
		try {
			Thread.sleep(5000);
			String[] routes = route.split(";");

			Map<String, List<String>> disposalTimeDrilldownTemp = new HashMap<>();

			for (int i = 0; i < routes.length; i++) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t2']/tbody/tr[" + (i + 1) + "]/td/a/span")).click();
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer,subIDspDetailAll_Row" + (i + 1));
					disposalTimeDrilldownTemp = readTable(disposalCycleTimeDrilldownTableColumns, disposalCycleTimeDrilldownTableRows, "disposal drill down");
					Report.PassTestScreenshot(test, driver, "Disposal Time Drilldown All Tab table data read successfully",
							"Disposal Time Drilldown All Tab table Data");
					Util.switchToDefaultWindow();
				} catch (Exception e) {
					Report.FailTest(test, "Not able to read A Time Drilldown All Tab table");
				}
				disposalTimeAllTabDrilldownTableData.put(routes[i], disposalTimeDrilldownTemp);
			}

			for (Entry<String, Map<String, List<String>>> entry : disposalTimeAllTabDrilldownTableData.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
				}
			}
			return disposalTimeAllTabDrilldownTableData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to read Disposal Time Drilldown All Tab data", "Disposal Time Drilldown All Tab");
		}

		return disposalTimeAllTabDrilldownTableData;
	}
	
	public void clickDisposalTimeDrilldownExceptionTab() {
		
		try {
			
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t2e']/tbody/tr[1]/td/a")).click();
				Util.switchToDefaultWindow();
			}
		
		 catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to Click on Disposal Time Drill down", "Disposal Cycle Time Drilldown");
		}

		
	}
	
public void clickDisposalTimeDrilldownAllTab() {
		
		try {
			
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t2']/tbody/tr[1]/td/a")).click();
				Util.switchToDefaultWindow();
			}
		
		 catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to Click on Disposal Time Drill down", "Disposal Cycle Time Drilldown");
		}

		
	}

	public void clickOnActualSubView() {
		
		try
		{
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");
			actualSubView.click();
			Util.switchToDefaultWindow();
		}
		catch(Exception e)
		{
			Report.FailTest(test, "Not able to click on Actual SubView");
		}
		
	}
	
public void clickOnActualSubViewExceptionTab() {
		
		try
		{
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");
			actualSubViewExceptionTab.click();
			Util.switchToDefaultWindow();
		}
		catch(Exception e)
		{
			Report.FailTest(test, "Not able to click on Actual SubView of Exception Tab");
		}
		
	}
	
public void clickOnPerformanceSubView() {
		
		try
		{
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");
			performanceSubView.click();
			Util.switchToDefaultWindow();
		}
		catch(Exception e)
		{
			Report.FailTest(test, "Not able to click on Performance SubView");
		}
		
	}

public void clickOnExceptionTab() {
	
	try
	{
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		exceptionTab.click();
		Util.switchToDefaultWindow();
	}
	catch(Exception e)
	{
		Report.FailTest(test, "Not able to click on Exception Tab");
	}
	
}


/**
 * Perform click operation on All tab
 *  
 *  @author kpatel7
 * 
 * @param none
 * 
 * @return void
 * 
 * @throws none
 * 
 * @ImplementedDate 12/25/2017
 * 
 * @LastModifiedDate N/A
 * 
 */

public void clickOnAllTab() {
	
	try
	{
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		allTab.click();
		Util.switchToDefaultWindow();
	}
	catch(Exception e)
	{
		Report.FailTest(test, "Not able to click on All Tab");
	}
	
}


/**
 * Validate the Summary Metrics of Disposal Cycle Detail Report
 *  
 * @author kpatel7
 * 
 * @param
 * 
 * @return void
 * 
 * @ImplementedDate 01/02/2018
 * 
 * @LastModifiedDate N/A
 * 
 */
public void validateSummaryData(Map<String, List<String>> actualSummaryTable,
		Map<String, List<String>> actualDetailExceptionsTabPerformanceSubView,
		Map<String, List<String>> actualDetailAllTabPerformanceSubView,
		Map<String,Map<String, List<String>>> actualDisposalTimeDrilldownExceptionsTabPerformanceSubView,
		Map<String,Map<String, List<String>>> actualDisposalTimeDrilldownAllTabPerformanceSubView) {
	
	validateSummaryTotalExceptionTime(actualSummaryTable,actualDetailExceptionsTabPerformanceSubView);	
	validateSummaryTotalDisposalLoadsWithException(actualSummaryTable,actualDetailAllTabPerformanceSubView,actualDisposalTimeDrilldownExceptionsTabPerformanceSubView);
	validateSummaryTotalDisposalLoads(actualSummaryTable,actualDetailAllTabPerformanceSubView,actualDisposalTimeDrilldownAllTabPerformanceSubView);
	validateSummaryExceptionTimePercent(actualSummaryTable,actualDetailExceptionsTabPerformanceSubView);	
	validateSummaryAvgActualTime(actualSummaryTable,actualDetailAllTabPerformanceSubView,actualDisposalTimeDrilldownAllTabPerformanceSubView);
	validateSummaryAvgThresholdTime(actualSummaryTable);

	
}

private void validateSummaryTotalExceptionTime(Map<String, List<String>> actualSummaryTable,
		Map<String, List<String>> actualDetailExceptionsTabPerformanceSubView) {
	
	//Total Exception Time (h:m) = Total (Disposal Cycle) Exception Time for selected report filter criteria
	
	String actualTotalExceptionTime = null;
	String expectedTotalExceptionTime = null;
	int totalMins = 0;
	try {
		for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
			if (entry.getKey().contains("Total Exception Time (h:m)")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualTotalExceptionTime = "99:99";
					} else {
						actualTotalExceptionTime = entry.getValue().get(i).toString();
					}
				}
			}
		}

		for (Entry<String, List<String>> entry : actualDetailExceptionsTabPerformanceSubView.entrySet()) {
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

		// Validate Time Format of Total Exception Time
		Util.validateTimeFormat(actualTotalExceptionTime, "Total Exception Time (h:m) in Summary Section");

		
		expectedTotalExceptionTime = Util.convertMinsToHours(totalMins);

		if (actualTotalExceptionTime.equals("99:99")) {
			Report.FailTest(test,
					"Total Exception Time (h:m) is not calculated as expected in summary report Actual is : -- and Expected is : "
							+ expectedTotalExceptionTime);
		} else {

			if (actualTotalExceptionTime.equals(expectedTotalExceptionTime)) {
				Report.InfoTest(test, "Total Exception Time (h:m) is correct in summary report Actual is : "
						+ actualTotalExceptionTime + " and Expected is : " + expectedTotalExceptionTime);
				Report.PassTest(test, "Total Exception Time (h:m) is as expected in Summary report");
			} else {
				Report.FailTest(test, "Total Exception Time (h:m) is not as expected in summary report Actual is : "
						+ actualTotalExceptionTime + " and Expected is : " + expectedTotalExceptionTime);
			}
		}
	} catch (Exception e) {
		Report.FailTest(test, "Total Exception Time (h:m) is not as expected in summary report Actual is : "
				+ actualTotalExceptionTime + " and Expected is : " + expectedTotalExceptionTime);
	}
	
	
}

private void validateSummaryTotalDisposalLoadsWithException(Map<String, List<String>> actualSummaryTable,
		Map<String, List<String>> actualDetailAllTabPerformanceSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldownExceptionsTabPerformanceSubView) {
	//Total Disposal Loads w/ Exception = Total count of Disposal loads where exception time > 0 for selected report filter criteria
	int actualTotalDisposalLoadsWithException = 0;
	int expectedTotalDisposalLoadsWithException = 0;
	List<String> route = new ArrayList<>();
	try {
		for (Entry<String, List<String>> entry : actualDetailAllTabPerformanceSubView.entrySet()) {
			if (entry.getKey().equals("Route")) {
				route = entry.getValue();
			}
		}
		
		for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
			if (entry.getKey().contains("Total Disposal Loads w/ Exception")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualTotalDisposalLoadsWithException = 999;
					} else {
						actualTotalDisposalLoadsWithException = Integer.parseInt(entry.getValue().get(i).toString());
						Util.validateWholeNumber(actualTotalDisposalLoadsWithException, "Total Disposal Loads w/ Exception in Summary section");
					}
				}
			}
		}

		for (int j=0;j<route.size();j++)
		{
		
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldownExceptionsTabPerformanceSubView.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Exception Time (h:m)")) {
										
							expectedTotalDisposalLoadsWithException+=entry2.getValue().size();
						
					}
				}
			}
		
			
		}
			
		if (Util.compareNumber(actualTotalDisposalLoadsWithException, expectedTotalDisposalLoadsWithException)) {
			Report.InfoTest(test,
					"Total Disposal Loads w/ Exception is correct in Summary section Actual is : "
							+ actualTotalDisposalLoadsWithException + " and Expected is : "
							+ expectedTotalDisposalLoadsWithException);
			Report.PassTest(test, "Total Disposal Loads w/ Exception is as expected in Summary section");
		} else {
			Report.FailTest(test, "Total Disposal Loads w/ Exception is not as expected in Summary section. Actual is : "
					+ actualTotalDisposalLoadsWithException + " and Expected is : " + expectedTotalDisposalLoadsWithException);
		}
	 

}catch (Exception e) {
		Report.FailTest(test, "Total Disposal Loads w/ Exception is not as expected in Summary section Actual is : "
				+ actualTotalDisposalLoadsWithException + " and Expected is : " + expectedTotalDisposalLoadsWithException);
	}
	
}

private void validateSummaryTotalDisposalLoads(Map<String, List<String>> actualSummaryTable,
		Map<String, List<String>> actualDetailAllTabPerformanceSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldownAllTabPerformanceSubView) {
	//Total Disposal Loads = Total Disposal Loads (exception and non-exception) for selected report filter criteria i
	int actualTotalDisposalLoads = 0;
	int expectedTotalDisposalLoads = 0;
	List<String> route = new ArrayList<>();
	try {
		for (Entry<String, List<String>> entry : actualDetailAllTabPerformanceSubView.entrySet()) {
			if (entry.getKey().equals("Route")) {
				route = entry.getValue();
			}
		}
		
		for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
			if (entry.getKey().contains("Total Disposal Loads")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualTotalDisposalLoads = 999;
					} else {
						actualTotalDisposalLoads = Integer.parseInt(entry.getValue().get(i).toString());
						Util.validateWholeNumber(actualTotalDisposalLoads, "Total Disposal Loads in Summary section");
					}
				}
			}
		}

		for (int j=0;j<route.size();j++)
		{
		
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldownAllTabPerformanceSubView.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Exception Time (h:m)")) {
										
						expectedTotalDisposalLoads+=entry2.getValue().size();
						
					}
				}
			}
		
			
		}
			
		if (Util.compareNumber(actualTotalDisposalLoads, expectedTotalDisposalLoads)) {
			Report.InfoTest(test,
					"Total Disposal Loads is correct in Summary section Actual is : "
							+ actualTotalDisposalLoads + " and Expected is : "
							+ expectedTotalDisposalLoads);
			Report.PassTest(test, "Total Disposal Loads is as expected in Summary section");
		} else {
			Report.FailTest(test, "Total Disposal Loads is not as expected in Summary section. Actual is : "
					+ actualTotalDisposalLoads + " and Expected is : " + expectedTotalDisposalLoads);
		}
	 

}catch (Exception e) {
		Report.FailTest(test, "Total Disposal Loads is not as expected in Summary section Actual is : "
				+ actualTotalDisposalLoads + " and Expected is : " + expectedTotalDisposalLoads);
	}
	
}

private void validateSummaryExceptionTimePercent(Map<String, List<String>> actualSummaryTable,
		Map<String, List<String>> actualDetailExceptionsTabPerformanceSubView) {
	
	//Exception Time % =Total (Disposal Cycle) Exception Time / Total (Actual) Disposal Cycle Time
	
	int actualExceptionTimePercent = 0;
	int expectedExceptionTimePercent = 0;
	int totalDisposalExceptionTime = 0;
	int totalDisposalCycleTime=0;
	List<String> route = new ArrayList<>();
	try {
		
		
		for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
			if (entry.getKey().contains("Exception Time %")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualExceptionTimePercent = 999;
					} else {
						actualExceptionTimePercent = Integer.parseInt(entry.getValue().get(i).toString());
						Util.validateWholeNumber(actualExceptionTimePercent, "Exception Time % in Summary Section");
					}
				}
			}
		}
		
		for (Entry<String, List<String>> entry : actualDetailExceptionsTabPerformanceSubView.entrySet()) {
			if (entry.getKey().contains("Exception Time (h:m)")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (!(entry.getValue().get(i).equals("--"))) {
						String[] split = entry.getValue().get(i).split(":", 2);
						int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
						totalDisposalExceptionTime += mins;
					}
				}
			}
		}
		
		for (Entry<String, List<String>> entry : actualDetailExceptionsTabPerformanceSubView.entrySet()) {
			if (entry.getKey().contains("Disp Cycle Time (h:m)")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (!(entry.getValue().get(i).equals("--"))) {
						String[] split = entry.getValue().get(i).split(":", 2);
						int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
						totalDisposalCycleTime += mins;
					}
				}
			}
		}

		
		expectedExceptionTimePercent = (totalDisposalExceptionTime*100)/totalDisposalCycleTime;
	
			
		if (Util.compareNumber(actualExceptionTimePercent, expectedExceptionTimePercent)) {
			Report.InfoTest(test,
					"Exception Time % is correct in Summary section Actual is : "
							+ actualExceptionTimePercent + " and Expected is : "
							+ expectedExceptionTimePercent);
			Report.PassTest(test, "Exception Time % is as expected in Summary section");
		} else {
			Report.FailTest(test, "Exception Time % is not as expected in Summary section. Actual is : "
					+ actualExceptionTimePercent + " and Expected is : " + expectedExceptionTimePercent);
		}
	 

}catch (Exception e) {
		Report.FailTest(test, "Exception Time % is not as expected in Summary section Actual is : "
				+ actualExceptionTimePercent + " and Expected is : " + expectedExceptionTimePercent);
	}
	
}

private void validateSummaryAvgActualTime(Map<String, List<String>> actualSummaryTable,
		Map<String, List<String>> actualDetailAllTabPerformanceSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldownAllTabPerformanceSubView) {

	//Avg Actual Time (h:m)=Average Actual Disposal Cycle Time for all (exception and non-exception) loads for selected report filter criteria
		String actualAvgTime = null;
		String expectedAvgTime = null;
		int totalDisposalCycleTime=0;
		int totalDisposalLoads=0;
		List<String> route = new ArrayList<>();
		try {
			for (Entry<String, List<String>> entry : actualDetailAllTabPerformanceSubView.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().contains("Avg Actual Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualAvgTime = "99:99";
						} else {
							actualAvgTime = entry.getValue().get(i).toString();
							Util.validateTimeFormat(entry.getValue().get(i).toString(), "Avg Actual Time (h:m) in Summary section");
						}
					}
				}
			}

			for (int j=0;j<route.size();j++)
			{
			
				for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldownAllTabPerformanceSubView.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Disp Cycle Time (h:m)")) {
											
							totalDisposalLoads+=entry2.getValue().size();
							
						}
					}
				}
				
				for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldownAllTabPerformanceSubView.entrySet())
					{
					if (entry.getKey().equals(route.get(j))) {
						for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
							{

								if (entry2.getKey().contains("Disp Cycle Time (h:m)")) {
									for (int i = 0; i < entry.getValue().size(); i++) {
										if (!(entry.getValue().get(i).equals("--"))) {
											String[] split = entry2.getValue().get(i).split(":", 2);
											int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
											totalDisposalCycleTime += mins;
										}
									}
								}
							}
						}
					}
				}
			
				
			}
			
			expectedAvgTime=Util.convertMinsToHours(totalDisposalCycleTime/totalDisposalLoads);
				
			if (Util.compareTime(actualAvgTime, expectedAvgTime)) {
				Report.InfoTest(test,
						"Avg Actual Time (h:m) is correct in Summary section Actual is : "
								+ actualAvgTime + " and Expected is : "
								+ expectedAvgTime);
				Report.PassTest(test, "Avg Actual Time (h:m) is as expected in Summary section");
			} else {
				Report.FailTest(test, "Avg Actual Time (h:m) is not as expected in Summary section. Actual is : "
						+ actualAvgTime + " and Expected is : " + expectedAvgTime);
			}
		 

	}catch (Exception e) {
			Report.FailTest(test, "Avg Actual Time (h:m) is not as expected in Summary section Actual is : "
					+ actualAvgTime + " and Expected is : " + expectedAvgTime);
		}
	
}

private void validateSummaryAvgThresholdTime(Map<String, List<String>> actualSummaryTable) {
	// TODO Auto-generated method stub
	
}

public void validateDetailData(Map<String, List<String>> actualDetailExceptionsTabPerformanceSubView,
		Map<String, List<String>> actualDetailExceptionsTabActualSubView,
		Map<String, List<String>> actualDetailAllTabPerformanceSubView,
		Map<String, List<String>> actualDetailAllTabActualSubView, 
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldownExceptionsTabPerformanceSubView, 
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldownExceptionsTabActualSubView, 
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldownAllTabPerformanceSubView, 
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldownAllTabActualSubView, 
		String siteID, String lOB, String route, String driverName, String fromDate, String toDate) {
	
	
	validateDetailDate(actualDetailExceptionsTabPerformanceSubView,fromDate,toDate,"Exceptions","Performance");	
	validateDetailRoute(actualDetailExceptionsTabPerformanceSubView,route,"Exceptions","Performance");
	validateDetailDriver(actualDetailExceptionsTabPerformanceSubView,driverName,"Exceptions","Performance");
	validateDetailExceptionTime(actualDetailExceptionsTabPerformanceSubView,actualDisposalTimeDrilldownExceptionsTabPerformanceSubView,"Exceptions","Performance");
	validateDetailDispCycleTime(actualDetailExceptionsTabPerformanceSubView,actualDisposalTimeDrilldownExceptionsTabPerformanceSubView,"Exceptions","Performance");
	validateDetailTonsPerLoad(actualDetailExceptionsTabPerformanceSubView,actualDisposalTimeDrilldownExceptionsTabActualSubView,"Exceptions","Performance");	
	//validateDetailLoadVariance(actualDetailExceptionsTabPerformanceSubView,"Exceptions","Performance");	
	validateDetailLOB(actualDetailExceptionsTabPerformanceSubView,lOB,"Exceptions","Performance");
	validateDetailSubLOB(actualDetailExceptionsTabPerformanceSubView,"Exceptions","Performance");
	validateDetailRouteType(actualDetailExceptionsTabPerformanceSubView,"Exceptions","Performance");
	//validateDetailReportGroup();
	
	validateDetailDate(actualDetailExceptionsTabActualSubView,fromDate,toDate,"Exceptions","Actual");
	validateDetailRoute(actualDetailExceptionsTabActualSubView,route,"Exceptions","Actual");
	validateDetailDriver(actualDetailExceptionsTabActualSubView,driverName,"Exceptions","Actual");
	validateDetailExceptionTime(actualDetailExceptionsTabActualSubView,actualDisposalTimeDrilldownExceptionsTabActualSubView,"Exceptions","Performance");
	validateDetailDispCycleTime(actualDetailExceptionsTabActualSubView,actualDisposalTimeDrilldownExceptionsTabActualSubView,"Exceptions","Actual");
	validateDetailLOB(actualDetailExceptionsTabActualSubView,lOB,"Exceptions","Actual");
	validateDetailSubLOB(actualDetailExceptionsTabActualSubView,"Exceptions","Actual");
	validateDetailRouteType(actualDetailExceptionsTabActualSubView,"Exceptions","Actual");
	//validateDetailRouteManager();
	//validateDetailReportGroup();
	
	validateDetailDate(actualDetailAllTabPerformanceSubView,fromDate,toDate,"All","Performance");	
	validateDetailRoute(actualDetailAllTabPerformanceSubView,route,"All","Performance");
	validateDetailDriver(actualDetailAllTabPerformanceSubView,driverName,"All","Performance");
	validateDetailExceptionTime(actualDetailAllTabPerformanceSubView,actualDisposalTimeDrilldownAllTabPerformanceSubView,"All","Performance");
	validateDetailDispCycleTime(actualDetailAllTabPerformanceSubView,actualDisposalTimeDrilldownAllTabPerformanceSubView,"All","Performance");
	validateDetailTonsPerLoad(actualDetailAllTabPerformanceSubView,actualDisposalTimeDrilldownAllTabActualSubView,"All","Performance");	
	//validateDetailLoadVariance(actualDetailAllTabPerformanceSubView,"All","Performance");	
	validateDetailLOB(actualDetailAllTabPerformanceSubView,lOB,"All","Performance");
	validateDetailSubLOB(actualDetailAllTabPerformanceSubView,"All","Performance");
	validateDetailRouteType(actualDetailAllTabPerformanceSubView,"All","Performance");
	//validateDetailReportGroup();
	
	validateDetailDate(actualDetailAllTabActualSubView,fromDate,toDate,"All","Actual");
	validateDetailRoute(actualDetailAllTabActualSubView,route,"All","Actual");
	validateDetailDriver(actualDetailAllTabActualSubView,driverName,"All","Actual");
	validateDetailExceptionTime(actualDetailAllTabActualSubView,actualDisposalTimeDrilldownAllTabActualSubView,"All","Actual");
	validateDetailDispCycleTime(actualDetailAllTabActualSubView,actualDisposalTimeDrilldownAllTabActualSubView,"All","Actual");
	validateDetailLOB(actualDetailAllTabActualSubView,lOB,"All","Actual");
	validateDetailSubLOB(actualDetailAllTabActualSubView,"All","Actual");
	validateDetailRouteType(actualDetailAllTabActualSubView,"All","Actual");
	//validateDetailRouteManager();
	//validateDetailReportGroup();
	
	
}

private void validateDetailDate(Map<String, List<String>> actualDetailSubViewTable, String fromDate,
		String toDate, String tabName, String subViewName) {
	
	SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yy");
	Date startDate = null;
	Date endDate = null;
	List<Date> routeServedDate = new ArrayList<>();
	List<String> actualRoutes = null;
	try {
		startDate = format1.parse(fromDate);
		endDate = format1.parse(toDate);
		for (Entry<String, List<String>> entry : actualDetailSubViewTable.entrySet()) {
			if (entry.getKey().equals("Route")) {
				actualRoutes = entry.getValue();
			}
		}

		for (Entry<String, List<String>> entry : actualDetailSubViewTable.entrySet()) {
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
						"Route Date is correct in " + tabName+" Tab "+ subViewName + " Sub view report for route : "
								+ actualRoutes.get(i) + ". Actual is : " + format1.format(routeServedDate.get(i))
								+ " and Expected is between : " + format1.format(startDate) + "and "
								+ format1.format(endDate));
				Report.PassTest(test, "Route Date is as expected in " + tabName+" Tab "+ subViewName + " sub view report");
			} else {
				Report.FailTest(test,
						"Route Date is not as expected in " + tabName+" Tab "+ subViewName + " sub view report Actual is : "
								+ format1.format(routeServedDate.get(i)) + " and Expected is between : "
								+ format1.format(startDate) + "and " + format1.format(endDate));
			}
		}

	} catch (Exception e) {
		Report.FailTest(test, "Not able to compare date is in between the date range");
		LogClass.error(e.getMessage());
	}
	
}

private void validateDetailRoute(Map<String, List<String>> actualDetailSubViewTable, String expectedRouteName,
		String tabName, String subViewName) {
	
	String[] routes = expectedRouteName.split(";");
	List<String> actualRoutes = null;
	try {
		for (Entry<String, List<String>> entry : actualDetailSubViewTable.entrySet()) {
			if (entry.getKey().equals("Route")) {
				actualRoutes = entry.getValue();
			}
		}
		for (int i = 0; i < routes.length; i++) {
			if (actualRoutes.contains(routes[i])) {
				Report.InfoTest(test,
						"Route Name is correct in " + tabName+" Tab "+ subViewName + " Sub View report Actual List is : "
								+ actualRoutes.toString() + " and Expected is : " + routes[i]);
				Report.PassTest(test, "Route Name is as expected in " + tabName+" Tab "+ subViewName + " Sub View report");
			} else {
				Report.FailTest(test,
						"Route Name is not as expected in " + tabName+" Tab "+ subViewName + " Sub View report Actual List is : " + actualRoutes.toString()
								+ " and Expected is : " + routes[i]);
			}
		}

	} catch (Exception e) {
		Report.FailTest(test,
				"Route is not as expected in " + tabName+" Tab "+ subViewName + " Sub View report Actual List is : "
						+ actualRoutes.toString() + " and Expected is : " + routes.toString());
	}
	
}

private void validateDetailDriver(Map<String, List<String>> actualDetailSubViewTable,
		String expectedDriverName, String tabName, String subViewName) {

	String[] drivers = expectedDriverName.split(";");
	List<String> actualDrivers = null;
	try {
		for (Entry<String, List<String>> entry : actualDetailSubViewTable.entrySet()) {
			if (entry.getKey().equals("Driver")) {
				actualDrivers = entry.getValue();
			}
		}
		for (int i = 0; i < drivers.length; i++) {
			if (actualDrivers.contains(drivers[i])) {
				Report.InfoTest(test,
						"Driver Name is correct in " + tabName+" Tab "+ subViewName + " Sub View report Actual List is : "
								+ actualDrivers.toString() + " and Expected is : " + drivers[i]);
				Report.PassTest(test, "Driver Name is as expected in " + tabName+" Tab "+ subViewName + " sub view report");
			} else {
				Report.FailTest(test,
						"Driver Name is not as expected in " + tabName+" Tab "+ subViewName + " Sub View report Actual List is : " + actualDrivers.toString()
								+ " and Expected is : " + drivers[i]);
			}
		}

	} catch (Exception e) {
		Report.FailTest(test,
				"Driver Name is not as expected in " + tabName+" Tab "+ subViewName + " Sub View report Actual List is : "
						+ actualDrivers.toString() + " and Expected is : " + drivers.toString());
	}
	
}

private void validateDetailExceptionTime(Map<String, List<String>> actualDetailSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldown, 
		 String tabName, String subViewName) {
	
	
	//Exception Time (h:m)= Sum(Exception Time for each disposal Loads)
	
			
			List<String> actualExceptionTime=new ArrayList<>();
			String expectedExceptionTime=null;
			int totalMins=0;
			List<String> route = new ArrayList<>();
			try {
				for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
					if (entry.getKey().equals("Route")) {
						route = entry.getValue();
					}
				}
				
				for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
					if (entry.getKey().equals("Exception Time (h:m)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								actualExceptionTime.add("00:00");
							} else {
								actualExceptionTime.add(entry.getValue().get(i));
								// Validate Decimal Format for the value : should
								// be hh:mm
								Util.validateTimeFormat(entry.getValue().get(i), "Exception Time (h:m) in " + tabName+" Tab "+ subViewName + " Sub View report");
							}
						}
					}
				}
				
				
				
				for (int j=0;j<route.size();j++)
				{
				
					for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
						if(entry.getKey().equals(route.get(j)))
						for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
							if (entry2.getKey().equals("Exception Time (h:m)")) {
								for (int i = 0; i < entry2.getValue().size(); i++) {
									if (!(entry2.getValue().get(i).equals("--"))) {										
										String[] split = entry2.getValue().get(i).split(":", 2);
										int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
										totalMins += mins;
										}
								}
							}
						}
					}
				

				
				
				expectedExceptionTime=Util.convertMinsToHours(totalMins);
				if (Util.compareTime(actualExceptionTime.get(j), expectedExceptionTime)) {
					Report.InfoTest(test,
							"Exception Time (h:m) is correct in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle Detail report for Route : "+route.get(j)+". Actual is : "
									+ actualExceptionTime.get(j) + " and Expected is : "
									+ expectedExceptionTime);
					Report.PassTest(test, "Exception Time (h:m) is as expected in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle report for Route : "+route.get(j));
				} else {
					Report.FailTest(test, "Exception Time (h:m) is not as expected in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle report for Route : "+route.get(j)+". Actual is : "
							+ actualExceptionTime.get(j) + " and Expected is : " + expectedExceptionTime);
				}
			} 
		
		}catch (Exception e) {
				Report.FailTest(test, "Exception Time (h:m) is not as expected in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle report Actual is : "
						+ actualExceptionTime + " and Expected is : " + expectedExceptionTime);
			}
	
}

private void validateDetailDispCycleTime(Map<String, List<String>> actualDetailSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldown,
		String tabName, String subViewName) {
	
	//Disposal Cycle Time (h:m)= Sum(Disposal Cycle Time for each disposal Loads)
	
	
	List<String> actualDisposalCycleTime=new ArrayList<>();
	String expectedDisposalCycleTime=null;
	int totalMins=0;
	List<String> route = new ArrayList<>();
	try {
		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
			if (entry.getKey().equals("Route")) {
				route = entry.getValue();
			}
		}
		
		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
			if (entry.getKey().equals("Disp Cycle Time (h:m)")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualDisposalCycleTime.add("00:00");
					} else {
						actualDisposalCycleTime.add(entry.getValue().get(i));
						// Validate Decimal Format for the value : should
						// be hh:mm
						Util.validateTimeFormat(entry.getValue().get(i), "Disp Cycle Time (h:m) in " + tabName+" Tab "+ subViewName + " Sub View report");
					}
				}
			}
		}
		
		
		
		for (int j=0;j<route.size();j++)
		{
		
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Disp Cycle Time (h:m)")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (!(entry2.getValue().get(i).equals("--"))) {										
								String[] split = entry2.getValue().get(i).split(":", 2);
								int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
								totalMins += mins;
								}
						}
					}
				}
			}
		

		
		
		expectedDisposalCycleTime=Util.convertMinsToHours(totalMins);
		if (Util.compareTime(actualDisposalCycleTime.get(j), expectedDisposalCycleTime)) {
			Report.InfoTest(test,
					"Disp Cycle Time (h:m) is correct in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle Detail report for Route : "+route.get(j)+". Actual is : "
							+ actualDisposalCycleTime.get(j) + " and Expected is : "
							+ expectedDisposalCycleTime);
			Report.PassTest(test, "Disp Cycle Time (h:m) is as expected in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle report for Route : "+route.get(j));
		} else {
			Report.FailTest(test, "Disp Cycle Time (h:m) is not as expected in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle report for Route : "+route.get(j)+". Actual is : "
					+ actualDisposalCycleTime.get(j) + " and Expected is : " + expectedDisposalCycleTime);
		}
	} 

}catch (Exception e) {
		Report.FailTest(test, "Disp Cycle Time (h:m) is not as expected in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle report Actual is : "
				+ actualDisposalCycleTime + " and Expected is : " + expectedDisposalCycleTime);
	}
	
}

private void validateDetailTonsPerLoad(Map<String, List<String>> actualDetailSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldown, String tabName,
		String subViewName) {
	
	//Tons/Load= Total Tons/Number of disposal Trips
	
	
		List<Double> actualTonsPerLoad=new ArrayList<>();
		double expectedTonsPerLoad=0.00;
		List<String> route = new ArrayList<>();
		double totalTons=0.00;
		int noOfDisposalLoads=0;
		try {
			for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			
			for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
				if (entry.getKey().equals("Tons/Load")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualTonsPerLoad.add(0.00);
						} else {
							actualTonsPerLoad.add(Double.parseDouble(entry.getValue().get(i)));
							// Validate Decimal Format for the value : should
							// be 2
							Util.validateFieldDecimalPlaces(entry.getValue().get(i),2, "Tons/Load in " + tabName+" Tab "+ subViewName + " Sub View report");
						}
					}
				}
			}
			
			
			
			for (int j=0;j<route.size();j++)
			{
			
				for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Tons")) {
							for (int i = 0; i < entry2.getValue().size(); i++) {
								if (!(entry2.getValue().get(i).equals("--"))) {										
									totalTons+=Double.parseDouble(entry2.getValue().get(i));
									}
							}
						}
					}
				}
				
				for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Disp Cycle Time (h:m)")) {
							
							noOfDisposalLoads=entry2.getValue().size();
								
							}
						}
					}

			expectedTonsPerLoad=totalTons/noOfDisposalLoads;
			if (Util.compareNumber(actualTonsPerLoad.get(j), expectedTonsPerLoad)) {
				Report.InfoTest(test,
						"Tons/Load is correct in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle Detail report for Route : "+route.get(j)+". Actual is : "
								+ actualTonsPerLoad.get(j) + " and Expected is : "
								+ expectedTonsPerLoad);
				Report.PassTest(test, "Tons/Load is as expected in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle report for Route : "+route.get(j));
			} else {
				Report.FailTest(test, "Tons/Load is not as expected in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle report for Route : "+route.get(j)+". Actual is : "
						+ actualTonsPerLoad.get(j) + " and Expected is : " + expectedTonsPerLoad);
			}
		} 

	}catch (Exception e) {
			Report.FailTest(test, "Tons/Load is not as expected in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle report Actual is : "
					+ actualTonsPerLoad + " and Expected is : " + expectedTonsPerLoad);
		}
	
}

private void validateDetailLoadVariance(Map<String, List<String>> actualDetailSubView,
		String tabName, String subViewName) {
	// TODO Auto-generated method stub
	
}

private void validateDetailLOB(Map<String, List<String>> actualDetailSubView, String expectedLOB,
		String tabName, String subViewName) {
	String actualLOB = null;
	try {
		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
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

			Report.InfoTest(test, "LOB is correct in  " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle Detail report Actual is : "
					+ actualLOB + " and Expected is : " + expectedLOB);
			Report.PassTest(test, "LOB is as expected in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle Detail report");
		} else {
			Report.FailTest(test, "LOB is not as expected in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle Detail report Actual is : "
					+ actualLOB + " and Expected is : " + expectedLOB);
		}
	} catch (Exception e) {
		Report.FailTest(test, "LOB is not as expected in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle Detail report Actual List is : " + actualLOB + " and Expected is : " + expectedLOB);
	}

}

private void validateDetailSubLOB(Map<String, List<String>> actualDetailSubView, String tabName,
		String subViewName) {
	
	List<String> actualSubLOB = new ArrayList<>();
	List<String> expectedSubLOB = new ArrayList<>();
	List<String> route = new ArrayList<>();

	try {
		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
			if (entry.getKey().equals("Sub LOB")) {
				actualSubLOB = entry.getValue();
			}
		}

		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
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
						"Sub LOB is correct in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle Detail report for Route : "
								+ route.get(i) + " with Actual SubLOB : " + actualSubLOB.get(0)
								+ " and expected Sub LOB : " + expectedSubLOB.get(0));
				Report.PassTest(test, "Sub LOB is as expected in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle Detail report");
			} else {
				Report.FailTest(test,
						"Sub LOB is not as expected in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle Detail report for Route : "
								+ route.get(i) + " with Actual Sub LOB : " + actualSubLOB.get(i)
								+ " and expected Sub LOB : " + expectedSubLOB.get(0));
			}
		}
	} catch (Exception e) {
		Report.FailTest(test, e.getMessage());
		Report.FailTest(test, "Sub LOB is not as expected in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle Detail report Actual is : "
				+ actualSubLOB.toString() + " and Expected is : " + expectedSubLOB.toString());
	}
	
}

private void validateDetailRouteType(Map<String, List<String>> actualDetailSubView,
		String tabName, String subViewName) {
	
	List<String> actualRouteType = new ArrayList<>();
	List<String> expectedRouteType = new ArrayList<>();
	List<String> route = new ArrayList<>();
	List<String> date = new ArrayList<>();
	String expectedRouteTypes = "";
	SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");

	try {
		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
			if (entry.getKey().equals("Route Type")) {
				actualRouteType = entry.getValue();
			}
		}

		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
			if (entry.getKey().equals("Route")) {
				route = entry.getValue();
			}
		}

		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
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
						"Route Type  is correct in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle Detail report for Route : "
								+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
								+ " and expected Route Type  : " + expectedRouteTypes);
				Report.PassTest(test, "Route Type is as expected in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle Detail Sub View report");
			} else {
				Report.FailTest(test,
						"Route Type is not as expected in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle Detail report for Route : "
								+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
								+ " and expected Route Type : " + expectedRouteTypes);
			}
		}
	} catch (Exception e) {
		Report.FailTest(test, e.getMessage());
		Report.FailTest(test,
				"Route Type  is not as expected in " + tabName+" Tab "+ subViewName + " subView of Disposal Cycle Detail report Actual is : "
						+ actualRouteType.toString() + " and Expected is : " + expectedRouteType.toString());
	}
	
}

public void validateDisposalTimeDrilldownData(Map<String, List<String>> actualDetailExceptionsTabPerformanceSubView,
		Map<String, List<String>> actualDetailExceptionsTabActualSubView,
		Map<String, List<String>> actualDetailAllTabPerformanceSubView,
		Map<String, List<String>> actualDetailAllTabActualSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldownExceptionsTabPerformanceSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldownExceptionsTabActualSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldownAllTabPerformanceSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldownAllTabActualSubView, String siteID,
		String lOB, String route, String driverName, String fromDate, String toDate) {
	
	validateDrilldownExceptionTime(actualDetailExceptionsTabPerformanceSubView,actualDisposalTimeDrilldownExceptionsTabPerformanceSubView,"Exceptions","Performance");
	validateDrilldownDispCycleTime(actualDetailExceptionsTabPerformanceSubView,actualDisposalTimeDrilldownExceptionsTabPerformanceSubView,"Exceptions","Performance");
	validateDrilldownDispSite(actualDetailExceptionsTabPerformanceSubView,actualDisposalTimeDrilldownExceptionsTabPerformanceSubView,"Exceptions","Performance");
	validateDrilldownTimeOfDay(actualDetailExceptionsTabPerformanceSubView,actualDisposalTimeDrilldownExceptionsTabPerformanceSubView,"Exceptions","Performance");
	
	validateDrilldownExceptionTime(actualDetailExceptionsTabActualSubView,actualDisposalTimeDrilldownExceptionsTabActualSubView,"Exceptions","Actual");
	validateDrilldownDispCycleTime(actualDetailExceptionsTabActualSubView,actualDisposalTimeDrilldownExceptionsTabActualSubView,"Exceptions","Actual");
	validateDrilldownDispSite(actualDetailExceptionsTabActualSubView,actualDisposalTimeDrilldownExceptionsTabActualSubView,"Exceptions","Actual");
	validateDrilldownArrive(actualDetailExceptionsTabActualSubView,actualDisposalTimeDrilldownExceptionsTabActualSubView,"Exceptions","Actual");
	validateDrilldownDepart(actualDetailExceptionsTabActualSubView,actualDisposalTimeDrilldownExceptionsTabActualSubView,"Exceptions","Actual");
	validateDrilldownDownTime(actualDetailExceptionsTabActualSubView,actualDisposalTimeDrilldownExceptionsTabActualSubView,"Exceptions","Actual");
	validateDrilldownMeal(actualDetailExceptionsTabActualSubView,actualDisposalTimeDrilldownExceptionsTabActualSubView,"Exceptions","Actual");
	validateDrilldownTons(actualDetailExceptionsTabActualSubView,actualDisposalTimeDrilldownExceptionsTabActualSubView,"Exceptions","Actual");
	
	validateDrilldownExceptionTime(actualDetailAllTabPerformanceSubView,actualDisposalTimeDrilldownAllTabPerformanceSubView,"All","Performance");
	validateDrilldownDispCycleTime(actualDetailAllTabPerformanceSubView,actualDisposalTimeDrilldownAllTabPerformanceSubView,"All","Performance");
	validateDrilldownDispSite(actualDetailAllTabPerformanceSubView,actualDisposalTimeDrilldownAllTabPerformanceSubView,"All","Performance");
	validateDrilldownTimeOfDay(actualDetailAllTabPerformanceSubView,actualDisposalTimeDrilldownAllTabPerformanceSubView,"All","Performance");
	
	
	validateDrilldownExceptionTime(actualDetailAllTabActualSubView,actualDisposalTimeDrilldownAllTabActualSubView,"All","Actual");
	validateDrilldownDispCycleTime(actualDetailAllTabActualSubView,actualDisposalTimeDrilldownAllTabActualSubView,"All","Actual");
	validateDrilldownDispSite(actualDetailAllTabActualSubView,actualDisposalTimeDrilldownAllTabActualSubView,"All","Actual");
	validateDrilldownArrive(actualDetailAllTabActualSubView,actualDisposalTimeDrilldownAllTabActualSubView,"All","Actual");
	validateDrilldownDepart(actualDetailAllTabActualSubView,actualDisposalTimeDrilldownAllTabActualSubView,"All","Actual");
	validateDrilldownDownTime(actualDetailAllTabActualSubView,actualDisposalTimeDrilldownAllTabActualSubView,"All","Actual");
	validateDrilldownMeal(actualDetailAllTabActualSubView,actualDisposalTimeDrilldownAllTabActualSubView,"All","Actual");
	validateDrilldownTons(actualDetailAllTabActualSubView,actualDisposalTimeDrilldownAllTabActualSubView,"All","Actual");
	
	
}

private void validateDrilldownTimeOfDay(Map<String, List<String>> actualDetailSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldown, String tabName,
		String subViewName) {
	// TODO Auto-generated method stub
	
}

private void validateDrilldownExceptionTime(Map<String, List<String>> actualDetailSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldown, String tabName,
		String subViewName) {
	// TODO Auto-generated method stub
	
}

private void validateDrilldownDispCycleTime(Map<String, List<String>> actualDetailSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldown, String tabName,
		String subViewName) {
	
	// Disposal Cycle Time = [Depart Time - Arrive Time] - Meal Time - Downtime
	
	List<String> departTime = new ArrayList<>();
	List<String> arriveTime= new ArrayList<>();
	List<String> downTime = new ArrayList<>();
	List<String> mealTime= new ArrayList<>();
	List<String> actualDisposalCycleTime=new ArrayList<>();
	String expectedDisposalCycleTime=null;
	int noOfDisposalLoads=0;
	List<String> route = new ArrayList<>();
	
	try {
		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
			if (entry.getKey().equals("Route")) {
				route = entry.getValue();
			}
		}
		
		
		for (int j=0;j<route.size();j++)
		{
		
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Depart")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								departTime.add("00:00");
							} else {
								departTime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
							}
						}
					}
				}
			}
		
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Arrive")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								arriveTime.add("00:00");
							} else {
								arriveTime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
							}
						}
					}
				}
			}
			
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Down Time (h:m)")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								downTime.add("00:00");
							} else {
								downTime.add(entry2.getValue().get(i).toString().trim());
							}
						}
					}
				}
			}
			
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Meal (h:m)")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								mealTime.add("00:00");
							} else {
								mealTime.add(entry2.getValue().get(i).toString().trim());
							}
						}
					}
				}
			}
			
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Disp Cycle Time (h:m)")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								actualDisposalCycleTime.add("00:00");
							} else {
								actualDisposalCycleTime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
								
							}
						}
					}
				}
			}
			
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Disp Cycle Time (h:m)")) {
						noOfDisposalLoads=entry2.getValue().size();
						}
					}
				}
			
		
		List<Integer> departTimeMins=new ArrayList<>();
		List<Integer> arriveTimeMins=new ArrayList<>();
		List<Integer> mealTimeMins=new ArrayList<>();
		List<Integer> downTimeMins=new ArrayList<>();
		
		for(int i=0;i<departTime.size();i++)
		{
			departTimeMins.add(Util.convertHoursToMins(departTime.get(i)));
		}
		for(int i=0;i<arriveTime.size();i++)
		{
			arriveTimeMins.add(Util.convertHoursToMins(arriveTime.get(i)));
		}

		for(int i=0;i<mealTime.size();i++)
		{
			mealTimeMins.add(Util.convertHoursToMins(mealTime.get(i)));
		}
		for(int i=0;i<downTime.size();i++)
		{
			downTimeMins.add(Util.convertHoursToMins(downTime.get(i)));
		}
		int disposalCycleTimeInMins=0;
		for (int i = 0; i < noOfDisposalLoads; i++) {
			disposalCycleTimeInMins =  (departTimeMins.get(i)-arriveTimeMins.get(i))-downTimeMins.get(i)-mealTimeMins.get(i);	
				

		expectedDisposalCycleTime=Util.convertMinsToHours(disposalCycleTimeInMins);	
		if (Util.compareTime(actualDisposalCycleTime.get(i), expectedDisposalCycleTime)) {
			Report.InfoTest(test,
					"Disposal Cycle Time is correct in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j)+". Actual is : "
							+ actualDisposalCycleTime.get(i) + " and Expected is : "
							+ expectedDisposalCycleTime);
			Report.PassTest(test, "Disposal Cycle Time is as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j));
		} else {
			Report.FailTest(test, "Disposal Cycle Time is not as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j)+". Actual is : "
					+ actualDisposalCycleTime.get(i) + " and Expected is : " + expectedDisposalCycleTime);
		}
	} 
}
		
}catch (Exception e) {
		Report.FailTest(test, "Disposal Cycle Time is not as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report Actual is : "
				+ actualDisposalCycleTime + " and Expected is : " + expectedDisposalCycleTime);
	}
	
}

private void validateDrilldownDispSite(Map<String, List<String>> actualDetailSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldown, String tabName,
		String subViewName) {
	
	List<String> actualDisposalSite = new ArrayList<>();
	List<String> expectedDisposalSite = new ArrayList<>();			
	int noOfDisposalLoads=0;
	SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
	List<String> date = new ArrayList<>();
	List<String> route = new ArrayList<>();
	try {
		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
			if (entry.getKey().equals("Route")) {
				route = entry.getValue();
			}
		}
		
		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
			if (entry.getKey().contains("Date")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					Date dates = format1.parse(entry.getValue().get(i));
					date.add(format2.format(dates));
				}
			}
		}


		
		for (int j=0;j<route.size();j++)
		{
		
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Disp Site")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {						
								actualDisposalSite.add(entry2.getValue().get(i).trim());
							
						}
					}
				}
			}
			
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Disp Cycle Time (h:m)")) {
						noOfDisposalLoads=entry2.getValue().size();
						}
					}
				}
		
			

		expectedDisposalSite=Util.getDataFromDB("select NAME from OCS_ADMIN.TP_LANDFILL where PKEY in (select FK_LANDFILL from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID =  ('"+route.get(j)+"')) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
		+ Util.sqlFormatedList(date)
		+")) and FK_VEHICLE IS NOT NULl)");
	
		for (int i = 0; i < noOfDisposalLoads; i++) {
	
		//if (actualDisposalSite.get(i).equals(expectedDisposalSite.get(i))) {
		if (expectedDisposalSite.contains(actualDisposalSite.get(i))) {
			Report.InfoTest(test,
					"Disposal Site Name is correct in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j)+". Actual is : "
							+ actualDisposalSite.get(i) + " and Expected is : "
							+ expectedDisposalSite.get(i));
			Report.PassTest(test, "Disposal Site Name is as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j));
		} else {
			Report.FailTest(test, "Disposal Site Name is not as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j)+". Actual is : "
					+ actualDisposalSite.get(i) + " and Expected is : " + expectedDisposalSite.get(i));
		}
	} 
}

}catch (Exception e) {
		Report.FailTest(test, "Disposal Site Name is not as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report Actual is : "
				+ actualDisposalSite + " and Expected is : " + expectedDisposalSite);
	}
	
	
}

private void validateDrilldownArrive(Map<String, List<String>> actualDetailSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldown, String tabName,
		String subViewName) {
	
	List<String> actualArriveTime = new ArrayList<>();
	List<String> expectedArriveTime = new ArrayList<>();			
	int noOfDisposalLoads=0;
	SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
	List<String> date = new ArrayList<>();
	List<String> route = new ArrayList<>();
	try {
		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
			if (entry.getKey().equals("Route")) {
				route = entry.getValue();
			}
		}
		
		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
			if (entry.getKey().contains("Date")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					Date dates = format1.parse(entry.getValue().get(i));
					date.add(format2.format(dates));
				}
			}
		}


		
		for (int j=0;j<route.size();j++)
		{
		
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Arrive")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {						
							actualArriveTime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
							
						}
					}
				}
			}
			
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Disp Cycle Time (h:m)")) {
						noOfDisposalLoads=entry2.getValue().size();
						}
					}
				}
		
			

			expectedArriveTime=Util.getDataFromDB(
					"select  TO_CHAR(ARRIVESTAMP - (6 / 24), 'HH:MI') from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID =  '"+ route.get(j) + "') and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");
	
		
		

		for (int i = 0; i < noOfDisposalLoads; i++) {
		
		
		if (actualArriveTime.get(i).equals(expectedArriveTime.get(i))) {
			Report.InfoTest(test,
					"Arrive Time is correct in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j)+". Actual is : "
							+ actualArriveTime.get(i) + " and Expected is : "
							+ expectedArriveTime.get(i));
			Report.PassTest(test, "Arrive Time is as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j));
		} else {
			Report.FailTest(test, "Arrive Time is not as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j)+". Actual is : "
					+ actualArriveTime.get(i) + " and Expected is : " + expectedArriveTime.get(i));
		}
	} 
}

}catch (Exception e) {
		Report.FailTest(test, "Arrive Time is not as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report Actual is : "
				+ actualArriveTime + " and Expected is : " + expectedArriveTime);
	}
	
}

private void validateDrilldownDepart(Map<String, List<String>> actualDetailSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldown, String tabName,
		String subViewName) {
	
	List<String> actualDepartTime = new ArrayList<>();
	List<String> expectedDepartTime = new ArrayList<>();			
	int noOfDisposalLoads=0;
	SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
	List<String> date = new ArrayList<>();
	List<String> route = new ArrayList<>();
	try {
		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
			if (entry.getKey().equals("Route")) {
				route = entry.getValue();
			}
		}
		
		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
			if (entry.getKey().contains("Date")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					Date dates = format1.parse(entry.getValue().get(i));
					date.add(format2.format(dates));
				}
			}
		}


		
		for (int j=0;j<route.size();j++)
		{
		
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Depart")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {						
							actualDepartTime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
							
						}
					}
				}
			}
			
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Disp Cycle Time (h:m)")) {
						noOfDisposalLoads=entry2.getValue().size();
						}
					}
				}
		
			

			expectedDepartTime=Util.getDataFromDB(
					"select  TO_CHAR(DEPARTSTAMP - (6 / 24), 'HH:MI') from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID =  '"+ route.get(j) + "') and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");
	
		
		

		for (int i = 0; i < noOfDisposalLoads; i++) {
		
		
		if (actualDepartTime.get(i).equals(expectedDepartTime.get(i))) {
			Report.InfoTest(test,
					"Depart Time is correct in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j)+". Actual is : "
							+ actualDepartTime.get(i) + " and Expected is : "
							+ expectedDepartTime.get(i));
			Report.PassTest(test, "Depart Time is as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j));
		} else {
			Report.FailTest(test, "Depart Time is not as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j)+". Actual is : "
					+ actualDepartTime.get(i) + " and Expected is : " + expectedDepartTime.get(i));
		}
	} 
}

}catch (Exception e) {
		Report.FailTest(test, "Depart Time is not as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report Actual is : "
				+ actualDepartTime + " and Expected is : " + expectedDepartTime);
	}
	
}

private void validateDrilldownDownTime(Map<String, List<String>> actualDetailSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldown, String tabName,
		String subViewName) {
	
	List<String> actualDownTime = new ArrayList<>();
	List<String> expectedDownTime = new ArrayList<>();			
	int noOfDisposalLoads=0;
	SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
	List<String> date = new ArrayList<>();
	List<String> route = new ArrayList<>();
	try {
		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
			if (entry.getKey().equals("Route")) {
				route = entry.getValue();
			}
		}
		
		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
			if (entry.getKey().contains("Date")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					Date dates = format1.parse(entry.getValue().get(i));
					date.add(format2.format(dates));
				}
			}
		}


		
		for (int j=0;j<route.size();j++)
		{
		
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Down Time (h:m)")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {						
							actualDownTime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
							
						}
					}
				}
			}
			
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Disp Cycle Time (h:m)")) {
						noOfDisposalLoads=entry2.getValue().size();
						}
					}
				}
		
			

			expectedDownTime=Util.getDataFromDB(
					"select  TO_CHAR(DOWNTIMESTAMP - (6 / 24), 'HH:MI') from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID =  '"+ route.get(j) + "') and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");
	
		
		

		for (int i = 0; i < noOfDisposalLoads; i++) {
		
		
		if (actualDownTime.get(i).equals(expectedDownTime.get(i))) {
			Report.InfoTest(test,
					"Down Time is correct in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j)+". Actual is : "
							+ actualDownTime.get(i) + " and Expected is : "
							+ expectedDownTime.get(i));
			Report.PassTest(test, "Down Time is as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j));
		} else {
			Report.FailTest(test, "Down Time is not as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j)+". Actual is : "
					+ actualDownTime.get(i) + " and Expected is : " + expectedDownTime.get(i));
		}
	} 
}

}catch (Exception e) {
		Report.FailTest(test, "Down Time is not as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report Actual is : "
				+ actualDownTime + " and Expected is : " + expectedDownTime);
	}
	
}

private void validateDrilldownMeal(Map<String, List<String>> actualDetailSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldown, String tabName,
		String subViewName) {
	List<String> actualMealTime = new ArrayList<>();
	List<String> expectedMealTime = new ArrayList<>();			
	int noOfDisposalLoads=0;
	SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
	List<String> date = new ArrayList<>();
	List<String> route = new ArrayList<>();
	try {
		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
			if (entry.getKey().equals("Route")) {
				route = entry.getValue();
			}
		}
		
		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
			if (entry.getKey().contains("Date")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					Date dates = format1.parse(entry.getValue().get(i));
					date.add(format2.format(dates));
				}
			}
		}


		
		for (int j=0;j<route.size();j++)
		{
		
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Meal (h:m)")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {						
							actualMealTime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
							
						}
					}
				}
			}
			
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Disp Cycle Time (h:m)")) {
						noOfDisposalLoads=entry2.getValue().size();
						}
					}
				}
		
			

			expectedMealTime=Util.getDataFromDB(
					"select  TO_CHAR(LUNCHTIMESTAMP - (6 / 24), 'HH:MI') from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID =  '"+ route.get(j) + "') and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");
	
		
		

		for (int i = 0; i < noOfDisposalLoads; i++) {
		
		
		if (actualMealTime.get(i).equals(expectedMealTime.get(i))) {
			Report.InfoTest(test,
					"Meal Time is correct in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j)+". Actual is : "
							+ actualMealTime.get(i) + " and Expected is : "
							+ expectedMealTime.get(i));
			Report.PassTest(test, "Meal Time is as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j));
		} else {
			Report.FailTest(test, "Meal Time is not as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j)+". Actual is : "
					+ actualMealTime.get(i) + " and Expected is : " + expectedMealTime.get(i));
		}
	} 
}

}catch (Exception e) {
		Report.FailTest(test, "Meal Time is not as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report Actual is : "
				+ actualMealTime + " and Expected is : " + expectedMealTime);
	}
	
}

private void validateDrilldownTons(Map<String, List<String>> actualDetailSubView,
		Map<String, Map<String, List<String>>> actualDisposalTimeDrilldown, String tabName,
		String subViewName) {
	
	List<Double> actualTotalTons = new ArrayList<>();
	List<String> expectedTotalTons = new ArrayList<>();			
	int noOfDisposalLoads=0;
	SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
	List<String> date = new ArrayList<>();
	List<String> route = new ArrayList<>();
	try {
		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
			if (entry.getKey().equals("Route")) {
				route = entry.getValue();
			}
		}
		
		for (Entry<String, List<String>> entry : actualDetailSubView.entrySet()) {
			if (entry.getKey().contains("Date")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					Date dates = format1.parse(entry.getValue().get(i));
					date.add(format2.format(dates));
				}
			}
		}


		
		for (int j=0;j<route.size();j++)
		{
		
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Tons")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {						
							actualTotalTons.add(Double.parseDouble(entry2.getValue().get(i).trim()));
							
						}
					}
				}
			}
			
			for (Entry<String, Map<String, List<String>>> entry : actualDisposalTimeDrilldown.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Disp Cycle Time (h:m)")) {
						noOfDisposalLoads=entry2.getValue().size();
						}
					}
				}
		
			

			expectedTotalTons=Util.getDataFromDB(
					"select  TONNAGE from OCS_ADMIN.TP_RO_LANDFILL where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID =  '"+ route.get(j) + "') and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");
	
		
		

		for (int i = 0; i < noOfDisposalLoads; i++) {
		
		
		if (Util.compareNumber(actualTotalTons.get(i), Double.parseDouble(expectedTotalTons.get(i)))) {
			Report.InfoTest(test,
					"Tons is correct in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j)+". Actual is : "
							+ actualTotalTons.get(i) + " and Expected is : "
							+ expectedTotalTons.get(i));
			Report.PassTest(test, "Tons is as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j));
		} else {
			Report.FailTest(test, "Tons is not as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report for Route : "+route.get(j)+". Actual is : "
					+ actualTotalTons.get(i) + " and Expected is : " + expectedTotalTons.get(i));
		}
	} 
}

}catch (Exception e) {
		Report.FailTest(test, "Tons is not as expected in drill down of " + tabName+" Tab "+ subViewName + " Sub View report Actual is : "
				+ actualTotalTons + " and Expected is : " + expectedTotalTons);
	}
	
}

}
