package org.example.tests;

import org.example.testComponents.BaseTest;
import org.example.testsObjects.pageObjects.IntroPage;
import org.example.testsObjects.pageObjects.RegisterPage;
import org.example.testsObjects.pageObjects.WelcomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationValidationTest2 extends BaseTest {

    @Test(dataProvider = "registerData")
    public void registerValidation(String username, String password, String reTypePassword) throws IOException, InterruptedException {


        WebDriver driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        RegisterPage loginPage = welcomePage.registerClick();
        Thread.sleep(1000);

        loginPage.login(username, password, reTypePassword);
        IntroPage introPage = loginPage.submitClick();
        Thread.sleep(2000);

        introPage.clickDsElement();
        Thread.sleep(2000);
        introPage.clickTreeElement();
        introPage.signOutClick();

        String actual = driver.findElement(By.xpath("//div[contains(text(),'Logged')]")).getText();
        Assert.assertEquals(actual, "Logged out successfully");
        driver.close();
    }

    @DataProvider
    public Object[][] registerData() {
        return new Object[][]{{"gertilsa1", "1jungle@43!@@#!221", "1jungle@43!@@#!221"}};

    }
}

