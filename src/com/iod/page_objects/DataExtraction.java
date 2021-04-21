package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iod.main.Base;

public class DataExtraction extends Base
{
	public static WebElement dataExtraction() {
		return getDriver().findElement(By.xpath("//a[@id='ctl00_tvMenut18']"));
	}

	public static WebElement contactType() {
		return getDriver().findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolderMain$ddlContactType']"));	 
	}
	
	public static WebElement selection() {
		return getDriver().findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolderMain$ddlSelection']"));
	}
	
	public static WebElement firmType() {
		return getDriver().findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolderMain$ddlFirmType']"));
	}
	
	public static WebElement firmStatus() {
		return getDriver().findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolderMain$ddlFirmStatus']"));
	}
	
	public static WebElement buttonAllRight() {
		return getDriver().findElement(By.xpath("//input[@value='>>']"));
	}
	
	public static WebElement extract() {
		return getDriver().findElement(By.xpath("//input[@value='Extract']"));
	}
	
	public static WebElement exportToExcel() {
		return getDriver().findElement(By.xpath("//input[@value='Export to Excel']"));
	}
	
	public static WebElement cancel() {
		return getDriver().findElement(By.xpath("//input[@value='Cancel']"));
	}
	
	public static WebElement reset() {
		return getDriver().findElement(By.xpath("//input[@value='Reset']"));
	}
}
