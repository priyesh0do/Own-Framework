package ObjectRepository.Logi;

import java.util.ArrayList;
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

import SupportLibraries.Report;
import SupportLibraries.Util;

public class ServiceExceptionsSummaryReport {
	
	ExtentTest test;
	WebDriver driver;

	public ServiceExceptionsSummaryReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//table[@id='dtSvcExceptionsSummary']/thead/tr/th")
	List<WebElement> serviceExceptionsSummaryTableColumns;

	@FindBy(xpath = "//table[@id='dtSvcExceptionsSummary']/tbody/tr")
	List<WebElement> serviceExceptionsSummaryTableRows;
	
	@FindBy(xpath = "//table[@id='dtSvcExceptionsDetail']/thead/tr/th")
	List<WebElement> serviceExceptionsSummaryDetailTableColumns;

	@FindBy(xpath = "//table[@id='dtSvcExceptionsDetail']/tbody/tr")
	List<WebElement> serviceExceptionsSummaryDetailTableRows;

	public Map<String, List<String>> getActualSummaryTableData() {
		System.out.println("inside Actual Summary Data");
		Map<String, List<String>> summaryTableData = new HashMap<>();
		try {
			summaryTableData = readTable(serviceExceptionsSummaryTableColumns, serviceExceptionsSummaryTableRows, "dtSvcExceptionsSummary");
			Report.PassTestScreenshot(test, driver, "Summary table data read successfully", "Service Exceptions Summary Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read summary table data");
		}

		for (Entry<String, List<String>> entry : summaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return summaryTableData;
	}
	
	public Map<String, List<String>> getActualDetailTableData() {
		System.out.println("inside Actual Service Exceptions Summary Detail Table Data");
		Map<String, List<String>> serviceExceptionsSummaryDetailTableData = new HashMap<>();
		try {
			serviceExceptionsSummaryDetailTableData = readTable(serviceExceptionsSummaryDetailTableColumns, serviceExceptionsSummaryDetailTableRows, "dtSvcExceptionsDetail");
			Report.PassTestScreenshot(test, driver, "Actual Service Exceptions Summary Detail table data read successfully",
					"Service Exceptions Summary Detail Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Service Exceptions Summary Detail table data");
		}
		for (Entry<String, List<String>> entry : serviceExceptionsSummaryDetailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return serviceExceptionsSummaryDetailTableData;
	}
	
	public Map<String, List<String>> readTable(List<WebElement> columns, List<WebElement> rows, String tableName) {
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

	public void validateServiceExceptionsSummaryData(Map<String, List<String>> actualServiceExceptionsSummaryTable,
			Map<String, List<String>> actualServiceExceptionsSummaryDetailTable, String lOB) {
		validateSummaryTotalExceptions(actualServiceExceptionsSummaryTable,actualServiceExceptionsSummaryDetailTable);
		validateSummaryPercentofHOCs(actualServiceExceptionsSummaryTable,actualServiceExceptionsSummaryDetailTable);
		validateSummaryTotalHOCs(actualServiceExceptionsSummaryTable,actualServiceExceptionsSummaryDetailTable);
		validateSummaryPercentofMPUs(actualServiceExceptionsSummaryTable,actualServiceExceptionsSummaryDetailTable);
		validateSummaryTotalMPUs(actualServiceExceptionsSummaryTable,actualServiceExceptionsSummaryDetailTable);
		validateSummaryPercentofETAs(actualServiceExceptionsSummaryTable,actualServiceExceptionsSummaryDetailTable);
		validateSummaryTotalETAs(actualServiceExceptionsSummaryTable,actualServiceExceptionsSummaryDetailTable);
		validateSummaryNumberofSites(actualServiceExceptionsSummaryTable,actualServiceExceptionsSummaryDetailTable);
	}

	private void validateSummaryTotalExceptions(Map<String, List<String>> actualServiceExceptionsSummaryTable,
			Map<String, List<String>> actualServiceExceptionsSummaryDetailTable) {
		
		int actualSummaryTotalExceptions = 0;
		int expectedSummaryTotalExceptions = 0;	
		try {
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Exceptions")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualSummaryTotalExceptions = 0;
						} else {
							actualSummaryTotalExceptions = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualSummaryTotalExceptions, "Total Exceptions in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Total Exceptions")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedSummaryTotalExceptions = expectedSummaryTotalExceptions + Integer.parseInt("0.0");
						} else {
							expectedSummaryTotalExceptions = expectedSummaryTotalExceptions
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			// Summary Total Exceptions = sum of total Exceptions of all sites
			
			if (actualSummaryTotalExceptions == 0) {
				Report.FailTest(test,
						"Total Exceptions is not calculated as expected in Service Exceptions summary report Actual is : -- and Expected is : "
								+ expectedSummaryTotalExceptions);
			} else {
				if (Util.compareNumber(actualSummaryTotalExceptions, expectedSummaryTotalExceptions)) {
					Report.InfoTest(test, "Total Exceptions is correct in Service Exceptions summary report Actual is : "
							+ actualSummaryTotalExceptions + " and Expected is : " + expectedSummaryTotalExceptions);
					Report.PassTest(test, "Total Exceptions is as expected in Service Exceptions Summary report");
				} else {
					Report.FailTest(test, "Total Exceptions is not as expected in Service Exceptions summary report Actual is : " + actualSummaryTotalExceptions
							+ " and Expected is : " + expectedSummaryTotalExceptions);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Exceptions is not as expected in Service Exceptions summary report Actual is : " + actualSummaryTotalExceptions
					+ " and Expected is : " + expectedSummaryTotalExceptions);
		}
		
	}

	private void validateSummaryPercentofHOCs(Map<String, List<String>> actualServiceExceptionsSummaryTable,
			Map<String, List<String>> actualServiceExceptionsSummaryDetailTable) {
	
		int actualSummaryPercentOfHOCs = 0;
		int expectedSummaryPercentOfHOCs = 0;	
		try {
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryTable.entrySet()) {
				if (entry.getKey().equals("% of HOCs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualSummaryPercentOfHOCs = 0;
						} else {
							actualSummaryPercentOfHOCs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualSummaryPercentOfHOCs, "% of HOCs in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("% of HOCs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedSummaryPercentOfHOCs = expectedSummaryPercentOfHOCs + Integer.parseInt("0.0");
						} else {
							expectedSummaryPercentOfHOCs = expectedSummaryPercentOfHOCs
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			// Summary % of HOCs = sum of % of HOCs of all sites
			
			if (actualSummaryPercentOfHOCs == 0) {
				Report.FailTest(test,
						"% of HOCs is not calculated as expected in Service Exceptions summary report Actual is : -- and Expected is : "
								+ expectedSummaryPercentOfHOCs);
			} else {
				if (Util.compareNumber(actualSummaryPercentOfHOCs, expectedSummaryPercentOfHOCs)) {
					Report.InfoTest(test, "% of HOCs is correct in Service Exceptions summary report Actual is : "
							+ actualSummaryPercentOfHOCs + " and Expected is : " + expectedSummaryPercentOfHOCs);
					Report.PassTest(test, "% of HOCs is as expected in Service Exceptions Summary report");
				} else {
					Report.FailTest(test, "% of HOCs is not as expected in Service Exceptions summary report Actual is : " + actualSummaryPercentOfHOCs
							+ " and Expected is : " + expectedSummaryPercentOfHOCs);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "% of HOCs is not as expected in Service Exceptions summary report Actual is : " + actualSummaryPercentOfHOCs
					+ " and Expected is : " + expectedSummaryPercentOfHOCs);
		}
		
	}

	private void validateSummaryTotalHOCs(Map<String, List<String>> actualServiceExceptionsSummaryTable,
			Map<String, List<String>> actualServiceExceptionsSummaryDetailTable) {
		int actualSummaryTotalHOCs = 0;
		int expectedSummaryTotalHOCs = 0;	
		try {
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total HOCs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualSummaryTotalHOCs = 0;
						} else {
							actualSummaryTotalHOCs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualSummaryTotalHOCs, "Total HOCs in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryDetailTable.entrySet()) {
				if (entry.getKey().contains("# of HOCs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedSummaryTotalHOCs = expectedSummaryTotalHOCs + Integer.parseInt("0.0");
						} else {
							expectedSummaryTotalHOCs = expectedSummaryTotalHOCs
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			// Summary Total HOCs = sum of Total HOCs of all sites
			
			if (actualSummaryTotalHOCs == 0) {
				Report.FailTest(test,
						"Total HOCs is not calculated as expected in Service Exceptions summary report Actual is : -- and Expected is : "
								+ expectedSummaryTotalHOCs);
			} else {
				if (Util.compareNumber(actualSummaryTotalHOCs, expectedSummaryTotalHOCs)) {
					Report.InfoTest(test, "Total HOCs is correct in Service Exceptions summary report Actual is : "
							+ actualSummaryTotalHOCs + " and Expected is : " + expectedSummaryTotalHOCs);
					Report.PassTest(test, "Total HOCs is as expected in Service Exceptions Summary report");
				} else {
					Report.FailTest(test, "Total HOCs is not as expected in Service Exceptions summary report Actual is : " + actualSummaryTotalHOCs
							+ " and Expected is : " + expectedSummaryTotalHOCs);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total HOCs is not as expected in Service Exceptions summary report Actual is : " + actualSummaryTotalHOCs
					+ " and Expected is : " + expectedSummaryTotalHOCs);
		}
		
	}

	private void validateSummaryPercentofMPUs(Map<String, List<String>> actualServiceExceptionsSummaryTable,
			Map<String, List<String>> actualServiceExceptionsSummaryDetailTable) {
		
		int actualSummaryPercentOfMPUs = 0;
		int expectedSummaryPercentOfMPUs = 0;	
		try {
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryTable.entrySet()) {
				if (entry.getKey().equals("% of MPUs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualSummaryPercentOfMPUs = 0;
						} else {
							actualSummaryPercentOfMPUs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualSummaryPercentOfMPUs, "% of MPUs in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("% of MPUs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedSummaryPercentOfMPUs = expectedSummaryPercentOfMPUs + Integer.parseInt("0.0");
						} else {
							expectedSummaryPercentOfMPUs = expectedSummaryPercentOfMPUs
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			// Summary % of MPUs = sum of % of MPUs of all sites
			
			if (actualSummaryPercentOfMPUs == 0) {
				Report.FailTest(test,
						"% of MPUs is not calculated as expected in Service Exceptions summary report Actual is : -- and Expected is : "
								+ expectedSummaryPercentOfMPUs);
			} else {
				if (Util.compareNumber(actualSummaryPercentOfMPUs, expectedSummaryPercentOfMPUs)) {
					Report.InfoTest(test, "% of MPUs is correct in Service Exceptions summary report Actual is : "
							+ actualSummaryPercentOfMPUs + " and Expected is : " + expectedSummaryPercentOfMPUs);
					Report.PassTest(test, "% of MPUs is as expected in Service Exceptions Summary report");
				} else {
					Report.FailTest(test, "% of MPUs is not as expected in Service Exceptions summary report Actual is : " + actualSummaryPercentOfMPUs
							+ " and Expected is : " + expectedSummaryPercentOfMPUs);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "% of MPUs is not as expected in Service Exceptions summary report Actual is : " + actualSummaryPercentOfMPUs
					+ " and Expected is : " + expectedSummaryPercentOfMPUs);
		}
		
	}

	private void validateSummaryTotalMPUs(Map<String, List<String>> actualServiceExceptionsSummaryTable,
			Map<String, List<String>> actualServiceExceptionsSummaryDetailTable) {
		
		int actualSummaryTotalMPUs = 0;
		int expectedSummaryTotalMPUs = 0;	
		try {
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total MPUs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualSummaryTotalMPUs = 0;
						} else {
							actualSummaryTotalMPUs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualSummaryTotalMPUs, "Total MPUs in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryDetailTable.entrySet()) {
				if (entry.getKey().contains("# of MPUs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedSummaryTotalMPUs = expectedSummaryTotalMPUs + Integer.parseInt("0.0");
						} else {
							expectedSummaryTotalMPUs = expectedSummaryTotalMPUs
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			// Summary Total MPUs = sum of Total MPUs of all sites
			
			if (actualSummaryTotalMPUs == 0) {
				Report.FailTest(test,
						"Total MPUs is not calculated as expected in Service Exceptions summary report Actual is : -- and Expected is : "
								+ expectedSummaryTotalMPUs);
			} else {
				if (Util.compareNumber(actualSummaryTotalMPUs, expectedSummaryTotalMPUs)) {
					Report.InfoTest(test, "Total MPUs is correct in Service Exceptions summary report Actual is : "
							+ actualSummaryTotalMPUs + " and Expected is : " + expectedSummaryTotalMPUs);
					Report.PassTest(test, "Total MPUs is as expected in Service Exceptions Summary report");
				} else {
					Report.FailTest(test, "Total MPUs is not as expected in Service Exceptions summary report Actual is : " + actualSummaryTotalMPUs
							+ " and Expected is : " + expectedSummaryTotalMPUs);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total MPUs is not as expected in Service Exceptions summary report Actual is : " + actualSummaryTotalMPUs
					+ " and Expected is : " + expectedSummaryTotalMPUs);
		}
		
	}

	private void validateSummaryPercentofETAs(Map<String, List<String>> actualServiceExceptionsSummaryTable,
			Map<String, List<String>> actualServiceExceptionsSummaryDetailTable) {
		int actualSummaryPercentOfETAs = 0;
		int expectedSummaryPercentOfETAs = 0;	
		try {
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryTable.entrySet()) {
				if (entry.getKey().equals("% of ETAs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualSummaryPercentOfETAs = 999;
						} else {
							actualSummaryPercentOfETAs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualSummaryPercentOfETAs, "% of ETAs in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("% of ETAs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedSummaryPercentOfETAs = expectedSummaryPercentOfETAs + Integer.parseInt("0.0");
						} else {
							expectedSummaryPercentOfETAs = expectedSummaryPercentOfETAs
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			// Summary % of ETAs = sum of % of ETAs of all sites
			
			if (actualSummaryPercentOfETAs == 999) {
				Report.FailTest(test,
						"% of ETAs is not calculated as expected in Service Exceptions summary report Actual is : -- and Expected is : "
								+ expectedSummaryPercentOfETAs);
			} else {
				if (Util.compareNumber(actualSummaryPercentOfETAs, expectedSummaryPercentOfETAs)) {
					Report.InfoTest(test, "% of ETAs is correct in Service Exceptions summary report Actual is : "
							+ actualSummaryPercentOfETAs + " and Expected is : " + expectedSummaryPercentOfETAs);
					Report.PassTest(test, "% of ETAs is as expected in Service Exceptions Summary report");
				} else {
					Report.FailTest(test, "% of ETAs is not as expected in Service Exceptions summary report Actual is : " + actualSummaryPercentOfETAs
							+ " and Expected is : " + expectedSummaryPercentOfETAs);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "% of ETAs is not as expected in Service Exceptions summary report Actual is : " + actualSummaryPercentOfETAs
					+ " and Expected is : " + expectedSummaryPercentOfETAs);
		}
		
	}

	private void validateSummaryTotalETAs(Map<String, List<String>> actualServiceExceptionsSummaryTable,
			Map<String, List<String>> actualServiceExceptionsSummaryDetailTable) {
		int actualSummaryTotalETAs = 0;
		int expectedSummaryTotalETAs = 0;	
		try {
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total ETAs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualSummaryTotalETAs = 0;
						} else {
							actualSummaryTotalETAs = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualSummaryTotalETAs, "Total ETAs in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryDetailTable.entrySet()) {
				if (entry.getKey().contains("# of ETAs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedSummaryTotalETAs = expectedSummaryTotalETAs + Integer.parseInt("0.0");
						} else {
							expectedSummaryTotalETAs = expectedSummaryTotalETAs
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			// Summary Total ETAs = sum of Total ETAs of all sites
			
			if (actualSummaryTotalETAs == 0) {
				Report.FailTest(test,
						"Total ETAs is not calculated as expected in Service Exceptions summary report Actual is : -- and Expected is : "
								+ expectedSummaryTotalETAs);
			} else {
				if (Util.compareNumber(actualSummaryTotalETAs, expectedSummaryTotalETAs)) {
					Report.InfoTest(test, "Total ETAs is correct in Service Exceptions summary report Actual is : "
							+ actualSummaryTotalETAs + " and Expected is : " + expectedSummaryTotalETAs);
					Report.PassTest(test, "Total ETAs is as expected in Service Exceptions Summary report");
				} else {
					Report.FailTest(test, "Total ETAs is not as expected in Service Exceptions summary report Actual is : " + actualSummaryTotalETAs
							+ " and Expected is : " + expectedSummaryTotalETAs);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total ETAs is not as expected in Service Exceptions summary report Actual is : " + actualSummaryTotalETAs
					+ " and Expected is : " + expectedSummaryTotalETAs);
		}
		
	}

	private void validateSummaryNumberofSites(Map<String, List<String>> actualServiceExceptionsSummaryTable,
			Map<String, List<String>> actualServiceExceptionsSummaryDetailTable) {
		
		// # of sites = sites displayed in the detail section
		int actualNumberOfSites = 0;
		int expectedNumberOfSites = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryTable.entrySet()) {
				if (entry.getKey().equals("# of Sites")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualNumberOfSites = 0;
						} else {
							actualNumberOfSites = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualNumberOfSites, "# of Sites in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Site")) {
					
					expectedNumberOfSites=entry.getValue().size();
				}
			}	
				if (Util.compareNumber(actualNumberOfSites, expectedNumberOfSites)) {
					Report.InfoTest(test, "# of Sites is correct in Service Exceptions summary report Actual is : "
							+ actualNumberOfSites + " and Expected is : " + expectedNumberOfSites);
					Report.PassTest(test, "# of Sites is as expected in Service Exceptions Summary report");
				} else {
					Report.FailTest(test, "# of Sites is not as expected in Service Exceptions summary report Actual is : " + actualNumberOfSites
							+ " and Expected is : " + expectedNumberOfSites);
				}
	
		} catch (Exception e) {
			Report.FailTest(test, "# of Sites is not as expected in Service Exceptions summary report Actual is : " + actualNumberOfSites
					+ " and Expected is : " + expectedNumberOfSites);
		}
		
	}

	public void validateServiceExceptionsSummaryDetailData(
			Map<String, List<String>> actualServiceExceptionsSummaryDetailTable,
			Map<String, List<String>> actualServiceExceptionsDetailSummaryTable, String siteID, String lOB) {
		
		//validateDetailTier(actualServiceExceptionsSummaryDetailTable,siteID);	
		validateDetailArea(actualServiceExceptionsSummaryDetailTable,siteID);	
		validateDetailBU(actualServiceExceptionsSummaryDetailTable,siteID);	
		validateDetailSite(actualServiceExceptionsSummaryDetailTable,siteID);	
		validateDetailTotalExceptions(actualServiceExceptionsSummaryDetailTable,actualServiceExceptionsDetailSummaryTable);	
		if(lOB.equals("Commercial"))
		{
		validateDetailPercentofHOCs(actualServiceExceptionsSummaryDetailTable,actualServiceExceptionsDetailSummaryTable);
		validateDetailNumberofHOCs(actualServiceExceptionsSummaryDetailTable,actualServiceExceptionsDetailSummaryTable);
		}
		if(lOB.equals("Commercial") || lOB.equals("Residential"))
		{
		validateDetailPercentofMPUs(actualServiceExceptionsSummaryDetailTable,actualServiceExceptionsDetailSummaryTable);
		validateDetailNumberofMPUs(actualServiceExceptionsSummaryDetailTable,actualServiceExceptionsDetailSummaryTable);
		validateDetailPercentofETAs(actualServiceExceptionsSummaryDetailTable,actualServiceExceptionsDetailSummaryTable);
		validateDetailNumberofETAs(actualServiceExceptionsSummaryDetailTable,actualServiceExceptionsDetailSummaryTable);
		}
		
	}
	
	private void validateDetailTotalExceptions(Map<String, List<String>> actualServiceExceptionsSummaryDetailTable,
			Map<String, List<String>> actualServiceExceptionsDetailSummaryTable) {
		
		int actualTotalExceptions = 0;
		int expectedTotalExceptions = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Total Exceptions")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualTotalExceptions = 999;
						} else {
							actualTotalExceptions = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualTotalExceptions, "Total Exceptions in Detail Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualServiceExceptionsDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Exceptions")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedTotalExceptions = 0;
						} else {
							expectedTotalExceptions = Integer.parseInt(entry.getValue().get(i));
						}
					

					}
				}
			}

		
			

			// Total Exceptions = Total Exceptions for corresponding site in detail report;
			

			if (actualTotalExceptions==999) {
				Report.FailTest(test,
						"Total Exceptions is not calculated as expected in Service Exceptions Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedTotalExceptions);
			} else {

				if (Util.compareNumber(actualTotalExceptions,expectedTotalExceptions)) {
					Report.InfoTest(test, "Total Exceptions is correct in Service Exceptions Summary report Detail Section. Actual is : "
							+ actualTotalExceptions + " and Expected is : " + expectedTotalExceptions);
					Report.PassTest(test, "Total Exceptions is as expected in Service Exceptions Summary report");
				} else {
					Report.FailTest(test, "Total Exceptions is not as expected in Service Exceptions Summary report Detail Section. Actual is : "
							+ actualTotalExceptions + " and Expected is : " + expectedTotalExceptions);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Exceptions is not as expected in Service Exceptions Summary report Detail Section. Actual is : "
					+ actualTotalExceptions + " and Expected is : " + expectedTotalExceptions);
		}
		
	}

	private void validateDetailPercentofHOCs(Map<String, List<String>> actualServiceExceptionsSummaryDetailTable,
			Map<String, List<String>> actualServiceExceptionsDetailSummaryTable) {
		
		int actualPercentOfHOCs = 0;
		int expectedPercentOfHOCs = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("% of HOCs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualPercentOfHOCs = 999;
						} else {
							actualPercentOfHOCs = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualPercentOfHOCs, "% of HOCs in Detail Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualServiceExceptionsDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("% of HOCs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedPercentOfHOCs = 0;
						} else {
							expectedPercentOfHOCs = Integer.parseInt(entry.getValue().get(i));
						}
					

					}
				}
			}

		
			

			// % of HOCs = % of HOCs for corresponding site in detail report;
			

			if (actualPercentOfHOCs==999) {
				Report.FailTest(test,
						"% of HOCs is not calculated as expected in Service Exceptions Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedPercentOfHOCs);
			} else {

				if (Util.compareNumber(actualPercentOfHOCs,expectedPercentOfHOCs)) {
					Report.InfoTest(test, "% of HOCs is correct in Service Exceptions Summary report Detail Section. Actual is : "
							+ actualPercentOfHOCs + " and Expected is : " + expectedPercentOfHOCs);
					Report.PassTest(test, "% of HOCs is as expected in Service Exceptions Summary report");
				} else {
					Report.FailTest(test, "% of HOCs is not as expected in Service Exceptions Summary report Detail Section. Actual is : "
							+ actualPercentOfHOCs + " and Expected is : " + expectedPercentOfHOCs);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "% of HOCs is not as expected in Service Exceptions Summary report Detail Section. Actual is : "
					+ actualPercentOfHOCs + " and Expected is : " + expectedPercentOfHOCs);
		}
		
	}

	private void validateDetailNumberofHOCs(Map<String, List<String>> actualServiceExceptionsSummaryDetailTable,
			Map<String, List<String>> actualServiceExceptionsDetailSummaryTable) {
		
		int actualNumberOfHOCs = 0;
		int expectedNumberOfHOCs = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("# of HOCs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualNumberOfHOCs = 999;
						} else {
							actualNumberOfHOCs = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualNumberOfHOCs, "# of HOCs in Detail Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualServiceExceptionsDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total HOCs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedNumberOfHOCs = 0;
						} else {
							expectedNumberOfHOCs = Integer.parseInt(entry.getValue().get(i));
						}
					

					}
				}
			}

		
			

			// # of HOCs = # of HOCs for corresponding site in detail report;
			

			if (actualNumberOfHOCs==999) {
				Report.FailTest(test,
						"# of HOCs is not calculated as expected in Service Exceptions Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedNumberOfHOCs);
			} else {

				if (Util.compareNumber(actualNumberOfHOCs,expectedNumberOfHOCs)) {
					Report.InfoTest(test, "# of HOCs is correct in Service Exceptions Summary report Detail Section. Actual is : "
							+ actualNumberOfHOCs + " and Expected is : " + expectedNumberOfHOCs);
					Report.PassTest(test, "# of HOCs is as expected in Service Exceptions Summary report");
				} else {
					Report.FailTest(test, "# of HOCs is not as expected in Service Exceptions Summary report Detail Section. Actual is : "
							+ actualNumberOfHOCs + " and Expected is : " + expectedNumberOfHOCs);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "# of HOCs is not as expected in Service Exceptions Summary report Detail Section. Actual is : "
					+ actualNumberOfHOCs + " and Expected is : " + expectedNumberOfHOCs);
		}
		
	}

	private void validateDetailPercentofMPUs(Map<String, List<String>> actualServiceExceptionsSummaryDetailTable,
			Map<String, List<String>> actualServiceExceptionsDetailSummaryTable) {
		
		int actualPercentOfMPUs = 0;
		int expectedPercentOfMPUs = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("% of MPUs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualPercentOfMPUs = 999;
						} else {
							actualPercentOfMPUs = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualPercentOfMPUs, "% of MPUs in Detail Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualServiceExceptionsDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("% of MPUs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedPercentOfMPUs = 0;
						} else {
							expectedPercentOfMPUs = Integer.parseInt(entry.getValue().get(i));
						}
					

					}
				}
			}

		
			

			// % of MPUs = % of MPUs for corresponding site in detail report;
			

			if (actualPercentOfMPUs==999) {
				Report.FailTest(test,
						"% of MPUs is not calculated as expected in Service Exceptions Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedPercentOfMPUs);
			} else {

				if (Util.compareNumber(actualPercentOfMPUs,expectedPercentOfMPUs)) {
					Report.InfoTest(test, "% of MPUs is correct in Service Exceptions Summary report Detail Section. Actual is : "
							+ actualPercentOfMPUs + " and Expected is : " + expectedPercentOfMPUs);
					Report.PassTest(test, "% of MPUs is as expected in Service Exceptions Summary report");
				} else {
					Report.FailTest(test, "% of MPUs is not as expected in Service Exceptions Summary report Detail Section. Actual is : "
							+ actualPercentOfMPUs + " and Expected is : " + expectedPercentOfMPUs);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "% of MPUs is not as expected in Service Exceptions Summary report Detail Section. Actual is : "
					+ actualPercentOfMPUs + " and Expected is : " + expectedPercentOfMPUs);
		}
		
	}

	private void validateDetailNumberofMPUs(Map<String, List<String>> actualServiceExceptionsSummaryDetailTable,
			Map<String, List<String>> actualServiceExceptionsDetailSummaryTable) {
		
		int actualNumberOfMPUs = 0;
		int expectedNumberOfMPUs = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("# of MPUs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualNumberOfMPUs = 999;
						} else {
							actualNumberOfMPUs = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualNumberOfMPUs, "# of MPUs in Detail Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualServiceExceptionsDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total MPUs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedNumberOfMPUs = 0;
						} else {
							expectedNumberOfMPUs = Integer.parseInt(entry.getValue().get(i));
						}
					

					}
				}
			}

		
			

			// # of MPUs = # of MPUs for corresponding site in detail report;
			

			if (actualNumberOfMPUs==999) {
				Report.FailTest(test,
						"# of MPUs is not calculated as expected in Service Exceptions Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedNumberOfMPUs);
			} else {

				if (Util.compareNumber(actualNumberOfMPUs,expectedNumberOfMPUs)) {
					Report.InfoTest(test, "# of MPUs is correct in Service Exceptions Summary report Detail Section. Actual is : "
							+ actualNumberOfMPUs + " and Expected is : " + expectedNumberOfMPUs);
					Report.PassTest(test, "# of MPUs is as expected in Service Exceptions Summary report");
				} else {
					Report.FailTest(test, "# of MPUs is not as expected in Service Exceptions Summary report Detail Section. Actual is : "
							+ actualNumberOfMPUs + " and Expected is : " + expectedNumberOfMPUs);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "# of MPUs is not as expected in Service Exceptions Summary report Detail Section. Actual is : "
					+ actualNumberOfMPUs + " and Expected is : " + expectedNumberOfMPUs);
		}
		
	}

	private void validateDetailPercentofETAs(Map<String, List<String>> actualServiceExceptionsSummaryDetailTable,
			Map<String, List<String>> actualServiceExceptionsDetailSummaryTable) {
		
		int actualPercentOfETAs = 0;
		int expectedPercentOfETAs = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("% of ETAs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualPercentOfETAs = 999;
						} else {
							actualPercentOfETAs = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualPercentOfETAs, "% of ETAs in Detail Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualServiceExceptionsDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("% of ETAs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedPercentOfETAs = 0;
						} else {
							expectedPercentOfETAs = Integer.parseInt(entry.getValue().get(i));
						}
					

					}
				}
			}

		
			

			// % of ETAs = % of ETAs for corresponding site in detail report;
			

			if (actualPercentOfETAs==999) {
				Report.FailTest(test,
						"% of ETAs is not calculated as expected in Service Exceptions Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedPercentOfETAs);
			} else {

				if (Util.compareNumber(actualPercentOfETAs,expectedPercentOfETAs)) {
					Report.InfoTest(test, "% of ETAs is correct in Service Exceptions Summary report Detail Section. Actual is : "
							+ actualPercentOfETAs + " and Expected is : " + expectedPercentOfETAs);
					Report.PassTest(test, "% of ETAs is as expected in Service Exceptions Summary report");
				} else {
					Report.FailTest(test, "% of ETAs is not as expected in Service Exceptions Summary report Detail Section. Actual is : "
							+ actualPercentOfETAs + " and Expected is : " + expectedPercentOfETAs);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "% of ETAs is not as expected in Service Exceptions Summary report Detail Section. Actual is : "
					+ actualPercentOfETAs + " and Expected is : " + expectedPercentOfETAs);
		}
		
	}

	private void validateDetailNumberofETAs(Map<String, List<String>> actualServiceExceptionsSummaryDetailTable,
			Map<String, List<String>> actualServiceExceptionsDetailSummaryTable) {
		
		int actualNumberOfETAs = 0;
		int expectedNumberOfETAs = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualServiceExceptionsSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("# of ETAs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualNumberOfETAs = 0;
						} else {
							actualNumberOfETAs = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualNumberOfETAs, "# of ETAs in Detail Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualServiceExceptionsDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total ETAs")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedNumberOfETAs = 0;
						} else {
							expectedNumberOfETAs = Integer.parseInt(entry.getValue().get(i));
						}
					

					}
				}
			}

		
			

			// # of ETAs = # of ETAs for corresponding site in detail report;
			

			if (actualNumberOfETAs==0) {
				Report.FailTest(test,
						"# of ETAs is not calculated as expected in Service Exceptions Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedNumberOfETAs);
			} else {

				if (Util.compareNumber(actualNumberOfETAs,expectedNumberOfETAs)) {
					Report.InfoTest(test, "# of ETAs is correct in Service Exceptions Summary report Detail Section. Actual is : "
							+ actualNumberOfETAs + " and Expected is : " + expectedNumberOfETAs);
					Report.PassTest(test, "# of ETAs is as expected in Service Exceptions Summary report");
				} else {
					Report.FailTest(test, "# of ETAs is not as expected in Service Exceptions Summary report Detail Section. Actual is : "
							+ actualNumberOfETAs + " and Expected is : " + expectedNumberOfETAs);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "# of ETAs is not as expected in Service Exceptions Summary report Detail Section. Actual is : "
					+ actualNumberOfETAs + " and Expected is : " + expectedNumberOfETAs);
		}
		
	}

	private void validateDetailArea(Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable,
			String site) {
		String actualAreaName=null;
		List<String> expectedAreaName = null;
		String siteID=site.substring(0, 6);
		try {
			
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Area")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualAreaName = "--";
						} else {
							actualAreaName = entry.getValue().get(i).toString();
						}
					}
				}
			}

			expectedAreaName=Util.getDataFromDB("select t.CMARKETAREA.NAME from ocs_admin.vp_site t where pkey in (select PKEY from ocs_admin.tp_site where UNIQUEID like '%"+ siteID +"%')");
				
				if (actualAreaName.equals(expectedAreaName.get(0))) {
					Report.InfoTest(test, "Area is correct in Service Exceptions Summary report Detail Section. Actual is : "
							+ actualAreaName + " and Expected is : " + expectedAreaName.get(0));
					Report.PassTest(test, "Area is as expected in Service Exceptions Summary report Detail Section");
				} else {
					Report.FailTest(test, "Area is not as expected in Service Exceptions Summary report Detail Section. Actual is : " + actualAreaName
							+ " and Expected is : " + expectedAreaName.get(0));
				}
			
		} catch (Exception e) {
			Report.FailTest(test, "Area is not as expected in Service Exceptions Summary report Detail Section. Actual is : " + actualAreaName
					+ " and Expected is : " + expectedAreaName.get(0));
		}
		
	}

	private void validateDetailBU(Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable, String site) {
		
		String actualBUName=null;
		List<String> expectedBUName = null;
		String siteID=site.substring(0, 6);
		try {
			
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("BU")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualBUName = "--";
						} else {
							actualBUName = entry.getValue().get(i).toString();
						}
					}
				}
			}

		expectedBUName=Util.getDataFromDB("select t.CBUSINESSUNIT.NAME from ocs_admin.vp_site t where pkey in (select PKEY from ocs_admin.tp_site where UNIQUEID like '%"+ siteID +"%')");
				
				if (actualBUName.equals(expectedBUName.get(0))) {
					Report.InfoTest(test, "BU is correct in Service Exceptions Summary report Detail Section. Actual is : "
							+ actualBUName + " and Expected is : " + expectedBUName.get(0));
					Report.PassTest(test, "BU is as expected in Service Exceptions Summary report Detail Section");
				} else {
					Report.FailTest(test, "BU is not as expected in Service Exceptions Summary report Detail Section. Actual is : " + actualBUName
							+ " and Expected is : " + expectedBUName.get(0));
				}
			
		} catch (Exception e) {
			Report.FailTest(test, "BU is not as expected in Service Exceptions Summary report Detail Section. Actual is : " + actualBUName
					+ " and Expected is : " + expectedBUName.get(0));
		}
		
	}

	private void validateDetailSite(Map<String, List<String>> actualSequenceComplinaceSummaryDetailTable,
			String site) {
		String actualSiteName=null;
		String expectedSiteName = site;
			
		try {
			
			for (Entry<String, List<String>> entry : actualSequenceComplinaceSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Site")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualSiteName = "--";
						} else {
							actualSiteName = entry.getValue().get(i).toString();
						}
					}
				}
			}

			
				if (actualSiteName.equals(expectedSiteName)) {
					Report.InfoTest(test, "Site is correct in Service Exceptions Summary report Detail Section. Actual is : "
							+ actualSiteName + " and Expected is : " + expectedSiteName);
					Report.PassTest(test, "Site is as expected in Service Exceptions Summary report Detail Section");
				} else {
					Report.FailTest(test, "Site is not as expected in Service Exceptions Summary report Detail Section. Actual is : " + actualSiteName
							+ " and Expected is : " + expectedSiteName);
				}
			
		} catch (Exception e) {
			Report.FailTest(test, "Site is not as expected in Service Exceptions Summary report Detail Section. Actual is : " + actualSiteName
					+ " and Expected is : " + expectedSiteName);
		}
		
	}
	

}
