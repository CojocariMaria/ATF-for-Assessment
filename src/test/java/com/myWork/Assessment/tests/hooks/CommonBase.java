package com.myWork.Assessment.tests.hooks;

import com.microsoft.playwright.Page;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class responsible for initializing and providing access to a Playwright {@link Page} instance.
 * <p>
 * It calls the {@link PlaywrightFactory#setup()} method to initialize the Playwright runtime and retrieves
 * the current page using {@link PlaywrightFactory#getPage()}.
 * <p>
 * Useful for shared setup logic that needs access to a browser page.
 */
@Slf4j
@Getter
public class CommonBase {
    private final Page page;
    public CommonBase() {

        PlaywrightFactory.setup();
        this.page = PlaywrightFactory.getPage();
        log.debug("Playwright initialized");

        log.debug("Page is obtained from the PlaywrightFactory. Current URL: {}", page.url());
    }

}
