package com.myWork.Assessment.tests.base;

import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.hooks.PlaywrightFactory;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

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
