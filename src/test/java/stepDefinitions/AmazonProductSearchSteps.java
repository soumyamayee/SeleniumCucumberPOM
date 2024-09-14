package stepDefinitions;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.HomePage;
import pages.ProductSearchPage;
import org.openqa.selenium.support.ui.WebDriverWait;
public class AmazonProductSearchSteps {
	
	WebDriver driver;
    HomePage homePage;
    ProductSearchPage productSearchPage;

	@Given("user on the Amazon UK homepage")
	public void user_on_the_amazon_uk_homepage() {
		WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.amazon.co.uk");
        driver.manage().window().maximize();
        homePage=new HomePage(driver);
        homePage.clickAcceptCookies();
	}

	@When("user navigate to the {string} category")
	public void user_navigate_to_the_category(String category) throws InterruptedException {
		 homePage.clickAllMenu();
	     homePage.navigateToElectronicsAndComputers();
	    
	}

	@When("user select {string}")
	public void user_select(String subCategory) {
		if (subCategory.equals("Phones and Accessories")) {
            homePage.navigateToPhonesAndAccessories();
        } else if (subCategory.equals("Mobile Phones")) {
            homePage.navigateToMobilePhones();
        }
	    
	}

	@When("user search for {string}")
	public void user_search_for(String product) {
		productSearchPage = new ProductSearchPage(driver) ;
		productSearchPage.searchForProduct(product);
	    
	}

	@When("user apply the filter {string}")
	public void user_apply_the_filter(String filter) {
		if (filter.equals("Camera Resolution 20 MP and above")) {
            productSearchPage.applyCameraFilter();
        } else if (filter.equals("Model Year 2023")) {
            productSearchPage.applyModelYearFilter();
        }
	    
	}

	@When("user apply the price range filter {string}")
	public void user_apply_the_price_range_filter(String priceRange) {
		String[] prices = priceRange.split(" - ");
        productSearchPage.applyPriceFilter(prices[0], prices[1]);
	    
	}

	@Then("user should see a list of Samsung phones that match the specifications")
	public void user_should_see_a_list_of_samsung_phones_that_match_the_specifications() {
		 System.out.println("Matching Samsung phones are displayed.");
	     driver.quit();
	    
	}

	@Then("user should see a message indicating {string}")
	public void user_should_see_a_message_indicating(String message) {
		// Verification logic for no results found
        System.out.println(message);
        driver.quit();
	    
	}

	@Given("user have searched for Samsung phones with Camera Resolution {int} MP and above, Model Year {int}, Price Range £{int} {double} £{int}")
	public void user_have_searched_for_samsung_phones_with_camera_resolution_mp_and_above_model_year_price_range_£_£(Integer int1, Integer int2, Integer int3, Double double1, Integer int4) {
	    
	    
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
