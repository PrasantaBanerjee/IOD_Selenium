package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iod.main.Base;

public class Contact_Category extends Base{
	
	public WebElement contactCategoryTab() {
		return getDriver().findElement(By.xpath("//a[@title='Maintain Contact Category']"));
	}

}
