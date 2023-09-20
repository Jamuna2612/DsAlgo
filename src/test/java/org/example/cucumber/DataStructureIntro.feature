@DSIntro
Feature: Validation of DataStructure Introduction

  Background: User explores the Data Structures Introduction Page

  Scenario: User not Sign In and clicks on Get Started button they should see a message You are not logged in
    Given User is not signed in and navigates to home page
    When User clicks on Get Started link
    Then You are not logged in message is displayed

  Scenario Outline: User after sign in clicks on Get Started button of Data Structures and they should land in the Data Structures Introduction Page
    Given User is in the Home page
    When The user clicks on Sign In button
    And User enters username <username> and password <password>
    And User clicks on login button
    And User clicks on Get Started button of Data Structure
    Then The user should land in Data Structures Introduction Page
    Examples:
      | username | password |
      |hope      | $imp1e123|

  Scenario: User after they clicked on Time Complexity link they should navigated to Time Complexity Page
    Given The user is in the Data structures-Introduction page
    When The user clicks on Time Complexity link
    Then The user should be redirected to Time Complexity page

  Scenario: User after they clicked on Practice Question link they should navigated to Practice Questions Page
    Given The user is in the Time Complexity page
    When The user clicks on Practice Question link
    Then The user should be redirected to Practice Questions Page


  Scenario: User after they clicked on Try Here link they should navigated to Assessment Page with tryEditor and Run button
    Given The user is in the Time Complexity page
    When The user clicks on Try Here button link
    Then The user should be redirected to a page having an tryEditor with a Run button to test

  Scenario: User after they entered valid python print statement Hello in tryEditor they should see the output printed below the Run button
    Given The user is in the Assessment page
    When The user enters valid python print statement <validPythonCode>
    And User Clicks on Run Button
    Then The user should see the run result below the Run button

  Scenario: User after they entered invalid python print statement in tryEditor they should see the SyntaxError popup when Click Run Button
    Given The user is in the Try Editor page
    When The user enters invalid python print statement <invalidPythonCode>
    And User Clicks on Run Button
    Then The user should see the SyntaxError popup