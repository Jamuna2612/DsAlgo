package org.example.testComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.example.resources.ExtentReporterNG;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentTest extentTest;
    ExtentReports extentReports = ExtentReporterNG.getReport();

    public void onTestStart(ITestResult result) {
        extentReports.createTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, " Test Passed");
    }

    public void onTestFail(ITestResult result) throws IOException, NoSuchFieldException, IllegalAccessException {
        extentTest.fail(result.getThrowable());
        driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        String path = getScreenshot(result.getMethod().getMethodName(), driver);
        extentTest.addScreenCaptureFromPath(path, result.getMethod().getMethodName());
    }

    public void onFinish(ITestContext context) {
        extentReports.flush();
    }


}
