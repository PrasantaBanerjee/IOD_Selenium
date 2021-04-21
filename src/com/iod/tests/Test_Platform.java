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
import com.iod.page_objects.Platform;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_Platform extends Platform{

	CommonFunctions cf = new CommonFunctions();	
	CommonPageObjects cpo = new CommonPageObjects();
	ExtentTest platform;

	public void clickPlatformTab() {
		platformTab().click();
		Screenshot.captureScreenshot(getDriver(), "Platform_");
	}

	public void validateIfListContainsOnly(String expectedStatus) {
		List<WebElement> listOfItems = cpo.statusInPlatformList();
		for (WebElement eachItem : listOfItems) {
			JavascriptExecutor jse = (JavascriptExecutor) getDriver();
			jse.executeScript("arguments[0].scrollIntoView();", eachItem);
			String actualStatus = eachItem.getText();
			if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
				System.out.println("Mismatch found in the Platform List.");
				System.out.println("Expected: " + expectedStatus + ". Actual: " + actualStatus);
			}
		}
		System.out.println("Status for all the Platforms was validated successfully.");
	}

	public void validatePlatformList() {	
		ExtentTest activeStatus = platform.createNode("Active Status Platforms.","Validate if Platforms displayed have 'Active' status.");
		try {
			cf.changeDropdownStatusTo("Active");
			//This triggers an AJAX call. Hence the next line throws Exception. Repeat the same line inside catch block.
			validateIfListContainsOnly("Active");
		} catch (StaleElementReferenceException e) {
			Screenshot.captureScreenshot(getDriver(), "Platform_");
			validateIfListContainsOnly("Active");
		}
		activeStatus.log(Status.PASS, "Successfully validated status for all the 'Active' Platforms.");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		ExtentTest inactiveStatus = platform.createNode("Inactive Status Platforms.","Validate if Platforms displayed have 'Inactive' status.");
		try {
			cf.changeDropdownStatusTo("Inactive");
			//This triggers an AJAX call. Hence the next line throws Exception. Repeat the same line inside catch block.
		} catch (StaleElementReferenceException e) {
			cf.changeDropdownStatusTo("Inactive");
			Screenshot.captureScreenshot(getDriver(), "Platform_");
			validateIfListContainsOnly("Inactive");
		}
		inactiveStatus.log(Status.PASS, "Successfully validated status for all the 'Inactive' Platforms.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}		
	}

	public void downloadPlatformExtract() {
		ExtentTest ExportToExcel = platform.createNode("Export To Excel.","Validate if User can successfully export Platform List.");
		ExportToExcel.log(Status.PASS, "Clicked on 'Export to Excel' Button.");

		cf.clickExportToExcelBtn();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "Platform_");
		ExportToExcel.log(Status.PASS, "Succesfully downloaded the extract & placed in path: " + Base.extractDownloadedPath);
		
	}

	public void clickAddBtn() {
		cpo.addBtn().click();
	}

	public void addPlatformAndValidate() {
		ExtentTest addPlatform = platform.createNode("Add Platform.","Validate if User can successfully add new Platform.");
		addPlatform.log(Status.PASS, "Clicked on 'Add' button");
		clickAddBtn();
		Screenshot.captureScreenshot(getDriver(), "Platform_");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		//Get random 3 digit number & append the same with platformID & platformName.
		int appendedID = cf.getRandomNumberUsingNextInt(100, 400)+cf.getRandomNumberUsingNextInt(1, 10);
		String platformIDValue = AppendedValue.appendedAtFirst+appendedID;
		getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_txtPlatformID']")).sendKeys(platformIDValue);
		addPlatform.log(Status.PASS, "Entered value for Platform ID: "+platformIDValue);
		
		String platformNameValue = AppendedValue.appendedAtFirst+cf.getRandomNumberUsingNextInt(100, 400);
		getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_txtPlatformName']")).sendKeys(platformNameValue);
		addPlatform.log(Status.PASS, "Entered value for Platform Name: "+platformNameValue);
		
		Screenshot.captureScreenshot(getDriver(), "Platform_");
		
		// Save Button.
		cpo.saveBtn().click();
		addPlatform.log(Status.PASS, "Clicked on 'Save' button");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		Screenshot.captureScreenshot(getDriver(), "Platform_");

		//Close Button.
		cpo.closeBtn().click();
		addPlatform.log(Status.PASS, "Clicked on 'Close' button");

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

		//Validate if new platform is present in active list or not.
		WebElement newPlatformAdded = getDriver().findElement(By.xpath("//td[text()='"+platformNameValue+"']"));
		if(newPlatformAdded.isEnabled()) {
			System.out.println("Successfully added new platform: "+platformNameValue);
			addPlatform.log(Status.PASS, "Successfully added new platform: "+platformNameValue);
			cf.highlightElement(newPlatformAdded);
			
		} else {
			System.out.println("Failed to add new platform: "+platformNameValue);
			addPlatform.log(Status.PASS, "Failed to add new platform: "+platformNameValue);
		}

	}

	public void validatePlatformPage() {
		platform = Reports.extent.createTest("Platform","Validate Test Scenarios for Platform page.");
		clickPlatformTab();
		validatePlatformList();
		if(config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			downloadPlatformExtract();
		}
		addPlatformAndValidate();
	}

}
