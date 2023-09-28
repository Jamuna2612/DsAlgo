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


    @FindBy(xpath = "(//a[text()='Get Started'])[3]")
    WebElement linkedList;

    @FindBy(xpath = "//a[text()='Types of Linked List']")
    WebElement typesLinkedList;
    @FindBy(xpath = "(//a[text()='Get Started'])[6]")
    WebElement tree;
    @FindBy(xpath = "//a[text()='Introduction']")
    WebElement intro;

    @FindBy(xpath = "//a[text()='Creating Linked LIst']")
    WebElement creatingLinkedList;

    public void clickOnCreatingLL(){
        creatingLinkedList.click();
    }
    @FindBy(xpath = "//a[text()='Overview of Trees']")
    WebElement overviewOfTree;

    @FindBy(xpath = "//a[text()='Try here>>>']")
    WebElement tryHere;
    @FindBy(xpath = "//ul/a[3]")
    WebElement signOut;

    @FindBy(xpath = "//div[contains(text(),'Logged out successfully')]")
    WebElement logOutMessage;

    @FindBy(css = "div[class*='alert-primary']")
    WebElement invalidDataMessage;
    
    @FindBy(css = "div[class*='alert-primary']")
    WebElement alertDataMessage;

    @FindBy(xpath = "//a[text()='Terminologies']")
    WebElement terminology;

    @FindBy(xpath = "//a[text()='Types of Trees']")
    WebElement treeTypes;
    

    @FindBy(xpath = "//button[text()='Run']")
    WebElement run;

    public void clickTreeTypes(){
        treeTypes.click();
    }
    public void clickTerminology(){
        terminology.click();
    }
    public void clickOnTree(){
        overviewOfTree.click();
    }
    public void runButton(){
        run.click();
    }
    public void tryOnClick(){
        tryHere.click();
    }
    public void typesOfLL(){
        typesLinkedList.click();
    }
    public void llClick() {
        linkedList.click();
    }

    public void introClick(){
        intro.click();
    }
    public void treeClick() {
        tree.click();
    }
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
        return invalidDataMessage.getText();
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
