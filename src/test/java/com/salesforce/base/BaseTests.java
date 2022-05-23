package com.salesforce.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.salesforce.utilities.Constants;
import com.salesforce.utilities.GenerateReports;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTests{
	protected static WebDriver driver;
	protected static SoftAssert softassertObject = new SoftAssert();
	public static GenerateReports report;

	@BeforeTest
	public void setUpTestAllTest() {
		report = GenerateReports.getInstance();	
	}

	@BeforeMethod
	@Parameters("browser")
	public void setUpForAllMethods(String browser, Method method) {
		report.startExtentTestReport(method.getName());
		launchAndOpenBrowser(browser);
		BasePage bPage = new BasePage(driver);
		bPage.goToSFLoginPage();	
	}
	@AfterMethod
	public void tearDown() {
		closeAllDrivers();
	}
	public static void launchAndOpenBrowser(String browser) {

		switch(browser.toLowerCase()) {

		case "chrome":		
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox": 
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		default:
			System.out.println("Invalid browser");
			report.logTestInfo("Invalid Browser");
		}
		System.out.println("Launched browser"+browser);
//		report.logTestInfo(browser+"browser launched");
		driver.manage().window().maximize();	
	}
	
	//ScreenShot 
		public static void  captureScreenshot(String fileName) throws IOException {
			TakesScreenshot captureScreen = (TakesScreenshot)driver;
			File source = captureScreen.getScreenshotAs(OutputType.FILE);
			Date currentdatetime = new Date();
			File destination = new File(Constants.SALESFORCE_SCREENSHOTFOLDER_PATH+fileName+currentdatetime);
			FileUtils.copyFile(source, destination);
			report.attachScreenshot(destination.getPath());
		}
		
		public static void closeAllDrivers() {
			driver.quit();
		}
		
}
