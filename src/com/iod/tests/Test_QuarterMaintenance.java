package com.iod.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.iod.page_objects.Quarter_Maintenance;
import com.iod.utils.Reports;
import com.iod.utils.Screenshot;

public class Test_QuarterMaintenance extends Quarter_Maintenance{

	ExtentTest quarterMaintenance; 

	public void validateQ4Report() {
		ExtentTest q4Report = quarterMaintenance.createNode("Q4 Report.","Validate Q4 Report.");
		if(generateQ4ReportBtn().isEnabled()) {
			q4Report.log(Status.PASS, "Q4 Report Button is Enabled.");
			Screenshot.captureScreenshot(getDriver(), "QuarterMaintenance_");
		} else {
			q4Report.log(Status.FAIL, "Q4 Report Button is Disabled.");
			Screenshot.captureScreenshot(getDriver(), "QuarterMaintenance_");
		}
	}

	public void validateQ1Report() {
		ExtentTest q1Report = quarterMaintenance.createNode("Q1 Report.","Validate Q1 Report.");
		if(generateQ1ReportBtn().isEnabled()) {
			q1Report.log(Status.PASS, "Q1 Report Button is Enabled.");
			Screenshot.captureScreenshot(getDriver(), "QuarterMaintenance_");
		} else {
			q1Report.log(Status.FAIL, "Q1 Report Button is Disabled.");
			Screenshot.captureScreenshot(getDriver(), "QuarterMaintenance_");
		}
	}

	public void validate_QuarterMaintenancePage() {
		quarterMaintenance = Reports.extent.createTest("Quarter Maintenance","Validate Test Scenarios for Quarter Maintenance page.");
		quarterMaintenanceTab().click();
		validateQ4Report();
		validateQ1Report();

	}
}
