package com.iod.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.iod.commons.CommonFunctions;
import com.iod.page_objects.AuditReport;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_AuditReport extends AuditReport {

	ExtentTest auditReport;

	public void generateAuditReport() {
		Screenshot.captureScreenshot(getDriver(), "AuditReport_");
		//Click Generate button.
		getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnGenerate']")).click();
		ExtentTest extract = auditReport.createNode("Generate Audit Report.","Validate if User can generate Audit Report");
		extract.log(Status.PASS, "Clicked on Generate button");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//Click Export to Excel button.
		CommonFunctions cf = new CommonFunctions();
		if(config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			cf.clickExportToExcelBtn();
		}
		extract.log(Status.PASS, "Clicked on Export to Excel button.");
		extract.log(Status.PASS, "Successfully downloaded the Extract.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Screenshot.captureScreenshot(getDriver(), "AuditReport_");
		//Click Cancel button.
		getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnCancel']")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Screenshot.captureScreenshot(getDriver(), "AuditReport_");

	}

	public void generateAuditReportFrom() {
		//Click Date Range check box.
		getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_chkDateRange']")).click();

		//Enter 'From Date'.
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		jse.executeScript("document.getElementById('x:2146485478.0:mkr:3').value='10/10/2020'");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//Generate report based on user provided from date.
		//generateAuditReport();

	}

	public void validate_auditReportPage() {
		auditReport = Reports.extent.createTest("Audit Report","Validate Test Scenarios for Audit Report page.");
		auditReportTab().click();
		generateAuditReport();
		//generateAuditReportFrom();

	}

}
