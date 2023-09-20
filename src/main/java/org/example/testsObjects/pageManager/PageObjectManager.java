package org.example.testsObjects.pageManager;

import org.example.testsObjects.pageObjects.*;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    WebDriver driver;
    private WelcomePage welcomePage;
    private IntroPage introPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private DSIntroductionPage dsIntroductionPage;
    private TimeComplexityPage timeComplexityPage;
    private ArrayPage arrayPage;
    private TryEditorPage tryEditorPage;

    public LoginPage getLoginPage() {
        return (loginPage==null)?loginPage=new LoginPage(driver):loginPage;
    }
    public WelcomePage getWelcomePage() {
        return (welcomePage==null)?welcomePage=new WelcomePage(driver):welcomePage;
    }

    public IntroPage getIntroPage() {
        return (introPage==null)?introPage=new IntroPage(driver):introPage;
    }

    public RegisterPage getRegisterPage() {
        return (registerPage==null)?registerPage=new RegisterPage(driver):registerPage;
    }

    public DSIntroductionPage getDsIntroductionPage() {
        return (dsIntroductionPage==null)?dsIntroductionPage=new DSIntroductionPage(driver):dsIntroductionPage;
    }

    public TimeComplexityPage getTimeComplexityPage() {
        return (timeComplexityPage==null)?timeComplexityPage=new TimeComplexityPage(driver):timeComplexityPage;
    }

    public TryEditorPage getTryEditorPage() {
        return (tryEditorPage==null)?tryEditorPage=new TryEditorPage(driver):tryEditorPage;
    }

    public ArrayPage getArrayPage() {
        return (arrayPage==null)?arrayPage=new ArrayPage():arrayPage;
    }
}
