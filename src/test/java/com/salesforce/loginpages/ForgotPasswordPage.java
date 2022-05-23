package com.salesforce.loginpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.salesforce.base.BasePage;

public class ForgotPasswordPage extends BasePage {
	public static final String FORGOT_PASSWORD_PAGE_TITLE = "Forgot Your Password | Salesforce"; 
	public static final String PASSWORD_RESET_MESSAGE = "We\u2019ve sent you an email with a link to finish resetting your password.";
	
	@FindBy(xpath="//*[@id='un']")public  WebElement userNameForForgotPassword;
	@FindBy(id="continue") public WebElement continueButton;
	@FindBy(xpath="//*[@id='forgotPassForm']/div[1]/input[3]")public  WebElement cancelButton;
	@FindBy(id="sandbox-login") public WebElement sandboxLoginLink; 
	@FindBy(id="video-link") public WebElement needHelpLogginginLink;	
	@FindBy(xpath="//div[@id='forgotPassForm']/div[1]/p[1]")public  WebElement passwordResetMessage;
	
	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterUserName(String userNameValue) {
		clearText(userNameForForgotPassword);
		enterText(userNameForForgotPassword,userNameValue);
	}
	
	public void clickOnContinueButton() {
		clickOnElement(continueButton, "Continue Button");
	}
	public void clickOnCancelButton() {
		clickOnElement(cancelButton, "Cancel Button");
	}
	public void clickOnSandboxLoginLink() {
		clickOnElement(sandboxLoginLink, "Sandbox Login Link");
	}
	public void clickOnNeedHelpLogginginLink() {
		clickOnElement(needHelpLogginginLink, "Need Help Logging in Link");
	}
}
