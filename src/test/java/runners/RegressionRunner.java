package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        tags = "@regression",
        glue = "stepDefinitions",
        plugin = {"pretty"},
        monochrome = true
)
public class RegressionRunner {
}
