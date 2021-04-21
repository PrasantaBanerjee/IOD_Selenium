package com.iod.commons;

import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.iod.main.Base;

public class CommonFunctions extends Base {

	CommonPageObjects cpo = new CommonPageObjects();

	public void changeDropdownStatusTo(String status) {
		Select statusDropdown = new Select(cpo.statusDropdown());
		statusDropdown.selectByVisibleText(status);
	}

	public void clickExportToExcelBtn() {
		cpo.exportToExcelBtn().click();
	}
	
	public int getRandomNumberUsingNextInt(int userMin, int userMax) {
	    Random random = new Random();
	    int finalValue = (random.nextInt(userMax - userMin) + userMin + 3) + (random.nextInt(userMax - userMin) + userMin + 1); 
	    return finalValue;
	}
	
	public void highlightElement(WebElement newElementAdded) {
		Actions act = new Actions(getDriver());
		act.moveToElement(newElementAdded);
		JavascriptExecutor js = (JavascriptExecutor)getDriver();
		js.executeScript("arguments[0].setAttribute('style', 'background: white; border: 3px solid blue;');", newElementAdded);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

}
