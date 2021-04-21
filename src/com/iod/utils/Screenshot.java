package com.iod.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.iod.main.Base;

public class Screenshot extends Base {

	static String static_tmsp = new SimpleDateFormat("EEE MM-dd-yyyy hh-mm-ss a").format(new Date());

	public static String captureScreenshot(WebDriver driver, String screenshotname) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		String timestamp = new SimpleDateFormat("EEE MM-dd-yyyy hh-mm-ss a").format(new Date());
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = Base.screenshotPath + "\\" + screenshotname + " " + timestamp + ".png";
		File target = new File(destination);
		try {
			FileUtils.copyFile(source, target);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destination;
	}
}
