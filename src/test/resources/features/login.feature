Feature: Login Tests

  Scenario: Login using existing test
    Given I am in the homepage
    When I enter valid username "testJ123456" and password "Test@123Br1"
    And I click on Login Button
    Then Welcome message with my username "testJ123456" should be displayed

  Scenario: Login using incorrect password should fail
    Given I am in the homepage
    When I enter a valid username "testJ123456" with a random password
    And I click on Login Button
    Then Error message of incorrect password is shown