package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductSearchPage {
    WebDriver driver;

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    WebElement searchButton;

    @FindBy(xpath = "//span[text()='20 MP and above']")
    WebElement cameraFilter;

    @FindBy(xpath = "//span[text()='2023']")
    WebElement modelYearFilter;

    @FindBy(xpath = "//input[@name='low-price']")
    WebElement lowPriceFilter;

    @FindBy(xpath = "//input[@name='high-price']")
    WebElement highPriceFilter;

    @FindBy(xpath = "//input[@aria-labelledby='a-autoid-1-announce']")
    WebElement goPriceFilter;

    @FindBy(xpath = "//span[text()='In Stock']")
    WebElement inStockFilter;

    @FindBy(xpath = "//span[text()='Sort by:']")
    WebElement sortByDropdown;

    @FindBy(xpath = "//a[text()='Price: Low to High']")
    WebElement sortByPriceLowToHigh;

    @FindBy(xpath = "//span[text()='Samsung']")
    WebElement brandFilter;

    public ProductSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchForProduct(String productName) {
        searchBox.sendKeys(productName);
        searchButton.click();
    }

    public void applyCameraFilter() {
        cameraFilter.click();
    }

    public void applyModelYearFilter() {
        modelYearFilter.click();
    }

    public void applyPriceFilter(String lowPrice, String highPrice) {
        lowPriceFilter.sendKeys(lowPrice);
        highPriceFilter.sendKeys(highPrice);
        goPriceFilter.click();
    }

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
   
}