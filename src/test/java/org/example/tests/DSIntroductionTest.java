package org.example.tests;

import org.example.testComponents.BaseTest;
import org.example.testsObjects.pageManager.PageObjectManager;
import org.example.testsObjects.pageObjects.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Time;
import java.time.Duration;
import java.util.ArrayList;

import static org.testng.Assert.assertTrue;

public class DSIntroductionTest extends BaseTest {
    PageObjectManager pom = new PageObjectManager();

    //******* Scenario 1  ********
    @Test
    public void user_Tried_ToAccess_AnyDSMenu_WithoutLoggedIn() throws IOException, InterruptedException {
        WebDriver driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        IntroPage introPage = welcomePage.click();
        // WebElement dsGetStartedLink = null;

        introPage.dsGetStartedLink.click();

        String notLoggedInMessage = introPage.notLoggedInMessage();
        Assert.assertEquals(notLoggedInMessage, "You are not logged in");
        // Assert.assertEquals("verify You are not Logged In",string, pom.getIntroPage().notLoggedInMessage());
    }

    //******* Scenario 2  ********
    @Test(dataProvider = "loginData")
    public void verify_user_ableTo_launch_DataStructuresIntroductionPage(String userName, String password) throws IOException, InterruptedException {
        WebDriver driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        LoginPage loginPage = welcomePage.login();

        loginPage.loginDataClick(userName, password);
        IntroPage introPage = loginPage.submit();
        String logInMessage = loginPage.signInMessage();

        introPage.dsGetStartedLink.click();
        assertTrue(driver.getTitle().contains("Data Structures-Introduction"));
    }

    //******* Scenario 3  ********
    @Test
    public void verify_User_after_they_clickedOn_Time_Complexity_link_they_should_navigated_to_Time_Complexity_Page() throws IOException, InterruptedException {
      DSIntroductionPage dsIntroductionPage = new DSIntroductionPage(driver);

        pom.getDsIntroductionPage();

        //TimeComplexityPage timeComplexityPage = dsIntroductionPage.timeComplexityLinkClick();
      // pom.getDsIntroductionPage().timeComplexityLinkClick();
        WebElement timecomplex = driver.findElement(By.xpath("//a[text()='Time Complexity']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", timecomplex);
        js.executeScript("arguments[0].click()",timecomplex);
        assertTrue(driver.getTitle().contains("Time Complexity"));
    }

    @Test(dataProvider = "loginData")
    public void verify_user_LandedTo_DSIntroductionPage_After_ClickedOn_GetStartedLink(String userName, String password) throws IOException, InterruptedException, AWTException {
        WebDriver driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.click();
        LoginPage loginPage = welcomePage.login();

        loginPage.loginDataClick(userName, password);
        IntroPage introPage = loginPage.submit();
        String logInMessage = loginPage.signInMessage();
        Assert.assertEquals(logInMessage, "You are logged in");


        introPage.dsGetStartedLink.click();
        assertTrue(driver.getTitle().contains("Data Structures-Introduction"));

        Thread.sleep(2000);
        //Scenario 3: assert that TimeComplexity link is active
        //DSIntroductionPage dsIntroductionPage = new DSIntroductionPage();
        DSIntroductionPage ds = new DSIntroductionPage(driver);

        WebElement timecomplex = driver.findElement(By.xpath("//a[text()='Time Complexity']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", timecomplex);

        //assertTrue(pom.getDsIntroductionPage().getTimeComplexity().isEnabled());
        js.executeScript("arguments[0].click()",timecomplex);
      // TimeComplexityPage timeComplexityPage = ds.timeComplexityLinkClick();

        // assert that user landed on TimeComplexity Page
        //assertTrue(driver.getTitle().contains("Time Complexity"));
        //assert that Try Here button is enabled
        //assertTrue(pom.getTimeComplexityPage().tc_Tryhere.isEnabled());
        WebElement tryHere = driver.findElement(By.xpath("//a[text()='Try here>>>']"));
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("arguments[0].scrollIntoView(true)", tryHere);
        js1.executeScript("arguments[0].click()",tryHere);
        //TryEditorPage tryEditorPage = pom.getTimeComplexityPage().tcTryhereClick();

        // assert that user landed on Assessment Page or TryEditor page
        assertTrue(driver.getTitle().contains("Assessment"));

        TryEditorPage tryEditorPage = new TryEditorPage(driver);
        //enter text print hello and click Run button
        Thread.sleep(3000);
        tryEditorPage.textEditor.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='CodeMirror-code']")));
        //WebElement ele = driver.findElement(By.xpath("//div[@class='CodeMirror-code']"));
        Actions a = new Actions(driver);
        JavascriptExecutor js2 = (JavascriptExecutor)driver;
//        js2.executeScript("arguments[0].textContent='print \"Hello\"'", element);
//        js2.executeScript("arguments[0].textContent='print \"Hello\"'",element);
        js2.executeScript("arguments[0].textContent ='print'",element);
    new Actions(driver).keyDown(Keys.ENTER).sendKeys("Print").sendKeys(Keys.SPACE).sendKeys("\"Hello\"").sendKeys(Keys.ENTER).perform();
        //a.sendKeys(Keys.SPACE).perform();
        //element.sendKeys("\"Hello\"");


      // element.sendKeys("Hello");
        //tryEditorPage.textEditor.sendKeys( "hello");
       // tryEditorPage.enterTextInTryEditor("print");
        //tryEditorPage.textEditor.sendKeys("print");
        Thread.sleep(2000);
        tryEditorPage.clickRunBtn();

        /*  ----- uncomment this 2 lines for valid python code test data
        String result = tryEditorPage.getOutputResult();
        Assert.assertEquals(result, "Hello");
        */
        Alert alert = driver.switchTo().alert();
		String invalidSyntaxAlert = alert.getText();
        System.out.println(invalidSyntaxAlert);
    }

    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{{"hope", "$imp1e123"}};
    }


}

