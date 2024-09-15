package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{
	
    @FindBy(id = "nav-hamburger-menu") 
    WebElement allMenu;    
       
    @FindBy(xpath = "//div[text()= 'Electronics & Computers'][1]")
    WebElement electronicsComputersCategory;

    @FindBy(xpath ="//a[text()='Phones & Accessories']")
    WebElement phonesAccessoriesCategory;

	/*
	 * @FindBy(xpath = "//div[text()='Mobile Phones']") WebElement
	 * mobilePhonesCategory;
	 */
    
    public HomePage(WebDriver driver) {
        super(driver);        
    }

    public void clickAllMenu() {
        allMenu.click();
    }

    public void navigateToElectronicsAndComputers() {    	
    	
		/*
		 * scrollToElement(By.xpath("//div[text()= 'Electronics & Computers'][1]"));
		 * 
		 * electronicsComputersCategory.click();
		 */
        By locator = By.xpath("//div[text()= 'Electronics & Computers'][1]");
        scrollToElement(locator);
        //WebElement electronicsComputersCategory = driver.findElement(locator);
        
        try {
            // Attempt to click the element
            wait.until(ExpectedConditions.elementToBeClickable(electronicsComputersCategory)).click();
        } catch (Exception e) {
            // Handle click interception with JavaScript click if normal click fails
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", electronicsComputersCategory);
        }
    }

    public void navigateToPhonesAndAccessories() {
    	 By locator = By.xpath("//a[text()='Phones & Accessories']");
         scrollToElement(locator);
        // WebElement electronicsComputersCategory = driver.findElement(locator);
         
         try {
             // Attempt to click the element
             wait.until(ExpectedConditions.elementToBeClickable(phonesAccessoriesCategory)).click();
         } catch (Exception e) {
             // Handle click interception with JavaScript click if normal click fails
             JavascriptExecutor js = (JavascriptExecutor) driver;
             js.executeScript("arguments[0].click();", phonesAccessoriesCategory);
         }
        
    }

	/*
	 * public void navigateToMobilePhones() { mobilePhonesCategory.click(); }
	 */
    // Wait for the 'Accept Cookies' button to be clickable
    public void clickAcceptCookies() {
    	WebElement acceptCookiesButton = waitForElementToBeClickable(By.id("sp-cc-accept"), defaultTimeout);
        if (acceptCookiesButton != null) {
            acceptCookiesButton.click();
        }
    }
   
    
    /**
     * Generic wait method that waits for an element to meet a given condition.
     *
     * @param locator   The element locator (By.id, By.xpath, By.cssSelector, etc.)
     * @param condition The ExpectedCondition to wait for (e.g., clickable, visible, present, etc.)
     * @param timeout   Time in seconds to wait for the condition to be met
     * @return WebElement if found and condition is met, or null if not found within the timeout
     */
    public WebElement waitForCondition(By locator, ExpectedCondition<WebElement> condition, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(condition);
    }
    public WebElement waitForElementToBeClickable(By locator, long timeout) {
        return waitForCondition(locator, ExpectedConditions.elementToBeClickable(locator), timeout);
    }
    /**
     * Convenience method to wait until an element is visible.
     */
    public WebElement waitForElementToBeVisible(By locator, long timeout) {
        return waitForCondition(locator, ExpectedConditions.visibilityOfElementLocated(locator), timeout);
    }

    /**
     * Convenience method to wait until an element is present in the DOM.
     */
    public WebElement waitForElementToBePresent(By locator, long timeout) {
        return waitForCondition(locator, ExpectedConditions.presenceOfElementLocated(locator), timeout);
    }
 // Generic method to scroll to an element (using Actions)
    
    public void scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        
        
    }
    
}