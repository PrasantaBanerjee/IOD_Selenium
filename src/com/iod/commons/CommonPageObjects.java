package com.iod.commons;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.iod.main.Base;

public class CommonPageObjects extends Base{
	
	public WebElement statusDropdown() {
		return getDriver().findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolderMain_ddlListStatus']"));
	}
	
	public List<WebElement> statusInFirmList(){
		return getDriver().findElements(By.xpath("//th[@class='ig_Header igg_RowSelector rowSelectorWidth ']/parent::tr/td[4]"));
	}
	
	public List<WebElement> statusInPlatformList(){
		return getDriver().findElements(By.xpath("//th[@class='ig_Header igg_RowSelector rowSelectorWidth ']/parent::tr/td[3]"));
	}
	
	public List<WebElement> statusInFundList(){
		return getDriver().findElements(By.xpath("//th[@class='ig_Header igg_RowSelector rowSelectorWidth ']/parent::tr/td[4]"));
	}
	
	public List<WebElement> statusInUserAccessList(){
		return getDriver().findElements(By.xpath("//th[@class='ig_Header igg_RowSelector rowSelectorWidth ']/parent::tr/td[3]"));
	}
	
	public List<WebElement> statusInRiskTopicList(){
		return getDriver().findElements(By.xpath("//th[@class='ig_Header igg_RowSelector rowSelectorWidth ']/parent::tr/td[3]"));
	}
	
	public List<WebElement> statusInTrackingOptList(){
		return getDriver().findElements(By.xpath("//th[@class='ig_Header igg_RowSelector rowSelectorWidth ']/parent::tr/td[5]"));
	}	
	
	public List<WebElement> statusInRiskElementList(){
		return getDriver().findElements(By.xpath("//th[@class='ig_Header igg_RowSelector rowSelectorWidth ']/parent::tr/td[4]"));
	}
	
	public WebElement addBtn() {
		return getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_btnAdd']"));
	}
	
	public WebElement exportToExcelBtn() {
		return getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnExport']"));
	}
	
	public WebElement saveBtn() {
		return getDriver().findElement(By.xpath("//input[@value='Save']"));
	}
	
	public WebElement closeBtn() {
		return getDriver().findElement(By.xpath("//input[@id='btnClose']"));
	}

}
