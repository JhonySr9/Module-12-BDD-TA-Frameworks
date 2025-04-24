Feature: Login Tests

  Background:
    Given I am in the homepage

  @smoke
  Scenario Outline: Login using existing user
    When I enter valid username "<user>" and password "<password>"
    And I click on Login Button
    Then Welcome message with my username "<user>" should be displayed

    Examples:
      | user         | password    |
      | testJ123456  | Test@123Br1 |
      | john_doe1234 | qwerty123   |
      | support898   | SuPp0rT!    |
      | dev00002     | D3v3l0p$    |

  @smoke
  Scenario: Login using incorrect password should fail
    When I enter a valid username "testJ123456" with a random password
    And I click on Login Button
    Then Error message of incorrect password is shown