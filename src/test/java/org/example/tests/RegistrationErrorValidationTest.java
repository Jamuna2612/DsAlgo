package org.example.tests;

import org.example.testComponents.BaseTest;
import org.example.testsObjects.pageObjects.RegisterPage;
import org.example.testsObjects.pageObjects.WelcomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationErrorValidationTest extends BaseTest {


    @Test
    public void registrationErrorValidationWithNumericValue() throws IOException, InterruptedException {


        WebDriver driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        RegisterPage loginPage = welcomePage.registerClick();
        loginPage.login("geng", "6534", "6534");
        loginPage.submitClick();
        String actual = loginPage.errorMessage();
        Assert.assertEquals(actual, "password_mismatch:The two password fields didn’t match.");
    }

    @Test
    public void registrationErrorWithAlphaNumeric() throws IOException, InterruptedException {


        WebDriver driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        RegisterPage loginPage = welcomePage.registerClick();
        loginPage.login("Johan", "!6534@", "!6534@");
        loginPage.submitClick();
        String actual = loginPage.errorMessage();
        Assert.assertEquals(actual, "password_mismatch:The two password fields didn’t match.");
    }


}


