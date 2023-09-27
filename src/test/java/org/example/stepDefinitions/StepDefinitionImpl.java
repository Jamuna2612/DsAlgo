package org.example.stepDefinitions;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.example.testComponents.BaseTest;
import org.example.testsObjects.pageObjects.ArrayPage;
import org.example.testsObjects.pageObjects.GraphPage;
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
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class StepDefinitionImpl extends BaseTest {

    public WebDriver driver;
    public LoginPage loginPage;
    public IntroPage introPage;
    public WelcomePage welcomePage;
    public RegisterPage registerPage;
    public ArrayPage arrayPage;
    public GraphPage graphPage;

    @Given("User is in login page")
    public void User_is_in_login_page() throws IOException, InterruptedException {
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
        introPage = loginPage.submit();
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
    
    // Array/Graph page step definitions
    @Given("User is on home page")
    public void userOnHomePage() throws IOException, InterruptedException {
        driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();    	
    }
    
    // added separate step to avoid conflict with validation test
    // After discussion, we'll keep only one step
    @Given("User enters {string} and {string}")
    public void loginWithInputCredentials(String username, String password) {
        loginPage.loginDataClick(username,password);
    }
    
    
    @When("User clicks get started button for {string} topic")
    public void clickGetStartedPage(String pageType) {
    	if (pageType.toLowerCase().contains("array")) {
            arrayPage = new ArrayPage(driver);
            arrayPage.clickGetStarted();
    	}
    	else if (pageType.toLowerCase().contains("graph")) {
            graphPage = new GraphPage(driver);
            graphPage.clickGetStarted();
    	}
    }
    
    @Then("User verify {string} topic click error message {string}")
    public void verifyErrorMessage(String pageType, String error) {
    	String errorMsg = "";
    	if (pageType.toLowerCase().contains("array")) {
            errorMsg = arrayPage.getErrorMessage();
            System.out.println(errorMsg);
    	}
    	else if (pageType.toLowerCase().contains("graph")) {
            errorMsg = graphPage.getErrorMessage();
            System.out.println(errorMsg);
    	}
    	// verify expected error message with got from test
        if (errorMsg == error) {
        	System.out.println("Expected error message: " + error + " Actual message: " + errorMsg);
        }else {
        	System.out.println("Expected error message: " + error + " Actual message: " + errorMsg);
        }
    }
    
    @Then("User verify {string} topic link page is displayed")
    public void verifyTopicLinksOpened(String pageType) {
    	if (pageType.toLowerCase().contains("array")) {
    		boolean onArrayTopicsPage = arrayPage.onArrayTopicsPage();
    		if (onArrayTopicsPage == true) {
    			System.out.println(pageType + " links present on web page");
    		}
//            Assert.assertEquals(onArrayTopicsPage, true);
    	}
    	
    }
    
    @And("User clicks signout button")
    public void clickSignOutButton() {
        introPage.signOutClick();
        String actualMessage = introPage.signOutMessage();
        Assert.assertEquals(actualMessage, "Logged out successfully");    	
    }
    
    @Then("Login {string} message is displayed")
    public void loginSuccessMessageDisplayed(String expectedMsgType) {
    	String actualMsg = ""; 
    	String expectedMsg = "";
    	if (expectedMsgType.contains("success")) {
            actualMsg = loginPage.signInMessage();
            expectedMsg = "You are logged in";
    	}
    	else if (expectedMsgType.contains("error")) {
    		actualMsg = loginPage.errorMsg();
    		expectedMsg = "Invalid Username and Password";
    	}
        System.out.println("Expected Message: " + expectedMsg + " Actual Message: " + actualMsg);
//        Assert.assertEquals(logInMessage, expectedMsg);
    } 
    
    @When("User clicks {string} page {string}")
    public void clickInputTopicLink(String pageType, String topicLink) {
    	if (pageType.toLowerCase().contains("array")) {
    		arrayPage.clickArrayTopic(topicLink);
    	}
    	else if (pageType.toLowerCase().contains("graph")) {
    		graphPage.clickArrayTopic(topicLink);
    	}    	
    }
    
    @Then("{string} page {string} is displayed on web page")
    public void verifyTopicTitleDisplayed(String pageType, String topicLink) {
    	String titleStr = "";
    	if (pageType.toLowerCase().contains("array")) {
            titleStr = arrayPage.getTopicPageTitle(topicLink);
            System.out.println("Topic page title: " + titleStr);
    	}
    	else if (pageType.toLowerCase().contains("graph")) {
    		titleStr = graphPage.getTopicPageTitle(topicLink);
    		System.out.println("Topic page title: " + titleStr);
    	}
    }
    
    @When("User executes {string} on {string} page and verify code output")
    public void executePythonCodeAndVerifyOutputMessage(String pythonCodeType, String pageType) {
        String output = "";
    	if (pageType.toLowerCase().contains("array")) {
            // click TryHere button
            arrayPage.scrollToElementAndClick("tryHere");
            
            //Enter python code in text Editor and press Run
            arrayPage.updateTextEditorAndClickRun(pythonCodeType);
            arrayPage.clickRunBtn();
            if (pythonCodeType.contains("CodeError")) {
            	output = arrayPage.getPythonCodeErrorOutput();
            }else {
            	output = arrayPage.getPythonCodeOutput();
            }
    	}
    	else if (pageType.toLowerCase().contains("graph")) {
            // click TryHere button
            graphPage.scrollToElementAndClick("tryHere");
            
            //Enter python code in text Editor and press Run
            graphPage.updateTextEditorAndClickRun(pythonCodeType);
            graphPage.clickRunBtn();
            if (pythonCodeType.contains("CodeError")) {
            	output = graphPage.getPythonCodeErrorOutput();
            }else {
            	output = graphPage.getPythonCodeOutput();
            }    		
    	}
    	System.out.println("python code output: " + output);
    }
    
    @Then("I close web driver")
    public void closeWebDriver() {
    	// Commented below code as it is written in @After function
//    	if (driver != null) {
//	    	driver.close();
//	    	driver.quit();
//    	}
    }
    
    // @After will get called for every test scenario at the end of test
    // Code logic is added to check scenario status and if failed, take screenshot of web page
    @After
    public void afterTest(Scenario scenario) throws IOException {
    	//System.out.println("Test status: " + scenario.getStatus());
    	String scenarioName = scenario.getName();
    	Collection<String> tags = scenario.getSourceTagNames();
    	String fileName = "";
    	
    	Iterator<String> iterator = tags.iterator();
    	while (iterator.hasNext()) {
    	    fileName += iterator.next(); //.replaceAll("@", "");
    	}
    	
    	// making filename unique for scenario outline using executed scenario line# 
    	if (scenario.isFailed()) {
    		// take screenshot
    		String filePath = getScreenshot(fileName, driver);
    		System.out.println("Screenshot for " + scenarioName + " is saved at: " + filePath);
    	}
    	if (driver != null) {
	    	driver.close();
	    	driver.quit();
    	}
    }
    	
    	 		
    @Given("User is in intro page")
    public void user_is_in_intro_page() throws IOException, InterruptedException {
        driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        loginPage = welcomePage.login();
        loginPage.loginDataClick("james", "apple@123");
        introPage = loginPage.submit();
    }

    @When("user click linkedList")
    public void user_click_linked_list() {
        introPage.llClick();
    }

    @Then("click intro link")
    public void click_intro_link() {
        introPage.introClick();
    }

    @Then("navigate back to intro page")
    public void navigate_back_to_intro_page() {
        navigateBack(2);
    }

    @And("click tree link")
    public void clickTreeLink() {
        introPage.treeClick();
        driver.quit();
    }
}
