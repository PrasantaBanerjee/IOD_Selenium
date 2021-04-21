package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.iod.main.Base;

public class User_Access extends Base{
	
	public WebElement userAccessTab() {
		return getDriver().findElement(By.xpath("//a[@title='Maintain User Details']"));
	}

}
