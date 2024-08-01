Feature: Purchase the order from the ecommerce Website

  Background:
    Given I landed on Ecommerce Page

    @Regression
  Scenario Outline: Positive Tests of submitting the order
    Given Logged in with username <name> and password <password>
    When I add the product <productName> from cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message  is displayed on ConfirmationPage

    Examples:
    | name                     | password    | productName |
    | dfalvarez98@mailinator.com" | Careplafon12* | ZARA COAT 3 |