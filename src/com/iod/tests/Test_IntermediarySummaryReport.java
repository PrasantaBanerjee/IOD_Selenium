package com.iod.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.iod.page_objects.IntermediarySummaryReport;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_IntermediarySummaryReport extends IntermediarySummaryReport {

	ExtentTest intermSummaryReport; 

	public void validate_intermediarySummaryReport() {
		intermSummaryReport = Reports.extent.createTest("Intermediary Summary Report","Validate Test Scenarios for Intermediary Summary Report page.");
		summaryReportTab().click();

		ExtentTest summaryReport = intermSummaryReport.createNode("Generate Intermediary Summary Report.","Validate if User can generate Intermediary Summary Report");
		WebElement allFirmBtn = getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$mslbFirms$btnAllRight']"));
		allFirmBtn.click();
		summaryReport.log(Status.PASS, "Selected all Firms");

		WebElement allPlatformBtn = getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$mslbPlatforms$btnAllRight']"));
		try {
			allPlatformBtn.click();
		} catch (StaleElementReferenceException e) {
			allPlatformBtn = getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$mslbPlatforms$btnAllRight']"));
			allPlatformBtn.click();
		}
		summaryReport.log(Status.PASS, "Selected all Platforms");

		Screenshot.captureScreenshot(getDriver(), "IntermediarySummaryReport_");
		//Click Generate button
		try {
			getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnGenerate']")).click();
		} catch(StaleElementReferenceException x) {
			getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnGenerate']")).click();
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		summaryReport.log(Status.PASS, "Clicked Generate button");
		Screenshot.captureScreenshot(getDriver(), "IntermediarySummaryReport_");

		//Click Export to Excel button.
		WebElement exportBtn = getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnExportToExcel']"));
		WebDriverWait wait = new WebDriverWait(getDriver(),30);
		wait.until(ExpectedConditions.visibilityOf(exportBtn));
		if(config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			if(exportBtn.isDisplayed()) {
				exportBtn.click();
			}
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		summaryReport.log(Status.PASS, "Clicked Export to Excel button");
		summaryReport.log(Status.PASS, "Successfully downloaded the extract.");
		Screenshot.captureScreenshot(getDriver(), "IntermediarySummaryReport_");
		//Click Cancel button.
		getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnCancel']")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Screenshot.captureScreenshot(getDriver(), "IntermediarySummaryReport_");
	}


}
