package org.example.tests;

import java.io.IOException;

import org.example.testComponents.BaseTest;
import org.example.testsObjects.pageObjects.ArrayPage;
import org.example.testsObjects.pageObjects.IntroPage;
import org.example.testsObjects.pageObjects.LoginPage;
import org.example.testsObjects.pageObjects.WelcomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ArrayValidationTest extends BaseTest {
	WebDriver driver;
	
	@Test
	public void noLoginArrayPageTest() throws IOException, InterruptedException {
        driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        
        ArrayPage arrayPage = new ArrayPage(driver);
        arrayPage.clickGetStarted();
        String errorMsg = arrayPage.getErrorMessage();
        System.out.println(errorMsg);
        Assert.assertEquals(errorMsg, "You are not logged in");
        driver.close();
        driver.quit();
	}
	
	@Test(dataProvider = "userCredentialsData")
	public void arrayTopicWithLoginString(String username, String password) throws IOException, InterruptedException {
        driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        
        // using welcomePage get LoginPage class reference and login to web page
        LoginPage loginPage = welcomePage.login();
        loginPage.loginDataClick(username, password);
        IntroPage introPage = loginPage.submit();
        String logInMessage = loginPage.signInMessage();
        Assert.assertEquals(logInMessage, "You are logged in");

        ArrayPage arrayPage = new ArrayPage(driver);
        arrayPage.clickGetStarted();
        boolean onArrayTopicsPage = arrayPage.onArrayTopicsPage();
        
//        String errorMsg = arrayPage.getErrorMessage();
        System.out.println("onArrayTopicsPage: " + onArrayTopicsPage);
        Assert.assertEquals(onArrayTopicsPage, true);
        
        introPage.signOutClick();
        String actualMessage = introPage.signOutMessage();
        Assert.assertEquals(actualMessage, "Logged out successfully");        
        
        driver.close();
        driver.quit();
	}
	
	@Test(dataProvider = "testInputData")
	// add topic argument to the test for data driven topic and use same function
	public void testArraysPageTopics(String username, String password, String topic, String pythonCodeType) throws IOException, InterruptedException {
        driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        
        // using welcomePage get LoginPage class reference and login to web page
        LoginPage loginPage = welcomePage.login();
        loginPage.loginDataClick(username, password);
        IntroPage introPage = loginPage.submit();
        String logInMessage = loginPage.signInMessage();
        Assert.assertEquals(logInMessage, "You are logged in");

        ArrayPage arrayPage = new ArrayPage(driver);
        arrayPage.clickGetStarted();
        boolean onArrayTopicsPage = arrayPage.onArrayTopicsPage();
        System.out.println("onArrayTopicsPage: " + onArrayTopicsPage);
        Assert.assertEquals(onArrayTopicsPage, true);
        
        // click user provided topic page
        arrayPage.clickArrayTopic(topic);
        String titleStr = arrayPage.getTopicPageTitle(topic);
        System.out.println("Topic page title: " + titleStr);
        
        // click TryHere button
        arrayPage.scrollToElementAndClick("tryHere");
        
        //Enter python code in text Editor and press Run
        String output = "";
        arrayPage.updateTextEditorAndClickRun(pythonCodeType);
        arrayPage.clickRunBtn();
        if (pythonCodeType.contains("CodeError")) {
        	output = arrayPage.getPythonCodeErrorOutput();
        }else {
        	output = arrayPage.getPythonCodeOutput();
        }
    	System.out.println("python code output: " + output);
        
        // go back on web page window
        driver.navigate().back();
        
        introPage.signOutClick();
        String actualMessage = introPage.signOutMessage();
        Assert.assertEquals(actualMessage, "Logged out successfully");        
        
        driver.close();
        driver.quit();
	}
	
    @DataProvider
    public Object[][] testInputData() {
        return new Object[][]{
        	{"mike555", "Open4You", "Arrays in Python", "pythonCodeError"},
        	{"mike555", "Open4You", "Arrays in Python", "pythonCodeSuccess"},
        	{"mike555", "Open4You", "Arrays Using List", "pythonCodeSuccess"},
        	{"mike555", "Open4You", "Arrays Using List", "pythonCodeError"}
        	};
    }
    
    @DataProvider
    public Object[][] userCredentialsData() {
        return new Object[][]{
        	{"mike555", "Open4You"}
        };
    }	
    
}
