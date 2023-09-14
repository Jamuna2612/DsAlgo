package org.example.cucumber;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(monochrome = true,
				 plugin ={"html:target/cucumber.html"},
				 features = "src/test/java/org/example/cucumber",
				 glue = "org.example.stepDefinitions",
				 tags = "@lowercaseUsernameRegister")

public class RegisterTestRunner extends AbstractTestNGCucumberTests {
	@Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
				
		return super.scenarios();
    }
}
