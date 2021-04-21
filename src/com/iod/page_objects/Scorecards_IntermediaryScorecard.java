package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iod.main.Base;

public class Scorecards_IntermediaryScorecard extends Base{
	
	public WebElement intermediaryScorecardTab() {
		return getDriver().findElement(By.xpath("//a[@title='Maintain / View Intermediary Scorecard']"));
	}

}
