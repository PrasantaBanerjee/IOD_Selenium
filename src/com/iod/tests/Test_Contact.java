package com.iod.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.iod.commons.CommonPageObjects;
import com.iod.page_objects.Contact;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_Contact extends Contact
{
	ExtentTest contact;

	public void selectFirmAndExport() 
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//Navigating to Contact Maintenance Page
		Contact.contact().click();

		//Selecting firm name.
		ExtentTest selectFirm = contact.createNode("Select Firm & Export Contact.","Validate if User can select Firm & export Contact.");
		Select firmNameDropdown = new Select(Contact.firmName());
		firmNameDropdown.selectByValue("11");
		selectFirm.log(Status.PASS, "Selected Firm Name from dropdown.");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//Clicking on Export to Excel Button
		if(config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			downloadContactExtract();
		} 
		selectFirm.log(Status.PASS, "Clicked Export to Excel button");	
		selectFirm.log(Status.PASS, "Successfully extracted Contacts.xls");		
		Screenshot.captureScreenshot(getDriver(), "Contact_");

	}

	public void downloadContactExtract() {
		getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnExportContact']")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

	}

	public void addContact()
	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ExtentTest selectFirm = contact.createNode("Add Contact.","Validate if User can Add Contact.");
		//Clicking on Add Button
		Contact.Add_Contact().click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String titleValue = "QA_Automation";
		WebElement title = getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$txtTitle']"));
		title.sendKeys(titleValue);
		selectFirm.log(Status.PASS, "Entered Title: " + titleValue);

		String firstName = "Test";
		Contact.FirstName().sendKeys(firstName);
		selectFirm.log(Status.PASS, "Entered First Name: " + firstName);

		String lastName = "QA";
		Contact.LastName().sendKeys(lastName);
		selectFirm.log(Status.PASS, "Entered Last Name: " + lastName);

		String workPhone = "7762233499";
		Contact.WorkPhone().sendKeys(workPhone);
		selectFirm.log(Status.PASS, "Entered Work Phone: " + workPhone);

		Screenshot.captureScreenshot(getDriver(), "Contact_");
		Contact.Radiobtn().click();

		String mail = "test@qa.com";
		Contact.Email().sendKeys(mail);
		selectFirm.log(Status.PASS, "Entered E-Mail: " + mail);

		Contact.Radiobtn1().click();

		String address = "QA_Test";
		Contact.Address1().sendKeys(address);
		selectFirm.log(Status.PASS, "Entered Address: " + address);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String city = "Chicago";
		Contact.City().sendKeys(city); 
		selectFirm.log(Status.PASS, "Entered City: " + city);

		String zip = "66554";
		Contact.Zip().sendKeys(zip);
		selectFirm.log(Status.PASS, "Entered Zip: " + zip);

		Screenshot.captureScreenshot(getDriver(), "Contact_");
		Contact.Sameaddress().click();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Contact.Category().click();
		Contact.Rtbtn().click();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Contact.AddComment().click();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String comments = "Added by QA Automation";
		Contact.TextComment().sendKeys(comments);
		selectFirm.log(Status.PASS, "Entered Comments: " + comments);

		Screenshot.captureScreenshot(getDriver(), "Contact_");     
		Contact.SaveComment().click();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "Contact_");
		Contact.SaveButton().click();
		selectFirm.log(Status.PASS, "Clicked Save button");

		//If no primary contact has been set, then click to set it.
		try {
			if(getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$wdwPrimaryContact$tmpl$btnPrimaryContactYes']")).isDisplayed()) {
				getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$wdwPrimaryContact$tmpl$btnPrimaryContactYes']")).click();
			} 
		}catch(StaleElementReferenceException x) {	
			if(getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$wdwPrimaryContact$tmpl$btnPrimaryContactYes']")).isDisplayed()) {
				getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$wdwPrimaryContact$tmpl$btnPrimaryContactYes']")).click();

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} 
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Screenshot.captureScreenshot(getDriver(), "Contact_");
		CommonPageObjects cpo = new CommonPageObjects();
		cpo.closeBtn().click();
		selectFirm.log(Status.PASS, "Clicked Close button");
		Screenshot.captureScreenshot(getDriver(), "Contact_");
	}

	public void deleteContact() {
		ExtentTest delContact = contact.createNode("Delete Contact.","Validate if User can Delete Contact.");
		WebElement checkBox = getDriver().findElement(By.xpath("//td[contains(text(),'QA_Automation')]/parent::tr/td/input"));
		checkBox.click();
		delContact.log(Status.PASS, "Clicked on the checkbox");
		Screenshot.captureScreenshot(getDriver(), "Contact_");

		WebElement deleteBtn = getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_btnDelete']"));
		deleteBtn.click();
		delContact.log(Status.PASS, "Clicked on Delete button");
		Screenshot.captureScreenshot(getDriver(), "Contact_");

		try {
			Thread.sleep(1500);
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		//Click on 'Yes' button on the confirmation box.
		getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$yndDelete$wdwConfirmation$tmpl$btnYes']")).click();
		delContact.log(Status.PASS, "Clicked on Yes button to confirm deletion.");
		Screenshot.captureScreenshot(getDriver(), "Contact_");

		//Click on Save button.
		CommonPageObjects cpo =new CommonPageObjects();
		try {
			//Click on Close button.
			WebElement closeBtn = cpo.closeBtn();
			if(closeBtn.isDisplayed()) {
				closeBtn.click();			
			} else {
				System.out.println("Not displayed");
			}

			delContact.log(Status.PASS, "Clicked on Close button");
			Screenshot.captureScreenshot(getDriver(), "Contact_");
		} catch(StaleElementReferenceException e) {
			cpo.saveBtn().click();
			delContact.log(Status.PASS, "Clicked on Save button");
		}
	}

	public void validate_ContactPage() {
		contact = Reports.extent.createTest("Contact","Validate Test Scenarios for Contact page.");
		selectFirmAndExport();
		addContact();
		deleteContact();

	}
}
