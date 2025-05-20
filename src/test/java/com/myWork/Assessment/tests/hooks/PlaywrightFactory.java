package com.myWork.Assessment.tests.hooks;

import com.microsoft.playwright.*;
import com.myWork.Assessment.tests.utils.ConfigLoader;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;


public class PlaywrightFactory {


    private static final Logger logger = LoggerFactory.getLogger(PlaywrightFactory.class);

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext browserContext;
    @Getter
    private static Page page;

    public static void setup() {
        logger.debug("Setting up Playwright and browser...");
        playwright = Playwright.create();

        String browserName = ConfigLoader.getProperty("browser");
        String url = ConfigLoader.getProperty("base.url");
        boolean headless = ConfigLoader.getBoolean("headless");
        int viewportWidth = Integer.parseInt(ConfigLoader.getProperty("viewport.width"));
        int viewportHeight = Integer.parseInt(ConfigLoader.getProperty("viewport.height"));


        switch (browserName.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            default:
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless)
                        .setExecutablePath(Paths
                                .get("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe")));

        }
        browserContext = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(viewportWidth, viewportHeight));
        page = browserContext.newPage();

        page.navigate(url);
        logger.info("Navigated to: " + url);
    }

    public static void tearDown() {

        logger.info("Closing browser...");
        if (browserContext != null) {
            browserContext.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }

    }

}
