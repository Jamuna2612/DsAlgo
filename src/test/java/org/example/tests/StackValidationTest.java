package org.example.tests;

import java.io.IOException;

import org.example.testComponents.BaseTest;
import org.example.testsObjects.pageObjects.IntroPage;
import org.example.testsObjects.pageObjects.LoginPage;
import org.example.testsObjects.pageObjects.StackPage;
import org.example.testsObjects.pageObjects.WelcomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StackValidationTest extends BaseTest{
	
	WebDriver driver;
	
	@Test
	public void noLoginStackPageTest() throws IOException, InterruptedException {
		driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        
        StackPage stackPage = new StackPage(driver);
        stackPage.clickGetStarted();
        String errorMsg = stackPage.getErrorMessage();
        System.out.println(errorMsg);
        Assert.assertEquals(errorMsg, "You are not logged in");
        driver.close();
        driver.quit(); 
	}
	
	@Test(dataProvider = "testInputData")
	public void tryEditorFlowForStack(String username, String password, String topic, String codeType) throws IOException, InterruptedException {
		 driver = initializeDriver();
	        WelcomePage welcomePage = new WelcomePage(driver);
	        welcomePage.click();
	        
	        LoginPage loginPage = welcomePage.login();
	        loginPage.loginDataClick(username, password);
	        IntroPage introPage = loginPage.submit();
	        String logInMessage = loginPage.signInMessage();
	        Assert.assertEquals(logInMessage, "You are logged in");
	        
	        StackPage stackPage = new StackPage(driver);
	        stackPage.clickGetStarted();
	        boolean onStackPage =  stackPage.onStackTopicsPage();
	        System.out.println("onStackTopicsPage: " + onStackPage);
	        Assert.assertEquals(onStackPage, true);
	        
	        stackPage.clickStackTopic(topic);
	        String titleStr = stackPage.getTopicPageTitle(topic);
	        System.out.println("Topic page title: " + titleStr);
	        
	        stackPage.scrollToElementAndClick("tryHere");
	        
	        String output = "";
	        stackPage.updateTextEditorAndClickRun(codeType);
	        stackPage.clickRunBtn();
	        
	        if (codeType.contains("CodeError")) {
	        	output = stackPage.getPythonCodeErrorOutput();
	        }else {
	        	output = stackPage.getPythonCodeOutput();
	        }
	    	System.out.println("python code output: " + output);
	    	
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
        	{"hope", "$imp1e123", "Operations in Stack", "pythonCodeError"},
        	{"hope", "$imp1e123", "Operations in Stack", "pythonCodeSuccess"},
        	{"hope", "$imp1e123", "Implementation", "pythonCodeSuccess"},
        	{"hope", "$imp1e123", "Implementation", "pythonCodeError"}
        	};
    }	

}
