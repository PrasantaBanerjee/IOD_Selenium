package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iod.main.Base;

public class IntermediaryDetailReport extends Base {
	
	public WebElement detailReportTab() {
		return getDriver().findElement(By.xpath("//a[@title='Intermediary Detail Report']"));
	}

}
