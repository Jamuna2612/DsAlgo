package org.example.testsObjects.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='id_username']") //By.xpath("//input[@name='username']
    WebElement userName;
    @FindBy(xpath = "//input[@id='id_password1']")
    WebElement password;

    @FindBy(xpath = "//input[@id='id_password2']")
    WebElement retypePassword;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement submit;

    @FindBy(css = "[class*='alert']")
    WebElement error;

    public void login(String uName, String pwd, String rePassword) {
        userName.sendKeys(uName);
        password.sendKeys(pwd);
        retypePassword.sendKeys(rePassword);
    }

    public IntroPage submitClick() {
        submit.click();
        return new IntroPage(driver);

    }

    public String errorMessage() {
        String message = error.getText();
        return message;
    }

}
