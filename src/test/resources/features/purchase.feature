Feature: Purchase Tests

  Background:
    Given I am in the homepage
    And I add a product to the cart
    And I check the product is added

  @smoke
  Scenario: A default user can purchase a product
    When I fill the form with valid information
    Then I check that the order information is correct

  @regression
  Scenario: A random user can purchase a product
    When I fill the form with random information
    Then I check that the order information is correct

  @regression
  Scenario: A product can be removed from cart
    When I add a second product
    And I check both products are added
    And I delete the second product
    Then I verify the product was removed correctly