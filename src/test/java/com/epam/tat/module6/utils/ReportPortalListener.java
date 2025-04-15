package com.epam.tat.module6.utils;

import com.epam.reportportal.service.ReportPortal;
import com.epam.tat.module6.driver.DriverInitialization;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.util.Date;

public class ReportPortalListener implements ScreenshotReporter {

    public void captureScreenshot(String message) {
        try {
            File screenshot = ((TakesScreenshot) DriverInitialization.getDriver()).getScreenshotAs(OutputType.FILE);

            // Send screenshot to Report Portal
            ReportPortal.emitLog(message, "INFO", new Date(), screenshot);
        } catch (Exception e) {
            LogManager.getRootLogger().error("Failed to capture and send screenshot to Report Portal" + e.getMessage());
        }
    }
}
