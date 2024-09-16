package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Utilities.ScreenShots;
import pages.BasePage;
import pages.HomePage;
import pages.ProductPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductSearchSteps {
	

	WebDriver driver;
    HomePage homePage;
    ProductPage productSearchPage;
    private static final Logger logger = LogManager.getLogger(ProductSearchSteps.class);
    SoftAssert softAssert = new SoftAssert();
    @Before
    public void setUp() {
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }  
    @After
    public void tearDown(io.cucumber.java.Scenario scenario) {
        if (scenario.isFailed()) {
            // Capture screenshot on failure
        	ScreenShots.takeScreenshot(driver,scenario.getName());
        }		
		/*
		 * if (driver != null){ driver.quit(); }
		 */		 
    }
    
	@Given("user on the Amazon UK homepage")
	public void userOnAmazonHomePage() {
        driver.get("https://www.amazon.co.uk");	
        Assert.assertTrue(driver.getTitle().contains("Amazon.co.uk"), "The page title did not match!");
        homePage=new HomePage(driver);
        homePage.clickAcceptCookies();
        Assert.assertTrue(driver.getTitle().contains("Electronics"), "Failed to navigate to Electronics category");
        logger.info("Navigated to Amazon UK homepage.");        
	}

	@When("user navigate to the {string} category")
	public void navigateToCategory(String category) throws InterruptedException {
		 homePage.clickAllMenu();
	     homePage.navigateToElectronicsAndComputers();
	     Assert.assertTrue(driver.getTitle().contains("Electronics"), "Failed to navigate to Electronics category");
	     logger.info("User Navigated to Electronics and Computers category."); 
	}

	@When("user select {string}")
	public void selectSubCategory(String subCategory) {
		if (subCategory.equals("Phones and Accessories")) {
			// Select the 'Phones and Accessories' sub-category
            homePage.navigateToPhonesAndAccessories();
            Assert.assertTrue(driver.getTitle().contains("Phones"), "Failed to navigate to Phones & Accessories");
            logger.info("User Navigated to Phones and Accessories"); 
        } else if (subCategory.equals("Electronics & Photo")) {
        	productSearchPage.selectProductCatagory(); 
        	logger.info("User Navigated to Electronics & Photo"); 
        }
	    
	}

	@When("user search for {string}")
	public void searchForProduct(String product) throws InterruptedException {
		// Search for 'Samsung phones' in the search bar
		productSearchPage = new ProductPage(driver) ;
		productSearchPage.searchForProduct(product);
		Thread.sleep(500);
		productSearchPage.verifyProductSearchResult();
		
	}

	@When("user apply the filter {string}")
	public void applyFilter(String filter) {
		  // Apply the 'Camera Resolution 20 MP and above' filter
		if (filter.equals("Camera Resolution 20 MP and above")) {
            Boolean isApplied =productSearchPage.applyCameraFilter();
            Assert.assertTrue(isApplied, "Failed to apply Camera Resolution filter");
        } else if (filter.equals("Model Year 2023")) {
        	// Apply the 'Model Year 2023' filter
            Boolean isApplied =productSearchPage.applyModelYearFilter();
            Assert.assertTrue(isApplied, "Failed to apply Model year filter");
        }
	    
	}

	@When("user apply the price range filter {string}")
	public void applyPriceRangeFilter(String priceRange) throws InterruptedException {
		 // Apply the price range filter '£120 - £150'
		String[] prices = priceRange.split(" - ");
        productSearchPage.applyPriceFilter(prices[0], prices[1]);        
        //Assert.assertEquals("true", "Price range selected");
	}

	@Then("user should see a list of Samsung phones that match the specifications")
	public void verifySamsungPhonesInResults() throws InterruptedException {
		Thread.sleep(500);
		Thread.sleep(500);
		productSearchPage.verifyProductSearchResult();
		   
	}
	

	
	
	
	
	
	//work in progress codes
	
	@Given("user have searched for Samsung phones with Camera Resolution {int} MP and above, Model Year {int}, Price Range £{int} {double} £{int}")
	public void user_have_searched_for_samsung_phones_with_camera_resolution_mp_and_above_model_year_price_range_£_£(Integer int1, Integer int2, Integer int3, Double double1, Integer int4) {
	    
	    
	}
	@Then("user should see a message indicating {string}")
	public void user_should_see_a_message_indicating(String message) {
		// Verification logic for no results found
        System.out.println(message);
	}
	@Given("user click on one of the listed Samsung phones")
	public void user_click_on_one_of_the_listed_samsung_phones() {
	    
	    
	}

	@Then("user should be taken to the product details page")
	public void user_should_be_taken_to_the_product_details_page() {
	    
	    
	}

	@Then("the page should display a camera resolution of at least {int} MP")
	public void the_page_should_display_a_camera_resolution_of_at_least_mp(Integer int1) {
	    
	    
	}

	@Then("the page should show that the model year is {int}")
	public void the_page_should_show_that_the_model_year_is(Integer int1) {
	    
	    
	}

	@Then("the price should be between £{int} {double} £{int}")
	public void the_price_should_be_between_£_£(Integer int1, Double double1, Integer int2) {
	    
	    
	}

	@Given("user have applied filters for Camera Resolution {int} MP and above, Model Year {int}, Price Range £{int} {double} £{int}")
	public void user_have_applied_filters_for_camera_resolution_mp_and_above_model_year_price_range_£_£(Integer int1, Integer int2, Integer int3, Double double1, Integer int4) {
	    
	    
	}

	@Given("user filter the results by availability {string}")
	public void user_filter_the_results_by_availability(String availability) {
		if (availability.equals("In Stock")) {
            productSearchPage.applyInStockFilter();
	    
	}}

	@Then("user should see only the Samsung phones that are available for immediate purchase")
	public void user_should_see_only_the_samsung_phones_that_are_available_for_immediate_purchase() {
	    
	    
	}

	@Given("user have filtered the search results to show Samsung phones with Camera Resolution {int} MP and above, Model Year {int}, Price Range £{int} {double} £{int}")
	public void user_have_filtered_the_search_results_to_show_samsung_phones_with_camera_resolution_mp_and_above_model_year_price_range_£_£(Integer int1, Integer int2, Integer int3, Double double1, Integer int4) {
	    
	    
	}

	@When("user sort the results by price {string}")
	public void user_sort_the_results_by_price(String sortOrder) {
		 if (sortOrder.equals("low to high")) {
	            productSearchPage.sortByPriceLowToHigh();
	        }
	    
	}

	@Then("the search results should be sorted by price in ascending order")
	public void the_search_results_should_be_sorted_by_price_in_ascending_order() {
		// Verification logic here
        System.out.println("Search results sorted by price.");
        driver.quit();
	    
	}

	@When("the results include phones from other brands")
	public void the_results_include_phones_from_other_brands() {
	    
	    
	}

	@Then("user should apply the brand filter {string}")
	public void user_should_apply_the_brand_filter(String string) {
	    
	    
	}

	@Then("the search results should only display Samsung phones")
	public void the_search_results_should_only_display_samsung_phones() {
		productSearchPage.applyBrandFilter();
        System.out.println("Only Samsung phones are displayed.");
        driver.quit();
	    
	}



}
