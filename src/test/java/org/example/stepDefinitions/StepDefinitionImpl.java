package org.example.stepDefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.testComponents.BaseTest;
import org.example.testsObjects.pageObjects.IntroPage;
import org.example.testsObjects.pageObjects.LoginPage;
import org.example.testsObjects.pageObjects.WelcomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

public class StepDefinitionImpl extends BaseTest {

    public WebDriver driver;
    public LoginPage loginPage;
    public IntroPage introPage;

    @Given("User is in login page")
    public void  User_is_in_login_page() throws IOException, InterruptedException {

        driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        loginPage = welcomePage.login();
        Thread.sleep(1000);
    }

    @Given("^Logged in with username (.+) and password(.+)$")
    public void Logged_in_with_username_and_password(String userName, String password) throws InterruptedException {

        loginPage.loginDataClick(userName,password);
        Thread.sleep(1000);
    }
    @When("User click login")
    public void User_click_login(){
        loginPage.submit();
    }
    @Then("{string} message is displayed")
    public void message_is_displayed(String string) {
        String actualLoginMessage = loginPage.errorMsg();
        Assert.assertTrue(actualLoginMessage.equalsIgnoreCase(string));
        driver.close();
    }
}
