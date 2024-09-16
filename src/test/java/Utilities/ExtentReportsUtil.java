package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsUtil {
	

public static class ExtentReportManager {
    private static ExtentReports extentReports;
    private static ExtentSparkReporter sparkReporter;

    public static  ExtentReports getInstance() {
        if (extentReports == null) {
            extentReports = new ExtentReports();
            sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/reports/ExtentReport.html");
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
            extentReports.setSystemInfo("Browser", "Chrome");
        }
        return extentReports;
    }

    public static void closeReport() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
}