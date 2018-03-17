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

public class KronosOcsLunchExceptionDetailReport {


	ExtentTest test;
	WebDriver driver;

	public KronosOcsLunchExceptionDetailReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	
	  //KronosOcsDetailTableColumn
		@FindBy(xpath = "//*[@id='dtKronos']/thead/tr/th")
		List<WebElement> KronosOcsTableColumn;
		
		//KronosOcsDetailTableRow
		@FindBy(xpath = "//*[@id='dtKronos']/tbody/tr")
		List<WebElement> KronosOcsTableRow;
	
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
		
	public Map<String, List<String>> getDetailTableData() {
		System.out.println("Inside Kronos Ocs Detail Table Data");
		Util.CaptureScreenshot("Actual Kronos Ocs Detail Table Data");
		Map<String, List<String>> KronosOcsDetailTableData = new HashMap<>();
		try {
			KronosOcsDetailTableData = readTable(KronosOcsTableColumn, KronosOcsTableRow, "dtKronos");
			Report.PassTestScreenshot(test, driver, "Kronos Ocs table data read successfully",
					"Kronos Ocs Detail Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Kronos Ocs Detail table data");
		}
		for (Entry<String, List<String>> entry : KronosOcsDetailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return KronosOcsDetailTableData;
	}

	public void ValidateDetailsTableData(Map<String, List<String>> getDetailTableData, String fromDate, String toDate, String DriverName) {
		validateDate(getDetailTableData, fromDate, toDate, "Detail Table");
		validateDriverName(getDetailTableData, DriverName, "Detail Table");
		
		
		
	}
	
	public void validateDate(Map<String, List<String>> getDetailTableData, String fromDate, String toDate,
			String subViewReportName) {
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yy");
		Date startDate = null;
		Date endDate = null;
		List<Date> routeServedDate = new ArrayList<>();
		List<String> actualDrivers = null;
		try {
			startDate = format1.parse(fromDate);
			endDate = format1.parse(toDate);
			for (Entry<String, List<String>> entry : getDetailTableData.entrySet()) {
				if (entry.getKey().equals("Driver ID")) {
					actualDrivers = entry.getValue();
				}
			}

			for (Entry<String, List<String>> entry : getDetailTableData.entrySet()) {
				if (entry.getKey().equals("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						routeServedDate.add(format2.parse(entry.getValue().get(i).toString()));
					}
				}
			}

			for (int i = 0; i < actualDrivers.size(); i++) {
				if (routeServedDate.get(i).equals(startDate) || routeServedDate.get(i).equals(endDate)
						|| (routeServedDate.get(i).after(startDate) && routeServedDate.get(i).before(endDate))) {
					Report.InfoTest(test,
							"Route Date is correct in " + subViewReportName + " Sub view report for DriverId : "
									+ actualDrivers.get(i) + ". Actual is : " + format1.format(routeServedDate.get(i))
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
	
	public void validateDriverName(Map<String, List<String>> getDetailTableData, String expectedDriverName,
			String subViewReportName) {
		String[] drivers = expectedDriverName.split(";");
		List<String> actualDrivers = null;
		try {
			for (Entry<String, List<String>> entry : getDetailTableData.entrySet()) {
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
	
	
}
