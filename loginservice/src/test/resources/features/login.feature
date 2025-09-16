
Feature: User Login
  As a registered user
  I want to login with my credentials
  So that I can access protected resources
  Scenario: Successful login with valid credentials
    Given a user exists with username "Alice" and password "Password123!"
    When the user tries to login with username "Alice" and password "Password123!"
    Then the login should be successful

  Scenario: Failed login with valid credentials
    Given a user exists with username "Bob" and password "Password123!"
    When the user tries to login with username "Bob" and password "Passtt123!"
    Then the login should fail
