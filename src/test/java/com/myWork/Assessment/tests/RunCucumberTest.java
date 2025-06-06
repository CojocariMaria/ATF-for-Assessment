package com.myWork.Assessment.tests;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "summary"},
        features = "src/test/resources/features",
        glue = "com.myWork.Assessment.tests",
        monochrome = true
        //tags = "@ui"
)

public class RunCucumberTest {

}
