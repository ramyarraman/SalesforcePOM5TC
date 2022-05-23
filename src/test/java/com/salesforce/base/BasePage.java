package com.salesforce.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.utilities.CommonUtilities;

public class BasePage {
	
	public static WebDriver driver;
	@FindBy(xpath="//*[@id='userNav-menuItems']/a[5]") public WebElement logout;
	@FindBy(id="userNavButton") public WebElement userMenu;
	
	
	
	public BasePage(WebDriver driver1) {
		driver=driver1;
		PageFactory.initElements(driver, this);
	}
	
	
	//Go to SalesForce login page
	public void goToSFLoginPage() {
		String url = CommonUtilities.getProperty("url");
		goToUrl(url);
	}
	public void goToUrl(String url) {
		driver.get(url);
		System.out.println("Fetched url "+url);
//		report.logTestInfo("Fetched url "+url);
	}
	
	//Log out of salesforce
		public void logoutSalesForce() {
			
			clickOnElement(userMenu,"userMenu");
			clickOnElement(logout, "logout");
		}
		//GetTitle of page 
		public String getPageTitle() {
			return driver.getTitle();
		}
		
		//Waits................................................
		public void waitForPageToLoad(int seconds) {
			driver.manage().timeouts().pageLoadTimeout(seconds,TimeUnit.SECONDS);
		}
		
		public void waitSecondsForElementToBeClickable(int seconds, WebElement webElement) {
			new WebDriverWait(driver,seconds).until(ExpectedConditions.elementToBeClickable(webElement));
		}

		public void waitSecondsForElementVisible(int seconds, WebElement webElement) {
			new WebDriverWait(driver,seconds).until(ExpectedConditions.visibilityOf(webElement));
		}
		
		public void webDriverWait(int seconds) {
			new WebDriverWait(driver,seconds);
		}
		//Enter text in webElement
		public void enterText(WebElement webElement,String text) {
			clearText(webElement);
			webElement.sendKeys(text);
			System.out.println("Text Entered"+text);
//			report.logTestInfo("Text Entered");
		}
		//click on Element
		public void clickOnElement(WebElement webElement,String elementName) {
			new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(webElement));
			webElement.click();
			System.out.println("clicked on "+elementName);
//			report.logTestInfo("clicked on "+elementName);
		}
		//clear the text 
		public void clearText(WebElement webElement) {
			webElement.clear();
		}
		//check if the element is selected
		public Boolean checkifSelected(WebElement webElement) {
			return webElement.isSelected();
		}
		//get the text from webElement
		public String getText(WebElement webElement) {
			System.out.println("Text from webElement: "+webElement.getText());
			return webElement.getText();
		}
		//get the attribute value of the webelement
		public String getAttributeValueOfElement(WebElement webElement,String attributeName) {
			//		System.out.println(attributeName+" of webElement: "+webElement.getAttribute(attributeName));
			return webElement.getAttribute(attributeName);
		}
		
		//Close driver..........................................
		public void closeDriver() {
			driver.close();
			System.out.println("Driver closed");

		}	
		public void closeAllDriver() {
			driver.quit();
			System.out.println("All Drivers closed");
//			report.logTestInfo("All Drivers closed");
		}
	
}
