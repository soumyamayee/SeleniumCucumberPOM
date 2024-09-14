package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.en.Then;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;

public class LoginSteps {
	
		WebDriver driver;
		 @Before
		 public void startTest(Scenario scenario) {
				/*
				 * ExtentReportListener.startReport();
				 * ExtentReportListener.createTest(scenario.getName());
				 */
		 }
		 @Given("user is on login page")
		 public void verifyLoginPage() {
				/*
				 * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
				 * driver.get("https://www.amazon.co.uk/");
				 */
		       
		 }

		 @When("user enters <username> and <password>")
		 public void user_enters_username_and_password() {
		     
		     
		 }

		 @When("clicks on login button")
		 public void clicks_on_login_button() {
		     
		     
		 }

		 @Then("user is navigated to the home page")
		 public void user_is_navigated_to_the_home_page() {
		     
		     
		 }
		 
		 
			/*
			 * @Given("I am on the Amazon homepage") public void
			 * i_am_on_the_Amazon_homepage() {
			 * 
			 * }
			 * 
			 * @When("I search for {string}") public void i_search_for(String searchTerm) {
			 * WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
			 * searchBox.sendKeys(searchTerm);
			 * driver.findElement(By.id("nav-search-submit-button")).click(); }
			 * 
			 * @Then("I should see results related to Samsung phones") public void
			 * i_should_see_results_related_to_Samsung_phones() { WebElement results =
			 * driver.findElement(By.xpath("//div[@class='s-main-slot']"));
			 * Assert.assertTrue(results.getText().contains("Samsung")); driver.quit(); }
			 * 
			 * @AfterStep public void takeScreenshotOnFailure() { byte[] screenshot =
			 * ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			 * //scenario.attach(screenshot, "image/png", "Screenshot on Failure"); }
			 */
	}


