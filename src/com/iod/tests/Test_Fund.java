package com.iod.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.iod.commons.AppendedValue;
import com.iod.commons.CommonFunctions;
import com.iod.commons.CommonPageObjects;
import com.iod.main.Base;
import com.iod.page_objects.Fund;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_Fund extends Fund {

	CommonFunctions cf = new CommonFunctions();
	CommonPageObjects cpo = new CommonPageObjects();
	ExtentTest fund;

	public void clickFundTab() {
		fundTab().click();
		Screenshot.captureScreenshot(getDriver(), "Fund_");
	}

	public void validateIfListContainsOnly(String expectedStatus) {
		List<WebElement> listOfItems = cpo.statusInFundList();
		for (WebElement eachItem : listOfItems) {
			JavascriptExecutor jse = (JavascriptExecutor) getDriver();
			jse.executeScript("arguments[0].scrollIntoView();", eachItem);
			String actualStatus = eachItem.getText();
			if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
				System.out.println("Mismatch found in the Fund List.");
				System.out.println("Expected: " + expectedStatus + ". Actual: " + actualStatus);
			}
		}
		System.out.println("Status for all the Funds was validated successfully.");
	}

	public void validateFundList() {
		ExtentTest activeStatus = fund.createNode("Active Status Funds.","Validate if Funds displayed have 'Active' status.");
		try {
			cf.changeDropdownStatusTo("Active");
			// This triggers an AJAX call, hence the Web Elements become Stale.
			validateIfListContainsOnly("Active"); // Throws Exception. Repeat the same line inside catch block.
		} catch (StaleElementReferenceException e) {
			Screenshot.captureScreenshot(getDriver(), "Fund_");
			validateIfListContainsOnly("Active");
		}
		activeStatus.log(Status.PASS, "Successfully validated Funds with 'Active' status.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		ExtentTest inactiveStatus = fund.createNode("Inactive Status Firms.","Validate if Firms displayed have 'Inactive' status.");
		try {
			cf.changeDropdownStatusTo("Inactive");
			// This triggers an AJAX call, hence the Web Elements become Stale.
			// It will throw Exception. Repeat the same line inside catch block.
		} catch (StaleElementReferenceException e) {
			cf.changeDropdownStatusTo("Inactive");
			Screenshot.captureScreenshot(getDriver(), "Fund_");
			validateIfListContainsOnly("Inactive");
		}
		inactiveStatus.log(Status.PASS, "Successfully validated Funds with 'Inactive' status.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	public void downloadFundExtract() {
		ExtentTest ExportToExcel = fund.createNode("Export To Excel.","Validate if User can successfully export Fund List.");
		ExportToExcel.log(Status.PASS, "Clicked on 'Export to Excel' Button.");
		
		cf.clickExportToExcelBtn();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "Fund_");
		ExportToExcel.log(Status.PASS, "Succesfully downloaded the extract & placed in path: " + Base.extractDownloadedPath);
		
	}
	
	public void clickAddBtn() {
		cpo.addBtn().click();
	}
	
	public void addFundAndValidate() {
		ExtentTest addFund = fund.createNode("Add Fund.","Validate if User can successfully add new Fund.");
		addFund.log(Status.PASS, "Clicked on 'Add' button");
		clickAddBtn();
		Screenshot.captureScreenshot(getDriver(), "Fund_");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		//Get random 3 digit number & append the same with custID & fundName.
		int appendedIDVal = cf.getRandomNumberUsingNextInt(11, 40)+cf.getRandomNumberUsingNextInt(1, 10);
		String custIDValue = AppendedValue.appendedAtFirst+appendedIDVal;
		getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_txtCustodianId']")).sendKeys(custIDValue);
		addFund.log(Status.PASS, "Entered value for Custodian ID: "+custIDValue);
		
		int appendedNameVal = cf.getRandomNumberUsingNextInt(11, 40)+cf.getRandomNumberUsingNextInt(1, 10);
		String fundNameValue = AppendedValue.appendedAtFirst+appendedNameVal;
		getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_txtFundName']")).sendKeys(fundNameValue);
		addFund.log(Status.PASS, "Entered value for Fund Name: "+fundNameValue);
		
		//Enter fiscal year end value.
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		String fiscalVal="10/11";
		jse.executeScript("document.getElementById('ctl00_ContentPlaceHolderMain_wmeFiscalYearEnd').value='"+fiscalVal+"';");
		getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_wmeFiscalYearEnd']")).click();
		addFund.log(Status.PASS, "Entered value for Fiscal year End: "+fiscalVal);
		
		Screenshot.captureScreenshot(getDriver(), "Fund_");
		
		// Save Button.
		cpo.saveBtn().click();
		addFund.log(Status.PASS, "Clicked on 'Save' button");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "Fund_");
		
		//Close Button.
		cpo.closeBtn().click();
		addFund.log(Status.PASS, "Clicked on 'Close' button");
		
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

		//Validate if new fund is present in active list or not.
		WebElement newFundAdded = getDriver().findElement(By.xpath("//td[text()='"+fundNameValue+"']"));
		if(newFundAdded.isEnabled()) {
			System.out.println("Successfully added new fund: "+fundNameValue);
			addFund.log(Status.PASS, "Successfully added new fund: "+fundNameValue);
			cf.highlightElement(newFundAdded);
		} else {
			System.out.println("Failed to add new fund: "+fundNameValue);
			addFund.log(Status.FAIL, "Failed to add new fund: "+fundNameValue);
		}
		
	}
	
	public void validateFundPage() {
		fund = Reports.extent.createTest("Fund","Validate Test Scenarios for Fund page.");
		clickFundTab();
		validateFundList();
		if(config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			downloadFundExtract();
		}
		addFundAndValidate();
	}

}
