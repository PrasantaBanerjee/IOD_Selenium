package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iod.main.Base;

public class Tracking_Option extends Base {
	
	public WebElement trackingOptionTab() {
		return getDriver().findElement(By.xpath("//a[@title='Maintain Tracking Options']"));
	}
	

}
