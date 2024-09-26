package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	protected WebDriver driver;
    protected WebDriverWait wait;
	protected Actions actions;
	protected long defaultTimeout = 10;
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
    public WebDriver getDriver() {
        return driver;
    } 

    // JavaScript click for hidden elements
    public void clickElementWithJS(WebElement ele) {
    	try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].click();", ele);
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    // JavaScript sendkeys for hidden elements
    public void sendTextWithJS(WebElement ele, String text) {        
        try {
        	JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value='"+text+"';", ele);
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    // JavaScript scrollto elements
    public void scrollToEleWithJS(WebElement ele) {
    	try {
    		 JavascriptExecutor js = (JavascriptExecutor) driver;
    	     js.executeScript("arguments[0].scrollIntoView(true);", ele);
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
       
    }
	 // JavaScript get innnertext elements
	    public String getInnerTextWithJS(WebElement ele) {
	    	String text = "";
	    	try {
	    		JavascriptExecutor js = (JavascriptExecutor) driver;
	  	       text= (String) js.executeScript("return arguments[0].innerText;", ele);
	  	      return text;
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
			return text;
	        
	    }
    
    
    // Generic explicit wait method for element to be clickable
    public WebElement waitForElementToBeClickable(WebElement ele, Long timeoutInSeconds) {    	
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(ele));
    }
	 // Generic explicit wait method for visibility of element in the page
	    public WebElement waitForElementToBevisible(WebElement ele, Long timeoutInSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        return wait.until(ExpectedConditions.visibilityOf(ele));
	    }
	 // Generic explicit wait method for text to be present in a specific element
	    public boolean waitForTextToBePresentInElement(By locator, String text, Long timeoutInSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	    }
	    
	    /**
	     * Highlights a given WebElement by changing its background color.
	     * @param element - The WebElement to highlight.
	     */
	    public void highlightElement(WebElement element) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;	        
	        // Save the original style of the element
	        String originalStyle = element.getAttribute("style");
	        // Set the new style to highlight the element
	        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 2px solid red;");
	        // Pause for a short time so that the highlight is visible
	        try {
	            Thread.sleep(500); // Highlight is visible for half a second
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    /**
	     * Performs a click on the given element.
	     * @param element - The WebElement to be clicked.
	     */
	    public void clickElement(WebElement element) {
	    	try {
	    		 actions.moveToElement(element).click().build().perform();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	       
	    }

	    /**
	     * Performs a right-click (context click) on the given element.
	     * @param element - The WebElement to right-click on.
	     */
	    public void rightClickElement(WebElement element) {
	    	try {
	    		 actions.contextClick(element).build().perform();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	       
	    }

	    /**
	     * Performs a double-click on the given element.
	     * @param element - The WebElement to double-click on.
	     */
	    public void doubleClickElement(WebElement element) {
	    	try {
	    		actions.doubleClick(element).build().perform();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}	        
	    }

	    /**
	     * Performs a click-and-hold action on the given element.
	     * @param element - The WebElement to click and hold.
	     */
	    public void clickAndHoldElement(WebElement element) {
	    	try {
	    		actions.clickAndHold(element).build().perform();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	        
	    }

	    /**
	     * Performs a drag-and-drop operation from the source element to the target element.
	     * @param source - The WebElement to drag from.
	     * @param target - The WebElement to drop to.
	     */
	    public void dragAndDrop(WebElement source, WebElement target) {
	    	try {
	    		 actions.dragAndDrop(source, target).build().perform();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	       
	    }

	    /**
	     * Moves the mouse to the given element and performs a hover action.
	     * @param element - The WebElement to hover over.
	     */
	    public void hoverOverElement(WebElement element) {
	    	try {
	    		actions.moveToElement(element).build().perform();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	        
	    }

	    /**
	     * Moves the slider to a specific offset.
	     * @param slider - The WebElement representing the slider.
	     * @param xOffset - The horizontal offset to move the slider.
	     * @param yOffset - The vertical offset to move the slider (usually 0 for horizontal sliders).
	     */
	    public void moveSlider(WebElement slider, int xOffset, int yOffset) {
	        actions.clickAndHold(slider).moveByOffset(xOffset, yOffset).release().build().perform();
	    }

	    /**
	     * Sends a series of keys to the specified element.
	     * @param element - The WebElement to send keys to.
	     * @param keys - The keys to send (e.g., Keys.ENTER).
	     */
	    public void sendKeys(WebElement element, String keys) {
	    	try {
	    		actions.moveToElement(element).sendKeys(element, keys).build().perform();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	        
	    }
	    
	    public static double findSliderPosition(double value, double minValue, double maxValue) {
	        if (value <= minValue || value >= maxValue) {
	            throw new IllegalArgumentException("Value must be more than £98 and less than £740");
	        }
	        // Interpolating the position of the slider
	        double position = (value - minValue) / (maxValue - minValue);
	        // Returning the slider position as a percentage
	        return position * 100;
	    }
	    
	    /**
	     * Performes click operation on the specified element.
	     * @param element - The WebElement to perform click.
	     */
	    public void click(WebElement element) {	    
	    	try {
	    		highlightElement(element);   
	        	waitForElementToBeClickable(element,defaultTimeout).click();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	        	
	        	
	        }
	    
	    /**
	     * verify if element is selected.
	     * @param element - The WebElement to perform click.
	     */
	    public boolean verifyElementIsSelected(WebElement element) {	  
	    	try {    		 
	   		   waitForElementToBevisible(element,defaultTimeout);
	   	    }catch (Exception e){
	   		   scrollToEleWithJS(element);
	   		   highlightElement(element);
	   	    }    	
	    	if(element.isSelected()) {
	     		return true;
	     	} return false;
	    }
	    /* wait
	     * 
	     */
	    public void waitFor() {
	    	try {
	    		Thread.sleep(500);
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	        
	    }

}

    

