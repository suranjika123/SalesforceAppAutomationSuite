package com.salesforce.pages.loginpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.base.BasePage;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver1) {
		super(driver1);
	}
	
	@FindBy(id="username") WebElement username;
	@FindBy(id="password") WebElement password;
	@FindBy(id ="Login") WebElement accLogin;
	@FindBy(id ="error") WebElement error;
	@FindBy(id ="forgot_password_link") WebElement forgotPassword;
	@FindBy(id="un") WebElement userId;
	@FindBy(id="continue") WebElement contButton;
	@FindBy(xpath="//div[@class='message']/p[1]") WebElement message;
	@FindBy(id="rememberUn") WebElement rememberMe;
	@FindBy(id="idcard-identity") WebElement rememberMeUserId;
	
	public void enterIntoUsername(String userName) {
		report.logTestInfo("Enter User Name");
		enterText(username, userName);
	}

	public void enterIntoPassword(String pwd) {
		report.logTestInfo("Enter Password");
		enterText(password, pwd);
	}

	public void clearPasswordField() {
		report.logTestInfo("Clear Password Value");
		clearElement(password);
	}

	public void clickLoginButton() {
		report.logTestInfo("Click on Account Login Button");
		clickElement(accLogin);
	}

	public void clickforgotPasswordLink() {
		report.logTestInfo("Click on Forgot Password Link");
		clickElement(forgotPassword);
	}

	public String getErrorMessage() {
		return getText(error);
	}

	public void enterUserId(String userName) {
		report.logTestInfo("Enter User Name for Forgot Password Link");
		enterText(userId, userName);
	}

	public void clickContinueButton() {
		report.logTestInfo("Click on Continue Button");
		clickElement(contButton);
	}

	public String getforgotPasswordMessage() {
		return getText(message);
	}

	public void selectRememberMe() {
		report.logTestInfo("Select Remember Me Checkbox");
		clickElement(rememberMe);
	}

	public String getRememberMeUserId() {
		return getText(rememberMeUserId);
	}
	
	public String getPageURL() {
		return getURL();
	}

}
