package ObjectRepository.SDO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.aventstack.extentreports.ExtentTest;


import ObjectRepository.General.FilterSection;
import ObjectRepository.Logi.DowntimeDetailReport;
import ObjectRepository.Logi.IdleDetailReport;
import ObjectRepository.Logi.PostRouteDetailReport;
import ObjectRepository.Logi.PreRouteDetailReport;
import ObjectRepository.Logi.SequenceComplianceDetailReport;
import SupportLibraries.*;
import TestData.*;

public class RMDashboardReport {

	ExtentTest test;
	WebDriver driver;

	public RMDashboardReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = ".//*[@id='rdRows-2']/tbody/tr[@id='Row4']/td/div/div/table/tbody")
	WebElement CustomerFocusTable;
	
	@FindBy (id="dtCustomerFocusTable")
	WebElement CustomerFocusTable_ID;
	
	@FindBy (xpath = ".//*[@id='dtCustomerFocusTable']/colgroup/col")
	List<WebElement> CustomerFocusSectionColumns;
	
	@FindBy (xpath = ".//*[@id='dtCustomerFocusTable']/tbody/tr")
	List<WebElement> CustomerFocusSectionRows;
	
	@FindBy (id ="inpDateOptionGroup_1")
	WebElement DOWGraphRadioButton;

	@FindBy(id = "inpDateOptionGroup_2")
	WebElement HistoricalGraphRadioButton;

	@FindBy(xpath = "//td[@id='colChartComm']//td/span")
	WebElement commercialGraphHeader;

	@FindBy(xpath = "//td[@id='colChartRol']//td/span[1]")
	WebElement rollOffGraphHeader;

	@FindBy(xpath = "//td[@id='colChartResi']//td/span")
	WebElement residentialGraphHeader;

	@FindBy(xpath = "//td[@id='colChartComm']//*[@xmlns='http://www.w3.org/2000/svg']//*[@class=' highcharts-xaxis-title']/*")
	WebElement commercialGraphFooter;

	@FindBy(xpath = "//td[@id='colChartRol']//*[@xmlns='http://www.w3.org/2000/svg']//*[@class=' highcharts-xaxis-title']/*")
	WebElement rollOffGraphFooter;

	@FindBy(xpath = "//td[@id='colChartResi']//*[@xmlns='http://www.w3.org/2000/svg']//*[@class=' highcharts-xaxis-title']/*")
	WebElement residentialGraphFooter;

	@FindBy(xpath = ".//td[@id='colChartComm']//*[@xmlns='http://www.w3.org/2000/svg']/*[@class='highcharts-series-group']/*[@class='highcharts-markers highcharts-tracker']/*[@fill = '#77B310'][1]")
	WebElement commercialGraphTooltipHover;

	@FindBy(xpath = "//td[@id='colChartRol']//*[@xmlns='http://www.w3.org/2000/svg']/*[@class='highcharts-series-group']/*[@class='highcharts-markers highcharts-tracker']/*[@fill = '#77B310'][1]")
	WebElement rollOffGraphTooltipHover;

	@FindBy(xpath = "//td[@id='colChartResi']//*[@xmlns='http://www.w3.org/2000/svg']/*[@class='highcharts-series-group']/*[@class='highcharts-markers highcharts-tracker']/*[@fill = '#77B310'][1]")
	WebElement residentialGraphTooltipHover;

	@FindBy(xpath = "//td[@id='colChartComm']//*[(@class='highcharts-tooltip') and contains(@style, 'visibility: visible')]/span/div/div[@class='header']")
	WebElement commercialGraphToolTipHeader;

	@FindBy(xpath = "//td[@id='colChartComm']//*[@class='highcharts-tooltip']/span/div/div/table/tbody/tr/td[2]")
	WebElement commercialGraphToolTipActualEfficiency;

	@FindBy(xpath = "//td[@id='colChartRol']//*[@class='highcharts-tooltip']/span/div/div[@class='header']")
	WebElement rollOffGraphToolTipHeader;

	@FindBy(xpath = "//td[@id='colChartRol']//*[@class='highcharts-tooltip']/span/div/div/table/tbody/tr/td[2]")
	WebElement rollOffGraphToolTipActualEfficiency;

	@FindBy(xpath = "//td[@id='colChartResi']//*[@class='highcharts-tooltip']/span/div/div[@class='header']")
	WebElement residentialGraphToolTipHeader;

	@FindBy(xpath = "//td[@id='colChartResi']//*[@class='highcharts-tooltip']/span/div/div/table/tbody/tr/td[2]")
	WebElement residentialGraphToolTipActualEfficiency;

	@FindBy(xpath = ".//td[@id='colChartComm']//*[@xmlns='http://www.w3.org/2000/svg']/*[@class='highcharts-no-data']/*/*")
	List<WebElement> commercialGraphNoRecordToDisplay;

	@FindBy(xpath = ".//td[@id='colChartRol']//*[@xmlns='http://www.w3.org/2000/svg']/*[@class='highcharts-no-data']/*/*")
	List<WebElement> rollOffGraphNoRecordToDisplay;

	@FindBy(xpath = ".//td[@id='colChartResi']//*[@xmlns='http://www.w3.org/2000/svg']/*[@class='highcharts-no-data']/*/*")
	List<WebElement> residentialGraphNoRecordToDisplay;

	@FindBy(xpath = "//table[@id='dtEfficiencySummary1']/tbody")
	List<WebElement> commercialEfficiencySummarySection;

	@FindBy(xpath = "//table[@id='dtEfficiencySummary2']/tbody")
	List<WebElement> rollOffEfficiencySummarySection;

	@FindBy(xpath = "//table[@id='dtEfficiencySummary3']/tbody")
	List<WebElement> residentialEfficiencySummarySection;

	@FindBy(xpath = "//table[@id='dtEfficiencySummary1']/tbody/tr[2]/td/div[@id='divActualEff_Row2']/span")
	WebElement commercialEfficiency;

	@FindBy(xpath = "//table[@id='dtEfficiencySummary2']/tbody/tr[2]/td/div[@id='divActualEff_Row2']/span")
	WebElement rollOffEfficiency;

	@FindBy(xpath = "//table[@id='dtEfficiencySummary3']/tbody/tr[2]/td/div[@id='divActualEff_Row2']/span")
	WebElement residentialEfficiency;

	@FindBy(xpath = "//button[@title = 'Variance Min per Haul']/span[2]")
	WebElement rollOffGraphType;

	@FindBy(xpath = "//button[@title = 'Variance Min per Haul']")
	WebElement rollOffGraphDropdown;

	@FindBy(xpath = "//input[@title='Equivalent Hauls per Hour']/following-sibling::span")
	WebElement optionEquivalentHaulsPerHour;

	@FindBy(xpath = "//input[@title='Variance Min per Haul']/following-sibling::span")
	WebElement optionVarianceMinPerHaul;

	@FindBy(xpath = "//table[@id='dtTopDriversCom']/colgroup/col")
	List<WebElement> top5ComDriversTableColumns;

	@FindBy(xpath = "//table[@id='dtTopDriversCom']/tbody/tr")
	List<WebElement> top5ComDriversTableRows;

	@FindBy(xpath = "//table[@id='dtTopDriversRol']/colgroup/col")
	List<WebElement> top5RollOffDriversTableColumns;

	@FindBy(xpath = "//table[@id='dtTopDriversRol']/tbody/tr")
	List<WebElement> top5RollOffDriversTableRows;

	@FindBy(xpath = "//table[@id='dtTopDriversRes']/colgroup/col")
	List<WebElement> top5ResiDriversTableColumns;

	@FindBy(xpath = "//table[@id='dtTopDriversRes']/tbody/tr")
	List<WebElement> top5ResiDriversTableRows;

	@FindBy(xpath = ".//*[@id='lblCrumb1']")
	WebElement backToRMDashBoard;

	@FindBy(xpath = "//table[@id='dtBottomDriversCom']/colgroup/col")
	List<WebElement> bottom5ComDriversTableColumns;

	@FindBy(xpath = "//table[@id='dtBottomDriversCom']/tbody/tr")
	List<WebElement> bottom5ComDriversTableRows;

	@FindBy(xpath = "//table[@id='dtBottomDriversRol']/colgroup/col")
	List<WebElement> bottom5RollOffDriversTableColumns;

	@FindBy(xpath = "//table[@id='dtBottomDriversRol']/tbody/tr")
	List<WebElement> bottom5RollOffDriversTableRows;

	@FindBy(xpath = "//table[@id='dtBottomDriversRes']/colgroup/col")
	List<WebElement> bottom5ResiDriversTableColumns;

	@FindBy(xpath = "//table[@id='dtBottomDriversRes']/tbody/tr")
	List<WebElement> bottom5ResiDriversTableRows;

	@FindBy(xpath = "//table[@id='rdRows-2']/tbody/tr/td/span[contains(., 'Walk & Talk Coaching Opportunities')]")
	List<WebElement> walkAndTalkCoachingOpportunitiesSection;

	@FindBy(id = "inpSubRptOpt_1")
	WebElement netIdleRadioButton;

	@FindBy(xpath = "//input[@id='inpSubRptOpt_1']/following-sibling::label[1]")
	WebElement netIdleLabel;

	@FindBy(id = "inpSubRptOpt_2")
	WebElement preRouteRadioButton;

	@FindBy(xpath = "//input[@id='inpSubRptOpt_2']/following-sibling::label[1]")
	WebElement preRouteLabel;

	@FindBy(id = "inpSubRptOpt_3")
	WebElement postRouteRadioButton;

	@FindBy(xpath = "//input[@id='inpSubRptOpt_3']/following-sibling::label[1]")
	WebElement postRouteLabel;

	@FindBy(id = "inpSubRptOpt_4")
	WebElement sequenceComplianceRadioButton;

	@FindBy(xpath = "//input[@id='inpSubRptOpt_4']/following-sibling::label[1]")
	WebElement sequenceComplianceLabel;

	@FindBy(id = "inpSubRptOpt_5")
	WebElement downtimeRadioButton;

	@FindBy(xpath = "//input[@id='inpSubRptOpt_5']/following-sibling::label[1]")
	WebElement downtimeLabel;

	@FindBy(xpath = "//table[@id='dtWalkNTalkNetIdleCom']/colgroup/col")
	List<WebElement> netIdleComOpportunitiesColumns;

	@FindBy(xpath = "//table[@id='dtWalkNTalkNetIdleCom']/tbody/tr")
	List<WebElement> netIdleComOpportunitiesRows;

	@FindBy(xpath = "//table[@id='dtWalkNTalkPreCom']/colgroup/col")
	List<WebElement> preRouteComOpportunitiesColumns;

	@FindBy(xpath = "//table[@id='dtWalkNTalkPreCom']/tbody/tr")
	List<WebElement> preRouteComOpportunitiesRows;

	@FindBy(xpath = "//table[@id='dtWalkNTalkPostCom']/colgroup/col")
	List<WebElement> postRouteComOpportunitiesColumns;

	@FindBy(xpath = "//table[@id='dtWalkNTalkPostCom']/tbody/tr")
	List<WebElement> postRouteComOpportunitiesRows;

	@FindBy(xpath = "//table[@id='dtWalkNTalkSeqCom']/colgroup/col")
	List<WebElement> sequenceComplianceComOpportunitiesColumns;

	@FindBy(xpath = "//table[@id='dtWalkNTalkSeqCom']/tbody/tr")
	List<WebElement> sequenceComplianceComOpportunitiesRows;

	@FindBy(xpath = "//table[@id='dtWalkNTalkDowntimeCom']/colgroup/col")
	List<WebElement> downtimeComOpportunitiesColumns;

	@FindBy(xpath = "//table[@id='dtWalkNTalkDowntimeCom']/tbody/tr")
	List<WebElement> downtimeComOpportunitiesRows;

	@FindBy(xpath = "//table[@id='dtWalkNTalkNetIdleRol']/colgroup/col")
	List<WebElement> netIdleRolOpportunitiesColumns;

	@FindBy(xpath = "//table[@id='dtWalkNTalkNetIdleRol']/tbody/tr")
	List<WebElement> netIdleRolOpportunitiesRows;

	@FindBy(xpath = "//table[@id='dtWalkNTalkPreRol']/colgroup/col")
	List<WebElement> preRouteRolOpportunitiesColumns;

	@FindBy(xpath = "//table[@id='dtWalkNTalkPreRol']/tbody/tr")
	List<WebElement> preRouteRolOpportunitiesRows;

	@FindBy(xpath = "//table[@id='dtWalkNTalkPostRol']/colgroup/col")
	List<WebElement> postRouteRolOpportunitiesColumns;

	@FindBy(xpath = "//table[@id='dtWalkNTalkPostRol']/tbody/tr")
	List<WebElement> postRouteRolOpportunitiesRows;

	@FindBy(xpath = "//table[@id='dtWalkNTalkSeqRol']/colgroup/col")
	List<WebElement> sequenceComplianceRolOpportunitiesColumns;

	@FindBy(xpath = "//table[@id='dtWalkNTalkSeqRol']/tbody/tr")
	List<WebElement> sequenceComplianceRolOpportunitiesRows;

	@FindBy(xpath = "//table[@id='dtWalkNTalkDowntimeRol']/colgroup/col")
	List<WebElement> downtimeRolOpportunitiesColumns;

	@FindBy(xpath = "//table[@id='dtWalkNTalkDowntimeRol']/tbody/tr")
	List<WebElement> downtimeRolOpportunitiesRows;

	@FindBy(xpath = "//table[@id='dtWalkNTalkNetIdleRes']/colgroup/col")
	List<WebElement> netIdleResOpportunitiesColumns;

	@FindBy(xpath = "//table[@id='dtWalkNTalkNetIdleRes']/tbody/tr")
	List<WebElement> netIdleResOpportunitiesRows;

	@FindBy(xpath = "//table[@id='dtWalkNTalkPreRes']/colgroup/col")
	List<WebElement> preRouteResOpportunitiesColumns;

	@FindBy(xpath = "//table[@id='dtWalkNTalkPreRes']/tbody/tr")
	List<WebElement> preRouteResOpportunitiesRows;

	@FindBy(xpath = "//table[@id='dtWalkNTalkPostRes']/colgroup/col")
	List<WebElement> postRouteResOpportunitiesColumns;

	@FindBy(xpath = "//table[@id='dtWalkNTalkPostRes']/tbody/tr")
	List<WebElement> postRouteResOpportunitiesRows;

	@FindBy(xpath = "//table[@id='dtWalkNTalkSeqRes']/colgroup/col")
	List<WebElement> sequenceComplianceResOpportunitiesColumns;

	@FindBy(xpath = "//table[@id='dtWalkNTalkSeqRes']/tbody/tr")
	List<WebElement> sequenceComplianceResOpportunitiesRows;

	@FindBy(xpath = "//table[@id='dtWalkNTalkDowntimeRes']/colgroup/col")
	List<WebElement> downtimeResOpportunitiesColumns;

	@FindBy(xpath = "//table[@id='dtWalkNTalkDowntimeRes']/tbody/tr")
	List<WebElement> downtimeResOpportunitiesRows;
	
	
	@FindBy(id = "colChartComm")
	WebElement commercialGraph;
	
	@FindBy(id = "colChartRol")
	WebElement RollOffGraph;
	
	@FindBy(id = "colChartResi")
	WebElement ResidentialGraph;

	@FindBy(xpath = "//*[@id='divExport']/a/span")
	WebElement PDFLink;

	@FindBy(xpath = "//table[@id='dtEfficiencySummary1']//th[@id='colPlanEff-TH']")
	WebElement commPlanEfficiencyLabel;
	
	@FindBy(xpath = "//table[@id='dtEfficiencySummary1']//th[@id='colActualEff-TH']")
	WebElement commActualEfficiencyLabel;
	
	@FindBy(xpath = "//table[@id='dtEfficiencySummary1']//th[@id='colGap-TH']")
	WebElement commGapLabel;
	
	@FindBy(xpath = "//table[@id='dtEfficiencySummary2']//th[@id='colAllowance-TH']")
	WebElement rollOffAllowanceLabel;
	
	@FindBy(xpath = "//table[@id='dtEfficiencySummary2']//th[@id='colActualHours-TH']")
	WebElement rollOffActualLabel;
	
	@FindBy(xpath = "//table[@id='dtEfficiencySummary2']//th[@id='colGap-TH']")
	WebElement rollOffGapLabel;
	
	@FindBy(xpath = "//table[@id='dtEfficiencySummary3']//th[@id='colPlanEff-TH']")
	WebElement resiPlanEfficiencyLabel;
	
	@FindBy(xpath = "//table[@id='dtEfficiencySummary3']//th[@id='colActualEff-TH']")
	WebElement resiActualEfficiencyLabel;
	
	@FindBy(xpath = "//table[@id='dtEfficiencySummary3']//th[@id='colGap-TH']")
	WebElement resiGapLabel;
	
	@FindBy(xpath = "//tr[@id='Row2']/td[1]/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement top5DriverLabelComm;
	
	@FindBy(xpath = "//tr[@id='Row2']/td[1]/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement top5DriverHMLabelComm;
	
	@FindBy(xpath = "//tr[@id='Row2']/td[2]/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement	top5DriverLabelRO;
		
	@FindBy(xpath = "//tr[@id='Row2']/td[2]/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement top5DriverHMLabelRO;
		
    @FindBy(xpath = "//tr[@id='Row2']/td[3]/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement top5DriverLabelResi;
		
	@FindBy(xpath = "//tr[@id='Row2']/td[3]/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement top5DriverHMLabelResi;
	
	@FindBy(xpath = "//tr[@id='Row3']/td[1]/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement bottom5DriverLabelComm;
	
	@FindBy(xpath = "//tr[@id='Row3']/td[1]/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement bottom5DriverHMLabelComm;
	
	@FindBy(xpath = "//tr[@id='Row3']/td[2]/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement	 bottom5DriverLabelRO;
		
	@FindBy(xpath = "//tr[@id='Row3']/td[2]/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement  bottom5DriverHMLabelRO;
		
    @FindBy(xpath = "//tr[@id='Row3']/td[3]/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement  bottom5DriverLabelResi;
		
	@FindBy(xpath = "//tr[@id='Row3']/td[3]/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement  bottom5DriverHMLabelResi;
	
	@FindBy(xpath = "//tr[@id='Row3']/following-sibling::tr[1]/td/span")
	WebElement walkAndTalkSectionHeader;
	
	@FindBy(xpath = "//span[@id='divIdleTmCom']/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement walkAndTalkTopOpportunitiesNetIdleLabelComm;
	
	@FindBy(xpath = "//span[@id='divIdleTmCom']/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement walkAndTalkHMNetIdleLabelComm;
	
	@FindBy(xpath = "//span[@id='divIdleTmRol']/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement	walkAndTalkTopOpportunitiesNetIdleLabelRO;
		
	@FindBy(xpath = "//span[@id='divIdleTmRol']/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement walkAndTalkHMNetIdleLabelRO;
		
    @FindBy(xpath = "//span[@id='divIdleTmRes']/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement walkAndTalkTopOpportunitiesNetIdleLabelResi;
		
	@FindBy(xpath = "//span[@id='divIdleTmRes']/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement walkAndTalkHMNetIdleLabelResi;
	
	@FindBy(xpath = "//span[@id='divPreTmCom']/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement walkAndTalkTopOpportunitiesPreRouteLabelComm;
	
	@FindBy(xpath = "//span[@id='divPreTmCom']/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement walkAndTalkHMPreRouteLabelComm;
	
	@FindBy(xpath = "//span[@id='divPreTmRol']/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement	walkAndTalkTopOpportunitiesPreRouteLabelRO;
		
	@FindBy(xpath = "//span[@id='divPreTmRol']/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement walkAndTalkHMPreRouteLabelRO;
		
    @FindBy(xpath = "//span[@id='divPreTmRes']/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement walkAndTalkTopOpportunitiesPreRouteLabelResi;
		
	@FindBy(xpath = "//span[@id='divPreTmRes']/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement walkAndTalkHMPreRouteLabelResi;
	
	@FindBy(xpath = "//span[@id='divPostTmCom']/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement walkAndTalkTopOpportunitiesPostRouteLabelComm;
	
	@FindBy(xpath = "//span[@id='divPostTmCom']/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement walkAndTalkHMPostRouteLabelComm;
	
	@FindBy(xpath = "//span[@id='divPostTmRol']/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement	walkAndTalkTopOpportunitiesPostRouteLabelRO;
		
	@FindBy(xpath = "//span[@id='divPostTmRol']/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement walkAndTalkHMPostRouteLabelRO;
		
    @FindBy(xpath = "//span[@id='divPostTmRes']/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement walkAndTalkTopOpportunitiesPostRouteLabelResi;
		
	@FindBy(xpath = "//span[@id='divPostTmRes']/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement walkAndTalkHMPostRouteLabelResi;
	
	@FindBy(xpath = "//span[@id='divSequenceComplianceCom']/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement walkAndTalkTopOpportunitiesSequenceComplianceLabelComm;
	
	@FindBy(xpath = "//span[@id='divSequenceComplianceCom']/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement walkAndTalkHMSequenceComplianceLabelComm;
	
	@FindBy(xpath = "//span[@id='divSequenceComplianceRol']/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement	walkAndTalkTopOpportunitiesSequenceComplianceLabelRO;
		
	@FindBy(xpath = "//span[@id='divSequenceComplianceRol']/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement walkAndTalkHMSequenceComplianceLabelRO;
		
    @FindBy(xpath = "//span[@id='divSequenceComplianceRes']/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement walkAndTalkTopOpportunitiesSequenceComplianceLabelResi;
		
	@FindBy(xpath = "//span[@id='divSequenceComplianceRes']/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement walkAndTalkHMSequenceComplianceLabelResi;
	
	@FindBy(xpath = "//span[@id='divDowntimeCom']/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement walkAndTalkTopOpportunitiesDowntimeLabelComm;
	
	@FindBy(xpath = "//span[@id='divDowntimeCom']/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement walkAndTalkHMDowntimeLabelComm;
	
	@FindBy(xpath = "//span[@id='divDowntimeRol']/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement	walkAndTalkTopOpportunitiesDowntimeLabelRO;
		
	@FindBy(xpath = "//span[@id='divDowntimeRol']/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement walkAndTalkHMDowntimeLabelRO;
		
    @FindBy(xpath = "//span[@id='divDowntimeRes']/table/tbody/tr/td/table/tbody/tr/td[1]/span")
	WebElement walkAndTalkTopOpportunitiesDowntimeLabelResi;
		
	@FindBy(xpath = "//span[@id='divDowntimeRes']/table/tbody/tr/td/table/tbody/tr/td[2]/span")
	WebElement walkAndTalkHMDowntimeLabelResi;
	
	@FindBy(xpath = "//tr[@id='Row4']/preceding-sibling::tr[2]//span")
	WebElement customerFocusSectionHeader;
	
	
	@FindBy(xpath = "//span[@id='lblLOB_Row1']")
	WebElement comLOBLabel;
	
	@FindBy(xpath = "//span[@id='lblLOB_Row2']")
	WebElement ResiLOBLabel;
	
	@FindBy(xpath = "//span[@id='lblLOB_Row3']")
	WebElement ROLOBLabel;

	public void VerifyCustomerFocusTable() throws IOException, InterruptedException {
		Thread.sleep(5000);
		driver.switchTo().frame("opusReportContainer").switchTo().frame("subReportContainer").switchTo()
				.frame("subRptRMHuddle");

		Report.InfoTest(test, "Verify that Customer Focus Table is getting displayed or not");

		Thread.sleep(5000);

		if (driver.findElements(By.xpath(".//*[@id='rdRows-2']/tbody/tr[@id='Row4']/td/div/div/table/tbody"))
				.size() > 0) {
			Report.PassTest(test, "Customer Focus Table is getting displayed");
			Report.PassTestScreenshot(test, driver, "Customer Focus Table is getting displayed", "Yes");
			// System.out.println("Customer Focus Table is getting displayed");
		} else {
			Report.FailTest(test, "Customer Focus Table is not getting displayed");
			// System.out.println("Customer Focus Table is not getting
			// displayed.");
		}
		Util.waitForElement(driver, CustomerFocusTable);
		Util.ElementExist(driver, CustomerFocusTable);
		driver.switchTo().defaultContent();
	}

	public void VerifyColumnName_CustomerFocusTable(String[] columnname) {
		driver.switchTo().frame("opusReportContainer").switchTo().frame("subReportContainer").switchTo()
				.frame("subRptRMHuddle");
		Util.verifyColumnNames(CustomerFocusTable, columnname);
		driver.switchTo().defaultContent().switchTo().defaultContent();
	}

	public void VerifyNoOfRows() {
		driver.switchTo().frame("opusReportContainer").switchTo().frame("subReportContainer").switchTo()
				.frame("subRptRMHuddle");
		if (driver.findElements(By.xpath(".//*[@id='rdRows-2']/tbody/tr[@id='Row4']/td/div/div/table/tbody/tr"))
				.size() != 3) {

			Report.FailTest(test, "There are no rows for each LOB - COM : RES : ROL");

		} else
			Report.PassTest(test, "There are 3 rows for each LOB - COM : RES :ROL");

		driver.switchTo().defaultContent();

	}

	public void getDatafromDBandApp() throws ClassNotFoundException, SQLException {

		String NoOfCustomerScheduledDB = DB.reqdata(GlobalQuery.NoofCustomerScheduledCOM, "");

		driver.switchTo().frame("opusReportContainer").switchTo().frame("subReportContainer").switchTo()
				.frame("subRptRMHuddle");

		String NoOfCustomerScheduledDisplayed = driver
				.findElement(By.xpath("//*[@id='rdRows-2']/tbody/tr[@id='Row4']/td/div/div/table/tbody/tr[1]/td[2]"))
				.getText();

		int NCSD = Integer.parseInt(NoOfCustomerScheduledDisplayed);
		int NCSDB = Integer.parseInt(NoOfCustomerScheduledDB);

		Util.datavalidation_Int(NCSDB, NCSD);
		driver.switchTo().defaultContent();

		// *[@id='lblNumOfCustScheduled_Row1']
		// *[@id='rdRows-2']/tbody/tr[@id='Row4']/td/div/div/table/tbody/tr[1]/td[2]
	}

	public void isDOWGraphSelected() {
		try {
			if (Util.isRadioButtonSelected(DOWGraphRadioButton)) {
				Report.PassTestScreenshot(test, driver, "DOW graph is selected by default", "DOW Graph");
			} else {
				Report.FailTest(test, "DOW graph is not selected by default");
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Not able to Locate DOW Graph radio button");
		}
	}

	public void isHistoricalGraphSelected() {
		try {
			if (Util.isRadioButtonSelected(HistoricalGraphRadioButton)) {
				Report.PassTest(test, "Historical graph is selected");
				Util.CaptureScreenshot("Historical graph");
			} else {
				Report.FailTest(test, "Historical graph is not selected");
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Not able to Locate Historical Graph radio button");
		}
	}

	public void selectHistoricalGraph() throws IOException {
		if (HistoricalGraphRadioButton.isDisplayed()) {
			Util.highLightElement(driver, HistoricalGraphRadioButton);
			HistoricalGraphRadioButton.click();

		} else {
			Report.FailTest(test, "Historical graph is not selected");
		}
	}

	public void validateCommercialDOWgraph(String date) throws IOException, InterruptedException, ParseException {
		Thread.sleep(5000);
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		Date dt1 = format1.parse(date);
		DateFormat format2 = new SimpleDateFormat("EEEE");
		String finalDay = format2.format(dt1);
		validateTextExist("commercial DOW Graph Header", commercialGraphHeader,
				GlobalTestData.expectedCommercialGraphHeader);
		validateTextExist("Commercial DOW Graph footer", commercialGraphFooter, finalDay);
		System.out.println("Day : " + commercialGraphFooter.getText());
		validateCommercialGraphActualEfficiency(date);

	}

	public void validateResidentialDOWgraph(String date) throws IOException, InterruptedException, ParseException {
		Thread.sleep(5000);
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		Date dt1 = format1.parse(date);
		DateFormat format2 = new SimpleDateFormat("EEEE");
		String finalDay = format2.format(dt1);
		validateTextExist("Residential DOW Graph Header", residentialGraphHeader,
				GlobalTestData.expectedResidentialGraphHeader);
		validateTextExist("Residential DOW Graph footer", residentialGraphFooter, finalDay);
		System.out.println("Day : " + residentialGraphFooter.getText());
		validateResidentialGraphActualEfficiency(date);

	}

	public void validateRollOffDOWgraph(String date) throws IOException, InterruptedException, ParseException {
		Thread.sleep(5000);
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		Date dt1 = format1.parse(date);
		DateFormat format2 = new SimpleDateFormat("EEEE");
		String finalDay = format2.format(dt1);
		validateTextExist("Roll Off DOW Graph Header", rollOffGraphHeader, GlobalTestData.expectedRollOffGraphHeader1);
		validateTextExist("Roll Off DOW Graph footer", rollOffGraphFooter, finalDay);
		System.out.println("Day : " + rollOffGraphFooter.getText());
		validateRollOffGraphActualEfficiency(date);

	}

	public void validateCommercialHistoricalGraph() throws InterruptedException {
		Thread.sleep(5000);
		validateTextExist("Commercial Historical Graph Header", commercialGraphHeader,
				GlobalTestData.expectedCommercialGraphHeader);
		validateTextExist("Commercial Historical Graph footer", commercialGraphFooter,
				GlobalTestData.expectedRMDashboardHistoricalGraphFooter);
	}

	public void validateResidentialHistoricalGraph() throws InterruptedException {
		Thread.sleep(5000);
		validateTextExist("Residential Historical Graph Header", residentialGraphHeader,
				GlobalTestData.expectedResidentialGraphHeader);
		validateTextExist("Residential Historical Graph footer", residentialGraphFooter,
				GlobalTestData.expectedRMDashboardHistoricalGraphFooter);
	}

	public void validateRollOffHistoricalGraph() throws InterruptedException {
		Thread.sleep(5000);
		validateTextExist("RollOff Historical Graph Header", rollOffGraphHeader,
				GlobalTestData.expectedRollOffGraphHeader1);
		validateTextExist("RollOff Historical Graph footer", rollOffGraphFooter,
				GlobalTestData.expectedRMDashboardHistoricalGraphFooter);
	}

	public void validateTextExist(String fieldName, WebElement element, String expectedText) {

		String actualText = null;
		try {
			if (element.isDisplayed()) {
				actualText = element.getText();
				actualText = actualText.trim();
				if (actualText.equals(expectedText)) {
					Report.PassTest(test, fieldName + " is as expected Actual is : " + actualText
							+ " and expected is : " + expectedText);
				} else {
					Util.CaptureScreenshot(fieldName + " not expected");
					Report.FailTest(test, fieldName + " is not as expected Actual is : " + actualText
							+ " and expected is : " + expectedText);
				}
			} else {
				Util.CaptureScreenshot(fieldName + " not exist");
				Report.FailTest(test, fieldName + " is not available on the webpage");
			}
		}

		catch (Exception e) {
			Report.FailTest(test, "Historical graph is not selected");
		}

	}

	public void verifyAllLinks() {
		List<WebElement> links = findAllLinks(driver);
		System.out.println("Total number of links on the webpage : " + links.size());
		for (int i = 0; i < links.size(); i++) {
			WebElement element = links.get(i);
			try

			{
				String url = element.getAttribute("href");
				System.out.println("URL: " + element.getAttribute("href") + " " + verifyLinkActive(url));
			} catch (Exception exp)

			{
				System.out.println(
						"At " + element.getAttribute("innerHTML") + " Exception occured -&gt; " + exp.getMessage());
			}
		}
	}

	public static List<WebElement> findAllLinks(WebDriver driver)

	{
		List<WebElement> elementList = new ArrayList<WebElement>();

		elementList = driver.findElements(By.tagName("a"));

		elementList.addAll(driver.findElements(By.tagName("img")));

		List<WebElement> finalList = new ArrayList<WebElement>();
		;

		for (WebElement element : elementList)

		{
			String url = element.getAttribute("href");
			if (url != null && !url.contains("javascript"))

			{
				finalList.add(element);
			}
		}

		return finalList;
	}

	public static String verifyLinkActive(String linkUrl) {
		try {
			URL url = new URL(linkUrl);

			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();

			httpURLConnect.setConnectTimeout(3000);

			httpURLConnect.connect();

			if (httpURLConnect.getResponseCode() == 200) {
				// System.out.println(linkUrl+" -
				// "+httpURLConnect.getResponseMessage());
				return httpURLConnect.getResponseCode() + " " + httpURLConnect.getResponseMessage();
			}
			if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				// System.out.println(linkUrl+" -
				// "+httpURLConnect.getResponseMessage() + " - "+
				// HttpURLConnection.HTTP_NOT_FOUND);
				return httpURLConnect.getResponseCode() + " " + httpURLConnect.getResponseMessage();
			} else {
				return httpURLConnect.getResponseCode() + " " + httpURLConnect.getResponseMessage();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "Link is not working";
		}
	}

	public void validateCommercialGraphActualEfficiency(String date) throws ParseException {
		String actualDate = null;
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		Date dt1 = format1.parse(date);
		Format formatter = new SimpleDateFormat("M/d");
		String expectedDate = formatter.format(dt1);
		double actualEfficiency = 0.0;
		double expectedEfficiency = 0.0;
		System.out.println("Date from data sheet : " + expectedDate);
		try {
			if (!(commercialGraphNoRecordToDisplay.size() > 0)) {

				Util.Hover(driver, commercialGraphTooltipHover);
				System.out.println(commercialGraphToolTipHeader.getAttribute("style"));

				actualDate = commercialGraphToolTipHeader.getText();
				if (actualDate.equals(expectedDate)) {
					actualEfficiency = Double.parseDouble(commercialGraphToolTipActualEfficiency.getText());
					if (commercialEfficiencySummarySection.size() > 0) {
						expectedEfficiency = Double.parseDouble(commercialEfficiency.getText());
						if (actualEfficiency == expectedEfficiency) {
							Report.PassTest(test, "Efficiency is as expected Actual is : " + actualEfficiency
									+ " and expected is : " + expectedEfficiency);
						} else {
							Report.FailTest(test, "Efficiency is not as expected Actual is : " + actualEfficiency
									+ " and expected is : " + expectedEfficiency);
						}
					} else {
						Report.FailTest(test, "No data available in efficiency summary for : " + actualDate);
					}
				} else {
					Report.FailTest(test, "No route served on : " + actualDate);
				}
			} else {
				System.out.println(commercialGraphNoRecordToDisplay.get(0).getText());
				Report.PassTest(test, "There is no efficiency graph on the selected date");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTest(test, "No route served on : " + actualDate);
		}
	}

	public void validateResidentialGraphActualEfficiency(String date) throws ParseException {
		String actualDate = null;
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		Date dt1 = format1.parse(date);
		Format formatter = new SimpleDateFormat("M/d");
		String expectedDate = formatter.format(dt1);
		double actualEfficiency = 0.0;
		double expectedEfficiency = 0.0;
		System.out.println("Date from data sheet : " + expectedDate);
		try {
			if (!(residentialGraphNoRecordToDisplay.size() > 0)) {

				Util.Hover(driver, residentialGraphTooltipHover);
				System.out.println(residentialGraphToolTipHeader.getAttribute("style"));

				actualDate = residentialGraphToolTipHeader.getText();
				if (actualDate.equals(expectedDate)) {
					actualEfficiency = Double.parseDouble(residentialGraphToolTipActualEfficiency.getText());
					if (residentialEfficiencySummarySection.size() > 0) {
						expectedEfficiency = Double.parseDouble(residentialEfficiency.getText());
						if (actualEfficiency == expectedEfficiency) {
							Report.PassTest(test, "Efficiency is as expected Actual is : " + actualEfficiency
									+ " and expected is : " + expectedEfficiency);
						} else {
							Report.FailTest(test, "Efficiency is not as expected Actual is : " + actualEfficiency
									+ " and expected is : " + expectedEfficiency);
						}
					} else {
						Report.FailTest(test, "No data available in efficiency summary for : " + actualDate);
					}
				} else {
					Report.FailTest(test, "No route served on : " + date);
				}
			} else {
				System.out.println(residentialGraphNoRecordToDisplay.get(0).getText());
				Report.PassTest(test, "There is no efficiency graph on the selected date");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTest(test, "No route served on : " + actualDate);
		}
	}

	public void validateRollOffGraphActualEfficiency(String date) throws ParseException {
		String actualDate = null;
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		Date dt1 = format1.parse(date);
		SimpleDateFormat formatter = new SimpleDateFormat("M/d");
		String expectedDate = formatter.format(dt1);
		double actualEfficiency = 0.0;
		double expectedEfficiency = 0.0;
		System.out.println("Date from data sheet : " + expectedDate);
		try {
			if (!(rollOffGraphNoRecordToDisplay.size() > 0)) {

				Util.Hover(driver, rollOffGraphTooltipHover);
				System.out.println(rollOffGraphToolTipHeader.getAttribute("style"));

				actualDate = rollOffGraphToolTipHeader.getText();
				if (actualDate.equals(expectedDate)) {
					actualEfficiency = Double.parseDouble(rollOffGraphToolTipActualEfficiency.getText());
					if (rollOffEfficiencySummarySection.size() > 0) {
						expectedEfficiency = Double.parseDouble(rollOffEfficiency.getText());
						if (actualEfficiency == expectedEfficiency) {
							Report.PassTest(test, "Efficiency is as expected Actual is : " + actualEfficiency
									+ " and expected is : " + expectedEfficiency);
						} else {
							Report.FailTest(test, "Efficiency is not as expected Actual is : " + actualEfficiency
									+ " and expected is : " + expectedEfficiency);
						}
					} else {
						Report.FailTest(test, "No data available in efficiency summary for : " + actualDate);
					}
				} else {
					Report.FailTest(test, "No route served on : " + date);
				}
			} else {
				System.out.println(rollOffGraphNoRecordToDisplay.get(0).getText());
				Report.PassTest(test, "There is no efficiency graph on the selected date");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTest(test, "No route served on : " + actualDate);
		}
	}

	public void validateRollOffGraphVarianceMinPerHaul(String date) throws ParseException {
		String actualDate = null;
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		Date dt1 = format1.parse(date);
		SimpleDateFormat formatter = new SimpleDateFormat("M/d");
		String expectedDate = formatter.format(dt1);
		String actualVarianceMinPerHaul = "00.00";
		String expectedVarianceMinPerHaul = "00.00";
		System.out.println("Date from data sheet : " + expectedDate);
		try {
			if (!(rollOffGraphNoRecordToDisplay.size() > 0)) {

				Util.Hover(driver, rollOffGraphTooltipHover);
				System.out.println(rollOffGraphToolTipHeader.getAttribute("style"));

				actualDate = rollOffGraphToolTipHeader.getText();
				if (actualDate.equals(expectedDate)) {
					actualVarianceMinPerHaul = rollOffGraphToolTipActualEfficiency.getText();
					expectedVarianceMinPerHaul = getVarianceMinPerHaul();
					if (actualVarianceMinPerHaul.equals(expectedVarianceMinPerHaul)) {
						Report.PassTest(test, "Variance Min Per Haul is as expected Actual is : "
								+ actualVarianceMinPerHaul + " and expected is : " + expectedVarianceMinPerHaul);
					} else {
						Report.FailTest(test, "Variance Min Per Haul is not as expected Actual is : "
								+ actualVarianceMinPerHaul + " and expected is : " + expectedVarianceMinPerHaul);
					}

				} else {
					Report.FailTest(test, "No route served on : " + date);
				}
			} else {
				System.out.println(rollOffGraphNoRecordToDisplay.get(0).getText());
				Report.PassTest(test, "There is no efficiency graph on the selected date");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTest(test, "No route served on : " + actualDate);
		}
	}

	public String getVarianceMinPerHaul() {
		String expectedVarianceMinPerHaul = "00.00";
		try {
			RollOffFlashReport rollOffObj = new RollOffFlashReport(driver, test);
			rollOffObj.clickRollOffFlashReport();
			Map<String, List<String>> actualEfficiencyTable = rollOffObj.getActualEfficiencyTableData();
			expectedVarianceMinPerHaul = rollOffObj.getRollOffVarianceMinPerHaul(actualEfficiencyTable);
		    backToRMDashBoard.click();
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
		}
		return expectedVarianceMinPerHaul;
	}

	public void setAttribute(WebElement element, String attName, String attValue) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attName, attValue);
	}

	public void validateTop5Driver() {
		Map<String, List<String>> top5DriversComTableData = new HashMap<>();
		Map<String, List<String>> top5DriversResiTableData = new HashMap<>();
		Map<String, List<String>> top5DriversRollOffTableData = new HashMap<>();
		try {
			top5DriversComTableData = readTable(top5ComDriversTableColumns, top5ComDriversTableRows, "dtTopDriversCom");
			top5DriversResiTableData = readTable(top5ResiDriversTableColumns, top5ResiDriversTableRows,
					"dtTopDriversRes");
			top5DriversRollOffTableData = readTable(top5RollOffDriversTableColumns, top5RollOffDriversTableRows,
					"dtTopDriversRol");
			if (top5DriversComTableData == null) {
				Report.InfoTest(test, "There is no data for commercial top 5 drivers");
			} else {
				for (Entry<String, List<String>> entry : top5DriversComTableData.entrySet()) {
					System.out.println(entry.getKey() + ":" + entry.getValue().toString());
					String actualHoursVariance = getDriversActualHoursVariance(entry.getValue().get(0),
							"dtTopDriversCom");
					if (actualHoursVariance.equals(entry.getValue().get(1))) {
						Report.InfoTest(test, "Top 5 Drivers table data read successfully");
						Report.PassTest(test,
								"Driver Hours Variance is as expected for driver : " + entry.getValue().get(0)
										+ " Actual Hours Variance : " + actualHoursVariance
										+ " Expected Hours variance : " + entry.getValue().get(1));
					} else {
						Report.InfoTest(test, "Top 5 Drivers table data read successfully");
						Report.FailTest(test,
								"Driver Hours Variance is not as expected for driver : " + entry.getValue().get(0)
										+ " Actual Hours Variance : " + actualHoursVariance
										+ " Expected Hours variance : " + entry.getValue().get(1));
					}
				}
			}
			if (top5DriversRollOffTableData == null) {
				Report.InfoTest(test, "There is no data for Roll Off top 5 drivers");
			} else {
				for (Entry<String, List<String>> entry : top5DriversRollOffTableData.entrySet()) {
					System.out.println(entry.getKey() + ":" + entry.getValue().toString());
					String actualHoursVariance = getDriversActualHoursVariance(entry.getValue().get(0),
							"dtTopDriversRol");
					if (actualHoursVariance.equals(entry.getValue().get(1))) {
						Report.InfoTest(test, "Top 5 Drivers table data read successfully");
						Report.PassTest(test,
								"Actual Hours Variance is as expected for driver : " + entry.getValue().get(0)
										+ " Actual Hours Variance : " + actualHoursVariance
										+ " Expected Hours variance : " + entry.getValue().get(1));
					} else {
						Report.InfoTest(test, "Top 5 Drivers table data read successfully");
						Report.FailTest(test,
								"Actual Hours Variance is not as expected for driver : " + entry.getValue().get(0)
										+ " Actual Hours Variance : " + actualHoursVariance
										+ " Expected Hours variance : " + entry.getValue().get(1));
					}
				}
			}
			if (top5DriversResiTableData == null) {
				Report.InfoTest(test, "There is no data for Residential top 5 drivers");
			} else {
				for (Entry<String, List<String>> entry : top5DriversResiTableData.entrySet()) {
					System.out.println(entry.getKey() + ":" + entry.getValue().toString());
					String actualHoursVariance = getDriversActualHoursVariance(entry.getValue().get(0),
							"dtTopDriversRes");
					if (actualHoursVariance.equals(entry.getValue().get(1))) {
						Report.InfoTest(test, "Top 5 Drivers table data read successfully");
						Report.PassTest(test,
								"Actual Hours Variance is as expected for driver : " + entry.getValue().get(0)
										+ " Actual Hours Variance : " + actualHoursVariance
										+ " Expected Hours variance : " + entry.getValue().get(1));
					} else {
						Report.InfoTest(test, "Top 5 Drivers table data read successfully");
						Report.FailTest(test,
								"Actual Hours Variance is not as expected for driver : " + entry.getValue().get(0)
										+ " Actual Hours Variance : " + actualHoursVariance
										+ " Expected Hours variance : " + entry.getValue().get(1));
					}
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Top 5 Drivers table data");
		}

	}

	public void validateBottom5Driver() {
		Map<String, List<String>> bottom5DriversComTableData = new HashMap<>();
		Map<String, List<String>> bottom5DriversResiTableData = new HashMap<>();
		Map<String, List<String>> bottom5DriversRollOffTableData = new HashMap<>();
		try {
			bottom5DriversComTableData = readTable(bottom5ComDriversTableColumns, bottom5ComDriversTableRows,
					"dtBottomDriversCom");
			bottom5DriversResiTableData = readTable(bottom5ResiDriversTableColumns, bottom5ResiDriversTableRows,
					"dtBottomDriversRes");
			bottom5DriversRollOffTableData = readTable(bottom5RollOffDriversTableColumns,
					bottom5RollOffDriversTableRows, "dtBottomDriversRol");
			if (bottom5DriversComTableData == null) {
				Report.InfoTest(test, "There is no data for commercial bottom 5 drivers");
			} else {
				for (Entry<String, List<String>> entry : bottom5DriversComTableData.entrySet()) {
					System.out.println(entry.getKey() + ":" + entry.getValue().toString());
					String actualHoursVariance = getDriversActualHoursVariance(entry.getValue().get(0),
							"dtBottomDriversCom");
					if (actualHoursVariance.equals(entry.getValue().get(1))) {
						Report.InfoTest(test, "Bottom 5 Drivers table data read successfully");
						Report.PassTest(test,
								"Actual Hours Variance is as expected for driver : " + entry.getValue().get(0)
										+ " Actual Hours Variance : " + actualHoursVariance
										+ " Expected Hours variance : " + entry.getValue().get(1));
					} else {
						Report.InfoTest(test, "Bottom 5 Drivers table data read successfully");
						Report.FailTest(test,
								"Actual Hours Variance is not as expected for driver : " + entry.getValue().get(0)
										+ " Actual Hours Variance : " + actualHoursVariance
										+ " Expected Hours variance : " + entry.getValue().get(1));
					}
				}
			}
			if (bottom5DriversRollOffTableData == null) {
				Report.InfoTest(test, "There is no data for Roll Off bottom 5 drivers");
			} else {
				for (Entry<String, List<String>> entry : bottom5DriversRollOffTableData.entrySet()) {
					System.out.println(entry.getKey() + ":" + entry.getValue().toString());
					String actualHoursVariance = getDriversActualHoursVariance(entry.getValue().get(0),
							"dtBottomDriversRol");
					if (actualHoursVariance.equals(entry.getValue().get(1))) {
						Report.InfoTest(test, "Bottom 5 Drivers table data read successfully");
						Report.PassTest(test,
								"Actual Hours Variance is as expected for driver : " + entry.getValue().get(0)
										+ " Actual Hours Variance : " + actualHoursVariance
										+ " Expected Hours variance : " + entry.getValue().get(1));
					} else {
						Report.InfoTest(test, "Bottom 5 Drivers table data read successfully");
						Report.FailTest(test,
								"Actual Hours Variance is not as expected for driver : " + entry.getValue().get(0)
										+ " Actual Hours Variance : " + actualHoursVariance
										+ " Expected Hours variance : " + entry.getValue().get(1));
					}
				}
			}
			if (bottom5DriversResiTableData == null) {
				Report.InfoTest(test, "There is no data for Residential bottom 5 drivers");
			} else {
				for (Entry<String, List<String>> entry : bottom5DriversResiTableData.entrySet()) {
					System.out.println(entry.getKey() + ":" + entry.getValue().toString());
					String actualHoursVariance = getDriversActualHoursVariance(entry.getValue().get(0),
							"dtBottomDriversRes");
					if (actualHoursVariance.equals(entry.getValue().get(1))) {
						Report.InfoTest(test, "Bottom 5 Drivers table data read successfully");
						Report.PassTest(test,
								"Actual Hours Variance is as expected for driver : " + entry.getValue().get(0)
										+ " Actual Hours Variance : " + actualHoursVariance
										+ " Expected Hours variance : " + entry.getValue().get(1));
					} else {
						Report.InfoTest(test, "Bottom 5 Drivers table data read successfully");
						Report.FailTest(test,
								"Actual Hours Variance is not as expected for driver : " + entry.getValue().get(0)
										+ " Actual Hours Variance : " + actualHoursVariance
										+ " Expected Hours variance : " + entry.getValue().get(1));
					}
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Bottom 5 Drivers table data");
		}

	}

	public Map<String, List<String>> readTable(List<WebElement> columns, List<WebElement> rows, String tableName) {
		Map<String, List<String>> objTable = new HashMap<>();
		System.out.println("Number of cloumns : " + columns.size());
		System.out.println("Number of rows : " + rows.size());
		if (rows.size() > 0) {
			for (int iCount = 1; iCount <= rows.size(); iCount++) {
				List<String> rowValues = new ArrayList<>();
				for (int col = 1; col <= columns.size(); col++) {
					try {
						rowValues.add(driver.findElement(By.xpath(
								"//table[@id='" + tableName + "']/tbody/tr[" + iCount + "]/td[" + col + "]//span"))
								.getText());
					} catch (Exception e) {
						rowValues.add(driver
								.findElement(By.xpath(
										"//table[@id='" + tableName + "']/tbody/tr[" + iCount + "]/td[" + col + "]"))
								.getText());
					}
				}
				objTable.put("Driver " + iCount, rowValues);
			}
			return objTable;
		} else {
			return null;
		}
	}
	public Map<String, Map<String, String>> readTableLOBWise(List<WebElement> columns, List<WebElement> rows,
			String tableName) {
		Map<String, Map<String, String>> objTable = new HashMap<>();
		System.out.println("Number of cloumns : " + columns.size());
		System.out.println("Number of rows : " + rows.size());
		String colName = null;
		String LOB = null;
		String value = null;
		// Map < String, String> tempMap = new HashMap<>();

		Map<String, Map<String, String>> tempFinalMap = new HashMap<>();
		try {
			if (rows.size() > 0) {

				for (int col = 1; col <= columns.size(); col++) {
					colName = driver.findElement(By.xpath("//table[@id='" + tableName + "']/thead/tr/th[" + col + "]"))
							.getText();
					Map<String, String> tempMap = new HashMap<>();
					for (int iCount = 1; iCount <= rows.size(); iCount++) {
						LOB = driver.findElement(By
								.xpath("//table[@id='" + tableName + "']/tbody/tr[" + iCount + "]/td[" + 1 + "]//span"))
								.getText();
						value = driver.findElement(By.xpath(
								"//table[@id='" + tableName + "']/tbody/tr[" + iCount + "]/td[" + col + "]//span"))
								.getText();
						tempMap.put(LOB, value);

					}
					tempFinalMap.put(colName, tempMap);
				}
				objTable.putAll(tempFinalMap);
			}

		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());

		}
		return objTable;

	}	
	  
	  public String getDriversActualHoursVariance(String driverName,String TableName)
	  {
		  String actualHoursVariance=null;
		try
		{
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
			driver.findElement(
					By.xpath("//table[@id='" + TableName + "']/tbody/tr/td//span[text()='" + driverName + "']"))
					.click();
			Util.selectFrame("opusReportContainer,subReportContainer");
			CommercialFlashReport commObj = new CommercialFlashReport(driver, test);
			Map<String, List<String>> actualSummaryTableData = commObj.getActualSummaryTableData();
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Hours Variance")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualHoursVariance = entry.getValue().get(i);
					}
				}
			}
			System.out.println("Actual Hours variance : " + actualHoursVariance);
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer");
			backToRMDashBoard.click();
			return actualHoursVariance;
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read the hours variance for driver : " + driverName);
		}
		return null;
	}

	public void validateWalkAndTalkSectionExist() {

		try {
			if (walkAndTalkCoachingOpportunitiesSection.size() > 0) {
				Report.PassTestScreenshot(test, driver, "Walk and Talk Section is available on AM Huddle",
						"Passed Walk and Talk Section visibility");
			} else {
				Report.FailTestSnapshot(test, driver, "Walk and Talk Section is not available on AM Huddle",
						"Failed Walk and Talk section");
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTestSnapshot(test, driver, "Walk and Talk Section is not available on AM Huddle",
					"Failed Walk and Talk section");
		}
	}

	public void validateOpportunitiesLabels() {

		try {
			if (netIdleLabel.getText().equals("Net Idle")) {
				Report.PassTest(test, "Net Idle Option is available in Walk and Talk Section ");
			} else {
				Report.FailTestSnapshot(test, driver,
						"Net Idle options in Walk and Talk Section is not available on AM Huddle",
						"Failed Walk and Talk section");
			}
			if (preRouteLabel.getText().equals("Pre-Route")) {
				Report.PassTest(test, "Pre-Route Option is available in Walk and Talk Section ");
			} else {
				Report.FailTestSnapshot(test, driver,
						"Pre-Route options in Walk and Talk Section is not available on AM Huddle",
						"Failed Walk and Talk section");
			}
			if (postRouteLabel.getText().equals("Post-Route")) {
				Report.PassTest(test, "Post-Route Option is available in Walk and Talk Section ");
			} else {
				Report.FailTestSnapshot(test, driver,
						"Post-Route options in Walk and Talk Section is not available on AM Huddle",
						"Failed Walk and Talk section");
			}
			if (sequenceComplianceLabel.getText().equals("Sequence Compliance")) {
				Report.PassTest(test, "Sequence Compliance Option is available in Walk and Talk Section ");
			} else {
				Report.FailTestSnapshot(test, driver,
						"Sequence Compliance options in Walk and Talk Section is not available on AM Huddle",
						"Failed Walk and Talk section");
			}
			if (downtimeLabel.getText().equals("Downtime")) {
				Report.PassTest(test, "Downtime Option is available in Walk and Talk Section ");
			} else {
				Report.FailTestSnapshot(test, driver,
						"Downtime options in Walk and Talk Section is not available on AM Huddle",
						"Failed Walk and Talk section");
			}

		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTestSnapshot(test, driver,
					"Opportunities options in Walk and Talk Section is not available on AM Huddle",
					"Failed Walk and Talk section");
		}
	}

	public void validateNetIdleTopOpportunities(String LOB,String Route) {
		Map<String, List<String>> topOpportunitiesNetIdleComTableData = new HashMap<>();
		Map<String, List<String>> topOpportunitiesNetIdleResTableData = new HashMap<>();
		Map<String, List<String>> topOpportunitiesNetIdleRolTableData = new HashMap<>();

		try {
			if (LOB.equals("Commercial")) {
				netIdleRadioButton.click();
				topOpportunitiesNetIdleComTableData = readTable(netIdleComOpportunitiesColumns,
						netIdleComOpportunitiesRows, "dtWalkNTalkNetIdleCom");

				if (topOpportunitiesNetIdleComTableData == null) {
					Report.PassTestScreenshot(test, driver, "There is no data for Net idle in walk and Talk section",
							"Walk and Talk Net Idle");
				} else {
					for (Entry<String, List<String>> entry : topOpportunitiesNetIdleComTableData.entrySet()) {
						System.out.println(entry.getKey() + ":" + entry.getValue().toString());
						String actualNetIdleTime = getDriversNetIdleTime(entry.getValue().get(0),Route,
								"dtWalkNTalkNetIdleCom");
						if (actualNetIdleTime.equals(entry.getValue().get(1))) {
							Report.InfoTest(test, "Top Opportunities Net Idle table data read successfully");
							Report.PassTest(test,
									"Net Idle is as expected for driver : " + entry.getValue().get(0)
											+ " Actual Net Idle Time : " + actualNetIdleTime
											+ " Expected Net Idle Time : " + entry.getValue().get(1));
						} else {
							Report.InfoTest(test, "Top Opportunities Net Idle table data read successfully");
							Report.FailTest(test,
									"Net Idle is not as expected for driver : " + entry.getValue().get(0)
											+ " Actual Net Idle Time : " + actualNetIdleTime
											+ " Expected Net Idle Time : " + entry.getValue().get(1));
						}
					}
				}
			} else if (LOB.equals("Residential")) {
				netIdleRadioButton.click();
				topOpportunitiesNetIdleResTableData = readTable(netIdleResOpportunitiesColumns,
						netIdleResOpportunitiesRows, "dtWalkNTalkNetIdleRes");

				if (topOpportunitiesNetIdleResTableData == null) {
					Report.PassTestScreenshot(test, driver, "There is no data for Net idle in walk and Talk section",
							"Walk and Talk Net Idle");
				} else {
					for (Entry<String, List<String>> entry : topOpportunitiesNetIdleResTableData.entrySet()) {
						System.out.println(entry.getKey() + ":" + entry.getValue().toString());
						String actualNetIdleTime = getDriversNetIdleTime(entry.getValue().get(0),Route,
								"dtWalkNTalkNetIdleRes");
						if (actualNetIdleTime.equals(entry.getValue().get(1))) {
							Report.InfoTest(test, "Top Opportunities Net Idle table data read successfully");
							Report.PassTest(test,
									"Net Idle is as expected for driver : " + entry.getValue().get(0)
											+ " Actual Net Idle Time : " + actualNetIdleTime
											+ " Expected Net Idle Time : " + entry.getValue().get(1));
						} else {
							Report.InfoTest(test, "Top Opportunities Net Idle table data read successfully");
							Report.FailTest(test,
									"Net Idle is not as expected for driver : " + entry.getValue().get(0)
											+ " Actual Net Idle Time : " + actualNetIdleTime
											+ " Expected Net Idle Time : " + entry.getValue().get(1));
						}
					}
				}
			}

			else if (LOB.equals("Roll Off")) {
				netIdleRadioButton.click();
				topOpportunitiesNetIdleRolTableData = readTable(netIdleRolOpportunitiesColumns,
						netIdleRolOpportunitiesRows, "dtWalkNTalkNetIdleRol");

				if (topOpportunitiesNetIdleRolTableData == null) {
					Report.PassTestScreenshot(test, driver, "There is no data for Net idle in walk and Talk section",
							"Walk and Talk Net Idle");
				} else {
					for (Entry<String, List<String>> entry : topOpportunitiesNetIdleRolTableData.entrySet()) {
						System.out.println(entry.getKey() + ":" + entry.getValue().toString());
						String actualNetIdleTime = getDriversNetIdleTime(entry.getValue().get(0),Route,
								"dtWalkNTalkNetIdleRol");
						if (actualNetIdleTime.equals(entry.getValue().get(1))) {
							Report.InfoTest(test, "Top Opportunities Net Idle table data read successfully");
							Report.PassTest(test,
									"Net Idle is as expected for driver : " + entry.getValue().get(0)
											+ " Actual Net Idle Time : " + actualNetIdleTime
											+ " Expected Net Idle Time : " + entry.getValue().get(1));
						} else {
							Report.InfoTest(test, "Top Opportunities Net Idle table data read successfully");
							Report.FailTest(test,
									"Net Idle is not as expected for driver : " + entry.getValue().get(0)
											+ " Actual Net Idle Time : " + actualNetIdleTime
											+ " Expected Net Idle Time : " + entry.getValue().get(1));
						}
					}
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Top Opportunities Net Idle table data");
		}

	}

	public String getDriversNetIdleTime(String driverName,String Route, String TableName) {
		String actualNetIdleTime = null;
		try {
			FilterSection filterObj=new FilterSection(driver, test);
			filterObj.selectRoute(Route);
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
			netIdleRadioButton.click();
			driver.findElement(
					By.xpath("//table[@id='" + TableName + "']/tbody/tr/td//span[text()='" + driverName + "']"))
					.click();
			Util.selectFrame("opusReportContainer,subReportContainer");
			IdleDetailReport idleObj = new IdleDetailReport(driver, test);
			Map<String, List<String>> actualIdleSummaryTableData = idleObj.getActualIdleSummaryTableData();
			for (Entry<String, List<String>> entry : actualIdleSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Total Net Idle Time (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualNetIdleTime = entry.getValue().get(i);
					}
				}
			}
			System.out.println("Actual Net Idle Time : " + actualNetIdleTime);
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer");
			backToRMDashBoard.click();
			return actualNetIdleTime;
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read the net idle time for driver : " + driverName);
		}
		return null;
	}

	public void validatePreRouteTopOpportunities(String LOB,String Route) {
		Map<String, List<String>> topOpportunitiesPreRouteComTableData = new HashMap<>();
		Map<String, List<String>> topOpportunitiesPreRouteResTableData = new HashMap<>();
		Map<String, List<String>> topOpportunitiesPreRouteRolTableData = new HashMap<>();
		try {
			if (LOB.equals("Commercial")) {
				preRouteRadioButton.click();
				topOpportunitiesPreRouteComTableData = readTable(preRouteComOpportunitiesColumns,
						preRouteComOpportunitiesRows, "dtWalkNTalkPreCom");

				if (topOpportunitiesPreRouteComTableData == null) {
					Report.PassTestScreenshot(test, driver, "There is no data for Pre-Route in walk and Talk section",
							"Walk and Talk Pre-Route");
				} else {
					for (Entry<String, List<String>> entry : topOpportunitiesPreRouteComTableData.entrySet()) {
						System.out.println(entry.getKey() + ":" + entry.getValue().toString());
						String actualPreRouteTime = getDriversPreRouteTime(entry.getValue().get(0),Route,
								"dtWalkNTalkPreCom");
						if (actualPreRouteTime.equals(entry.getValue().get(1))) {
							Report.InfoTest(test, "Top Opportunities Pre Route table data read successfully");
							Report.PassTest(test,
									"Pre Route Time is as expected for driver : " + entry.getValue().get(0)
											+ " Actual Pre Route Time : " + actualPreRouteTime
											+ " Expected Net Idle Time : " + entry.getValue().get(1));
						} else {
							Report.InfoTest(test, "Top Opportunities Pre Route table data read successfully");
							Report.FailTest(test,
									"Pre Route is not as expected for driver : " + entry.getValue().get(0)
											+ " Actual Pre Route Time : " + actualPreRouteTime
											+ " Expected Net Idle Time : " + entry.getValue().get(1));
						}
					}
				}
			} else if (LOB.equals("Residential")) {
				preRouteRadioButton.click();
				topOpportunitiesPreRouteResTableData = readTable(preRouteResOpportunitiesColumns,
						preRouteResOpportunitiesRows, "dtWalkNTalkPreRes");

				if (topOpportunitiesPreRouteResTableData == null) {
					Report.PassTestScreenshot(test, driver, "There is no data for Pre-Route in walk and Talk section",
							"Walk and Talk Pre-Route");
				} else {
					for (Entry<String, List<String>> entry : topOpportunitiesPreRouteResTableData.entrySet()) {
						System.out.println(entry.getKey() + ":" + entry.getValue().toString());
						String actualPreRouteTime = getDriversPreRouteTime(entry.getValue().get(0),Route,
								"dtWalkNTalkPreRes");
						if (actualPreRouteTime.equals(entry.getValue().get(1))) {
							Report.InfoTest(test, "Top Opportunities Pre Route table data read successfully");
							Report.PassTest(test,
									"Pre Route Time is as expected for driver : " + entry.getValue().get(0)
											+ " Actual Pre Route Time : " + actualPreRouteTime
											+ " Expected Pre Route Time : " + entry.getValue().get(1));
						} else {
							Report.InfoTest(test, "Top Opportunities Pre Route table data read successfully");
							Report.FailTest(test,
									"Pre Route is not as expected for driver : " + entry.getValue().get(0)
											+ " Actual Pre Route Time : " + actualPreRouteTime
											+ " Expected Pre Route Time : " + entry.getValue().get(1));
						}
					}
				}
			}

			else if (LOB.equals("Roll Off")) {
				preRouteRadioButton.click();
				topOpportunitiesPreRouteRolTableData = readTable(preRouteRolOpportunitiesColumns,
						preRouteRolOpportunitiesRows, "dtWalkNTalkPreRol");

				if (topOpportunitiesPreRouteRolTableData == null) {
					Report.PassTestScreenshot(test, driver, "There is no data for Pre-Route in walk and Talk section",
							"Walk and Talk Pre-Route");
				} else {
					for (Entry<String, List<String>> entry : topOpportunitiesPreRouteRolTableData.entrySet()) {
						System.out.println(entry.getKey() + ":" + entry.getValue().toString());
						String actualPreRouteTime = getDriversPreRouteTime(entry.getValue().get(0),Route,
								"dtWalkNTalkPreRol");
						if (actualPreRouteTime.equals(entry.getValue().get(1))) {
							Report.InfoTest(test, "Top Opportunities Pre Route table data read successfully");
							Report.PassTest(test,
									"Pre Route Time is as expected for driver : " + entry.getValue().get(0)
											+ " Actual Pre Route Time : " + actualPreRouteTime
											+ " Expected Net Idle Time : " + entry.getValue().get(1));
						} else {
							Report.InfoTest(test, "Top Opportunities Pre Route table data read successfully");
							Report.FailTest(test,
									"Pre Route is not as expected for driver : " + entry.getValue().get(0)
											+ " Actual Pre Route Time : " + actualPreRouteTime
											+ " Expected Net Idle Time : " + entry.getValue().get(1));
						}
					}
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Top Opportunities Pre Route table data");
		}

	}

	public String getDriversPreRouteTime(String driverName,String route, String TableName) {
		String actualPreRouteTime = null;
		try {
			FilterSection filterObj=new FilterSection(driver, test);
			filterObj.selectRoute(route);
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
			preRouteRadioButton.click();
			driver.findElement(
					By.xpath("//table[@id='" + TableName + "']/tbody/tr/td//span[text()='" + driverName + "']"))
					.click();
			Util.selectFrame("opusReportContainer,subReportContainer");
			PreRouteDetailReport preRouteObj = new PreRouteDetailReport(driver, test);
			Map<String, List<String>> actualPreRouteSummaryTableData = preRouteObj.getActualPreRouteSummaryTableData();
			for (Entry<String, List<String>> entry : actualPreRouteSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Avg Actual (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualPreRouteTime = entry.getValue().get(i);
					}
				}
			}
			System.out.println("Actual Pre Route Time : " + actualPreRouteTime);
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer");
			backToRMDashBoard.click();
			return actualPreRouteTime;
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read the pre route time for driver : " + driverName);
		}
		return null;
	}

	public void validatePostRouteTopOpportunities(String LOB,String Route) {
		Map<String, List<String>> topOpportunitiesPostRouteComTableData = new HashMap<>();
		Map<String, List<String>> topOpportunitiesPostRouteResTableData = new HashMap<>();
		Map<String, List<String>> topOpportunitiesPostRouteRolTableData = new HashMap<>();
		try {
			if (LOB.equals("Commercial")) {
				postRouteRadioButton.click();
				topOpportunitiesPostRouteComTableData = readTable(postRouteComOpportunitiesColumns,
						postRouteComOpportunitiesRows, "dtWalkNTalkPostCom");

				if (topOpportunitiesPostRouteComTableData == null) {
					Report.PassTestScreenshot(test, driver, "There is no data for Post-Route in walk and Talk section",
							"Walk and Talk Post-Route");
				} else {
					for (Entry<String, List<String>> entry : topOpportunitiesPostRouteComTableData.entrySet()) {
						System.out.println(entry.getKey() + ":" + entry.getValue().toString());
						String actualPostRouteTime = getDriversPostRouteTime(entry.getValue().get(0),Route,
								"dtWalkNTalkPostCom");
						if (actualPostRouteTime.equals(entry.getValue().get(1))) {
							Report.InfoTest(test, "Top Opportunities Post Route table data read successfully");
							Report.PassTest(test,
									"Post Route Time is as expected for driver : " + entry.getValue().get(0)
											+ " Actual Post Route Time : " + actualPostRouteTime
											+ " Expected Post Route Time : " + entry.getValue().get(1));
						} else {
							Report.InfoTest(test, "Top Opportunities Post Route table data read successfully");
							Report.FailTest(test,
									"Post Route is not as expected for driver : " + entry.getValue().get(0)
											+ " Actual Post Route Time : " + actualPostRouteTime
											+ " Expected Post Route Time : " + entry.getValue().get(1));
						}
					}
				}
			} else if (LOB.equals("Residential")) {
				postRouteRadioButton.click();
				topOpportunitiesPostRouteResTableData = readTable(postRouteResOpportunitiesColumns,
						postRouteResOpportunitiesRows, "dtWalkNTalkPostRes");

				if (topOpportunitiesPostRouteResTableData == null) {
					Report.PassTestScreenshot(test, driver, "There is no data for Post-Route in walk and Talk section",
							"Walk and Talk Post-Route");
				} else {
					for (Entry<String, List<String>> entry : topOpportunitiesPostRouteResTableData.entrySet()) {
						System.out.println(entry.getKey() + ":" + entry.getValue().toString());
						String actualPostRouteTime = getDriversPostRouteTime(entry.getValue().get(0),Route,
								"dtWalkNTalkPostRes");
						if (actualPostRouteTime.equals(entry.getValue().get(1))) {
							Report.InfoTest(test, "Top Opportunities Post Route table data read successfully");
							Report.PassTest(test,
									"Post Route Time is as expected for driver : " + entry.getValue().get(0)
											+ " Actual Post Route Time : " + actualPostRouteTime
											+ " Expected Post Route Time : " + entry.getValue().get(1));
						} else {
							Report.InfoTest(test, "Top Opportunities Post Route table data read successfully");
							Report.FailTest(test,
									"Post Route is not as expected for driver : " + entry.getValue().get(0)
											+ " Actual Post Route Time : " + actualPostRouteTime
											+ " Expected Post Route Time : " + entry.getValue().get(1));
						}
					}
				}
			}

			else if (LOB.equals("Roll Off")) {
				postRouteRadioButton.click();
				topOpportunitiesPostRouteRolTableData = readTable(postRouteRolOpportunitiesColumns,
						postRouteRolOpportunitiesRows, "dtWalkNTalkPostRol");

				if (topOpportunitiesPostRouteRolTableData == null) {
					Report.PassTestScreenshot(test, driver, "There is no data for Post-Route in walk and Talk section",
							"Walk and Talk Post-Route");
				} else {
					for (Entry<String, List<String>> entry : topOpportunitiesPostRouteRolTableData.entrySet()) {
						System.out.println(entry.getKey() + ":" + entry.getValue().toString());
						String actualPostRouteTime = getDriversPostRouteTime(entry.getValue().get(0),Route,
								"dtWalkNTalkPostRol");
						if (actualPostRouteTime.equals(entry.getValue().get(1))) {
							Report.InfoTest(test, "Top Opportunities Post Route table data read successfully");
							Report.PassTest(test,
									"Post Route Time is as expected for driver : " + entry.getValue().get(0)
											+ " Actual Post Route Time : " + actualPostRouteTime
											+ " Expected Post Route Time : " + entry.getValue().get(1));
						} else {
							Report.InfoTest(test, "Top Opportunities Post Route table data read successfully");
							Report.FailTest(test,
									"Post Route is not as expected for driver : " + entry.getValue().get(0)
											+ " Actual Post Route Time : " + actualPostRouteTime
											+ " Expected Post Route Time : " + entry.getValue().get(1));
						}
					}
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Top Opportunities Post Route table data");
		}

	}

	public String getDriversPostRouteTime(String driverName,String Route, String TableName) {
		String actualPostRouteTime = null;
		try {
			FilterSection filterObj=new FilterSection(driver, test);
			filterObj.selectRoute(Route);
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
			postRouteRadioButton.click();
			driver.findElement(
					By.xpath("//table[@id='" + TableName + "']/tbody/tr/td//span[text()='" + driverName + "']"))
					.click();
			Util.selectFrame("opusReportContainer,subReportContainer");
			PostRouteDetailReport postRouteObj = new PostRouteDetailReport(driver, test);
			Map<String, List<String>> actualPostRouteSummaryTableData = postRouteObj
					.getActualPostRouteSummaryTableData();
			for (Entry<String, List<String>> entry : actualPostRouteSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Avg Actual (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualPostRouteTime = entry.getValue().get(i);
					}
				}
			}
			System.out.println("Actual Post Route Time : " + actualPostRouteTime);
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer");
			backToRMDashBoard.click();
			return actualPostRouteTime;
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read the post route time for driver : " + driverName);
		}
		return null;
	}

	public void validateSequenceComplianceTopOpportunities(String LOB,String Route) {
		Map<String, List<String>> topOpportunitiesSequenceComplianceComTableData = new HashMap<>();
		Map<String, List<String>> topOpportunitiesSequenceComplianceResTableData = new HashMap<>();
		Map<String, List<String>> topOpportunitiesSequenceComplianceRolTableData = new HashMap<>();
		try {
			if (LOB.equals("Commercial")) {
				sequenceComplianceRadioButton.click();
				topOpportunitiesSequenceComplianceComTableData = readTable(sequenceComplianceComOpportunitiesColumns,
						sequenceComplianceComOpportunitiesRows, "dtWalkNTalkSeqCom");

				if (topOpportunitiesSequenceComplianceComTableData == null) {
					Report.PassTestScreenshot(test, driver,
							"There is no data for Sequence Compliance in walk and Talk section",
							"Walk and Talk Sequence Compliance");
				} else {
					for (Entry<String, List<String>> entry : topOpportunitiesSequenceComplianceComTableData
							.entrySet()) {
						System.out.println(entry.getKey() + ":" + entry.getValue().toString());
						String actualSequenceCompliancePercentage = getDriversSequenceCompliancePercent(
								entry.getValue().get(0),Route, "dtWalkNTalkSeqCom");
						if (actualSequenceCompliancePercentage.equals(entry.getValue().get(1))) {
							Report.InfoTest(test, "Top Opportunities Sequence Compliance table data read successfully");
							Report.PassTest(test,
									"Sequence Compliance % is as expected for driver : " + entry.getValue().get(0)
											+ " Actual Sequence Compliance % : " + actualSequenceCompliancePercentage
											+ " Expected Sequence Compliance % : " + entry.getValue().get(1));
						} else {
							Report.InfoTest(test, "Top Opportunities Sequence Compliance table data read successfully");
							Report.FailTest(test,
									"Sequence Compliance is not as expected for driver : " + entry.getValue().get(0)
											+ " Actual Sequence Compliance % : " + actualSequenceCompliancePercentage
											+ " Expected Sequence Compliance % : " + entry.getValue().get(1));
						}
					}
				}
			} else if (LOB.equals("Residential")) {
				sequenceComplianceRadioButton.click();
				topOpportunitiesSequenceComplianceResTableData = readTable(sequenceComplianceResOpportunitiesColumns,
						sequenceComplianceResOpportunitiesRows, "dtWalkNTalkSeqRes");

				if (topOpportunitiesSequenceComplianceResTableData == null) {
					Report.PassTestScreenshot(test, driver,
							"There is no data for Sequence Compliance in walk and Talk section",
							"Walk and Talk Sequence Compliance");
				} else {
					for (Entry<String, List<String>> entry : topOpportunitiesSequenceComplianceResTableData
							.entrySet()) {
						System.out.println(entry.getKey() + ":" + entry.getValue().toString());
						String actualSequenceCompliancePercentage = getDriversSequenceCompliancePercent(
								entry.getValue().get(0),Route, "dtWalkNTalkSeqRes");
						if (actualSequenceCompliancePercentage.equals(entry.getValue().get(1))) {
							Report.InfoTest(test, "Top Opportunities Sequence Compliance table data read successfully");
							Report.PassTest(test,
									"Sequence Compliance % is as expected for driver : " + entry.getValue().get(0)
											+ " Actual Sequence Compliance % : " + actualSequenceCompliancePercentage
											+ " Expected Sequence Compliance % : " + entry.getValue().get(1));
						} else {
							Report.InfoTest(test, "Top Opportunities Sequence Compliance table data read successfully");
							Report.FailTest(test,
									"Sequence Compliance is not as expected for driver : " + entry.getValue().get(0)
											+ " Actual Sequence Compliance % : " + actualSequenceCompliancePercentage
											+ " Expected Sequence Compliance % : " + entry.getValue().get(1));
						}
					}
				}
			}

			else if (LOB.equals("Roll Off")) {
				sequenceComplianceRadioButton.click();
				topOpportunitiesSequenceComplianceRolTableData = readTable(sequenceComplianceRolOpportunitiesColumns,
						sequenceComplianceRolOpportunitiesRows, "dtWalkNTalkSeqRol");

				if (topOpportunitiesSequenceComplianceRolTableData == null) {
					Report.PassTestScreenshot(test, driver,
							"There is no data for Sequence Compliance in walk and Talk section",
							"Walk and Talk Sequence Compliance");
				} else {
					for (Entry<String, List<String>> entry : topOpportunitiesSequenceComplianceRolTableData
							.entrySet()) {
						System.out.println(entry.getKey() + ":" + entry.getValue().toString());
						String actualSequenceCompliancePercentage = getDriversSequenceCompliancePercent(
								entry.getValue().get(0),Route, "dtWalkNTalkSeqRol");
						if (actualSequenceCompliancePercentage.equals(entry.getValue().get(1))) {
							Report.InfoTest(test, "Top Opportunities Sequence Compliance table data read successfully");
							Report.PassTest(test,
									"Sequence Compliance % is as expected for driver : " + entry.getValue().get(0)
											+ " Actual Sequence Compliance % : " + actualSequenceCompliancePercentage
											+ " Expected Sequence Compliance % : " + entry.getValue().get(1));
						} else {
							Report.InfoTest(test, "Top Opportunities Sequence Compliance table data read successfully");
							Report.FailTest(test,
									"Sequence Compliance is not as expected for driver : " + entry.getValue().get(0)
											+ " Actual Sequence Compliance % : " + actualSequenceCompliancePercentage
											+ " Expected Sequence Compliance % : " + entry.getValue().get(1));
						}
					}
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Top Opportunities Sequence Compliance table data");
		}

	}

	public String getDriversSequenceCompliancePercent(String driverName,String Route, String TableName) {
		String actualDriverSequenceCompliancePercent = null;
		try {
			FilterSection filterObj=new FilterSection(driver, test);
			filterObj.selectRoute(Route);
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
			sequenceComplianceRadioButton.click();
			driver.findElement(
					By.xpath("//table[@id='" + TableName + "']/tbody/tr/td//span[text()='" + driverName + "']"))
					.click();
			Util.selectFrame("opusReportContainer,subReportContainer");
			SequenceComplianceDetailReport seqComplianceObj = new SequenceComplianceDetailReport(driver, test);
			Map<String, List<String>> actualSequenceComplianceSummaryTableData = seqComplianceObj
					.getActualSequenceComplianceSummaryTableData();
			for (Entry<String, List<String>> entry : actualSequenceComplianceSummaryTableData.entrySet()) {
				if (entry.getKey().contains("In Seq %")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualDriverSequenceCompliancePercent = entry.getValue().get(i);
					}
				}
			}
			System.out
					.println("Actual Driver Sequence Compliance Percentage : " + actualDriverSequenceCompliancePercent);
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer");
			backToRMDashBoard.click();
			return actualDriverSequenceCompliancePercent;
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read the Sequence Compliance Percentage for driver : " + driverName);
		}
		return null;
	}

	public void validateDowntimeTopOpportunities(String LOB,String Route) {
		Map<String, List<String>> topOpportunitiesDowntimeComTableData = new HashMap<>();
		Map<String, List<String>> topOpportunitiesDowntimeResTableData = new HashMap<>();
		Map<String, List<String>> topOpportunitiesDowntimeRolTableData = new HashMap<>();
		try {
			if (LOB.equals("Commercial")) {
				downtimeRadioButton.click();
				topOpportunitiesDowntimeComTableData = readTable(downtimeComOpportunitiesColumns,
						downtimeComOpportunitiesRows, "dtWalkNTalkDowntimeCom");

				if (topOpportunitiesDowntimeComTableData == null) {
					Report.PassTestScreenshot(test, driver, "There is no data for Downtime in walk and Talk section",
							"Walk and Talk Downtime");
				} else {
					for (Entry<String, List<String>> entry : topOpportunitiesDowntimeComTableData.entrySet()) {
						System.out.println(entry.getKey() + ":" + entry.getValue().toString());
						String actualDowntime = getDriversDowntime(entry.getValue().get(0),Route, "dtWalkNTalkDowntimeCom");
						if (actualDowntime.equals(entry.getValue().get(1))) {
							Report.InfoTest(test, "Top Opportunities Downtime table data read successfully");
							Report.PassTest(test,
									"Downtime is as expected for driver : " + entry.getValue().get(0)
											+ " Actual Downtime % : " + actualDowntime + " Expected Downtime : "
											+ entry.getValue().get(1));
						} else {
							Report.InfoTest(test, "Top Opportunities Downtime table data read successfully");
							Report.FailTest(test,
									"Downtime is not as expected for driver : " + entry.getValue().get(0)
											+ " Actual Downtime : " + actualDowntime + " Expected Downtime : "
											+ entry.getValue().get(1));
						}
					}
				}
			} else if (LOB.equals("Residential")) {
				downtimeRadioButton.click();
				topOpportunitiesDowntimeResTableData = readTable(downtimeResOpportunitiesColumns,
						downtimeResOpportunitiesRows, "dtWalkNTalkDowntimeRes");

				if (topOpportunitiesDowntimeResTableData == null) {
					Report.PassTestScreenshot(test, driver, "There is no data for Downtime in walk and Talk section",
							"Walk and Talk Downtime");
				} else {
					for (Entry<String, List<String>> entry : topOpportunitiesDowntimeResTableData.entrySet()) {
						System.out.println(entry.getKey() + ":" + entry.getValue().toString());
						String actualDowntime = getDriversDowntime(entry.getValue().get(0),Route, "dtWalkNTalkDowntimeRes");
						if (actualDowntime.equals(entry.getValue().get(1))) {
							Report.InfoTest(test, "Top Opportunities Downtime table data read successfully");
							Report.PassTest(test,
									"Downtime is as expected for driver : " + entry.getValue().get(0)
											+ " Actual Downtime % : " + actualDowntime + " Expected Downtime : "
											+ entry.getValue().get(1));
						} else {
							Report.InfoTest(test, "Top Opportunities Downtime table data read successfully");
							Report.FailTest(test,
									"Downtime is not as expected for driver : " + entry.getValue().get(0)
											+ " Actual Downtime : " + actualDowntime + " Expected Downtime : "
											+ entry.getValue().get(1));
						}
					}
				}
			}

			else if (LOB.equals("Roll Off")) {
				downtimeRadioButton.click();
				topOpportunitiesDowntimeRolTableData = readTable(downtimeRolOpportunitiesColumns,
						downtimeRolOpportunitiesRows, "dtWalkNTalkDowntimeRol");

				if (topOpportunitiesDowntimeRolTableData == null) {
					Report.PassTestScreenshot(test, driver, "There is no data for Downtime in walk and Talk section",
							"Walk and Talk Downtime");
				} else {
					for (Entry<String, List<String>> entry : topOpportunitiesDowntimeRolTableData.entrySet()) {
						System.out.println(entry.getKey() + ":" + entry.getValue().toString());
						String actualDowntime = getDriversDowntime(entry.getValue().get(0),Route, "dtWalkNTalkDowntimeRol");
						if (actualDowntime.equals(entry.getValue().get(1))) {
							Report.InfoTest(test, "Top Opportunities Downtime table data read successfully");
							Report.PassTest(test,
									"Downtime is as expected for driver : " + entry.getValue().get(0)
											+ " Actual Downtime % : " + actualDowntime + " Expected Downtime : "
											+ entry.getValue().get(1));
						} else {
							Report.InfoTest(test, "Top Opportunities Downtime table data read successfully");
							Report.FailTest(test,
									"Downtime is not as expected for driver : " + entry.getValue().get(0)
											+ " Actual Downtime : " + actualDowntime + " Expected Downtime : "
											+ entry.getValue().get(1));
						}
					}
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Top Opportunities Downtime table data");
		}

	}

	public String getDriversDowntime(String driverName,String Route, String TableName) {
		String actualDriverDowntime = null;
		try {
			FilterSection filterObj=new FilterSection(driver, test);
			filterObj.selectRoute(Route);
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
			downtimeRadioButton.click();
			driver.findElement(
					By.xpath("//table[@id='" + TableName + "']/tbody/tr/td//span[text()='" + driverName + "']"))
					.click();
			Util.selectFrame("opusReportContainer,subReportContainer");
			DowntimeDetailReport downtimeObj = new DowntimeDetailReport(driver, test);
			Map<String, List<String>> actualDowntimeSummaryTableData = downtimeObj.getActualDowntimeSummaryTableData();
			for (Entry<String, List<String>> entry : actualDowntimeSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Total Downtime (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						actualDriverDowntime = entry.getValue().get(i);
					}
				}
			}
			System.out.println("Actual Driver Downtime : " + actualDriverDowntime);
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer");
			backToRMDashBoard.click();
			return actualDriverDowntime;
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read the Downtime for driver : " + driverName);
		}
		return null;
	}

	public void validateRollOffGraphDropdown(String expectedGraphType) {
		String actualGraphType = null;
		try {
			actualGraphType = rollOffGraphType.getText();
			if (actualGraphType.equals(expectedGraphType)) {
				Report.PassTest(test, "Roll Off graph type is selected as expected. Actual is : " + actualGraphType
						+ " Expected is : " + expectedGraphType);
			} else {
				Report.FailTest(test, "Roll Off graph type is not as expected. Actual is : " + actualGraphType
						+ " Expected is : " + expectedGraphType);
			}

		} catch (Exception e) {
			Report.FailTestSnapshot(test, driver, e.getMessage(), "Roll Off graph Dropdown values");

		}
	}

	  
	 public Map<String,Map<String,String>> ReadCustomerFocusSectionTable(){
		  
		 Map<String,Map<String,String>> ReadTable = readTableLOBWise(CustomerFocusSectionColumns, CustomerFocusSectionRows, "dtCustomerFocusTable");
		  return ReadTable;
		  
		  
		  
	  }





	public void selectRollOffGraphType(String graphDropdownValue) {
		try {
			rollOffGraphDropdown.click();

			if (graphDropdownValue.equals(GlobalTestData.expectedRollOffGraphEquivalentHaulsPerHour)) {
				optionEquivalentHaulsPerHour.click();
				Report.PassTest(test, "Roll Off graph type " + graphDropdownValue + " is selected as expected.");
			} else if (graphDropdownValue.equals(GlobalTestData.expectedRollOffGraphVarianceMinPerHaul)) {
				optionVarianceMinPerHaul.click();
				Report.PassTest(test, "Roll Off graph type " + graphDropdownValue + " is selected as expected.");
			} else {
				Report.FailTest(test, "Roll Off graph type is not as expected.");
			}

		} catch (Exception e) {
			Report.FailTestSnapshot(test, driver, e.getMessage(), "Not able to select roll off graph type");

		}
	}

	public void validateRollOffGraphDateRange(String toDate) {
		try {
			SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
			Date dt1 = format1.parse(toDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dt1);
			calendar.add(Calendar.DAY_OF_YEAR, -105);
			Date dt2 = calendar.getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("M/d");
			String fromDate = formatter.format(dt2);
			toDate = formatter.format(dt1);
			Thread.sleep(2000);
			// Validate the X Axis is having Date range
			int noOfDatesXAxis = driver.findElements(By.xpath("//td[@id='colChartRol']/div/div/div/div/span")).size();
			String firstDateXAxis = driver.findElement(By.xpath("//td[@id='colChartRol']/div/div/div/div/span[1]"))
					.getText();
			String lastDateXAxis = driver
					.findElement(By.xpath("//td[@id='colChartRol']/div/div/div/div/span[" + (noOfDatesXAxis) + "]"))
					.getText();

			Date firstDateXAxisFormat = formatter.parse(firstDateXAxis);
			calendar.setTime(firstDateXAxisFormat);
			calendar.set(Calendar.YEAR, 2017);
			firstDateXAxisFormat = calendar.getTime();
			Date lastDateXAxisFormat = formatter.parse(lastDateXAxis);
			calendar.setTime(lastDateXAxisFormat);
			calendar.set(Calendar.YEAR, 2017);
			lastDateXAxisFormat = calendar.getTime();

			if ((firstDateXAxisFormat.getTime() >= dt2.getTime()) && (lastDateXAxisFormat.getTime() <= dt1.getTime()))

			{
				Report.PassTest(test,
						firstDateXAxis + " and " + lastDateXAxis + " are falling under " + fromDate + " and " + toDate);
			} else
				Report.FailTest(test, lastDateXAxis + " and " + lastDateXAxis + " are not falling under " + fromDate
						+ " and " + toDate);
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Not able to read date range");
		}

	}
	


	

	
	
	public void selectDOWGraph() throws IOException {
		if (DOWGraphRadioButton.isDisplayed()) {
			Util.highLightElement(driver, DOWGraphRadioButton);
			DOWGraphRadioButton.click();

		} else {
			Report.FailTest(test, "DOW graph is not selected");
		}
	}
	
	public void checkCommGraphExistence() throws IOException {
		try
		{
			Util.highLightElement(driver, commercialGraph);
			Report.PassTest(test, "Commercial graph is visible on RM Dashboard");
		} 
		catch(Exception e)
		{
			Report.FailTest(test, "Commercial graph is not visible on RM Dashboard");
		}
	}
	
	public void checkRollOffGraphExistence() throws IOException {
		try
		{
			Util.highLightElement(driver, RollOffGraph);
			Report.PassTest(test, "Roll Off graph is visible on RM Dashboard");
		} 
		catch(Exception e)
		{
			Report.FailTest(test, "Roll Off graph is not visible on RM Dashboard");
		}
	}
	
	public void checkResiGraphExistence() throws IOException {
		try
		{
			Util.highLightElement(driver, ResidentialGraph);
			Report.PassTest(test, "Residential graph is visible on RM Dashboard");
		} 
		catch(Exception e)
		{
			Report.FailTest(test, "Residential graph is not visible on RM Dashboard");
		}
	}

	

	public void UI_ValidateGraphSection() {
		try
		{
			isDOWGraphSelected();
			checkCommGraphExistence();
			checkRollOffGraphExistence();
			checkResiGraphExistence();
			selectHistoricalGraph();
			Thread.sleep(1000);
			isHistoricalGraphSelected();
			checkCommGraphExistence();
			checkRollOffGraphExistence();
			checkResiGraphExistence();
			selectDOWGraph();
			
		}
		catch(Exception e)
		{
			Report.FailTest(test, e.getMessage());
			Report.FailTestSnapshot(test, driver, "RM Dashboard Graph section validation", "RM Dashboard Graph section");
		}
		
	}

	public void UI_ValidateWeekToDateTopAndBottom5DriverSection() {
		
		String expectedWTDCommResiPlanEffHeader="Plan Efficiency";
		String expectedWTDCommResiActualEffHeader="Actual Efficiency";
		String expectedWTDCommResiROGapHeader="Gap";	
		String expectedWTDROPlanEffHeader="Allowance";
		String expectedWTDROActualEffHeader="Actual";
		String expectedTop5driverLabel="Top 5 Drivers";
		String expectedTopAndBottom5ActualHoursLabel="h:m";		
		String expectedBottom5driverLabel="Bottom 5 Drivers";
		try
		{
			Thread.sleep(2000);
			validateHeaderSectionwise("Plan Efficiency", expectedWTDCommResiPlanEffHeader, commPlanEfficiencyLabel, "Week to Date");
			validateHeaderSectionwise("Actual Efficiency", expectedWTDCommResiActualEffHeader, commActualEfficiencyLabel, "Week to Date");
			validateHeaderSectionwise("Gap", expectedWTDCommResiROGapHeader, commGapLabel, "Week to Date");
			validateHeaderSectionwise("Allowance", expectedWTDROPlanEffHeader,rollOffAllowanceLabel , "Week to Date");
			validateHeaderSectionwise("Actual", expectedWTDROActualEffHeader, rollOffActualLabel, "Week to Date");
			validateHeaderSectionwise("Gap", expectedWTDCommResiROGapHeader, rollOffGapLabel, "Week to Date");
			validateHeaderSectionwise("Plan Efficiency", expectedWTDCommResiPlanEffHeader, resiPlanEfficiencyLabel, "Week to Date");
			validateHeaderSectionwise("Actual Efficinecy", expectedWTDCommResiActualEffHeader, resiActualEfficiencyLabel, "Week to Date");
			validateHeaderSectionwise("Gap", expectedWTDCommResiROGapHeader, resiGapLabel, "Week to Date");
			validateHeaderSectionwise("Top 5 Drivers", expectedTop5driverLabel, top5DriverLabelComm, "Commercial Top 5 Driver");
			validateHeaderSectionwise("H:M", expectedTopAndBottom5ActualHoursLabel, top5DriverHMLabelComm, "Commercial Top 5 Driver");
			validateHeaderSectionwise("Top 5 Drivers", expectedTop5driverLabel, top5DriverLabelRO, "Roll Off Top 5 Driver");
			validateHeaderSectionwise("H:M", expectedTopAndBottom5ActualHoursLabel, top5DriverHMLabelRO, "Roll Off Top 5 Driver");
			validateHeaderSectionwise("Top 5 Drivers", expectedTop5driverLabel, top5DriverLabelResi, "Residential Top 5 Driver");
			validateHeaderSectionwise("H:M", expectedTopAndBottom5ActualHoursLabel, top5DriverHMLabelResi, "Residential Top 5 Driver");
			validateHeaderSectionwise("Bottom 5 Drivers", expectedBottom5driverLabel, bottom5DriverLabelComm, "Commercial Bottom 5 Driver");
			validateHeaderSectionwise("H:M", expectedTopAndBottom5ActualHoursLabel, bottom5DriverHMLabelComm, "Commercial Bottom 5 Driver");
			validateHeaderSectionwise("Bottom 5 Drivers", expectedBottom5driverLabel, bottom5DriverLabelRO, "Roll Off Bottom 5 Driver");
			validateHeaderSectionwise("H:M", expectedTopAndBottom5ActualHoursLabel, bottom5DriverHMLabelRO, "Roll Off Bottom 5 Driver");
			validateHeaderSectionwise("Bottom 5 Drivers", expectedBottom5driverLabel, bottom5DriverLabelResi, "Residential Bottom 5 Driver");
			validateHeaderSectionwise("H:M", expectedTopAndBottom5ActualHoursLabel, bottom5DriverHMLabelResi, "Residential Bottom 5 Driver");
			
		
		}
		catch(Exception e)
		{
			Report.FailTest(test, e.getMessage());
			Report.FailTestSnapshot(test, driver, "RM Dashboard Top and Bottom 5 driver section is not as expected", "RM Dashboard filter section");
		}
	}

	public void UI_ValidateWalkAndTalkSection() {
		String expectedWalkAndTalkHeader="Walk & Talk Coaching Opportunities";
		String expectedWalkAndTalkNetIdle="Net Idle";
		String expectedWalkAndTalkPreRoute="Pre-Route";
		String expectedWalkAndTalkPostRoute="Post-Route";
		String expectedWalkAndTalkSequenceCompliance="Sequence Compliance";
		String expectedWalkAndTalkDowntime="Downtime";	
		String expectedWalkAndTalkTopOpportunities="Top Opportunities: ";
		String expectedWalkAndTalkHM="h:m";
		String expectedWalkAndTalkPercent="%";
		try
		{
			
			validateHeaderSectionwise("Walk and talk header ", expectedWalkAndTalkHeader, walkAndTalkSectionHeader, "Walk and Talk");
			validateHeaderSectionwise("Net Idle ", expectedWalkAndTalkNetIdle, netIdleLabel, "Walk and Talk");
			validateHeaderSectionwise("Top opportunities Net Idle ", expectedWalkAndTalkTopOpportunities+expectedWalkAndTalkNetIdle, walkAndTalkTopOpportunitiesNetIdleLabelComm, "Commerical Walk and Talk");
			validateHeaderSectionwise("Net Idle H:M ", expectedWalkAndTalkHM, walkAndTalkHMNetIdleLabelComm, "Commercial Walk and Talk");
			validateHeaderSectionwise("Top opportunities Net Idle ", expectedWalkAndTalkTopOpportunities+expectedWalkAndTalkNetIdle, walkAndTalkTopOpportunitiesNetIdleLabelRO, "Roll Off Walk and Talk");
			validateHeaderSectionwise("Net Idle H:M ", expectedWalkAndTalkHM, walkAndTalkHMNetIdleLabelRO, "Roll Off Walk and Talk");
			validateHeaderSectionwise("Top opportunities Net Idle ", expectedWalkAndTalkTopOpportunities+expectedWalkAndTalkNetIdle, walkAndTalkTopOpportunitiesNetIdleLabelResi, "Residential Walk and Talk");
			validateHeaderSectionwise("Net Idle H:M ", expectedWalkAndTalkHM, walkAndTalkHMNetIdleLabelResi, "Residential Walk and Talk");
			preRouteRadioButton.click();
			validateHeaderSectionwise("Pre Route ", expectedWalkAndTalkPreRoute, preRouteLabel, "Walk and Talk");
			validateHeaderSectionwise("Top opportunities Pre Route ", expectedWalkAndTalkTopOpportunities+expectedWalkAndTalkPreRoute, walkAndTalkTopOpportunitiesPreRouteLabelComm, "Commerical Walk and Talk");
			validateHeaderSectionwise("Pre Route H:M ", expectedWalkAndTalkHM, walkAndTalkHMPreRouteLabelComm, "Commercial Walk and Talk");
			validateHeaderSectionwise("Top opportunities Pre Route ", expectedWalkAndTalkTopOpportunities+expectedWalkAndTalkPreRoute, walkAndTalkTopOpportunitiesPreRouteLabelRO, "Roll Off Walk and Talk");
			validateHeaderSectionwise("Pre Route H:M ", expectedWalkAndTalkHM, walkAndTalkHMPreRouteLabelRO, "Roll Off Walk and Talk");
			validateHeaderSectionwise("Top opportunities Pre Route ", expectedWalkAndTalkTopOpportunities+expectedWalkAndTalkPreRoute, walkAndTalkTopOpportunitiesPreRouteLabelResi, "Residential Walk and Talk");
			validateHeaderSectionwise("Pre Route H:M ", expectedWalkAndTalkHM, walkAndTalkHMPreRouteLabelResi, "Residential Walk and Talk");
			postRouteRadioButton.click();
			validateHeaderSectionwise("Post Route ", expectedWalkAndTalkPostRoute, postRouteLabel, "Walk and Talk");
			validateHeaderSectionwise("Top opportunities Post Route ", expectedWalkAndTalkTopOpportunities+expectedWalkAndTalkPostRoute, walkAndTalkTopOpportunitiesPostRouteLabelComm, "Commerical Walk and Talk");
			validateHeaderSectionwise("Post Route H:M ", expectedWalkAndTalkHM, walkAndTalkHMPostRouteLabelComm, "Commercial Walk and Talk");
			validateHeaderSectionwise("Top opportunities Post Route ", expectedWalkAndTalkTopOpportunities+expectedWalkAndTalkPostRoute, walkAndTalkTopOpportunitiesPostRouteLabelRO, "Roll Off Walk and Talk");
			validateHeaderSectionwise("Post Route H:M ", expectedWalkAndTalkHM, walkAndTalkHMPostRouteLabelRO, "Roll Off Walk and Talk");
			validateHeaderSectionwise("Top opportunities Post Route ", expectedWalkAndTalkTopOpportunities+expectedWalkAndTalkPostRoute, walkAndTalkTopOpportunitiesPostRouteLabelResi, "Residential Walk and Talk");
			validateHeaderSectionwise("Post Route H:M ", expectedWalkAndTalkHM, walkAndTalkHMPostRouteLabelResi, "Residential Walk and Talk");
			sequenceComplianceRadioButton.click();
			validateHeaderSectionwise("Sequence Compliance ", expectedWalkAndTalkSequenceCompliance, sequenceComplianceLabel, "Walk and Talk");
			validateHeaderSectionwise("Top opportunities Sequence Compliance ", expectedWalkAndTalkTopOpportunities+expectedWalkAndTalkSequenceCompliance, walkAndTalkTopOpportunitiesSequenceComplianceLabelComm, "Commerical Walk and Talk");
			validateHeaderSectionwise("Sequence Compliance H:M ", expectedWalkAndTalkPercent, walkAndTalkHMSequenceComplianceLabelComm, "Commercial Walk and Talk");
			validateHeaderSectionwise("Top opportunities Sequence Compliance ", expectedWalkAndTalkTopOpportunities+expectedWalkAndTalkSequenceCompliance, walkAndTalkTopOpportunitiesSequenceComplianceLabelRO, "Roll Off Walk and Talk");
			validateHeaderSectionwise("Sequence Compliance H:M ", expectedWalkAndTalkPercent, walkAndTalkHMSequenceComplianceLabelRO, "Roll Off Walk and Talk");
			validateHeaderSectionwise("Top opportunities Sequence Compliance ", expectedWalkAndTalkTopOpportunities+expectedWalkAndTalkSequenceCompliance, walkAndTalkTopOpportunitiesSequenceComplianceLabelResi, "Residential Walk and Talk");
			validateHeaderSectionwise("Sequence Compliance H:M ", expectedWalkAndTalkPercent, walkAndTalkHMSequenceComplianceLabelResi, "Residential Walk and Talk");
		    downtimeRadioButton.click();
		    validateHeaderSectionwise("Downtime ", expectedWalkAndTalkDowntime, downtimeLabel, "Walk and Talk");
			validateHeaderSectionwise("Top opportunities Downtime ", expectedWalkAndTalkTopOpportunities+expectedWalkAndTalkDowntime, walkAndTalkTopOpportunitiesDowntimeLabelComm, "Commerical Walk and Talk");
			validateHeaderSectionwise("Downtime H:M ", expectedWalkAndTalkHM, walkAndTalkHMDowntimeLabelComm, "Commercial Walk and Talk");
			validateHeaderSectionwise("Top opportunities Downtime ", expectedWalkAndTalkTopOpportunities+expectedWalkAndTalkDowntime, walkAndTalkTopOpportunitiesDowntimeLabelRO, "Roll Off Walk and Talk");
			validateHeaderSectionwise("Downtime H:M ", expectedWalkAndTalkHM, walkAndTalkHMDowntimeLabelRO, "Roll Off Walk and Talk");
			validateHeaderSectionwise("Top opportunities Downtime ", expectedWalkAndTalkTopOpportunities+expectedWalkAndTalkDowntime, walkAndTalkTopOpportunitiesDowntimeLabelResi, "Residential Walk and Talk");
			validateHeaderSectionwise("Downtime H:M ", expectedWalkAndTalkHM, walkAndTalkHMDowntimeLabelResi, "Residential Walk and Talk");
	
		
		}
		catch(Exception e)
		{
			Report.FailTest(test, e.getMessage());
			Report.FailTestSnapshot(test, driver, "RM Dashboard Walk and Talk section is not as expected", "RM Dashboard Walk and Talk section");
		}
		
	}

	public void UI_ValidateCustomerFocusSection() {
		
		String expectedCustomerFocusHeader="Customer Focus Section";
		String expectedComLOBLabel = "COM";
		String expectedResiLOBLabel = "RES";
		String expectedROLOBLabel = "ROL";
		
		
		try{
		validateHeaderSectionwise("Customer Focus Section header ", expectedCustomerFocusHeader, customerFocusSectionHeader, "Customer Focus");
		validateHeaderSectionwise("Customer Focus Section Commercial  ", expectedComLOBLabel, comLOBLabel, "Customer Focus");
		validateHeaderSectionwise("Customer Focus Section Residential ", expectedResiLOBLabel, ResiLOBLabel, "Customer Focus");
		validateHeaderSectionwise("Customer Focus Section Roll Off ", expectedROLOBLabel, ROLOBLabel, "Customer Focus");
		Util.switchToDefaultWindow();
		VerifyColumnName_CustomerFocusTable(GlobalTestData.ArColumnName);
		
		
		}
		catch(Exception e)
		{
			Report.FailTest(test, e.getMessage());
			Report.FailTestSnapshot(test, driver, "RM Dashboard Customer Focus section is not as expected", "RM Dashboard Customer Focus section");
		}
		
	}
	
	private void validateHeaderSectionwise(String headerName,String expectedValue,WebElement actualValue,String sectionName)
	{
		try
		{
			
			if(expectedValue.equals(actualValue.getText()))
			{
				Util.highLightElement(driver, actualValue);
				Report.PassTest(test, headerName+" label is as expected in "+sectionName+" section of AM Huddle.");
			}
			else
			{
				Util.highLightElement(driver, actualValue);
				Report.FailTest(test, headerName+" label is not as expected in "+sectionName+" section of AM Huddle Actual is : "+actualValue.getText()+" and Expected is : "+expectedValue);
			}
		
			
		
		}
		catch(Exception e)
		{
			Report.FailTest(test, e.getMessage());
			Report.FailTestSnapshot(test, driver, "RM Dashboard Filter section validation", "RM Dashboard filter section");
		}
	}
	
	public void UI_ValidateWalkAndTalkSection_ExportPDF(String downloadFilePath) throws IOException
	{	
		String[] activity= new String[] {"Net Idle","Pre-Route","Post-Route","Sequence Compliance","Downtime"}; 
		for (int i=1;i<=5;i++)
		{	
			try
			{
				Util.pageScroll(0, 550);
				Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
				driver.findElement(By.xpath(".//*[@id='inpSubRptOpt_"+i+"']")).click();
				System.out.println(activity[i-1]+" Radio Button was selected");
				Report.PassTest(test, activity[i-1]+" Radio Button was selected");
				Thread.sleep(2000);
				Util.switchToDefaultWindow();
				Util.pageScroll(0,-550);
				Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
				PDFLink.click();
				System.out.println("PDF export was clicked for "+ activity[i-1]);
				Report.PassTest(test, "PDF export was clicked for "+ activity[i-1]);
				Thread.sleep(4000);
				
				Util.switchBrowserTabsAndStay();
				
				Util.keystrokes_ControlS();
				
				Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\AutoIt\\ChromeDownload.exe");
				Thread.sleep(15000);
				File recentFile=Util.getLatestFilefromDir("C:\\Working\\PDFDownloads");
				System.out.println(recentFile.getName());
				verifyPDFContent("C:\\Working\\PDFDownloads"+"\\"+recentFile.getName(),activity[i-1]);
				driver.close();
				
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(0));
				Util.switchToDefaultWindow();
				
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				Report.FailTest(test, e.getMessage());
			}
		}
		
	}
	
	public void verifyPDFContent(String filepath,String reqTextInPDF)
	{
		String[] reqText = reqTextInPDF.split(";");

		for (int i = 0; i < reqText.length; i++) {
			try {
				String allText = Util.getPDFText(filepath);
				if (allText.contains(reqText[i])) {
					System.out.println(reqText[i] + " is present in the PDF");
					Report.PassTest(test, reqText[i] + "is present in the PDF");
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				Report.FailTest(test, e.getMessage());
			}
		}
	}
}
//}//last closing bracket
//>>>>>>> branch 'master' of https://github.wm.com/srathore/SDO_Automation
