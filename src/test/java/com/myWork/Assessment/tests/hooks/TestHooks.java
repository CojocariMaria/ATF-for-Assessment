package com.myWork.Assessment.tests.hooks;

import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.utils.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestHooks {

    private static final Logger logger = LoggerFactory.getLogger(TestHooks.class);

    public TestHooks() {
    }
    @Before (value = "@ui")
    public void setup(Scenario scenario){
        logger.info(">> Launching Playwright...");
        PlaywrightFactory.setup();
        Page page = PlaywrightFactory.getPage();
        ScenarioContext.getInstance().setScenarioName(scenario.getName());
        logger.info("[HOOK] Starting scenario: {}", scenario.getName());
    }

    @After (value = "@ui")
    public void tearDown(Scenario scenario){

        PlaywrightFactory.tearDown();
        ScenarioContext.getInstance().clear();
        logger.info("[HOOK] END: {}", scenario.getName());
    }


}
