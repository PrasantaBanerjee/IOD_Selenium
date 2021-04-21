package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iod.main.Base;

public class CalendarManagement extends Base {
	
	public WebElement calendarMgmtTab() {
		return getDriver().findElement(By.xpath("//a[@title='Manage Calendar Events']"));
	}

}
