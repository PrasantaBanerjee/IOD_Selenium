package com.iod.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.iod.main.Base;

public class Reports {

	static String static_tmsp = new SimpleDateFormat("EEE MM-dd-yyyy hh-mm-ss a").format(new Date());
	static PropertyFileReader config = new PropertyFileReader();
	public static ExtentReports extent;

	public static void init_Report() {
		extent = new ExtentReports();
		extent.setSystemInfo("Browser", config.getProperty("Browser"));
		extent.setSystemInfo("Region", config.getProperty("Region"));
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(new File(Base.reportPath));
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setDocumentTitle("IOD Automation Report");
		reporter.config().setReportName("IOD Automation Report");
		reporter.config().setEncoding("utf-8");
		extent.attachReporter(reporter);
	}

	public static void flushReport() {
		extent.flush();
	}

}
