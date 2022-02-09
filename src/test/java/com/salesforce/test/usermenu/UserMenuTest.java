package com.salesforce.test.usermenu;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.salesforce.base.BaseTest;
import com.salesforce.utility.CommonUtilities;

public class UserMenuTest extends BaseTest {
	
	@BeforeMethod
	public void launchHomePage() {
		String username = CommonUtilities.getApplicationProperty("username");
		loginPage.enterIntoUsername(username);
		String password = CommonUtilities.getApplicationProperty("password");
		loginPage.enterIntoPassword(password);
		loginPage.clickLoginButton();
	}

	@Test
	public void userNavigationTest() {
		homePage.clickUserNavigation();
		List<String> optionList = new ArrayList<String>();
		optionList.add("My Profile");
		optionList.add("My Settings");
		optionList.add("Developer Console");
		optionList.add("Switch to Lightning Experience");
		optionList.add("Logout");
		
		List<String> menuList = homePage.getUserNavMenuList();
		Assert.assertEquals(menuList, optionList);
	}
	
	@Test
	public void clickLogoutMenuTest() {
		homePage.clickUserNavigation();
		homePage.clickLogut();
		String currentURL = loginPage.getPageURL();
		Assert.assertEquals(currentURL, "https://whitebox2-dev-ed.my.salesforce.com/secur/logout.jsp");
	}
}
