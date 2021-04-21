package com.iod.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.iod.commons.AppendedValue;
import com.iod.commons.CommonFunctions;
import com.iod.commons.CommonPageObjects;
import com.iod.main.Base;
import com.iod.page_objects.Department;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_Department extends Department{

	ExtentTest department;
	
	public void addDepartment() {
		ExtentTest addDepartment = department.createNode("Add Departmentl.","Validate if User can Add Department.");
		CommonPageObjects cpo =new CommonPageObjects();
		CommonFunctions cf = new CommonFunctions();
		cpo.addBtn().click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "Department_");
		//Enter Department ID.
		WebElement deptID = getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$txtDepartmentID']"));
		int appendedVal = cf.getRandomNumberUsingNextInt(100, 400)+cf.getRandomNumberUsingNextInt(1, 10);
		String deptValue = AppendedValue.appendedAtFirst+appendedVal;
		deptID.sendKeys(deptValue);
		addDepartment.log(Status.PASS, "Entered Department ID: " + deptValue);

		//Enter Department Name.
		String nameVal = AppendedValue.appendedAtFirst+"_Automation"+appendedVal;
		getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$txtDepartmentName']")).sendKeys(nameVal);
		addDepartment.log(Status.PASS, "Entered Department Name: " + nameVal);

		Screenshot.captureScreenshot(getDriver(), "Department_");
		//Click Save Button.
		cpo.saveBtn().click();
		addDepartment.log(Status.PASS, "Clicked on Save button.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "Department_");
		//Click Close button.
		cpo.closeBtn().click();
		addDepartment.log(Status.PASS, "Clicked on Close button.");
		Screenshot.captureScreenshot(getDriver(), "Department");
	}

	public void downloadDepartmentExtract() {
		ExtentTest ExportToExcel = department.createNode("Export To Excel.","Validate if User can successfully export Department List.");
		ExportToExcel.log(Status.PASS, "Clicked on 'Export to Excel' Button.");

		CommonFunctions cf = new CommonFunctions();
		cf.clickExportToExcelBtn();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		ExportToExcel.log(Status.PASS, "Succesfully downloaded the extract & placed in path: " + Base.extractDownloadedPath);
	}

	public void validate_DepartmentPage() {
		department = Reports.extent.createTest("Department","Validate Test Scenarios for Department page.");
		departmentTab().click();

		if(getDriver().getTitle().equalsIgnoreCase("No Access")) {
			department.log(Status.FAIL, "User doesn't have access to the Department module. Make sure you have 'Application Owner' access.");
			getDriver().navigate().back();
		} else {
			addDepartment();
			if(config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
				downloadDepartmentExtract();
			}
		}
		
	}
	
}
