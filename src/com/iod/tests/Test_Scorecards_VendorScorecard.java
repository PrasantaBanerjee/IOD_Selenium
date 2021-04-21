package com.iod.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.iod.commons.CommonFunctions;
import com.iod.main.Base;
import com.iod.page_objects.Scorecards_VendorScorecard;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_Scorecards_VendorScorecard extends Scorecards_VendorScorecard {
	
ExtentTest vendorScorecard;
	
	public void validateReportData(WebElement firmToBeValidated) {		
		//Data achieved before opening report.
		String firmNameBeforeClick = firmToBeValidated.getText();
		ExtentTest validateReport = vendorScorecard.createNode("Validate Report Data for "+firmNameBeforeClick,"Validate the Report Name & Quarter value.");
		validateReport.log(Status.PASS, "Captured Firm Name from Firm List: " + firmNameBeforeClick);
		String quarterDateBeforeClick = getDriver().findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolderMain$ddlYearQuarter']//option")).getText();
		validateReport.log(Status.PASS, "Captured Quarter Value from Firm List: " + quarterDateBeforeClick);

		//Double click on the firm.
		Actions act = new Actions(getDriver());
		act.doubleClick(firmToBeValidated).perform();
		validateReport.log(Status.PASS, "Double clicked on the firm.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "Scorecard_");
		//Data achieved after opening report.
		String firmNameAfterClick = getDriver().findElement(By.xpath("//span[@id='ctl00_ContentPlaceHolderMain_lblFirmName']")).getText();
		validateReport.log(Status.PASS, "Captured Quarter Value from Firm List: " + firmNameAfterClick);
		String quarterDateAfterClick = getDriver().findElement(By.xpath("//span[@id='ctl00_ContentPlaceHolderMain_lblYearQuarter']")).getText();
		validateReport.log(Status.PASS, "Captured Quarter Value from Firm List: " + quarterDateAfterClick);

		//Compare
		if(firmNameBeforeClick.equals(firmNameAfterClick) && quarterDateBeforeClick.equals(quarterDateAfterClick)) {
			validateReport.log(Status.PASS, "Firm Name & Quarter value successfully matched.");
		} else {
			validateReport.log(Status.FAIL, "Firm Name & Quarter value failed to match.");
		}

		//Scroll down to back to firm button.
		WebElement backToFirmBtn = getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnBackToFirm']"));
		JavascriptExecutor jse = (JavascriptExecutor) getDriver();
		jse.executeScript("arguments[0].scrollIntoView();", backToFirmBtn);

		Screenshot.captureScreenshot(getDriver(), "Scorecard_");
		//Click to go back to the page.
		backToFirmBtn.click();
		Screenshot.captureScreenshot(getDriver(), "Scorecard_");
	}

	public void validateReportForFirm1() {
		List<WebElement>firmNames = getDriver().findElements(By.xpath("//th[@class='ig_Header igg_RowSelector rowSelectorWidth ']/parent::tr/td[2]"));
		
		WebElement firm1 = firmNames.get(0);
		validateReportData(firm1);
	}
	
	public void validateReportForFirm2() {
		List<WebElement>firmNames = getDriver().findElements(By.xpath("//th[@class='ig_Header igg_RowSelector rowSelectorWidth ']/parent::tr/td[2]"));
		
		WebElement firm2 = firmNames.get(1);
		validateReportData(firm2);
	}
	
	public void downloadScorecardExtract() {
		ExtentTest ExportToExcel = vendorScorecard.createNode("Export To Excel.","Validate if User can successfully export Scorecard List.");
		ExportToExcel.log(Status.PASS, "Clicked on 'Export to Excel' Button.");

		CommonFunctions cf = new CommonFunctions();
		cf.clickExportToExcelBtn();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "Scorecard_");
		ExportToExcel.log(Status.PASS, "Succesfully downloaded the extract & placed in path: " + Base.extractDownloadedPath);
		
	}

	public void validate_vendorScorecardPage() {
		vendorScorecard = Reports.extent.createTest("Scorecard - Vendor Scorecard","Validate Test Scenarios for Vendor Scorecard page.");
		vendorScorecardTab().click();
		Screenshot.captureScreenshot(getDriver(), "Scorecard_");
		validateReportForFirm1();
		validateReportForFirm2();
		if(config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			downloadScorecardExtract();
		}
	}

}
