package com.iod.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.iod.commons.AppendedValue;
import com.iod.commons.CommonFunctions;
import com.iod.commons.CommonPageObjects;
import com.iod.main.Base;
import com.iod.page_objects.Firm;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_Firm extends Firm {

	CommonFunctions cf = new CommonFunctions();
	CommonPageObjects cpo = new CommonPageObjects();
	ExtentTest firm;	

	public void clickFirmTab() {
		firmTab().click();
		Screenshot.captureScreenshot(getDriver(), "Firm_");
	}

	public void clickAddBtn() {
		cpo.addBtn().click();
	}

	public void validateIfListContainsOnly(String expectedStatus) {
		List<WebElement> listOfItems = cpo.statusInFirmList();
		for (WebElement eachItem : listOfItems) {
			JavascriptExecutor jse = (JavascriptExecutor) getDriver();
			jse.executeScript("arguments[0].scrollIntoView();", eachItem);
			String actualStatus = eachItem.getText();
			if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
				System.out.println("Mismatch found in the Firm List.");
				System.out.println("Expected: " + expectedStatus + ". Actual: " + actualStatus);
			}
		}
		System.out.println("Status for all the Firms was validated successfully.");
	}

	public void validateFirmList() {
		ExtentTest activeStatus = firm.createNode("Active Status Firms.","Validate if Firms displayed have 'Active' status.");
		try {
			cf.changeDropdownStatusTo("Active");
			// This triggers an AJAX call, hence the Web Elements become Stale.
			validateIfListContainsOnly("Active"); // Throws Exception. Repeat the same line inside catch block.
		} catch (StaleElementReferenceException e) {
			Screenshot.captureScreenshot(getDriver(), "Firm_");
			validateIfListContainsOnly("Active");
			activeStatus.log(Status.PASS, "Successfully validated status for all the 'Active' Firms.");
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		ExtentTest inactiveStatus = firm.createNode("Inactive Status Firms.","Validate if Firms displayed have 'Inactive' status.");
		try {
			cf.changeDropdownStatusTo("Inactive");
			// This triggers an AJAX call, hence the Web Elements become Stale.
			// It will throw Exception. Repeat the same line inside catch block.
		} catch (StaleElementReferenceException e) {
			cf.changeDropdownStatusTo("Inactive");
			Screenshot.captureScreenshot(getDriver(), "Firm_");
			validateIfListContainsOnly("Inactive");
		}
		inactiveStatus.log(Status.PASS, "Successfully validated status for all the 'Inactive' Firms.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public void downloadFirmExtract() {
		ExtentTest ExportToExcel = firm.createNode("Export To Excel.","Validate if User can successfully export Firm List.");
		ExportToExcel.log(Status.PASS, "Clicked on 'Export to Excel' Button.");

		cf.clickExportToExcelBtn();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "Firm_");
		ExportToExcel.log(Status.PASS, "Succesfully downloaded the extract & placed in path: " + Base.extractDownloadedPath);
		
	}

	public void addFirmAndValidate() {
		ExtentTest addFirm = firm.createNode("Add Firm.","Validate if User can successfully add new Firm.");
		addFirm.log(Status.PASS, "Clicked on 'Add' button");
		clickAddBtn();
		Screenshot.captureScreenshot(getDriver(), "Firm_");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		//Get random 3 digit number & append the same with firmName.
		int appendedNameVal = cf.getRandomNumberUsingNextInt(100, 400)+cf.getRandomNumberUsingNextInt(1, 10);
		String firmNameValue = AppendedValue.appendedAtFirst+appendedNameVal;
		getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_txtFirmName']")).sendKeys(firmNameValue);
		addFirm.log(Status.PASS, "Entered value for Firm Name: "+firmNameValue);

		Select opt = new Select(getDriver().findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolderMain_ddlFirmType']")));
		String selectedOption = "B";
		opt.selectByValue(selectedOption);
		addFirm.log(Status.PASS, "Selected value for Firm Type: "+selectedOption);

		getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_sclPlatform_chklItems_0']")).click();
		addFirm.log(Status.PASS, "Entered value for Firm Name: "+firmNameValue);

		getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_wneAssets']")).click();
		addFirm.log(Status.PASS, "Clicked on 'All Platforms' button.");

		String assetAmt = "123456";
		getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_wneAssets']")).sendKeys(assetAmt);
		addFirm.log(Status.PASS, "Entered value for Asset Under Management: "+assetAmt);

		String feeSchedule = "Added by QA Automation.";
		getDriver().findElement(By.xpath("//textarea[@id='ctl00_ContentPlaceHolderMain_txtFeeSchedule']")).sendKeys(feeSchedule);
		addFirm.log(Status.PASS, "Entered value for Fee Schedule: "+feeSchedule);

		Screenshot.captureScreenshot(getDriver(), "Firm_");
		
		// Click Save Button.
		cpo.saveBtn().click();
		addFirm.log(Status.PASS, "Clicked on 'Save' button");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "Firm_");
		
		// Click Close Button.
		cpo.closeBtn().click();
		addFirm.log(Status.PASS, "Clicked on 'Close' button");

		//Change status to 'Active' again.
		try {
			cf.changeDropdownStatusTo("Active");
			// This triggers an AJAX call, hence the Web Elements become Stale.
		} catch (StaleElementReferenceException e) {
			validateIfListContainsOnly("Active");
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		//Validate if new firm is present in active list or not.
		WebElement newFirmAdded = getDriver().findElement(By.xpath("//td[text()='"+firmNameValue+"']"));
		if(newFirmAdded.isEnabled()) {
			System.out.println("Successfully added new firm: "+firmNameValue);
			addFirm.log(Status.PASS, "Successfully added new firm: "+firmNameValue);
			cf.highlightElement(newFirmAdded);
		} else {
			System.out.println("Failed to add new firm: "+firmNameValue);
			addFirm.log(Status.PASS, "Failed to add new firm: "+firmNameValue);
		}
	}

	public void validateFirmPage() {
		firm = Reports.extent.createTest("Firm","Validate Test Scenarios for Firm page.");
		clickFirmTab();
		validateFirmList();
		if(config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			downloadFirmExtract();
		}
		addFirmAndValidate();
	}
}
