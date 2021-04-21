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
import com.iod.page_objects.Tracking_Option;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_TrackingOption extends Tracking_Option{
	
	CommonFunctions cf = new CommonFunctions();
	CommonPageObjects cpo = new CommonPageObjects();
	ExtentTest trackingOpt;
	
	public void clickTrackingOptionTab() {
		trackingOptionTab().click();
		Screenshot.captureScreenshot(getDriver(), "TrackingOption_");
	}
	
	public void clickAddBtn() {
		cpo.addBtn().click();
	}
	
	public void validateIfListContainsOnly(String expectedStatus) {
		List<WebElement> listOfItems = cpo.statusInTrackingOptList();
		for (WebElement eachItem : listOfItems) {
			JavascriptExecutor jse = (JavascriptExecutor) getDriver();
			jse.executeScript("arguments[0].scrollIntoView();", eachItem);
			String actualStatus = eachItem.getText();
			if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
				System.out.println("Mismatch found in the Tracking Option List.");
				System.out.println("Expected: " + expectedStatus + ". Actual: " + actualStatus);
			}
		}
		System.out.println("Status for all the Tracking Options was validated successfully.");
	}
	
	public void validateTrackingOptionList() {
		ExtentTest activeStatus = trackingOpt.createNode("Active Status Tracking Option.","Validate if Tracking Options displayed have 'Active' status.");
		try {
			cf.changeDropdownStatusTo("Active");
			// This triggers an AJAX call, hence the Web Elements become Stale.
			validateIfListContainsOnly("Active"); // Throws Exception. Repeat the same line inside catch block.
		} catch (StaleElementReferenceException e) {
			Screenshot.captureScreenshot(getDriver(), "TrackingOption_");
			validateIfListContainsOnly("Active");
			activeStatus.log(Status.PASS, "Successfully validated status for all the 'Active' Tracking Options.");
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		ExtentTest inactiveStatus = trackingOpt.createNode("Inactive Status Tracking Option.","Validate if Tracking Options displayed have 'Inactive' status.");
		try {
			cf.changeDropdownStatusTo("Inactive");
			// This triggers an AJAX call, hence the Web Elements become Stale.
			// It will throw Exception. Repeat the same line inside catch block.
		} catch (StaleElementReferenceException e) {
			cf.changeDropdownStatusTo("Inactive");
			Screenshot.captureScreenshot(getDriver(), "TrackingOption_");
			validateIfListContainsOnly("Inactive");
		}
		inactiveStatus.log(Status.PASS, "Successfully validated status for all the 'Inactive' Tracking Options.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	public void downloadTrackingOptionExtract() {
		ExtentTest ExportToExcel = trackingOpt.createNode("Export To Excel.","Validate if User can successfully export Tracking Option List.");
		ExportToExcel.log(Status.PASS, "Clicked on 'Export to Excel' Button.");

		cf.clickExportToExcelBtn();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "TrackingOption_");
		ExportToExcel.log(Status.PASS, "Succesfully downloaded the extract & placed in path: " + Base.extractDownloadedPath);
		
	}
	
	public void addTrackingOptionAndValidate() {
		ExtentTest addTrackingOption = trackingOpt.createNode("Add Tracking Option.","Validate if User can successfully add new Tracking Option.");
		addTrackingOption.log(Status.PASS, "Clicked on 'Add' button");
		clickAddBtn();
		Screenshot.captureScreenshot(getDriver(), "TrackingOption_");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		//Get random 3 digit number & append the same with firmName.
		int appendedID = cf.getRandomNumberUsingNextInt(100, 400)+cf.getRandomNumberUsingNextInt(1, 10);
		String optionID = AppendedValue.appendedAtFirst+appendedID;
		getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_txtTrackingOptionId']")).sendKeys(optionID);
		addTrackingOption.log(Status.PASS, "Entered value for Option ID: "+optionID);

		int appendedName = cf.getRandomNumberUsingNextInt(100, 400)+cf.getRandomNumberUsingNextInt(1, 10);
		String optionName = AppendedValue.appendedAtFirst+appendedName;
		getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_txtTrackingOptionName']")).sendKeys(optionName);
		addTrackingOption.log(Status.PASS, "Entered value for Option Name: "+optionName);
		
		Select opt = new Select(getDriver().findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolderMain_ddlTrackingOptionType']")));
		String selectedOption = "NM";
		opt.selectByValue(selectedOption);
		addTrackingOption.log(Status.PASS, "Selected value for Option Type: "+selectedOption);

		Screenshot.captureScreenshot(getDriver(), "TrackingOption_");
		
		// Click Save Button.
		cpo.saveBtn().click();
		addTrackingOption.log(Status.PASS, "Clicked on 'Save' button");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "TrackingOption_");
		
		// Click Close Button.
		cpo.closeBtn().click();
		addTrackingOption.log(Status.PASS, "Clicked on 'Close' button");

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
		WebElement newTrackingOptAdded = getDriver().findElement(By.xpath("//td[text()='"+optionName+"']"));
		if(newTrackingOptAdded.isEnabled()) {
			System.out.println("Successfully added new Tracking Option: "+optionName);
			addTrackingOption.log(Status.PASS, "Successfully added new Tracking Option: "+optionName);
			cf.highlightElement(newTrackingOptAdded);
		} else {
			System.out.println("Failed to add new Tracking Option: "+optionName);
			addTrackingOption.log(Status.PASS, "Failed to add new Tracking Option: "+optionName);
		}
		
	}
	
	public void validateTrackingOptionPage() {
		trackingOpt = Reports.extent.createTest("Tracking Option","Validate Test Scenarios for Tracking Option page.");
		clickTrackingOptionTab();
		validateTrackingOptionList();
		if(config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			downloadTrackingOptionExtract();
		}
		addTrackingOptionAndValidate();
		
	}

}
