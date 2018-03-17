package ObjectRepository.SDO;

import java.io.IOError;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.sun.tools.internal.xjc.Driver;

import SupportLibraries.DB;
import SupportLibraries.LogClass;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;
import TestData.GlobalQuery;

public class RollOffFlashReport {

	ExtentTest test;
	WebDriver driver;

	public RollOffFlashReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='jsFlashLink']/span[contains(., 'See Roll Off Flash Report')]")
	WebElement RollOffFlashReportLink;

	@FindBy(xpath = "//*[@id='inpTimeOption_1']")
	WebElement HistoricalOption;

	@FindBy(xpath = "//table[@id='dtRouteSummary']")
	WebElement summaryTable;

	@FindBy(xpath = "//table[@id='t2']")
	WebElement efficiencyTable;

	@FindBy(xpath = "//table[@id='t2']")
	WebElement detailTable;

	@FindBy(xpath = "//table[@id='dtRouteSummary']/thead/tr/th")
	List<WebElement> summaryTableColumns;

	@FindBy(xpath = "//table[@id='dtRouteSummary']/tbody/tr")
	List<WebElement> summaryTableRows;

	@FindBy(xpath = "//table[@id='t2']/thead/tr/th")
	List<WebElement> efficiencyTableColumns;

	@FindBy(xpath = "//table[@id='t2']/tbody/tr")
	List<WebElement> efficiencyTableRows;

	@FindBy(xpath = "//table[@id='t2']/thead/tr/th")
	List<WebElement> detailTableColumns;

	@FindBy(xpath = "//table[@id='t2']/tbody/tr")
	List<WebElement> detailTableRows;

	@FindBy(xpath = "//*[@id='inpSubReportOpt_2']")
	WebElement detailSubView;

	@FindBy(xpath = "//*[@id='inpSubReportOpt_1']")
	WebElement efficiencySubView;

	@FindBy(xpath = "//div[@class='rdWaitCustomClass']/table/tbody/tr/td/span[text()='Please Wait..']")
	WebElement pleaseWait;

	@FindBy(xpath = "//table[@id='dtFlashSub1']/thead/tr/th")
	List<WebElement> ticketInformationTableColumns;

	@FindBy(xpath = "//table[@id='dtFlashSub1']/tbody/tr")
	List<WebElement> ticketInformationTableRows;

	@FindBy(xpath = "//table[@id='dtFlashSub2']/thead/tr/th")
	List<WebElement> travelInformationTableColumns;

	@FindBy(xpath = "//table[@id='dtFlashSub2']/tbody/tr")
	List<WebElement> travelInformationTableRows;

	@FindBy(xpath = "//table[@id='dtFlashSub3']/thead/tr/th")
	List<WebElement> onPropertyInformationTableColumns;

	@FindBy(xpath = "//table[@id='dtFlashSub3']/tbody/tr")
	List<WebElement> onPropertyInformationTableRows;

	@FindBy(xpath = "//table[@id='dtFlashSub3']/thead/tr/th")
	List<WebElement> disposalInformationTableColumns;

	@FindBy(xpath = "//table[@id='dtFlashSub3']/tbody/tr")
	List<WebElement> disposalInformationTableRows;

	@FindBy(xpath = "//table[@id='dtFlashSub4']/thead/tr/th")
	List<WebElement> driverDisposalInformationTableColumns;

	@FindBy(xpath = "//table[@id='dtFlashSub4']/tbody/tr")
	List<WebElement> driverDisposalInformationTableRows;

	@FindBy(xpath = "//table[@id='dtFlashSub2']/thead/tr/th")
	List<WebElement> serviceInformationTableColumns;

	@FindBy(xpath = "//table[@id='dtFlashSub2']/tbody/tr")
	List<WebElement> serviceInformationTableRows;

	@FindBy(xpath = "//*[@id='actExportToPDF']/span")
	WebElement PDFOptionLink;

	@FindBy(xpath = "//*[@id='t2']/tbody/tr/td/a/span[@id='lblDriver_Row1']")
	WebElement FirstRowDriverLink;

	@FindBy(xpath = "//*[@id='t2']/tbody/tr/td/a/span[@id='lblRte_Row1']")
	WebElement FirstRowRouteLink;

	@FindBy(xpath = ".//*[@class='highcharts-container']/*[@xmlns='http://www.w3.org/2000/svg']/*[@class='highcharts-legend']/*[@id]/*[@id]/*[@class='highcharts-legend-item']/*[text()='Actual']")
	WebElement GraphLegendActual;

	@FindBy(xpath = ".//*[@class='highcharts-container']/*[@xmlns='http://www.w3.org/2000/svg']/*[@class='highcharts-legend']/*[@id]/*[@id]/*[@class='highcharts-legend-item']/*[text()='Plan']")
	WebElement GraphLegendPlan;

	@FindBy(xpath = ".//*[@class='highcharts-container']/*[@xmlns='http://www.w3.org/2000/svg']/*[@class='highcharts-legend']/*[@id]/*[@id]/*[@class='highcharts-legend-item']/*[text()='Target']")
	WebElement GraphLegendTarget;

	@FindBy(xpath = ".//*[@class='highcharts-container']/*[@xmlns='http://www.w3.org/2000/svg']/*[@class='highcharts-axis']/*[@class=' highcharts-yaxis-title']/*")
	WebElement GraphYAxis;

	@FindBy(xpath = ".//*[@class='highcharts-container']/*[@xmlns='http://www.w3.org/2000/svg']/*[@class='highcharts-series-group']/*[@class='highcharts-markers highcharts-tracker']/*[@fill='#77B310'][1]")
	WebElement GraphPointerActual;

	@FindBy(xpath = ".//*[@class='highcharts-tooltip']/span/div/div[1]")
	WebElement GraphPointerActualOverLayHeader;

	@FindBy(xpath = ".//*[@class='highcharts-tooltip']/span/div/div[2]/table/tbody/tr[1]/td[2]")
	WebElement GraphPointerActualOverLayActual;

	@FindBy(xpath = ".//*[@class='highcharts-tooltip']/span/div/div[2]/table/tbody/tr[2]/td[2]")
	WebElement GraphPointerActualOverLayPlan;

	@FindBy(xpath = ".//*[@class='highcharts-tooltip']/span/div/div[2]/table/tbody/tr[3]/td[2]")
	WebElement GraphPointerActualOverLayTarget;

	public void clickRollOffFlashReport() {
		try {
			Util.ElementExist(driver, RollOffFlashReportLink);
			RollOffFlashReportLink.click();
			Report.PassTest(test, "Clicked on Roll off Flash Report");
		} catch (Exception e) {
			Report.FailTestSnapshot(test, driver, "Not able to click on See Roll Off Flash Link",
					"Roll Off Flash Report Link");
		}
	}

	public void selectHistoricalOption() throws IOException {
		Util.ElementExist(driver, HistoricalOption);
		HistoricalOption.click();
		Report.PassTest(test, "Historical Option get selected");
	}

	public void selectDetailSubView() throws IOException {
		Util.ElementExist(driver, detailSubView);
		detailSubView.click();
		Report.PassTest(test, "Detail SubView get selected as expected");
	}

	public void selectEfficiencySubView() throws IOException {
		Util.ElementExist(driver, efficiencySubView);
		efficiencySubView.click();
		Report.PassTest(test, "Efficiency SubView get selected as expected");

	}

	public Map<String, List<String>> getActualSummaryTableData() throws IOException {
		System.out.println("inside Actual Summary Data");
		Map<String, List<String>> summaryTableData = new HashMap<>();
		try {
			summaryTableData = readTable(summaryTableColumns, summaryTableRows, "dtRouteSummary");
			Report.InfoTest(test, "Summary table data read successfully");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read summary table data");
		}

		for (Entry<String, List<String>> entry : summaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return summaryTableData;
	}

	public Map<String, List<String>> getActualEfficiencyTableData() {
		System.out.println("inside Actual Efficiency Data");
		Map<String, List<String>> efficiencyTableData = new HashMap<>();
		try {
			efficiencyTableData = readTable(efficiencyTableColumns, efficiencyTableRows, "t2");
			Report.InfoTest(test, "Efficiency table data read successfully");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Efficiency table data");
		}
		for (Entry<String, List<String>> entry : efficiencyTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return efficiencyTableData;
	}

	public Map<String, List<String>> getActualDetailTableData() {
		System.out.println("inside Actual Detail Data");
		Map<String, List<String>> detailTableData = new HashMap<>();
		try {
			detailTableData = readTable(detailTableColumns, detailTableRows, "t2");
			Report.InfoTest(test, "Detail table data read successfully");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Detail table data");
		}
		for (Entry<String, List<String>> entry : detailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return detailTableData;
	}

	public Map<String, Map<String, List<String>>> getActualTicketInformationTableData(String route)
			throws IOException, InterruptedException {
		Thread.sleep(5000);
		String[] routes = route.split(";");
		Map<String, Map<String, List<String>>> ticketInformationTableData = new HashMap<>();
		Map<String, List<String>> ticketInformationTemp = new HashMap<>();
		for (int i = 0; i < routes.length; i++) {
			Util.selectFrame("opusReportContainer,subReportContainer");
			driver.findElement(By.xpath("//table[@id='t2']/tbody/tr/td/a/span[text()='" + routes[i] + "']")).click();
			try {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer,subFlashDetail_Row" + (i + 1));
				ticketInformationTemp = readTable(ticketInformationTableColumns, ticketInformationTableRows,
						"dtFlashSub1");
				Report.InfoTest(test, "Ticket Information table data read successfully for Route : " + routes[i]);
				Util.switchToDefaultWindow();
			} catch (Exception e) {
				Report.FailTest(test, "Not able to read Ticket Information table data");
			}
			ticketInformationTableData.put(routes[i], ticketInformationTemp);
		}

		for (Entry<String, Map<String, List<String>>> entry : ticketInformationTableData.entrySet()) {
			for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
				System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
			}
		}

		return ticketInformationTableData;
	}

	public Map<String, Map<String, List<String>>> getActualServiceInformation(String route) throws IOException {
		String[] routes = route.split(";");
		Map<String, Map<String, List<String>>> serviceInformationTableData = new HashMap<>();
		Map<String, List<String>> serviceInformationTemp = new HashMap<>();
		for (int i = 0; i < routes.length; i++) {
			// Util.selectFrame("opusReportContainer,subReportContainer");
			// driver.findElement(By.xpath("//table[@id='t2']/tbody/tr/td/a/span[text()='"+routes[i]+"']")).click();
			try {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer,subFlashDetail_Row" + (i + 1));
				serviceInformationTemp = readTable(serviceInformationTableColumns, serviceInformationTableRows,
						"dtFlashSub2");
				Report.InfoTest(test, "Service Information table data read successfully for Route : " + routes[i]);
				Util.switchToDefaultWindow();
			} catch (Exception e) {
				Report.FailTest(test, "Not able to read Service Information table data");
			}
			serviceInformationTableData.put(routes[i], serviceInformationTemp);
		}

		for (Entry<String, Map<String, List<String>>> entry : serviceInformationTableData.entrySet()) {
			for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
				System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
			}
		}

		return serviceInformationTableData;
	}

	public Map<String, Map<String, List<String>>> getActualDisposalInformation(String route) throws IOException {
		String[] routes = route.split(";");
		Map<String, Map<String, List<String>>> disposalInformationTableData = new HashMap<>();
		Map<String, List<String>> disposalInformationTemp = new HashMap<>();
		for (int i = 0; i < routes.length; i++) {
			// Util.selectFrame("opusReportContainer,subReportContainer");
			// driver.findElement(By.xpath("//table[@id='t2']/tbody/tr/td/a/span[text()='"+routes[i]+"']")).click();
			try {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer,subFlashDetail_Row" + (i + 1));
				disposalInformationTemp = readTable(disposalInformationTableColumns, disposalInformationTableRows,
						"dtFlashSub3");
				Report.InfoTest(test, "Service Information table data read successfully for Route : " + routes[i]);
				Util.switchToDefaultWindow();
			} catch (Exception e) {
				Report.FailTest(test, "Not able to read Service Information table data");
			}
			disposalInformationTableData.put(routes[i], disposalInformationTemp);
		}

		for (Entry<String, Map<String, List<String>>> entry : disposalInformationTableData.entrySet()) {
			for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
				System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
			}
		}

		return disposalInformationTableData;
	}

	public Map<String, Map<String, List<String>>> getActualTravelInformation(String Driver) throws IOException {
		String[] drivers = Driver.split(";");
		Map<String, Map<String, List<String>>> travelInformationTableData = new HashMap<>();
		Map<String, List<String>> travelInformationTemp = new HashMap<>();
		for (int i = 0; i < drivers.length; i++) {
			Util.selectFrame("opusReportContainer,subReportContainer");
			driver.findElement(By.xpath("//table[@id='t2']/tbody/tr/td/a/span[text()='" + drivers[i] + "']")).click();
			try {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer,subFlashDetailTravel_Row" + (i + 1));
				travelInformationTemp = readTable(travelInformationTableColumns, travelInformationTableRows,
						"dtFlashSub2");
				Report.InfoTest(test, "Travel Information table data read successfully for driver : " + drivers[i]);
				Util.switchToDefaultWindow();
			} catch (Exception e) {
				Report.FailTest(test, "Not able to read Travel Information table data");
			}
			travelInformationTableData.put(drivers[i], travelInformationTemp);
		}

		for (Entry<String, Map<String, List<String>>> entry : travelInformationTableData.entrySet()) {
			for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
				System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
			}
		}

		return travelInformationTableData;
	}

	public Map<String, Map<String, List<String>>> getActualOnPropertyInformation(String Driver) throws IOException {
		String[] drivers = Driver.split(";");
		Map<String, Map<String, List<String>>> onPropertyInformationTableData = new HashMap<>();
		Map<String, List<String>> onPropertyInformationTemp = new HashMap<>();
		for (int i = 0; i < drivers.length; i++) {
			Util.selectFrame("opusReportContainer,subReportContainer");
			driver.findElement(By.xpath("//table[@id='t2']/tbody/tr/td/a/span[text()='" + drivers[i] + "']")).click();
			try {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer,subFlashDetailTravel_Row" + (i + 1));
				onPropertyInformationTemp = readTable(onPropertyInformationTableColumns, onPropertyInformationTableRows,
						"dtFlashSub3");
				Report.InfoTest(test,
						"On Property Information table data read successfully for driver : " + drivers[i]);
				Util.switchToDefaultWindow();
			} catch (Exception e) {
				Report.FailTest(test, "Not able to read On Property Information table data");
			}
			onPropertyInformationTableData.put(drivers[i], onPropertyInformationTemp);
		}

		for (Entry<String, Map<String, List<String>>> entry : onPropertyInformationTableData.entrySet()) {
			for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
				System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
			}
		}

		return onPropertyInformationTableData;
	}

	public Map<String, Map<String, List<String>>> getActualDriverDisposalInformation(String Driver) throws IOException {
		String[] drivers = Driver.split(";");
		Map<String, Map<String, List<String>>> driverDisposalInformationTableData = new HashMap<>();
		Map<String, List<String>> driverDisposalInformationTemp = new HashMap<>();
		for (int i = 0; i < drivers.length; i++) {
			Util.selectFrame("opusReportContainer,subReportContainer");
			driver.findElement(By.xpath("//table[@id='t2']/tbody/tr/td/a/span[text()='" + drivers[i] + "']")).click();
			try {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer,subFlashDetailTravel_Row" + (i + 1));
				driverDisposalInformationTemp = readTable(driverDisposalInformationTableColumns,
						driverDisposalInformationTableRows, "dtFlashSub4");
				Report.InfoTest(test,
						"Driver Disposal Information table data read successfully for driver : " + drivers[i]);
				Util.switchToDefaultWindow();
			} catch (Exception e) {
				Report.FailTest(test, "Not able to read Driver Disposal Information table data");
			}
			driverDisposalInformationTableData.put(drivers[i], driverDisposalInformationTemp);
		}

		for (Entry<String, Map<String, List<String>>> entry : driverDisposalInformationTableData.entrySet()) {
			for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
				System.out.println(entry2.getKey() + ":" + entry2.getValue().toString());
			}
		}

		return driverDisposalInformationTableData;
	}

	public Map<String, List<String>> readTable(List<WebElement> columns, List<WebElement> rows, String tableName) {

		if (tableName.equals("dtRouteSummary")) {
			Map<String, List<String>> objTable = new HashMap<>();
			// System.out.println("Number of cloumns : "+columns.size());
			// System.out.println("Number of rows : "+rows.size());
			for (int iCount = 2; iCount <= columns.size(); iCount++) {
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

		else if (tableName.contains("FlashSub")) {
			Map<String, List<String>> objTable = new HashMap<>();
			// System.out.println("Number of cloumns : "+columns.size());
			// System.out.println("Number of rows : "+rows.size());
			for (int iCount = 1; iCount <= columns.size(); iCount++) {
				// List<WebElement> objCol =
				// rows.get(iCount).findElements(By.cssSelector("td.tableTxt"));
				List<String> rowValues = new ArrayList<>();
				for (int row = 1; row <= rows.size(); row++) {
					try {
						if (driver.findElement(By.xpath(
								"//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + iCount + "]/span"))
								.getText().contains("\n"))
							rowValues.add(driver.findElement(By.xpath(
									"//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + iCount + "]/span"))
									.getText().replace("\n", ""));
						else
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

		else {
			Map<String, List<String>> objTable = new HashMap<>();
			// System.out.println("Number of cloumns : "+columns.size());
			// System.out.println("Number of rows : "+rows.size());
			for (int iCount = 1; iCount <= columns.size(); iCount++) {
				// List<WebElement> objCol =
				// rows.get(iCount).findElements(By.cssSelector("td.tableTxt"));
				List<String> rowValues = new ArrayList<>();
				for (int row = 1; row < rows.size(); row = row + 4) {
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

	public List<String> getSummaryTableColumns() throws IOException {
		System.out.println("Reading summary columns");
		List<String> summaryTableColumn = null;
		try {
			summaryTableColumn = readColumns(summaryTableColumns, "dtRouteSummary");
			Report.InfoTest(test, "Summary table column read successfully");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read summary table columns");
		}

		System.out.println(summaryTableColumn.toString());
		return summaryTableColumn;
	}

	public List<String> getEfficiencyTableColumns() throws IOException {
		System.out.println("Reading Efficiency columns");
		List<String> efficiencyTableColumn = null;
		try {
			efficiencyTableColumn = readColumns(efficiencyTableColumns, "t2");
			Report.InfoTest(test, "Efficiency table column read successfully");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Efficiency table columns");
		}

		System.out.println(efficiencyTableColumn.toString());
		return efficiencyTableColumn;
	}

	public List<String> getDetailTableColumns() throws IOException {
		System.out.println("Reading Detail columns");
		List<String> detailTableColumn = null;
		try {
			detailTableColumn = readColumns(detailTableColumns, "t2");
			Report.InfoTest(test, "Detail table column read successfully");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Detail table columns");
		}

		System.out.println(detailTableColumn.toString());
		return detailTableColumn;
	}

	public List<String> getTicketInformationTableColumns(String route) throws IOException {
		String[] routes = route.split(";");
		System.out.println("Reading Ticket Information Table columns");
		Util.selectFrame("opusReportContainer,subReportContainer");
		driver.findElement(By.xpath("//table[@id='t2']/tbody/tr/td/a/span[text()='" + routes[0] + "']")).click();
		List<String> ticketInformationTableColumn = null;
		try {
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer,subFlashDetail_Row1");
			ticketInformationTableColumn = readColumns(ticketInformationTableColumns, "dtFlashSub1");
			Report.InfoTest(test, "Ticket Information table column read successfully");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Ticket Information table columns");
		}

		System.out.println(ticketInformationTableColumn.toString());
		return ticketInformationTableColumn;
	}

	public List<String> getServiceInformationTableColumns(String route) throws IOException {
		System.out.println("Reading Service Information Table columns");
		// Util.selectFrame("opusReportContainer,subReportContainer");
		// driver.findElement(By.xpath("//table[@id='t2']/tbody/tr/td/a/span[text()='"+routes[0]+"']")).click();
		List<String> serviceInformationTableColumn = null;
		try {
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer,subFlashDetail_Row1");
			serviceInformationTableColumn = readColumns(serviceInformationTableColumns, "dtFlashSub2");
			Report.InfoTest(test, "Service Information table column read successfully");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Service Information table columns");
		}

		System.out.println(serviceInformationTableColumn.toString());
		return serviceInformationTableColumn;
	}

	public List<String> getDisposalInformationTableColumns(String route) throws IOException {
		System.out.println("Reading Disposal Information Table columns");
		// Util.selectFrame("opusReportContainer,subReportContainer");
		// driver.findElement(By.xpath("//table[@id='t2']/tbody/tr/td/a/span[text()='"+routes[0]+"']")).click();
		List<String> disposalInformationTableColumn = null;
		try {
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer,subFlashDetail_Row1");
			disposalInformationTableColumn = readColumns(disposalInformationTableColumns, "dtFlashSub3");
			Report.InfoTest(test, "Disposal Information table column read successfully");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Disposal Information table columns");
		}

		System.out.println(disposalInformationTableColumn.toString());
		return disposalInformationTableColumn;
	}

	public List<String> getTravelInformationTableColumns(String Driver) throws IOException {
		String[] drivers = Driver.split(";");
		System.out.println("Reading Travel Information Table columns");
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		driver.findElement(By.xpath("//table[@id='t2']/tbody/tr/td/a/span[text()='" + drivers[0] + "']")).click();
		List<String> travelInformationTableColumn = null;
		try {
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer,subFlashDetailTravel_Row1");
			travelInformationTableColumn = readColumns(travelInformationTableColumns, "dtFlashSub2");
			Report.InfoTest(test, "Travel Information table column read successfully");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Travel Information table columns");
		}

		System.out.println(travelInformationTableColumn.toString());
		return travelInformationTableColumn;
	}

	public List<String> getOnPropertyInformationTableColumns(String Driver) throws IOException {
		System.out.println("Reading On Property Information Table columns");
		// Util.switchToDefaultWindow();
		// Util.selectFrame("opusReportContainer,subReportContainer");
		// driver.findElement(By.xpath("//table[@id='t2']/tbody/tr/td/a/span[text()='"+drivers[0]+"']")).click();
		List<String> onPropertyInformationTableColumn = null;
		try {
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer,subFlashDetailTravel_Row1");
			onPropertyInformationTableColumn = readColumns(onPropertyInformationTableColumns, "dtFlashSub3");
			Report.InfoTest(test, "On Property Information table column read successfully");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read On Property Information table columns");
		}

		System.out.println(onPropertyInformationTableColumn.toString());
		return onPropertyInformationTableColumn;
	}

	public List<String> readColumns(List<WebElement> columns, String tableName) {
		List<String> columnNames = new ArrayList<>();
		if (tableName.equals("dtRouteSummary")) {
			System.out.println("Number of cloumns : " + columns.size());
			for (int iCount = 2; iCount <= columns.size(); iCount++) {
				try {
					if (columns.get(iCount - 1).getText().contains("\n"))
						columnNames.add(columns.get(iCount - 1).getText().replace("\n", ""));
					else
						columnNames.add(columns.get(iCount - 1).getText());
				} catch (Exception e) {
					columnNames.add(columns.get(iCount - 1).getText());
				}
			}
		} else {
			System.out.println("Number of cloumns : " + columns.size());
			for (int iCount = 1; iCount <= columns.size(); iCount++) {
				try {
					if (columns.get(iCount - 1).getText().contains("\n"))
						columnNames.add(columns.get(iCount - 1).getText().replace("\n", ""));
					else
						columnNames.add(columns.get(iCount - 1).getText());
				} catch (Exception e) {
					columnNames.add(columns.get(iCount - 1).getText());
				}
			}
		}
		System.out.println("Column ----" + columnNames.size());
		return columnNames;
	}

	public void validateSummaryData(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualEfficiencyTableData, Map<String, List<String>> actualDetailTableData) {
		validateMiles(actualSummaryTableData, actualEfficiencyTableData);
		validatePreRouteHours(actualSummaryTableData, actualDetailTableData);
		validatePostRouteHours(actualSummaryTableData, actualDetailTableData);
		validateIdleTime(actualSummaryTableData, actualDetailTableData);
		validateDownTime(actualSummaryTableData, actualDetailTableData);
	}

	/*
	 * public void validateEfficiencyData(Map<String, List<String>>
	 * actualEfficiencyTableData, String expectedLOB, String expectedRoute,
	 * String expectedDriver, String expectedClockInTime, String
	 * expectedClockOutTime) { validateLOB(actualEfficiencyTableData,
	 * expectedLOB); validateRouteName(actualEfficiencyTableData,
	 * expectedRoute); validateDriverName(actualEfficiencyTableData,
	 * expectedDriver); validateClockInTime(actualEfficiencyTableData,
	 * expectedClockInTime); validateClockOutTime(actualEfficiencyTableData,
	 * expectedClockOutTime);
	 * validateActualDriverHours(actualEfficiencyTableData);
	 * 
	 * }
	 * 
	 * public void validateDetailData(Map<String, List<String>>
	 * actualDetailTableData, String expectedLOB, String expectedRoute, String
	 * expectedDriver, String expectedClockInTime, String expectedClockOutTime)
	 * { validateLOB(actualDetailTableData, expectedLOB);
	 * validateRouteName(actualDetailTableData, expectedRoute);
	 * validateDriverName(actualDetailTableData, expectedDriver);
	 * validatePreRouteTime(); validatePreRouteTime(); validateTonsLoad();
	 * validatePostRouteTime(); validateIdleOccurences(); validateNetidleTime();
	 * validateDowntime(); validateMealTime(); validateActualUnits(); }
	 */

	public void validateMiles(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualEfficiencyTableData) {
		int actualMiles = 0;
		int expectedMiles = 0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().equals("Miles")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualMiles = 0;
						} else {
							actualMiles = Integer.parseInt(entry.getValue().get(i));
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencyTableData.entrySet()) {
				if (entry.getKey().equals("Miles")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedMiles = expectedMiles + 0;
						} else {
							expectedMiles = expectedMiles + Integer.parseInt(entry.getValue().get(i));
						}
					}
				}
			}

			if (actualMiles == expectedMiles) {
				Report.InfoTest(test, "Miles is correct in summary report Actual is : " + actualMiles
						+ " and Expected is : " + expectedMiles);
				Report.PassTest(test, "Miles is as expected in Summary report");
			} else {
				Report.FailTest(test, "Miles is not as expected in summary report Actual is : " + actualMiles
						+ " and Expected is : " + expectedMiles);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Miles is not as expected in summary report Actual is : " + actualMiles
					+ " and Expected is : " + expectedMiles);
		}
	}

	public void validatePreRouteHours(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualDetailTableData) {
		String actualPreRouteHours = null;
		String expectedPreRouteHours = null;
		int totalHours = 0;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Pre-Route")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualPreRouteHours = null;
						} else {
							actualPreRouteHours = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("Pre-Route")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String plannedDriver[] = entry.getValue().get(i).split(":");
							System.out.println("Hours : " + plannedDriver[0]);
							System.out.println("Minutes : " + plannedDriver[1]);
							int hours = Integer.parseInt(plannedDriver[0]);
							int mins = Integer.parseInt(plannedDriver[1]);
							totalHours = totalHours + hours;
							totalMins = totalMins + mins;
						}

					}
				}
			}
			if (totalMins > 60) {
				totalHours = totalHours + totalMins / 60;
				totalMins = totalMins % 60;
				if (totalHours < 10 && totalMins < 10)
					expectedPreRouteHours = "0" + Integer.toString(totalHours) + ":" + "0"
							+ Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedPreRouteHours = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedPreRouteHours = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedPreRouteHours = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				System.out.println("Expected Pre Route Hours : " + expectedPreRouteHours);

			} else {
				if (totalHours == 0 && totalMins > 9)
					expectedPreRouteHours = "00:" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedPreRouteHours = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours == 0 && totalMins < 10)
					expectedPreRouteHours = "00:" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedPreRouteHours = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedPreRouteHours = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				System.out.println("Expected Pre Route Hours : " + expectedPreRouteHours);
			}

			if (actualPreRouteHours.equals(expectedPreRouteHours)) {
				Report.InfoTest(test, "Pre Route Hours is correct in summary report Actual is : " + actualPreRouteHours
						+ " and Expected is : " + expectedPreRouteHours);
				Report.PassTest(test, "Pre Route Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Pre Route Hours is not as expected in summary report Actual is : "
						+ actualPreRouteHours + " and Expected is : " + expectedPreRouteHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Pre Route Hours is not as expected in summary report Actual is : "
					+ actualPreRouteHours + " and Expected is : " + expectedPreRouteHours);
		}
	}

	public void validatePostRouteHours(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualDetailTableData) {
		String actualPostRouteHours = null;
		String expectedPostRouteHours = null;
		int totalHours = 0;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Post-Route")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualPostRouteHours = null;
						} else {
							actualPostRouteHours = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("Post-Route")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!(entry.getValue().get(i).equals("--"))) {
							String plannedDriver[] = entry.getValue().get(i).split(":");
							System.out.println("Hours : " + plannedDriver[0]);
							System.out.println("Minutes : " + plannedDriver[1]);
							int hours = Integer.parseInt(plannedDriver[0]);
							int mins = Integer.parseInt(plannedDriver[1]);
							totalHours = totalHours + hours;
							totalMins = totalMins + mins;
						}

					}
				}
			}
			if (totalMins > 60) {
				totalHours = totalHours + totalMins / 60;
				totalMins = totalMins % 60;
				if (totalHours < 10 && totalMins < 10)
					expectedPostRouteHours = "0" + Integer.toString(totalHours) + ":" + "0"
							+ Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedPostRouteHours = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedPostRouteHours = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedPostRouteHours = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				System.out.println("Expected Post Route Hours : " + expectedPostRouteHours);

			} else {
				if (totalHours == 0 && totalMins > 9)
					expectedPostRouteHours = "00:" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedPostRouteHours = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours == 0 && totalMins < 10)
					expectedPostRouteHours = "00:" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedPostRouteHours = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedPostRouteHours = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				System.out.println("Expected Post Route Hours : " + expectedPostRouteHours);
			}

			if (actualPostRouteHours.equals(expectedPostRouteHours)) {
				Report.InfoTest(test, "Post Route Hours is correct in summary report Actual is : "
						+ actualPostRouteHours + " and Expected is : " + expectedPostRouteHours);
				Report.PassTest(test, "Post Route Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Post Route Hours is not as expected in summary report Actual is : "
						+ actualPostRouteHours + " and Expected is : " + expectedPostRouteHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Post Route Hours is not as expected in summary report Actual is : "
					+ actualPostRouteHours + " and Expected is : " + expectedPostRouteHours);
		}
	}

	public void validateIdleTime(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualDetailTableData) {
		String actualIdleTime = null;
		String expectedIdleTime = null;
		int totalHours = 0;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Idle Time")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualIdleTime = null;
						} else {
							actualIdleTime = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("Net Idle")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!(entry.getValue().get(i).equals("--"))) {
							String plannedDriver[] = entry.getValue().get(i).split(":");
							System.out.println("Hours : " + plannedDriver[0]);
							System.out.println("Minutes : " + plannedDriver[1]);
							int hours = Integer.parseInt(plannedDriver[0]);
							int mins = Integer.parseInt(plannedDriver[1]);
							totalHours = totalHours + hours;
							totalMins = totalMins + mins;
						}

					}
				}
			}
			if (totalMins > 60) {
				totalHours = totalHours + totalMins / 60;
				totalMins = totalMins % 60;
				if (totalHours < 10 && totalMins < 10)
					expectedIdleTime = "0" + Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedIdleTime = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedIdleTime = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedIdleTime = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				System.out.println("Expected Idle Time : " + expectedIdleTime);

			} else {
				if (totalHours == 0 && totalMins > 9)
					expectedIdleTime = "00:" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedIdleTime = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours == 0 && totalMins < 10)
					expectedIdleTime = "00:" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedIdleTime = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedIdleTime = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours == 0 && totalMins == 0)
					expectedIdleTime = "00:00";
				System.out.println("Expected Idle Time : " + expectedIdleTime);
			}
			if (actualIdleTime.equals(expectedIdleTime)) {
				Report.InfoTest(test, "Idle Time is correct in summary report Actual is : " + actualIdleTime
						+ " and Expected is : " + expectedIdleTime);
				Report.PassTest(test, "Idle Time Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Idle Time Hours is not as expected in summary report Actual is : "
						+ actualIdleTime + " and Expected is : " + expectedIdleTime);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Idle Time Hours is not as expected in summary report Actual is : " + actualIdleTime
					+ " and Expected is : " + expectedIdleTime);
		}
	}

	public void validateDownTime(Map<String, List<String>> actualSummaryTableData,
			Map<String, List<String>> actualDetailTableData) {
		String actualDownTime = null;
		String expectedDownTime = null;
		int totalHours = 0;
		int totalMins = 0;
		try {
			for (Entry<String, List<String>> entry : actualSummaryTableData.entrySet()) {
				if (entry.getKey().contains("Downtime")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualDownTime = null;
						} else {
							actualDownTime = entry.getValue().get(i).toString();
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualDetailTableData.entrySet()) {
				if (entry.getKey().contains("Downtime")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!(entry.getValue().get(i).equals("--"))) {
							String plannedDriver[] = entry.getValue().get(i).split(":");
							System.out.println("Hours : " + plannedDriver[0]);
							System.out.println("Minutes : " + plannedDriver[1]);
							int hours = Integer.parseInt(plannedDriver[0]);
							int mins = Integer.parseInt(plannedDriver[1]);
							totalHours = totalHours + hours;
							totalMins = totalMins + mins;
						}

					}
				}
			}
			if (totalMins > 60) {
				totalHours = totalHours + totalMins / 60;
				totalMins = totalMins % 60;
				if (totalHours < 10 && totalMins < 10)
					expectedDownTime = "0" + Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedDownTime = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedDownTime = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedDownTime = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				System.out.println("Expected Down Time : " + expectedDownTime);

			} else {
				if (totalHours == 0 && totalMins > 9)
					expectedDownTime = "00:" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins < 10)
					expectedDownTime = Integer.toString(totalHours) + ":" + "0" + Integer.toString(totalMins);
				else if (totalHours == 0 && totalMins < 10)
					expectedDownTime = "00:" + "0" + Integer.toString(totalMins);
				else if (totalHours < 10 && totalMins > 9)
					expectedDownTime = "0" + Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours > 9 && totalMins > 9)
					expectedDownTime = Integer.toString(totalHours) + ":" + Integer.toString(totalMins);
				else if (totalHours == 0 && totalMins == 0)
					expectedDownTime = "00:00";
				System.out.println("Expected Idle Time : " + expectedDownTime);
			}

			if (actualDownTime.equals(expectedDownTime)) {
				Report.InfoTest(test, "Down Time is correct in summary report Actual is : " + actualDownTime
						+ " and Expected is : " + expectedDownTime);
				Report.PassTest(test, "Down Time Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Down Time Hours is not as expected in summary report Actual is : "
						+ actualDownTime + " and Expected is : " + expectedDownTime);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Down Time Hours is not as expected in summary report Actual is : " + actualDownTime
					+ " and Expected is : " + expectedDownTime);
		}
	}

	public void validateColumns(List<String> actualColumnNames, List<String> expectedColumnNames, String tableName) {
		// TODO Auto-generated method stub
		for (int i = 0; i < expectedColumnNames.size(); i++) {
			if (expectedColumnNames.get(i).equals(expectedColumnNames.get(i))) {
				Report.InfoTest(test, "Column Names is correct in " + tableName + " Actual Column Name is : "
						+ actualColumnNames.get(i) + " and Expected is : " + expectedColumnNames.get(i));
				Report.PassTest(test, "Column Names are as expected in " + tableName);
			} else {
				Report.FailTest(test, "Column Names is not as expected in " + tableName + " Actual are : "
						+ actualColumnNames.get(i) + " and Expected are : " + expectedColumnNames.get(i));
			}
		}

	}

	public void validateStoppedTime(Map<String, Map<String, List<String>>> actualTicketInformationTable,
			Map<String, Map<String, List<String>>> actualServiceInformationTable, String route) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String[] routes = route.split(";");
		List<String> tickets = new ArrayList<>();
		List<Date> actualStoppedTime = new ArrayList<>();
		Date expectedStoppedTime = null;
		try {
			for (Entry<String, Map<String, List<String>>> entry : actualServiceInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().contains("Stopped")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {

								actualStoppedTime.add(dateFormat.parse("00:00"));

							} else {
								actualStoppedTime.add(dateFormat.parse(entry2.getValue().get(i).toString()));
							}
						}
					}
				}
			}
			for (Entry<String, Map<String, List<String>>> entry : actualTicketInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Ticket")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								tickets.add("--");
							} else {
								tickets.add(entry2.getValue().get(i).toString());
							}
						}
					}
				}
			}
			List<Date> RestartTime = getDateDataFromDB(
					"select TO_CHAR(v.RESTARTTICKETSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			List<Date> StopTime = getDateDataFromDB(
					"select TO_CHAR(v.STOPTICKETSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");

			for (int i = 0; i < actualStoppedTime.size(); i++) {
				long diff = RestartTime.get(i).getTime() - StopTime.get(i).getTime();
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				DecimalFormat twodigits = new DecimalFormat("00");
				if (diffHours == 0 && diffMinutes == 0)
					expectedStoppedTime = dateFormat.parse("00:00");
				else
					expectedStoppedTime = dateFormat
							.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));
				System.out.println(actualStoppedTime.get(i));
				System.out.println(expectedStoppedTime);
				if (expectedStoppedTime.equals(actualStoppedTime.get(i))) {
					Report.InfoTest(test,
							"Stopped time is correct  for ticket : " + tickets.get(i) + " with Restart Time : "
									+ dateFormat.format(RestartTime.get(i).getTime()) + " and Stop Time : "
									+ dateFormat.format(StopTime.get(i).getTime()) + " Actual Stopped time : "
									+ dateFormat.format(actualStoppedTime.get(i)));
					Report.PassTest(test, "Stopped time is as expected in Service Information");

				} else {
					Report.FailTest(test,
							"Stopped time is not as expected  for ticket : " + tickets.get(i) + " with Restart Time : "
									+ dateFormat.format(RestartTime.get(i).getTime()) + " and Stop Time : "
									+ dateFormat.format(StopTime.get(i).getTime()) + " Actual Stopped time : "
									+ dateFormat.format(actualStoppedTime.get(i)));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Stopped Hours is not as expected in service information Actual is : "
					+ actualStoppedTime.toString() + " and Expected is : " + expectedStoppedTime.toString());
		}

	}

	public void validateServiceHours(Map<String, Map<String, List<String>>> actualTicketInformationTable,
			Map<String, Map<String, List<String>>> actualServiceInformationTable) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		List<String> actionTypeSet1 = Arrays.asList("E/R", "S/O", "LIV", "DNR", "BTN", "BTY", "BTS", "DNE", "TRP",
				"REL", "DEL");
		List<String> tickets = new ArrayList<>();
		List<String> actionTypeSet2 = Arrays.asList("FFY", "FYN", "FYS");
		List<String> actualActionType = new ArrayList<>();
		List<Date> actualServiceHours = new ArrayList<>();
		Date expectedServiceHrs = null;
		try {
			for (Entry<String, Map<String, List<String>>> entry : actualTicketInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Action")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								actualActionType.add("--");
							} else {
								actualActionType.add(entry2.getValue().get(i).toString());
							}
						}
					}
				}
			}
			for (Entry<String, Map<String, List<String>>> entry : actualTicketInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Ticket")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								tickets.add("--");
							} else {
								tickets.add(entry2.getValue().get(i).toString());
							}
						}
					}
				}
			}
			for (Entry<String, Map<String, List<String>>> entry : actualServiceInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Service (h:m)")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								actualServiceHours.add(dateFormat.parse("00:00"));
							} else {
								actualServiceHours.add(dateFormat.parse(entry2.getValue().get(i).toString()));
							}
						}
					}
				}
			}
			List<Date> arriveCustomerTime = getDateDataFromDB(
					"select TO_CHAR(v.ARRIVECUSTOMERSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			List<Date> departCustomer = getDateDataFromDB(
					"select TO_CHAR(v.DEPARTCUSTOMERSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			List<Date> RestartTime = getDateDataFromDB(
					"select TO_CHAR(v.RESTARTTICKETSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			List<Date> StopTime = getDateDataFromDB(
					"select TO_CHAR(v.STOPTICKETSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			List<Date> startCustomerTime = getDateDataFromDB(
					"select TO_CHAR(v.DISPATCHEDSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			List<Date> completeTime = getDateDataFromDB(
					"select TO_CHAR(v.TICKETCOMPLETEDSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			System.out.println("Actual Action type size : " + actualActionType.size());
			for (int i = 0; i < actualActionType.size(); i++) {
				if (actionTypeSet1.contains(actualActionType.get(i))) {
					if (((StopTime.get(i).getTime() >= arriveCustomerTime.get(i).getTime())
							&& (StopTime.get(i).getTime() <= completeTime.get(i).getTime())
							|| ((RestartTime.get(i).getTime() >= arriveCustomerTime.get(i).getTime())
									&& (RestartTime.get(i).getTime() <= completeTime.get(i).getTime())))) {
						long diff = ((completeTime.get(i).getTime() - arriveCustomerTime.get(i).getTime())
								- (RestartTime.get(i).getTime() - StopTime.get(i).getTime()));
						long diffMinutes = diff / (60 * 1000) % 60;
						long diffHours = diff / (60 * 60 * 1000) % 24;
						DecimalFormat twodigits = new DecimalFormat("00");
						if (diffHours == 0 && diffMinutes == 0)
							expectedServiceHrs = dateFormat.parse("00:00");
						else
							expectedServiceHrs = dateFormat
									.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

						System.out.println(actualServiceHours.get(i));
						System.out.println(expectedServiceHrs);
						if (expectedServiceHrs.equals(actualServiceHours.get(i))) {
							Report.InfoTest(test, "Service Hours is correct for ticket : " + tickets.get(i)
									+ " with Complete Time : " + dateFormat.format(completeTime.get(i).getTime())
									+ ",arrive customer time : "
									+ dateFormat.format(arriveCustomerTime.get(i).getTime()) + " and Stop Time : "
									+ dateFormat.format(StopTime.get(i).getTime()) + " Actual Service Hours : "
									+ dateFormat.format(actualServiceHours.get(i)));
							Report.PassTest(test, "Service Hours is as expected in Service Information");

						} else {
							Report.FailTest(test, "Service hours is not as expected for ticket : " + tickets.get(i)
									+ " with Complete Time : " + dateFormat.format(completeTime.get(i).getTime())
									+ ", Arrive customer time : "
									+ dateFormat.format(arriveCustomerTime.get(i).getTime()) + " and Stop Time : "
									+ dateFormat.format(StopTime.get(i).getTime()) + " Actual Service Hours : "
									+ dateFormat.format(actualServiceHours.get(i)));
						}
					} else if (((StopTime.get(i).getTime() >= startCustomerTime.get(i).getTime())
							&& (StopTime.get(i).getTime() <= arriveCustomerTime.get(i).getTime())
							|| ((RestartTime.get(i).getTime() >= startCustomerTime.get(i).getTime())
									&& (RestartTime.get(i).getTime() <= arriveCustomerTime.get(i).getTime()))
							|| (StopTime.get(i).getTime() == RestartTime.get(i).getTime()))) {
						long diff = (completeTime.get(i).getTime() - arriveCustomerTime.get(i).getTime());
						long diffMinutes = diff / (60 * 1000) % 60;
						long diffHours = diff / (60 * 60 * 1000) % 24;
						DecimalFormat twodigits = new DecimalFormat("00");
						if (diffHours == 0 && diffMinutes == 0)
							expectedServiceHrs = dateFormat.parse("00:00");
						else
							expectedServiceHrs = dateFormat
									.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

						System.out.println(actualServiceHours.get(i));
						System.out.println(expectedServiceHrs);
						if (expectedServiceHrs.equals(actualServiceHours.get(i))) {
							Report.InfoTest(test, "Service Hours is correct for ticket : " + tickets.get(i)
									+ " with Complete Time : " + dateFormat.format(completeTime.get(i).getTime())
									+ ",arrive customer time : "
									+ dateFormat.format(arriveCustomerTime.get(i).getTime()) + " and Stop Time : "
									+ dateFormat.format(StopTime.get(i).getTime()) + " Actual Service Hours : "
									+ dateFormat.format(actualServiceHours.get(i)));
							Report.PassTest(test, "Service Hours is as expected in Service Information");

						} else {
							Report.FailTest(test, "Service hours is not as expected for ticket : " + tickets.get(i)
									+ " with Complete Time : " + dateFormat.format(completeTime.get(i).getTime())
									+ ",arrive customer time : "
									+ dateFormat.format(arriveCustomerTime.get(i).getTime()) + " and Stop Time : "
									+ dateFormat.format(StopTime.get(i).getTime()) + " Actual Service Hours : "
									+ dateFormat.format(actualServiceHours.get(i)));
						}
					} else {
						Report.FailTest(test,
								"Stop and Restart time is not falling in between arrive customer and complete time or not between start and arrive customer time");
					}

				} else if (actionTypeSet2.contains(actualActionType.get(i))) {
					if (StopTime.get(i).equals(dateFormat.parse("00:00"))) {
						Report.FailTest(test, "There is no stop time for the action type : " + actualActionType.get(i));
					} else {
						long diff = ((completeTime.get(i).getTime() - startCustomerTime.get(i).getTime())
								- (RestartTime.get(i).getTime() - StopTime.get(i).getTime()));
						long diffMinutes = diff / (60 * 1000) % 60;
						long diffHours = diff / (60 * 60 * 1000) % 24;
						DecimalFormat twodigits = new DecimalFormat("00");
						if (diffHours == 0 && diffMinutes == 0)
							expectedServiceHrs = dateFormat.parse("00:00");
						else
							expectedServiceHrs = dateFormat
									.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

						System.out.println(actualServiceHours.get(i));
						System.out.println(expectedServiceHrs);
						if (expectedServiceHrs.equals(actualServiceHours.get(i))) {
							Report.InfoTest(test, "Service Hours is correct for ticket : " + tickets.get(i)
									+ " with Complete Time : " + dateFormat.format(completeTime.get(i).getTime())
									+ ",start customer time : " + dateFormat.format(startCustomerTime.get(i).getTime())
									+ " and Stop Time : " + dateFormat.format(StopTime.get(i).getTime())
									+ " Actual Service Hours : " + dateFormat.format(actualServiceHours.get(i)));
							Report.PassTest(test, "Service Hours is as expected in Service Information");

						} else {
							Report.FailTest(test, "Service hours is not as expected for ticket : " + tickets.get(i)
									+ " with Complete Time : " + dateFormat.format(completeTime.get(i).getTime())
									+ ",start customer time : " + dateFormat.format(startCustomerTime.get(i).getTime())
									+ " and Stop Time : " + dateFormat.format(StopTime.get(i).getTime())
									+ " Actual Service Hours : " + dateFormat.format(actualServiceHours.get(i)));
						}

					}

				} else {
					Report.FailTest(test,
							"Action type is not as expected Actual is : " + actualActionType.get(i).toString()
									+ " and Expected is : " + actionTypeSet1.toString() + "or "
									+ actionTypeSet2.toString());
				}
			}

		} catch (Exception e) {
			Report.FailTest(test, "Service Hours is not as expected in service information Actual is : "
					+ actualServiceHours.toString() + " and Expected is : " + expectedServiceHrs.toString());

		}
	}

	public void validateTicketInformationData(Map<String, Map<String, List<String>>> actualTicketInformationTable,
			String expectedTicket) {
		List<String> tickets = new ArrayList<>();
		List<String> expectedTickets = Arrays.asList(expectedTicket.split(";"));
		for (Entry<String, Map<String, List<String>>> entry : actualTicketInformationTable.entrySet()) {
			for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
				if (entry2.getKey().equals("Ticket")) {
					for (int i = 0; i < entry2.getValue().size(); i++) {
						if (entry2.getValue().get(i).equals("--")) {
							tickets.add("--");
						} else {
							tickets.add(entry2.getValue().get(i).toString());
						}
					}
				}
			}
		}
		List<String> expectedCustomerNames = getDataFromDB(
				"select NAME from OCS_ADMIN.TP_CUSTOMER where PKEY in (select OCS_ADMIN.TP_CUSTOMERORDER.FK_CUSTOMER from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
						+ StringUtils.join(tickets, ',') + "))");
		List<String> expectedCustomerIds = getDataFromDB(
				"select (Companyid || '-' || companynumber) as accountNumber from OCS_ADMIN.TP_CUSTOMER where PKEY in (select OCS_ADMIN.TP_CUSTOMERORDER.FK_CUSTOMER from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
						+ StringUtils.join(tickets, ',') + "))");
		List<String> expectedLoadTypes = getDataFromDB(
				"select UNIQUEID from OCS_ADMIN.TP_TICKETLOADTYPE where PKEY in (select OCS_ADMIN.TP_CUSTOMERORDER.FK_TICKETLOADTYPE from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
						+ StringUtils.join(tickets, ',') + "))");
		List<String> expectedDisposalFacilities = getDataFromDB(
				"select unique DSCODE from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
						+ StringUtils.join(tickets, ',') + ")");
		List<String> expectedContainerTypes = getDataFromDB(
				"select distinct CODE from OCS_ADMIN.TN_CUSTOMERSERVICE where fk_customerorder in (select pkey from ocs_admin.tp_customerorder where ticketnumber in ("
						+ StringUtils.join(tickets, ',') + "))");
		validateColumnData("Customer", actualTicketInformationTable, expectedCustomerNames);
		validateColumnData("Account", actualTicketInformationTable, expectedCustomerIds);
		validateColumnData("Ticket", actualTicketInformationTable, expectedTickets);
		validateColumnData("Action", actualTicketInformationTable, expectedLoadTypes);
		validateColumnData("Container Type", actualTicketInformationTable, expectedContainerTypes);
		validateColumnData("Disposal Facility", actualTicketInformationTable, expectedDisposalFacilities);
	}

	public void validateColumnData(String columnName, Map<String, Map<String, List<String>>> actualInformationTable,
			List<String> expectedDataList) {
		List<String> actualData = new ArrayList<>();
		List<String> actualActions = new ArrayList<>();
		try {
			if (columnName.equals("Disposal Facility")) {
				for (Entry<String, Map<String, List<String>>> entry : actualInformationTable.entrySet()) {
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals(columnName)) {
							for (int i = 0; i < entry2.getValue().size(); i++) {
								if (entry2.getValue().get(i).equals("--")) {
									actualData.add("--");
								} else {
									actualData.add(entry2.getValue().get(i).toString());
								}
							}
						}
					}
				}

				for (Entry<String, Map<String, List<String>>> entry : actualInformationTable.entrySet()) {
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals("Action")) {
							for (int i = 0; i < entry2.getValue().size(); i++) {
								if (entry2.getValue().get(i).equals("--")) {
									actualActions.add("--");
								} else {
									actualActions.add(entry2.getValue().get(i).toString());
								}
							}
						}
					}
				}

				for (int i = 0; i < actualData.size(); i++) {
					if (expectedDataList.contains(actualData.get(i))) {
						Report.InfoTest(test, columnName + " is correct in Accordion report Actual is : "
								+ actualData.get(i) + " and Expected is : " + expectedDataList.toString());
						Report.PassTest(test, columnName + " is as expected in Accordion report");
					} else if (actualData.get(i).equals("--")) {
						if (GlobalExpectedColumns.ticketTypeWithoutDisposal.contains(actualActions.get(i))) {
							Report.InfoTest(test, columnName + " is missing in Accordion report Actual is : "
									+ actualData.get(i) + " and Expected is : " + expectedDataList.toString());
							Report.PassTest(test,
									"Disposal Facility not required for load type : " + actualActions.get(i));
						} else {
							Report.InfoTest(test, columnName + " is missing in Accordion report Actual is : "
									+ actualData.get(i) + " and Expected is : " + expectedDataList.toString());
						}
					} else {
						Report.FailTest(test, columnName + " is not as expected in Accordion report Actual is : "
								+ actualData.get(i) + " and Expected is : " + expectedDataList.toString());

					}
				}
			} else {
				for (Entry<String, Map<String, List<String>>> entry : actualInformationTable.entrySet()) {
					for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
						if (entry2.getKey().equals(columnName)) {
							for (int i = 0; i < entry2.getValue().size(); i++) {
								if (entry2.getValue().get(i).equals("--")) {
									actualData.add("--");
								} else {
									actualData.add(entry2.getValue().get(i).toString());
								}
							}
						}
					}
				}

				for (int i = 0; i < actualData.size(); i++) {
					if (expectedDataList.contains(actualData.get(i))) {
						Report.InfoTest(test, columnName + " is correct in Accordion report Actual is : "
								+ actualData.get(i) + " and Expected is : " + expectedDataList.toString());
						Report.PassTest(test, columnName + " is as expected in Accordion report");
					} else if (actualData.get(i).equals("--")) {

						Report.InfoTest(test, columnName + " is missing in Accordion report Actual is : "
								+ actualData.get(i) + " and Expected is : " + expectedDataList.toString());
					} else {
						Report.FailTest(test, columnName + " is not as expected in Accordion report Actual is : "
								+ actualData.get(i) + " and Expected is : " + expectedDataList.toString());

					}
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, columnName + " is not as expected in Accordion report Actual is : "
					+ actualData.toString() + " and Expected is : " + expectedDataList.toString());
		}
	}

	public List<Date> getDateDataFromDB(String sqlQuery) {
		List<String> expectedData = new ArrayList<>();
		List<Date> expectedDateData = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
		try {
			expectedData = DB.getData(sqlQuery);
			for (String dateString : expectedData) {
				try {
					if (dateString.equalsIgnoreCase("null"))
						expectedDateData.add(simpleDateFormat.parse("00:00"));
					else
						expectedDateData.add(simpleDateFormat.parse(dateString));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Print the time result retrieved from database
		for (int i = 0; i < expectedDateData.size(); i++) {
			System.out.println("Expected Data from DB : " + simpleDateFormat.format(expectedDateData.get(i)));
		}
		return expectedDateData;
	}

	public List<String> getDataFromDB(String sqlQuery) {
		/*
		 * @HELP
		 * 
		 * @class: RollOffFlashReport
		 * 
		 * @method: getDataFromDB()
		 * 
		 * @parameter: String sqlQuery
		 * 
		 * @notes: Execute the sql query and select the required data of the
		 * column.
		 * 
		 * @returns: Retruns the column data in a List<String>
		 * 
		 * @END
		 */
		List<String> expectedDataList = new ArrayList<>();
		List<String> expectedData = new ArrayList<>();

		try {
			expectedDataList = DB.getData(sqlQuery);
			System.out.println("Expected Data from DB : " + expectedDataList.toString());
			for (String data : expectedDataList) {

				if (data.equals("null"))
					expectedData.add("--");
				else
					expectedData.add(data);
			}

		} catch (ClassNotFoundException e) {
			LogClass.error(e.getMessage());
		} catch (SQLException e) {
			LogClass.error(e.getMessage());
		}
		return expectedData;
	}

	public void ClickAccordianLinkOnTable(String Type) throws IOException {

		if (Type.equalsIgnoreCase("Driver")) {
			try {
				Thread.sleep(2000);
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				FirstRowDriverLink.click();
				System.out.println("First Row Driver was clicked");
				Util.switchToDefaultWindow();
			} catch (Exception e) {
				Report.FailTest(test, e.getMessage());
				Report.FailTest(test, "Driver Link was not clicked");
			}
		}

		else if (Type.equalsIgnoreCase("Route")) {
			try {
				Thread.sleep(2000);
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				FirstRowRouteLink.click();
				System.out.println("First Row Route was clicked");
				Util.switchToDefaultWindow();
			} catch (Exception e) {
				Report.FailTest(test, "Route Link was not clicked");
			}

		}
	}

	public void ClickOnPDFLinkInSubView(String Type) throws IOException {

		try
		{
		Thread.sleep(2000);
		if (Type.equals("Driver")) {
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer,subFlashDetailTravel_Row1");
			Util.ElementExist(driver, PDFOptionLink);
			PDFOptionLink.click();
		} else if (Type.equals("Route")) {
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer,subFlashDetail_Row1");
			Util.ElementExist(driver, PDFOptionLink);
			PDFOptionLink.click();
		}
		}
		catch(Exception e)
		{
			LogClass.error(e.getMessage());
			Report.FailTest(test, e.getMessage());
			Report.FailTest(test, "Not able to click on Export to PDF icon");
		}

		
		Util.switchToDefaultWindow();

	}

	public void verifyPDFOpensInNewTab(String ResultText) {
		
		try {
			Thread.sleep(1000);
			String url = Util.switchBrowserTabs();
			if (url.contains("RO_Driver_Detail.pdf")) {
				Report.PassTest(test, ResultText);
			} else
				Report.FailTest(test, "PDF is not getting displayed for RO Driver from in a new tab");
		} catch (Exception e) {
			System.out.println("PDF was not getting displayed");
			Report.FailTest(test, e.getMessage());
		}
	}

	public void validateLegendInGraph() throws IOException, InterruptedException {

		Thread.sleep(5000);
		String[] AllLegends = { "Actual", "Plan", "Target" };

		int noOfLegends = driver
				.findElements(By.xpath(".//*[@class='highcharts-legend']/*/*/*[@class='highcharts-legend-item']/*[3]"))
				.size();
		System.out.println("There are " + noOfLegends + " Legends in the graph");

		for (int i = 1; i <= noOfLegends; i++) {
			String LegendVal = driver
					.findElement(By.xpath(
							".//*[@class='highcharts-legend']/*/*/*[@class='highcharts-legend-item'][" + i + "]/*[3]"))
					.getText();
			if (LegendVal.equals(AllLegends[i - 1])) {
				Report.PassTest(test, AllLegends[i - 1] + " is getting displayed in the Graph Legend");
			} else
				Report.FailTest(test, AllLegends[i - 1] + " is not getting displayed in the Graph Legend");

			// String Beforeclick =
			// driver.findElement(By.xpath(".//*[@class='highcharts-legend']/*/*/*[@class='highcharts-legend-item']["+i+"]/*[3]")).getAttribute("style");
			// System.out.println(Beforeclick);

			driver.findElement(
					By.xpath(".//*[@class='highcharts-legend']/*/*/*[@class='highcharts-legend-item'][" + i + "]/*[3]"))
					.click();
			Thread.sleep(5000);

			String AfterClick = driver
					.findElement(By.xpath(
							".//*[@class='highcharts-legend']/*/*/*[@class='highcharts-legend-item'][" + i + "]/*[3]"))
					.getAttribute("style");
			System.out.println(AfterClick);

			if (driver
					.findElement(By.xpath(
							".//*[@class='highcharts-legend']/*/*/*[@class='highcharts-legend-item'][" + i + "]/*[3]"))
					.getAttribute("style").contains("rgb(51, 51, 51)")) {
				Report.FailTest(test, AllLegends[i] + " Legend Item is not clickable");
			} else
				Report.PassTest(test, AllLegends[i] + " Legend is getting displayed and clickable");

			driver.findElement(
					By.xpath(".//*[@class='highcharts-legend']/*/*/*[@class='highcharts-legend-item'][" + i + "]/*[3]"))
					.click();
			Thread.sleep(1000);
		}

	}

	public void validateAxisInGraph(String FromDate, String ToDate) throws IOException, ParseException {

		// Validate the Y Axis is having Units Per Hour
		if (GraphYAxis.getText().equals("Units Per Hour")) {
			Report.PassTest(test, "Units per Hour is getting displayed in the Y- Axis for the Graph");
		} else
			Report.FailTest(test, "Units per Hour is not getting displayed in the Y-Axis of the Graph ");

		// Validate the X Axis is having Date range
		int noOfDatesXAxis = driver
				.findElements(By
						.xpath(".//*[@class='highcharts-container']/div[@class='highcharts-axis-labels highcharts-xaxis-labels']/span"))
				.size();
		String firstDateXAxis = driver
				.findElement(By
						.xpath(".//*[@class='highcharts-container']/div[@class='highcharts-axis-labels highcharts-xaxis-labels']/span[1]"))
				.getText();
		String lastDateXAxis = driver.findElement(By
				.xpath(".//*[@class='highcharts-container']/div[@class='highcharts-axis-labels highcharts-xaxis-labels']/span["
						+ (noOfDatesXAxis) + "]"))
				.getText();

		// String formatFirstDateXAxis

		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yy");
		Date FromDateFormat = format1.parse(FromDate);
		Date ToDateForamt = format1.parse(ToDate);
		Date firstDateXAxisFormat = format1.parse(firstDateXAxis);
		Date lastDateXAxisFormat = format1.parse(lastDateXAxis);

		if ((firstDateXAxisFormat.getTime() >= FromDateFormat.getTime())
				&& (lastDateXAxisFormat.getTime() <= ToDateForamt.getTime()))

		{
			Report.PassTest(test,
					firstDateXAxis + " and " + lastDateXAxis + " are falling under" + FromDate + " and " + FromDate);
		} else
			Report.FailTest(test, firstDateXAxis + " and " + lastDateXAxis + " are not falling under" + FromDate
					+ " and " + FromDate);

	}

	public void validateTheGraphPoints() throws IOException {

		String actualEff = null;
		String planEff = null;
		String targetEff = null;

		Util.HoverAndGetText(driver, GraphPointerActual);

		String HeaderDate = GraphPointerActualOverLayHeader.getText();
		String ActualOverLay = GraphPointerActualOverLayActual.getText();
		String PlanOverLay = GraphPointerActualOverLayPlan.getText();
		String TargetOverLay = GraphPointerActualOverLayTarget.getText();

		if (HeaderDate != null) {
			Report.PassTest(test, HeaderDate + " : Date is getting Displayed in the Graph OverLay");
		} else
			Report.FailTest(test, "Date is not getting displayed in the Graph OverLay");

		Map<String, List<String>> SummaryTableData = getActualSummaryTableData();

		for (Entry<String, List<String>> entry : SummaryTableData.entrySet()) {
			if (entry.getKey().equals("Actual Eff")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						actualEff = "";
					} else {
						actualEff = entry.getValue().get(i);
					}
				}
			}
		}
		for (Entry<String, List<String>> entry : SummaryTableData.entrySet()) {
			if (entry.getKey().equals("Plan Eff")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						planEff = "";
					} else {
						planEff = entry.getValue().get(i);
					}
				}
			}
		}
		for (Entry<String, List<String>> entry : SummaryTableData.entrySet()) {
			if (entry.getKey().equals("Target Eff")) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals("--")) {
						targetEff = "";
					} else {
						targetEff = entry.getValue().get(i);
					}
				}
			}
		}

		if (ActualOverLay.equals(actualEff)) {
			Report.PassTest(test, ActualOverLay + " is getting displayed in the Summary Section and the Graph Overlay");
		} else
			Report.FailTest(test,
					ActualOverLay + " is getting displayed on the Overlay which is not same as in the Summary Section");

		if (PlanOverLay.equals(planEff)) {
			Report.PassTest(test, PlanOverLay + " is getting displayed in the Summary Section and the Graph Overlay");
		} else
			Report.FailTest(test,
					PlanOverLay + " is getting displayed on the Overlay which is not same as in the Summary Section");

		if (TargetOverLay.equals(targetEff)) {
			Report.PassTest(test, TargetOverLay + " is getting displayed in the Summary Section and the Graph Overlay");
		} else
			Report.FailTest(test,
					TargetOverLay + " is getting displayed on the Overlay which is not same as in the Summary Section");

	}

	public String getRollOffVarianceMinPerHaul(Map<String, List<String>> actualEfficiencyTable) {
		String actualAllowanceMinPerLoad = null;
		try {
			for (Entry<String, List<String>> entry : actualEfficiencyTable.entrySet()) {
				if (entry.getKey().equals("Allowance Min/Load")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {

							actualAllowanceMinPerLoad = "00:00";

						} else {
							actualAllowanceMinPerLoad = entry.getValue().get(i).toString();
						}
					}
				}
			}
			return actualAllowanceMinPerLoad;

		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Roll Off variance per min haul");
		}
		return actualAllowanceMinPerLoad;
	}

	public void validateTravelInformationData(Map<String, List<String>> actualEfficiencyTable,
			Map<String, Map<String, List<String>>> actualTravelInformationTable,
			Map<String, Map<String, List<String>>> actualTicketInformationTable) {
		List<Date> actualTravelTime = new ArrayList<>();
		List<Date> actualTraveleRLTime = new ArrayList<>();
		List<Date> actualTravelVariance = new ArrayList<>();
		List<Date> arriveDisposalTime = new ArrayList<>();
		List<Date> departDisposalTime = new ArrayList<>();
		List<Date> customerStartTime = new ArrayList<>();
		List<Date> restartTime = new ArrayList<>();
		List<Date> stopTime = new ArrayList<>();
		List<Date> arriveCustomerTime = new ArrayList<>();
		List<Date> departCustomer = new ArrayList<>();
		List<Date> completeTime = new ArrayList<>();
		List<String> tickets = new ArrayList<>();
		List<String> ticketType = new ArrayList<>();
		Date expectedTravelTime = null;
		Date expectedTraveleRLTime = null;
		Date expectedTravelVarianceTime = null;
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

		try {

			for (Entry<String, List<String>> entry : actualEfficiencyTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualEfficiencyTable.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			for (Entry<String, Map<String, List<String>>> entry : actualTravelInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().contains("Actual")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								actualTravelTime.add(null);
							} else {
								actualTravelTime.add(dateFormat.parse(entry2.getValue().get(i).toString()));
							}
						}
					}
				}
			}

			for (Entry<String, Map<String, List<String>>> entry : actualTravelInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().contains("eRL")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								actualTraveleRLTime.add(null);
							} else {
								actualTraveleRLTime.add(dateFormat.parse(entry2.getValue().get(i).toString()));
							}
						}
					}
				}
			}

			for (Entry<String, Map<String, List<String>>> entry : actualTravelInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().contains("Var")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								actualTravelVariance.add(null);
							} else {
								actualTravelVariance.add(dateFormat.parse(entry2.getValue().get(i).toString()));
							}
						}
					}
				}
			}

			for (Entry<String, Map<String, List<String>>> entry : actualTicketInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Ticket")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								tickets.add("--");
							} else {
								tickets.add(entry2.getValue().get(i).toString());
							}
						}
					}
				}
			}

			for (Entry<String, Map<String, List<String>>> entry : actualTicketInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Action")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								ticketType.add("--");
							} else {
								ticketType.add(entry2.getValue().get(i).toString());
							}
						}
					}
				}
			}
			arriveDisposalTime = Util.getDateDataFromDB(
					"select TO_CHAR(v.ARRIVELANDFILLSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");

			departDisposalTime = Util.getDateDataFromDB(
					"select TO_CHAR(v.FINISHEDATLANDFILLSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");

			customerStartTime = Util.getDateDataFromDB(
					"select TO_CHAR(v.DISPATCHEDSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			restartTime = getDateDataFromDB(
					"select TO_CHAR(v.RESTARTTICKETSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			stopTime = getDateDataFromDB(
					"select TO_CHAR(v.STOPTICKETSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			arriveCustomerTime = getDateDataFromDB(
					"select TO_CHAR(v.ARRIVECUSTOMERSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			departCustomer = getDateDataFromDB(
					"select TO_CHAR(v.DEPARTCUSTOMERSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			completeTime = getDateDataFromDB(
					"select TO_CHAR(v.TICKETCOMPLETEDSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");

			for (int i = 0; i < route.size(); i++) {

				for (int j = 0; j < tickets.size(); j++) {

					if (ticketType.contains("FFY") || ticketType.contains("FYN") || ticketType.contains("FYS")) {

						// need to check logic for no stopped time
						if (stopTime.get(j).getTime() == restartTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenarion : No Stopped Time for the Ticket");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							if (!(actualTravelTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Travel Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Travel Time : -- ");
							} else {
								diff = (arriveDisposalTime.get(j).getTime() - customerStartTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelTime = dateFormat.parse("00:00");
								else
									expectedTravelTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								//if (actualTravelTime.get(j).getTime() == expectedTravelTime.getTime()) {
								if(Util.compareTime(actualTravelTime.get(j), expectedTravelTime)){
									Report.InfoTest(test,
											"Travel Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
									Report.PassTest(test, "Travel Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Travel Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
								}
							}
							if (!(actualTraveleRLTime.get(j) != null && actualTravelVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Travel Variance : -- ");
							} else {
								diff = (actualTravelTime.get(j).getTime() - actualTraveleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelVarianceTime = dateFormat.parse("00:00");
								else
									expectedTravelVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualTravelVariance.get(j).getTime() == expectedTravelVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Travel Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
									Report.PassTest(test,
											"Travel Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Travel Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
								}

							}

						}

						else if (arriveDisposalTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departDisposalTime.get(j).getTime()) {
							// "Travel Actual (h:m)" time should be equal to
							// [(Arrive Landfill - Start)]
							Report.InfoTest(test,
									"Scenarion : Stopped time is in between Arrive Landfill and Depart Landfill");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							if (!(actualTravelTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Travel Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Travel Time : -- ");
							} else {
								diff = (arriveDisposalTime.get(j).getTime() - customerStartTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelTime = dateFormat.parse("00:00");
								else
									expectedTravelTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualTravelTime.get(j), expectedTravelTime)){
									Report.InfoTest(test,
											"Travel Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
									Report.PassTest(test, "Travel Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Travel Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
								}
							}
							if (!(actualTraveleRLTime.get(j) != null && actualTravelVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Travel Variance : -- ");
							} else {
								diff = (actualTravelTime.get(j).getTime() - actualTraveleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelVarianceTime = dateFormat.parse("00:00");
								else
									expectedTravelVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualTravelVariance.get(j).getTime() == expectedTravelVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Travel Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
									Report.PassTest(test,
											"Travel Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Travel Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
								}

							}

						}

						else if (departDisposalTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= completeTime.get(j).getTime())

						{

							Report.InfoTest(test,
									"Scenario : Stopped time is in between Depart Landfill and Complete Time");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							if (!(actualTravelTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Travel Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Travel Time : -- ");
							} else {
								diff = (arriveDisposalTime.get(j).getTime() - customerStartTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelTime = dateFormat.parse("00:00");
								else
									expectedTravelTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualTravelTime.get(j), expectedTravelTime)){
									Report.InfoTest(test,
											"Travel Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
									Report.PassTest(test, "Travel Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Travel Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
								}
							}

							if (!(actualTraveleRLTime.get(j) != null && actualTravelVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Travel Variance : -- ");
							} else {
								diff = (actualTravelTime.get(j).getTime() - actualTraveleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelVarianceTime = dateFormat.parse("00:00");
								else
									expectedTravelVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualTravelVariance.get(j).getTime() == expectedTravelVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Travel Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
									Report.PassTest(test,
											"Travel Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Travel Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
								}

							}

						}

						else if (customerStartTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= arriveDisposalTime.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenarion : Stopped time is in between Start Customer and Arrive Landfill");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							if (!(actualTravelTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Travel Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Travel Time : -- ");
							} else {
								diff = (arriveDisposalTime.get(j).getTime() - customerStartTime.get(j).getTime())
										- (restartTime.get(j).getTime() - stopTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelTime = dateFormat.parse("00:00");
								else
									expectedTravelTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualTravelTime.get(j), expectedTravelTime)){
									Report.InfoTest(test,
											"Travel Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
									Report.PassTest(test, "Travel Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Travel Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
								}
							}
							if (!(actualTraveleRLTime.get(j) != null && actualTravelVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Travel Variance : -- ");
							} else {
								diff = (actualTravelTime.get(j).getTime() - actualTraveleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelVarianceTime = dateFormat.parse("00:00");
								else
									expectedTravelVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualTravelVariance.get(j).getTime() == expectedTravelVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Travel Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
									Report.PassTest(test,
											"Travel Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Travel Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
								}

							}

						}

						else {

							Report.FailTest(test, "For Route : " + route.get(i) + " and for Ticket " + tickets.get(j)
									+ " stopped time is not between Arrive Landfill and depart landfill or between Depart landfill and complete customer or between Start customer and arrive landfill");
						}

					} else if (ticketType.contains("E/R") || ticketType.contains("S/O") || ticketType.contains("DNR")
							|| ticketType.contains("LIV")) {

						if (stopTime.get(j).getTime() == restartTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : No Stopped Time for the Ticket");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Travel Actual (h:m)" time should be equal to
							 * [(Travel to Customer 1)+ {2(Travel To
							 * Disposal)}].
							 * 
							 * Travel to Customer 1 = Arrive Customer - Start.
							 * 
							 * Travel To Disposal=Arrive Landfill - Depart
							 * Customer.
							 */

							if (!(actualTravelTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Travel Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Travel Time : -- ");
							} else {
								diff = ((arriveCustomerTime.get(j).getTime() - customerStartTime.get(j).getTime())
										- (restartTime.get(j).getTime() - stopTime.get(j).getTime()))
										+ (arriveDisposalTime.get(j).getTime() - departCustomer.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelTime = dateFormat.parse("00:00");
								else
									expectedTravelTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

							
								if(Util.compareTime(actualTravelTime.get(j), expectedTravelTime)){
									Report.InfoTest(test,
											"Travel Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
									Report.PassTest(test, "Travel Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Travel Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
								}
							}
							if (!(actualTraveleRLTime.get(j) != null && actualTravelVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Travel Variance : -- ");
							} else {
								diff = (actualTravelTime.get(j).getTime() - actualTraveleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelVarianceTime = dateFormat.parse("00:00");
								else
									expectedTravelVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualTravelVariance.get(j).getTime() == expectedTravelVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Travel Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
									Report.PassTest(test,
											"Travel Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Travel Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
								}

							}
						} else if (arriveCustomerTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departCustomer.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Arrive Customer and Depart Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Travel Actual (h:m)" time should be equal to
							 * [(Travel to Customer 1)+ {2(Travel To
							 * Disposal)}].
							 * 
							 * Travel to Customer 1 = Arrive Customer - Start.
							 * Travel To Disposal=Arrive Landfill - Depart
							 * Customer.
							 */

							if (!(actualTravelTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Travel Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Travel Time : -- ");
							} else {
								diff = (arriveCustomerTime.get(j).getTime() - customerStartTime.get(j).getTime())
										+ (arriveDisposalTime.get(j).getTime() - departCustomer.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelTime = dateFormat.parse("00:00");
								else
									expectedTravelTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualTravelTime.get(j), expectedTravelTime)){
									Report.InfoTest(test,
											"Travel Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
									Report.PassTest(test, "Travel Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Travel Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
								}
							}
							if (!(actualTraveleRLTime.get(j) != null && actualTravelVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Travel Variance : -- ");
							} else {
								diff = (actualTravelTime.get(j).getTime() - actualTraveleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelVarianceTime = dateFormat.parse("00:00");
								else
									expectedTravelVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualTravelVariance.get(j).getTime() == expectedTravelVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Travel Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
									Report.PassTest(test,
											"Travel Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Travel Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
								}

							}

						} else if (arriveDisposalTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departDisposalTime.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Arrive Landfill and Depart Landfill");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Travel Actual (h:m)" time should be equal to
							 * [(Travel to Customer 1)+ {2(Travel To
							 * Disposal)}].
							 * 
							 * Travel to Customer 1 = Arrive Customer - Start.
							 * Travel To Disposal=Arrive Landfill - Depart
							 * Customer.
							 */
							if (!(actualTravelTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Travel Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Travel Time : -- ");
							} else {
								diff = (arriveCustomerTime.get(j).getTime() - customerStartTime.get(j).getTime())
										+ (arriveDisposalTime.get(j).getTime() - departCustomer.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelTime = dateFormat.parse("00:00");
								else
									expectedTravelTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualTravelTime.get(j), expectedTravelTime)){
									Report.InfoTest(test,
											"Travel Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
									Report.PassTest(test, "Travel Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Travel Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
								}
							}
							if (!(actualTraveleRLTime.get(j) != null && actualTravelVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Travel Variance : -- ");
							} else {
								diff = (actualTravelTime.get(j).getTime() - actualTraveleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelVarianceTime = dateFormat.parse("00:00");
								else
									expectedTravelVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualTravelVariance.get(j).getTime() == expectedTravelVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Travel Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
									Report.PassTest(test,
											"Travel Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Travel Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
								}

							}
						} else if (departCustomer.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= arriveDisposalTime.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Depart Customer and Arrive Landfill");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Travel Actual (h:m)" time should be equal to
							 * [(Travel to Customer 1)+ {2(Travel To Disposal)-
							 * Stopped Time}].
							 * 
							 * Travel to Customer 1 = Arrive Customer - Start.
							 * Stopped Time = Restart - Stop. Travel To
							 * Disposal=Arrive Landfill - Depart Customer.
							 */

							if (!(actualTravelTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Travel Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Travel Time : -- ");
							} else {
								diff = (arriveCustomerTime.get(j).getTime() - customerStartTime.get(j).getTime())
										+ ((arriveDisposalTime.get(j).getTime() - departCustomer.get(j).getTime())
												- (restartTime.get(j).getTime() - stopTime.get(j).getTime()));
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelTime = dateFormat.parse("00:00");
								else
									expectedTravelTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualTravelTime.get(j), expectedTravelTime)){
									Report.InfoTest(test,
											"Travel Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
									Report.PassTest(test, "Travel Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Travel Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
								}
							}
							if (!(actualTraveleRLTime.get(j) != null && actualTravelVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Travel Variance : -- ");
							} else {
								diff = (actualTravelTime.get(j).getTime() - actualTraveleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelVarianceTime = dateFormat.parse("00:00");
								else
									expectedTravelVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualTravelVariance.get(j).getTime() == expectedTravelVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Travel Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
									Report.PassTest(test,
											"Travel Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Travel Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
								}

							}
						} else if (departDisposalTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= completeTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : Stopped time is between Depart Landfill and Complete");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Travel Actual (h:m)" time should be equal to
							 * [(Travel to Customer 1)+ {2(Travel To
							 * Disposal)}].
							 * 
							 * Travel to Customer 1 = Arrive Customer - Start.
							 * Travel To Disposal=Arrive Landfill - Depart
							 * Customer.
							 */

							if (!(actualTravelTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Travel Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Travel Time : -- ");
							} else {
								diff = (arriveCustomerTime.get(j).getTime() - customerStartTime.get(j).getTime())
										+ (arriveDisposalTime.get(j).getTime() - departCustomer.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelTime = dateFormat.parse("00:00");
								else
									expectedTravelTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualTravelTime.get(j), expectedTravelTime)){
									Report.InfoTest(test,
											"Travel Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
									Report.PassTest(test, "Travel Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Travel Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
								}
							}
							if (!(actualTraveleRLTime.get(j) != null && actualTravelVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Travel Variance : -- ");
							} else {
								diff = (actualTravelTime.get(j).getTime() - actualTraveleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelVarianceTime = dateFormat.parse("00:00");
								else
									expectedTravelVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualTravelVariance.get(j).getTime() == expectedTravelVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Travel Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
									Report.PassTest(test,
											"Travel Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Travel Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
								}

							}
						} else if (customerStartTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= arriveCustomerTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : Stopped time is between Start and Arrive Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Travel Actual (h:m)" time should be equal to
							 * [(Travel to Customer 1- Stopped Time)+ {2(Travel
							 * To Disposal)}].
							 * 
							 * Travel to Customer 1 = Arrive Customer - Start.
							 * Stopped Time = Restart - Stop. Travel To
							 * Disposal=Arrive Landfill - Depart Customer.
							 */

							if (!(actualTravelTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Travel Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Travel Time : -- ");
							} else {
								diff = ((arriveCustomerTime.get(j).getTime() - customerStartTime.get(j).getTime())
										- (restartTime.get(j).getTime() - stopTime.get(j).getTime()))
										+ (arriveDisposalTime.get(j).getTime() - departCustomer.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelTime = dateFormat.parse("00:00");
								else
									expectedTravelTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualTravelTime.get(j), expectedTravelTime)){
									Report.InfoTest(test,
											"Travel Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
									Report.PassTest(test, "Travel Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Travel Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
								}
							}
							if (!(actualTraveleRLTime.get(j) != null && actualTravelVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Travel Variance : -- ");
							} else {
								diff = (actualTravelTime.get(j).getTime() - actualTraveleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelVarianceTime = dateFormat.parse("00:00");
								else
									expectedTravelVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualTravelVariance.get(j).getTime() == expectedTravelVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Travel Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
									Report.PassTest(test,
											"Travel Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Travel Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
								}

							}

						}

					}

					else if (ticketType.contains("BTN") || ticketType.contains("BTY") || ticketType.contains("BTS")
							|| ticketType.contains("DNE")) {

						if (restartTime.get(j).getTime() == stopTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : No Stopped Time for the Ticket");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Travel Actual (h:m)" time should be equal to
							 * [(Travel to Customer) + Travel from Customer].
							 * 
							 * Travel to Customer = Arrive Customer - Start.
							 * Travel from Customer = Complete - Depart
							 * Customer.
							 */

							if (!(actualTravelTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Travel Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Travel Time : -- ");
							} else {
								diff = (arriveCustomerTime.get(j).getTime() - customerStartTime.get(j).getTime())
										+ (completeTime.get(j).getTime() - departCustomer.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelTime = dateFormat.parse("00:00");
								else
									expectedTravelTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualTravelTime.get(j), expectedTravelTime)){
									Report.InfoTest(test,
											"Travel Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
									Report.PassTest(test, "Travel Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Travel Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
								}
							}
							if (!(actualTraveleRLTime.get(j) != null && actualTravelVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Travel Variance : -- ");
							} else {
								diff = (actualTravelTime.get(j).getTime() - actualTraveleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelVarianceTime = dateFormat.parse("00:00");
								else
									expectedTravelVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualTravelVariance.get(j).getTime() == expectedTravelVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Travel Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
									Report.PassTest(test,
											"Travel Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Travel Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
								}

							}
						}

						else if (arriveCustomerTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departCustomer.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Arrive Customer and Depart Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Travel Actual (h:m)" time should be equal to
							 * [(Travel to Customer) + Travel from Customer].
							 * 
							 * Travel to Customer = Arrive Customer - Start.
							 * Travel from Customer = Complete - Depart
							 * Customer.
							 */
							if (!(actualTravelTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Travel Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Travel Time : -- ");
							} else {
								diff = (arriveCustomerTime.get(j).getTime() - customerStartTime.get(j).getTime())
										+ (completeTime.get(j).getTime() - departCustomer.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelTime = dateFormat.parse("00:00");
								else
									expectedTravelTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualTravelTime.get(j), expectedTravelTime)){
									Report.InfoTest(test,
											"Travel Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
									Report.PassTest(test, "Travel Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Travel Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
								}
							}
							if (!(actualTraveleRLTime.get(j) != null && actualTravelVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Travel Variance : -- ");
							} else {
								diff = (actualTravelTime.get(j).getTime() - actualTraveleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelVarianceTime = dateFormat.parse("00:00");
								else
									expectedTravelVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualTravelVariance.get(j).getTime() == expectedTravelVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Travel Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
									Report.PassTest(test,
											"Travel Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Travel Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
								}

							}

						}

						else if (completeTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departCustomer.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : Stopped time is between Complete and Depart Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Travel Actual (h:m)" time should be equal to
							 * [(Travel to Customer) + (Travel from
							 * Customer-Stopped Time)].
							 * 
							 * Travel to Customer = Arrive Customer - Start.
							 * Travel from Customer = Complete - Depart
							 * Customer. Stopped Time = Restart - Stop.
							 */
							if (!(actualTravelTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Travel Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Travel Time : -- ");
							} else {
								diff = (arriveCustomerTime.get(j).getTime() - customerStartTime.get(j).getTime())
										+ ((completeTime.get(j).getTime() - departCustomer.get(j).getTime())
												- (restartTime.get(j).getTime() - stopTime.get(j).getTime()));
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelTime = dateFormat.parse("00:00");
								else
									expectedTravelTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualTravelTime.get(j), expectedTravelTime)){
									Report.InfoTest(test,
											"Travel Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
									Report.PassTest(test, "Travel Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Travel Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
								}
							}
							if (!(actualTraveleRLTime.get(j) != null && actualTravelVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Travel Variance : -- ");
							} else {
								diff = (actualTravelTime.get(j).getTime() - actualTraveleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelVarianceTime = dateFormat.parse("00:00");
								else
									expectedTravelVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualTravelVariance.get(j).getTime() == expectedTravelVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Travel Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
									Report.PassTest(test,
											"Travel Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Travel Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
								}

							}
						}

						else if (customerStartTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= arriveCustomerTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : Stopped time is between Start and Arrive Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Travel Actual (h:m)" time should be equal to
							 * [(Travel to Customer-Stopped Time) + Travel from
							 * Customer].
							 * 
							 * Travel to Customer = Arrive Customer - Start.
							 * Travel from Customer = Complete - Depart
							 * Customer. Stopped Time = Restart - Stop.
							 */

							if (!(actualTravelTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Travel Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Travel Time : -- ");
							} else {
								diff = ((arriveCustomerTime.get(j).getTime() - customerStartTime.get(j).getTime())
										- (restartTime.get(j).getTime() - stopTime.get(j).getTime()))
										+ (completeTime.get(j).getTime() - departCustomer.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelTime = dateFormat.parse("00:00");
								else
									expectedTravelTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualTravelTime.get(j), expectedTravelTime)){
									Report.InfoTest(test,
											"Travel Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
									Report.PassTest(test, "Travel Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Travel Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
								}
							}
							if (!(actualTraveleRLTime.get(j) != null && actualTravelVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Travel Variance : -- ");
							} else {
								diff = (actualTravelTime.get(j).getTime() - actualTraveleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelVarianceTime = dateFormat.parse("00:00");
								else
									expectedTravelVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualTravelVariance.get(j).getTime() == expectedTravelVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Travel Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
									Report.PassTest(test,
											"Travel Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Travel Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
								}

							}
						}

						else {
							Report.FailTest(test, "Stop time for ticket : " + tickets.get(j) + "with ticket type : "
									+ ticketType.get(j) + " for route : " + route.get(i)
									+ " is not between arriver customer and depart customer time or between complete and depart time or between start and arrive customer time");
						}

					} else if (ticketType.contains("DEL") || ticketType.contains("REL") || ticketType.contains("TRP")) {

						if (restartTime.get(j).getTime() == stopTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : No Stopped Time for the Ticket");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Travel Actual (h:m)" time should be equal to
							 * [(Travel to Customer) ].
							 * 
							 * Travel to Customer = Arrive Customer - Start.
							 */

							if (!(actualTravelTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Travel Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Travel Time : -- ");
							} else {
								diff = arriveCustomerTime.get(j).getTime() - customerStartTime.get(j).getTime();
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelTime = dateFormat.parse("00:00");
								else
									expectedTravelTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualTravelTime.get(j), expectedTravelTime)){
									Report.InfoTest(test,
											"Travel Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
									Report.PassTest(test, "Travel Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Travel Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
								}
							}
							if (!(actualTraveleRLTime.get(j) != null && actualTravelVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Travel Variance : -- ");
							} else {
								diff = (actualTravelTime.get(j).getTime() - actualTraveleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelVarianceTime = dateFormat.parse("00:00");
								else
									expectedTravelVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualTravelVariance.get(j).getTime() == expectedTravelVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Travel Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
									Report.PassTest(test,
											"Travel Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Travel Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
								}

							}
						}
						
					else if (arriveCustomerTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departCustomer.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Arrive Customer and Depart Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Travel Actual (h:m)" time should be equal to
							 * [(Travel to Customer) ].
							 * 
							 * Travel to Customer = Arrive Customer - Start.
							 */

							if (!(actualTravelTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Travel Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Travel Time : -- ");
							} else {
								diff = arriveCustomerTime.get(j).getTime() - customerStartTime.get(j).getTime();
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelTime = dateFormat.parse("00:00");
								else
									expectedTravelTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualTravelTime.get(j), expectedTravelTime)){
									Report.InfoTest(test,
											"Travel Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
									Report.PassTest(test, "Travel Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Travel Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
								}
							}
							if (!(actualTraveleRLTime.get(j) != null && actualTravelVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Travel Variance : -- ");
							} else {
								diff = (actualTravelTime.get(j).getTime() - actualTraveleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelVarianceTime = dateFormat.parse("00:00");
								else
									expectedTravelVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualTravelVariance.get(j).getTime() == expectedTravelVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Travel Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
									Report.PassTest(test,
											"Travel Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Travel Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
								}

							}

						}

						else if (completeTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departCustomer.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : Stopped time is between Complete and Depart Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Travel Actual (h:m)" time should be equal to
							 * [(Travel to Customer) ].
							 * 
							 * Travel to Customer = Arrive Customer - Start.
							 */

							if (!(actualTravelTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Travel Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Travel Time : -- ");
							} else {
								diff = arriveCustomerTime.get(j).getTime() - customerStartTime.get(j).getTime();
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelTime = dateFormat.parse("00:00");
								else
									expectedTravelTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualTravelTime.get(j), expectedTravelTime)){
									Report.InfoTest(test,
											"Travel Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
									Report.PassTest(test, "Travel Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Travel Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
								}
							}
							if (!(actualTraveleRLTime.get(j) != null && actualTravelVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Travel Variance : -- ");
							} else {
								diff = (actualTravelTime.get(j).getTime() - actualTraveleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelVarianceTime = dateFormat.parse("00:00");
								else
									expectedTravelVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualTravelVariance.get(j).getTime() == expectedTravelVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Travel Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
									Report.PassTest(test,
											"Travel Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Travel Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
								}

							}
						}

						else if (customerStartTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= arriveCustomerTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : Stopped time is between Start and Arrive Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Travel Actual (h:m)" time should be equal to
							 * [(Travel to Customer-Stopped Time)].
							 * 
							 * Travel to Customer = Arrive Customer - Start.
							 * Stopped Time = Restart - Stop.
							 */

							if (!(actualTravelTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Travel Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Travel Time : -- ");
							} else {
								diff = (arriveCustomerTime.get(j).getTime() - customerStartTime.get(j).getTime())
										- (restartTime.get(j).getTime() - stopTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelTime = dateFormat.parse("00:00");
								else
									expectedTravelTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualTravelTime.get(j), expectedTravelTime)){
									Report.InfoTest(test,
											"Travel Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
									Report.PassTest(test, "Travel Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Travel Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Time : "
													+ dateFormat.format(actualTravelTime.get(j))
													+ " and expected Travel Time : "
													+ dateFormat.format(expectedTravelTime));
								}
							}
							if (!(actualTraveleRLTime.get(j) != null && actualTravelVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Travel Variance : -- ");
							} else {
								diff = (actualTravelTime.get(j).getTime() - actualTraveleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedTravelVarianceTime = dateFormat.parse("00:00");
								else
									expectedTravelVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualTravelVariance.get(j).getTime() == expectedTravelVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Travel Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
									Report.PassTest(test,
											"Travel Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Travel Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Travel Variance Time : "
													+ dateFormat.format(actualTravelVariance.get(j))
													+ " and expected Travel Variance Time : "
													+ dateFormat.format(expectedTravelVarianceTime));
								}

							}
						}  else {
							Report.FailTest(test, "Stop time for ticket : " + tickets.get(j) + "with ticket type : "
									+ ticketType.get(j) + " for route : " + route.get(i)
									+ " is not between arriver customer and depart customer time or between complete and depart time or between start and arrive customer time");
						}

					} else {
						Report.FailTest(test,
								"Ticket should be among (E/R,S/O,DNR,LIV,FFY,FYN,FYS,BTN,BTY,BTS,DEL,DNE,REL,TRP) For Ticket : "
										+ tickets.get(j) + " actual ticket type is : " + ticketType.get(j));
					}

				}
			}
		} catch (Exception e) {
			LogClass.error(e.getMessage());
			Report.FailTest(test, "Travel Information is not as expected in Travel Information accordion report");
		}

	}

	public void validateOnPropertyInformationData(Map<String, List<String>> actualEfficiencyTable,
			Map<String, Map<String, List<String>>> actualOnPropertyInformationTable,
			Map<String, Map<String, List<String>>> actualTicketInformationTable) {

		List<Date> actualOnPropertyTime = new ArrayList<>();
		List<Date> actualOnPropertyeRLTime = new ArrayList<>();
		List<Date> actualOnPropertyVariance = new ArrayList<>();
		List<Date> arriveDisposalTime = new ArrayList<>();
		List<Date> departDisposalTime = new ArrayList<>();
		List<Date> customerStartTime = new ArrayList<>();
		List<Date> restartTime = new ArrayList<>();
		List<Date> stopTime = new ArrayList<>();
		List<Date> arriveCustomerTime = new ArrayList<>();
		List<Date> departCustomer = new ArrayList<>();
		List<Date> completeTime = new ArrayList<>();
		List<String> tickets = new ArrayList<>();
		List<String> ticketType = new ArrayList<>();
		Date expectedOnPropertyTime = null;
		Date expectedOnPropertyRLTime = null;
		Date expectedOnPropertyVarianceTime = null;
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

		try {

			for (Entry<String, List<String>> entry : actualEfficiencyTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualEfficiencyTable.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			for (Entry<String, Map<String, List<String>>> entry : actualOnPropertyInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().contains("Actual")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								actualOnPropertyTime.add(null);
							} else {
								actualOnPropertyTime.add(dateFormat.parse(entry2.getValue().get(i).toString()));
							}
						}
					}
				}
			}

			for (Entry<String, Map<String, List<String>>> entry : actualOnPropertyInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().contains("eRL")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								actualOnPropertyeRLTime.add(null);
							} else {
								actualOnPropertyeRLTime.add(dateFormat.parse(entry2.getValue().get(i).toString()));
							}
						}
					}
				}
			}

			for (Entry<String, Map<String, List<String>>> entry : actualOnPropertyInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().contains("Var")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								actualOnPropertyVariance.add(null);
							} else {
								actualOnPropertyVariance.add(dateFormat.parse(entry2.getValue().get(i).toString()));
							}
						}
					}
				}
			}

			for (Entry<String, Map<String, List<String>>> entry : actualTicketInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Ticket")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								tickets.add("--");
							} else {
								tickets.add(entry2.getValue().get(i).toString());
							}
						}
					}
				}
			}

			for (Entry<String, Map<String, List<String>>> entry : actualTicketInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Action")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								ticketType.add("--");
							} else {
								ticketType.add(entry2.getValue().get(i).toString());
							}
						}
					}
				}
			}
			arriveDisposalTime = Util.getDateDataFromDB(
					"select TO_CHAR(v.ARRIVELANDFILLSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");

			departDisposalTime = Util.getDateDataFromDB(
					"select TO_CHAR(v.FINISHEDATLANDFILLSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");

			customerStartTime = Util.getDateDataFromDB(
					"select TO_CHAR(v.DISPATCHEDSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			restartTime = getDateDataFromDB(
					"select TO_CHAR(v.RESTARTTICKETSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			stopTime = getDateDataFromDB(
					"select TO_CHAR(v.STOPTICKETSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			arriveCustomerTime = getDateDataFromDB(
					"select TO_CHAR(v.ARRIVECUSTOMERSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			departCustomer = getDateDataFromDB(
					"select TO_CHAR(v.DEPARTCUSTOMERSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			completeTime = getDateDataFromDB(
					"select TO_CHAR(v.TICKETCOMPLETEDSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");

			for (int i = 0; i < route.size(); i++) {

				for (int j = 0; j < tickets.size(); j++) {

					if (ticketType.contains("FFY") || ticketType.contains("FYN") || ticketType.contains("FYS")) {

						if (actualOnPropertyTime.get(j) != null) {
							Report.FailTest(test, "Actual On Property time is not as expected for Route : "
									+ route.get(j) + " and Ticket : " + tickets.get(j) + " Actual is : "
									+ dateFormat.format(actualOnPropertyTime.get(j).getTime()) + " Expected is --");
						} else {
							Report.PassTest(test, "Actual On Property time is as expected for Route : " + route.get(j)
									+ " and Ticket : " + tickets.get(j) + " Actual is : -- and Expected is : --");
						}
						if (actualOnPropertyeRLTime.get(j) != null) {
							Report.FailTest(test, "Actual On Property eRL time is not as expected for Route : "
									+ route.get(j) + " and Ticket : " + tickets.get(j) + " Actual is : "
									+ dateFormat.format(actualOnPropertyeRLTime.get(j).getTime()) + " Expected is --");
						} else {
							Report.PassTest(test,
									"Actual On Property eRL time is as expected for Route : " + route.get(j)
											+ " and Ticket : " + tickets.get(j)
											+ " Actual is : -- and Expected is : --");
						}
						if (actualOnPropertyVariance.get(j) != null) {
							Report.FailTest(test, "Actual On Property Variance time is not as expected for Route : "
									+ route.get(j) + " and Ticket : " + tickets.get(j) + " Actual is : "
									+ dateFormat.format(actualOnPropertyVariance.get(j).getTime()) + " Expected is --");
						} else {
							Report.PassTest(test,
									"Actual On Property Variance time is as expected for Route : " + route.get(j)
											+ " and Ticket : " + tickets.get(j)
											+ " Actual is : -- and Expected is : --");
						}

					} else if (ticketType.contains("S/O") || ticketType.contains("DNR") || ticketType.contains("LIV")) {
						
						if (stopTime.get(j).getTime() == restartTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : No Stopped Time for the Ticket");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as " (On Property Time 1) ". On
							 * Property Time 1 =
							 * "Depart Customer - Arrive Customer".
							 */

							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = (departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								//if (actualOnPropertyTime.get(j).getTime() == expectedOnPropertyTime.getTime()) {
								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}
						}
					else if (arriveCustomerTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departCustomer.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Arrive Customer and Depart Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as
							 * " (On Property Time 1- Stopped Time)".
							 * 
							 * On Property Time 1 =
							 * "Depart Customer - Arrive Customer". Stopped Time
							 * = Restart - Stop
							 */

							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = ((departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime())
										- (restartTime.get(j).getTime() - stopTime.get(j).getTime()));
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}

						} else if (arriveDisposalTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departDisposalTime.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Arrive Landfill and Depart Landfill");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as " (On Property Time 1) ". On
							 * Property Time 1 =
							 * "Depart Customer - Arrive Customer".
							 */

							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = (departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}
						} else if (departCustomer.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= arriveDisposalTime.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Depart Customer and Arrive Landfill");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as " (On Property Time 1) ".
							 * 
							 * On Property Time 1 =
							 * "Depart Customer - Arrive Customer".
							 */
							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = (departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}
						} else if (departDisposalTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= completeTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : Stopped time is between Depart Landfill and Complete");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as " (On Property Time 1) ". On
							 * Property Time 1 =
							 * "Depart Customer - Arrive Customer".
							 */
							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = (departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}
						} else if (customerStartTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= arriveCustomerTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : Stopped time is between Start and Arrive Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as " (On Property Time 1) ". On
							 * Property Time 1 =
							 * "Depart Customer - Arrive Customer".
							 */

							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = (departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}

						} 
					}

					else if (ticketType.contains("E/R")) {
						if (stopTime.get(j).getTime() == restartTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : No Stopped Time for the Ticket");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as
							 * " (On Property Time 1) + (On Property Time 2)".
							 * On Property Time 1 =
							 * "Depart Customer - Arrive Customer". On Property
							 * Time 2 = [(Complete - Depart Landfill) - (Arrive
							 * Landfill - Depart Customer)]
							 */

							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = (departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime())
										+ ((completeTime.get(j).getTime() - departDisposalTime.get(j).getTime())
												- (arriveDisposalTime.get(j).getTime()
														- departCustomer.get(j).getTime()));
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}
						}
					else if (arriveCustomerTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departCustomer.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Arrive Customer and Depart Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");

							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as
							 * " (On Property Time 1- Stopped Time) + (On Property Time 2)"
							 * . On Property Time 1 =
							 * "Depart Customer - Arrive Customer". On Property
							 * Time 2 = [(Complete - Depart Landfill) - (Arrive
							 * Landfill - Depart Customer)] Stopped Time =
							 * Restart - Stop.
							 */

							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = ((departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime())
										- (restartTime.get(j).getTime() - stopTime.get(j).getTime()))
										+ ((completeTime.get(j).getTime() - departDisposalTime.get(j).getTime())
												- (arriveDisposalTime.get(j).getTime()
														- departCustomer.get(j).getTime()));
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}

						} else if (arriveDisposalTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departDisposalTime.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Arrive Landfill and Depart Landfill");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as
							 * " (On Property Time 1) + (On Property Time 2)".
							 * On Property Time 1 =
							 * "Depart Customer - Arrive Customer". On Property
							 * Time 2 = [(Complete - Depart Landfill) - (Arrive
							 * Landfill - Depart Customer)]
							 */

							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = (departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime())
										+ ((completeTime.get(j).getTime() - departDisposalTime.get(j).getTime())
												- (arriveDisposalTime.get(j).getTime()
														- departCustomer.get(j).getTime()));
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}
						} else if (departCustomer.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= arriveDisposalTime.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Depart Customer and Arrive Landfill");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as
							 * " (On Property Time 1) + (On Property Time 2)".
							 * On Property Time 1 =
							 * "Depart Customer - Arrive Customer". On Property
							 * Time 2 = [(Complete - Depart Landfill) - (Arrive
							 * Landfill - Depart Customer)]
							 */

							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = (departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime())
										+ ((completeTime.get(j).getTime() - departDisposalTime.get(j).getTime())
												- (arriveDisposalTime.get(j).getTime()
														- departCustomer.get(j).getTime()));
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}
						} else if (departDisposalTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= completeTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : Stopped time is between Depart Landfill and Complete");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as
							 * " (On Property Time 1) + (On Property Time 2-Stopped Time)"
							 * .
							 * 
							 * On Property Time 1 =
							 * "Depart Customer - Arrive Customer". On Property
							 * Time 2 = [(Complete - Depart Landfill) - (Arrive
							 * Landfill - Depart Customer)] Stopped Time =
							 * Restart - Stop.
							 */

							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = (departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime())
										+ (((completeTime.get(j).getTime() - departDisposalTime.get(j).getTime())
												- (arriveDisposalTime.get(j).getTime()
														- departCustomer.get(j).getTime())
												- (restartTime.get(j).getTime() - stopTime.get(j).getTime())));
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}
						} else if (customerStartTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= arriveCustomerTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : Stopped time is between Start and Arrive Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as
							 * " (On Property Time 1) + (On Property Time 2)".
							 * On Property Time 1 =
							 * "Depart Customer - Arrive Customer". On Property
							 * Time 2 = [(Complete - Depart Landfill) - (Arrive
							 * Landfill - Depart Customer)]
							 */

							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = (departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime())
										+ ((completeTime.get(j).getTime() - departDisposalTime.get(j).getTime())
												- (arriveDisposalTime.get(j).getTime()
														- departCustomer.get(j).getTime()));
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}

						} 

					}

					else if (ticketType.contains("BTN") || ticketType.contains("BTY") || ticketType.contains("BTS")
							|| ticketType.contains("DNE")) {
						
						 if (restartTime.get(j).getTime() == stopTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : No Stopped Time for the Ticket");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as " On Property Time". On Property
							 * Time = "Depart Customer - Arrive Customer".
							 */
							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime();
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}
						}
					else if (arriveCustomerTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departCustomer.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Arrive Customer and Depart Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as " On Property Time - Stopped Time".
							 * On Property Time =
							 * "Depart Customer - Arrive Customer". Stopped Time
							 * = Restart - Stop.
							 */

							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = (departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime())
										- (restartTime.get(j).getTime() - stopTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}
						}

						else if (completeTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departCustomer.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : Stopped time is between Complete and Depart Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as " On Property Time". On Property
							 * Time = "Depart Customer - Arrive Customer".
							 */
							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime();
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}
						}

						else if (customerStartTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= arriveCustomerTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : Stopped time is between Start and Arrive Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as " On Property Time". On Property
							 * Time = "Depart Customer - Arrive Customer".
							 */
							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime();
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}
						}  else {
							Report.FailTest(test, "Stop time for ticket : " + tickets.get(j) + "with ticket type : "
									+ ticketType.get(j) + " for route : " + route.get(i)
									+ " is not between arriver customer and depart customer time or between complete and depart time or between start and arrive customer time");
						}
					} else if (ticketType.contains("DEL") || ticketType.contains("REL") || ticketType.contains("TRP")) {
						
						if (restartTime.get(j).getTime() == stopTime.get(j).getTime()) {
							if (ticketType.contains("REL") || ticketType.contains("DEL")) {
								Report.InfoTest(test, "Scenario : No Stopped Time for the Ticket");
								long diff = 0;
								long diffMinutes = 0;
								long diffHours = 0;
								DecimalFormat twodigits = new DecimalFormat("00");
								/*
								 * "On Property Actual (h:m)" time should be
								 * displayed as
								 * " On Property Time + Travel from Customer".
								 * On Property Time =
								 * "Depart Customer - Arrive Customer". Travel
								 * from Customer = Complete - Depart Customer.
								 */
								if (!(actualOnPropertyTime.get(j) != null)) {
									Report.FailTest(test,
											"Actual On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : -- ");
								} else {
									diff = (departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime())
											+ (completeTime.get(j).getTime() - departCustomer.get(j).getTime());
									diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
									diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

									if (diffHours == 0 && diffMinutes == 0)
										expectedOnPropertyTime = dateFormat.parse("00:00");
									else
										expectedOnPropertyTime = dateFormat.parse(
												twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

									if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
										Report.InfoTest(test,
												"On Property Time is correct in driver accordion report for Route : "
														+ route.get(i) + " and for Ticket " + tickets.get(j)
														+ " with Actual On Property Time : "
														+ dateFormat.format(actualOnPropertyTime.get(j))
														+ " and expected On Property Time : "
														+ dateFormat.format(expectedOnPropertyTime));
										Report.PassTest(test,
												"On Property Time is as expected in Driver Accordion report");
									} else {
										Report.FailTest(test,
												"On Property Time is not correct in driver accordion report for Route : "
														+ route.get(i) + " and for Ticket " + tickets.get(j)
														+ " with Actual On Property Time : "
														+ dateFormat.format(actualOnPropertyTime.get(j))
														+ " and expected On Property Time : "
														+ dateFormat.format(expectedOnPropertyTime));
									}
								}
								if (!(actualOnPropertyeRLTime.get(j) != null
										&& actualOnPropertyVariance.get(j) != null)) {
									Report.FailTest(test,
											"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
								} else {
									diff = (actualOnPropertyTime.get(j).getTime()
											- actualOnPropertyeRLTime.get(j).getTime());
									diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
									diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

									if (diffHours == 0 && diffMinutes == 0)
										expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
									else
										expectedOnPropertyVarianceTime = dateFormat.parse(
												twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

									if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
											.getTime()) {
										Report.InfoTest(test,
												"On Property Variance Time is correct in driver accordion report for Route : "
														+ route.get(i) + " and for Ticket " + tickets.get(j)
														+ " with Actual On Property Variance Time : "
														+ dateFormat.format(actualOnPropertyVariance.get(j))
														+ " and expected On Property Variance Time : "
														+ dateFormat.format(expectedOnPropertyVarianceTime));
										Report.PassTest(test,
												"On Property Variance Time is as expected in Driver Accordion Report");
									} else {
										Report.FailTest(test,
												"On Property Variance Time is not correct in driver accordion report for Route : "
														+ route.get(i) + " and for Ticket " + tickets.get(j)
														+ " with Actual On Property Variance Time : "
														+ dateFormat.format(actualOnPropertyVariance.get(j))
														+ " and expected On Property Variance Time : "
														+ dateFormat.format(expectedOnPropertyVarianceTime));
									}

								}
							} else if (ticketType.contains("TRP")) {
								Report.InfoTest(test, "Scenario : No Stopped Time for the Ticket");
								long diff = 0;
								long diffMinutes = 0;
								long diffHours = 0;
								DecimalFormat twodigits = new DecimalFormat("00");
								/*
								 * "On Property Actual (h:m)" time should be
								 * displayed as " On Property Time". On Property
								 * Time = "Depart Customer - Arrive Customer".
								 */
								if (!(actualOnPropertyTime.get(j) != null)) {
									Report.FailTest(test,
											"Actual On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : -- ");
								} else {
									diff = (departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime());
									diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
									diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

									if (diffHours == 0 && diffMinutes == 0)
										expectedOnPropertyTime = dateFormat.parse("00:00");
									else
										expectedOnPropertyTime = dateFormat.parse(
												twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

									if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
										Report.InfoTest(test,
												"On Property Time is correct in driver accordion report for Route : "
														+ route.get(i) + " and for Ticket " + tickets.get(j)
														+ " with Actual On Property Time : "
														+ dateFormat.format(actualOnPropertyTime.get(j))
														+ " and expected On Property Time : "
														+ dateFormat.format(expectedOnPropertyTime));
										Report.PassTest(test,
												"On Property Time is as expected in Driver Accordion report");
									} else {
										Report.FailTest(test,
												"On Property Time is not correct in driver accordion report for Route : "
														+ route.get(i) + " and for Ticket " + tickets.get(j)
														+ " with Actual On Property Time : "
														+ dateFormat.format(actualOnPropertyTime.get(j))
														+ " and expected On Property Time : "
														+ dateFormat.format(expectedOnPropertyTime));
									}
								}
								if (!(actualOnPropertyeRLTime.get(j) != null
										&& actualOnPropertyVariance.get(j) != null)) {
									Report.FailTest(test,
											"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
								} else {
									diff = (actualOnPropertyTime.get(j).getTime()
											- actualOnPropertyeRLTime.get(j).getTime());
									diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
									diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

									if (diffHours == 0 && diffMinutes == 0)
										expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
									else
										expectedOnPropertyVarianceTime = dateFormat.parse(
												twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

									if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
											.getTime()) {
										Report.InfoTest(test,
												"On Property Variance Time is correct in driver accordion report for Route : "
														+ route.get(i) + " and for Ticket " + tickets.get(j)
														+ " with Actual On Property Variance Time : "
														+ dateFormat.format(actualOnPropertyVariance.get(j))
														+ " and expected On Property Variance Time : "
														+ dateFormat.format(expectedOnPropertyVarianceTime));
										Report.PassTest(test,
												"On Property Variance Time is as expected in Driver Accordion Report");
									} else {
										Report.FailTest(test,
												"On Property Variance Time is not correct in driver accordion report for Route : "
														+ route.get(i) + " and for Ticket " + tickets.get(j)
														+ " with Actual On Property Variance Time : "
														+ dateFormat.format(actualOnPropertyVariance.get(j))
														+ " and expected On Property Variance Time : "
														+ dateFormat.format(expectedOnPropertyVarianceTime));
									}

								}
							}
						}
					else if (arriveCustomerTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departCustomer.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Arrive Customer and Depart Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as
							 * " [(On Property Time - Stopped Time)+Travel from Customer]"
							 * . On Property Time =
							 * "Depart Customer - Arrive Customer". Stopped Time
							 * = Restart - Stop. Travel from Customer = Complete
							 * - Depart Customer.
							 */
							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = ((departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime())
										- (restartTime.get(j).getTime() - stopTime.get(j).getTime()))
										+ (completeTime.get(j).getTime() - departCustomer.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}
						}

						else if (completeTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departCustomer.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : Stopped time is between Complete and Depart Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as
							 * " [(On Property Time )+(Travel from Customer - Stopped Time)]"
							 * . On Property Time =
							 * "Depart Customer - Arrive Customer". Stopped Time
							 * = Restart - Stop. Travel from Customer = Complete
							 * - Depart Customer.
							 */

							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = (departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime())
										+ ((completeTime.get(j).getTime() - departCustomer.get(j).getTime())
												- (restartTime.get(j).getTime() - stopTime.get(j).getTime()));
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}
						}

						else if (customerStartTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= arriveCustomerTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : Stopped time is between Start and Arrive Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "On Property Actual (h:m)" time should be
							 * displayed as
							 * " On Property Time + Travel from Customer". On
							 * Property Time =
							 * "Depart Customer - Arrive Customer". Travel from
							 * Customer = Complete - Depart Customer.
							 */
							if (!(actualOnPropertyTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual On Property Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property Time : -- ");
							} else {
								diff = (departCustomer.get(j).getTime() - arriveCustomerTime.get(j).getTime())
										+ (completeTime.get(j).getTime() - departCustomer.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if(Util.compareTime(actualOnPropertyTime.get(j), expectedOnPropertyTime)) {
									Report.InfoTest(test,
											"On Property Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
									Report.PassTest(test, "On Property Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"On Property Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Time : "
													+ dateFormat.format(actualOnPropertyTime.get(j))
													+ " and expected On Property Time : "
													+ dateFormat.format(expectedOnPropertyTime));
								}
							}
							if (!(actualOnPropertyeRLTime.get(j) != null && actualOnPropertyVariance.get(j) != null)) {
								Report.FailTest(test,
										"On Property eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual On Property eRL Time : -- and Actual On Property Variance : -- ");
							} else {
								diff = (actualOnPropertyTime.get(j).getTime()
										- actualOnPropertyeRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedOnPropertyVarianceTime = dateFormat.parse("00:00");
								else
									expectedOnPropertyVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualOnPropertyVariance.get(j).getTime() == expectedOnPropertyVarianceTime
										.getTime()) {
									Report.InfoTest(test,
											"On Property Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
									Report.PassTest(test,
											"On Property Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"On Property Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual On Property Variance Time : "
													+ dateFormat.format(actualOnPropertyVariance.get(j))
													+ " and expected On Property Variance Time : "
													+ dateFormat.format(expectedOnPropertyVarianceTime));
								}

							}
						}  else {
							Report.FailTest(test, "Stop time for ticket : " + tickets.get(j) + "with ticket type : "
									+ ticketType.get(j) + " for route : " + route.get(i)
									+ " is not between arriver customer and depart customer time or between complete and depart time or between start and arrive customer time");
						}
					} else {
						Report.FailTest(test,
								"Ticket should be among (E/R,S/O,DNR,LIV,FFY,FYN,FYS,BTN,BTY,BTS,DEL,DNE,REL,TRP) For Ticket : "
										+ tickets.get(j) + " actual ticket type is : " + ticketType.get(j));
					}

				}
			}
		} catch (Exception e) {
			LogClass.error(e.getMessage());
			Report.FailTest(test,
					"On Property Information is not as expected in On Property Information accordion report");
		}

	}

	public void validateDisposalInformationData(Map<String, List<String>> actualEfficiencyTable,
			Map<String, Map<String, List<String>>> actualDisposalInformationTable,
			Map<String, Map<String, List<String>>> actualTicketInformationTable) {

		List<Date> actualDisposalTime = new ArrayList<>();
		List<Date> actualDisposaleRLTime = new ArrayList<>();
		List<Date> actualDisposalVariance = new ArrayList<>();
		List<Date> arriveDisposalTime = new ArrayList<>();
		List<Date> departDisposalTime = new ArrayList<>();
		List<Date> customerStartTime = new ArrayList<>();
		List<Date> restartTime = new ArrayList<>();
		List<Date> stopTime = new ArrayList<>();
		List<Date> arriveCustomerTime = new ArrayList<>();
		List<Date> departCustomer = new ArrayList<>();
		List<Date> completeTime = new ArrayList<>();
		List<String> tickets = new ArrayList<>();
		List<String> ticketType = new ArrayList<>();
		Date expectedDisposalTime = null;
		Date expectedDisposaleRLTime = null;
		Date expectedDisposalVarianceTime = null;
		List<String> route = new ArrayList<>();
		List<String> date = new ArrayList<>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

		try {

			for (Entry<String, List<String>> entry : actualEfficiencyTable.entrySet()) {
				if (entry.getKey().equals("Route")) {
					route = entry.getValue();
				}
			}
			for (Entry<String, List<String>> entry : actualEfficiencyTable.entrySet()) {
				if (entry.getKey().contains("Date")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						Date dates = format1.parse(entry.getValue().get(i));
						date.add(format2.format(dates));
					}
				}
			}

			for (Entry<String, Map<String, List<String>>> entry : actualDisposalInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().contains("Actual")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								actualDisposalTime.add(null);
							} else {
								actualDisposalTime.add(dateFormat.parse(entry2.getValue().get(i).toString()));
							}
						}
					}
				}
			}

			for (Entry<String, Map<String, List<String>>> entry : actualDisposalInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().contains("eRL")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								actualDisposaleRLTime.add(null);
							} else {
								actualDisposaleRLTime.add(dateFormat.parse(entry2.getValue().get(i).toString()));
							}
						}
					}
				}
			}

			for (Entry<String, Map<String, List<String>>> entry : actualDisposalInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().contains("Var")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								actualDisposalVariance.add(null);
							} else {
								actualDisposalVariance.add(dateFormat.parse(entry2.getValue().get(i).toString()));
							}
						}
					}
				}
			}

			for (Entry<String, Map<String, List<String>>> entry : actualTicketInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Ticket")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								tickets.add("--");
							} else {
								tickets.add(entry2.getValue().get(i).toString());
							}
						}
					}
				}
			}

			for (Entry<String, Map<String, List<String>>> entry : actualTicketInformationTable.entrySet()) {
				for (Entry<String, List<String>> entry2 : entry.getValue().entrySet()) {
					if (entry2.getKey().equals("Action")) {
						for (int i = 0; i < entry2.getValue().size(); i++) {
							if (entry2.getValue().get(i).equals("--")) {
								ticketType.add("--");
							} else {
								ticketType.add(entry2.getValue().get(i).toString());
							}
						}
					}
				}
			}
			arriveDisposalTime = Util.getDateDataFromDB(
					"select TO_CHAR(v.ARRIVELANDFILLSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");

			departDisposalTime = Util.getDateDataFromDB(
					"select TO_CHAR(v.FINISHEDATLANDFILLSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");

			customerStartTime = Util.getDateDataFromDB(
					"select TO_CHAR(v.DISPATCHEDSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			restartTime = getDateDataFromDB(
					"select TO_CHAR(v.RESTARTTICKETSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			stopTime = getDateDataFromDB(
					"select TO_CHAR(v.STOPTICKETSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			arriveCustomerTime = getDateDataFromDB(
					"select TO_CHAR(v.ARRIVECUSTOMERSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			departCustomer = getDateDataFromDB(
					"select TO_CHAR(v.DEPARTCUSTOMERSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");
			completeTime = getDateDataFromDB(
					"select TO_CHAR(v.TICKETCOMPLETEDSTAMP - (5 / 24) , 'HH:MI') from ocs_admin.tp_co_result v where FK_CUSTOMERORDER in (select PKEY from OCS_ADMIN.TP_CUSTOMERORDER where TICKETNUMBER in ("
							+ StringUtils.join(tickets, ',') + "))and fk_vehicle is not null order by v.CHANGED_STAMP");

			for (int i = 0; i < route.size(); i++) {

				for (int j = 0; j < tickets.size(); j++) {

					if (ticketType.contains("FFY") || ticketType.contains("FYN") || ticketType.contains("FYS")) {
						
						// need to check logic for no stopped time
						if (stopTime.get(j).getTime() == restartTime.get(j).getTime()) {
							/*
							 * Disposal Actual
							 * (h:m)" time should be displayed as " Disposal
							 * Time + Disposal". Note:- Disposal Time = (Depart
							 * Landfill - Arrive Landfill) Disposal= Complete -
							 * Depart Landfill
							 */

							Report.InfoTest(test, "Scenarion : No Stopped Time for the Ticket");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							if (!(actualDisposalTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Disposal Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Disposal Time : -- ");
							} else {
								diff = (departDisposalTime.get(j).getTime() - arriveDisposalTime.get(j).getTime())
										+ (completeTime.get(j).getTime() - departDisposalTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalTime = dateFormat.parse("00:00");
								else
									expectedDisposalTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								//if (actualDisposalTime.get(j).getTime() == expectedDisposalTime.getTime()) {
								if (Util.compareTime(actualDisposalTime.get(j), expectedDisposalTime)) {
									Report.InfoTest(test,
											"Disposal Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
									Report.PassTest(test, "Disposal Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Disposal Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
								}
							}
							if (!(actualDisposaleRLTime.get(j) != null && actualDisposalVariance.get(j) != null)) {
								Report.FailTest(test,
										"Disposal eRL Time and Disposal Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Disposal Variance : -- ");
							} else {
								diff = (actualDisposalTime.get(j).getTime() - actualDisposaleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalVarianceTime = dateFormat.parse("00:00");
								else
									expectedDisposalVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualDisposalVariance.get(j).getTime() == expectedDisposalVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Disposal Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
									Report.PassTest(test,
											"Disposal Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Disposal Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
								}

							}

						}

					else if (arriveDisposalTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departDisposalTime.get(j).getTime()) {

							/*
							 * Disposal Actual (h:m)=
							 * " (Disposal Time- Stopped Time)+(Disposal )".
							 * Note:- Disposal Time = (Depart Landfill - Arrive
							 * Landfill). Disposal= Complete - Depart Landfill.
							 * Stopped Time = Restart - Stop.
							 */

							Report.InfoTest(test,
									"Scenarion : Stopped time is in between Arrive Landfill and Depart Landfill");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							if (!(actualDisposalTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Disposal Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Disposal Time : -- ");
							} else {
								diff = ((departDisposalTime.get(j).getTime() - arriveDisposalTime.get(j).getTime())
										- (restartTime.get(j).getTime() - stopTime.get(j).getTime()))
										+ (completeTime.get(j).getTime() - departDisposalTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalTime = dateFormat.parse("00:00");
								else
									expectedDisposalTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (Util.compareTime(actualDisposalTime.get(j), expectedDisposalTime)) {
									Report.InfoTest(test,
											"Disposal Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
									Report.PassTest(test, "Disposal Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Disposal Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
								}
							}
							if (!(actualDisposaleRLTime.get(j) != null && actualDisposalVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Disposal Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Disposal Variance : -- ");
							} else {
								diff = (actualDisposalTime.get(j).getTime() - actualDisposaleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalVarianceTime = dateFormat.parse("00:00");
								else
									expectedDisposalVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualDisposalVariance.get(j).getTime() == expectedDisposalVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Disposal Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
									Report.PassTest(test,
											"Disposal Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Disposal Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
								}

							}

						}

						else if (departDisposalTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= completeTime.get(j).getTime())

						{
							/*
							 * Disposal Actual (h:m)=
							 * " Disposal Time + (Disposal - Stopped Time)".
							 * Note:- Disposal Time = (Depart Landfill - Arrive
							 * Landfill). Disposal= Complete - Depart Landfill.
							 * Stopped Time = Restart - Stop.
							 */
							Report.InfoTest(test,
									"Scenarion : Stopped time is in between Depart Landfill and Complete Time");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							if (!(actualDisposalTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Disposal Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Disposal Time : -- ");
							} else {
								diff = ((departDisposalTime.get(j).getTime() - arriveDisposalTime.get(j).getTime())
										- (restartTime.get(j).getTime() - stopTime.get(j).getTime()))
										+ (completeTime.get(j).getTime() - departDisposalTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalTime = dateFormat.parse("00:00");
								else
									expectedDisposalTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (Util.compareTime(actualDisposalTime.get(j), expectedDisposalTime)) {
									Report.InfoTest(test,
											"Disposal Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
									Report.PassTest(test, "Disposal Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Disposal Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
								}
							}

							if (!(actualDisposaleRLTime.get(j) != null && actualDisposalVariance.get(j) != null)) {
								Report.FailTest(test,
										"Disposal eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Disposal Variance : -- ");
							} else {
								diff = (actualDisposalTime.get(j).getTime() - actualDisposaleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalVarianceTime = dateFormat.parse("00:00");
								else
									expectedDisposalVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualDisposalVariance.get(j).getTime() == expectedDisposalVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Disposal Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
									Report.PassTest(test,
											"Disposal Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Disposal Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
								}

							}

						}

						else if (customerStartTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= arriveDisposalTime.get(j).getTime()) {
							/*
							 * "Disposal Actual (h:m)" time should be displayed
							 * as " Disposal Time + Disposal". Note:- Disposal
							 * Time = (Depart Landfill - Arrive Landfill)
							 * Disposal= Complete - Depart Landfill
							 */
							Report.InfoTest(test,
									"Scenarion : Stopped time is in between Start Customer and Arrive Landfill");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							if (!(actualDisposalTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Disposal Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Disposal Time : -- ");
							} else {
								diff = (departDisposalTime.get(j).getTime() - arriveDisposalTime.get(j).getTime())
										+ (completeTime.get(j).getTime() - departDisposalTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalTime = dateFormat.parse("00:00");
								else
									expectedDisposalTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (Util.compareTime(actualDisposalTime.get(j), expectedDisposalTime)) {
									Report.InfoTest(test,
											"Disposal Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
									Report.PassTest(test, "Disposal Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Disposal Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
								}
							}
							if (!(actualDisposaleRLTime.get(j) != null && actualDisposalVariance.get(j) != null)) {
								Report.FailTest(test,
										"Disposal eRL Time and Travel Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Disposal Variance : -- ");
							} else {
								diff = (actualDisposalTime.get(j).getTime() - actualDisposaleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalVarianceTime = dateFormat.parse("00:00");
								else
									expectedDisposalVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualDisposalVariance.get(j).getTime() == expectedDisposalVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Disposal Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
									Report.PassTest(test,
											"Disposal Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Disposal Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
								}

							}

						}
						 else {

							Report.FailTest(test, "For Route : " + route.get(i) + " and for Ticket " + tickets.get(j)
									+ " stopped time is not between Arrive Landfill and depart landfill or between Depart landfill and complete customer or between Start customer and arrive landfill");
						}

					} else if (ticketType.contains("S/O") || ticketType.contains("DNR") || ticketType.contains("LIV")) {
						
						if (stopTime.get(j).getTime() == restartTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : No Stopped Time for the Ticket");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Disposal Actual (h:m)" time should be displayed
							 * as " Disposal Time + (On Property Time 2)".
							 * 
							 * Disposal Time = Depart Landfill - Arrive Landfill
							 * On Property Time 2 = [(Complete - Depart
							 * Landfill) - (Arrive Landfill - Depart Customer)]
							 */

							if (!(actualDisposalTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Disposal Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Disposal Time : -- ");
							} else {
								diff = ((departDisposalTime.get(j).getTime() - arriveDisposalTime.get(j).getTime())
										+ ((completeTime.get(j).getTime() - departDisposalTime.get(j).getTime())
												- (arriveDisposalTime.get(j).getTime()
														- departCustomer.get(j).getTime())));
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalTime = dateFormat.parse("00:00");
								else
									expectedDisposalTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (Util.compareTime(actualDisposalTime.get(j), expectedDisposalTime)) {
									Report.InfoTest(test,
											"Disposal Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
									Report.PassTest(test, "Disposal Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Disposal Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
								}
							}
							if (!(actualDisposaleRLTime.get(j) != null && actualDisposalVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Disposal Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Disposal Variance : -- ");
							} else {
								diff = (actualDisposalTime.get(j).getTime() - actualDisposaleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalVarianceTime = dateFormat.parse("00:00");
								else
									expectedDisposalVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualDisposalVariance.get(j).getTime() == expectedDisposalVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Disposal Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
									Report.PassTest(test,
											"Disposal Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Disposal Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
								}

							}
						}
					else if (arriveCustomerTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departCustomer.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Arrive Customer and Depart Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Disposal Actual (h:m)" time should be displayed
							 * as " Disposal Time + (On Property Time 2)".
							 * 
							 * Disposal Time = Depart Landfill - Arrive Landfill
							 * On Property Time 2 = [(Complete - Depart
							 * Landfill) - (Arrive Landfill - Depart Customer)]
							 */

							if (!(actualDisposalTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Disposal Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Disposal Time : -- ");
							} else {
								diff = ((departDisposalTime.get(j).getTime() - arriveDisposalTime.get(j).getTime())
										+ ((completeTime.get(j).getTime() - departDisposalTime.get(j).getTime())
												- (arriveDisposalTime.get(j).getTime()
														- departCustomer.get(j).getTime())));
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalTime = dateFormat.parse("00:00");
								else
									expectedDisposalTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (Util.compareTime(actualDisposalTime.get(j), expectedDisposalTime)) {
									Report.InfoTest(test,
											"Disposal Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
									Report.PassTest(test, "Disposal Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Disposal Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
								}
							}
							if (!(actualDisposaleRLTime.get(j) != null && actualDisposalVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Disposal Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Disposal Variance : -- ");
							} else {
								diff = (actualDisposalTime.get(j).getTime() - actualDisposaleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalVarianceTime = dateFormat.parse("00:00");
								else
									expectedDisposalVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualDisposalVariance.get(j).getTime() == expectedDisposalVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Disposal Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
									Report.PassTest(test,
											"Disposal Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Disposal Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
								}

							}
						} else if (arriveDisposalTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departDisposalTime.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Arrive Landfill and Depart Landfill");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Disposal Actual (h:m)" time should be displayed
							 * as
							 * " [(Disposal Time - Stopped Time )+ (On Property Time 2)]"
							 * .
							 * 
							 * Disposal Time = Depart Landfill - Arrive Landfill
							 * Stopped Time = Restart - Stop. On Property Time 2
							 * = [(Complete - Depart Landfill) - (Arrive
							 * Landfill - Depart Customer)]
							 */

							if (!(actualDisposalTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Disposal Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Disposal Time : -- ");
							} else {
								diff = (((departDisposalTime.get(j).getTime() - arriveDisposalTime.get(j).getTime())
										- (restartTime.get(j).getTime() - stopTime.get(j).getTime()))
										+ ((completeTime.get(j).getTime() - departDisposalTime.get(j).getTime())
												- (arriveDisposalTime.get(j).getTime()
														- departCustomer.get(j).getTime())));
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalTime = dateFormat.parse("00:00");
								else
									expectedDisposalTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (Util.compareTime(actualDisposalTime.get(j), expectedDisposalTime)) {
									Report.InfoTest(test,
											"Disposal Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
									Report.PassTest(test, "Disposal Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Disposal Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
								}
							}
							if (!(actualDisposaleRLTime.get(j) != null && actualDisposalVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Disposal Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Disposal Variance : -- ");
							} else {
								diff = (actualDisposalTime.get(j).getTime() - actualDisposaleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalVarianceTime = dateFormat.parse("00:00");
								else
									expectedDisposalVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualDisposalVariance.get(j).getTime() == expectedDisposalVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Disposal Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
									Report.PassTest(test,
											"Disposal Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Disposal Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
								}

							}
						} else if (departCustomer.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= arriveDisposalTime.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Depart Customer and Arrive Landfill");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Disposal Actual (h:m)" time should be displayed
							 * as " Disposal Time + (On Property Time 2)".
							 * 
							 * Disposal Time = Depart Landfill - Arrive Landfill
							 * On Property Time 2 = [(Complete - Depart
							 * Landfill) - (Arrive Landfill - Depart Customer)]
							 */

							if (!(actualDisposalTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Disposal Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Disposal Time : -- ");
							} else {
								diff = ((departDisposalTime.get(j).getTime() - arriveDisposalTime.get(j).getTime())
										+ ((completeTime.get(j).getTime() - departDisposalTime.get(j).getTime())
												- (arriveDisposalTime.get(j).getTime()
														- departCustomer.get(j).getTime())));
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalTime = dateFormat.parse("00:00");
								else
									expectedDisposalTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (Util.compareTime(actualDisposalTime.get(j), expectedDisposalTime)) {
									Report.InfoTest(test,
											"Disposal Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
									Report.PassTest(test, "Disposal Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Disposal Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
								}
							}
							if (!(actualDisposaleRLTime.get(j) != null && actualDisposalVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Disposal Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Disposal Variance : -- ");
							} else {
								diff = (actualDisposalTime.get(j).getTime() - actualDisposaleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalVarianceTime = dateFormat.parse("00:00");
								else
									expectedDisposalVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualDisposalVariance.get(j).getTime() == expectedDisposalVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Disposal Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
									Report.PassTest(test,
											"Disposal Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Disposal Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
								}

							}
						} else if (departDisposalTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= completeTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : Stopped time is between Depart Landfill and Complete");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Disposal Actual (h:m)" time should be displayed
							 * as
							 * " [(Disposal Time )+ (On Property Time 2 - Stopped Time)]"
							 * .
							 * 
							 * Disposal Time = Depart Landfill - Arrive Landfill
							 * Stopped Time = Restart - Stop. On Property Time 2
							 * = [(Complete - Depart Landfill) - (Arrive
							 * Landfill - Depart Customer)]
							 */

							if (!(actualDisposalTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Disposal Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Disposal Time : -- ");
							} else {
								diff = (departDisposalTime.get(j).getTime() - arriveDisposalTime.get(j).getTime())
										+ (((completeTime.get(j).getTime() - departDisposalTime.get(j).getTime())
												- (arriveDisposalTime.get(j).getTime()
														- departCustomer.get(j).getTime()))
												- (restartTime.get(j).getTime() - stopTime.get(j).getTime()));
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalTime = dateFormat.parse("00:00");
								else
									expectedDisposalTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (Util.compareTime(actualDisposalTime.get(j), expectedDisposalTime)) {
									Report.InfoTest(test,
											"Disposal Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
									Report.PassTest(test, "Disposal Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Disposal Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
								}
							}
							if (!(actualDisposaleRLTime.get(j) != null && actualDisposalVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Disposal Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Disposal Variance : -- ");
							} else {
								diff = (actualDisposalTime.get(j).getTime() - actualDisposaleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalVarianceTime = dateFormat.parse("00:00");
								else
									expectedDisposalVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualDisposalVariance.get(j).getTime() == expectedDisposalVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Disposal Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
									Report.PassTest(test,
											"Disposal Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Disposal Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
								}

							}
						} else if (customerStartTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= arriveCustomerTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : Stopped time is between Start and Arrive Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");

							/*
							 * "Disposal Actual (h:m)" time should be displayed
							 * as " Disposal Time + (On Property Time 2)".
							 * 
							 * Disposal Time = Depart Landfill - Arrive Landfill
							 * On Property Time 2 = [(Complete - Depart
							 * Landfill) - (Arrive Landfill - Depart Customer)]
							 */

							if (!(actualDisposalTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Disposal Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Disposal Time : -- ");
							} else {
								diff = ((departDisposalTime.get(j).getTime() - arriveDisposalTime.get(j).getTime())
										+ ((completeTime.get(j).getTime() - departDisposalTime.get(j).getTime())
												- (arriveDisposalTime.get(j).getTime()
														- departCustomer.get(j).getTime())));
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalTime = dateFormat.parse("00:00");
								else
									expectedDisposalTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (Util.compareTime(actualDisposalTime.get(j), expectedDisposalTime)) {
									Report.InfoTest(test,
											"Disposal Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
									Report.PassTest(test, "Disposal Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Disposal Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
								}
							}
							if (!(actualDisposaleRLTime.get(j) != null && actualDisposalVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Disposal Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Disposal Variance : -- ");
							} else {
								diff = (actualDisposalTime.get(j).getTime() - actualDisposaleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalVarianceTime = dateFormat.parse("00:00");
								else
									expectedDisposalVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualDisposalVariance.get(j).getTime() == expectedDisposalVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Disposal Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
									Report.PassTest(test,
											"Disposal Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Disposal Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
								}

							}

						} 
					}

					else if (ticketType.contains("E/R")) {
						
						if (stopTime.get(j).getTime() == restartTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : No Stopped Time for the Ticket");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Disposal Actual (h:m)" time should be displayed
							 * as " Disposal Time ".
							 * 
							 * Disposal Time = Depart Landfill - Arrive Landfill
							 */

							if (!(actualDisposalTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Disposal Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Disposal Time : -- ");
							} else {
								diff = (departDisposalTime.get(j).getTime() - arriveDisposalTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalTime = dateFormat.parse("00:00");
								else
									expectedDisposalTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (Util.compareTime(actualDisposalTime.get(j), expectedDisposalTime)) {
									Report.InfoTest(test,
											"Disposal Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
									Report.PassTest(test, "Disposal Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Disposal Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
								}
							}
							if (!(actualDisposaleRLTime.get(j) != null && actualDisposalVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Disposal Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Disposal Variance : -- ");
							} else {
								diff = (actualDisposalTime.get(j).getTime() - actualDisposaleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalVarianceTime = dateFormat.parse("00:00");
								else
									expectedDisposalVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualDisposalVariance.get(j).getTime() == expectedDisposalVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Disposal Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
									Report.PassTest(test,
											"Disposal Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Disposal Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
								}

							}
						}
					else if (arriveCustomerTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departCustomer.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Arrive Customer and Depart Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");

							/*
							 * "Disposal Actual (h:m)" time should be displayed
							 * as " Disposal Time ".
							 * 
							 * Disposal Time = Depart Landfill - Arrive Landfill
							 */

							if (!(actualDisposalTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Disposal Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Disposal Time : -- ");
							} else {
								diff = (departDisposalTime.get(j).getTime() - arriveDisposalTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalTime = dateFormat.parse("00:00");
								else
									expectedDisposalTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (Util.compareTime(actualDisposalTime.get(j), expectedDisposalTime)) {
									Report.InfoTest(test,
											"Disposal Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
									Report.PassTest(test, "Disposal Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Disposal Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
								}
							}
							if (!(actualDisposaleRLTime.get(j) != null && actualDisposalVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Disposal Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Disposal Variance : -- ");
							} else {
								diff = (actualDisposalTime.get(j).getTime() - actualDisposaleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalVarianceTime = dateFormat.parse("00:00");
								else
									expectedDisposalVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualDisposalVariance.get(j).getTime() == expectedDisposalVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Disposal Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
									Report.PassTest(test,
											"Disposal Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Disposal Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
								}

							}

						} else if (arriveDisposalTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= departDisposalTime.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Arrive Landfill and Depart Landfill");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Disposal Actual (h:m)" time should be displayed
							 * as " Disposal Time ".
							 * 
							 * Disposal Time = Depart Landfill - Arrive Landfill
							 */

							if (!(actualDisposalTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Disposal Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Disposal Time : -- ");
							} else {
								diff = (departDisposalTime.get(j).getTime() - arriveDisposalTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalTime = dateFormat.parse("00:00");
								else
									expectedDisposalTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (Util.compareTime(actualDisposalTime.get(j), expectedDisposalTime)) {
									Report.InfoTest(test,
											"Disposal Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
									Report.PassTest(test, "Disposal Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Disposal Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
								}
							}
							if (!(actualDisposaleRLTime.get(j) != null && actualDisposalVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Disposal Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Disposal Variance : -- ");
							} else {
								diff = (actualDisposalTime.get(j).getTime() - actualDisposaleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalVarianceTime = dateFormat.parse("00:00");
								else
									expectedDisposalVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualDisposalVariance.get(j).getTime() == expectedDisposalVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Disposal Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
									Report.PassTest(test,
											"Disposal Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Disposal Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
								}

							}
						} else if (departCustomer.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= arriveDisposalTime.get(j).getTime()) {
							Report.InfoTest(test,
									"Scenario : Stopped time is between Depart Customer and Arrive Landfill");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Disposal Actual (h:m)" time should be displayed
							 * as " Disposal Time ".
							 * 
							 * Disposal Time = Depart Landfill - Arrive Landfill
							 */

							if (!(actualDisposalTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Disposal Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Disposal Time : -- ");
							} else {
								diff = (departDisposalTime.get(j).getTime() - arriveDisposalTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalTime = dateFormat.parse("00:00");
								else
									expectedDisposalTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (Util.compareTime(actualDisposalTime.get(j), expectedDisposalTime)) {
									Report.InfoTest(test,
											"Disposal Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
									Report.PassTest(test, "Disposal Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Disposal Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
								}
							}
							if (!(actualDisposaleRLTime.get(j) != null && actualDisposalVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Disposal Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Disposal Variance : -- ");
							} else {
								diff = (actualDisposalTime.get(j).getTime() - actualDisposaleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalVarianceTime = dateFormat.parse("00:00");
								else
									expectedDisposalVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualDisposalVariance.get(j).getTime() == expectedDisposalVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Disposal Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
									Report.PassTest(test,
											"Disposal Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Disposal Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
								}

							}
						} else if (departDisposalTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= completeTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : Stopped time is between Depart Landfill and Complete");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Disposal Actual (h:m)" time should be displayed
							 * as " Disposal Time ".
							 * 
							 * Disposal Time = Depart Landfill - Arrive Landfill
							 */

							if (!(actualDisposalTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Disposal Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Disposal Time : -- ");
							} else {
								diff = (departDisposalTime.get(j).getTime() - arriveDisposalTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalTime = dateFormat.parse("00:00");
								else
									expectedDisposalTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (Util.compareTime(actualDisposalTime.get(j), expectedDisposalTime)) {
									Report.InfoTest(test,
											"Disposal Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
									Report.PassTest(test, "Disposal Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Disposal Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
								}
							}
							if (!(actualDisposaleRLTime.get(j) != null && actualDisposalVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Disposal Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Disposal Variance : -- ");
							} else {
								diff = (actualDisposalTime.get(j).getTime() - actualDisposaleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalVarianceTime = dateFormat.parse("00:00");
								else
									expectedDisposalVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualDisposalVariance.get(j).getTime() == expectedDisposalVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Disposal Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
									Report.PassTest(test,
											"Disposal Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Disposal Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
								}

							}
						} else if (customerStartTime.get(j).getTime() <= stopTime.get(j).getTime()
								&& restartTime.get(j).getTime() <= arriveCustomerTime.get(j).getTime()) {
							Report.InfoTest(test, "Scenario : Stopped time is between Start and Arrive Customer");
							long diff = 0;
							long diffMinutes = 0;
							long diffHours = 0;
							DecimalFormat twodigits = new DecimalFormat("00");
							/*
							 * "Disposal Actual (h:m)" time should be displayed
							 * as " Disposal Time ".
							 * 
							 * Disposal Time = Depart Landfill - Arrive Landfill
							 */

							if (!(actualDisposalTime.get(j) != null)) {
								Report.FailTest(test,
										"Actual Disposal Time is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual Disposal Time : -- ");
							} else {
								diff = (departDisposalTime.get(j).getTime() - arriveDisposalTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalTime = dateFormat.parse("00:00");
								else
									expectedDisposalTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (Util.compareTime(actualDisposalTime.get(j), expectedDisposalTime)) {
									Report.InfoTest(test,
											"Disposal Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
									Report.PassTest(test, "Disposal Time is as expected in Driver Accordion report");
								} else {
									Report.FailTest(test,
											"Disposal Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Time : "
													+ dateFormat.format(actualDisposalTime.get(j))
													+ " and expected Disposal Time : "
													+ dateFormat.format(expectedDisposalTime));
								}
							}
							if (!(actualDisposaleRLTime.get(j) != null && actualDisposalVariance.get(j) != null)) {
								Report.FailTest(test,
										"eRL Time and Disposal Variance is not correct in driver accordion report for Route : "
												+ route.get(i) + " and for Ticket " + tickets.get(j)
												+ " with Actual eRL Time : -- and Actual Disposal Variance : -- ");
							} else {
								diff = (actualDisposalTime.get(j).getTime() - actualDisposaleRLTime.get(j).getTime());
								diffMinutes = diffMinutes + diff / (60 * 1000) % 60;
								diffHours = diffHours + diff / (60 * 60 * 1000) % 24;

								if (diffHours == 0 && diffMinutes == 0)
									expectedDisposalVarianceTime = dateFormat.parse("00:00");
								else
									expectedDisposalVarianceTime = dateFormat
											.parse(twodigits.format(diffHours) + ":" + twodigits.format(diffMinutes));

								if (actualDisposalVariance.get(j).getTime() == expectedDisposalVarianceTime.getTime()) {
									Report.InfoTest(test,
											"Disposal Variance Time is correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
									Report.PassTest(test,
											"Disposal Variance Time is as expected in Driver Accordion Report");
								} else {
									Report.FailTest(test,
											"Disposal Variance Time is not correct in driver accordion report for Route : "
													+ route.get(i) + " and for Ticket " + tickets.get(j)
													+ " with Actual Disposal Variance Time : "
													+ dateFormat.format(actualDisposalVariance.get(j))
													+ " and expected Disposal Variance Time : "
													+ dateFormat.format(expectedDisposalVarianceTime));
								}

							}

						} 
					}

					else if (ticketType.contains("BTN") || ticketType.contains("BTY") || ticketType.contains("BTS")
							|| ticketType.contains("DNE") || ticketType.contains("DEL") || ticketType.contains("REL")
							|| ticketType.contains("TRP")) {
						if (actualDisposalTime.get(j) != null) {
							Report.FailTest(test, "Actual Disposal time is not as expected for Route : " + route.get(j)
									+ " and Ticket : " + tickets.get(j) + " Actual is : "
									+ dateFormat.format(actualDisposalTime.get(j).getTime()) + " Expected is --");
						} else {
							Report.PassTest(test, "Actual Disposal time is as expected for Route : " + route.get(j)
									+ " and Ticket : " + tickets.get(j) + " Actual is : -- and Expected is : -- because there is no disposal trip for ticket type : "+ticketType.get(j));
						}
						if (actualDisposaleRLTime.get(j) != null) {
							Report.FailTest(test, "Actual Disposal eRL time is not as expected for Route : "
									+ route.get(j) + " and Ticket : " + tickets.get(j) + " Actual is : "
									+ dateFormat.format(actualDisposaleRLTime.get(j).getTime()) + " Expected is --");
						} else {
							Report.PassTest(test, "ActualDisposal eRL time is as expected for Route : " + route.get(j)
									+ " and Ticket : " + tickets.get(j) + " Actual is : -- and Expected is : -- because there is no disposal trip for ticket type : "+ticketType.get(j));
						}
						if (actualDisposalVariance.get(j) != null) {
							Report.FailTest(test, "Actual Disposal Variance time is not as expected for Route : "
									+ route.get(j) + " and Ticket : " + tickets.get(j) + " Actual is : "
									+ dateFormat.format(actualDisposalVariance.get(j).getTime()) + " Expected is --");
						} else {
							Report.PassTest(test,
									"Actual Disposal Variance time is as expected for Route : " + route.get(j)
											+ " and Ticket : " + tickets.get(j)
											+ " Actual is : -- and Expected is : -- because there is no disposal trip for ticket type : "+ticketType.get(j));
						}
					} else {
						Report.FailTest(test,
								"Ticket should be among (E/R,S/O,DNR,LIV,FFY,FYN,FYS,BTN,BTY,BTS,DEL,DNE,REL,TRP) For Ticket : "
										+ tickets.get(j) + " actual ticket type is : " + ticketType.get(j));
					}

				}
			}
		} catch (Exception e) {
			LogClass.error(e.getMessage());
			Report.FailTest(test,
					"Driver Disposal Information is not as expected in Disposal Information accordion report");
		}

	}

}// last closed bracket
