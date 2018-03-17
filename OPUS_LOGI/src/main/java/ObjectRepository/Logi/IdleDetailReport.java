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

import SupportLibraries.LogClass;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class IdleDetailReport {
	
	ExtentTest test;
	WebDriver driver;

	public IdleDetailReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//table[@id='dtIdleTimeSummary']/thead/tr/th")
	List<WebElement> idleSummaryTableColumns;

	@FindBy(xpath = "//table[@id='dtIdleTimeSummary']/tbody/tr")
	List<WebElement> idleSummaryTableRows;
	
	@FindBy(xpath = "//table[@id='t2']/thead/tr/th")
	List<WebElement> idleDetailTableColumns;

	@FindBy(xpath = "//table[@id='t2']/tbody/tr")
	List<WebElement> idleDetailTableRows;
	
	@FindBy(xpath = "//table[@id='t2Idle']/thead/tr/th")
	List<WebElement> netIdleDrilldownTableColumns;

	@FindBy(xpath = "//table[@id='t2Idle']/tbody/tr")
	List<WebElement> netIdleDrilldownTableRows;
	
	public Map<String, List<String>> getActualIdleSummaryTableData() throws IOException {
		//System.out.println("inside Actual Summary Data");
		Map<String, List<String>> idleSummaryTableData = new HashMap<>();
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		
		try {
			idleSummaryTableData = readTable(idleSummaryTableColumns, idleSummaryTableRows, "dtIdleTimeSummary");
			Report.PassTestScreenshot(test, driver, "Idle Time Summary table data read successfully", "Idle detail report");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Idle Time summary table data");
		}

		for (Entry<String, List<String>> entry : idleSummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return idleSummaryTableData;
	}
	
	public Map<String, List<String>> getActualIdleDetailTableData() {
		System.out.println("inside Actual Idle Detail Table Data");
		Map<String, List<String>> idleDetailTableData = new HashMap<>();
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		
		try {
			idleDetailTableData = readTable(idleDetailTableColumns, idleDetailTableRows, "t2");
			Report.PassTestScreenshot(test, driver, "Actual Idle Detail table data read successfully",
					"Idle Detail Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Idle Detail table data");
		}
		for (Entry<String, List<String>> entry : idleDetailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return idleDetailTableData;
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
	
	public Map<String, Map<String, List<String>>> getActualNetIdleDrilldownTableData(String route) {
		Map<String, Map<String, List<String>>> netIdleDrilldownTableData = new HashMap<>();
		try {
			Thread.sleep(5000);
			String[] routes = route.split(";");

			Map<String, List<String>> netIdleDrilldownTemp = new HashMap<>();

			for (int i = 0; i < routes.length; i++) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t2']/tbody/tr[" + (i + 1) + "]/td/a/span")).click();
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer,subIdleDetail_Row" + (i + 1));
					netIdleDrilldownTemp = readTable(netIdleDrilldownTableColumns, netIdleDrilldownTableRows, "t2Idle");
					Report.PassTestScreenshot(test, driver, "Net Idle Drilldown table data read successfully",
							"Net Idle Drilldown Data");
					Util.switchToDefaultWindow();
				} catch (Exception e) {
					Report.FailTest(test, "Not able to read Net Idle drilldown table data");
				}
				netIdleDrilldownTableData.put(routes[i], netIdleDrilldownTemp);
			}

			for (Entry<String, Map<String, List<String>>> entry : netIdleDrilldownTableData.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
				}
			}
			return netIdleDrilldownTableData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to read Net Idle drilldown data", "Net Idle Drilldown");
		}

		return netIdleDrilldownTableData;
	}
	
	public void clickNetIdleDrilldown() {
		
		try {
			
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t2']/tbody/tr[1]/td/a/span")).click();
				
			}
		
		 catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to Click on Net Idle Time", "Net Idle Time Drilldown");
		}

		
	}

	public void validateSummaryData(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualDetailTable) {
		
		validateTotalNetIdleTime(actualSummaryTable,actualDetailTable);
		validateTotalIdleEvents(actualSummaryTable,actualDetailTable);
		validateAvgNetIdleTime(actualSummaryTable);
		
	}

	

	public void validateDetailData(Map<String, List<String>> actualDetailTable,Map<String,Map<String, List<String>>> actualNetIdleDrilldownTable,String siteID, String LOB, String route,
			String Driver, String fromDate, String toDate) {
		
		validateDate(actualDetailTable,fromDate,toDate);
		validateRoute(actualDetailTable,route);	
		validateDriver(actualDetailTable,Driver);
		validateNetIdle(actualDetailTable,actualNetIdleDrilldownTable);
		validateIdleOcc(actualDetailTable,actualNetIdleDrilldownTable);
		validateLOB(actualDetailTable,LOB);
		validateSubLOB(actualDetailTable);
		validateRouteType(actualDetailTable);
		//validateRouteManager(actualDetailTable);
		
	}
	
	public void validateNetIdleDrilldownData(Map<String, List<String>> actualDetailTable,Map<String, Map<String, List<String>>> actualNetIdleDrilldownTable) {
		validateDrilldownNetIdle(actualDetailTable,actualNetIdleDrilldownTable);
		validateDrilldownIdleonMap(actualDetailTable,actualNetIdleDrilldownTable);
		validateDrilldownTimeofDay(actualDetailTable,actualNetIdleDrilldownTable);
		validateDrilldownStartIdle(actualDetailTable,actualNetIdleDrilldownTable);
		validateDrilldownEndIdle(actualDetailTable,actualNetIdleDrilldownTable);
		validateDrilldownDowntime(actualDetailTable,actualNetIdleDrilldownTable);
		validateDrilldownMealtime(actualDetailTable,actualNetIdleDrilldownTable);
		
	}
	
	
		private void validateDrilldownMealtime(Map<String, List<String>> actualDetailTable,Map<String, Map<String, List<String>>> actualNetIdleDrilldownTable) {
		
			List<String> actualIdleMealTime = new ArrayList<>();
			List<String> expectedIdleMealTimeStart = new ArrayList<>();
			List<String> expectedIdleMealTimeEnd = new ArrayList<>();
			String expectedIdleMealTime =null;			
			List<Integer> noOfIdleOccurences=new ArrayList<>();
			SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
			List<String> date = new ArrayList<>();
			List<String> route = new ArrayList<>();
			try {
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

				
				for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
					if (entry.getKey().equals("Idle Occ")) {
						for (int i = 0; i < entry.getValue().size(); i++) {						
							noOfIdleOccurences.add(Integer.parseInt(entry.getValue().get(i)));					
						}
					}
				}
				
				for (int j=0;j<route.size();j++)
				{
				
					for (Entry<String, Map<String, List<String>>> entry : actualNetIdleDrilldownTable.entrySet()) {
						if(entry.getKey().equals(route.get(j)))
						for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
							if (entry2.getKey().equals("Mealtime (h:m)")) {
								for (int i = 0; i < entry2.getValue().size(); i++) {
									if (entry2.getValue().get(i).equals("--")) {
										actualIdleMealTime.add("00:00");
									} else {
										actualIdleMealTime.add(entry2.getValue().get(i).trim());
									}
								}
							}
						}
					}
				
					

					expectedIdleMealTimeStart=Util.getDataFromDB("select  TO_CHAR(LUNCHSTARTSTAMP - (6 / 24)  ,'HH:MI') from OCS_ADMIN.TP_ALERT where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ")) and FK_VEHICLE IS NOT NULL ORDER BY (FINISHED-STARTED) desc ");
			
					expectedIdleMealTimeEnd=Util.getDataFromDB("select  TO_CHAR(LUNCHENDSTAMP - (6 / 24)  ,'HH:MI') from OCS_ADMIN.TP_ALERT where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ")) and FK_VEHICLE IS NOT NULL ORDER BY (FINISHED-STARTED) desc ");
				

				for (int i = 0; i < noOfIdleOccurences.get(j); i++) {
				
				if(expectedIdleMealTimeStart.get(i).equals("--") || expectedIdleMealTimeEnd.get(i).equals("--"))
				{
					expectedIdleMealTime="00:00";
				}
				else
				{
					expectedIdleMealTime=Util.getTimeDifferenceInString(expectedIdleMealTimeEnd.get(i), expectedIdleMealTimeStart.get(i));
				}
					
					
				if (Util.compareTime(actualIdleMealTime.get(i), expectedIdleMealTime)) {
					Report.InfoTest(test,
							"Idle Meal Time is correct in drill down of Net Idle Time report for Route : "+route.get(j)+". Actual is : "
									+ actualIdleMealTime.get(i) + " and Expected is : "
									+ expectedIdleMealTime);
					Report.PassTest(test, "Idle Meal Time is as expected in drill down of Net Idle Time report for Route : "+route.get(j));
				} else {
					Report.FailTest(test, "Idle Meal Time is not as expected in drill down of Net Idle Time report for Route : "+route.get(j)+". Actual is : "
							+ actualIdleMealTime.get(i) + " and Expected is : " + expectedIdleMealTime);
				}
			} 
		}
		
		}catch (Exception e) {
				Report.FailTest(test, "Idle Meal Time is not as expected in drill down of Net Idle Time report Actual is : "
						+ actualIdleMealTime + " and Expected is : " + expectedIdleMealTime);
			}
			
		
	}

		private void validateDrilldownDowntime(Map<String, List<String>> actualDetailTable,Map<String, Map<String, List<String>>> actualNetIdleDrilldownTable) {
			List<String> actualIdleDownTime = new ArrayList<>();
			List<String> expectedIdleDownTimeStart = new ArrayList<>();
			List<String> expectedIdleDownTimeEnd = new ArrayList<>();
			String expectedIdleDownTime =null;			
			List<Integer> noOfIdleOccurences=new ArrayList<>();
			SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
			List<String> date = new ArrayList<>();
			List<String> route = new ArrayList<>();
			try {
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

				
				for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
					if (entry.getKey().equals("Idle Occ")) {
						for (int i = 0; i < entry.getValue().size(); i++) {						
							noOfIdleOccurences.add(Integer.parseInt(entry.getValue().get(i)));					
						}
					}
				}
				
				for (int j=0;j<route.size();j++)
				{
				
					for (Entry<String, Map<String, List<String>>> entry : actualNetIdleDrilldownTable.entrySet()) {
						if(entry.getKey().equals(route.get(j)))
						for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
							if (entry2.getKey().equals("Downtime (h:m)")) {
								for (int i = 0; i < entry2.getValue().size(); i++) {
									if (entry2.getValue().get(i).equals("--")) {
										actualIdleDownTime.add("00:00");
									} else {
										actualIdleDownTime.add(entry2.getValue().get(i).trim());
									}
								}
							}
						}
					}
				
					

					expectedIdleDownTimeStart=Util.getDataFromDB("select  TO_CHAR(DOWNTIMESTARTSTAMP - (6 / 24)  ,'HH:MI') from OCS_ADMIN.TP_ALERT where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ")) and FK_VEHICLE IS NOT NULL ORDER BY (FINISHED-STARTED) desc ");
			
					expectedIdleDownTimeEnd=Util.getDataFromDB("select  TO_CHAR(DOWNTIMEENDSTAMP - (6 / 24)  ,'HH:MI') from OCS_ADMIN.TP_ALERT where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ")) and FK_VEHICLE IS NOT NULL ORDER BY (FINISHED-STARTED) desc ");
				

				for (int i = 0; i < noOfIdleOccurences.get(j); i++) {
				
				if(expectedIdleDownTimeStart.get(i).equals("--") || expectedIdleDownTimeEnd.get(i).equals("--"))
				{
					expectedIdleDownTime="00:00";
				}
				else
				{
					expectedIdleDownTime=Util.getTimeDifferenceInString(expectedIdleDownTimeEnd.get(i), expectedIdleDownTimeStart.get(i));
				}
					
					
				if (Util.compareTime(actualIdleDownTime.get(i), expectedIdleDownTime)) {
					Report.InfoTest(test,
							"Idle Down Time is correct in drill down of Net Idle Time report for Route : "+route.get(j)+". Actual is : "
									+ actualIdleDownTime.get(i) + " and Expected is : "
									+ expectedIdleDownTime);
					Report.PassTest(test, "Idle Down Time is as expected in drill down of Net Idle Time report for Route : "+route.get(j));
				} else {
					Report.FailTest(test, "Idle Down Time is not as expected in drill down of Net Idle Time report for Route : "+route.get(j)+". Actual is : "
							+ actualIdleDownTime.get(i) + " and Expected is : " + expectedIdleDownTime);
				}
			} 
		}
		
		}catch (Exception e) {
				Report.FailTest(test, "Idle Down Time is not as expected in drill down of Net Idle Time report Actual is : "
						+ actualIdleDownTime + " and Expected is : " + expectedIdleDownTime);
			}
			
		
	}

		private void validateDrilldownEndIdle(Map<String, List<String>> actualDetailTable,Map<String, Map<String, List<String>>> actualNetIdleDrilldownTable) {
		
			List<String> actualEndIdleTime = new ArrayList<>();
			List<String> expectedEndIdleTime = new ArrayList<>();			
			List<Integer> noOfIdleOccurences=new ArrayList<>();
			SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
			List<String> date = new ArrayList<>();
			List<String> route = new ArrayList<>();
			try {
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

				
				for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
					if (entry.getKey().equals("Idle Occ")) {
						for (int i = 0; i < entry.getValue().size(); i++) {						
							noOfIdleOccurences.add(Integer.parseInt(entry.getValue().get(i)));					
						}
					}
				}
				
				for (int j=0;j<route.size();j++)
				{
				
					for (Entry<String, Map<String, List<String>>> entry : actualNetIdleDrilldownTable.entrySet()) {
						if(entry.getKey().equals(route.get(j)))
						for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
							if (entry2.getKey().equals("End Idle")) {
								for (int i = 0; i < entry2.getValue().size(); i++) {
									if (entry2.getValue().get(i).equals("--")) {
										actualEndIdleTime.add("00:00");
									} else {
										actualEndIdleTime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
									}
								}
							}
						}
					}
				
					

					expectedEndIdleTime=Util.getDataFromDB("select  TO_CHAR(FINISHED - (6 / 24)  ,'HH:MI') from OCS_ADMIN.TP_ALERT where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ")) and FK_VEHICLE IS NOT NULL ORDER BY (FINISHED-STARTED) desc ");
			
				
				

				for (int i = 0; i < noOfIdleOccurences.get(j); i++) {
				
				
				if (Util.compareTime(actualEndIdleTime.get(i), expectedEndIdleTime.get(i))) {
					Report.InfoTest(test,
							"Idle End Time is correct in drill down of Net Idle Time report for Route : "+route.get(j)+". Actual is : "
									+ actualEndIdleTime.get(i) + " and Expected is : "
									+ expectedEndIdleTime.get(i));
					Report.PassTest(test, "Idle End Time is as expected in drill down of Net Idle Time report for Route : "+route.get(j));
				} else {
					Report.FailTest(test, "Idle End Time is not as expected in drill down of Net Idle Time report for Route : "+route.get(j)+". Actual is : "
							+ actualEndIdleTime.get(i) + " and Expected is : " + expectedEndIdleTime.get(i));
				}
			} 
		}
		
		}catch (Exception e) {
				Report.FailTest(test, "Idle End Time is not as expected in drill down of Net Idle Time report Actual is : "
						+ actualEndIdleTime + " and Expected is : " + expectedEndIdleTime);
			}
		
	}

		private void validateDrilldownStartIdle(Map<String, List<String>> actualDetailTable,Map<String, Map<String, List<String>>> actualNetIdleDrilldownTable) {
		
			
			List<String> actualStartIdleTime = new ArrayList<>();
			List<String> expectedStartIdleTime = new ArrayList<>();			
			List<Integer> noOfIdleOccurences=new ArrayList<>();
			SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
			List<String> date = new ArrayList<>();
			List<String> route = new ArrayList<>();
			try {
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

				
				for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
					if (entry.getKey().equals("Idle Occ")) {
						for (int i = 0; i < entry.getValue().size(); i++) {						
							noOfIdleOccurences.add(Integer.parseInt(entry.getValue().get(i)));					
						}
					}
				}
				
				for (int j=0;j<route.size();j++)
				{
				
					for (Entry<String, Map<String, List<String>>> entry : actualNetIdleDrilldownTable.entrySet()) {
						if(entry.getKey().equals(route.get(j)))
						for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
							if (entry2.getKey().equals("Start Idle")) {
								for (int i = 0; i < entry2.getValue().size(); i++) {
									if (entry2.getValue().get(i).equals("--")) {
										actualStartIdleTime.add("00:00");
									} else {
										actualStartIdleTime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
									}
								}
							}
						}
					}
				
					

					expectedStartIdleTime=Util.getDataFromDB("select  TO_CHAR(STARTED - (6 / 24)  ,'HH:MI') from OCS_ADMIN.TP_ALERT where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
							+ Util.sqlFormatedList(date) + ")) and FK_VEHICLE IS NOT NULL ORDER BY (FINISHED-STARTED) desc ");
			
				
				

				for (int i = 0; i < noOfIdleOccurences.get(j); i++) {
				
				
				if (Util.compareTime(actualStartIdleTime.get(i), expectedStartIdleTime.get(i))) {
					Report.InfoTest(test,
							"Idle Start Time is correct in drill down of Net Idle Time report for Route : "+route.get(j)+". Actual is : "
									+ actualStartIdleTime.get(i) + " and Expected is : "
									+ expectedStartIdleTime.get(i));
					Report.PassTest(test, "Idle Start Time is as expected in drill down of Net Idle Time report for Route : "+route.get(j));
				} else {
					Report.FailTest(test, "Idle Start Time is not as expected in drill down of Net Idle Time report for Route : "+route.get(j)+". Actual is : "
							+ actualStartIdleTime.get(i) + " and Expected is : " + expectedStartIdleTime.get(i));
				}
			} 
		}
		
		}catch (Exception e) {
				Report.FailTest(test, "Idle Start Time is not as expected in drill down of Net Idle Time report Actual is : "
						+ actualStartIdleTime + " and Expected is : " + expectedStartIdleTime);
			}
			
		
	}

		private void validateDrilldownTimeofDay(Map<String, List<String>> actualDetailTable,Map<String, Map<String, List<String>>> actualNetIdleDrilldownTable) {
		
			
			// Net Idle Time = Sum[[End Idle Time - Start Idle Time] - Meal Time - Downtime]
			
						List<String> startIdleTime = new ArrayList<>();
						List<String> endIdleTime= new ArrayList<>();
						List<String> actualTimeOfDay=new ArrayList<>();
						String expectedTimeOfDay=null;
						List<String> actualNetIdleTime=new ArrayList<>();
						List<Integer> noOfIdleOccurences=new ArrayList<>();
						List<String> route = new ArrayList<>();
						int threshold=5;
						try {
							for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
								if (entry.getKey().equals("Route")) {
									route = entry.getValue();
								}
							}
							
						
							
							for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
								if (entry.getKey().equals("Idle Occ")) {
									for (int i = 0; i < entry.getValue().size(); i++) {						
										noOfIdleOccurences.add(Integer.parseInt(entry.getValue().get(i)));					
									}
								}
							}
							
							for (int j=0;j<route.size();j++)
							{
							
								for (Entry<String, Map<String, List<String>>> entry : actualNetIdleDrilldownTable.entrySet()) {
									if(entry.getKey().equals(route.get(j)))
									for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
										if (entry2.getKey().equals("Start Idle")) {
											for (int i = 0; i < entry2.getValue().size(); i++) {
												if (entry2.getValue().get(i).equals("--")) {
													startIdleTime.add("00:00");
												} else {
													startIdleTime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
												}
											}
										}
									}
								}
							
								for (Entry<String, Map<String, List<String>>> entry : actualNetIdleDrilldownTable.entrySet()) {
									if(entry.getKey().equals(route.get(j)))
									for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
										if (entry2.getKey().equals("End Idle")) {
											for (int i = 0; i < entry2.getValue().size(); i++) {
												if (entry2.getValue().get(i).equals("--")) {
													endIdleTime.add("00:00");
												} else {
													endIdleTime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
												}
											}
										}
									}
								}
								
								for (Entry<String, Map<String, List<String>>> entry : actualNetIdleDrilldownTable.entrySet()) {
									if(entry.getKey().equals(route.get(j)))
									for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
										if (entry2.getKey().equals("Time of Day")) {
											for (int i = 0; i < entry2.getValue().size(); i++) {
												if (entry2.getValue().get(i).equals("--")) {
													actualTimeOfDay.add("00:00");
												} else {
													actualTimeOfDay.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
												}
											}
										}
									}
								}
							
							
						
							
							List<Integer> startIdleTimeMins=new ArrayList<>();
							List<Integer> endIdleTimeMins=new ArrayList<>();
						
							
							for(int i=0;i<startIdleTime.size();i++)
							{
								startIdleTimeMins.add(Util.convertHoursToMins(startIdleTime.get(i)));
							}
							for(int i=0;i<endIdleTime.size();i++)
							{
								endIdleTimeMins.add(Util.convertHoursToMins(endIdleTime.get(i)));
							}
							
						
							int timeOfDayInMins=0;
							for (int i = 0; i < noOfIdleOccurences.get(j); i++) {
							timeOfDayInMins =  (startIdleTimeMins.get(i)+threshold+1);	
									

							expectedTimeOfDay=Util.convertMinsToHours(timeOfDayInMins);
							
							if (Util.compareTime(actualTimeOfDay.get(i), expectedTimeOfDay)) {
								Report.InfoTest(test,
										"Time of Day is correct in drill down Idle Detail report for Route : "+route.get(j)+". Actual is : "
												+ actualTimeOfDay.get(i) + " and Expected is : "
												+ expectedTimeOfDay);
								Report.PassTest(test, "Time of Day is as expected in drill down of Idle Detail report for Route : "+route.get(j));
							} else {
								Report.FailTest(test, "Time of Day is not as expected in drill down of Idle Detail report for Route : "+route.get(j)+". Actual is : "
										+ actualTimeOfDay.get(i) + " and Expected is : " + expectedTimeOfDay);
							}
						} 
							}
					}catch (Exception e) {
							Report.FailTest(test, "Time of Day is not as expected in drill down of Idle Detail report Actual is : "
									+ actualTimeOfDay + " and Expected is : " + expectedTimeOfDay);
						}
		
	}

		private void validateDrilldownIdleonMap(Map<String, List<String>> actualDetailTable,Map<String, Map<String, List<String>>> actualNetIdleDrilldownTable) {
			
			List<String> actualIdleOnMap = new ArrayList<>();
			String expectedIdleOnMap="Idle on Map";
			List<Integer> noOfIdleOccurences=new ArrayList<>();
			List<String> route = new ArrayList<>();
			try {
				for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
					if (entry.getKey().equals("Route")) {
						route = entry.getValue();
					}
				}
				
			
				
				for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
					if (entry.getKey().equals("Idle Occ")) {
						for (int i = 0; i < entry.getValue().size(); i++) {						
							noOfIdleOccurences.add(Integer.parseInt(entry.getValue().get(i)));					
						}
					}
				}
				
				for (int j=0;j<route.size();j++)
				{
				
					for (Entry<String, Map<String, List<String>>> entry : actualNetIdleDrilldownTable.entrySet()) {
						if(entry.getKey().equals(route.get(j)))
						for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
							if (entry2.getKey().equals("Idle on Map")) {
								for (int i = 0; i < entry2.getValue().size(); i++) {									
										actualIdleOnMap.add(entry2.getValue().get(i).toString().trim());
									
								}
							}
						}
					}
				
				for (int i = 0; i < noOfIdleOccurences.get(j); i++) {
				
				if (actualIdleOnMap.get(i).equals(expectedIdleOnMap)) {
					
					Report.PassTest(test, "Idle On Map is as expected in drill down of Idle Detail report for Route : "+route.get(j));
				} else {
					Report.FailTest(test, "Idle On Map is not as expected in drill down of Idle Detail report for Route : "+route.get(j)+". Actual is : "
							+ actualIdleOnMap.get(i) + " and Expected is : " + expectedIdleOnMap+" with hyperlink opening in new tab");
				}
			} 
				}
		}catch (Exception e) {
				Report.FailTest(test, "Idle On Map is not as expected in drill down of Idle Detail report Actual is : "
						+ actualIdleOnMap + " and Expected is : " + expectedIdleOnMap);
			}
		
			
		
	}

		private void validateDrilldownNetIdle(Map<String, List<String>> actualDetailTable,Map<String, Map<String, List<String>>> actualNetIdleDrilldownTable) {
		
			// Net Idle Time = Sum[[End Idle Time - Start Idle Time] - Meal Time - Downtime]
			
			List<String> startIdleTime = new ArrayList<>();
			List<String> endIdleTime= new ArrayList<>();
			List<String> mealTime= new ArrayList<>();
			List<String> downTime=new ArrayList<>();
			List<String> actualNetIdleTime=new ArrayList<>();
			String expectedNetIdleTime=null;
			List<Integer> noOfIdleOccurences=new ArrayList<>();
			List<String> route = new ArrayList<>();
			try {
				for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
					if (entry.getKey().equals("Route")) {
						route = entry.getValue();
					}
				}
				
			
				
				for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
					if (entry.getKey().equals("Idle Occ")) {
						for (int i = 0; i < entry.getValue().size(); i++) {						
							noOfIdleOccurences.add(Integer.parseInt(entry.getValue().get(i)));					
						}
					}
				}
				
				for (int j=0;j<route.size();j++)
				{
				
					for (Entry<String, Map<String, List<String>>> entry : actualNetIdleDrilldownTable.entrySet()) {
						if(entry.getKey().equals(route.get(j)))
						for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
							if (entry2.getKey().equals("Start Idle")) {
								for (int i = 0; i < entry2.getValue().size(); i++) {
									if (entry2.getValue().get(i).equals("--")) {
										startIdleTime.add("00:00");
									} else {
										startIdleTime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
									}
								}
							}
						}
					}
				
					for (Entry<String, Map<String, List<String>>> entry : actualNetIdleDrilldownTable.entrySet()) {
						if(entry.getKey().equals(route.get(j)))
						for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
							if (entry2.getKey().equals("End Idle")) {
								for (int i = 0; i < entry2.getValue().size(); i++) {
									if (entry2.getValue().get(i).equals("--")) {
										endIdleTime.add("00:00");
									} else {
										endIdleTime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
									}
								}
							}
						}
					}
					
					for (Entry<String, Map<String, List<String>>> entry : actualNetIdleDrilldownTable.entrySet()) {
						if(entry.getKey().equals(route.get(j)))
						for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
							if (entry2.getKey().equals("Downtime (h:m)")) {
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
					
					for (Entry<String, Map<String, List<String>>> entry : actualNetIdleDrilldownTable.entrySet()) {
						if(entry.getKey().equals(route.get(j)))
						for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
							if (entry2.getKey().equals("Mealtime (h:m)")) {
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
					
					for (Entry<String, Map<String, List<String>>> entry : actualNetIdleDrilldownTable.entrySet()) {
						if(entry.getKey().equals(route.get(j)))
						for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
							if (entry2.getKey().equals("Net Idle (h:m)")) {
								for (int i = 0; i < entry2.getValue().size(); i++) {
									if (entry2.getValue().get(i).equals("--")) {
										actualNetIdleTime.add("00:00");
									} else {
										actualNetIdleTime.add(entry2.getValue().get(i).toString());
									}
								}
							}
						}
					}
				
			
				
				List<Integer> startIdleTimeMins=new ArrayList<>();
				List<Integer> endIdleTimeMins=new ArrayList<>();
				List<Integer> mealTimeMins=new ArrayList<>();
				List<Integer> downTimeMins=new ArrayList<>();
				
				for(int i=0;i<startIdleTime.size();i++)
				{
					startIdleTimeMins.add(Util.convertHoursToMins(startIdleTime.get(i)));
				}
				for(int i=0;i<endIdleTime.size();i++)
				{
					endIdleTimeMins.add(Util.convertHoursToMins(endIdleTime.get(i)));
				}
				
				for(int i=0;i<mealTime.size();i++)
				{
					mealTimeMins.add(Util.convertHoursToMins(mealTime.get(i)));
				}
				for(int i=0;i<downTime.size();i++)
				{
					downTimeMins.add(Util.convertHoursToMins(downTime.get(i)));
				}
				int expectedNetIdleTimeInMins = 0;
				for (int i = 0; i < noOfIdleOccurences.get(j); i++) {
				expectedNetIdleTimeInMins =  (endIdleTimeMins.get(i)-startIdleTimeMins.get(i))-downTimeMins.get(i)-mealTimeMins.get(i);	
						

				expectedNetIdleTime=Util.convertMinsToHours(expectedNetIdleTimeInMins);
				if (Util.compareTime(actualNetIdleTime.get(i), expectedNetIdleTime)) {
					Report.InfoTest(test,
							"Net idle Time is correct in drill down Idle Detail report for Route : "+route.get(j)+". Actual is : "
									+ actualNetIdleTime.get(i) + " and Expected is : "
									+ expectedNetIdleTime);
					Report.PassTest(test, "Net idle Time is as expected in drill down of Idle Detail report for Route : "+route.get(j));
				} else {
					Report.FailTest(test, "Net idle Time is not as expected in drill down of Idle Detail report for Route : "+route.get(j)+". Actual is : "
							+ actualNetIdleTime.get(i) + " and Expected is : " + expectedNetIdleTime);
				}
			} 
				}
		}catch (Exception e) {
				Report.FailTest(test, "Net idle Time is not as expected in drill down of Idle Detail report Actual is : "
						+ actualNetIdleTime + " and Expected is : " + expectedNetIdleTime);
			}
		
	}

		private void validateRouteType(Map<String, List<String>> actualDetailTable) {
		
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
								"Route Type  is correct in idle Detail report for Route : "
										+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
										+ " and expected Route Type  : " + expectedRouteTypes);
						Report.PassTest(test, "Route Type is as expected in idle Detail Sub View report");
					} else {
						Report.FailTest(test,
								"Route Type is not as expected in idle Detail report for Route : "
										+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
										+ " and expected Route Type : " + expectedRouteTypes);
					}
				}
			} catch (Exception e) {
				Report.FailTest(test, e.getMessage());
				Report.FailTest(test,
						"Route Type  is not as expected in idle Detail report Actual is : "
								+ actualRouteType.toString() + " and Expected is : " + expectedRouteType.toString());
			}
		
	}

		private void validateSubLOB(Map<String, List<String>> actualDetailTable) {
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
								"Sub LOB is correct in idle Detail report for Route : "
										+ route.get(i) + " with Actual SubLOB : " + actualSubLOB.get(0)
										+ " and expected Sub LOB : " + expectedSubLOB.get(0));
						Report.PassTest(test, "Sub LOB is as expected in idle Detail report");
					} else {
						Report.FailTest(test,
								"Sub LOB is not as expected in idle Detail report for Route : "
										+ route.get(i) + " with Actual Sub LOB : " + actualSubLOB.get(i)
										+ " and expected Sub LOB : " + expectedSubLOB.get(0));
					}
				}
			} catch (Exception e) {
				Report.FailTest(test, e.getMessage());
				Report.FailTest(test, "Sub LOB is not as expected in idle Detail report Actual is : "
						+ actualSubLOB.toString() + " and Expected is : " + expectedSubLOB.toString());
			}
		
	}

		public void validateLOB(Map<String, List<String>> actualDetailTable, String expectedLOB) {
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

					Report.InfoTest(test, "LOB is correct in idle Detail report Actual is : "
							+ actualLOB + " and Expected is : " + expectedLOB);
					Report.PassTest(test, "LOB is as expected in idle Detail report");
				} else {
					Report.FailTest(test, "LOB is not as expected in idle Detail report Actual is : "
							+ actualLOB + " and Expected is : " + expectedLOB);
				}
			} catch (Exception e) {
				Report.FailTest(test, "LOB is not as expected in idle Detail report Actual List is : " + actualLOB + " and Expected is : " + expectedLOB);
			}	
	}

	private void validateIdleOcc(Map<String, List<String>> actualDetailTable,
			Map<String, Map<String, List<String>>> actualNetIdleDrilldownTable) {
	
	// Net Idle Time = Sum[[End Idle Time - Start Idle Time] - Meal Time - Downtime]
		
		
		
		int expectedIdleOcc=0;
		
		List<Integer> actualIdleOccurences=new ArrayList<>();
		List<String> route = new ArrayList<>();
		try {
			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			
			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().equals("Idle Occ")) {
					for (int i = 0; i < entry.getValue().size(); i++) {						
						actualIdleOccurences.add(Integer.parseInt(entry.getValue().get(i)));
						Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)), "Idle Occ in Idle Detail Report");
					}
				}
			}
			
			for (int j=0;j<route.size();j++)
			{
			
				for (Entry<String, Map<String, List<String>>> entry : actualNetIdleDrilldownTable.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Net Idle (h:m)")) {							
							expectedIdleOcc=entry2.getValue().size();							
						}
					}
				}
			
			if (actualIdleOccurences.get(j)==expectedIdleOcc) {
				Report.InfoTest(test,
						"Idle Occ is correct in detail section of Idle Detail report for Route : "+route.get(j)+". Actual is : "
								+ actualIdleOccurences.get(j) + " and Expected is : "
								+ expectedIdleOcc);
				Report.PassTest(test, "Idle Occ is as expected in detail section of Idle Detail report for Route : "+route.get(j));
			} else {
				Report.FailTest(test, "Idle Occ is not as expected in detail section of Idle Detail report for Route : "+route.get(j)+". Actual is : "
						+ actualIdleOccurences.get(j) + " and Expected is : " + expectedIdleOcc);
			}
		} 
	
	}catch (Exception e) {
			Report.FailTest(test, "Idle Occ is not as expected in detail section of Idle Detail report Actual is : "
					+ actualIdleOccurences + " and Expected is : " + expectedIdleOcc);
		}
		
	}

	private void validateNetIdle(Map<String, List<String>> actualDetailTable,Map<String,Map<String, List<String>>> actualNetIdleDrilldownTable) {
		// Net Idle Time = Sum[[End Idle Time - Start Idle Time] - Meal Time - Downtime]
		
		List<String> startIdleTime = new ArrayList<>();
		List<String> endIdleTime= new ArrayList<>();
		List<String> mealTime= new ArrayList<>();
		List<String> downTime=new ArrayList<>();
		List<String> actualNetIdleTime=new ArrayList<>();
		String expectedNetIdleTime=null;
		List<Integer> noOfIdleOccurences=new ArrayList<>();
		List<String> route = new ArrayList<>();
		try {
			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			
			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().equals("Net Idle (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualNetIdleTime.add("00:00");
						} else {
							actualNetIdleTime.add(entry.getValue().get(i));
							// Validate Decimal Format for the value : should
							// be hh:mm
							Util.validateTimeFormat(entry.getValue().get(i), "Net Idle Time in Detail section");
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualDetailTable.entrySet()) {
				if (entry.getKey().equals("Idle Occ")) {
					for (int i = 0; i < entry.getValue().size(); i++) {						
						noOfIdleOccurences.add(Integer.parseInt(entry.getValue().get(i)));					
					}
				}
			}
			
			for (int j=0;j<route.size();j++)
			{
			
				for (Entry<String, Map<String, List<String>>> entry : actualNetIdleDrilldownTable.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Start Idle")) {
							for (int i = 0; i < entry2.getValue().size(); i++) {
								if (entry2.getValue().get(i).equals("--")) {
									startIdleTime.add("00:00");
								} else {
									startIdleTime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
								}
							}
						}
					}
				}
			
				for (Entry<String, Map<String, List<String>>> entry : actualNetIdleDrilldownTable.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("End Idle")) {
							for (int i = 0; i < entry2.getValue().size(); i++) {
								if (entry2.getValue().get(i).equals("--")) {
									endIdleTime.add("00:00");
								} else {
									endIdleTime.add(entry2.getValue().get(i).replaceAll("AM", "").replaceAll("PM","").trim());
								}
							}
						}
					}
				}
				
				for (Entry<String, Map<String, List<String>>> entry : actualNetIdleDrilldownTable.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Downtime (h:m)")) {
							for (int i = 0; i < entry2.getValue().size(); i++) {
								if (entry2.getValue().get(i).equals("--")) {
									downTime.add("00:00");
								} else {
									downTime.add(entry2.getValue().get(i).toString());
								}
							}
						}
					}
				}
				
				for (Entry<String, Map<String, List<String>>> entry : actualNetIdleDrilldownTable.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Mealtime (h:m)")) {
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
			
		
			
			List<Integer> startIdleTimeMins=new ArrayList<>();
			List<Integer> endIdleTimeMins=new ArrayList<>();
			List<Integer> mealTimeMins=new ArrayList<>();
			List<Integer> downTimeMins=new ArrayList<>();
			
			for(int i=0;i<startIdleTime.size();i++)
			{
				startIdleTimeMins.add(Util.convertHoursToMins(startIdleTime.get(i)));
			}
			for(int i=0;i<endIdleTime.size();i++)
			{
				endIdleTimeMins.add(Util.convertHoursToMins(endIdleTime.get(i)));
			}
			
			for(int i=0;i<mealTime.size();i++)
			{
				mealTimeMins.add(Util.convertHoursToMins(mealTime.get(i)));
			}
			for(int i=0;i<downTime.size();i++)
			{
				downTimeMins.add(Util.convertHoursToMins(downTime.get(i)));
			}
			int expectedNetIdleTimeInMins = 0;
			for (int i = 0; i < noOfIdleOccurences.get(j); i++) {
			expectedNetIdleTimeInMins =  expectedNetIdleTimeInMins+((endIdleTimeMins.get(i)-startIdleTimeMins.get(i))-downTimeMins.get(i)-mealTimeMins.get(i));	
					
			}
			expectedNetIdleTime=Util.convertMinsToHours(expectedNetIdleTimeInMins);
			if (Util.compareTime(actualNetIdleTime.get(j), expectedNetIdleTime)) {
				Report.InfoTest(test,
						"Net idle Time is correct in detail section of Idle Detail report for Route : "+route.get(j)+". Actual is : "
								+ actualNetIdleTime.get(j) + " and Expected is : "
								+ expectedNetIdleTime);
				Report.PassTest(test, "Net idle Time is as expected in detail section of Idle Detail report for Route : "+route.get(j));
			} else {
				Report.FailTest(test, "Net idle Time is not as expected in detail section of Idle Detail report for Route : "+route.get(j)+". Actual is : "
						+ actualNetIdleTime.get(j) + " and Expected is : " + expectedNetIdleTime);
			}
		} 
	
	}catch (Exception e) {
			Report.FailTest(test, "Net idle Time is not as expected in detail section of Idle Detail report Actual is : "
					+ actualNetIdleTime + " and Expected is : " + expectedNetIdleTime);
		}
		
	}

	private void validateDriver(Map<String, List<String>> actualDetailTable, String expectedDriverName) {
		
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
							"Driver Name is correct in idle Detail report Actual List is : "
									+ actualDrivers.toString() + " and Expected is : " + drivers[i]);
					Report.PassTest(test, "Driver Name is as expected in idle Detail sub view report");
				} else {
					Report.FailTest(test,
							"Driver Name is not as expected in idle Detail report Actual List is : " + actualDrivers.toString()
									+ " and Expected is : " + drivers[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Driver Name is not as expected in idle Detail report Actual List is : "
							+ actualDrivers.toString() + " and Expected is : " + drivers.toString());
		}
		
	}

	private void validateRoute(Map<String, List<String>> actualDetailTable, String expectedRouteName) {
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
							"Route Name is correct in idle Detail report Actual List is : "
									+ actualRoutes.toString() + " and Expected is : " + routes[i]);
					Report.PassTest(test, "Route Name is as expected in idle Detail report");
				} else {
					Report.FailTest(test,
							"Route Name is not as expected in idle Detail report Actual List is : " + actualRoutes.toString()
									+ " and Expected is : " + routes[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Route is not as expected in idle Detail report Actual List is : "
							+ actualRoutes.toString() + " and Expected is : " + routes.toString());
		}
		
	}

	private void validateDate(Map<String, List<String>> actualDetailTable, String fromDate, String toDate) {
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
							"Route Date is correct in idle Detail report for route : "
									+ actualRoutes.get(i) + ". Actual is : " + format1.format(routeServedDate.get(i))
									+ " and Expected is between : " + format1.format(startDate) + "and "
									+ format1.format(endDate));
					Report.PassTest(test, "Route Date is as expected in idle Detail Report");
				} else {
					Report.FailTest(test,
							"Route Date is not as expected in idle Detail report Actual is : "
									+ format1.format(routeServedDate.get(i)) + " and Expected is between : "
									+ format1.format(startDate) + "and " + format1.format(endDate));
				}
			}

		} catch (Exception e) {
			Report.FailTest(test, "Not able to compare date is in between the date range");
			LogClass.error(e.getMessage());
		}
		
	}

	private void validateAvgNetIdleTime(Map<String, List<String>> actualSummaryTableData) {
		
		int totalIdleEvents = 0;
		String totalNetIdleTime=null;
		String actualAvgNetIdleTime = null;
		String expectedAvgNetIdleTime = null;	
		try {
			
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Total Net Idle Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							totalNetIdleTime = "00:00";
						} else {
							totalNetIdleTime = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().equals("Total Idle Events")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							totalIdleEvents = 0;
						} else {
							totalIdleEvents = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));							
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().equals("Avg Net Idle Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualAvgNetIdleTime = "00:00";
						} else {
							actualAvgNetIdleTime = entry.getValue().get(i).toString();
						}
					}

				
				}
			}

			// Avg Net Idle Time = Total Net Idle time / Total Idle Events
			int netIdleTimeInMins=Util.convertHoursToMins(totalNetIdleTime);			
			int expectedAvgIdleTimeInMins=netIdleTimeInMins/totalIdleEvents;
			
			expectedAvgNetIdleTime=Util.convertMinsToHours(expectedAvgIdleTimeInMins);
			
			if (actualAvgNetIdleTime.equals("00:00")) {
				Report.FailTest(test,
						"Avg Net Idle Time is not calculated as expected in Idle summary report Actual is : -- and Expected is : "
								+ expectedAvgNetIdleTime);
			} else {
				if (Util.compareTime(actualAvgNetIdleTime, expectedAvgNetIdleTime)) {
					Report.InfoTest(test, "Avg Net Idle Time is correct in Idle summary report Actual is : "
							+ actualAvgNetIdleTime + " and Expected is : " + expectedAvgNetIdleTime);
					Report.PassTest(test, "Avg Net Idle Time is as expected in Summary report");
				} else {
					Report.FailTest(test, "Avg Net Idle Time is not as expected in Idle summary report Actual is : " + actualAvgNetIdleTime
							+ " and Expected is : " + expectedAvgNetIdleTime);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Avg Net Idle Time is not as expected in Idle summary report Actual is : " + actualAvgNetIdleTime
					+ " and Expected is : " + expectedAvgNetIdleTime);
		}
		
	}

	private void validateTotalIdleEvents(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualIdleDetailTableData) {
		int actualTotalIdleEvents = 0;
		int expectedTotalIdleEvents = 0;	
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().equals("Total Idle Events")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualTotalIdleEvents = 0;
						} else {
							actualTotalIdleEvents = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualTotalIdleEvents, "Total Idle Events in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualIdleDetailTableData.entrySet()) {
				if (entry.getKey().equals("Idle Occ")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedTotalIdleEvents = expectedTotalIdleEvents + Integer.parseInt("0.0");
						} else {
							expectedTotalIdleEvents = expectedTotalIdleEvents
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			// Summary Total Idle Events = sum of idle occurences of all routes
			
			if (actualTotalIdleEvents == 0) {
				Report.FailTest(test,
						"Total Idle Events is not calculated as expected in Idle Detail summary report Actual is : -- and Expected is : "
								+ expectedTotalIdleEvents);
			} else {
				if (Util.compareNumber(actualTotalIdleEvents, expectedTotalIdleEvents)) {
					Report.InfoTest(test, "Total Idle Events is correct in Idle Detail summary report Actual is : "
							+ actualTotalIdleEvents + " and Expected is : " + expectedTotalIdleEvents);
					Report.PassTest(test, "Total Idle Events is as expected in Idle Detail Summary report");
				} else {
					Report.FailTest(test, "Total Idle Events is not as expected in Idle Detail summary report Actual is : " + actualTotalIdleEvents
							+ " and Expected is : " + expectedTotalIdleEvents);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Idle Events is not as expected in Idle Detail summary report Actual is : " + actualTotalIdleEvents
					+ " and Expected is : " + expectedTotalIdleEvents);
		}
		
	}

	private void validateTotalNetIdleTime(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualIdleDetailTableData) {
		
		String actualNetIdleTime = null;
		String expectedNetIdleTime = null;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Total Net Idle Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualNetIdleTime = "00:00";
						} else {
							actualNetIdleTime = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualIdleDetailTableData.entrySet()) {
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

			// Validate Time Format of Actual Driver Hours
			Util.validateTimeFormat(actualNetIdleTime, "Net Idle Time in Summary Section");

			// expectedNetIdleTime=sum of idle time of each
			// routes
			// expectedActualDriverHours=String.format("%02d",(int)Math.floor(totalMins/60))
			// + ":" +String.format("%02d",( totalMins % 60 ));
			expectedNetIdleTime = Util.convertMinsToHours(totalMins);

			if (actualNetIdleTime.equals("00:00")) {
				Report.FailTest(test,
						"Total Net Idle Time is not calculated as expected in summary report Actual is : -- and Expected is : "
								+ expectedNetIdleTime);
			} else {

				if (actualNetIdleTime.equals(expectedNetIdleTime)) {
					Report.InfoTest(test, "Total Net Idle Time is correct in summary report Actual is : "
							+ actualNetIdleTime + " and Expected is : " + expectedNetIdleTime);
					Report.PassTest(test, "Total Net Idle Time is as expected in Summary report");
				} else {
					Report.FailTest(test, "Total Net Idle Time is not as expected in summary report Actual is : "
							+ actualNetIdleTime + " and Expected is : " + expectedNetIdleTime);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Net Idle Time is not as expected in summary report Actual is : "
					+ actualNetIdleTime + " and Expected is : " + expectedNetIdleTime);
		}
		
	}


	
	

}
