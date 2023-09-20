package org.example.testsObjects.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PracticeQuestionsPage {
    WebDriver driver;
    public PracticeQuestionsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
