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


public class TreeValidationTest extends BaseTest {

    @Test(dataProvider = "positiveLoginData")
    public void validatingClickOnTreeStructure(String userName, String pswd) throws IOException, InterruptedException {

        WebDriver driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        LoginPage loginPage = welcomePage.login();

        loginPage.loginDataClick(userName, pswd);
        IntroPage introPage = loginPage.submit();

        introPage.treeClick();
        introPage.clickOnTree();
        introPage.tryOnClick();
        actionMethod();
        introPage.runButton();
        driver.quit();
    }

    @Test(dataProvider = "positiveLoginData")
    public void validatingClickOnTreeLinks(String userName, String pswd) throws IOException, InterruptedException {

        WebDriver driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        LoginPage loginPage = welcomePage.login();

        loginPage.loginDataClick(userName, pswd);
        IntroPage introPage = loginPage.submit();

        introPage.treeClick();
        introPage.clickTerminology();
        introPage.tryOnClick();
        actionMethod();
        introPage.runButton();
        driver.quit();
    }

    @Test(dataProvider = "positiveLoginData")
    public void validatingClickOnTreeTypes(String userName, String pswd) throws IOException, InterruptedException {

        WebDriver driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        LoginPage loginPage = welcomePage.login();

        loginPage.loginDataClick(userName, pswd);
        IntroPage introPage = loginPage.submit();

        introPage.treeClick();
        introPage.clickTreeTypes();
        introPage.tryOnClick();
        actionMethod();
        introPage.runButton();
        driver.quit();
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
