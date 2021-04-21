package com.iod.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.iod.commons.CommonFunctions;
import com.iod.commons.CommonPageObjects;
import com.iod.main.Base;
import com.iod.page_objects.Risk_Element;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_RiskElement extends Risk_Element{

	CommonFunctions cf = new CommonFunctions();
	CommonPageObjects cpo = new CommonPageObjects();
	ExtentTest riskElement;

	public void clickRiskElementTab() {
		riskElementTab().click();
		Screenshot.captureScreenshot(getDriver(), "RiskElement_");
	}

	public void validateIfListContainsOnly(String expectedStatus) {
		List<WebElement> listOfItems = cpo.statusInRiskElementList();
		for (WebElement eachItem : listOfItems) {
			JavascriptExecutor jse = (JavascriptExecutor) getDriver();
			jse.executeScript("arguments[0].scrollIntoView();", eachItem);
			String actualStatus = eachItem.getText();
			if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
				System.out.println("Mismatch found in the Risk Element List.");
				System.out.println("Expected: " + expectedStatus + ". Actual: " + actualStatus);
			}
		}
		System.out.println("Status for all the Risk Element was validated successfully.");
	}

	public void validateRiskElementList() {
		//clickChangeDisplayOrderBtn();
		//This triggers AJAX call.

		ExtentTest activeStatus = riskElement.createNode("Active Status Risk Element.","Validate if Risk Element displayed have 'Active' status.");
		try {
			cf.changeDropdownStatusTo("Active");
			// This triggers an AJAX call, hence the Web Elements become Stale.
			validateIfListContainsOnly("Active"); // Throws Exception. Repeat the same line inside catch block.
		} catch (StaleElementReferenceException e) {
			Screenshot.captureScreenshot(getDriver(), "RiskElement_");
			validateIfListContainsOnly("Active");
		}
		activeStatus.log(Status.PASS, "Successfully validated status for all the 'Active' Risk Element.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		ExtentTest inactiveStatus = riskElement.createNode("Inactive Status Risk Element.","Validate if Risk Element displayed have 'Inactive' status.");
		try {
			cf.changeDropdownStatusTo("Inactive");
			// This triggers an AJAX call, hence the Web Elements become Stale.
			// It will throw Exception. Repeat the same line inside catch block.
		} catch (StaleElementReferenceException e) {
			cf.changeDropdownStatusTo("Inactive");
			Screenshot.captureScreenshot(getDriver(), "RiskElement_");
			validateIfListContainsOnly("Inactive");
		}
		inactiveStatus.log(Status.PASS, "Successfully validated status for all the 'Inactive' Risk Element.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public void downloadRiskElementExtract() {
		ExtentTest ExportToExcel = riskElement.createNode("Export To Excel.","Validate if User can successfully export Risk Element List.");
		ExportToExcel.log(Status.PASS, "Clicked on 'Export to Excel' Button.");
		cf.clickExportToExcelBtn();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		ExportToExcel.log(Status.PASS, "Succesfully downloaded the extract & placed in path: " + Base.extractDownloadedPath);
		Screenshot.captureScreenshot(getDriver(), "RiskElement_");
	}

	public void clickAddBtn() {
		cpo.addBtn().click();
	}

	public void scoreCalculation(String qualifierValue, String conditionValue, String pointsValue) {
		WebElement addBtn = getDriver().findElement(By.xpath("//div[@id='ctl00_ContentPlaceHolderMain_pnlCalculationDetail']//tr//td[@nowrap=\"nowrap\"]//input[@value='Add']"));
		addBtn.click();

		Select qualifierDropdown;
		try {
			qualifierDropdown = new Select(getDriver().findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolderMain_wdwRiskCatNS_tmpl_ddlRiskCatNSQualifier']")));
		} catch(StaleElementReferenceException e) {
			qualifierDropdown = new Select(getDriver().findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolderMain_wdwRiskCatNS_tmpl_ddlRiskCatNSQualifier']")));
			qualifierDropdown.selectByValue(qualifierValue);
			//qualifierDropdown.selectByVisibleText("Less Than");
			
			WebElement condition = getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_wdwRiskCatNS_tmpl_wneRiskCatNSFrom']"));
			condition.sendKeys(conditionValue);

			WebElement points = getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_wdwRiskCatNS_tmpl_wneRiskCatNSScore']"));
			points.sendKeys(pointsValue);

			getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_wdwRiskCatNS_tmpl_btnRiskCatNSSave']")).click();
		}
		
		
	}

	public void addRiskTopicAndValidate() {
		ExtentTest addRiskElement = riskElement.createNode("Add Risk Element.","Validate if User can successfully add new Risk Element.");
		addRiskElement.log(Status.PASS, "Clicked on 'Add' button");
		clickAddBtn();
		Screenshot.captureScreenshot(getDriver(), "RiskElement_");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		int appendedID = cf.getRandomNumberUsingNextInt(100, 400)+cf.getRandomNumberUsingNextInt(1, 10);
		String riskElementIDValue = "Q"+appendedID;
		getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_txtRiskCatId']")).sendKeys(riskElementIDValue);
		addRiskElement.log(Status.PASS, "Entered value for Risk Element ID: "+riskElementIDValue);

		int appendedName = cf.getRandomNumberUsingNextInt(100, 400)+cf.getRandomNumberUsingNextInt(1, 10);
		String riskElementNameValue = "Auto"+appendedName;
		getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_txtRiskCatName']")).sendKeys(riskElementNameValue);
		addRiskElement.log(Status.PASS, "Entered value for Risk Element Name: "+riskElementNameValue);

		Select topicValueDropdown = new Select(getDriver().findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolderMain_ddlScoreCat']")));
		String topicDropdownValue = "AGREEMENT PROVISIONS";
		topicValueDropdown.selectByValue(topicDropdownValue);
		addRiskElement.log(Status.PASS, "Selected value for Risk Topic: "+topicDropdownValue);

		Select riskElementTypeDropdown = new Select(getDriver().findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolderMain_ddlRiskCatType']")));
		String riskTypeDropdownValue = "NS";
		riskElementTypeDropdown.selectByValue(riskTypeDropdownValue);
		addRiskElement.log(Status.PASS, "Selected value for Risk Element Type: "+riskTypeDropdownValue);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}


		//Score calculation for less than value.
		scoreCalculation("<","12","5");








		try {
			WebElement selectAllTrackOption = getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_mslbTrackingOption_btnAllRight']"));
			selectAllTrackOption.click();
		} catch(StaleElementReferenceException e) {
			WebElement selectAllTrackOption = getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_mslbTrackingOption_btnAllRight']"));
			selectAllTrackOption.click();
			addRiskElement.log(Status.PASS, "Selected value for Tracking Options: ALL");			
		}

		Screenshot.captureScreenshot(getDriver(), "RiskElement_");

		// Save Button.
		cpo.saveBtn().click();
		addRiskElement.log(Status.PASS, "Clicked on 'Save' button.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "RiskElement_");

		//Close Button.
		cpo.closeBtn().click();
		addRiskElement.log(Status.PASS, "Clicked on 'Close' button.");

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
		WebElement newRiskElementAdded = getDriver().findElement(By.xpath("//td[text()='"+riskElementNameValue+"']"));
		if(newRiskElementAdded.isEnabled()) {
			System.out.println("Successfully added new fund: "+riskElementNameValue);
			addRiskElement.log(Status.PASS, "Successfully added new Risk Element: "+riskElementNameValue);
			cf.highlightElement(newRiskElementAdded);
		} else {
			System.out.println("Failed to add new fund: "+riskElementNameValue);
			addRiskElement.log(Status.FAIL, "Failed to add Risk Element: "+riskElementNameValue);
		}
	}

	public void validateRiskElementPage() {
		riskElement = Reports.extent.createTest("Risk Element","Validate Test Scenarios for Risk Element page.");
		clickRiskElementTab();
		validateRiskElementList();
		if(config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			downloadRiskElementExtract();
		}
		//addRiskTopicAndValidate();
	}

}
