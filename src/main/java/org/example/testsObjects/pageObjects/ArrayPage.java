package org.example.testsObjects.pageObjects;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ArrayPage {
	
    WebDriver driver;
    
    By arrayTopicGetStartedPath = By.xpath("//a[@href='array']");
    By errorPath = By.cssSelector("[class*='alert']");
    By arraysInPythonTopicPath = By.xpath("//a[text()='Arrays in Python']");
    By arraysUsingLinkedListTopicPath = By.xpath("//a[text()='Arrays Using List']");
    By basicOpenrationsInListTopicPath = By.xpath("//a[text()='Basic Operations in Lists']");
    By applicationsOfArrayTopicPath = By.xpath("//a[text()='Applications of Array']");
    By tryHerePath = By.xpath("//a[@href='/tryEditor']");
    By runBtnPath = By.xpath("//button[@type='button']");
    By textEditorPath = By.xpath("//div[@class='CodeMirror-code']");
    By pythonCodeOutputPath = By.xpath("//pre[@id='output']");
    
    WebElement arrayGetStartedBtn ;
    WebElement error ;
    WebElement arraysInPythonLink;
    WebElement arraysUsingListLink;
    WebElement basicOperationListLink;
    WebElement applicationOfArrLink;
    WebElement tryHereBtn;
    WebElement runBtn;
    WebElement textEditorBox ;
    WebElement pythonCodeOutput;
    
    public ArrayPage(WebDriver driver) {
	  this.driver = driver;
	}


	// This function waits for given element and returns it
	public WebElement waitAndGetElement(By element) {
	    WebElement testElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
	    if (testElement.isDisplayed()) {
	      return testElement; 
	    }
	    else {
	    	  return null;
	    }
    }
	
	public boolean onArrayTopicsPage() {
		arraysInPythonLink = waitAndGetElement(arraysInPythonTopicPath);
		if (arraysInPythonLink != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getTopicPageTitle(String topic) {
		// sample xpath => //a[text()='Arrays in Python']
		String xpathStr = "//a[text()='" + topic + "']";
		System.out.println("Xpath for topic: " + xpathStr);
		By element = By.xpath(xpathStr);
		WebElement tempElement = waitAndGetElement(element);
	    if (tempElement != null) {
	    	return tempElement.getText();
	    }
		return "";
	}
	
	public void clickArrayTopic(String topicLinkStr) {
		scrollToElementAndClick(topicLinkStr);
	}
	
	public void clickGetStarted() {
		arrayGetStartedBtn = waitAndGetElement(arrayTopicGetStartedPath);
		arrayGetStartedBtn.click();
	}
	
	public void clickRunBtn() {
		runBtn = waitAndGetElement(runBtnPath);
		runBtn.click();
	}
	
	public String getPythonCodeOutput() {
		pythonCodeOutput = waitAndGetElement(pythonCodeOutputPath);
		return pythonCodeOutput.getText();
	}
	
	public String getPythonCodeErrorOutput() {
        Alert alert = driver.switchTo().alert();
		String message = alert.getText();
		// press ok button on alert
		alert.accept();
		return message;
	}
	
	public void updateTextEditorAndClickRun(String codeType) {
		String command = "print";
		if (codeType.contains("CodeError")) {
			command = "Print";
		}
		// get text editor element
		textEditorBox = waitAndGetElement(textEditorPath);
		
        Actions action = new Actions(driver);
        JavascriptExecutor js2 = (JavascriptExecutor)driver;
        js2.executeScript("arguments[0].textContent ='print'",textEditorBox);
        
        // new Actions(driver).keyDown(Keys.ENTER).sendKeys("Print").sendKeys(Keys.SPACE).sendKeys("\"Hello\"").sendKeys(Keys.ENTER).perform();
        action.keyDown(Keys.ENTER).sendKeys(command).sendKeys(Keys.SPACE).sendKeys("\"Hello\"").sendKeys(Keys.ENTER).perform();
	}

	public String getErrorMessage() {
		error = waitAndGetElement(errorPath);
		String message = error.getText();
		return message;
	}	
	
	// This function waits for element to be clickable and return once element is available
	// This is useful function to avoid Thread.sleep in the code
	public boolean waitForElementDisplayed(By element) {
	  WebElement testElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
	  if (testElement.isDisplayed()) {
	    return true;
	  }    	
		return false;
	}	
	
	
	// finds the element and scrolls to element using java script scroll into view
    public void scrollToElementAndClick(String str){
    	By tempElement = null;
    	if (str.contains("tryHere")) {
    		tempElement = tryHerePath;
    	}
    	else if (str.contains("Arrays in Python")) {
    		tempElement = arraysInPythonTopicPath;			
		}
		else if (str.contains("Arrays Using List")) {
			tempElement = arraysUsingLinkedListTopicPath;
		}
		else if (str.contains("Basic Operations in Lists")) {
			tempElement = basicOpenrationsInListTopicPath;
		}
		else if (str.contains("Applications of Array")) {
			tempElement = applicationsOfArrayTopicPath;
		}    	
    	
        WebElement testElement = driver.findElement(tempElement);                
        JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView()", testElement); 
        testElement.click();        
    }

}
