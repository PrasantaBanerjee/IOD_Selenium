package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iod.main.Base;

public class History_VendorScorecard extends Base{
	
	public WebElement vendorScorecardTab() {
		return getDriver().findElement(By.xpath("//a[@title='View Vendor Scorecard']"));
	}
	

}
