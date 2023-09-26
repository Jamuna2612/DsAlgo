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


    @FindBy(xpath = "(//a[text()='Get Started'])[3]")
    WebElement linkedList;

    @FindBy(xpath = "(//a[text()='Get Started'])[6]")
    WebElement tree;
    @FindBy(xpath = "//a[text()='Introduction']")
    WebElement intro;
    @FindBy(xpath = "//ul/a[3]")
    WebElement signOut;

    @FindBy(xpath = "//div[contains(text(),'Logged out successfully')]")
    WebElement logOutMessage;

    @FindBy(css = "div[class*='alert-primary']")
    WebElement invalidDataMessage;

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
        String message = invalidDataMessage.getText();
        return message;
    }


}
