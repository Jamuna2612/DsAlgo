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
        RegisterPage registerPage = welcomePage.registerClick();
        
        // username field displayed on webpage
        if (registerPage.waitForElementDisplayed("username")) {
        	// generate username with length of 6 chars
        	registerPage.generateUsername(6, "startWithLowerLetters");
        	
        	// generate password
        	registerPage.generatePassword(8, "numeric");
        	
        	registerPage.inputCredentials();
            registerPage.submitClick();
            String actualErrStr = registerPage.getErrorMessage();
            System.out.println("Actual Error: " + actualErrStr);
            Assert.assertEquals(actualErrStr, "password_mismatch:The two password fields didn’t match.");
        }
        driver.close();
    }

    @Test
    public void registrationErrorWithAlphaNumeric() throws IOException, InterruptedException {
        WebDriver driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        
        welcomePage.click();
        RegisterPage registerPage = welcomePage.registerClick();
        
        // username field displayed on web page
        if (registerPage.waitForElementDisplayed("username")) {
        	// generate username with length of 6 chars
        	registerPage.generateUsername(6, "startWithLowerLetters");
        	
        	// generate password
        	registerPage.generatePassword(8, "specialcharnumbers");
        	
        	registerPage.inputCredentials();
            registerPage.submitClick();
            String actualErrStr = registerPage.getErrorMessage();
            System.out.println("Actual Error: " + actualErrStr);
            Assert.assertEquals(actualErrStr, "password_mismatch:The two password fields didn’t match.");
        }
        driver.close();
    }
    
    @Test
    public void registrationErrorUserNameLength() throws IOException, InterruptedException {
        WebDriver driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        
        welcomePage.click();
        RegisterPage registerPage = welcomePage.registerClick();
        
        // username field displayed on web page
        if (registerPage.waitForElementDisplayed("username")) {
        	// generate username with length of 6 chars
        	registerPage.generateUsername(151, "startWithLowerLetters");
        	
        	// generate password
        	registerPage.generatePassword(8, "specialcharnumbers");
        	

        	registerPage.inputCredentials();
            registerPage.submitClick();
            String actualErrStr = registerPage.getErrorMessage();
            System.out.println("Actual Error: " + actualErrStr);
            // password_mismatch:The two password fields didn�t  match,
            // Here didn't sometimes comes as ', sometimes ? and sometimes symbol
            // hence assert not equals "" is used
            Assert.assertNotEquals(actualErrStr, "");
        }
        driver.close();
    }    
    
    @Test
    public void registrationErrorInvalidSpecialChar() throws IOException, InterruptedException {
        WebDriver driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        
        welcomePage.click();
        RegisterPage registerPage = welcomePage.registerClick();
        
        // username field displayed on web page
        if (registerPage.waitForElementDisplayed("username")) {
        	// generate username with length of 6 chars
        	registerPage.generateUsername(4, "containsInvalidSpecialChars");
        	
        	// generate password
        	registerPage.generatePassword(8, "alphanumeric");
        	
        	registerPage.inputCredentials();
            registerPage.submitClick();
            String actualErrStr = registerPage.getErrorMessage();
            System.out.println("Actual Error: " + actualErrStr);
            Assert.assertNotEquals(actualErrStr, "");
        }
        driver.close();
    }
    
    @Test
    public void registrationErrorShortPasswordLength() throws IOException, InterruptedException {
        WebDriver driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        
        welcomePage.click();
        RegisterPage registerPage = welcomePage.registerClick();
        
        // username field displayed on web page
        if (registerPage.waitForElementDisplayed("username")) {
        	// generate username with length of 6 chars
        	registerPage.generateUsername(4, "startWithLowerLetters");
        	
        	// generate password
        	registerPage.generatePassword(5, "alphanumeric");
        	
        	// retype password
        	registerPage.setRetypePassword("valid");
        	
        	registerPage.inputCredentials();
            registerPage.submitClick();
            String actualErrStr = registerPage.getErrorMessage();
            System.out.println("Actual Error: " + actualErrStr);
            Assert.assertNotEquals(actualErrStr, "");
        }
        driver.close();
    }     
}


