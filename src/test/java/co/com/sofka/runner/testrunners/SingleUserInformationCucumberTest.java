package co.com.sofka.runner.testrunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/singleUser.feature"},
        glue = {"co.com.sofka.stepdefinitions.stepdefinitionsclasses"},
        publish = true
)

public class SingleUserInformationCucumberTest {
}
