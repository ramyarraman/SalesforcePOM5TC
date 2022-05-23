package com.salesforce.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class GenerateReports {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extentReportObj;
	public static ExtentTest	extentTestObj;
	private static GenerateReports obj;  
	public static String testCaseName; 
	private GenerateReports() {
		
	}
	public static GenerateReports getInstance() {
		if(obj==null) {
			obj=new GenerateReports();
		}
		return obj;
	}
	public void startExtentReports() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("MM-dd-YYhh:mm:ss");
		String formattedTime = sf.format(date);
		htmlReporter = new ExtentHtmlReporter(Constants.SALESFORCE_GENERATEREPORTS_PATH+"extentReport"+formattedTime+".html");
		htmlReporter.config().setDocumentTitle("Salesforce POM");
		htmlReporter.config().setReportName("Automation Test Reports");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		htmlReporter.config().setTimeStampFormat(formattedTime);
		
		extentReportObj = new ExtentReports();
		extentReportObj.attachReporter(htmlReporter);
		extentReportObj.setSystemInfo("Environment","Automation");
		extentReportObj.setSystemInfo("Host Name", "Salesforce");
		extentReportObj.setSystemInfo("User Ifnfo","Ramya");
		
	}
	
	public void startExtentTestReport(String testName) {
		testCaseName=testName;
		extentTestObj = extentReportObj.createTest(testCaseName);
	}
	public void logTestInfo(String message) {
		extentTestObj.log(Status.INFO,message);
	}
	public void logTestPassed() {
		extentTestObj.log(Status.PASS,MarkupHelper.createLabel(testCaseName+"Passed", ExtentColor.GREEN));
	}
	public void logTestFail(Throwable e) {
		extentTestObj.log(Status.FAIL,MarkupHelper.createLabel(testCaseName+"Failed", ExtentColor.RED));
		extentTestObj.log(Status.FAIL, e);
	}
	public void logTestSkipped() {
		extentTestObj.log(Status.SKIP,MarkupHelper.createLabel(testCaseName+"Skipped", ExtentColor.YELLOW));
	}
	public void endReport() {
		extentReportObj.flush();
	}
	public void attachScreenshot(String filePath) throws IOException {
		extentTestObj.addScreenCaptureFromPath(filePath);
	}
}
