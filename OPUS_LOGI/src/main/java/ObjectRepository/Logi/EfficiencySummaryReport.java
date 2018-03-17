package ObjectRepository.Logi;

import java.io.IOException;
import java.text.DecimalFormat;
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

public class EfficiencySummaryReport {
	
	ExtentTest test;
	WebDriver driver;

	public EfficiencySummaryReport(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath = "//table[@id='dtEfficiencySummary']/thead/tr/th")
	List<WebElement> summaryTableColumns;

	@FindBy(xpath = "//table[@id='dtEfficiencySummary']/tbody/tr")
	List<WebElement> summaryTableRows;
	
	@FindBy(xpath = "//table[@id='dtEfficiencyDetail']/thead/tr/th")
	List<WebElement> efficiencyDetailTableColumns;

	@FindBy(xpath = "//table[@id='dtEfficiencyDetail']/tbody/tr")
	List<WebElement> efficiencyDetailTableRows;
	
	
	public Map<String, List<String>> getActualSummaryTableData() throws IOException {
		System.out.println("inside Actual Summary Data");
		Map<String, List<String>> summaryTableData = new HashMap<>();
		try {
			summaryTableData = readTable(summaryTableColumns, summaryTableRows, "dtEfficiencySummary");
			Report.PassTestScreenshot(test, driver, "Summary table data read successfully", "Summary Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read summary table data");
		}

		for (Entry<String, List<String>> entry : summaryTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		return summaryTableData;
	}
	
	public Map<String, List<String>> getActualDetailTableData() {
		System.out.println("inside Actual Efficiency Summary Detail Table Data");
		Util.CaptureScreenshot("Actual Efficiency Summary Detail Table Data");
		Map<String, List<String>> efficiencySummaryDetailTableData = new HashMap<>();
		try {
			efficiencySummaryDetailTableData = readTable(efficiencyDetailTableColumns, efficiencyDetailTableRows, "dtEfficiencyDetail");
			Report.PassTestScreenshot(test, driver, "Actual Efficiency Summary Detail table data read successfully",
					"Efficiency Summary Detail Data");
		} catch (Exception e) {
			Report.FailTest(test, "Not able to read Efficiency Summary Detail table data");
		}
		for (Entry<String, List<String>> entry : efficiencySummaryDetailTableData.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}

		return efficiencySummaryDetailTableData;
	}
	
	public Map<String, List<String>> readTable(List<WebElement> columns, List<WebElement> rows, String tableName) {

		if (tableName.equals("dtEfficiencySummary")) {
			Map<String, List<String>> objTable = new HashMap<>();
			for (int iCount = 2; iCount <= columns.size(); iCount++) {

				for (int row = 1; row <= rows.size(); row++) {

					List<String> rowValues=new ArrayList<>();
					try {
						rowValues.add(driver.findElement(By.xpath(
								"//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + iCount + "]/span"))
								.getText());
						String summaryItem = driver.findElement(By.xpath("//table[@id='" + tableName + "']/tbody/tr[" + row + "]/td[" + 1 + "]/span")).getText();
						objTable.put(summaryItem+" "+columns.get(iCount - 1).getText(), rowValues);
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

		else if (tableName.equals("t2")) {
			Map<String, List<String>> objTable = new HashMap<>();
		
			for (int iCount = 1; iCount <= columns.size(); iCount++) {
				
				List<String> rowValues = new ArrayList<>();
				for (int row = 1; row < rows.size(); row = row + 2) {
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
				objTable.put(columns.get(iCount - 1).getText().replace("\n", ""), rowValues);
			}
			return objTable;
		} else {
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
				objTable.put(columns.get(iCount - 1).getText().replace("\n", ""), rowValues);
			}
			return objTable;
		}
	}

	public void validateEfficiencySummaryData(Map<String, List<String>> actualEfficiencySummaryTable,
			Map<String, List<String>> actualEfficiencyDetailTable, Map<String, List<String>> actualTravelUnitsTable,String lOB) {
		
		
		       validateDriverTotalEffVar(actualEfficiencySummaryTable,lOB);
		       validateDriverAvgEffVar(actualEfficiencySummaryTable,lOB);
		       validateDriverAndHelperTotalEffVar(actualEfficiencySummaryTable,actualEfficiencyDetailTable,lOB);
		       validateDriverAndHelperAvgEffVar(actualEfficiencySummaryTable,lOB);
		       validateDriverTotalActualEff(actualEfficiencySummaryTable,lOB);
		       validateDriverAvgActualEff(actualEfficiencySummaryTable,lOB);
		       validateDriverAndHelperTotalActualEff(actualEfficiencySummaryTable,lOB);
		       validateDriverAndHelperAvgActualEff(actualEfficiencySummaryTable,actualEfficiencyDetailTable,lOB);
		       validateDriverTotalPlanEff(actualEfficiencySummaryTable,lOB);
		       validateDriverAvgPlanEff(actualEfficiencySummaryTable,lOB);
		       validateDriverAndHelperTotalPlanEff(actualEfficiencySummaryTable,lOB);
		       validateDriverAndHelperAvgPlanEff(actualEfficiencySummaryTable,lOB);      		       
		       validateDriverTotalActualUnits(actualEfficiencySummaryTable,actualEfficiencyDetailTable,lOB);
		       validateDriverAvgActualUnits(actualEfficiencySummaryTable,lOB);
		       validateDriverAndHelperTotalActualUnits(actualEfficiencySummaryTable,lOB);
		       validateDriverAndHelperAvgActualUnits(actualEfficiencySummaryTable,lOB);
		       validateDriverTotalPlanUnits(actualEfficiencySummaryTable,actualEfficiencyDetailTable,lOB);
		       validateDriverAvgPlanUnits(actualEfficiencySummaryTable,lOB);
		       validateDriverAndHelperTotalPlanUnits(actualEfficiencySummaryTable,lOB);
		       validateDriverAndHelperAvgPlanUnits(actualEfficiencySummaryTable,lOB);
		       validateDriverTotalActualHours(actualEfficiencySummaryTable,actualEfficiencyDetailTable,lOB);
		       validateDriverAvgActualHours(actualEfficiencySummaryTable,lOB);
		       validateDriverAndHelperTotalActualHours(actualEfficiencySummaryTable,actualEfficiencyDetailTable,lOB);	       
		       validateDriverAndHelperAvgActualHours(actualEfficiencySummaryTable,lOB);
		       validateDriverTotalPlanDriverHours(actualEfficiencySummaryTable,actualEfficiencyDetailTable,lOB);
		       validateDriverAvgPlanDriverHours(actualEfficiencySummaryTable,lOB);
		       validateDriverAndHelperTotalPlanDriverHours(actualEfficiencySummaryTable,actualEfficiencyDetailTable,lOB);
		       validateDriverAndHelperAvgPlanDriverHours(actualEfficiencySummaryTable,lOB);		      
		       validateDriverTotalMiles(actualEfficiencySummaryTable,actualEfficiencyDetailTable,lOB);
		       validateDriverAvgMiles(actualEfficiencySummaryTable,lOB);
		       validateDriverAndHelperTotalMiles(actualEfficiencySummaryTable,lOB);
		       validateDriverAndHelperAvgMiles(actualEfficiencySummaryTable,lOB);
		       validateDriverTotalUnitsPerMile(actualEfficiencySummaryTable,lOB);
		       validateDriverAvgUnitsPerMile(actualEfficiencySummaryTable,actualEfficiencyDetailTable,actualTravelUnitsTable,lOB);
		       validateDriverAndHelperTotalUnitsPerMile(actualEfficiencySummaryTable,lOB);
		       validateDriverAndHelperAvgUnitsPerMile(actualEfficiencySummaryTable,lOB);
		       validateDriverTotalLiftsPerMile(actualEfficiencySummaryTable,lOB);
		       validateDriverAvgLiftsPerMile(actualEfficiencySummaryTable,actualEfficiencyDetailTable,actualTravelUnitsTable,lOB);
		       validateDriverAndHelperTotalLiftsPerMile(actualEfficiencySummaryTable,lOB);
		       validateDriverAndHelperAvgLiftsPerMile(actualEfficiencySummaryTable,lOB);
		       validateDriverTotalMilesPerEqHaul(actualEfficiencySummaryTable,lOB);
		       validateDriverAvgMilesPerEqHaul(actualEfficiencySummaryTable,actualEfficiencyDetailTable,actualTravelUnitsTable,lOB);
		       validateDriverAndHelperTotalMilesPerEqHaul(actualEfficiencySummaryTable,lOB);
		       validateDriverAndHelperAvgMilesPerEqHaul(actualEfficiencySummaryTable,lOB);
		       validateDriverTotalSwapOutPercent(actualEfficiencySummaryTable,lOB);
		       validateDriverAvgSwapOutPercent(actualEfficiencySummaryTable,actualEfficiencyDetailTable,lOB);
		       validateDriverAndHelperTotalSwapOutPercent(actualEfficiencySummaryTable,lOB);
		       validateDriverAndHelperAvgSwapOutPercent(actualEfficiencySummaryTable,lOB);		        
		       validateDriverTotalNumberOfSites(actualEfficiencySummaryTable,actualEfficiencyDetailTable,lOB);
		       validateDriverAvgNumberOfSites(actualEfficiencySummaryTable,lOB);		      			
		       validateDriverAndHelperTotalNumberOfSites(actualEfficiencySummaryTable,actualEfficiencyDetailTable,lOB);		
		       validateDriverAndHelperAvgNumberOfSites(actualEfficiencySummaryTable,lOB);
		
	}
	
	public void validateEfficiencySummaryDetailData(Map<String, List<String>> actualEfficiencyDetailTable,Map<String, List<String>>  actualEfficiencyPerformanceSummaryTable,Map<String, List<String>> actualPerformanceTable,Map<String, List<String>> actualTravelUnitsTable,String lOB) {


		 

		 	
		validateDetailEffVar(actualEfficiencyDetailTable,lOB);
        validateDetailActualEff(actualEfficiencyDetailTable,actualEfficiencyPerformanceSummaryTable,lOB);		       
        validateDetailPlanEff(actualEfficiencyDetailTable,actualEfficiencyPerformanceSummaryTable,lOB);
        validateDetailActualUnits(actualEfficiencyDetailTable,actualEfficiencyPerformanceSummaryTable,lOB);
        validateDetailPlanUnits(actualEfficiencyDetailTable,actualEfficiencyPerformanceSummaryTable,lOB);
        validateDetailActualDriverHours(actualEfficiencyDetailTable,actualEfficiencyPerformanceSummaryTable,lOB);
        validateDetailPlanDriverHours(actualEfficiencyDetailTable,actualEfficiencyPerformanceSummaryTable,lOB);
        validateDetailHelperHour(actualEfficiencyDetailTable,actualPerformanceTable,lOB);
        validateDetailMiles(actualEfficiencyDetailTable,actualTravelUnitsTable,lOB);
        validateDetailUnitsPerMile(actualEfficiencyDetailTable,lOB);

				/*validateDetailTier(actualEfficiencyDetailTable,lOB);
		 		validateDetailArea(actualEfficiencyDetailTable,lOB);
		 		validateDetailBU(actualEfficiencyDetailTable,lOB);
		        validateDetailSite(actualEfficiencyDetailTable,lOB);
		        validateSwapOutPercent(actualEfficiencyDetailTable,lOB);
		        validateMilesPerEqHaul(actualEfficiencyDetailTable,lOB);		       
		        validateLiftsPerMile(actualEfficiencyDetailTable,lOB);*/
		
	}

	private void validateDetailUnitsPerMile(Map<String, List<String>> actualEfficiencyDetailTable, String lOB) {
		
		//units per miles = actual units / miles
		
		List<Double> actualUnitsPerMile = new ArrayList<>();
		List<Double> actualUnits= new ArrayList<>();
		List<Integer> miles= new ArrayList<>();
		
		double expectedUnitsPerMile = 0.00;
		int noOfSites=0;
		DecimalFormat formatter = new DecimalFormat("#0.00");
		try {
			for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().equals("Units Per Mile")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualUnitsPerMile.add(0.00);
						} else {
							actualUnitsPerMile.add(Double.parseDouble(entry.getValue().get(i)));
							// Validate Decimal Format for the value : should
							// have 2 decimal places
							Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, "Units per Mile in Detail section");
						}
					}
				}
			}
			

			for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualUnits.add(0.0);
						} else {
							actualUnits.add(Double.parseDouble(entry.getValue().get(i)));
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().equals("Miles")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							miles.add(0);
						} else {
							miles.add(Integer.parseInt(entry.getValue().get(i)));
						}
					}
				}
			}
			
			
			for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().contains("Site")) {
					noOfSites=entry.getValue().size();
				}
			}
			
		
		

		
		
		for(int i=0;i<noOfSites;i++)
		{
			expectedUnitsPerMile = actualUnits.get(i)/miles.get(i);			
			if (Util.compareNumber(actualUnits.get(i), expectedUnitsPerMile)) {
				Report.InfoTest(test,
						"Units Per Miles is correct in detail section of Efficiency Summary report Actual is : "
								+ formatter.format(actualUnits.get(i)) + " and Expected is : "
								+ formatter.format(expectedUnitsPerMile));
				Report.PassTest(test, "Units Per Miles is as expected in detail section of Efficiency Summary report");
			} else {
				Report.FailTest(test, "Units Per Miles is not as expected in detail section of Efficiency Summary report Actual is : "
						+ actualUnits.get(i) + " and Expected is : " + expectedUnitsPerMile);
			}
		} 
	}catch (Exception e) {
			Report.FailTest(test, "Units Per Miles is not as expected in detail section of Efficiency Summary report Actual is : "
					+ actualUnits + " and Expected is : " + expectedUnitsPerMile);
		}
	}

	private void validateDetailMiles(Map<String, List<String>> actualEfficiencyDetailTable,
			Map<String, List<String>> actualTravelUnitsTable, String lOB) {
		
		// Miles traveled=sum of miles of each route of the selected site
				List<Integer> actualMiles = new ArrayList<>();
				int expectedMiles = 0;
				int noOfSites=0;

				try {
					for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().contains("Miles")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualMiles.add(0);
								} else {
									actualMiles.add(Integer.parseInt(entry.getValue().get(i).toString()));
									Util.validateWholeNumber(Integer.parseInt(entry.getValue().get(i)), "Miles in Detail Section");
								}
							}
						}
					}

					for (Entry<String, List<String>> entry : actualTravelUnitsTable.entrySet()) {
						if (entry.getKey().contains("Actual Distance")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (!entry.getValue().get(i).equals("--")) {
									expectedMiles=expectedMiles+Integer.parseInt(entry.getValue().get(i));	
								}

							}
						}
					}

					for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().contains("Site")) {
							noOfSites=entry.getValue().size();
						}
					}
					
					
							
							
		for(int i=0;i<noOfSites;i++)
		{
					if (Util.compareNumber(actualMiles.get(i), expectedMiles)) {
						Report.InfoTest(test, "Miles is correct in detail section of efficiency summary report Actual is : "
								+ actualMiles.get(i) + " and Expected is : " + expectedMiles);
						Report.PassTest(test, "Miles is as expected in detail section of efficiency Summary report");
					} else {
						Report.FailTest(test, "Miles is not as expected in detail section of efficiency summary report Actual is : "
								+ actualMiles.get(i) + " and Expected is : " + expectedMiles);
					}
				} 
				}catch (Exception e) {
					Report.FailTest(test, "Miles is not as expected in detail section of efficiency summary report Actual is : "
							+ actualMiles + " and Expected is : " + expectedMiles);
				}
		
	}

	private void validateDetailHelperHour(Map<String, List<String>> actualEfficiencyDetailTable,
			Map<String, List<String>> actualPerformanceTable, String lOB) {
		// HelperHours=sum of helper hours of each route of the selected site
		List<String> actualHelperHours = new ArrayList<>();
		String expectedHelperHours = null;
		int totalMins = 0;
		int noOfSites=0;

		try {
			for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().contains("Helper Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualHelperHours.add("00:00");
						} else {
							actualHelperHours.add(entry.getValue().get(i).toString());
							Util.validateTimeFormat(entry.getValue().get(i), "Helper Hours in Detail Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualPerformanceTable.entrySet()) {
				if (entry.getKey().contains("Helper Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins += mins;
						}

					}
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().contains("Site")) {
					noOfSites=entry.getValue().size();
				}
			}
			
			expectedHelperHours = Util.convertMinsToHours(totalMins);
					
					
for(int i=0;i<noOfSites;i++)
{
			if (actualHelperHours.get(i).equals(expectedHelperHours)) {
				Report.InfoTest(test, "Helper Hours is correct in detail section of efficiency summary report Actual is : "
						+ actualHelperHours.get(i) + " and Expected is : " + expectedHelperHours);
				Report.PassTest(test, "Helper Hours is as expected in detail section of efficiency Summary report");
			} else {
				Report.FailTest(test, "Helper Hours is not as expected in detail section of efficiency summary report Actual is : "
						+ actualHelperHours.get(i) + " and Expected is : " + expectedHelperHours);
			}
		} 
		}catch (Exception e) {
			Report.FailTest(test, "Helper Hours is not as expected in detail section of efficiency summary report Actual is : "
					+ actualHelperHours + " and Expected is : " + expectedHelperHours);
		}
		
		
	}

	private void validateDetailPlanDriverHours(Map<String, List<String>> actualEfficiencyDetailTable,
			Map<String, List<String>> actualEfficiencyPerformanceSummaryTable, String lOB) {
		// Plan Driver Hours should be same as Plan Driver Hours in Eff Performance report
		List<String> planDriverHours = new ArrayList<>();
		List<String> expectedPlanDriverHours =new ArrayList<>();
		int noOfSites=0;
		try {
			for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().equals("Plan Driver Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							planDriverHours.add("00:00");
						} 
						
						else {
							planDriverHours.add(entry.getValue().get(i));							
							Util.validateTimeFormat(entry.getValue().get(i), "Plan Driver Hours in Detail Section");
						}
					}
				}
			}
		
			
		
			
			for (Entry<String, List<String>> entry : actualEfficiencyPerformanceSummaryTable.entrySet()) {
				if (entry.getKey().equals("Plan Driver Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedPlanDriverHours.add("00:00");
						} else {
							expectedPlanDriverHours.add(entry.getValue().get(i));
						}
					}						
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().contains("Site")) {
					noOfSites=entry.getValue().size();
				}
			}
			
			for(int i=0;i<noOfSites;i++)
			{
				if (planDriverHours.get(i).equals("00:00")) {
				Report.FailTest(test,
						"Plan Driver Hours is not calculated as expected in detail section of Efficiency summary report Actual is : -- and Expected is : "
								+ expectedPlanDriverHours.get(i));
			} else {
				
				
				if (Util.compareTime(planDriverHours.get(i), expectedPlanDriverHours.get(i))) {
					Report.InfoTest(test, "Plan Driver Hours is correct in detail section of Efficiency summary report Actual is : "
							+ planDriverHours.get(i) + " and Expected is : " + expectedPlanDriverHours.get(i));
					Report.PassTest(test, "Plan Driver Hours is as expected in detail section of Summary report");
				} else {
					Report.FailTest(test, "Plan Driver Hours is not as expected in detail section of summary report Actual is : " + planDriverHours.get(i)
							+ " and Expected is : " + expectedPlanDriverHours.get(i));
				}
			}
		 }
		}
		
		
		
		
		catch (Exception e) {
			Report.FailTest(test, "Plan Driver Hours is not as expected in detail section of summary report Actual is : " + planDriverHours
					+ " and Expected is : " + expectedPlanDriverHours);
		}
		
	}

	private void validateDetailActualDriverHours(Map<String, List<String>> actualEfficiencyDetailTable,
			Map<String, List<String>> actualEfficiencyPerformanceSummaryTable, String lOB) {

		// Actual Driver Hours should be same as Actual Driver Hours in Eff Performance report
				List<String> actualDriverHours = new ArrayList<>();
				List<String> expectedActualDriverHours =new ArrayList<>();
				int noOfSites=0;
				try {
					for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().equals("Actual Driver Hours (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualDriverHours.add("00:00");
								} 
								
								else {
									actualDriverHours.add(entry.getValue().get(i));
									Util.validateTimeFormat(entry.getValue().get(i), "Actual Driver Hours in Detail Section");
								}
							}
						}
					}
				
					
				
					
					for (Entry<String, List<String>> entry : actualEfficiencyPerformanceSummaryTable.entrySet()) {
						if (entry.getKey().equals("Actual Driver Hours (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									expectedActualDriverHours.add("00:00");
								} else {
									expectedActualDriverHours.add(entry.getValue().get(i));
								}
							}						
						}
					}

					for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().contains("Site")) {
							noOfSites=entry.getValue().size();
						}
					}
					
					for(int i=0;i<noOfSites;i++)
					{
						if (actualDriverHours.get(i).equals("00:00")) {
						Report.FailTest(test,
								"Actual Driver Hours is not calculated as expected in detail section of Efficiency summary report Actual is : -- and Expected is : "
										+ expectedActualDriverHours.get(i));
					} else {
						
						
						if (Util.compareTime(actualDriverHours.get(i), expectedActualDriverHours.get(i))) {
							Report.InfoTest(test, "Actual Driver Hours is correct in detail section of Efficiency summary report Actual is : "
									+ actualDriverHours.get(i) + " and Expected is : " + expectedActualDriverHours.get(i));
							Report.PassTest(test, "Actual Driver Hours is as expected in detail section of Summary report");
						} else {
							Report.FailTest(test, "Actual Driver Hours is not as expected in detail section of summary report Actual is : " + actualDriverHours.get(i)
									+ " and Expected is : " + expectedActualDriverHours.get(i));
						}
					}
				 }
				}
				
				
				
				
				catch (Exception e) {
					Report.FailTest(test, "Actual Driver Hours is not as expected in detail section of summary report Actual is : " + actualDriverHours
							+ " and Expected is : " + expectedActualDriverHours);
				}
		
	}

	private void validateDetailPlanUnits(Map<String, List<String>> actualEfficiencyDetailTable,
			Map<String, List<String>> actualEfficiencyPerformanceSummaryTable, String lOB) {
		
		// Plan Units should be same as Plan Units in Eff Performance report
				List<Double>planUnits = new ArrayList<>();
				List<Double> expectedPlanUnits =new ArrayList<>();
				int noOfSites=0;
				try {
					for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().equals("Plan Units")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									planUnits.add(0.00);
								} 
								
								else {
									planUnits.add(Double.parseDouble(entry.getValue().get(i)));
									Util.validateFieldDecimalPlaces(entry.getValue().get(i), 1, "Plan Units in Detail Section");
								}
							}
						}
					}
				
					
				
					
					for (Entry<String, List<String>> entry : actualEfficiencyPerformanceSummaryTable.entrySet()) {
						if (entry.getKey().equals("Plan Units")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									expectedPlanUnits.add(0.00);
								} else {
									expectedPlanUnits.add(Double.parseDouble(entry.getValue().get(i)));
								}
							}						
						}
					}

					for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().contains("Site")) {
							noOfSites=entry.getValue().size();
						}
					}
					
					for(int i=0;i<noOfSites;i++)
					{
						if (planUnits.get(i) == 0.00) {
						Report.FailTest(test,
								"Plan Units is not calculated as expected in detail section of Efficiency summary report Actual is : -- and Expected is : "
										+ expectedPlanUnits.get(i));
					} else {
						
						
						if (Util.compareNumber(planUnits.get(i), expectedPlanUnits.get(i))) {
							Report.InfoTest(test, "Plan Units is correct in detail section of Efficiency summary report Actual is : "
									+ planUnits.get(i) + " and Expected is : " + expectedPlanUnits.get(i));
							Report.PassTest(test, "Plan Units is as expected in detail section of Summary report");
						} else {
							Report.FailTest(test, "Plan Units is not as expected in detail section of summary report Actual is : " + planUnits.get(i)
									+ " and Expected is : " + expectedPlanUnits.get(i));
						}
					}
				 }
				}
				
				
				
				
				catch (Exception e) {
					Report.FailTest(test, "Plan Units is not as expected in detail section of summary report Actual is : " + planUnits
							+ " and Expected is : " + expectedPlanUnits);
				}
		
	}

	private void validateDetailActualUnits(Map<String, List<String>> actualEfficiencyDetailTable,
			Map<String, List<String>> actualEfficiencyPerformanceSummaryTable, String lOB) {

		// Actual Units should be same as Actual Units in Eff Performance report
		List<Double> actualUnits = new ArrayList<>();
		List<Double> expectedActualUnits =new ArrayList<>();
		int noOfSites=0;
		try {
			for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualUnits.add(0.00);
						} 
						
						else {
							actualUnits.add(Double.parseDouble(entry.getValue().get(i)));
							Util.validateFieldDecimalPlaces(entry.getValue().get(i), 1, "Actual Units in Detail Section");
						}
					}
				}
			}
		
			
		
			
			for (Entry<String, List<String>> entry : actualEfficiencyPerformanceSummaryTable.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedActualUnits.add(0.00);
						} else {
							expectedActualUnits.add(Double.parseDouble(entry.getValue().get(i)));
						}
					}						
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().contains("Site")) {
					noOfSites=entry.getValue().size();
				}
			}
			
			for(int i=0;i<noOfSites;i++)
			{
				if (actualUnits.get(i) == 0.00) {
				Report.FailTest(test,
						"Actual Units is not calculated as expected in detail section of Efficiency summary report Actual is : -- and Expected is : "
								+ expectedActualUnits.get(i));
			} else {
				
				
				if (Util.compareNumber(actualUnits.get(i), expectedActualUnits.get(i))) {
					Report.InfoTest(test, "Actual Units is correct in detail section of Efficiency summary report Actual is : "
							+ actualUnits.get(i) + " and Expected is : " + expectedActualUnits.get(i));
					Report.PassTest(test, "Actual Units is as expected in detail section of Summary report");
				} else {
					Report.FailTest(test, "Actual Units is not as expected in detail section of summary report Actual is : " + actualUnits.get(i)
							+ " and Expected is : " + expectedActualUnits.get(i));
				}
			}
		 }
		}
		
		
		
		
		catch (Exception e) {
			Report.FailTest(test, "Actual Units is not as expected in detail section of summary report Actual is : " + actualUnits
					+ " and Expected is : " + expectedActualUnits);
		}
		
	}

	private void validateDetailPlanEff(Map<String, List<String>> actualEfficiencyDetailTable,
			Map<String, List<String>> actualEfficiencyPerformanceSummaryTable, String lOB) {
		// Plan Efficiency should be same as Plan Efficiency in Eff Performance report
		List<Double>planEff = new ArrayList<>();
		List<Double> expectedPlanEff =new ArrayList<>();
		int noOfSites=0;
		try {
			for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().equals("Plan Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							planEff.add(0.00);
						} 
						
						else {
							planEff.add(Double.parseDouble(entry.getValue().get(i)));
							Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, "Plan Eff in Detail Section");
						}
					}
				}
			}
		
			
		
			
			for (Entry<String, List<String>> entry : actualEfficiencyPerformanceSummaryTable.entrySet()) {
				if (entry.getKey().equals("Plan Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedPlanEff.add(0.00);
						} else {
							expectedPlanEff.add(Double.parseDouble(entry.getValue().get(i)));
						}
					}						
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().contains("Site")) {
					noOfSites=entry.getValue().size();
				}
			}
			
			for(int i=0;i<noOfSites;i++)
			{
				if (planEff.get(i) == 0.00) {
				Report.FailTest(test,
						"Plan Eff is not calculated as expected in detail section of Efficiency summary report Actual is : -- and Expected is : "
								+ expectedPlanEff.get(i));
			} else {
				
				
				if (Util.compareNumber(planEff.get(i), expectedPlanEff.get(i))) {
					Report.InfoTest(test, "Plan Eff is correct in detail section of Efficiency summary report Actual is : "
							+ planEff.get(i) + " and Expected is : " + expectedPlanEff.get(i));
					Report.PassTest(test, "Plan Eff is as expected in detail section of Summary report");
				} else {
					Report.FailTest(test, "Plan Eff is not as expected in detail section of summary report Actual is : " + planEff.get(i)
							+ " and Expected is : " + expectedPlanEff.get(i));
				}
			}
		 }
		}
		
		
		
		
		catch (Exception e) {
			Report.FailTest(test, "Plan Eff is not as expected in detail section of summary report Actual is : " + planEff
					+ " and Expected is : " + expectedPlanEff);
		}
		
	}

	private void validateDetailActualEff(Map<String, List<String>> actualEfficiencyDetailTable,Map<String, List<String>> actualEfficiencyPerformanceSummaryTable,String lOB) {
	
		// Actual Efficiency should be same as Actual Efficiency in Eff Performance report
				List<Double> actualEff = new ArrayList<>();
				List<Double> expectedActualEff =new ArrayList<>();
				int noOfSites=0;
				try {
					for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().equals("Actual Eff")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualEff.add(0.00);
								} 
								
								else {
									actualEff.add(Double.parseDouble(entry.getValue().get(i)));
									Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, "Actual Eff in Detail Section");
								}
							}
						}
					}
				
					
				
					
					for (Entry<String, List<String>> entry : actualEfficiencyPerformanceSummaryTable.entrySet()) {
						if (entry.getKey().equals("Actual Eff")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									expectedActualEff.add(0.00);
								} else {
									expectedActualEff.add(Double.parseDouble(entry.getValue().get(i)));
								}
							}						
						}
					}

					for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().contains("Site")) {
							noOfSites=entry.getValue().size();
						}
					}
					
					for(int i=0;i<noOfSites;i++)
					{
						if (actualEff.get(i) == 0.00) {
						Report.FailTest(test,
								"Actual Eff is not calculated as expected in detail section of Efficiency summary report Actual is : -- and Expected is : "
										+ expectedActualEff.get(i));
					} else {
						
						
						if (Util.compareNumber(actualEff.get(i), expectedActualEff.get(i))) {
							Report.InfoTest(test, "Actual Eff is correct in detail section of Efficiency summary report Actual is : "
									+ actualEff.get(i) + " and Expected is : " + expectedActualEff.get(i));
							Report.PassTest(test, "Actual Eff is as expected in detail section of Summary report");
						} else {
							Report.FailTest(test, "Actual Eff is not as expected in detail section of summary report Actual is : " + actualEff.get(i)
									+ " and Expected is : " + expectedActualEff.get(i));
						}
					}
				 }
				}
				
				
				
				
				catch (Exception e) {
					Report.FailTest(test, "Actual Eff is not as expected in detail section of summary report Actual is : " + actualEff
							+ " and Expected is : " + expectedActualEff);
				}
		
	}

	private void validateDetailEffVar(Map<String, List<String>> actualEfficiencyDetailTable, String lOB) {
		
		//[(Sum of Actual Units - Sum Of Plan Units) / (Sum of Plan Units / Sum of Plan Drvr Hours)] + [Sum of Plan Drvr Hours - Sum of Act  Drvr Hours]
	// [(Sum of Actual Units - Sum Of Plan Units) / (Plan Efficiency)] in minutes*60 + [Sum of Plan Drvr Hours - Sum of Act  Drvr Hours]
				List<Double> actualEffVariance = new ArrayList<>();
				List<Double> actualUnits= new ArrayList<>();
				List<Double> planUnits= new ArrayList<>();
				List<String> planHours=new ArrayList<>();
				List<String> actualHours=new ArrayList<>();
				double expectedEffVariance = 0.00;
				int noOfSites=0;
				DecimalFormat formatter = new DecimalFormat("#0.00");
				try {
					for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().equals("Eff Var (in hrs)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualEffVariance.add(0.00);
								} else {
									actualEffVariance.add(Double.parseDouble(entry.getValue().get(i)));
									// Validate Decimal Format for the value : should
									// have 2 decimal places
									Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, "Eff Var in Detail section");
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().contains("Plan Driver Hours (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									planHours.add("00:00");
								} else {
									planHours.add(entry.getValue().get(i).toString());

								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().contains("Actual Driver Hours (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualHours.add("00:00");
								} else {
									actualHours.add(entry.getValue().get(i).toString());

								}
							}
						}
					}

					for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().equals("Plan Units")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									planUnits.add(0.0);
								} else {
									planUnits.add(Double.parseDouble(entry.getValue().get(i)));
								}
							}
						}
					}
					for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().equals("Actual Units")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualUnits.add(0.0);
								} else {
									actualUnits.add(Double.parseDouble(entry.getValue().get(i)));
								}
							}
						}
					}
					
					
					for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().contains("Site")) {
							noOfSites=entry.getValue().size();
						}
					}
					
					List<Double> planHoursInNumeric=new ArrayList<>();
					List<Double> actualHourInNumeric=new ArrayList<>();
					
					for(int i=0;i<planHours.size();i++)
					{
						planHoursInNumeric.add(Util.convertTimeToHours(planHours.get(i)));
					}
					
					for(int i=0;i<actualHours.size();i++)
					{
						actualHourInNumeric.add(Util.convertTimeToHours(actualHours.get(i)));
					}

				
				
				for(int i=0;i<noOfSites;i++)
				{
					expectedEffVariance = ((actualUnits.get(i)-planUnits.get(i))/(planUnits.get(i)/planHoursInNumeric.get(i)))+(planHoursInNumeric.get(i)-actualHourInNumeric.get(i));			
					if (Util.compareNumber(actualEffVariance.get(i), expectedEffVariance)) {
						Report.InfoTest(test,
								"Efficiency Variance is correct in detail section of Efficiency Summary report Actual is : "
										+ formatter.format(actualEffVariance.get(i)) + " and Expected is : "
										+ formatter.format(expectedEffVariance));
						Report.PassTest(test, "Efficiency Variance is as expected in detail section of Efficiency Summary report");
					} else {
						Report.FailTest(test, "Efficiency Variance is not as expected in detail section of Efficiency Summary report Actual is : "
								+ actualEffVariance.get(i) + " and Expected is : " + expectedEffVariance);
					}
				} 
			}catch (Exception e) {
					Report.FailTest(test, "Efficiency Variance is not as expected in detail section of Efficiency Summary report Actual is : "
							+ actualEffVariance + " and Expected is : " + expectedEffVariance);
				}
		
	}

	private void validateDriverTotalNumberOfSites(Map<String, List<String>> actualEfficiencySummaryTable,
			Map<String, List<String>> actualEfficiencyDetailTable, String lOB) {
	
		// # of sites = sites displayed in the detail section
		int actualNumberOfSites = 0;
		int expectedNumberOfSites = 0;
		
		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Total # of Sites")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualNumberOfSites = 0;
						} else {
							actualNumberOfSites = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(actualNumberOfSites, "Driver Total # of Sites in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().equals("Site")) {
					
					expectedNumberOfSites=entry.getValue().size();
				}
			}

			
				if (Util.compareNumber(actualNumberOfSites, expectedNumberOfSites)) {
					Report.InfoTest(test, "Driver Total  # of Sites is correct in Efficiency summary report Actual is : "
							+ actualNumberOfSites + " and Expected is : " + expectedNumberOfSites);
					Report.PassTest(test, "Driver Total  # of Sites is as expected in Summary report");
				} else {
					Report.FailTest(test, "Driver Total  # of Sites is not as expected in summary report Actual is : " + actualNumberOfSites
							+ " and Expected is : " + expectedNumberOfSites);
				}
	
		} catch (Exception e) {
			Report.FailTest(test, "Driver Total  # of Sites is not as expected in summary report Actual is : " + actualNumberOfSites
					+ " and Expected is : " + expectedNumberOfSites);
		}
		
	}

	private void validateDriverAvgNumberOfSites(Map<String, List<String>> actualEfficiencySummaryTable, String lOB) {

		String driverAvgNumberOfSites = null;
		String expectedDriverAvgNumberOfSites="N/A";

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Avg # of Sites")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverAvgNumberOfSites=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (driverAvgNumberOfSites.equals(expectedDriverAvgNumberOfSites)) {
				Report.InfoTest(test,
						"Driver Avg # of Sites is correct in summary report Actual is : "
								+ driverAvgNumberOfSites + " and Expected is : "
								+ expectedDriverAvgNumberOfSites);
				Report.PassTest(test, "Driver Avg # of Sites is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver Avg # of Sites is not as expected in summary report Actual is : "
						+ driverAvgNumberOfSites + " and Expected is : " + expectedDriverAvgNumberOfSites);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Avg # of Sites is not as expected in summary report Actual is : "
					+ driverAvgNumberOfSites + " and Expected is : " + expectedDriverAvgNumberOfSites);
		}
		
	}

	private void validateDriverAndHelperTotalNumberOfSites(Map<String, List<String>> actualEfficiencySummaryTable,
			Map<String, List<String>> actualEfficiencyDetailTable, String lOB) {

		// # of sites = sites displayed in the detail section
				int actualNumberOfSites = 0;
				int expectedNumberOfSites = 0;
				
				try {
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver & Helper Total # of Sites")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualNumberOfSites = 0;
								} else {
									actualNumberOfSites = Integer.parseInt(entry.getValue().get(i));
									Util.validateWholeNumber(actualNumberOfSites, "Driver Total # of Sites in Summary Section");
								}
							}
						}
					}
					for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().equals("Site")) {
							
							expectedNumberOfSites=entry.getValue().size();
						}
					}

					
						if (Util.compareNumber(actualNumberOfSites, expectedNumberOfSites)) {
							Report.InfoTest(test, "Driver Total  # of Sites is correct in Efficiency summary report Actual is : "
									+ actualNumberOfSites + " and Expected is : " + expectedNumberOfSites);
							Report.PassTest(test, "Driver Total  # of Sites is as expected in Summary report");
						} else {
							Report.FailTest(test, "Driver Total  # of Sites is not as expected in summary report Actual is : " + actualNumberOfSites
									+ " and Expected is : " + expectedNumberOfSites);
						}
			
				} catch (Exception e) {
					Report.FailTest(test, "Driver Total  # of Sites is not as expected in summary report Actual is : " + actualNumberOfSites
							+ " and Expected is : " + expectedNumberOfSites);
				}
		
	}

	private void validateDriverAndHelperAvgNumberOfSites(Map<String, List<String>> actualEfficiencySummaryTable,
			String lOB) {
		
		//Should always be N/A
		
		String driverAndHelperAvgNumberOfSites = null;
		String expectedDriverAndHelperAvgNumberOfSites="N/A";

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver & Helper Avg # of Sites")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverAndHelperAvgNumberOfSites=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (driverAndHelperAvgNumberOfSites.equals(expectedDriverAndHelperAvgNumberOfSites)) {
				Report.InfoTest(test,
						"Driver & Helper Avg # of Sites is correct in summary report Actual is : "
								+ driverAndHelperAvgNumberOfSites + " and Expected is : "
								+ expectedDriverAndHelperAvgNumberOfSites);
				Report.PassTest(test, "Driver & Helper Avg # of Sites is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Avg # of Sites is not as expected in summary report Actual is : "
						+ driverAndHelperAvgNumberOfSites + " and Expected is : " + expectedDriverAndHelperAvgNumberOfSites);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver & Helper Avg # of Sites is not as expected in summary report Actual is : "
					+ driverAndHelperAvgNumberOfSites + " and Expected is : " + expectedDriverAndHelperAvgNumberOfSites);
		}
		
	}

	private void validateDriverTotalSwapOutPercent(Map<String, List<String>> actualEfficiencySummaryTable, String lOB) {
		String driverTotalSwapOutPercent = null;
		String expectedDriverTotalSwapOutPercent="N/A";

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Total Swap Out %")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverTotalSwapOutPercent=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (driverTotalSwapOutPercent.equals(expectedDriverTotalSwapOutPercent)) {
				Report.InfoTest(test,
						"Driver Total Swap Out % is correct in summary report Actual is : "
								+ driverTotalSwapOutPercent + " and Expected is : "
								+ expectedDriverTotalSwapOutPercent);
				Report.PassTest(test, "Driver Total Swap Out % is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver Total Swap Out % is not as expected in summary report Actual is : "
						+ driverTotalSwapOutPercent + " and Expected is : " + expectedDriverTotalSwapOutPercent);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Total Swap Out % is not as expected in summary report Actual is : "
					+ driverTotalSwapOutPercent + " and Expected is : " + expectedDriverTotalSwapOutPercent);
		}
		
	}

	private void validateDriverAvgSwapOutPercent(Map<String, List<String>> actualEfficiencySummaryTable,
			Map<String, List<String>> actualEfficiencyDetailTable, String LOB) {
		//[Driver Total Swap Out %/No of Sites]
		
				int driverAvgSwapOutPercent = 0;
				int noOfSites=0;
				int expectedDriverSwapOutPercent= 0;
				int driverTotalSwapOutPercent= 0;

				String driverAvgSwapOutPercentCommAndResi=null;
				String expectedDriverAvgSwapOutPercentCommAndResi="N/A";
				
				DecimalFormat formatter = new DecimalFormat("#0.00");
				try {
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver Avg Swap Out %")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if(LOB.equals("Commercial") || LOB.equals("Residential"))
								{ 
									driverAvgSwapOutPercentCommAndResi = entry.getValue().get(i);
								} else {
									driverAvgSwapOutPercent = Integer.parseInt(entry.getValue().get(i).trim());
									Util.validateWholeNumber(driverAvgSwapOutPercent, "Driver Avg Swap Out % in Summary Section");
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().equals("Swap Out % ")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									driverTotalSwapOutPercent = driverTotalSwapOutPercent + 0;
								} else {
									driverTotalSwapOutPercent = driverTotalSwapOutPercent + Integer.parseInt(entry.getValue().get(i));
									
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().contains("Driver Total # of Sites")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									noOfSites = 0;
								} else {
									noOfSites = Integer.parseInt(entry.getValue().get(i));

								}
							}
						}
					}

					if(LOB.equals("Commercial") || LOB.equals("Residential"))
					{
					if (driverAvgSwapOutPercentCommAndResi.equals(expectedDriverAvgSwapOutPercentCommAndResi)) {
						Report.InfoTest(test,
								"Driver Avg Swap Out %  is correct in summary report Actual is : "
										+ driverAvgSwapOutPercentCommAndResi + " and Expected is : "
										+ expectedDriverAvgSwapOutPercentCommAndResi);
						Report.PassTest(test, "Driver Avg Swap Out % is as expected in Summary report");
					} else {
						Report.FailTest(test, "Driver Avg Swap Out % is not as expected in summary report Actual is : "
								+ driverAvgSwapOutPercentCommAndResi + " and Expected is : " + expectedDriverAvgSwapOutPercentCommAndResi);
					}
					}
					else
					{
					
					expectedDriverSwapOutPercent = driverTotalSwapOutPercent/noOfSites;			
					if (Util.compareNumber(driverAvgSwapOutPercent, expectedDriverSwapOutPercent)) {
						Report.InfoTest(test,
								"Driver Avg Swap Out % is correct in summary report Actual is : "
										+ formatter.format(driverAvgSwapOutPercent) + " and Expected is : "
										+ formatter.format(expectedDriverSwapOutPercent));
						Report.PassTest(test, "Driver Avg Swap Out % is as expected in Summary report");
					} else {
						Report.FailTest(test, "Driver Avg Swap Out % is not as expected in summary report Actual is : "
								+ driverAvgSwapOutPercent + " and Expected is : " + expectedDriverSwapOutPercent);
					}
					}
				} catch (Exception e) {
					Report.FailTest(test, "Driver Avg Swap Out % is not as expected in summary report Actual is : "
							+ driverAvgSwapOutPercent + " and Expected is : " + expectedDriverSwapOutPercent);
				}
	}

	private void validateDriverAndHelperTotalSwapOutPercent(Map<String, List<String>> actualEfficiencySummaryTable,
			String lOB) {
		// should be N/A
		String driverAndHelperTotalSwapOutPercent = null;
		String expectedDriverAndHelperTotalSwapOutPercent="N/A";

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver & Helper Total Swap Out %")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverAndHelperTotalSwapOutPercent=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (driverAndHelperTotalSwapOutPercent.equals(expectedDriverAndHelperTotalSwapOutPercent)) {
				Report.InfoTest(test,
						"Driver & Helper Total Swap Out % is correct in summary report Actual is : "
								+ driverAndHelperTotalSwapOutPercent + " and Expected is : "
								+ expectedDriverAndHelperTotalSwapOutPercent);
				Report.PassTest(test, "Driver & Helper Total Swap Out % is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Total Swap Out % is not as expected in summary report Actual is : "
						+ driverAndHelperTotalSwapOutPercent + " and Expected is : " + expectedDriverAndHelperTotalSwapOutPercent);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver & Helper Total Swap Out % is not as expected in summary report Actual is : "
					+ driverAndHelperTotalSwapOutPercent + " and Expected is : " + expectedDriverAndHelperTotalSwapOutPercent);
		}
		
	}

	private void validateDriverAndHelperAvgSwapOutPercent(Map<String, List<String>> actualEfficiencySummaryTable,
			String LOB) {

		// Should be NA for Comm and resi, -- for Roll oFF
		
				String driverAndHelperTotalSwapOutPercent = null;
				String expectedDriverAndHelperTotalSwapOutPercent="N/A";
				String expectedDriverAndHelperTotalSwapOutPercent_RO="--";

				
				try {
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver & Helper Total Swap Out %")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
							
								driverAndHelperTotalSwapOutPercent=entry.getValue().get(i).toString();
							}
						}
					}

				if(LOB.equals("Roll Off"))
				{
					if (driverAndHelperTotalSwapOutPercent.equals(expectedDriverAndHelperTotalSwapOutPercent_RO)) {
						Report.InfoTest(test,
								"Driver & Helper Total Swap Out % is correct in summary report Actual is : "
										+ driverAndHelperTotalSwapOutPercent + " and Expected is : "
										+ expectedDriverAndHelperTotalSwapOutPercent_RO);
						Report.PassTest(test, "Driver & Helper Total Swap Out % is as expected in Summary report");
					} else {
						Report.FailTest(test, "Driver & Helper Total Swap Out % is not as expected in summary report Actual is : "
								+ driverAndHelperTotalSwapOutPercent + " and Expected is : " + expectedDriverAndHelperTotalSwapOutPercent_RO);
					}
				}
				else
				{
					if (driverAndHelperTotalSwapOutPercent.equals(expectedDriverAndHelperTotalSwapOutPercent)) {
						Report.InfoTest(test,
								"Driver & Helper Total Swap Out % is correct in summary report Actual is : "
										+ driverAndHelperTotalSwapOutPercent + " and Expected is : "
										+ expectedDriverAndHelperTotalSwapOutPercent);
						Report.PassTest(test, "Driver & Helper Total Swap Out % is as expected in Summary report");
					} else {
						Report.FailTest(test, "Driver & Helper Total Swap Out % is not as expected in summary report Actual is : "
								+ driverAndHelperTotalSwapOutPercent + " and Expected is : " + expectedDriverAndHelperTotalSwapOutPercent);
					}
				}
				} catch (Exception e) {
					Report.FailTest(test, "Driver & Helper Total Swap Out % is not as expected in summary report Actual is : "
							+ driverAndHelperTotalSwapOutPercent + " and Expected is : " + expectedDriverAndHelperTotalSwapOutPercent);
				}
		
	}

	private void validateDriverTotalMilesPerEqHaul(Map<String, List<String>> actualEfficiencySummaryTable, String lOB) {
		String driverTotalMilesPerEqHaul = null;
		String expectedDriverTotalMilesPerEqHaul="N/A";

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Total Miles Per Eq Haul")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverTotalMilesPerEqHaul=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (driverTotalMilesPerEqHaul.equals(expectedDriverTotalMilesPerEqHaul)) {
				Report.InfoTest(test,
						"Driver Total Miles Per Eq Haul is correct in summary report Actual is : "
								+ driverTotalMilesPerEqHaul + " and Expected is : "
								+ expectedDriverTotalMilesPerEqHaul);
				Report.PassTest(test, "Driver Total Miles Per Eq Haul is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver Total Miles Per Eq Haul is not as expected in summary report Actual is : "
						+ driverTotalMilesPerEqHaul + " and Expected is : " + expectedDriverTotalMilesPerEqHaul);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Total Miles Per Eq Haul is not as expected in summary report Actual is : "
					+ driverTotalMilesPerEqHaul + " and Expected is : " + expectedDriverTotalMilesPerEqHaul);
		}
		
		
		
	}

	private void validateDriverAndHelperAvgMilesPerEqHaul(Map<String, List<String>> actualEfficiencySummaryTable,
			 String LOB) {

		// Should be NA for Comm and resi, -- for Roll oFF
		
		String driverAndHelperTotalMilesPerEqHaul = null;
		String expectedDriverAndHelperTotalMilesPerEqHaul="N/A";
		String expectedDriverAndHelperTotalMilesPerEqHaul_RO="--";

		
		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver & Helper Total Miles Per Eq Haul")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverAndHelperTotalMilesPerEqHaul=entry.getValue().get(i).toString();
					}
				}
			}

		if(LOB.equals("Roll Off"))
		{
			if (driverAndHelperTotalMilesPerEqHaul.equals(expectedDriverAndHelperTotalMilesPerEqHaul_RO)) {
				Report.InfoTest(test,
						"Driver & Helper Total Miles Per Eq Haul is correct in summary report Actual is : "
								+ driverAndHelperTotalMilesPerEqHaul + " and Expected is : "
								+ expectedDriverAndHelperTotalMilesPerEqHaul_RO);
				Report.PassTest(test, "Driver & Helper Total Miles Per Eq Haul is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Total Miles Per Eq Haul is not as expected in summary report Actual is : "
						+ driverAndHelperTotalMilesPerEqHaul + " and Expected is : " + expectedDriverAndHelperTotalMilesPerEqHaul_RO);
			}
		}
		else
		{
			if (driverAndHelperTotalMilesPerEqHaul.equals(expectedDriverAndHelperTotalMilesPerEqHaul)) {
				Report.InfoTest(test,
						"Driver & Helper Total Miles Per Eq Haul is correct in summary report Actual is : "
								+ driverAndHelperTotalMilesPerEqHaul + " and Expected is : "
								+ expectedDriverAndHelperTotalMilesPerEqHaul);
				Report.PassTest(test, "Driver & Helper Total Miles Per Eq Haul is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Total Miles Per Eq Haul is not as expected in summary report Actual is : "
						+ driverAndHelperTotalMilesPerEqHaul + " and Expected is : " + expectedDriverAndHelperTotalMilesPerEqHaul);
			}
		}
		} catch (Exception e) {
			Report.FailTest(test, "Driver & Helper Total Miles Per Eq Haul is not as expected in summary report Actual is : "
					+ driverAndHelperTotalMilesPerEqHaul + " and Expected is : " + expectedDriverAndHelperTotalMilesPerEqHaul);
		}
		
	}

	private void validateDriverAvgMilesPerEqHaul(Map<String, List<String>> actualEfficiencySummaryTable,Map<String, List<String>> actualEfficiencyDetailTable,Map<String, List<String>> actualTravelUnitsTable,String LOB) {
		// Summary Driver Avg Miles Per Eq Haul = sum of Miles Per Eq Haul of all sites
				String driverAvgMilesPerEqHaul = null;
				String expectedDriverAvgMilesPerEqHaul ="N/A";
				int driverTotalMiles=0;
				double actualDistancePerEqvHauls=0.00;
				double driverAvgMilesPerEqHaul_RO=0.00;
				double expectedAvgMilesPerEqHaul_RO=0.00;
				try {
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver Avg Miles Per Eq Haul")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									driverAvgMilesPerEqHaul_RO = 0.0;
								} 
								else if(entry.getValue().get(i).equals("N/A"))
								{
									driverAvgMilesPerEqHaul=entry.getValue().get(i);
								}
								else {
									driverAvgMilesPerEqHaul_RO = Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
									Util.validateFieldDecimalPlaces(entry.getValue().get(i).trim().replaceAll(",", ""), 2, "Driver Avg Miles Per Eq Haul in Summary Section");
								}
							}
						}
					}
					/*for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().equals("Miles Per Eq Haul")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--") || entry.getValue().get(i).equals("N/A")) {
									expectedAvgMilesPerEqHaul_RO = expectedAvgMilesPerEqHaul_RO + Double.parseDouble("0.0");
								} else {
									expectedAvgMilesPerEqHaul_RO = expectedAvgMilesPerEqHaul_RO
											+ Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
								}
							}

						}
					}*/
					
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver Total Miles")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									driverTotalMiles = 0;
								} 
								else {
									driverTotalMiles = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));								
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualTravelUnitsTable.entrySet()) {
						if (entry.getKey().equals("Distance per Eqv Hauls")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualDistancePerEqvHauls = actualDistancePerEqvHauls + Double.parseDouble("0.00");
								} else {
									actualDistancePerEqvHauls = actualDistancePerEqvHauls
											+ Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
								}
							}						
						}
					}

		if(LOB.equals("Commercial") || LOB.equals("Residential"))
		{
		if (driverAvgMilesPerEqHaul.equals(expectedDriverAvgMilesPerEqHaul)) {
			Report.InfoTest(test,
					"Driver Avg Miles Per Eq Haul is correct in summary report Actual is : "
							+ driverAvgMilesPerEqHaul + " and Expected is : "
							+ expectedDriverAvgMilesPerEqHaul);
			Report.PassTest(test, "Driver Avg Miles Per Eq Haul is as expected in Summary report");
		} else {
			Report.FailTest(test, "Driver Avg Miles Per Eq Haul is not as expected in summary report Actual is : "
					+ driverAvgMilesPerEqHaul + " and Expected is : " + expectedDriverAvgMilesPerEqHaul);
		}
		}
		else
		{
					if (driverAvgMilesPerEqHaul_RO == 0.0) {
						Report.FailTest(test,
								"Driver Avg Miles Per Eq Haul is not calculated as expected in summary report Actual is : -- and Expected is : "
										+ expectedDriverAvgMilesPerEqHaul);
					} else {
						
						expectedAvgMilesPerEqHaul_RO=actualDistancePerEqvHauls/driverTotalMiles;
						if (Util.compareNumber(driverAvgMilesPerEqHaul_RO, expectedAvgMilesPerEqHaul_RO)) {
							Report.InfoTest(test, "Driver Avg Miles Per Eq Haul is correct in Efficiency summary report Actual is : "
									+ driverAvgMilesPerEqHaul_RO + " and Expected is : " + expectedAvgMilesPerEqHaul_RO);
							Report.PassTest(test, "Driver Avg Miles Per Eq Haul is as expected in Summary report");
						} else {
							Report.FailTest(test, "Driver Avg Miles Per Eq Haul is not as expected in summary report Actual is : " + driverAvgMilesPerEqHaul_RO
									+ " and Expected is : " + expectedAvgMilesPerEqHaul_RO);
						}
					}
				}
				
			}		
				
				
				catch (Exception e) {
					Report.FailTest(test, "Driver Avg Miles Per Eq Haul is not as expected in summary report Actual is : " + driverAvgMilesPerEqHaul_RO
							+ " and Expected is : " + expectedAvgMilesPerEqHaul_RO);
				}
		
	}

	private void validateDriverAndHelperTotalMilesPerEqHaul(Map<String, List<String>> actualEfficiencySummaryTable,
			String lOB) {
		// should be N/A
				String driverAndHelperTotalMilesPerEqHaul = null;
				String expectedDriverAndHelperTotalMilesPerEqHaul="N/A";

				try {
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver & Helper Total Miles Per Eq Haul")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
							
								driverAndHelperTotalMilesPerEqHaul=entry.getValue().get(i).toString();
							}
						}
					}

					
					if (driverAndHelperTotalMilesPerEqHaul.equals(expectedDriverAndHelperTotalMilesPerEqHaul)) {
						Report.InfoTest(test,
								"Driver & Helper Total Miles Per Eq Haul is correct in summary report Actual is : "
										+ driverAndHelperTotalMilesPerEqHaul + " and Expected is : "
										+ expectedDriverAndHelperTotalMilesPerEqHaul);
						Report.PassTest(test, "Driver & Helper Total Miles Per Eq Haul is as expected in Summary report");
					} else {
						Report.FailTest(test, "Driver & Helper Total Miles Per Eq Haul is not as expected in summary report Actual is : "
								+ driverAndHelperTotalMilesPerEqHaul + " and Expected is : " + expectedDriverAndHelperTotalMilesPerEqHaul);
					}
				} catch (Exception e) {
					Report.FailTest(test, "Driver & Helper Total Miles Per Eq Haul is not as expected in summary report Actual is : "
							+ driverAndHelperTotalMilesPerEqHaul + " and Expected is : " + expectedDriverAndHelperTotalMilesPerEqHaul);
				}
		
	}

	private void validateDriverTotalLiftsPerMile(Map<String, List<String>> actualEfficiencySummaryTable, String lOB) {
		String driverTotalLiftsPerMile = null;
		String expectedDriverTotalLiftsPerMile="N/A";

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Total Lifts Per Mile")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverTotalLiftsPerMile=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (driverTotalLiftsPerMile.equals(expectedDriverTotalLiftsPerMile)) {
				Report.InfoTest(test,
						"Driver Total Lifts Per Mile is correct in summary report Actual is : "
								+ driverTotalLiftsPerMile + " and Expected is : "
								+ expectedDriverTotalLiftsPerMile);
				Report.PassTest(test, "Driver Total Lifts Per Mile is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver Total Lifts Per Mile is not as expected in summary report Actual is : "
						+ driverTotalLiftsPerMile + " and Expected is : " + expectedDriverTotalLiftsPerMile);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Total Lifts Per Mile is not as expected in summary report Actual is : "
					+ driverTotalLiftsPerMile + " and Expected is : " + expectedDriverTotalLiftsPerMile);
		}
		
		
	}

	private void validateDriverAvgLiftsPerMile(Map<String, List<String>> actualEfficiencySummaryTable,
			Map<String, List<String>> actualEfficiencyDetailTable,Map<String, List<String>> actualTravelUnitsTable, String LOB) {
		// Summary Driver Avg Lifts Per Lift = Total Lifts/Driver Total Miles
		double driverAvgLiftsPerMile = 0.00;
		double expectedDriverAvgLiftsPerMile = 0.00;
		double actualLifts=0.0;
		int driverTotalMiles=0;
		String driverAvgLiftsPerMile_RO=null;
		String expectedAvgLiftsPerMile_RO="N/A";
		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Avg Lifts Per Mile")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverAvgLiftsPerMile = 0.0;
						} 
						else if(entry.getValue().get(i).equals("N/A"))
						{
							driverAvgLiftsPerMile_RO=entry.getValue().get(i);
						}
						else {
							driverAvgLiftsPerMile = Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateFieldDecimalPlaces(entry.getValue().get(i).trim().replaceAll(",", ""), 2, "Driver Avg Lifts Per Mile in Summary Section");
						}
					}
				}
			}
			/*for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().equals("Lifts Per Mile")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--") || entry.getValue().get(i).equals("N/A")) {
							expectedDriverAvgLiftsPerMile = expectedDriverAvgLiftsPerMile + Double.parseDouble("0.0");
						} else {
							expectedDriverAvgLiftsPerMile = expectedDriverAvgLiftsPerMile
									+ Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
						}
					}

				}
			}*/
			
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Total Miles")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverTotalMiles = 0;
						} 
						else {
							driverTotalMiles = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));								
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualTravelUnitsTable.entrySet()) {
				if (entry.getKey().equals("Lifts")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualLifts = actualLifts + Double.parseDouble("0.0");
						} else {
							actualLifts = actualLifts
									+ Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
						}
					}						
				}
			}

if(LOB.equals("Roll Off"))
{
if (driverAvgLiftsPerMile_RO.equals(expectedAvgLiftsPerMile_RO)) {
	Report.InfoTest(test,
			"Driver Avg Lifts Per Mile is correct in summary report Actual is : "
					+ driverAvgLiftsPerMile_RO + " and Expected is : "
					+ expectedAvgLiftsPerMile_RO);
	Report.PassTest(test, "Driver Avg Lifts Per Mile is as expected in Summary report");
} else {
	Report.FailTest(test, "Driver Avg Lifts Per Mile is not as expected in summary report Actual is : "
			+ driverAvgLiftsPerMile_RO + " and Expected is : " + expectedAvgLiftsPerMile_RO);
}
}
else
{
			if (driverAvgLiftsPerMile == 0.0) {
				Report.FailTest(test,
						"Driver Avg Lifts Per Mile is not calculated as expected in summary report Actual is : -- and Expected is : "
								+ expectedDriverAvgLiftsPerMile);
			} else {
				
				expectedDriverAvgLiftsPerMile=actualLifts/driverTotalMiles;
				if (Util.compareNumber(driverAvgLiftsPerMile, expectedDriverAvgLiftsPerMile)) {
					Report.InfoTest(test, "Driver Avg Lifts Per Mile is correct in Efficiency summary report Actual is : "
							+ driverAvgLiftsPerMile + " and Expected is : " + expectedDriverAvgLiftsPerMile);
					Report.PassTest(test, "Driver Avg Lifts Per Mile is as expected in Summary report");
				} else {
					Report.FailTest(test, "Driver Avg Lifts Per Mile is not as expected in summary report Actual is : " + driverAvgLiftsPerMile
							+ " and Expected is : " + expectedDriverAvgLiftsPerMile);
				}
			}
		}
		
		}		
		
		
		catch (Exception e) {
			Report.FailTest(test, "Driver Avg Lifts Per Mile is not as expected in summary report Actual is : " + driverAvgLiftsPerMile
					+ " and Expected is : " + expectedDriverAvgLiftsPerMile);
		}

		
	}

	private void validateDriverAndHelperTotalLiftsPerMile(Map<String, List<String>> actualEfficiencySummaryTable,
			String lOB) {
		// should be N/A
		String driverAndHelperTotalLiftsPerMile = null;
		String expectedDriverAndHelperTotalLiftsPerMile="N/A";

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver & Helper Total Lifts Per Mile")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverAndHelperTotalLiftsPerMile=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (driverAndHelperTotalLiftsPerMile.equals(expectedDriverAndHelperTotalLiftsPerMile)) {
				Report.InfoTest(test,
						"Driver & Helper Total Lifts Per Mile is correct in summary report Actual is : "
								+ driverAndHelperTotalLiftsPerMile + " and Expected is : "
								+ expectedDriverAndHelperTotalLiftsPerMile);
				Report.PassTest(test, "Driver & Helper Total Lifts Per Mile is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Total Lifts Per Mile is not as expected in summary report Actual is : "
						+ driverAndHelperTotalLiftsPerMile + " and Expected is : " + expectedDriverAndHelperTotalLiftsPerMile);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver & Helper Total Lifts Per Mile is not as expected in summary report Actual is : "
					+ driverAndHelperTotalLiftsPerMile + " and Expected is : " + expectedDriverAndHelperTotalLiftsPerMile);
		}
		
		
	}

	private void validateDriverAndHelperAvgLiftsPerMile(Map<String, List<String>> actualEfficiencySummaryTable,
			String LOB) {
		String driverAndHelperTotalLiftsPerMile = null;
		String expectedDriverAndHelperTotalLiftsPerMile="N/A";
		String expectedDriverAndHelperTotalLiftsPerMile_RO="--";

		
		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver & Helper Total Lifts Per Mile")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverAndHelperTotalLiftsPerMile=entry.getValue().get(i).toString();
					}
				}
			}

		if(LOB.equals("Roll Off"))
		{
			if (driverAndHelperTotalLiftsPerMile.equals(expectedDriverAndHelperTotalLiftsPerMile_RO)) {
				Report.InfoTest(test,
						"Driver & Helper Total Lifts Per Mile is correct in summary report Actual is : "
								+ driverAndHelperTotalLiftsPerMile + " and Expected is : "
								+ expectedDriverAndHelperTotalLiftsPerMile_RO);
				Report.PassTest(test, "Driver & Helper Total Lifts Per Mile is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Total Lifts Per Mile is not as expected in summary report Actual is : "
						+ driverAndHelperTotalLiftsPerMile + " and Expected is : " + expectedDriverAndHelperTotalLiftsPerMile_RO);
			}
		}
		else
		{
			if (driverAndHelperTotalLiftsPerMile.equals(expectedDriverAndHelperTotalLiftsPerMile)) {
				Report.InfoTest(test,
						"Driver & Helper Total Lifts Per Mile is correct in summary report Actual is : "
								+ driverAndHelperTotalLiftsPerMile + " and Expected is : "
								+ expectedDriverAndHelperTotalLiftsPerMile);
				Report.PassTest(test, "Driver & Helper Total Lifts Per Mile is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Total Lifts Per Mile is not as expected in summary report Actual is : "
						+ driverAndHelperTotalLiftsPerMile + " and Expected is : " + expectedDriverAndHelperTotalLiftsPerMile);
			}
		}
		} catch (Exception e) {
			Report.FailTest(test, "Driver & Helper Total Lifts Per Mile is not as expected in summary report Actual is : "
					+ driverAndHelperTotalLiftsPerMile + " and Expected is : " + expectedDriverAndHelperTotalLiftsPerMile);
		}
		
	}

	private void validateDriverTotalUnitsPerMile(Map<String, List<String>> actualEfficiencySummaryTable,String lOB) {
		String driverTotalUnitsPerMile = null;
		String expectedDriverTotalUnitsPerMile="N/A";

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Total Units Per Mile")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverTotalUnitsPerMile=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (driverTotalUnitsPerMile.equals(expectedDriverTotalUnitsPerMile)) {
				Report.InfoTest(test,
						"Driver Total Units Per Mile is correct in summary report Actual is : "
								+ driverTotalUnitsPerMile + " and Expected is : "
								+ expectedDriverTotalUnitsPerMile);
				Report.PassTest(test, "Driver Total Units Per Mile is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver Total Units Per Mile is not as expected in summary report Actual is : "
						+ driverTotalUnitsPerMile + " and Expected is : " + expectedDriverTotalUnitsPerMile);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Total Units Per Mile is not as expected in summary report Actual is : "
					+ driverTotalUnitsPerMile + " and Expected is : " + expectedDriverTotalUnitsPerMile);
		}
		
	}

	private void validateDriverAvgUnitsPerMile(Map<String, List<String>> actualEfficiencySummaryTable,Map<String, List<String>>  actualEfficiencyDetailTable,Map<String, List<String>> actualTravelUnitsTable,String LOB) {
		
		// Summary Driver Avg Units Per Lift = actualUnits /driver Total Miles
				double driverAvgUnitsPerMile = 0.00;
				double expectedDriverAvgUnitsPerMile = 0.00;
				double actualUnits=0.0;
				int driverTotalMiles=0;
				String driverAvgUnitsPerMile_RO=null;
				String expectedAvgUnitsPerMile_RO="N/A";
				try {
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver Avg Units Per Mile")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									driverAvgUnitsPerMile = 0.0;
								} 
								else if(entry.getValue().get(i).equals("N/A"))
								{
									driverAvgUnitsPerMile_RO=entry.getValue().get(i);
								}
								else {
									driverAvgUnitsPerMile = Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
									Util.validateFieldDecimalPlaces(entry.getValue().get(i).trim().replaceAll(",", ""), 2, "Driver Avg Units Per Mile in Summary Section");
								}
							}
						}
					}
				/*	for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().equals("Units Per Mile")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--") || entry.getValue().get(i).equals("N/A")) {
									expectedDriverAvgUnitsPerMile = expectedDriverAvgUnitsPerMile + Double.parseDouble("0.0");
								} else {
									expectedDriverAvgUnitsPerMile = expectedDriverAvgUnitsPerMile
											+ Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
								}
							}

						}
					}*/
					
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver Total Miles")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									driverTotalMiles = 0;
								} 
								else {
									driverTotalMiles = Integer.parseInt(entry.getValue().get(i).trim().replaceAll(",", ""));								
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualTravelUnitsTable.entrySet()) {
						if (entry.getKey().equals("Actual Units")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualUnits = actualUnits + Double.parseDouble("0.0");
								} else {
									actualUnits = actualUnits
											+ Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
								}
							}						
						}
					}

 if(LOB.equals("Roll Off"))
 {
		if (driverAvgUnitsPerMile_RO.equals(expectedAvgUnitsPerMile_RO)) {
			Report.InfoTest(test,
					"Driver Avg Units Per Mile is correct in summary report Actual is : "
							+ driverAvgUnitsPerMile_RO + " and Expected is : "
							+ expectedAvgUnitsPerMile_RO);
			Report.PassTest(test, "Driver Avg Units Per Mile is as expected in Summary report");
		} else {
			Report.FailTest(test, "Driver Avg Units Per Mile is not as expected in summary report Actual is : "
					+ driverAvgUnitsPerMile_RO + " and Expected is : " + expectedAvgUnitsPerMile_RO);
		}
 }
 else
 {
	 
					if (driverAvgUnitsPerMile == 0.0) {
						Report.FailTest(test,
								"Driver Avg units Per Mile is not calculated as expected in summary report Actual is : -- and Expected is : "
										+ expectedDriverAvgUnitsPerMile);
					} 
					
					else 
					{
						expectedDriverAvgUnitsPerMile=actualUnits/driverTotalMiles;
						if (Util.compareNumber(driverAvgUnitsPerMile, expectedDriverAvgUnitsPerMile)) {
							Report.InfoTest(test, "Driver Avg units Per Mile is correct in Efficiency summary report Actual is : "
									+ driverAvgUnitsPerMile + " and Expected is : " + expectedDriverAvgUnitsPerMile);
							Report.PassTest(test, "Driver Avg units Per Mile is as expected in Summary report");
						} else {
							Report.FailTest(test, "Driver Avg units Per Mile is not as expected in summary report Actual is : " + driverAvgUnitsPerMile
									+ " and Expected is : " + expectedDriverAvgUnitsPerMile);
						}
					}
				}
				
				}		
				
				
				catch (Exception e) {
					Report.FailTest(test, "Driver Avg units Per Mile is not as expected in summary report Actual is : " + driverAvgUnitsPerMile
							+ " and Expected is : " + expectedDriverAvgUnitsPerMile);
				}
		
	}

	private void validateDriverAndHelperTotalUnitsPerMile(Map<String, List<String>> actualEfficiencySummaryTable,
			String lOB) {
		// should be N/A
		String driverAndHelperTotalUnitsPerMile = null;
		String expectedDriverAndHelperTotalUnitsPerMile="N/A";

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver & Helper Total Units Per Mile")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverAndHelperTotalUnitsPerMile=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (driverAndHelperTotalUnitsPerMile.equals(expectedDriverAndHelperTotalUnitsPerMile)) {
				Report.InfoTest(test,
						"Driver & Helper Total Units Per Mile is correct in summary report Actual is : "
								+ driverAndHelperTotalUnitsPerMile + " and Expected is : "
								+ expectedDriverAndHelperTotalUnitsPerMile);
				Report.PassTest(test, "Driver & Helper Total Units Per Mile is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Total Units Per Mile is not as expected in summary report Actual is : "
						+ driverAndHelperTotalUnitsPerMile + " and Expected is : " + expectedDriverAndHelperTotalUnitsPerMile);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver & Helper Total Units Per Mile is not as expected in summary report Actual is : "
					+ driverAndHelperTotalUnitsPerMile + " and Expected is : " + expectedDriverAndHelperTotalUnitsPerMile);
		}
		
	}

	private void validateDriverAndHelperAvgUnitsPerMile(Map<String, List<String>> actualEfficiencySummaryTable,
			String LOB) {

		String driverAndHelperTotalUnitsPerMile = null;
		String expectedDriverAndHelperTotalUnitsPerMile="N/A";
		String expectedDriverAndHelperTotalUnitsPerMile_RO="--";

		
		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver & Helper Total Units Per Mile")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverAndHelperTotalUnitsPerMile=entry.getValue().get(i).toString();
					}
				}
			}

		if(LOB.equals("Roll Off"))
		{
			if (driverAndHelperTotalUnitsPerMile.equals(expectedDriverAndHelperTotalUnitsPerMile_RO)) {
				Report.InfoTest(test,
						"Driver & Helper Total Units Per Mile is correct in summary report Actual is : "
								+ driverAndHelperTotalUnitsPerMile + " and Expected is : "
								+ expectedDriverAndHelperTotalUnitsPerMile_RO);
				Report.PassTest(test, "Driver & Helper Total Units Per Mile is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Total Units Per Mile is not as expected in summary report Actual is : "
						+ driverAndHelperTotalUnitsPerMile + " and Expected is : " + expectedDriverAndHelperTotalUnitsPerMile_RO);
			}
		}
		else
		{
			if (driverAndHelperTotalUnitsPerMile.equals(expectedDriverAndHelperTotalUnitsPerMile)) {
				Report.InfoTest(test,
						"Driver & Helper Total Units Per Mile is correct in summary report Actual is : "
								+ driverAndHelperTotalUnitsPerMile + " and Expected is : "
								+ expectedDriverAndHelperTotalUnitsPerMile);
				Report.PassTest(test, "Driver & Helper Total Units Per Mile is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Total Units Per Mile is not as expected in summary report Actual is : "
						+ driverAndHelperTotalUnitsPerMile + " and Expected is : " + expectedDriverAndHelperTotalUnitsPerMile);
			}
		}
		} catch (Exception e) {
			Report.FailTest(test, "Driver & Helper Total Units Per Mile is not as expected in summary report Actual is : "
					+ driverAndHelperTotalUnitsPerMile + " and Expected is : " + expectedDriverAndHelperTotalUnitsPerMile);
		}
		
	}

	private void validateDriverTotalMiles(Map<String, List<String>> actualEfficiencySummaryTable,
			Map<String, List<String>> actualEfficiencyDetailTable, String lOB) {
		// Summary Actual Units = sum of actual units of all sites
				int driverTotalMiles = 0;
				int expectedDriverTotalMiles = 0;
				
				try {
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver Total Miles")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									driverTotalMiles = 0;
								} else {
									driverTotalMiles = Integer.parseInt(entry.getValue().get(i));
									Util.validateWholeNumber(driverTotalMiles, "Driver Total Miles in Summary Section");
								}
							}
						}
					}
					for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().equals("Miles")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									expectedDriverTotalMiles = expectedDriverTotalMiles + Integer.parseInt("0.0");
								} else {
									expectedDriverTotalMiles = expectedDriverTotalMiles
											+ Integer.parseInt(entry.getValue().get(i));
								}
							}

						}
					}

					
				

					
				
					if (expectedDriverTotalMiles == 0.0) {
						Report.FailTest(test,
								"Driver Total Miles is not calculated as expected in summary report Actual is : -- and Expected is : "
										+ expectedDriverTotalMiles);
					} else {
						if (Util.compareNumber(driverTotalMiles, expectedDriverTotalMiles)) {
							Report.InfoTest(test, "Driver Total Miles is correct in Efficiency summary report Actual is : "
									+ driverTotalMiles + " and Expected is : " + expectedDriverTotalMiles);
							Report.PassTest(test, "Driver Total Miles is as expected in Summary report");
						} else {
							Report.FailTest(test, "Driver Total Miles is not as expected in summary report Actual is : " + driverTotalMiles
									+ " and Expected is : " + expectedDriverTotalMiles);
						}
					}
				} catch (Exception e) {
					Report.FailTest(test, "Driver Total Miles is not as expected in summary report Actual is : " + driverTotalMiles
							+ " and Expected is : " + expectedDriverTotalMiles);
				}
		
	}

	private void validateDriverAvgMiles(Map<String, List<String>> actualEfficiencySummaryTable, String lOB) {
		//[Driver Total Miles/No of Sites]
		
		int driverAvgMiles = 0;
		int noOfSites=0;
		int expectedDriverAvgMiles= 0;
		int driverTotalMiles= 0;

		DecimalFormat formatter = new DecimalFormat("#0.00");
		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Avg Miles")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverAvgMiles = 0;
						} else {
							driverAvgMiles = Integer.parseInt(entry.getValue().get(i));
							Util.validateWholeNumber(driverTotalMiles, "Driver Avg Miles in Summary Section");
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Total Miles")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverTotalMiles = 0;
						} else {
							driverTotalMiles = Integer.parseInt(entry.getValue().get(i));
							
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver Total # of Sites")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							noOfSites = 0;
						} else {
							noOfSites = Integer.parseInt(entry.getValue().get(i));

						}
					}
				}
			}

			
			expectedDriverAvgMiles = driverTotalMiles/noOfSites;			
			if (Util.compareNumber(driverAvgMiles, expectedDriverAvgMiles)) {
				Report.InfoTest(test,
						"Driver Avg Miles is correct in summary report Actual is : "
								+ formatter.format(driverAvgMiles) + " and Expected is : "
								+ formatter.format(expectedDriverAvgMiles));
				Report.PassTest(test, "Driver Avg Miles is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver Avg Miles is not as expected in summary report Actual is : "
						+ driverAvgMiles + " and Expected is : " + expectedDriverAvgMiles);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Avg Miles is not as expected in summary report Actual is : "
					+ driverAvgMiles + " and Expected is : " + expectedDriverAvgMiles);
		}
		
	}

	private void validateDriverAndHelperAvgMiles(Map<String, List<String>> actualEfficiencySummaryTable, String lOB) {
		// Should be --
		String driverAndHelperAvgMiles = null;
		String expectedDriverAndHelperAvgMiles="--";

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver & Helper Avg Miles")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverAndHelperAvgMiles=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (driverAndHelperAvgMiles.equals(expectedDriverAndHelperAvgMiles)) {
				Report.InfoTest(test,
						"Driver & Helper Avg Miles is correct in summary report Actual is : "
								+ driverAndHelperAvgMiles + " and Expected is : "
								+ expectedDriverAndHelperAvgMiles);
				Report.PassTest(test, "Driver & Helper Avg Miles is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Avg Miles is not as expected in summary report Actual is : "
						+ driverAndHelperAvgMiles + " and Expected is : " + expectedDriverAndHelperAvgMiles);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver & Helper Avg Miles is not as expected in summary report Actual is : "
					+ driverAndHelperAvgMiles + " and Expected is : " + expectedDriverAndHelperAvgMiles);
		}
		
	}

	private void validateDriverAndHelperTotalMiles(Map<String, List<String>> actualEfficiencySummaryTable, String lOB) {
		// Should be --
				String driverAndHelperTotalMiles = null;
				String expectedDriverAndHelperTotalMiles="--";

				try {
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver & Helper Total Miles")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
							
								driverAndHelperTotalMiles=entry.getValue().get(i).toString();
							}
						}
					}

					
					if (driverAndHelperTotalMiles.equals(expectedDriverAndHelperTotalMiles)) {
						Report.InfoTest(test,
								"Driver & Helper Total Miles is correct in summary report Actual is : "
										+ driverAndHelperTotalMiles + " and Expected is : "
										+ expectedDriverAndHelperTotalMiles);
						Report.PassTest(test, "Driver & Helper Total Miles is as expected in Summary report");
					} else {
						Report.FailTest(test, "Driver & Helper Total Miles is not as expected in summary report Actual is : "
								+ driverAndHelperTotalMiles + " and Expected is : " + expectedDriverAndHelperTotalMiles);
					}
				} catch (Exception e) {
					Report.FailTest(test, "Driver & Helper Total Miles is not as expected in summary report Actual is : "
							+ driverAndHelperTotalMiles + " and Expected is : " + expectedDriverAndHelperTotalMiles);
				}
		
	}

	private void validateDriverTotalActualHours(Map<String, List<String>> actualEfficiencySummaryTable, Map<String, List<String>> actualEfficiencyDetailTable, String lOB) {
		// DriverTotalActualHours=sum of actual driver hours for all sites
		String driverTotalActualHours = null;
		String expectedDriverTotalActualHours = null;
		int totalMins = 0;

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver Total Actual Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverTotalActualHours = null;
						} else {
							driverTotalActualHours = entry.getValue().get(i).toString();
							Util.validateTimeFormat(driverTotalActualHours, "Driver Total Actual Hours in Summary Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().contains("Actual Driver Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins += mins;
						}

					}
				}
			}

			
			Util.convertMinsToHours(totalMins);
			
			expectedDriverTotalActualHours = Util.convertMinsToHours(totalMins);
					
					

			if (driverTotalActualHours.equals(expectedDriverTotalActualHours)) {
				Report.InfoTest(test, "Driver Total Actual Hours is correct in summary report Actual is : "
						+ driverTotalActualHours + " and Expected is : " + expectedDriverTotalActualHours);
				Report.PassTest(test, "Driver Total Actual Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver Total Actual Hours is not as expected in summary report Actual is : "
						+ driverTotalActualHours + " and Expected is : " + expectedDriverTotalActualHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Total Actual Hours is not as expected in summary report Actual is : "
					+ driverTotalActualHours + " and Expected is : " + expectedDriverTotalActualHours);
		}
		
	}

	private void validateDriverAvgActualHours(Map<String, List<String>> actualEfficiencySummaryTable, String lOB) {
		// DriverAvgActualHours=sum of actual driver hours for all sites/No of Sites
				String driverAvgActualHours = null;
				String expectedDriverAvgActualHours = null;
				int noOfSites=0;
				int totalMins = 0;

				try {
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().contains("Driver Avg Actual Hours (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									driverAvgActualHours = null;
								} else {
									driverAvgActualHours = entry.getValue().get(i).toString();
									Util.validateTimeFormat(driverAvgActualHours, "Driver Avg Actual Hours in Summary Section");
								}
							}
						}
					}

					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
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
					
					
					
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().contains("Driver Total # of Sites")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									noOfSites = 0;
								} else {
									noOfSites = Integer.parseInt(entry.getValue().get(i));

								}
							}
						}
					}


					totalMins=totalMins/noOfSites;
					expectedDriverAvgActualHours = Util.convertMinsToHours(totalMins);

					if (driverAvgActualHours.equals(expectedDriverAvgActualHours)) {
						Report.InfoTest(test, "Driver Avg Actual Hours is correct in summary report Actual is : "
								+ driverAvgActualHours + " and Expected is : " + expectedDriverAvgActualHours);
						Report.PassTest(test, "Driver Avg Actual Hours is as expected in Summary report");
					} else {
						Report.FailTest(test, "Driver Avg Actual Hours is not as expected in summary report Actual is : "
								+ driverAvgActualHours + " and Expected is : " + expectedDriverAvgActualHours);
					}
				} catch (Exception e) {
					Report.FailTest(test, "Driver Avg Actual Hours is not as expected in summary report Actual is : "
							+ driverAvgActualHours + " and Expected is : " + expectedDriverAvgActualHours);
				}
		
	}

	private void validateDriverAndHelperTotalActualHours(Map<String, List<String>> actualEfficiencySummaryTable,
			Map<String, List<String>> actualEfficiencyDetailTable, String lOB) {
		
		// DriverAndHelperTotalActualHours=sum of actual driver hours and helper hours for all sites
		
		String driverAndHelperTotalActualHours = null;
		String expectedDriverAndHelperTotalActualHours = null;
		int totalMins = 0;

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver & Helper Total Actual Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverAndHelperTotalActualHours = null;
						} else {
							driverAndHelperTotalActualHours = entry.getValue().get(i).toString();
							Util.validateTimeFormat(driverAndHelperTotalActualHours, "Driver & Helper Total Actual Hours in Summary Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().contains("Actual Driver Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins += mins;
						}

					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().contains("Helper Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins += mins;
						}

					}
				}
			}

			
			

			

			
			expectedDriverAndHelperTotalActualHours = Util.convertMinsToHours(totalMins);

			if (driverAndHelperTotalActualHours.equals(expectedDriverAndHelperTotalActualHours)) {
				Report.InfoTest(test, "Driver & Helper Total Actual Hours is correct in summary report Actual is : "
						+ driverAndHelperTotalActualHours + " and Expected is : " + expectedDriverAndHelperTotalActualHours);
				Report.PassTest(test, "Driver & Helper Total Actual Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Total Actual Hours is not as expected in summary report Actual is : "
						+ driverAndHelperTotalActualHours + " and Expected is : " + expectedDriverAndHelperTotalActualHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver & Helper Total Actual Hours is not as expected in summary report Actual is : "
					+ driverAndHelperTotalActualHours + " and Expected is : " + expectedDriverAndHelperTotalActualHours);
		}
		
	}

	private void validateDriverAndHelperAvgActualHours(Map<String, List<String>> actualEfficiencySummaryTable,
			String lOB) {
		// DriverAvgActualHours=sum of actual driver hours for all sites/No of Sites
		String driverAndHelperAvgActualHours = null;
		String expectedDriverAndHelperAvgActualHours = null;
		int noOfSites=0;
		int totalMins = 0;

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver & Helper Avg Actual Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverAndHelperAvgActualHours = null;
						} else {
							driverAndHelperAvgActualHours = entry.getValue().get(i).toString();
							Util.validateTimeFormat(driverAndHelperAvgActualHours, "Driver & Helper Avg Actual Hours in Summary Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver & Helper Total Actual Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins += mins;
						}
					}
				}
			}
			
			
			
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver Total # of Sites")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							noOfSites = 0;
						} else {
							noOfSites = Integer.parseInt(entry.getValue().get(i));

						}
					}
				}
			}


			totalMins=totalMins/noOfSites;
			expectedDriverAndHelperAvgActualHours =Util.convertMinsToHours(totalMins);

			if (driverAndHelperAvgActualHours.equals(expectedDriverAndHelperAvgActualHours)) {
				Report.InfoTest(test, "Driver & Helper Avg Actual Hours is correct in summary report Actual is : "
						+ driverAndHelperAvgActualHours + " and Expected is : " + expectedDriverAndHelperAvgActualHours);
				Report.PassTest(test, "Driver & Helper Avg Actual Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Avg Actual Hours is not as expected in summary report Actual is : "
						+ driverAndHelperAvgActualHours + " and Expected is : " + expectedDriverAndHelperAvgActualHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver & Helper Avg Actual Hours is not as expected in summary report Actual is : "
					+ driverAndHelperAvgActualHours + " and Expected is : " + expectedDriverAndHelperAvgActualHours);
		}
		
	}

	private void validateDriverTotalPlanDriverHours(Map<String, List<String>> actualEfficiencySummaryTable,Map<String, List<String>> actualEfficiencyDetailTable,
			String lOB) {

		// DriverTotalActualHours=sum of actual driver hours for all sites
		String driverTotalPlanHours = null;
		String expectedDriverTotalPlanHours = null;
		int totalMins = 0;

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver Total Plan Driver Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverTotalPlanHours = null;
						} else {
							driverTotalPlanHours = entry.getValue().get(i).toString();
							Util.validateTimeFormat(driverTotalPlanHours, "Driver Total Plan Hours in Summary Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().contains("Plan Driver Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins += mins;
						}

					}
				}
			}

			expectedDriverTotalPlanHours = Util.convertMinsToHours(totalMins);

			if (driverTotalPlanHours.equals(expectedDriverTotalPlanHours)) {
				Report.InfoTest(test, "Driver Total Plan Hours is correct in summary report Actual is : "
						+ driverTotalPlanHours + " and Expected is : " + expectedDriverTotalPlanHours);
				Report.PassTest(test, "Driver Total Plan Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver Total Plan Hours is not as expected in summary report Actual is : "
						+ driverTotalPlanHours + " and Expected is : " + expectedDriverTotalPlanHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Total Plan Hours is not as expected in summary report Actual is : "
					+ driverTotalPlanHours + " and Expected is : " + expectedDriverTotalPlanHours);
		}
		
	}

	private void validateDriverAvgPlanDriverHours(Map<String, List<String>> actualEfficiencySummaryTable, String lOB) {
		// DriverAvgPlanHours=sum of plan driver hours for all sites/No of Sites
		String driverAvgPlanHours = null;
		String expectedDriverAvgPlanHours = null;
		int noOfSites=0;
		int totalMins = 0;

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver Avg Plan Driver Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverAvgPlanHours = null;
						} else {
							driverAvgPlanHours = entry.getValue().get(i).toString();
							Util.validateTimeFormat(driverAvgPlanHours, "Driver Avg Plan Hours in Summary Section");
						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver Total Plan Driver Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).equals("--")) {
							String[] split = entry.getValue().get(i).split(":", 2);
							int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
							totalMins += mins;
						}
					}
				}
			}
			
			
			
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver Total # of Sites")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							noOfSites = 0;
						} else {
							noOfSites = Integer.parseInt(entry.getValue().get(i));

						}
					}
				}
			}


			totalMins=totalMins/noOfSites;
			expectedDriverAvgPlanHours = Util.convertMinsToHours(totalMins);

			if (driverAvgPlanHours.equals(expectedDriverAvgPlanHours)) {
				Report.InfoTest(test, "Driver Avg Plan Hours is correct in summary report Actual is : "
						+ driverAvgPlanHours + " and Expected is : " + expectedDriverAvgPlanHours);
				Report.PassTest(test, "Driver Avg Plan Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver Avg Plan Hours is not as expected in summary report Actual is : "
						+ driverAvgPlanHours + " and Expected is : " + expectedDriverAvgPlanHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Avg Plan Hours is not as expected in summary report Actual is : "
					+ driverAvgPlanHours + " and Expected is : " + expectedDriverAvgPlanHours);
		}
		
	}

	private void validateDriverAndHelperTotalPlanDriverHours(Map<String, List<String>> actualEfficiencySummaryTable,Map<String, List<String>> actualEfficiencyDetailTable,
			String lOB) {
		
		// Should be --
		String driverAndHelperTotalPlanDriverHours = null;
		String expectedDriverAndHelperTotalPlanDriverHours="--";

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver & Helper Total Plan Driver Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverAndHelperTotalPlanDriverHours=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (driverAndHelperTotalPlanDriverHours.equals(expectedDriverAndHelperTotalPlanDriverHours)) {
				Report.InfoTest(test,
						"Driver & Helper Total Plan Driver Hours is correct in summary report Actual is : "
								+ driverAndHelperTotalPlanDriverHours + " and Expected is : "
								+ expectedDriverAndHelperTotalPlanDriverHours);
				Report.PassTest(test, "Driver & Helper Total Plan Driver Hours is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Total Plan Driver Hours is not as expected in summary report Actual is : "
						+ driverAndHelperTotalPlanDriverHours + " and Expected is : " + expectedDriverAndHelperTotalPlanDriverHours);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver & Helper Total Plan Driver Hours is not as expected in summary report Actual is : "
					+ driverAndHelperTotalPlanDriverHours + " and Expected is : " + expectedDriverAndHelperTotalPlanDriverHours);
		}
		
	}

	private void validateDriverAndHelperAvgPlanDriverHours(Map<String, List<String>> actualEfficiencySummaryTable,
			String lOB) {
		// Should be --
				String driverAndHelperAvgPlanDriverHours = null;
				String expectedDriverAndHelperAvgPlanDriverHours="--";

				try {
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver & Helper Avg Plan Driver Hours (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
							
								driverAndHelperAvgPlanDriverHours=entry.getValue().get(i).toString();
							}
						}
					}

					
					if (driverAndHelperAvgPlanDriverHours.equals(expectedDriverAndHelperAvgPlanDriverHours)) {
						Report.InfoTest(test,
								"Driver & Helper Avg Plan Driver Hours is correct in summary report Actual is : "
										+ driverAndHelperAvgPlanDriverHours + " and Expected is : "
										+ expectedDriverAndHelperAvgPlanDriverHours);
						Report.PassTest(test, "Driver & Helper Avg Plan Driver Hours is as expected in Summary report");
					} else {
						Report.FailTest(test, "Driver & Helper Avg Plan Driver Hours is not as expected in summary report Actual is : "
								+ driverAndHelperAvgPlanDriverHours + " and Expected is : " + expectedDriverAndHelperAvgPlanDriverHours);
					}
				} catch (Exception e) {
					Report.FailTest(test, "Driver & Helper Avg Plan Driver Hours is not as expected in summary report Actual is : "
							+ driverAndHelperAvgPlanDriverHours + " and Expected is : " + expectedDriverAndHelperAvgPlanDriverHours);
				}
		
	}

	public void validateDriverTotalActualUnits(Map<String, List<String>> actualEfficiencySummaryTable,
			Map<String, List<String>> actualEfficiencyDetailTable, String lOB) {
		// Summary Actual Units = sum of actual units of all sites
		double driverTotalActualUnits = 0.0;
		double expectedDriverTotalActualUnits = 0.0;
		
		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Total Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverTotalActualUnits = 0.0;
						} else {
							driverTotalActualUnits = Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateFieldDecimalPlaces(entry.getValue().get(i).trim().replaceAll(",", ""), 1, "Driver Total Actual Units in Summary Section");
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
				if (entry.getKey().equals("Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							expectedDriverTotalActualUnits = expectedDriverTotalActualUnits + Double.parseDouble("0.0");
						} else {
							expectedDriverTotalActualUnits = expectedDriverTotalActualUnits
									+ Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
						}
					}

				}
			}

			
		

			
		
			if (expectedDriverTotalActualUnits == 0.0) {
				Report.FailTest(test,
						"Driver Total Actual Units is not calculated as expected in summary report Actual is : -- and Expected is : "
								+ expectedDriverTotalActualUnits);
			} else {
				if (Util.compareNumber(driverTotalActualUnits, expectedDriverTotalActualUnits)) {
					Report.InfoTest(test, "Driver Total Actual Units is correct in Efficiency summary report Actual is : "
							+ driverTotalActualUnits + " and Expected is : " + expectedDriverTotalActualUnits);
					Report.PassTest(test, "Driver Total Actual Units is as expected in Summary report");
				} else {
					Report.FailTest(test, "Driver Total Actual Units is not as expected in summary report Actual is : " + driverTotalActualUnits
							+ " and Expected is : " + expectedDriverTotalActualUnits);
				}
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Total Actual Units is not as expected in summary report Actual is : " + driverTotalActualUnits
					+ " and Expected is : " + expectedDriverTotalActualUnits);
		}
		
	}

	public void validateDriverAvgActualUnits(Map<String, List<String>> actualEfficiencySummaryTable, String lOB) {
		//[Driver Total Actual Units/No of Sites]
		
				double driverAvgActualUnits = 0.00;
				int noOfSites=0;
				double expectedDriverAvgActualUnits= 0.00;
				double driverTotalActualUnits = 0.00;

				DecimalFormat formatter = new DecimalFormat("#0.00");
				try {
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver Avg Actual Units")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									driverAvgActualUnits = 0.0;
								} else {
									driverAvgActualUnits = Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
									Util.validateFieldDecimalPlaces(entry.getValue().get(i).trim().replaceAll(",", ""), 1, "Driver Avg Actual Units in Summary Section");
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver Total Actual Units")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									driverTotalActualUnits = 0.0;
								} else {
									driverTotalActualUnits = Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
									
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().contains("Driver Total # of Sites")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									noOfSites = 0;
								} else {
									noOfSites = Integer.parseInt(entry.getValue().get(i));

								}
							}
						}
					}

					
					expectedDriverAvgActualUnits = driverTotalActualUnits/noOfSites;			
					if (Util.compareNumber(driverAvgActualUnits, expectedDriverAvgActualUnits)) {
						Report.InfoTest(test,
								"Driver Avg Actual Units is correct in summary report Actual is : "
										+ formatter.format(driverAvgActualUnits) + " and Expected is : "
										+ formatter.format(expectedDriverAvgActualUnits));
						Report.PassTest(test, "Driver Avg Actual Units is as expected in Summary report");
					} else {
						Report.FailTest(test, "Driver Avg Actual Units is not as expected in summary report Actual is : "
								+ driverAvgActualUnits + " and Expected is : " + expectedDriverAvgActualUnits);
					}
				} catch (Exception e) {
					Report.FailTest(test, "Driver Avg Actual Units is not as expected in summary report Actual is : "
							+ driverAvgActualUnits + " and Expected is : " + expectedDriverAvgActualUnits);
				}
		
	}

	public void validateDriverAndHelperTotalActualUnits(Map<String, List<String>> actualEfficiencySummaryTable,
			String lOB) {
		
		// Should be --
		String driverAndHelperTotalActualUnits = null;
		String expectedDriverAndHelperTotalActualUnits="--";

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver & Helper Total Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverAndHelperTotalActualUnits=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (driverAndHelperTotalActualUnits.equals(expectedDriverAndHelperTotalActualUnits)) {
				Report.InfoTest(test,
						"Driver & Helper Total Actual Units is correct in summary report Actual is : "
								+ driverAndHelperTotalActualUnits + " and Expected is : "
								+ expectedDriverAndHelperTotalActualUnits);
				Report.PassTest(test, "Driver & Helper Total Actual Units is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Total Actual Units is not as expected in summary report Actual is : "
						+ driverAndHelperTotalActualUnits + " and Expected is : " + expectedDriverAndHelperTotalActualUnits);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver & Helper Total Actual Units is not as expected in summary report Actual is : "
					+ driverAndHelperTotalActualUnits + " and Expected is : " + expectedDriverAndHelperTotalActualUnits);
		}
		
	}

	public void validateDriverAndHelperAvgActualUnits(Map<String, List<String>> actualEfficiencySummaryTable,
			String lOB) {
		// Should be --
				String driverAndHelperAvgActualUnits = null;
				String expectedDriverAndHelperAvgActualUnits="--";

				try {
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver & Helper Avg Actual Units")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
							
								driverAndHelperAvgActualUnits=entry.getValue().get(i).toString();
							}
						}
					}

					
					if (driverAndHelperAvgActualUnits.equals(expectedDriverAndHelperAvgActualUnits)) {
						Report.InfoTest(test,
								"Driver & Helper Avg Actual Units is correct in summary report Actual is : "
										+ driverAndHelperAvgActualUnits + " and Expected is : "
										+ expectedDriverAndHelperAvgActualUnits);
						Report.PassTest(test, "Driver & Helper Avg Actual Units is as expected in Summary report");
					} else {
						Report.FailTest(test, "Driver & Helper Avg Actual Units is not as expected in summary report Actual is : "
								+ driverAndHelperAvgActualUnits + " and Expected is : " + expectedDriverAndHelperAvgActualUnits);
					}
				} catch (Exception e) {
					Report.FailTest(test, "Driver & Helper Avg Actual Units is not as expected in summary report Actual is : "
							+ driverAndHelperAvgActualUnits + " and Expected is : " + expectedDriverAndHelperAvgActualUnits);
				}
		
	}

	private void validateDriverTotalPlanUnits(Map<String, List<String>> actualEfficiencySummaryTable,
			Map<String, List<String>> actualEfficiencyDetailTable, String lOB) {
		// Summary Plan Units = sum of plan units of all sites
				double driverTotalPlanUnits = 0.0;
				double expectedDriverTotalPlanUnits = 0.0;
				
				try {
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver Total Plan Units")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									driverTotalPlanUnits = 0.0;
								} else {
									driverTotalPlanUnits = Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
									Util.validateFieldDecimalPlaces(entry.getValue().get(i).trim().replaceAll(",", ""), 1, "Driver Total Plan Units in Summary Section");
								}
							}
						}
					}
					for (Entry<String, List<String>> entry : actualEfficiencyDetailTable.entrySet()) {
						if (entry.getKey().equals("Plan Units")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									expectedDriverTotalPlanUnits = expectedDriverTotalPlanUnits + Double.parseDouble("0.0");
								} else {
									expectedDriverTotalPlanUnits = expectedDriverTotalPlanUnits
											+ Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
								}
							}

						}
					}

					
				

					
				
					if (expectedDriverTotalPlanUnits == 0.0) {
						Report.FailTest(test,
								"Driver Total Plan Units is not calculated as expected in summary report Actual is : -- and Expected is : "
										+ expectedDriverTotalPlanUnits);
					} else {
						if (Util.compareNumber(driverTotalPlanUnits, expectedDriverTotalPlanUnits)) {
							Report.InfoTest(test, "Driver Total Plan Units is correct in Efficiency summary report Actual is : "
									+ driverTotalPlanUnits + " and Expected is : " + expectedDriverTotalPlanUnits);
							Report.PassTest(test, "Driver Total Plan Units is as expected in Summary report");
						} else {
							Report.FailTest(test, "Driver Total Plan Units is not as expected in summary report Actual is : " + driverTotalPlanUnits
									+ " and Expected is : " + expectedDriverTotalPlanUnits);
						}
					}
				} catch (Exception e) {
					Report.FailTest(test, "Driver Total Plan Units is not as expected in summary report Actual is : " + driverTotalPlanUnits
							+ " and Expected is : " + expectedDriverTotalPlanUnits);
				}
		
	}

	public void validateDriverAvgPlanUnits(Map<String, List<String>> actualEfficiencySummaryTable, String lOB) {

		//[Driver Total Plan Units/No of Sites]
		
		double driverAvgPlanUnits = 0.00;
		int noOfSites=0;
		double expectedDriverAvgPlanUnits= 0.00;
		double driverTotalPlanUnits = 0.00;

		DecimalFormat formatter = new DecimalFormat("#0.00");
		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Avg Plan Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverAvgPlanUnits = 0.0;
						} else {
							driverAvgPlanUnits = Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));
							Util.validateFieldDecimalPlaces(entry.getValue().get(i).trim().replaceAll(",", ""), 1, "Driver Avg Plan Units in Summary Section");
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Total Plan Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverTotalPlanUnits = 0.0;
						} else {
							driverTotalPlanUnits = Double.parseDouble(entry.getValue().get(i).trim().replaceAll(",", ""));

						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver Total # of Sites")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							noOfSites = 0;
						} else {
							noOfSites = Integer.parseInt(entry.getValue().get(i));

						}
					}
				}
			}

			
			expectedDriverAvgPlanUnits = driverTotalPlanUnits/noOfSites;			
			if (Util.compareNumber(driverAvgPlanUnits, expectedDriverAvgPlanUnits)) {
				Report.InfoTest(test,
						"Driver Avg Actual Units is correct in summary report Actual is : "
								+ formatter.format(driverAvgPlanUnits) + " and Expected is : "
								+ formatter.format(expectedDriverAvgPlanUnits));
				Report.PassTest(test, "Driver Avg Actual Units is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver Avg Actual Units is not as expected in summary report Actual is : "
						+ driverAvgPlanUnits + " and Expected is : " + expectedDriverAvgPlanUnits);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Avg Actual Units is not as expected in summary report Actual is : "
					+ driverAvgPlanUnits + " and Expected is : " + expectedDriverAvgPlanUnits);
		}
		
	}

	public void validateDriverAndHelperTotalPlanUnits(Map<String, List<String>> actualEfficiencySummaryTable,
			String lOB) {
		// Should be --
				String driverAndHelperTotalPlanUnits = null;
				String expectedDriverAndHelperTotalPlanUnits="--";

				try {
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver & Helper Total Plan Units")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
							
								driverAndHelperTotalPlanUnits=entry.getValue().get(i).toString();
							}
						}
					}

					
					if (driverAndHelperTotalPlanUnits.equals(expectedDriverAndHelperTotalPlanUnits)) {
						Report.InfoTest(test,
								"Driver & Helper Total Plan Units is correct in summary report Actual is : "
										+ driverAndHelperTotalPlanUnits + " and Expected is : "
										+ expectedDriverAndHelperTotalPlanUnits);
						Report.PassTest(test, "Driver & Helper Total Plan Units is as expected in Summary report");
					} else {
						Report.FailTest(test, "Driver & Helper Total Plan Units is not as expected in summary report Actual is : "
								+ driverAndHelperTotalPlanUnits + " and Expected is : " + expectedDriverAndHelperTotalPlanUnits);
					}
				} catch (Exception e) {
					Report.FailTest(test, "Driver & Helper Total Plan Units is not as expected in summary report Actual is : "
							+ driverAndHelperTotalPlanUnits + " and Expected is : " + expectedDriverAndHelperTotalPlanUnits);
				}
		
	}

	private void validateDriverAndHelperAvgPlanUnits(Map<String, List<String>> actualEfficiencySummaryTable,
			String lOB) {
		// Should be --
		String driverAndHelperAvgPlanUnits = null;
		String expectedDriverAndHelperAvgPlanUnits="--";

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver & Helper Avg Plan Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverAndHelperAvgPlanUnits=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (driverAndHelperAvgPlanUnits.equals(expectedDriverAndHelperAvgPlanUnits)) {
				Report.InfoTest(test,
						"Driver & Helper Avg Plan Units is correct in summary report Actual is : "
								+ driverAndHelperAvgPlanUnits + " and Expected is : "
								+ expectedDriverAndHelperAvgPlanUnits);
				Report.PassTest(test, "Driver & Helper Avg Plan Units is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Avg Plan Units is not as expected in summary report Actual is : "
						+ driverAndHelperAvgPlanUnits + " and Expected is : " + expectedDriverAndHelperAvgPlanUnits);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver & Helper Avg Plan Units is not as expected in summary report Actual is : "
					+ driverAndHelperAvgPlanUnits + " and Expected is : " + expectedDriverAndHelperAvgPlanUnits);
		}
	}

	public void validateDriverAndHelperAvgPlanEff(Map<String, List<String>> actualEfficiencySummaryTable, String lOB) {
		
		String driverAndHelperAvgPlanEff = null;
		String expectedDriverAndHelperAvgPlanEff="--";

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver & Helper Avg Plan Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverAndHelperAvgPlanEff=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (driverAndHelperAvgPlanEff.equals(expectedDriverAndHelperAvgPlanEff)) {
				Report.InfoTest(test,
						"Driver & Helper Avg Plan Efficiency is correct in summary report Actual is : "
								+ driverAndHelperAvgPlanEff + " and Expected is : "
								+ expectedDriverAndHelperAvgPlanEff);
				Report.PassTest(test, "Driver & Helper Avg Plan Efficiency is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Avg Plan Efficiency is not as expected in summary report Actual is : "
						+ driverAndHelperAvgPlanEff + " and Expected is : " + expectedDriverAndHelperAvgPlanEff);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver & Helper Avg Plan Efficiency is not as expected in summary report Actual is : "
					+ driverAndHelperAvgPlanEff + " and Expected is : " + expectedDriverAndHelperAvgPlanEff);
		}
		
	}

	public void validateDriverAndHelperTotalPlanEff(Map<String, List<String>> actualEfficiencySummaryTable,
			String lOB) {
		String driverAndHelperTotalPlanEff = null;
		String expectedDriverAndHelperTotalPlanEff="N/A";

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver & Helper Total Plan Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverAndHelperTotalPlanEff=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (driverAndHelperTotalPlanEff.equals(expectedDriverAndHelperTotalPlanEff)) {
				Report.InfoTest(test,
						"Driver & Helper Total Plan Efficiency is correct in summary report Actual is : "
								+ driverAndHelperTotalPlanEff + " and Expected is : "
								+ expectedDriverAndHelperTotalPlanEff);
				Report.PassTest(test, "Driver & Helper Total Plan Efficiency is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Total Plan Efficiency is not as expected in summary report Actual is : "
						+ driverAndHelperTotalPlanEff + " and Expected is : " + expectedDriverAndHelperTotalPlanEff);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver & Helper Total Plan Efficiency is not as expected in summary report Actual is : "
					+ driverAndHelperTotalPlanEff + " and Expected is : " + expectedDriverAndHelperTotalPlanEff);
		}
		
	}

	public void validateDriverTotalPlanEff(Map<String, List<String>> actualEfficiencySummaryTable, String lOB) {
		String driverPlanEff = null;
		String expectedDriverTotalPlanEff="N/A";

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Total Plan Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverPlanEff=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (driverPlanEff.equals(expectedDriverTotalPlanEff)) {
				Report.InfoTest(test,
						"Driver Total Plan Efficiency is correct in summary report Actual is : "
								+ driverPlanEff + " and Expected is : "
								+ expectedDriverTotalPlanEff);
				Report.PassTest(test, "Driver Total Plan Efficiency is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver Total Plan Efficiency is not as expected in summary report Actual is : "
						+ driverPlanEff + " and Expected is : " + expectedDriverTotalPlanEff);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Total Plan Efficiency is not as expected in summary report Actual is : "
					+ driverPlanEff + " and Expected is : " + expectedDriverTotalPlanEff);
		}
		
	}

	public void validateDriverAvgPlanEff(Map<String, List<String>> actualEfficiencySummaryTable, String LOB) {
	//  SUM(Plan Units) / SUM(Plan Driver Hours)
		

			double driverAvgPlanEff = 0.00;
			double driverTotalPlanUnits=0.0;
			String driverTotalPlanHours=null;			
			double expectedDriverAvgPlanlEff = 0.00;
			DecimalFormat formatter=null;
			if (LOB.equals("Commercial") || LOB.equals("Residential"))
			{
			formatter = new DecimalFormat("#0.00");
			}
			else
			{
			formatter = new DecimalFormat("#0.0000");	
			}
			try {
				for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
					if (entry.getKey().equals("Driver Avg Plan Eff")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								driverAvgPlanEff = 99.99;
							} else {
								driverAvgPlanEff = Double.parseDouble(entry.getValue().get(i));
								// Validate Decimal Format for the value : should
								// have 2 decimal places
								if (LOB.equals("Commercial") || LOB.equals("Residential"))
								Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, "Driver Avg Plan Eff in Summary Section");
								else
								Util.validateFieldDecimalPlaces(entry.getValue().get(i), 4, "Driver Avg Plan Eff in Summary Section");
							}
						}
					}
				}
				
				for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
					if (entry.getKey().equals("Driver Total Plan Units")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								driverTotalPlanUnits = 99.99;
							} else {
								driverTotalPlanUnits = Double.parseDouble(entry.getValue().get(i));

							}
						}
					}
				}
				
				for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
					if (entry.getKey().contains("Driver Total Plan Driver Hours (h:m)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								driverTotalPlanHours = null;
							} else {
								driverTotalPlanHours = entry.getValue().get(i).toString();

							}
						}
					}
				}

				double driverHours=Util.convertTimeToHours(driverTotalPlanHours);
				expectedDriverAvgPlanlEff=driverTotalPlanUnits/driverHours;		
				if (Util.compareNumber(driverAvgPlanEff, expectedDriverAvgPlanlEff)) {
					Report.InfoTest(test,
							"Driver Avg Actual Efficiency is correct in summary report Actual is : "
									+ formatter.format(driverAvgPlanEff) + " and Expected is : "
									+ formatter.format(expectedDriverAvgPlanlEff));
					Report.PassTest(test, "Driver Avg Actual Efficiency  is as expected in Summary report");
				} else {
					Report.FailTest(test, "Driver Avg Actual Efficiency  is not as expected in summary report Actual is : "
							+ driverAvgPlanEff + " and Expected is : " + expectedDriverAvgPlanlEff);
				}
			} catch (Exception e) {
				Report.FailTest(test, "Driver Avg Actual Efficiency is not as expected in summary report Actual is : "
						+ driverAvgPlanEff + " and Expected is : " + expectedDriverAvgPlanlEff);
			}
		
	}

	public void validateDriverTotalActualEff(Map<String, List<String>> actualEfficiencySummaryTable, String lOB) {
	
		String driverActualEff = null;
		String expectedDriverTotalActualEff="N/A";

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Total Actual Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverActualEff=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (driverActualEff.equals(expectedDriverTotalActualEff)) {
				Report.InfoTest(test,
						"Driver Total Actual Efficiency is correct in summary report Actual is : "
								+ driverActualEff + " and Expected is : "
								+ expectedDriverTotalActualEff);
				Report.PassTest(test, "Driver Total Actual Efficiency is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver Total Actual Efficiency is not as expected in summary report Actual is : "
						+ driverActualEff + " and Expected is : " + expectedDriverTotalActualEff);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Total Actual Efficiency is not as expected in summary report Actual is : "
					+ driverActualEff + " and Expected is : " + expectedDriverTotalActualEff);
		}
		
	}

	public void validateDriverAvgActualEff(Map<String, List<String>> actualEfficiencySummaryTable, String LOB) {
		//  SUM(Actual Units) / SUM(Actual Driver Hours)
		

		double driverAvgActualEff = 0.00;
		double driverTotalActualUnits=0.0;
		String driverTotalActualHours=null;			
		double expectedDriverAvgActualEff = 0.00;
		DecimalFormat formatter=null;
		if (LOB.equals("Commercial") || LOB.equals("Residential"))
		{
		formatter = new DecimalFormat("#0.00");
		}
		else
		{
		formatter = new DecimalFormat("#0.0000");	
		}
		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Avg Actual Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverAvgActualEff = 0.00;
						} else {
							driverAvgActualEff = Double.parseDouble(entry.getValue().get(i));
							// Validate Decimal Format for the value : should
							// have 2 decimal places
							if (LOB.equals("Commercial") || LOB.equals("Residential"))
							Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, "Driver Avg Actual Eff in Summary Section");
							else
							Util.validateFieldDecimalPlaces(entry.getValue().get(i), 4, "Driver Avg Actual Eff in Summary Section");
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Total Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverTotalActualUnits = 0.00;
						} else {
							driverTotalActualUnits = Double.parseDouble(entry.getValue().get(i));

						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver Total Actual Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverTotalActualHours = null;
						} else {
							driverTotalActualHours = entry.getValue().get(i).toString();

						}
					}
				}
			}

			double driverHours=Util.convertTimeToHours(driverTotalActualHours);
			expectedDriverAvgActualEff=driverTotalActualUnits/driverHours;		
			if (Util.compareNumber(driverAvgActualEff, expectedDriverAvgActualEff)) {
				Report.InfoTest(test,
						"Driver Avg Actual Efficiency is correct in summary report Actual is : "
								+ formatter.format(driverAvgActualEff) + " and Expected is : "
								+ formatter.format(expectedDriverAvgActualEff));
				Report.PassTest(test, "Driver Avg Actual Efficiency  is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver Avg Actual Efficiency  is not as expected in summary report Actual is : "
						+ driverAvgActualEff + " and Expected is : " + expectedDriverAvgActualEff);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Avg Actual Efficiency is not as expected in summary report Actual is : "
					+ driverAvgActualEff + " and Expected is : " + expectedDriverAvgActualEff);
		}
		
	}

	public void validateDriverAndHelperTotalActualEff(Map<String, List<String>> actualEfficiencySummaryTable,
			String lOB) {
		String driverAndHelperActualEff = null;
		String expectedDriverAndHelperTotalActualEff="N/A";

		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Total Actual Eff")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
					
						driverAndHelperActualEff=entry.getValue().get(i).toString();
					}
				}
			}

			
			if (driverAndHelperActualEff.equals(expectedDriverAndHelperTotalActualEff)) {
				Report.InfoTest(test,
						"Driver & Helper Total Actual Efficiency is correct in summary report Actual is : "
								+ driverAndHelperActualEff + " and Expected is : "
								+ expectedDriverAndHelperTotalActualEff);
				Report.PassTest(test, "Driver & Helper Total Actual Efficiency is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Total Actual Efficiency is not as expected in summary report Actual is : "
						+ driverAndHelperActualEff + " and Expected is : " + expectedDriverAndHelperTotalActualEff);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Total Actual Efficiency is not as expected in summary report Actual is : "
					+ driverAndHelperActualEff + " and Expected is : " + expectedDriverAndHelperTotalActualEff);
		}
		
	}

	public void validateDriverAndHelperAvgActualEff(Map<String, List<String>> actualEfficiencySummaryTable,Map<String, List<String>> actualEfficiencySummaryDetailTable,
			String LOB) {
	//  SUM(Actual Units) / SUM(Actual Driver Hours + Helper Hours)
		

			double driverAndHelperAvgActualEff = 0.00;
			double driverTotalActualUnits=0.0;
			String driverTotalActualHours=null;	
			String helperTotalActualHours=null;
			double expectedDriverAndHelperAvgActualEff = 0.00;
			int totalMins=0;
			DecimalFormat formatter=null;
			if (LOB.equals("Commercial") || LOB.equals("Residential"))
			{
			formatter = new DecimalFormat("#0.00");
			}
			else
			{
			formatter = new DecimalFormat("#0.0000");	
			}
			try {
				for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
					if (entry.getKey().equals("Driver & Helper Avg Actual Eff")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								driverAndHelperAvgActualEff = 0.00;
							} else {
								driverAndHelperAvgActualEff = Double.parseDouble(entry.getValue().get(i));
								// Validate Decimal Format for the value : should
								// have 2 decimal places
								if (LOB.equals("Commercial") || LOB.equals("Residential"))
								Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, "Driver & Helper Avg Actual Eff in Summary Section");
								else
								Util.validateFieldDecimalPlaces(entry.getValue().get(i), 4, "Driver & Helper Avg Actual Eff in Summary Section");
							}
						}
					}
				}
				
				for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
					if (entry.getKey().equals("Driver Total Actual Units")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								driverTotalActualUnits = 0.00;
							} else {
								driverTotalActualUnits = Double.parseDouble(entry.getValue().get(i));

							}
						}
					}
				}
				
				for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
					if (entry.getKey().contains("Driver Total Actual Hours (h:m)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).equals("--")) {
								driverTotalActualHours = null;
							} else {
								driverTotalActualHours = entry.getValue().get(i).toString();

							}
						}
					}
				}
				
				for (Entry<String, List<String>> entry : actualEfficiencySummaryDetailTable.entrySet()) {
					if (entry.getKey().equals("Helper Hours (h:m)")) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (!entry.getValue().get(i).equals("--")) {
								String[] split = entry.getValue().get(i).split(":", 2);
								int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
								totalMins += mins;
							}

						}
					}
				}

				helperTotalActualHours= Util.convertMinsToHours(totalMins);
				
				double helperTotalHours=Util.convertTimeToHours(helperTotalActualHours);
				double driverHours=Util.convertTimeToHours(driverTotalActualHours);
				expectedDriverAndHelperAvgActualEff=driverTotalActualUnits/(driverHours+helperTotalHours);		
				if (Util.compareNumber(driverAndHelperAvgActualEff, expectedDriverAndHelperAvgActualEff)) {
					Report.InfoTest(test,
							"Driver Avg Actual Efficiency is correct in summary report Actual is : "
									+ formatter.format(driverAndHelperAvgActualEff) + " and Expected is : "
									+ formatter.format(expectedDriverAndHelperAvgActualEff));
					Report.PassTest(test, "Driver Avg Actual Efficiency  is as expected in Summary report");
				} else {
					Report.FailTest(test, "Driver Avg Actual Efficiency  is not as expected in summary report Actual is : "
							+ formatter.format(driverAndHelperAvgActualEff) + " and Expected is : " + formatter.format(expectedDriverAndHelperAvgActualEff));
				}
			} catch (Exception e) {
				Report.FailTest(test, "Driver Avg Actual Efficiency is not as expected in summary report Actual is : "
						+ driverAndHelperAvgActualEff + " and Expected is : " + expectedDriverAndHelperAvgActualEff);
			}
		
	}

	public void validateDriverAndHelperTotalEffVar(Map<String, List<String>> actualEfficiencySummaryTable,Map<String, List<String>> actualEfficiencySummaryDetailTable,
			String lOB) {
		// [(Sum of Actual Units - Sum of Plan Units) / (Sum of Plan Units / Sum of Plan Paid Drvr Hours)] + [Sum of Plan Paid Drvr Hours – (Sum of Act Pd Drvr Hours + Helper hours)]
		
		
		
				double actualDriverAndHelperTotalEffVariance = 0.00;
				double driverTotalActualUnits=0.0;
				double driverTotalPlanUnits=0.0;
				String driverTotalPlanHours=null;
				String driverTotalActualHours=null;
				String helperHours=null;
				double expectedDriverandHelperTotalEffVariance = 0.00;
				int totalMins = 0;
				DecimalFormat formatter = new DecimalFormat("#0.00");
				try {
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver & Helper Total Eff Var (in hrs)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualDriverAndHelperTotalEffVariance = 99.99;
								} else {
									actualDriverAndHelperTotalEffVariance = Double.parseDouble(entry.getValue().get(i));
									// Validate Decimal Format for the value : should
									// have 2 decimal places
									Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, "Driver & Helper Total Eff Var in Summary Section");
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().contains("Driver Total Plan Driver Hours (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									driverTotalPlanHours = null;
								} else {
									driverTotalPlanHours = entry.getValue().get(i).toString();

								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().contains("Driver Total Actual Hours (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									driverTotalActualHours = null;
								} else {
									driverTotalActualHours = entry.getValue().get(i).toString();

								}
							}
						}
					}

					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver Total Plan Units")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									driverTotalPlanUnits = 99.99;
								} else {
									driverTotalPlanUnits = Double.parseDouble(entry.getValue().get(i));
								}
							}
						}
					}
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver Total Actual Units")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									driverTotalActualUnits = 99.99;
								} else {
									driverTotalActualUnits = Double.parseDouble(entry.getValue().get(i));
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualEfficiencySummaryDetailTable.entrySet()) {
						if (entry.getKey().equals("Helper Hours (h:m)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (!entry.getValue().get(i).equals("--")) {
									String[] split = entry.getValue().get(i).split(":", 2);
									int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
									totalMins += mins;
								}

							}
						}
					}

					helperHours= Util.convertMinsToHours(totalMins);
					
					double helperTotalHours=Util.convertTimeToHours(helperHours);					
					double planHours=Util.convertTimeToHours(driverTotalPlanHours);
					double actualHours=Util.convertTimeToHours(driverTotalActualHours);
					
					expectedDriverandHelperTotalEffVariance = ((driverTotalActualUnits-driverTotalPlanUnits)/(driverTotalPlanUnits/planHours))+(planHours-(actualHours+helperTotalHours));			
					if (Util.compareNumber(actualDriverAndHelperTotalEffVariance, expectedDriverandHelperTotalEffVariance)) {
						Report.InfoTest(test,
								"Driver & Helper Total Efficiency Variance is correct in summary report Actual is : "
										+ formatter.format(actualDriverAndHelperTotalEffVariance) + " and Expected is : "
										+ formatter.format(expectedDriverandHelperTotalEffVariance));
						Report.PassTest(test, "Driver & Helper Total Efficiency Variance is as expected in Summary report");
					} else {
						Report.FailTest(test, "Driver & Helper Total Efficiency Variance is not as expected in summary report Actual is : "
								+ actualDriverAndHelperTotalEffVariance + " and Expected is : " + expectedDriverandHelperTotalEffVariance);
					}
				} catch (Exception e) {
					Report.FailTest(test, "Driver & Helper Total Efficiency Variance is not as expected in summary report Actual is : "
							+ actualDriverAndHelperTotalEffVariance + " and Expected is : " + expectedDriverandHelperTotalEffVariance);
				}
		
		
	}

	public void validateDriverAndHelperAvgEffVar(Map<String, List<String>> actualEfficiencySummaryTable, String lOB) {
		//[Driver & Helper Total Eff Var/No of Sites]
		
		double driverAndHelperTotalEffVar = 0.00;
		int noOfSites=0;
		double expectedDriverAndHelperAvgEffVar = 0.00;
		double actualDriverAndHelperAvgEffVar = 0.00;

		DecimalFormat formatter = new DecimalFormat("#0.00");
		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver & Helper Avg Eff Var (in hrs)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualDriverAndHelperAvgEffVar = 99.99;
						} else {
							actualDriverAndHelperAvgEffVar = Double.parseDouble(entry.getValue().get(i));
							// Validate Decimal Format for the value : should
							// have 2 decimal places
							Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, "Driver and Helper Avg Eff Var in Summary Section");
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver & Helper Total Eff Var (in hrs)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverAndHelperTotalEffVar = 99.99;
						} else {
							driverAndHelperTotalEffVar = Double.parseDouble(entry.getValue().get(i));

						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver Total # of Sites")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							noOfSites = 0;
						} else {
							noOfSites = Integer.parseInt(entry.getValue().get(i));

						}
					}
				}
			}

			
			expectedDriverAndHelperAvgEffVar = driverAndHelperTotalEffVar/noOfSites;			
			if (Util.compareNumber(actualDriverAndHelperAvgEffVar, expectedDriverAndHelperAvgEffVar)) {
				Report.InfoTest(test,
						"Driver & Helper Avg Efficiency Variance is correct in summary report Actual is : "
								+ formatter.format(actualDriverAndHelperAvgEffVar) + " and Expected is : "
								+ formatter.format(expectedDriverAndHelperAvgEffVar));
				Report.PassTest(test, "Driver & Helper Avg Efficiency Variance is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver & Helper Avg Efficiency Variance is not as expected in summary report Actual is : "
						+ actualDriverAndHelperAvgEffVar + " and Expected is : " + expectedDriverAndHelperAvgEffVar);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver & Helper Avg Efficiency Variance is not as expected in summary report Actual is : "
					+ actualDriverAndHelperAvgEffVar + " and Expected is : " + expectedDriverAndHelperAvgEffVar);
		}
		
	}

	public void validateDriverAvgEffVar(Map<String, List<String>> actualEfficiencySummaryTable, String lOB) {
		
		//[Driver Total Eff Var/No of Sites]
		
				double driverTotalEffVar = 0.00;
				int noOfSites=0;
				double expectedDriverAvgEffVar = 0.00;
				double actualDriverAvgEffVar = 0.00;

				DecimalFormat formatter = new DecimalFormat("#0.00");
				try {
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().equals("Driver Total Eff Var (in hrs)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									driverTotalEffVar = 0.00;
								} else {
									driverTotalEffVar = Double.parseDouble(entry.getValue().get(i));
									
								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().contains("Driver Avg Eff Var (in hrs)")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									actualDriverAvgEffVar = 0.00;
								} else {
									actualDriverAvgEffVar = Double.parseDouble(entry.getValue().get(i));
									// Validate Decimal Format for the value : should
									// have 2 decimal places
									Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, "Driver Avg Eff Var in Summary Section");

								}
							}
						}
					}
					
					for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
						if (entry.getKey().contains("Driver Total # of Sites")) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								if (entry.getValue().get(i).equals("--")) {
									noOfSites = 0;
								} else {
									noOfSites = Integer.parseInt(entry.getValue().get(i));

								}
							}
						}
					}

			

		
					
					expectedDriverAvgEffVar = driverTotalEffVar/noOfSites;			
					if (Util.compareNumber(actualDriverAvgEffVar, expectedDriverAvgEffVar)) {
						Report.InfoTest(test,
								"Driver Avg Efficiency Variance is correct in summary report Actual is : "
										+ formatter.format(actualDriverAvgEffVar) + " and Expected is : "
										+ formatter.format(expectedDriverAvgEffVar));
						Report.PassTest(test, "Driver Avg Efficiency Variance is as expected in Summary report");
					} else {
						Report.FailTest(test, "Driver Avg Efficiency Variance is not as expected in summary report Actual is : "
								+ actualDriverAvgEffVar + " and Expected is : " + expectedDriverAvgEffVar);
					}
				} catch (Exception e) {
					Report.FailTest(test, "Driver Avg Efficiency Variance is not as expected in summary report Actual is : "
							+ actualDriverAvgEffVar + " and Expected is : " + expectedDriverAvgEffVar);
				}
		
	}

	public void validateDriverTotalEffVar(Map<String, List<String>> actualEfficiencySummaryTable, String lOB) {
		//[(Sum of Actual Units - Sum Of Plan Units) / (Sum of Plan Units / Sum of Plan Drvr Hours)] + [Sum of Plan Drvr Hours - Sum of Act  Drvr Hours]
		
		double actualDriverTotalEffVariance = 0.00;
		double driverTotalActualUnits=0.0;
		double driverTotalPlanUnits=0.0;
		String driverTotalPlanHours=null;
		String driverTotalActualHours=null;
		double expectedDriverTotalEffVariance = 0.00;
		DecimalFormat formatter = new DecimalFormat("#0.00");
		try {
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Total Eff Var (in hrs)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							actualDriverTotalEffVariance = 0.00;
						} else {
							actualDriverTotalEffVariance = Double.parseDouble(entry.getValue().get(i));
							// Validate Decimal Format for the value : should
							// have 2 decimal places
							Util.validateFieldDecimalPlaces(entry.getValue().get(i), 2, "Driver Total Eff Var in Summary Section");
						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver Total Plan Driver Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverTotalPlanHours = null;
						} else {
							driverTotalPlanHours = entry.getValue().get(i).toString();

						}
					}
				}
			}
			
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().contains("Driver Total Actual Hours (h:m)")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverTotalActualHours = null;
						} else {
							driverTotalActualHours = entry.getValue().get(i).toString();

						}
					}
				}
			}

			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Total Plan Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverTotalPlanUnits = 99.99;
						} else {
							driverTotalPlanUnits = Double.parseDouble(entry.getValue().get(i));
						}
					}
				}
			}
			for (Entry<String, List<String>> entry : actualEfficiencySummaryTable.entrySet()) {
				if (entry.getKey().equals("Driver Total Actual Units")) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).equals("--")) {
							driverTotalActualUnits = 99.99;
						} else {
							driverTotalActualUnits = Double.parseDouble(entry.getValue().get(i));
						}
					}
				}
			}

			double planHours=Util.convertTimeToHours(driverTotalPlanHours);
			double actualHours=Util.convertTimeToHours(driverTotalActualHours);
			
			expectedDriverTotalEffVariance = ((driverTotalActualUnits-driverTotalPlanUnits)/(driverTotalPlanUnits/planHours))+(planHours-actualHours);			
			if (Util.compareNumber(actualDriverTotalEffVariance, expectedDriverTotalEffVariance)) {
				Report.InfoTest(test,
						"Driver Total Efficiency Variance is correct in summary report Actual is : "
								+ formatter.format(actualDriverTotalEffVariance) + " and Expected is : "
								+ formatter.format(expectedDriverTotalEffVariance));
				Report.PassTest(test, "Driver Total Efficiency Variance is as expected in Summary report");
			} else {
				Report.FailTest(test, "Driver Total Efficiency Variance is not as expected in summary report Actual is : "
						+ actualDriverTotalEffVariance + " and Expected is : " + expectedDriverTotalEffVariance);
			}
		} catch (Exception e) {
			Report.FailTest(test, "Driver Total Efficiency Variance is not as expected in summary report Actual is : "
					+ actualDriverTotalEffVariance + " and Expected is : " + expectedDriverTotalEffVariance);
		}
		
	}

	

}
