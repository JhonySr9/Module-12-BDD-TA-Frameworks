Feature: Register Tests

  Background:
    Given I am in the homepage

  @smoke
  Scenario: A user cannot register an already registered username and password
    When I enter a new username and password
    And I verify the sign up was successful
    And I enter the same user and password
    Then I receive a message indicating the username is already used