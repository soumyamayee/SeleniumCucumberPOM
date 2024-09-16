package pages;

import static org.junit.Assert.assertFalse;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

public class ProductPage extends BasePage {
	
       
    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    WebElement searchButton;

    @FindBy(xpath = "//span[text()='20 MP & above']")	
    WebElement cameraFilter;

    @FindBy(xpath = "//span[text()='2023']")
    WebElement modelYearFilter;

    @FindBy(xpath = "//input[contains(@id,'_slider-item_lower-bound')]")
    WebElement lowPriceFilter;

    @FindBy(xpath = "//input[contains(@id,'_slider-item_upper-bound')]")
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
    
    @FindBy(xpath ="//*[@aria-label='Minimum']//span[@class='a-size-base a-color-base a-text-bold']")
    WebElement priceRangeLower;
  
    @FindBy(xpath ="//*[@aria-label='Maximum']//span[@class='a-size-base a-color-base a-text-bold']")
    WebElement priceRangeUpper;
    
    @FindBy(xpath ="//span[text()='20 MP & above']/preceding-sibling::div//input")
    WebElement cameraFilterCheck;
    
    @FindBy(xpath ="//span[text()='2023']/preceding-sibling::div//input")
    WebElement ModelYearFilterCheck;
    
    @FindBy(xpath ="//span[@class='a-size-base-plus a-color-base']")
    List<WebElement> productTitles;
  
     
    /**
     * Constructor to initialize the WebDriver from super class
     */
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    
    SoftAssert softAssert = new SoftAssert();
    
    /**
     * Searches for a product using the search bar on the homepage.
     * @param product - The product name to search for (e.g., "Samsung phones").
     * This method enters the product name into the search box and submits the search.
     */
    public void searchForProduct(String productName) {
    	highlightElement(searchBox);
        searchBox.sendKeys(productName);
        highlightElement(searchButton);
        searchButton.click();
    }
    
    /**
     * Applies a filter by camera resolution on the product search results page.
     * This method clicks the camera resolution filter from the filter options.
     */
    public boolean applyCameraFilter() {
    	
    	highlightElement(cameraFilter);    	
    	 cameraFilter.click(); 
    	 try {    		 
    		 waitForElementToBevisible(cameraFilterCheck,defaultTimeout);
    	 }catch (Exception e){
    		 scrollToEleWithJS(cameraFilterCheck);
    		 highlightElement(cameraFilterCheck);
    	 }
    	if(cameraFilterCheck.isSelected()) {
    		return true;
    	}
		return false;
    }
    
    /**
     * Applies and verify a filter by model year on the product search results page.
     * This method clicks the model year filter option.
     */
    public boolean applyModelYearFilter() {
    	try {
            // Attempt to click the element
   		 waitForElementToBeClickable(modelYearFilter,defaultTimeout).click();
            //wait.until(ExpectedConditions.elementToBeClickable(modelYearFilter)).click();
        } catch (Exception e) {
            // Handle click interception with JavaScript click if normal click fails
       	 scrollToEleWithJS(modelYearFilter);
       	 clickElementWithJS(modelYearFilter);
       	 
        }    	
    	
    	//verify the filter selected
    	try {    		 
   		   waitForElementToBevisible(ModelYearFilterCheck,defaultTimeout);
   	    }catch (Exception e){
   		   scrollToEleWithJS(ModelYearFilterCheck);
   		   highlightElement(ModelYearFilterCheck);
   	    }    	
    	if(ModelYearFilterCheck.isSelected()) {
     		return true;
     	} return false;
    }
    
    /**
     * Verifies that Samsung phones are present in the search results.
     * This method checks the search results and verifies if they contain the expected "Samsung" product name.
     */
    public void verifyProductSearchResult() throws InterruptedException {
	    Thread.sleep(1000);
	    assertFalse("No products found", productTitles.isEmpty());
	    //verify product titles
	    for (WebElement ele : productTitles) {
	        String productTitle = ele.getText();	       
	        System.out.print(productTitle +",");
	        if(!productTitle.contains("Samsung")) {
	        	softAssert.assertEquals("Product does not contain 'Samsung'",productTitle.contains("Samsung"));  
	        }
	    }
    }
    
    /**
     * Applies a price range filter to the search results.
     * @param priceRange - The price range to filter by (e.g., "£120 - £150").
     * This method selects the price range filter for the product listings.
     */
     public void applyPriceFilter(String lowPrice, String highPrice) throws InterruptedException { 
            try {
                // Attempt to click the element
                wait.until(ExpectedConditions.visibilityOf(highPriceFilter));
                //WebElement highSlider = driver.findElement(By.id("p_36/range-slider_slider-item_upper-bound-slider"));
                String ariaValueText = highPriceFilter.getAttribute("aria-valuetext");
                System.out.println("aria-valuetext before dragging: " + ariaValueText);
                String valueToSet=priceRangeUpper.getText().replace("+", "");
                // Set the value using JavascriptExecutor
                JavascriptExecutor js = (JavascriptExecutor) driver;
                Thread.sleep(500);
                highlightElement(highPriceFilter);
                js.executeScript("arguments[0].click();", highPriceFilter);
                
                
                //js.executeScript("arguments[0].value = arguments[1];", highPriceFilter, highPrice); 
                //moveSlider(highPriceFilter,-200,0);
                //sendKeys(highPriceFilter,highPrice);
                System.out.println("aria-valuetext before click : " + ariaValueText);
                rightClickElement(goPriceFilter);                
                ariaValueText = highPriceFilter.getAttribute("aria-valuetext");
                System.out.println("aria-valuetext after click: " + ariaValueText);
               
                
            } catch (Exception e) {
               
				/*
				 * if (ariaValueText.equals("£300")) {
				 * System.out.println("Slider moved correctly to the expected value."); } else {
				 * System.out.println("Slider did not reach the expected value."); }
				 *///                int maxPrice =Integer.parseInt( priceRangeUpper.getText().replace("+", ""));
//              int minPrice = Integer.parseInt(priceRangeLower.getText().replace("+", ""));
//              System.out.println("maxPrice is  "+maxPrice);
//              System.out.println("minPrice is  "+minPrice);
//              int valueToSet=((maxPrice-minPrice)/100)*Integer.parseInt(highPrice.replace("£", ""));
//              System.out.println("valuetoset is  "+valueToSet);
              // Adjust this based on the actual mapping
             // String valueToSet = highPrice.replace(" ", "");

                
        }
        
     }
		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("arguments[0].style.display='block';", lowPriceFilter);
		 * lowPriceFilter.sendKeys(lowPrice);
		 * js.executeScript("arguments[0].value='"+highPrice+"';", lowPriceFilter);
		 * //highPriceFilter.sendKeys(highPrice);
		 * js.executeScript("arguments[0].click();", goPriceFilter);
		 * //goPriceFilter.click();
		 */    
    
    
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