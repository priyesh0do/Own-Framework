package ObjectRepository.Logi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.gargoylesoftware.htmlunit.javascript.host.Set;

import SupportLibraries.Report;
import SupportLibraries.Util;

public class FlashReportBeta {

	ExtentTest test;
	WebDriver driver;

	public FlashReportBeta(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//table[@id='dtScorecardSummary']/thead/tr[2]/th")
	List<WebElement> summaryTableColumns;

	@FindBy(xpath = "//table[@id='dtScorecardSummary']/tbody/tr")
	List<WebElement> summaryTableRows;
	
	@FindBy(xpath="//*[@id='dtSummaryEfficiency']/thead/tr/th")
	List<WebElement> efficiencySummaryTableColumns;
	
	@FindBy(xpath="//*[@id='dtSummaryEfficiency']/tbody/tr")
	List<WebElement> efficiencySummaryTableRows;
	
	@FindBy(xpath="//*[@id='dtRouteSummary']/thead/tr/th")
	List<WebElement> DetailSubviewSummaryColumns;
	
	@FindBy(xpath="//*[@id='dtRouteSummary']/tbody/tr")
	List<WebElement> DetailSubviewSummaryRows;
	
	@FindBy(xpath="//*[@id='dtComplianceSummary']/thead/tr/th")
	List<WebElement> ComplianceSubviewSummaryTableColumns;

	@FindBy(xpath="//*[@id='dtComplianceSummary']/tbody/tr")
	List<WebElement> ComplianceSubviewSummaryTableRows;
	
	//ScorecardSubviewDetailTableColumns
	@FindBy(xpath="//*[@id='dtScorecardSubview']/thead/tr/th")
	List<WebElement> ScorecardSubviewDetailTableColumns;
	
	//ScorecardSubviewDetailTableRows	
	@FindBy(xpath="//*[@id='dtScorecardSubview']/tbody/tr")
	List<WebElement> ScorecardSubviewDetailTableRows;
	
	//EfficiencySubview
	@FindBy(xpath="//*[@id='inpSubReportOpt_2']")
	WebElement EfficiencySubview;
	
	//DetailSubview
	@FindBy(xpath="//*[@id='inpSubReportOpt_3']")
	WebElement DetailSubview;
	
	//ComplianceSubview
	@FindBy(xpath="//*[@id='inpSubReportOpt_4']")
	WebElement ComplianceSubview;
	
	//ScorecardSubview
	@FindBy(xpath="//*[@id='inpSubReportOpt_1']")
	WebElement ScorecardSubview;
	
	//NumberofPages
	@FindBy(xpath="//*[@id='dtScorecardSubview-of']/following-sibling::span[contains(@class,'horRight ipClas')]")
	WebElement NumberofPages;
	
	//ClickonNextButton
	@FindBy(xpath="//*[@id='dtScorecardSubview-NextPageCaption' and contains(text(),'Next')]")
	WebElement ClickonNextButton;
	
	//ClickonFirstButton
		@FindBy(xpath="//*[@id='dtScorecardSubview-FirstPageCaption' and contains(text(),'First')]")
		WebElement ClickonFirstButton;
		
	@FindBy(xpath="//*[@id='dtEfficiencySubview']/thead/tr/th")
	List<WebElement> EfficiencySubviewDetailTableColumns;
	
	@FindBy(xpath=".//*[@id='dtEfficiencySubview']/tbody/tr")
	List<WebElement> EfficiencySubviewDetailTableRows;	
	
	@FindBy(xpath="//*[@id='dtDetailSubivew']/thead/tr/th")
	List<WebElement> DetailSubviewDetailTableColumns;
	
	@FindBy(xpath="//*[@id='dtDetailSubivew']/tbody/tr")
	List<WebElement> DetailSubviewDetailTableRows;
	
	@FindBy(xpath="//*[@id='dtComplianceMainGrid']/thead/tr/th")
	List<WebElement> ComplianceSubviewDetailTableColumns;
	
	@FindBy(xpath="//*[@id='dtComplianceMainGrid']/tbody/tr")
	List<WebElement> ComplianceSubviewDetailTableRows;

	
	public Map<String, List<String>> readTable(List<WebElement> columns, List<WebElement> rows, String tableName) {

		Map<String, List<String>> objTable = new HashMap<>();
		for (int iCount = 1; iCount <= columns.size(); iCount++) {
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
	
	
	
	public Map<String, List<String>> getScoreCardSubviewSummaryTableData() throws IOException {
				Map<String, List<String>> ScorecardSubviewsummaryTableData = new HashMap<>();
		try {
			ScorecardSubviewsummaryTableData = readTable(summaryTableColumns, summaryTableRows, "dtScorecardSummary");
			Report.PassTestScreenshot(test, driver, "Scorecard Summary table data read successfully", "Summary Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Scorecard summary table data");
		}

		for (Entry<String, List<String>> entry : ScorecardSubviewsummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return ScorecardSubviewsummaryTableData;
	}
	
	
	

	public Map<String, List<String>> getEfficiencySubviewSummaryTableData() throws IOException {
				Map<String, List<String>> EfficiencySubviewsummaryTableData = new HashMap<>();
		try {
			EfficiencySubviewsummaryTableData = readTable(efficiencySummaryTableColumns, efficiencySummaryTableRows, "dtSummaryEfficiency");
			Report.PassTestScreenshot(test, driver, "Efficiency Summary table data read successfully", "Summary Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Efficiency summary table data");
		}

		for (Entry<String, List<String>> entry : EfficiencySubviewsummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return EfficiencySubviewsummaryTableData;
	}
	
	
	
	
	public Map<String, List<String>> getDetailSubviewSummaryTableData() throws IOException {
				Map<String, List<String>> DetailSubviewsummaryTableData = new HashMap<>();
		try {
			DetailSubviewsummaryTableData = readTable(DetailSubviewSummaryColumns, DetailSubviewSummaryRows, "dtRouteSummary");
			Report.PassTestScreenshot(test, driver, "Detail Subview Summary table data read successfully", "Summary Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Detail Subview summary table data");
		}
		
		for (Entry<String, List<String>> entry : DetailSubviewsummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return DetailSubviewsummaryTableData;
		}
	
	
	
	public Map<String, List<String>> getComplianceSubviewSummaryTableData() throws IOException {
				Map<String, List<String>> ComplianceSubviewsummaryTableData = new HashMap<>();
		try {
			ComplianceSubviewsummaryTableData = readTable(ComplianceSubviewSummaryTableColumns, ComplianceSubviewSummaryTableRows, "dtComplianceSummary");
			Report.PassTestScreenshot(test, driver, "Compliance Subview Summary table data read successfully", "Summary Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Compliance Subview summary table data");
		}
		
		for (Entry<String, List<String>> entry : ComplianceSubviewsummaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return ComplianceSubviewsummaryTableData;
		}
	
	
	
	public void RedirectedToEfficiencySubView() {
		try {
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");
			EfficiencySubview.click();
			Thread.sleep(2000);
			Report.PassTestScreenshot(test, driver,"Redirected to Efficiency Subview ", "PassedEfficiencySubView");
		} catch (Exception e) {
			Report.FailTestSnapshot(test, driver,"Unable to Redirect Efficiency Subview ", "EfficiencySubViewFailed");
		}
		
		
		
	}
	
	public void RedirectedToDetailSubView() {
		try {
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");
			DetailSubview.click();
			Thread.sleep(2000);
			Report.PassTestScreenshot(test, driver,"Redirected to Detail Subview ", "PassedDetailSubView");
		} catch (Exception e) {
			Report.FailTestSnapshot(test, driver,"Unable to Redirect Detail Subview ", "DetailSubViewFailed");
		}
		
		
	}
	
	
	
	
	public void RedirectedToComplianceSubView() {
		try {
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");
			ComplianceSubview.click();
			Thread.sleep(2000);
			Report.PassTestScreenshot(test, driver,"Redirected to Compliance Subview", "PassedComplianceSubview");
		} catch (Exception e) {
			Report.FailTestSnapshot(test, driver,"Unable to Redirect Compliance Subview ", "ComplianceSubviewFailed");
		}
		
		
	}
	
	
	
	public void RedirectedToScorecardSubView() {
		try {
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");
			ScorecardSubview.click();
			Thread.sleep(2000);
			Report.PassTestScreenshot(test, driver,"Redirected to Scorecard Subview", "PassedScorecardSubview");
		} catch (Exception e) {
			Report.FailTestSnapshot(test, driver,"Unable to Redirect Scoreboard Subview ", "ScoreboardSubviewFailed");
		}
		
		
	}
	
	
	
	
	public Map<String, List<String>> getSocrecardSubviewDetailTableData() throws IOException {
		Map<String, List<String>> ScorecardSubviewDetailTableData = new HashMap<>();
	try {
		ScorecardSubviewDetailTableData = readTable2(ScorecardSubviewDetailTableColumns, ScorecardSubviewDetailTableRows, "dtScorecardSubview");
		Report.PassTestScreenshot(test, driver, "ScoreCard Subview Detail table data read successfully", "SocrecardSubview Detail Data");
	} catch (Exception e) {
		Report.FailTest(test, "No able to read Scorecard Subview Detail table data");
	}
	
	for (Entry<String, List<String>> entry : ScorecardSubviewDetailTableData.entrySet()) {
		System.out.println(entry.getKey() + ":" + entry.getValue().toString());
	}
	return ScorecardSubviewDetailTableData;
	}
	
	
	
	
	public Map<String, List<String>> getEfficiencySubviewDetailTableData() throws IOException {
		Map<String, List<String>> EfficiencySubviewDetailTableData = new HashMap<>();
	try {
		EfficiencySubviewDetailTableData = readTable2(EfficiencySubviewDetailTableColumns, EfficiencySubviewDetailTableRows, "dtEfficiencySubview");
		Report.PassTestScreenshot(test, driver, "Efficiency Subview Detail table data read successfully", "EfficiencySubview Detail Data");
	} catch (Exception e) {
		Report.FailTest(test, "No able to read Efficiency Subview Detail table data");
	}
	
	for (Entry<String, List<String>> entry : EfficiencySubviewDetailTableData.entrySet()) {
		System.out.println(entry.getKey() + ":" + entry.getValue().toString());
	}
	return EfficiencySubviewDetailTableData;
	}
	
	
	
	
	public Map<String, List<String>> getDetailSubviewDetailTableData() throws IOException {
		Map<String, List<String>> DetailSubviewDetailTableData = new HashMap<>();
	try {
		DetailSubviewDetailTableData = readTable2(DetailSubviewDetailTableColumns, DetailSubviewDetailTableRows, "dtDetailSubivew");
		Report.PassTestScreenshot(test, driver, "DetailSubview Detail table data read successfully", "DetailSubview Detail Data");
	} catch (Exception e) {
		Report.FailTest(test, "No able to read DetailSubview Detail table data");
	}
	
	for (Entry<String, List<String>> entry : DetailSubviewDetailTableData.entrySet()) {
		System.out.println(entry.getKey() + ":" + entry.getValue().toString());
	}
	return DetailSubviewDetailTableData;
	}
	
	

	
	public Map<String, List<String>> getComplianceSubviewDetailTableData() throws IOException {
		Map<String, List<String>> ComplianceSubviewDetailTableData = new HashMap<>();
	try {
		ComplianceSubviewDetailTableData = readTable2(ComplianceSubviewDetailTableColumns, ComplianceSubviewDetailTableRows, "dtComplianceMainGrid");
		Report.PassTestScreenshot(test, driver, "Compliance Subview Detail table data read successfully", "Compliance Subview Detail Data");
	} catch (Exception e) {
		Report.FailTest(test, "No able to read Compliance Subview Detail table data");
	}
	
	for (Entry<String, List<String>> entry : ComplianceSubviewDetailTableData.entrySet()) {
		System.out.println(entry.getKey() + ":" + entry.getValue().toString());
	}
	return ComplianceSubviewDetailTableData;
	}
	
		
	
	public Map<String, List<String>> readTable2(List<WebElement> columns, List<WebElement> rows, String tableName){
		Map<String, List<String>> objTable = new HashMap<>();
		try {
		Util.switchToDefaultWindow(); 
		Util.selectFrame("opusReportContainer,subReportContainer");
		String NumofRecords=NumberofPages.getText();
		int page=Integer.parseInt(NumofRecords);
		
			for (int iCount = 1; iCount <= columns.size(); iCount++) {
			List<String> rowValues = new ArrayList<>();
			for (int i = 0; i < page; i++) {
			for (int row = 1; row <= rows.size(); row=row+4) {
					try {
						rowValues.add(driver
								.findElement(By.xpath(
										"//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + iCount + "]/span"))
								.getText());				
					} 
					
					catch (Exception e) {
						rowValues.add(driver
								.findElement(By
										.xpath("//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + iCount + "]"))
								.getText());
					}
					
				}
			if (page>1) {
				ClickonNextButton.click();
				Thread.sleep(2000);
			}
			
				
						
			
		}	
			objTable.put(columns.get(iCount - 1).getText(), rowValues);
			
			if (page>1) {
				ClickonFirstButton.click();
				Thread.sleep(2000);
			}
				
				
				
		}
		   	
		}
				
		catch (Exception e) {
			Report.FailTest(test, "Unable to Read Table2");
			
		}
		
		return objTable;
	
	
	}
	
	
	public void ValidateScorecardSubviewSummaryData(Map<String, List<String>> getScoreCardSubviewSummaryTableData,Map<String, List<String>> getSocrecardSubviewDetailTableData,String expectedLOB) {
		validateLOB(getScoreCardSubviewSummaryTableData, expectedLOB, "Scorecard");
		validateSubLOB(getScoreCardSubviewSummaryTableData, getSocrecardSubviewDetailTableData, "Scorecard");
		//validateMIE();//Future enhancement
		//validateHoS();//Future enhancement
		validateOBAs(getScoreCardSubviewSummaryTableData, getSocrecardSubviewDetailTableData);
		
	}
	
	public void validateLOB(Map<String, List<String>> getScoreCardSubviewSummaryTableData, String expectedLOB, String subViewReportName) {
		String actualLOB = null;
		try {
			for (Entry<String, List<String>> entry : getScoreCardSubviewSummaryTableData.entrySet()) {
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
			

			if (actualLOB.equals(expectedLOB.toUpperCase())) {

				Report.InfoTest(test, "LOB is correct in "+subViewReportName+" Flash Report Beta report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
				Report.PassTest(test, "LOB is as expected in "+subViewReportName+" Flash Report Beta report");
			} else {
				Report.FailTest(test, "LOB is not as expected in "+subViewReportName+" Flash Report Beta report Actual is : "
						+ actualLOB + " and Expected is : " + expectedLOB);
			}
		} catch (Exception e) {
			Report.FailTest(test, "LOB is not as expected in "+subViewReportName+" Flash Report Beta report Actual List is : " + actualLOB + " and Expected is : " + expectedLOB);
		}	
}
	
	public void validateSubLOB(Map<String, List<String>> getScoreCardSubviewSummaryTableData,Map<String, List<String>> getSocrecardSubviewDetailTableData, String Subview) {
				List<String> actualSubLOB = new ArrayList<>();
				List<String> expectedSubLOB = new ArrayList<>();
				

				try {
					for (Entry<String, List<String>> entry : getScoreCardSubviewSummaryTableData.entrySet()) {
						if (entry.getKey().equals("Sub LOB")) {
							expectedSubLOB = entry.getValue();
							
						}
					}
					
					String expectedSubLOBs[]=expectedSubLOB.get(0).split(",");
					
					for(int i=0;i<expectedSubLOBs.length;i++)
					{
						expectedSubLOBs[i]=expectedSubLOBs[i].trim();
					}
					expectedSubLOB = Arrays.asList(expectedSubLOBs);  
						    
					for (Entry<String, List<String>> entry : getSocrecardSubviewDetailTableData.entrySet()) {
						if (entry.getKey().equals("Sub LOB")) {
							actualSubLOB = entry.getValue();
						}
					}
					
					for (int i = 0; i < actualSubLOB.size(); i++) {
					
					
					
				if (expectedSubLOB.contains(actualSubLOB.get(i))) {
					Report.PassTestScreenshot(test, driver, "Sub Lob is Expected in " + Subview + "Actual Sub LOB from Detail Table is" + actualSubLOB.get(i) + "and Expected Sub LOB from Summary Table is "  + expectedSubLOB , "PassedSubLOB");
				}	
				else {
					Report.FailTestSnapshot(test, driver, "Sub Lob is Not Expected in " + Subview + "Actual Sub LOB from Detail Table is" + actualSubLOB.get(i) + "and Expected Sub LOB from Summary Table is "  + expectedSubLOB , "FailedSubLOB");
				}
			
					}
				} catch (Exception e) {
					Report.FailTest(test, e.getMessage());
					Report.FailTest(test, "Sub LOB is not as expected in Flash Report Beta report Actual is : "
							+ actualSubLOB.toString() + " and Expected is : " + expectedSubLOB.toString());
				}
			
		}
	
	public void validateOBAs(Map<String, List<String>> getScoreCardSubviewSummaryTableData,Map<String, List<String>> getSocrecardSubviewDetailTableData) {
		
		List<String> actualOBAs = new ArrayList<>();
		int expectedOBAs=0;
		java.util.Set<String> hs = new HashSet<>();
		
		
	try {
		
		for (Entry<String, List<String>> entry : getScoreCardSubviewSummaryTableData.entrySet()) {
			if (entry.getKey().contains("OBAs")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						expectedOBAs = 0;
					} else {
						expectedOBAs = Integer.parseInt(entry.getValue().get(i));
					}
				}
			}
		}
		
		for (Entry<String, List<String>> entry : getSocrecardSubviewDetailTableData.entrySet()) {
			if (entry.getKey().equals("OBAs")) {
				actualOBAs = entry.getValue();
			}
		}
		hs.addAll(actualOBAs);
		actualOBAs.clear();
		actualOBAs.addAll(hs);
		System.out.println(actualOBAs);
		
		
		
		
		
	} catch (Exception e) {
		
	} 

		
		
	}
	  
	   
}
