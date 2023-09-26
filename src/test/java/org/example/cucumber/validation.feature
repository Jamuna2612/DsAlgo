Feature: Login Validation

  Background:
    Given User is in login page

  Scenario Outline: Positive test for Login validation
    Given Logged in with username <username> and password <password>
    When User click login
    Then "Invalid Username and Password" message is displayed

    Examples:
    |username|password|
    |Hemu|orange@456|


  Scenario: Validating click Elements
    Given User is in intro page
    When user click linkedList
    Then click intro link
    And navigate back to intro page
    And click tree link

