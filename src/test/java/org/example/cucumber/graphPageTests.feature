@graphTests
Feature: Graph page test scenarios
  This feature contains graph page tests 

  @graphNoLoginError
  Scenario: Graph No Login
    Given User is on home page
    When User clicks get started button for "graph" topic
    Then User verify "graph" topic click error message "You are not logged in"
    
  Scenario Outline: Graph with Login, topics displayed 
    Given User is in login page
    Given User enters "<username>" and "<password>"
    When User click login
    Then Login "success" message is displayed
    When User clicks get started button for "graph" topic
    Then User verify "graph" topic link page is displayed
    
		@graphTopicsPageDisplayed
    Examples: Topic Displayed
    |username|password|
    |mike555 |Open4You|    
    
  Scenario Outline: Graph with Login, topic link verification
    Given User is in login page
    Given User enters "<username>" and "<password>"
    When User click login
    Then Login "success" message is displayed
    When User clicks get started button for "graph" topic
    When User clicks "graph" page "<topicLink>"
    Then "graph" page "<topicLink>" is displayed on web page
    When User executes "<pythonCodeType>" on "graph" page and verify code output
    #And User clicks signout button
    #Then I close web driver
    
    @topic1SuccessOutput
    Examples: Python Code Execution Success
    | username | password | pythonCodeType    | topicLink        |
    | mike555  | Open4You | pythonCodeSuccess | Graph 					 |
    
    @topic1ErrorOutput
    Examples: Python Code Execution Error
    | username | password | pythonCodeType    | topicLink        |
    | mike555  | Open4You | pythonCodeError   | Graph            | 
    
    @topic2SuccessOutput
    Examples: Python Code Execution Success
    | username | password | pythonCodeType    | topicLink             |
    | mike555  | Open4You | pythonCodeSuccess | Graph Representations |
    
    @topic2ErrorOutput
    Examples: Python Code Execution Error
    | username | password | pythonCodeType    | topicLink             |
    | mike555  | Open4You | pythonCodeError   | Graph Representations |     
     