@registrationSuccessTests
Feature: Registration succesful
  This feature contains registration successful test scenarios 

  Background:
    Given User opens Register Page
    Then User clicks Register button

  Scenario Outline: Registration success
    Given User enters "<usernameType>" with "<passwordType>" and "<userNamelength>"
    When User click submit button
    Then User registration is successful
    Then I close web driver

    @lowercaseUsernameRegister
    Examples: Username with Lowercase Alphabets only
    | usernameType   | passwordType    | userNamelength | 
    | lowerCase      | alphabets       | 6              |

    @uppercaseUsernameRegister    
    Examples: Username with Uppercase Alphabets only
    | usernameType   | passwordType    | userNamelength | 
    | upperCase      | alphabets       | 6              |

    @numbersOnlyUsernameRegister
    Examples: Username with numbers only
    | usernameType   | passwordType    | userNamelength | 
    | numbers        | alphabets  | 6              |   
   
    @validSpecialCharUsernameRegister
    Examples: Username with allowed special characters
    | usernameType         | passwordType    | userNamelength | 
    | validSpecialChars  | alphabets  | 6              |        

    @mixedLettersUsernameRegister
    Examples: Username with mixed Alphabets only
    | usernameType   | passwordType | userNamelength |
    | mixedAlphabets | alphabets    | 6              |

    @alphanumericUsernameRegister
    Examples: Username with Alphanumeric only
    | usernameType   | passwordType  | userNamelength |
    | alphanumeric   | alphabets     | 6              |

