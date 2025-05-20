package com.myWork.Assessment.tests.hooks;

import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.utils.ScenarioContext;
import io.cucumber.java.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestHooks {

    private static final Logger logger = LoggerFactory.getLogger(TestHooks.class);
    private final ScenarioContext scenarioContext;
    private Page page;


    public TestHooks(ScenarioContext scenarioContext, Page page) {
        this.scenarioContext = scenarioContext;
        this.page = page;
    }
    @Before
    public void setup(){
        logger.info(">> Launching Playwright...");
        logger.info("\n[HOOK] Starting scenario: " + scenarioContext.getName());
        PlaywrightFactory.setup();
        page = PlaywrightFactory.getPage();

    }
    @After
    public void tearDown(){
        logger.info("[HOOK] END: " + scenarioContext.getName());
        PlaywrightFactory.tearDown();
        ScenarioContext.getInstance().clear();
    }


}
