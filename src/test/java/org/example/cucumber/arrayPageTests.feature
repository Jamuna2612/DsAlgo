@arrayTests
Feature: Array page test scenarios
  This feature contains array page tests 

  @noLoginError
  Scenario: Array page no login error
    Given User is on home page
    When User clicks get started button for "array" topic
    Then User verify "array" topic click error message "You are not logged in"
    Then I close web driver
    
  Scenario Outline: Verify array page topic list when user login
    Given User is in login page
    Given User enters "<username>" and "<password>"
    When User click login
    Then Login "success" message is displayed
    When User clicks get started button for "array" topic
    Then User verify "array" topic link page is displayed
    And User clicks signout button
    Then I close web driver
    
		@loginArrayPageTopicsVerify
    Examples: Successful login
    |username|password|
    |mike555 |Open4You|
    
  @arrayTopicLinkTests
  Scenario Outline: Verify array page topic links and execute python code
    Given User is in login page
    Given User enters "<username>" and "<password>"
    When User click login
    Then Login "success" message is displayed
    When User clicks get started button for "array" topic
    When User clicks "array" page "<topicLink>"
    Then "array" page "<topicLink>" is displayed on web page
    When User executes "<pythonCodeType>" on "array" page and verify code output
    And User clicks signout button
    Then I close web driver
    
    @arrayTopic1SuccessOutput
    Examples: Arrays in Python topic link code run success
    | username | password | pythonCodeType    | topicLink        |
    | mike555  | Open4You | pythonCodeSuccess | Arrays in Python |
    
    @arrayTopic1ErrorOutput
    Examples: Arrays in Python topic link code run error
    | username | password | pythonCodeType    | topicLink        |
    | mike555  | Open4You | pythonCodeError   | Arrays in Python | 
    
    @arrayTopic2SuccessOutput
    Examples: Arrays Using List topic link code run success
    | username | password | pythonCodeType    | topicLink         |
    | mike555  | Open4You | pythonCodeSuccess | Arrays Using List |
    
    @arrayTopic2ErrorOutput
    Examples: Arrays Using List topic link with code run error
    | username | password | pythonCodeType    | topicLink         |
    | mike555  | Open4You | pythonCodeError   | Arrays Using List |     
     
    
    