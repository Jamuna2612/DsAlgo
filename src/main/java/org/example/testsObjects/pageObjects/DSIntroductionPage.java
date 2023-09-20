package org.example.testsObjects.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DSIntroductionPage {
    WebDriver driver;
    public DSIntroductionPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//a[text()='Time Complexity']")
    public WebElement timeComplexity;

    @FindBy(xpath = "//a[text()='Data Structures']")
    private WebElement navbarDropdown;

    @FindBy(xpath = "//div[@class='dropdown-menu show']")
    private WebElement navbarDropdownList;


    public WebElement getNavbarDropdown() {
        return navbarDropdown;
    }

    public WebElement getNavbarDropdownList() {
        return navbarDropdownList;
    }

    public void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }


    public void selectByVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void navigateForward() {
        driver.navigate().forward();
    }

    public void navigateRefresh() {
        driver.navigate().refresh();
    }

    public TimeComplexityPage timeComplexityLinkClick(){
        //timeComplexity.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement timeComplexity1 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Time Complexity']")));
        js.executeScript("arguments[0].scrollIntoView(true)", timeComplexity1);
        js.executeScript("arguments[0].click()",timeComplexity1);
        return new TimeComplexityPage(driver);
    }


}
