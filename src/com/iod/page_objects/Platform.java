package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iod.main.Base;

public class Platform extends Base {
	
	public WebElement platformTab() {
		return getDriver().findElement(By.xpath("//a[@id='ctl00_tvMenut4']"));
	}	

}
