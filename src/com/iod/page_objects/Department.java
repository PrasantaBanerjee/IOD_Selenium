package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iod.main.Base;

public class Department extends Base{
	
	public WebElement departmentTab() {
		return getDriver().findElement(By.xpath("//a[@title='Maintain Department Details']"));
	}

}
