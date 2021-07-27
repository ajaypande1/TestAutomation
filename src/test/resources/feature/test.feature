Feature: Test feature

  Scenario: Search and verify result in google
    Given User launch browser and navigates to "https://google.com/"
    When enters "Majesco" in search box and click on Search button
    Then verifies "About Majesco" link is displayed in search result
    And clicks on "About Majesco" link
    And verifies that "About Majesco" page is displayed
    Then close the browser
	
  Scenario: Trigger an API and verify response data
    Given User triggers an API request and verifies response data with below details
      | ApiEndpoint                           | activity         | type      | participants | price | link                                     | key     | accessibility |
      | https://www.boredapi.com/api/activity | Learn Morse code | education | 1            | 0     | https://en.wikipedia.org/wiki/Morse_code | 3646173 | 0             |