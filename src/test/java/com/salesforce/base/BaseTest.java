package com.salesforce.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import org.openqa.selenium.OutputType;
import com.salesforce.pages.homepage.HomePage;
import com.salesforce.pages.loginpage.LoginPage;
import com.salesforce.utility.Constants;
import com.salesforce.utility.GenerateTestReport;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.salesforce.utility.CommonUtilities;

public class BaseTest {

	protected static WebDriver driver;
	protected static GenerateTestReport report = GenerateTestReport.getInstance();
	protected static LoginPage loginPage;
	protected static HomePage homePage;

	@BeforeTest
	public void setUp() {
		report.startExtentReport();
	}

	@Parameters({ "browser" })
	@BeforeMethod
	public void initialSetUp(@Optional("chrome") String browserName, Method method) {
		report.startSingleTestReport(method.getName());
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(CommonUtilities.getApplicationProperty("url"));
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			report.logTestpassed();
		} else if (result.getStatus() == ITestResult.FAILURE) {
			report.logTestFailed();
			String path=takescreenshot();
			report.attachScreenshot(path);
		}

		driver.quit();
	}

	@AfterTest
	public void endUp() {
		report.endTestReport();
	}

	public static String takescreenshot() {
		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		// Call getScreenshotAs method to create image file
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		String filePath = Constants.SCREENSHOT_PATH + srcFile.getName();
		File DestFile = new File(filePath);
		// Copy file at destination
		try {
			FileUtils.copyFile(srcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DestFile.getAbsolutePath();
	}

}
