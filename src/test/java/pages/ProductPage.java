package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {
	
       
    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    WebElement searchButton;

    @FindBy(xpath = "//span[text()='20 MP & above']")
    WebElement cameraFilter;

    @FindBy(xpath = "//span[text()='2023']")
    WebElement modelYearFilter;

    @FindBy(xpath = "//input[@name='low-price']")
    WebElement lowPriceFilter;

    @FindBy(xpath = "//input[@name='high-price']")
    WebElement highPriceFilter;

    @FindBy(xpath = "//input[@class='a-button-input']")
    WebElement goPriceFilter;

    @FindBy(xpath = "//span[text()='In Stock']")
    WebElement inStockFilter;

    @FindBy(xpath = "//span[text()='Sort by:']")
    WebElement sortByDropdown;

    @FindBy(xpath = "//a[text()='Price: Low to High']")
    WebElement sortByPriceLowToHigh;

    @FindBy(xpath = "//span[text()='Samsung']")
    WebElement brandFilter;
    
    @FindBy(xpath ="//span[text()='Electronics & Photo']")
    WebElement electronicsAndPhotoCategory;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void searchForProduct(String productName) {
        searchBox.sendKeys(productName);
        searchButton.click();
    }

    public void applyCameraFilter() {
    	
        cameraFilter.click();
    }

    public void applyModelYearFilter() {
    	 try {
             // Attempt to click the element
             wait.until(ExpectedConditions.elementToBeClickable(modelYearFilter)).click();
         } catch (Exception e) {
             // Handle click interception with JavaScript click if normal click fails
             JavascriptExecutor js = (JavascriptExecutor) driver;
             js.executeScript("arguments[0].scrollIntoView(true);", modelYearFilter);
             js.executeScript("arguments[0].click();", modelYearFilter);
         }
    }

    public void applyPriceFilter(String lowPrice, String highPrice) {
        
        try {
            // Attempt to click the element
            wait.until(ExpectedConditions.elementToBeClickable(lowPriceFilter));
        } catch (Exception e) {
            // Handle click interception with JavaScript click if normal click fails
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value='"+lowPrice+"';",lowPriceFilter);
            
            js.executeScript("arguments[0].value='"+highPrice+"';",highPriceFilter);
        }
        goPriceFilter.click();
    	
		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("arguments[0].style.display='block';", lowPriceFilter);
		 * lowPriceFilter.sendKeys(lowPrice);
		 * js.executeScript("arguments[0].value='"+highPrice+"';", lowPriceFilter);
		 * //highPriceFilter.sendKeys(highPrice);
		 * js.executeScript("arguments[0].click();", goPriceFilter);
		 * //goPriceFilter.click();
		 */    }

    public void applyInStockFilter() {
        inStockFilter.click();
    }

    public void sortByPriceLowToHigh() {
        sortByDropdown.click();
        sortByPriceLowToHigh.click();
    }

    public void applyBrandFilter() {
        brandFilter.click();
    }
    
    public void selectProductCatagory() {
    	electronicsAndPhotoCategory.click();
    }
    
   
}