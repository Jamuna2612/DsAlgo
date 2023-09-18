package org.example.stepDefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.example.testComponents.BaseTest;
import org.example.testsObjects.pageObjects.IntroPage;
import org.example.testsObjects.pageObjects.LoginPage;
import org.example.testsObjects.pageObjects.RegisterPage;
import org.example.testsObjects.pageObjects.WelcomePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

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
    
    @Given("User request to create {string} username")
    public void generateUsernameForGivenType(String userNameType) {

    	if (userNameType.toLowerCase().contains("empty")) {
    		registerPage.setUserName("");
    	}else {
        	// default username length to be created  is 8 char log
        	int length = 8;
        	// when user requests 151_alphabets length (in error test), we pass requested length as 151 instead of 8
        	if (userNameType.toLowerCase().contains("151_alphabets")) {
        		length = 151;
        	}
        	registerPage.generateUsername(length, userNameType);
    	}
    }

    @Given("User request to create {string} password")
    public void generatePasswordForGivenType(String passwordType) {
    	if (passwordType.toLowerCase().contains("emptypassword")) {
    		registerPage.setPassword("");
    	}else if (passwordType.toLowerCase().contains("emptyretypepassword")) {
    		registerPage.setRetypePassword("");
    	}else if (passwordType.toLowerCase().contains("shortlength")) {
    		Random random = new Random();
    		// valid password is min 8 char long so choosing invalid char length from 1 to 7
    		// can't take 0, as it is empty password usercase, which is separate test case
    		int length = random.nextInt(1,7);
    		registerPage.generatePassword(length, passwordType);
    	}
    	else {
    		//default password length to be created is 8 char log
    		registerPage.generatePassword(8, passwordType);
    	}
    }

    @Given("User inputs credentials created")
    public void inputCredentials() {
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
    		registerPage.generatePassword(8, "repasswordempty");
    	}    	
		registerPage.inputCredentials();
    }
    
    @Then("I verify registration failure {string}")
    public void validateRegistrationFailureMessage(String errorMsg) {
    	String errorStr = "";
    	// check if expected message contains string related to input field validation
    	// If yes, then read active element validatioMessage to verify expected v/s actual message
    	if (errorMsg.contains("Please fill out")) {
    		errorStr = registerPage.getActiveElementValidationMessage();
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
    		// without below 2 lines of code, test will PASS even though expected and actual error messages are different
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
