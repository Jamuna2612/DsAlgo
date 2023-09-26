@registerTests
Feature: Registration Negative Tests
  This feature contains registration validation and error scenarios test 

  Background:
    Given User opens Register Page
    Then User clicks Register button

  @emptyUsername
  Scenario: Empty username
    Given User request to create "empty" username
    Given User inputs credentials created
    When User click submit button
    Then I verify registration failure "Please fill out this field"
    
  @emptyPassword
  Scenario: Empty password
  Given User request to create "alphabetsonly" username
    Given User request to create "emptyPassword" password
    Given User inputs credentials created
    When User click submit button
    Then I verify registration failure "Please fill out this field"
    
  @emptyRetypePassword
  Scenario: Empty retype password
    Given User request to create "alphabetsonly" username
    Given User request to create "alphanumbericSpecialChars" password
    Given User request to create "emptyRetypepassword" password
    Given User inputs credentials created
    When User click submit button
    Then I verify registration failure "Please fill out this field"

  @passwordMismatch
  Scenario: Password mismatch
    Given User request to create "alphabetsonly" username
    Given User request to create "passwordmismatch" password
    Given User inputs credentials created
    When User click submit button
    Then I verify registration failure "password_mismatch:The two password fields didn’t match."
    
  @151CharactersUsername
  Scenario: 151 character username
    Given User request to create "151_alphabets" username
    Given User request to create "alphanumbericSpecialChars" password
    Given User inputs credentials created
    When User click submit button
    # Expected error message is missing from DS algo test scenario sheet. Adding one per username hint
    Then I verify registration failure "Username should be 150 characters or fewer"


  @invalidSpecialCharUsername
  Scenario: Invalid special character username
    Given User request to create "invalidspecialchars" username
    Given User request to create "alphanumbericSpecialChars" password
    Given User inputs credentials created
    When User click submit button
    Then I verify registration failure "Please enter valid username"

  @shortLengthPassword
  Scenario: Short Password
    Given User request to create "alphabetsonly" username
    Given User request to create "shortlength" password
    Given User inputs credentials created
    When User click submit button
    Then I verify registration failure "Password should contain atleast 8 characters"
    
  @numbersOnlyPassword
  Scenario: Number only password
    Given User request to create "alphabetsonly" username
    Given User request to create "numbers" password
    Given User inputs credentials created
    When User click submit button
    # Expected error message is missing from DS algo test scenario sheet. Adding one per password hint
    Then I verify registration failure "Password can't be entirely numeric"