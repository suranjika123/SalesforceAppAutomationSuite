package com.salesforce.pages.homepage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.base.BasePage;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver1) {
		super(driver1);
	}

	@FindBy(id = "userNav") WebElement userNavigation;
	@FindBy(linkText = "Logout") WebElement logout;
	@FindBy(xpath = "//div[@id='userNav-menuItems']/a") List<WebElement> menuItems;

	public String getHomePageTitle() {
		return getPageTitle();
	}

	public void clickUserNavigation() {
		report.logTestInfo("Click On User Navigation Menu");
		clickElement(userNavigation);
	}

	public void clickLogut() {
		report.logTestInfo("Click on  Logout Button");
		clickElement(logout);
	}
	
	public List<String> getUserNavMenuList() {
		List<String> menuList = new ArrayList<String>();
		for (WebElement menuItem : menuItems) {
			menuList.add(menuItem.getText());
		}
		return menuList;
	}

}