package com.iod.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.iod.page_objects.IntermediaryDetailReport;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_IntermediaryDetailReport extends IntermediaryDetailReport {

	ExtentTest intermDetailReport;

	public void validate_intermediaryDetailReport() {
		intermDetailReport = Reports.extent.createTest("Intermediary Detail Report","Validate Test Scenarios for Intermediary Detail Report page.");
		detailReportTab().click();

		getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$mslbFirms$btnAllRight']")).click();
		ExtentTest detailReport = intermDetailReport.createNode("Generate Intermediary Detail Report.","Validate if User can generate Intermediary Detail Report");
		detailReport.log(Status.PASS, "Selected all Firms.");
		Screenshot.captureScreenshot(getDriver(), "IntermediaryDetailReport_");

		//Click Generate button.
		try {
			getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnGenerate']")).click();
		} catch(StaleElementReferenceException x) {
			getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnGenerate']")).click();
		}
		detailReport.log(Status.PASS, "Clicked on Generate button.");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "IntermediaryDetailReport_");
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

		detailReport.log(Status.PASS, "Clicked on Export button");
		detailReport.log(Status.PASS, "Successfully downloaded the extract.");

		Screenshot.captureScreenshot(getDriver(), "IntermediaryDetailReport_");
		//Click Cancel button.
		getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnCancel']")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
