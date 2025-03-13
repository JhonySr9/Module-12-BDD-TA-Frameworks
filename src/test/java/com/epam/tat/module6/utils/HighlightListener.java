package com.epam.tat.module6.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class HighlightListener implements WebDriverListener {

    private void highlight(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String originalStyle = element.getDomAttribute("style");
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid red; background: rgba(255, 255, 0, 0.3);');", element); // Color for the highlight.

        try {
            Thread.sleep(200); // Sleep to see the highlight.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
    }

    @Override
    public void beforeClick(WebElement element) {
        highlight(((RemoteWebElement) element).getWrappedDriver(), element);
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        highlight(((RemoteWebElement) element).getWrappedDriver(), element);
    }
}
