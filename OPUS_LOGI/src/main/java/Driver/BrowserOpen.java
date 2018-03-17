package Driver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import TestData.GlobalTestData;
import io.appium.java_client.android.AndroidDriver;

public class BrowserOpen {
	static String systemDir = System.getProperty("user.dir");
	static String DriverPath ;
	static String downloadFilePath;
	public static WebDriver StartBrowser(WebDriver driver, String browserName, String URL) {
		if (browserName.equals("firefox")) {
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("log", "{level: trace}");
			capabilities.setCapability("marionette", true);
			capabilities.setCapability("moz:firefoxOptions", options);
			System.setProperty("webdriver.gecko.driver", systemDir + "\\src\\main\\java\\driver\\" + "geckodriver.exe");
				driver = new FirefoxDriver(capabilities);
		} else if (browserName.equals("chrome")) {
			DriverPath = systemDir + "\\src\\main\\java\\driver\\" + "chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", DriverPath);
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			 chromePrefs.put("plugins.plugins_disabled", new String[] {"Adobe Flash Player", "Chrome PDF Viewer"});
			chromePrefs.put("profile.default_content_settings.popups", 0);
			//chromePrefs.put("download.default_directory", GlobalVariables.downloadFilePath);
			downloadFilePath=systemDir + "\\downloads";
			chromePrefs.put("download.default_directory", downloadFilePath);
			ChromeOptions options = new ChromeOptions();
			HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--test-type");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			//cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
			driver = new ChromeDriver(cap);
			driver.manage().deleteAllCookies();
		} else if (browserName.equals("ie")) {

			DriverPath = systemDir + "\\src\\main\\java\\driver\\" + "IEDriverServer32.exe";
			System.setProperty("webdriver.ie.driver", DriverPath);
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.get(URL);
		return driver;
	}

	public static WebDriver StartAndroidBrowser(WebDriver driver, String URL) throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("VERSION", "6.0.1");
		// capabilities.setCapability("deviceName","ce061606e5372a1002"); //S7
		capabilities.setCapability("deviceName", "Appium1");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.android.chrome");
		capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		// capabilities.setCapability("appActivity",".screen.activity.SplashActivity");
		// capabilities.setCapability("BROWSER_NAME","CHROME");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.get(URL);
		return driver;

	}
}
