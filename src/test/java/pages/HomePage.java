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
    
    @FindBy(id ="sp-cc-accept")
    WebElement acceptCookiesButton;
    

    /**
     * invoking super class constructor for driver initialisation.
     * 
     */   
    public HomePage(WebDriver driver) {
        super(driver);        
    }
    
    /**
     * 
     * This method clicks on the All menu.
     */  
        public void clickAllMenu() {
        allMenu.click();
    }
    
    /**
     * Navigates to ElectronicsAndComputers from the homepage.
     * This method clicks on the specified category link.
     */  
    
    public void navigateToElectronicsAndComputers() {	
    			        
        try {
            // Attempt to click the element
        	 waitForElementToBeClickable(electronicsComputersCategory,defaultTimeout).click();
        } catch (Exception e) {
            // Handle click interception with JavaScript click if normal click fails
        	scrollToEleWithJS(electronicsComputersCategory);
          	clickElementWithJS(electronicsComputersCategory);
        }
    }
    
    /**
     * Navigates to phonesAccessoriesCategory from the homepage.
     * This method clicks on the specified category link.
     */     

    public void navigateToPhonesAndAccessories() {
    	          
         try {
             // Attempt to click the element
        	 waitForElementToBeClickable(phonesAccessoriesCategory,defaultTimeout).click();
         } catch (Exception e) {
        	scrollToEleWithJS(phonesAccessoriesCategory);
           	clickElementWithJS(phonesAccessoriesCategory);
         }
        
    }

    /**
     * Handle cookie from the homepage.
     * This method clicks on the specified cookie btn.
     */ 
    public void clickAcceptCookies() {    	
    	waitForElementToBeClickable(acceptCookiesButton, defaultTimeout);
        if (acceptCookiesButton != null) {
            acceptCookiesButton.click();
        }
    }   
    
    
 
    
}