package org.example.testComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.example.resources.ExtentReporterNG;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentTest extentTest;
    ExtentReports extentReports = ExtentReporterNG.getReport();

    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, " Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
            //    File file = new File(System.getProperty("user.dir") + "\\reports\\" + result.getMethod().getMethodName()+ ".png");
                FileUtils.copyFile(source, new File(System.getProperty("user.dir") + "\\reports\\" + result.getMethod().getMethodName()+ ".png"));
                System.out.println("Successfully captured a screenshot");
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }

    }


    public void onFinish(ITestContext context) {
        extentReports.flush();
    }


}
