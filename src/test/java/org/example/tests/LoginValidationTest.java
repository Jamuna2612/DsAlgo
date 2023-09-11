package org.example.tests;

import org.example.testComponents.BaseTest;
import org.example.testsObjects.pageObjects.IntroPage;
import org.example.testsObjects.pageObjects.LoginPage;
import org.example.testsObjects.pageObjects.WelcomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginValidationTest extends BaseTest {
    @Test(dataProvider = "positiveLoginData")
    public void positiveLoginValidationTest(String userName, String pswd) throws IOException, InterruptedException {

        WebDriver driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        LoginPage loginPage = welcomePage.login();

        loginPage.loginDataClick(userName, pswd);
        IntroPage introPage = loginPage.submit();
        String logInMessage = loginPage.signInMessage();
        Assert.assertEquals(logInMessage, "You are logged in");

        introPage.signOutClick();
        String actualMessage = introPage.signOutMessage();
        Assert.assertEquals(actualMessage, "Logged out successfully");
    }


    @Test(dataProvider = "errorLoginData")
    public void negativeLoginValidationTest(String userName, String pswd) throws IOException, InterruptedException {

        WebDriver driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        LoginPage loginPage = welcomePage.login();

        loginPage.loginDataClick(userName, pswd);
        IntroPage introPage = loginPage.submit();

        String actualMessage = introPage.signOutErrorMessage();
        Assert.assertEquals(actualMessage, "Invali Username and Password");
    }

    @DataProvider
    public Object[][] errorLoginData() {
        return new Object[][]{{"Junglelu", "Twinkle4@!"}};
    }

    @DataProvider
    public Object[][] positiveLoginData() {
        return new Object[][]{{"quan", "India@123"}};
    }
}
