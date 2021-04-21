package com.iod.tests;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.iod.commons.CommonFunctions;
import com.iod.commons.CommonPageObjects;
import com.iod.main.Base;
import com.iod.page_objects.User_Access;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_UserAccess extends User_Access {
	
	CommonFunctions cf = new CommonFunctions();	
	CommonPageObjects cpo = new CommonPageObjects();
	ExtentTest userAccess;
	
	public void clickUserAccessTab() {
		userAccessTab().click();
		Screenshot.captureScreenshot(getDriver(), "UserAccess_");
	}
	
	public void validateIfListContainsOnly(String expectedStatus) {
		List<WebElement> listOfItems = cpo.statusInUserAccessList();
		for (WebElement eachItem : listOfItems) {
			JavascriptExecutor jse = (JavascriptExecutor) getDriver();
			jse.executeScript("arguments[0].scrollIntoView();", eachItem);
			String actualStatus = eachItem.getText();
			if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
				System.out.println("Mismatch found in the User Access List.");
				System.out.println("Expected: " + expectedStatus + ". Actual: " + actualStatus);
			}
		}
		System.out.println("Status for all the User Access was validated successfully.");
	}
	
	public void validateUserAccessList() {
		ExtentTest activeStatus = userAccess.createNode("Active Status Users.","Validate if Users displayed have 'Active' status.");
		try {
			cf.changeDropdownStatusTo("Active");
			//This triggers an AJAX call. Hence the next line throws Exception. Repeat the same line inside catch block.
			validateIfListContainsOnly("Active");
		} catch (StaleElementReferenceException e) {
			Screenshot.captureScreenshot(getDriver(), "UserAccess_");
			validateIfListContainsOnly("Active");
		}
		activeStatus.log(Status.PASS, "Successfully validated status for all the 'Active' Users.");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		ExtentTest inactiveStatus = userAccess.createNode("Inactive Status Users.","Validate if Users displayed have 'Inactive' status.");
		try {
			cf.changeDropdownStatusTo("Inactive");
			//This triggers an AJAX call. Hence the next line throws Exception. Repeat the same line inside catch block.
		} catch (StaleElementReferenceException e) {
			cf.changeDropdownStatusTo("Inactive");
			Screenshot.captureScreenshot(getDriver(), "UserAccess_");
			validateIfListContainsOnly("Inactive");
		}
		inactiveStatus.log(Status.PASS, "Successfully validated status for all the 'Inactive' Firms.");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	public void downloadUserAccessExtract() {
		ExtentTest ExportToExcel = userAccess.createNode("Export To Excel.","Validate if User can successfully export User Access List.");
		ExportToExcel.log(Status.PASS, "Clicked on 'Export to Excel' Button.");
		cf.clickExportToExcelBtn();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		ExportToExcel.log(Status.PASS, "Succesfully downloaded the extract & placed in path: " + Base.extractDownloadedPath);
		Screenshot.captureScreenshot(getDriver(), "UserAccess_");
	}
	
	public void validateUserAccessPage() {
		userAccess = Reports.extent.createTest("User Access","Validate Test Scenarios for User Access page.");
		clickUserAccessTab();
		validateUserAccessList();
		if(config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			downloadUserAccessExtract();
		}
		
	}

}
