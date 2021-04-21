package com.iod.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.iod.commons.AppendedValue;
import com.iod.commons.CommonFunctions;
import com.iod.commons.CommonPageObjects;
import com.iod.main.Base;
import com.iod.page_objects.Contact_Category;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_ContactCategory extends Contact_Category{
	
	ExtentTest contactCategory;
	
	public void addContactCategory() {
		Screenshot.captureScreenshot(getDriver(), "ContactCategory_");
		ExtentTest addContact = contactCategory.createNode("Add Contact Category.","Validate if User can add Contact Category.");
		CommonPageObjects cpo = new CommonPageObjects();
		CommonFunctions cf = new CommonFunctions();
		cpo.addBtn().click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "ContactCategory_");
		//Enter Department ID.
		WebElement deptID = getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$txtContactCatId']"));
		int appendedVal = cf.getRandomNumberUsingNextInt(100, 400)+cf.getRandomNumberUsingNextInt(1, 10);
		String deptValue = AppendedValue.appendedAtFirst+appendedVal;
		deptID.sendKeys(deptValue);
		addContact.log(Status.PASS, "Entered Department ID: "+deptValue);

		//Enter Department Name.
		String nameVal = AppendedValue.appendedAtFirst+"_Automation"+appendedVal;
		getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$txtContactCatName']")).sendKeys(nameVal);
		addContact.log(Status.PASS, "Entered Department Name: "+nameVal);
		
		Screenshot.captureScreenshot(getDriver(), "ContactCategory_");
		//Click Save Button.
		cpo.saveBtn().click();
		addContact.log(Status.PASS, "Clicked on Save button");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "ContactCategory_");
		//Click Close button.
		cpo.closeBtn().click();
		addContact.log(Status.PASS, "Clicked on Close button");
		Screenshot.captureScreenshot(getDriver(), "ContactCategory_");
	}
	
	public void downloadContactExtract() {
		ExtentTest ExportToExcel = contactCategory.createNode("Export To Excel.","Validate if User can successfully export Contact Category List.");
		ExportToExcel.log(Status.PASS, "Clicked on 'Export to Excel' Button.");
		
		CommonFunctions cf = new CommonFunctions();
		cf.clickExportToExcelBtn();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		ExportToExcel.log(Status.PASS, "Succesfully downloaded the extract & placed in path: " + Base.extractDownloadedPath);
		Screenshot.captureScreenshot(getDriver(), "ContactCategory_");
		
	}
	
	public void validate_ContactCategoryPage() {
		contactCategory = Reports.extent.createTest("Contact Category","Validate Test Scenarios for Contact Category page.");
		contactCategoryTab().click();
		addContactCategory();
		if(config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			downloadContactExtract();
		}
	}
}