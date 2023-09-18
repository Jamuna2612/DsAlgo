@registrationSuccessTests
@registerTests
Feature: Successful registration tests
  This feature contains registration successful test scenarios 

  Background:
    Given User opens Register Page
    Then User clicks Register button
    
  @alphabetsOnlyUsername
  Scenario: Username with alphabetes only
  Given User request to create "alphabetsonly" username
    Given User request to create "alphanumbericSpecialChars" password
    Given User inputs credentials created
    When User click submit button
    Then User registration is successful
    Then I close web driver    

  @alphanumericUsername
  Scenario: Alphanumeric Username
  Given User request to create "alphanumeric" username
    Given User request to create "alphanumbericSpecialChars" password
    Given User inputs credentials created
    When User click submit button
    Then User registration is successful
    Then I close web driver    

  @allowedSpecialChars
  Scenario: Allowed special char Username
  Given User request to create "allowedSpecialChars" username
    Given User request to create "alphanumbericSpecialChars" password
    Given User inputs credentials created
    When User click submit button
    Then User registration is successful
    Then I close web driver