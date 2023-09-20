package org.example.testComponents;

import org.apache.commons.io.FileUtils;
import org.example.testsObjects.pageObjects.WelcomePage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public WebDriver initializeDriver() throws IOException {


        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/org/example/resources/GlobalData.properties");
        properties.load(fis);
        String browserValue = System.getProperty("browser")!=null ? System.getProperty("browser"): properties.getProperty("browser");
        if (browserValue.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
            driver = new ChromeDriver();
        }
        else if (browserValue.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\jamun\\IdeaProjects\\Practice\\src\\test\\resources\\geckodriver.exe");
            WebDriver driver = new FirefoxDriver();
        }
        else if (browserValue.equals("ie")) {
        /*System.setProperty("webdriver.gecko.driver", "D:\\Jamuna\\software\\geckodriver.exe");
        driver = new ChromeDriver();*/
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
        driver.manage().window().maximize();
        driver.get("https://dsportalapp.herokuapp.com/");
        return driver;
    }

/*
    @BeforeTest
    public WelcomePage launchApplication() throws IOException {
        driver = initializeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.goTo();
        return welcomePage;
    }
*/

    public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+ "//reports//"+ testcaseName+".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+ "//reports//"+ testcaseName+".png";
    }
}

