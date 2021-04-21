package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iod.main.Base;

public class IntermediarySummaryReport extends Base {
	
	public WebElement summaryReportTab() {
		return getDriver().findElement(By.xpath("//a[@title='Intermediary Summary Report']"));
	}

}
