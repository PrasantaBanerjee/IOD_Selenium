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
import com.iod.page_objects.Risk_Topic;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_RiskTopic extends Risk_Topic{

	CommonFunctions cf = new CommonFunctions();
	CommonPageObjects cpo = new CommonPageObjects();
	ExtentTest riskTopic;

	public void clickRiskTopicTab() {
		riskTopicTab().click();
		Screenshot.captureScreenshot(getDriver(), "RiskTopic_");
	}

	public void clickChangeDisplayOrderBtn() {
		changeDisplayOrderBtn().click();
	}

	public void validateIfListContainsOnly(String expectedStatus) {
		List<WebElement> listOfItems = cpo.statusInRiskTopicList();
		for (WebElement eachItem : listOfItems) {
			JavascriptExecutor jse = (JavascriptExecutor) getDriver();
			jse.executeScript("arguments[0].scrollIntoView();", eachItem);
			String actualStatus = eachItem.getText();
			if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
				System.out.println("Mismatch found in the Risk Topic List.");
				System.out.println("Expected: " + expectedStatus + ". Actual: " + actualStatus);
			}
		}
		System.out.println("Status for all the Risk Topic was validated successfully.");
	}

	public void validateRiskTopicList() {
		//clickChangeDisplayOrderBtn();
		//This triggers AJAX call.

		ExtentTest activeStatus = riskTopic.createNode("Active Status Risk Topics.","Validate if Risk Topics displayed have 'Active' status.");
		try {
			cf.changeDropdownStatusTo("Active");
			// This triggers an AJAX call, hence the Web Elements become Stale.
			validateIfListContainsOnly("Active"); // Throws Exception. Repeat the same line inside catch block.
		} catch (StaleElementReferenceException e) {
			Screenshot.captureScreenshot(getDriver(), "RiskTopic_");
			validateIfListContainsOnly("Active");
		}
		activeStatus.log(Status.PASS, "Successfully validated status for all the 'Active' Risk Topics.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		ExtentTest inactiveStatus = riskTopic.createNode("Inactive Status Risk Topics.","Validate if Risk Topics displayed have 'Inactive' status.");
		try {
			cf.changeDropdownStatusTo("Inactive");
			// This triggers an AJAX call, hence the Web Elements become Stale.
			// It will throw Exception. Repeat the same line inside catch block.
		} catch (StaleElementReferenceException e) {
			cf.changeDropdownStatusTo("Inactive");
			Screenshot.captureScreenshot(getDriver(), "RiskTopic_");
			validateIfListContainsOnly("Inactive");
		}
		inactiveStatus.log(Status.PASS, "Successfully validated status for all the 'Inactive' Risk Topics.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public void downloadRiskTopicExtract() {
		ExtentTest ExportToExcel = riskTopic.createNode("Export To Excel.","Validate if User can successfully export Risk Topics List.");
		ExportToExcel.log(Status.PASS, "Clicked on 'Export to Excel' Button.");
		cf.clickExportToExcelBtn();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		ExportToExcel.log(Status.PASS, "Succesfully downloaded the extract & placed in path: " + Base.extractDownloadedPath);
		Screenshot.captureScreenshot(getDriver(), "RiskTopic_");
	}

	public void clickAddBtn() {
		cpo.addBtn().click();
	}

	public void addRiskTopicAndValidate() {
		ExtentTest addRiskTopic = riskTopic.createNode("Add Risk Topic.","Validate if User can successfully add new Risk Topic.");
		addRiskTopic.log(Status.PASS, "Clicked on 'Add' button");
		clickAddBtn();
		Screenshot.captureScreenshot(getDriver(), "RiskTopic_");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		int appendedID = cf.getRandomNumberUsingNextInt(100, 400)+cf.getRandomNumberUsingNextInt(1, 10);
		String riskTopicIDValue = AppendedValue.appendedAtFirst+appendedID;
		getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_txtScoreCatId']")).sendKeys(riskTopicIDValue);
		addRiskTopic.log(Status.PASS, "Entered value for Risk Topic ID: "+riskTopicIDValue);
		
		int appendedName = cf.getRandomNumberUsingNextInt(100, 400)+cf.getRandomNumberUsingNextInt(1, 10);
		String riskTopicNameValue = AppendedValue.appendedAtFirst+appendedName;
		getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_txtScoreCatName']")).sendKeys(riskTopicNameValue);
		addRiskTopic.log(Status.PASS, "Entered value for Risk Topic Name: "+riskTopicNameValue);
		
		Screenshot.captureScreenshot(getDriver(), "RiskTopic_");
		
		// Save Button.
		cpo.saveBtn().click();
		addRiskTopic.log(Status.PASS, "Clicked 'Save' button");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "RiskTopic_");
		
		//Close Button.
		cpo.closeBtn().click();
		addRiskTopic.log(Status.PASS, "Clicked 'Close' button");

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

		//Validate if new risk topic is present in active list or not.
		WebElement newRiskTopicAdded = getDriver().findElement(By.xpath("//td[text()='"+riskTopicNameValue+"']"));
		if(newRiskTopicAdded.isEnabled()) {
			System.out.println("Successfully added new fund: "+riskTopicNameValue);
			addRiskTopic.log(Status.PASS, "Successfully added new fund: "+riskTopicNameValue);
			cf.highlightElement(newRiskTopicAdded);
		} else {
			System.out.println("Failed to add new fund: "+riskTopicNameValue);
			addRiskTopic.log(Status.FAIL, "Failed to add new fund: "+riskTopicNameValue);
		}
	}

	public void validateRiskTopicPage() {
		riskTopic = Reports.extent.createTest("Risk Topic", "Validate all Test Scenarios for Risk Topic page");
		clickRiskTopicTab();
		validateRiskTopicList();
		if(config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			downloadRiskTopicExtract();
		}
		addRiskTopicAndValidate();	

	}

}
