package ObjectRepository.Logi;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import ObjectRepository.General.FilterSection;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class SummaryDashboardReport {
	
	ExtentTest test;
	WebDriver driver;

	public SummaryDashboardReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//table[@id='dtOverview']/thead/tr/th")
	List<WebElement> DriverSummaryTableColumns;

	@FindBy(xpath = "//table[@id='dtOverview']/tbody/tr")
	List<WebElement> DriverSummaryTableRows;
	
	@FindBy(xpath = "//table[@id='dtOverviewHlp']/thead/tr/th")
	List<WebElement> DriverAndHelperSummaryTableColumns;

	@FindBy(xpath = "//table[@id='dtOverviewHlp']/tbody/tr")
	List<WebElement> DriverAndHelperSummaryTableRows;
	
	/////////////////// Efficiency Summary Report
	
	@FindBy(xpath = "//table[@id='dtOverview']/thead/tr/th")
	List<WebElement> EfficiencySummaryTableColumns;

	@FindBy(xpath = "//table[@id='dtOverview']/tbody/tr")
	List<WebElement> EfficiencySummaryTableRows;
		
	/*/////////////////Pre-RouteDetail
	@FindBy(xpath="(.//*[@class='report' and contains(text(),'Pre-Route Detail')])[2]")
	WebElement Pre_RouteDetail;*/
	
	////MultiselectDropDown
	@FindBy(xpath="//*[@class='ui-multiselect ui-widget ui-state-default ui-corner-all' and @type='button']")
	WebElement MultiselectDropDown;
	
	///loader
	@FindBy(id="rdWaitImage")
	WebElement loader;
	
	////////////PreRouteHeader
	@FindBy(xpath="//*[@id='rdDataTableDiv-dtOverview']/table/thead/tr")
	WebElement OverviewHeader;
	
	////////////PreRouteBody
	@FindBy(xpath="//*[@id='rdDataTableDiv-dtOverview']/table/tbody/tr")
	WebElement SummaryBdy;
	
	/////////////ExceptionsAll
	@FindBy(xpath="//*[@id='rdTabs']/ul")
	WebElement ExceptionsAll;
		
	//////////////////ToDate
	@FindBy(xpath="//*[@id='inpToDate' and @name='inpToDate']")
	WebElement ToDate;
	
	//////////From Date
	@FindBy(xpath="//*[@id='inpFromDate' and @name='inpFromDate']")
	WebElement FromDate;
	
	/////Reset Button /////////////////////
	@FindBy(xpath="//*[@id='btnReset' and @type='button']")
	WebElement ResetBtn;
	
	///Go Btn
	@FindBy(xpath="//*[@id='btnSubmitGo' and @type='button']")
	WebElement GoBtn;
	
	//SiteFilter
	@FindBy(xpath="//*[@id='divSiteFilter']/span/button")
	WebElement SiteFilter;

	///SiteFilterSearch
	@FindBy(xpath="(//*[@class='ui-multiselect-filter']/input)[1]")
	WebElement SiteFilterSearch;
	
	//LOBSearchBox
	@FindBy(xpath="(//*[@class='ui-multiselect-filter']/input)[2]")
	WebElement LOBSearchBox;
	
	///LObSearchBoxForPreRouteSummary
	@FindBy(xpath="(//*[@class='ui-multiselect-filter']/input)[5]")
	WebElement LOBSearchBoxForPreRouteSummary;
	//CheckAllLOB
	@FindBy(xpath="//*[@id='ui-multiselect-islLOBFilter-option-0']")
	WebElement CheckAllLOB;
	
	//CheckCom
	@FindBy(xpath="//*[@id='ui-multiselect-islLOBFilter-option-1']")
	WebElement CheckCom;
	
	//CheckResi
	@FindBy(xpath="//*[@id='ui-multiselect-islLOBFilter-option-2']")
	WebElement CheckResi;
	
	//CheckRollOff
	@FindBy(xpath="//*[@id='ui-multiselect-islLOBFilter-option-3']")
	WebElement CheckRollOff;
	
	
	///ActualSubview
	@FindBy(xpath="//*[@id='inpSubReportOptEx_2']")
	WebElement ActualSubview;
	
	//ActualSubviewAllTab
	@FindBy(xpath=".//*[@id='inpSubReportOpt_2']")
	WebElement ActualSubviewAllTab; 
	
	//AllTab
	@FindBy(xpath=".//*[@id='tabAll']/a/em/span")
	WebElement AllTab;
	
	// Summary Dashboard Overview tabs
	@FindBy (xpath = ".//*[@id='tabAll']")
	WebElement DriverTab;
	
	@FindBy (xpath = ".//*[@id='tabHlp']")
	WebElement DriverAndHelperTab;
	
	//Summary Dashboard > Route Summary Section
	@FindBy (xpath = ".//*[@id='ColPreRte-TH']")
	WebElement PreRouteSummaryLink ;
	
	@FindBy (xpath = ".//*[@id='divPreRte_Row1']/span[text()]")
	WebElement PreRouteSummaryValue;
	
	@FindBy (xpath = ".//*[@id='ColPostRte-TH']")
	WebElement PostRouteSummaryLink;
	
	@FindBy (xpath = ".//*[@id='divPostRte_Row1']/span[text()]")
	WebElement PostRouteSummaryValue;
	
	@FindBy (xpath = ".//*[@id='ColDsp-TH']")
	WebElement DisposalCycleSummaryLink;
	
	@FindBy (xpath = ".//*[@id='divDsp_Row1']/span[text()]")
	WebElement DisposalCycleSummaryValue;
	
	@FindBy (xpath = ".//*[@id='ColIdle-TH']")
	WebElement IdleTimeSummaryLink;
	
	@FindBy (xpath = ".//*[@id='divIdle_Row1']/span[text()]")
	WebElement IdleTimeSummaryValue;
	
	@FindBy (xpath = ".//*[@id='ColDwntm-TH']")
	WebElement DowntimeSummaryLink;
	
	@FindBy (xpath = ".//*[@id='divDwntm_Row1']/span[text()]")
	WebElement DowntimeSummaryValue;
	
	@FindBy (xpath = ".//*[@id='lblSeeRteSumm']")
	WebElement SeeRouteDetailLink;
	
	@FindBy (xpath = ".//*[@id='divRteLOBFilter']/span/button/span[1]")
	WebElement RouteSummaryLOBDropdownButton;
	
	FilterSection filterObj = new FilterSection(driver, test);
	
	
	public Map<String, List<String>> getActualDriverSummaryTableData() throws IOException {
		Map<String, List<String>> SummaryTableData = new HashMap<>();
		try {
			SummaryTableData = readTable(DriverSummaryTableColumns, DriverSummaryTableRows, "dtOverview");
			Report.PassTestScreenshot(test, driver, "Driver > Summary table data read successfully", "Summary Dashboard report");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Summary table data");
		}

		for (Entry<String, List<String>> entry : SummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return SummaryTableData;
	}
	
	public Map<String, List<String>> getActualDriverAndHelperSummaryTableData() throws IOException {
		Map<String, List<String>> SummaryTableData = new HashMap<>();
		try {
			SummaryTableData = readTable(DriverAndHelperSummaryTableColumns, DriverAndHelperSummaryTableRows, "dtOverviewHlp");
			Report.PassTestScreenshot(test, driver, "Driver and Helper Summary table data read successfully", "Summary Dashboard report");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Summary table data");
		}

		for (Entry<String, List<String>> entry : SummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return SummaryTableData;
	}
	
	public Map<String, List<String>> readTable(List<WebElement> columns, List<WebElement> rows, String tableName) {

	
		Map<String, List<String>> objTable = new HashMap<>();
		for (int iCount = 2; iCount <= columns.size(); iCount++) {

			for (int row = 1; row <= rows.size(); row++) {

				List<String> rowValues=new ArrayList<>();
				try {
					rowValues.add(driver.findElement(By.xpath(
							"//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + iCount + "]/span"))
							.getText());
					String summaryItem = driver.findElement(By.xpath("//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + 1 + "]/span")).getText();
					objTable.put(summaryItem+" "+columns.get(iCount - 1).getText().replace("\n", ""), rowValues);
				} catch (Exception e) {
					rowValues.add(driver
							.findElement(By.xpath(
									"//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + iCount + "]"))
							.getText());
					String summaryItem = driver.findElement(By.xpath("//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + 1 + "]/span")).getText();
					objTable.put(summaryItem+" "+columns.get(iCount - 1).getText().replace("\n", ""), rowValues);
				}
			}			
		}
		return objTable;
		
	}
	public void validateDriverOverviewTableData(String LOB, Map<String, List<String>> actualDriverCOMOverviewSummaryTable,Map<String,List<String>> expectedEfficiencySummaryTable,
			Map<String, List<String>> efficiencyPerformanceCOMSummaryTableData,Map<String, List<String>> efficiencyPerformanceCOMDisposalLoadDetailTableData)
	{
		try
		{
			//Validate Total Efficiency Variance
			validateCOMTotalEffVarInHours(LOB, actualDriverCOMOverviewSummaryTable,expectedEfficiencySummaryTable);
			
			//Validate ExcpEffVariance
			
			
			//Validate Efficiency
			validateCOMEfficiency(LOB, actualDriverCOMOverviewSummaryTable,expectedEfficiencySummaryTable);
			
			//validate Units
			validateCOMUnits(LOB, actualDriverCOMOverviewSummaryTable,expectedEfficiencySummaryTable);
			
			//Validate Driver hours
			validateCOMDriverHours(LOB, actualDriverCOMOverviewSummaryTable,expectedEfficiencySummaryTable);
			
			//validate Miles
			validateCOMDriverMiles(LOB, actualDriverCOMOverviewSummaryTable,expectedEfficiencySummaryTable);
			
			//Validate Tons
			validateCOMDriverTons(LOB, actualDriverCOMOverviewSummaryTable,efficiencyPerformanceCOMDisposalLoadDetailTableData);
			
			//Validate no. of routes
			validateCOMDriverNoOfRoutes(LOB, actualDriverCOMOverviewSummaryTable, efficiencyPerformanceCOMSummaryTableData);
			
			//validate Avg Efficiency Variance
			validateCOMDriverAvgEfficiencyVar(LOB, actualDriverCOMOverviewSummaryTable);
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void validateResiDriverOverviewTableData(Map<String, List<String>> actualDriverResiOverviewSummaryTable,Map<String,List<String>> expectedEfficiencySummaryTable,
			Map<String, List<String>> efficiencyPerformanceCOMSummaryTableData,Map<String, List<String>> efficiencyPerformanceCOMDisposalLoadDetailTableData)
	{
		try
		{
			//Validate Total Efficiency Variance
			/*validateCOMTotalEffVarInHours(actualDriverCOMOverviewSummaryTable,expectedEfficiencySummaryTable);
			
			//Validate ExcpEffVariance
			
			
			//Validate Efficiency
			validateCOMEfficiency(actualDriverCOMOverviewSummaryTable,expectedEfficiencySummaryTable);
			
			//validate Units
			validateCOMUnits(actualDriverCOMOverviewSummaryTable,expectedEfficiencySummaryTable);
			
			//Validate Driver hours
			validateCOMDriverHours(actualDriverCOMOverviewSummaryTable,expectedEfficiencySummaryTable);
			
			//validate Miles
			validateCOMDriverMiles(actualDriverCOMOverviewSummaryTable,expectedEfficiencySummaryTable);
			
			//Validate Tons
			validateCOMDriverTons(actualDriverCOMOverviewSummaryTable,efficiencyPerformanceCOMDisposalLoadDetailTableData);
			
			//Validate no. of routes
			validateCOMDriverNoOfRoutes(actualDriverCOMOverviewSummaryTable, efficiencyPerformanceCOMSummaryTableData);
			
			//validate Avg Efficiency Variance
			validateCOMDriverAvgEfficiencyVar(actualDriverCOMOverviewSummaryTable);*/
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void validateComDriverOverviewTableData(Map<String, List<String>> actualDriverCOMOverviewSummaryTable,Map<String,List<String>> expectedEfficiencySummaryTable,
			Map<String, List<String>> efficiencyPerformanceCOMSummaryTableData,Map<String, List<String>> efficiencyPerformanceCOMDisposalLoadDetailTableData)
	{
		try
		{
			/*//Validate Total Efficiency Variance
			validateCOMTotalEffVarInHours(actualDriverCOMOverviewSummaryTable,expectedEfficiencySummaryTable);
			
			//Validate ExcpEffVariance
			
			
			//Validate Efficiency
			validateCOMEfficiency(actualDriverCOMOverviewSummaryTable,expectedEfficiencySummaryTable);
			
			//validate Units
			validateCOMUnits(actualDriverCOMOverviewSummaryTable,expectedEfficiencySummaryTable);
			
			//Validate Driver hours
			validateCOMDriverHours(actualDriverCOMOverviewSummaryTable,expectedEfficiencySummaryTable);
			
			//validate Miles
			validateCOMDriverMiles(actualDriverCOMOverviewSummaryTable,expectedEfficiencySummaryTable);
			
			//Validate Tons
			validateCOMDriverTons(actualDriverCOMOverviewSummaryTable,efficiencyPerformanceCOMDisposalLoadDetailTableData);
			
			//Validate no. of routes
			validateCOMDriverNoOfRoutes(actualDriverCOMOverviewSummaryTable, efficiencyPerformanceCOMSummaryTableData);
			
			//validate Avg Efficiency Variance
			validateCOMDriverAvgEfficiencyVar(actualDriverCOMOverviewSummaryTable);*/
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
    
	public void validateCOMTotalEffVarInHours(String LOB, Map<String, List<String>> actualDriverCOMOverviewSummaryTable,Map<String,List<String>> expectedEfficiencySummaryTable)
	{
		double actualDriverCOMTotalEffVarInHrs = 0.00;
		double expectedDriverCOMTotalEffVarInHrs = 0.00;
		
		for (Entry<String, List<String>> entry : actualDriverCOMOverviewSummaryTable.entrySet()) {
			if (entry.getKey().contains(LOB+" Total Efficiency Variance")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualDriverCOMTotalEffVarInHrs = 0.00;
					} else {
						actualDriverCOMTotalEffVarInHrs = Double.parseDouble(entry.getValue().get(i));
						// Validate Decimal Format for the value have 2 decimal places
						Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, LOB+" Total Efficiency Variance(in hrs)");
					}
				}
			}
		}
		for (Entry<String, List<String>> entry : expectedEfficiencySummaryTable.entrySet()) {
			if (entry.getKey().contains("Driver Total Eff Var")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						expectedDriverCOMTotalEffVarInHrs = 0.00;
					} else {
						expectedDriverCOMTotalEffVarInHrs = Double.parseDouble(entry.getValue().get(i));
						
					}
				}
			}
		}
		if ((actualDriverCOMTotalEffVarInHrs==expectedDriverCOMTotalEffVarInHrs)||(actualDriverCOMTotalEffVarInHrs==expectedDriverCOMTotalEffVarInHrs+1) || (actualDriverCOMTotalEffVarInHrs==expectedDriverCOMTotalEffVarInHrs-1))
		{
			Report.PassTest(test, LOB+ " - Actual Value from Summary Dashboard - "+actualDriverCOMTotalEffVarInHrs+", is same as displayed on the Efficiency Summary Report - "+expectedDriverCOMTotalEffVarInHrs);
			System.out.println("Actual is same as expected");
		}
		else
		{
			Report.FailTest(test, LOB + " - Actual Value from Summary Dashboard - "+actualDriverCOMTotalEffVarInHrs+", is NOT same as displayed on the Efficiency Summary Report - "+expectedDriverCOMTotalEffVarInHrs);
			System.out.println("Actual is NOT same as expected");
		}
	}
	
	public void validateCOMEfficiency(String LOB, Map<String, List<String>> actualDriverCOMOverviewSummaryTable,Map<String,List<String>> expectedEfficiencySummaryTable)
	{
		double actualDriverCOMEfficiency = 0.00;
		double expectedDriverCOMEfficiency = 0.00;
		int noOfDecimal = 0;
		if(LOB.contains("ROLL OFF"))
		{
			noOfDecimal = 4;
		}
		else
		{
			noOfDecimal = 2;
		}
		
		for (Entry<String, List<String>> entry : actualDriverCOMOverviewSummaryTable.entrySet()) {
			if (entry.getKey().equals(LOB+" Efficiency")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualDriverCOMEfficiency = 0.00;
					} else {
						actualDriverCOMEfficiency = Double.parseDouble(entry.getValue().get(i));
						// Validate Decimal Format for the value have 2 decimal places
						Util.validateFieldDecimalPlaces(entry.getValue().get(i), noOfDecimal, LOB+" Efficiency");
					}
				}
			}
		}
		for (Entry<String, List<String>> entry : expectedEfficiencySummaryTable.entrySet()) {
			if (entry.getKey().contains("Driver Avg Actual Eff")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						expectedDriverCOMEfficiency = 0.00;
					} else {
						expectedDriverCOMEfficiency = Double.parseDouble(entry.getValue().get(i));
						
					}
				}
			}
		}
		if ((actualDriverCOMEfficiency==expectedDriverCOMEfficiency)||(actualDriverCOMEfficiency==expectedDriverCOMEfficiency+1) || (actualDriverCOMEfficiency==expectedDriverCOMEfficiency-1))
		{
			Report.PassTest(test, LOB+" - Actual Value from Summary Dashboard - "+actualDriverCOMEfficiency+", is same as displayed on the Efficiency Summary Report - "+expectedDriverCOMEfficiency);
			System.out.println(LOB+" - Actual is same as expected");
		}
		else
		{
			Report.FailTest(test,LOB+" - Actual Value from Summary Dashboard - "+actualDriverCOMEfficiency+", is NOT same as displayed on the Efficiency Summary Report - "+expectedDriverCOMEfficiency);
			System.out.println(LOB+" - Actual is NOT same as expected");
		}
	}
	
	public void validateCOMUnits(String LOB, Map<String, List<String>> actualDriverCOMOverviewSummaryTable,Map<String,List<String>> expectedEfficiencySummaryTable)
	{
		double actualDriverCOMUnits = 0.0;
		double expectedDriverCOMUnits = 0.0;
		
		for (Entry<String, List<String>> entry : actualDriverCOMOverviewSummaryTable.entrySet()) {
			if (entry.getKey().equals(LOB+" Units")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualDriverCOMUnits = 0.0;
					} else {
						actualDriverCOMUnits = Double.parseDouble(entry.getValue().get(i).trim().replace(",",""));
						// Validate Decimal Format for the value have 2 decimal places
						Util.validateFieldDecimalPlaces(entry.getValue().get(i), 1, LOB+" Units");
					}
				}
			}
		}
		for (Entry<String, List<String>> entry : expectedEfficiencySummaryTable.entrySet()) {
			if (entry.getKey().contains("Driver Total Actual Units")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						expectedDriverCOMUnits = 0.0;
					} else {
						expectedDriverCOMUnits = Double.parseDouble(entry.getValue().get(i).trim().replace(",", ""));
						
					}
				}
			}
		}
		if ((actualDriverCOMUnits==expectedDriverCOMUnits)||(actualDriverCOMUnits==expectedDriverCOMUnits+1) || (actualDriverCOMUnits==expectedDriverCOMUnits-1))
		{
			Report.PassTest(test, LOB+" - Actual Value from Summary Dashboard of column - "+LOB+" Units - "+actualDriverCOMUnits+", is same as displayed on the Efficiency Summary Report - "+expectedDriverCOMUnits);
			System.out.println(LOB+" - Actual is same as expected");
		}
		else
		{
			Report.FailTest(test, LOB+" - Actual Value from Summary Dashboard of column - "+LOB+" Units - "+actualDriverCOMUnits+", is NOT same as displayed on the Efficiency Summary Report - "+expectedDriverCOMUnits);
			System.out.println(LOB+" - Actual is NOT same as expected");
		}
	}
	
	public void validateCOMDriverHours(String LOB, Map<String, List<String>> actualDriverCOMOverviewSummaryTable,Map<String,List<String>> expectedEfficiencySummaryTable)
	{

		List<String> actualDriverHours = new ArrayList<>();
		String expectedDriverHours = null;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : actualDriverCOMOverviewSummaryTable.entrySet()) {
				if (entry.getKey().contains(LOB+" Driver Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualDriverHours.add("00:00");
						} else {
							actualDriverHours.add(entry.getValue().get(i).toString());
							Util.validateTimeFormat(entry.getValue().get(i), LOB+" Driver Hours");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : expectedEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver Total Actual Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins += mins;
						}

					}
				}
			}
	
			expectedDriverHours = Util.convertMinsToHours(totalMins);
			if(!Util.compareTime(actualDriverHours.get(0), expectedDriverHours))
			{
				Report.FailTest(test, LOB +" - Helper Hours is not as expected in detail section of efficiency summary report Actual is : "
						+ actualDriverHours + " and Expected is : " + expectedDriverHours);
			}
			else
			{
				Report.InfoTest(test, LOB +" - Driver Hours is correct in Summary section of Summary Dashboard report Actual is : "
						+ actualDriverHours + " and Expected is : " + expectedDriverHours);
				Report.PassTest(test, LOB +" - Driver Hours is as expected in Summary section of Summary Dashboard and efficiency Summary report");
			}
					
		} 
		catch (Exception e) {
			Report.FailTest(test, LOB +" - Helper Hours is not as expected in detail section of efficiency summary report Actual is : "
					+ actualDriverHours + " and Expected is : " + expectedDriverHours);
		}
		
	}
	
	public void validateCOMDriverMiles(String LOB, Map<String, List<String>> actualDriverCOMOverviewSummaryTable,Map<String,List<String>> expectedEfficiencySummaryTable)
	{
		int actualMiles = 0;
		int expectedMiles = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualDriverCOMOverviewSummaryTable.entrySet()) {
				if (entry.getKey().equals(LOB+" Miles")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualMiles = 0;
						} else {
							actualMiles = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualMiles, LOB+" Miles");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : expectedEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Total Miles")) {
					
					expectedMiles=Integer.parseInt(entry.getValue().get(0));
				}
			}
				if (Util.compareNumber(actualMiles, expectedMiles)) {
					Report.InfoTest(test, LOB+" - Driver Total  Miles is correct in Summary Dashboard and Efficiency Summary report Actual is : "
							+ actualMiles + " and Expected is : " + expectedMiles);
					Report.PassTest(test,LOB+" - Driver Total  Miles is as expected in Summary Dashboard and Efficiency Summary report");
				} else {
					Report.FailTest(test,LOB+" - Driver Total Miles is not as expected in Summary Dashboard and Efficiency Summary report Actual is : " + actualMiles
							+ " and Expected is : " + expectedMiles);
				}
	
		} catch (Exception e) {
			Report.FailTest(test, LOB+" - Driver Total Miles is not as expected in Summary Dashboard report Actual is : " + actualMiles
					+ " and Expected is : " + expectedMiles);
		}
	}
	
	public void validateCOMDriverTons(String LOB, Map<String, List<String>> actualDriverCOMOverviewSummaryTable, Map<String, List<String>>efficiencyPerformanceCOMDisposalLoadDetailTableData)
	{
		int actualTons = 0;
		double expectedTons = 0.00;
		double finalactualTons = 0.00;
		
		try {
			for (Entry<String, List<String>> entry : actualDriverCOMOverviewSummaryTable.entrySet()) {
				if (entry.getKey().equals(LOB+" Tons")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualTons = 0;
						} else {
							actualTons = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualTons, LOB+" Tons");
							finalactualTons = Double.parseDouble(entry.getValue().get(i).concat(".00"));
						}
					}
				}
			}
		
		//Get the value from Disposal Loads 
		for (Entry<String, List<String>> entry : efficiencyPerformanceCOMDisposalLoadDetailTableData.entrySet()) {
			if (entry.getKey().equals("Total Tons")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						expectedTons = expectedTons + Double.parseDouble("0.00");
					} else 
					{
						expectedTons = expectedTons + Double.parseDouble(entry.getValue().get(i).trim().replace(",", ""));
										
					}
					
				}
				
			}
			
		}
		
		if((finalactualTons==expectedTons)||(finalactualTons==expectedTons+1.00)||(finalactualTons==expectedTons-1.00))
		{
			Report.PassTest(test, LOB + " - Actual Tons - "+finalactualTons+" is same as expected from Efficiency Performance - " +expectedTons);
			System.out.println(LOB + " - Actual Tons - "+finalactualTons+" is same as expected from Efficiency Performance - " +expectedTons);
		}
		else
		{
			Report.FailTest(test, LOB + " - Actual Tons - "+finalactualTons+" is NOT same as expected from Efficiency Performance - " +expectedTons);
			System.out.println(LOB + " - Actual Tons - "+finalactualTons+" is NOT same as expected from Efficiency Performance - " +expectedTons);
		}	
		
		
		}
		
	
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void validateCOMDriverNoOfRoutes(String LOB, Map<String, List<String>> actualDriverCOMOverviewSummaryTable,Map<String, List<String>> efficiencyPerformanceCOMSummaryTableData)
	{
		int actualNoOfRoutes = 0;
		int expectedNoOfRoutes = 0;
		
		for (Entry<String, List<String>> entry : actualDriverCOMOverviewSummaryTable.entrySet()) {
			if (entry.getKey().equals(LOB+" # of Routes")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualNoOfRoutes = 0;
					} else {
						actualNoOfRoutes = Integer.parseInt(entry.getValue().get(i));
					}
				}
			}
		}
		
		for (Entry<String, List<String>> entry : efficiencyPerformanceCOMSummaryTableData.entrySet()) {
			if (entry.getKey().contains("# of Routes")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						expectedNoOfRoutes = 0;
					} else {
						expectedNoOfRoutes = Integer.parseInt(entry.getValue().get(i));
					}
				}
			}
		}
		
		if (actualNoOfRoutes==expectedNoOfRoutes)
		{
			Report.PassTest(test, LOB + " - Number of Routes are same as expected - "+actualNoOfRoutes);
			System.out.println(LOB + " - Number of Routes are same as expected - "+actualNoOfRoutes);
		}
		else
		{
			Report.FailTest(test, LOB + " - Number of Routes are NOT same as expected - "+actualNoOfRoutes);
			System.out.println(LOB + " - Number of Routes are NOT same as expected - "+actualNoOfRoutes);
		}
			
		
	}
		
	public void validateCOMDriverAvgEfficiencyVar(String LOB,Map<String, List<String>> actualDriverCOMOverviewSummaryTable)
	{
		double actualNoOfRoutes = 0.00;
		double actualTotalEffVar = 0.00;
		double actualAvgEffVar = 0.00;
		double calcAvgEffVal = 0.00; 
		
		for (Entry<String, List<String>> entry : actualDriverCOMOverviewSummaryTable.entrySet()) {
			if (entry.getKey().equals(LOB+" Total Efficiency Variance (in hrs)")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualTotalEffVar = 0.00;
					} else {
						actualTotalEffVar = Double.parseDouble(entry.getValue().get(i));
					}
				}
			}
		}
		
		for (Entry<String, List<String>> entry : actualDriverCOMOverviewSummaryTable.entrySet()) {
			if (entry.getKey().equals(LOB+" Avg Efficiency Variance (in hrs)")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualAvgEffVar = 0.00;
					} else {
						actualAvgEffVar = Double.parseDouble(entry.getValue().get(i));
					}
				}
			}
		}
		
		for (Entry<String, List<String>> entry : actualDriverCOMOverviewSummaryTable.entrySet()) {
			if (entry.getKey().equals(LOB+" # of Routes")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualNoOfRoutes = 0.00;
					} else {
						actualNoOfRoutes = Double.parseDouble(entry.getValue().get(i));
					}
				}
			}
		}
		
		calcAvgEffVal = Math.round((actualTotalEffVar / actualNoOfRoutes));
		//DecimalFormat df = new DecimalFormat("0.00");
		//String CalcAvgEffValInFormat = df.format(calcAvgEffVal);
		
		if ((calcAvgEffVal == Math.round(actualAvgEffVar))||(calcAvgEffVal == Math.round(actualAvgEffVar)+1)||(calcAvgEffVal == Math.round(actualAvgEffVar)-1))
		{
			Report.PassTest(test, LOB+" - Calculated Avg Eff Value - " +calcAvgEffVal+" is same as displayed value - "+actualAvgEffVar);
			System.out.println(LOB+" - Calculated Avg Eff Value - " +calcAvgEffVal+" is same as displayed value - "+actualAvgEffVar);
		}
		else
		{
			Report.FailTest(test, LOB+" - Calculated Avg Eff Value - " +calcAvgEffVal+" is NOT same as displayed value - "+actualAvgEffVar);
			System.out.println(LOB+" - Calculated Avg Eff Value - " +calcAvgEffVal+" is NOT same as displayed value - "+actualAvgEffVar);
		}	
	}
	
	public void SwitchReportTabs(String TabName)
	{
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		if (TabName.equals("Driver"))
		{
			DriverTab.click();
		}
		else if (TabName.equals("Driver & Helper"))
		{
			DriverAndHelperTab.click();
		}
				
	}
	
	public void validateDriverAndHelperOverviewTableData(String LOB, Map<String, List<String>> actualDriverAndHelperSummaryTable,Map<String,List<String>> expectedEfficiencySummaryTable)
	{
		try
		{
			//Validate Total Efficiency Variance
			validateCOMTotalEffVarInHours_DriverAndHelper(LOB, actualDriverAndHelperSummaryTable,expectedEfficiencySummaryTable);
			
			//Validate ExcpEffVariance
			
			
			//Validate Efficiency
			validateCOMEfficiency_DriverAndHelper(LOB, actualDriverAndHelperSummaryTable,expectedEfficiencySummaryTable);
						
			//Validate Helper hours
			validateCOMHelperHours_DriverAndHelper(LOB, actualDriverAndHelperSummaryTable,expectedEfficiencySummaryTable);
						
			//validate Avg Efficiency Variance
			validateCOMDriverAvgEfficiencyVar_DriverAndHelper(LOB, actualDriverAndHelperSummaryTable);
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	
	}
	
	public void validateCOMTotalEffVarInHours_DriverAndHelper(String LOB, Map<String,List<String>>actualDriverAndHelperSummaryTable,Map<String,List<String>>expectedEfficiencySummaryTable)
	{
		double actualDriverAndHelperCOMTotalEffVarInHrs = 0.00;
		double expectedDriverAndHelperCOMTotalEffVarInHrs = 0.00;
		
		for (Entry<String, List<String>> entry : actualDriverAndHelperSummaryTable.entrySet()) {
			if (entry.getKey().contains(LOB+" Total Efficiency Variance")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualDriverAndHelperCOMTotalEffVarInHrs = 0.00;
					} else {
						actualDriverAndHelperCOMTotalEffVarInHrs = Double.parseDouble(entry.getValue().get(i));
						// Validate Decimal Format for the value have 2 decimal places
						Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, LOB+" Total Efficiency Variance(in hrs)");
					}
				}
			}
		}
		for (Entry<String, List<String>> entry : expectedEfficiencySummaryTable.entrySet()) {
			if (entry.getKey().contains("Driver & Helper Total Eff Var")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						expectedDriverAndHelperCOMTotalEffVarInHrs = 0.00;
					} else {
						expectedDriverAndHelperCOMTotalEffVarInHrs = Double.parseDouble(entry.getValue().get(i));
						
					}
				}
			}
		}
		if ((actualDriverAndHelperCOMTotalEffVarInHrs==expectedDriverAndHelperCOMTotalEffVarInHrs)||(actualDriverAndHelperCOMTotalEffVarInHrs==expectedDriverAndHelperCOMTotalEffVarInHrs+1) || (actualDriverAndHelperCOMTotalEffVarInHrs==expectedDriverAndHelperCOMTotalEffVarInHrs-1))
		{
			Report.PassTest(test, LOB+ " - Actual Value from Summary Dashboard - "+actualDriverAndHelperCOMTotalEffVarInHrs+", is same as displayed on the Efficiency Summary Report - "+expectedDriverAndHelperCOMTotalEffVarInHrs);
			System.out.println(LOB+ " - Actual is same as expected");
		}
		else
		{
			Report.FailTest(test, LOB+ " - Actual Value from Summary Dashboard - "+actualDriverAndHelperCOMTotalEffVarInHrs+", is NOT same as displayed on the Efficiency Summary Report - "+expectedDriverAndHelperCOMTotalEffVarInHrs);
			System.out.println(LOB+ " - Actual is NOT same as expected");
		}
	}
	
	public void validateCOMEfficiency_DriverAndHelper(String LOB, Map<String,List<String>>actualDriverAndHelperSummaryTable,Map<String,List<String>>expectedEfficiencySummaryTable)
	{
		double actualDriverAndHelperCOMEfficiency = 0.00;
		double expectedDriverAndHelperCOMEfficiency = 0.00;
		int noOfDecimal = 0;
		if(LOB.contains("ROLL OFF"))
		{
			noOfDecimal = 4;
		}
		else
		{
			noOfDecimal = 2;
		}
		for (Entry<String, List<String>> entry : actualDriverAndHelperSummaryTable.entrySet()) {
			if (entry.getKey().equals(LOB+" Efficiency")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualDriverAndHelperCOMEfficiency = 0.00;
					} else {
						actualDriverAndHelperCOMEfficiency = Double.parseDouble(entry.getValue().get(i));
						// Validate Decimal Format for the value have 2 decimal places
						Util.validateFieldDecimalPlaces(entry.getValue().get(i), noOfDecimal, LOB+" Efficiency");
					}
				}
			}
		}
		for (Entry<String, List<String>> entry : expectedEfficiencySummaryTable.entrySet()) {
			if (entry.getKey().contains("Driver & Helper Avg Actual Eff")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						expectedDriverAndHelperCOMEfficiency = 0.00;
					} else {
						expectedDriverAndHelperCOMEfficiency = Double.parseDouble(entry.getValue().get(i));
						
					}
				}
			}
		}
		if ((actualDriverAndHelperCOMEfficiency==expectedDriverAndHelperCOMEfficiency)||(actualDriverAndHelperCOMEfficiency==expectedDriverAndHelperCOMEfficiency+1) || (actualDriverAndHelperCOMEfficiency==expectedDriverAndHelperCOMEfficiency-1))
		{
			Report.PassTest(test, LOB+" - Actual Value from Summary Dashboard - "+actualDriverAndHelperCOMEfficiency+", is same as displayed on the Efficiency Summary Report - "+expectedDriverAndHelperCOMEfficiency);
			System.out.println(LOB+" - Actual is same as expected");
		}
		else
		{
			Report.FailTest(test, LOB+" - Actual Value from Summary Dashboard - "+actualDriverAndHelperCOMEfficiency+", is NOT same as displayed on the Efficiency Summary Report - "+expectedDriverAndHelperCOMEfficiency);
			System.out.println(LOB+" - Actual is NOT same as expected");
		}
	}
	
	public void validateCOMHelperHours_DriverAndHelper(String LOB, Map<String,List<String>>actualDriverAndHelperSummaryTable,Map<String,List<String>>expectedEfficiencySummaryTable)
	{

		List<String> actualHelperHours = new ArrayList<>();
		String expectedDriverHours = null;
		String expectedDriverAndHelperHours = null;
		String expectedHelperHours = null;
		int totalMins1 = 0;
		int totalMins2 = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualDriverAndHelperSummaryTable.entrySet()) {
				if (entry.getKey().contains(LOB+" Helper Hours")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualHelperHours.add("00:00");
						} else {
							actualHelperHours.add(entry.getValue().get(i).toString());
							Util.validateTimeFormat(entry.getValue().get(i), LOB+" Helper Hours");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : expectedEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver Total Actual Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins1 += mins;
						}

					}
				}
			}
	
			expectedDriverHours = Util.convertMinsToHours(totalMins1);
			
			for (Entry<String, List<String>> entry : expectedEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver & Helper Total Actual Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins2 += mins;
						}

					}
				}
			}
	
			expectedDriverAndHelperHours = Util.convertMinsToHours(totalMins2);
			expectedHelperHours = Util.getTimeDifferenceInString(expectedDriverAndHelperHours, expectedDriverHours);
			
			
			if(!Util.compareTime(actualHelperHours.get(0), expectedHelperHours))
			{
				Report.FailTest(test, LOB+" - Helper Hours is not as expected in detail section of efficiency summary report Actual is : "
						+ actualHelperHours + " and Expected is : " + expectedHelperHours);
			}
			else
			{
				Report.InfoTest(test, LOB+" - Driver Hours is correct in Summary section of Summary Dashboard report Actual is : "
						+ actualHelperHours + " and Expected is : " + expectedHelperHours);
				Report.PassTest(test, LOB+" - Driver Hours is as expected in Summary section of Summary Dashboard and efficiency Summary report");
			}
					
		} 
		catch (Exception e) {
			Report.FailTest(test, LOB+" - Helper Hours is not as expected in detail section of efficiency summary report Actual is : "
					+ actualHelperHours + " and Expected is : " + expectedHelperHours);
		}
	}
	
	public void validateCOMDriverAvgEfficiencyVar_DriverAndHelper(String LOB, Map<String,List<String>>actualDriverAndHelperSummaryTable)
	{
		double actualNoOfRoutes = 0.00;
		double actualTotalEffVar = 0.00;
		double actualAvgEffVar = 0.00;
		double calcAvgEffVal = 0.00; 
		
		for (Entry<String, List<String>> entry : actualDriverAndHelperSummaryTable.entrySet()) {
			if (entry.getKey().equals(LOB+" Total Efficiency Variance (in hrs)")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualTotalEffVar = 0.00;
					} else {
						actualTotalEffVar = Double.parseDouble(entry.getValue().get(i));
					}
				}
			}
		}
		
		for (Entry<String, List<String>> entry : actualDriverAndHelperSummaryTable.entrySet()) {
			if (entry.getKey().equals(LOB+" Avg Efficiency Variance (in hrs)")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualAvgEffVar = 0.00;
					} else {
						actualAvgEffVar = Double.parseDouble(entry.getValue().get(i));
					}
				}
			}
		}
		
		for (Entry<String, List<String>> entry : actualDriverAndHelperSummaryTable.entrySet()) {
			if (entry.getKey().equals(LOB+" # of Routes")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualNoOfRoutes = 0.00;
					} else {
						actualNoOfRoutes = Double.parseDouble(entry.getValue().get(i));
					}
				}
			}
		}
		
		calcAvgEffVal = Math.round((actualTotalEffVar / actualNoOfRoutes));
		DecimalFormat df = new DecimalFormat("0.00");
		
		
		if ((calcAvgEffVal == Math.round(actualAvgEffVar))||(calcAvgEffVal == Math.round(actualAvgEffVar)+1)||(calcAvgEffVal == Math.round(actualAvgEffVar)-1))
		{
			Report.PassTest(test, LOB +" - Calculated Avg Eff Value - " +calcAvgEffVal+" is same as displayed value - "+actualAvgEffVar);
			System.out.println(LOB +" - Calculated Avg Eff Value - " +calcAvgEffVal+" is same as displayed value - "+actualAvgEffVar);
		}
		else
		{
			Report.FailTest(test, LOB +" - Calculated Avg Eff Value - " +calcAvgEffVal+" is NOT same as displayed value - "+actualAvgEffVar);
			System.out.println(LOB +" - Calculated Avg Eff Value - " +calcAvgEffVal+" is NOT same as displayed value - "+actualAvgEffVar);
		}	
	}
	
	public void validateRouteSummarySection(String LOB,	Map<String,List<String>>actualPreRouteSummaryTable,Map<String,List<String>>actualPostRouteSummaryTable,
			Map<String,List<String>> actualDisposalCycleSummaryTable,Map<String,List<String>>actualIdleSummaryTable,Map<String,List<String>>actualRouteSummarySummaryTable)
	{
		try{
		selectRouteSummaryLOB(LOB);
		Thread.sleep(3000);
		validatePreRouteSummaryFromRouteSummarySection(LOB,actualPreRouteSummaryTable);
		validatePostRouteSummaryFromRouteSummarySection(LOB,actualPostRouteSummaryTable);
		validateDisposalCycleSummaryFromRouteSummarySection(LOB,actualDisposalCycleSummaryTable);
		validateIdleSummaryFromRouteSummarySection(LOB,actualIdleSummaryTable);
		validateDowntimeSummaryFromRouteSummarySection(LOB,actualRouteSummarySummaryTable);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void validatePreRouteSummaryFromRouteSummarySection(String LOB,Map<String,List<String>>actualPreRouteSummaryTable)
	{
		List<String> actualPreRouteExpTime = new ArrayList<>();
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		String expectedPreRouteExpTime = PreRouteSummaryValue.getText();
		
		for (Entry<String, List<String>> entry : actualPreRouteSummaryTable.entrySet()) {
			if (entry.getKey().contains("Total Exception Time (h:m)")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualPreRouteExpTime.add("00:00");
					} else {
						actualPreRouteExpTime.add(entry.getValue().get(i).toString());
						Util.validateTimeFormat(entry.getValue().get(i), "Total Exception Time (h:m)");
					}
				}
			}
		}
		
		if (!Util.compareTime(actualPreRouteExpTime.get(0), expectedPreRouteExpTime))
			{
				Report.FailTest(test, LOB+" - Total Pre Route Exception time is not same in the Report. From Summary Dashboard - "+expectedPreRouteExpTime+" and from Pre Route Summary Report - "+actualPreRouteExpTime );
			}
		else
		{
			Report.PassTest(test, LOB + " - Total Pre Route Exception time is same in the Report. From Summary Dashboard - "+expectedPreRouteExpTime+" and from Pre Route Summary Report - "+actualPreRouteExpTime);
		}
	}
	
	public void validatePostRouteSummaryFromRouteSummarySection(String LOB,Map<String,List<String>>actualPostRouteSummaryTable)
	{
		List<String> actualPostRouteExpTime = new ArrayList<>();
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		String expectedPostRouteExpTime = PostRouteSummaryValue.getText();
		
		for (Entry<String, List<String>> entry : actualPostRouteSummaryTable.entrySet()) {
			if (entry.getKey().contains("Total Exception Time (h:m)")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualPostRouteExpTime.add("00:00");
					} else {
						actualPostRouteExpTime.add(entry.getValue().get(i).toString());
						Util.validateTimeFormat(entry.getValue().get(i), "Total Exception Time (h:m)");
					}
				}
			}
		}
		
		if (!Util.compareTime(actualPostRouteExpTime.get(0), expectedPostRouteExpTime))
			{
				Report.FailTest(test,LOB+" - Total Post Route Exception time is not same in the Report. From Summary Dashboard - "+expectedPostRouteExpTime+" and from Post Route Summary Report - "+actualPostRouteExpTime );
			}
		else
		{
			Report.PassTest(test, LOB +" - Total Post Route Exception time is same in the Report. From Summary Dashboard - "+expectedPostRouteExpTime+" and from Post Route Summary Report - "+actualPostRouteExpTime);
		}
	}
		
	public void validateDisposalCycleSummaryFromRouteSummarySection(String LOB,Map<String,List<String>>actualDisposalCycleSummaryTable)
	{
		List<String> actualDispExpTime = new ArrayList<>();
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		String expectedDispExpTime = DisposalCycleSummaryValue.getText();
		
		for (Entry<String, List<String>> entry : actualDisposalCycleSummaryTable.entrySet()) {
			if (entry.getKey().contains("Total Exception Time (h:m)")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualDispExpTime.add("00:00");
					} else {
						actualDispExpTime.add(entry.getValue().get(i).toString());
						Util.validateTimeFormat(entry.getValue().get(i), "Total Exception Time (h:m)");
					}
				}
			}
		}
		
		if (!Util.compareTime(actualDispExpTime.get(0), expectedDispExpTime))
			{
				Report.FailTest(test, LOB + " - Total Disposal Exception time is not same in the Report. From Summary Dashboard - "+expectedDispExpTime+" and from Disposal Summary Report - "+actualDispExpTime );
			}
		else
		{
			Report.PassTest(test, LOB + " - Total Disposal Exception time is same in the Report. From Summary Dashboard - "+expectedDispExpTime+" and from Disposal Summary Report - "+actualDispExpTime);
		}
	}
	
	public void validateIdleSummaryFromRouteSummarySection(String LOB,Map<String,List<String>>actualIdleSummaryTable)
	{
		List<String> actualIdleExpTime = new ArrayList<>();
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		String expectedIdleExpTime = IdleTimeSummaryValue.getText();
		
		for (Entry<String, List<String>> entry : actualIdleSummaryTable.entrySet()) {
			if (entry.getKey().contains("Total Net Idle Time(h:m)")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualIdleExpTime.add("00:00");
					} else {
						actualIdleExpTime.add(entry.getValue().get(i).toString());
						Util.validateTimeFormat(entry.getValue().get(i), "Total Net Idle Time(h:m)");
					}
				}
			}
		}
		
		if (!Util.compareTime(actualIdleExpTime.get(0), expectedIdleExpTime))
			{
				Report.FailTest(test, LOB + " - Total Idle Exception time is not same in the Report. From Summary Dashboard - "+expectedIdleExpTime+" and from Idle Summary Report - "+actualIdleExpTime );
			}
		else
		{
			Report.PassTest(test, LOB +" - Total Idle Exception time is same in the Report. From Summary Dashboard - "+expectedIdleExpTime+" and from Idle Summary Report - "+actualIdleExpTime);
		}
	}
	
	public void validateDowntimeSummaryFromRouteSummarySection(String LOB,Map<String,List<String>>actualRouteSummarySummaryTable)
	{
		List<String> actualDowntimeExpTime = new ArrayList<>();
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		String expectedDowntimeExpTime = DowntimeSummaryValue.getText();
		
		for (Entry<String, List<String>> entry : actualRouteSummarySummaryTable.entrySet()) {
			if (entry.getKey().contains("Total Downtime")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualDowntimeExpTime.add("00:00");
					} else {
						actualDowntimeExpTime.add(entry.getValue().get(i).toString());
						Util.validateTimeFormat(entry.getValue().get(i), "Total Downtime (h:m)");
					}
				}
			}
		}
		
		if (!Util.compareTime(actualDowntimeExpTime.get(0), expectedDowntimeExpTime))
			{
				Report.FailTest(test, LOB +" - Total Downtime Exception time is not same in the Report. From Summary Dashboard - "+expectedDowntimeExpTime+" and from Downtime Summary Report - "+actualDowntimeExpTime );
			}
		else
		{
			Report.PassTest(test, LOB + " - Total Downtime Exception time is same in the Report. From Summary Dashboard - "+expectedDowntimeExpTime+" and from Downtime Summary Report - "+actualDowntimeExpTime);
		}
	}
	
	public void selectRouteSummaryLOB(String LOB)
	{
		try
		{
			Thread.sleep(2000);
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");					
			RouteSummaryLOBDropdownButton.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[text()='" + LOB + "']")).click();
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			Report.PassTest(test, "LOB : " + LOB + " was selected successfully");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void validateResiTotalEffVarInHours(Map<String, List<String>> actualDriverResiOverviewSummaryTable,Map<String,List<String>> expectedEfficiencySummaryTable)
	{
		double actualDriverResiTotalEffVarInHrs = 0.00;
		double expectedDriverResiTotalEffVarInHrs = 0.00;
		
		for (Entry<String, List<String>> entry : actualDriverResiOverviewSummaryTable.entrySet()) {
			if (entry.getKey().contains("RESIDENTIAL Total Efficiency Variance")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualDriverResiTotalEffVarInHrs = 0.00;
					} else {
						actualDriverResiTotalEffVarInHrs = Double.parseDouble(entry.getValue().get(i));
						// Validate Decimal Format for the value have 2 decimal places
						Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, "RESIDENTIAL Total Efficiency Variance(in hrs)");
					}
				}
			}
		}
		for (Entry<String, List<String>> entry : expectedEfficiencySummaryTable.entrySet()) {
			if (entry.getKey().contains("Driver Total Eff Var")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						expectedDriverResiTotalEffVarInHrs = 0.00;
					} else {
						expectedDriverResiTotalEffVarInHrs = Double.parseDouble(entry.getValue().get(i));
						
					}
				}
			}
		}
		if ((actualDriverResiTotalEffVarInHrs==expectedDriverResiTotalEffVarInHrs)||(actualDriverResiTotalEffVarInHrs==expectedDriverResiTotalEffVarInHrs+1) || (actualDriverResiTotalEffVarInHrs==expectedDriverResiTotalEffVarInHrs-1))
		{
			Report.PassTest(test, "Actual Value from Summary Dashboard - "+actualDriverResiTotalEffVarInHrs+", is same as displayed on the Efficiency Summary Report - "+expectedDriverResiTotalEffVarInHrs);
			System.out.println("Actual is same as expected");
		}
		else
		{
			Report.FailTest(test, "Actual Value from Summary Dashboard - "+actualDriverResiTotalEffVarInHrs+", is NOT same as displayed on the Efficiency Summary Report - "+expectedDriverResiTotalEffVarInHrs);
			System.out.println("Actual is NOT same as expected");
		}
	
		
	}
}// Last closing bracket

		
	    
			 
			  
			  
			  
	   
		 
	    
    
    

