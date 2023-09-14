@registrationErrorTests
Feature: Registration error test cases
  This feature contains registration validation and error scenarios test 

  Background:
    Given User opens Register Page
    Then User clicks Register button

  @emptyUserInputRegister
  Scenario: Register click without any user input 
    When User click submit button
    Then I verify registration failure "Please fill out this field"
    Then I close web driver
  
  @emptyPasswordRegister  
  Scenario: Register click with passwords empty 
    When I input "username"
    When User click submit button
    Then I verify registration failure "Please fill out this field"
    Then I close web driver    

  @emptyReTypePasswordRegister    
  Scenario: Register click with re type password field empty 
    When I input "username"
    When I input "password"
    When User click submit button
    Then I verify registration failure "Please fill out this field"
    Then I close web driver

  Scenario Outline: Invalid chars username Register
    Given User enters "<usernameType>" with "<passwordType>" and "<userNamelength>"
    When User click submit button
    Then I verify registration failure "Please enter a valid username"
    Then I close web driver
        
    @invalidCharsUsernameRegister
    @defectTest
    Examples: Username with allowed special characters
    | usernameType  | passwordType | userNamelength | 
    | invalidChars  | alphabets    | 6              |        
    
    
  Scenario Outline: Password mismatch register error
    Given User enters "<usernameType>" with "<passwordType>" and "<userNamelength>"
    When User click submit button
    Then I verify registration failure "password_mismatch:The two password fields didn’t match."
    Then I close web driver
        
    @passwordMismatchRegister
    Examples: Username with allowed special characters
    | usernameType  | passwordType | userNamelength | 
    | upperCase     | mismatch     | 6              |    
    
  Scenario Outline: Short length password register error
    Given User enters "<usernameType>" with "<passwordType>" and "<userNamelength>"
    When User click submit button
    Then I verify registration failure "Password should contain atleast 8 characters"
    Then I close web driver
        
    @shortPasswordRegister
    @defectTest
    Examples: Username with allowed special characters
    | usernameType  | passwordType  | userNamelength | 
    | upperCase     | shortlength_7 | 6              |    
    
  Scenario Outline: Password with only numbers register error
    Given User enters "<usernameType>" with "<passwordType>" and "<userNamelength>"
    When User click submit button
    # Expected error message is missing from DS algo test scenario sheet. Adding one per password hint 
    Then I verify registration failure "Password can't be entirely numeric"
    Then I close web driver
        
    @numbersPasswordRegister
    @defectTest
    Examples: Username with allowed special characters
    | usernameType  | passwordType  | userNamelength | 
    | upperCase     | numeric       | 6              |   
    
    
  Scenario Outline: Username with more than 150 char
    Given User enters "<usernameType>" with "<passwordType>" and "<userNamelength>"
    When User click submit button
    # Expected error message is missing from DS algo test scenario sheet. Adding one per password hint 
    Then I verify registration failure "Username should have 150 characters or fewer"
    Then I close web driver
        
    @151CharLongUsernamePasswordRegister
    @defectTest
    Examples: Username with allowed special characters
    | usernameType  | passwordType  | userNamelength | 
    | upperCase     | alphabets     | 151            |        