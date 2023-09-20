package org.example.testsObjects.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage {

    WebDriver driver;
    public WelcomePage(WebDriver driver){
    this.driver = driver;
    PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//button[contains(text(),'Get Started')]")
    WebElement getStarted;

    //driver.findElement(By.linkText("Register")).click();
    @FindBy(linkText = "Register")
    WebElement register;

    @FindBy(xpath = "//a[contains(text(),'Sign in')]")
    WebElement loginClick;


    public void goTo(){
        driver.get("https://dsportalapp.herokuapp.com/");
    }
    public IntroPage click() throws InterruptedException {
        getStarted.click();
        return new IntroPage(driver);
    }

    public LoginPage login(){
        loginClick.click();
        return new LoginPage(driver);
    }

    public RegisterPage registerClick() throws InterruptedException {
        register.click();
        return new RegisterPage(driver);
    }
}
