package org.example.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class test {
    WebDriver driver = null;
    private static final Logger LOG = Logger.getLogger(test.class);

    @Given("User launch browser and navigates to {string}")
    public void user_launch_browser_and_navigates_to(String url) {
        //maximize browser
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);

        //navigate to the user provided url
        driver.get(url);
        LOG.info("Navigating to url: "+url);
    }

    @When("enters {string} in search box and click on Search button")
    public void enters_in_search_box_and_click_on_search_button(String searchString) {
        WebElement searchBox = driver.findElement(By.name("q"));

        //enter user provided search string
        searchBox.sendKeys(searchString);

        //click on search button
        searchBox.submit();

        LOG.info("Searching for text '"+searchString+"'");
    }

    @Then("verifies {string} link is displayed in search result")
    public void verifies_link_is_displayed_in_search_result(String linkText) {
        LOG.info("Verifying '"+linkText+"' present on the search result page");
        Assert.assertTrue("Verifying link having specified text available on the page",driver.findElement(By.linkText(linkText)).isDisplayed());
    }

    @Then("clicks on {string} link")
    public void clicks_on_link(String linkText) {
        LOG.info("Clicking on '"+linkText+"'");
        driver.findElement(By.linkText(linkText)).click();
    }

    @Then("verifies that {string} page is displayed")
    public void verifies_that_page_is_displayed(String pageName) {
        LOG.info("Verifying '"+pageName+"' page is displayed");

        //verifying ""about-us" text in url
        String currentUrl=driver.getCurrentUrl();
        boolean urlVerification=currentUrl.contains("about-us");
        Assert.assertTrue("Verifying about-us present in page url",urlVerification);

        //verifying about-Majesco is the selected/active link item
        boolean islinkActive=driver.findElement(By.xpath("//a[@class='page__nav__menu__item__link active']/span[text()='About Majesco']")).isDisplayed();
        Assert.assertTrue("Verifying about-Majesco link is active in menu item",islinkActive);
    }

    @Then("close the browser")
    public void close_the_browser() {
        LOG.info("Closing the browser window.");
        //close the browser
        driver.quit();
    }
}
