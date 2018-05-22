Feature: Gather the Accebility report of Website
  Verify if user is able to Login in to the site

  Scenario Outline: Open and Record Accessibility Report
    Given Opens webpage <wpg>
    When Runs Accessibility Report Tool
    Then Excel output is records the report
    
		Examples:
    |wpg|
    |"https://www.google.com/"|
    |"http://www.beckmaneserve.com"|