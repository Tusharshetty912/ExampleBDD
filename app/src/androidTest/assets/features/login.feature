Feature:Login
  Perform login using username and password

  Scenario Outline:Successful Login
    Given I have a login Activity
    When I input email "<email>"
    And I input password "<password>"
    And I press login button
    Then I should see status on the "<status>"
    Examples:
      | email                       | password | status  |
      | exampleEmail123@gmail.com   | 1234567  | Success |
      | exampleEmail456@gmail.com   | 1231231  | Failed  |
      | exampleEmail123@gmail.com   | 1234567  | Success |