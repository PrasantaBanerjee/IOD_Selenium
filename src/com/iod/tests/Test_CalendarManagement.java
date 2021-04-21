package com.iod.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.iod.commons.AppendedValue;
import com.iod.commons.CommonFunctions;
import com.iod.commons.CommonPageObjects;
import com.iod.page_objects.CalendarManagement;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_CalendarManagement extends CalendarManagement {

	ExtentTest calendar;
	
	public void addCalendarEvent() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Screenshot.captureScreenshot(getDriver(), "CalendarManagement_");
		ExtentTest addEvent = calendar.createNode("Add Event.","Validate if User can add new Calendar Event.");
		
		//Double click on date grid.
		WebElement dateGrid = getDriver().findElement(By.xpath("//table[@id='ctl00_ContentPlaceHolderMain_calEvent']//div[@id='divCalendarDay20201103']"));
		Actions act = new Actions(getDriver());
		act.doubleClick(dateGrid).perform();
		addEvent.log(Status.PASS, "Double-clcikced on Date Grid");

		//Appended string.
		CommonFunctions cf = new CommonFunctions();
		int appendedVal = cf.getRandomNumberUsingNextInt(100, 400)+cf.getRandomNumberUsingNextInt(1, 10);
		String eventName = AppendedValue.appendedAtFirst+appendedVal;

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Screenshot.captureScreenshot(getDriver(), "CalendarManagement_");
		//Enter event name.
		getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$ecdCalendar$txtEventName']")).sendKeys(eventName);
		addEvent.log(Status.PASS, "Entered Event Name: "+eventName);
		
		//Enter event description.
		String eventDesc = "QA_Automation";
		getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$ecdCalendar$txtEventDesc']")).sendKeys(eventDesc);
		addEvent.log(Status.PASS, "Entered Event Description: "+ eventDesc);
		Screenshot.captureScreenshot(getDriver(), "CalendarManagement_");
		
		//Click on Save.
		CommonPageObjects cpo = new CommonPageObjects();
		cpo.saveBtn().click();
		addEvent.log(Status.PASS, "Clicked on Save button.");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "CalendarManagement_");
		//Click on Close.
		try {
			CommonPageObjects cp = new CommonPageObjects();
			WebElement close = cp.closeBtn();
			if(close.isDisplayed()) {
				close.click();
			} 
		} catch(StaleElementReferenceException e) {
			CommonPageObjects cppo = new CommonPageObjects();
			WebElement closed = cppo.closeBtn();
			if(closed.isDisplayed()) {
				closed.click();
			} 
		}
		addEvent.log(Status.PASS, "Clicked on Close button.");
		Screenshot.captureScreenshot(getDriver(), "CalendarManagement_");
	}

	public void deleteCalendarEvent() {
		ExtentTest deleteEvent = calendar.createNode("Delete Event.","Validate if User can delete Calendar Event.");
		Screenshot.captureScreenshot(getDriver(), "CalendarManagement_");
		//Click on event created.
		getDriver().findElement(By.xpath("//div[@id='divCalendarDay20201103']//td[@class='CalendarEventDayEventRow']//a")).click();
		deleteEvent.log(Status.PASS, "Clicked on the Event.");
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "CalendarManagement_");
		//Click Delete.
		getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$ecdCalendar$btnDelete']")).click();
		deleteEvent.log(Status.PASS, "Clicked on the Delete button.");
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		Screenshot.captureScreenshot(getDriver(), "CalendarManagement_");
		//Click on 'Yes' button on the confirmation box.
		try {
			WebElement yesBtn = getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$ecdCalendar$wdwDelete$tmpl$btnDeleteYes']"));
			yesBtn.click();
		} catch(StaleElementReferenceException e) {
			WebElement yesBtn = getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$ecdCalendar$wdwDelete$tmpl$btnDeleteYes']"));
			yesBtn.click();
		}
		deleteEvent.log(Status.PASS, "Clicked on the 'Yes' button to confirm.");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Screenshot.captureScreenshot(getDriver(), "CalendarManagement_");
		//Click on Close.
		CommonPageObjects cpo = new CommonPageObjects();
		WebElement closeBtn = cpo.closeBtn();
		if(closeBtn.isDisplayed()) {
			closeBtn.click();
		}
		deleteEvent.log(Status.PASS, "Clicked on the Close button.");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Screenshot.captureScreenshot(getDriver(), "CalendarManagement_");
	}

	public void validate_CalendarMgmtPage() {
		calendar = Reports.extent.createTest("Calendar","Validate Test Scenarios for Calendar page.");
		calendarMgmtTab().click();
		addCalendarEvent();
		deleteCalendarEvent();

	}

}
