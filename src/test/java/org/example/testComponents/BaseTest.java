package org.example.testComponents;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

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
        String browserValue = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");
        if (browserValue.equals("chrome")) {
            //System.setProperty("webdriver.chrome.driver", "C:\\Users\\jamun\\IdeaProjects\\Practice\\src\\test\\resources\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserValue.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\jamun\\IdeaProjects\\Practice\\src\\test\\resources\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browserValue.equals("ie")) {
        /*System.setProperty("webdriver.gecko.driver", "D:\\Jamuna\\software\\geckodriver.exe");
        driver = new ChromeDriver();*/
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
        driver.manage().window().maximize();
        driver.get("https://dsportalapp.herokuapp.com/");
        return driver;
    }


    public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+ "//reports//"+ testcaseName+".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+ "//reports//"+ testcaseName+".png";
    }

    public void actionMethod(){
        Actions action = new Actions(driver);
        action.keyDown(Keys.ENTER).sendKeys("print").sendKeys(Keys.SPACE).sendKeys("\"Hello\"").sendKeys(Keys.ENTER).perform();

    }
    public void navigateBack(int noOfTimes){
        for (int i = 0; i < noOfTimes; i++) {
            driver.navigate().back();
        }
    }
}

