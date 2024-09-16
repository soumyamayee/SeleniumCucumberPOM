package Utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import pages.BasePage;

public class TestFailureListener implements ITestListener{
	 @Override
	    public void onTestFailure(ITestResult result) {
	        Object testClass = result.getInstance();
	        WebDriver driver = ((BasePage) testClass).getDriver();
	        
	        if (driver != null) {
	            ScreenShots.takeScreenshot(driver, result.getName());
	        }
	    }

	    @Override
	    public void onTestStart(ITestResult result) {}
	    @Override
	    public void onTestSuccess(ITestResult result) {}
	    @Override
	    public void onTestSkipped(ITestResult result) {}
	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
	    @Override
	    public void onStart(ITestContext context) {}
	    @Override
	    public void onFinish(ITestContext context) {}
}
