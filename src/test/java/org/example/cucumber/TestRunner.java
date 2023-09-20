package org.example.cucumber;

import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(features = "src/test/java/org/example/cucumber",glue = "org.example.stepDefinitions", monochrome = true,
        plugin ={"html:target/cucumber.html"})
public class TestRunner {

}
