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

public class ServiceExceptionsDetailReport {
	
	ExtentTest test;
	WebDriver driver;

	public ServiceExceptionsDetailReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//table[@id='dtSvcExceptionsSummary']/thead/tr/th")
	List<WebElement> serviceExceptionsSummaryTableColumns;

	@FindBy(xpath = "//table[@id='dtSvcExceptionsSummary']/tbody/tr")
	List<WebElement> serviceExceptionsSummaryTableRows;
	
	@FindBy(xpath = "//table[@id='t2e']/thead/tr/th")
	List<WebElement> serviceExceptionsDetailTableColumns;

	@FindBy(xpath = "//table[@id='t2e']/tbody/tr")
	List<WebElement> serviceExceptionsDetailTableRows;
	
	@FindBy(xpath = "//table[@id='t3']/thead/tr/th")
	List<WebElement> totalExceptionsDrilldownTableColumns;

	@FindBy(xpath = "//table[@id='t3']/tbody/tr")
	List<WebElement> totalExceptionsDrilldownTableRows;
	
	
	public Map<String, List<String>> getActualServiceExceptionsSummaryTableData() throws IOException {
		Map<String, List<String>> serviceExceptionsSummaryTableData = new HashMap<>();
		
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		try {
			serviceExceptionsSummaryTableData = readTable(serviceExceptionsSummaryTableColumns, serviceExceptionsSummaryTableRows, "dtSvcExceptionsSummary");
			Report.PassTestScreenshot(test, driver, "Service Exceptions Summary table data read successfully", "Service Exceptions Summary Table Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Service Exceptions summary table data");
		}

		for (Entry<String, List<String>> entry : serviceExceptionsSummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return serviceExceptionsSummaryTableData;
	}
	

	public Map<String, List<String>> getActualServiceExceptionsAllTabDetailTableData() {
		System.out.println("inside Actual Service Exceptions All tab Detail Table Data");
		Map<String, List<String>> serviceExceptionsDetailTableData = new HashMap<>();
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		try {
			serviceExceptionsDetailTableData = readTable(serviceExceptionsDetailTableColumns, serviceExceptionsDetailTableRows, "t2e");
			Report.PassTestScreenshot(test, driver, "Service Exceptions All Tab Detail table data read successfully",
					"Service Exceptions All Tab Detail Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Service Exceptions All Tab Detail table data");
		}
		for (Entry<String, List<String>> entry : serviceExceptionsDetailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return serviceExceptionsDetailTableData;
	}
	

	public Map<String, Map<String, List<String>>> getActualTotalExceptionsDrilldownAllTabTableData(String route) {
		Map<String, Map<String, List<String>>> totalExceptionsDrilldownTableData = new HashMap<>();
		try {
			
			Thread.sleep(5000);
			String[] routes = route.split(";");

			Map<String, List<String>> totalExceptionsDrilldownTemp = new HashMap<>();

			for (int i = 0; i < routes.length; i++) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				driver.findElement(By.xpath("//table[@id='t2e']/tbody/tr[@row='" + (i + 1) + "']/td/a/span")).click();
				try {
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer,subSvcExceptionsDetail_Row" + (i + 1));
					totalExceptionsDrilldownTemp = readTable(totalExceptionsDrilldownTableColumns, totalExceptionsDrilldownTableRows, "t3");
					Report.PassTestScreenshot(test, driver, "Total Exceptions Drilldown All Tab table data read successfully",
							"Total Exceptions Drilldown All Tab Data");
					Util.switchToDefaultWindow();
				} catch (Exception e) {
					Report.FailTest(test, "Not able to read Total Exceptions Drill down All Tab table data");
				}
				totalExceptionsDrilldownTableData.put(routes[i], totalExceptionsDrilldownTemp);
			}

			for (Entry<String, Map<String, List<String>>> entry : totalExceptionsDrilldownTableData.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
				}
			}
			return totalExceptionsDrilldownTableData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to read Total Exceptions drilldown All Tab data", "Total Exceptions Drilldown All Tab");
		}

		return totalExceptionsDrilldownTableData;
	}
	
	public Map<String, List<String>> readTable(List<WebElement> columns, List<WebElement> rows, String tableName) {

		
		 if (tableName.equals("t2e")) {
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
				objTable.put(columns.get(iCount - 1).getText(), rowValues);
			}
			return objTable;
		
	}

	}


	public void validateSummaryData(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualAllTabDetailTable, Map<String,Map<String, List<String>>> actualTotalExceptionsDrilldownAllTabTable,String LOB) {
		
		
		if(LOB.equals("Commercial"))
		{
			validateSummaryTotalExceptionsCom(actualSummaryTable,actualAllTabDetailTable);
			validateSummaryPercentofHOCs(actualSummaryTable,actualAllTabDetailTable);
			validateSummaryTotalHOCs(actualSummaryTable,actualAllTabDetailTable,actualTotalExceptionsDrilldownAllTabTable);
		}
		if(LOB.equals("Residential"))
		{
			validateSummaryTotalExceptionsResi(actualSummaryTable,actualAllTabDetailTable);
		}
		if(LOB.equals("Commercial") || LOB.equals("Residential"))
		{
		validateSummaryPercentofMPUs(actualSummaryTable,actualAllTabDetailTable);
		validateSummaryTotalMPUs(actualSummaryTable,actualAllTabDetailTable,actualTotalExceptionsDrilldownAllTabTable);
		validateSummaryPercentofETAs(actualSummaryTable,actualAllTabDetailTable);
		validateSummaryTotalETAs(actualSummaryTable,actualAllTabDetailTable,actualTotalExceptionsDrilldownAllTabTable);
		}
		if(LOB.equals("Roll Off"))
		{
			validateSummaryTotalExceptionsRO(actualSummaryTable,actualAllTabDetailTable);
		}
		
	}


	private void validateSummaryTotalExceptionsRO(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualAllTabDetailTable) {
		
		
		//SUM(MPUs+ETAs)
		
		
				int actualTotalExceptions = 0;
				int expectedTotalExceptions = 0;
			
				try {
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("Total Exceptions")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualTotalExceptions = 999;
								} else {
									actualTotalExceptions = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
									Util.validateWholeNumber(actualTotalExceptions, "Total Exceptions in Summary Section");
								}
							}
						}
					}
					
				
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().equals("Total Exceptions")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									expectedTotalExceptions = expectedTotalExceptions + 0;
								} else {
									expectedTotalExceptions = expectedTotalExceptions + Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
								}
							}
						}
					}

					if (actualTotalExceptions == 999) {
						Report.FailTest(test,
								"Total Exceptions is not calculated as expected in Summary section of Service Exceptions Detail Report. Actual is : -- and Expected is : "
										+ expectedTotalExceptions);
					} else {
						if (Util.compareNumber(actualTotalExceptions, expectedTotalExceptions)) {
							Report.InfoTest(test, "Total Exceptions is correct in Summary section of Service Exceptions Detail Report. Actual is : "
									+ actualTotalExceptions + " and Expected is : " + expectedTotalExceptions);
							Report.PassTest(test, "Total Exceptions is as expected in Summary section of Service Exceptions Detail Report");
						} else {
							Report.FailTest(test, "Total Exceptions is not as expected in Summary section of Service Exceptions Detail Report. Actual is : " + actualTotalExceptions
									+ " and Expected is : " + expectedTotalExceptions);
						}
					}
				} catch (Exception e) {
					Report.FailTest(test, "Total Exceptions is not as expected in Summary section of Service Exceptions Detail Report. Actual is : " + actualTotalExceptions
							+ " and Expected is : " + expectedTotalExceptions);
				}
		
	}


	private void validateSummaryTotalExceptionsResi(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualAllTabDetailTable) {
		
		//SUM(MPUs+ETAs)
		
		
				int actualTotalExceptions = 0;
				int expectedTotalExceptions = 0;
				int totalMPUs=0;
				int totalETAs=0;
				try {
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("Total Exceptions")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualTotalExceptions = 999;
								} else {
									actualTotalExceptions = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
									Util.validateWholeNumber(actualTotalExceptions, "Total Exceptions in Summary Section");
								}
							}
						}
					}
					
				
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("Total MPUs")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									totalMPUs = 0;
								} else {
									totalMPUs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("Total ETAs")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									totalETAs = 0;
								} else {
									totalETAs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
								}
							}
						}
					}
					

					expectedTotalExceptions = totalMPUs+totalETAs;
					
					if (actualTotalExceptions == 999) {
						Report.FailTest(test,
								"Total Exceptions is not calculated as expected in Summary section of Service Exceptions Detail Report. Actual is : -- and Expected is : "
										+ expectedTotalExceptions);
					} else {
						if (Util.compareNumber(actualTotalExceptions, expectedTotalExceptions)) {
							Report.InfoTest(test, "Total Exceptions is correct in Summary section of Service Exceptions Detail Report. Actual is : "
									+ actualTotalExceptions + " and Expected is : " + expectedTotalExceptions);
							Report.PassTest(test, "Total Exceptions is as expected in Summary section of Service Exceptions Detail Report");
						} else {
							Report.FailTest(test, "Total Exceptions is not as expected in Summary section of Service Exceptions Detail Report. Actual is : " + actualTotalExceptions
									+ " and Expected is : " + expectedTotalExceptions);
						}
					}
				} catch (Exception e) {
					Report.FailTest(test, "Total Exceptions is not as expected in Summary section of Service Exceptions Detail Report. Actual is : " + actualTotalExceptions
							+ " and Expected is : " + expectedTotalExceptions);
				}
		
	}


	private void validateSummaryTotalExceptionsCom(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualAllTabDetailTable) {
		//SUM(HOCs+MPUs+ETAs)
		
		
		int actualTotalExceptions = 0;
		int expectedTotalExceptions = 0;
		int totalHOCs=0;
		int totalMPUs=0;
		int totalETAs=0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Exceptions")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualTotalExceptions = 999;
						} else {
							actualTotalExceptions = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualTotalExceptions, "Total Exceptions in Summary Section");
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total HOCs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							totalHOCs = 0;
						} else {
							totalHOCs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total MPUs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							totalMPUs = 0;
						} else {
							totalMPUs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total ETAs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							totalETAs = 0;
						} else {
							totalETAs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
						}
					}
				}
			}
			

			expectedTotalExceptions = totalHOCs+totalMPUs+totalETAs;
			
			if (actualTotalExceptions == 999) {
				Report.FailTest(test,
						"Total Exceptions is not calculated as expected in Summary section of Service Exceptions Detail Report. Actual is : -- and Expected is : "
								+ expectedTotalExceptions);
			} else {
				if (Util.compareNumber(actualTotalExceptions, expectedTotalExceptions)) {
					Report.InfoTest(test, "Total Exceptions is correct in Summary section of Service Exceptions Detail Report. Actual is : "
							+ actualTotalExceptions + " and Expected is : " + expectedTotalExceptions);
					Report.PassTest(test, "Total Exceptions is as expected in Summary section of Service Exceptions Detail Report");
				} else {
					Report.FailTest(test, "Total Exceptions is not as expected in Summary section of Service Exceptions Detail Report. Actual is : " + actualTotalExceptions
							+ " and Expected is : " + expectedTotalExceptions);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Exceptions is not as expected in Summary section of Service Exceptions Detail Report. Actual is : " + actualTotalExceptions
					+ " and Expected is : " + expectedTotalExceptions);
		}
		
		
	}


	private void validateSummaryPercentofHOCs(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualAllTabDetailTable) {
		
		//SUM(HOCs) / Total Exceptions
		
		
				int actualPercentOfHOCs = 0;
				int expectedPercentOfHOCs = 0;
				int totalHOCs=0;
				int totalExceptions=0;
				
				try {
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("% of HOCs")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualPercentOfHOCs = 999;
								} else {
									actualPercentOfHOCs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
									Util.validateWholeNumber(actualPercentOfHOCs, "% of HOCs in Summary Section");
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("Total Exceptions")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									totalExceptions = 0;
								} else {
									totalExceptions = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("Total HOCs")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									totalHOCs = 0;
								} else {
									totalHOCs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
								}
							}
						}
					}
				

					expectedPercentOfHOCs = (totalHOCs * 100)/totalExceptions;
					
					if (actualPercentOfHOCs == 999) {
						Report.FailTest(test,
								"% of HOCs is not calculated as expected in Summary section of Service Exceptions Detail Report. Actual is : -- and Expected is : "
										+ expectedPercentOfHOCs);
					} else {
						if (Util.compareNumber(actualPercentOfHOCs, expectedPercentOfHOCs)) {
							Report.InfoTest(test, "% of HOCs is correct in Summary section of Service Exceptions Detail Report. Actual is : "
									+ actualPercentOfHOCs + " and Expected is : " + expectedPercentOfHOCs);
							Report.PassTest(test, "% of HOCs is as expected in Summary section of Service Exceptions Detail Report");
						} else {
							Report.FailTest(test, "% of HOCs is not as expected in Summary section of Service Exceptions Detail Report. Actual is : " + actualPercentOfHOCs
									+ " and Expected is : " + expectedPercentOfHOCs);
						}
					}
				} catch (Exception e) {
					Report.FailTest(test, "% of HOCs is not as expected in Summary section of Service Exceptions Detail Report. Actual is : " + actualPercentOfHOCs
							+ " and Expected is : " + expectedPercentOfHOCs);
				}
		
	}


	private void validateSummaryTotalHOCs(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualAllTabDetailTable,Map<String,Map<String, List<String>>> actualTotalExceptionsDrilldownAllTabTable) {
	
		//Total HOCs from Total Exceptions Drilldown of Detail Table
		
		
		int actualTotalHOCs = 0;
		int expectedTotalHOCs = 0;
		List<String> route=new ArrayList<>();
		
		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total HOCs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualTotalHOCs = 999;
						} else {
							actualTotalHOCs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualTotalHOCs, "Total HOCs in Summary Section");
						}
					}
				}
			}
			
			
			
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!(entry.getValue().get(i).equals("--"))) {
							route.add(entry.getValue().get(i));
						} 
					}
				}
			}
			
			
			for(int j=0;j<route.size();j++)
			{
				for (Entry<String, Map<String, List<String>>> entry : actualTotalExceptionsDrilldownAllTabTable.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().contains("Ticket Type")) {		
							for (int i = 0; i < entry2.getValue().size(); i++) {
							if(entry2.getValue().get(i).equals("HOC"))
							{
								expectedTotalHOCs=expectedTotalHOCs+1;
							}
							}
						}
					}
				}

			}
			
			if (actualTotalHOCs == 999) {
				Report.FailTest(test,
						"Total HOCs is not calculated as expected in Summary section of Service Exceptions Detail Report. Actual is : -- and Expected is : "
								+ expectedTotalHOCs);
			} else {
				if (Util.compareNumber(actualTotalHOCs, expectedTotalHOCs)) {
					Report.InfoTest(test, "Total HOCs is correct in Summary section of Service Exceptions Detail Report. Actual is : "
							+ actualTotalHOCs + " and Expected is : " + expectedTotalHOCs);
					Report.PassTest(test, "Total HOCs is as expected in Summary section of Service Exceptions Detail Report");
				} else {
					Report.FailTest(test, "Total HOCs is not as expected in Summary section of Service Exceptions Detail Report. Actual is : " + actualTotalHOCs
							+ " and Expected is : " + expectedTotalHOCs);
				}
			}
		
		} catch (Exception e) {
			Report.FailTest(test, "Total HOCs is not as expected in Summary section of Service Exceptions Detail Report. Actual is : " + actualTotalHOCs
					+ " and Expected is : " + expectedTotalHOCs);
		}
		
	}


	private void validateSummaryPercentofMPUs(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualAllTabDetailTable) {
		//SUM(MPUs) / Total Exceptions
		
		
		int actualPercentOfMPUs = 0;
		int expectedPercentOfMPUs = 0;
		int totalMPUs=0;
		int totalExceptions=0;
		
		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("% of MPUs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualPercentOfMPUs = 999;
						} else {
							actualPercentOfMPUs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualPercentOfMPUs, "% of MPUs in Summary Section");
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Exceptions")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							totalExceptions = 0;
						} else {
							totalExceptions = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total MPUs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							totalMPUs = 0;
						} else {
							totalMPUs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
						}
					}
				}
			}
		

			expectedPercentOfMPUs = (totalMPUs * 100)/totalExceptions;
			
			if (actualPercentOfMPUs == 999) {
				Report.FailTest(test,
						"% of MPUs is not calculated as expected in Summary section of Service Exceptions Detail Report. Actual is : -- and Expected is : "
								+ expectedPercentOfMPUs);
			} else {
				if (Util.compareNumber(actualPercentOfMPUs, expectedPercentOfMPUs)) {
					Report.InfoTest(test, "% of MPUs is correct in Summary section of Service Exceptions Detail Report. Actual is : "
							+ actualPercentOfMPUs + " and Expected is : " + expectedPercentOfMPUs);
					Report.PassTest(test, "% of MPUs is as expected in Summary section of Service Exceptions Detail Report");
				} else {
					Report.FailTest(test, "% of MPUs is not as expected in Summary section of Service Exceptions Detail Report. Actual is : " + actualPercentOfMPUs
							+ " and Expected is : " + expectedPercentOfMPUs);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "% of MPUs is not as expected in Summary section of Service Exceptions Detail Report. Actual is : " + actualPercentOfMPUs
					+ " and Expected is : " + expectedPercentOfMPUs);
		}
		
	}


	private void validateSummaryTotalMPUs(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualAllTabDetailTable,Map<String,Map<String, List<String>>> actualTotalExceptionsDrilldownAllTabTable) {
		
		//Total MPUs from Total Exceptions Drilldown of Detail Table
		
		
				int actualTotalMPUs = 0;
				int expectedTotalMPUs = 0;
				List<String> route=new ArrayList<>();
				
				try {
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("Total MPUs")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualTotalMPUs = 999;
								} else {
									actualTotalMPUs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
									Util.validateWholeNumber(actualTotalMPUs, "Total MPUs in Summary Section");
								}
							}
						}
					}
					
					
					
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().equals("Route")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (!(entry.getValue().get(i).equals("--"))) {
									route.add(entry.getValue().get(i));
								} 
							}
						}
					}
					
					
					for(int j=0;j<route.size();j++)
					{
						for (Entry<String, Map<String, List<String>>> entry : actualTotalExceptionsDrilldownAllTabTable.entrySet()) {
							if(entry.getKey().equals(route.get(j)))
							for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
								if (entry2.getKey().contains("Ticket Type")) {		
									for (int i = 0; i < entry2.getValue().size(); i++) {
									if(entry2.getValue().get(i).equals("MPU"))
									{
										expectedTotalMPUs=expectedTotalMPUs+1;
									}
									}
								}
							}
						}

					}
					
					if (actualTotalMPUs == 999) {
						Report.FailTest(test,
								"Total MPUs is not calculated as expected in Summary section of Service Exceptions Detail Report. Actual is : -- and Expected is : "
										+ expectedTotalMPUs);
					} else {
						if (Util.compareNumber(actualTotalMPUs, expectedTotalMPUs)) {
							Report.InfoTest(test, "Total MPUs is correct in Summary section of Service Exceptions Detail Report. Actual is : "
									+ actualTotalMPUs + " and Expected is : " + expectedTotalMPUs);
							Report.PassTest(test, "Total MPUs is as expected in Summary section of Service Exceptions Detail Report");
						} else {
							Report.FailTest(test, "Total MPUs is not as expected in Summary section of Service Exceptions Detail Report. Actual is : " + actualTotalMPUs
									+ " and Expected is : " + expectedTotalMPUs);
						}
					}
				
				} catch (Exception e) {
					Report.FailTest(test, "Total MPUs is not as expected in Summary section of Service Exceptions Detail Report. Actual is : " + actualTotalMPUs
							+ " and Expected is : " + expectedTotalMPUs);
				}
		
	}


	private void validateSummaryPercentofETAs(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualAllTabDetailTable) {
		
		//SUM(ETAs) / Total Exceptions
		
		
				int actualPercentOfETAs = 0;
				int expectedPercentOfETAs = 0;
				int totalETAs=0;
				int totalExceptions=0;
				
				try {
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("% of ETAs")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualPercentOfETAs = 999;
								} else {
									actualPercentOfETAs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
									Util.validateWholeNumber(actualPercentOfETAs, "% of ETAs in Summary Section");
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("Total Exceptions")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									totalExceptions = 0;
								} else {
									totalExceptions = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
						if (entry.getKey().equals("Total ETAs")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									totalETAs = 0;
								} else {
									totalETAs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
								}
							}
						}
					}
				

					expectedPercentOfETAs = (totalETAs * 100)/totalExceptions;
					
					if (actualPercentOfETAs == 999) {
						Report.FailTest(test,
								"% of ETAs is not calculated as expected in Summary section of Service Exceptions Detail Report. Actual is : -- and Expected is : "
										+ expectedPercentOfETAs);
					} else {
						if (Util.compareNumber(actualPercentOfETAs, expectedPercentOfETAs)) {
							Report.InfoTest(test, "% of ETAs is correct in Summary section of Service Exceptions Detail Report. Actual is : "
									+ actualPercentOfETAs + " and Expected is : " + expectedPercentOfETAs);
							Report.PassTest(test, "% of ETAs is as expected in Summary section of Service Exceptions Detail Report");
						} else {
							Report.FailTest(test, "% of ETAs is not as expected in Summary section of Service Exceptions Detail Report. Actual is : " + actualPercentOfETAs
									+ " and Expected is : " + expectedPercentOfETAs);
						}
					}
				} catch (Exception e) {
					Report.FailTest(test, "% of ETAs is not as expected in Summary section of Service Exceptions Detail Report. Actual is : " + actualPercentOfETAs
							+ " and Expected is : " + expectedPercentOfETAs);
				}
		
	}


	private void validateSummaryTotalETAs(Map<String, List<String>> actualSummaryTable,
			Map<String, List<String>> actualAllTabDetailTable,Map<String,Map<String, List<String>>> actualTotalExceptionsDrilldownAllTabTable) {
		
		//Total ETAs from Total Exceptions Drilldown of Detail Table
		
		
		int actualTotalETAs = 0;
		int expectedTotalETAs = 0;
		List<String> route=new ArrayList<>();
		
		try {
			for (Entry<String, List<String>> entry : actualSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total ETAs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualTotalETAs = 999;
						} else {
							actualTotalETAs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualTotalETAs, "Total ETAs in Summary Section");
						}
					}
				}
			}
			
			
			
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!(entry.getValue().get(i).equals("--"))) {
							route.add(entry.getValue().get(i));
						} 
					}
				}
			}
			
			
			for(int j=0;j<route.size();j++)
			{
				for (Entry<String, Map<String, List<String>>> entry : actualTotalExceptionsDrilldownAllTabTable.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().contains("Ticket Type")) {		
							for (int i = 0; i < entry2.getValue().size(); i++) {
							if(entry2.getValue().get(i).equals("ETA"))
							{
								expectedTotalETAs=expectedTotalETAs+1;
							}
							}
						}
					}
				}

			}
			
			if (actualTotalETAs == 999) {
				Report.FailTest(test,
						"Total ETAs is not calculated as expected in Summary section of Service Exceptions Detail Report. Actual is : -- and Expected is : "
								+ expectedTotalETAs);
			} else {
				if (Util.compareNumber(actualTotalETAs, expectedTotalETAs)) {
					Report.InfoTest(test, "Total ETAs is correct in Summary section of Service Exceptions Detail Report. Actual is : "
							+ actualTotalETAs + " and Expected is : " + expectedTotalETAs);
					Report.PassTest(test, "Total ETAs is as expected in Summary section of Service Exceptions Detail Report");
				} else {
					Report.FailTest(test, "Total ETAs is not as expected in Summary section of Service Exceptions Detail Report. Actual is : " + actualTotalETAs
							+ " and Expected is : " + expectedTotalETAs);
				}
			}
		
		} catch (Exception e) {
			Report.FailTest(test, "Total ETAs is not as expected in Summary section of Service Exceptions Detail Report. Actual is : " + actualTotalETAs
					+ " and Expected is : " + expectedTotalETAs);
		}
		
	}


	public void validateDetailData(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalExceptionsDrilldownAllTabTable, String siteID, String lOB,
			String route, String Driver, String fromDate, String toDate) {
		
		validateDetailDate(actualAllTabDetailTable,fromDate,toDate);
		validateDetailRoute(actualAllTabDetailTable,route);
		validateDetailDriver(actualAllTabDetailTable,Driver);
		validateDetailTotalExceptions(actualAllTabDetailTable,actualTotalExceptionsDrilldownAllTabTable);
		validateDetailLOB(actualAllTabDetailTable,lOB);
		validateDetailSubLOB(actualAllTabDetailTable);
		validateDetailRouteType(actualAllTabDetailTable);
		//validateDetailRouteManager(actualAllTabDetailTable);
		
	}

private void validateDetailTotalExceptions(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalExceptionsDrilldownAllTabTable) {
		
	  
		//Total Exceptions = Number of exception in the drilldown
		
		
		List<Integer> actualTotalExceptions = new ArrayList<>();;
		int expectedTotalExceptions = 0;
		List<String> route=new ArrayList<>();
		
		try {
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Total Exceptions")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualTotalExceptions.add(999);
						} else {
							actualTotalExceptions.add(Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", "")));
							Util.validateWholeNumber(actualTotalExceptions.get(i), "Total Exceptions in Detail Section");
						}
					}
				}
			}
			
			
			
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!(entry.getValue().get(i).equals("--"))) {
							route.add(entry.getValue().get(i));
						} 
					}
				}
			}
			
			
			for(int j=0;j<route.size();j++)
			{
				expectedTotalExceptions=0;
				
				for (Entry<String, Map<String, List<String>>> entry : actualTotalExceptionsDrilldownAllTabTable.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().contains("Ticket Type")) {		
							for (int i = 0; i < entry2.getValue().size(); i++) {
							if(entry2.getValue().get(i).equals("HOC")||entry2.getValue().get(i).equals("MPU")||entry2.getValue().get(i).equals("ETA"))
							{
								expectedTotalExceptions=expectedTotalExceptions+1;
							}
							}
						}
					}
				}

			
			
			if (actualTotalExceptions.get(j) == 999) {
				Report.FailTest(test,
						"Total Exceptions is not calculated as expected in Detail section of Service Exceptions Detail Report. Actual is : -- and Expected is : "
								+ expectedTotalExceptions);
			} else {
				if (Util.compareNumber(actualTotalExceptions.get(j), expectedTotalExceptions)) {
					Report.InfoTest(test, "Total Exceptions is correct in Detail section of Service Exceptions Detail Report for route : "+route.get(j)+" . Actual is : "
							+ actualTotalExceptions.get(j) + " and Expected is : " + expectedTotalExceptions);
					Report.PassTest(test, "Total Exceptions is as expected in Detail section of Service Exceptions Detail Report");
				} else {
					Report.FailTest(test, "Total Exceptions is not as expected in Detail section of Service Exceptions Detail Report for route : "+route.get(j)+" . Actual is : " + actualTotalExceptions.get(j)
							+ " and Expected is : " + expectedTotalExceptions);
				}
			}
		}
		
		} catch (Exception e) {
			Report.FailTest(test, "Total Exceptions is not as expected in Detail section of Service Exceptions Detail Report. Actual is : " + actualTotalExceptions
					+ " and Expected is : " + expectedTotalExceptions);
		}
		
	}


private void validateDetailDriver(Map<String, List<String>> actualDetailTable, String expectedDriverName) {
		
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
							"Driver Name is correct in Service Exceptions Detail report Actual List is : "
									+ actualDrivers.toString() + " and Expected is : " + drivers[i]);
					Report.PassTest(test, "Driver Name is as expected in Service Exceptions Detail sub view report");
				} else {
					Report.FailTest(test,
							"Driver Name is not as expected in Service Exceptions Detail report Actual List is : " + actualDrivers.toString()
									+ " and Expected is : " + drivers[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Driver Name is not as expected in Service Exceptions Detail report Actual List is : "
							+ actualDrivers.toString() + " and Expected is : " + drivers.toString());
		}
		
	}

	private void validateDetailRoute(Map<String, List<String>> actualDetailTable, String expectedRouteName) {
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
							"Route Name is correct in Service Exceptions Detail report Actual List is : "
									+ actualRoutes.toString() + " and Expected is : " + routes[i]);
					Report.PassTest(test, "Route Name is as expected in Service Exceptions Detail report");
				} else {
					Report.FailTest(test,
							"Route Name is not as expected in Service Exceptions Detail report Actual List is : " + actualRoutes.toString()
									+ " and Expected is : " + routes[i]);
				}
			}

		} catch (Exception e) {
			Report.FailTest(test,
					"Route is not as expected in Service Exceptions Detail report Actual List is : "
							+ actualRoutes.toString() + " and Expected is : " + routes.toString());
		}
		
	}

	private void validateDetailDate(Map<String, List<String>> actualDetailTable, String fromDate, String toDate) {
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
							"Route Date is correct in Service Exceptions Detail report for route : "
									+ actualRoutes.get(i) + ". Actual is : " + format1.format(routeServedDate.get(i))
									+ " and Expected is between : " + format1.format(startDate) + "and "
									+ format1.format(endDate));
					Report.PassTest(test, "Route Date is as expected in Service Exceptions Detail Report");
				} else {
					Report.FailTest(test,
							"Route Date is not as expected in Service Exceptions Detail report Actual is : "
									+ format1.format(routeServedDate.get(i)) + " and Expected is between : "
									+ format1.format(startDate) + "and " + format1.format(endDate));
				}
			}

		} catch (Exception e) {
			Report.FailTest(test, "Not able to compare date is in between the date range");
			LogClass.error(e.getMessage());
		}
		
	}
	
private void validateDetailRouteType(Map<String, List<String>> actualDetailTable) {
		
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
							"Route Type  is correct in Service Exceptions Detail report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type  : " + expectedRouteTypes);
					Report.PassTest(test, "Route Type is as expected in Service Exceptions Detail Sub View report");
				} else {
					Report.FailTest(test,
							"Route Type is not as expected in Service Exceptions Detail report for Route : "
									+ route.get(i) + " with Actual Route Type  : " + actualRouteType.get(i)
									+ " and expected Route Type : " + expectedRouteTypes);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test,
					"Route Type  is not as expected in Service Exceptions Detail report Actual is : "
							+ actualRouteType.toString() + " and Expected is : " + expectedRouteType.toString());
		}
	
}

	private void validateDetailSubLOB(Map<String, List<String>> actualDetailTable) {
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
							"Sub LOB is correct in Service Exceptions Detail report for Route : "
									+ route.get(i) + " with Actual SubLOB : " + actualSubLOB.get(0)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
					Report.PassTest(test, "Sub LOB is as expected in Service Exceptions Detail report");
				} else {
					Report.FailTest(test,
							"Sub LOB is not as expected in Service Exceptions Detail report for Route : "
									+ route.get(i) + " with Actual Sub LOB : " + actualSubLOB.get(i)
									+ " and expected Sub LOB : " + expectedSubLOB.get(0));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Sub LOB is not as expected in Service Exceptions Detail report Actual is : "
					+ actualSubLOB.toString() + " and Expected is : " + expectedSubLOB.toString());
		}
	
}

	public void validateDetailLOB(Map<String, List<String>> actualDetailTable, String expectedLOB) {
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

				Report.InfoTest(test, "LOB is correct in Service Exceptions Detail report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
				Report.PassTest(test, "LOB is as expected in Service Exceptions Detail report");
			} else {
				Report.FailTest(test, "LOB is not as expected in Service Exceptions Detail report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
			}
		} catch (Exception e) {
			Report.FailTest(test, "LOB is not as expected in Service Exceptions Detail report Actual List is : " + actualLOB + " and Expected is : " + expectedLOB);
		}	
}


	public void validateTotalExceptionsDrilldownData(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalExceptionsDrilldownAllTabTable) {
		
		validateDrilldownTicketType(actualAllTabDetailTable,actualTotalExceptionsDrilldownAllTabTable);	
		validateDrilldownTicketTime(actualAllTabDetailTable,actualTotalExceptionsDrilldownAllTabTable);
		//validateDrilldownTicketComment(actualAllTabDetailTable,actualTotalExceptionsDrilldownAllTabTable);
		validateDrilldownCustID(actualAllTabDetailTable,actualTotalExceptionsDrilldownAllTabTable);
		validateDrilldownCustName(actualAllTabDetailTable,actualTotalExceptionsDrilldownAllTabTable);
		//validateDrilldownServiceDescription(actualAllTabDetailTable,actualTotalExceptionsDrilldownAllTabTable);
		//validateDrilldownSafetyIssue(actualAllTabDetailTable,actualTotalExceptionsDrilldownAllTabTable);
		
	}
	
	private void validateDrilldownTicketType(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalExceptionsDrilldownAllTabTable) {
		
		
		// Ticket Type  = Among HOC,MPU or ETA
		
				List<Integer> noOfExceptions=new ArrayList<>();
				List<String> route = new ArrayList<>();
				List<String> date = new ArrayList<>();
				List<String> expectedTicketType = new ArrayList<>();
				expectedTicketType.add("HOC");
				expectedTicketType.add("MPU");
				expectedTicketType.add("ETA");
				SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
				SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
			
				try {
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().equals("Route")) {
							route = entry.getValue();
						}
					}
					
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().contains("Date")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								Date dates = format1.parse(entry.getValue().get(i));
								date.add(format2.format(dates));
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().equals("Total Exceptions")) {
							for (int i = 0; i < entry.getValue().size(); i++) {						
								noOfExceptions.add(Integer.parseInt(entry.getValue().get(i)));					
							}
						}
					}
					
					for (int j=0;j<route.size();j++)
					{
						List<String> actualTicketType = new ArrayList<>();
						
					
						for (Entry<String, Map<String, List<String>>> entry : actualTotalExceptionsDrilldownAllTabTable.entrySet()) {
							if(entry.getKey().equals(route.get(j)))
							for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
								if (entry2.getKey().equals("Ticket Type ")) {
									for (int i = 0; i < entry2.getValue().size(); i++) {
										if (entry2.getValue().get(i).equals("--")) {
											actualTicketType.add("NA");
										} else {
											actualTicketType.add(entry2.getValue().get(i).trim());
										}
									}
								}
							}
						}
						
						
						for(int i=0;i<noOfExceptions.get(j);i++)
						{
						
						if (expectedTicketType.contains(actualTicketType.get(i))) {
						Report.InfoTest(test,
								"Ticket Type is correct in drill down of Total Exceptions of Service Exceptions Detail report for Route : "+route.get(j)+". Actual is : "
										+ actualTicketType.get(i) + " and Expected is : "
										+ expectedTicketType);
						Report.PassTest(test, "Ticket TypeCust Name is as expected in drill down of Total Exceptions of Service Exceptions Detail report for Route : "+route.get(j));
					} else {
						Report.FailTest(test, "Ticket Type is not as expected in drill down of Total Exceptions of Service Exceptions Detail report for Route : "+route.get(j)+". Actual is : "
								+ actualTicketType.get(i) + " and Expected is : " + expectedTicketType);
					}
					}
			 
				}
			}catch (Exception e) {
					Report.FailTest(test, "Ticket Type is not as expected in drill down of Total Exceptions of Service Exceptions Detail report.");
				}
		
		
	}


	private void validateDrilldownCustName(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalExceptionsDrilldownAllTabTable) {
		// Cust Name  = Customer Name in OCS.
		
		List<Integer> noOfExceptions=new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
	
		try {
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Total Exceptions")) {
					for (int i = 0; i < entry.getValue().size(); i++) {						
						noOfExceptions.add(Integer.parseInt(entry.getValue().get(i)));					
					}
				}
			}
			
			for (int j=0;j<route.size();j++)
			{
				List<String> actualCustomerName = new ArrayList<>();
				List<String> expectedCustomerName = new ArrayList<>();
			
				for (Entry<String, Map<String, List<String>>> entry : actualTotalExceptionsDrilldownAllTabTable.entrySet()) {
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
						"Cust Name is correct in drill down of Total Exceptions of Service Exceptions Detail report for Route : "+route.get(j)+". Actual is : "
								+ actualCustomerName + " and Expected is : "
								+ expectedCustomerName);
				Report.PassTest(test, "Cust Name is as expected in drill down of Total Exceptions of Service Exceptions Detail report for Route : "+route.get(j));
			} else {
				Report.FailTest(test, "Cust Name is not as expected in drill down of Total Exceptions of Service Exceptions Detail report for Route : "+route.get(j)+". Actual is : "
						+ actualCustomerName + " and Expected is : " + expectedCustomerName);
			}
	 
			}
	}catch (Exception e) {
			Report.FailTest(test, "Cust Name is not as expected in drill down of Total Exceptions of Service Exceptions Detail report.");
		}
		
	}


	private void validateDrilldownCustID(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalExceptionsDrilldownAllTabTable) {

		// Cust ID  = Customer ID in OCS.
		
				List<Integer> noOfExceptions=new ArrayList<>();
				List<String> route = new ArrayList<>();
				List<String> date = new ArrayList<>();
				SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
				SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
			
				try {
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().equals("Route")) {
							route = entry.getValue();
						}
					}
					
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().contains("Date")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								Date dates = format1.parse(entry.getValue().get(i));
								date.add(format2.format(dates));
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
						if (entry.getKey().equals("Total Exceptions")) {
							for (int i = 0; i < entry.getValue().size(); i++) {						
								noOfExceptions.add(Integer.parseInt(entry.getValue().get(i)));					
							}
						}
					}
					
					for (int j=0;j<route.size();j++)
					{
						List<String> actualCustomerID = new ArrayList<>();
						List<String> expectedCustomerID = new ArrayList<>();
					
						for (Entry<String, Map<String, List<String>>> entry : actualTotalExceptionsDrilldownAllTabTable.entrySet()) {
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
								"Cust ID is correct in drill down of Total Exceptions of Service Exceptions Detail report for Route : "+route.get(j)+". Actual is : "
										+ actualCustomerID + " and Expected is : "
										+ expectedCustomerID);
						Report.PassTest(test, "Cust ID is as expected in drill down of Total Exceptions of Service Exceptions Detail report for Route : "+route.get(j));
					} else {
						Report.FailTest(test, "Cust ID is not as expected in drill down of Total Exceptions of Service Exceptions Detail report for Route : "+route.get(j)+". Actual is : "
								+ actualCustomerID + " and Expected is : " + expectedCustomerID);
					}
			 
					}
			}catch (Exception e) {
					Report.FailTest(test, "Cust ID is not as expected in drill down of Total Exceptions of Service Exceptions Detail report.");
				}
		
	}


	private void validateDrilldownTicketTime(Map<String, List<String>> actualAllTabDetailTable,
			Map<String, Map<String, List<String>>> actualTotalExceptionsDrilldownAllTabTable) {
		
		// Ticket Time = Time at which HOC taken.
		
		List<Integer> noOfExceptions=new ArrayList<>();
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
	
		try {
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualAllTabDetailTable.entrySet()) {
				if (entry.getKey().equals("Total Exceptions")) {
					for (int i = 0; i < entry.getValue().size(); i++) {						
						noOfExceptions.add(Integer.parseInt(entry.getValue().get(i)));					
					}
				}
			}
			
			for (int j=0;j<route.size();j++)
			{
				List<String> actualTicketTime = new ArrayList<>();
				List<String> expectedTicketTime = new ArrayList<>();
			
				for (Entry<String, Map<String, List<String>>> entry : actualTotalExceptionsDrilldownAllTabTable.entrySet()) {
					if(entry.getKey().equals(route.get(j)))
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Ticket Time")) {
							for (int i = 0; i < entry2.getValue().size(); i++) {
								if (entry2.getValue().get(i).equals("--")) {
									actualTicketTime.add("NA");
								} else {
									actualTicketTime.add(entry2.getValue().get(i).trim());
								}
							}
						}
					}
				}
				
				expectedTicketTime = Util.getDataFromDB(
						"select  TO_CHAR(STAMP - (6 / 24) , 'HH:MI AM') from OCS_ADMIN.TP_CO_HAULCALL where fk_customerorder in (select pkey from ocs_admin.tp_customerorder where FK_ROUTEORDER IN (select PKEY from OCS_ADMIN.TP_ROUTEORDER where FK_ROUTE IN (select PKEY from OCS_ADMIN.TP_ROUTE where ID =  '"
								+ route.get(j) + "') and OCS_ADMIN.TP_ROUTEORDER.ORDERDATE IN ("
								+ Util.sqlFormatedList(date) + ")) and FK_VEHICLE IS NOT NULL)  order by OCS_ADMIN.TP_CO_HAULCALL.STAMP");

			

				
				if (actualTicketTime.equals(expectedTicketTime)) {
				Report.InfoTest(test,
						"Ticket Time is correct in drill down of Total Exceptions of Service Exceptions Detail report for Route : "+route.get(j)+". Actual is : "
								+ actualTicketTime + " and Expected is : "
								+ expectedTicketTime);
				Report.PassTest(test, "Ticket Time is as expected in drill down of Total Exceptions of Service Exceptions Detail report for Route : "+route.get(j));
			} else {
				Report.FailTest(test, "Ticket Time is not as expected in drill down of Total Exceptions of Service Exceptions Detail report for Route : "+route.get(j)+". Actual is : "
						+ actualTicketTime + " and Expected is : " + expectedTicketTime);
			}
	 
			}
	}catch (Exception e) {
			Report.FailTest(test, "Ticket Time is not as expected in drill down of Total Exceptions of Service Exceptions Detail report.");
		}
		
	}


	public void clickTotalExceptionsDrilldown() {
		
		try {
			
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");
			driver.findElement(By.xpath("//table[@id='t2e']/tbody/tr[1]/td/a/span")).click();
			
		}
	
	 catch (Exception e) {
		System.out.println(e.getMessage());
		Report.FailTestSnapshot(test, driver, "Not able to Click on Net Idle Time", "Net Idle Time Drilldown");
	}

		
	}
	

}
