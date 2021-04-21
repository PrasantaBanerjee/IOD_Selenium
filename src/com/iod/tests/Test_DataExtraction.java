package com.iod.tests;

import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.iod.page_objects.DataExtraction;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_DataExtraction extends DataExtraction
{
	ExtentTest dataExtract;
	
	public void extractData()	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Navigating to Data Extraction Page
		DataExtraction.dataExtraction().click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Selecting "Phone Contacts" from Contact Type Drop down
		Select contactTypeDropdown = new Select(DataExtraction.contactType());
		contactTypeDropdown.selectByVisibleText("Phone Contacts");
		ExtentTest extract = dataExtract.createNode("Extract Data.","Validate if User can extract Data.");
		extract.log(Status.PASS, "Selected Contact Type: Phone Contacts");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Selecting "All" from Selection Drop down
		Select selectionDropdown = new Select(DataExtraction.selection());
		selectionDropdown.selectByVisibleText("All");
		extract.log(Status.PASS, "Selected value for Firm: All");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Selecting "All" from Firm Type Drop down
		Select firmTypeDropdown = new Select(DataExtraction.firmType());
		firmTypeDropdown.selectByVisibleText("All");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Selecting "Both" from Firm Status Drop down
		Select firmStatusDropdown = new Select(DataExtraction.firmStatus());
		firmStatusDropdown.selectByVisibleText("Both");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Clicking on All Right Button
		DataExtraction.buttonAllRight().click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Screenshot.captureScreenshot(getDriver(), "DataExtraction_");
		
		//Clicking on Extract Button
		DataExtraction.extract().click();
		extract.log(Status.PASS, "Clicked on Extract button.");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Screenshot.captureScreenshot(getDriver(), "DataExtraction_");
		//Clicking on Export to Excel Button
		if(config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			DataExtraction.exportToExcel().click();
		}
		extract.log(Status.PASS, "Clicked Export to Excel button");	
		extract.log(Status.PASS, "Successfully extracted the file.");		

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Screenshot.captureScreenshot(getDriver(), "DataExtraction_");
		//Clicking on Cancel Button
		DataExtraction.cancel().click();
		extract.log(Status.PASS, "Clicked on Cancel button.");
			
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Screenshot.captureScreenshot(getDriver(), "DataExtraction_");
		
		//Clicking on Reset Button
		DataExtraction.reset().click();		
		extract.log(Status.PASS, "Clicked on Reset button.");
		Screenshot.captureScreenshot(getDriver(), "DataExtraction_");
	}
	
	public void validate_dataExtractionPage() {
		dataExtract = Reports.extent.createTest("Data Extraction","Validate Test Scenarios for Data Extraction page.");
		extractData();
	}
}


