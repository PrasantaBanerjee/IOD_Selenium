package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.iod.main.Base;

public class Firm extends Base{
	
	public WebElement firmTab(){
		return getDriver().findElement(By.xpath("//a[@title='Maintain Firm Details']"));
	}

}
