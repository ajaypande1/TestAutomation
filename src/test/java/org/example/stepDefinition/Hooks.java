package org.example.stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import org.apache.log4j.PropertyConfigurator;

public class Hooks {
    @Before
    public void initialize(){
        //specifying Log4J properties file path
        PropertyConfigurator.configure(System.getProperty("user.dir") +"/src/test/resources/log4j.properties");

        //specifying chromedriver.exe file path
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/driver/chrome/chromedriver.exe");
    }

    @After
    public void tearDown(){
        //ToDo
       // generate reporting, capture screenshot for failed scenario step, etc.
    }
}
