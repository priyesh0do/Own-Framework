package ObjectRepository.Logi;

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

public class SlaesInformationDetailReport {

	ExtentTest test;
	WebDriver driver;

	public SlaesInformationDetailReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//table[@id='dtSalesInfo']/thead/tr/th")
	List<WebElement> SelesInformationDetailTableColumns;

	@FindBy(xpath = "//table[@id='dtSalesInfo']/tbody/tr")
	List<WebElement> SalesInformationDetailTableRows;
	
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
	
	
	
	public Map<String, List<String>> getActualSalesInformationDetailTableData() {
		System.out.println("inside Actual Sales Information Detail Table Data");
		Map<String, List<String>> SalesInformationDetailTableData = new HashMap<>();
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		
		try {
			SalesInformationDetailTableData = readTable(SelesInformationDetailTableColumns, SalesInformationDetailTableRows, "dtSalesInfo");
			Report.PassTestScreenshot(test, driver, "Actual Sales Information Detail table data read successfully",
					"Sales Information Detail Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Sales Information Detail table data");
		}
		for (Entry<String, List<String>> entry : SalesInformationDetailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return SalesInformationDetailTableData;
	}
	
	
	public void ValidateDetailTableData(Map<String, List<String>> getActualSalesInformationDetailTableData,String fromDate, String toDate,String expectedRouteName,String expectedDriverName, String expectedLOB, String DriverComments) {
		validateDetailDate(getActualSalesInformationDetailTableData, fromDate, toDate);
		validateDetailRoute(getActualSalesInformationDetailTableData, expectedRouteName);
		validateDetailDriver(getActualSalesInformationDetailTableData, expectedDriverName);
		validateCustID(getActualSalesInformationDetailTableData);
		validateCustName(getActualSalesInformationDetailTableData);
		validateDetailLOB(getActualSalesInformationDetailTableData, expectedLOB);
		validateDetailSubLOB(getActualSalesInformationDetailTableData);
		validateDetailRouteType(getActualSalesInformationDetailTableData);
		validateDetailDriverComments(getActualSalesInformationDetailTableData, DriverComments);
	}
	
	
	private void validateDetailDate(Map<String, List<String>> getActualSalesInformationDetailTableData, String fromDate, String toDate) {
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yy");
		Date startDate = null;
		Date endDate = null;
		List<Date> routeServedDate = new ArrayList<>();
		List<String> actualRoutes = null;
		try {
			startDate = format1.parse(fromDate);
			endDate = format1.parse(toDate);
			for (Entry<String, List<String>> entry : getActualSalesInformationDetailTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					actualRoutes = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getActualSalesInformationDetailTableData.entrySet()) {
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
							"Route Date is correct in Sales Information Detail report for route : "
									+ actualRoutes.get(i) + ". Actual is : " + format1.format(routeServedDate.get(i))
									+ " and Expected is between : " + format1.format(startDate) + "and "
									+ format1.format(endDate));
					Report.PassTest(test, "Route Date is as expected in Sales Information Detail Report");
				} else {
					Report.FailTest(test,
							"Route Date is not as expected in Sales Information Detail report Actual is : "
									+ format1.format(routeServedDate.get(i)) + " and Expected is between : "
									+ format1.format(startDate) + "and " + format1.format(endDate));
				}
			}

		} catch (Exception e) {
			Report.FailTest(test, "Not able to compare date is in between the date range");
			LogClass.error(e.getMessage());
		}
		
	}

	private void validateDetailRoute(Map<String, List<String>> getActualSalesInformationDetailTableData, String expectedRouteName) {
		String[] routes = expectedRouteName.split(";");
		List<String> actualRoutes = null;
		try {
			for (Entry<String, List<String>> entry : getActualSalesInformationDetailTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					actualRoutes = entry.getValue();
				}
			}
			for (int i = 0; i < routes.length; i++) {
				if (actualRoutes.contains(routes[i])) {
					Report.InfoTest(test,
							"Route Name is correct in Sales Information Detail report Actual List is : "
									+ actualRoutes.toString() + " and Expected is : " + routes[i]);
					Report.PassTest(test, "Route Name is as expected in Sales Information Detail report");
				} else {
					Report.FailTest(test,
							"Route Name is not as expected in Sales Information Detail report Actual List is : " + actualRoutes.toString()
									+ " and Expected is : " + routes[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Route is not as expected in Sales Information Detail report Actual List is : "
							+ actualRoutes.toString() + " and Expected is : " + routes.toString());
		}
		
	}

    private void validateDetailDriver(Map<String, List<String>> getActualSalesInformationDetailTableData, String expectedDriverName) {
		
		String[] drivers = expectedDriverName.split(";");
		List<String> actualDrivers = null;
		try {
			for (Entry<String, List<String>> entry : getActualSalesInformationDetailTableData.entrySet()) {
				if (entry.getKey().equals("Driver")) {
					actualDrivers = entry.getValue();
				}
			}
			for (int i = 0; i < drivers.length; i++) {
				if (actualDrivers.contains(drivers[i])) {
					Report.InfoTest(test,
							"Driver Name is correct in Sales Information Detail report Actual List is : "
									+ actualDrivers.toString() + " and Expected is : " + drivers[i]);
					Report.PassTest(test, "Driver Name is as expected in Sales Information Detail sub view report");
				} else {
					Report.FailTest(test,
							"Driver Name is not as expected in Sales Information Detail report Actual List is : " + actualDrivers.toString()
									+ " and Expected is : " + drivers[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Driver Name is not as expected in Sales Information Detail report Actual List is : "
							+ actualDrivers.toString() + " and Expected is : " + drivers.toString());
		}
		
	}

    
    private void validateCustID(Map<String, List<String>> getActualSalesInformationDetailTableData) {

		// Cust ID  = Customer ID in OCS.
		
				List<String> route = new ArrayList<>();
				List<String> date = new ArrayList<>();
				SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
				SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
			
				try {
					for (Entry<String, List<String>> entry : getActualSalesInformationDetailTableData.entrySet()) {
						if (entry.getKey().equals("Route")) {
							route = entry.getValue();
						}
					}
					
					for (Entry<String, List<String>> entry : getActualSalesInformationDetailTableData.entrySet()) {
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
						for (Entry<String, List<String>> entry : getActualSalesInformationDetailTableData.entrySet()) {
							if (entry.getKey().equals("Cust ID")) {
								for (int i = 0; i < entry.getValue().size(); i++) {
									if (entry.getValue().get(i).equals("--")) {
										actualCustomerID.add("NA");
									} else {
										actualCustomerID.add(entry.getValue().get(i).trim());
									}
								}
							}	
							
							
						}	
											
						expectedCustomerID = Util.getDataFromDB("select OCS_ADMIN.TP_CUSTOMER.COMPANYID || '-' || OCS_ADMIN.TP_CUSTOMER.COMPANYNUMBER as CustomerID from OCS_ADMIN.TP_CUSTOMER join OCS_ADMIN.TP_CUSTOMERORDER on OCS_ADMIN.TP_CUSTOMER.PKEY = OCS_ADMIN.TP_CUSTOMERORDER.FK_CUSTOMER join OCS_ADMIN.TP_ROUTEORDER  on OCS_ADMIN.TP_CUSTOMERORDER.FK_ROUTEORDER = OCS_ADMIN.TP_ROUTEORDER.PKEY join OCS_ADMIN.TP_ROUTE on OCS_ADMIN.TP_ROUTEORDER.FK_ROUTE = OCS_ADMIN.TP_ROUTE.PKEY where OCS_ADMIN.TP_ROUTE.ID = '"+route.get(j)+"' and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("+ Util.sqlFormatedList(date) +") order by OCS_ADMIN.TP_CUSTOMERORDER.AUDIT_CREATE_DT");
						
						if (expectedCustomerID.contains(actualCustomerID)) {
						Report.InfoTest(test,
								"Cust ID is correct Sales Information Detail report for Route : "+route.get(j)+". Actual is : "
										+ actualCustomerID + " and Expected is : "
										+ expectedCustomerID);
						Report.PassTest(test, "Cust ID is as expected Sales Information Detail report for Route : "+route.get(j));
					} else {
						Report.FailTest(test, "Cust ID is not as expected Sales Information Detail report for Route : "+route.get(j)+". Actual is : "
								+ actualCustomerID + " and Expected is : " + expectedCustomerID);
					}
			 
					}
			}catch (Exception e) {
					Report.FailTest(test, "Cust ID is not as expected Sales Information Detail report.");
				}
		
	}

    private void validateCustName(Map<String, List<String>> getActualSalesInformationDetailTableData) {
		// Cust Name  = Customer Name in OCS.
		
		
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
	
		try {
			for (Entry<String, List<String>> entry : getActualSalesInformationDetailTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			
			for (Entry<String, List<String>> entry : getActualSalesInformationDetailTableData.entrySet()) {
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
				for (Entry<String, List<String>> entry : getActualSalesInformationDetailTableData.entrySet()) {
					if (entry.getKey().equals("Cust Name")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								actualCustomerName.add("NA");
							} else {
								actualCustomerName.add(entry.getValue().get(i).trim());
							}
						}
					}
					
					
				}
				

				expectedCustomerName = Util.getDataFromDB("select OCS_ADMIN.TP_CUSTOMER.NAME from OCS_ADMIN.TP_CUSTOMER join OCS_ADMIN.TP_CUSTOMERORDER on OCS_ADMIN.TP_CUSTOMER.PKEY = OCS_ADMIN.TP_CUSTOMERORDER.FK_CUSTOMER join OCS_ADMIN.TP_ROUTEORDER  on OCS_ADMIN.TP_CUSTOMERORDER.FK_ROUTEORDER = OCS_ADMIN.TP_ROUTEORDER.PKEY join OCS_ADMIN.TP_ROUTE on OCS_ADMIN.TP_ROUTEORDER.FK_ROUTE = OCS_ADMIN.TP_ROUTE.PKEY where OCS_ADMIN.TP_ROUTE.ID = '"+route.get(j)+"' and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("+ Util.sqlFormatedList(date) +") order by OCS_ADMIN.TP_CUSTOMERORDER.AUDIT_CREATE_DT");

				
				if (actualCustomerName.equals(expectedCustomerName)) {
				Report.InfoTest(test,
						"Cust Name is correct in Sales Information Detail report for Route : "+route.get(j)+". Actual is : "
								+ actualCustomerName + " and Expected is : "
								+ expectedCustomerName);
				Report.PassTest(test, "Cust Name is as expected in Sales Information Detail report for Route : "+route.get(j));
			} else {
				Report.FailTest(test, "Cust Name is not as expected in Sales Information Detail report for Route : "+route.get(j)+". Actual is : "
						+ actualCustomerName + " and Expected is : " + expectedCustomerName);
			}
	 
			}
	}catch (Exception e) {
			Report.FailTest(test, "Cust Name is not as expected in Sales Information Detail report.");
		}
		
	}

   private void validateDetailDriverComments(Map<String, List<String>> getActualSalesInformationDetailTableData, String expectedDriverName) {
		
		String[] drivers = expectedDriverName.split(";");
		List<String> actualDrivers = null;
		try {
			for (Entry<String, List<String>> entry : getActualSalesInformationDetailTableData.entrySet()) {
				if (entry.getKey().equals("Driver Comments")) {
					actualDrivers = entry.getValue();
				}
			}
			for (int i = 0; i < drivers.length; i++) {
				if (actualDrivers.contains(drivers[i])) {
					Report.InfoTest(test,
							"Driver Comments is correct in Sales Information Detail report Actual List is : "
									+ actualDrivers.toString() + " and Expected is : " + drivers[i]);
					Report.PassTest(test, "Driver Comments is as expected in Sales Information Detail sub view report");
				} else {
					Report.FailTest(test,
							"Driver Comments is not as expected in Sales Information Detail report Actual List is : " + actualDrivers.toString()
									+ " and Expected is : " + drivers[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Driver Comments is not as expected in Sales Information Detail report Actual List is : "
							+ actualDrivers.toString() + " and Expected is : " + drivers.toString());
		}
		
	}
    
    public void validateDetailLOB(Map<String, List<String>> getActualSalesInformationDetailTableData, String expectedLOB) {
		String actualLOB = null;
		try {
			for (Entry<String, List<String>> entry : getActualSalesInformationDetailTableData.entrySet()) {
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

				Report.InfoTest(test, "LOB is correct in Sales Information Detailreport Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
				Report.PassTest(test, "LOB is as expected in Sales Information Detailreport");
			} else {
				Report.FailTest(test, "LOB is not as expected in Sales Information Detailreport Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
			}
		} catch (Exception e) {
			Report.FailTest(test, "LOB is not as expected in Sales Information Detailreport Actual List is : " + actualLOB + " and Expected is : " + expectedLOB);
		}	
}
     
    private void validateDetailSubLOB(Map<String, List<String>> getActualSalesInformationDetailTableData) {
		List<String> actualSubLOB = new ArrayList<>();
		List<String> expectedSubLOB = new ArrayList<>();
		List<String> route = new ArrayList<>();

		try {
			for (Entry<String, List<String>> entry : getActualSalesInformationDetailTableData.entrySet()) {
				if (entry.getKey().equals("Sub LOB")) {
					actualSubLOB = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getActualSalesInformationDetailTableData.entrySet()) {
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
							"Sub LOB is correct in Sales Information Detailreport for Route : "
									+ route.get(i) + " with Actual SubLOB : " + actualSubLOB.get(0)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
					Report.PassTest(test, "Sub LOB is as expected in Sales Information Detailreport");
				} else {
					Report.FailTest(test,
							"Sub LOB is not as expected in Sales Information Detailreport for Route : "
									+ route.get(i) + " with Actual Sub LOB : " + actualSubLOB.get(i)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Sub LOB is not as expected in Sales Information Detailreport Actual is : "
					+ actualSubLOB.toString() + " and Expected is : " + expectedSubLOB.toString());
		}
	
}

    private void validateDetailRouteType(Map<String, List<String>> getActualSalesInformationDetailTableData) {
		
		List<String> actualRouteType = new ArrayList<>();
		List<String> expectedRouteType = new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		String expectedRouteTypes = "";
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");

		try {
			for (Entry<String, List<String>> entry : getActualSalesInformationDetailTableData.entrySet()) {
				if (entry.getKey().equals("Route Type")) {
					actualRouteType = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getActualSalesInformationDetailTableData.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getActualSalesInformationDetailTableData.entrySet()) {
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
							"Route Type  is correct in Sales Information Detail report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type  : " + expectedRouteTypes);
					Report.PassTest(test, "Route Type is as expected in Sales Information Detail Sub View report");
				} else {
					Report.FailTest(test,
							"Route Type is not as expected in Sales Information Detail report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type : " + expectedRouteTypes);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test,
					"Route Type  is not as expected in Sales Information Detail report Actual is : "
							+ actualRouteType.toString() + " and Expected is : " + expectedRouteType.toString());
		}
	
}
}
