package com.salesforce.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.utility.GenerateTestReport;

public class BasePage {
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected static GenerateTestReport report = GenerateTestReport.getInstance();

	public BasePage(WebDriver driver1) {
		driver = driver1;
		PageFactory.initElements(driver1, this);
	}

	public static void enterText(WebElement element, String text) {
		waitUntilVisible(element);
		if (element.isDisplayed()) {
			clearElement(element);
			element.sendKeys(text);
		} else {
			System.out.println("fail: element not found to enter text");
		}
	}

	public static void clickElement(WebElement element) {
		if (element.isDisplayed()) {
			element.click();
		} else {
			System.out.println("fail: element not displayed to click element");
		}
	}

	public static void clearElement(WebElement element) {
		if (element.isDisplayed()) {
			element.clear();
		} else {
			System.out.println("fail: element not displayed to clear element");
		}
	}

	public static void waitUntilVisible(WebElement element) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static String getPageTitle() {
		return driver.getTitle();
	}

	public static String getText(WebElement element) {
		waitUntilVisible(element);
		return element.getText();
	}
	
	public static String getURL() {
		return driver.getCurrentUrl();
	}
}
