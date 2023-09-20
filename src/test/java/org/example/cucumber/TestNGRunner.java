package org.example.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = "src/test/java/org/example/cucumber",glue = "org.example.stepDefinitions.stepDefinitionImpl_DSIntro", monochrome = true,
        plugin ={"html:target/cucumber.html"})
public class TestNGRunner extends AbstractTestNGCucumberTests {
//    @Override
//    @DataProvider(parallel = false)
//    public Object[][] scenarios() {
//
//        return super.scenarios();
    }




