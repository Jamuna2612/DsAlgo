package org.example.stepDefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.example.testComponents.BaseTest;
import org.example.testsObjects.pageObjects.IntroPage;
import org.example.testsObjects.pageObjects.LoginPage;
import org.example.testsObjects.pageObjects.RegisterPage;
import org.example.testsObjects.pageObjects.WelcomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;

public class StepDefinitionImpl extends BaseTest {

    public WebDriver driver;
    public LoginPage loginPage;
    public IntroPage introPage;
    public WelcomePage welcomePage;
    public RegisterPage registerPage;

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
    
    // registration steps. Can be moved to separate file if required
    @Given("User opens Register Page")
    public void openRegisterPage() throws IOException, InterruptedException {
        driver = initializeDriver();
        welcomePage = new WelcomePage(driver);
        welcomePage.click();    	
    }
    
    @Then("User clicks Register button")
    public void clickRegisterButton() throws IOException, InterruptedException {
    	registerPage = welcomePage.registerClick();    	
    }    
    
    @Given("User enters {string} with {string} and {string}")
    public void inputCredentials(String userNameType, String passwordType, String usernameLength) {
    	System.out.println("UsernameType: " + userNameType + " passwordType: " + passwordType + " userNameLength: " + usernameLength);
    	registerPage.generateUsername(Integer.parseInt(usernameLength), userNameType);
    	registerPage.generatePassword(8, passwordType);
    	registerPage.inputCredentials();
    }

    @When("User click submit button")
    public void submitRegistration() {
    	introPage = registerPage.submitClick();
    }
    
    @Then("User registration is successful")
    public void validateRegistrationSuccess() {
        if (introPage.waitForElementDisplayed("alertDataMessage")) {
        	String msg = introPage.getAlertDataMessage();
        	if (msg.contains("New Account Created")) {
        		System.out.println("Verified message: " + msg);
            	System.out.println("Registration with username: " + registerPage.getUserName() + " and password: " + registerPage.getPassword() + "is successful");
                introPage.signOutClick();
        	}
        }else {
        	System.out.println("Registration with username: " + registerPage.getUserName() + " and password: " + registerPage.getPassword() + "is FAILED");
        }
    }
    
    @Then("I input {string}")
    public void enterUserInput(String inputType) {
    	if (inputType.contains("username")) {
    		// generate username
    		registerPage.generateUsername(8, "default");
    	}
    	if (inputType.contains("password")) {
    		// generate password
    		registerPage.generatePassword(8, "default");
    	}    	
		registerPage.inputCredentials();
    }
    
    @Then("I verify registration failure {string}")
    public void validateRegistrationFailureMessage(String errorMsg) {
    	String errorStr ; 
    	// check if expected error message is from popup window
    	// NOTE: //* returning all text from register page not catching pop up window
    	if (errorMsg.contains("Please fill out")) {
        	errorStr = registerPage.getPopUpWindowMessage();
    	}
    	// else expected error message is from alert box
    	else {
        	errorStr = registerPage.getErrorMessage();
    	}
    	
    	// validate error message
    	if (errorStr.contains(errorMsg)) {
    		System.out.println("Expected error message observed: " + errorStr);    		
    	}else {
    		System.out.println("Expected error message: " + errorMsg); 
    		System.out.println("Actual error message: " + errorStr);
    		System.out.println("Test Failed : DEFECT to be RAISED");
            ITestResult result = Reporter.getCurrentTestResult();
            result.setStatus(ITestResult.FAILURE);
    	}
    }
    
    
    @Then("I close web driver")
    public void closeWebDriver() {
    	if (driver != null) {
	    	driver.close();
	    	driver.quit();
    	}
    }
}
