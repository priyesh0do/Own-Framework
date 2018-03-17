package SupportLibraries;

public class GlobalVariables {
	
	String EzPayIDValue;
	public static String rootPath = "R:\\Automation\\OPUS_LOGI\\";
	public static String projectRootPath=System.getProperty("user.dir");
	public static String tcPath = rootPath + "Test Case/";
	public static String testDataPath = rootPath + "TestData/";
	public static Excel sdoTestCase = new Excel(tcPath+"Test Case Sheet.xlsx");
	public static Excel logiTestCase = new Excel(testDataPath+"Opus_LOGI_TestData.xlsx");
	public static Excel EMAP = new Excel(testDataPath+"EMAP Planning.xlsx");
	public static Excel testCaseDriver=new Excel(rootPath+"TestCaseDriver.xlsm");
	
}
