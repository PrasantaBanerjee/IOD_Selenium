package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iod.main.Base;

public class Risk_RatingLevel extends Base {
	
	public WebElement riskRatingLevelTab() {
		return getDriver().findElement(By.xpath("//a[@title='Risk Rating Level']"));
	}

}
