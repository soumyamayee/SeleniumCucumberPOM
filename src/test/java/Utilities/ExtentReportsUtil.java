package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsUtil {
	
	public static void extentTest() {
		
		 ExtentReports extent;		
		 ExtentSparkReporter spark = new ExtentSparkReporter("C:/Users/UNIVERSAL MOBILES/SeeleniumCucumberProj/CucumberJava/reports/ExtentReport.html");
	     spark.config().setReportName("Automation Test Results");
	     spark.config().setDocumentTitle("Test Report");	
	     // Create ExtentReports object
	     extent = new ExtentReports();
	     extent.attachReporter(spark);
	
	     // Optional: Add some system/environment information to the report
	     extent.setSystemInfo("Tester", "Soumyamayee");
	     extent.setSystemInfo("Environment", "QA");
	     extent.setSystemInfo("Browser", "Chrome");
	}
}