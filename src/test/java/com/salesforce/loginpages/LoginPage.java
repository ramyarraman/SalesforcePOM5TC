package com.salesforce.loginpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.salesforce.base.BasePage;
import com.salesforce.utilities.CommonUtilities;
import com.salesforce.utilities.GenerateReports;

public class LoginPage extends BasePage{

	public static final String LOGIN_PAGE_TITLE = "Login | Salesforce"  ;
	
	public static final String NO_PASSWORD_ERROR_MESSAGE = "Please enter your password.";
	public static final String LOGIN_ERROR_MESSAGE = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
	
	@FindBy(xpath="//*[@id='username']") public  WebElement userName;
	@FindBy(id="password") public  WebElement password;
	@FindBy(id="Login")  public WebElement loginButton;
	@FindBy(id="rememberUn") public WebElement rememberMeCheckBox;
	@FindBy(xpath="//*[@id='error']")  public WebElement loginError;
	@FindBy(id="forgot_password_link") public WebElement forgotPasswordLink;
	
	GenerateReports report = GenerateReports.getInstance();
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterUserName(String userNameValue) {
		clearText(userName);
		enterText(userName,userNameValue);
	}
	
	public void enterPassword(String passwordValue) {
		clearText(password);
		enterText(password,passwordValue);
	}
	
	public void clickOnLoginButton() {
		loginButton.click();
		
	}
	public void selectRememberMeCheckBox(boolean checkBoxSelection) {
		//select check box if checkBoxSelection is true, un select if flase
		if(checkBoxSelection) {	
			if(!rememberMeCheckBox.isSelected())
				rememberMeCheckBox.click();
		}else {
			if(rememberMeCheckBox.isSelected())
				rememberMeCheckBox.click();			
		}
	}
	
	public void clickOnForgotYourPasswordLink() {
		clickOnElement(forgotPasswordLink, "forgotPasswordLink");
	}
	
	public String getTextValuePresentInUserName() {
		String enteredUserName = getAttributeValueOfElement(userName,"value");
		System.out.println(enteredUserName);
		return enteredUserName;
	}
	
	public String getTextValuePresentInPassword() {
		String enteredPassword = getAttributeValueOfElement(password,"value");
		System.out.println(enteredPassword);
		return enteredPassword;
	}
	
	public String getErrorText() {
		return getText(loginError);		
	}
	//SalesForce login
			public void loginSalesforceWithValidData(boolean checkBoxSelection){

				String userNameValue = CommonUtilities.getProperty("userName");		
				System.out.println(userNameValue);
				enterUserName(userNameValue);

				String passwordValue = CommonUtilities.getProperty("password");
				enterPassword(passwordValue);

				selectRememberMeCheckBox(checkBoxSelection);
				
				clickOnLoginButton();
				System.out.println("Login clicked");
				report.logTestInfo("Login clicked");
			}
	
}
