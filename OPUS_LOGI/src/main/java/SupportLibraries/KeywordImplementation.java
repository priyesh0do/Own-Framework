package SupportLibraries;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KeywordImplementation {
	
	public static WebElement returnElementIfPresent(WebDriver driver,String objectIdentifierType,String objectIdentifierValue) {
		
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		
		try{

			switch (objectIdentifierType) {
			case "id":
				element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(objectIdentifierValue)));
				Thread.sleep(250);
				//System.out.println("OR    : "+ObjectIdentifier+ " element is present on Page & identified by [id]");
				break;
			case "cssSelector":
				element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(objectIdentifierValue)));
				Thread.sleep(250);
				//System.out.println("OR    : "+ObjectIdentifier+ " element is present on Page & identified by [cssSelector]");		
				break;
			case "linkText":
				element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(objectIdentifierValue)));
				Thread.sleep(250);
				//System.out.println("OR    : "+ObjectIdentifier+ " element is present on Page & identified by [linkText]");
				break;
			case "xpath":
				element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objectIdentifierValue)));
				Thread.sleep(450);
				//System.out.println("OR    : "+ObjectIdentifier+ " element is present on Page & identified by [xpath]");
				break;
			case "name":
				element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(objectIdentifierValue)));
				Thread.sleep(450);
				//System.out.println("OR    : "+ObjectIdentifier+ " element is present on Page & identified by [xpath]");
				break;
			default:
				System.out.println("RESULT: FAIL - Please check your identifier Type in OR file  for => "+ objectIdentifierValue);
				//APP_LOGS.debug("RESULT: FAIL - Please check your identifier Type in OR file  for => "+ ObjectIdentifier);
			}

		} catch (Exception e) {
			System.out.println("RESULT: FAIL - Unable to locate "+objectIdentifierValue+" element using its "+objectIdentifierType+" identifier , This can be because new code/content deployemnt on AUT. Please check and update OR file");
			//APP_LOGS.debug("RESULT: FAIL - Unable to locate "+ObjectIdentifier+" element using its "+objectIdentifierType+" identifier , This can be because new code/content deployemnt on AUT. Please check and update OR file");
//			e.printStackTrace();
			return null;
		}
		return element;
	}

}
