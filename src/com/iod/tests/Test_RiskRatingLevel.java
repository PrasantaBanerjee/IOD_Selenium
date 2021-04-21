package com.iod.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.iod.commons.CommonPageObjects;
import com.iod.page_objects.Risk_RatingLevel;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_RiskRatingLevel extends Risk_RatingLevel{
	
	ExtentTest riskRateLevel;

	public void addRiskLevel() {
		Screenshot.captureScreenshot(getDriver(), "RiskRating_");
		ExtentTest addRiskLevel = riskRateLevel.createNode("Add Risk Level.","Validate if User can Add Risk Level.");
		getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnAddRiskRatingLevel']")).click();
		addRiskLevel.log(Status.PASS, "Clicked on Add button");
		Screenshot.captureScreenshot(getDriver(), "RiskRating_");
		
		try {
			Select qualifier = new Select(getDriver().findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolderMain$wdwRiskRatingLevel$tmpl$ddlRiskRatingLevelQualifier']")));
			qualifier.selectByValue("<");
			addRiskLevel.log(Status.PASS, "Selected Qualifier value: Less than");
		} catch(StaleElementReferenceException e) {
			Select qualifier = new Select(getDriver().findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolderMain$wdwRiskRatingLevel$tmpl$ddlRiskRatingLevelQualifier']")));
			qualifier.selectByValue("<");
			addRiskLevel.log(Status.PASS, "Selected Qualifier value: Less than");
		}

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			WebElement riskRating = getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$wdwRiskRatingLevel$tmpl$txtRiskRating']"));
			riskRating.sendKeys("Too Low");
			addRiskLevel.log(Status.PASS, "Entered Risk rating value: Too Low");
		} catch(StaleElementReferenceException e) {
			WebElement riskRating = getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$wdwRiskRatingLevel$tmpl$txtRiskRating']"));
			riskRating.sendKeys("Too Low");
			addRiskLevel.log(Status.PASS, "Entered Risk rating value: Too Low");
		}

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			WebElement greenColor = getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_wdwRiskRatingLevel_tmpl_rblRiskRatingColors_0']"));
			greenColor.click();
			addRiskLevel.log(Status.PASS, "Clicked on Color Radio button: Green");
		} catch(StaleElementReferenceException e) {
			WebElement greenColor = getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_wdwRiskRatingLevel_tmpl_rblRiskRatingColors_0']"));
			greenColor.click();
			addRiskLevel.log(Status.PASS, "Clicked on Color Radio button: Green");
		}

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "RiskRating_");
		getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$wdwRiskRatingLevel$tmpl$btnSaveRiskRatingLevel']")).click();
		addRiskLevel.log(Status.PASS, "Clicked on Save button");
		
		CommonPageObjects cpo =new CommonPageObjects();
		try {
			cpo.saveBtn().click();
			addRiskLevel.log(Status.PASS, "Clicked on Save button");
		} catch(StaleElementReferenceException e) {
			cpo.saveBtn().click();
			addRiskLevel.log(Status.PASS, "Clicked on Save button");
			Screenshot.captureScreenshot(getDriver(), "RiskRating_");
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			
			cpo.closeBtn().click();
			addRiskLevel.log(Status.PASS, "Clicked on Close button");
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

	}
	
	public void deleteRiskLevel() {
		ExtentTest delRiskLevel = riskRateLevel.createNode("Delete Risk Level.","Validate if User can Delete Risk Level.");
		WebElement checkBox = getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_wdgRiskRatingLevels_it0_0_cbxOption']"));
		checkBox.click();
		delRiskLevel.log(Status.PASS, "Clicked on the checkbox");
		Screenshot.captureScreenshot(getDriver(), "RiskRating_");

		WebElement deleteBtn = getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_btnDeleteRiskRatingLevel']"));
		deleteBtn.click();
		delRiskLevel.log(Status.PASS, "Clicked on Delete button");
		Screenshot.captureScreenshot(getDriver(), "RiskRating_");

		try {
			Thread.sleep(1500);
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		//Click on 'Yes' button on the confirmation box.
		getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$yndConfirm$wdwConfirmation$tmpl$btnYes']")).click();
		delRiskLevel.log(Status.PASS, "Clicked on Yes button to confirm deletion.");
		Screenshot.captureScreenshot(getDriver(), "RiskRating_");

		//Click on Save button.
		CommonPageObjects cpo =new CommonPageObjects();
		try {
			cpo.saveBtn().click();
			delRiskLevel.log(Status.PASS, "Clicked on Save button");
			Screenshot.captureScreenshot(getDriver(), "RiskRating_");
			
			//Click on Close button.
			WebElement closeBtn = cpo.closeBtn();
			if(closeBtn.isDisplayed()) {
				closeBtn.click();			
			} else {
				System.out.println("Not displayed");
			}
			
			delRiskLevel.log(Status.PASS, "Clicked on Close button");
			Screenshot.captureScreenshot(getDriver(), "RiskRating_");
		} catch(StaleElementReferenceException e) {
			cpo.saveBtn().click();
			delRiskLevel.log(Status.PASS, "Clicked on Save button");
		}
		
	}

	public void validate_RiskRatingLevelPage() {
		riskRateLevel = Reports.extent.createTest("Risk Rating Level","Validate Test Scenarios for Risk Rating Level page.");
		riskRatingLevelTab().click();
		
		addRiskLevel();
		getDriver().navigate().refresh();
		deleteRiskLevel();
		
	}

}
