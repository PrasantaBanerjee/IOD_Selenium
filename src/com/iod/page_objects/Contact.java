package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iod.main.Base;

public class Contact extends Base {
	public static WebElement contact() {
		return getDriver().findElement(By.xpath("//a[text()='Contact']"));
	}

	public static WebElement firmName() {
		return getDriver().findElement(By.xpath("//td[@class='Label']//select"));
	}

	public static WebElement exportToExcel() {
		return getDriver().findElement(By.xpath("//input[@value='Export to Excel']"));
	}

	public static WebElement Add_Contact() {
		return getDriver().findElement(By.xpath("//*[@id=\'ctl00_ContentPlaceHolderMain_btnAdd\']"));
	}

	public static WebElement FirstName() {
		return getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$txtFirstName']"));
	}

	public static WebElement LastName() {
		return getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$txtLastName']"));
	}

	public static WebElement WorkPhone() {
		return getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$txtWork']"));
	}

	public static WebElement Radiobtn() {
		return getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_rbWork']"));
	}

	public static WebElement Email() {
		return getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$txtGroup']"));
	}

	public static WebElement Radiobtn1() {
		return getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_rbGroup']"));
	}

	public static WebElement Address1() {
		return getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$txtLAddress1']"));
	}

	public static WebElement City() {
		return getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$txtLCity']"));
	}

	public static WebElement Zip() {
		return getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$txtLZipCode']"));
	}

	public static WebElement Sameaddress() {
		return getDriver().findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolderMain_chkSameAddress']"));
	}

	public static WebElement Category() {
		//return getDriver().findElement(By.xpath("//option[@value='A2471']"));
		return getDriver().findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolderMain$mslbContactCategory$lstAvailable']/option[1]"));
	}

	public static WebElement Rtbtn() {
		return getDriver().findElement(By.xpath("//input[@title='Add selected item(s)']"));
	}

	public static WebElement AddComment() {
		return getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnAddComment']"));
	}

	public static WebElement TextComment() {
		return getDriver().findElement(By.xpath("//textarea[@id='ctl00_ContentPlaceHolderMain_wdwComment_tmpl_txtComment']"));
	}

	public static WebElement SaveComment() {
		return getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$wdwComment$tmpl$btnSaveComment']"));
	}

	public static WebElement SaveButton() {
		return getDriver().findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolderMain$btnSave']"));
	}

	public static WebElement Close() {
		return getDriver().findElement(By.xpath("//input[@id='btnClose']"));
	}
}
