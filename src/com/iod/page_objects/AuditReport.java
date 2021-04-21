package com.iod.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iod.main.Base;

public class AuditReport extends Base {
	
	public WebElement auditReportTab() {
		return getDriver().findElement(By.xpath("//a[@title='Audit Report']"));
	}
	
}
