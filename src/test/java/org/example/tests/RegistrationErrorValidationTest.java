package org.example.tests;

import org.example.testComponents.BaseTest;
import org.example.testsObjects.pageObjects.RegisterPage;
import org.example.testsObjects.pageObjects.WelcomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationErrorValidationTest extends BaseTest {

	WebDriver driver;

    @Test
    public void numbersOnlyPasswordRegisterError() throws IOException, InterruptedException {
        driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        
        welcomePage.click();
        RegisterPage registerPage = welcomePage.registerClick();
        
        // username field displayed on webpage
        if (registerPage.waitForElementDisplayed("username")) {
        	// generate username with length of 6 chars
        	registerPage.generateUsername(6, "alphabetsonly");
        	
        	// generate password
        	registerPage.generatePassword(8, "numbers");
        	
        	registerPage.inputCredentials();
            registerPage.submitClick();
            String actualErrStr = registerPage.getErrorMessage();
            System.out.println("Actual Error: " + actualErrStr);
           // Assert.assertEquals(actualErrStr, "password_mismatch:The two password fields didn’t match.");
        }
        driver.close();
    }

    @Test
    public void passwordMismatchRegisterError() throws IOException, InterruptedException {
        driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        
        welcomePage.click();
        RegisterPage registerPage = welcomePage.registerClick();
        
        // username field displayed on web page
        if (registerPage.waitForElementDisplayed("username")) {
        	// generate username with length of 6 chars
        	registerPage.generateUsername(6, "alphabetsOnly");
        	
        	// generate password
        	registerPage.generatePassword(8, "passwordmismatch");
        	
        	registerPage.inputCredentials();
            registerPage.submitClick();
            String actualErrStr = registerPage.getErrorMessage();
            System.out.println("Actual Error: " + actualErrStr);
           // Assert.assertEquals(actualErrStr, "password_mismatch:The two password fields didn’t match.");
        }
        driver.close();
    }
    
    @Test
    public void longUserNameRegisterError() throws IOException, InterruptedException {
        driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        
        welcomePage.click();
        RegisterPage registerPage = welcomePage.registerClick();
        
        // username field displayed on web page
        if (registerPage.waitForElementDisplayed("username")) {
        	// generate username with length of 6 chars
        	registerPage.generateUsername(151, "alphabetsOnly");
        	
        	// generate password
        	registerPage.generatePassword(8, "alphanumbericSpecialChars");
        	

        	registerPage.inputCredentials();
            registerPage.submitClick();
            String actualErrStr = registerPage.getErrorMessage();
            System.out.println("Actual Error: " + actualErrStr);
            // password_mismatch:The two password fields didn�t  match,
            // Here didn't sometimes comes as ', sometimes ? and sometimes symbol
            // hence assert not equals "" is used
           // Assert.assertNotEquals(actualErrStr, "");
        }
        driver.close();
    }    
    
    @Test
    public void invalidSpecialCharUsernameRegisterError() throws IOException, InterruptedException {
        driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        
        welcomePage.click();
        RegisterPage registerPage = welcomePage.registerClick();
        
        // username field displayed on web page
        if (registerPage.waitForElementDisplayed("username")) {
        	// generate username with length of 6 chars
        	registerPage.generateUsername(4, "invalidspecialchars");
        	
        	// generate password
        	registerPage.generatePassword(8, "alphanumbericSpecialChars");
        	
        	registerPage.inputCredentials();
            registerPage.submitClick();
            String actualErrStr = registerPage.getErrorMessage();
            System.out.println("Actual Error: " + actualErrStr);
          //  Assert.assertNotEquals(actualErrStr, "");
        }
        driver.close();
    }
    
    @Test
    public void shortPasswordRegisterError() throws IOException, InterruptedException {
        driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        
        welcomePage.click();
        RegisterPage registerPage = welcomePage.registerClick();
        
        // username field displayed on web page
        if (registerPage.waitForElementDisplayed("username")) {
        	// generate username with length of 6 chars
        	registerPage.generateUsername(4, "alphabetsonly");
        	
        	// generate password
        	registerPage.generatePassword(5, "alphanumbericSpecialChars");
        	
        	registerPage.inputCredentials();
            registerPage.submitClick();
            String actualErrStr = registerPage.getErrorMessage();
            System.out.println("Actual Error: " + actualErrStr);
          //  Assert.assertNotEquals(actualErrStr, "");
        }
        driver.close();
    }   
}


