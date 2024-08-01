Feature: Error validation

  @ErrorValidation
  Scenario Outline: Positive Tests of submitin the order
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message  is displayed

    Examples:
      | name                     | password    |
      | dfalvarez99@mailinator.com" | Careplafon11! |