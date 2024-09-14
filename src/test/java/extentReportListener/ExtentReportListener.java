/*
 * package ExtentReportListener; import
 * com.aventstack.extentreports.ExtentReports; import
 * com.aventstack.extentreports.ExtentTest; import
 * com.aventstack.extentreports.reporter.ExtentHtmlReporter;
 * 
 * public class ExtentReportListener {
 * 
 * 
 * 
 * private static ExtentReports extent; private static ExtentTest test; private
 * static ExtentHtmlReporter htmlReporter;
 * 
 * public static void startReport() { htmlReporter = new
 * ExtentHtmlReporter("target/extent-report.html"); extent = new
 * ExtentReports(); extent.attachReporter(htmlReporter); }
 * 
 * public static void endReport() { extent.flush(); }
 * 
 * public static ExtentTest createTest(String testName) { return
 * extent.createTest(testName); }
 * 
 * }
 */
package extentReportListener;

