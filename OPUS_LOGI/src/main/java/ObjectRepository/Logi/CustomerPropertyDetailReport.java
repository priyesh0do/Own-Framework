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

public class CustomerPropertyDetailReport {

	ExtentTest test;
	WebDriver driver;

	public CustomerPropertyDetailReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	//CustomerPropertyDetailSummaryColumns
	@FindBy(xpath="//*[@id='dtCustTimeSummary']/thead/tr/th")
	List<WebElement> CustomerPropertyDetailSummaryColumns;
	
	
	//CustomerPropertyDetailSummaryRows
	@FindBy(xpath="//*[@id='dtCustTimeSummary']/tbody/tr")
	List<WebElement> CustomerPropertyDetailSummaryRows;
	
	
	//CustomerPropertyDetailSectionExceptionTabColumns
	@FindBy(xpath="//*[@id='t3']/thead/tr/th")
	List<WebElement> CustomerPropertyDetailSectionExceptionTabColumns;
	
	//CustomerPropertyDetailSectionExceptionTabRows
	@FindBy(xpath="//*[@id='t3']/tbody/tr")
	List<WebElement> CustomerPropertyDetailSectionExceptionTabRows;
	
	//CustomerPropertyDetailSectionAllTabColumns
		@FindBy(xpath="//*[@id='t2']/thead/tr/th")
		List<WebElement> CustomerPropertyDetailSectionAllTabColumns;
		
		//CustomerPropertyDetailSectionAllTabRows
		@FindBy(xpath="//*[@id='t2']/tbody/tr")
		List<WebElement> CustomerPropertyDetailSectionAllTabRows;
		
		//CustomerPropertyDrillDownColums
		@FindBy(xpath="//*[@id='CustTime_Sub']/thead/tr/th")
		List<WebElement > CustomerPropertyDrillDownColums;
		
		//RedirectedToAllTab
		@FindBy(xpath="//*[@id='rdCaption_tabALL']")
		WebElement RedirectedToAllTab;
		
		//RedirectedtoExceptionTab
		@FindBy(xpath="//*[@id='rdCaption_tabExcp']")
		WebElement RedirectedtoExceptionTab;
		
		//CustomerPropertyDrillDownRows
		@FindBy(xpath="//*[@id='CustTime_Sub']/tbody/tr")
		List<WebElement > CustomerPropertyDrillDownRows;
		
		@FindBy(xpath="//*[@id='CustTime_Sub']/thead/tr/th")
		   List<WebElement> CustomerPropertyTimeDrillDownColumns;
		   
		   @FindBy(xpath="//*[@id='CustTime_Sub']/tbody/tr")
		   List<WebElement> CustomerPropertyTimeDrillDownRows;
		
	
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
			 else if (tableName.equals("t3")) {
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

		
		
		
	
	public Map<String, List<String>> CustomerPropertyDetailReportSummaryData() throws IOException {
		//System.out.println("inside Actual Summary Data");
		Map<String, List<String>> CustomerPropertySummaryTableData = new HashMap<>();
		try {
			CustomerPropertySummaryTableData = readTable(CustomerPropertyDetailSummaryColumns, CustomerPropertyDetailSummaryRows, "dtCustTimeSummary");
			Report.PassTestScreenshot(test, driver, "CustomerPropertySummary table data read successfully", "CustomerPropertyDetail report");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read CustomerPropertysummary table data");
		}

		for (Entry<String, List<String>> entry : CustomerPropertySummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return CustomerPropertySummaryTableData;
	}
	
	
	public Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab() throws IOException {
		
		Map<String, List<String>> CustomerPropertyDetailTableData = new HashMap<>();
		try {
			CustomerPropertyDetailTableData = readTable(CustomerPropertyDetailSectionExceptionTabColumns,CustomerPropertyDetailSectionExceptionTabRows,"t3");
			Report.PassTestScreenshot(test, driver, "CustomerProperty Detail Exceptions Tab table data read successfully", "CustomerPropertyDetail Exceptions Tab report");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read CustomerProperty Detail Exceptions Tab table data");
		}

		for (Entry<String, List<String>> entry : CustomerPropertyDetailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return CustomerPropertyDetailTableData;
	}
	
	public Map<String, List<String>> CustomerPropertyDetailReportDetailDataAllTab() throws IOException {
		
		Map<String, List<String>> CustomerPropertyDetailTableData = new HashMap<>();
		try {
			CustomerPropertyDetailTableData = readTable(CustomerPropertyDetailSectionAllTabColumns, CustomerPropertyDetailSectionAllTabRows, "t2");
			Report.PassTestScreenshot(test, driver, "CustomerProperty Detail All Tab table data read successfully", "CustomerPropertyDetail All Tab report");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read CustomerProperty Detail All Tab table data");
		}

		for (Entry<String, List<String>> entry : CustomerPropertyDetailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return CustomerPropertyDetailTableData;
	}
	
	public Map<String, Map<String, List<String>>> getActualCustomerPropertDrilldownTableDataExcTab(String route) {
		Map<String, Map<String, List<String>>> CustomerPropertyDrilldownTableData = new HashMap<>();
		try {
			Thread.sleep(5000);
			String[] routes = route.split(";");

			Map<String, List<String>> CustPropertyDrilldownTemp = new HashMap<>();

			for (int i = 0; i < routes.length; i++) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t3']/tbody/tr[" + (i + 1) + "]/td/a/span")).click();
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer,subIdleDetail_Row" + (i + 1));
					CustPropertyDrilldownTemp = readTable(CustomerPropertyDrillDownColums, CustomerPropertyDrillDownRows, "CustTime_Sub");
					Report.PassTestScreenshot(test, driver, "CustomerProperty Drilldown  Exception Tab table data read successfully",
							"CustomerProperty Drilldown Data");
					Util.switchToDefaultWindow();
				} catch (Exception e) {
					Report.FailTest(test, "Not able to read CustomerProperty drilldown Exception Tab table data");
				}
				CustomerPropertyDrilldownTableData.put(routes[i], CustPropertyDrilldownTemp);
			}

			for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyDrilldownTableData.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
				}
			}
			Util.switchToDefaultWindow();
			return CustomerPropertyDrilldownTableData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to read CustomerProperty drilldown Exception Tab data", "Customer Property Drilldown");
		}

		return CustomerPropertyDrilldownTableData;
	}

	public Map<String, Map<String, List<String>>> getActualCustomerPropertDrilldownTableDataAllTab(String route) {
		Map<String, Map<String, List<String>>> CustomerPropertyDrilldownTableData = new HashMap<>();
		try {
			Thread.sleep(5000);
			String[] routes = route.split(";");

			Map<String, List<String>> CustPropertyDrilldownTemp = new HashMap<>();

			for (int i = 0; i < routes.length; i++) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t2']/tbody/tr[" + (i + 1) + "]/td/a/span")).click();
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer,subIdleDetail_Row" + (i + 1));
					CustPropertyDrilldownTemp = readTable(CustomerPropertyDrillDownColums, CustomerPropertyDrillDownRows,"CustTime_Sub");
					Report.PassTestScreenshot(test, driver, "CustomerProperty Drilldown All Tab table data read successfully",
							"CustomerProperty Drilldown Data");
					Util.switchToDefaultWindow();
				} catch (Exception e) {
					Report.FailTest(test, "Not able to read CustomerProperty drilldown All Tab table data");
				}
				CustomerPropertyDrilldownTableData.put(routes[i], CustPropertyDrilldownTemp);
			}

			for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyDrilldownTableData.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
				}
			}
			Util.switchToDefaultWindow();
			return CustomerPropertyDrilldownTableData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to read CustomerProperty drilldown All Tab data","Customer Property Drilldown");
		}

		return CustomerPropertyDrilldownTableData;
	}

   public void RedirectedToAllTab() {
	   
	   try {
		   RedirectedToAllTab.click();
		   Thread.sleep(2000);
		   Report.PassTest(test, "Redirected to All Tab");
	} catch (Exception e) {
		Report.FailTest(test, "Unable to Redirect to All Tab");
	}
	   
	   
   }
   
   public void RedirectedToExceptionTab() {
	   
	   try {
		   RedirectedtoExceptionTab.click();
		   Report.PassTest(test, "Redirected to Exception Tab");
		
	} catch (Exception e) {
		Report.FailTest(test, "Unable to Redirect to Exception Tab");
	}
	   
   }
   
   public void ClickOnAllTab() {
	   
	   try {
		   Util.switchToDefaultTab();
			Util.selectFrame("opusReportContainer,subReportContainer");
		     RedirectedToAllTab.click();
		     Thread.sleep(2000);
		     Report.PassTestScreenshot(test, driver, "Redirected to All Tab", "PassedAllTabRedirection");
	} catch (Exception e) {
		Report.FailTest(test, "Unable to Redirecte to All Tab");
	}
	   
	   
   }
   
   public void ClickonCustomerPropertyDrillDown() {

		try {
			
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t2']/tbody/tr[1]/td/a/span")).click();
				Thread.sleep(2000);
				
			}
		
		 catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to Click on Customer Property Drill Down", "CustomerPropertyDrillDown");
		}
		
	}
   
   
   
   public Map<String, Map<String, List<String>>> CustomerPropertyTimeDrillDownDataExceptionsTab(String route) {
		Map<String, Map<String, List<String>>> CustomerPropertyTimeDrilldownTableData = new HashMap<>();
		try {
			Thread.sleep(5000);
			String[] routes = route.split(";");

			Map<String, List<String>> CustomerPropertyTimeDrilldownTemp = new HashMap<>();

			for (int i = 0; i < routes.length; i++) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t3']/tbody/tr[" + (i + 1) + "]/td/a/span")).click();
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer,subCustTimeExcp_Row" + (i + 1));
					CustomerPropertyTimeDrilldownTemp = readTable(CustomerPropertyTimeDrillDownColumns, CustomerPropertyTimeDrillDownRows, "CustTime_Sub");
					Report.PassTestScreenshot(test, driver, "Customer Property Drilldown table data read successfully",
							"Customer Property Drilldown Data");
					Util.switchToDefaultWindow();
				} catch (Exception e) {
					Report.FailTest(test, "Not able to read Customer Property drilldown table data");
				}
				CustomerPropertyTimeDrilldownTableData.put(routes[i], CustomerPropertyTimeDrilldownTemp);
			}

			for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyTimeDrilldownTableData.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
				}
			}
			Util.switchToDefaultWindow();
			return CustomerPropertyTimeDrilldownTableData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to read Customer Property drilldown data", "Customer Property Drilldown");
		}

		return CustomerPropertyTimeDrilldownTableData;
	}
   
  
   
   public Map<String, Map<String, List<String>>> CustomerPropertyTimeDrillDownDataAllTab(String route) {
		Map<String, Map<String, List<String>>> CustomerPropertyTimeDrilldownTableData = new HashMap<>();
		try {
			Thread.sleep(5000);
			String[] routes = route.split(";");

			Map<String, List<String>> CustomerPropertyTimeDrilldownTemp = new HashMap<>();

			for (int i = 0; i < routes.length; i++) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t2']/tbody/tr[" + (i + 1) + "]/td/a/span")).click();
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer,subCustTimeExcp_Row" + (i + 1));
					CustomerPropertyTimeDrilldownTemp = readTable(CustomerPropertyTimeDrillDownColumns, CustomerPropertyTimeDrillDownRows, "CustTime_Sub");
					Report.PassTestScreenshot(test, driver, "Customer Property Drilldown table data read successfully",
							"Customer Property Drilldown Data");
					Util.switchToDefaultWindow();
				} catch (Exception e) {
					Report.FailTest(test, "Not able to read Customer Property drilldown table data");
				}
				CustomerPropertyTimeDrilldownTableData.put(routes[i], CustomerPropertyTimeDrilldownTemp);
			}

			for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyTimeDrilldownTableData.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
				}
			}
			Util.switchToDefaultWindow();
			return CustomerPropertyTimeDrilldownTableData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to read Customer Property drilldown data", "Customer Property Drilldown");
		}

		return CustomerPropertyTimeDrilldownTableData;
	}
   
   
   
   
   public void ValidateCustomerPropertyDetailReport_SummaryTabledata(Map<String, List<String>> CustomerPropertyDetailReportSummaryData,Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab,Map<String, Map<String, List<String>>> CustomerPropertyTimeDrillDownDataExceptionsTab,Map<String, List<String>> CustomerPropertyDetailReportDetailDataAllTab) {
	   validateTotalExceptionsTime(CustomerPropertyDetailReportSummaryData, CustomerPropertyDetailReportDetailDataExceptionsTab);
	   validateTotalStopWithException(CustomerPropertyDetailReportSummaryData, CustomerPropertyDetailReportDetailDataExceptionsTab, CustomerPropertyTimeDrillDownDataExceptionsTab);
	   validateTotalStops(CustomerPropertyDetailReportSummaryData, CustomerPropertyDetailReportDetailDataAllTab, "Summary Section");
	   validateExceptionTimePercentages(CustomerPropertyDetailReportSummaryData, CustomerPropertyDetailReportDetailDataAllTab);
	   validateAvgActualTime(CustomerPropertyDetailReportSummaryData, CustomerPropertyDetailReportDetailDataAllTab);
	   ValidateAvgThresholdTime(CustomerPropertyDetailReportDetailDataAllTab, CustomerPropertyDetailReportSummaryData);
   }
   
   
   private void validateTotalExceptionsTime(Map<String, List<String>> CustomerPropertyDetailReportSummaryData,
			Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab) {
		
		String actualExceptionTime = null;
		String expectedExceptionTime = null;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportSummaryData.entrySet()) {
				if (entry.getKey().contains("Total Exception Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualExceptionTime = "00:00";
						} else {
							actualExceptionTime = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
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

		
			Util.validateTimeFormat(actualExceptionTime, "Exception Time in Summary Section");

			
			expectedExceptionTime = Util.convertMinsToHours(totalMins);

			if (actualExceptionTime.equals("00:00")) {
				Report.FailTest(test,
						"Total Exception Time is not calculated as expected in summary report Actual is : -- and Expected is : "
								+ expectedExceptionTime);
			} else {

				if (actualExceptionTime.equals(expectedExceptionTime)) {
					Report.InfoTest(test, "Total Exception Time is correct in summary report Actual is : "
							+ actualExceptionTime + " and Expected is : " + expectedExceptionTime);
					Report.PassTest(test, "Total Exception Time is as expected in Summary report");
				} else {
					Report.FailTest(test, "Total Exception Time is not as expected in summary report Actual is : "
							+ actualExceptionTime + " and Expected is : " + expectedExceptionTime);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Exception Time is not as expected in summary report Actual is : "
					+ actualExceptionTime + " and Expected is : " + expectedExceptionTime);
		}
		
	}

   private void validateTotalStopWithException(Map<String, List<String>> CustomerPropertyDetailReportSummaryData,Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab,
		   Map<String, Map<String, List<String>>> CustomerPropertyTimeDrillDownDataExceptionsTab) {
		int actualTotalStopswithException = 0;
		int expectedTotalIdleEvents = 0;
		int expectedExceptionTime=0;
		List<String> route = new ArrayList<>();
		try {
			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportSummaryData.entrySet()) {
				if (entry.getKey().equals("Total Stops w/ Exception")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualTotalStopswithException = 0;
						} else {
							actualTotalStopswithException = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualTotalStopswithException, "Total Customer Property Time in Summary Section");
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			
			for (int j=0;j<route.size();j++)
			{
				for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyTimeDrillDownDataExceptionsTab.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Exception Time (h:m)")) {
							
								expectedExceptionTime+=entry2.getValue().size();
								
							}
						}
					}
				}
			if (Util.compareNumber(actualTotalStopswithException, expectedExceptionTime)) {
				Report.PassTestScreenshot(test, driver, "Validated Total Stops w/ Exception Actual is > " + actualTotalStopswithException+ " And Expected is > " + expectedExceptionTime, "Passed Total stop w/ Exception");
			}
			else {
				Report.FailTestSnapshot(test, driver, "Unable to Validate Total Stops w/ Exception Actual is > " + actualTotalStopswithException+ " And Expected is > " + expectedExceptionTime, "Failed Total stop w/ Exception");
			}
				
				
				
			}
			
		catch (Exception e) {
			Report.InfoTest(test, "Unable to Validate Total Stops w/ Exception Actual is > " + actualTotalStopswithException+ " And Expected is > " + expectedExceptionTime);
	}

	
	
	
}
  
   private void validateTotalStops(Map<String, List<String>> CustomerPropertyDetailReportSummaryData,Map<String, List<String>>CustomerPropertyDetailReportDetailDataAllTab,String subViewReportName) {
		// Total Stops will be directly validated against confirmation DB

		List<Double> actualStops = new ArrayList<>();
		List<String> expectedStops = new ArrayList<>();
		List<String> expectedTickets = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		try {
			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportSummaryData.entrySet()) {
				if (entry.getKey().equals("Total Stops")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualStops.add(Double.parseDouble(entry.getValue().get(i)));
						Util.validateFieldDecimalPlaces(Double.parseDouble(entry.getValue().get(i)), 1,
								"Stops in " + subViewReportName + " sub view Report");
					}
				}
			}
			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataAllTab.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataAllTab.entrySet()) {
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

 
   
   private void validateExceptionTimePercentages(Map<String, List<String>> CustomerPropertyDetailReportSummaryData,Map<String, List<String>>CustomerPropertyDetailReportDetailDataAllTab) {
	 /*SUM(Exception Time) / SUM(Customer Property Time)  Percentage:  Whole Number*/
	   int actualTotalException = 0;
	   int totalExceptionTime=0;
	   int totalCustPropTime=0;
	   int ExpectedTotalException=0;
	   
	   try {
		   for (Entry<String, List<String>> entry : CustomerPropertyDetailReportSummaryData.entrySet()) {
				if (entry.getKey().equals("Exception Time %")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualTotalException = 0;
						} else {
							actualTotalException = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualTotalException, "Total Exception Time % in Summary Section");
						}
					}
				}
			}
		   
		   for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataAllTab.entrySet()) {
				if (entry.getKey().contains("Exception Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalExceptionTime += mins;
						}

					}
				}
			}

		   for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataAllTab.entrySet()) {
				if (entry.getKey().contains("Customer Property Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalCustPropTime += mins;
						}

					}
				}
			}

		
		   ExpectedTotalException=(totalExceptionTime/totalCustPropTime)*100;
		   if (Util.compareNumber(actualTotalException, ExpectedTotalException)) {
			Report.PassTestScreenshot(test, driver, "Validated Exception Time % Actual is  " + actualTotalException+ " and Expected is "+ ExpectedTotalException , "PassedTotalException");
		}
		   else {
			   Report.FailTestSnapshot(test, driver, "Unable to validate Exception Time % Actual is  " + actualTotalException+ " and Expected is "+ ExpectedTotalException , "FailedTotalException");
		}

			
			
		   
	} catch (Exception e) {
		Report.InfoTest(test, "Unable to validate Exception Time % Actual is  " + actualTotalException+ " and Expected is "+ ExpectedTotalException);
	}
	   
	   
	   
	   
   }
   
   
   private void validateAvgActualTime(Map<String, List<String>> CustomerPropertyDetailReportSummaryData,Map<String, List<String>> CustomerPropertyDetailReportDetailDataAllTab) {
	 /*  SUM(Customer Property Time) / Total Stops	   Format:  hh:mm*/
	   int actualAvgActualtime=0;
	   int totalCustomerPropertyTimeTime=0;
	   int actualTotalStops=0;
	   int ExpectedAvgActual=0;
	   
	   
	   try {
		   for (Entry<String, List<String>> entry : CustomerPropertyDetailReportSummaryData.entrySet()) {
				if (entry.getKey().equals("Avg Actual Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualAvgActualtime = 0;
						} else {
							actualAvgActualtime = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualAvgActualtime, "Total Avg Actual in Summary Section");
						}
					}
				}
			}
		   
		   
		   
		   for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataAllTab.entrySet()) {
				if (entry.getKey().contains("Customer Property Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalCustomerPropertyTimeTime += mins;
						}

					}
				}
			}
		   
		   for (Entry<String, List<String>> entry : CustomerPropertyDetailReportSummaryData.entrySet()) {
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
			
		   ExpectedAvgActual=(totalCustomerPropertyTimeTime/actualTotalStops)*100;
		   if (Util.compareNumber(actualAvgActualtime, ExpectedAvgActual)) {
			   Report.PassTestScreenshot(test, driver, "Validated Avg Actual Time Expected is > " + ExpectedAvgActual + "And Actual is" +actualAvgActualtime , "PassedAvgActual");
			
		}
		   else {
			   Report.FailTestSnapshot(test, driver, "Unable to Validate Avg Actual Time Expected is > " + ExpectedAvgActual + "And Actual is" +actualAvgActualtime , "FailedAvgActual");
		}
		   
		   
		   
	} catch (Exception e) {
		Report.InfoTest(test, "Unable to Validate Avg Actual Time Expected is > " + ExpectedAvgActual + "And Actual is" +actualAvgActualtime);
	}
   }
   
   private void ValidateAvgThresholdTime(Map<String, List<String>> CustomerPropertyDetailReportDetailDataAllTab,Map<String, List<String>> CustomerPropertyDetailReportSummaryData) {
	  /* Avg Threshold (h:m)= SUM(Threshold) / Total Stops.
			   Threshold = 4*# of Lifts
	   */
	   int actualNumberofLifts=0;
	   int actualTotalstops=0;
	   int actualAvgThresholdTime=0;
	   int ExpectedThresholdTime=0;
	  try { 
	   for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataAllTab.entrySet()) {
			if (entry.getKey().equals("# of Lifts")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualNumberofLifts = actualNumberofLifts + Integer.parseInt("0.0");
					} else {
						actualNumberofLifts = actualNumberofLifts
								+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
						
					}
				}

			
			}
		}
	   
	   for (Entry<String, List<String>> entry : CustomerPropertyDetailReportSummaryData.entrySet()) {
			if (entry.getKey().equals("Total Stops")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualTotalstops = 0;
					} else {
						actualTotalstops = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
						Util.validateWholeNumber(actualTotalstops, "actual Total stops in Summary Section");
					}
				}
			}
		}
	   
	   for (Entry<String, List<String>> entry : CustomerPropertyDetailReportSummaryData.entrySet()) {
			if (entry.getKey().equals("Avg Threshold Time (h:m)")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualAvgThresholdTime = 0;
					} else {
						actualAvgThresholdTime = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
						Util.validateWholeNumber(actualAvgThresholdTime, "avg Threshold Time in Summary Section");
					}
				}
			}
		}
	   
	   
	   ExpectedThresholdTime=(actualNumberofLifts*4)/actualTotalstops;
	   
	   if (Util.compareNumber(actualAvgThresholdTime,ExpectedThresholdTime)) {
		Report.PassTestScreenshot(test, driver, "Validated Avg Threshold Time Actaul is " + actualAvgThresholdTime +" and Expected is " + ExpectedThresholdTime, "PassedAvgActual");
	}
	   else {
		   Report.FailTestSnapshot(test, driver, "Unable to Validate Avg Threshold Time Actaul is " + actualAvgThresholdTime +" and Expected is " + ExpectedThresholdTime, "FailedAvgActual");
	}
	   
   }
	  catch (Exception e) {
		Report.InfoTest(test, "Unable to Validate Avg Threshold Time Actaul is " + actualAvgThresholdTime +" and Expected is " + ExpectedThresholdTime);
	}
   }
   
   public void validateDate(Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab, String fromDate, String toDate,
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
			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
				if (entry.getKey().equals("Route")) {
					actualRoutes = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
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

   public void validateRouteName(Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab, String expectedRouteName,
			String subViewReportName) {
		String[] routes = expectedRouteName.split(";");
		List<String> actualRoutes = null;
		try {
			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
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
 
   public void validateDriverName(Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab, String expectedDriverName,
			String subViewReportName) {
		String[] drivers = expectedDriverName.split(";");
		List<String> actualDrivers = null;
		try {
			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
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

   public void validateLOB(Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab, String expectedLOB, String ReportName,String Subview) {
		String actualLOB = null;
		try {
			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
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

				Report.InfoTest(test, "LOB is correct in"+ ReportName + " report when Subview is "+Subview+" Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
				Report.PassTest(test, "LOB is as expected in "+ ReportName + " report");
			} else {
				Report.FailTest(test, "LOB is not as expected in "+ ReportName + " report report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
			}
		} catch (Exception e) {
			Report.FailTest(test, "LOB is not as expected in "+ ReportName + " report when Subview is "+Subview+ "Actual List is : " + actualLOB + " and Expected is : " + expectedLOB);
		}	
}

  private void validateSubLOB(Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab, String ReportName, String Subview) {
		List<String> actualSubLOB = new ArrayList<>();
		List<String> expectedSubLOB = new ArrayList<>();
		List<String> route = new ArrayList<>();

		try {
			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
				if (entry.getKey().equals("Sub LOB")) {
					actualSubLOB = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
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
							"Sub LOB is correct in "+ReportName+"report when Subview is "+Subview+" for Route : "
									+ route.get(i) + " with Actual SubLOB : " + actualSubLOB.get(0)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
					Report.PassTest(test, "Sub LOB is as expected in "+ReportName+" report");
				} else {
					Report.FailTest(test,
							"Sub LOB is not as expected in "+ReportName+" report for Route : "
									+ route.get(i) + " with Actual Sub LOB : " + actualSubLOB.get(i)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Sub LOB is not as expected in "+ReportName+" report Actual is : "
					+ actualSubLOB.toString() + " and Expected is : " + expectedSubLOB.toString());
		}
	
}

  private void validateRouteType(Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab, String ReportName, String Subview) {
		
		List<String> actualRouteType = new ArrayList<>();
		List<String> expectedRouteType = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		String expectedRouteTypes = "";
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");

		try {
			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
				if (entry.getKey().equals("Route Type")) {
					actualRouteType = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
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
							"Route Type  is correct in "+ReportName+" report When Subview is"+Subview+"for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type  : " + expectedRouteTypes);
					Report.PassTest(test, "Route Type is as expected in "+ReportName+" When Sub View is " + Subview);
				} else {
					Report.FailTest(test,
							"Route Type is not as expected in "+ReportName+" report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type : " + expectedRouteTypes);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test,
					"Route Type  is not as expected in "+ReportName+" report Actual is : "
							+ actualRouteType.toString() + " and Expected is : " + expectedRouteType.toString());
		}
	
}

  private void validateExceptionTimeHours(Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab,Map<String, Map<String, List<String>>> CustomerPropertyTimeDrillDownDataExceptionsTab, String TabName) {
	  List<String> route = new ArrayList<>();
	  List<String> actualExceptionTime=new ArrayList<>();
	  List<String> ExceptionTimeInDrillDown = new ArrayList<>();
	  int noOfExceptions=0;
	  String ExpectedExceprionTimeHrs=null;
	  try {
	  for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
			if (entry.getKey().equals("Route")) {
				route = entry.getValue();
			}
		}
		
	  
	  for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
			if (entry.getKey().equals("Exception Time (h:m)")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualExceptionTime.add("00:00");
					} else {
						actualExceptionTime.add(entry.getValue().get(i));
						Util.validateTimeFormat(entry.getValue().get(i), "Exception Time in Detail section");
					}
				}
			}
		}
	  for (int j=0;j<route.size();j++)
		{
		  
		  for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyTimeDrillDownDataExceptionsTab.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Exception Time (h:m)")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								ExceptionTimeInDrillDown.add("00:00");
							} else {
								ExceptionTimeInDrillDown.add(entry2.getValue().get(i).toString());
							}
						}
					}
				}
			}
		  for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyTimeDrillDownDataExceptionsTab.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Exception Time (h:m)")) {
						noOfExceptions=entry2.getValue().size();
						}
					}
				}
			
		  
		  
		  
		  List<Integer> ExceptionTimeInDrillDownMins=new ArrayList<>();
		  
		  for(int i=0;i<ExceptionTimeInDrillDown.size();i++)
			{
			  ExceptionTimeInDrillDownMins.add(Util.convertHoursToMins(ExceptionTimeInDrillDown.get(i)));
			}
		                  
		  
		  int expectedExceptionTime = 0;
			for (int i = 0; i < noOfExceptions; i++) {
				expectedExceptionTime =  expectedExceptionTime+	ExceptionTimeInDrillDownMins.get(i);
					
			}
			ExpectedExceprionTimeHrs=Util.convertMinsToHours(expectedExceptionTime);
			
		  if (Util.compareTime(actualExceptionTime.get(j), ExpectedExceprionTimeHrs)) {
			Report.PassTestScreenshot(test, driver, "Validated Exception Time (h:m) For "+TabName+" Route is " + route.get(j) + "Expected Exception time is" + ExpectedExceprionTimeHrs + "And Actual Exception time is " +actualExceptionTime.get(j), "PassedExceptionTime");
		}
		  else {
			  Report.FailTestSnapshot(test, driver, "unable to Validate Exception Time (h:m) For "+TabName+" Route is " + route.get(j) + "Expected Exception time is" + ExpectedExceprionTimeHrs + "And Actual Exception time is " +actualExceptionTime.get(j), "FailedExceptionTime");
		}
		}
  }
	  catch (Exception e) {
		Report.InfoTest(test, "unable to Validate Exception Time (h:m)");
	}
  }

  private void validateCustomerPropertyTimeHours(Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab,Map<String, Map<String, List<String>>> CustomerPropertyTimeDrillDownDataExceptionsTab, String TabName) {
	  List<String> route = new ArrayList<>();
	  List<String> actualCustomerPropertyTime=new ArrayList<>();
	  List<String> CustomerPropertyTimeInDrillDown = new ArrayList<>();
	  int noOfCustPropTime=0;
	  String ExpectedExceprionTimeHrs=null;
	  try {
	  for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
			if (entry.getKey().equals("Route")) {
				route = entry.getValue();
			}
		}
		
	  
	  for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
			if (entry.getKey().equals("Customer Property Time (h:m)")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualCustomerPropertyTime.add("00:00");
					} else {
						actualCustomerPropertyTime.add(entry.getValue().get(i));
						Util.validateTimeFormat(entry.getValue().get(i), "Customer Property Time in Detail section");
					}
				}
			}
		}
	  for (int j=0;j<route.size();j++)
		{
		  
		  for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyTimeDrillDownDataExceptionsTab.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Customer Property Time (h:m)")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								CustomerPropertyTimeInDrillDown.add("00:00");
							} else {
								CustomerPropertyTimeInDrillDown.add(entry2.getValue().get(i).toString());
							}
						}
					}
				}
			}
		  for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyTimeDrillDownDataExceptionsTab.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Customer Property Time (h:m)")) {
						noOfCustPropTime=entry2.getValue().size();
						}
					}
				}
			
		  
		  
		  
		  List<Integer> CustomerPropertyTimeInDrillDownMins=new ArrayList<>();
		  
		  for(int i=0;i<CustomerPropertyTimeInDrillDown.size();i++)
			{
			  CustomerPropertyTimeInDrillDownMins.add(Util.convertHoursToMins(CustomerPropertyTimeInDrillDown.get(i)));
			}
		                  
		  
		  int expectedExceptionTime = 0;
			for (int i = 0; i < noOfCustPropTime; i++) {
				expectedExceptionTime =  expectedExceptionTime+	CustomerPropertyTimeInDrillDownMins.get(i);
					
			}
			ExpectedExceprionTimeHrs=Util.convertMinsToHours(expectedExceptionTime);
			
		  if (Util.compareTime(actualCustomerPropertyTime.get(j), ExpectedExceprionTimeHrs)) {
			Report.PassTestScreenshot(test, driver, "Validated Customer Property Time (h:m) For "+TabName+" Route is " + route.get(j) + "Expected Exception time is" + ExpectedExceprionTimeHrs + "And Actual Exception time is " +actualCustomerPropertyTime.get(j), "PassedExceptionTime");
		}
		  else {
			  Report.FailTestSnapshot(test, driver, "unable to Validate Customer Property Time (h:m) For "+TabName+" Route is " + route.get(j) + "Expected Exception time is" + ExpectedExceprionTimeHrs + "And Actual Exception time is " +actualCustomerPropertyTime.get(j), "FailedExceptionTime");
		}
		}
  }
	  catch (Exception e) {
		Report.InfoTest(test, "unable to Validate Customer Property Time (h:m)");
	}
  }
	
  
  
 private void ValidateNumberOfLifts(Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab,Map<String, Map<String, List<String>>> CustomerPropertyTimeDrillDownDataExceptionsTab,String Subview ) {
	 List<String> route = new ArrayList<>();
	 List<Integer> actualNumberOfLifts=new ArrayList<>();
	 int expectedNumofLifts=0;
	 
	 try {
		 for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
		 for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
				if (entry.getKey().equals("# of Lifts")) {
					for (int i = 0; i < entry.getValue().size(); i++) {						
						actualNumberOfLifts.add(Integer.parseInt(entry.getValue().get(i)));
						Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)), "actual Number Of Lifts in Customer Property Detail Report");
					}
				}
			}
		 
		 for (int j=0;j<route.size();j++)
			{

			for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyTimeDrillDownDataExceptionsTab.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("# of Lifts")) {							
						expectedNumofLifts=entry2.getValue().size();							
					}
				}
			}
			if (actualNumberOfLifts.get(j)==expectedNumofLifts) {
				Report.PassTestScreenshot(test, driver, "Verified Number of Lifts when  Subview is" + Subview +"Actaul Number of lifts is " + actualNumberOfLifts.get(j)+ "Expected Number of Lifts is "+expectedNumofLifts + "For Route "+route.get(j) , "PassedNumberofLifts");
			}
			else {
				Report.FailTestSnapshot(test, driver, "Unable to Verify Number of Lifts when  Subview is" + Subview +"Actaul Number of lifts is " + actualNumberOfLifts.get(j)+ "Expected Number of Lifts is "+expectedNumofLifts + "For Route "+route.get(j) , "FailedNumberofLifts");
			}
			
			
			}
		 
		 
	} catch (Exception e) {
		Report.FailTest(test,"Unable to Verify Number of Lifts when  Subview is" + Subview +"Actaul Number of lifts is " + actualNumberOfLifts+ "Expected Number of Lifts is "+expectedNumofLifts );
	}
	 
	 
	 
	 
 } 

 private void ValidateTimePerLiftHours(Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab,Map<String, Map<String, List<String>>> CustomerPropertyTimeDrillDownDataExceptionsTab, String TabName) {
	 
	 List<String> route = new ArrayList<>();
	  List<String> actualTimePerLift=new ArrayList<>();
	  List<String> TimePerLiftsInDrillDown = new ArrayList<>();
	  int noOfTimePerLifts=0;
	  String ExpectedTimePerLiftsHours=null;
	  try {
	  for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
			if (entry.getKey().equals("Route")) {
				route = entry.getValue();
			}
		}
		
	  
	  for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
			if (entry.getKey().equals("Time/ Lift (h:m)")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualTimePerLift.add("00:00");
					} else {
						actualTimePerLift.add(entry.getValue().get(i));
						Util.validateTimeFormat(entry.getValue().get(i), "Time/ Lift (h:m) in Detail section");
					}
				}
			}
		}
	  for (int j=0;j<route.size();j++)
		{
		  
		  for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyTimeDrillDownDataExceptionsTab.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Time/ Lift (h:m)")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								TimePerLiftsInDrillDown.add("00:00");
							} else {
								TimePerLiftsInDrillDown.add(entry2.getValue().get(i).toString());
							}
						}
					}
				}
			}
		  for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyTimeDrillDownDataExceptionsTab.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Time/ Lift (h:m")) {
						noOfTimePerLifts=entry2.getValue().size();
						}
					}
				}
			
		  
		  
		  
		  List<Integer> TimePerLiftsInDrillDownMins=new ArrayList<>();
		  
		  for(int i=0;i<TimePerLiftsInDrillDown.size();i++)
			{
			  TimePerLiftsInDrillDownMins.add(Util.convertHoursToMins(TimePerLiftsInDrillDown.get(i)));
			}
		                  
		  
		  int expectedExceptionTime = 0;
			for (int i = 0; i < noOfTimePerLifts; i++) {
				expectedExceptionTime =  expectedExceptionTime+	TimePerLiftsInDrillDownMins.get(i);
					
			}
			ExpectedTimePerLiftsHours=Util.convertMinsToHours(expectedExceptionTime);
			
		  if (Util.compareTime(actualTimePerLift.get(j), ExpectedTimePerLiftsHours)) {
			Report.PassTestScreenshot(test, driver, "Validated Time/ Lift (h:m) For "+TabName+" Route is " + route.get(j) + "Expected Time Per Lift Hours  is" + ExpectedTimePerLiftsHours + "And Actual Time Per Lift Hours is " +actualTimePerLift.get(j), "PassedTimePerLiftHours");
		}
		  else {
			  Report.FailTestSnapshot(test, driver, "unable to Validate Time/ Lift (h:m) For "+TabName+" Route is " + route.get(j) + "Expected Time Per Lift Hours is" + ExpectedTimePerLiftsHours + "And Actual Time Per Lift Hours is " +actualTimePerLift.get(j), "FailedTimePerLiftHours");
		}
		}
 }
	  catch (Exception e) {
		Report.InfoTest(test, "unable to Validate Time/ Lift (h:m)");
	}
 }

 
 private void ValidateTotalUnits(Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab,Map<String, Map<String, List<String>>> CustomerPropertyTimeDrillDownDataExceptionsTab,String Subview ) {
	 List<String> route = new ArrayList<>();
	 List<Integer> actualtotalUnits=new ArrayList<>();
	 int expectedTotalUnits=0;
	 
	 try {
		 for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
		 for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
				if (entry.getKey().equals("Total Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {						
						actualtotalUnits.add(Integer.parseInt(entry.getValue().get(i)));
						Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)), "actual Number Of Units in Customer Property Detail Report");
					}
				}
			}
		 
		 for (int j=0;j<route.size();j++)
			{

			for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyTimeDrillDownDataExceptionsTab.entrySet()) {
				if(entry.getKey().equals(route.get(j)))
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Total Units")) {							
						expectedTotalUnits=entry2.getValue().size();							
					}
				}
			}
			if (actualtotalUnits.get(j)==expectedTotalUnits) {
				Report.PassTestScreenshot(test, driver, "Verified Total Units when  Subview is" + Subview +"Actaul Number of Units is " + actualtotalUnits.get(j)+ "Expected Total Units is "+expectedTotalUnits + "For Route "+route.get(j) , "PassedNumberofUnits");
			}
			else {
				Report.FailTestSnapshot(test, driver, "Unable to Verify Total Units when  Subview is" + Subview +"Actaul Number of Units is " + actualtotalUnits.get(j)+ "Expected Total Units is "+expectedTotalUnits + "For Route "+route.get(j) , "FailedNumberofUnits");
			}
			
			
			}
		 
		 
	} catch (Exception e) {
		Report.FailTest(test,"Unable to Verify Total Units when  Subview is" + Subview +"Actaul Number of Units is " + actualtotalUnits+ "Expected Total Units is "+expectedTotalUnits );
	}
	 
	 
	 
	 
 } 

 
 private void validateDrilldownMealtime(Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab,Map<String, Map<String, List<String>>> CustomerPropertyTimeDrillDownDataExceptionsTab, String Subview) {
		
		List<String> actualIdleMealTime = new ArrayList<>();
		List<String> expectedIdleMealTimeStart = new ArrayList<>();
		List<String> expectedIdleMealTimeEnd = new ArrayList<>();
		String expectedIdleMealTime =null;			
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		List<String> date = new ArrayList<>();
		List<String> route = new ArrayList<>();
		int numOfMealTimeOccurances=0;
		try {
			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			
			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}


			 
			
			for (int j=0;j<route.size();j++)
			{
				for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyTimeDrillDownDataExceptionsTab.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Meal (h:m))")) {
							numOfMealTimeOccurances=entry2.getValue().size();
							}
						}
					}
				
				
				for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyTimeDrillDownDataExceptionsTab.entrySet()) {
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
			

			for (int i = 0; i < numOfMealTimeOccurances; i++) {
			
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
						"Idle Meal Time is correct in drill down of Customer Property report for Route : "+route.get(j)+". Actual is : "
								+ actualIdleMealTime.get(i) + " and Expected is : "
								+ expectedIdleMealTime + "When Subview is " + Subview);
				Report.PassTest(test, "Idle Meal Time is as expected in drill down of Customer Propertyreport for Route : "+route.get(j));
			} else {
				Report.FailTest(test, "Idle Meal Time is not as expected in drill down of Customer Property report for Route : "+route.get(j)+". Actual is : "
						+ actualIdleMealTime.get(i) + " and Expected is : " + expectedIdleMealTime+ "When Subview is " + Subview);
			}
		} 
	}
	
	}catch (Exception e) {
			Report.FailTest(test, "Idle Meal Time is not as expected in drill down of Customer Property report Actual is : "
					+ actualIdleMealTime + " and Expected is : " + expectedIdleMealTime + "When Subview is" + Subview);
		}
		
	
}

 private void validateDrilldownDowntime(Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab,Map<String, Map<String, List<String>>> CustomerPropertyTimeDrillDownDataExceptionsTab, String Subview) {
		List<String> actualIdleDownTime = new ArrayList<>();
		List<String> expectedIdleDownTimeStart = new ArrayList<>();
		List<String> expectedIdleDownTimeEnd = new ArrayList<>();
		String expectedIdleDownTime =null;			
		List<Integer> noOfIdleOccurences=new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		List<String> date = new ArrayList<>();
		List<String> route = new ArrayList<>();
		int numofDowntimeOccurances=0;
		try {
			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			
			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			
			
			
			for (int j=0;j<route.size();j++)
			{
				for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyTimeDrillDownDataExceptionsTab.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Downtime (h:m)")) {
							numofDowntimeOccurances=entry2.getValue().size();
							}
						}
					}
			
				for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyTimeDrillDownDataExceptionsTab.entrySet()) {
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
			

			for (int i = 0; i < numofDowntimeOccurances; i++) {
			
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
						"Idle Down Time is correct in drill down of DownTime report for Route : "+route.get(j)+". Actual is : "
								+ actualIdleDownTime.get(i) + " and Expected is : "
								+ expectedIdleDownTime + "When Subview is" + Subview);
				Report.PassTest(test, "Idle Down Time is as expected in drill down of DownTime report for Route : "+route.get(j));
			} else {
				Report.FailTest(test, "Idle Down Time is not as expected in drill down of DownTime report for Route : "+route.get(j)+". Actual is : "
						+ actualIdleDownTime.get(i) + " and Expected is : " + expectedIdleDownTime + "When Subview is " + Subview);
			}
		} 
	}
	
	}catch (Exception e) {
			Report.FailTest(test, "Idle Down Time is not as expected in drill down of DownTime report Actual is : "
					+ actualIdleDownTime + " and Expected is : " + expectedIdleDownTime + "When Subview is" + Subview);
		}
		
	
}

 private void validateDrilldownCustID(Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab,
			Map<String, Map<String, List<String>>> CustomerPropertyTimeDrillDownDataExceptionsTab, String Subview) {

		// Cust ID  = Customer ID in OCS.
		
				
				List<String> route = new ArrayList<>();
				List<String> date = new ArrayList<>();
				SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
				SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
			
				try {
					for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
						if (entry.getKey().equals("Route")) {
							route = entry.getValue();
						}
					}
					
					for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
						if (entry.getKey().contains("Date")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								Date dates = format1.parse(entry.getValue().get(i));
								date.add(format2.format(dates));
							}
						}
					}
					
					
					
					for (int j=0;j<route.size();j++)
					{
						List<String> actualCustomerID = new ArrayList<>();
						List<String> expectedCustomerID = new ArrayList<>();
					
						for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyTimeDrillDownDataExceptionsTab.entrySet()) {
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
								"Cust ID is correct in drill down of Customer Property Time in Customer Property Detail Report for Route : "+route.get(j)+". Actual is : "
										+ actualCustomerID + " and Expected is : "
										+ expectedCustomerID + "When Subview is " + Subview);
						Report.PassTest(test, "Cust ID is as expected in drill down of Customer Property Time in Customer Property Detail Report for Route : "+route.get(j));
					} else {
						Report.FailTest(test, "Cust ID is not as expected in drill down of Customer Property Time in Customer Property Detail Report for Route : "+route.get(j)+". Actual is : "
								+ actualCustomerID + " and Expected is : " + expectedCustomerID + "When Subview is" + Subview);
					}
			 
					}
			}catch (Exception e) {
					Report.FailTest(test, "Cust ID is not as expected in drill down of Customer Property Time in Customer Property Detail Report.When Subview is" + Subview);
				}
		
	}

 private void validateDrilldownCustName(Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab,
			Map<String, Map<String, List<String>>> CustomerPropertyTimeDrillDownDataExceptionsTab, String Subview) {
		// Cust Name  = Customer Name in OCS.
		
		
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
	
		try {
			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			
			for (Entry<String, List<String>> entry : CustomerPropertyDetailReportDetailDataExceptionsTab.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}
			
			
			
			for (int j=0;j<route.size();j++)
			{
				List<String> actualCustomerName = new ArrayList<>();
				List<String> expectedCustomerName = new ArrayList<>();
			
				for (Entry<String, Map<String, List<String>>> entry : CustomerPropertyTimeDrillDownDataExceptionsTab.entrySet()) {
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
						"Cust Name is correct in drill down of Customer Property Time in Customer Property Detail Report for Route : "+route.get(j)+". Actual is : "
								+ actualCustomerName + " and Expected is : "
								+ expectedCustomerName + "When Subview is" +Subview);
				Report.PassTest(test, "Cust Name is as expected in drill down of TCustomer Property Time in Customer Property Detail report for Route : "+route.get(j));
			} else {
				Report.FailTest(test, "Cust Name is not as expected in drill down of Customer Property Time in Customer Property Detail report for Route : "+route.get(j)+". Actual is : "
						+ actualCustomerName + " and Expected is : " + expectedCustomerName + "When Subview is" + Subview);
			}
	 
			}
	}catch (Exception e) {
			Report.FailTest(test, "Cust Name is not as expected in drill down of Customer Property Time in Customer Property Detail report.When Subview is " + Subview);
		}
		
	}

 
public void ValidateCustomerPropertyDetailReport_DetailTabledataExceptionTab(Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab,Map<String, Map<String, List<String>>> CustomerPropertyTimeDrillDownDataExceptionsTab,String fromDate,String toDate,String expectedRouteName,String expectedDriverName,String expectedLOB ) {
	validateDate(CustomerPropertyDetailReportDetailDataExceptionsTab, fromDate, toDate, "Exceptions");
	validateRouteName(CustomerPropertyDetailReportDetailDataExceptionsTab, expectedRouteName, "Exceptions");
	validateDriverName(CustomerPropertyDetailReportDetailDataExceptionsTab, expectedDriverName, "Exceptions");
	validateExceptionTimeHours(CustomerPropertyDetailReportDetailDataExceptionsTab, CustomerPropertyTimeDrillDownDataExceptionsTab, "Exceptions");
	validateCustomerPropertyTimeHours(CustomerPropertyDetailReportDetailDataExceptionsTab, CustomerPropertyTimeDrillDownDataExceptionsTab, "Exceptions");
	ValidateNumberOfLifts(CustomerPropertyDetailReportDetailDataExceptionsTab, CustomerPropertyTimeDrillDownDataExceptionsTab, "Exceptions");
	ValidateTimePerLiftHours(CustomerPropertyDetailReportDetailDataExceptionsTab, CustomerPropertyTimeDrillDownDataExceptionsTab, "Exceptions");
	ValidateTotalUnits(CustomerPropertyDetailReportDetailDataExceptionsTab, CustomerPropertyTimeDrillDownDataExceptionsTab, "Exceptions");
	validateLOB(CustomerPropertyDetailReportDetailDataExceptionsTab, expectedLOB, "Customer Property Detail", "Exceptions");
	validateSubLOB(CustomerPropertyDetailReportDetailDataExceptionsTab, "Customer Property Detail", "Exceptions");
	validateRouteType(CustomerPropertyDetailReportDetailDataExceptionsTab, "Customer Property Detail", "Exceptions");
}
 
public void ValidateCustomerPropertyDetailReport_DetailTabledataAllTab(Map<String, List<String>> CustomerPropertyDetailReportDetailDataAllTab,Map<String, Map<String, List<String>>> CustomerPropertyTimeDrillDownDataAllTab,String fromDate,String toDate,String expectedRouteName,String expectedDriverName,String expectedLOB ) {
	validateDate(CustomerPropertyDetailReportDetailDataAllTab, fromDate, toDate, "All");
	validateRouteName(CustomerPropertyDetailReportDetailDataAllTab, expectedRouteName, "All");
	validateDriverName(CustomerPropertyDetailReportDetailDataAllTab, expectedDriverName, "All");
	validateExceptionTimeHours(CustomerPropertyDetailReportDetailDataAllTab, CustomerPropertyTimeDrillDownDataAllTab, "All");
	validateCustomerPropertyTimeHours(CustomerPropertyDetailReportDetailDataAllTab, CustomerPropertyTimeDrillDownDataAllTab, "All");
	ValidateNumberOfLifts(CustomerPropertyDetailReportDetailDataAllTab, CustomerPropertyTimeDrillDownDataAllTab, "All");
	ValidateTimePerLiftHours(CustomerPropertyDetailReportDetailDataAllTab, CustomerPropertyTimeDrillDownDataAllTab, "All");
	ValidateTotalUnits(CustomerPropertyDetailReportDetailDataAllTab, CustomerPropertyTimeDrillDownDataAllTab, "All");
	validateLOB(CustomerPropertyDetailReportDetailDataAllTab, expectedLOB, "Customer Property Detail", "All");
	validateSubLOB(CustomerPropertyDetailReportDetailDataAllTab, "Customer Property Detail", "All");
	validateRouteType(CustomerPropertyDetailReportDetailDataAllTab, "Customer Property Detail", "All");
}
 
public void ValidateCustomerPropertyDetailReport_DrillDownDataExceptionTab(Map<String, List<String>> CustomerPropertyDetailReportDetailDataExceptionsTab,Map<String, Map<String, List<String>>> CustomerPropertyTimeDrillDownDataExceptionsTab) {
	validateDrilldownMealtime(CustomerPropertyDetailReportDetailDataExceptionsTab, CustomerPropertyTimeDrillDownDataExceptionsTab, "Exceptions");
	validateDrilldownDowntime(CustomerPropertyDetailReportDetailDataExceptionsTab, CustomerPropertyTimeDrillDownDataExceptionsTab, "Exceptions");
	validateDrilldownCustID(CustomerPropertyDetailReportDetailDataExceptionsTab, CustomerPropertyTimeDrillDownDataExceptionsTab, "Exceptions");
	validateDrilldownCustName(CustomerPropertyDetailReportDetailDataExceptionsTab, CustomerPropertyTimeDrillDownDataExceptionsTab, "Exceptions");
	
}

public void ValidateCustomerPropertyDetailReport_DrillDownDataAllTab(Map<String, List<String>>CustomerPropertyDetailReportDetailDataAllTab,Map<String, Map<String, List<String>>>CustomerPropertyTimeDrillDownDataAllTab) {

	validateDrilldownMealtime(CustomerPropertyDetailReportDetailDataAllTab, CustomerPropertyTimeDrillDownDataAllTab, "All");
	validateDrilldownDowntime(CustomerPropertyDetailReportDetailDataAllTab, CustomerPropertyTimeDrillDownDataAllTab, "All");
	validateDrilldownCustID(CustomerPropertyDetailReportDetailDataAllTab, CustomerPropertyTimeDrillDownDataAllTab, "All");
	validateDrilldownCustName(CustomerPropertyDetailReportDetailDataAllTab, CustomerPropertyTimeDrillDownDataAllTab, "All");
		
	
	
	
} 
 }
 
  
  


	
