package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iod.main.Base;

public class Risk_Topic extends Base{
	
	public WebElement riskTopicTab() {
		return getDriver().findElement(By.xpath("//a[@title='Maintain Risk Topic']"));
	}
	
	public WebElement changeDisplayOrderBtn() {
		return getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_btnRiskTopicChangeDisplayOrder']"));
	}

}
