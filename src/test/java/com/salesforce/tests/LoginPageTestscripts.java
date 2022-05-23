package com.salesforce.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Parameters;


import com.salesforce.base.BaseTests;
import com.salesforce.homepages.HomePage;
import com.salesforce.loginpages.ForgotPasswordPage;
import com.salesforce.loginpages.LoginPage;
import com.salesforce.utilities.CommonUtilities;

public class LoginPageTestscripts extends BaseTests{
	
	@Test
	public void LoginErrorMessage1() {
		System.out.println("In TC1");
		LoginPage login = new LoginPage(driver);
		login.enterUserName("User@gmail.com");
		String actualValue = login.getTextValuePresentInUserName();
		System.out.println("actualUsername "+actualValue);
		Assert.assertTrue(actualValue.equalsIgnoreCase("User@gmail.com"));

		login.enterPassword("");
		actualValue =login.getTextValuePresentInPassword();
		System.out.println("actualPassword "+actualValue);
		Assert.assertTrue(actualValue.equalsIgnoreCase(""));

		login.clickOnLoginButton();	
		login.waitForPageToLoad(5);
		String actualError = login.getText(login.loginError);
		Assert.assertEquals(actualError, LoginPage.NO_PASSWORD_ERROR_MESSAGE);
		
	}
	
	@Test
	@Parameters("TC_2_LoginToSalesForce2_expectedPageTitle")
	public void LoginToSalesForce2(String TC_2_LoginToSalesForce2_expectedPageTitle) {
		LoginPage login = new LoginPage(driver);
		login.loginSalesforceWithValidData(false);
		String actualpageTitle = login.getPageTitle();
		Assert.assertEquals(actualpageTitle, TC_2_LoginToSalesForce2_expectedPageTitle);		
	}
	
	@Test
	public void CheckRemeberMe3() throws InterruptedException {
		LoginPage login = new LoginPage(driver);
		login.loginSalesforceWithValidData(true);
		Thread.sleep(3000);
		String actualpageTitle = login.getPageTitle();
		Assert.assertEquals(actualpageTitle, HomePage.HOME_PAGE_TITLE);
		
		login.logoutSalesForce();
		Thread.sleep(3000);
		String actualPageTitle = login.getPageTitle();
		Assert.assertEquals(actualPageTitle, LoginPage.LOGIN_PAGE_TITLE);
				
		softassertObject.assertTrue(login.checkifSelected(login.rememberMeCheckBox));
		
		String actualUserName = login.getTextValuePresentInUserName();
		String expectedName = CommonUtilities.getProperty("userName");
		Assert.assertEquals(actualUserName, expectedName);	
		
	}
	
	@Test
	public void ForgotPassword4A() {
		LoginPage login = new LoginPage(driver);
		ForgotPasswordPage forgotPassword  = new ForgotPasswordPage(driver);
		login.clickOnForgotYourPasswordLink();
		String actualPageTitle = login.getPageTitle();
		String expectedTitle = ForgotPasswordPage.FORGOT_PASSWORD_PAGE_TITLE;
		Assert.assertEquals(actualPageTitle, expectedTitle);
		
		forgotPassword.enterUserName(CommonUtilities.getProperty("userName"));
		forgotPassword.clickOnContinueButton();
		String actualMessage = forgotPassword.getText(forgotPassword.passwordResetMessage);
		String expectedMessage = ForgotPasswordPage.PASSWORD_RESET_MESSAGE;
		Assert.assertEquals(actualMessage, expectedMessage);		
	}
	
	@Test
	@Parameters({"TC_4_ForgotPassword4B_userName","TC_4_ForgotPassword4B_password"})
	public void ForgotPassword4B(String TC_4_ForgotPassword4B_userName, String TC_4_ForgotPassword4B_password) { 
		LoginPage login = new LoginPage(driver);
		login.enterUserName(TC_4_ForgotPassword4B_userName);
		String actualValue = login.getTextValuePresentInUserName();
		Assert.assertEquals(actualValue, TC_4_ForgotPassword4B_userName);
		
		login.enterPassword(TC_4_ForgotPassword4B_password);
		actualValue = login.getTextValuePresentInPassword();
		Assert.assertEquals(actualValue, TC_4_ForgotPassword4B_password);
		
		login.clickOnLoginButton();
		login.waitForPageToLoad(5);
		String actualErrorDisplayed = login.getErrorText();
		String expectedError = LoginPage.LOGIN_ERROR_MESSAGE;
		Assert.assertEquals(actualErrorDisplayed, expectedError);		
	}
	
}
