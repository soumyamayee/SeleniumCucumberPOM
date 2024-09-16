package pages;

import static org.junit.Assert.assertFalse;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
  
    @FindBy(xpath ="//span[@class='a-button-text a-declarative']")
    WebElement sortByButton;
    
    @FindBy(xpath ="//div[@aria-hidden='false']//a[text()='Price: High to low']")
    WebElement optionHighToLowPrice;
  
    @FindBy(xpath ="//div[contains(@class,'template=SEARCH_RESULTS')]//span[@class='a-offscreen']")
    List<WebElement> productList;
    
    @FindBy(xpath ="(//div[contains(@class,'template=SEARCH_RESULTS')]//span[@class='a-offscreen'])[1]")
    WebElement firstProduct;
    
    @FindBy(xpath =" //span[@class='a-dropdown-label']//following-sibling::span")
    WebElement SelectedSortByOption;
 
    
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
	    Thread.sleep(500);
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
            
          String temp=highPrice.replaceAll("[^0-9]", "");
          int maxValue=Integer.parseInt(temp);
          
          temp = lowPrice.replaceAll("[^0-9]", "");
          int minPrice = Integer.parseInt(temp);
          setUpperSliderToTargetValue(maxValue);
        
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to apply price filter", e);
        }
    }

    
    public void setLowerSliderToTargetValue(int targetVal) throws InterruptedException { 
    	
        try {
	            // Wait for sliders to be visible
	            wait.until(ExpectedConditions.visibilityOf(highPriceFilter));
	            wait.until(ExpectedConditions.visibilityOf(lowPriceFilter));
	            
	            // Get current aria-valuetext values for both sliders
	            BigDecimal upperValue = getSliderValue(highPriceFilter);  // Aria-valuetext of the upper slider
	            BigDecimal lowerValue = getSliderValue(lowPriceFilter);   // Aria-valuetext of the lower slider
	
	            System.out.println("Current Upper Slider Value: " + upperValue);
	            System.out.println("Current Lower Slider Value: " + lowerValue);
	
	            // Parse the target value
	            BigDecimal targetValue = new BigDecimal(targetVal);
	            System.out.println("Target Value: " + targetValue);
	
	            // Validate that the target value is within the slider range
	            if (targetValue.compareTo(lowerValue) <= 0 || targetValue.compareTo(upperValue) > 0) {
	                throw new IllegalArgumentException("Target value must be greater than lower slider value and less than or equal to the upper slider value.");
	            }
	
	            // Initialize JavaScriptExecutor
	            JavascriptExecutor jslower = (JavascriptExecutor) driver;
	            Thread.sleep(500);
	            highlightElement(lowPriceFilter);
	
	            // Move the slider one step at a time
	            String previousValueText = lowPriceFilter.getAttribute("aria-valuetext");
	            String currentValueText = previousValueText;
	
	            int step = 1;  // Initial step size
	
	            while (true) {
	                String temp = currentValueText.replaceAll("[^0-9]", "");
	                int currValue = Integer.parseInt(temp);
	
	                System.out.println("Current Reached Value is: " + currValue);
	
	                
	                if(currValue >= (targetValue.intValue() - 30))
	                {
	                	//Slider Value close enough use smaller steps 
	                	step = 1;
	                }
	
	
	                if(currValue < targetValue.intValue()){
	                    // Move the slider by a small step to the left (negative x direction)
	                	System.out.println("Step size  is: " + step);
	                	jslower.executeScript("arguments[0].value += arguments[1]; arguments[0].dispatchEvent(new Event('change'));", lowPriceFilter, step);
	                }
	                else {
	                	break;
	                }
	
	                Thread.sleep(300);  // Short wait to allow the slider to update
	
	                // Fetch the new aria-valuetext after moving the slider
	                currentValueText = lowPriceFilter.getAttribute("aria-valuetext");
	                System.out.println("Current aria-valuetext: " + currentValueText);
	
	                // Check if the slider value stopped changing
	                if (currentValueText.equals(previousValueText)) {
	                    System.out.println("Slider stopped changing. Breaking the loop.");
	                    break;
	                }	                
	                previousValueText = currentValueText;
	
	                // Exit loop if the target value is reached or very close
	                if (Math.abs(targetValue.intValue() - currValue) <= step) {
	                    break;
	                }
	            }
	
	            // Optional: apply any additional actions after the slider is set
	            doubleClickElement(goPriceFilter);
	            Thread.sleep(500);
	
	            System.out.println("Slider set to target value or closest possible.");
	
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("Failed to set slider to target value", e);
	        }
    }
    
 
    public void setUpperSliderToTargetValue(int targetVal) throws InterruptedException { 
        try {
            // Wait for sliders to be visible
            wait.until(ExpectedConditions.visibilityOf(highPriceFilter));
            wait.until(ExpectedConditions.visibilityOf(lowPriceFilter));
            
            // Get current aria-valuetext values for both sliders
            BigDecimal upperValue = getSliderValue(highPriceFilter);  // Aria-valuetext of the upper slider
            BigDecimal lowerValue = getSliderValue(lowPriceFilter);   // Aria-valuetext of the lower slider

            System.out.println("Current Upper Slider Value: " + upperValue);
            System.out.println("Current Lower Slider Value: " + lowerValue);

            // Parse the target value
            BigDecimal targetValue = new BigDecimal(targetVal);
            System.out.println("Target Value: " + targetValue);

            // Validate that the target value is within the slider range
            if (targetValue.compareTo(lowerValue) <= 0 || targetValue.compareTo(upperValue) > 0) {
                throw new IllegalArgumentException("Target value must be greater than lower slider value and less than or equal to the upper slider value.");
            }

            // Initialize JavaScriptExecutor
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Thread.sleep(100);
            highlightElement(highPriceFilter);

            // Move the slider one step at a time
            String previousValueText = highPriceFilter.getAttribute("aria-valuetext");
            String currentValueText = previousValueText;

            int step = 5;  // Initial step size

            while (true) {
                String temp = currentValueText.replaceAll("[^0-9]", "");
                int currValue = Integer.parseInt(temp);

                System.out.println("Current Reached Value is: " + currValue);

                
                if(currValue <= (20 + targetValue.intValue()))
                {
                	//Slider Value close enough use smaller steps 
                	step = 1;
                }


                if(currValue > targetValue.intValue()){
                    // Move the slider by a small step to the left (negative x direction)
                    js.executeScript("arguments[0].value -= arguments[1]; arguments[0].dispatchEvent(new Event('change'));", highPriceFilter, step);
                }
                else {
                	break;
                }

               // Thread.sleep(300);  // Short wait to allow the slider to update

                // Fetch the new aria-valuetext after moving the slider
                currentValueText = highPriceFilter.getAttribute("aria-valuetext");
                System.out.println("Current aria-valuetext: " + currentValueText);

                // Check if the slider value stopped changing
                if (currentValueText.equals(previousValueText)) {
                    System.out.println("Slider stopped changing. Breaking the loop.");
                    break;
                }
                
                previousValueText = currentValueText;

                // Exit loop if the target value is reached or very close
                if (Math.abs(currValue - targetValue.intValue()) <= step) {
                    break;
                }
            }

            // Optional: apply any additional actions after the slider is set
            doubleClickElement(goPriceFilter);
            //Thread.sleep(500);

            System.out.println("Slider set to target value or closest possible.");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set slider to target value", e);
        }
    }
    
   
    
    private BigDecimal getSliderValue(WebElement slider) {
        // Get the aria-valuetext of the slider and convert it to BigDecimal
        String ariaValueText = slider.getAttribute("aria-valuetext");
        String numericString = ariaValueText.replaceAll("[^0-9]", "");
        return new BigDecimal(numericString);
    }
    
    
    public void applyInStockFilter() {
        inStockFilter.click();
    }

    public boolean sortByPrice(String sortOption) throws InterruptedException {
    	waitForElementToBeClickable(sortByDropdown,defaultTimeout).click();
        //sortByDropdown.click();
        if(sortOption.equalsIgnoreCase("high to low")) {
        	//Thread.sleep(500);
        	//waitForElementToBeClickable(optionHighToLowPrice,defaultTimeout).click();
        	clickElementWithJS(optionHighToLowPrice);
        	//Thread.sleep(500);
        	}else {
        	//sortByPriceLowToHigh.click();
        }
        waitForElementToBeClickable(SelectedSortByOption,defaultTimeout);
        String textOptionSelected=SelectedSortByOption.getText();
        System.out.println("sort product value : " + textOptionSelected);
       if(textOptionSelected.contains(sortOption)) {
    	   return true;
       }else {
       return false;}
    }
    
    public boolean verifyFirstPhonePrice(String price) {
    	//get price of first phone
    	price = price.replaceAll("[^0-9]", "");
    	int expectedPrice= Integer.parseInt(price);
    	System.out.println("expected price is : " + expectedPrice);
    	//scrollToEleWithJS(firstProduct);
    	//verify the filter selected
    	try {    		 
   		   waitForElementToBevisible(SelectedSortByOption,defaultTimeout);
   	    }catch (Exception e){
   		   scrollToEleWithJS(SelectedSortByOption);
   		   highlightElement(SelectedSortByOption);
   	    }
    	try {    		 
    		   waitForElementToBevisible(firstProduct,defaultTimeout);
	    }catch (Exception e){
		   scrollToEleWithJS(firstProduct);
		   highlightElement(firstProduct);
	    }
    	 JavascriptExecutor js = (JavascriptExecutor) driver;
         String text = (String) js.executeScript("return arguments[0].innerText;", firstProduct);
         text= text.replace("£", "").replace(",", "");
         float priceFloat = Float.parseFloat(text);
         int highPriceProduct = (int) priceFloat;
    	System.out.println("First product price is : " + text);    	
    	System.out.println("highPriceProduct is : " + highPriceProduct);
    	if(highPriceProduct < expectedPrice)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

    public void applyBrandFilter() {
        brandFilter.click();
    }
    
    public void selectProductCatagory() {
    	electronicsAndPhotoCategory.click();
    }
    
   
	
    //working with step increment = 1
//  public void setSliderToTargetValue(int targetVal) throws InterruptedException { 
//      try {
//          // Wait for sliders to be visible
//          wait.until(ExpectedConditions.visibilityOf(highPriceFilter));
//          wait.until(ExpectedConditions.visibilityOf(lowPriceFilter));
//          
//          // Get current aria-valuetext values for both sliders
//          BigDecimal upperValue = getSliderValue(highPriceFilter);  // Aria-valuetext of the upper slider
//          BigDecimal lowerValue = getSliderValue(lowPriceFilter);   // Aria-valuetext of the lower slider
//
//          System.out.println("Current Upper Slider Value: " + upperValue);
//          System.out.println("Current Lower Slider Value: " + lowerValue);
//
//          // Parse the target value
//          BigDecimal targetValue = new BigDecimal(targetVal);
//          System.out.println("Target Value: " + targetValue);
//
//          // Validate that the target value is within the slider range
//          if (targetValue.compareTo(lowerValue) <= 0 || targetValue.compareTo(upperValue) > 0) {
//              throw new IllegalArgumentException("Target value must be greater than lower slider value and less than or equal to the upper slider value.");
//          }
//
//          // Initialize JavaScriptExecutor
//          JavascriptExecutor js = (JavascriptExecutor) driver;
//          Thread.sleep(500);
//          highlightElement(highPriceFilter);
//
//          // Move the slider one step at a time
//          String previousValueText = highPriceFilter.getAttribute("aria-valuetext");
//          String currentValueText = previousValueText;
//
//          int step = 5;  // You can adjust this step value based on slider sensitivity
//
//          while (!currentValueText.equals("£" + targetVal)) {
//          	String temp = currentValueText.replaceAll("[^0-9]", "");
//          	int currValue = Integer.parseInt(temp);
//          	
//          	//System.out.println("Current Reached Value is : " + currentValueText);
//          	
//          	if(currValue < targetVal)
//          	{
//          		step = 1;
//                  // Move the slider by a small step to the left (negative x direction)
//                  js.executeScript("arguments[0].value += arguments[1]; arguments[0].dispatchEvent(new Event('change'));", highPriceFilter, step);
//                  Thread.sleep(300);  // Short wait to allow the slider to update
//          	}
//          	else 
//          	{
//                  // Move the slider by a small step to the left (negative x direction)
//                  js.executeScript("arguments[0].value -= arguments[1]; arguments[0].dispatchEvent(new Event('change'));", highPriceFilter, step);
//                  Thread.sleep(300);  // Short wait to allow the slider to update
//          	}
//
//              
//              // Fetch the new aria-valuetext after moving the slider
//              currentValueText = highPriceFilter.getAttribute("aria-valuetext");
//              System.out.println("Current aria-valuetext: " + currentValueText);
//
//              // Check if the slider value stopped changing
//              if (currentValueText.equals(previousValueText)) {
//                  System.out.println("Slider stopped changing. Breaking the loop.");
//                  break;
//              }
//              
//              previousValueText = currentValueText;
//
//              // Optionally, add a small delay to avoid overwhelming the system
//              Thread.sleep(200);
//          }
//
//          // Optional: apply any additional actions after the slider is set
//          doubleClickElement(goPriceFilter);
//          Thread.sleep(500);
//
//          System.out.println("Slider set to target value or closest possible.");
//
//      } catch (Exception e) {
//          e.printStackTrace();
//          throw new RuntimeException("Failed to set slider to target value", e);
//      }
//  }


  
//  public void setSliderToTargetValue(int targetVal) throws InterruptedException { 
//      try {
//          // Wait for sliders to be visible
//          wait.until(ExpectedConditions.visibilityOf(highPriceFilter));
//          wait.until(ExpectedConditions.visibilityOf(lowPriceFilter));
//          
//          // Get current aria-valuetext values for both sliders
//          BigDecimal upperValue = getSliderValue(highPriceFilter);  // Aria-valuetext of the upper slider
//          BigDecimal lowerValue = getSliderValue(lowPriceFilter);   // Aria-valuetext of the lower slider (assumed 0)
//
//          System.out.println("Current Upper Slider Value: " + upperValue);
//          System.out.println("Current Lower Slider Value: " + lowerValue);
//
//          // Parse the target value 150 (the value to set the upper slider to)
//          BigDecimal targetValue = new BigDecimal(targetVal);
//          System.out.println("Target Value: " + targetValue);
//
//          // Validate that the target value is within the slider range
//          if (targetValue.compareTo(lowerValue) <= 0 || targetValue.compareTo(upperValue) > 0) {
//              throw new IllegalArgumentException("Target value must be greater than lower slider value and less than or equal to the upper slider value.");
//          }
//
//          // Calculate position based on the current slider range
//          BigDecimal range = upperValue.subtract(lowerValue); // Full range of the slider
//          BigDecimal position = targetValue.subtract(lowerValue).divide(range, 10, RoundingMode.HALF_UP);
//          System.out.println("Calculated position: " + position);
//
//          // Get the slider width in pixels
//          int sliderWidth = highPriceFilter.getSize().getWidth();
//          System.out.println("Slider Width: " + sliderWidth);
//
//          // Calculate the offset in pixels to move the upper slider
//          BigDecimal offset = position.multiply(new BigDecimal(sliderWidth));
//          int xOffset = offset.intValue();
//          System.out.println("Calculated offset in pixels: " + xOffset);
//          
//          // Move the slider to the calculated offset
//          JavascriptExecutor js = (JavascriptExecutor) driver;
//          Thread.sleep(500);
//          highlightElement(highPriceFilter);
//          js.executeScript("arguments[0].click();", highPriceFilter);          
//          
//          js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change'));", highPriceFilter, xOffset);
//          Thread.sleep(500);
//          doubleClickElement(goPriceFilter);
//      
//      } catch (Exception e) {
//          e.printStackTrace();
//          throw new RuntimeException("Failed to set slider to target value", e);
//      }
//  }
  
	
   
}