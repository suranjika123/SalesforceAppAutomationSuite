package com.salesforce.test.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.salesforce.base.BaseTest;
import com.salesforce.utility.CommonUtilities;

public class LoginTest extends BaseTest {

	@Test
	public void emptyPasswordTest() {
		loginPage.enterIntoUsername("User@gmail.com");
		loginPage.clearPasswordField();
		loginPage.clickLoginButton();
		String errorMessage = loginPage.getErrorMessage();
		Assert.assertEquals(errorMessage, "Please enter your password.");
	}

	@Test
	public void invalidLoginTest() {
		loginPage.enterIntoUsername("123");
		loginPage.enterIntoPassword("22131");
		loginPage.clickLoginButton();
		String errorMessage = loginPage.getErrorMessage();
		Assert.assertEquals(errorMessage,"Please check your username and password. If you still can't log in, contact your Salesforce administrator.");
	}

	@Test
	public void forgotPasswordTest() {
		loginPage.clickforgotPasswordLink();
		loginPage.enterUserId("Userxyzasdd@gmail123434343.com");
		loginPage.clickContinueButton();
		String message = loginPage.getforgotPasswordMessage();
		Assert.assertEquals(message, "We’ve sent you an email with a link to finish resetting your password.");
	}

	@Test
	public void validLoginTest() {
		String username = CommonUtilities.getApplicationProperty("username");
		loginPage.enterIntoUsername(username);
		String password = CommonUtilities.getApplicationProperty("password");
		loginPage.enterIntoPassword(password);
		loginPage.clickLoginButton();
		String pageTitle = homePage.getHomePageTitle();
		Assert.assertEquals(pageTitle, "Home Page ~ Salesforce - Developer Edition");
	}

	@Test
	public void rememberMeLoginTest() {
		String username = CommonUtilities.getApplicationProperty("username");
		loginPage.enterIntoUsername(username);
		String password = CommonUtilities.getApplicationProperty("password");
		loginPage.enterIntoPassword(password);
		loginPage.selectRememberMe();
		loginPage.clickLoginButton();
		homePage.clickUserNavigation();
		homePage.clickLogut();
		String userId = loginPage.getRememberMeUserId();
		Assert.assertEquals(userId, CommonUtilities.getApplicationProperty("username"));
	}
}
