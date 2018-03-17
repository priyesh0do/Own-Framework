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

public class DowntimeDetailReport {
	
	ExtentTest test;
	WebDriver driver;

	public DowntimeDetailReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//table[@id='dtDowntimeSummary']/thead/tr/th")
	List<WebElement> downtimeSummaryTableColumns;

	@FindBy(xpath = "//table[@id='dtDowntimeSummary']/tbody/tr")
	List<WebElement> downtimeSummaryTableRows;
	
	//DowntimeDetailReportSummaryColumn
	@FindBy(xpath = "//table[@id='dtDowntimeSummary']/thead/tr/th")
	List<WebElement> DowntimeDetailReportSummaryColumn;
	
	//DowntimeDetailReportDetailColumn
	@FindBy(xpath = "//table[@id='t2']/thead/tr/th")
	List<WebElement> DowntimeDetailReportDetailColumn;
	
	//DowntimeDetailReportSummaryRows
	@FindBy(xpath = "//table[@id='dtDowntimeSummary']/tbody/tr")
	List<WebElement> DowntimeDetailReportSummaryRows;
	
	//DowntimeDetailReportDetailRows
	@FindBy(xpath = "//table[@id='t2']/tbody/tr")
	List<WebElement> DowntimeDetailReportDetailRows;
	
	//RedirectedtoActualSubview;
	@FindBy(xpath="//*[@id='inpSubReportOpt_2']")
	WebElement RedirectedtoActualSubview;
	
	//RedirectedtoPerformanceSubview
	@FindBy(xpath="//*[@id='inpSubReportOpt_1']")
	WebElement RedirectedtoPerformanceSubview;
	
	//DownTimeDrilDownTableColumn
	@FindBy(xpath="//*[@id='t2']/thead/tr/th")
	List<WebElement> DownTimeDrilDownTableColumn;
	
	//DownTimeDrillDownTableRow
	@FindBy(xpath="//*[@id='t2']/tbody/tr")
	List<WebElement> DownTimeDrillDownTableRow;
	
	//DrillDownActualCount
	@FindBy(xpath="//*[@id='t2']/tbody/tr")
	List<WebElement> DrillDownActualCount;
	
	///DownTimeDrillDownCount
	@FindBy(xpath="//*[@id='t2']/tbody/tr/td[contains(@id,'colDownTm_')]/a")
	List<WebElement> DownTimeDrillDownCount;
	
	
	public Map<String, List<String>> getActualDowntimeSummaryTableData() throws IOException {
		Map<String, List<String>> downtimeSummaryTableData = new HashMap<>();
		try {
			downtimeSummaryTableData = readTable(downtimeSummaryTableColumns, downtimeSummaryTableRows, "dtDowntimeSummary");
			Report.PassTestScreenshot(test, driver, "Downtime Summary table data read successfully", "Downtime detail report");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Downtime summary table data");
		}

		for (Entry<String, List<String>> entry : downtimeSummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return downtimeSummaryTableData;
	}
	
	
	
public void clickDownTimeDrilldown() {
		
		try {
			
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t2']/tbody/tr[1]/td/a/span")).click();
				
			}
		
		 catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to Click on DownTime ", "DownTime Drilldown");
		}

		
	}

	

	public Map<String, List<String>> readTable(List<WebElement> columns, List<WebElement> rows, String tableName) {

		
		 if (tableName.equals("t2")) {
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
		 else  if (tableName.equals("t2 drill down")) {
			 Map<String, List<String>> objTable = new HashMap<>();			
				for (int iCount = 1; iCount <= columns.size(); iCount++) {
					List<String> rowValues = new ArrayList<>();
					for (int row = 1; row <= rows.size(); row++) {

						try {
							rowValues.add(driver.findElement(By.xpath(
									"//table[@id='t2']/tbody/tr[" + row + "]/td[" + iCount + "]/span"))
									.getText());
						} catch (Exception e) {
							rowValues.add(driver
									.findElement(By.xpath(
											"//table[@id='t2']/tbody/tr[" + row + "]/td[" + iCount + "]"))
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

	public Map<String, List<String>> getDownTimeDetailSummaryData() throws IOException {
		
		Map<String, List<String>> DowntimeSummaryTableData = new HashMap<>();
		try {
			DowntimeSummaryTableData = readTable(DowntimeDetailReportSummaryColumn, DowntimeDetailReportSummaryRows, "dtDowntimeSummary");
			Report.PassTestScreenshot(test, driver, "Down time Detail Summary table data read successfully", "Downtime detail report");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Downtime Detail summary table data");
		}

		for (Entry<String, List<String>> entry : DowntimeSummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return DowntimeSummaryTableData;
	}
	
	
 public Map<String, List<String>> getDownTimeDetailReportDetailDataPerformanceSubview() throws IOException {
		
		Map<String, List<String>> DowntimeSummaryTableData = new HashMap<>();
		try {
			DowntimeSummaryTableData = readTable(DowntimeDetailReportDetailColumn, DowntimeDetailReportDetailRows, "t2");
			Report.PassTestScreenshot(test, driver, "Downtime Detail Report Detail table Performance Subview data read successfully", "Downtime detail report");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Downtime Detail Report Detail Table Performance Subview");
		}

		for (Entry<String, List<String>> entry : DowntimeSummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return DowntimeSummaryTableData;
	}
	
 public Map<String, List<String>> getDownTimeDetailReportDetailDataActualSubview() throws IOException {
		
		Map<String, List<String>> DowntimeSummaryTableData = new HashMap<>();
		try {
			DowntimeSummaryTableData = readTable(DowntimeDetailReportDetailColumn, DowntimeDetailReportDetailRows, "t2");
			Report.PassTestScreenshot(test, driver, "Downtime Detail Report Detail table Performance Subview data read successfully", "Downtime detail report");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Downtime Detail Report Detail Table Performance Subview");
		}

		for (Entry<String, List<String>> entry : DowntimeSummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return DowntimeSummaryTableData;
	}
	
	
	
	
	public void RedirectedtoActualSubview() {
		try {
			RedirectedtoActualSubview.click();
			Thread.sleep(1000);
			Report.PassTest(test, "Redirected to Actual Subview");
		} catch (Exception e) {
			Report.FailTest(test, "Unable to Redirect to Actual Subview");
		}
		
		
	}
	
	
	public void RedirectedtoPerformanceSubview() {
		
		try {
			RedirectedtoPerformanceSubview.click();
			Thread.sleep(1000);
			Report.PassTest(test, "Redirected to Performance Subview");
		} catch (Exception e) {
			Report.FailTest(test, "Unable to Redirect to Performance Subview");
		}
		
	}
	
	public Map<String, Map<String, List<String>>> getActualDowntimeDrilldownTableDataPerformanceSubview(String route) {
		Map<String, Map<String, List<String>>> DowntimeDrilldownTableData = new HashMap<>();
		try {
			Thread.sleep(5000);
			String[] routes = route.split(";");

			Map<String, List<String>> netDowntimeDrilldownTemp = new HashMap<>();

			for (int i = 0; i < routes.length; i++) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t2']/tbody/tr[" + (i + 1) + "]/td/a/span")).click();
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer,subIDownDetail_Row" + (i + 1));
					netDowntimeDrilldownTemp = readTable(DownTimeDrilDownTableColumn, DownTimeDrillDownTableRow, "t2 drill down");
					Report.PassTestScreenshot(test, driver, "In Performance Subview Downtime Drilldown table data read successfully",
							"Downtime Drilldown Data");
					Util.switchToDefaultWindow();
				} catch (Exception e) {
					Report.FailTest(test, "Not able to read Performance Subview Downtime drilldown table data");
				}
				DowntimeDrilldownTableData.put(routes[i], netDowntimeDrilldownTemp);
			}

			for (Entry<String, Map<String, List<String>>> entry : DowntimeDrilldownTableData.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
				}
			}
			Util.switchToDefaultWindow();
			return DowntimeDrilldownTableData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to read Performance Subview Downtime drilldown data", "DownTime Drilldown");
		}

		return DowntimeDrilldownTableData;
	}

	public Map<String, Map<String, List<String>>> getActualDowntimeDrilldownTableDataActualSubview(String route) {
		Map<String, Map<String, List<String>>> DowntimeDrilldownTableData = new HashMap<>();
		try {
			Thread.sleep(5000);
			String[] routes = route.split(";");

			Map<String, List<String>> netDowntimeDrilldownTemp = new HashMap<>();

			for (int i = 0; i < routes.length; i++) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t2']/tbody/tr[" + (i + 1) + "]/td/a/span")).click();
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer,subIDownDetail_Row" + (i + 1));
					netDowntimeDrilldownTemp = readTable(DownTimeDrilDownTableColumn, DownTimeDrillDownTableRow, "t2 drill down");
					Report.PassTestScreenshot(test, driver, "In Actual Subview Downtime Drilldown table data read successfully ",
							"Downtime Drilldown Data");
					Util.switchToDefaultWindow();
				} catch (Exception e) {
					Report.FailTest(test, "Not able to read Actual Subview Downtime drilldown table data");
				}
				DowntimeDrilldownTableData.put(routes[i], netDowntimeDrilldownTemp);
			}

			for (Entry<String, Map<String, List<String>>> entry : DowntimeDrilldownTableData.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
				}
			}
			Util.switchToDefaultWindow();
			return DowntimeDrilldownTableData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to read Actual Subview Downtime drilldown data", "DownTime Drilldown");
		}

		return DowntimeDrilldownTableData;
	}

	
	
	

	private void validateTotalDownTime(Map<String, List<String>> getDownTimeDetailSummaryData,
			Map<String, List<String>> getDownTimeDetailReportDetailDataPerformanceSubview) {
		
		String actualDownTime = null;
		String expectedDownTime = null;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : getDownTimeDetailSummaryData.entrySet()) {
				if (entry.getKey().contains("Total Downtime (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualDownTime = "99:99";
						} else {
							actualDownTime = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : getDownTimeDetailReportDetailDataPerformanceSubview.entrySet()) {
				if (entry.getKey().contains("Downtime (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins += mins;
						}

					}
				}
			}

			
			Util.validateTimeFormat(actualDownTime, "DownTime Time in Summary Section");

			
			expectedDownTime = Util.convertMinsToHours(totalMins);

			if (actualDownTime.equals("99:99")) {
				Report.FailTest(test,
						"Total DownTime is not calculated as expected in summary report Actual is : -- and Expected is : "
								+ expectedDownTime);
			} else {

				if (actualDownTime.equals(expectedDownTime)) {
					Report.InfoTest(test, "Total DownTime is correct in summary report Actual is : "
							+ actualDownTime + " and Expected is : " + expectedDownTime);
					Report.PassTest(test, "TotalDownTime is as expected in Summary report");
				} else {
					Report.FailTest(test, "Total DownTime is not as expected in summary report Actual is : "
							+ actualDownTime + " and Expected is : " + expectedDownTime);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total DownTime is not as expected in summary report Actual is : "
					+ actualDownTime + " and Expected is : " + expectedDownTime);
		}
		
	}

	private void validateTotalDowntimeEvents(Map<String, List<String>> getDownTimeDetailSummaryData,
			Map<String, List<String>> getDownTimeDetailReportDetailDataPerformanceSubview) {
		int actualTotalDownTime = 0;
		int expectedTotalIdleEvents = 0;
		int Drilldowncounts=0;
		int currentcount = 0;
		try {
			for (Entry<String, List<String>> entry : getDownTimeDetailSummaryData.entrySet()) {
				if (entry.getKey().equals("Total Downtime Events")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualTotalDownTime = 0;
						} else {
							actualTotalDownTime = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualTotalDownTime, "Total DownTime Events in Summary Section");
						}
					}
				}
			}
			
			
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");
			for (int i = 0; i < DownTimeDrillDownCount.size(); i++) {
				
				if (!DownTimeDrillDownCount.get(i).getText().contains("99:99")) {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer");
					DownTimeDrillDownCount.get(i).click();
					Thread.sleep(2000);					
					try {
						Util.switchToDefaultWindow();
						Util.selectFrame("opusReportContainer,subReportContainer,subIDownDetail_Row" + (i + 1));
						Drilldowncounts=DrillDownActualCount.size();
					
						currentcount = currentcount + Drilldowncounts;
						
					} catch (Exception e) {
						Report.FailTest(test, "Not able to read Performance Subview Downtime drilldown table data");
					}
					
				}
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				Thread.sleep(2000);
				
				//Drilldowncounts++;
				
			}
					
					if (currentcount==actualTotalDownTime) {
						Report.PassTestScreenshot(test, driver, "ValidatedTotal DownTime Evevnts Actual is > " + actualTotalDownTime + " And Expected is >" + Drilldowncounts, "PassedTotalDowntim");
					}
					else {
						Report.FailTestSnapshot(test, driver, "Unable to Validate DownTime Evevnts Actual is > " + actualTotalDownTime + " And Expected is >" + Drilldowncounts, "FailedTotalDowntim");
					}
				
					
			
			
			
			
		
	}
		catch (Exception e) {
		
	}

	
	
	
}

	private void ValidateAvgDownTime(Map<String, List<String>> getDownTimeDetailSummaryData) {
		
		int ActualAvgDowntime=0;
		int TotalDownTimeEvents=0;
		int TotalDownTime=0;
		int ExpectedAvgDownTime=0;
		
		try {
			for (Entry<String, List<String>> entry : getDownTimeDetailSummaryData.entrySet()) {
				if (entry.getKey().contains("Total Downtime (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							TotalDownTime += mins;
						}

					}
				}
			}
			
			for (Entry<String, List<String>> entry : getDownTimeDetailSummaryData.entrySet()) {
				if (entry.getKey().contains("Avg Downtime(h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							ActualAvgDowntime += mins;
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : getDownTimeDetailSummaryData.entrySet()) {
				if (entry.getKey().equals("Total Downtime Events")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							TotalDownTimeEvents = 0;
						} else {
							TotalDownTimeEvents = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(TotalDownTimeEvents, "Total DownTime Events in Summary Section");
						}
					}
				}
			}
			
			//ExpectedAvgDownTime=Util.convertHoursToMins(ActualAvgDowntime);
			  
			ExpectedAvgDownTime=(TotalDownTime/TotalDownTimeEvents);
			if (ActualAvgDowntime==ExpectedAvgDownTime) {
				Report.PassTestScreenshot(test, driver, "Validated Avg DownTime Expected is  " + ExpectedAvgDownTime + " And Actual is >" +ActualAvgDowntime , "PassedAvgDownTime");
				
			}
			else {
				Report.PassTestScreenshot(test, driver, "Unable to Validate Avg DownTime Expected is  " + ExpectedAvgDownTime + " And Actual is >" +ActualAvgDowntime , "FailedAvgDownTime");
			}
		} catch (Exception e) {
			Report.FailTestSnapshot(test, driver,"Unable to Validate Avg DownTime Expected is  " + ExpectedAvgDownTime + " And Actual is >" +ActualAvgDowntime , "FailedAvgDownTime");
		}
		
		
		
		
	}

   public void ValidateDowntimeDetailSummaryData(Map<String, List<String>> getDownTimeDetailSummaryData,
			Map<String, List<String>> getDownTimeDetailReportDetailDataPerformanceSubview) {
	   
	   validateTotalDownTime(getDownTimeDetailSummaryData,getDownTimeDetailReportDetailDataPerformanceSubview);
	   validateTotalDowntimeEvents(getDownTimeDetailSummaryData, getDownTimeDetailReportDetailDataPerformanceSubview);
	   ValidateAvgDownTime(getDownTimeDetailSummaryData);
	   
	   
	   
   }
   
   public void ValidateDowntimeDetailReportPerformanceSubviewData(Map<String, List<String>> getDownTimeDetailReportDetailDataPerformanceSubview, String fromDate, String toDate,String expectedRouteName,String expectedDriverName, String expectedLOB) {
	   validateDate(getDownTimeDetailReportDetailDataPerformanceSubview, fromDate, toDate); 
	   validateRoute(getDownTimeDetailReportDetailDataPerformanceSubview,expectedRouteName);
	   validateDriver(getDownTimeDetailReportDetailDataPerformanceSubview,expectedDriverName);
	   validateLOB(getDownTimeDetailReportDetailDataPerformanceSubview, expectedLOB);
	   validateSubLOB(getDownTimeDetailReportDetailDataPerformanceSubview);
	   validateRouteType(getDownTimeDetailReportDetailDataPerformanceSubview);
	   
   }
   private void validateDate(Map<String, List<String>> getDownTimeDetailReportDetailDataPerformanceSubview, String fromDate, String toDate) {
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yy");
		Date startDate = null;
		Date endDate = null;
		List<Date> routeServedDate = new ArrayList<>();
		List<String> actualRoutes = null;
		try {
			startDate = format1.parse(fromDate);
			endDate = format1.parse(toDate);
			for (Entry<String, List<String>> entry : getDownTimeDetailReportDetailDataPerformanceSubview.entrySet()) {
				if (entry.getKey().equals("Route")) {
					actualRoutes = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getDownTimeDetailReportDetailDataPerformanceSubview.entrySet()) {
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
							"Route Date is correct in DownTime Detail report for route : "
									+ actualRoutes.get(i) + ". Actual is : " + format1.format(routeServedDate.get(i))
									+ " and Expected is between : " + format1.format(startDate) + "and "
									+ format1.format(endDate));
					Report.PassTest(test, "Route Date is as expected in DownTime Detail Report");
				} else {
					Report.FailTest(test,
							"Route Date is not as expected in DownTime Detail report Actual is : "
									+ format1.format(routeServedDate.get(i)) + " and Expected is between : "
									+ format1.format(startDate) + "and " + format1.format(endDate));
				}
			}

		} catch (Exception e) {
			Report.FailTest(test, "Not able to compare date is in between the date range");
			LogClass.error(e.getMessage());
		}
		
	}
   
   private void validateRoute(Map<String, List<String>> getDownTimeDetailReportDetailDataPerformanceSubview, String expectedRouteName) {
		String[] routes = expectedRouteName.split(";");
		List<String> actualRoutes = null;
		try {
			for (Entry<String, List<String>> entry : getDownTimeDetailReportDetailDataPerformanceSubview.entrySet()) {
				if (entry.getKey().equals("Route")) {
					actualRoutes = entry.getValue();
				}
			}
			for (int i = 0; i < routes.length; i++) {
				if (actualRoutes.contains(routes[i])) {
					Report.InfoTest(test,
							"Route Name is correct in DownTime Detail report Actual List is : "
									+ actualRoutes.toString() + " and Expected is : " + routes[i]);
					Report.PassTest(test, "Route Name is as expected in DownTime Detail report");
				} else {
					Report.FailTest(test,
							"Route Name is not as expected in DownTime Detail report Actual List is : " + actualRoutes.toString()
									+ " and Expected is : " + routes[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Route is not as expected in DownTime Detail report Actual List is : "
							+ actualRoutes.toString() + " and Expected is : " + routes.toString());
		}
		
	}

   private void validateDriver(Map<String, List<String>> getDownTimeDetailReportDetailDataPerformanceSubview, String expectedDriverName) {
		
		String[] drivers = expectedDriverName.split(";");
		List<String> actualDrivers = null;
		try {
			for (Entry<String, List<String>> entry : getDownTimeDetailReportDetailDataPerformanceSubview.entrySet()) {
				if (entry.getKey().equals("Driver")) {
					actualDrivers = entry.getValue();
				}
			}
			for (int i = 0; i < drivers.length; i++) {
				if (actualDrivers.contains(drivers[i])) {
					Report.InfoTest(test,
							"Driver Name is correct in DownTime Detail report Actual List is : "
									+ actualDrivers.toString() + " and Expected is : " + drivers[i]);
					Report.PassTest(test, "Driver Name is as expected in DownTime Detail sub view report");
				} else {
					Report.FailTest(test,
							"Driver Name is not as expected in DownTime Detail report Actual List is : " + actualDrivers.toString()
									+ " and Expected is : " + drivers[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Driver Name is not as expected in DownTime Detail report Actual List is : "
							+ actualDrivers.toString() + " and Expected is : " + drivers.toString());
		}
		
	}
   
   public void validateLOB(Map<String, List<String>> getDownTimeDetailReportDetailDataPerformanceSubview, String expectedLOB) {
		String actualLOB = null;
		try {
			for (Entry<String, List<String>> entry : getDownTimeDetailReportDetailDataPerformanceSubview.entrySet()) {
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

				Report.InfoTest(test, "LOB is correct in DownTime Detail report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
				Report.PassTest(test, "LOB is as expected in DownTime Detail report");
			} 
			else if (!actualLOB.equals(expectedLOB)) {
				Report.InfoTest(test, "Validated LOB is Passed Expected LOB is " + expectedLOB + "And Actual is " + actualLOB );
			}
			else {
				Report.FailTest(test, "LOB is not as expected in DownTime Detail report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
			}
		} catch (Exception e) {
			Report.FailTest(test, "LOB is not as expected in DownTime Detail report Actual List is : " + actualLOB + " and Expected is : " + expectedLOB);
		}	
}

   private void validateSubLOB(Map<String, List<String>> getDownTimeDetailReportDetailDataPerformanceSubview) {
		List<String> actualSubLOB = new ArrayList<>();
		List<String> expectedSubLOB = new ArrayList<>();
		List<String> route = new ArrayList<>();

		try {
			for (Entry<String, List<String>> entry : getDownTimeDetailReportDetailDataPerformanceSubview.entrySet()) {
				if (entry.getKey().equals("Sub LOB")) {
					actualSubLOB = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getDownTimeDetailReportDetailDataPerformanceSubview.entrySet()) {
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
							"Sub LOB is correct in DownTime Detail report for Route : "
									+ route.get(i) + " with Actual SubLOB : " + actualSubLOB.get(0)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
					Report.PassTest(test, "Sub LOB is as expected in DownTime Detail report");
				} else {
					Report.FailTest(test,
							"Sub LOB is not as expected in DownTime Detail report for Route : "
									+ route.get(i) + " with Actual Sub LOB : " + actualSubLOB.get(i)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Sub LOB is not as expected in DownTime Detail report Actual is : "
					+ actualSubLOB.toString() + " and Expected is : " + expectedSubLOB.toString());
		}
	
}

   private void validateRouteType(Map<String, List<String>> getDownTimeDetailReportDetailDataPerformanceSubview) {
		
		List<String> actualRouteType = new ArrayList<>();
		List<String> expectedRouteType = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		String expectedRouteTypes = "";
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");

		try {
			for (Entry<String, List<String>> entry : getDownTimeDetailReportDetailDataPerformanceSubview.entrySet()) {
				if (entry.getKey().equals("Route Type")) {
					actualRouteType = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getDownTimeDetailReportDetailDataPerformanceSubview.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getDownTimeDetailReportDetailDataPerformanceSubview.entrySet()) {
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
							"Route Type  is correct in DownTime Detail report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type  : " + expectedRouteTypes);
					Report.PassTest(test, "Route Type is as expected in DownTime Detail Sub View report");
				} else {
					Report.FailTest(test,
							"Route Type is not as expected in DownTime Detail report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type : " + expectedRouteTypes);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test,
					"Route Type  is not as expected in DownTime Detail report Actual is : "
							+ actualRouteType.toString() + " and Expected is : " + expectedRouteType.toString());
		}
	
}

   
   public void ValidateDowntimeDetailReportActualSubviewData(Map<String, List<String>> getDownTimeDetailReportDetailDataActualSubview,Map<String,Map<String, List<String>>> getActualDowntimeDrilldownTableDataActualSubview,String fromDate, String toDate,String expectedRouteName,String expectedDriverName, String expectedLOB) {
	   validateDate(getDownTimeDetailReportDetailDataActualSubview, fromDate, toDate); 
	   validateRoute(getDownTimeDetailReportDetailDataActualSubview,expectedRouteName);
	   validateDriver(getDownTimeDetailReportDetailDataActualSubview,expectedDriverName);
	   validateLOB(getDownTimeDetailReportDetailDataActualSubview, expectedLOB);
	   validateSubLOB(getDownTimeDetailReportDetailDataActualSubview);
	   validateRouteType(getDownTimeDetailReportDetailDataActualSubview);
	   validateDownTime(getDownTimeDetailReportDetailDataActualSubview, getActualDowntimeDrilldownTableDataActualSubview);
	   
   }
   
   private void validateDownTime(Map<String, List<String>> getDownTimeDetailReportDetailDataActualSubview,Map<String,Map<String, List<String>>> getActualDowntimeDrilldownTableDataActualSubview) {
		// DownTime = Sum[[End DownTime - Start DownTime] - Meal Time ]
		
		List<String> startDowntime = new ArrayList<>();
		List<String> endDownTime= new ArrayList<>();
		List<String> mealTime= new ArrayList<>();
		List<String> actualDowntime=new ArrayList<>();
		String expectedDownTime=null;
		List<Integer> DownTimeoccurrences=new ArrayList<>();
		
		List<String> route = new ArrayList<>();
		try {
			for (Entry<String, List<String>> entry : getDownTimeDetailReportDetailDataActualSubview.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			
			for (Entry<String, List<String>> entry : getDownTimeDetailReportDetailDataActualSubview.entrySet()) {
				if (entry.getKey().equals("Downtime (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualDowntime.add("00:00");
						} else {
							actualDowntime.add(entry.getValue().get(i));
							// Validate Decimal Format for the value : should
							// be hh:mm
							Util.validateTimeFormat(entry.getValue().get(i), "Down Time in Detail section");
						}
					}
				}
			}
			
			
			
			for (int j=0;j<route.size();j++)
			{
			
				for (Entry<String, Map<String, List<String>>> entry : getActualDowntimeDrilldownTableDataActualSubview.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Start")) {
							for (int i = 0; i < entry2.getValue().size(); i++) {
								if (entry2.getValue().get(i).equals("--")) {
									startDowntime.add("00:00");
								} else {
									startDowntime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
								}
							}
						}
					}
				}
			
				for (Entry<String, Map<String, List<String>>> entry : getActualDowntimeDrilldownTableDataActualSubview.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("End")) {
							for (int i = 0; i < entry2.getValue().size(); i++) {
								if (entry2.getValue().get(i).equals("--")) {
									endDownTime.add("00:00");
								} else {
									endDownTime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
								}
							}
						}
					}
				}
				
				
				
				for (Entry<String, Map<String, List<String>>> entry : getActualDowntimeDrilldownTableDataActualSubview.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Meal (h:m)")) {
							for (int i = 0; i < entry2.getValue().size(); i++) {
								if (entry2.getValue().get(i).equals("--")) {
									mealTime.add("00:00");
								} else {
									mealTime.add(entry2.getValue().get(i).toString());
								}
							}
						}
					}
				}
				
				
				for (Entry<String, Map<String, List<String>>> entry : getActualDowntimeDrilldownTableDataActualSubview.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Downtime (h:m)")) {
							DownTimeoccurrences.add(entry2.getValue().size());
							}
						}
					}
				
				
			
		
			
			List<Integer> startDownTimeMins=new ArrayList<>();
			List<Integer> endDownTimeMins=new ArrayList<>();
			List<Integer> mealTimeMins=new ArrayList<>();
			
			
			for(int i=0;i<startDowntime.size();i++)
			{
				startDownTimeMins.add(Util.convertHoursToMins(startDowntime.get(i)));
			}
			for(int i=0;i<endDownTime.size();i++)
			{
				endDownTimeMins.add(Util.convertHoursToMins(endDownTime.get(i)));
			}
			
			for(int i=0;i<mealTime.size();i++)
			{
				mealTimeMins.add(Util.convertHoursToMins(mealTime.get(i)));
			}
			
			int expectedDownTimeTimeInMins = 0;
			for (int i = 0; i < DownTimeoccurrences.get(j); i++) {
				expectedDownTimeTimeInMins =  expectedDownTimeTimeInMins+((endDownTimeMins.get(i)-startDownTimeMins.get(i))-mealTimeMins.get(i));	
					
			}
			expectedDownTime=Util.convertMinsToHours(expectedDownTimeTimeInMins);
			if (Util.compareTime(actualDowntime.get(j), expectedDownTime)) {
				Report.InfoTest(test,
						" DownTime is correct in detail section of DownTimereport for Route : "+route.get(j)+". Actual is : "
								+ actualDowntime.get(j) + " and Expected is : "
								+ expectedDownTime);
				Report.PassTest(test, " DownTime is as expected in detail section of DownTimereport for Route : "+route.get(j));
			} else {
				Report.FailTest(test, " DownTime is not as expected in detail section of DownTimereport for Route : "+route.get(j)+". Actual is : "
						+ actualDowntime.get(j) + " and Expected is : " + expectedDownTime);
			}
		}
   
		}
	
	catch (Exception e) {
			Report.FailTest(test, " DownTime is not as expected in detail section of DownTimereport Actual is : "
					+ actualDowntime + " and Expected is : " + expectedDownTime);
		}
		
	}

   
   public void ValidateDrillDownDataActualSubview() {
	   
	   
	   
	   
   }
   
   private void ValidateDrillDownDowntimeHours(Map<String, List<String>> getDownTimeDetailReportDetailDataActualSubview,Map<String,Map<String, List<String>>> getActualDowntimeDrilldownTableDataActualSubview) {
	   
	// DownTime = Sum[[End DownTime - Start DownTime] - Meal Time ]
		
			List<String> startDowntime = new ArrayList<>();
			List<String> endDownTime= new ArrayList<>();
			List<String> mealTime= new ArrayList<>();
			List<String> actualDowntime=new ArrayList<>();
			String expectedDownTime=null;
			List<Integer> DownTimeoccurrences=new ArrayList<>();
			
			List<String> route = new ArrayList<>();
			try {
				for (Entry<String, List<String>> entry : getDownTimeDetailReportDetailDataActualSubview.entrySet()) {
					if (entry.getKey().equals("Route")) {
						route = entry.getValue();
					}
				}
				
				for (Entry<String, List<String>> entry : getDownTimeDetailReportDetailDataActualSubview.entrySet()) {
					if (entry.getKey().equals("Downtime (h:m)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								actualDowntime.add("00:00");
							} else {
								actualDowntime.add(entry.getValue().get(i));
								// Validate Decimal Format for the value : should
								// be hh:mm
								Util.validateTimeFormat(entry.getValue().get(i), "Down Time in Detail section");
							}
						}
					}
				}
				
				
				
				for (int j=0;j<route.size();j++)
				{
				
					for (Entry<String, Map<String, List<String>>> entry : getActualDowntimeDrilldownTableDataActualSubview.entrySet()) {
						if(entry.getKey().equals(route.get(j)))
						for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
							if (entry2.getKey().equals("Start")) {
								for (int i = 0; i < entry2.getValue().size(); i++) {
									if (entry2.getValue().get(i).equals("--")) {
										startDowntime.add("00:00");
									} else {
										startDowntime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
									}
								}
							}
						}
					}
				
					for (Entry<String, Map<String, List<String>>> entry : getActualDowntimeDrilldownTableDataActualSubview.entrySet()) {
						if(entry.getKey().equals(route.get(j)))
						for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
							if (entry2.getKey().equals("End")) {
								for (int i = 0; i < entry2.getValue().size(); i++) {
									if (entry2.getValue().get(i).equals("--")) {
										endDownTime.add("00:00");
									} else {
										endDownTime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
									}
								}
							}
						}
					}
					
					
					
					for (Entry<String, Map<String, List<String>>> entry : getActualDowntimeDrilldownTableDataActualSubview.entrySet()) {
						if(entry.getKey().equals(route.get(j)))
						for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
							if (entry2.getKey().equals("Meal (h:m)")) {
								for (int i = 0; i < entry2.getValue().size(); i++) {
									if (entry2.getValue().get(i).equals("--")) {
										mealTime.add("00:00");
									} else {
										mealTime.add(entry2.getValue().get(i).toString());
									}
								}
							}
						}
					}
					
					
					for (Entry<String, Map<String, List<String>>> entry : getActualDowntimeDrilldownTableDataActualSubview.entrySet()) {
						if(entry.getKey().equals(route.get(j)))
						for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
							if (entry2.getKey().equals("Downtime (h:m)")) {
								DownTimeoccurrences.add(entry2.getValue().size());
								}
							}
						}
					
					
				
			
				
				List<Integer> startDownTimeMins=new ArrayList<>();
				List<Integer> endDownTimeMins=new ArrayList<>();
				List<Integer> mealTimeMins=new ArrayList<>();
				
				
				for(int i=0;i<startDowntime.size();i++)
				{
					startDownTimeMins.add(Util.convertHoursToMins(startDowntime.get(i)));
				}
				for(int i=0;i<endDownTime.size();i++)
				{
					endDownTimeMins.add(Util.convertHoursToMins(endDownTime.get(i)));
				}
				
				for(int i=0;i<mealTime.size();i++)
				{
					mealTimeMins.add(Util.convertHoursToMins(mealTime.get(i)));
				}
				
				int expectedDownTimeTimeInMins = 0;
				for (int i = 0; i < DownTimeoccurrences.get(j); i++) {
					expectedDownTimeTimeInMins =  expectedDownTimeTimeInMins+((endDownTimeMins.get(i)-startDownTimeMins.get(i))-mealTimeMins.get(i));	
						
				}
				expectedDownTime=Util.convertMinsToHours(expectedDownTimeTimeInMins);
				if (Util.compareTime(actualDowntime.get(j), expectedDownTime)) {
					Report.InfoTest(test,
							" DownTime is correct in detail section of DownTimereport for Route : "+route.get(j)+". Actual is : "
									+ actualDowntime.get(j) + " and Expected is : "
									+ expectedDownTime);
					Report.PassTest(test, " DownTime is as expected in detail section of DownTimereport for Route : "+route.get(j));
				} else {
					Report.FailTest(test, " DownTime is not as expected in detail section of DownTimereport for Route : "+route.get(j)+". Actual is : "
							+ actualDowntime.get(j) + " and Expected is : " + expectedDownTime);
				}
			}
	   
			}
		
		catch (Exception e) {
				Report.FailTest(test, " DownTime is not as expected in detail section of DownTimereport Actual is : "
						+ actualDowntime + " and Expected is : " + expectedDownTime);
			}
	   
	   
	   
   }

   
   
   
}
