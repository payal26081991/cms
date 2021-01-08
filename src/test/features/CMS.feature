Feature: Editorial Validations in CMS required for Synopsis****

  Scenario Outline: Verify that the Editorial Admin user is not able to Add/Edit the program, series, episode synopsis with provided words
    Given User launches CMS Application
    And User login with "<UserName>" and "<Password>"
    When User clicks on program link
    And User search for the "<program>"
    And User clicks program name
    And User enters the provided words in Desc, Long Description, Short Description, STB Desc and clicks submit
    Then Validation should be displayed to the user
    When User enters 'Series name', 'Series STB Desc' and 'Series Mobile Desc' and clicks Add button
    Then Validation should be displayed to the user
    When User clicks browser back button
    And Click Add/Edit Episode button
    And User Clicks Edit button
    And User Enters 'Ep Long Desc', 'STB Episode Description' and 'Short Ep. Desc
    Examples:
      | UserName       | Password | program    |
      | aashika.mallya | aashika  | hum paanch |