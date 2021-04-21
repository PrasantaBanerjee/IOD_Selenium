package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iod.main.Base;

public class Risk_Element extends Base{
	
	public WebElement riskElementTab() {
		return getDriver().findElement(By.xpath("//a[@title='Maintain Risk Element']"));
	}

}
