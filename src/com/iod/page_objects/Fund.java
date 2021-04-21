package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iod.main.Base;

public class Fund extends Base{
	
	public WebElement fundTab() {
		return getDriver().findElement(By.xpath("//a[@title='Maintain Fund Details']"));
	}

}
