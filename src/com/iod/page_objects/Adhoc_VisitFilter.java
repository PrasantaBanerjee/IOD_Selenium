package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iod.main.Base;

public class Adhoc_VisitFilter extends Base {
	
	public WebElement adhocVisitFilterTab() {
		return getDriver().findElement(By.xpath("//a[@title='Set Adhoc Visit Filter']"));
	}
	
	public WebElement deleteBtn() {
		return getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnDelete']"));
	}

}
