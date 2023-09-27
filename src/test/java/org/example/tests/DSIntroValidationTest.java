package org.example.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;

import org.example.testComponents.BaseTest;
import org.example.testsObjects.pageObjects.DSIntroPage;
import org.example.testsObjects.pageObjects.IntroPage;

import org.example.testsObjects.pageObjects.LoginPage;
import org.example.testsObjects.pageObjects.WelcomePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;

public class DSIntroValidationTest extends BaseTest {

	WebDriver driver;
	
	@Test
	public void noLoginDSIntroPageTest() throws IOException, InterruptedException {
        driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        IntroPage introPage = welcomePage.click();
        
        //introPage.getStartedHome_ds();
        
        DSIntroPage dsIntroPage = new DSIntroPage(driver);
        dsIntroPage.clickGetStarted();
        
        String errorMsg = dsIntroPage.getErrorMessage();
        System.out.println(errorMsg);
        Assert.assertEquals(errorMsg, "You are not logged in");
        driver.close();
        driver.quit();
	}
	
	@Test(dataProvider = "userCredentialsData" )
	public void tryEditorFlowForDSIntro(String username, String password) throws IOException, InterruptedException {
		driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        
        
        LoginPage loginPage = welcomePage.login();
        loginPage.loginDataClick(username, password);
        loginPage.submit();
        String logInMessage = loginPage.signInMessage();
        Assert.assertEquals(logInMessage, "You are logged in");
        
        DSIntroPage dsIntroPage = new DSIntroPage(driver);
        dsIntroPage.clickGetStarted();
        
        String pageTitle = driver.getTitle();
        System.out.println("Page Title after LoggedIn : " + pageTitle);
        Assert.assertTrue(pageTitle.contains("Data Structures-Introduction"));
        
    
        
        WebElement timecomplex = driver.findElement(By.xpath("//a[text()='Time Complexity']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", timecomplex);
        js.executeScript("arguments[0].click()",timecomplex);
        Assert.assertTrue(driver.getTitle().contains("Time Complexity"));      
        
        driver.close();
        driver.quit();

	}
	
	@DataProvider
    public Object[][] userCredentialsData() {
        return new Object[][]{
        	{"hope", "$imp1e123"}
        };
    }	
	
	
	@Test(dataProvider = "testInputData")
	public void verifyTryEditorValidation(String username, String password, String topic, String codeType) throws IOException, InterruptedException {
		
		driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        
        
        LoginPage loginPage = welcomePage.login();
        loginPage.loginDataClick(username, password);
        IntroPage introPage = loginPage.submit();
        String logInMessage = loginPage.signInMessage();
        Assert.assertEquals(logInMessage, "You are logged in");
        
       
        DSIntroPage dsIntroPage = new DSIntroPage(driver);
        dsIntroPage.clickGetStarted();
        
        String pageTitle = driver.getTitle();
        System.out.println("Page Title after LoggedIn : " + pageTitle);
        Assert.assertTrue(pageTitle.contains("Data Structures-Introduction"));
        
        dsIntroPage.scrollToElementAndClick(topic);
        dsIntroPage.scrollToElementAndClick("tryHere");
        
        String output = "";
        dsIntroPage.updateTextEditorAndClickRun(codeType);
        dsIntroPage.clickRunBtn();
        
        if (codeType.contains("CodeError")) {
        	output = dsIntroPage.getPythonCodeErrorOutput();
        }else {
        	output = dsIntroPage.getPythonCodeOutput();
        }
    	System.out.println("python code output: " + output);
    	
    	 driver.navigate().back();
         
         introPage.signOutClick();
         String actualMessage = introPage.signOutMessage();
         Assert.assertEquals(actualMessage, "Logged out successfully");
         driver.close();
         driver.quit();
        
        /*
        WebElement timecomplex = driver.findElement(By.xpath("//a[text()='Time Complexity']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", timecomplex);
        js.executeScript("arguments[0].click()",timecomplex);
        assertTrue(driver.getTitle().contains("Time Complexity"));     
        
        WebElement tryHere = driver.findElement(By.xpath("//a[text()='Try here>>>']"));
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("arguments[0].scrollIntoView(true)", tryHere);
        js1.executeScript("arguments[0].click()",tryHere);

        assertTrue(driver.getTitle().contains("Assessment"));
        
       
        
        dsIntroPage.textEditor.click();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='CodeMirror-code']")));
        
       // Actions a = new Actions(driver);
        JavascriptExecutor js2 = (JavascriptExecutor)driver;

        js2.executeScript("arguments[0].textContent ='print'",element);
        new Actions(driver).sendKeys("Print").sendKeys(Keys.SPACE).sendKeys("\"Hello\"").sendKeys(Keys.ENTER).perform();
        
        Thread.sleep(2000);
        dsIntroPage.clickRunBtn();

        /*  ----- uncomment this 2 lines for valid python code test data
        String result = tryEditorPage.getOutputResult();
        Assert.assertEquals(result, "Hello");
        */
        
        
//        Alert alert = driver.switchTo().alert();
//		String invalidSyntaxAlert = alert.getText();
//        System.out.println(invalidSyntaxAlert);
//        alert.accept();
	
	}
	
	@DataProvider
    public Object[][] loginData() {
        return new Object[][]{{"hope", "$imp1e123"}};
    }
	
	@DataProvider
    public Object[][] testInputData() {
        return new Object[][]{
        	{"hope", "$imp1e123", "Time Complexity", "pythonCodeError"},
        	{"hope", "$imp1e123", "Time Complexity", "pythonCodeSuccess"}
        	
        	};
    }	
	
}
