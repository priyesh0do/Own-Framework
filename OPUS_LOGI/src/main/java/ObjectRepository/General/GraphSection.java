package ObjectRepository.General;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.Map.Entry;
import com.aventstack.extentreports.ExtentTest;
import com.google.common.base.Function;
import com.sun.tools.javac.util.*;
import com.sun.tools.javac.util.Context.Key;

import ObjectRepository.Confirmation.RouteDetails;
import TestData.GlobalTestData;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import net.sourceforge.cobertura.reporting.ReportName;
import SupportLibraries.*;
		
public class GraphSection 
{
	
	ExtentTest test;
	WebDriver driver;
		
	public GraphSection(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	//PreRouteCheckBox
		@FindBy(xpath=".//*[@id='chtCk1']")
		WebElement PreRouteCheckBox;
		
	//PostRouteCheckBox
		@FindBy(xpath=".//*[@id='chtCk2']")
		WebElement PostRouteCheckBox;
		
		//DisposalCycleCheckBox
		@FindBy(xpath=".//*[@id='chtCk3']")
		WebElement DisposalCycleCheckBox;
				
		//DowntimeCheckBox
		@FindBy(xpath=".//*[@id='chtCk4']")
		WebElement DowntimeCheckBox;
				
		//NetIdleCheckBox
		@FindBy(xpath=".//*[@id='chtCk5']")
		WebElement NetIdleCheckBox;
				
	    //TotalCheckBoxes
		@FindBy(xpath="//div[@class='chtSelectorsSummD chtSelectors']/span/input[contains(@id,'chtCk')]")
		List<WebElement> TotalCheckBoxesInRouteSummary;
	     //PreRouteCount
		@FindBy(xpath="//div[@id='chtRoute']//*[@class='highcharts-legend-item']//*[@fill='none' and @stroke='#77B310']")
		List<WebElement> PreRouteCount;
		//PostRouteCount
		@FindBy(xpath="//div[@id='chtRoute']//*[@class='highcharts-legend-item']//*[@fill='none' and @stroke='#575850']")
		List<WebElement> PostRouteCount;
		
		//DisposalCycleCount
		@FindBy(xpath="//div[@id='chtRoute']//*[@class='highcharts-legend-item']//*[@fill='none' and @stroke='#C6C6BC']")
		List<WebElement> DisposalCycleCount;
		
		//DowntimeCount
		@FindBy(xpath="//div[@id='chtRoute']//*[@class='highcharts-legend-item']//*[@fill='none' and @stroke='#E4FF10']")
		List<WebElement> DowntimeCount;
		
		//NetIdleCount
		@FindBy(xpath="//div[@id='chtRoute']//*[@class='highcharts-legend-item']//*[@fill='none' and @stroke='#C15004']")
		List<WebElement> NetIdleCount;
		
		//TotalCheckBoxesInServiceExceptions
		@FindBy(xpath="//div[@class='chtSelectorsSummD2 chtSelectors']/span/input[contains(@id,'chtCk')]")
		List<WebElement> TotalCheckBoxesInServiceExceptions;
		
		//HOCCount
		@FindBy(xpath="//div[@id='chtSerExcp']//*[@class='highcharts-legend-item']//*[@fill='#575850']")
		List<WebElement> HOCCount;
		
		//ETACount
		@FindBy(xpath="//div[@id='chtSerExcp']//*[@class='highcharts-legend-item']//*[@fill='#E4FF10']")
		List<WebElement> ETACount;
		
		//MPUCount
		@FindBy(xpath="//div[@id='chtSerExcp']//*[@class='highcharts-legend-item']//*[@fill='#77B310']")
		List<WebElement> MPUCount;
	
	public void validateGraphSection(String reportName, String SiteID)
	{
		FilterSection filterObj = new FilterSection(driver, test);
		String ExpectedYAxisValue = "";
		try
		{
		
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer");
		WebElement ResetButton = driver.findElement(By.xpath(".//*[@id='btnReset']"));
		ResetButton.click();
				
			if(reportName.equals("Summary Dashboard"))
				{
					//select site
					
					selectToAndFromDate("Month");
					filterObj.selectSite(SiteID);
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
					driver.findElement(By.xpath(".//*[@id='btnSubmitGo']")).click();
					Thread.sleep(3000);
					
					validateGraphSectionWithDefaultDates("cht001:cht002:cht003"); //For Efficiency Performance
					pageSroll("Down",1,800);
					validateGraphSectionWithDefaultDates("chtRoute");//For Route Summary
					pageSroll("Down",1,800);
					validateGraphSectionWithDefaultDates("chtSeq"); //For Sequence Summary
					pageSroll("Down",1,800);
					validateGraphSectionWithDefaultDates("chtSerExcp"); //For Service Exception
					pageSroll("Up",1,-1800);
										
					selectToAndFromDate("Day");
					validateGraphSectionWithDateDifferenceOf20Days("cht001:cht002:cht003"); //For Efficiency Performance
					pageSroll("Down",1,800);
					validateGraphSectionWithDateDifferenceOf20Days("chtRoute"); //For Route Summary
					pageSroll("Down",1,800);
					validateGraphSectionWithDateDifferenceOf20Days("chtSeq"); //For Sequence Summary
					pageSroll("Down",1,800);
					validateGraphSectionWithDateDifferenceOf20Days("chtSerExcp"); //For Service Exception
					pageSroll("Up",1,-1800);
					
					selectToAndFromDate("Half Year");
					validateGraphSectionWithDateDifferenceOf6Months("cht001:cht002:cht003"); //For Efficiency Performance
					pageSroll("Down",1,800);
					validateGraphSectionWithDateDifferenceOf6Months("chtRoute"); //For Route Summary
					pageSroll("Down",1,800);
					validateGraphSectionWithDateDifferenceOf6Months("chtSeq"); //For Sequence Summary
					pageSroll("Down",1,800);
					validateGraphSectionWithDateDifferenceOf6Months("chtSerExcp"); //For Service Exception
					pageSroll("Up",1,-1800);
					
					selectToAndFromDate("Year");
					validateGraphSectionWithDateDifferenceOf1Year("cht001:cht002:cht003"); //For Efficiency Performance
					pageSroll("Down",1,800);
					validateGraphSectionWithDateDifferenceOf1Year("chtRoute"); //For Route Summary
					pageSroll("Down",1,800);
					validateGraphSectionWithDateDifferenceOf1Year("chtSeq"); //For Sequence Summary
					pageSroll("Down",1,800);
					validateGraphSectionWithDateDifferenceOf1Year("chtSerExcp"); //For Service Exception
					pageSroll("Up",1,-1800);
					
					validateYAxis(reportName,"cht001","Units Per Hour");
					validateYAxis(reportName,"cht002","Units Per Hour");
					validateYAxis(reportName,"cht003","Units Per Hour");
					validateYAxis(reportName,"chtRoute","Total Time (hours)");
					validateYAxis(reportName,"chtSeq","Percentage");
					validateYAxis(reportName,"chtSerExcp","# of Service Exceptions");
								
				}
			else if (reportName.equals("RM Dashboard"))
			{
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				//select site
				filterObj.selectSite(SiteID);
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				//Enter the date thru Date picker
				String ToDateVal = driver.findElement(By.xpath(".//*[@id='inpToDate']")).getAttribute("value");
				filterObj.selectDateToDatePicker(ToDateVal);
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				driver.findElement(By.xpath(".//*[@id='btnSubmitGo']")).click();
				Thread.sleep(3000);
				// get the value of day of week
				String dayVal = driver.findElement(By.xpath(".//*[@id='lblDayOfWeekValue']")).getText();
				//check graph values
				validateGraphSectionWithDefaultDates_RMDashboard("cht001:cht002:cht003",dayVal);
			}
			else if ((reportName.equals("Pre-Route Detail"))||(reportName.equals("Post-Route Detail"))||(reportName.equals("Idle Detail"))||
					(reportName.equals("Downtime Detail"))||(reportName.equals("Customer Property Detail"))||(reportName.equals("Sequence Compliance Detail"))||
					(reportName.equals("Service Exceptions Detail"))||(reportName.equals("Flash Report")))
							
			{
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				/*selectToAndFromDate("Month");
				filterObj.selectSite(SiteID);
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				WebElement GoButton = driver.findElement(By.xpath(".//*[@id='btnSubmitGo']"));
				GoButton.click();
				
				Thread.sleep(3000);
				validateGraphSectionWithDefaultDates("cht001");*/
				if((reportName.equals("Pre-Route Detail"))||(reportName.equals("Post-Route Detail"))||(reportName.equals("Customer Property Detail")))
				{
					ExpectedYAxisValue =  "Average Time (minutes)";
				}
				else if ((reportName.equals("Downtime Detail"))||(reportName.equals("Idle Detail")))
				{
					ExpectedYAxisValue = "Total Time (hours)";
				}
				else if ((reportName.equals("Flash Report")))
				{
					ExpectedYAxisValue =  "Units Per Hour";
				}
				else if ((reportName.equals("Sequence Compliance Detail")))
				{
					ExpectedYAxisValue =  "Percentage";
				}
				else if ((reportName.equals("Service Exceptions Detail")))
				{
					ExpectedYAxisValue =  "Count";
				}
				validateYAxis(reportName, "cht001", ExpectedYAxisValue);
							
			}
			else if ((reportName.equals("Efficiency Summary"))|| (reportName.equals("Route Summary"))||
					(reportName.equals("Pre-Route Summary"))||(reportName.equals("Post-Route Summary"))||
					(reportName.equals("Idle Summary"))||
					(reportName.equals("Sequence Compliance Summary"))||(reportName.equals("Service Exceptions Summary")))
			{
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				selectToAndFromDate("Month");
				filterObj.selectSite(SiteID);
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				driver.findElement(By.xpath(".//*[@id='btnSubmitGo']")).click();
				validateGraphSectionWithDefaultDates("cht001");
				
				selectToAndFromDate("Day");
				filterObj.selectSite(SiteID);
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				driver.findElement(By.xpath(".//*[@id='btnSubmitGo']")).click();
				validateGraphSectionWithDateDifferenceOf20Days("cht001");
				
				selectToAndFromDate("Half Year");
				filterObj.selectSite(SiteID);
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				driver.findElement(By.xpath(".//*[@id='btnSubmitGo']")).click();
				validateGraphSectionWithDateDifferenceOf6Months("cht001");
				
				selectToAndFromDate("Year");
				filterObj.selectSite(SiteID);
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				driver.findElement(By.xpath(".//*[@id='btnSubmitGo']")).click();
				validateGraphSectionWithDateDifferenceOf1Year("cht001");
				
				if ((reportName.equals("Efficiency Summary")))
				{
					ExpectedYAxisValue = "Units Per Hour";
				}
				else if ((reportName.equals("Route Summary"))||(reportName.equals("Idle Summary")))
				{
					ExpectedYAxisValue = "Total Time (hours)";
				}
				else if ((reportName.equals("Pre-Route Summary"))||(reportName.equals("Post-Route Summary")))
				{
					ExpectedYAxisValue = "Average Time (minutes)";
				}
				else if ((reportName.equals("Service Exceptions Summary")))
				{
					ExpectedYAxisValue = "Count";
				}
				else if ((reportName.equals("Sequence Compliance Summary")))
				{
					ExpectedYAxisValue = "Percentage";
				}
				
				validateYAxis(reportName, "cht001", ExpectedYAxisValue);		
			}
			
			else if ((reportName.equals("Disposal Cycle Detail"))||(reportName.equals("Disposal Cycle Summary")))
			{
					
					ExpectedYAxisValue = "Average Time (minutes)";
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
					selectToAndFromDate("Month");
					filterObj.selectSite(SiteID);
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer");
					WebElement GoButton = driver.findElement(By.xpath(".//*[@id='btnSubmitGo']"));
					GoButton.click();
					
					Thread.sleep(3000);
					validateGraphSectionWithDefaultDates("cht001");
					pageSroll("Down", 1, 500);
					validateGraphSectionWithDefaultDates_DisposalDetailAndSummary_TimeOfDay("cht002");
					validateYAxis(reportName, "cht001", ExpectedYAxisValue);
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
			
	
	public void validateGraphSectionWithDefaultDates(String chartID)
	{
		try
		{
			int cht = 0;
			String[] charts = chartID.split(":");
			if (charts.length==3)
			{	for (cht = 0;cht<charts.length;cht++)
				{
					List<WebElement> noDataOnChart = driver.findElements(By.xpath(".//*[@id='"+charts[cht]+"']/div[1]/*/*[@class='highcharts-no-data']"));
					if (noDataOnChart.size()<=0)
					{						
						Util.switchToDefaultWindow();
						Util.selectFrame("opusReportContainer,subReportContainer");
						int noOfDates = driver.findElements(By.xpath(".//*[@id='"+charts[cht]+"']/div[1]/div[1]/span")).size();
						String[] ArrayOfDates = new String[noOfDates];
						for (int val = 1;val <=noOfDates;val++)
						{
							ArrayOfDates[val-1] = driver.findElement(By.xpath(".//*[@id='"+charts[cht]+"']/div[1]/div[1]/span["+val+"]")).getText();
							SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yy");
							Date dateInFormat = sdf1.parse(ArrayOfDates[val-1]);
							Calendar dateobj = Calendar.getInstance();
							dateobj.setTime(dateInFormat);
							int day = dateobj.get(Calendar.DAY_OF_WEEK);
							if (day == 1)
							{
								System.out.println("Date - "+ArrayOfDates[val-1]+" is a Sunday");
								Report.PassTest(test, "Date - "+ArrayOfDates[val-1]+" is a Sunday");
							}
							
						}
					}
					else
					{
						Report.InfoTest(test, "No Data for LOB");
						System.out.println("No Data for LOB available");
					}
				}
			}
			else if (charts.length ==1)
			{
				String datepat = "^[0-1]?[0-9].[0-3]?[0-9].(?:[0-9])?(?:[0-9])$";
				boolean tempDate = false;
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");
				List<WebElement> noDataOnChart = driver.findElements(By.xpath(".//*[@id='"+charts[cht]+"']/div[1]/*/*[@class='highcharts-no-data']"));
				if (noDataOnChart.size()<=0)
				{
					int noOfDates = driver.findElements(By.xpath(".//*[@id='"+charts[cht]+"']/div[1]/div[1]/span")).size();
					String[] ArrayOfDates = new String[noOfDates];
					for (int val = 1;val <=noOfDates;val++)
					{
						ArrayOfDates[val-1] = driver.findElement(By.xpath(".//*[@id='"+charts[cht]+"']/div[1]/div[1]/span["+val+"]")).getText();
						tempDate = ArrayOfDates[val-1].matches(datepat);
						if(tempDate=true)
						{
							System.out.println(ArrayOfDates[val-1]+" : This date is in the format MM/DD/YY");
							Report.PassTest(test, ArrayOfDates[val-1]+" : This date is in the format MM/DD/YY");
						}
						else
						{
							System.out.println(ArrayOfDates[val-1]+" : This date is not in the format MM/DD/YY");
							Report.FailTest(test, ArrayOfDates[val-1]+" : This date is not in the format MM/DD/YY");
						}
					}
				}
				else
				{
					Report.InfoTest(test, "No Data for LOB");
					System.out.println("No Data for LOB available");
					Report.InfoTest(test, "No Data for LOB available");
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	public void validateGraphSectionWithDateDifferenceOf20Days(String chartID)
	{
		try
		{
			String[] charts = chartID.split(":");
			String datepat = "^[0-1]?[0-9].[0-3]?[0-9].(?:[0-9])?(?:[0-9])$";
			boolean tempDate = false;
			
				for (int cht = 0;cht<charts.length;cht++)
				{
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer");
					List<WebElement> noDataOnChart = driver.findElements(By.xpath(".//*[@id='"+charts[cht]+"']/div[1]/*/*[@class='highcharts-no-data']"));
					if (noDataOnChart.size()<=0)
					{
						int noOfDates = driver.findElements(By.xpath(".//*[@id='"+charts[cht]+"']/div[1]/div[1]/span")).size();
						String[] ArrayOfDates = new String[noOfDates];
						for (int val = 1;val <=noOfDates;val++)
						{
							ArrayOfDates[val-1] = driver.findElement(By.xpath(".//*[@id='"+charts[cht]+"']/div[1]/div[1]/span["+val+"]")).getText();
							SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yy");
							sdf1.setLenient(false);
							try
							{
								Date dateInFormat = sdf1.parse(ArrayOfDates[val-1]);
								tempDate = ArrayOfDates[val-1].matches(datepat);
								if(tempDate=true)
								{
									System.out.println(ArrayOfDates[val-1]+" : This date is in the format MM/DD/YY");
									Report.PassTest(test, ArrayOfDates[val-1]+" : This date is in the format MM/DD/YY");
								}
								else
								{
									System.out.println(ArrayOfDates[val-1]+" : This date is not in the format MM/DD/YY");
									Report.FailTest(test, ArrayOfDates[val-1]+" : This date is not in the format MM/DD/YY");
								}
								
							}
							catch (ParseException e)
							{
								System.out.println(ArrayOfDates[val-1]+" - is an Invalid Date format");
								Report.FailTest(test, ArrayOfDates[val-1]+" - is an Invalid Date format");
							}
						}
					}
					else
					{
						Report.InfoTest(test, "No Data for LOB");
						System.out.println("No Data for LOB available");
					}
				}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void validateGraphSectionWithDateDifferenceOf6Months(String chartID)
	{
		try
		{
			String[] charts = chartID.split(":");
			
				for (int cht = 0;cht<charts.length;cht++)
				{
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer");
					List<WebElement> noDataOnChart = driver.findElements(By.xpath(".//*[@id='"+charts[cht]+"']/div[1]/*/*[@class='highcharts-no-data']"));
					if (noDataOnChart.size()<=0)
					{
						//subReportContainer
						int noOfDates = driver.findElements(By.xpath(".//*[@id='"+charts[cht]+"']/div[1]/div[1]/span")).size();
						String[] ArrayOfDates = new String[noOfDates];
						for (int val = 1;val <=noOfDates;val++)
						{
							ArrayOfDates[val-1] = driver.findElement(By.xpath(".//*[@id='"+charts[cht]+"']/div[1]/div[1]/span["+val+"]")).getText();
							SimpleDateFormat sdf1 = new SimpleDateFormat("MMM yy");
							sdf1.setLenient(false);
							try
							{
								Date dateInFormat = sdf1.parse(ArrayOfDates[val-1]);
								System.out.println(ArrayOfDates[val-1]+ " - is a valid Date format MMM YY");
								Report.PassTest(test, ArrayOfDates[val-1]+ " - is a valid Date format MMM YY");
								
							}
							catch (ParseException e)
							{
								System.out.println(ArrayOfDates[val-1]+" - is an Invalid Date format");
								Report.FailTest(test, ArrayOfDates[val-1]+" - is an Invalid Date format");
							}
						}
					}
					else
					{
						Report.InfoTest(test, "No Data for LOB");
						System.out.println("No Data for LOB available");
					}
				}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void validateGraphSectionWithDateDifferenceOf1Year(String chartID)
	{
		try
		{
			String[] charts = chartID.split(":");
			Date CurrentDate = new Date();
			System.out.println(CurrentDate);
			Calendar CalObj1 = Calendar.getInstance();
			CalObj1.setTime(CurrentDate);
			String currentyear = Integer.toString(CalObj1.get(Calendar.YEAR));
			CalObj1.add(Calendar.YEAR, -1);
			String lastyear = Integer.toString(CalObj1.get(Calendar.YEAR));
			
			
				for (int cht = 0;cht<charts.length;cht++)
				{
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer");
					List<WebElement> noDataOnChart = driver.findElements(By.xpath(".//*[@id='"+charts[cht]+"']/div[1]/*/*[@class='highcharts-no-data']"));
					if (noDataOnChart.size()<=0)
					{
						int noOfDates = driver.findElements(By.xpath(".//*[@id='"+charts[cht]+"']/div[1]/div[1]/span")).size();
						String[] ArrayOfDates = new String[noOfDates];
						
						for (int val = 1;val <=noOfDates;val++)
						{
							ArrayOfDates[val-1] = driver.findElement(By.xpath(".//*[@id='"+charts[cht]+"']/div[1]/div[1]/span["+val+"]")).getText();
							if ((ArrayOfDates[val-1].contains("Q1"))||(ArrayOfDates[val-1].contains("Q2"))||(ArrayOfDates[val-1].contains("Q3"))||(ArrayOfDates[val-1].contains("Q4")) )
							{
								if ((ArrayOfDates[val-1].contains(currentyear))||(ArrayOfDates[val-1].contains(lastyear)))
								{
									System.out.println(ArrayOfDates[val-1]+ " - The date format is correct with Year and Q#");
									Report.PassTest(test, ArrayOfDates[val-1]+ " - The date format is correct with Year and Q#");
								}
								else
								{
									System.out.println("Year is not correct in the date - " + ArrayOfDates[val-1]);
									Report.FailTest(test, "Year is not correct in the date - " + ArrayOfDates[val-1]);
								}
							}
							else if (!(ArrayOfDates[val-1].contains("Q")))
							{
								SimpleDateFormat sdf1 = new SimpleDateFormat("MMM yy");
								sdf1.setLenient(false);
								try
								{
									Date dateInFormat = sdf1.parse(ArrayOfDates[val-1]);
									System.out.println(ArrayOfDates[val-1]+ " - is a valid Date format MMM YY");
									Report.PassTest(test, ArrayOfDates[val-1]+ " - is a valid Date format MMM YY");
									
								}
								catch (ParseException e)
								{
									System.out.println(ArrayOfDates[val-1]+" - is an Invalid Date format");
									Report.FailTest(test, ArrayOfDates[val-1]+" - is an Invalid Date format");
								}
								
							}
							else
							{
								System.out.println(ArrayOfDates[val-1]+" is not a valid date format");
								Report.FailTest(test, ArrayOfDates[val-1]+" is not a valid date format");
							}
						}
					}
					else
					{
						Report.InfoTest(test, "No Data for LOB");
						System.out.println("No Data for LOB available");
					}
				}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
		
	public void selectToAndFromDate(String range)
	{
		try
		{
			switch (range)
			{
			case "Day":
			{
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				WebElement ResetButton = driver.findElement(By.xpath(".//*[@id='btnReset']")); 
				ResetButton.click();
				Thread.sleep(3000);
				
				WebElement FromDate = driver.findElement(By.xpath(".//*[@id='inpFromDate']"));
				WebElement ToDate = driver.findElement(By.xpath(".//*[@id='inpToDate']"));
				WebElement GoButton = driver.findElement(By.xpath(".//*[@id='btnSubmitGo']"));
								
				String ToDateVal = ToDate.getAttribute("value");
				
				SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
				Date ToDateInFormat = sdf1.parse(ToDateVal);
							
				Calendar calObj = Calendar.getInstance();
				calObj.setTime(ToDateInFormat);
				calObj.add(Calendar.DATE, -15);
				
				Date backDateToDate = calObj.getTime();
				String RqdbackDateToDate = sdf1.format(backDateToDate);
				
				FromDate.clear();
				FromDate.sendKeys(RqdbackDateToDate);
				FromDate.sendKeys(Keys.TAB);
				
				GoButton.click();
				Thread.sleep(3000);
								
				break;
			}
			case "Month":
			{
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				WebElement ResetButton = driver.findElement(By.xpath(".//*[@id='btnReset']"));
				ResetButton.click();
				Thread.sleep(3000);
				break;
			}
			case "Half Year":
			{
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				WebElement ResetButton = driver.findElement(By.xpath(".//*[@id='btnReset']")); 
				ResetButton.click();
				Thread.sleep(3000);
				WebElement FromDate = driver.findElement(By.xpath(".//*[@id='inpFromDate']"));
				WebElement ToDate = driver.findElement(By.xpath(".//*[@id='inpToDate']"));
				WebElement GoButton = driver.findElement(By.xpath(".//*[@id='btnSubmitGo']"));
								
				String ToDateVal = ToDate.getAttribute("value");
				
				SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
				Date ToDateInFormat = sdf1.parse(ToDateVal);
							
				Calendar calObj = Calendar.getInstance();
				calObj.setTime(ToDateInFormat);
				calObj.add(Calendar.DATE, -190);
				
				Date backDateToDate = calObj.getTime();
				String RqdbackDateToDate = sdf1.format(backDateToDate);
				
				FromDate.clear();
				FromDate.sendKeys(RqdbackDateToDate);
				FromDate.sendKeys(Keys.TAB);
				
				GoButton.click();
				Thread.sleep(3000);
								
				break;
			}
			case "Year":
			{
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer");
				WebElement ResetButton = driver.findElement(By.xpath(".//*[@id='btnReset']")); 
				ResetButton.click();
				Thread.sleep(3000);
				WebElement FromDate = driver.findElement(By.xpath(".//*[@id='inpFromDate']"));
				WebElement ToDate = driver.findElement(By.xpath(".//*[@id='inpToDate']"));
				WebElement GoButton = driver.findElement(By.xpath(".//*[@id='btnSubmitGo']"));
								
				String ToDateVal = ToDate.getAttribute("value");
				
				SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
				Date ToDateInFormat = sdf1.parse(ToDateVal);
							
				Calendar calObj = Calendar.getInstance();
				calObj.setTime(ToDateInFormat);
				calObj.add(Calendar.DATE, -364);
				
				Date backDateToDate = calObj.getTime();
				String RqdbackDateToDate = sdf1.format(backDateToDate);
				
				FromDate.clear();
				FromDate.sendKeys(RqdbackDateToDate);
				FromDate.sendKeys(Keys.TAB);
				
				GoButton.click();
				Thread.sleep(3000);
								
				break;
			}
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
		  
	public void pageSroll(String dir,int To, int From)
	{
		try
		{
			Util.switchToDefaultWindow();
			
			if (dir.equalsIgnoreCase("Up"))
			{
				Util.PageScrollUp(To, From);
			}
			else if (dir.equalsIgnoreCase("Down"))
			{
				Util.PageScrollDown(From, To);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void validateGraphSectionWithDefaultDates_RMDashboard(String chartID, String dayofWeek)
	{
		int cht=0;
		String[]charts = chartID.split(":");
		try
		{	
			for (cht = 0;cht<charts.length;cht++)
			{
				List<WebElement> noDataOnChart = driver.findElements(By.xpath(".//*[@id='"+charts[cht]+"']/div[1]/*/*[@class='highcharts-no-data']"));
				if (noDataOnChart.size()<=0)
				{						
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
					int noOfDates = driver.findElements(By.xpath(".//*[@id='"+charts[cht]+"']/div[1]/div[1]/span")).size();
					String[] ArrayOfDates = new String[noOfDates];
					for (int val = 1;val <=noOfDates;val++)
					{
						ArrayOfDates[val-1] = driver.findElement(By.xpath(".//*[@id='"+charts[cht]+"']/div[1]/div[1]/span["+val+"]")).getText();
						SimpleDateFormat sdf1 = new SimpleDateFormat("M/d");
						Date dateInFormat = sdf1.parse(ArrayOfDates[val-1]);
						Calendar dateobj = Calendar.getInstance();
						dateobj.setTime(dateInFormat);
						dateobj.set(Calendar.YEAR,2017);
						Date dateval = dateobj.getTime();
						int day = dateobj.get(Calendar.DAY_OF_WEEK);
						switch (dayofWeek)
						{
							case "Sunday":
							{
								if (day == 1)
								{
									System.out.println("Date - "+ArrayOfDates[val-1]+" is a Sunday");
									Report.PassTest(test, "Date - "+ArrayOfDates[val-1]+" is a Sunday");
								}
								else 
								{
									System.out.println("Date - "+ArrayOfDates[val-1]+" is not a Sunday");
									Report.FailTest(test, "Date - "+ArrayOfDates[val-1]+" is not a Sunday");
								}
								break;
							}
							
							case "Monday":
							{
								if (day == 2)
								{
									System.out.println("Date - "+ArrayOfDates[val-1]+" is a Monday");
									Report.PassTest(test, "Date - "+ArrayOfDates[val-1]+" is a Monday");
								}
								else 
								{
									System.out.println("Date - "+ArrayOfDates[val-1]+" is not a Monday");
									Report.FailTest(test, "Date - "+ArrayOfDates[val-1]+" is not a Monday");
								}
								break;
							}
							case "Tuesday":
							{
								if (day == 3)
								{
									System.out.println("Date - "+ArrayOfDates[val-1]+" is a Tuesday");
									Report.PassTest(test, "Date - "+ArrayOfDates[val-1]+" is a Tuesday");
								}
								else 
								{
									System.out.println("Date - "+ArrayOfDates[val-1]+" is not a Tuesday");
									Report.FailTest(test, "Date - "+ArrayOfDates[val-1]+" is not a Tuesday");
								}
								break;
							}
							
							case "Wednesday":
							{
								if (day == 4)
								{
									System.out.println("Date - "+ArrayOfDates[val-1]+" is a Wednesday");
									Report.PassTest(test, "Date - "+ArrayOfDates[val-1]+" is a Wednesday");
								}
								else 
								{
									System.out.println("Date - "+ArrayOfDates[val-1]+" is not a Wednesday");
									Report.FailTest(test, "Date - "+ArrayOfDates[val-1]+" is not a Wednesday");
								}
								break;
							}
							
							case "Thursday":
							{
								if (day == 5)
								{
									System.out.println("Date - "+ArrayOfDates[val-1]+" is a Thursday");
									Report.PassTest(test, "Date - "+ArrayOfDates[val-1]+" is a Thursday");
								}
								else 
								{
									System.out.println("Date - "+ArrayOfDates[val-1]+" is not a Thursday");
									Report.FailTest(test, "Date - "+ArrayOfDates[val-1]+" is not a Thursday");
								}
								break;
							}
							
							case "Friday":
							{
								if (day == 6)
								{
									System.out.println("Date - "+ArrayOfDates[val-1]+" is a Friday");
									Report.PassTest(test, "Date - "+ArrayOfDates[val-1]+" is a Friday");
								}
								else 
								{
									System.out.println("Date - "+ArrayOfDates[val-1]+" is not a Friday");
									Report.FailTest(test, "Date - "+ArrayOfDates[val-1]+" is not a Friday");
								}
								break;
							}
							
							case "Saturday":
							{
								if (day == 7)
								{
									System.out.println("Date - "+ArrayOfDates[val-1]+" is a Saturday");
									Report.PassTest(test, "Date - "+ArrayOfDates[val-1]+" is a Saturday");
								}
								else 
								{
									System.out.println("Date - "+ArrayOfDates[val-1]+" is not a Saturday");
									Report.FailTest(test, "Date - "+ArrayOfDates[val-1]+" is not a Saturday");
								}
								break;
							}
						}
					}
				}
				else
				{
					Report.InfoTest(test, "No Data for LOB");
					System.out.println("No Data for LOB available");
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
		 
	public void validateGraphSectionWithDefaultDates_DisposalDetailAndSummary_TimeOfDay(String chartID)
	{
		try
		{
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");
			List<WebElement> noDataOnChart = driver.findElements(By.xpath(".//*[@id='"+chartID+"']/div[1]/*/*[@class='highcharts-no-data']"));
			if (noDataOnChart.size()<=0)
			{
				int noOfDates = driver.findElements(By.xpath(".//*[@id='"+chartID+"']/div[1]/div[1]/span")).size();
				String[] ArrayOfDates = new String[noOfDates];
				for (int val = 1;val <=noOfDates;val++)
				{
					ArrayOfDates[val-1] = driver.findElement(By.xpath(".//*[@id='"+chartID+"']/div[1]/div[1]/span["+val+"]")).getText();
					
					if ((ArrayOfDates[val-1].contains("AM")) || (ArrayOfDates[val-1].contains("PM")))
					{
						System.out.println(ArrayOfDates[val-1]+" : This date is in the format ##AM or ##PM");
						Report.PassTest(test, ArrayOfDates[val-1]+" : This date is in the format ##AM or ##PM");
					}
					else
					{
						System.out.println(ArrayOfDates[val-1]+" : This date is not in the format ##AM or ##PM");
						Report.FailTest(test, ArrayOfDates[val-1]+" : This date is not in the format ##AM or ##PM");
					}
				}
			}
			else
			{
				Report.InfoTest(test, "No Data for LOB");
				System.out.println("No Data for LOB available");
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void validateYAxis(String reportName ,String chartID, String ExpectedYAxisValue)
	{
		try
		{	
			String ActualYAxisValue = "";
			switch (reportName)
			{	
				case "Summary Dashboard":
				case "Customer Property Detail":
				case "Disposal Cycle Detail":
				case "Disposal Cycle Summary":
				case "Post-Route Detail":
				case "Pre-Route Detail":
				case "Idle Detail":
				case "Downtime Detail":
				case "Flash Report":
				case "Efficiency Summary":
				case "Route Summary":
				case "Pre-Route Summary":
				case "Post-Route Summary":
				case "Idle Summary":
							
				{
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer");
					
					if (chartID.equals("chtSeq"))
					{
						ActualYAxisValue = driver.findElement(By.xpath(".//*[@id='"+chartID+"']/div[1]/*/*/*[text()]")).getText();
					}
					else
					{	
						ActualYAxisValue = driver.findElement(By.xpath(".//*[@id='"+chartID+"']/div[1]/*/*/*/*[text()]")).getText();
					}
					if(ActualYAxisValue.equals(ExpectedYAxisValue))
					{
						Report.PassTest(test, "Actual Value of Y Axis is same as Expected - "+ActualYAxisValue);
						System.out.println("Actual Value of Y Axis is same as Expected - "+ActualYAxisValue);
					}
					else
					{
						Report.FailTest(test, "Actual Value of Y Axis is NOT same as Expected. Actual Value - "+ActualYAxisValue+". Expected Value - "+ExpectedYAxisValue);
					}
					break;
				}
				case "Sequence Compliance Detail":
				case "Sequence Compliance Summary":
				case "Service Exceptions Detail":
				case "Service Exceptions Summary":
				{
					Util.switchToDefaultWindow();
					Util.selectFrame("opusReportContainer,subReportContainer");
					ActualYAxisValue = driver.findElement(By.xpath(".//*[@id='"+chartID+"']/div[1]/*/*/*[text()]")).getText();
					if(ActualYAxisValue.equals(ExpectedYAxisValue))
					{
						Report.PassTest(test, "Actual Value of Y Axis is same as Expected - "+ActualYAxisValue);
						System.out.println("Actual Value of Y Axis is same as Expected - "+ActualYAxisValue);
					}
					else
					{
						Report.FailTest(test, "Actual Value of Y Axis is NOT same as Expected. Actual Value - "+ActualYAxisValue+". Expected Value - "+ExpectedYAxisValue);
						System.out.println("Actual Value of Y Axis is NOT same as Expected. Actual Value - "+ActualYAxisValue+". Expected Value - "+ExpectedYAxisValue);
					}
					break;
				}
				
				
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	
	
    
	public void ValidateGraphLegends() {
		int beforeClickPreRouteCount=0;
		int beforeClickPostRouteCount=0;
		int beforeClickDisposalCycleCount=0;
		int beforeClickDowntimeCount=0;
		int beforeClickNetIdleCount=0;
		int afterClickPreRouteCount=0;
		int afterClickPostRouteCount=0;
		int afterClickDisposalCycleCount=0;
		int afterClickDowntimeCount=0;
		int afterClickNetIdleCount=0;
		int beforeClickHOCCount=0;
		int afterClickHOCCount=0;
		int beforeClickETACount=0;
		int aferClickETACount=0;
		int beforeClickMPUCount=0;
		int afterClickMPUCount=0;
		
		
		
		try {
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");
			Util.pageScroll(0,250);
			beforeClickPreRouteCount=PreRouteCount.size();
			beforeClickPostRouteCount=PostRouteCount.size();
			beforeClickDisposalCycleCount=DisposalCycleCount.size();
			beforeClickDowntimeCount=DowntimeCount.size();
			beforeClickNetIdleCount=NetIdleCount.size();
			 
			for (int i = 1; i <=TotalCheckBoxesInRouteSummary.size(); i++) {
				driver.findElement(By.xpath(".//*[@id='chtCk"+i+"']")).click();
				Thread.sleep(1000);
			}
			
			afterClickPreRouteCount=PreRouteCount.size();
			afterClickPostRouteCount=PostRouteCount.size();
			afterClickDisposalCycleCount=DisposalCycleCount.size();
			afterClickDowntimeCount=DowntimeCount.size();
			afterClickNetIdleCount=NetIdleCount.size();
			
			if (beforeClickPreRouteCount!=afterClickPreRouteCount && beforeClickPostRouteCount!=afterClickPostRouteCount && beforeClickDisposalCycleCount!=afterClickDisposalCycleCount
					&& beforeClickDowntimeCount!=afterClickDowntimeCount && beforeClickNetIdleCount!=afterClickNetIdleCount ) {
				
				Report.PassTestScreenshot(test, driver, "Pre Route Check Box is working fine Before click  count is  " + beforeClickPreRouteCount + 
						"after Click Pre Route count is "+ afterClickPreRouteCount + "Post Route Check Box is working fine Before Click Count is " +beforeClickPostRouteCount+
						"After click count is "+afterClickPostRouteCount + "Disposal cycle check box is working fine before click count is " +beforeClickDisposalCycleCount+
						"After Click count is " + afterClickDisposalCycleCount + "Downtime check box is working fine before click count is " + beforeClickDowntimeCount+
						"After Click count is " + afterClickDowntimeCount + "Net Idle Check box is working fine before click count is " + beforeClickNetIdleCount+
						"After Click count is " + afterClickNetIdleCount , "PassedRouteSummaryGraphLegands");
			}
			else {
				Report.FailTestSnapshot(test, driver, "Pre Route Check Box is not working fine Before click  count is  " + beforeClickPreRouteCount + 
						"after Click Pre Route count is "+ afterClickPreRouteCount + "Post Route Check Box is not working fine Before Click Count is " +beforeClickPostRouteCount+
						"After click count is "+afterClickPostRouteCount + "Disposal cycle check box is not working fine before click count is " +beforeClickDisposalCycleCount+
						"After Click count is " + afterClickDisposalCycleCount + "Downtime check box is not working fine before click count is " + beforeClickDowntimeCount+
						"After Click count is " + afterClickDowntimeCount + "Net Idle Check box is not working fine before click count is " + beforeClickNetIdleCount+
						"After Click count is " + afterClickNetIdleCount , "FailedRouteSummaryGraphLegands");
			}
			
			for (int i = 1; i <=TotalCheckBoxesInRouteSummary.size(); i++) {
				driver.findElement(By.xpath(".//*[@id='chtCk"+i+"']")).click();
				Thread.sleep(1000);
			}
			
			Util.pageScroll(0,250);
			
			beforeClickHOCCount=HOCCount.size();
			beforeClickETACount=ETACount.size();
			beforeClickMPUCount=MPUCount.size();
			
			for (int j = 0; j <=TotalCheckBoxesInServiceExceptions.size(); j++) {
				driver.findElement(By.xpath(".//*[@id='chtCk"+j+"']")).click();
				Thread.sleep(1000);				
				
			}
			afterClickHOCCount=HOCCount.size();
			aferClickETACount=ETACount.size();
			afterClickMPUCount=MPUCount.size();
			
			if (beforeClickHOCCount!=afterClickHOCCount && beforeClickETACount!=aferClickETACount && beforeClickMPUCount!=afterClickMPUCount) {
				Report.PassTestScreenshot(test, driver, "HOC, ETA ,MPu Check box is working fine", "PassedCheckboxclick");
			}
			else {
				Report.FailTestSnapshot(test, driver, "HOC, ETA ,MPu Check box is working fine", "FailedCheckboxclick");
			}
			
			
			
			
			
			
		} catch (Exception e) {
			
			Report.FailTest(test, "Failed Grapth Legand Validation");
		}
		
		
	}
	
	
	


}// Last closing bracket