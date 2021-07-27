package org.example.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue="org.example.stepDefinition",
        features={"src/test/resources/feature"}
)
public class runner {
}
