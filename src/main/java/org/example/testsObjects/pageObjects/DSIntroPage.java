package org.example.testsObjects.pageObjects;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class DSIntroPage {
	
	WebDriver driver;
	
	public DSIntroPage(WebDriver driver) {	
		this.driver = driver;
			
	}
	
	 By getStarted_DS = By.xpath("//div/div[1]/div/div/a[text()='Get Started']");
     WebElement dsGetStartedLink;
	 
     By timeComplexityPath = By.xpath("//a[text()='Time Complexity']");
	 WebElement timeComplexity;

	 By errorPath = By.xpath("//div[contains(text(),'You are not logged in')]");
	 WebElement error;
	 
	 By tryHerePath = By.xpath("//a[text()='Try here>>>']");
	 WebElement tryHere;
	 
	 By textEditorPath = By.xpath("//div[@class='CodeMirror-code']");
	 WebElement textEditor;
	 
	 By runBtnPath = By.xpath("//button[@type='button']");
	 WebElement runBtn;
	 
	 By getStarted_ds = By.xpath("//a[@href='data-structures-introduction']");
	 WebElement getStartedHome;
	 
	 By pythonCodeOutputPath = By.xpath("//pre[@id='output']");
	 WebElement pythonCodeOutput;
	 
	 public void clickGetStarted() {
		 getStartedHome = waitAndGetElement(getStarted_ds);
		 getStartedHome.click();
		}
	 
//	 public void clickGetStarted() {
//		 dsGetStartedLink.click();
//		}
	 public String getErrorMessage() {
			error = waitAndGetElement(errorPath);
			String message = error.getText();
			return message;
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
	 
	 
	 public void scrollToElementAndClick(String str){
		 By element = null;
	    	if (str.contains("tryHere")) {
	    		element = tryHerePath;
	    	}
	    	else if(str.contains("Time Complexity")) {
	    		element = timeComplexityPath;			
			}
			  	
	    	
	        WebElement testElement = driver.findElement(element);                
	        JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView()", testElement); 
	        testElement.click();        
	    }
	 
	 public void clickRunBtn() {
			runBtn = waitAndGetElement(runBtnPath);
			runBtn.click();
		}
	 
	 public void updateTextEditorAndClickRun(String codeType) {
			String command = "print";
			if (codeType.contains("CodeError")) {
				command = "Print";
			}
			// get text editor element
			textEditor = waitAndGetElement(textEditorPath);
			
	        Actions action = new Actions(driver);
	        JavascriptExecutor js2 = (JavascriptExecutor)driver;
	        js2.executeScript("arguments[0].textContent ='print'",textEditor);
	        
	        action.keyDown(Keys.ENTER).sendKeys(command).sendKeys(Keys.SPACE).sendKeys("\"Hello\"").sendKeys(Keys.ENTER).perform();
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
	 
}
