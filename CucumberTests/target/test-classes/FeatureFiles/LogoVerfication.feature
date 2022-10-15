@tag
Feature: J.P.Morgan Logo Verification
  
  @tag1
  Scenario: Access J.P.Morgan link from Google and verify its logo
    Given User is on Google Home Page
    When Enters keyword as "J.P.Morgan"
    And Click Search
    And Click on First Search result
    Then Verify the Logo on the first search result page
