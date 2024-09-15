package Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import pages.BasePage;

public class ScreenShots extends BasePage {
	
	
	  public ScreenShots(WebDriver driver) { super(driver); }
	 

	public static String takeScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destination = "C:/Users/UNIVERSAL MOBILES/SeeleniumCucumberProj/CucumberJava/screenshots/" + screenshotName + timestamp + ".png";
        try {
            FileUtils.copyFile(srcFile, new File(destination));
            System.out.println("Screenshot taken: " + destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }
}
