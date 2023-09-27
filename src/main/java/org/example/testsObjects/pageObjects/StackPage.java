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

public class StackPage {
	
	WebDriver driver;
	
	By getStarted_Stack = By.xpath("//a[@href='stack']");
	By errorPath = By.cssSelector("[class*='alert']");
	By topic_OperationsInStackPath = By.xpath("//a[@href='operations-in-stack']");
	By topic_ImplementationPath = By.xpath("//a[@href='implementation']");
	By topic_ApplicationsPath = By.xpath("//a[@href='stack-applications']");
	By tryHerePath = By.xpath("//a[@href='/tryEditor']");
	By runBtnPath = By.xpath("//button[@type='button']");
	By textEditorPath = By.xpath("//div[@class='CodeMirror-code']");
	By pythonCodeOutputPath = By.xpath("//pre[@id='output']");
	
	
	WebElement getStartedStack;
	WebElement error;
	WebElement operationsInStack;
	WebElement implementation;
	WebElement applications;
	WebElement tryHereBtn;
	WebElement runBtn;
	WebElement textEditorBox;
	WebElement pythonCodeOutput;
	
	public StackPage(WebDriver driver) {
		  this.driver = driver;
		}


		public WebElement waitAndGetElement(By element) {
		    WebElement testElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
		    if (testElement.isDisplayed()) {
		      return testElement; 
		    }
		    else {
		    	  return null;
		    }
	    }
		
		public boolean onStackTopicsPage() {
			operationsInStack = waitAndGetElement(topic_OperationsInStackPath);
			if (operationsInStack != null) {
				return true;
			}
			else {
				return false;
			}
		}
		
		public String getTopicPageTitle(String topic) {
			String xpathStr = "";
			if (topic.contains("Operations in Stack")) {
				xpathStr = "//p[@class='bg-secondary text-white' and text()='Operations in Stack']"; 
			}
			else if (topic.contains("Implementation")) {
				xpathStr = "//p[@class='bg-secondary text-white' and text()='Implementation']";
			}
			By element = By.xpath(xpathStr);
			WebElement tempElement = waitAndGetElement(element);
		    if (tempElement != null) {
		    	return tempElement.getText();
		    }
			return "";
		}
		
		public void clickStackTopic(String topicTitle) {
			WebElement tempElement = null;
			if (topicTitle == "Operations in Stack") {
				tempElement = waitAndGetElement(topic_OperationsInStackPath);			
			}
			else if (topicTitle == "Implementation") {
				tempElement = waitAndGetElement(topic_ImplementationPath );
			}
			
			
			if (tempElement != null) {
				tempElement.click();
			}
		}
		
		public void clickGetStarted() {
			getStartedStack = waitAndGetElement(getStarted_Stack);
			getStartedStack.click();
		}
		
		public void clickRunBtn() {
			runBtn = waitAndGetElement(runBtnPath);
			runBtn.click();
		}
		
		public void clickStackTopic1(String topicLinkStr) {
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
		
		public boolean waitForElementDisplayed(By element) {
			  WebElement testElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(element));
			  if (testElement.isDisplayed()) {
			    return true;
			  }    	
				return false;
			}
		
		public void scrollToElementAndClick(String str){
	    	By element = null;
	    	if (str == "tryHere") {
	    		element = tryHerePath;
	    	}
	    	else if (str.contains("Operations in Stack")) {
	    		element = topic_OperationsInStackPath;			
			}
			else if (str.contains("Implementation")) {
				element = topic_ImplementationPath;
			}
	    	
	        WebElement testElement = driver.findElement(element);                
	        JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView()", testElement); 
	        testElement.click();        
	    }
}
