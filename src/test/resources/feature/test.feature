Feature: Test feature

  Scenario: Test Scenarios
    Given User launch browser and navigates to "https://google.com/"
    When enters "Majesco" in search box and click on Search button
    Then verifies "About Majesco" link is displayed in search result
    And clicks on "About Majesco" link
    And verifies that "About Majesco" page is displayed
    Then close the browser