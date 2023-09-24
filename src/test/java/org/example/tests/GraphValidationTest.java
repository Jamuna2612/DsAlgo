package org.example.tests;

import java.io.IOException;

import org.example.testComponents.BaseTest;
import org.example.testsObjects.pageObjects.GraphPage;
import org.example.testsObjects.pageObjects.IntroPage;
import org.example.testsObjects.pageObjects.LoginPage;
import org.example.testsObjects.pageObjects.WelcomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GraphValidationTest extends BaseTest {
	WebDriver driver;
	
	@Test
	public void noLoginGraphPageTest() throws IOException, InterruptedException {
        driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        
        GraphPage graphPage = new GraphPage(driver);
        graphPage.clickGetStarted();
        String errorMsg = graphPage.getErrorMessage();
        System.out.println(errorMsg);
        Assert.assertEquals(errorMsg, "You are not logged in");
	}
	
	@Test(dataProvider = "userCredentialsData")
	public void graphTopicWithLogin(String username, String password) throws IOException, InterruptedException {
        driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        
        // using welcomePage get LoginPage class reference and login to web page
        LoginPage loginPage = welcomePage.login();
        loginPage.loginDataClick(username, password);
        IntroPage introPage = loginPage.submit();
        String logInMessage = loginPage.signInMessage();
        Assert.assertEquals(logInMessage, "You are logged in");

        GraphPage graphPage = new GraphPage(driver);
        graphPage.clickGetStarted();
        boolean onGraphTopicsPage = graphPage.onGraphTopicsPage();
        
        System.out.println("onGraphTopicsPage: " + onGraphTopicsPage);
        Assert.assertEquals(onGraphTopicsPage, true);
        
        introPage.signOutClick();
        String actualMessage = introPage.signOutMessage();
        Assert.assertEquals(actualMessage, "Logged out successfully");        
	}
	
	@Test(dataProvider = "testInputData")
	// add topic argument to the test for data driven topic and use same function
	public void testGraphPageTopics(String username, String password, String topic, String codeType) throws IOException, InterruptedException {
        driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        
        // using welcomePage get LoginPage class reference and login to web page
        LoginPage loginPage = welcomePage.login();
        loginPage.loginDataClick(username, password);
        IntroPage introPage = loginPage.submit();
        String logInMessage = loginPage.signInMessage();
        Assert.assertEquals(logInMessage, "You are logged in");

        GraphPage graphPage = new GraphPage(driver);
        graphPage.clickGetStarted();
        boolean onGraphTopicsPage = graphPage.onGraphTopicsPage();
        System.out.println("onGraphTopicsPage: " + onGraphTopicsPage);
        Assert.assertEquals(onGraphTopicsPage, true);
        
        // click user provided topic page
        graphPage.clickGraphTopic(topic);
        String titleStr = graphPage.getTopicPageTitle(topic);
        System.out.println("Topic page title: " + titleStr);
        
        // click TryHere button
        graphPage.scrollToElementAndClick("tryHere");
        
        //Enter python code in text Editor and press Run
        String output = "";
        graphPage.updateTextEditorAndClickRun(codeType);
        graphPage.clickRunBtn();
        
        if (codeType.contains("SyntaxError")) {
        	output = graphPage.getPythonSyntaxErrorOutput();
        }else {
        	output = graphPage.getPythonValidSyntaxOutput();
        }
    	System.out.println("python code output: " + output);
    	
        // go back on web page window
        driver.navigate().back();
        
        introPage.signOutClick();
        String actualMessage = introPage.signOutMessage();
        Assert.assertEquals(actualMessage, "Logged out successfully");        
	}
	
    @DataProvider
    public Object[][] testInputData() {
        return new Object[][]{
        	{"mike555", "Open4You", "Graph", "pythonSyntaxError"},
        	{"mike555", "Open4You", "Graph", "pythonValidSyntax"},
        	{"mike555", "Open4You", "Graph Representations", "pythonValidSyntax"},
        	{"mike555", "Open4You", "Graph Representations", "pythonSyntaxError"}
        	};
    }	
    
    @DataProvider
    public Object[][] userCredentialsData() {
        return new Object[][]{
        	{"mike555", "Open4You"}
        };
    }    
}
