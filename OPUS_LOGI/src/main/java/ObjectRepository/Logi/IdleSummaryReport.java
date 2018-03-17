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
import TestData.GlobalExpectedColumns;

public class IdleSummaryReport {
	
	ExtentTest test;
	WebDriver driver;

	public IdleSummaryReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//table[@id='dtIdleTimeSummary']/thead/tr/th")
	List<WebElement> idleSummaryTableColumns;

	@FindBy(xpath = "//table[@id='dtIdleTimeSummary']/tbody/tr")
	List<WebElement> idleSummaryTableRows;
	
	@FindBy(xpath = "//table[@id='dtIdleTmDetail']/thead/tr/th")
	List<WebElement> idleDetailTableColumns;

	@FindBy(xpath = "//table[@id='dtIdleTmDetail']/tbody/tr")
	List<WebElement> idleDetailTableRows;
	
	@FindBy(xpath="(//*[@id='dtExcpPopUp']/tbody/tr/td[contains(@id,'colRteMgr_Row1')]/span)[1]")
    WebElement ExpectedRoutemanagerinPopUp;
    
    @FindBy(xpath="(//*[@id='dtExcpPopUp']/tbody/tr/td[contains(@id,'colDriver_Row1')]/a/span)[1]")
    WebElement ExpectedDriverInPopUp;
    
    @FindBy(xpath="(//*[@id='dtExcpPopUp']/tbody/tr/td[contains(@id,'colExcpTime_Row1')]/span)[1]")
    WebElement ExpectedExceptionTimeinPopUp;
    
    @FindBy(xpath="(//*[@id='dtExcpPopUp']/tbody/tr/td[contains(@id,'colIdleTime_Row1')]/span)[1]")
    WebElement ExpectedNewIdleinPopUp;
    
    @FindBy(xpath="//*[@id='dtExcpPopUp']/tbody/tr/td[contains(@id,'colDriver_Row1')]/a")
    WebElement ClickOnFirstDriver;
    
    @FindBy(xpath="//*[@id='lblReportTitle']")
    WebElement GetDetailtableTitle;
    
    @FindBy(xpath="//*[@id='divRMFilter']//span")
    WebElement GetRouteManageName;
    
    @FindBy(xpath="//*[@id='divDriverFilter']//span")
    WebElement GetDriverName;
    
    @FindBy(xpath="//*[@id='lblCrumb1']")
    WebElement BacktoPreRouteSummaryReport;
    
    @FindBy(xpath="//*[@id='lblSite']") 
    WebElement ValidateSite;
    
    @FindBy(xpath="//*[@id='lblExcpDateRange']")
    WebElement ValidateDateRange;

	public Map<String, List<String>> getActualSummaryTableData() {
		System.out.println("inside Actual Summary Data");
		Map<String, List<String>> summaryTableData = new HashMap<>();
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		try {
			summaryTableData = readTable(idleSummaryTableColumns, idleSummaryTableRows, "dtIdleTimeSummary");
			Report.PassTestScreenshot(test, driver, "Summary table data read successfully", "Idle Summary Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read summary table data");
		}

		for (Entry<String, List<String>> entry : summaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return summaryTableData;
	}

	public Map<String, List<String>> getActualDetailTableData() {
		
		System.out.println("inside Actual Idle Summary Detail Table Data");
		
		Map<String, List<String>> idleSummaryDetailTableData = new HashMap<>();
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		
		try {
			idleSummaryDetailTableData = readTable(idleDetailTableColumns, idleDetailTableRows, "dtIdleTmDetail");
			Report.PassTestScreenshot(test, driver, "Actual Idle Summary Detail table data read successfully",
					"Idle Summary Detail Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Idle Summary Detail table data");
		}
		for (Entry<String, List<String>> entry : idleSummaryDetailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return idleSummaryDetailTableData;
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

	public void validateIdleSummaryData(Map<String, List<String>> actualIdleSummaryTable,
			Map<String, List<String>> actualIdleSummaryDetailTable, String lOB) {


		ValidateSummaryTotalNetIdleTime(actualIdleSummaryTable,actualIdleSummaryDetailTable);
		ValidateSummaryTotalIdleEvents(actualIdleSummaryTable,actualIdleSummaryDetailTable);
		ValidateSummaryAvgNetIdleTime(actualIdleSummaryTable,actualIdleSummaryDetailTable); 
		ValidateSummaryNoOfSites(actualIdleSummaryTable,actualIdleSummaryDetailTable);
		
	}

	public void validateIdleSummaryDetailData(Map<String, List<String>> actualIdleSummaryDetailTable,
			Map<String, List<String>> actualIdleDetailSummaryTable,String site, String lOB) {
		
		//validateDetailTier(actualIdleSummaryDetailTable,site);
		validateDetailArea(actualIdleSummaryDetailTable,site);
		validateDetailBU(actualIdleSummaryDetailTable,site);
		validateDetailSite(actualIdleSummaryDetailTable,site);
		validateDetailNetIdleTime(actualIdleSummaryDetailTable,actualIdleDetailSummaryTable);
		validateDetailNoOfIdleEvents(actualIdleSummaryDetailTable,actualIdleDetailSummaryTable);
		validateDetailAvgNetIdleTime(actualIdleSummaryDetailTable);
		
	}
	
	private void validateDetailArea(Map<String, List<String>> actualIdleSummaryDetailTable, String site) {
		
		String actualAreaName=null;
		List<String> expectedAreaName = null;
		String siteID=site.substring(0, 6);
		try {
			
			for (Entry<String, List<String>> entry : actualIdleSummaryDetailTable.entrySet()) {
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
					Report.InfoTest(test, "Area is correct in Idle Summary report Detail Section. Actual is : "
							+ actualAreaName + " and Expected is : " + expectedAreaName.get(0));
					Report.PassTest(test, "Area is as expected in Idle Summary report Detail Section");
				} else {
					Report.FailTest(test, "Area is not as expected in Idle Summary report Detail Section. Actual is : " + actualAreaName
							+ " and Expected is : " + expectedAreaName.get(0));
				}
			
		} catch (Exception e) {
			Report.FailTest(test, "Area is not as expected in Idle Summary report Detail Section. Actual is : " + actualAreaName
					+ " and Expected is : " + expectedAreaName.get(0));
		}
		
		
		
	}

	private void validateDetailBU(Map<String, List<String>> actualIdleSummaryDetailTable, String site) {
		
		String actualBUName=null;
		List<String> expectedBUName = null;
		String siteID=site.substring(0, 6);
		try {
			
			for (Entry<String, List<String>> entry : actualIdleSummaryDetailTable.entrySet()) {
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
					Report.InfoTest(test, "BU is correct in Idle Summary report Detail Section. Actual is : "
							+ actualBUName + " and Expected is : " + expectedBUName.get(0));
					Report.PassTest(test, "BU is as expected in Idle Summary report Detail Section");
				} else {
					Report.FailTest(test, "BU is not as expected in Idle Summary report Detail Section. Actual is : " + actualBUName
							+ " and Expected is : " + expectedBUName.get(0));
				}
			
		} catch (Exception e) {
			Report.FailTest(test, "BU is not as expected in Idle Summary report Detail Section. Actual is : " + actualBUName
					+ " and Expected is : " + expectedBUName.get(0));
		}
		
	}
		

	private void validateDetailSite(Map<String, List<String>> actualIdleSummaryDetailTable, String site) {
		
		String actualSiteName=null;
		String expectedSiteName = site;
			
		try {
			
			for (Entry<String, List<String>> entry : actualIdleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Site")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualSiteName = "00:00";
						} else {
							actualSiteName = entry.getValue().get(i).toString();
						}
					}
				}
			}

			
				if (actualSiteName.equals(expectedSiteName)) {
					Report.InfoTest(test, "Site is correct in Idle Summary report Detail Section. Actual is : "
							+ actualSiteName + " and Expected is : " + expectedSiteName);
					Report.PassTest(test, "Site is as expected in Idle Summary report Detail Section");
				} else {
					Report.FailTest(test, "Site is not as expected in Idle Summary report Detail Section. Actual is : " + actualSiteName
							+ " and Expected is : " + expectedSiteName);
				}
			
		} catch (Exception e) {
			Report.FailTest(test, "Site is not as expected in Idle Summary report Detail Section. Actual is : " + actualSiteName
					+ " and Expected is : " + expectedSiteName);
		}
		
	}

	private void validateDetailAvgNetIdleTime(Map<String, List<String>> actualIdleSummaryDetailTable) {
		
		int totalIdleEvents = 0;
		String totalNetIdleTime=null;
		String actualAvgNetIdleTime = null;
		String expectedAvgNetIdleTime = null;	
		try {
			
			for (Entry<String, List<String>> entry : actualIdleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Net Idle Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							totalNetIdleTime = "00:00";
						} else {
							totalNetIdleTime = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualIdleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("# of Idle Events")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							totalIdleEvents = 0;
						} else {
							totalIdleEvents = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));							
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualIdleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Avg Net Idle Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualAvgNetIdleTime = "00:00";
						} else {
							actualAvgNetIdleTime = entry.getValue().get(i).toString();
							Util.validateTimeFormat(entry.getValue().get(i).toString(), "Avg Net Idle Time (h:m) in Idle Summary Report Detail section ");
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
						"Avg Net Idle Time is not calculated as expected in Idle Summary report Detail Section. Actual is : -- and Expected is : "
								+ expectedAvgNetIdleTime);
			} else {
				if (Util.compareTime(actualAvgNetIdleTime, expectedAvgNetIdleTime)) {
					Report.InfoTest(test, "Avg Net Idle Time is correct in Idle Summary report Detail Section. Actual is : "
							+ actualAvgNetIdleTime + " and Expected is : " + expectedAvgNetIdleTime);
					Report.PassTest(test, "Avg Net Idle Time is as expected in Idle Summary report Detail Section");
				} else {
					Report.FailTest(test, "Avg Net Idle Time is not as expected in Idle Summary report Detail Section. Actual is : " + actualAvgNetIdleTime
							+ " and Expected is : " + expectedAvgNetIdleTime);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Avg Net Idle Time is not as expected in Idle Summary report Detail Section. Actual is : " + actualAvgNetIdleTime
					+ " and Expected is : " + expectedAvgNetIdleTime);
		}
		
	}

	private void validateDetailNoOfIdleEvents(Map<String, List<String>> actualIdleSummaryDetailTable,
			Map<String, List<String>> actualIdleDetailSummaryTable) {
		
		int actualNumberOfIdleEvents = 0;
		int expectedNumberOfIdleEvents = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualIdleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("# of Idle Events")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualNumberOfIdleEvents = 0;
						} else {
							actualNumberOfIdleEvents = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualNumberOfIdleEvents, "# of Idle Events in Detail Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualIdleDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Idle Events")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedNumberOfIdleEvents = 0;
						} else {
							expectedNumberOfIdleEvents = Integer.parseInt(entry.getValue().get(i));
						}
					

					}
				}
			}

		
			

			// # of idle events = Total number of idle events for corresponding site in detail report;
			

			if (actualNumberOfIdleEvents==0) {
				Report.FailTest(test,
						"# of Idle Events is not calculated as expected in Idle Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedNumberOfIdleEvents);
			} else {

				if (Util.compareNumber(actualNumberOfIdleEvents,expectedNumberOfIdleEvents)) {
					Report.InfoTest(test, "# of Idle Events is correct in Idle Summary report Detail Section. Actual is : "
							+ actualNumberOfIdleEvents + " and Expected is : " + expectedNumberOfIdleEvents);
					Report.PassTest(test, "# of Idle Events is as expected in Summary report");
				} else {
					Report.FailTest(test, "# of Idle Events is not as expected in Idle Summary report Detail Section. Actual is : "
							+ actualNumberOfIdleEvents + " and Expected is : " + expectedNumberOfIdleEvents);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "# of Idle Events is not as expected in Idle Summary report Detail Section. Actual is : "
					+ actualNumberOfIdleEvents + " and Expected is : " + expectedNumberOfIdleEvents);
		}
		
	}

	private void validateDetailNetIdleTime(Map<String, List<String>> actualIdleSummaryDetailTable,
			Map<String, List<String>> actualIdleDetailSummaryTable) {
		
		String actualNetIdleTime = null;
		String expectedNetIdleTime = null;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : actualIdleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Net Idle Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualNetIdleTime = "00:00";
						} else {
							actualNetIdleTime = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualIdleDetailSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Net Idle Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedNetIdleTime = "00:00";
						} else {
							expectedNetIdleTime = entry.getValue().get(i).toString();
						}
					

					}
				}
			}

			// Validate Time Format of Net Idle Time
			Util.validateTimeFormat(actualNetIdleTime, "Net Idle Time in Detail Section");

			// expectedNetIdleTime=sum of idle time of each
			// routes
			// expectedActualDriverHours=String.format("%02d",(int)Math.floor(totalMins/60))
			// + ":" +String.format("%02d",( totalMins % 60 ));
			

			if (actualNetIdleTime.equals("00:00")) {
				Report.FailTest(test,
						"Net Idle Time is not calculated as expected in Idle Summary report Detail Section Actual is : -- and Expected is : "
								+ expectedNetIdleTime);
			} else {

				if (actualNetIdleTime.equals(expectedNetIdleTime)) {
					Report.InfoTest(test, "Net Idle Time is correct in Idle Summary report Detail Section. Actual is : "
							+ actualNetIdleTime + " and Expected is : " + expectedNetIdleTime);
					Report.PassTest(test, "Net Idle Time is as expected in Summary report");
				} else {
					Report.FailTest(test, "Net Idle Time is not as expected in Idle Summary report Detail Section. Actual is : "
							+ actualNetIdleTime + " and Expected is : " + expectedNetIdleTime);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Net Idle Time is not as expected in Idle Summary report Detail Section. Actual is : "
					+ actualNetIdleTime + " and Expected is : " + expectedNetIdleTime);
		}
		
	}

	private void ValidateSummaryTotalNetIdleTime(Map<String, List<String>> actualIdleSummaryTable,
			Map<String, List<String>> actualIdleSummaryDetailTable) {
		
		String actualSummaryTotalNetIdleTime = null;
		String expectedSummaryTotalNetIdleTime = null;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : actualIdleSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Net Idle Time(h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualSummaryTotalNetIdleTime = "99:99";
						} else {
							actualSummaryTotalNetIdleTime = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualIdleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Net Idle Time (h:m)")) {
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
			Util.validateTimeFormat(actualSummaryTotalNetIdleTime, "Net Idle Time in Summary Section");

			// expectedNetIdleTime=sum of idle time of each
			// routes
			// expectedActualDriverHours=String.format("%02d",(int)Math.floor(totalMins/60))
			// + ":" +String.format("%02d",( totalMins % 60 ));
			expectedSummaryTotalNetIdleTime = Util.convertMinsToHours(totalMins);

			if (actualSummaryTotalNetIdleTime.equals("99:99")) {
				/*Report.FailTest(test,
						"Total Net Idle Time is not calculated as expected in summary report Actual is : -- and Expected is : "
								+ expectedSummaryTotalNetIdleTime);*/
			} else {

				if (Util.compareTime(actualSummaryTotalNetIdleTime, expectedSummaryTotalNetIdleTime)) {
					Report.InfoTest(test, "Total Net Idle Time is correct in summary report Actual is : "
							+ actualSummaryTotalNetIdleTime + " and Expected is : " + expectedSummaryTotalNetIdleTime);
					Report.PassTest(test, "Total Net Idle Time is as expected in Summary report");
				} else {
					Report.FailTest(test, "Total Net Idle Time is not as expected in summary report Actual is : "
							+ actualSummaryTotalNetIdleTime + " and Expected is : " + expectedSummaryTotalNetIdleTime);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Net Idle Time is not as expected in summary report Actual is : "
					+ actualSummaryTotalNetIdleTime + " and Expected is : " + expectedSummaryTotalNetIdleTime);
		}
		
	}

	private void ValidateSummaryTotalIdleEvents(Map<String, List<String>> actualIdleSummaryTable,
			Map<String, List<String>> actualIdleSummaryDetailTable) {
		
		int actualSummaryTotalIdleEvents = 0;
		int expectedSummaryTotalIdleEvents = 0;	
		try {
			for (Entry<String, List<String>> entry : actualIdleSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Idle Events")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualSummaryTotalIdleEvents = 99;
						} else {
							actualSummaryTotalIdleEvents = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateWholeNumber(actualSummaryTotalIdleEvents, "Total Idle Events in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualIdleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("# of Idle Events")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedSummaryTotalIdleEvents = expectedSummaryTotalIdleEvents + Integer.parseInt("0.0");
						} else {
							expectedSummaryTotalIdleEvents = expectedSummaryTotalIdleEvents
									+ Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));
							
						}
					}

				
				}
			}

			// Summary Total Idle Events = sum of idle occurences of all routes
			
			if (actualSummaryTotalIdleEvents == 99) {
				Report.FailTest(test,
						"Total Idle Events is not calculated as expected in Idle summary report Actual is : -- and Expected is : "
								+ expectedSummaryTotalIdleEvents);
			} else {
				if (Util.compareNumber(actualSummaryTotalIdleEvents, expectedSummaryTotalIdleEvents)) {
					Report.InfoTest(test, "Total Idle Events is correct in Idle summary report Actual is : "
							+ actualSummaryTotalIdleEvents + " and Expected is : " + expectedSummaryTotalIdleEvents);
					Report.PassTest(test, "Total Idle Events is as expected in Idle Summary report");
				} else {
					Report.FailTest(test, "Total Idle Events is not as expected in Idle summary report Actual is : " + actualSummaryTotalIdleEvents
							+ " and Expected is : " + expectedSummaryTotalIdleEvents);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Total Idle Events is not as expected in Idle summary report Actual is : " + actualSummaryTotalIdleEvents
					+ " and Expected is : " + expectedSummaryTotalIdleEvents);
		}
		
	}

	private void ValidateSummaryAvgNetIdleTime(Map<String, List<String>> actualIdleSummaryTable,
			Map<String, List<String>> actualIdleSummaryDetailTable) {
		
		int totalIdleEvents = 0;
		String totalNetIdleTime=null;
		String actualAvgNetIdleTime = null;
		String expectedAvgNetIdleTime = null;	
		try {
			
			for (Entry<String, List<String>> entry : actualIdleSummaryTable.entrySet()) {
				if (entry.getKey().equals("Total Net Idle Time(h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							totalNetIdleTime = "00:00";
						} else {
							totalNetIdleTime = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualIdleSummaryTable.entrySet()) {
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
			for (Entry<String, List<String>> entry : actualIdleSummaryTable.entrySet()) {
				if (entry.getKey().equals("Avg Net Idle Time(h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualAvgNetIdleTime = "99:99";
						} else {
							actualAvgNetIdleTime = entry.getValue().get(i).toString();
							Util.validateTimeFormat(actualAvgNetIdleTime, "Avg Net Idle Time in Summary section");
						}
					}

				
				}
			}

			// Avg Net Idle Time = Total Net Idle time / Total Idle Events
			int netIdleTimeInMins=Util.convertHoursToMins(totalNetIdleTime);			
			int expectedAvgIdleTimeInMins=netIdleTimeInMins/totalIdleEvents;
			
			expectedAvgNetIdleTime=Util.convertMinsToHours(expectedAvgIdleTimeInMins);
			
			if (actualAvgNetIdleTime.equals("99:99")) {
				Report.FailTest(test,
						"Total Idle Events is not calculated as expected in summary report Actual is : -- and Expected is : "
								+ expectedAvgNetIdleTime);
			} else {
				if (Util.compareTime(actualAvgNetIdleTime, expectedAvgNetIdleTime)) {
					Report.InfoTest(test, "Avg Net Idle Time is correct in  summary report Actual is : "
							+ actualAvgNetIdleTime + " and Expected is : " + expectedAvgNetIdleTime);
					Report.PassTest(test, "Avg Net Idle Time is as expected in Summary report");
				} else {
					Report.FailTest(test, "Avg Net Idle Time is not as expected in summary report Actual is : " + actualAvgNetIdleTime
							+ " and Expected is : " + expectedAvgNetIdleTime);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Avg Net Idle Time is not as expected in summary report Actual is : " + actualAvgNetIdleTime
					+ " and Expected is : " + expectedAvgNetIdleTime);
		}
		
	}

	private void ValidateSummaryNoOfSites(Map<String, List<String>> actualIdleSummaryTable,
			Map<String, List<String>> actualIdleSummaryDetailTable) {
		
		// # of sites = sites displayed in the detail section
		int actualNumberOfSites = 0;
		int expectedNumberOfSites = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualIdleSummaryTable.entrySet()) {
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
			for (Entry<String, List<String>> entry : actualIdleSummaryDetailTable.entrySet()) {
				if (entry.getKey().equals("Site")) {
					
					expectedNumberOfSites=entry.getValue().size();
				}
			}	
				if (Util.compareNumber(actualNumberOfSites, expectedNumberOfSites)) {
					Report.InfoTest(test, "# of Sites is correct in Idle summary report Actual is : "
							+ actualNumberOfSites + " and Expected is : " + expectedNumberOfSites);
					Report.PassTest(test, "# of Sites is as expected in Idle Summary report");
				} else {
					Report.FailTest(test, "# of Sites is not as expected in Idle summary report Actual is : " + actualNumberOfSites
							+ " and Expected is : " + expectedNumberOfSites);
				}
	
		} catch (Exception e) {
			Report.FailTest(test, "# of Sites is not as expected in Idle summary report Actual is : " + actualNumberOfSites
					+ " and Expected is : " + expectedNumberOfSites);
		}
		
	}

	
	public void ValidatePopUp(String Site, String FromDate, String ToDate,String ReportName,String downloadFilePath) {
     	Popupclick("dtIdleTmDetail");
     	ValidatefixedText(Site, FromDate, ToDate);  	
     	ValidateColumnNameinPopUp();
     	ValidateExportExcelButton(ReportName, downloadFilePath);
     	ValidatePopUpDatailsDetailReport("Idle Detail","Idle Summary","dtIdleTimeSummary");
     }  
     
     
     
    
     
     
     
     private void Popupclick(String DetailTableName) {
     	int NumofRecords=0;
     	
     	try {
     		Util.switchToDefaultWindow();
     		Util.selectFrame("opusReportContainer,subReportContainer");
     		NumofRecords=driver.findElements(By.xpath("//*[@id='"+DetailTableName+"']/tbody/tr")).size();
     		if (NumofRecords!=0) {
     			driver.findElement(By.xpath("//*[@id='"+DetailTableName+"']/tbody/tr/td[contains(@id,'colSite_Row1')]/span")).click();
     			Thread.sleep(5000);
     			Util.pageScroll(0, 400);
     			Report.PassTestScreenshot(test, driver, "Clicked on first record", "PassedClickedFirstRecord");
     			
     			
 			}
     		else {
 				Report.FailTestSnapshot(test, driver, "Not Found Any Record", "FailedNoRecordsExist");
 			}
     		
 		} catch (Exception e) {
 			Report.FailTest(test, "popup click Failed");
 		}
     	
     	
     	
     	
     }

     private void ValidatefixedText(String Site, String FromDate, String ToDate) {
     	String ActualSite=null;
     	String dateRangeText=null;
     	String actualFromDate=null;
 		String actualToDate=null;
 		String actualSiteName=null;
     	try {
     		Util.switchToDefaultWindow();
     		Util.selectFrame("opusReportContainer,subReportContainer");
     		ActualSite=ValidateSite.getText();
     		String SitePrts[]=ActualSite.split("for");  
     		actualSiteName=SitePrts[1].substring(1,27);
     		if (ActualSite.contains(Site)) {
     			Report.PassTestScreenshot(test, driver, "Validated site Actual site is " + actualSiteName + " and Expected Site is" +Site , "PassedSiteVerification");
 				
 			}
     		else {
     			Report.FailTestSnapshot(test, driver, "Unable to validate site Actual site is " + actualSiteName + " and Expected Site is" +Site , "PassedSiteVerification");
 			}
     		
     		dateRangeText=ValidateDateRange.getText();
     		actualFromDate=dateRangeText.substring(20,30);
     		actualToDate=dateRangeText.substring(34,44);
     		    		
     		if (actualFromDate.contains(FromDate) && actualToDate.contains(ToDate) ) {
 				Report.PassTestScreenshot(test, driver, "Validated From and To Date Expected From Date is " + FromDate +" actual From Date is"+actualFromDate + "Expected To Date is " + ToDate + "and Actual To Date is" +actualToDate, "PassedDateRangeVerification");
 			}
     		else {
     			Report.FailTestSnapshot(test, driver, "Unable to validate From and To Date Expected From Date is " + FromDate +" actual From Date is"+actualFromDate + "Expected To Date is " + ToDate + "and Actual To Date is" +actualToDate, "FailedDateRangeVerification");
 			}
     		
 		} catch (Exception e) {
 			Report.FailTest(test, "Failed Validate fixed Text");
 		}
     	
     	
     }

     private void ValidateColumnNameinPopUp() {
     	List<String> actualpopupColumns=new ArrayList<>();
     	
     	try {
     		actualpopupColumns = Util.getColumnNames("dtExcpPopUp");
     		Util.validateColumns(actualpopupColumns, GlobalExpectedColumns.IdleSummaryPopUpColumns, "Idle Summary pop Up columns");
 		} catch (Exception e) {
 			Report.FailTest(test, "Validate Column Name in PopUp Failed");
 		}
     	
     	
     	
     }

     private void ValidateExportExcelButton(String ReportName,String downloadFilePath) {

     	List<String> UIDataList=new ArrayList<>();
     	   	
     	try {
     		
 			Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsIdleSumamryPopUp, downloadFilePath,1);
 		
 			UIDataList=Util.getUIColumnData(ReportName,"PopUp","RteMgr","dtExcpPopUp");
 			Util.validateExportExcelData(downloadFilePath, ReportName, "Route Manager", 0, UIDataList);
 			
 			
 			
 			UIDataList=Util.getUIColumnData(ReportName,"PopUp","Driver","dtExcpPopUp");
 			Util.validateExportExcelData(downloadFilePath, ReportName, "Driver", 0, UIDataList);
 			
 			UIDataList=Util.getUIColumnData(ReportName,"PopUp","IdleTime","dtExcpPopUp");
 			Util.validateExportExcelData(downloadFilePath, ReportName, "Net Idle Time(h:m)", 0, UIDataList);
     	
     	
     	} catch (Exception e) {
 			Report.FailTestSnapshot(test, driver, "Validate Export Excel Button Failed", "ExportToExcelFailed");
 		}
     	
     }
     
    
     
     
    private void ValidatePopUpDatailsDetailReport(String expectedDetailReportTitle,String expectedSummaryReportTitle,String SummaryTableName) {
 	     String expectedRouteManager=null;
 	     String expectedDriver=null;
 	     String netIdleTime=null;
 	     String actualDetailReportTitle=null;
 	     String actualRouteManager=null;
 	     String actualDriver=null;
 	     String actualNetIdleTime=null;
 	     String actualSummaryReportTitlePreRouteSummary=null;
 	   
 	   try {
 		   expectedRouteManager=ExpectedRoutemanagerinPopUp.getText();
 		   expectedDriver=ExpectedDriverInPopUp.getText();
 		  netIdleTime=ExpectedNewIdleinPopUp.getText();
 		   ClickOnFirstDriver.click();
 		   Thread.sleep(10000);
 		   Util.switchToDefaultWindow();
 		   Util.selectFrame("opusReportContainer");
 		   actualDetailReportTitle=GetDetailtableTitle.getText();
 		   if (actualDetailReportTitle.contains(expectedDetailReportTitle)) {
 			   Report.PassTestScreenshot(test, driver, "Navigation working fine Actual page title is " +actualDetailReportTitle + " and Expected Page title is "  + expectedDetailReportTitle, "PassedPageTitleValidateion");
 			
 		} else {
 			Report.FailTestSnapshot(test, driver, "Navigation Not working fine Actual page title is " +actualDetailReportTitle + " and Expected Page title is "  + expectedDetailReportTitle, "FailedPageTitleValidateion");
 		}
 		   
 		   
 		   actualRouteManager=GetRouteManageName.getText();
 		   actualDriver=GetDriverName.getText();
 		   Util.switchToDefaultWindow();
 		   Util.selectFrame("opusReportContainer,subReportContainer");
 		  actualNetIdleTime=driver.findElement(By.xpath("//*[@id='"+SummaryTableName+"']/tbody/tr/td[contains(@id,'colTtlNetIdleTm_Row1')]/span")).getText();
 		   if (expectedRouteManager.contains(actualRouteManager) && expectedDriver.contains(actualDriver) && netIdleTime.contains(actualNetIdleTime)) {
 			Report.PassTestScreenshot(test, driver, "Validated Route Manager Name Expected is "+ expectedRouteManager + " and Actaul is "+actualRouteManager + " Validated Driver Name Expected is " + expectedDriver+ " and actual is " + actualDriver + "Validated Exception Time Expected Net Idle time is " + netIdleTime + " and Actual Net Idle time is " +actualNetIdleTime, "PassedRMDriverAndNetIdleTime");
 		}
 		   else {
 			   Report.PassTestScreenshot(test, driver, "Unable to Validate Route Manager Name Expected is "+ expectedRouteManager + " and Actaul is "+actualRouteManager + " Unable to Validate Driver Name Expected is " + expectedDriver+ " and actual is " + actualDriver + "Unable to Validate Exception time is Expected Net Idle time is " + netIdleTime + " and Actual Net Idle time is " +actualNetIdleTime, "FailedRMDriverAndNetIdleTime");
 		}
 		   
 		   Util.switchToDefaultWindow();
 		   Util.selectFrame("opusReportContainer");
 		   BacktoPreRouteSummaryReport.click();
 		   Thread.sleep(3000);
 		   Util.switchToDefaultWindow();
 		   Util.selectFrame("opusReportContainer");
 		   actualSummaryReportTitlePreRouteSummary=GetDetailtableTitle.getText();
 		   if (actualSummaryReportTitlePreRouteSummary.contains(expectedSummaryReportTitle)) {
 			   Report.PassTest(test, "Validated Page title actual is " +actualSummaryReportTitlePreRouteSummary + " and Expected is" +expectedSummaryReportTitle);
 			
 		} else {
 			 Report.FailTest(test, "Unable to Validate Page title actual is " +actualSummaryReportTitlePreRouteSummary + " and Expected is" +expectedSummaryReportTitle);
 		}
 		       
 		   
 		   
 	} catch (Exception e) {
 		 Report.FailTest(test, "Failed ValidatePopUpDatainDetailReport");
 	}
 	   
    }



}
