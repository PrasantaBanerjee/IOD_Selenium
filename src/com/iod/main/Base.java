package com.iod.main;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.iod.tests.Test_AdhocVisitFilter;
import com.iod.tests.Test_AuditReport;
import com.iod.tests.Test_CalendarManagement;
import com.iod.tests.Test_Contact;
import com.iod.tests.Test_ContactCategory;
import com.iod.tests.Test_DataExtraction;
import com.iod.tests.Test_Department;
import com.iod.tests.Test_Firm;
import com.iod.tests.Test_Fund;
import com.iod.tests.Test_History_IntermediaryScorecard;
import com.iod.tests.Test_History_VendorScorecard;
import com.iod.tests.Test_IntermediaryDetailReport;
import com.iod.tests.Test_IntermediarySummaryReport;
import com.iod.tests.Test_Platform;
import com.iod.tests.Test_QuarterMaintenance;
import com.iod.tests.Test_RiskElement;
import com.iod.tests.Test_RiskRatingLevel;
import com.iod.tests.Test_RiskTopic;
import com.iod.tests.Test_Scorecards_IntermediaryScorecard;
import com.iod.tests.Test_Scorecards_VendorScorecard;
import com.iod.tests.Test_TrackingOption;
import com.iod.tests.Test_UserAccess;
import com.iod.utils.ExcelFileReader;
import com.iod.utils.PropertyFileReader;
import com.iod.utils.Reports;

/*
 * This class controls the Test Execution for IOD Application based on the Test Region & Browser name in config sheet.
 */
public class Base extends Reports {

	public static PropertyFileReader config = new PropertyFileReader();
	public static WebDriver driver;
	public static String timestamp = new SimpleDateFormat("EEE MM-dd-yyyy hh-mm-ss a").format(new Date());
	public static String resultsPath = System.getProperty("user.dir") + "\\results\\Test Results_" + timestamp;
	public static String extractDownloadedPath = resultsPath + "\\Extracts";
	public static String screenshotPath = resultsPath + "\\Screenshots";
	public static String reportPath = resultsPath + "\\Test Execution Report\\Report_" + timestamp + ".html";

	/*
	 * This method will return the WebDriver object for re-usability by the child classes.  
	 */
	public static WebDriver getDriver() {
		if (driver != null) {
			return driver;
		} else {
			return driver;
		}
	}

	/*
	 * This method is used to initialize WebDriver based on browser.
	 * Once WebDriver has been initialized, the browser will then navigate to the application.
	 */
	public static void launchApplicationInBrowser() {
		String browser_name = config.getProperty("Browser");
		if (browser_name.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", config.getProperty("ChromeDriver") );

			//Setup download directory path to save all Excel extracts.
			Map<String,Object>chromePref = new HashMap<>();
			chromePref.put("download.default_directory", extractDownloadedPath);
			ChromeOptions opt = new ChromeOptions();
			opt.setExperimentalOption("prefs", chromePref);

			driver = new ChromeDriver(opt);
		} else if (browser_name.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", config.getProperty("IEDriver"));
			driver = new InternetExplorerDriver();
		}

		driver.manage().window().maximize();

		String test_region = config.getProperty("Region");
		if (test_region.equalsIgnoreCase("DEV")) {
			driver.get(config.getProperty("DEV_URL"));
		} else if (test_region.equalsIgnoreCase("MOD")) {
			driver.get(config.getProperty("MOD_URL"));
		}
	}

	/*
	 * This method will close the browser session once execution has been completed.
	 */
	public static void closeBrowserSession() {
		if (driver != null) {
			driver.quit();
		}
		System.out.println("Closed the browser session.");
	}

	/*
	 * This method is responsible to setup the Report configurations & starting WebDriver instance.
	 */
	public static void setup() {
		System.out.println("Setting up the Report configurations.");
		Reports.init_Report();
		System.out.println("Launching the application as per below configurations:");
		System.out.println("\tRegion:  " + config.getProperty("Region"));
		System.out.println("\tBrowser: " + config.getProperty("Browser"));
		launchApplicationInBrowser();
	}

	/*
	 * This method is responsible to end WebDriver instance & generate Test Reports.
	 * Once reports have been generated, the User will be navigated to the Results folder.
	 */
	public static void teardown() {
		closeBrowserSession();
		Reports.flushReport();
		System.out.println("Generating the Test Results.");
		try {
			Desktop.getDesktop().open(new File(".\\results"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This method will control the test case execution based on 'Run_Flag' value.
	 */
	public static void executionFlow() {		
		Test_Department department = new Test_Department();
		if (ExcelFileReader.readData("Modules", 1, 2).equalsIgnoreCase("Yes")) {
			department.validate_DepartmentPage();
		}

		Test_Firm firm = new Test_Firm();
		if (ExcelFileReader.readData("Modules", 2, 2).equalsIgnoreCase("Yes")) {		
			firm.validateFirmPage();
		}

		Test_Platform platform = new Test_Platform();
		if (ExcelFileReader.readData("Modules", 3, 2).equalsIgnoreCase("Yes")) {
			platform.validatePlatformPage();
		}

		Test_Fund fund = new Test_Fund();
		if (ExcelFileReader.readData("Modules", 4, 2).equalsIgnoreCase("Yes")) {
			fund.validateFundPage();
		}

		Test_UserAccess userAccess = new Test_UserAccess();
		if (ExcelFileReader.readData("Modules", 5, 2).equalsIgnoreCase("Yes")) {
			userAccess.validateUserAccessPage();
		}

		Test_RiskTopic riskTopic = new Test_RiskTopic();
		if (ExcelFileReader.readData("Modules", 6, 2).equalsIgnoreCase("Yes")) {
			riskTopic.validateRiskTopicPage();
		}

		Test_TrackingOption trackingOption = new Test_TrackingOption();
		if (ExcelFileReader.readData("Modules", 7, 2).equalsIgnoreCase("Yes")) {
			trackingOption.validateTrackingOptionPage();
		}

		Test_RiskElement riskElement = new Test_RiskElement();
		if (ExcelFileReader.readData("Modules", 8, 2).equalsIgnoreCase("Yes")) {
			riskElement.validateRiskElementPage();
		}

		Test_QuarterMaintenance quarterMaintenance = new Test_QuarterMaintenance();
		if (ExcelFileReader.readData("Modules", 9, 2).equalsIgnoreCase("Yes")) {
			quarterMaintenance.validate_QuarterMaintenancePage();
		}

		Test_AdhocVisitFilter adhocVisitFilter = new Test_AdhocVisitFilter();
		if (ExcelFileReader.readData("Modules", 10, 2).equalsIgnoreCase("Yes")) {
			adhocVisitFilter.validate_adhocVisitFilterPage();
		}

		Test_ContactCategory contactCategory = new Test_ContactCategory();
		if (ExcelFileReader.readData("Modules", 11, 2).equalsIgnoreCase("Yes")) {
			contactCategory.validate_ContactCategoryPage();
		}

		Test_RiskRatingLevel riskRatingLevel = new Test_RiskRatingLevel();
		if (ExcelFileReader.readData("Modules", 12, 2).equalsIgnoreCase("Yes")) {
			riskRatingLevel.validate_RiskRatingLevelPage();
		}

		Test_CalendarManagement calendarMgmt = new Test_CalendarManagement();
		if(config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			if (ExcelFileReader.readData("Modules", 13, 2).equalsIgnoreCase("Yes")) {
				calendarMgmt.validate_CalendarMgmtPage();
			}
		}

		Test_Contact contact = new Test_Contact();
		if (ExcelFileReader.readData("Modules", 14, 2).equalsIgnoreCase("Yes")) {
			contact.validate_ContactPage();
		}

		Test_DataExtraction dataExtract = new Test_DataExtraction();
		if (ExcelFileReader.readData("Modules", 15, 2).equalsIgnoreCase("Yes")) {
			dataExtract.validate_dataExtractionPage();
		}

		Test_Scorecards_IntermediaryScorecard scoreInterim = new Test_Scorecards_IntermediaryScorecard();
		if (ExcelFileReader.readData("Modules", 16, 2).equalsIgnoreCase("Yes")) {
			scoreInterim.validate_intermediaryScorecardPage();
		}

		Test_Scorecards_VendorScorecard scoreVendor = new Test_Scorecards_VendorScorecard();
		if (ExcelFileReader.readData("Modules", 17, 2).equalsIgnoreCase("Yes")) {
			scoreVendor.validate_vendorScorecardPage();
		}

		Test_History_IntermediaryScorecard histInterim = new Test_History_IntermediaryScorecard();
		if (ExcelFileReader.readData("Modules", 18, 2).equalsIgnoreCase("Yes")) {
			histInterim.validate_intermediaryScorecardPage();
		}		

		Test_History_VendorScorecard histVendor = new Test_History_VendorScorecard();
		if (ExcelFileReader.readData("Modules", 19, 2).equalsIgnoreCase("Yes")) {
			histVendor.validate_vendorScorecardPage();
		}

		Test_AuditReport auditReport = new Test_AuditReport();
		if (ExcelFileReader.readData("Modules", 20, 2).equalsIgnoreCase("Yes")) {
			auditReport.validate_auditReportPage();
		}	

		Test_IntermediarySummaryReport summaryReport = new Test_IntermediarySummaryReport();
		if (ExcelFileReader.readData("Modules", 21, 2).equalsIgnoreCase("Yes")) {
			summaryReport.validate_intermediarySummaryReport();
		}

		Test_IntermediaryDetailReport detailReport = new Test_IntermediaryDetailReport();
		if (ExcelFileReader.readData("Modules", 22, 2).equalsIgnoreCase("Yes")) {
			detailReport.validate_intermediaryDetailReport();
		}

		System.out.println("Test Execution completed.");
	}

	public static void main(String[] args) {
		setup();
		executionFlow();
		teardown();

	}
}

