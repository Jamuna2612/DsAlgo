@arrayTests
Feature: Array page test scenarios
  This feature contains array page tests 

  @arrayNoLoginError
  Scenario: Array no login error
    Given User is on home page
    When User clicks get started button for "array" topic
    Then User verify "array" topic click error message "You are not logged in"
    
  Scenario Outline: Array with login, topic links displayed
    Given User is in login page
    Given User enters "<username>" and "<password>"
    When User click login
    Then Login "success" message is displayed
    When User clicks get started button for "array" topic
    Then User verify "array" topic link page is displayed
    
		@arrayTopicsPageDisplayed
    Examples: Successful login
    |username|password|
    |mike555 |Open4You|
    
  Scenario Outline: Array with login, topic link verification
    Given User is in login page
    Given User enters "<username>" and "<password>"
    When User click login
    Then Login "success" message is displayed
    When User clicks get started button for "array" topic
    When User clicks "array" page "<topicLink>"
    Then "array" page "<topicLink>" is displayed on web page
    When User executes "<pythonCodeType>" on "array" page and verify code output
    
    @topic1SuccessOutput
    Examples: Arrays in Python topic link code run success
    | username | password | pythonCodeType    | topicLink        |
    | mike555  | Open4You | pythonCodeSuccess | Arrays in Python |
    
    @topic1ErrorOutput
    Examples: Arrays in Python topic link code run error
    | username | password | pythonCodeType    | topicLink        |
    | mike555  | Open4You | pythonCodeError   | Arrays in Python | 
    
    @topic2SuccessOutput
    Examples: Arrays Using List topic link code run success
    | username | password | pythonCodeType    | topicLink         |
    | mike555  | Open4You | pythonCodeSuccess | Arrays Using List |
    
    @topic2ErrorOutput
    Examples: Arrays Using List topic link with code run error
    | username | password | pythonCodeType    | topicLink         |
    | mike555  | Open4You | pythonCodeError   | Arrays Using List |    
    
    @topic3SuccessOutput
    @newTest
    Examples: Arrays Using List topic link code run success
    | username | password | pythonCodeType    | topicLink                 |
    | mike555  | Open4You | pythonCodeSuccess | Basic Operations in Lists |
    
    @topic3ErrorOutput
    @newTest
    Examples: Arrays Using List topic link with code run error
    | username | password | pythonCodeType    | topicLink                 |
    | mike555  | Open4You | pythonCodeError   | Basic Operations in Lists | 
    
    @topic4SuccessOutput
    @newTest
    Examples: Arrays Using List topic link code run success
    | username | password | pythonCodeType    | topicLink             |
    | mike555  | Open4You | pythonCodeSuccess | Applications of Array |
    
    @topic4ErrorOutput
    @newTest
    Examples: Arrays Using List topic link with code run error
    | username | password | pythonCodeType    | topicLink             |
    | mike555  | Open4You | pythonCodeError   | Applications of Array |          
     
    
    