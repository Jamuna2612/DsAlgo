package org.example.testsObjects.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TryEditorPage {
    WebDriver driver;
    public  TryEditorPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //@FindBy(xpath="(//div/pre/span['<span cm-text=\"\">\u200B</span>'])[2]")
    // xpath = "//div[@class='CodeMirror-code']/div/pre/span/span"
    @FindBy(xpath = "//div[@class='CodeMirror-code']")
   public WebElement textEditor;

    @FindBy(xpath = "//button[text()='Run']")
   public WebElement runBtn;

    @FindBy(xpath ="//pre[@id='output']")
    WebElement output;

    public void enterTextInTryEditor(WebElement element,String text){
        textEditor.sendKeys(text);
  }


    public void clickRunBtn(){
        runBtn.click();
    }

    public String getOutputResult() {
        String result = output.getText();
        return result;
    }

}
