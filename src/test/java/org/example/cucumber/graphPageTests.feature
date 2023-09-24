@graphTests
Feature: Graph page test scenarios
  This feature contains graph page tests 

  @noLoginError
  Scenario: Graph page no login error
    Given User is on home page
    When User clicks get started button for "graph" topic
    Then User verify "graph" topic click error message "You are not logged in"
    Then I close web driver
    
  Scenario Outline: Verify graph page topic displayed when user login
    Given User is in login page
    Given User enters "<username>" and "<password>"
    When User click login
    Then Login "success" message is displayed
    When User clicks get started button for "graph" topic
    Then User verify "graph" topic link page is displayed
    And User clicks signout button
    Then I close web driver
    
		@loginGraphPageTopicsVerify
    Examples: Successful login
    |username|password|
    |mike555 |Open4You|    
    
    @graphTopicLinkTests
  Scenario Outline: Verify graph page topic links and execute python code
    Given User is in login page
    Given User enters "<username>" and "<password>"
    When User click login
    Then Login "success" message is displayed
    When User clicks get started button for "graph" topic
    When User clicks "graph" page "<topicLink>"
    Then "graph" page "<topicLink>" is displayed on web page
    When User executes "<pythonCodeType>" on "graph" page and verify code output
    And User clicks signout button
    Then I close web driver
    
    @graphTopic1SuccessOutput
    Examples: Graph in Python topic link code run success
    | username | password | pythonCodeType    | topicLink        |
    | mike555  | Open4You | pythonCodeSuccess | Graph 					 |
    
    @arrayTopic1ErrorOutput
    Examples: Graph in Python topic link code run error
    | username | password | pythonCodeType    | topicLink        |
    | mike555  | Open4You | pythonCodeError   | Graph            | 
    
    @arrayTopic2SuccessOutput
    Examples: Graph Representations Using List topic link code run success
    | username | password | pythonCodeType    | topicLink             |
    | mike555  | Open4You | pythonCodeSuccess | Graph Representations |
    
    @arrayTopic2ErrorOutput
    Examples: Graph Representations Using List topic link with code run error
    | username | password | pythonCodeType    | topicLink             |
    | mike555  | Open4You | pythonCodeError   | Graph Representations |     
     