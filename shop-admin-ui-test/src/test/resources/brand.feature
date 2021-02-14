Feature: Brands

  Scenario Outline: Successful Login to the page and add new Brand and logout after
    Given I open web browser
    When I navigate to login page
    And I provide username as "<username>" and password as "<password>"
    And I click on login button
    When I navigate to brand page
    And I click on create new button
    And I provide brandname as "<brandname>"
    And I click on submit button
    When Open dropdown menu
    And click logout button

    Examples:
      | username | password | brandname |
      | admin | admin | test |