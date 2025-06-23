package com.myWork.Assessment.tests;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


/**
 * Entry point for running Cucumber scenarios.
 * <p>
 * Configured via {@link CucumberOptions} to locate feature files and glue code,
 * and to enable readable console output.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "summary"},
        features = "src/test/resources/features",
        glue = "com.myWork.Assessment.tests",
        monochrome = true

)

public class  RunCucumberTest {

}
