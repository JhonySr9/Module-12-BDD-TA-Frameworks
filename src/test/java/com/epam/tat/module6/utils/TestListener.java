package com.epam.tat.module6.utils;

import com.epam.tat.module6.driver.DriverInitialization;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {

    private final Logger log = LogManager.getRootLogger();
    private int retryCount = 0;

    private static final int MAX_RETRY_COUNT = 1;

    public void onTestFailure(ITestResult result) {
        saveScreenshot(result);
        log.error("Test Failed: " + result.getName());
        retryOrSkipTest(result);
    }

    public boolean retry(ITestResult result) {
        if (!result.isSuccess() && retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            return true;
        }
        return false;
    }

    private void saveScreenshot(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String environment = System.getProperty("env");
        String browser = System.getProperty("browser");
        String screenshotPath = ".//target/screenshots/" + testName + "_" + environment + "_" + browser + "_" + getCurrentTimeAsString() + ".png";

        File screenCapture = ((TakesScreenshot) DriverInitialization.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(screenshotPath));
            log.error("Screenshot of failure has been saved in: " + screenshotPath);
        } catch (IOException e) {
            log.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private void retryOrSkipTest(ITestResult result) {
        if (retry(result)) {
            result.setStatus(ITestResult.FAILURE); // Mark the test as failed to trigger retry
        } else {
            result.setStatus(ITestResult.FAILURE); // If retries exhausted, mark as failure
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}
