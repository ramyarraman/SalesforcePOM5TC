package com.salesforce.utilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.salesforce.base.BaseTests;


public class Listeners implements ITestListener{
	GenerateReports listenersObj = GenerateReports.getInstance();
	@Override
	public void onTestStart(ITestResult result) {
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		listenersObj.logTestPassed();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		listenersObj.logTestFail(result.getThrowable());
		String methodName = result.getMethod().getMethodName();
		try {
			BaseTests.captureScreenshot("/listenerScreenShot/FailedCasesScreenshot/"+methodName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		listenersObj.logTestSkipped();
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("In onStart");
		listenersObj.startExtentReports();
	}

	@Override
	public void onFinish(ITestContext context) {
		listenersObj.endReport();
	}
	
}
