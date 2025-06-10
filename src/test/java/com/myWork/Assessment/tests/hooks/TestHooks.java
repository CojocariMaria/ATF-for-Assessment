package com.myWork.Assessment.tests.hooks;

import com.myWork.Assessment.tests.context.ScenarioContext;
import com.myWork.Assessment.tests.driver.PlaywrightFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;

/**
 * Cucumber hook class responsible for setting up and tearing down Playwright before and after UI scenarios.
 * <p>
 * The {@code @Before} and {@code @After} methods are scoped to scenarios tagged with <b>@ui</b>.
 * <p>
 * Stores scenario metadata in {@link ScenarioContext} and manages Playwright lifecycle via {@link PlaywrightFactory}.
 */
@Slf4j
public class TestHooks {

    /**
     * Default constructor. Required by Cucumber for hook instantiation.
     */
    public TestHooks() {
    }

    /**
     * Executed before each scenario tagged with {@code @ui}.
     * <p>
     * Initializes Playwright and stores the current scenario name in the {@link ScenarioContext}.
     *
     * @param scenario the Cucumber scenario object
     */
    @Before(value = "@ui")
    public void setup(Scenario scenario) {
        log.debug("Launching Playwright...");
        PlaywrightFactory.setup();
        ScenarioContext.getInstance().setScenarioName(scenario.getName());
        log.debug("Starting scenario: {}", scenario.getName());
    }

    /**
     * Executed after each scenario tagged with {@code @ui}.
     * <p>
     * Shuts down Playwright and clears the {@link ScenarioContext}.
     *
     * @param scenario the Cucumber scenario object
     */
    @After(value = "@ui")
    public void tearDown(Scenario scenario) {

        PlaywrightFactory.tearDown();
        ScenarioContext.getInstance().clear();
        log.debug("END: {}", scenario.getName());
    }

}
