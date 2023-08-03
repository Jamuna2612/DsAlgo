package org.example.testsObjects.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#id_username")
    WebElement userName;

    @FindBy(xpath = "//input[@id='id_password']")
    WebElement pwd;

    @FindBy(xpath = "//input[@value ='Login']")
    WebElement login;

    @FindBy(xpath = "//div[contains(text(),'You are logged in')]")
    WebElement loginMessage;

    @FindBy(xpath = "//div[contains(text(),'Invalid Username and Password')]")
    WebElement errorLoginMsg;

    public void loginDataClick(String uname,String pswd){
        userName.sendKeys(uname);
        pwd.sendKeys(pswd);
    }

    public IntroPage submit(){
        login.click();
        return new IntroPage(driver);
    }
    public String signInMessage(){
        String loginText = loginMessage.getText();
        return loginText;
    }

    public String errorMsg(){
        String emsg= errorLoginMsg.getText();
        return emsg;
    }
}
