package org.example.testsObjects.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TimeComplexityPage {
    WebDriver driver;
    public TimeComplexityPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//a[text()='Practice Questions']")
    WebElement practice_Qus;
    @FindBy(xpath = "//a[text()='Try here>>>']")
    public WebElement tc_Tryhere;


    public TryEditorPage tcTryhereClick(){
        tc_Tryhere.click();
        return new TryEditorPage(driver);
    }

    public PracticeQuestionsPage practiceQusClick(){
        practice_Qus.click();
        return new PracticeQuestionsPage(driver);
    }
}
