package com.myWork.Assessment.tests.hooks;

import com.microsoft.playwright.Page;
import lombok.Data;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class CommonBase {
    private final Page page;
    private static final Logger logger = LoggerFactory.getLogger(CommonBase.class);
    public CommonBase() {

        PlaywrightFactory.setup();
        this.page = PlaywrightFactory.getPage();
    }

}
