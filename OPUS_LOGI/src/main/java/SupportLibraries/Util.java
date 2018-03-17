package SupportLibraries;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.swing.text.DefaultEditorKit.PasteAction;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.joda.time.Days;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ObjectRepository.General.FilterSection;
import Opus.BaseClass;
import net.sourceforge.htmlunit.corejs.javascript.regexp.SubString;


public class Util extends BaseClass {

	public static String scrFileName = null;
	public static String screenshotPath = System.getProperty("user.dir") + "/screenshots/";

	public static String capture(WebDriver driver, String screenshotName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "/screenshots/" + screenshotName + randomNum() + ".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);
		return dest;
	}

	public static void waitForElement(WebDriver driver, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {

			Report.FailTestSnapshot(test, driver, e.getMessage(), "Element not found");
		}

	}

	public static void waitForElementInvisible(WebDriver driver, WebElement element) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {

			Report.FailTestSnapshot(test, driver, e.getMessage(), "Element not found");
		}
	}

	public static void highLightElement(WebDriver driver, WebElement element) // public
																				// void
	{
		try {
			Util.waitForElement(driver, element);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
			js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);
		} catch (Exception e) {
			Report.FailTestSnapshot(test, driver, e.getMessage(), "Element not found");
		}
	}

	public static void SelectOption(WebDriver driver, WebElement element, String selectOptionValue) throws IOException {
		// Util.waitForElement(driver, element);
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(selectOptionValue);
	}

	public static void ElementExist(WebDriver driver, WebElement element) throws IOException {
		Util.waitForElement(driver, element);
		if (element.isDisplayed()) {
			Util.highLightElement(driver, element);
			Report.PassTest(test, element + " is getting displayed");
			// System.out.println(element +" is getting displayed");
		} else
			Report.FailTest(test, element + " not getting displayed");

	}

	public static void verifyColumnNames(WebElement tableID, String[] columnHeaderList) {
		String displayedcolumns[];
		// String columnvalue[] = (String) new String();
		// driver.findElement(By.xpath(".//*[@id='rdRows-2']/tbody/tr[@id='Row4']/td/div/div/table/thead/tr/th[1]")).getText().contains("LOB");
		int columncount = driver
				.findElements(By.xpath(".//*[@id='rdRows-2']/tbody/tr[@id='Row4']/td/div/div/table/thead/tr/th"))
				.size();
		String columnvalue[] = new String[columncount];

		// int a = columnvalue.length;
		for (int i = 0; i < columncount; i++) {
			// columnvalue[i] =
			// driver.findElement(By.xpath("[id='"+tableID+"']>thead>tr>th["+(i+1)+"]")).getText();
			columnvalue[i] = driver
					.findElement(By.xpath(
							".//*[@id='rdRows-2']/tbody/tr[@id='Row4']/td/div/div/table/thead/tr/th[" + (i + 1) + "]"))
					.getText();
		}
		Util.arraysAreIdentical(columnvalue, columnHeaderList);

	}

	public static void arraysAreIdentical(String[] arr1, String[] arr2) {
		if (arr1.length != arr2.length) {
			Report.FailTest(test, "No of columns are not matching");
		}
		for (int j = 0; j < arr1.length; j++) {
			if (arr1[j].contains(arr2[j])) {
				System.out.println("match");
			} else
				Report.FailTest(test, arr1[j] + " Value is not matching");
		}
	}

	public static void datavalidation_Int(int val1, int val2) throws ClassNotFoundException, SQLException {

		if (val1 != val2) {
			Report.FailTest(test, "Value is not matching");
		} else
			Report.PassTest(test, "Values " + val1 + " and " + val2 + " are matching");

	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public static void pageScroll(int x, int y) {
		try
		{
		Util.switchToDefaultWindow();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(" + x + "," + y + ")", "");
		}
		catch(Exception e)
		{
			Report.FailTest(test, "Not able to Scroll the page");
		}

	}

	public static void selectFrame(String frameName) {
		try {
			String[] frames = frameName.split("\\s*(=>|,|\\s)\\s*");
			int numOfFrames = frames.length;
			// System.out.println("Number of frames : "+numOfFrames);
			if (numOfFrames == 1) {
				driver.switchTo().frame(frameName);
				// Report.PassTest(test, "User switched to : "+frameName+"
				// frame");
			}

			else if (numOfFrames == 2) {
				driver.switchTo().frame(frames[0]).switchTo().frame(frames[1]);
				// Report.PassTest(test, "User switched to : "+frameName+"
				// frame");
			}

			else if (numOfFrames == 3) {
				driver.switchTo().frame(frames[0]).switchTo().frame(frames[1]).switchTo().frame(frames[2]);
				// Report.PassTest(test, "User switched to : "+frameName+"
				// frame");
			} else {
				Report.FailTest(test, "User is not able to switch to : " + frameName + " frame");
			}
		} catch (Exception e) {
			Report.FailTest(test, "Not able to switch to frame : " + frameName);
		}

	}

	public static void switchToDefaultWindow() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			Report.FailTest(test, "Not able to switch to default window");
		}

	}

	public static boolean compareTime(String actual, String expected) {
		int actualHours;
		int actualMins;
		int expectedHours;
		int expectedMins;
		String[] actualTime = actual.split(":");
		String[] expectedTime = expected.split(":");
		actualHours = Integer.parseInt(actualTime[0]);
		actualMins = Integer.parseInt(actualTime[1]);
		actualMins = (actualHours * 60) + actualMins;
		expectedHours = Integer.parseInt(expectedTime[0]);
		expectedMins = Integer.parseInt(expectedTime[1]);
		expectedMins = (expectedHours * 60) + expectedMins;

		if (((expectedMins - 1) <= actualMins & actualMins <=(expectedMins + 1)) || ((actualMins - 1) <=expectedMins  &  expectedMins <= (actualMins + 1)) )
			return true;
		else
			return false;
	}

	public static boolean compareTime(Date actualTime, Date expectedTime) {
		long ONE_MINUTE_IN_MILLIS = 60000;// millisecs
		Calendar cal = Calendar.getInstance();
		cal.setTime(actualTime);
		long t = cal.getTimeInMillis();
		Date startTime = new Date(t - (1 * ONE_MINUTE_IN_MILLIS));
		Date endTime = new Date(t + (1 * ONE_MINUTE_IN_MILLIS));
		return !(expectedTime.before(startTime) || expectedTime.after(endTime));

	}

	public static boolean compareNumber(double actual, double expected) {
		if (actual >= (expected - 1) && actual <= (expected + 1))
			return true;
		else
			return false;

	}

	public static boolean isTestCaseExecutable(String testCase, Excel xls) {
		/*
		 * @HELP
		 * 
		 * @class: TestUtil
		 * 
		 * @method: isTestCaseExecutable()
		 * 
		 * @parameter: String testCase, Xls_Reader xls
		 * 
		 * @notes: \ 1. Checks the runmode from the Test Cases sheet and
		 * executes the same accordingly
		 * 
		 * @returns: return object array
		 * 
		 * @END
		 */
		for (int rNum = 2; rNum <= xls.getRowCount("Test Cases"); rNum++) {
			if (testCase.equals(xls.getCellData("Test Cases", "TCID", rNum))) {
				if (xls.getCellData("Test Cases", "RunMode", rNum).contains("Y")) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public static void CaptureScreenshot(String testName) {

		/*
		 * @HELP
		 * 
		 * @Class: Keywords
		 * 
		 * @method: SwitchToMainWindow()
		 * 
		 * @parameter: None
		 * 
		 * @notes: After switching to a child window, should send control back
		 * to parent window
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got
		 * executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		try {
			File scrFile = null;
			scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			scrFileName = testName + " Passed AT - " + getCurrentTimeForScreenShot() + ".png";
			FileUtils.copyFile(scrFile, new File(screenshotPath + "\\" + scrFileName));
			System.out.println("Screenshot captured " + scrFileName);
		} catch (Exception e) {
			System.out.println("FAIL - Not able to take screenshot");
		}

	}

	public static String getCurrentTimeForScreenShot() {
		/*
		 * @HELP
		 * 
		 * @class: Constants
		 * 
		 * @method: getCurrentTimeForScreenShot ()
		 * 
		 * @parameter: No Parameters
		 * 
		 * @notes: Gets the current time stamp as per the format of
		 * "dd_MMM_hh-mm-ss_aaa(zzz)"
		 * 
		 * @returns: Returns the String in date format "dd_MMM_hh-mm-ss-aaa(zzz)
		 * 
		 * @END
		 */

		SimpleDateFormat sdf = new SimpleDateFormat("ddMM_hhmmss_aaa(zzz)");
		java.util.Date curDate = new java.util.Date();
		String strDate = sdf.format(curDate);
		String strActDate = strDate.toString();
		return strActDate;
	}

	public static boolean isRadioButtonSelected(WebElement element) {
		String str = element.getAttribute("checked");
		if (str.equalsIgnoreCase("True")) {
			System.out.println("Radio button selected");
			return true;
		} else {
			System.out.println("Radio button not selected");
			return false;
		}
	}

	public static void Hover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	public static void HoverAndClick(WebDriver driver, WebElement elementToHover, WebElement elementToClick) {
		Actions action = new Actions(driver);
		action.moveToElement(elementToHover).click(elementToClick).build().perform();
	}

	public static void pageReferesh() throws InterruptedException {
		Thread.sleep(1000);
		driver.navigate().refresh();
	}

	public static void HoverAndGetText(WebDriver driver, WebElement elementToHover) throws IOException {
		Actions action = new Actions(driver);
		action.click(elementToHover).build().perform();
		Report.PassTestScreenshot(test, driver, "Graph Over is getting displayed", "GraphOverLay");

	}

	public static void deleteAllCookies() {
		driver.manage().deleteAllCookies();

	}

	public static List<String> getDataFromDB(String sqlQuery) {
		/*
		 * @HELP
		 * 
		 * @class: Util
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return expectedData;
	}

	public static List<Date> getDateDataFromDB(String sqlQuery) {
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

	public static String switchBrowserTabs() {

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1)); // will switch to new tab opened
		String url = driver.getCurrentUrl();
		System.out.println(url);
		driver.close();
		driver.switchTo().window(tabs.get(0));
		return url;
	}

	public static void switchToNewTab() {
		try {
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1)); // will switch to new tab
													// opened
		} catch (Exception e) {
			Report.FailTest(test, "Not able to Switch to the new tab opened");
			Report.FailTest(test, e.getMessage());
		}
	}

	public static void switchToDefaultTab() {
		try {
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(0)); // will switch to new tab
			Thread.sleep(2000);										// opened
		} catch (Exception e) {
			Report.FailTest(test, "Not able to Switch to the default tab opened");
			Report.FailTest(test, e.getMessage());
		}

	}

	public static String sqlFormatedList(List<String> codeList) {
		StringBuilder sb = new StringBuilder();
		// sb.append("(");
		for (String str : codeList) {
			sb.append("'" + str + "',");
		}
		sb.deleteCharAt(sb.length() - 1);
		// sb.append(")");
		return sb.toString();
	}

	public static String randomNum() throws IOException {
		return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()).toString();
	}

	public static boolean isNotNullNotEmptyNotWhiteSpace(String CmpVal) {
		CmpVal = CmpVal.replaceAll("\u00a0", "");
		CmpVal = CmpVal.replaceAll("&nbsp", "").trim();
		if (CmpVal.trim() != "" && CmpVal != null && (CmpVal.isEmpty()) == false) {
			return false;
		} else {
			return true;
		}
		// return CmpVal != null && !string.isEmpty() &&
		// !string.trim().isEmpty();
	}

	/////////////////// ========= To calender object should be opened
	/////////////////// ========////////////////////////// '
	public static void datepicker(WebDriver driver, WebElement CaledrEleWbTbl, String DateToSelect) throws IOException

	{
		Util.highLightElement(driver, CaledrEleWbTbl);
		List<WebElement> columns = CaledrEleWbTbl.findElements(By.tagName("td"));

		LocalDate localDateT = LocalDate.now();
		LocalDate localDateT_1 = null;

		if (DateToSelect.toLowerCase().contains("+".toLowerCase())) {
			//// it contain the plus and we need t+ X value ///////
			// DateToSelect = DateToSelect.replaceAll("[\n\r\\s]", "");
			// String[] DateToSelectArr = DateToSelect.split("+");//
			//// DateToSelect.split("+");
			String[] DateToSelectArr = DateToSelect.split("\\+");
			String DayNum = DateToSelectArr[1];
			int DaysToIncrement = Integer.parseInt(DayNum.trim());
			System.out.println("Days to increase >>" + DaysToIncrement);
			localDateT_1 = localDateT.plus(DaysToIncrement, ChronoUnit.DAYS);
		} else if (DateToSelect.toLowerCase().contains("-".toLowerCase())) {
			//// it contain the plus and we need t - X value ///////

			String[] DateToSelectArr = DateToSelect.split("\\-");
			String DayNum = DateToSelectArr[1];
			int DaysToIncrement = Integer.parseInt(DayNum.trim());
			System.out.println("Days to decrease >>" + DaysToIncrement);
			localDateT_1 = localDateT.minus(DaysToIncrement, ChronoUnit.DAYS);
		} else {
			//////// Do nothing we need current date only////////////////////
			localDateT_1 = localDateT.plus(0, ChronoUnit.DAYS);
		}

		// System.out.println("Date Time " + localDateT_1);
		// System.out.println(DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate));
		String[] dateParts = DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDateT_1).split("-");
		String SrvcDay = dateParts[2]; // String month = dateParts[1]; //String
		// year = dateParts[2];
		System.out.println("SrvcDay>> " + SrvcDay);
		if (SrvcDay.trim().subSequence(0, 1).equals("0")) {
			SrvcDay = SrvcDay.trim().replace("0", "");
		}
		for (WebElement cell : columns) { // Select 13th Date
			Util.highLightElement(driver, cell);

			/*
			 * if (cell.getText().equals(SrvcDay)) { ////// old working code
			 * ///// {
			 * //System.out.println("Inside the date table link click ");
			 * cell.findElement(By.linkText(SrvcDay)).click(); break; }
			 */ ////// old working code /////

			////// this will select the next date if our date is not selectable
			if (Util.isNotNullNotEmptyNotWhiteSpace(cell.getText()) == false) {
				if (Integer.parseInt(cell.getText()) >= (Integer.parseInt(SrvcDay))
						&& (cell.getAttribute("class")).trim().contains("disabled") == false) {
					// System.out.println("Inside the date table link click ");
					// cell.findElement(By.linkText(SrvcDay)).click();
					cell.findElement(By.linkText(cell.getText())).click();
					break;
				}
			}

		}
	}//// End of function

	public static void generateExcelReport()

	{
		File xmlFile = new File(System.getProperty("user.dir") + "/target/surefire-reports/testng-results.xml");
		try {
			if (xmlFile.isFile()) {
				DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
				DocumentBuilder build = fact.newDocumentBuilder();
				org.w3c.dom.Document doc = build.parse(xmlFile);
				doc.getDocumentElement().normalize();
				NodeList test_list = doc.getElementsByTagName("test");
				System.out.println("Size of test list : " + test_list.getLength());
				for (int i = 0; i < test_list.getLength(); i++) {
					Node test_node = test_list.item(i);
					if (test_list.item(i).getNodeType() == Node.ELEMENT_NODE) {
						String test_name = ((Element) test_node).getAttribute("name");
						System.out.println("Test Name : " + test_name);
						NodeList class_list = ((Element) test_node).getElementsByTagName("class");
						System.out.println("Size of class list : " + class_list.getLength());
						for (int j = 0; j < class_list.getLength(); j++) {
							Node class_node = class_list.item(i);
							if (class_list.item(i).getNodeType() == Node.ELEMENT_NODE) {
								String class_name = ((Element) class_node).getAttribute("name");
								System.out.println("Class Name : " + class_name);
							}
						}

					}

				}

			} else {
				System.out.println("Testng result xml is not available");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static double getEMAPData(String routeName, String LOB, String columnName, Excel EMAP) {

		/**
		 * @HELP
		 * 
		 * @class: Util
		 * 
		 * @method: getPlanEfficiency()
		 * 
		 * @parameter: String routeName,String LOB, Xls_Reader xls
		 * 
		 * @notes: \ 1. Checks the plan efficiency column in excel for the sheet
		 * of the LOB
		 * 
		 * @returns: return object array
		 * 
		 * @END
		 */
		double planEMAPValue = 0.00;
		String excelRouteName = "";

		try {
		for (int rNum = 2; rNum <= EMAP.getRowCount(LOB); rNum++) {
			excelRouteName = EMAP.getCellData(LOB, "Route", rNum);
			System.out.println(EMAP.getCellData(LOB, "Route", rNum) + ": " + EMAP.getCellData(LOB, "PlanEff", rNum)
					+ " : " + EMAP.getCellData(LOB, "PlanUnits", rNum) + " : "
					+ EMAP.getCellData(LOB, "PlanDriverHours", rNum));
			if (excelRouteName.contains(routeName)) {
				planEMAPValue = Double.parseDouble(EMAP.getCellData(LOB, columnName, rNum));
				return planEMAPValue;
			}
		}
		}
		catch (Exception e) {
			Report.FailTest(test, "Unable to Read EMAp Sheet When LOB is " + LOB + "And Emap Value is" + planEMAPValue);
		}
		return planEMAPValue;
		
	}

	public static String getEMAPDataTimeValue(String routeName, String LOB, String columnName, Excel EMAP) {

		/**
		 * @HELP
		 * 
		 * @class: Util
		 * 
		 * @method: getPlanEfficiency()
		 * 
		 * @parameter: String routeName,String LOB, Xls_Reader xls
		 * 
		 * @notes: \ 1. Checks the plan efficiency column in excel for the sheet
		 * of the LOB
		 * 
		 * @returns: return object array
		 * 
		 * @END
		 */
		
		String planEMAPValue = "";
		String excelRouteName = "";
     try {
    	 	

		for (int rNum = 2; rNum <= EMAP.getRowCount(LOB); rNum++) {
			excelRouteName = EMAP.getCellData(LOB, "Route", rNum);
			System.out.println(EMAP.getCellData(LOB, "Route", rNum) + ": " + EMAP.getCellData(LOB, "PlanEff", rNum)
					+ " : " + EMAP.getCellData(LOB, "PlanUnits", rNum) + " : "
					+ EMAP.getCellData(LOB, "PlanDriverHours", rNum));
			if (excelRouteName.contains(routeName)) {
				planEMAPValue = EMAP.getCellData(LOB, columnName, rNum);
				return planEMAPValue;
			}
		}
     		}
			catch (Exception e) {
				Report.FailTest(test, "Unable to Read EAMP Data Sheet When LOB is " + LOB + "And Emap Plan Value is" + planEMAPValue);
			}
		
		return planEMAPValue;
		
		
	}

	///////////////// Example : From 0 to 1000 ///////////////////
	public static void PageScrollDown(int From, int To) {
		JavascriptExecutor Scroll = (JavascriptExecutor) driver;
		Scroll.executeScript("window.scrollBy(" + From + "," + To + ")", "");

	}

	//////////////// Example : 0 to -1000 ///////////////////////////
	public static void PageScrollUp(int To, int From) {

		JavascriptExecutor Scroll = (JavascriptExecutor) driver;
		Scroll.executeScript("window.scrollBy(" + To + "," + From + ")", "");

	}

	/////////////// Filter Sorting ///////////////
	public static void FilterSorting(String TabelName, String ColumnName) throws InterruptedException {
		try {

			String Beforestatus = driver.findElement(By.xpath("//*[@id='" + TabelName + "']/thead/tr/th"))
					.getAttribute("class");
			System.out.println("Before Click Status is >>" + Beforestatus);
			driver.findElement(By.xpath("//*[@id='" + TabelName + "']/thead/tr/th[text()='" + ColumnName + "']"))
					.click();
			Thread.sleep(2000);
			String Afterstatus = driver.findElement(By.xpath("//*[@id='" + TabelName + "']/thead/tr/th"))
					.getAttribute("class");
			System.out.println("After Click Status is >>" + Afterstatus);
			if (Afterstatus.contains("disabled")) {

				System.out.println("Sorting is working fine for >" + ColumnName);

			} else {
				System.out.println("Sorting is not working fine for >" + ColumnName);
			}

		} catch (Exception e) {

			Report.FailTestSnapshot(test, driver, e.getMessage(), "Sorting is not working fine when Table is > "
					+ TabelName + " and " + "Column Name is" + ColumnName);
		}
		driver.switchTo().defaultContent();
	}

	public static void switchBrowserTabsAndStay() {

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1)); // will switch to new tab opened

	}

	public static void keystrokes_ControlS() throws AWTException, InterruptedException {
		// String[]keystroke = keystrokes.split(";");
		Robot robot = new Robot();
		Thread.sleep(5000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		// robot.keyPress(KeyEvent.);
		Thread.sleep(2000);
		System.out.println("About to get saved");
		// Actions builder = new Actions(driver);
		// builder.keyDown(Keys.CONTROL);

	}

	public static String getPDFText(String filepath) {
		String text = "";

		try {
			File tempfile = new File(filepath);
			text = getText(tempfile);
			return text;
		} catch (IOException e) {
			e.printStackTrace();
			return text;

		}

	}

	public static String getText(File pdfFile) throws IOException {
		PDDocument doc = PDDocument.load(pdfFile);
		return new PDFTextStripper().getText(doc);
	}

	public static File getLatestFilefromDir(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	public static String getTimeDifferenceInString(String time1, String time2) {
		String[] splitTime1 = time1.split(":", 2);
		int time1Mins = Integer.valueOf(splitTime1[0]) * 60 + Integer.valueOf(splitTime1[1]);
		String[] splitTime2 = time2.split(":", 2);
		int time2Mins = Integer.valueOf(splitTime2[0]) * 60 + Integer.valueOf(splitTime2[1]);

		int timeDiff = time1Mins - time2Mins;
		if (timeDiff < 0) {
			if (((int) (timeDiff / 60) < 0) & ((int) (timeDiff / 60) >= -9)) {
				return "-" + (String.format("%02d", -(int) (timeDiff / 60))) + ":"
						+ (String.format("%02d", -(timeDiff % 60)));
			} else {
				return (String.format("%02d", (int) (timeDiff / 60))) + ":" + (String.format("%02d", -(timeDiff % 60)));
			}
		} else {
			return (String.format("%02d", (int) (timeDiff / 60))) + ":" + (String.format("%02d", (timeDiff % 60)));
		}
	}

	public static int convertHoursToMins(String time) {
		String[] splitTime = time.split(":", 2);
		int timeMins = Integer.valueOf(splitTime[0]) * 60 + Integer.valueOf(splitTime[1]);
		return timeMins;

	}
	
	public static double convertTimeToHours(String time) {
		String[] splitTime = time.split(":", 2);		
		double hours= Double.valueOf(splitTime[0])+(Double.valueOf(splitTime[1])/60);
		return hours;

	}

	public static String convertMinsToHours(int timeInMins) {

		if (timeInMins < 0) {
			if (((int) (timeInMins / 60) < 0) & ((int) (timeInMins / 60) >= -9)) {
				return "-" + (String.format("%02d", -(int) (timeInMins / 60))) + ":"
						+ (String.format("%02d", -(timeInMins % 60)));
			} else {
				return (String.format("%02d", (int) (timeInMins / 60))) + ":"
						+ (String.format("%02d", -(timeInMins % 60)));
			}
		} else {
			return (String.format("%02d", (int) (timeInMins / 60))) + ":" + (String.format("%02d", (timeInMins % 60)));
		}

	}

	public static String DateTimeString() {

		int MyDay = LocalDateTime.now().getDayOfMonth(); // dd
		int MyYear = LocalDateTime.now().getYear(); // yyyy
		int MyMonth = LocalDateTime.now().getMonthValue(); // yyyy
		int Myhours = LocalDateTime.now().getHour();
		int Mymins = LocalDateTime.now().getMinute();
		int Mysecs = LocalDateTime.now().getSecond();

		final String CureentDtTm = (String.valueOf(MyMonth) + String.valueOf(MyDay) + String.valueOf(MyYear)
				+ String.valueOf(Myhours) + String.valueOf(Mymins) + String.valueOf(Mysecs));
		return CureentDtTm;

	}

	public static void validateFieldDecimalPlaces(double actualValue, int noOfDecimals, String columnName) {
		try {
			String string = Double.toString(Math.abs(actualValue));
			int index = string.indexOf(".");
			int actualDecimalPlaces = index < 0 ? 0 : string.length() - index - 1;

			if (actualDecimalPlaces == noOfDecimals) {
				Report.PassTest(test, "Format of the column " + columnName + " is as expected. Actual Decimal Places: "
						+ actualDecimalPlaces + " and Expected Decimal Places: " + noOfDecimals);
			} else {
				Report.FailTest(test, "Format of the column " + columnName + " is incorrect. Actual Decimal Places: "
						+ actualDecimalPlaces + " and Expected Decimal Places: " + noOfDecimals);
			}

		} catch (Exception e) {
			Report.FailTest(test, "Not able to validate format of the field" + columnName);
		}

	}

	public static void validateFieldDecimalPlaces(String actualValue, int noOfDecimals, String columnName) {
		try {

			int index = actualValue.indexOf(".");
			int actualDecimalPlaces = index < 0 ? 0 : actualValue.length() - index - 1;

			if (actualDecimalPlaces == noOfDecimals) {
				Report.PassTest(test, "Format of the column " + columnName + " is as expected. Actual Decimal Places: "
						+ actualDecimalPlaces + " and Expected Decimal Places: " + noOfDecimals);
			} else {
				Report.FailTest(test, "Format of the column " + columnName + " is incorrect. Actual Decimal Places: "
						+ actualDecimalPlaces + " and Expected Decimal Places: " + noOfDecimals);
			}

		} catch (Exception e) {
			Report.FailTest(test, "Not able to validate format of the field" + columnName);
		}

	}

	public static void validateTimeFormat(String actualValue, String columnName) {
		try {
			String[] time = actualValue.split(":");
			int hourValue = Integer.parseInt(time[0]);
			int minValue = Integer.parseInt(time[1]);
			if ((hourValue >= 0 & minValue >= 0) || (hourValue <= 0 & minValue >= 0)) {
				Report.PassTest(test,
						"Format of the column " + columnName + " is as expected (h:m). Actual Value: " + actualValue);
			} else {
				Report.FailTest(test, "Format of the column " + columnName
						+ " is incorrect. Should be in h:m Actual Value: " + actualValue);
			}

		} catch (Exception e) {
			Report.FailTest(test, "Not able to validate format of the field" + columnName);
		}

	}

	public static void validateWholeNumber(int actualValue, String columnName) {
		try {
			if ((actualValue % 1) != 0) {
				Report.FailTest(test, "Format of the column " + columnName
						+ " is incorrect. Should be a whole Number Actual Value: " + actualValue);
			} else {
				Report.PassTest(test,
						"Format of the column " + columnName + " is as expected. Actual Value: " + actualValue);
			}

		} catch (Exception e) {
			Report.FailTest(test, "Not able to validate format of the field" + columnName);
		}
	}

	public static void validateSortingFunctionality(String ReportName, String SubView, String columnName,
			String sortingType, String columnType, String TabName) {
		try {
			ArrayList<Double> obtainedListDouble = new ArrayList<>();
			ArrayList<Double> sortedListDouble = new ArrayList<>();
			ArrayList<String> obtainedListString = new ArrayList<>();
			ArrayList<String> sortedListString = new ArrayList<>();
			List<WebElement> elementList = null;

			switchToDefaultWindow();
			selectFrame("opusReportContainer,subReportContainer");
			if (TabName.equals("All")) {
				driver.findElement(
						By.xpath("//table[@id='t2']/thead/tr/th[contains(@id,'col" + columnName + "-TH')]/a")).click();
				Thread.sleep(2000);
				elementList = driver.findElements(
						By.xpath("//table[@id='t2']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='t2']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
			} else if (TabName.equals("Helper"))

			{
				driver.findElement(
						By.xpath("//table[@id='t2h']/thead/tr/th[contains(@id,'col" + columnName + "-TH')]/a")).click();
				Thread.sleep(2000);
				elementList = driver.findElements(
						By.xpath("//table[@id='t2h']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='t2h']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));

			} else if (TabName.equals("Exceptions")) {
				driver.findElement(
						By.xpath("//table[@id='t2e']/thead/tr/th[contains(@id,'col" + columnName + "-TH')]/a")).click();
				Thread.sleep(2000);
				elementList = driver.findElements(
						By.xpath("//table[@id='t2e']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='t2e']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
			}
			else if (TabName.equals("RouteSummary")) {
				
				driver.findElement(
						By.xpath("//table[@id='dtRouteDetail']/thead/tr/th[contains(@id,'col" + columnName + "-TH')]/a")).click();
				Thread.sleep(2000);
				elementList = driver.findElements(
						By.xpath("//table[@id='dtRouteDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtRouteDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
				
					
							
			}

			else if (TabName.equals("Efficiency Summary"))

			{
				driver.findElement(
						By.xpath("//table[@id='dtEfficiencyDetail']/thead/tr/th[contains(@id,'col" + columnName + "-TH')]/a")).click();
				Thread.sleep(2000);
				elementList = driver.findElements(
						By.xpath("//table[@id='dtEfficiencyDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtEfficiencyDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtEfficiencyDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]"));

			}

			else if (TabName.equals("PreRouteSummary")) {
							
							driver.findElement(
									By.xpath("//table[@id='dtPreRouteDetail']/thead/tr/th[contains(@id,'col" + columnName + "-TH')]/a")).click();
							Thread.sleep(2000);
							elementList = driver.findElements(
									By.xpath("//table[@id='dtPreRouteDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
							if (!(elementList.size() > 0))
								elementList = driver.findElements(
										By.xpath("//table[@id='dtPreRouteDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
											
										
						}
			else if (TabName.equals("Idle Summary"))

			{
				driver.findElement(
						By.xpath("//table[@id='dtIdleTmDetail']/thead/tr/th[contains(@id,'col" + columnName + "-TH')]/a")).click();
				Thread.sleep(2000);
				elementList = driver.findElements(
						By.xpath("//table[@id='dtIdleTmDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtIdleTmDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtIdleTmDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]"));

			}
			
			else if (TabName.equals("Disposal Cycle Summary"))

			{
				driver.findElement(
						By.xpath("//table[@id='dtDisposalTimeDetail']/thead/tr/th[contains(@id,'col" + columnName + "-TH')]/a")).click();
				Thread.sleep(2000);
				elementList = driver.findElements(
						By.xpath("//table[@id='dtDisposalTimeDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtDisposalTimeDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtDisposalTimeDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]"));

			}
			
			else if (TabName.equals("PostRouteSummary"))

			{
				driver.findElement(
						By.xpath("//table[@id='dtPostRouteDetail']/thead/tr/th[contains(@id,'col" + columnName + "-TH')]/a")).click();
				Thread.sleep(2000);
				elementList = driver.findElements(
						By.xpath("//table[@id='dtPostRouteDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtPostRouteDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtPostRouteDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]"));

			}
			
			else if (TabName.equals("Sequence Compliance Summary"))

			{
				driver.findElement(
						By.xpath("//table[@id='dtSeqComplianceDetail']/thead/tr/th[contains(@id,'col" + columnName + "-TH')]/a")).click();
				Thread.sleep(2000);
				elementList = driver.findElements(
						By.xpath("//table[@id='dtSeqComplianceDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtSeqComplianceDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtSeqComplianceDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]"));

			}
			

			else if (TabName.equals("dtKronos"))

			{
				driver.findElement(
						By.xpath("//table[@id='dtKronos']/thead/tr/th[contains(@id,'col" + columnName + "-TH')]/a")).click();
				Thread.sleep(2000);
				elementList = driver.findElements(
						By.xpath("//table[@id='dtKronos']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtKronos']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtKronos']/tbody/tr/td[contains(@id,'col" + columnName + "_')]"));
				
			}

			else if (TabName.equals("Service Exceptions Summary"))

			{
				driver.findElement(
						By.xpath("//table[@id='dtSvcExceptionsDetail']/thead/tr/th[contains(@id,'col" + columnName + "-TH')]/a")).click();
				Thread.sleep(2000);
				elementList = driver.findElements(
						By.xpath("//table[@id='dtSvcExceptionsDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtSvcExceptionsDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtSvcExceptionsDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]"));


			}
				
			else if (TabName.equals("Sales Information Detail"))

			{
				driver.findElement(
						By.xpath("//table[@id='dtSalesInfo']/thead/tr/th[contains(@id,'col" + columnName + "-TH')]/a")).click();
				Thread.sleep(2000);
				elementList = driver.findElements(
						By.xpath("//table[@id='dtSalesInfo']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtSalesInfo']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtSalesInfo']/tbody/tr/td[contains(@id,'col" + columnName + "_')]"));


			}
			
			if (elementList.size() > 0) {
				if (columnType.equals("Double"))

				{
					for (WebElement we : elementList) {
						// System.out.println(we.getText().trim());
						if(we.getText().equals("N/A") || we.getText().equals("UNK"))
						obtainedListDouble.add(0.00);
						else
						obtainedListDouble.add(Double.parseDouble(we.getText().trim().replaceAll(",", "")));
					}

					for (Double d : obtainedListDouble) {
						sortedListDouble.add(d);
					}
					if (sortingType.equals("Ascending")) {
						Collections.sort(sortedListDouble);
					} else if (sortingType.equals("Descending")) {
						Collections.sort(sortedListDouble);
						Collections.reverse(sortedListDouble);
					}

					System.out.println("Obtained List : " + obtainedListDouble.toString());
					System.out.println("Sorted List : " + sortedListDouble.toString());

					if (sortedListDouble.equals(obtainedListDouble)) {

						Report.PassTestScreenshot(test, driver,
								ReportName + " : " + SubView + " : Sorting working fine for column : " + columnName,
								columnName + " sorted " + sortingType);
					} else {
						Report.FailTestSnapshot(test, driver,
								"Sorting not working as expected for column : " + columnName,
								columnName + " sorted " + sortingType);
					}

				} else if (columnType.equals("String")) {
					for (WebElement we : elementList) {
						obtainedListString.add(we.getText().trim());
					}

					for (String s : obtainedListString) {
						sortedListString.add(s);
					}
					if (sortingType.equals("Ascending")) {
						Collections.sort(sortedListString);
					} else if (sortingType.equals("Descending")) {
						Collections.sort(sortedListString);
						Collections.reverse(sortedListString);
					}

					System.out.println("Obtained List : " + obtainedListString.toString());
					System.out.println("Sorted List : " + sortedListString.toString());

					if (sortedListString.equals(obtainedListString)) {

						Report.PassTestScreenshot(test, driver, "Sorting working fine for column : " + columnName,
								columnName + " sorted " + sortingType);
					} else {
						Report.FailTestSnapshot(test, driver,
								"Sorting not working as expected for column : " + columnName,
								columnName + " sorted " + sortingType);
					}

				}

				if (columnType.equals("Time"))

				{
					for (WebElement we : elementList) {
						String timeInDouble = we.getText().replaceAll(":", ".").replaceAll("AM", "").replaceAll("PM",
								"");
						obtainedListDouble.add(Double.parseDouble(timeInDouble.trim()));
					}

					for (Double d : obtainedListDouble) {
						sortedListDouble.add(d);
					}
					if (sortingType.equals("Ascending")) {
						Collections.sort(sortedListDouble);
					} else if (sortingType.equals("Descending")) {
						Collections.sort(sortedListDouble);
						Collections.reverse(sortedListDouble);
					}

					System.out.println("Obtained List : " + obtainedListDouble.toString());
					System.out.println("Sorted List : " + sortedListDouble.toString());

					if (sortedListDouble.equals(obtainedListDouble)) {

						Report.PassTestScreenshot(test, driver, "Sorting working fine for column : " + columnName,
								columnName + " sorted " + sortingType);
					} else {
						Report.FailTestSnapshot(test, driver,
								"Sorting not working as expected for column : " + columnName,
								columnName + " sorted " + sortingType);
					}

				}

			}

			else {
				Report.FailTestSnapshot(test, driver, "No Data in detail section to test sorting for " + columnName,
						"Detail Section column " + columnName);
			}

		
		}catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Sorting not working as expected for column : " + columnName,
					columnName + " sorted " + sortingType);
		}
	}

	public static void validateExportToExcelColumns(String exportToExcelButton, List<String> excelexportcolumns,
			String dirPath,int startRow) {

		try {
			switchToDefaultWindow();
			selectFrame("opusReportContainer,subReportContainer");
			
			try
			{
				driver.findElement(By.id(exportToExcelButton)).click();
			}
			catch(Exception e)
			{
				try {
					driver.findElement(By.xpath("(//div[not(@style = 'display: none;') and contains(@id,'rdTabPanel')]//a[@id='"+exportToExcelButton+"'])[1]")).click();
				} catch (Exception e2) {
					driver.findElement(By.xpath("(//a[@id='"+exportToExcelButton+"'])[2]")).click();		
					}
			
			}
			Thread.sleep(6000);
			File recentFile = Util.getLatestFilefromDir(dirPath);
			Excel exportExcelFile = new Excel(dirPath + "/" + recentFile.getName());
			validateColumnNamesInExcel(exportExcelFile, excelexportcolumns, startRow);
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			LogClass.error(e.getMessage());
			Report.FailTest(test, "Export to excel is not working as expected. File didn't get downloaded from the application.");
		}

	}

	public static void validateExportExcelData(String dirPath, String reportName, String colName, int dataStartRowNum,
			List<String> dataListFromUI) {
		try {

			File recentFile = Util.getLatestFilefromDir(dirPath);
			Excel exportExcelFile = new Excel(dirPath + "/" + recentFile.getName());
			List<String> dataListFromExcel = exportExcelFile.getColumnData(dirPath + "/" + recentFile.getName(),
					colName, dataStartRowNum, reportName);
			validateExcelAndUIData(dataListFromUI, dataListFromExcel, reportName, colName);
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			LogClass.error(e.getMessage());
			Report.FailTest(test, "Export to excel is not as expected for commercial flash report");
		}

	}

	public static void validateExcelAndUIData(List<String> dataListFromUI, List<String> dataListFromExcel,
			String reportName, String columnName) {
		try {
			
			if(dataListFromUI.size()<dataListFromExcel.size())
			{
				for(int i=0;i<dataListFromUI.size();i++)
				{
					if (dataListFromExcel.contains(dataListFromUI.get(i))) {

						Report.PassTest(test, "Data in Export Excel for Report : " + reportName + " for column Name : "
								+ columnName + " is working fine");
					} else {
						Report.FailTest(test,
								"Data in Export Excel for Report : " + reportName + " for column Name : " + columnName
										+ " is not as expected Data On UI : " + dataListFromUI.toString()
										+ " and Data In Excel Export : " + dataListFromExcel.toString());
					}
				}
			}
			else if(dataListFromUI.size()==dataListFromExcel.size())
			{
			if (dataListFromUI.equals(dataListFromExcel)) {

				Report.PassTest(test, "Data in Export Excel for Report : " + reportName + "for column Name : "
						+ columnName + " is working fine");
			} else {
				Report.FailTest(test,
						"Data in Export Excel for Report : " + reportName + "for column Name : " + columnName
								+ " is not as expected Data On UI : " + dataListFromUI.toString()
								+ " and Data In Excel Export : " + dataListFromExcel.toString());
			}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			LogClass.error(e.getMessage());
			Report.FailTest(test, "Export to excel is not as expected for commercial flash report");
		}
	}

	public static void validateColumnNamesInExcel(Excel exportExcelFile, List<String> excelexportcolumns,
			int headerRowNum) {

		String expectedExcelColumn = "";

		try {
			for (int i = 0; i < excelexportcolumns.size(); i++) {
				expectedExcelColumn = exportExcelFile.getCellData(headerRowNum - 1, i);
				System.out.println(excelexportcolumns.get(i));
				System.out.println(expectedExcelColumn);
				if (expectedExcelColumn.equals(excelexportcolumns.get(i))) {
					Report.PassTest(test, "Column name is correct in Excel Export File. Actual is "
							+ excelexportcolumns.get(i) + " and Expected is " + expectedExcelColumn);
				} else {
					Report.FailTest(test, "Column name is incorrect in excel file. Actual is "
							+ excelexportcolumns.get(i) + " and Expected is " + expectedExcelColumn);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, e.getMessage());
			LogClass.error(e.getMessage());
			Report.FailTest(test, "Export to excel file is not as expected");
		}

	}

	public static List<String> getUIColumnData(String reportName, String reportLevel, String columnName,
			String TabName) {
		ArrayList<String> obtainedListString = new ArrayList<>();
		try {
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");
			/*
			 * int noOfPages = Integer .parseInt(driver.findElement(By.xpath(
			 * "//span[@id='t2-of']//following-sibling::span")).getText());
			 */

			List<WebElement> elementList = null;

			/* for (int i = 0; i < noOfPages; i++) { */

			if (TabName.equals("All")) {

				elementList = driver.findElements(
						By.xpath("//table[@id='t2']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='t2']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
			} else if (TabName.equals("Helper"))

			{
				elementList = driver.findElements(
						By.xpath("//table[@id='t2h']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='t2h']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));

			} else if (TabName.equals("Exceptions")) {
				elementList = driver.findElements(
						By.xpath("//table[@id='t2e']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='t2e']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
			}

			else if (TabName.equals("RouteSummary")) {
				elementList=driver.findElements(By.xpath("//*[@id='dtRouteDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList=driver.findElements(By.xpath(""));		
			}
			
			else if (TabName.equals("Efficiency Summary")) {
				elementList = driver.findElements(
						By.xpath("//table[@id='dtEfficiencyDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtEfficiencyDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
			
				if(!(elementList.size() > 0))
					elementList=driver.findElements(By.xpath("//table[@id='dtEfficiencyDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]"));
			}
			
			 else if (TabName.equals("Idle Summary")) {
					elementList = driver.findElements(
							By.xpath("//table[@id='dtIdleTmDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
					if (!(elementList.size() > 0))
						elementList = driver.findElements(
								By.xpath("//table[@id='dtIdleTmDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
				}
			
			 else if (TabName.equals("Sequence Compliance Summary")) {
					elementList = driver.findElements(
							By.xpath("//table[@id='dtSeqComplianceDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
					if (!(elementList.size() > 0))
						elementList = driver.findElements(
								By.xpath("//table[@id='dtSeqComplianceDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
				}
			
			 else if (TabName.equals("Disposal Cycle Summary")) {
					elementList = driver.findElements(
							By.xpath("//table[@id='dtDisposalTimeDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
					if (!(elementList.size() > 0))
						elementList = driver.findElements(
								By.xpath("//table[@id='dtDisposalTimeDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
				}
			
			 else if (TabName.equals("Service Exceptions Summary")) {
					elementList = driver.findElements(
							By.xpath("//table[@id='dtSvcExceptionsDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
					if (!(elementList.size() > 0))
						elementList = driver.findElements(
								By.xpath("//table[@id='dtSvcExceptionsDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
				}

			else if (TabName.equals("PreRouteSummary")) {
				elementList=driver.findElements(By.xpath("//*[@id='dtPreRouteDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList=driver.findElements(By.xpath(""));		
			}
			
			else if (TabName.equals("PostRouteSummary")) {
				elementList=driver.findElements(By.xpath("//*[@id='dtPostRouteDetail']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList=driver.findElements(By.xpath(""));		
			}

			else if (TabName.equals("NetIdleDrillDown")) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer,subIdleDetail_Row1"); 
				elementList = driver.findElements(
						By.xpath("//table[@id='t2Idle']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='t2Idle']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
			}
			else if (TabName.equals("DisposalCycleDrilldown")) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer,subIDspDetailAll_Row1");
				elementList = driver.findElements(
						By.xpath("//table[@id='t2e']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='t2Idle']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
			}
			else if (TabName.equals("TotalExceptionsDrillDown")) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer,subIdleDetail_Row1");
				elementList = driver.findElements(
						By.xpath("//table[@id='t3']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='t3']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
			}
			
			else if (TabName.equals("TotalStopsDrillDown")) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer,subSeqComplianceDetail_Row1");
				elementList = driver.findElements(
						By.xpath("//table[@id='t2Idle']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='t2Idle']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
			}
			else if (TabName.equals("CustomerPropertyDrillDown")) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer,subCustTime_Row1");
				elementList = driver.findElements(
						By.xpath("//table[@id='CustTime_Sub']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='CustTime_Sub']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
			}
			else if (TabName.equals("dtKronos")) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				elementList = driver.findElements(
						By.xpath("//table[@id='dtKronos']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtKronos']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
			}
			else if (TabName.equals("dtExcpPopUp")) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				elementList = driver.findElements(
						By.xpath("//table[@id='dtExcpPopUp']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtExcpPopUp']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
			}
			else if (TabName.equals("dtSalesInfo")) {
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				elementList = driver.findElements(
						By.xpath("//table[@id='dtSalesInfo']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/span"));
				if (!(elementList.size() > 0))
					elementList = driver.findElements(
							By.xpath("//table[@id='dtSalesInfo']/tbody/tr/td[contains(@id,'col" + columnName + "_')]/a/span"));
			}

			if (elementList.size() > 0) {

				for (WebElement we : elementList) {

					obtainedListString.add(we.getText().trim().replaceAll(",", "").trim());

				}
			}



			else {
				Report.FailTestSnapshot(test, driver, "No Data in detail section for " + columnName,
						"Detail Section column " + columnName);
			}

			/*
			 * driver.findElement(By.xpath(
			 * "//*[@id='t2-NextPageCaption']//ancestor::a")).click(); }
			 */

			return obtainedListString;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTestSnapshot(test, driver, "Not able to read data from UI for column name : " + columnName,
					"UI Data Missing");
		}

		return obtainedListString;
	}

	public static void openLinkInNewTab(String reportName) {
		try {
			String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
			driver.findElement(By.linkText(reportName)).sendKeys(selectLinkOpeninNewTab);
			Thread.sleep(3000);
		} catch (Exception e) {
			Report.FailTest(test, "Not able to select the report : " + reportName);
		}
	}

	public static void openReportFromMegaMenu(String reportName) {
		try {
			Actions action = new Actions(driver);
			if (reportName.equals("RM Dashboard") || reportName.equals("Summary Dashboard")
					|| reportName.equals("Pre/Post/Idle Summary Dashboard"))

			{
				Util.switchToDefaultWindow();
				action.moveToElement(driver.findElement(By.xpath("//*[@id='main-nav']/ul/li[1]/a"))).perform();
				driver.findElement(By.linkText(reportName)).click();
				Thread.sleep(5000);
			} else {
				Util.switchToDefaultWindow();
				action.moveToElement(driver.findElement(By.xpath("//*[@id='main-nav']/ul/li[2]/a"))).perform();
				driver.findElement(By.linkText(reportName)).click();
				Thread.sleep(5000);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Not able to select the report : " + reportName);
		}
	}

	public static void validateColumns(List<String> actualColumnNames, List<String> expectedColumnNames,
			String tableName) {

		try {
			for (int i = 0; i < expectedColumnNames.size(); i++) {
				if (expectedColumnNames.get(i).equals(expectedColumnNames.get(i))) {
					Report.InfoTest(test, "Column Name is correct in " + tableName + ". Actual Column Name is : "
							+ actualColumnNames.get(i) + " and Expected is : " + expectedColumnNames.get(i));
					Report.PassTest(test, "Column Name is as expected in " + tableName + " of the report");
					LogClass.info("Checking class level logger");
				} else {
					Report.FailTest(test, "Column Name is not as expected in " + tableName + " Actual are : "
							+ actualColumnNames.get(i) + " and Expected are : " + expectedColumnNames.get(i));
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Not able to validate column names");
		}

	}

	public static List<String> getColumnNames(String tableName) {
		List<String> columnNames = new ArrayList<>();
		
		
		if (tableName.equals("dtRouteSummary") || tableName.equals("dtEfficiencySummary") || tableName.equals("dtDowntimeSummary") || tableName.equals("dtKronos")) {
			switchToDefaultWindow();
			selectFrame("opusReportContainer,subReportContainer");
			List<WebElement> columns = driver.findElements(By.xpath("//table[@id='" + tableName + "']/thead/tr/th"));
			System.out.println("Number of columns : " + columns.size());
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
		} 
		
		else if(tableName.equals("t2Idle")  || tableName.equals("dtCustTimeSummary"))
		{
			switchToDefaultWindow();
			selectFrame("opusReportContainer,subReportContainer,subIdleDetail_Row1");
			List<WebElement> columns = driver.findElements(By.xpath("//table[@id='" + tableName + "']/thead/tr/th"));
			System.out.println("Number of columns : " + columns.size());
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
		//CustTime_Sub
		else if(tableName.equals("CustTime_Sub"))
		{
			switchToDefaultWindow();
			selectFrame("opusReportContainer,subReportContainer,subCustTime_Row1");
			List<WebElement> columns = driver.findElements(By.xpath("//table[@id='" + tableName + "']/thead/tr/th"));
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
		
		else if(tableName.equals("t2DowntimeDrillDown"))
		{
			switchToDefaultWindow();
			selectFrame("opusReportContainer,subReportContainer,subIDownDetail_Row1");
			List<WebElement> columns = driver.findElements(By.xpath("//table[@id='t2']/thead/tr/th"));
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
		
		else if(tableName.equals("disposalDrilldown"))
		{
			switchToDefaultWindow();
			selectFrame("opusReportContainer,subReportContainer,subIDspDetailAll_Row1");
			List<WebElement> columns = driver.findElements(By.xpath("//table[@id='t2e']/thead/tr/th"));
			System.out.println("Number of columns : " + columns.size());
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
		
		else if(tableName.equals("t3"))
		{
			switchToDefaultWindow();
			selectFrame("opusReportContainer,subReportContainer,subSeqComplianceDetail_Row1");
			List<WebElement> columns = driver.findElements(By.xpath("//table[@id='" + tableName + "']/thead/tr/th"));
			System.out.println("Number of columns : " + columns.size());
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
		else {
			switchToDefaultWindow();
			selectFrame("opusReportContainer,subReportContainer");
			List<WebElement> columns = driver.findElements(By.xpath("//table[@id='" + tableName + "']/thead/tr/th"));
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

	public static void validateSortingOfFilterValues(String filterID) throws IOException {
		switchToDefaultWindow();
		ArrayList<String> UIfiltervalue = new ArrayList<>();
		ArrayList<String> Sortedfiltervalue = new ArrayList<>();
		List<WebElement> elementList = null;
		// filterID = "islBUFilter";
		try {
			Util.switchToDefaultTab();
			Util.selectFrame("opusReportContainer");
			// driver.findElement(By.xpath(".//*[@id='"+filterID+"']")).click();
			List<WebElement> filter = driver.findElements(By.xpath(".//*[@id='" + filterID + "']/option[@row]"));
			int noOfrows = filter.size();
			for (int i = 1; i <= noOfrows; i++) {
				List<WebElement> filterattribute = driver
						.findElements(By.xpath(".//*[@id='" + filterID + "']/option[@row = '" + i + "']"));
				String value = filterattribute.get(0).getAttribute("value");
				if (!value.equals("-1")) {
					// System.out.println(filterattribute.get(0).getAttribute("text"));

					for (WebElement we : filterattribute) {
						//UIfiltervalue.add(we.getAttribute("text").trim());
						if (!(we.getAttribute("text").trim().equals("Sub LOBs incl B, O, CD, P") || we.getAttribute("text").trim().equals("Sub LOBs excl B, O, CD, P")))
						
							UIfiltervalue.add(we.getAttribute("text").trim());

						// UIfiltervalue[i-1] =
						// filterattribute.getAttribute("text");

					}
				}
			}
			for (String d : UIfiltervalue) {
				Sortedfiltervalue.add(d);
			}
			Collections.sort(Sortedfiltervalue);

			if (Sortedfiltervalue.equals(UIfiltervalue)) {
				Report.PassTest(test, "The filter values are sorted for filter with ID - " + filterID);
				System.out.println("The filter values are sorted for filter with ID - " + filterID);
			} else {
				Report.FailTest(test, "The filter values are not sorted for filter with ID - " + filterID
						+ ". The actual list is - " + UIfiltervalue + " and the sorted list is - " + Sortedfiltervalue);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTest(test, e.getMessage());
		}
	}

	public static int getNumberOfStopsInSequence(List<String> stopsActualSequence) {

		int inSeqStopsCounter = 0;
		List<Integer> intList = new ArrayList<>();
		try {

			for (String s : stopsActualSequence) {
				intList.add(Integer.valueOf(s));
			}

			for (int i = 0; i < intList.size(); i++) {
				if (i == 0) {
					if (intList.get(i) == 2)
						inSeqStopsCounter++;
				} else {
					if (intList.get(i) - intList.get(i - 1) == 2) {
						inSeqStopsCounter++;
					} else {
						int tempCounter = 0;
						for (int j = i + 1; j < intList.size(); j++) {
							if (!(intList.get(i) < intList.get(j))) {
								tempCounter++;
							}
						}

						if (tempCounter == 0)
							inSeqStopsCounter++;
					}
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Report.FailTest(test, "Not able to identify Number of stops in sequence");
		}

		return inSeqStopsCounter;
	}
	

	public static void validateMaxValueSelectionOnFilter(String filterID)
	{
		try {
			
			Util.selectFrame("opusReportContainer");
			String filtername = filterID.substring(3);
			driver.findElement(By.xpath(".//*[@id='div"+filtername+"']/span/button")).click();
			List<WebElement> filter = driver.findElements(By.xpath(".//*[@id='" + filterID + "']/option[@row]"));
			int NoOfRows = filter.size();
			if (NoOfRows > 21) {
				for (int i = 1; i <= 21; i++) {
					driver.findElement(By.xpath(".//*[@id='ui-multiselect-" + filterID + "-option-" + i + "']")).click();
				}

				try {
					Alert alert = driver.switchTo().alert();
					String message = alert.getText();
					System.out.println(message);
					Report.PassTest(test, "A message was pooped up with text - "+message);
					alert.accept();
					} catch (NoAlertPresentException n) {
					System.out.println(n.getMessage());
					Report.FailTestSnapshot(test, driver, "Alert for Max value selection from filter is not visible",
							"MaxFilterOptionSelection");
					
					int xcount = driver.findElements(By.xpath("//div[contains(@style,'display: block')]//a[@class='ui-multiselect-close']/span")).size();
					if (xcount < 0)
					{
						driver.findElement(By.xpath("//div[contains(@style,'display: block')]//a[@class='ui-multiselect-close']/span")).click();
					}
					
				}
			} else {
				System.out.println("This filter is not having more than 21 values in its drop down.");
				Report.InfoTest(test, "This filter is not having more than 21 values in its drop down.");
			}

			} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void validateFromAndToDate(String reportName) throws ParseException, IOException
	{
		try {
			switchToDefaultWindow();
			selectFrame("opusReportContainer");
			WebElement FromDate = driver.findElement(By.xpath(".//*[@id='inpFromDate']"));
			WebElement ToDate = driver.findElement(By.xpath(".//*[@id='inpToDate']"));
			String defaultFromDate = FromDate.getAttribute("value");
			String defaultToDate = ToDate.getAttribute("value"); 
			
			// check if the date fields and some value in it are displayed
			
			if (!(defaultFromDate.equals("") || defaultToDate.equals(""))) 
			{

				verifyDefaultToDateValueNotGreaterThanTodaysDate();
				verifyDefaultToDateIsClosestToCurrentDate();
				verifyDifferenceBetweenFromDateAndToDateIs30days();
				validateToDateNotAcceptingFutureDates();
				validateDatesTwoYearsRolling();
				if (reportName.contains("Detail")|| reportName.contains("Lunch"))
				{
					validateDatesWhenDifferenceIsMoreThanOneMonth();
				}
				else 
				{
					validateDatesWhenDifferenceIsMoreThanOneYear();
				}
				verifyFromDateAndToDateWorkIndependently();
				verifyErrorMessageWhenFromDateIsGreaterThanToDate();
				verifyCopyAndPasteFunctionalityForDateFields();
			
			} else {
				Report.FailTestSnapshot(test, driver, "Either To or From date is not populated with Default dates",
						"Default Date on Date filters");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void verifyDefaultToDateValueNotGreaterThanTodaysDate()
	{
		try{
				switchToDefaultWindow();
				selectFrame("opusReportContainer");
			
				WebElement ToDate = driver.findElement(By.xpath(".//*[@id='inpToDate']"));
							
				String defaultToDate = ToDate.getAttribute("value");
				Date CurrentDate = new Date();
				System.out.println(CurrentDate);
				
				SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
				
				Date ToDateInFormat = sdf1.parse(defaultToDate);
				System.out.println(sdf1.format(ToDateInFormat));
				
				Calendar CalObj1 = Calendar.getInstance();
				CalObj1.setTime(CurrentDate);
				
				Calendar CalObj2 = Calendar.getInstance();
				CalObj2.setTime(ToDateInFormat);
						
					//Verify that Default To Date value is not greater than today's date
					if (CurrentDate.compareTo(ToDateInFormat) > 0) 
					{
			            System.out.println("To Date is before Current Date");
			            Report.PassTest(test, "To Date is before Current Date");
			        }
					else
					{
						System.out.println("To Date is not before Current Date");
						Report.FailTest(test, "To Date is not before Current Date");
					}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void verifyDifferenceBetweenFromDateAndToDateIs30days()
	{
		try 
		{
			switchToDefaultWindow();
			selectFrame("opusReportContainer");
			
			WebElement FromDate = driver.findElement(By.xpath(".//*[@id='inpFromDate']"));
			WebElement ToDate = driver.findElement(By.xpath(".//*[@id='inpToDate']"));
			String defaultFromDate = FromDate.getAttribute("value");
			String defaultToDate = ToDate.getAttribute("value");
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			Date ToDateInFormat = sdf1.parse(defaultToDate);
			System.out.println(sdf1.format(ToDateInFormat));
			
			Date FromDateInFormat = sdf1.parse(defaultFromDate);
			System.out.println(sdf1.format(FromDateInFormat));
			
			Calendar CalObj2 = Calendar.getInstance();
			CalObj2.setTime(ToDateInFormat);
			
			Calendar CalObj3 = Calendar.getInstance();
			CalObj3.setTime(FromDateInFormat);
		
			long diff = ToDateInFormat.getTime() - FromDateInFormat.getTime();
			Float days = (float) (diff / (1000*60*60*24));
			final long days1 = ChronoUnit.DAYS.between(CalObj3.toInstant(), CalObj2.toInstant());
			System.out.println(days +" & "+ days1);
		
			if (!(days.equals("30.0")) && (days1==30))
			{
				Report.PassTest(test, "The difference is less than or equal to 30 days");
				System.out.println("The difference is less than or equal to 30 days");
			}
			else 
			{
				Report.FailTest(test, "The difference is not less than or equal to 30 days");
				System.out.println("The difference is greater than 30 days");
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void validateToDateNotAcceptingFutureDates()
	{
		try
		{
			switchToDefaultWindow();
			selectFrame("opusReportContainer");
			
			WebElement ResetButton = driver.findElement(By.xpath(".//*[@id='btnReset']"));
			ResetButton.click();
			
			WebElement ToDate = driver.findElement(By.xpath(".//*[@id='inpToDate']"));
			WebElement GOButton = driver.findElement(By.xpath(".//*[@id='btnSubmitGo']"));
						
			String defaultToDate = ToDate.getAttribute("value");
			
			Date CurrentDate = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			
			Date ToDateInFormat = sdf1.parse(defaultToDate);
			System.out.println(sdf1.format(ToDateInFormat));
			
			//Enter Current or future date into To date
			Calendar CalNextDayObj = Calendar.getInstance();
			CalNextDayObj.setTime(CurrentDate);
			CalNextDayObj.add(Calendar.DATE, 1);
			Date nextday = CalNextDayObj.getTime();
			String nextdayInFormat = sdf1.format(nextday);
			ToDate.clear();
			ToDate.sendKeys(nextdayInFormat);
			
			GOButton.click();
			Thread.sleep(3000);
			try {
				Alert alert = driver.switchTo().alert();
				String message = alert.getText();
				System.out.println(message);
				Report.InfoTest(test, "Alert was generated for future date and Date difference more than 31 days. The message is -  "+ message);
				alert.accept();
				} 
			catch (NoAlertPresentException n) {
				//System.out.println(n.getMessage());
				String refreshedToDate = ToDate.getAttribute("value");
				System.out.println("Refreshed To Date is - "+refreshedToDate);
				Report.PassTestScreenshot(test, driver, "No Alert messages were generated. Future Date is not accepting", "Future Date in To Date");
				}
			String latestvalue = ToDate.getAttribute("value");
			
			if (!(latestvalue.equals(nextdayInFormat)))
			{
				Report.PassTest(test, "To Date is not taking date of the future date");
				System.out.println("To Date is not taking date of the future date");
			}
			else
			{
				Report.FailTest(test, "To date is accepting future dates");
				System.out.println("To date is accepting future dates");
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void validateDatesTwoYearsRolling() 
	{
		//Validate that user should be able to select/run the report within rolling 2 years period - and check graph values
		try 
		{
			switchToDefaultWindow();
			selectFrame("opusReportContainer");
			
			WebElement ResetButton = driver.findElement(By.xpath(".//*[@id='btnReset']"));
			ResetButton.click();
			
			WebElement GOButton = driver.findElement(By.xpath(".//*[@id='btnSubmitGo']"));
			WebElement FromDate = driver.findElement(By.xpath(".//*[@id='inpFromDate']"));
			WebElement ToDate = driver.findElement(By.xpath(".//*[@id='inpToDate']"));
			String defaultFromDate = FromDate.getAttribute("value");
			String defaultToDate = ToDate.getAttribute("value");
			Date CurrentDate = new Date();
			System.out.println(CurrentDate);
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			
			Date ToDateInFormat = sdf1.parse(defaultToDate);
			System.out.println(sdf1.format(ToDateInFormat));
			
			Date FromDateInFormat = sdf1.parse(defaultFromDate);
			System.out.println(sdf1.format(FromDateInFormat));
			
			Calendar CalObj1 = Calendar.getInstance();
			CalObj1.setTime(CurrentDate);
			
			Calendar CalTwoYearFromDateObj = Calendar.getInstance();
			CalTwoYearFromDateObj.setTime(FromDateInFormat);
			CalTwoYearFromDateObj.add(Calendar.YEAR, -2);
			Date TwoYearFromDate = CalTwoYearFromDateObj.getTime();
			String RqdTwoYearFromDate = sdf1.format(TwoYearFromDate);
			
			Calendar CalTwoYearToDateObj = Calendar.getInstance();
			CalTwoYearToDateObj.setTime(ToDateInFormat);
			CalTwoYearToDateObj.add(Calendar.YEAR, -2);
			Date TwoYearToDate = CalTwoYearToDateObj.getTime();
			String RqdTwoYearToDate = sdf1.format(TwoYearToDate);
			
			FromDate.clear();
			FromDate.sendKeys(RqdTwoYearFromDate);
			FromDate.sendKeys(Keys.TAB);
			ToDate.clear();
			ToDate.sendKeys(RqdTwoYearToDate);
			ToDate.sendKeys(Keys.TAB);
			
			//FilObj.selectDateToDatePicker(RqdTwoYearFromDate);
			//FilObj.selectDateFromDatePicker(RqdTwoYearToDate);
			GOButton.click();
			try {
				Alert alert = driver.switchTo().alert();
				String message = alert.getText();
				System.out.println(message);
				Report.FailTestSnapshot(test, driver, "Alert was genenrated when 2 year back dated dates were entered with message - "+message, "TwoYearRolling");
				alert.accept();
				} 
			catch (NoAlertPresentException n) {
				//System.out.println(n.getMessage());
				String refreshedFromDate = FromDate.getAttribute("value");
				String refreshedToDate = ToDate.getAttribute("value");
				
				System.out.println("Refreshed From Date is - "+refreshedFromDate+". And Refreshed To Date is - "+refreshedToDate);
				Report.PassTestScreenshot(test, driver, "No Alert messages were generated. Two year back dates are accepted", "TwoYearRollingBack");
				}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void validateDatesWhenDifferenceIsMoreThanOneYear()
	{
		//Validate that user should get an error when enters the Date range more than a year
		try {
			
			WebElement ResetButton = driver.findElement(By.xpath(".//*[@id='btnReset']")); 
			ResetButton.click();
			Thread.sleep(2000);
			
			WebElement FromDate = driver.findElement(By.xpath(".//*[@id='inpFromDate']"));
			WebElement ToDate = driver.findElement(By.xpath(".//*[@id='inpToDate']"));
			WebElement GOButton = driver.findElement(By.xpath(".//*[@id='btnSubmitGo']"));
					
			String defaultFromDate = FromDate.getAttribute("value");
			String defaultToDate = ToDate.getAttribute("value");
			Date CurrentDate = new Date();
			System.out.println(CurrentDate);
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			
			Date ToDateInFormat = sdf1.parse(defaultToDate);
			System.out.println(sdf1.format(ToDateInFormat));
			
			Date FromDateInFormat = sdf1.parse(defaultFromDate);
			System.out.println(sdf1.format(FromDateInFormat));
			
			Calendar CalFromDateOneYearBackObj = Calendar.getInstance();
			CalFromDateOneYearBackObj.setTime(FromDateInFormat);
			CalFromDateOneYearBackObj.add(Calendar.YEAR, -1);
			Date OneYearFromDate = CalFromDateOneYearBackObj.getTime();
			String RqdOneYearFromDate = sdf1.format(OneYearFromDate);
			FromDate.clear();
			FromDate.sendKeys(RqdOneYearFromDate);
			FromDate.sendKeys(Keys.TAB);
			GOButton.click();
			Thread.sleep(2000);
			try {
				Alert alert = driver.switchTo().alert();
				String message = alert.getText();
				System.out.println(message);
				Report.PassTest(test, "A message was pooped up with text - "+message);
				alert.accept();
				} 
			catch (NoAlertPresentException n)
				{
				System.out.println(n.getMessage());
				Report.FailTestSnapshot(test, driver, "Alert for One year window is not visible",
						"OneYearWindow");
				}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void verifyFromDateAndToDateWorkIndependently()
	{
		try
		{
			//'Verify that the From Date and To Date works independently
			WebElement ResetButton = driver.findElement(By.xpath(".//*[@id='btnReset']")); 
			ResetButton.click();
			
			WebElement FromDate = driver.findElement(By.xpath(".//*[@id='inpFromDate']"));
			WebElement ToDate = driver.findElement(By.xpath(".//*[@id='inpToDate']"));
			WebElement GOButton = driver.findElement(By.xpath(".//*[@id='btnSubmitGo']"));
				
			String defaultFromDate = FromDate.getAttribute("value");
			String defaultToDate = ToDate.getAttribute("value");
			Date CurrentDate = new Date();
			System.out.println(CurrentDate);
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			
			Date ToDateInFormat = sdf1.parse(defaultToDate);
			System.out.println(sdf1.format(ToDateInFormat));
			
			Date FromDateInFormat = sdf1.parse(defaultFromDate);
			System.out.println(sdf1.format(FromDateInFormat));
			
			Thread.sleep(2000);
			String currentToDateBefore = ToDate.getAttribute("value");
			Calendar CalFromDate5DaysObj = Calendar.getInstance();
			
			CalFromDate5DaysObj.setTime(FromDateInFormat);
			CalFromDate5DaysObj.add(Calendar.DATE, 5);
			Date Days5FromDate = CalFromDate5DaysObj.getTime();
			String Rqd5DaysFromDate = sdf1.format(Days5FromDate);
			FromDate.clear();
			FromDate.sendKeys(Rqd5DaysFromDate);
			FromDate.sendKeys(Keys.TAB);
			GOButton.click();
			Thread.sleep(2000);
			String currentToDateAfter = ToDate.getAttribute("value");
			if (currentToDateAfter.equals(currentToDateBefore))
			{
				Report.PassTestScreenshot(test, driver, "From Date is independent of To Date", "FromDate is Independent");
				System.out.println("From Date is independent of To Date");
			}
			else
			{
				Report.FailTest(test, "To and From Date are dependent on each other");
				System.out.println("To and From Date are dependent on each other");
			}
			
			ResetButton = driver.findElement(By.xpath(".//*[@id='btnReset']")); 
			ResetButton.click();
			
			FromDate = driver.findElement(By.xpath(".//*[@id='inpFromDate']"));
			ToDate = driver.findElement(By.xpath(".//*[@id='inpToDate']"));
			GOButton = driver.findElement(By.xpath(".//*[@id='btnSubmitGo']"));
			ResetButton = driver.findElement(By.xpath(".//*[@id='btnReset']"));
			
			Thread.sleep(2000);
			String currentFromDateBefore = FromDate.getAttribute("value");
			Calendar CalToDate5DaysObj = Calendar.getInstance();
			CalToDate5DaysObj.setTime(FromDateInFormat);
			CalToDate5DaysObj.add(Calendar.DATE, -5);
			Date Days5ToDate = CalToDate5DaysObj.getTime();
			String Rqd5DaysToDate = sdf1.format(Days5ToDate);
			ToDate.clear();
			ToDate.sendKeys(Rqd5DaysToDate);
			GOButton.click();
			Thread.sleep(2000);
			String currentFromDateAfter = FromDate.getAttribute("value");
			if (currentFromDateAfter.equals(currentFromDateBefore))
			{
				Report.PassTestScreenshot(test, driver, "To Date is independent of From Date", "ToDate is Independent");
				System.out.println("To Date is independent of From Date");
			}
			else
			{
				Report.FailTest(test, "To and From Date are dependent on each other");
				System.out.println("To and From Date are dependent on each other");
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void verifyErrorMessageWhenFromDateIsGreaterThanToDate()
	{
		try
		{
			WebElement ResetButton = driver.findElement(By.xpath(".//*[@id='btnReset']")); 
			ResetButton.click();
			
			WebElement FromDate = driver.findElement(By.xpath(".//*[@id='inpFromDate']"));
			WebElement ToDate = driver.findElement(By.xpath(".//*[@id='inpToDate']"));
			WebElement GOButton = driver.findElement(By.xpath(".//*[@id='btnSubmitGo']"));
			
			Thread.sleep(2000);
			String currentFromDate = FromDate.getAttribute("value");
			String currentToDate = ToDate.getAttribute("value");
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			
			Date ToDateInFormat = sdf1.parse(currentFromDate);
			System.out.println(sdf1.format(ToDateInFormat));
			
			Date FromDateInFormat = sdf1.parse(currentToDate);
			System.out.println(sdf1.format(FromDateInFormat));
			
			Calendar CalToDateObj = Calendar.getInstance();
			CalToDateObj.setTime(ToDateInFormat);
			CalToDateObj.add(Calendar.DATE, -40);
			Date backDateToDate = CalToDateObj.getTime();
			String RqdbackDateToDate = sdf1.format(backDateToDate);
			ToDate.clear();
			ToDate.sendKeys(RqdbackDateToDate);
			GOButton.click();
			Thread.sleep(2000);
			try {
				Alert alert = driver.switchTo().alert();
				String message = alert.getText();
				System.out.println(message);
				Report.PassTest(test, "A message was pooped up for From Date greater than To Date is displayed with text - "+message);
				alert.accept();
				} 
			catch (NoAlertPresentException n)
				{
				System.out.println(n.getMessage());
				Report.FailTestSnapshot(test, driver, "Alert for From Date greater than To Date is not visible",
						"OneYearWindow");
				}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

		
	}

	public static void verifyCopyAndPasteFunctionalityForDateFields()
	{
		try
		{
			//Verify that user should be able to copy the From Date value and paste in To Date and Viceversa
			WebElement ResetButton = driver.findElement(By.xpath(".//*[@id='btnReset']")); 
			ResetButton.click();
			
			WebElement FromDate = driver.findElement(By.xpath(".//*[@id='inpFromDate']"));
			WebElement ToDate = driver.findElement(By.xpath(".//*[@id='inpToDate']"));
			WebElement GOButton = driver.findElement(By.xpath(".//*[@id='btnSubmitGo']"));
			String FromDateValue = FromDate.getAttribute("value");
			
			
			StringSelection stringSelection = new StringSelection (FromDateValue);
			Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
			clpbrd.setContents(stringSelection, null);
			
			FromDate.sendKeys(Keys.TAB);
			ToDate.sendKeys(Keys.chord(Keys.CONTROL,"v"));
			GOButton.click();
			
			Thread.sleep(3000);
			
			ToDate = driver.findElement(By.xpath(".//*[@id='inpToDate']"));
			String AfterToDateValue = ToDate.getAttribute("value");
			
			if (FromDateValue.equals(AfterToDateValue))
			{
				Report.PassTestScreenshot(test, driver, "FromDate was copied and pasted on to ToDate", "From and To Dates are same");
				System.out.println("Copy paste functionality is working fine");
			}
			else
			{
				Report.FailTest(test, "Copy paste functionality is not working for dates");
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void verifyDefaultToDateIsClosestToCurrentDate()
	{
		try
		{
			switchToDefaultWindow();
			selectFrame("opusReportContainer");

			WebElement ToDate = driver.findElement(By.xpath(".//*[@id='inpToDate']"));
			WebElement DataLastRefreshDate = driver.findElement(By.xpath("//*[@id='divLastRefresh']/span[2]/span"));
			
			String defaultLastRefresh = DataLastRefreshDate.getText();
			String defaultLastRefreshDate = defaultLastRefresh.substring(0,9);
			
			String defaultToDate = ToDate.getAttribute("value");
			
			Date CurrentDate = new Date();
			System.out.println(CurrentDate);
								
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yy");
			
			Date ToDateInFormat = sdf1.parse(defaultToDate);
			System.out.println(sdf1.format(ToDateInFormat));
			
			Date LastRefreshDateInFormat = sdf2.parse(defaultLastRefreshDate);
			System.out.println(sdf1.format(LastRefreshDateInFormat));
			
			Calendar CalObj1 = Calendar.getInstance();
			CalObj1.setTime(CurrentDate);
			
			Calendar CalObj2 = Calendar.getInstance();
			CalObj2.setTime(ToDateInFormat);
			
			Calendar CalObj3 = Calendar.getInstance();
			CalObj3.setTime(LastRefreshDateInFormat);
			
			final long ToAndCurr = ChronoUnit.DAYS.between(CalObj2.toInstant(), CalObj1.toInstant());
			final long ToAndLastRef = ChronoUnit.DAYS.between(CalObj2.toInstant(), CalObj3.toInstant());
			
			
			if (((ToAndCurr==0)||(ToAndCurr==1)||(ToAndCurr==2)||(ToAndCurr==3))&&((ToAndLastRef==0)||(ToAndLastRef==1)||(ToAndLastRef==2)||(ToAndLastRef==3)))
			{
				Report.PassTest(test, "To date is closest to Current Date and Last Refresh Date. The difference of days are - "+ToAndCurr);
				System.out.println("To date is closest to Current Date and Last Refresh Date");
			}
			
			
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		}
	
	public static void validateDatesWhenDifferenceIsMoreThanOneMonth()
	{
		//Validate that user should get an error when enters the Date range more than a month
		try {
			
			WebElement ResetButton = driver.findElement(By.xpath(".//*[@id='btnReset']")); 
			ResetButton.click();
			Thread.sleep(2000);
			
			WebElement FromDate = driver.findElement(By.xpath(".//*[@id='inpFromDate']"));
			WebElement ToDate = driver.findElement(By.xpath(".//*[@id='inpToDate']"));
			WebElement GOButton = driver.findElement(By.xpath(".//*[@id='btnSubmitGo']"));
					
			String defaultFromDate = FromDate.getAttribute("value");
			String defaultToDate = ToDate.getAttribute("value");
			Date CurrentDate = new Date();
			System.out.println(CurrentDate);
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			
			Date ToDateInFormat = sdf1.parse(defaultToDate);
			System.out.println(sdf1.format(ToDateInFormat));
			
			Date FromDateInFormat = sdf1.parse(defaultFromDate);
			System.out.println(sdf1.format(FromDateInFormat));
			
			Calendar CalFromDateOneMonthBackObj = Calendar.getInstance();
			CalFromDateOneMonthBackObj.setTime(FromDateInFormat);
			
			CalFromDateOneMonthBackObj.add(Calendar.MONTH, -1);
			CalFromDateOneMonthBackObj.add(Calendar.DATE, -1);
			Date OneMonthFromDate = CalFromDateOneMonthBackObj.getTime();
			String RqdOneMonthFromDate = sdf1.format(OneMonthFromDate);
			FromDate.clear();
			FromDate.sendKeys(RqdOneMonthFromDate);
			FromDate.sendKeys(Keys.TAB);
			GOButton.click();
			Thread.sleep(2000);
			try {
				Alert alert = driver.switchTo().alert();
				String message = alert.getText();
				System.out.println(message);
				Report.PassTest(test, "A message was pooped up with text - "+message);
				alert.accept();
				} 
			catch (NoAlertPresentException n)
				{
				System.out.println(n.getMessage());
				Report.FailTestSnapshot(test, driver, "Alert for One year window is not visible",
						"OneYearWindow");
				}
		}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
				}
			
	}
	
	public static void ValidateFontAndColor(String columnName,String tableName,int row,int column)
	{
		try
		{
			
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");	
			
			String actualFontColor=driver.findElement(By.xpath("//table[@id='"+tableName+"']/tbody/tr["+row+"]/td["+column+"]/span")).getAttribute("class");
			String expectedFontColor="";
			
			
			if(columnName.contains("Eff Var (in hrs)"))
			{
				String effVar=driver.findElement(By.xpath("//table[@id='"+tableName+"']/tbody/tr["+row+"]/td["+column+"]")).getText();
				double effVariance=Double.parseDouble(effVar);
				
				if(effVariance>0)
				expectedFontColor="fontGreenColor";	
				else
				expectedFontColor="fontRedColor";
			}
			
			
			if(actualFontColor.equals(expectedFontColor))
			{
				Report.PassTest(test, "Font color of the column "+columnName+" is as expected. Actual is : "+actualFontColor+" and expected is : "+expectedFontColor);
			}
			else
			{
				Report.PassTest(test, "Font color of the column "+columnName+" is as not as expected. Actual is : "+actualFontColor+" and expected is : "+expectedFontColor);
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			Report.FailTest(test, "Not able to validate font color of the field : "+columnName);	
		}
	}

	public static void wait_inMinutes(double min)
	{
		min = min*60000;
		
		try
		{
			Thread.sleep((long) min);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	public static List<String> removeDuplicateFromList(List<String> actualList)
	{
		List<String> listDistinct=null;
		try
		{
			// Create a list with the distinct elements using stream.
			listDistinct = actualList.stream().distinct().collect(Collectors.toList());
			return listDistinct;
		}
		catch(Exception e)
		{
			Report.FailTest(test, "Not able to sort the element from the list : "+actualList.toString());
		}
		return listDistinct;
		
	}
}// Last Closing Bracket