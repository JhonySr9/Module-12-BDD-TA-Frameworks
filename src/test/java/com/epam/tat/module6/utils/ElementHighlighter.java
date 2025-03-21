package com.epam.tat.module6.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ElementHighlighter {
    void highlightElement(WebDriver driver, WebElement element, Long milliseconds);
}
