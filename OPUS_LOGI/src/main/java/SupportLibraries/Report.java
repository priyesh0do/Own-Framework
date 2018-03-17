package SupportLibraries;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Report {


	public static SoftAssert s_assert = new SoftAssert();

	public static ExtentHtmlReporter StartHtmlReport(ExtentHtmlReporter htmlReporter, String browserName) {

		htmlReporter = new ExtentHtmlReporter(
				(System.getProperty("user.dir") + "/test-output/ExtentReport_" +  Util.DateTimeString() + ".html"));
		htmlReporter.config().setDocumentTitle("Automation Execution Report");
		htmlReporter.config().setReportName("Test Execution Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		return htmlReporter;
	}

	public static ExtentReports StartExtentReport(ExtentHtmlReporter htmlReporter, ExtentReports extent) {
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Host Name", "MPINDO02PC556"); // MPINDO02056
		extent.setSystemInfo("Env", "OPUS QA");
		return extent;
	}

	public static ExtentTest testCreate(ExtentReports extent, ExtentTest test, String Stepdetails) {
		test = extent.createTest(Stepdetails, Stepdetails);
		return test;
	}

	public static void PassTest(ExtentTest test, String Stepdetails) {

		// test = extent.createTest(Stepdetails, Stepdetails);
		test.pass(MarkupHelper.createLabel(Stepdetails, ExtentColor.GREEN));
		Assert.assertTrue(true);
	}

	public static void SkipTest(ExtentTest test, String Stepdetails) {
		try {
			test.skip(MarkupHelper.createLabel(Stepdetails, ExtentColor.BLUE));
			throw new SkipException("Skipping the test as runmode is NO ");
		} catch (Exception e) {
			System.out.println("Skipping the test as runmode is NO");
		}
	}

	public static void InfoTest(ExtentTest test, String Stepdetails) {

		// test = extent.createTest(Stepdetails, Stepdetails);
		test.info(MarkupHelper.createLabel(Stepdetails, ExtentColor.ORANGE));
	}

	
    public static void PassTestScreenshot(ExtentTest test, WebDriver driver, String Stepdetails, String screenshotName)
             {

/*                           String screenshotPath = Util.capture(driver, screenshotName);
test.info(MarkupHelper.createLabel(Stepdetails, ExtentColor.GREEN));
// test.pass("SnapShot Below : " +
// test.addScreenCaptureFromPath(screenshotPath));
test.log(Status.INFO, "Hello" + test.addScreenCaptureFromPath(screenshotPath));                            */

            try {
                            String screenshotPath = Util.capture(driver, screenshotName);
                            test.pass(MarkupHelper.createLabel(Stepdetails, ExtentColor.GREEN));
                            test.pass(MarkupHelper.createLabel(
                                                            " <a href='" + screenshotPath
                                                                                            + "' target=\"_blank\" style=\"color: rgb(255, 255, 255)\"> ScreenShot link :" + screenshotName + "</a>",
                                                            ExtentColor.BLUE));

            } catch (IOException e) {
                            Report.InfoTest(test, " message " + e.getMessage());
                            Report.FailTest(test,  "File IO Exception ");
            }
            
            
}


	public static void FailTestStop(ExtentTest test, String Stepdetails) {

		// test = extent.createTest(Stepdetails,Stepdetails);
		test.fail(MarkupHelper.createLabel(Stepdetails, ExtentColor.RED));
		Assert.assertTrue(false);
	}

	public static void FailTest(ExtentTest test, String Stepdetails) {

		// test = extent.createTest("FailTest",Stepdetails);
		test.fail(MarkupHelper.createLabel(Stepdetails, ExtentColor.RED));
		s_assert.assertTrue(false);
		
	}

    public static void FailTestSnapshot(ExtentTest test, WebDriver driver, String Stepdetails, String screenshotName)
             {                         
try {
            test.fail(MarkupHelper.createLabel(Stepdetails, ExtentColor.RED));
            s_assert.assertTrue(false);
            String screenshotPath = Util.capture(driver, screenshotName);
            Thread.sleep(2000);
            test.fail(MarkupHelper.createLabel(
                                            " <a href='" + screenshotPath
                                                                            + "' target=\"_blank\" style=\"color: rgb(255, 0, 0)\"> ScreenShot link : " + screenshotName + "</a>",
                                            ExtentColor.BLUE));
            // test.pass("SnapShot Below : " +
            // test.addScreenCaptureFromPath(screenshotPath));
} catch (IOException e) {
            Report.FailTest(test, "Test Case fail while capturing the screenshot");

} catch (InterruptedException e) {

}                              

}


	public static void ConditionCheckTest(ExtentTest test, String actualstring, String expectedstring) {

		s_assert.assertEquals(actualstring, expectedstring);
		if (actualstring.equalsIgnoreCase(expectedstring)) {
			test.log(Status.PASS, MarkupHelper.createLabel(
					("Actual value :" + actualstring + " Expected Value :" + expectedstring), ExtentColor.GREEN));
		} else {
			test.log(Status.FAIL, MarkupHelper.createLabel(
					("Actual value :" + actualstring + " Expected Value :" + expectedstring), ExtentColor.RED));
		}
	}

	public static void endReport(ExtentReports extent) {
		extent.flush();
	}

}
