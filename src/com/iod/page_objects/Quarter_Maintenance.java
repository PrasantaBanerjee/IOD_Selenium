package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iod.main.Base;

public class Quarter_Maintenance extends Base {
	
	public WebElement quarterMaintenanceTab() {
		return getDriver().findElement(By.xpath("//a[@title='Generate or Lock Scorecard Quarters']"));
	}
	
	public WebElement generateQ4ReportBtn() {
		return getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnQuarter1']"));
	}
	
	public WebElement generateQ1ReportBtn() {
		return getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnQuarter2']"));
	}

}
