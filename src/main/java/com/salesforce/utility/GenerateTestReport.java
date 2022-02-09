package com.salesforce.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import static com.salesforce.utility.Constants.GENERATE_REPORT_PATH;

import java.io.IOException;

public class GenerateTestReport {

	ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	private static GenerateTestReport ob;

	private GenerateTestReport() {
		// TODO Auto-generated constructor stub
	}

	public static GenerateTestReport getInstance() {
		if (ob == null) {
			ob = new GenerateTestReport();
		}
		return ob;
	}

	static String testcaseName = null;

	public void startExtentReport() {

		htmlReporter = new ExtentHtmlReporter(GENERATE_REPORT_PATH);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Salesforce Application");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Suranjika");

		htmlReporter.config().setDocumentTitle("Test Execution Report");
		htmlReporter.config().setReportName("Salesforce Application regression test scripts");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}

	public void startSingleTestReport(String testName) {
		testcaseName = testName;
		logger = extent.createTest(testName);
	}

	public void logTestInfo(String message) {
		logger.log(Status.INFO, message);
	}

	public void logTestpassed() {
		logger.log(Status.PASS, MarkupHelper.createLabel(testcaseName + " is passTest", ExtentColor.GREEN));
	}

	public void logTestFailed() {
		logger.log(Status.FAIL, MarkupHelper.createLabel(testcaseName + " is not passTest", ExtentColor.RED));
	}
	
	public void attachScreenshot(String path) {
		logTestInfo(path);
		try {
			logger.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void endTestReport() {
		extent.flush();
	}

}
