package ObjectRepository.Logi;

import java.io.IOException;
import java.text.ParseException;
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
import SupportLibraries.Report;
import SupportLibraries.Util;

public class PostRouteDetailReport {
	
	ExtentTest test;
	WebDriver driver;

	public PostRouteDetailReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//table[@id='dtPostRouteSummary']/thead/tr/th")
	List<WebElement> postRouteSummaryTableColumns;

	@FindBy(xpath = "//table[@id='dtPostRouteSummary']/tbody/tr")
	List<WebElement> postRouteSummaryTableRows;
	
	@FindBy(xpath="//*[@id='inpSubReportOptEx_2']")
	WebElement ActualSubview;
	
	@FindBy(xpath=".//*[@id='tabAll']/a/em/span")
	WebElement AllTab;
	
	
	@FindBy(xpath="//*[@id='inpSubReportOpt_2']")
	WebElement ActualSubviewAllTab;
	
	//PostRoteSummaryTableColumn
	@FindBy(xpath="//*[@id='dtPostRouteSummary']/thead/tr/th")
	List<WebElement> PostRoteSummaryTableColumn;
	
	//PostRouteSummaryReportDetailColumn
	@FindBy(xpath="//*[@id='dtPostRouteDetail']/thead/tr/th")
	List<WebElement> PostRouteSummaryReportDetailColumn;
	
	//PostRouteSummaryTableRow
	@FindBy(xpath="//*[@id='dtPostRouteSummary']/tbody/tr")
	List<WebElement> PostRouteSummaryTableRow;
	
	//PostRouteSummaryReportDetailRow
	@FindBy(xpath="//*[@id='dtPostRouteDetail']/tbody/tr")
	List<WebElement> PostRouteSummaryReportDetailRow;
	
	
	@FindBy(xpath = "//*[@id='t2e']/thead/tr/th")
	List<WebElement> PostRouteDetailTableColumnExceptionTab;
	
	@FindBy(xpath = "//*[@id='t2']/thead/tr/th")
	List<WebElement> PostRouteDetailTableColumnAllTab;
	
	@FindBy(xpath = "//*[@id='t2e']/tbody/tr")
	List<WebElement> PostRouteDetailTableRowExceptionTab;
	
	@FindBy(xpath="//*[@id='t2']/tbody/tr")
	List<WebElement> PostRouteDetailTableRowAllTab;
	
	//ExceptionTabActualSubView
	@FindBy(xpath="//*[@id='inpSubReportOptEx_2']")
	WebElement ExceptionTabActualSubView;
	
	//ExceptionTabPerformanceSubview
	@FindBy(xpath="//*[@id='inpSubReportOptEx_1']")
	WebElement ExceptionTabPerformanceSubview;
	
	//AllTabActualSubview
	@FindBy(xpath="//*[@id='inpSubReportOpt_2']")
	WebElement AllTabActualSubview;
	
	//AllTabPerformnceSubview
	@FindBy(xpath="//*[@id='inpSubReportOpt_1']")
	WebElement AllTabPerformnceSubview;
	
	//AllTab
	@FindBy(xpath="//*[@id='tabAll']/a/em/span")
	WebElement AllTabBtn;
	
	//ExceptionTabBtn
	@FindBy(xpath="//*[@id='tabExceptions']/a/em/span")
	WebElement ExceptionTabBtn;
	
	//NumberofRows
	@FindBy(xpath="//*[@id='t2e']/tbody/tr")
	List<WebElement> NumberofRows;
	
	//TotalPostRouteWorkException
	@FindBy(xpath="//*[@id='dtPostRouteSummary']/tbody/tr/td[contains(@id,'colNumPreRteExcp_Row')]/span")
	WebElement TotalPostRouteWorkException;
	
	//TotalNumberofPostRoutesinSummary
	@FindBy(xpath="//*[@id='dtPostRouteSummary']/tbody/tr/td[contains(@id,'NumPreRte_')]/span")
	WebElement TotalNumberofPostRoutesinSummary;
	
	//GetActualExceptiontime
	@FindBy(xpath="//*[@id='dtPostRouteSummary']/tbody/tr/td[contains(@id,'colExcpTimePct')]/span")
	WebElement GetActualExceptiontime;
	
	//ExpectedActualHours
	@FindBy(xpath="//*[@id='t2']/tbody/tr/td[contains(@id,'colActTm')]/span")
	List<WebElement> ExpectedActualHours;
	
	//TotalNumberOfPlanHours
	@FindBy(xpath="//*[@id='t2']/tbody/tr/td[contains(@id,'colPlanTm_')]/span")
	List<WebElement> TotalNumberofPlanHoursRows;
	
	//GetTotalPostRouteCountFormUI
	@FindBy(xpath="//*[@id='dtPostRouteSummary']/tbody/tr/td[contains(@id,'colNumPreRte_Row')]/span")
    WebElement GetTotalPostRouteCountFormUI;  	


	public Map<String, List<String>> getActualPostRouteSummaryTableData() throws IOException {
		Map<String, List<String>> postRouteSummaryTableData = new HashMap<>();
		try {
			postRouteSummaryTableData = readTable(postRouteSummaryTableColumns, postRouteSummaryTableRows, "dtPostRouteSummary");
			Report.PassTestScreenshot(test, driver, "Post Route Summary table data read successfully", "Post Route detail report");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Post Route summary table data");
		}

		for (Entry<String, List<String>> entry : postRouteSummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return postRouteSummaryTableData;
	}
	
	public Map<String, List<String>> readTable(List<WebElement> columns, List<WebElement> rows, String tableName) {

		Map<String, List<String>> objTable = new HashMap<>();
		System.out.println("Number of cloumns : " + columns.size());
		System.out.println("Number of rows : " + rows.size());
		for (int iCount = 1; iCount <= columns.size(); iCount++) {
			// List<WebElement> objCol =
			// rows.get(iCount).findElements(By.cssSelector("td.tableTxt"));
			List<String> rowValues = new ArrayList<>();
			for (int row = 1; row <= rows.size(); row++) {
				try {
					rowValues.add(driver
							.findElement(By.xpath(
									"//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + iCount + "]/span"))
							.getText());
				} catch (Exception e) {
					rowValues.add(driver
							.findElement(By
									.xpath("//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + iCount + "]"))
							.getText());
				}
			}
			objTable.put(columns.get(iCount - 1).getText(), rowValues);
		}
		return objTable;

	}
	//
	   public void selectActualSubView() throws IOException {
				try {
					ActualSubview.click();
					Report.PassTest(test, "Actual SubView get selected as expected");
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
				}
			}
	   
	   public void selectAllTab() throws IOException {
			try {

				AllTab.click();
				Thread.sleep(7000);
				Report.PassTest(test, "All Tab get selected as expected");
			} catch (Exception e) {
				Report.FailTest(test, e.getMessage());
			}
		}
	 //*[@id='inpSubReportOpt_2']
	   public void selectActualSubviewAllTab() throws IOException {
				try {

					ActualSubviewAllTab.click();
					Thread.sleep(7000);
					Report.PassTest(test, "All Tab get selected as expected");
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
				}
			}

	   public Map<String, List<String>> getPostRouteSummaryTableDetailData() throws IOException {
				Map<String, List<String>> PostRouteSummaryTableDetailData = new HashMap<>();
				try {
					PostRouteSummaryTableDetailData = readTable(PostRouteSummaryReportDetailColumn, PostRouteSummaryReportDetailRow,
							"dtPostRouteDetail");
					Report.PassTestScreenshot(test, driver, "Post RouteSummaryReport Detail table data read successfully",
							"Pre Route Detail report");
				} catch (Exception e) {
					Report.FailTest(test, "Not able to read PostRoutesummaryReport Detail table data");
				}

				for (Entry<String, List<String>> entry : PostRouteSummaryTableDetailData.entrySet()) {
					System.out.println(entry.getKey() + ":" + entry.getValue().toString());
				}
				return PostRouteSummaryTableDetailData;
			}
	   
	   
	   
	   
	   public Map<String, List<String>> getPostRouteSummaryTableData() throws IOException {
			Map<String, List<String>> preRouteSummaryTableData = new HashMap<>();
			try {
				preRouteSummaryTableData = readTable(PostRoteSummaryTableColumn, PostRouteSummaryTableRow,
						"dtPostRouteSummary");
				Report.PassTestScreenshot(test, driver, "Post Route Summary table data read successfully",
						"Pre Route Detail report");
			} catch (Exception e) {
				Report.FailTest(test, "Not able to read Post Route summary table data");
			}

			for (Entry<String, List<String>> entry : preRouteSummaryTableData.entrySet()) {
				System.out.println(entry.getKey() + ":" + entry.getValue().toString());
			}
			return preRouteSummaryTableData;
		}
	   
	   
	   
	   public Map<String, List<String>> getPostRouteDetailTableDataExceptionTab() throws IOException {
			Map<String, List<String>> PostRouteDetailTableDataExceptionTab = new HashMap<>();
			try {
				PostRouteDetailTableDataExceptionTab = readTable(PostRouteDetailTableColumnExceptionTab, PostRouteDetailTableRowExceptionTab,
						"t2e");
				Report.PassTestScreenshot(test, driver, "Post Route Detail table Exception Tab data read successfully",
						"Pre Route Detail report");
			} catch (Exception e) {
				Report.FailTest(test, "Not able to read Post Route Detail table Exception Tab   data");
			}

			for (Entry<String, List<String>> entry : PostRouteDetailTableDataExceptionTab.entrySet()) {
				System.out.println(entry.getKey() + ":" + entry.getValue().toString());
			}
			return PostRouteDetailTableDataExceptionTab;
		}
	   
	   public Map<String, List<String>> getPostRouteDetailTableDataExceptionTabActualSubView() throws IOException {
			Map<String, List<String>> PostRouteDetailTableDataExceptionTabActualSubView = new HashMap<>();
			try {
				ExceptionTabActualSubView.click();
				Thread.sleep(2000);
				PostRouteDetailTableDataExceptionTabActualSubView = readTable(PostRouteDetailTableColumnExceptionTab, PostRouteDetailTableRowExceptionTab,
						"t2e");
				Report.PassTestScreenshot(test, driver, "Post Route Detail table Exception Tab data read successfully",
						"Pre Route Detail report");
			} catch (Exception e) {
				Report.FailTest(test, "Not able to read Post Route Detail table Exception Tab   data");
			}
			ExceptionTabPerformanceSubview.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			for (Entry<String, List<String>> entry : PostRouteDetailTableDataExceptionTabActualSubView.entrySet()) {
				System.out.println(entry.getKey() + ":" + entry.getValue().toString());
			}
			return PostRouteDetailTableDataExceptionTabActualSubView;
			
		}
	   
	   
	   
	   public Map<String, List<String>> getPostRouteDetailTableDataAllTab() throws IOException {
			Map<String, List<String>> PostRouteDetailTableDataAllTab = new HashMap<>();
			try {
				PostRouteDetailTableDataAllTab = readTable(PostRouteDetailTableColumnAllTab, PostRouteDetailTableRowAllTab,"t2");
				Report.PassTestScreenshot(test, driver, "Post Route Detail table All Tab data read successfully",
						"Pre Route Detail report");
			} catch (Exception e) {
				Report.FailTest(test, "Not able to read Post Route Detail table All Tab  data");
			}

			for (Entry<String, List<String>> entry : PostRouteDetailTableDataAllTab.entrySet()) {
				System.out.println(entry.getKey() + ":" + entry.getValue().toString());
			}
			return PostRouteDetailTableDataAllTab;
		}
	   
	   
	   public Map<String, List<String>> getPostRouteDetailTableDataAllTabActualSubview() throws IOException {
			Map<String, List<String>> PostRouteDetailTableDataAllTabActualSubview = new HashMap<>();
			try {
				AllTabActualSubview.click();
				Thread.sleep(2000);
				PostRouteDetailTableDataAllTabActualSubview = readTable(PostRouteDetailTableColumnAllTab, PostRouteDetailTableRowAllTab,
						"t2");
				Report.PassTestScreenshot(test, driver, "Post Route Detail table All Tab data read successfully",
						"Pre Route Detail report");
			} catch (Exception e) {
				Report.FailTest(test, "Not able to read Post Route Detail table All Tab   data");
			}
			AllTabPerformnceSubview.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Entry<String, List<String>> entry : PostRouteDetailTableDataAllTabActualSubview.entrySet()) {
				System.out.println(entry.getKey() + ":" + entry.getValue().toString());
			}
			return PostRouteDetailTableDataAllTabActualSubview;
		}
	   
	   public void RedirectedtoAllTab() {
		   try {
			   AllTabBtn.click();
			   Thread.sleep(1000);
			   Report.PassTest(test, "Redirected to All Tab");
		} catch (Exception e) {
			Report.FailTest(test, "Unable to Redirect to All Tab");
		}
		   
	   }
	   
	   public void RedirectedtoExceptionTab() {
		   
		   try {
			   ExceptionTabBtn.click();
			   Thread.sleep(200);
			   Report.PassTest(test, "Redirected to Exception Tab");
			   
		} catch (Exception e) {
			Report.FailTest(test, "Unable to Redirect to Exception Tab");
		}
		   
	   }
	 
	   
	   public void ValidateExceptionTabPerformanceSubviewData(Map<String, List<String>> getPostRouteDetailTableDataExceptionTab, String fromDate, String toDate,String expectedRouteName,String expectedDriverName,String LOB) {
		   validateDate(getPostRouteDetailTableDataExceptionTab, fromDate, toDate, "Performance");
		   validateRouteName(getPostRouteDetailTableDataExceptionTab, expectedRouteName, "Performance");
		   validateDriverName(getPostRouteDetailTableDataExceptionTab, expectedDriverName, "Performance");
		   validateExceptionTimeHours(getPostRouteDetailTableDataExceptionTab, "Performance");
		   validatePlanHours(getPostRouteDetailTableDataExceptionTab, LOB, "Performance");
		   validateLOB(getPostRouteDetailTableDataExceptionTab, LOB, "Performance");
		   validateSubLOB(getPostRouteDetailTableDataExceptionTab); 
		   validateRouteType(getPostRouteDetailTableDataExceptionTab);
		   ValidateActualHours(getPostRouteDetailTableDataExceptionTab);
		   
	   }
	   
	   public void ValidateExceptionTabActualSubviewData(Map<String, List<String>> PostRouteDetailTableDataExceptionTabActualSubview, String fromDate, String toDate,String expectedRouteName,String expectedDriverName,String LOB,String expectedClockoutTime) {
		   validateDate(PostRouteDetailTableDataExceptionTabActualSubview, fromDate, toDate, "Actual");
		   validateRouteName(PostRouteDetailTableDataExceptionTabActualSubview, expectedRouteName, "Actual");
		   validateDriverName(PostRouteDetailTableDataExceptionTabActualSubview, expectedDriverName, "Actual");
		   validateLOB(PostRouteDetailTableDataExceptionTabActualSubview, LOB, "Actual");
		   validateSubLOB(PostRouteDetailTableDataExceptionTabActualSubview); 
		   validateRouteType(PostRouteDetailTableDataExceptionTabActualSubview);
		   
		   //validateArriveYard
		   validatedClockOutTime(PostRouteDetailTableDataExceptionTabActualSubview, expectedClockoutTime);
		   
		   
		   
		   
	   }
	   
	   
	   public void ValidateAllTabPerformanceSubViewData(Map<String, List<String>> PostRouteDetailTableDataAllTab, String fromDate, String toDate,String expectedRouteName,String expectedDriverName,String LOB) {
		   
		   validateDate(PostRouteDetailTableDataAllTab, fromDate, toDate, "Performance");
		   validateRouteName(PostRouteDetailTableDataAllTab, expectedRouteName, "Performance");
		   validateDriverName(PostRouteDetailTableDataAllTab, expectedDriverName, "Performance");
		   validateExceptionTimeHours(PostRouteDetailTableDataAllTab, "Performance");
		   validatePlanHours(PostRouteDetailTableDataAllTab, LOB, "Performance");
		   validateLOB(PostRouteDetailTableDataAllTab, LOB, "Performance");
		   validateSubLOB(PostRouteDetailTableDataAllTab); 
		   validateRouteType(PostRouteDetailTableDataAllTab);
		   ValidateActualHours(PostRouteDetailTableDataAllTab);  
		   
		   
		   
	   }
	   
	   public void ValidateAllTabActualSubViewData(Map<String, List<String>> PostRouteDetailTableDataAllTabActualSubview, String fromDate, String toDate,String expectedRouteName,String expectedDriverName,String LOB,String expectedClockoutTime) {
		   validateDate(PostRouteDetailTableDataAllTabActualSubview, fromDate, toDate, "Actual");
		   validateRouteName(PostRouteDetailTableDataAllTabActualSubview, expectedRouteName, "Actual");
		   validateDriverName(PostRouteDetailTableDataAllTabActualSubview, expectedDriverName, "Actual");
		   validateLOB(PostRouteDetailTableDataAllTabActualSubview, LOB, "Actual");
		   validateSubLOB(PostRouteDetailTableDataAllTabActualSubview); 
		   validateRouteType(PostRouteDetailTableDataAllTabActualSubview);
		   
		   //validateArriveYard
		   validatedClockOutTime(PostRouteDetailTableDataAllTabActualSubview, expectedClockoutTime);  
		   
		   
		   
	   }
	   
	   
	   
	   public void validateArriveYard(Map<String, List<String>> PostRouteDetailTableDataExceptionTabActualSubview) throws ParseException {
		   
			List<Date> arriveYardTime = new ArrayList<>();
			List<String> route = new ArrayList<>();
			List<String> date = new ArrayList<>();
			SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
			
			
			for (Entry<String, List<String>> entry : PostRouteDetailTableDataExceptionTabActualSubview.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : PostRouteDetailTableDataExceptionTabActualSubview.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}
		   		   
		   arriveYardTime = Util.getDateDataFromDB(
					"select TO_CHAR(TOYARDSTAMP - (6 / 24) , 'HH:MI') from OCS_ADMIN.TP_RO_RESULT where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID  IN  ("
							+ Util.sqlFormatedList(route) + ")) and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE  IN ("
							+ Util.sqlFormatedList(date)
							+ ") and OCS_ADMIN.TP_ROUTEORDER.STATUS_CODE='CONFIRMED') and FK_VEHICLE IS NOT NULL");
		   
		   
		   
		   
	   } 
	   
	   
	   public void validatedClockOutTime(Map<String, List<String>> PostRouteDetailTableDataExceptionTabActualSubview,
				String expectedClockoutTime) {
			List<String> actualClockoutTime = null;
			try {
				for (Entry<String, List<String>> entry : PostRouteDetailTableDataExceptionTabActualSubview.entrySet()) {
					if (entry.getKey().equals("Clock Out")) {
						actualClockoutTime = entry.getValue();
					}
				}

				if (actualClockoutTime.contains(expectedClockoutTime)) {
					Report.InfoTest(test,
							"Clock Out Time is correct in Actual sub view report Actual List is : "
									+ actualClockoutTime.toString() + " and Expected is : " + expectedClockoutTime);
					Report.PassTest(test, "Clock out Time is as expected in Actual sub view report");
				} else {
					Report.FailTest(test,
							"Clock Out Time is not as expected in Actual sub view report Actual List is : " + actualClockoutTime.toString()
									+ " and Expected is : " + expectedClockoutTime);
				}

			} catch (Exception e) {
				Report.FailTest(test,
						"Clock Out Time is not as expected in Actual sub view report Actual is : "+ actualClockoutTime.toString() + " and Expected is : " + expectedClockoutTime);
			}

		}
		
	   
	   
	   
	   public void validateDate(Map<String, List<String>> getPostRouteDetailTableDataExceptionTab, String fromDate, String toDate,
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
				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
					if (entry.getKey().equals("Route")) {
						actualRoutes = entry.getValue();
					}
				}

				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
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
   
	   public void validateRouteName(Map<String, List<String>> getPostRouteDetailTableDataExceptionTab, String expectedRouteName,
				String subViewReportName) {
			String[] routes = expectedRouteName.split(";");
			List<String> actualRoutes = null;
			try {
				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
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
	   
	   public void validateDriverName(Map<String, List<String>> getPostRouteDetailTableDataExceptionTab, String expectedDriverName,
				String subViewReportName) {
			String[] drivers = expectedDriverName.split(";");
			List<String> actualDrivers = null;
			try {
				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
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
	   
	   public void validateExceptionTimeHours(Map<String, List<String>> getPostRouteDetailTableDataExceptionTab,String subViewReportName) {
			/*If Actual or Plan is Null, Excp Time is NULL
			If Actual or Plan <0, Excp Time is NULL
			If Actual <= Plan, the Excp Time = 0
			Else Excp Time = Actual - Planhh:mm*/
			
			int actualhours=0;
			int planhours=0;
			int exceptionhours=0;
			
			try {	
				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
				if (entry.getKey().contains("Actual (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							actualhours += mins;
						}

					}
				}
			}
				
				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
					if (entry.getKey().contains("Plan (h:m)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (!entry.getValue().get(i).equals("--")) {
								String[] split = entry.getValue().get(i).split(":", 2);
								int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
								planhours += mins;
							}

						}
					}
				}
				
				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
					if (entry.getKey().contains("Exception Time (h:m)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (!entry.getValue().get(i).equals("--")) {
								String[] split = entry.getValue().get(i).split(":", 2);
								int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
								exceptionhours += mins;
							}

						}
					}
				}
				Thread.sleep(2000);
			if ((actualhours <=0) && (planhours<=0)) {
				Report.PassTestScreenshot(test, driver, "Actual and Plan Hours is less or equal to 0 , Expected Exception time is" + exceptionhours + " When Subview is " + subViewReportName, "Passedexceptionhours");
				
			}
			else if (actualhours <=planhours) {
				Report.PassTestScreenshot(test, driver, "Actual hours  <= Plan hours, Expected Exception time is " + exceptionhours + "When Subview is "+ subViewReportName , "Passedexceptionhours");
			}
			else if (exceptionhours==(actualhours-planhours)) {
				Report.PassTestScreenshot(test, driver, "Difference between Actual Hours "+actualhours+ "And Plan Hours"+ planhours +"Expected Excetion hours is" +exceptionhours +" When Subview is "+subViewReportName, "Passedexceptionhours");
			}
			else {
				Report.FailTestSnapshot(test, driver, "Unable to validate Exeception Time Hours", "Failedexceptionhours");
			}
			
			      
			
			
			} catch (Exception e) {
				Report.FailTest(test, "Unable to validate Exception time hours");
			}
			
			
		}
		
	   public void validatePlanHours(Map<String, List<String>> getPostRouteDetailTableDataExceptionTab,String LOB,String subViewReportName) {
			// PlanHours=Derived from EMAP

			List<String> planHours = new ArrayList<>();
			String expectedPlanHours= "";
			List<String> route = new ArrayList<>();

			try {

				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
					if (entry.getKey().equals("Plan (h:m)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								planHours.add("99:99");
							} else {
								planHours.add(entry.getValue().get(i));
								Util.validateTimeFormat(entry.getValue().get(i) , "Plan Hours in" + subViewReportName + "Post Route Detail Report.");
							}

						}
					}
				}
				
				
				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
					if (entry.getKey().equals("Route")) {
						route = entry.getValue();
					}
				}

				for (int i = 0; i < route.size(); i++) {
					expectedPlanHours = Util.getEMAPDataTimeValue(route.get(i), LOB, "PlanPostRouteHours", GlobalVariables.EMAP);
					if (Util.compareTime(planHours.get(i), expectedPlanHours)) {
						Report.InfoTest(test,
								"Plan hours is correct in " + subViewReportName + " report for Route : "
										+ route.get(i) + " with Actual hours : " + planHours.get(i)
										+ " and expected Hours : " + expectedPlanHours);
						Report.PassTest(test, "Plan hours is as expected in " + subViewReportName + " report");
					} else {
						Report.FailTest(test,
								"Plan hours is not as expected in " + subViewReportName + " report for Route : "
										+ route.get(i) + " with Actual hours : " + planHours.get(i)
										+ " and expected Hours : " + expectedPlanHours);
					}
				}
			} catch (Exception e) {
				Report.FailTest(test,
						"Plan Hours is not as expected in " + subViewReportName + " report Actual is : "
								+ planHours.toString() + " and Expected is : " + expectedPlanHours);

			}

		} 
	   
	   public void validateLOB(Map<String, List<String>> getPostRouteDetailTableDataExceptionTab, String expectedLOB, String subViewReportName) {
			String actualLOB = null;
			try {
				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
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

				if (actualLOB.equals(expectedLOB) || expectedLOB.contains("All LOBs")) {

					Report.InfoTest(test, "LOB is correct in "+subViewReportName+" Post Route Detail report Actual is : "
							+ actualLOB + " and Expected is : " + expectedLOB);
					Report.PassTest(test, "LOB is as expected in "+subViewReportName+" Post Route Detail report");
				} else {
					Report.FailTest(test, "LOB is not as expected in "+subViewReportName+" Post Route Detail report Actual is : "
							+ actualLOB + " and Expected is : " + expectedLOB);
				}
			} catch (Exception e) {
				Report.FailTest(test, "LOB is not as expected in "+subViewReportName+" Post Route Detail report Actual List is : " + actualLOB + " and Expected is : " + expectedLOB);
			}	
	}
	  
	   public void validateSubLOB(Map<String, List<String>> getPostRouteDetailTableDataExceptionTab) {
			List<String> actualSubLOB = new ArrayList<>();
			List<String> expectedSubLOB = new ArrayList<>();
			List<String> route = new ArrayList<>();

			try {
				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
					if (entry.getKey().equals("Sub LOB")) {
						actualSubLOB = entry.getValue();
					}
				}

				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
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
						Report.PassTest(test, "Sub LOB is as expected in Post Route Detail report");
					} else {
						Report.FailTest(test,
								"Sub LOB is not as expected in Post Route Detail report for Route : "
										+ route.get(i) + " with Actual Sub LOB : " + actualSubLOB.get(i)
										+ " and expected Sub LOB : " + expectedSubLOB.get(0));
					}
				}
			} catch (Exception e) {
				Report.FailTest(test, e.getMessage());
				Report.FailTest(test, "Sub LOB is not as expected in Post Route Detail report Actual is : "
						+ actualSubLOB.toString() + " and Expected is : " + expectedSubLOB.toString());
			}
		
	}
	   
	   public void validateRouteType(Map<String, List<String>> getPostRouteDetailTableDataExceptionTab) {
			
			List<String> actualRouteType = new ArrayList<>();
			List<String> expectedRouteType = new ArrayList<>();
			List<String> route = new ArrayList<>();
			List<String> date = new ArrayList<>();
			String expectedRouteTypes = "";
			SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");

			try {
				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
					if (entry.getKey().equals("Route Type")) {
						actualRouteType = entry.getValue();
					}
				}

				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
					if (entry.getKey().equals("Route")) {
						route = entry.getValue();
					}
				}

				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
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
				
						Report.InfoTest(test,
								"Route Type  is correct in Post Route Detail report for Route : "
										+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
										+ " and expected Route Type  : " + expectedRouteTypes);
						Report.PassTest(test, "Route Type is as expected in Post Route Detail Sub View report");
					} else {
						Report.FailTest(test,
								"Route Type is not as expected in Post Route  Detail report for Route : "
										+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
										+ " and expected Route Type : " + expectedRouteTypes);
					}
				}
			} catch (Exception e) {
				Report.FailTest(test, e.getMessage());
				Report.FailTest(test,
						"Route Type  is not as expected in Post Route Detail report Actual is : "
								+ actualRouteType.toString() + " and Expected is : " + expectedRouteType.toString());
			}
		
	}

	   public void ValidateActualHours(Map<String, List<String>> getPostRouteDetailTableDataExceptionTab) {
		   int actualhours=0;
		   int PlanHours=0;
		   int ExceptionHours=0;
		   
		   for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
				if (entry.getKey().contains("Actual (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							actualhours += mins;
						}

					}
				}
				
			}
		   
		   
		   for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
				if (entry.getKey().contains("Plan (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							PlanHours += mins;
						}

					}
				}
				
			}
		   
		   for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
				if (entry.getKey().contains("Exception Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							ExceptionHours += mins;
						}

					}
				}
				
			}
		   
		   if (actualhours==(PlanHours + ExceptionHours)) {
			Report.PassTestScreenshot(test, driver, "Validated Actual Hours Actual  is " + actualhours +" Expected Plan Hours is  " + PlanHours + "And Expected Exception Hours is " +ExceptionHours, "PassedActualHours");
		}
		   else {
			   Report.FailTestSnapshot(test, driver, "Validated Actual Hours Actual  is " + actualhours +" Expected Plan Hours is  " + PlanHours + "And Expected Exception Hours is " +ExceptionHours, "PassedActualHours");
		}
		   
	   }
	   
	   
	   
	   
	   public void ValidatePostRouteSummaryData(Map<String, List<String>> getPostRouteSummaryTableData,Map<String, List<String>> getPostRouteDetailTableDataExceptionTab,Map<String, List<String>> getPostRouteDetailTableDataAllTab) {
		   ValidateTotalExceptionTimeHours(getPostRouteSummaryTableData, getPostRouteDetailTableDataExceptionTab);
		   ValidateTotalPostRoutesWithException();
		   ValidateTotalPostRoutes();
		   ValidateTotalExceptionTimePersentage(getPostRouteDetailTableDataExceptionTab);
		   ValidateAvgActualHours(getPostRouteDetailTableDataAllTab, getPostRouteSummaryTableData);
		   ValidateAvgPlanHours(getPostRouteDetailTableDataExceptionTab, getPostRouteSummaryTableData);
	   }
	   
	   public void ValidateTotalExceptionTimeHours(Map<String, List<String>> getPostRouteSummaryTableData,Map<String, List<String>> getPostRouteDetailTableDataExceptionTab) {
		   String TotalExceptionTime = null;
			String expectedTotalExceptionTime = null;
			int totalMins = 0;

			try {
				for (Entry<String, List<String>> entry : getPostRouteSummaryTableData.entrySet()) {
					if (entry.getKey().contains("Total Exception Time (h:m)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								TotalExceptionTime = null;
							} else {
								TotalExceptionTime = entry.getValue().get(i).toString();
								Util.validateTimeFormat(TotalExceptionTime, "Exception Time Hours in Summary Section");
							}
						}
					}
				}

				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
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

				Util.convertMinsToHours(totalMins);

				expectedTotalExceptionTime = Util.convertMinsToHours(totalMins);

				if (TotalExceptionTime.equals(expectedTotalExceptionTime)) {
					Report.InfoTest(test, "Exception Time Hours is correct in Post Route summary report Actual is : "
							+ TotalExceptionTime + " and Expected is : " + expectedTotalExceptionTime);
					Report.PassTest(test, "Exception Time Hours is as expected in Post Route Summary report");
				} else {
					Report.FailTest(test, "Exception Time Hours is not as expected in Post Route summary report Actual is : "
							+ TotalExceptionTime + " and Expected is : " + expectedTotalExceptionTime);
				}
			} catch (Exception e) {
				Report.FailTest(test, "Exception Time Hours is not as expected in Post Route summary report Actual is : "
						+ TotalExceptionTime + " and Expected is : " + expectedTotalExceptionTime);
			}

		   
		   
	   }
	   
	   public void ValidateTotalPostRoutesWithException() {
		   String ActualTotalPostRoutesWorkE = TotalPostRouteWorkException.getText();
			int Actualresult = Integer.parseInt(ActualTotalPostRoutesWorkE);
			if (Actualresult != 0) {
				int Expectedresult = NumberofRows.size();
				if (Expectedresult == Actualresult) {
					Report.PassTestScreenshot(test, driver,
							"Total Post-Routes w/ Exception is correct for PostRoute Detail Report Expected is " + Expectedresult +" and Actual is " + Actualresult ,
							"Passed Total Post-Routes w/ Exception ");
				} else {
					Report.FailTestSnapshot(test, driver,
							"Total Post-Routes w/ Exception is Incorrect for PosteRoute Detail Report Expected is " + Expectedresult +" and Actual is " + Actualresult,
							"Failed Total Post-Routes w/ Exception Fail");
				}
			} else {
				Report.InfoTest(test, "Total Post-Routes w/ Exception is >>" + Actualresult);
			}

		}
		   
	   public void ValidateTotalPostRoutes() {
			String ExpectedTotalPostRoutes=null;
			int ActualTotalPostRoutes=0;
			int ExpTotalPostRoute=0;
			this.RedirectedtoAllTab();
			try {
				
				ExpectedTotalPostRoutes=TotalNumberofPostRoutesinSummary.getText();
				ExpTotalPostRoute=Integer.parseInt(ExpectedTotalPostRoutes);
				
				ActualTotalPostRoutes=ExpectedActualHours.size();
				if (ExpectedActualHours.size()!=0) {
					if (ExpTotalPostRoute==ActualTotalPostRoutes) {
						Report.PassTestScreenshot(test, driver, "Validated Total Post-Routes in summary section Expected is >" + ExpTotalPostRoute + "And Actual is" + ActualTotalPostRoutes , "PassedTotalPostRoute");
					}
					else {
						Report.FailTestSnapshot(test, driver, "Unable to Validate Total Post-Routes in summary section Expected is >" + ExpTotalPostRoute + "And Actual is" + ActualTotalPostRoutes , "FailedTotalPostRoute");
					}
					
				}
				else {
					Report.FailTestSnapshot(test, driver, "Unable to Validate Total Post-Routes in summary section Expected is >" + ExpTotalPostRoute + "And Actual is" + ActualTotalPostRoutes , "FailedTotalPostRoute");
				}
				
				
			} catch (Exception e) {
				Report.FailTest(test, "Failed Total Post Routes Validation ");
			}
			
			this.RedirectedtoExceptionTab();
		}   
		   
	   public void ValidateTotalExceptionTimePersentage(Map<String, List<String>> getPostRouteDetailTableDataExceptionTab
				) {
			// (Total Exception Time / Total Post-Route Time) ==Percentage; Whole Number
			int TotalExpTime = 0;
			int TotalActTime = 0;
			String ActualExceptiontime = null;
			int ActualExpTime = 0;
				
			
			try {
				
				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
					if (entry.getKey().contains("Exception Time (h:m)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (!entry.getValue().get(i).equals("--")) {
								String[] split = entry.getValue().get(i).split(":", 2);
								int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
								TotalExpTime += mins;
							}

						}
					}
				}
				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
					if (entry.getKey().contains("Actual (h:m)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (!entry.getValue().get(i).equals("--")) {
								String[] split = entry.getValue().get(i).split(":", 2);
								int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
								TotalActTime += mins;
							}

						}
					}
				}

				ActualExceptiontime = GetActualExceptiontime.getText();
				ActualExpTime = Integer.parseInt(ActualExceptiontime);
				float actualdata = ((float) TotalExpTime / (float) TotalActTime) * 100;
				int ActData=Math.round(actualdata);
				if (ActualExpTime != 0) {
					if (Util.compareNumber(ActData, ActualExpTime)) {
						Report.PassTestScreenshot(test, driver, "Passed  Exception Time is  > " + ActualExpTime
								+ "Expected Exception time is > " + ActData, "PassedExceptiontime");
					}
					else {
						Report.FailTestSnapshot(test, driver, "Failed  Exception Time is  > " + ActualExpTime
								+ "Expected Exception time is > " + ActData, "PassedExceptiontimeFail");
					}
					
				} else {
					Report.InfoTest(test, "Exception time is Zero Unable to validate please change search criteria");
				}

			} catch (Exception e) {
				Report.FailTest(test, "Failed to Validate Total Exception Time Persentage");
			}
			

		}	  
 
	   public void ValidateAvgActualHours(Map<String, List<String>> getPostRouteDetailTableDataAllTab,Map<String, List<String>> getPostRouteSummaryTableData) {
		   int TotalplanHours=0; 
		   String TotalNumberofCount=null;
		   int TotalPostRoutes=0;
		   int AvgActualhours=0;
		   
		   for (Entry<String, List<String>> entry : getPostRouteDetailTableDataAllTab.entrySet()) {
				if (entry.getKey().contains("Actual (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							TotalplanHours += mins;
						}

					}
				}
			}
		   for (Entry<String, List<String>> entry : getPostRouteSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Avg Actual (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							AvgActualhours += mins;
						}

					}
				}
			}
		   
		   
		   TotalNumberofCount=GetTotalPostRouteCountFormUI.getText();
		   TotalPostRoutes=Integer.parseInt(TotalNumberofCount);
		   
		   if (TotalPostRoutes!=0) {
				
				float actualdata = ((float) TotalplanHours / (float) TotalPostRoutes) ;
				int ActData=Math.round(actualdata);
				if (Util.compareNumber(ActData, AvgActualhours)) {
					Report.PassTestScreenshot(test, driver, "Passed Avg Actual Hours Actual is > " + ActData + "Minutes and Expected is" + AvgActualhours + " Minutes", "Passed Avg Actual");
				}
				else {
					Report.FailTestSnapshot(test, driver,"Failed Avg Actual Hours Actual is > " + ActData + "Minutes and Expected is" + AvgActualhours + " Minutes " , "Failed Avg Actual");
				}
			}
			else {
				Report.FailTestSnapshot(test, driver, "0 records available", "Failed Avg Actual");
			}
			
		   
		   
	   } 
	   
	   @FindBy(xpath="//*[@id='t2e']/tbody/tr/td[contains(@id,'colPlanTm_')]/span")
	   List<WebElement> TotalNumberofPlanHoursinExceptionTAb;
	   
	   public void ValidateAvgPlanHours(Map<String, List<String>> getPostRouteDetailTableDataExceptionTab, Map<String, List<String>>getPostRouteSummaryTableData) {
			
			int TotalplanHours=0;
			int AvgPlanhours=0;
			int TotalNumbersofActualhrsRows=0;
	       
			try {
				for (Entry<String, List<String>> entry : getPostRouteDetailTableDataExceptionTab.entrySet()) {
					if (entry.getKey().contains("Plan (h:m)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (!entry.getValue().get(i).equals("--")) {
								String[] split = entry.getValue().get(i).split(":", 2);
								int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
								TotalplanHours += mins;
							}

						}
					}
				}
				
				for (Entry<String, List<String>> entry : getPostRouteSummaryTableData.entrySet()) {
					if (entry.getKey().contains("Avg Plan (h:m)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (!entry.getValue().get(i).equals("--")) {
								String[] split = entry.getValue().get(i).split(":", 2);
								int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
								AvgPlanhours += mins;
							}

						}
					}
				}
				
				//FoundNumber of Exception time rows.
				if (TotalNumberofPlanHoursinExceptionTAb.size()!=0) {
					TotalNumbersofActualhrsRows=TotalNumberofPlanHoursinExceptionTAb.size();
					float actualdata = ((float) TotalplanHours / (float) TotalNumbersofActualhrsRows) ;
					int ActData=Math.round(actualdata);
					if (Util.compareNumber(ActData, AvgPlanhours)) {
						Report.PassTestScreenshot(test, driver, "Passed Avg Plan Hours Actual  is > " + ActData + "  Minutes and Expected is" + AvgPlanhours + " Minutes ", "Passed Avg Plan hours");
					}
					else {
						Report.FailTestSnapshot(test, driver,"Failed Avg Plan Hours Actual  is > " + ActData + " Minutes and Expected is" + AvgPlanhours + " Minutes " , "Failed Avg Plan Hours");
					}
				}
				else {
					Report.FailTestSnapshot(test, driver, "0 records available", "Failed Avg Plan hours");
				}
				
				
				
				
				
			} catch (Exception e) {
				Report.FailTest(test, "Unable to Validate Avg Plan Hours");
			}
			
			
			
			
		}
	   
	   
	   
	   
	   
	   
	   public void ValidatePostRouteSummaryReportSummaryData(Map<String, List<String>> getPostRouteSummaryTableData,
				Map<String, List<String>> getPostRouteSummaryTableDetailData) {
		   validateTotalExceptionTime(getPostRouteSummaryTableData,getPostRouteSummaryTableDetailData);
		   ValidateTotalPostRoutesWithExceptionData(getPostRouteSummaryTableData, getPostRouteSummaryTableDetailData);
		   ValidateTotalPostRoutes(getPostRouteSummaryTableData, getPostRouteSummaryTableDetailData);
		   
	   }
	   
	   public void validateTotalExceptionTime(Map<String, List<String>> getPostRouteSummaryTableData,
				Map<String, List<String>> getPostRouteSummaryTableDetailData) {

			// TotalException TimeHours=sum of actual Exception Time hours for all sites
			String driverTotalActualHours = null;
			String expectedDriverTotalActualHours = null;
			int totalMins = 0;

			try {
				for (Entry<String, List<String>> entry : getPostRouteSummaryTableData.entrySet()) {
					if (entry.getKey().contains("Total Exception Time (h:m)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								driverTotalActualHours = null;
							} else {
								driverTotalActualHours = entry.getValue().get(i).toString();
								Util.validateTimeFormat(driverTotalActualHours, "Exception Time Hours in Summary Section");
							}
						}
					}
				}

				for (Entry<String, List<String>> entry : getPostRouteSummaryTableDetailData.entrySet()) {
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

				Util.convertMinsToHours(totalMins);

				expectedDriverTotalActualHours = Util.convertMinsToHours(totalMins);

				if (driverTotalActualHours.equals(expectedDriverTotalActualHours)) {
					Report.InfoTest(test, "Exception Time Hours is correct in Post Route summary report Actual is : "
							+ driverTotalActualHours + " and Expected is : " + expectedDriverTotalActualHours);
					Report.PassTest(test, "Exception Time Hours is as expected in Post Route Summary report");
				} else {
					Report.FailTest(test, "Exception Time Hours is not as expected in Post Route summary report Actual is : "
							+ driverTotalActualHours + " and Expected is : " + expectedDriverTotalActualHours);
				}
			} catch (Exception e) {
				Report.FailTest(test, "Exception Time Hours is not as expected in Post Route summary report Actual is : "
						+ driverTotalActualHours + " and Expected is : " + expectedDriverTotalActualHours);
			}

		}
	   
	   
	   
	   
	   public void ValidateTotalPostRoutesWithExceptionData(Map<String, List<String>> getPostRouteSummaryTableData, Map<String, List<String>> getPostRouteSummaryTableDetailData) {
			int ActualTotalPostRouteWihException=0;
			int ExpectedTotalPostRouteWithException=0;
		   
		   try {
				for (Entry<String, List<String>> entry : getPostRouteSummaryTableData.entrySet()) {
					if (entry.getKey().equals("Total Post-Routes w/ Exception")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								ActualTotalPostRouteWihException =99;
							} else {
								ActualTotalPostRouteWihException = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
								Util.validateWholeNumber(ActualTotalPostRouteWihException, "Total Idle Events in Summary Section");
							}
						}
					}
				}
				for (Entry<String, List<String>> entry : getPostRouteSummaryTableDetailData.entrySet()) {
					if (entry.getKey().equals("# of Post-Routes w/ Exception")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								ExpectedTotalPostRouteWithException = ExpectedTotalPostRouteWithException + Integer.parseInt("0.0");
							} else {
								ExpectedTotalPostRouteWithException = ExpectedTotalPostRouteWithException
										+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
								
							}
						}

					
					}
				}

				// Summary Post-Routes w/ Exception = sum of # of Post-Routes w/ Exception
				
				if (ActualTotalPostRouteWihException == 99) {
					Report.FailTest(test,
							"Total Post-Routes w/ Exception is not calculated as expected in Post Route summary report Actual is : -- and Expected is : "
									+ ExpectedTotalPostRouteWithException);
				} else {
					if (Util.compareNumber(ActualTotalPostRouteWihException, ExpectedTotalPostRouteWithException)) {
						Report.InfoTest(test, "Total Post-Routes w/ Exception is correct in Post Route  summary report Actual is : "
								+ ActualTotalPostRouteWihException + " and Expected is : " + ExpectedTotalPostRouteWithException);
						Report.PassTest(test, "Total Post-Routes w/ Exception is as expected in Post Route  Summary report");
					} else {
						Report.FailTest(test, "Total Post-Routes w/ Exception is not as expected in Post Route summary report Actual is : " + ActualTotalPostRouteWihException
								+ " and Expected is : " + ExpectedTotalPostRouteWithException);
					}
				}
			} catch (Exception e) {
				Report.FailTest(test, "Total Post-Routes w/ Exception is not as expected in Post Route  summary report Actual is : " + ActualTotalPostRouteWihException
						+ " and Expected is : " + ExpectedTotalPostRouteWithException);
			}

		}
	   
	   public void ValidateTotalPostRoutes(Map<String, List<String>> getPostRouteSummaryTableData, Map<String, List<String>> getPostRouteSummaryTableDetailData) {
			int ActualTotalPostRoute=0;
			int ExpectedTotalPostRoute=0;
		   
		   try {
				for (Entry<String, List<String>> entry : getPostRouteSummaryTableData.entrySet()) {
					if (entry.getKey().equals("Total Post-Routes")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								ActualTotalPostRoute = 0;
							} else {
								ActualTotalPostRoute = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
								Util.validateWholeNumber(ActualTotalPostRoute, "Total Post-Routes Summary Section");
							}
						}
					}
				}
				for (Entry<String, List<String>> entry : getPostRouteSummaryTableDetailData.entrySet()) {
					if (entry.getKey().equals("# of Post-Routes")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								ExpectedTotalPostRoute = ExpectedTotalPostRoute + Integer.parseInt("0.0");
							} else {
								ExpectedTotalPostRoute = ExpectedTotalPostRoute
										+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
								
							}
						}

					
					}
				}

				// Summary Post-Routes = sum of # of Post-Routes 
				
				if (ActualTotalPostRoute == 0) {
					Report.FailTest(test,
							"Total Post-Routes  is not calculated as expected in Post Route summary report Actual is : -- and Expected is : "
									+ ExpectedTotalPostRoute);
				} else {
					if (Util.compareNumber(ActualTotalPostRoute, ExpectedTotalPostRoute)) {
						Report.InfoTest(test, "Total Post-Routes is correct in Post Route  summary report Actual is : "
								+ ActualTotalPostRoute + " and Expected is : " + ExpectedTotalPostRoute);
						Report.PassTest(test, "Total Post-Routes  is as expected in Post Route  Summary report");
					} else {
						Report.FailTest(test, "Total Post-Routes  is not as expected in Post Route summary report Actual is : " + ActualTotalPostRoute
								+ " and Expected is : " + ExpectedTotalPostRoute);
					}
				}
			} catch (Exception e) {
				Report.FailTest(test, "Total Post-Routes is not as expected in Post Route  summary report Actual is : " + ActualTotalPostRoute
						+ " and Expected is : " + ExpectedTotalPostRoute);
			}

		}
	    
	   

}

