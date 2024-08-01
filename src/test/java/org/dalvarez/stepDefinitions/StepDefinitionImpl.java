package org.dalvarez.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.dalvarez.pageobjects.*;
import org.dalvarez.testcomponents.BaseTest;
import org.testng.Assert;

public class StepDefinitionImpl extends BaseTest {

    LandingPage landingPage;
    ProductCatalog productCatalog;
    ConfirmationPage confirmationPage;

    @SneakyThrows
    @Given("I landed on Ecommerce Page")
    public void iLandedOnEcommercePage() {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.*) and password (.*)$")
    public void loggedInWithUsernameAndPassword(String username, String password) {
        productCatalog =  landingPage.loginApplication(username, password);
    }

    @SneakyThrows
    @When("^I add the product (.+) from cart$")
    public void iAddTheProductFromCart(String productName) {
        productCatalog.addProductToCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void checkoutAndSubmitTheOrder(String productName) {
        CartPage cartPage = productCatalog.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("India");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message  is displayed on ConfirmationPage")
    public void messageIsDisplayedOnConfirmationPage(String message) {
        Assert.assertTrue(confirmationPage.getConfirmationMessage().equalsIgnoreCase(message));
    }

    @Then("{string} message  is displayed")
    public void messageIsDisplayed(String message) {
        Assert.assertEquals( landingPage.getErrorMessage(), message);
    }

    @After
    public void after() {
        driver.close();
    }
}
