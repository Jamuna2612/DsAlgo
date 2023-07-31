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

    public void loginDataClick(String uname,String pswd){
        userName.sendKeys(uname);
        pwd.sendKeys(pswd);
    }

    public IntroPage submit(){
        login.click();
        return new IntroPage(driver);
    }
}
