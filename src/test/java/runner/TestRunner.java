package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
   features = "src/test/resources/Features",
   glue = {"stepDefinitions"},
   plugin = { "pretty", "html:reports/cucumber-reports.html" },
   monochrome = true,
   tags = "@TestCase1 or @TestCase2" 
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
