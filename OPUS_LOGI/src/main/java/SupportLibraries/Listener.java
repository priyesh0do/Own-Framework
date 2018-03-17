package SupportLibraries;

import java.io.IOException;
import java.util.Set;

import org.testng.IClass;
import org.testng.IExecutionListener;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.aventstack.extentreports.TestListener;
import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Screencast;
import com.aventstack.extentreports.model.Test;

public class Listener implements IExecutionListener  {

	@Override
	public void onExecutionStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExecutionFinish() {
		System.out.println("After execution completion");
		Util.generateExcelReport();
	}

	




}
