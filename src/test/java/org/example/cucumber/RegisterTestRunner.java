package org.example.cucumber;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(monochrome = true,
				 plugin ={"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
				 features = "src/test/java/org/example/cucumber",
				 glue = "org.example.stepDefinitions",
				 tags = "@registerTests")

public class RegisterTestRunner extends AbstractTestNGCucumberTests {
	@Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
				
		return super.scenarios();
    }
}
