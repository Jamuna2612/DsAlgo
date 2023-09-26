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

public class GraphPage {
	
    WebDriver driver;
    
    By graphTopicGetStartedPath = By.xpath("//a[@href='graph']");
    By errorPath = By.cssSelector("[class*='alert']");
    By graphTopicPath = By.xpath("//a[@href='graph']");
    By graphRepresentationsTopicPath = By.xpath("//a[text()='Graph Representations']");
    By tryHerePath = By.xpath("//a[@href='/tryEditor']");
    By runBtnPath = By.xpath("//button[@type='button']");
    By textEditorPath = By.xpath("//div[@class='CodeMirror-code']");
    By pythonCodeOutputPath = By.xpath("//pre[@id='output']");
    
    WebElement graphGetStartedBtn ;
    WebElement error ;
    WebElement graphTopicLink;
    WebElement graphRepresentationsTopicLink;
    WebElement tryHereBtn;
    WebElement runBtn;
    WebElement textEditorBox ;
    WebElement pythonCodeOutput;
    
    public GraphPage(WebDriver driver) {
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
	
	public boolean onGraphTopicsPage() {
		graphRepresentationsTopicLink = waitAndGetElement(graphRepresentationsTopicPath);
		if (graphRepresentationsTopicLink != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getTopicPageTitle(String topic) {
		String xpathStr = "";
		if (topic.contains("Graph")) {
			xpathStr = "//div[@class='col-sm']//strong//p[@class='bg-secondary text-white']"; 
		}
		else if (topic.contains("Graph Representations")) {
			xpathStr = "//p[@class='bg-secondary text-white' and text()='Graph Representations']";
		}
		By element = By.xpath(xpathStr);
		WebElement tempElement = waitAndGetElement(element);
	    if (tempElement != null) {
	    	return tempElement.getText();
	    }
		return "";
	}
	
	public void clickGraphTopic(String topicTitle) {
		WebElement tempElement = null;
		if (topicTitle == "Graph") {
			tempElement = waitAndGetElement(graphTopicPath);			
		}
		else if (topicTitle == "Graph Representations") {
			tempElement = waitAndGetElement(graphRepresentationsTopicPath);
		}
		
		
		if (tempElement != null) {
			tempElement.click();
		}
	}
	
	public void clickGetStarted() {
		graphGetStartedBtn = waitAndGetElement(graphTopicGetStartedPath);
		graphGetStartedBtn.click();
	}
	
	public void clickRunBtn() {
		runBtn = waitAndGetElement(runBtnPath);
		runBtn.click();
	}
	
	public String getPythonValidSyntaxOutput() {
		pythonCodeOutput = waitAndGetElement(pythonCodeOutputPath);
		return pythonCodeOutput.getText();
	}
	
	public String getPythonSyntaxErrorOutput() {
        Alert alert = driver.switchTo().alert();
		String message = alert.getText();
		
		// press ok button on alert
		alert.accept();
		return message;
	}
	
	public void clickArrayTopic(String topicLinkStr) {
		scrollToElementAndClick(topicLinkStr);
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
	  WebElement testElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(element));
	  if (testElement.isDisplayed()) {
	    return true;
	  }    	
		return false;
	}	
	
	
	// finds the element and scrolls to element using java script scroll into view
    public void scrollToElementAndClick(String str){
    	By element = null;
    	if (str == "tryHere") {
    		element = tryHerePath;
    	}
    	else if (str.contains("Graph")) {
    		element = graphTopicPath;			
		}
		else if (str.contains("Graph Representations")) {
			element = graphRepresentationsTopicPath;
		}
    	
        WebElement testElement = driver.findElement(element);                
        JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView()", testElement); 
        testElement.click();        
    }

}
