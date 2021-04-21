package com.iod.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.iod.commons.CommonFunctions;
import com.iod.commons.CommonPageObjects;
import com.iod.main.Base;
import com.iod.page_objects.Adhoc_VisitFilter;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_AdhocVisitFilter extends Adhoc_VisitFilter {

	CommonPageObjects  cpo = new CommonPageObjects();
	ExtentTest adhocFilter;

	public void deleteAdhocFilter() {
		Screenshot.captureScreenshot(getDriver(), "AdhocVisitFilter_");
		ExtentTest deleteAdhoc = adhocFilter.createNode("Delete Adhoc Visit Filter.","Validate if User can delete an Adhoc Visit Filter.");
		WebElement checkBox = getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_wdgSubAdviserFilter_it0_0_chkRow']"));
		checkBox.click();
		deleteAdhoc.log(Status.PASS, "Clicked on the checkbox.");
		Screenshot.captureScreenshot(getDriver(), "AdhocVisitFilter_");

		WebElement deleteBtn = getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnDelete']"));
		deleteBtn.click();
		System.out.println("Clicked on Delete button.");
		deleteAdhoc.log(Status.PASS, "Clicked on Delete button.");

		try {
			Thread.sleep(1500);
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		//Click on 'Yes' button on the confirmation box.
		Screenshot.captureScreenshot(getDriver(), "AdhocVisitFilter_");
		getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$yndConfirm$wdwConfirmation$tmpl$btnYes']")).click();
		deleteAdhoc.log(Status.PASS, "Clicked on Yes button to confirm deletion.");

		//Click on 'Close' button on the dialog box.
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		// Click Close Button.
		Screenshot.captureScreenshot(getDriver(), "AdhocVisitFilter_");
		cpo.closeBtn().click();
		deleteAdhoc.log(Status.PASS, "Clicked on Close button.");
		Screenshot.captureScreenshot(getDriver(), "AdhocVisitFilter_");

	}

	public void addAdhocVisitFilter() {
		ExtentTest addAdhoc = adhocFilter.createNode("Add Adhoc Visit Filter.","Validate if User can add an Adhoc Vsiit Filter.");
		//Click on 'Add' button.
		getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnAdd']")).click();
		addAdhoc.log(Status.PASS, "Clicked on Add Button.");

		try {
			Thread.sleep(3000);
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "AdhocVisitFilter_");
		Select riskElementDropdown  = new Select(getDriver().findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolderMain$ddlRiskCat']")));
		riskElementDropdown.selectByValue("12B-1");
		addAdhoc.log(Status.PASS, "Selected value for Risk Element: 12B-1.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Select conditionDropdown = new Select(getDriver().findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolderMain$ddlDropDown']")));
		conditionDropdown.selectByValue("68");
		addAdhoc.log(Status.PASS, "Selected value from Condition dropdown: Yes");

		Screenshot.captureScreenshot(getDriver(), "AdhocVisitFilter_");
		getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnSave']")).click();
		addAdhoc.log(Status.PASS, "Clicked on Save button.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "AdhocVisitFilter_");
		// Click Close Button.
		cpo.closeBtn().click();
		addAdhoc.log(Status.PASS, "Clicked on Close button.");
		Screenshot.captureScreenshot(getDriver(), "AdhocVisitFilter_");

	}

	public void downloadAdhocVisitFilterExtract() {
		ExtentTest ExportToExcel = adhocFilter.createNode("Export To Excel.","Validate if User can successfully export Adhoc Visit Filter List.");
		ExportToExcel.log(Status.PASS, "Clicked on 'Export to Excel' Button.");
		
		CommonFunctions cf = new CommonFunctions();
		cf.clickExportToExcelBtn();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		ExportToExcel.log(Status.PASS, "Succesfully downloaded the extract & placed in path: " + Base.extractDownloadedPath);
		Screenshot.captureScreenshot(getDriver(), "AdhocVisitFilter_");
		
	}

	public void validate_adhocVisitFilterPage() {
		adhocFilter = Reports.extent.createTest("Adhoc Visit Filter","Validate Test Scenarios for Adhoc Visit Filter page.");
		adhocVisitFilterTab().click();
		deleteAdhocFilter();
		addAdhocVisitFilter();
		if(config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			downloadAdhocVisitFilterExtract();
		}
	}

}
