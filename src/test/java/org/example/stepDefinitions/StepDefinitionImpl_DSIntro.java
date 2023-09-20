package org.example.stepDefinitions;

import java.io.IOException;
import java.time.Duration;

import com.beust.ah.A;
import org.example.testComponents.BaseTest;
import org.example.testsObjects.pageManager.PageObjectManager;
import org.example.testsObjects.pageObjects.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertTrue;

public class StepDefinitionImpl_DSIntro extends BaseTest {
	PageObjectManager pom = new PageObjectManager();
	public WebDriver driver;
	public LoginPage loginPage;
	public IntroPage introPage;
	public DSIntroductionPage dsIntroPage;
	public TimeComplexityPage timeComplexityPage;

	//Scenario 1
	@Given("User is not signed in and navigates to home page")
	public void verify_User_Should_see_You_Are_Not_LoggedIn_Message() throws IOException, InterruptedException {
		driver = initializeDriver();
		WelcomePage welcomePage = new WelcomePage(driver);
		introPage =welcomePage.click();

	}

	@When("User clicks on Get Started link")
	public void user_clicks_on_get_started_link() {
		introPage.dsGetStartedLink.click();
	}

	@Then("You are not logged in message is displayed")
	public void you_are_not_logged_in_message_is_displayed(String string) {
		//Assert.assertEquals("verify You are not Logged In",string, pom.getIntroPage().notLoggedInMessage());
		Assert.assertEquals("verify You are not Logged In",string, introPage.notLoggedInMessage());
	}

	//Scenario 2
	@Given("User is in the Home page")
	public void user_is_in_the_home_page() throws IOException, InterruptedException {
		driver = initializeDriver();
		WelcomePage welcomePage = new WelcomePage(driver);
		introPage =welcomePage.click();
	}

	@When("The user clicks on Sign In button")
	public void the_user_clicks_on_sign_in_button() {
		//WelcomePage welcomePage = new WelcomePage();
		LoginPage loginPage = pom.getWelcomePage().login();

	}

	@When("User enters username hope and password $impl1e123")
	public void user_enters_username_hope_and_password_$impl1e123(String userName, String password) throws InterruptedException {
		pom.getLoginPage().loginDataClick(userName, password);
		Thread.sleep(2000);
	}

	@When("User clicks on login button")
	public void user_clicks_on_login_button() {
		IntroPage introPage = loginPage.submit();

	}

	@When("User clicks on Get Started button of Data Structure")
	public void user_clicks_on_get_started_button_of_data_structure() {
		introPage.dsGetStartedLink.click();
	}

	@Then("The user should land in Data Structures Introduction Page")
	public void the_user_should_land_in_data_structures_introduction_page() {
		assertTrue(driver.getTitle().contains("Data Structures-Introduction"));
	}


	//Scenario 3
	@Given("The user is in the Data structures-Introduction page")
	public void the_user_is_in_the_data_structures_introduction_page() {
		System.out.println("ASK THIS TO THE TEAM");
		//want to check the scenarios are executes continuously
	}

	@When("The user clicks on Time Complexity link")
	public void the_user_clicks_on_time_complexity_link() {
		pom.getDsIntroductionPage();
		//pom.getDsIntroductionPage().timeComplexity.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", pom.getDsIntroductionPage().timeComplexity);
		js.executeScript("arguments[0].click()",pom.getDsIntroductionPage().timeComplexity);
	}

	@Then("The user should be redirected to Time Complexity page")
	public void the_user_should_be_redirected_to_time_complexity_page() {
		assertTrue(driver.getTitle().contains("Time Complexity"));
	}

	//Scenario 4
	@Given("The user is in the Time Complexity page")
	public void the_user_is_in_the_time_complexity_page() throws IOException {
		pom.getTimeComplexityPage();
	}

	@When("The user clicks on Practice Question link")
	public void the_user_clicks_on_practice_question_link() {
		pom.getTimeComplexityPage().practiceQusClick();
	}

	@Then("The user should be redirected to Practice Questions Page")
	public void the_user_should_be_redirected_to_practice_questions_page() {
		assertTrue(driver.getTitle().contains("Practice Questions"));
	}

	//Scenario 5
	@Given("Given The user is in the Time Complexity page")
	public void The_user_is_in_the_Time_Complexity_page() throws IOException {
		pom.getTimeComplexityPage();
	}
	@When("The user clicks on Try Here button link")
	public void the_user_clicks_on_try_here_button_link() {
		pom.getTimeComplexityPage().tcTryhereClick();
	}

	@Then("The user should be redirected to a page having an tryEditor with a Run button to test")
	public void the_user_should_be_redirected_to_a_page_having_an_try_editor_with_a_run_button_to_test() {
		assertTrue(driver.getTitle().contains("Assessment"));
	}

	//Scenario 6
	@Given("The user is in the Assessment page")
	public void the_user_is_in_the_assessment_page() throws IOException {
		pom.getTryEditorPage();
	}

	@When("The user enters valid python print statement <validPythonCode>")
	public void the_user_enters_valid_python_print_statement_valid_python_code() {
		TryEditorPage tryEditorPage = new TryEditorPage(driver);
		tryEditorPage.textEditor.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='CodeMirror-code']")));
		Actions a = new Actions(driver);
		JavascriptExecutor js2 = (JavascriptExecutor)driver;
		js2.executeScript("arguments[0].textContent ='print'",element);
		new Actions(driver).keyDown(Keys.ENTER).sendKeys("print").sendKeys(Keys.SPACE).sendKeys("\"Hello\"").sendKeys(Keys.ENTER).perform();
	}

	@When("User Clicks on Run Button")
	public void user_clicks_on_run_button() {
		TryEditorPage tryEditorPage = new TryEditorPage(driver);
		tryEditorPage.clickRunBtn();
	}

	@Then("The user should see the run result below the Run button")
	public void the_user_should_see_the_run_result_below_the_run_button() {
		TryEditorPage tryEditorPage = new TryEditorPage(driver);
		String result = tryEditorPage.getOutputResult();
		Assert.assertEquals(result, "Hello");
	}

	//Scenario 7
	@Given("The user is in the Try Editor page")
	public void the_user_is_in_the_Try_Editor_page() throws IOException {
		pom.getTryEditorPage();
	}
	@When("The user enters invalid python print statement <invalidPythonCode>")
	public void the_user_enters_invalid_python_print_statement_invalid_python_code() {
		TryEditorPage tryEditorPage = new TryEditorPage(driver);
		tryEditorPage.textEditor.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='CodeMirror-code']")));
		Actions a = new Actions(driver);
		JavascriptExecutor js2 = (JavascriptExecutor)driver;
		js2.executeScript("arguments[0].textContent ='print'",element);
		new Actions(driver).keyDown(Keys.ENTER).sendKeys("Print").sendKeys(Keys.SPACE).sendKeys("\"Hello\"").sendKeys(Keys.ENTER).perform();
	}

	@Then("The user should see the SyntaxError popup")
	public void the_user_should_see_the_syntax_error_popup() {
		TryEditorPage tryEditorPage = new TryEditorPage(driver);
		tryEditorPage.clickRunBtn();
		Alert alert = driver.switchTo().alert();
		String invalidSyntaxAlert = alert.getText();
		System.out.println(invalidSyntaxAlert);
	}

}
