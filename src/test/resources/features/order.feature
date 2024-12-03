Feature: Retrieve all orders
  As an API client
  I want to retrieve all orders
  So that I can see the list of all available orders

  Scenario: Retrieve orders successfully
    Given there are existing orders in the system
    When I send a GET request to "/all"
    Then the response status should be 200
    And the response should contain a list of orders
    And each order should have a valid ID, customer name, and total amount

  Scenario: No orders available
    Given there are no orders in the system
    When I send a GET request to "/all"
    Then the response status should be 204
    And the response body should be empty