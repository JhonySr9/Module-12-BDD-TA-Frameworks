package com.epam.tat.module6.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class ScreenshotReportPortal implements WebDriverListener {

    private final ScreenshotReporter reporter;

    public ScreenshotReportPortal(ScreenshotReporter reporter) {
        this.reporter = reporter;
    }

    @Override
    public void afterClick(WebElement element) {
        WebDriverListener.super.afterClick(element);
        reporter.captureScreenshot("After click element " + element);
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        reporter.captureScreenshot("After send text to " + element);
    }
}
