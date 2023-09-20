package org.example.testsObjects.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(xpath = "//div/div[1]/div/div/a[text()='Get Started']")
    public WebElement dsGetStartedLink;

    @FindBy(xpath = "//div/div[2]/div/div/a[text()='Get Started']")
    WebElement arrayGetStartedLink;

    @FindBy(xpath = "//div/div[3]/div/div/a[text()='Get Started']")
    WebElement linkedListGetStartedLink;

    @FindBy(xpath = "//div/div[4]/div/div/a[text()='Get Started']")
    WebElement stackGetStartedLink;

    @FindBy(xpath = "//div/div[5]/div/div/a[text()='Get Started']")
    WebElement queueGetStartedLink;

    @FindBy(xpath = "//div/div[6]/div/div/a[text()='Get Started']")
    WebElement treeGetStartedLink;

    @FindBy(xpath = "//div/div[7]/div/div/a[text()='Get Started']")
    WebElement graphGetStartedLink;


    @FindBy(xpath = "//ul/a[3]")
    WebElement signOut;

    @FindBy(xpath = "//div[contains(text(),'Logged out successfully')]")
    WebElement logOutMessage;

    @FindBy(css = "div[class*='alert-primary']")
    WebElement invalidDataMessage;

    public String getNotLoggedInMessage() {
        String notLoginMessage = notLoggedInMessage.getText();
        return notLoginMessage;
    }

    @FindBy(xpath = "//div[contains(text(),'You are not logged in')]")
    WebElement notLoggedInMessage;

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

    public String notLoggedInMessage(){
        String message = notLoggedInMessage.getText();
        return message;
    }

    public void elementClick() {
        dsGetStartedLink.click();
    }


}
