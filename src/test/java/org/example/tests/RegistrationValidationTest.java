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

public class RegistrationValidationTest extends BaseTest {

    @Test
    public void registerValidation() throws IOException, InterruptedException {
        WebDriver driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        
        welcomePage.click();
        RegisterPage registerPage = welcomePage.registerClick();
        
        // username field displayed on webpage
        if (registerPage.waitForElementDisplayed("username")) {
        	// generate username with length of 6 chars
        	registerPage.generateUsername(4, "startWithLowerLetters");
        	
        	// generate password
        	registerPage.generatePassword(8, "alphanumeric");

        	registerPage.inputCredentials();
            IntroPage introPage = registerPage.submitClick();
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

        String actual = driver.findElement(By.xpath("//div[contains(text(),'Logged')]")).getText();
        Assert.assertEquals(actual, "Logged out successfully");
        driver.close();
    }
    
    @Test(dataProvider = "registerData")
    public void registerValidationWithDataProvider(String username, String password, String retypePassword) throws IOException, InterruptedException {
        WebDriver driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        
        welcomePage.click();
        RegisterPage registerPage = welcomePage.registerClick();
        
        // username field displayed on webpage
        if (registerPage.waitForElementDisplayed("username")) {
        	System.out.println("Username: " + username + " Password: " + password + " Retypepassword: " + retypePassword);
        	
        	// set user provided username password
        	registerPage.setUserName(username);
        	registerPage.setPassword(password);
        	registerPage.setRetypePassword(retypePassword);
        	
        	registerPage.inputCredentials();
            IntroPage introPage = registerPage.submitClick();
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

        String actual = driver.findElement(By.xpath("//div[contains(text(),'Logged')]")).getText();
        Assert.assertEquals(actual, "Logged out successfully");
        driver.close();
    }    

    @DataProvider
    public Object[][] registerData() {
        return new Object[][]{{"Camilinser", "1e@43!@@#!221!@242", "1e@43!@@#!221!@242"}};

    }
}

