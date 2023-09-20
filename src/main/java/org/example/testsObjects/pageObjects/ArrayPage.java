package org.example.testsObjects.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ArrayPage {
    WebDriver driver;
    public void ArrayPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath ="//a[text()='Arrays in Python']")
    WebElement arr_ArraysInPythonLink;

    @FindBy(xpath ="//a[text()='Arrays Using List']")
    WebElement arr_ArraysUsingListLink;

    @FindBy(xpath ="//a[text()='Basic Operations in Lists']")
    WebElement arr_BasicOperationListLink;

    @FindBy(xpath ="//a[text()='Applications of Array']")
    WebElement arr_ApplicationOfArrLink;

}
