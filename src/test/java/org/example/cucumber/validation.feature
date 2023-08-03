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