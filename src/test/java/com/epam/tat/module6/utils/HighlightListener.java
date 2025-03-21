package com.epam.tat.module6.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class HighlightListener implements WebDriverListener {
    private final ElementHighlighter highlighter;
    private final Long highLightTime = 200L;

    public HighlightListener(ElementHighlighter highlighter) {
        this.highlighter = highlighter;
    }

    @Override
    public void beforeClick(WebElement element) {
        highlighter.highlightElement(((RemoteWebElement) element).getWrappedDriver(), element, highLightTime);
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        highlighter.highlightElement(((RemoteWebElement) element).getWrappedDriver(), element, highLightTime);
    }
}
