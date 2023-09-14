package org.example.testsObjects.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IntroPage {

    WebDriver driver;
    public IntroPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[contains(text(),'Data Structures')]")
    WebElement dataStructure;

    @FindBy(xpath = "//div/a[4]")
    WebElement treeStructure;


    @FindBy(xpath = "//ul/a[3]")
    WebElement signOut;

    @FindBy(xpath = "//div[contains(text(),'Logged out successfully')]")
    WebElement logOutMessage;

    @FindBy(css = "div[class*='alert-primary']")
    WebElement invalidDataMessage;
    
    @FindBy(css = "div[class*='alert-primary']")
    WebElement alertDataMessage;
    

    public void clickDsElement(){
        dataStructure.click();
    }

    public void clickTreeElement(){
        treeStructure.click();
    }

    public void signOutClick(){
        signOut.click();
    }

    public String signOutMessage(){
        String message = logOutMessage.getText();
        return message;
    }

    public String signOutErrorMessage(){
        String message = invalidDataMessage.getText();
        return message;
    }
    
    public String getAlertDataMessage() {
    	return alertDataMessage.getText();
    }
    
    public boolean waitForElementDisplayed(String elementStr) {
    	WebElement element = null;
    	if (elementStr == "datastructure") {
    		element = dataStructure;
    	}else if (elementStr == "alertDataMessage") {
    		element = alertDataMessage;
    	}
    	// add more element else if conditions based on scenario
    	
	    WebElement testElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
	    if (testElement.isDisplayed()) {
	      return true;
	    }    	
    	return false;
    }    


}
