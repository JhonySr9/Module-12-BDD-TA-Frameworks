package com.epam.tat.module6.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DefaultElementHighlighter implements ElementHighlighter {

    @Override
    public void highlightElement(WebDriver driver, WebElement element, Long milliseconds) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String originalStyle = element.getDomAttribute("style");
        String newStyle = "border: 3px solid red; background: rgba(255, 255, 0, 0.3);";

        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
    }
}
